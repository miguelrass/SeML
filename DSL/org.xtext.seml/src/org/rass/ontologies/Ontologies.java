package org.rass.ontologies;

import org.eclipse.xtext.validation.Check;
import java.io.File;
import org.xtext.seml.seML.SeMLPackage;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntologyDocumentAlreadyExistsException;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semanticweb.owlapi.model.OWLClass;
import com.clarkparsia.pellet.owlapi.PelletReasoner;
import com.clarkparsia.pellet.owlapi.PelletReasonerFactory;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.OWLOntologyMerger;
import org.eclipse.xtext.EcoreUtil2;
import org.xtext.seml.seML.Import;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
import java.io.NotActiveException;
import java.io.IOException;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;

public class Ontologies {
	
	private static final String local_log = "Ontologies Log: ";
	public static final String OWL_Upper = "esrg:upper#";
	public static final String OWL_Nothing = "http://www.w3.org/2002/07/owl#Nothing";
	public static final String OWL_Thing = "http://www.w3.org/2002/07/owl#Thing";
	public static final String OWL_TopObjProperty = "http://www.w3.org/2002/07/owl#bottomObjectProperty"; //Note: reasoner.getTopObjectPropertyNode().iterator().next();
	public static final String OWL_Event = OWL_Upper + "Event";
	public static final String OWL_Entity = OWL_Upper + "Entity";
	public static final String OWL_Process = OWL_Upper + "Process";
	public static final String OWL_Property = OWL_Upper + "Property";
	public static final String OWL_Characteristic = OWL_Upper + "Characteristic";
	public static final String OWL_Goal = OWL_Upper + "Goal";
	public static final String OWL_Feature = OWL_Upper + "Feature";
	public static final String OWL_Annotation_NONInstantiable = OWL_Upper + "NonInstantiable";
	 
