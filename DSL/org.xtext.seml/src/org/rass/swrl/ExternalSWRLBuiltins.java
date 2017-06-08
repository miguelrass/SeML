package org.rass.swrl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;
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
import org.rass.implementation.ToolDispatcher;
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
		
        File folder = new File(Ontologies.SWRLfolder);
        if(!folder.exists() || folder.isFile()) return;

        ToolDispatcher.ScanCompileLoadFolder(folder, Ontologies.SWRLPackage);
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