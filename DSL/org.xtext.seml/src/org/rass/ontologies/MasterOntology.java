package org.rass.ontologies;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.rass.implementation.ImplementationHandler;
import org.rass.restrictions.CharacteristicsSolver;
import org.rass.restrictions.Problem;
import org.rass.restrictions.Problem.LevelE;
import org.rass.restrictions.RestrictionAnalyzer;
import org.rass.restrictions.RestrictionAnalyzer2;
import org.rass.restrictions.RestrictionVisitor;
import org.rass.swrl.CustomSWRLBuiltin;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.xtext.seml.seML.Assignment;
import org.xtext.seml.seML.BoolVal;
import org.xtext.seml.seML.Characteristic;
import org.xtext.seml.seML.FloatVal;
import org.xtext.seml.seML.ImportModel;
import org.xtext.seml.seML.Individual;
import org.xtext.seml.seML.IntVal;
import org.xtext.seml.seML.Relation;
import org.xtext.seml.seML.StringVal;
import org.xtext.seml.seML.Value;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;

public class MasterOntology {
	
	public static final String OWL_Master = "rs:Master.owl"; //the name is not relevant outside the application
	private static final String local_log = "Master Ontology Log: ";
	private static OWLOntologyManager manager = null;
	private static OWLOntology master = null; //Ontology used to validate the whole model
	private static PelletReasoner reasoner = null;
	private static OWLOntologyManager fullManager = null; //Manager mainly used to add axioms to full Master
	private static OWLOntology fullMaster = null; //Ontology used for relation restrictions of individuals
	private static PelletReasoner fullReasoner = null; //Reasoner for fullMaster
	private static OWLDataFactory factory = OWLManager.getOWLDataFactory();
	private static HashMap<String, HashSet<OWLClassExpression>> RestrictionsList = null;
	public static HashMap<String, String> cachedIRIs = null; //Key: Full IRI
	public static HashMap<String, String> cachedIRIsInverse = null; //Key: Alias
	public static HashSet<OWLNamedIndividual> indsToRemove = null; //Free individuals - Free referenced individuals
	//public static HashSet<Individual> activeIndividuals = null; //Static individuals and Chosen individuals
	 	
	public static Set<OWLAxiom> GetFullMaster(){
		return fullMaster.getAxioms();
	}
	

	
	
	/**
	 * Load Master ontology file and initialize OWLAPI objects
	 * Loaded ontology is consistent. This is tested upon creation.
	 * @param importsList
	 * @return
	 * @throws OWLOntologyCreationException 
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public static void loadMasterOntology(File masterfile, HashSet<String> freeRefIRIs) throws OWLOntologyCreationException{
		
		if(!MasterCache.LoadCache(Ontologies.GENfolder)) throw new OWLOntologyCreationException("Error loading cache");
		RestrictionsList = new HashMap<String, HashSet<OWLClassExpression>>();
		manager = 	OWLManager.createOWLOntologyManager();
		master =  	manager.loadOntologyFromOntologyDocument(masterfile);
		fullManager = OWLManager.createOWLOntologyManager();
		fullMaster = fullManager.loadOntologyFromOntologyDocument(masterfile);
		
		//Get elements to remove (Free individuals - Free referenced individuals)
		indsToRemove = (HashSet<OWLNamedIndividual>)MasterCache.freeIndividuals.clone(); //Free individuals
		indsToRemove.removeIf(i-> freeRefIRIs.contains(i.getIRI().toString())); //note: activeInds = Static + Referenced
		
		//Remove elements from master
		OWLEntityRemover remover = new OWLEntityRemover(Collections.singleton(master));
		indsToRemove.forEach(i -> i.accept(remover));
		manager.applyChanges(remover.getChanges());
		
        reasoner = 	CustomSWRLBuiltin.getSWRLReasoner(master);
        fullReasoner = PelletReasonerFactory.getInstance().createNonBufferingReasoner(fullMaster);
        //CharacteristicSubCls = reasoner.getSubClasses(factory.getOWLClass(IRI.create(Ontologies.OWL_Characteristic)), false).entities().collect(Collectors.toList());

        ImplementationHandler.SetReasoner(reasoner);
	}
	
	/**
	 * Maps Alias and IRIs to speed up conversion
	 * @param importM
	 * @param dslIndividuals
	 */
	public static void CacheIRIs(ImportModel instM){
		cachedIRIs = new HashMap<String, String>();
		cachedIRIsInverse = new HashMap<String, String>();
		
		//TODO: passar para a cache
		instM.getIndividualOptions().forEach(a -> CacheIRI(a.getIri(), a.getName()));
		instM.getCharacteristics().forEach(a -> CacheIRI(a.getIri(), a.getName()));
		instM.getObjectProperties().forEach(a-> CacheIRI(a.getIri(), a.getName()));
		instM.getStaticIndividuals().forEach(a -> CacheIRI(a.getIri(), a.getName()));
	}
	