	public static String GENfile_NAME = "imports.seml";
	public static File GENfile = null; //generated file object (absolute path)
	public static String GENfolder = null; //generated folder (absolute path)
	public static final String GENfirstline = "/* Automatically generated file. Source files: ";
	
	
	public static String SEMLfile_relpath = null; // 		"rPath/____.seml"
	public static IFile SEMLfile_object = null;
	public static String SEMLGENfolder_abspath = null; // 	".../rPath/____.seml.gen/"
	public static String SEMLGENfolder_relpath = null; // 	"____.seml.gen/" (without rPath)
	public static String SEMLGENfile_name = null; //		"____.dat"
	public static File SEMLGENfile_abspath = null; // 		".../rPath/____.seml.gen/____.dat"
	public static String SEMLGENfile_relpath = null; //		"____.seml.gen/____.dat" (without rPath)
	
	

	
	public static void ParseOntologies(String[] imports) throws IOException {
		final String local_log = Ontologies.local_log + "[ParseOntologies] ";
		OWLOntology master;
		final IRI masterIRI = IRI.create("SeML:O");
		final IRI masterNAME = IRI.create("master.owl");
		OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
		System.out.println(local_log + "Parsing all imported ontologies:");
		
		//=================================================================== Create Master (ontology) with all imports
		//=============================================================================================================
		
		//------------------------------------------------- Load all ontologies into OWL manager
		for(int i=0; i<imports.length; i++){
			File ontfile = new File(imports[i]);
			
			AutoIRIMapper automapper = new AutoIRIMapper(ontfile.getParentFile(), true);		
			manager.getIRIMappers().add(automapper); //Info: replacement of .addIRIMapper()
			
			try {
				manager.loadOntologyFromOntologyDocument(ontfile);// Add imported ontologies
			} catch (OWLOntologyCreationException e) {
	    		throw new IOException("Error Loading Ontology: " + imports[i] + " -> " + e.getMessage());	
			} 
		} 
		manager.ontologies().forEach(o -> System.out.println(local_log + o.getOntologyID().getOntologyIRI().get().toString()));		
	
		
		//------------------------------------------------- Create master ontology		
		
		final long startTime = System.currentTimeMillis(); //log execution time	
    	try {
			master = manager.createOntology(masterIRI);
		} catch (OWLOntologyCreationException e) {//Error occurred while creating ontology	
			throw new IOException("Error creating Master Ontology: " + e.getMessage());
		} 	  
  
    	
    	//------------------------------------------------- Merge them all
    	
    	manager.ontologies().forEach(o -> master.addAxioms(o.getAxioms()));
        System.out.println(local_log + "(" + (System.currentTimeMillis() - startTime) + "ms) Merged Ontology has: " + master.getAxiomCount() + " axioms");
        
        
        //------------------------------------------------- Save Ontology
        
        File masterfile = new File(GENfolder + masterNAME);
        try {
			manager.saveOntology(master,IRI.create(masterfile.toURI()));
		} catch (OWLOntologyStorageException e) {
			throw new IOException("Error saving Master Ontology: " + e.getMessage());
		}
        
        //====================================================================================== Generate keywords file
        //=============================================================================================================
		
        //------------------------------------------------- Prepare destination folder
        
	    final File GENfolder_f = new File(GENfolder);
	    
	    if(!GENfolder_f.exists()){ //Check if folder exists, create it otherwise
			if(!GENfolder_f.mkdir()){
				throw new IOException("Error creating directory: " + GENfolder); //Error occurred while creating generated files directory	
			}
			else System.out.println(local_log + "Directory for generated files was created: " + GENfolder);
	    }
	    
	    
	    //------------------------------------------------- Create reasoner and StringBuilder
	    
	    final PelletReasoner reasoner = PelletReasonerFactory.getInstance().createNonBufferingReasoner(master);
	    reasoner.precomputeInferences();// Ask the reasoner to do all the necessary work now
	    
    	StringBuilder sb = new StringBuilder(10000); //set initial capacity to 10000 chars
    	sb.append(GENfirstline + imports.length + "\n"); //set initial line + no of imported files
    	for(int i=0; i<imports.length; i++){ //list all imported files
    		sb.append(imports[i] + "\n");
    	}
    	sb.append("*/\n");
    	
    	final OWLDataFactory fac = OWLManager.createOWLOntologyManager().getOWLDataFactory();
    	
    	
    	//------------------------------------------------- Parse master ontology with reasoner
    	
    	ArrayList<String> tripleIDlist = new ArrayList<String>();//Sentence: Prefix + ShortIRI + IRI
    	
    	try {	
	        reasoner.getSubClasses(fac.getOWLClass(IRI.create(OWL_Event)), false).entities().forEach(c -> 	{if(isInstantiable(c.getIRI(),master)) {
	        	tripleIDlist.add("CompEvent ");      tripleIDlist.add(c.getIRI().getShortForm()); tripleIDlist.add( c.getIRI().toString() );} });
	        reasoner.getSubClasses(fac.getOWLClass(IRI.create(OWL_Entity)), false).entities().forEach(c -> 	{if(isInstantiable(c.getIRI(),master)) {
	        	tripleIDlist.add("CompEntity ");     tripleIDlist.add(c.getIRI().getShortForm()); tripleIDlist.add( c.getIRI().toString() );} });
	        reasoner.getSubClasses(fac.getOWLClass(IRI.create(OWL_Process)), false).entities().forEach(c -> {if(isInstantiable(c.getIRI(),master)) {
	        	tripleIDlist.add("CompProcess ");    tripleIDlist.add(c.getIRI().getShortForm()); tripleIDlist.add( c.getIRI().toString() );} });
	        reasoner.getSubClasses(fac.getOWLClass(IRI.create(OWL_Property)), false).entities().forEach(c -> {if(isInstantiable(c.getIRI(),master)){ 
	        	tripleIDlist.add("CompProperty ");   tripleIDlist.add(c.getIRI().getShortForm()); tripleIDlist.add( c.getIRI().toString() );} });
	        reasoner.getSubClasses(fac.getOWLClass(IRI.create(OWL_Characteristic)), false).entities().forEach(c -> {if(isInstantiable(c.getIRI(),master)){ 
	        	tripleIDlist.add("Characteristic "); tripleIDlist.add(c.getIRI().getShortForm()); tripleIDlist.add( c.getIRI().toString() );} });
	        reasoner.getSubObjectProperties(reasoner.getTopObjectPropertyNode().getRepresentativeElement()).entities().forEach(o -> {if(isInstantiable(o.getNamedProperty().getIRI(),master)){ 
	        	tripleIDlist.add("ObjectProperty "); tripleIDlist.add(o.getNamedProperty().getIRI().getShortForm()); tripleIDlist.add( o.getNamedProperty().getIRI().toString() );} });
	        reasoner.getInstances(fac.getOWLClass(IRI.create(OWL_Thing)), false).entities().forEach(i -> {
	        	StringBuilder sb_aux = new StringBuilder(1000); //set initial capacity to 1000 chars
	        	sb_aux.append("MetaIndividual "); EntitySearcher.getTypes(i, master).forEach(o -> { if(o.isOWLClass()) sb_aux.append("\"" + o.asOWLClass().getIRI().toString() + "\" "); });
	        	tripleIDlist.add(sb_aux.toString()); tripleIDlist.add(i.getIRI().getShortForm()); tripleIDlist.add( i.getIRI().toString() ); });
    	} catch (Exception e) {throw new IOException("Error parsing Master Ontology: " + e.getMessage());}     		
    	
    	
    	//------------------------------------------------- Eliminate the short-form for duplicates and build string
    	
    	String[] tripleIDarray = tripleIDlist.toArray(new String[0]);

    	for (int j=1;j<tripleIDlist.size();j+=3){ //iterate all elements
    		if(!tripleIDarray[j].equals(tripleIDarray[j+1])){ //check if element wasn't already fixed
    			for (int k=j+3;k<tripleIDlist.size();k+=3) //find all duplicates of an element
		    	    if (tripleIDarray[j].equals(tripleIDarray[k])){
		    	    	tripleIDarray[j] = tripleIDarray[j+1]; //eliminate short-form (redundant in 2nd duplicate)
		    	    	tripleIDarray[k] = tripleIDarray[k+1]; //eliminate short-form
		    	    }
    		}
    		//After checking a certain element, insert it in the string builder
    		sb.append(tripleIDarray[j-1] + tripleIDarray[j] + " \"" + tripleIDarray[j+1] + "\"\n");
    	}
    	
    	//------------------------------------------------- Save generated file
    	
    	GENfile.createNewFile();
		PrintWriter writer = new PrintWriter(GENfile,"UTF-8");
		writer.println(sb);
		writer.close();
		System.out.println(local_log + "Successfully generated: " + GENfile.getAbsolutePath());
		
	}
	

	
	
