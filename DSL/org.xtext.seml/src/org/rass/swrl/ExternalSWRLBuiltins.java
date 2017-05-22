package org.rass.swrl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import org.apache.commons.io.FilenameUtils;
import org.rass.ontologies.Ontologies;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.rules.builtins.BuiltInRegistry;

public class ExternalSWRLBuiltins {
	
	private static final OWLDataFactory fac = OWLManager.getOWLDataFactory();
	private static final String local_log = "ExternalSWRL Log: ";
	private static Map<String,Long> loadedClasses = new HashMap<String,Long>();
	
	public static final String OWL_C = "esrg:c#";
	private static final String OWL_Builtin = "http://www.w3.org/2003/11/swrl#Builtin";
	private static final String OWL_Args = OWL_C + "swrlArguments";
	private static final String OWL_hasArguments = OWL_C + "hasArguments";
	private static final String OWL_ArgLiteral = OWL_C + "literal";
	private static final String OWL_ArgIndividual = OWL_C + "individual";
	private static final String OWL_ArgUnbound = OWL_C + "unbound";
	
	public static final String OWL_Bi_no = OWL_C + "no";
	public static final String OWL_Bi_relEQ = OWL_C + "relEQ";
	public static final String OWL_Bi_relGT = OWL_C + "relGT";
	public static final String OWL_Bi_relGE = OWL_C + "relGE";
	public static final String OWL_Bi_relLT = OWL_C + "relLT";
	public static final String OWL_Bi_relLE = OWL_C + "relLE";
	public static final String OWL_Bi_notSame = OWL_C + "notSame";
	public static final String OWL_Bi_iListSum = OWL_C + "intListSum";
	public static final String OWL_Bi_fListSum = OWL_C + "floatListSum";
	private static final List<String> IgnoreBis = Arrays.asList(OWL_Bi_no, OWL_Bi_relEQ, OWL_Bi_relGT, OWL_Bi_relGE,OWL_Bi_relLT,OWL_Bi_relLE,OWL_Bi_notSame,OWL_Bi_iListSum,OWL_Bi_fListSum);

	public static void LoadBuiltins(){
		final String local_log = ExternalSWRLBuiltins.local_log + "[LoadBuiltins] ";
		
		File root = new File(Ontologies.PROJfolder); 
        File folder = new File(Ontologies.SWRLfolder);
        if(!folder.exists() || folder.isFile()) return;
        
        //------------------------------------------------- Get names of Built-ins, check compilable sources and loadable classes
        
        File[] listOfFiles = folder.listFiles(); //List of all files/folders
        Map<String,Long> javaPaths = new HashMap<String,Long>(); //Files to be compiled
        Map<String,Long> classPaths = new HashMap<String,Long>();
        Set<String> builtinIDs = new HashSet<String>(); //Classes to be loaded
        
        for (int i = 0; i < listOfFiles.length; i++) {
        	if(listOfFiles[i].isFile()){
        		String path = listOfFiles[i].getAbsolutePath();
        		Long lastMod = listOfFiles[i].lastModified();
        		if(path.endsWith(".java")){
        			javaPaths.put(path, lastMod);
        			
        			//Will load if it is not loaded yet or it is outdated (1/2)
        			if(loadedClasses.getOrDefault(path, new Long(0)).compareTo(lastMod) < 0)
        				builtinIDs.add(path);
        		}
        		else if(path.endsWith(".class")) 	classPaths.put(FilenameUtils.removeExtension(path), lastMod);
        	}
        }
        
        //------------------------------------------------- Excludes up-to-date Built-ins from compilation stage
        
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
			builtinIDs.add(javaNamesArray[i]);
        } 
        if(builtinIDs.isEmpty()) return; //nothing to compile/load
        
        //------------------------------------------------- Compile if needed
        
        javaNamesArray[javaPaths.size()]   = "-classpath";
        javaNamesArray[javaPaths.size()+1] = Ontologies.SWRLfolder + "/builtins.jar";
        