	private static void CacheIRI(String iri, String alias){
		cachedIRIs.put(iri, alias); 
		cachedIRIsInverse.put(alias, iri);
	}
	
	/**
	 * Adds an Individual created in a Quickfix
	 * @param name  Short IRI of the individual
	 */
	public static void cacheFixedIRI(String name){
		cachedIRIs.put(OWL_Master + "#" + name, name);
		cachedIRIsInverse.put(name, OWL_Master + "#" + name);
	}
	
	/**
	 * Adds an individual to the master ontology, checks for anomalies
	 * @param ind
	 * @return false if the model contains grammar errors
	 */
	///public static void addIndividual(/*Individual ind*/){
		//final String local_log = MasterOntology.local_log + "[addIndividual] ";	
		
		//OWLNamedIndividual owlInd = factory.getOWLNamedIndividual(IRI.create(OWL_Master + "#" /*+ ind.getName()*/)); //create OWL individual
		
		//Iterate through all classes of the new individual
		/*for(Component c : ind.getCls()){
			//if(c.getIri() == null) return false;
			OWLClass cls = factory.getOWLClass(IRI.create(c.getIri().toString())); //get class of new individual
			OWLAxiom axiom = factory.getOWLClassAssertionAxiom(cls, owlInd); //create axiom with the OWL individual
			System.out.format(local_log + "Adding individual to Ontology: %-10s of Class %s\n", ind.getName(), c.getIri().toString());
			manager.addAxiom(master, axiom); //add axiom to master ontology	
		}*/

		//return true;
		//return Anomaly.ReasonAndExplain(reasoner, master, ReportLevel.INFORMATION); 
	///}
	
	public static boolean ReasonAndExplainMaster(){
		return Anomaly.ReasonAndExplain(reasoner, master); 
	}
	
	public static boolean BuildMastersCharacteristicsTree(List<Characteristic> chrList) throws Exception{
		return  CharacteristicsSolver.BuildCharacteristicsTree(chrList, master);
	}

	/**
	 * Check if model complies with generic restrictions
	 * @param cls
	 * @return
	 */
	public static ArrayList<Problem> CheckModelRestrictions(OWLClass cls){

	    //List with all problems/solutions of a certain characteristic
	    ArrayList<Problem> cProblems = new ArrayList<Problem>();
	    
	    //This RA will analyze all restrictions of a Characteristic (some restrictions might have more than one problem)
	    RestrictionAnalyzer2 RA = new RestrictionAnalyzer2(cProblems, reasoner);
 
	    //Evaluate each restriction with the visitor pattern
	    MasterCache.chrRestrictions.get(cls).forEach(r -> r.accept(RA));
	    
	    //Insert restrictor (Characteristic Alias) reference in each problem
	    cProblems.forEach(p -> {p.restrictor = cls;});
	    
		return cProblems;
	}
	
	/**
	 * Check relation restrictions for a specific individual
	 * These restrictions cannot be statically cached because an individual can dynamically belong to different classes
	 * @param indIRI
	 * @return
	 */
	public static ArrayList<Problem> CheckRelationRestrictions(HashSet<List<String>> authorizatedRels, String indIRI){
		
	    ArrayList<Problem> iProblems = new ArrayList<Problem>(); //List with all problems of a certain individual
		
		OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(indIRI)); //get OWLNamedIndividual
		Set<OWLClass> clss = reasoner.getTypes(ind, false).getFlattened(); //get all classes of individual
		
		for(OWLClass c : clss){
			ArrayList<Problem> cProblems = new ArrayList<Problem>(); //List with own problems

		    //This RA will analyze all restrictions (some restrictions might have more than one problem)
		    RestrictionAnalyzer RA = new RestrictionAnalyzer(cProblems, authorizatedRels, ind, reasoner, fullReasoner);

		    HashSet<OWLClassExpression> cRestrList = Ontologies.GetAnonymousSuperClasses(master, c);
		    cRestrList.forEach(r -> r.accept(RA)); 		//Evaluate each restriction with the visitor pattern
		    cProblems.forEach(p -> {p.restrictor = c;}); //Insert restrictor reference in each problem

		    iProblems.addAll(cProblems);
		}
	
