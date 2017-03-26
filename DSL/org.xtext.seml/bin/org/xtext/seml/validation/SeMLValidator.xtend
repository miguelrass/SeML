/*
 * generated by Xtext 2.10.0
 */
package org.xtext.seml.validation

import java.io.BufferedReader
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader
import java.util.Arrays
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.emf.ecore.resource.ResourceSet
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.CheckType
import org.rass.ontologies.MasterOntology
import org.rass.ontologies.Ontologies
import org.xtext.seml.seML.Component
import org.xtext.seml.seML.ImportModel
import org.xtext.seml.seML.Individual
import org.xtext.seml.seML.MainModel
import org.xtext.seml.seML.MetaIndividual
import org.xtext.seml.seML.Model
import org.xtext.seml.seML.Sentence
import org.xtext.seml.seML.Relation
import org.xtext.seml.seML.SeMLPackage
import org.xtext.seml.seML.UseCharacteristic
import org.xtext.seml.SeMLStandaloneSetup
import org.eclipse.xtext.resource.XtextResourceSet
import com.google.inject.Injector
import org.eclipse.xtext.resource.XtextResource
import java.io.InputStream
import java.io.ByteArrayInputStream
import org.rass.ontologies.Anomaly
import org.eclipse.emf.ecore.EReference
import org.eclipse.emf.ecore.EStructuralFeature
import org.semanticweb.owlapi.apibinding.OWLManager
import org.eclipse.emf.ecore.EObject
import org.xtext.seml.seML.AnyIndividual
import org.eclipse.xtext.validation.AbstractDeclarativeValidator
import java.util.List
import javax.tools.JavaCompiler
import javax.tools.ToolProvider
import java.net.URL
import java.nio.file.Files
import java.nio.file.StandardCopyOption
import org.rass.swrl.CustomSWRLBuiltin
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Date
import org.rass.ontologies.CharacteristicsSolver
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.resource.Resource.Diagnostic

