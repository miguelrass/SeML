package org.rass.ontologies;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.xbase.lib.Pair;
import org.rass.swrl.CustomSWRLBuiltin;
import org.rass.swrl.ExternalSWRLBuiltins;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.OWLEntityRemover;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;

public class Ontologies {
	
	private static final String local_log = "Ontologies Log: ";
	private static final OWLDataFactory fac = OWLManager.getOWLDataFactory();
	
	public static final String OWL_Upper = "esrg:upper#";
	public static final String OWL_Nothing = "http://www.w3.org/2002/07/owl#Nothing";
	public static final String OWL_Component = OWL_Upper + "Component";
	public static final String OWL_Entity = OWL_Upper + "Entity";
	public static final String OWL_Process = OWL_Upper + "Process";
	public static final String OWL_Property = OWL_Upper + "Property";
	public static final String OWL_Characteristic = OWL_Upper + "Characteristic";
	public static final String OWL_Goal = OWL_Upper + "Goal";
	public static final String OWL_Feature = OWL_Upper + "Feature";
	public static final String OWL_DefaultCh = OWL_Upper + "Default";
	public static final String OWL_Problem = OWL_Upper + "Problem";
	public static final String OWL_Requires = OWL_Upper + "requires";
	
	public static final OWLClass OWLC_Thing = fac.getOWLClass(IRI.create("http://www.w3.org/2002/07/owl#Thing"));
	public static final OWLClass OWLC_Component = fac.getOWLClass(IRI.create(OWL_Component));
	public static final OWLClass OWLC_DefaultCh = fac.getOWLClass(IRI.create(OWL_DefaultCh));
	public static final OWLClass OWLC_Problem = fac.getOWLClass(IRI.create(OWL_Problem));
	
	public static final String OWL_OP_Bottom = "http://www.w3.org/2002/07/owl#bottomObjectProperty";
	public static final String OWL_OP_demands = OWL_Upper + "demands";
	public static final String OWL_OP_hasProblem = OWL_Upper + "hasProblem";
	public static final String OWL_OP_isSolvedBy = OWL_Upper + "isSolvedBy";
	public static final String OWL_DP_hasValue = OWL_Upper + "hasValue";
	public static final String OWL_DP_hasReport = OWL_Upper + "hasReport";
	public static final String OWL_DP_hasError = OWL_Upper + "hasError";
	public static final String OWL_DP_hasWarning = OWL_Upper + "hasWarning";
	public static final String OWL_DP_hasInfo = OWL_Upper + "hasInfo";
	
	public static final OWLObjectProperty OWLP_OP_Bottom = fac.getOWLObjectProperty(IRI.create(OWL_OP_Bottom));
	public static final OWLDataProperty OWLP_DP_hasValue = fac.getOWLDataProperty(IRI.create(OWL_DP_hasValue));
	public static final OWLDataProperty OWLP_DP_hasReport = fac.getOWLDataProperty(IRI.create(OWL_DP_hasReport));
	public static final OWLDataProperty OWLP_DP_hasError = fac.getOWLDataProperty(IRI.create(OWL_DP_hasError));
	public static final OWLDataProperty OWLP_DP_hasWarning = fac.getOWLDataProperty(IRI.create(OWL_DP_hasWarning));
	public static final OWLDataProperty OWLP_DP_hasInfo = fac.getOWLDataProperty(IRI.create(OWL_DP_hasInfo));
	
	public static final String OWL_Ann_NonInstantiable = OWL_Upper + "NonInstantiable";
	public static final String OWL_Ann_ArraySize = OWL_Upper + "ArraySize";
	public static final String OWL_Ann_StaticIndividual = OWL_Upper + "StaticIndividual";
	public static final String OWL_Ann_DefaultValue = OWL_Upper + "DefaultValue";
	public static final String OWL_Ann_ImplArg = OWL_Upper + "ImplArg";
	public static final String OWL_Ann_ImplDP = OWL_Upper + "ImplDP";
	public static final String OWL_Ann_ImplOP= OWL_Upper + "ImplOP";
	public static final String OWL_Ann_ImplInd = OWL_Upper + "ImplInd";
	
	 
	public static String GENfile_NAME = "imports.seml";
	public static File GENfile = null; //generated file object (absolute path)
	public static String GENfile_relpath = null; //relative path from workspace (to get imported model)
	public static String GENfolder = null; //generated folder (absolute path)
	public static String SWRLfolder = null; //swrl folder (absolute path)
	public static String Toolsfolder = null; //Tools folder (absolute path)
	public static String TEMPLfolder = null; //swrl templates folder (absolute path)
	public static String PROJfolder = null; //project folder (absolute path)
	public static String SWRLPackage = null; //swrl java package (semlbasename+ ".swrl")
	public static String ToolsPackage = null; //Tools java package (semlbasename+ ".tools")
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
		manager.getOntologies().forEach(o -> System.out.println(local_log + o.getOntologyID().getOntologyIRI().get().toString()));		
	
		
		//------------------------------------------------- Create merged ontology (master)
		