	public static File ParseOntology(File ontfile, String newAxiomsSEMLPath) throws IOException {
		final String local_log = Ontologies.local_log + "[ParseOntology] ";
		
		String simpleOntIRI;
		OWLOntology ontology;
		List<OWLOntology> ontCollection;
		
			
		//=================================================================== LOAD DESIRED ONTOLOGY
		try {
			ontology = loadOnt(ontfile);
			ontCollection = ontology.importsClosure().collect(Collectors.toList());
    		simpleOntIRI = getSimpleOntologyIRI(ontology);
		} catch (OWLOntologyCreationException e) {
    		System.out.println(local_log + "Error: " + e.getMessage());
    		throw new IOException("Error Loading Ontology: " + ontfile.getAbsolutePath()); //Error occurred while loading ontology	
    	}		  		
	    
	    //=================================================================== PREPARE DESTINATION FOLDER	
	    final File newAxiomsSEML = new File(newAxiomsSEMLPath + "/" + simpleOntIRI + ".dat");
	    final File newAxiomsSEMLdir = new File(newAxiomsSEMLPath);
	    
	    if(!newAxiomsSEMLdir.exists()){ //Create directory
			if(!newAxiomsSEMLdir.mkdir()){
				System.out.println(local_log + "Error creating directory: " + newAxiomsSEMLdir);
				throw new IOException("Error creating directory: " + newAxiomsSEMLdir); //Error occurred while creating generated files directory	
			}
			else System.out.println(local_log + "Directory for generated files was created: " + newAxiomsSEMLdir);
	    }
	    
	    //=================================================================== CREATE AND PARSE SPECIFIC AXIOMS
	    if(newAxiomsSEML.exists() && Long.valueOf(ontfile.lastModified()).compareTo(newAxiomsSEML.lastModified()) < 0) {
		    System.out.println(local_log + "Generated axioms file is up-to-date: " + newAxiomsSEML.getAbsolutePath());
		} else {
	    	
	    	StringBuilder sb = new StringBuilder(10000); //set initial capacity to 10000 chars
	    	sb.append("//Automatically generated file\n\"" + ontology.getOntologyID().getOntologyIRI().get().toString() + "\"\n");
	    	
	    	try {		    		
		    	//---------------------------------- Check if ontology file exists
	    		final PelletReasoner reasoner = PelletReasonerFactory.getInstance().createNonBufferingReasoner(ontology);
			    reasoner.precomputeInferences();// Ask the reasoner to do all the necessary work now, not later. OK.
			    
		        final OWLDataFactory fac = OWLManager.createOWLOntologyManager().getOWLDataFactory();
		        
		        reasoner.getSubClasses(fac.getOWLClass(IRI.create(OWL_Event)), false).entities().forEach(c -> 	{if(isObjectInstantiable(c.getIRI(),ontCollection)) sb.append("CompEvent \"" + c.getIRI() + "\"\n");});
		        reasoner.getSubClasses(fac.getOWLClass(IRI.create(OWL_Entity)), false).entities().forEach(c -> 	{if(isObjectInstantiable(c.getIRI(),ontCollection)) sb.append("CompEntity \"" + c.getIRI() + "\"\n");});
		        reasoner.getSubClasses(fac.getOWLClass(IRI.create(OWL_Process)), false).entities().forEach(c -> {if(isObjectInstantiable(c.getIRI(),ontCollection)) sb.append("CompProcess \"" + c.getIRI() + "\"\n");});
		        reasoner.getSubClasses(fac.getOWLClass(IRI.create(OWL_Property)), false).entities().forEach(c -> {if(isObjectInstantiable(c.getIRI(),ontCollection)) sb.append("CompProperty \"" + c.getIRI() + "\"\n");});
		        reasoner.getSubObjectProperties(reasoner.getTopObjectPropertyNode().getRepresentativeElement()).entities().forEach(o -> {if(isObjectInstantiable(o.getNamedProperty().getIRI(),ontCollection)) sb.append("ObjectProperty \"" + o.getNamedProperty().getIRI() + "\"\n");});
		        reasoner.getSubClasses(fac.getOWLClass(IRI.create(OWL_Characteristic)), false).entities().forEach(c -> {if(isObjectInstantiable(c.getIRI(),null)) sb.append("Characteristic \"" + c.getIRI() + "\"\n");});
		        reasoner.getInstances(fac.getOWLClass(IRI.create(OWL_Thing)), false).entities().forEach(i -> {sb.append("MetaIndividual "); EntitySearcher.getTypes(i, 
		        		ontology.importsClosure()).forEach(o -> { if(o.isOWLClass()) sb.append("\"" + o.asOWLClass().getIRI().toString() + "\" "); }); sb.append( i.getIRI() + "\n"); });	        

	    	} catch (Exception exception) {
	    		System.out.println(local_log + "Error: " + exception.getMessage());
	    		throw new IOException("Error Parsing Ontology: " + ontfile.getAbsolutePath()); //Error occurred while parsing ontology		    		
	    	}
	    	
	    	//=================================================================== POPULATE SEML FILE WITH RULES
	    	newAxiomsSEML.createNewFile();
			//FileOutputStream oFile = new FileOutputStream(newAxiomsSEML, false); //append=false
			PrintWriter writer = new PrintWriter(newAxiomsSEML,"UTF-8");
			writer.println(sb);
			writer.close();
			System.out.println(local_log + "Successfully generated: " + newAxiomsSEML.getAbsolutePath());
	    }
	    return newAxiomsSEML;

    }
	
	

    
    