        if(javaPaths.size() != 0){
        	
        	//------------------------------------------------- Copy built-in dependencies 
        	
        	File jarfile = new File(Ontologies.SWRLfolder + "/builtins.jar");
        	
        	if(!jarfile.exists()){
        		InputStream in = ExternalSWRLBuiltins.class.getResourceAsStream("/resources/builtins.jar"); 
	        	System.out.print(local_log + "Creating SWRL dependencies JAR...");
	        	final long startTime = System.currentTimeMillis(); //log execution time	
	        	
	        	try {
					Files.copy(in, jarfile.toPath(),StandardCopyOption.REPLACE_EXISTING );
				} catch (IOException e) {System.out.println("\n" + local_log + "Error creating JAR");}
	        	
	        	System.out.println("(" + (System.currentTimeMillis() - startTime) + "ms)");
        	}
        	

        	//------------------------------------------------- Compile uncompiled/outdated Built-ins
        	
        	System.out.print(local_log + "Compiling " + javaPaths.size() + " external Built-in(s)...");
        	final long startTime = System.currentTimeMillis(); //log execution time	
        	JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        	
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
		//Thread.currentThread().setContextClassLoader(classLoader);
		
		//------------------------------------------------- Load all classes and initialize them
		
		for(String IDpath: builtinIDs){
			String ID = FilenameUtils.getBaseName(IDpath);
			try {
	        	System.out.print(local_log + "[" + classLoader.getResource(Ontologies.SWRLPackage + "/" + ID + ".java") + "] ");
	        	
	        	//the static initialization block should register the Built-in
	        	Class<?> a = Class.forName(Ontologies.SWRLPackage + "." + ID, true, classLoader);
	        	CustomSWRLBuiltin.CustomSWRLFunction instance = (CustomSWRLBuiltin.CustomSWRLFunction)a.newInstance();
	        	BuiltInRegistry.instance.registerBuiltIn("esrg:calculator#lol", new CustomSWRLBuiltin( instance ) );
	        	//add to loaded classes list
	        	loadedClasses.put(IDpath, new File(IDpath).lastModified());
	        	
			} catch (ClassNotFoundException e) { //loading error only affects the current built-in
				System.err.println(local_log + "Error loading class: " + e.getMessage());
			} catch (Throwable t){
				System.err.println(local_log + "Error loading class: " + t.getMessage());
			}
        }
	}
	
	public static void GenerateTemplates(PelletReasoner reasoner) throws IOException{
		final String local_log = ExternalSWRLBuiltins.local_log + "[GenerateTemplates] ";
		
		//------------------------------------------------- Prepare destination folder
		
		final File templatesFolder = new File(Ontologies.TEMPLfolder);
		
		if(!templatesFolder.exists()){ //Check if folder exists, create it otherwise
			if(!templatesFolder.mkdir())
				throw new IOException("Error creating directory: " + templatesFolder); //Error occurred while creating generated files directory	
	    }
		
		//------------------------------------------------- Prepare future SWRL built-in class folder
		
		final File classFolder = new File(Ontologies.SWRLfolder);
		
		if(!classFolder.exists()){ //Check if folder exists, create it otherwise
			if(!classFolder.mkdir())
				throw new IOException("Error creating directory: " + classFolder); //Error occurred while creating generated files directory	
	    }
		
		//------------------------------------------------- Get Built-ins and Argument Groups
		
		Set<OWLNamedIndividual> builtins = reasoner.getInstances(fac.getOWLClass(IRI.create(OWL_Builtin)), false).getFlattened();
		builtins.removeIf(b -> IgnoreBis.contains(b.getIRI().toString())); //remove internal built-ins
		 
		if(builtins.isEmpty()) return;
		System.out.println(local_log + "Generating SWRL Templates...");
		
		Set<OWLNamedIndividual> argGroups = reasoner.getInstances(fac.getOWLClass(IRI.create(OWL_Args)), false).getFlattened();
			
		argMap_apply = new HashMap<String,String>();
		argMap_isApplicable = new HashMap<String,String>();
		
		argGroups.forEach(argGroup -> MapArguments(argGroup, reasoner));

		//------------------------------------------------- Generate argument related strings for the Templates

		for(OWLNamedIndividual b : builtins){
			
			StringBuilder applySB = new StringBuilder(500); //set initial capacity to 500 chars
			StringBuilder isApplicableSB = new StringBuilder(500); //set initial capacity to 500 chars
			String builtinName = Ontologies.GetShortIRI(b.getIRI());
			
			// Get all Argument groups for the current built-in
			Set<OWLNamedIndividual> linkedArgGroups = reasoner.getObjectPropertyValues(b, fac.getOWLObjectProperty(IRI.create(OWL_hasArguments))).getFlattened();
			if(linkedArgGroups.isEmpty()){
				System.out.println(local_log + "Built-in \"" + builtinName + "\" has no declared arguments. No template will be generated.");
				continue;
			}
			
			// Merge all Argument groups in one string for the current built-in
			for(OWLNamedIndividual gr : linkedArgGroups){
				applySB.append(argMap_apply.get(gr.getIRI().toString()));
				isApplicableSB.append(argMap_isApplicable.get(gr.getIRI().toString()));
				applySB.append(" && "); isApplicableSB.append(" || ");
			}
			
			// Remove the last logical operators
			applySB.setLength(applySB.length()-4); isApplicableSB.setLength(isApplicableSB.length()-4);
			
			String fileStr = TemplateGenerator.WriteTemplate(b.getIRI().toString(), builtinName, applySB.toString(), isApplicableSB.toString());
			
			// Save Template for the current built-in
			File templ = new File(Ontologies.TEMPLfolder + "/" + builtinName + ".java");
			templ.createNewFile();
			PrintWriter writer = new PrintWriter(templ,"UTF-8");
			writer.write(fileStr);
			writer.close();
		}
		
	}
	
