package ex_tools;

import java.util.Set; 
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.io.IOException;
import java.util.Map.Entry;

/**
* Tool
*/
public class ReplaceTool {
	
	private static Path srcPath = null;
	private static Path genPath = null;
	private static HashMap<String, String> openFiles = new HashMap<String, String>();
	
	static{
		System.out.println("ReplaceTool was loaded");
	}
	
	public static String checkExistence(ArrayList<List<String>> cmd) throws IOException{
		
		if(cmd.size() != 1) return "CheckExistence only allows 1 argument: " + cmd;
		
		//Iterate all arguments of individual (each one represents a file+variable)
		//If two arguments edit the same (file+variable) only the most priority one will prevail
		for(String rawArgs : cmd.get(0)){
			
			String[] args = rawArgs.split(",");
			String content = getFile(rawArgs, args[0]);
			
			//Check existence 
			if(!content.contains(args[1])) return args[0] + " DOES NOT CONTAIN: " + args[1];
			
			//Add to open files
			openFiles.put(args[0], content);
		}
		return "Contents were checked: " + cmd;
	}
	
	public static String replace(ArrayList<List<String>> cmd) throws IOException{
		
		if(cmd.size() != 2) return "Replace only allows 2 arguments: " + cmd;
		
		//Iterate all arguments of individual (each one represents a file+variable)
		//If two arguments edit the same (file+variable) only the most priority one will prevail
		for(String rawArgs : cmd.get(0)){
			
			String[] args = rawArgs.split(",");
			String content = getFile(rawArgs, args[0]);
			
			//Replace 
			openFiles.put(args[0], content.replaceAll(args[1], cmd.get(1).get(0)));
		}
		return "New Replacement: " + cmd;
	}
	
	private static String getFile(String rawArgs, String file) throws IOException{
		
		Path srcFile = srcPath.resolve(file);

		if(!Files.exists(srcFile)) throw new IOException("File not found: " + srcFile.toString());
		
		String content = null;
		
		//Check if file is already open
		if(openFiles.containsKey(file)){
			return openFiles.get(file);
		}else{
			return new String(Files.readAllBytes(srcFile), StandardCharsets.UTF_8);
		}
		
	}
	

	
	public static boolean setSrc(String path){
		srcPath = Paths.get(path);
		genPath = srcPath.resolve("gen/");
		return true;
	}
	
	public static String apply() throws IOException{

		//Save files to gen folder
		for(Entry<String, String> f : openFiles.entrySet()){
			Path p = genPath.resolve(f.getKey());
			
			//Create gen folder and required subfolders
			if (!Files.exists(p.getParent())) Files.createDirectories(p.getParent());
			Files.write(p, f.getValue().getBytes(StandardCharsets.UTF_8));
		}
		
		openFiles = new HashMap<String, String>(); //Reset open files
		return "Replacements were applied!";
	}
}