		long startTime = System.currentTimeMillis(); //log execution time	
    	try {
    		master = manager.createOntology(IRI.create(masterIRI), manager.getOntologies());
		} catch (OWLOntologyCreationException e) {//Error occurred while creating ontology	
			throw new IOException("Error creating Master Ontology: " + e.getMessage());
		} 	  
  
	    //------------------------------------------------- Create reasoner
	    
	    final PelletReasoner reasoner = CustomSWRLBuiltin.getSWRLReasoner(master);
	    
		//test master ontology for inconsistencies and unsatisfiable classes (otherwise, it becomes impossible to reason)
		if(Anomaly.ReasonAndExplain(reasoner, master)){
			throw new IOException("Master ontology has anomalies:\n" + Anomaly.getAnomalies()); //This check must be performed to use the reasoner (this cannot be fixed inside the DSL, use protégé)
		}
	    
	    reasoner.precomputeInferences();// Ask the reasoner to do all the necessary work now
	    
	    //------------------------------------------------- Add individuals to empty classes
	    
	    //Get subclasses of Component
		Set<OWLClass> compSubs = reasoner.getSubClasses(fac.getOWLClass(IRI.create(OWL_Component)), false).getFlattened();
		
		//Remove non-instantiable classes
		compSubs.removeIf(c -> !isInstantiable(c.getIRI(),master));
		
		//Create list of used aliases (also avoid DSL keywords)
		HashSet<String> aliases = new HashSet<String>(Arrays.asList("import","use","true","false","FreeIndividual","ObjectProperty","Characteristic","StaticIndividual")); //avoid DSL keywords
		
		//Add an individual to each empty class
		ArrayList<String> newiReferences = new ArrayList<String>(); //Alias, IRI
		HashMap<String,String> oldiReferences = new HashMap<String,String>(); //IRI, Alias
		HashMap<String,String> staticiReferences = new HashMap<String,String>(); //IRI, Alias
		
		//Store individuals to be added and then removed in the validation process
		HashSet<OWLAxiom> newAxioms = new HashSet<OWLAxiom>(); //do not add while reasoning (causes large delays)
		MasterCache.freeIndividuals = new HashSet<OWLNamedIndividual>();	
		
		//Store individuals classes to be used with relation restrictions of individuals
		MasterCache.individualsClassSet = new HashMap<OWLNamedIndividual, Integer>();
		