	private static Map<String,String> argMap_apply = null;
	private static Map<String,String> argMap_isApplicable = null;
	private static final OWLAnnotationProperty arg1 = fac.getOWLAnnotationProperty(IRI.create(OWL_C + "Argument_1"));
	private static final OWLAnnotationProperty arg2 = fac.getOWLAnnotationProperty(IRI.create(OWL_C + "Argument_2"));
	private static final OWLAnnotationProperty arg3 = fac.getOWLAnnotationProperty(IRI.create(OWL_C + "Argument_3"));
	private static final OWLAnnotationProperty arg4 = fac.getOWLAnnotationProperty(IRI.create(OWL_C + "Argument_4"));
	private static final OWLAnnotationProperty arg5 = fac.getOWLAnnotationProperty(IRI.create(OWL_C + "Argument_5"));
	private static final OWLAnnotationProperty arg6 = fac.getOWLAnnotationProperty(IRI.create(OWL_C + "Argument_6"));
	private static final OWLAnnotationProperty arg7 = fac.getOWLAnnotationProperty(IRI.create(OWL_C + "Argument_7"));
	private static final OWLAnnotationProperty arg8 = fac.getOWLAnnotationProperty(IRI.create(OWL_C + "Argument_8"));
	private static final OWLAnnotationProperty arg9 = fac.getOWLAnnotationProperty(IRI.create(OWL_C + "Argument_9"));
	
	private static void MapArguments(OWLNamedIndividual argGroup, PelletReasoner reasoner){
		final String local_log = ExternalSWRLBuiltins.local_log + "[MapArguments] ";
		
		int NoOfArgs = 0;
		String[] argTypes = new String[9];

		for (OWLAnnotationAssertionAxiom annotation : reasoner.getRootOntology().getAnnotationAssertionAxioms(argGroup.getIRI())) { //gets annotations associated with the class		
			String argNo = annotation.getProperty().getIRI().toString();

			if(!argNo.startsWith(OWL_C + "Argument_")) continue; //Unrelated annotation
				
			int no = argNo.charAt(argNo.length()-1) - '1'; //Argument Number (last ascii char to int)
			if(no < 0 || no > 9 ){ System.out.println("Error parsing number from annotation property: " + argNo); return;} //Parse Check
			argTypes[no] = annotation.getValue().toString(); //Argument Type
			NoOfArgs++;
		}			
			
		//Strings which will be used to generate the template
		StringBuilder applySB = new StringBuilder(200); //set initial capacity to 200 chars
		StringBuilder isApplicableSB = new StringBuilder(200); //set initial capacity to 200 chars
		applySB.append("(args.length != " + NoOfArgs + " || "); isApplicableSB.append("(boundPositions.length == " + NoOfArgs + " && ");
		
		for(int i=0; ; ){
			switch (argTypes[i]) {
				case OWL_ArgIndividual:
					applySB.append("!args[" + i + "].isIndividual()");	isApplicableSB.append("boundPositions[" + i + "]"); break;
				case OWL_ArgLiteral:
					applySB.append("!args[" + i + "].isLiteral()");		isApplicableSB.append("boundPositions[" + i + "]"); break;
				case OWL_ArgUnbound:
					applySB.append("args[" + i + "] != null");			isApplicableSB.append("!boundPositions["+ i + "]"); break;
				default:
					applySB.append("/* Argument Parse Error */");		isApplicableSB.append("/* Argument Parse Error */");break;
			}
			if(++i<NoOfArgs){applySB.append(" || "); isApplicableSB.append(" && "); }else break;
		}
		
		applySB.append(')'); isApplicableSB.append(")");
		argMap_apply.put(argGroup.getIRI().toString(), applySB.toString());
		argMap_isApplicable.put(argGroup.getIRI().toString(), isApplicableSB.toString());
	}
	
	
}