	/**
	 * Returns if an object(class,property,..) is instantiable or not. An object is not instantiable if there is a NonInstantiable annotation.
	 * Class http://www.w3.org/2002/07/owl#Nothing is not instantiable.
	 * The TopObjectProperty (http://www.w3.org/2002/07/owl#bottomObjectProperty) is not instantiable.
	 * 
	 * @param objID		the object to be tested
	 * @param ontology	the most specific ontology which contains the object
	 * @return			true if object is instantiable
	 */
    @SuppressWarnings("deprecation") //New Stream methods yield different results
	private static boolean isObjectInstantiable(IRI objID, List<OWLOntology> ontCollection) { 
    	
    	if (objID.toString().equals(OWL_Nothing)) return false; //test if object is class "Nothing"
    	if (objID.toString().equals(OWL_TopObjProperty)) return false; //test if object is the TopObjectProperty
    	if (objID.toString().equals(OWL_Goal) || objID.toString().equals(OWL_Feature)) return false; //test if characteristic is not a category
    	
    	if(ontCollection == null) return true; 
    	for(OWLOntology o : ontCollection){ //iterates all related ontologies (imports)
    		for (OWLAnnotationAssertionAxiom annotation : o.getAnnotationAssertionAxioms(objID)) { //gets annotations associated with the class
	        	for (OWLAnnotationProperty ap : annotation.getAnnotationPropertiesInSignature()) { //Iterates annotations (usually there is only 1 per signature)
	        		if(OWL_Annotation_NONInstantiable.compareTo(ap.getIRI().toString()) == 0) return false;
	        	}        	
	        }
    	}
	    return true;
	}
    
