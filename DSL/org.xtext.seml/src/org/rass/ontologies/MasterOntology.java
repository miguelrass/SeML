package org.rass.ontologies;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.rass.ontologies.Anomaly.ReportLevel;
import org.rass.swrl.CustomSWRLBuiltin;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.xtext.seml.seML.Component;
import org.xtext.seml.seML.ImportModel;
import org.xtext.seml.seML.Individual;
import org.xtext.seml.seML.MetaIndividual;
import org.xtext.seml.seML.Relation;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;

public class MasterOntology {
	
	public static final String OWL_Master = "rs:Master.owl"; //the name is not relevant outside the application
	private static final String local_log = "Master Ontology Log: ";
	private static OWLOntologyManager manager = null;
	private static OWLOntology master = null; //Ontology used to validate the whole model
	private static OWLDataFactory factory = null;
	private static PelletReasoner reasoner = null;
	private static HashMap<String, List<OWLClassExpression>> RestrictionsList = null;
	//private static List<OWLClass> CharacteristicSubCls = null; //not being used, might be erased in the future
	private static HashMap<String, String> cachedIRIs = null; //Key: Full IRI
	private static HashMap<String, String> cachedIRIsInverse = null; //Key: Short IRI
	 	
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
        reasoner = CustomSWRLBuiltin.getSWRLReasoner(master);
        MasterCache.loadReportClasses(Ontologies.GENfolder);
        //CharacteristicSubCls = reasoner.getSubClasses(factory.getOWLClass(IRI.create(Ontologies.OWL_Characteristic)), false).entities().collect(Collectors.toList());
        
        return;
	}
	
	public static void cacheIRIs(ImportModel importM, List<Individual> dslIndividuals){
		cachedIRIs = new HashMap<String, String>();
		cachedIRIsInverse = new HashMap<String, String>();
		importM.getComponents().forEach(c -> cachedIRIs.put(c.getIri(), c.getName()));
		importM.getObjectProperties().forEach(op -> cachedIRIs.put(op.getIri(), op.getName()));
		importM.getMetaIndividuals().forEach(i -> cachedIRIs.put(i.getIri(), i.getName()));
		dslIndividuals.forEach(i -> cachedIRIs.put(OWL_Master + "#" + i.getName(), i.getName()));
		
        Iterator<Entry<String, String>> it = cachedIRIs.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String> pair = it.next();
            cachedIRIsInverse.put(pair.getValue(), pair.getKey());
        }
	}
	
	/**
	 * Adds an individual to the master ontology, checks for anomalies
	 * @param ind
	 * @return false if the model contains grammar errors
	 */
	public static void addIndividual(Individual ind){
		final String local_log = MasterOntology.local_log + "[addIndividual] ";	
		
		OWLIndividual owlInd = factory.getOWLNamedIndividual(IRI.create(OWL_Master + "#" + ind.getName())); //create OWL individual
		
		//Iterate through all classes of the new individual
		for(Component c : ind.getCls()){
			//if(c.getIri() == null) return false;
			OWLClass cls = factory.getOWLClass(IRI.create(c.getIri().toString())); //get class of new individual
			OWLAxiom axiom = factory.getOWLClassAssertionAxiom(cls, owlInd); //create axiom with the OWL individual
			System.out.format(local_log + "Adding individual to Ontology: %-10s of Class %s\n", ind.getName(), c.getIri().toString());
			manager.addAxiom(master, axiom); //add axiom to master ontology	
		}

		//return true;
		//return Anomaly.ReasonAndExplain(reasoner, master, ReportLevel.INFORMATION); 
	}
	
	public static boolean ReasonAndExplainMaster(){
		return Anomaly.ReasonAndExplain(reasoner, master, ReportLevel.INFORMATION); 
	}
	
	public static String[] checkRelationRestrictions(String clsName, String indIRI){
		
		OWLClass cls = factory.getOWLClass(IRI.create(clsName)); //get class of new individual
		
		//---------------------------------------------- Get restrictions of class (and inherited restrictions)

		//Check if RestrictionsList contains the searched class (and hence the class's restrictions)
		List<OWLClassExpression> ClsRestrList = RestrictionsList.get(cls.getIRI().toString());
		
		if(ClsRestrList == null){// Use the approach based on Ignazio's to get the restrictions for the given class		
		    RestrictionVisitor restrVisitor = new RestrictionVisitor(master); //create visitor object	    
		    master.getSubClassAxiomsForSubClass(cls).forEach(ax -> restrVisitor.visit(ax.getSuperClass()));// visit all restrictions    		    
		    ClsRestrList = restrVisitor.getRestrictions(); //get all restriction for the given class
		    RestrictionsList.put(cls.getIRI().toString(), ClsRestrList); //cache class restrictions     
		}
	    
		//---------------------------------------------- Check if individual complies with its class restrictions
		
		//Get all relations and create a RestrictionOverseer object
		RestrictionOverseer RO = new RestrictionOverseer(factory.getOWLNamedIndividual(IRI.create(indIRI)), reasoner, cachedIRIs, cachedIRIsInverse);
		
		//Evaluate each restriction with the visitor pattern
		ClsRestrList.forEach(r -> r.accept(RO));
		
		//Return the errors report (or null if no error found)
		return RO.getFinalReport();
	}
	
	/**
	 * Adds a relation to the master ontology, checks for anomalies
	 * @param rel
	 * @return false if the model contains grammar errors
	 */
	public static boolean addRelation(Relation rel){
		final String local_log = MasterOntology.local_log + "[addRelation] ";
		String relationString = null;
		
		String ai1, ai2;	
		
		//If the individual is new, add master prefix to their IRI
		if(rel.getInstance1() instanceof Individual) ai1 = OWL_Master + "#" + rel.getInstance1().getName(); 
		else if(rel.getInstance1() instanceof MetaIndividual) ai1 = ((MetaIndividual)rel.getInstance1()).getIri();
		else return false; // Internal Error (user has grammar errors in file)
		if(rel.getInstance2() instanceof Individual) ai2 = OWL_Master + "#" + rel.getInstance2().getName(); 
		else if(rel.getInstance2() instanceof MetaIndividual) ai2 = ((MetaIndividual)rel.getInstance2()).getIri();
		else return false; // Internal Error (user has grammar errors in file)
			
		relationString = ai1 + " " + rel.getObj().getIri() + " " + ai2;
		System.out.println(local_log + "Adding relation to Ontology: " + relationString);
		
		// ind1 --> hasObj --> ind2
        OWLIndividual ind1 = factory.getOWLNamedIndividual(IRI.create(ai1));
        OWLIndividual ind2 = factory.getOWLNamedIndividual(IRI.create(ai2));
        OWLObjectProperty obj = factory.getOWLObjectProperty(IRI.create(rel.getObj().getIri()));
        OWLObjectPropertyAssertionAxiom axiom = factory.getOWLObjectPropertyAssertionAxiom(obj, ind1, ind2);
		manager.addAxiom(master, axiom);
		
		return true;
		//return Anomaly.ReasonAndExplain(reasoner, master, ReportLevel.INFORMATION);
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
