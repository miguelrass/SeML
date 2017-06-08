package org.rass.implementation;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.xtext.xbase.lib.Pair;
import org.rass.ontologies.Ontologies;
import org.rass.swrl.ExternalSWRLBuiltins;

public class ToolDispatcher {
	private static final String local_log = "ToolDispatcher Log: ";
	
	public static Map<String,Long> loadedFiles = new HashMap<String,Long>();
	public static Map<String,Class<?>> loadedClasses = new HashMap<String,Class<?>>();
	
	public static void LoadToolClasses(){
		
        File folder = new File(Ontologies.Toolsfolder);
        if(!folder.exists() || folder.isFile()) return;
        
        ScanCompileLoadFolder(folder, Ontologies.ToolsPackage);
        
	}
	
	
	public static void ScanCompileLoadFolder(File folder, String packageStr){
		final String local_log = ToolDispatcher.local_log + "[ScanCompileLoadFolder] ";
		
		File root = new File(Ontologies.PROJfolder); 
		
		//------------------------------------------------- Get names of Classes, check compilable sources and loadable classes
		
		File[] listOfFiles = folder.listFiles(); //List of all files/folders
        Map<String,Long> javaPaths = new HashMap<String,Long>(); //Files to be compiled
        Map<String,Long> classPaths = new HashMap<String,Long>();
        Set<String> ClassIDs = new HashSet<String>(); //Classes to be loaded
        
        for (int i = 0; i < listOfFiles.length; i++) {
        	if(listOfFiles[i].isFile()){
        		String path = listOfFiles[i].getAbsolutePath();
        		Long lastMod = listOfFiles[i].lastModified();
        		if(path.endsWith(".java")){
        			javaPaths.put(path, lastMod);
        			
        			//Will load if it is not loaded yet or it is outdated (1/2)
        			if(loadedFiles.getOrDefault(path, new Long(0)).compareTo(lastMod) < 0)
        				ClassIDs.add(path);
        		}
        		else if(path.endsWith(".class")) 	classPaths.put(FilenameUtils.removeExtension(path), lastMod);
        	}
        }
        
        //------------------------------------------------- Excludes up-to-date Classes from compilation stage
        
        javaPaths.entrySet().removeIf(path-> {
        	Long compilationDate = classPaths.get(FilenameUtils.removeExtension(path.getKey()));
        	if(compilationDate != null) {
        		if(compilationDate.compareTo(path.getValue()) < 0) return false; // outdated
        	}else return false; //Was never compiled
        	return true; //up-to-date 
        });
        
        String[] javaNamesArray = javaPaths.keySet().toArray(new String[javaPaths.size()+2]);
        
        //------------------------------------------------- Adds soon to be compiled classes to load list (2/2)
        
        for (int i = 0; i < javaPaths.size(); i++) {
        	ClassIDs.add(javaNamesArray[i]);
        } 
        if(ClassIDs.isEmpty()) return; //nothing to compile/load
        
        //------------------------------------------------- Compile if needed
        
        javaNamesArray[javaPaths.size()]   = "-classpath";
        javaNamesArray[javaPaths.size()+1] = Ontologies.Toolsfolder + "/resources.jar";
        
        if(javaPaths.size() != 0){
        	
        	//------------------------------------------------- Copy built-in dependencies 
        	
        	File jarfile = new File(Ontologies.Toolsfolder + "/resources.jar");
        	
        	if(!jarfile.exists()){
        		InputStream in = ExternalSWRLBuiltins.class.getResourceAsStream("/resources/resources.jar"); 
	        	System.out.print(local_log + "Creating dependencies JAR...");
	        	final long startTime = System.currentTimeMillis(); //log execution time	
	        	
	        	try {
					Files.copy(in, jarfile.toPath(),StandardCopyOption.REPLACE_EXISTING );
				} catch (IOException e) {System.out.println("\n" + local_log + "Error creating JAR");}
	        	
	        	System.out.println("(" + (System.currentTimeMillis() - startTime) + "ms)");
        	}
        	

        	//------------------------------------------------- Compile uncompiled/outdated Built-ins
        	
        	System.out.print(local_log + "Compiling " + javaPaths.size() + " external class(es)...");
        	final long startTime = System.currentTimeMillis(); //log execution time	
        	JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        	
        	if(compiler == null){
    			System.err.println(local_log + "Java Compiler was not created. Check JDK usage.");
    			return;
    		}
        	
        	if(compiler.run(null, null, null, javaNamesArray)!=0) 
        		System.out.println(local_log + "Some files could not be compiled. If possible, previous classes will be used instead.");
        	
        	System.out.println("(" + (System.currentTimeMillis() - startTime) + "ms)");
        }   
        
        //------------------------------------------------- Create class loader

        URLClassLoader classLoader;
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader(); //To access bundle classes
		try {
			classLoader = URLClassLoader.newInstance(new URL[] { root.toURI().toURL() },contextClassLoader);
		} catch (MalformedURLException e) {
			System.err.println(local_log + "MalformedURLException: " + e.getMessage());
			return;
		}
		
		//------------------------------------------------- Load all classes and initialize them
		
		for(String IDpath: ClassIDs){
			String ID = FilenameUtils.getBaseName(IDpath);
			try {
	        	System.out.print(local_log + "[" + classLoader.getResource(packageStr + "/" + ID + ".java") + "] ");
	        	
	        	//the static initialization block should register the Built-in
	        	Class<?> cls = Class.forName(packageStr + "." + ID, true, classLoader);

	        	//add to loaded classes list (FilePath, LastModified)
	        	loadedFiles.put(IDpath, new File(IDpath).lastModified());
	        	
	        	//add to loaded classes list (ClassName, ClassObject)
	        	loadedClasses.put(ID, cls);
	        	
	        	/* Using interfaces:
	        	 * CustomSWRLBuiltin.CustomSWRLFunction instance = (CustomSWRLBuiltin.CustomSWRLFunction)a.newInstance();
	        	 * BuiltInRegistry.instance.registerBuiltIn("esrg:calculator#lol", new CustomSWRLBuiltin( instance ) );
	        	 */
	        	
			} catch (ClassNotFoundException e) { //loading error only affects the current built-in
				System.err.println(local_log + "Error loading class: " + e.getMessage());
			} catch (Throwable t){
				System.err.println(local_log + "Error loading class: " + t.getMessage());
			}
        }
	}
}