	/**
	 * Returns if a class,property,etc is instantiable or not. An object is not instantiable if there is a NonInstantiable annotation.
	 * Class http://www.w3.org/2002/07/owl#Nothing is not instantiable.
	 * The TopObjectProperty (http://www.w3.org/2002/07/owl#bottomObjectProperty) is not instantiable.
	 * 
	 * @param objID		the object to be tested
	 * @param ontology	the most specific ontology which contains the object
	 * @return			true if object is instantiable
	 */
    @SuppressWarnings("deprecation") //New Stream methods yield different results
	private static boolean isInstantiable(IRI objID, OWLOntology o) { 
    	
    	if (objID.toString().equals(OWL_Nothing)) return false; //test if object is class "Nothing"
    	if (objID.toString().equals(OWL_TopObjProperty)) return false; //test if object is the TopObjectProperty
    	if (objID.toString().equals(OWL_Goal) || objID.toString().equals(OWL_Feature)) return false; //test if characteristic is not a category
    	
		for (OWLAnnotationAssertionAxiom annotation : o.getAnnotationAssertionAxioms(objID)) { //gets annotations associated with the class
        	for (OWLAnnotationProperty ap : annotation.getAnnotationPropertiesInSignature()) { //Iterates annotations (usually there is only 1 per signature)
        		if(OWL_Annotation_NONInstantiable.compareTo(ap.getIRI().toString()) == 0) return false;
        	}        	
        }

	    return true;
	}
    
    