/**
 * This class contains custom validation rules. 
 *
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
class SeMLValidator extends AbstractSeMLValidator {
	
	static String local_log = "Validator Log: ";

	public static val INVALID_NAME = 'invalidName'
	public static val GET_AXIOMS = "GetAxioms";
	public static val FIX_GENERATED = "FixGeneratedName";	
	public static val GENERATE_SOLUTION = "GenerateSolution";
	public static val USE_SOLUTION = "UseSolution";

	
	@Check(CheckType.FAST) 
	def checkIndividual(Individual ind){
		if(ind.name.contains('#')) error("Individual name cannot contain \"#\"", SeMLPackage.Literals.ANY_INDIVIDUAL__NAME);
	}
	
	@Check(CheckType.FAST) 
	def checkModelVal(MainModel m){
		val String local_log = local_log + "[checkModelVal] ";
		
		val EList<Diagnostic> er = m.eResource.errors
		if(er.size != 0){
			System.err.println(local_log + "Model contains errors:")
			er.forEach[e | System.err.println(local_log + e)]
			return
		}
		
		try {
			checkModel(m)
		} catch (Exception e) {
			System.err.println(local_log + "Internal Error: " + e.toString)
		}
	}
	
	def checkModel(MainModel m){
		val String local_log = local_log + "[checkModel] ";
		var String[] inconsistencyReport = null;
		if(!CheckImports(m)) return; //return if imports are invalid
		
		System.out.print(local_log + "Validating model...");
		
		val DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
	    val Date date = new Date();
	    System.out.println("("+dateFormat.format(date)+")");
		
		val individualsList = EcoreUtil2.getAllContentsOfType(m, Individual);
		val relationsList = EcoreUtil2.getAllContentsOfType(m, Relation);
		val useList = EcoreUtil2.getAllContentsOfType(m, UseCharacteristic);
		
		//Load Master ontology file and initialize OWLAPI objects
		try { 
			MasterOntology.loadMasterOntology(new File(Ontologies.GENfolder + Ontologies.masterNAME));
		} catch (IOException e) {
			error("Error loading master ontology file: " + e.message, m.imports.last, SeMLPackage.Literals.IMPORT__NAME); return;
		}			
		
		//Add all individuals to master ontology (check for duplicates)
		individualsList.forEach[i | MasterOntology.addIndividual(i)];
		
		//Add all relations to master ontology (check for inconsistency)
		for(Relation r: relationsList){
			if(!MasterOntology.addRelation(r)) 
				{System.out.println(local_log + "Aborted. Model contains relation errors.");return;}
				//if(!ReportAnomalies(r, SeMLPackage.Literals.RELATION__OBJ)) return;
		}
		
		//Reason (and explain if inconsistent)
		if(MasterOntology.ReasonAndExplainMaster()){
			if(!ReportAnomalies(m,individualsList, relationsList)) return;
		}
		
		//Import keywords file to extract MetaIndividuals and all IRIs (to aid solution generation)
		val ImportModel importRoot = getImportModel(m.eResource, Ontologies.GENfile_relpath);
		if(importRoot === null) {error("Error loading keywords file: " + Ontologies.GENfile.absolutePath, m.imports.last, SeMLPackage.Literals.IMPORT__NAME);return;}	
		val EList<Diagnostic> er = importRoot.eResource.errors
		if(er.size != 0){
			System.err.println(local_log + "Keywords file contains errors:")
			er.forEach[e | System.err.println(local_log + e)]
			return
		}
		val MetaIndividualsList = importRoot.metaIndividuals //Get all meta individuals
		MasterOntology.cacheIRIs(importRoot, individualsList); //must be done before calling checkRelationRestrictions

		//Check if individuals that were created in Prot�g� meet their class's restrictions
		//Note: these errors are not detected before due to the Open World Assumption 	
		System.out.print(local_log + "Checking individual restrictions");
		for(MetaIndividual i: MetaIndividualsList){ 
			for(String s: i.cls){ //Iterate each class of an individual and check the restrictions of each class
				inconsistencyReport = MasterOntology.CheckRelationRestrictions(s, i.iri);
				if(inconsistencyReport !== null) {
					if(inconsistencyReport.get(1).empty){error("Metamodel Individual: " +  i.iri + "\n" + inconsistencyReport.get(0), m.imports.last, SeMLPackage.Literals.IMPORT__NAME);}
					else error("Metamodel Individual: " +  i.iri + "\n" + inconsistencyReport.get(0), m.imports.last, SeMLPackage.Literals.IMPORT__NAME, GENERATE_SOLUTION, inconsistencyReport.get(1));
					return;
				}
			}
		}
		
		//Perform the same Check for individuals created in the DSL
		for(Individual i: individualsList){
			for(Component c: i.cls){ //Check individual for multiple classes
				inconsistencyReport = MasterOntology.CheckRelationRestrictions(c.iri, MasterOntology.OWL_Master + "#" + i.getName());
				if(inconsistencyReport !== null) {
					if(inconsistencyReport.get(1).empty){ error(inconsistencyReport.get(0), i, SeMLPackage.Literals.ANY_INDIVIDUAL__NAME);}
					else error(inconsistencyReport.get(0), i, SeMLPackage.Literals.ANY_INDIVIDUAL__NAME, GENERATE_SOLUTION, inconsistencyReport.get(1)); //Create solution
					return;
				}
			}
		}
		System.out.print("\n"); //add \n to "Checking class restrictions"
		
		//Build characteristics tree (at least, the default characteristic is used)
		var EObject eo; var EStructuralFeature sf;
		try {
			//If the are no "use sentences", use the last import as agent
			if(useList.isEmpty){eo = m.imports.last; sf = SeMLPackage.Literals.IMPORT__NAME;} 
			else{eo = useList.last; sf = SeMLPackage.Literals.USE_CHARACTERISTIC__NAME;}
			
			if(!MasterOntology.BuildMastersCharacteristicsTree(useList)){
				error("There are unsolved Characteristics variabilites: \n" +  CharacteristicsSolver.chrProblem, eo, sf, 
					USE_SOLUTION, CharacteristicsSolver.chrSolution);
				return;
			}	
		} catch (Exception e) {
			error("Error related with Characteristics: \n" +  e.getMessage, eo, sf);
			return;
		}


		//Perform the equivalent check for characteristics in use
		System.out.print(local_log + "Checking characteristics restrictions");
		for(String ch: CharacteristicsSolver.GetRequiredCharacteristics){
			inconsistencyReport = MasterOntology.CheckModelRestrictions(ch); 
			if(inconsistencyReport !== null) {
				//get the responsible agent
				val UseCharacteristic uc = useList.findFirst[c | c.name.iri == ch]
				if(uc === null){eo = m.imports.last; sf = SeMLPackage.Literals.IMPORT__NAME;} 
				else{eo = uc; sf = SeMLPackage.Literals.USE_CHARACTERISTIC__NAME;}
				//throw error (and solution)
				if(inconsistencyReport.get(1).empty){ error("Characteristic: " +  ch + "\n" + inconsistencyReport.get(0), eo, sf);}
				else error("Characteristic: " +  ch + "\n" + inconsistencyReport.get(0), eo, sf, GENERATE_SOLUTION, inconsistencyReport.get(1));
				return;
			}
		}

		System.out.println("\n" + local_log + "Done.");	
	}
	
	/**
	 * Very basic function to dispatch the issue to its agent (only implemented for non-meta individuals)
	 */
	def UseCharacteristic RouteCharacteristicToAgent(List<UseCharacteristic> chrList, String chIRI){
		return chrList.findFirst[c | c.name.iri == chIRI]
	}
	
	
	/**
	 * Auxiliary function to return anomalies for individual creation and relation instantiation
	 * @return returns false if the model is inconsistent
	 */
	def boolean ReportAnomalies(MainModel m, List<Individual> individualsList, List<Relation> relationsList){ //m.imports.last, SeMLPackage.Literals.IMPORT__NAME
		val String local_log = local_log + "[checkModel] ";
		var String issue = Anomaly.getAnomalies(); //get inconsistency/unsatisfiability
		if(issue !== null) {RouteIssueToAgent(m, issue, individualsList, relationsList, 1); return false;}
		issue = Anomaly.getErrors();
		if(issue !== null) {RouteIssueToAgent(m, issue, individualsList, relationsList, 2);}
		issue = Anomaly.getWarnings();
		if(issue !== null) {RouteIssueToAgent(m, issue, individualsList, relationsList, 3);}
		issue = Anomaly.getInfos();
		if(issue !== null) {RouteIssueToAgent(m, issue, individualsList, relationsList, 4);}
		return true;
	}
	
	/**
	 * Very basic function to dispatch the issue to its agent (only implemented for non-meta individuals)
	 */
	def void RouteIssueToAgent(MainModel m, String issue, List<Individual> individualsList, List<Relation> relationsList, int type){
		for(Individual i: individualsList){
			if(issue.contains(i.name)){
				DisplayAnomalies(" (related with this individual):\n" + issue, i, SeMLPackage.Literals.ANY_INDIVIDUAL__NAME, type); return;
			}
		}
		DisplayAnomalies(":\n" + issue, m.imports.last, SeMLPackage.Literals.IMPORT__NAME, type);
	}

	def void DisplayAnomalies(String s, EObject eo, EStructuralFeature eRef, int type){
		val String local_log = local_log + "[checkModel] ";
		switch (type) {
			case 1: {error("Anomaly detected" + s, eo, eRef);}
			case 2: {error("Error inferred" + s, eo, eRef);}
			case 3: {warning("Warning inferred" + s, eo, eRef);}
			case 4: {warning("Information inferred" + s, eo, eRef);}//System.out.println(local_log + "Information detected" + s);}
		}
	}

	
	/**
	 * Load and parse ImportModel. This method loads the file on-demand
	 * if the model contains no cross-references.
	 * 
	 * @param contextResource		Absolute file path of the ImportsModel
	 * @param importURIAsString		Absolute file path of the ImportsModel
	 * @return	the ImportModel or null in case of failure
	 */

	def ImportModel getImportModel(Resource contextResource, String importURIAsString) {
		val URI importURI = URI?.createURI(importURIAsString)
		val URI contextURI = contextResource?.getURI
		val URI resolvedURI = importURI?.resolve(contextURI)
		val ResourceSet contextResourceSet = contextResource?.resourceSet
		val Resource resource = contextResourceSet?.getResource(resolvedURI, true)
		return resource?.allContents?.head as ImportModel	
	}
	
	
	/**
	 * Auxiliary function of checkModel, to check imports and create the master ontology
	 * 
	 * @param m		MainModel
	 * @return		True if imports are valid 
	 */
	def boolean CheckImports(MainModel m){ //detects changes in Imported ontologies
		val String local_log = local_log + "[checkModelImports] ";
		var long mostRecentFile = 0;

    	
    	//CustomSWRLBuiltin.debug_wow;
    	
    	//Check if there are any imports
		if(m.imports.empty) return false;
		
		//Create imports paths list
		val String[] pathslist = newArrayOfSize(m.imports.length); var int cnt = 0;
		
		//Check if every file exists before proceeding
		for(i: m.imports){
			val File ontfile = new File(i.getName());
			if(!ontfile.exists || ontfile.isDirectory) {error("Ontology file was not found", i, SeMLPackage.Literals.IMPORT__NAME); return false;}
			if(mostRecentFile < ontfile.lastModified) mostRecentFile = ontfile.lastModified;
			pathslist.set(cnt++,i.getName());
		}
		Arrays.sort(pathslist); //Sort ontologies paths to compare them with the generated file's list
				
		//Generate paths for current SEML file and generated SEML file, for a given ontology
		Ontologies.populatePaths(m);
		
		//Check if generated file is up-to-date
		if(Ontologies.GENfile.exists && Ontologies.GENfile.file){ //Check if file exists
		
			//Check if generated file is older than the most recent ontology
			if(mostRecentFile.compareTo(Ontologies.GENfile.lastModified) < 0){
				
				try {
					//Check if every imported file matches exactly with the generated file summary
					val FileInputStream fis = new FileInputStream(Ontologies.GENfile); //Open generated file
					val BufferedReader br = new BufferedReader(new InputStreamReader(fis)); //Construct BufferedReader from InputStreamReader			 
					
					var String line = br.readLine(); cnt = 0;
					var int SourceFilesNo = Integer.valueOf(line.substring(Ontologies.GENfirstline.length));
					var boolean different = false;
					
					if(pathslist.size == SourceFilesNo){ //Check if number of source files matches
						while ((line = br.readLine()) != "*/") { //Read every line until the end of the commentary
							if(!pathslist.get(cnt++).equals(line)) different = true;
						}
						if(!different) {
							val File masterfile = new File(Ontologies.GENfolder + Ontologies.masterNAME);
							if(masterfile.exists && masterfile.file) {br.close(); return true;}
							else System.out.println(local_log + "Master Ontology file was deleted. Creating a new one...");	
						}
						else System.out.println(local_log + "Ontology sources have changed. Updating DSL keywords...");	
						
					} else System.out.println(local_log + "Number of ontology sources has changed. Updating DSL keywords...");	
					
					br.close();
				} catch (Exception e) {
					System.out.println(local_log + "Error while reading generated file: " + e.message);
					System.out.println(local_log + "Repairing file...");		   	
				}
				
			} else System.out.println(local_log + "Changes in ontologies detected. Updating DSL keywords...");	
		} else System.out.println(local_log + "Importing DSL keywords for the first time...");
		
		try {
			Ontologies.ParseOntologies(pathslist); //If there are no errors, the file was generated
		} catch (IOException e) {
			System.out.println(local_log + e.message);
			error(e.message, m.imports.last, SeMLPackage.Literals.IMPORT__NAME); //Error while loading or parsing ontology
			return false;
		}   	
		return true;
	}
	
}