		return iProblems;
	}
	
	public static void CheckReports(String indIRI, ArrayList<Problem> ps){
		OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(indIRI)); //get OWLNamedIndividual
		
		reasoner.getDataPropertyValues(ind, Ontologies.OWLP_DP_hasError).forEach(e-> ps.add(new Problem(e.getLiteral(), LevelE.ERROR)));
		reasoner.getDataPropertyValues(ind, Ontologies.OWLP_DP_hasWarning).forEach(e-> ps.add(new Problem(e.getLiteral(), LevelE.WARNING)));
		reasoner.getDataPropertyValues(ind, Ontologies.OWLP_DP_hasInfo).forEach(e-> ps.add(new Problem(e.getLiteral(), LevelE.INFO)));
	}
	
	
	/**
	 * Adds Characteristic individual to be used by SWRL rules (except the default one)
	 * @param relList
	 */
	public static void AddChIndividuals(Set<OWLClass> chList){
		for(OWLClass c : chList){
			if(Ontologies.OWL_DefaultCh.equals(c.getIRI().toString())) continue;
			String newIRI = Ontologies.masterIRI + "_Characteristic#" + Ontologies.GetShortIRI(c.getIRI());
			OWLNamedIndividual chInd = factory.getOWLNamedIndividual(IRI.create(newIRI));
			manager.addAxiom(master,factory.getOWLClassAssertionAxiom(c, chInd));
		}
	}
	
	/**
	 * Adds object property relations to the master ontology
	 * @param relList
	 */
	public static void AddOPRelations(List<Relation> relList){

		for(Relation r : relList){
			for(Individual i : r.getInd2()){
				OWLNamedIndividual ind1 = factory.getOWLNamedIndividual(IRI.create(r.getInd1().getIri()));
				OWLNamedIndividual ind2 = factory.getOWLNamedIndividual(IRI.create(i.getIri()));
		        OWLObjectProperty obj = factory.getOWLObjectProperty(IRI.create(r.getObj().getIri()));
		        OWLObjectPropertyAssertionAxiom axiom = factory.getOWLObjectPropertyAssertionAxiom(obj, ind1, ind2);
				manager.addAxiom(master, axiom);
				fullManager.addAxiom(fullMaster, axiom); 
			}
		}
	}
	
	/**
	 * Adds data property relations to the master ontology
	 * @param litList
	 */
	public static void AddDPRelations(List<Assignment> litList){
			
		for(Assignment l : litList){
			OWLNamedIndividual ind = factory.getOWLNamedIndividual(IRI.create(l.getInd().getIri()));
			
			//Get Literal with correct data type
			OWLLiteral OWLLit = null;
			Value v = l.getLiteral();
			if(v instanceof FloatVal) 		OWLLit = factory.getOWLLiteral(((FloatVal) v).getVal());
			else if(v instanceof BoolVal) 	OWLLit = factory.getOWLLiteral(((BoolVal)  v).isVal());
			else if(v instanceof IntVal) 	OWLLit = factory.getOWLLiteral(((IntVal)   v).getVal());
			else if(v instanceof StringVal) OWLLit = factory.getOWLLiteral(((StringVal)v).getVal());
	
			//Get data property "hasValue"
			OWLDataProperty dataP = factory.getOWLDataProperty(IRI.create(Ontologies.OWL_DP_hasValue));
	
			//ind --> hasValue --> OWLLit
			OWLDataPropertyAssertionAxiom axiom = factory.getOWLDataPropertyAssertionAxiom(dataP,ind,OWLLit);
			manager.addAxiom(master, axiom);
			fullManager.addAxiom(fullMaster, axiom); 
		}
		
	}
	
	
	public static Set<OWLClass> GetSubclassesOf(OWLClassExpression c, boolean getOnlyDirectSubclasses){
		return reasoner.getSubClasses(c, getOnlyDirectSubclasses).getFlattened();
	}

	/**
	 * Authorize relations with sub-properties of the existent Object Properties
	 * @param authorizatedRels
	 */
	public static void ExtendAuthorizations(HashSet<List<String>> authorizatedRels) {
		List<List<String>> newAR = new ArrayList<List<String>>();
		for(List<String> r : authorizatedRels){
			if(r.size() == 3){
				OWLObjectProperty p = factory.getOWLObjectProperty(IRI.create(cachedIRIsInverse.get(r.get(1))));
				Set<OWLObjectPropertyExpression> subPs = reasoner.getSubObjectProperties(p, false).getFlattened();
				subPs.remove(Ontologies.OWLP_OP_Bottom);  
				subPs.forEach(sp -> newAR.add(Arrays.asList(r.get(0), MasterOntology.cachedIRIs.get(sp.getNamedProperty().getIRI().toString()), r.get(2))));
			}
		}
		authorizatedRels.addAll(newAR);
	}
	
	
	/*public static Set<OWLClass> GetClassesOf(OWLNamedIndividual i, boolean getOnlyDirectClasses){
		return reasoner.getTypes(i, getOnlyDirectClasses).getFlattened();
	}*/
}