    private static OWLOntologyManager OntLibrary = OWLManager.createOWLOntologyManager();
    private static HashMap<String, OntFilePair> FileReferences = new HashMap<String, OntFilePair>();
    /**
     * Load ontology if not already loaded. Stored ontologies are reloaded, if outdated.
     * 
     * @param ontfile 	the physical .owl file where the ontology is stored
     * @return 			the created/loaded ontology object
     * @throws OWLOntologyCreationException
     */
    public static OWLOntology loadOnt(File ontfile) throws OWLOntologyCreationException{
    	final String local_log = Ontologies.local_log + "[loadOnt] ";
    	final long startTime = System.currentTimeMillis(); //log execution time
    	
    	//Create library if it doesn't exist
    	if(OntLibrary == null) OntLibrary = OWLManager.createOWLOntologyManager();
		
		//Check if ontology already exists in manager 
		OntFilePair ontfiledata = FileReferences.get(ontfile.getAbsolutePath());
		if(ontfiledata != null){
			//Check if the ontology file creation date is the same. If it is return the loaded ontology.
			if(ontfiledata.getDate() == ontfile.lastModified()) return OntLibrary.getOntology(ontfiledata.getIRI());
			//If date is not the same, delete ontology and its reference
			FileReferences.remove(ontfile.getAbsolutePath());
			OntLibrary.removeOntology(OntLibrary.getOntology(ontfiledata.getIRI()));
		}
		
		//-------------------------------------- Load Ontology and store its reference
		// Create IRI Mapper to find imported ontologies (mapping IRI to filename) in the same folder or subfolders
		final AutoIRIMapper automapper = new AutoIRIMapper(ontfile.getParentFile(), true);		
		OntLibrary.getIRIMappers().clear(); //Clear mapper every time
		OntLibrary.getIRIMappers().add(automapper); //Info: replacement of .addIRIMapper()
		// Load Ontology
		OWLOntology ontology = null;
		try {
			ontology = OntLibrary.loadOntologyFromOntologyDocument(ontfile);
		} catch (OWLOntologyCreationException e) {
			// If ontology creation fails (e.g. when loading a different ont with same IRI), the OntLibrary is reset.
			System.out.println(local_log + "Warning while loading ontology: " + e.getMessage());
			System.out.println(local_log + "Cleaning Ontologies cache...");
			OntLibrary.clearOntologies();
			FileReferences.clear();
			ontology = OntLibrary.loadOntologyFromOntologyDocument(ontfile); //If it still fails, an exception will be thrown
		}	
		final IRI ontologyIRI = ontology.getOntologyID().getOntologyIRI().get(); //get unique identification of ontology
		// Add Reference
		FileReferences.put(ontfile.getAbsolutePath(), new OntFilePair(ontfile.lastModified(),ontologyIRI));
		System.out.println(local_log + "(" + (System.currentTimeMillis() - startTime) + "ms) Loaded Ontology: " + ontologyIRI.toString() + " has " + ontology.getAxiomCount() + " axioms");	
		return ontology;
    	
    }
  
//    public static OWLOntology loadOntology(OWLOntologyManager manager, File ontfile) throws OWLOntologyCreationException{
//    	final String local_log = Ontologies.local_log + "[loadOntology] ";
//    	
//		//------------------------------ Create IRI Mapper to find imported ontologies (mapping IRI to filename)
//		// Note: the imported ontologies must be in the same folder or subfolders of the ontology folder
//		final AutoIRIMapper automapper = new AutoIRIMapper(ontfile.getParentFile(), true);		
//		//System.out.println(automapper.getOntologyIRIs()); Debug purposes, gets found ontologies
//		manager.getIRIMappers().add(automapper); //Info: replacement of .addIRIMapper()
//		
//		//------------------------------ Load Desired Ontology
//		final long startTime = System.currentTimeMillis(); //log execution time
//		OWLOntology ontology =  manager.loadOntologyFromOntologyDocument(ontfile); //try to load ontology	
//		final String ontologyIRI = ontology.getOntologyID().getOntologyIRI().get().toString(); //get unique identification of ontology
//		System.out.println(local_log + "(" + (System.currentTimeMillis() - startTime) + "ms) Loaded Ontology: " + ontologyIRI + " has " + ontology.getAxiomCount() + " axioms");	
//		return ontology;
//    }
    
    /**
     * Generates a simplification of the Ontology's original IRI (Allowed: [a-z][A-Z][0-9]._)
     * 
     * @param ontology	a loaded ontology object
     * @return
     */
    public static String getSimpleOntologyIRI(OWLOntology ontology) {

    	final String ontologyIRI = ontology.getOntologyID().getOntologyIRI().get().toString(); //get unique identification of ontology
		final String simpleOntIRI = ontologyIRI.replaceAll("[^.\\w]","-"); //Allowed: [a-z][A-Z][0-9]._ 		
		return simpleOntIRI;
		
    }
    
