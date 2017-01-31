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
import java.util.stream.Collectors;
import java.util.stream.Stream;

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

import org.eclipse.emf.common.util.EList;
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
import org.xtext.seml.seML.MetaIndividual;
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
	private static OWLOntologyManager manager = null;
	private static OWLOntology master = null; //Ontology used to validate the whole model
	private static OWLDataFactory factory = null;
	private static PelletReasoner reasoner = null;
	private static HashMap<String, List<OWLClassExpression>> RestrictionsList = null;
	//private static List<OWLClass> CharacteristicSubCls = null; //not being used, might be erased in the future
	private static HashMap<String, String> cachedComponentIRIs = null;
	 	
	/**
	 * Load Master ontology file and initialize OWLAPI objects
	 * Loaded ontology is consistent. This is tested upon creation.
	 * @param importsList
	 * @return
	 * @throws OWLOntologyCreationException 
	 * @throws IOException
	 */
	public static void loadMasterOntology(File masterfile) throws OWLOntologyCreationException{
		
		RestrictionsList = new HashMap<String, List<OWLClassExpression>>();
		manager = OWLManager.createOWLOntologyManager();
		master =  manager.loadOntologyFromOntologyDocument(masterfile);
		factory = OWLManager.getOWLDataFactory();
        reasoner = PelletReasonerFactory.getInstance().createNonBufferingReasoner(master);
        cachedComponentIRIs = new HashMap<String, String>();
        //CharacteristicSubCls = reasoner.getSubClasses(factory.getOWLClass(IRI.create(Ontologies.OWL_Characteristic)), false).entities().collect(Collectors.toList());
        
        return;
	}
	
	public static void cacheComponentIRIs(EList<Component> lst){
		cachedComponentIRIs = new HashMap<String, String>();
		lst.forEach(c -> cachedComponentIRIs.put(c.getIri(), c.getName()));
	}
	
	public static String addIndividual(Individual ind){
		final String local_log = MasterOntology.local_log + "[addIndividual] ";	
		
		OWLIndividual owlInd = factory.getOWLNamedIndividual(IRI.create(OWL_Master + "#" + ind.getName())); //create OWL individual
		
		//Iterate through all classes of the new individual
		for(Component c : ind.getCls()){
			OWLClass cls = factory.getOWLClass(IRI.create(c.getIri().toString())); //get class of new individual
			OWLAxiom axiom = factory.getOWLClassAssertionAxiom(cls, owlInd); //create axiom with the OWL individual
			System.out.format(local_log + "Adding individual to Master Ontology: %-10s of Class %s\n", ind.getName(), c.getIri().toString());
			manager.addAxiom(master, axiom); //add axiom to master ontology	
		}

        return null; 
	}
	

	
	public static String checkRelationRestrictions(String clsName, String indIRI){
		
		OWLClass cls = factory.getOWLClass(IRI.create(clsName)); //get class of new individual
		
		//---------------------------------------------- Get restrictions of class (and inherited restrictions)

		//Check if RestrictionsList contains the searched class (and hence the class's restrictions)
		List<OWLClassExpression> ClsRestrList = RestrictionsList.get(cls.getIRI().toString());
		
		if(ClsRestrList == null){// Use the approach based on Ignazio's to get the restrictions for the given class		
		    RestrictionVisitor restrVisitor = new RestrictionVisitor(master); //create visitor object	    
		    master.subClassAxiomsForSubClass(cls).forEach(ax -> restrVisitor.visit(ax.getSuperClass()));// visit all restrictions    		    
		    ClsRestrList = restrVisitor.getRestrictions(); //get all restriction for the given class
		    RestrictionsList.put(cls.getIRI().toString(), ClsRestrList); //cache class restrictions     
		}
	    
		//---------------------------------------------- Check if individual complies with its class restrictions
		
		//Get all relations and create a RestrictionOverseer object
		RestrictionOverseer RO = new RestrictionOverseer(factory.getOWLNamedIndividual(IRI.create(indIRI)), reasoner, cachedComponentIRIs);
		
		//Evaluate each restriction with the visitor pattern
		ClsRestrList.forEach(r -> r.accept(RO));
		
		//Return the errors report (or null if no error found)
		return RO.getReport();
	}
	
	
	public static String addRelation(Relation rel){
		final String local_log = MasterOntology.local_log + "[addRelation] ";
		String relationString = null;
		
		String ai1, ai2;	
		
		//If the individual is new, add master prefix to their IRI
		if(rel.getInstance1() instanceof Individual) ai1 = OWL_Master + "#" + rel.getInstance1().getName(); 
		else if(rel.getInstance1() instanceof MetaIndividual) ai1 = ((MetaIndividual)rel.getInstance1()).getIri();
		else return null; // Gramatical Error
		if(rel.getInstance2() instanceof Individual) ai2 = OWL_Master + "#" + rel.getInstance2().getName(); 
		else if(rel.getInstance2() instanceof MetaIndividual) ai2 = ((MetaIndividual)rel.getInstance2()).getIri();
		else return null; // Gramatical Error
			
		relationString = ai1 + " " + rel.getObj().getIri() + " " + ai2;
		System.out.println(local_log + "Adding relation to Master Ontology: " + relationString);
		
		// ind1 --> hasObj --> ind2
        OWLIndividual ind1 = factory.getOWLNamedIndividual(IRI.create(ai1));
        OWLIndividual ind2 = factory.getOWLNamedIndividual(IRI.create(ai2));
        OWLObjectProperty obj = factory.getOWLObjectProperty(IRI.create(rel.getObj().getIri()));
        OWLObjectPropertyAssertionAxiom axiom = factory.getOWLObjectPropertyAssertionAxiom(obj, ind1, ind2);
		manager.addAxiom(master, axiom);
		
		if(!reasoner.isConsistent()) return "Inconsistency detected after adding relation:\n" + relationString + "\n" + Ontologies.ExplainInconsistencies(master);		

		//System.out.println(local_log + "Success! Ontology has now " + masterOntology.getAxiomCount() + " axioms"); 
		return null;
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
	
	

    
	
}