		for(OWLClass c : compSubs){
			if(reasoner.getInstances(c, false).isEmpty()){
				
				//add to new individual's reference list				
				String newAlias = GetShortIRI(c.getIRI()); //alias of new individual (=ClassShortIRI)
				newAlias = GetAlias(aliases, newAlias.substring(0, 1).toLowerCase() + newAlias.substring(1)); //Alias -> alias
				String newIRI = masterIRI+"#"+newAlias;
				newiReferences.add(newAlias); //Alias of new individual
				newiReferences.add(newIRI); //IRI of new individual
				
				//create and add new individual
				OWLNamedIndividual ind = fac.getOWLNamedIndividual(IRI.create(newIRI)); //new individual
				OWLAxiom axiom = fac.getOWLClassAssertionAxiom(c, ind); //create axiom with the OWL individual
				newAxioms.add(axiom); //add axiom to master ontology	
				
				//store reference to remove new individual
				MasterCache.freeIndividuals.add(ind);

			//------------------------------------------------- Add individuals to arrays	
				
			}else{ for(OWLNamedIndividual i :  reasoner.getInstances(c, true).getFlattened()){ 
				
				boolean staticFlag = isStatic(i.getIRI(),master); 
				int arraySize = getArraySize(i.getIRI(),master);
				HashSet<OWLAnnotation> defaultAnn = new HashSet<OWLAnnotation>();
			
				if(arraySize > 1){ //If array (static or not)
					
					Pair<String,Integer> prefixIndex = getPrefixIndex(i.getIRI().toString()); //Get starting index and prefix
					
					//Get DefaultValue annotations, if any exists
					for (OWLAnnotationAssertionAxiom a : master.getAnnotationAssertionAxioms(i.getIRI())) {
						if(Ontologies.OWL_Ann_DefaultValue.equals(a.getProperty().getIRI().toString()))
							defaultAnn.add(a.getAnnotation());
					}
					
					for(int index=prefixIndex.getValue()+1; index<prefixIndex.getValue()+arraySize; index++ ){
								
						String newAlias = GetAlias(aliases, prefixIndex.getKey() + String.valueOf(index));
						String newIRI = masterIRI+"#"+newAlias;
						
						//create and add new individual
						OWLNamedIndividual ind = fac.getOWLNamedIndividual(IRI.create(newIRI)); //new individual
						OWLAxiom axiom = fac.getOWLClassAssertionAxiom(c, ind); //create axiom with the OWL individual
						newAxioms.add(axiom); //add axiom to master ontology	
						
						//Copy default values to new instances
						defaultAnn.forEach(a -> newAxioms.add(fac.getOWLAnnotationAssertionAxiom(ind.getIRI(), a)));
						
						//add to temporary individual's reference list		
						if(staticFlag){
							staticiReferences.put(newIRI, newAlias);	
							MasterCache.individualsClassSet.put(i, null); //Populating 1a/2 (rest in MasterCache.java)
						}else{
							newiReferences.add(newAlias); //alias of new individual
							newiReferences.add(newIRI); //IRI of new individual
							
							//store reference to remove new individual
							MasterCache.freeIndividuals.add(ind);
						}
					}
				}
				//------------------------------------------------- Register existing individuals
				
				if(isStatic(i.getIRI(),master)){ //Preserve static components
					
					//add to static individual's reference list
					if(!staticiReferences.containsKey(i.getIRI().toString())){ //to avoid the creation of another alias
						staticiReferences.put(i.getIRI().toString(), GetAlias(aliases, GetShortIRI(i.getIRI())));	
						MasterCache.individualsClassSet.put(i, null); //Populating 1b/2 (rest in MasterCache.java)
					}
				}else{
					
					if(!oldiReferences.containsKey(i.getIRI().toString())){ //to avoid the creation of another alias
						
						//store reference to remove old individuals
						MasterCache.freeIndividuals.add(i);
						
						//add to old individual's reference list
						oldiReferences.put(i.getIRI().toString(), GetAlias(aliases, GetShortIRI(i.getIRI())));
					}
				}
			}}
		}
		
		//------------------------------------------------- Check if any static individual exists
		 
		if(staticiReferences.isEmpty()) throw new IOException("Error: Master Ontology contains 0 static individuals.");

        //------------------------------------------------- Save Ontology with extra Individuals
        
		manager.addAxioms(master, newAxioms); 
        File masterfile = new File(GENfolder + masterNAME);
        try {
			manager.saveOntology(master,IRI.create(masterfile.toURI())); //also creates folder, if needed
		} catch (OWLOntologyStorageException e) {
			throw new IOException("Error saving Master Ontology: " + e.getMessage());
		}
        
        //========================================================================= Create Templates for SWRL Built-ins
        //=============================================================================================================

	    ExternalSWRLBuiltins.GenerateTemplates(reasoner);
	    
	    //====================================================================================== Generate keywords file
        //=============================================================================================================
	    
	    //------------------------------------------------- Create string builder
	    
    	StringBuilder sb = new StringBuilder(10000); //set initial capacity to 10000 chars
    	sb.append(GENfirstline + imports.length + "\n"); //set initial line + no of imported files
    	for(int i=0; i<imports.length; i++){ //list all imported files
    		sb.append(imports[i] + "\n");
    	}
    	sb.append("*/\n\n");
    	
    	//------------------------------------------------- Populate string builder
    	
    	//Add static individual references
    	sb.append("//Static individuals:\n\n");
    	staticiReferences.forEach((i,a) -> {sb.append("StaticIndividual "); sb.append(a); sb.append(" \""); sb.append(i); sb.append("\"\n");});
    	
    	
    	//Add generated individual references
    	sb.append("\n//Automatically created individuals for arrays or empty instantiable subclasses of Component:\n\n");
    	for(int i=0; i < newiReferences.size();){ 
    		sb.append("FreeIndividual "); sb.append(newiReferences.get(i++)); 
    		sb.append(" \""); sb.append(newiReferences.get(i++)); sb.append("\"\n");
    	}
    	