    public static void populatePaths(EObject E, File ontfile) throws IOException{
    	final String local_log = Ontologies.local_log + "[populatePaths] ";
    	
    	// Processa caminhos relativos ao ficheiro atual (diferente para cada ficheiro SEML)
		SEMLfile_relpath = E.eResource().getURI().toPlatformString(true);// 		"rPath/____.seml"
		SEMLfile_object = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(SEMLfile_relpath));
		//SEMLfile_abspath = SEMLfile_object.getProject().getLocation(); //".../path/file.seml" (currently unused)
		String SEMLfile_name = SEMLfile_object.getLocation().toFile().getName(); // "file.seml" (currently unused)
		SEMLGENfolder_abspath = SEMLfile_object.getLocation().toString() + ".gen/"; // 	".../rPath/____.dat.gen/"
		SEMLGENfolder_relpath = SEMLfile_name + ".gen/"; // 							"____.seml.gen/"		
		
		// Processa caminhos relativos ao ficheiro gerado (diferente para cada import do ficheiro SEML)
		try {
			SEMLGENfile_name = getSimpleOntologyIRI(Ontologies.loadOnt(ontfile)) + ".dat";// "____.dat"
		} catch (OWLOntologyCreationException e) {
    		System.out.println(local_log + "Error: " + e.getMessage());
    		throw new IOException("Error Loading Ontology: " + ontfile.getAbsolutePath()); //Error occurred while loading ontology	
    	}	
		SEMLGENfile_abspath = new File(SEMLGENfolder_abspath + SEMLGENfile_name);// ".../rPath/____.seml.gen/____.dat"
		SEMLGENfile_relpath = SEMLGENfolder_relpath + SEMLGENfile_name;// "____.seml.gen/____.dat"				
    }
    
    public static void populatePaths(EObject E){    	
    	//Find current SEML file
		SEMLfile_relpath = E.eResource().getURI().toPlatformString(true);// 		"rPath/____.seml"
		SEMLfile_object = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(SEMLfile_relpath));
		//SEMLfile_abspath = SEMLfile_object.getProject().getLocation(); //".../path/file.seml" (currently unused)
		//String SEMLfile_name = SEMLfile_object.getLocation().toFile().getName(); // "file.seml" 
		
		//Get generated folder path (relative + absolute)
		GENfolder = SEMLfile_object.getLocation().toString() + ".gen/"; // 	".../rPath/____.seml.gen/"
		//SEMLGENfolder_relpath = SEMLfile_name + ".gen/"; // 							"____.seml.gen/"		
		
		//Get generated file path (relative + absolute)
		GENfile = new File(GENfolder + GENfile_NAME);// ".../rPath/____.seml.gen/imports.seml"
		//SEMLGENfile_relpath = SEMLGENfolder_relpath + GENfile_NAME;// "____.seml.gen/imports.seml"				
    }
	
}

//ontology.importsClosure().forEach(o -> o.annotationAssertionAxioms(cls.getIRI()).forEach( a -> a.annotationPropertiesInSignature().forEach(f -> 
//{if(propNonInst2.compareTo(f.getIRI()) == 0) x[0]=true;}
//)));
//System.out.println("\t"+"§§§§§§§§§§§§§§§§§§" + x[0]);




//for (OWLOntology o : ontology.importsClosure().toArray(size -> new OWLOntology[size])) { //gets all related ontologies (imports)
//for (OWLAnnotationAssertionAxiom a : ontology.annotationAssertionAxioms(cls.getIRI()).toArray(size -> new OWLAnnotationAssertionAxiom[size])) { //gets all annotations related with the class
//	System.out.println("\t"+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ has Annotation (stream)");
//	
//	IRI propNonInst = IRI.create(ontology.getOntologyID().getOntologyIRI().get() + "#NonInstantiable");
//	
//	Set<OWLAnnotationProperty> anotproplist = a.getAnnotationPropertiesInSignature();
//	for (OWLAnnotationProperty ap : anotproplist) { //Percorre as anot props de cada anotacao (em principio so ha uma)
//		if(propNonInst.compareTo(ap.getIRI()) == 0) 
//			System.out.println("\t"+"Nao instanciavel!");
//	    System.out.println("\t"+"Annot Prop:  " + ap + "  por extenso:  " + ap.getIRI());
//	}
//	
//
//	System.out.println("\t"+"$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
//}
//}
