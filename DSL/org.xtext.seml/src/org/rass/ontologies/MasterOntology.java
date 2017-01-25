package org.rass.ontologies;

import org.eclipse.xtext.validation.Check;
import org.xtext.seml.seML.AnyIndividual;
import org.xtext.seml.seML.Component;
import java.io.File;
import org.xtext.seml.seML.SeMLPackage;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLSubClassOfAxiom;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntologyDocumentAlreadyExistsException;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.ClassExpressionType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLException;
import org.semanticweb.owlapi.model.OWLIndividual;

import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;

import com.clarkparsia.owlapi.explanation.ExplanationGenerator;
import com.clarkparsia.owlapi.explanation.PelletExplanation;
import com.clarkparsia.owlapi.explanation.io.manchester.ManchesterSyntaxExplanationRenderer;
import com.clarkparsia.pellet.owlapi.PelletReasoner;
import com.clarkparsia.pellet.owlapi.PelletReasonerFactory;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.semanticweb.owlapi.util.AutoIRIMapper;
import org.semanticweb.owlapi.util.OWLObjectVisitor;
import org.semanticweb.owlapi.util.OWLOntologyMerger;
import org.eclipse.xtext.EcoreUtil2;
import org.xtext.seml.seML.Import;
import org.xtext.seml.seML.Individual;
import org.xtext.seml.seML.MainModel;
import org.xtext.seml.seML.Relation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.MissingResourceException;
import java.io.NotActiveException;
import java.io.IOException;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLCardinalityRestriction;

public class MasterOntology {
	
	public static final String OWL_Master = "rs:Master.owl"; //the name is not relevant outside the application
	private static final String local_log = "Master Ontology Log: ";
	private static OWLOntologyManager masterManager = null;
	private static OWLOntology masterOntology = null; //Ontology used to validate the whole model
	private static OWLDataFactory factory = null;
	private static PelletReasoner reasoner = null;
	private static HashMap<String, List<OWLClassExpression>> RestrictionsList = null;
	 	
	/**
	 * Creates a Unique ontology with all imported ontologies, initializes ontology related object
	 * and checks if ontology is consistent returning the result
	 * @param importsList
	 * @return
	 * @throws IOException
	 */
	public static boolean createMasterOntology(List<Import> importsList) throws IOException{
		final String local_log = MasterOntology.local_log + "[createMasterOntology] ";
		System.out.println(local_log + "Trying to merge imported ontologies...");
		
		IRI mergedOntologyIRI = IRI.create(OWL_Master); //IRI of the new merged ontology
		masterManager = OWLManager.createOWLOntologyManager();
		factory = masterManager.getOWLDataFactory();
		RestrictionsList = new HashMap<String, List<OWLClassExpression>>();

		//Add all ontologies (including their imports) to the masterManager
		for(Import i: importsList){
			File ontfile = new File(i.getName());
			
			AutoIRIMapper automapper = new AutoIRIMapper(ontfile.getParentFile(), true);		
			masterManager.getIRIMappers().add(automapper); //Info: replacement of .addIRIMapper()
			
			try {
				masterManager.loadOntologyFromOntologyDocument(ontfile);// Add imported ontologies
			} catch (OWLOntologyCreationException e) {
				System.out.println(local_log + "Error: " + e.getMessage());
	    		throw new IOException("Error Loading Ontology: " + i.getName()); //Error occurred while loading ontology	
			} 
			
		}    
        
//		Merge all ontologies that are loaded in the masterManager (this method generated exceptions in complex cases)
//      OWLOntologyMerger merger = new OWLOntologyMerger(masterManager);      
//      try {
//			masterOntology = merger.createMergedOntology(masterManager, mergedOntologyIRI);
//		} catch (OWLOntologyCreationException e) {
//			System.out.println(local_log + "Error: " + e.getMessage());
//    		throw new IOException("Error Merging Imported Ontologies"); //Error occurred while merging ontologies		    		
//		}
        
  
		final long startTime = System.currentTimeMillis(); //log execution time	
    	try {
			masterOntology = masterManager.createOntology(mergedOntologyIRI);
		} catch (OWLOntologyCreationException e) {throw new IOException("Error Creating Master Ontology");} //Error occurred while creating ontology		  
  
        //Merge all ontologies that are loaded in the masterManager
        masterManager.ontologies().forEach(o -> masterOntology.addAxioms(o.getAxioms()));
        System.out.println(local_log + "(" + (System.currentTimeMillis() - startTime) + "ms) Merged Ontology has: " + masterOntology.getAxiomCount() + " axioms");
        
        reasoner = PelletReasonerFactory.getInstance().createNonBufferingReasoner(masterOntology);
        return reasoner.isConsistent();
	}
	