    	//Add old individual references
    	sb.append("\n//Individuals of subclasses of Component:\n\n");
    	oldiReferences.forEach((i,a) -> {sb.append("FreeIndividual "); sb.append(a); sb.append(" \""); sb.append(i); sb.append("\"\n");});
    	
    	//Add Characteristics
    	MasterCache.chrRestrictions = new HashMap<OWLClass,HashSet<OWLClassExpression>>();
    	sb.append("\n//Characteristics:\n\n");
        reasoner.getSubClasses(fac.getOWLClass(IRI.create(OWL_Characteristic)), false).getFlattened().forEach(c -> {if(isInstantiable(c.getIRI(),master)){ 
        	sb.append("Characteristic " + GetAlias(aliases,GetShortIRI(c.getIRI())) + " \"" + c.getIRI().toString() + "\"\n");
        	MasterCache.chrRestrictions.put(c, null); }});
        
        //Add Object Properties
        sb.append("\n//Object Properties:\n\n");
        reasoner.getSubObjectProperties(reasoner.getTopObjectPropertyNode().getRepresentativeElement(),false).getFlattened().forEach(o -> {if(isInstantiable(o.getNamedProperty().getIRI(),master)){ 
        	sb.append("ObjectProperty " + GetAlias(aliases, GetShortIRI(o.getNamedProperty().getIRI())) + " \"" + o.getNamedProperty().getIRI().toString() + "\"\n");} });
        
		//------------------------------------------------- Save master ontology cache data
		
		startTime = System.currentTimeMillis(); //log execution time	
		System.out.print(local_log + "Generating cache data...");
		if(!MasterCache.CreateCache(GENfolder, reasoner)) throw new IOException("Error creating cache");
		System.out.println("(" + (System.currentTimeMillis() - startTime) + "ms)");		
		
    	//------------------------------------------------- Save generated file
    	
