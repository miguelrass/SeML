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

import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.search.EntitySearcher;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;

import com.clarkparsia.owlapi.explanation.PelletExplanation;
import com.clarkparsia.owlapi.explanation.io.manchester.ManchesterSyntaxExplanationRenderer;
import com.clarkparsia.pellet.owlapi.PelletReasoner;
import com.clarkparsia.pellet.owlapi.PelletReasonerFactory;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

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
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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
	public static final String OWL_DefaultC = OWL_Upper + "Default";
	 
	public static String GENfile_NAME = "imports.seml";
	public static File GENfile = null; //generated file object (absolute path)
	public static String GENfile_relpath = null; //relative path from workspace (to get imported model)
	public static String GENfolder = null; //generated folder (absolute path)
	public static final String GENfirstline = "/* Automatically generated file. Source files: ";
	public static final String masterNAME = "master.owl";
	public static final String masterIRI = "Se:ML";

	
	public static void ParseOntologies(String[] imports) throws IOException {
		final String local_log = Ontologies.local_log + "[ParseOntologies] ";
		OWLOntology master;
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
			master = manager.createOntology(IRI.create(masterIRI));
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
	    if(!reasoner.isConsistent()) throw new IOException("Master ontology is inconsistent:\n" + ExplainInconsistencies(master)); //This check must be performed to use the reasoner (this cannot be fixed inside the DSL, use protégé)
	    reasoner.precomputeInferences();// Ask the reasoner to do all the necessary work now
	    
    	StringBuilder sb = new StringBuilder(10000); //set initial capacity to 10000 chars
    	sb.append(GENfirstline + imports.length + "\n"); //set initial line + no of imported files
    	for(int i=0; i<imports.length; i++){ //list all imported files
    		sb.append(imports[i] + "\n");
    	}
    	sb.append("*/\n");
    	
    	final OWLDataFactory fac = OWLManager.getOWLDataFactory();
    	
    	
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
	        	List<OWLClassExpression> classes = EntitySearcher.getTypes(i, master).collect(Collectors.toList());
	        	if(!classes.isEmpty()) { //An individual might be a lost fragment, with no owner
		        	sb_aux.append("MetaIndividual "); 
		        	classes.forEach(o -> { if(o.isOWLClass()) sb_aux.append("\"" + o.asOWLClass().getIRI().toString() + "\" "); });
		        	tripleIDlist.add(sb_aux.toString()); tripleIDlist.add(i.getIRI().getShortForm()); tripleIDlist.add( i.getIRI().toString() ); }
	        	});
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
	
	
	public static String ExplainInconsistencies(OWLOntology ontology){
		final String local_log = Ontologies.local_log + "[ExplainInconsistencies] ";
		
		// The renderer is used to format the explanation
		ManchesterSyntaxExplanationRenderer renderer = new ManchesterSyntaxExplanationRenderer();
		
		// The writer used for the explanation rendered
		StringWriter outputWriter = new StringWriter();
		renderer.startRendering(outputWriter );
		
		// Create an explanation generator
		PelletExplanation expGen = new PelletExplanation(ontology);
		
		//Try to get all explanations (up to 3)
		Set<Set<OWLAxiom>> explanationAxioms = null;
		try {
			explanationAxioms = expGen.getInconsistencyExplanations(3);
			while(!explanationAxioms.isEmpty()) {
				renderer.render( explanationAxioms);
				explanationAxioms.remove(explanationAxioms.iterator().next());
			}
			renderer.endRendering();
			
		//In case of failure try to get only the first explanation
		} catch (Exception e) {
			System.out.println(local_log + "Error: " + e.getMessage());
			try {
				explanationAxioms = expGen.getInconsistencyExplanations(1);
				renderer.render( explanationAxioms);
				renderer.endRendering();
			} catch (Exception e1) {
				
		//In case of failure report error in console
				System.out.println(local_log + "Error: " + e1.getMessage());
				outputWriter.write("The inconsistencies explanation could not be rendered");
			}
		}
		
		return outputWriter.toString();
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
    
    
    public static void populatePaths(EObject E){    	
    	//Find current SEML file
		String SEMLfile_relpath = E.eResource().getURI().toPlatformString(true);// 		"rPath/____.seml"
		IFile SEMLfile_object = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(SEMLfile_relpath));
		//SEMLfile_abspath = SEMLfile_object.getProject().getLocation(); //".../path/file.seml" (currently unused)
		String SEMLfile_name = SEMLfile_object.getLocation().toFile().getName(); // "file.seml" 
		
		//Get generated folder path (relative + absolute)
		GENfolder = SEMLfile_object.getLocation().toString() + ".gen/"; // 	".../rPath/____.seml.gen/"
		//SEMLGENfolder_relpath = SEMLfile_name + ".gen/"; // 							"____.seml.gen/"		
		
		//Get generated file path (relative + absolute)
		GENfile = new File(GENfolder + GENfile_NAME);// ".../rPath/____.seml.gen/imports.seml"
		GENfile_relpath = SEMLfile_name + ".gen/" + GENfile_NAME;// "____.seml.gen/imports.seml"				
    }
	
}