	public static String addIndividual(Individual ind){
		final String local_log = MasterOntology.local_log + "[addIndividual] ";
		//No need to check if it already exists. A new individual gets inserted with the master prefix
		//and after the owl is generated, the next time it will be for implementation, not modelling		
		
		OWLIndividual owlInd = factory.getOWLNamedIndividual(IRI.create(OWL_Master + "#" + ind.getName())); //create OWL individual
		
		//Iterate through all classes of the new individual
		for(Component c : ind.getCls()){
			OWLClass cls = factory.getOWLClass(IRI.create(c.getName())); //get class of new individual
			OWLAxiom axiom = factory.getOWLClassAssertionAxiom(cls, owlInd); //create axiom with the OWL individual
			System.out.println(local_log + "Adding individual to Master Ontology: " + ind.getName() + " of Class " + cls);
			masterManager.addAxiom(masterOntology, axiom); //add axiom to master ontology	
		}

        return null; 
	}
	
	
	public static String checkRelationRestrictions(String clsName, String indIRI){
		
		OWLClass cls = factory.getOWLClass(IRI.create(clsName)); //get class of new individual
		
		//---------------------------------------------- Get restrictions of class (and inherited restrictions)

		//Check if RestrictionsList contains the searched class (and hence the class's restrictions)
		List<OWLClassExpression> ClsRestrList = RestrictionsList.get(cls.getIRI().toString());
		
		if(ClsRestrList == null){// Use the approach based on Ignazio's to get the restrictions for the given class		
		    RestrictionVisitor restrVisitor = new RestrictionVisitor(masterOntology); //create visitor object	    
		    masterOntology.subClassAxiomsForSubClass(cls).forEach(ax -> restrVisitor.visit(ax.getSuperClass()));// visit all restrictions    		    
		    ClsRestrList = restrVisitor.getRestrictions(); //get all restriction for the given class
		    RestrictionsList.put(cls.getIRI().toString(), ClsRestrList); //cache class restrictions     
		}
	    
		//---------------------------------------------- Check if individual complies with its class restrictions
		
		//Get all relations and create a RestrictionOverseer object
		RestrictionOverseer RO = new RestrictionOverseer(factory.getOWLNamedIndividual(IRI.create(indIRI)), reasoner);
		
		//Evaluate each restriction with the visitor pattern
		ClsRestrList.forEach(r -> r.accept(RO));
		
		//Return the errors report (or null if no error found)
		return RO.getReport();
	}
	
	
	public static String addRelation(Relation rel){
		final String local_log = MasterOntology.local_log + "[addRelation] ";
		String relationString = null;
		
		String ai1 = rel.getInstance1().getName();
		String ai2 = rel.getInstance2().getName();	
		
		//If the individual is new, add master prefix to their IRI
		if(rel.getInstance1() instanceof Individual) ai1 = OWL_Master + "#" + ai1;
		if(rel.getInstance2() instanceof Individual) ai2 = OWL_Master + "#" + ai2;
			
		relationString = ai1 + " " + rel.getObj().getName() + " " + ai2;
		System.out.println(local_log + "Adding relation to Master Ontology: " + relationString);
		
		// ind1 --> hasObj --> ind2
        OWLIndividual ind1 = factory.getOWLNamedIndividual(IRI.create(ai1));
        OWLIndividual ind2 = factory.getOWLNamedIndividual(IRI.create(ai2));
        OWLObjectProperty obj = factory.getOWLObjectProperty(IRI.create(rel.getObj().getName()));
        OWLObjectPropertyAssertionAxiom axiom = factory.getOWLObjectPropertyAssertionAxiom(obj, ind1, ind2);
		masterManager.addAxiom(masterOntology, axiom);
		
		if(!reasoner.isConsistent()) return "Inconsistency detected after adding relation:\n" + relationString + "\n" + ExplainInconsistencies();		

		//System.out.println(local_log + "Success! Ontology has now " + masterOntology.getAxiomCount() + " axioms"); 
		return null;
	}
	