    	GENfile.createNewFile();
		PrintWriter writer = new PrintWriter(GENfile,"UTF-8");
		writer.println(sb);
		writer.close();
		System.out.println(local_log + "Successfully generated: " + GENfile.getAbsolutePath());
		
	}
	
		
	
	/**
	 * Method to replace org.semanticweb.owlapi.model.IRI.getShortForm()
	 * The original method stops when it finds several symbols
	 * This method stops when it finds '#' (has no effect if not found)
	 * @param iri
	 * @return
	 */
	public static String GetShortIRI(IRI iri){
		return iri.toString().substring(iri.toString().lastIndexOf('#') + 1);
	}
	
	private static String GetAlias(HashSet<String> aliases, String shortIRI){
		
		if(aliases.add(shortIRI)) return shortIRI; //If it is a new alias, add it and return it
		
		int counter = 1;
    	while(!aliases.add(shortIRI + String.valueOf(counter))) counter++; //Create new alias (shortIRI + number)
    	return shortIRI + String.valueOf(counter);
	}
	
	public static HashSet<OWLClassExpression> GetAnonymousSuperClasses(OWLOntology o, OWLClass c){
		
		HashSet<OWLClassExpression> AnonSuperClss = new HashSet<OWLClassExpression>();
		
		for(OWLSubClassOfAxiom ax : o.getSubClassAxiomsForSubClass(c)){
			if(!(ax.getSuperClass() instanceof OWLClass)){
				AnonSuperClss.add(ax.getSuperClass());
			}
		}

		return AnonSuperClss;
	}
	
	
	/**
	 * Returns if a class,property,etc is instantiable or not. An object is not instantiable if there is a NonInstantiable annotation.
	 * Class http://www.w3.org/2002/07/owl#Nothing is not instantiable.
	 * The BottomObjectProperty (http://www.w3.org/2002/07/owl#bottomObjectProperty) is not instantiable.
	 * 
	 * @param objID		the object to be tested
	 * @param ontology	the most specific ontology which contains the object
	 * @return			true if object is instantiable
	 */
    @SuppressWarnings("deprecation") //New Stream methods yield different results
	private static boolean isInstantiable(IRI objID, OWLOntology o) { 
    	
    	if (objID.toString().equals(OWL_Nothing)) return false; //test if object is class "Nothing"
    	if (objID.toString().equals(OWL_OP_Bottom)) return false; //test if object is the BottomObjectProperty
    	if (objID.toString().equals(OWL_Goal) || objID.toString().equals(OWL_Feature)) return false; //test if characteristic is not a category
    	
		for (OWLAnnotationAssertionAxiom annotation : o.getAnnotationAssertionAxioms(objID)) { //gets annotations associated with the class
        	//for (OWLAnnotationProperty ap : annotation.getAnnotationPropertiesInSignature()) { //Iterates annotations (usually there is only 1 per signature)
        		if(OWL_Ann_NonInstantiable.equals(annotation.getProperty().getIRI().toString())) return false;
        	//}        	
        }

	    return true;
	}
    
    private static boolean isStatic(IRI objID, OWLOntology o){
    	for (OWLAnnotationAssertionAxiom annotation : o.getAnnotationAssertionAxioms(objID)) { //gets annotations associated with the class
    		//for (OWLAnnotationProperty ap : annotation.getAnnotationPropertiesInSignature()) { //Iterates annotations (usually there is only 1 per signature)
        		if(OWL_Ann_StaticIndividual.equals(annotation.getProperty().getIRI().toString())) return true;
        	//}        	
        }
	    return false;
    }
    
    private static int getArraySize(IRI objID, OWLOntology o){ //if array size <= 1 it's not an array
    	
    	for (OWLAnnotationAssertionAxiom a : o.getAnnotationAssertionAxioms(objID)) { //gets annotations associated with the class
    		if(OWL_Ann_ArraySize.equals(a.getProperty().getIRI().toString())){
    			if(a.getValue().asLiteral().get().isInteger()) 
    				return a.getValue().asLiteral().get().parseInteger();
    		}
        }
	    return 1;
    }
    
    /**
     * Get Prefix (short IRI) and last integer in string
     * Returns integer=0 if:
     * - string contains only digits
     * - string doesn't end with a digit
     * - string is empty 
     * @param s string to be evaluated
     * @return Prefix and (last integer or Zero)
     */
    private static Pair<String,Integer> getPrefixIndex(String s){
    	for (int i = s.length() - 1; i >= 0; i--) {
    	    if (!Character.isDigit(s.charAt(i))) {
    	    	if(i==s.length()-1) break;
    	    	return new Pair<String,Integer>(s.substring(s.lastIndexOf('#') + 1, i+1), Integer.parseInt(s.substring(i+1)));
    	    }    
    	}
    	return new Pair<String,Integer>(s.substring(s.lastIndexOf('#') + 1), 0);
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
			OntLibrary = OWLManager.createOWLOntologyManager(); //clear everything
			OntLibrary.getIRIMappers().add(automapper);
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
    
    
    public static void populatePaths(EObject E) throws IOException{    	
    	//Find current SEML file
		String SEMLfile_relpath = E.eResource().getURI().toPlatformString(true);// 		"rPath/____.seml"
		IFile SEMLfile_object = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(SEMLfile_relpath));

		//Get SEML file simple basename and folder
		String SEMLfile_name = SEMLfile_object.getLocation().toFile().getName(); // "file.seml" 
		String SEMLfile_basename = SEMLfile_name.substring(0, SEMLfile_name.lastIndexOf('.')); //"file"
		PROJfolder =SEMLfile_object.getLocation().toFile().getParent(); //".../path/"
		
		//Get SEML file AbsPath without ".seml"
		String SEMLfile_abspath_noExt = PROJfolder + "/" + SEMLfile_basename; //".../path/file"
		
		//Get generated folder path (relative + absolute)
		GENfolder = SEMLfile_abspath_noExt + "_gen/"; 					// 	".../path/----_gen/"
		//GENfolder_relpath = SEMLfile_basename + "_gen/"; 				// 	"----_gen/"		
		
		SWRLfolder =  SEMLfile_abspath_noExt + "_swrl/";				// 	".../path/----_swrl/"
		Toolsfolder = SEMLfile_abspath_noExt + "_tools/";				// 	".../path/----_tools/"
		TEMPLfolder = SEMLfile_abspath_noExt + "_template/";			// 	".../path/----_template/"
		SWRLPackage = SEMLfile_basename + "_swrl";						// 	"----_swrl"
		ToolsPackage =SEMLfile_basename + "_tools";						// 	"----_tools"
		
		//Get generated file path (relative + absolute)
		GENfile = new File(GENfolder + GENfile_NAME);					// ".../path/----_gen/imports.seml"
		GENfile_relpath = SEMLfile_basename + "_gen/" + GENfile_NAME;	// "----_gen/imports.seml"				
		
		//Create tools folder 
		File toolsFolderFile = new File(Toolsfolder);
		if(!toolsFolderFile.exists()){ //Check if folder exists, create it otherwise
			if(!toolsFolderFile.mkdir())
				throw new IOException("Error creating directory: " + Toolsfolder); //Error occurred while creating folder
	    }
    }
	
}