	/**
	 * 
	 */
	private static String ExplainInconsistencies(){
		final String local_log = MasterOntology.local_log + "[ExplainInconsistencies] ";
		
		// The renderer is used to format the explanation
		ManchesterSyntaxExplanationRenderer renderer = new ManchesterSyntaxExplanationRenderer();
		
		// The writer used for the explanation rendered
		StringWriter outputWriter = new StringWriter();
		renderer.startRendering(outputWriter );
		
		// Create an explanation generator
		PelletExplanation expGen = new PelletExplanation(masterOntology);
		
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
				outputWriter.write("The inconsistencies explanation could not be rendered");;
			}
		}
	
		return outputWriter.toString();
		
	}
	
	
//	    @Test
//	    public void testAddAxioms() throws OWLException {
//	        OWLOntologyManager m = create();
//	        OWLOntology o = m.createOntology(KOALA_IRI);
//	        // class A and class B
//	        OWLClass clsA = df.getOWLClass(IRI.create(KOALA_IRI + "#A"));
//	        OWLClass clsB = df.getOWLClass(IRI.create(KOALA_IRI + "#B"));
//	        // Now create the axiom
//	        OWLAxiom axiom = df.getOWLSubClassOfAxiom(clsA, clsB);
//	        // add the axiom to the ontology.
//	        AddAxiom addAxiom = new AddAxiom(o, axiom);
//	        // We now use the manager to apply the change
//	        m.applyChange(addAxiom);
//	        // remove the axiom from the ontology
//	        RemoveAxiom removeAxiom = new RemoveAxiom(o, axiom);
//	        m.applyChange(removeAxiom);
//	}
//		  @Test
//		    public void testIndividualAssertions() throws OWLException {
//		        OWLOntologyManager m = create();
//		        OWLOntology o = m.createOntology(EXAMPLE_IRI);
//		        // We want to state that matthew has a father who is peter.
//		        OWLIndividual matthew = df.getOWLNamedIndividual(IRI.create(EXAMPLE_IRI + "#matthew"));
//		        OWLIndividual peter = df.getOWLNamedIndividual(IRI.create(EXAMPLE_IRI + "#peter"));
//		        // We need the hasFather property
//		        OWLObjectProperty hasFather = df.getOWLObjectProperty(IRI.create(EXAMPLE_IRI + "#hasFather"));
//		        // matthew --> hasFather --> peter
//		        OWLObjectPropertyAssertionAxiom assertion = df.getOWLObjectPropertyAssertionAxiom(hasFather, matthew, peter);
//		        // Finally, add the axiom to our ontology and save
//		        AddAxiom addAxiomChange = new AddAxiom(o, assertion);
//		        m.applyChange(addAxiomChange);
//		        // matthew is an instance of Person
//		        OWLClass personClass = df.getOWLClass(IRI.create(EXAMPLE_IRI + "#Person"));
//		        OWLClassAssertionAxiom ax = df.getOWLClassAssertionAxiom(personClass, matthew);
//		        // Add this axiom to our ontology - with a convenience method
//		        m.addAxiom(o, ax);
//		}
	
	
	public static void addToMasterOntology(File ontfile, String newAxiomsSEMLPath){
		//if (masterOntology == null)
	}
    
	
}
