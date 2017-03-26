package org.rass.ontologies;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.zip.DataFormatException;

import org.rass.ontologies.Anomaly.ReportLevel;
import org.rass.swrl.CustomSWRLBuiltin;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.ClassExpressionType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.xtext.seml.seML.Component;
import org.xtext.seml.seML.ImportModel;
import org.xtext.seml.seML.Individual;
import org.xtext.seml.seML.MetaIndividual;
import org.xtext.seml.seML.Relation;
import org.xtext.seml.seML.UseCharacteristic;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;

public class CharacteristicsSolver {
	
	private static final String local_log = "Characteristics Solver Log: ";
	private static Set<String> chrListRequired = null;
	private static Set<OWLClass> chrListRejected = null;
	private static ArrayList<Set<OWLClassExpression>> orListPending = null;
	
	private static final OWLDataFactory factory = OWLManager.getOWLDataFactory();
	private static OWLOntology ontology = null;
	private static OWLObjectProperty OWL_OP_Requires = factory.getOWLObjectProperty(IRI.create(Ontologies.OWL_Requires));
	private static HashMap<String, String> cachedIRIs = null; //Key: Full IRI
	
	public static String chrSolution = null;
	public static String chrProblem = null;
	
	public static Set<String> GetRequiredCharacteristics(){
		return chrListRequired;
	}
	
	private static void WriteSolution(){
		
		StringBuilder solution = new StringBuilder(200); //set initial capacity to 200 chars
		StringBuilder problem = new StringBuilder(200); //set initial capacity to 200 chars
		solution.append("\n");
		
		for(Set<OWLClassExpression> orList : orListPending){
			solution.append("use ");
			problem.append("Should use one of: ");
			
			Iterator<OWLClassExpression> it = orList.iterator();
			OWLClass i = it.next().asOWLClass();
			String op = cachedIRIs.get(i.getIRI().toString());
			if(op==null) op = "Characteristic_Not_Found";
			
			//there's always at least one alternative
			solution.append(op + " // or ");
			problem.append(op + " or ");
			
			while(it.hasNext()){
				op = cachedIRIs.get(it.next().asOWLClass().getIRI().toString());
				if(op==null) op = "Characteristic_Not_Found";
				solution.append(op + " or ");
				problem.append(op + " or ");
			}
			
			solution.setLength(solution.length()-4);
			problem.setLength(problem.length()-4);
			solution.append("\n");
			problem.append("\n");
		}
		
		solution.setLength(solution.length()-1); //remove "\n"
		problem.setLength(problem.length()-1);
		chrSolution = solution.toString();
		chrProblem = problem.toString();
	}
	
	/**
	 * This method will create a list with all the Characteristics which the model should abide by
	 * @param chrList
	 * @param ont
	 * @return true if tree was built and all variabilities are solved
	 * @throws Exception
	 */
	public static boolean BuildCharacteristicsTree(List<UseCharacteristic> chrList, OWLOntology ont, HashMap<String, String> ci) throws Exception{
		
		cachedIRIs = ci;
		ontology = ont;
		chrListRequired = new HashSet<String>();
		chrListRejected = new HashSet<OWLClass>();
		orListPending = new ArrayList<Set<OWLClassExpression>>();
		
		ParseNode( factory.getOWLClass( IRI.create(Ontologies.OWL_DefaultC))); //add default characteristic
		
		for(UseCharacteristic ch : chrList){
			ParseNode( factory.getOWLClass( IRI.create(ch.getName().getIri()) ) );
		}
			
		boolean progressFlag;
		
		//Iteratively solve OR variabilities until the iteration yields no result
		do{
			
			//Check if algorithm has ended
			if(orListPending.isEmpty()){
				System.out.println(local_log + "Characteristics being used:");
				chrListRequired.forEach(ch -> System.out.println(local_log + ch));
				return true; //the tree was built and all variabilities are solved
			}
			
			ArrayList<Set<OWLClassExpression>> currentOrList = orListPending;
			orListPending = new ArrayList<Set<OWLClassExpression>>();
			progressFlag = false;
			
			for(Set<OWLClassExpression> orList : currentOrList){
				if(ParseOR(orList)) progressFlag = true;
			}
		}while(progressFlag);
		
		WriteSolution();
		return false; //some variabilities are unsolved
		
	}
	
	private static void ParseNode(OWLClass node) throws Exception{
		
		AddToRequired(node);
		
		RestrictionVisitor restrVisitor = new RestrictionVisitor(ontology); //create visitor object	    
		ontology.getSubClassAxiomsForSubClass(node).forEach(ax -> restrVisitor.visit(ax.getSuperClass()));// visit all restrictions    		    
		List<OWLClassExpression> ClsRestrList = restrVisitor.getRestrictions(); //get all restriction for the given class

		//Iterate through all "requires" Object Properties
		for(OWLClassExpression ce : ClsRestrList){
			
			//Jump non-"EXACT CARDINALITY" (the result still contains non-"requires" Object Properties)
			if(ce.getClassExpressionType() != ClassExpressionType.OBJECT_EXACT_CARDINALITY) continue; 
			OWLObjectExactCardinality requiresLink = (OWLObjectExactCardinality) ce;
			
			//Jump non-"requires" Object Properties
			if(!requiresLink.getProperty().equals(OWL_OP_Requires)) continue; 
			
			//Get target
			OWLClassExpression target = requiresLink.getFiller();
			OWLClass targetClass = null;
			if(target.getClassExpressionType() == ClassExpressionType.OWL_CLASS) targetClass = target.asOWLClass();

			//If it is a Rejection relation
			if(requiresLink.getCardinality() == 0){
				//Check if target class expression is a Class (and not a logical expression)
				if(targetClass == null) 
					throw new Exception("Illegal target class expression for cardinality 0: " + requiresLink.toString());
				
				AddToRejected(targetClass);
			}
			
			//If it is a Requirement relation
			else if(requiresLink.getCardinality() == 1){
				
				if(targetClass != null){	
					//Keep traversing if the node was not already inserted
					if(AddToRequired(targetClass)) ParseNode(targetClass);
				}else{
					
					//Check if target class expression is a union of classes
					if(target.getClassExpressionType() != ClassExpressionType.OBJECT_UNION_OF) 
						throw new Exception("Illegal target class expression (only a Union is accepted): " + requiresLink.toString());
					
					OWLObjectUnionOf targetUnion = (OWLObjectUnionOf)target;
					
					//Attempt to solve variability
					ParseOR(targetUnion.getOperands());
				}
					
			}
			
			//Illegal cardinality
			else{
				throw new Exception("Illegal cardinality in expression: " + requiresLink.toString());
			}
		}
		
	}
	
	/**
	 * This method will attempt to solve the variability, using the required and rejected lists
	 * If it fails, the variability is stored for later
	 * @param operands
	 * @return true if the OR was solved
	 * @throws Exception
	 */
	private static boolean ParseOR(Set<OWLClassExpression> operands) throws Exception{
		
		Set<OWLClass> candidates = new HashSet<OWLClass>();

		if(operands.isEmpty()) throw new Exception("Illegal empty operands Union");
		
		for(OWLClassExpression orCE : operands){
			if(orCE.getClassExpressionType() != ClassExpressionType.OWL_CLASS) 
				throw new Exception("Illegal union operand (only a Class is accepted): " + orCE.toString());
			
			OWLClass orClass = orCE.asOWLClass();
			
			//Check if operand is already required (Job done)
			if(chrListRequired.contains(orClass.getIRI().toString())){
				candidates.clear(); //clear previous candidates
				candidates.add(orClass); //only candidate
				break;
			}
			
			//If it is not rejected, it is a candidate
			if(!chrListRejected.contains(orClass)){
				candidates.add(orClass); //add possible candidate
			}
		}
		
		//Evaluation step: solve the union or store it for later
		if(candidates.size() == 1){ //solve it
			OWLClass chosenOne = candidates.iterator().next();

			//Reject all other classes
			for(OWLClassExpression orCE : operands){
				if(!orCE.equals(chosenOne)) AddToRejected(orCE.asOWLClass());
			}
			
			//Keep traversing if the node was not already inserted
			if(AddToRequired(chosenOne)) ParseNode(chosenOne);
			return true;
			
		}else{
			orListPending.add(operands); //store
			return false;
		}
		
	}
	
	/**
	 * Adds a node to the Required list (throws exception if it already exists in the Rejected list)
	 * @param node
	 * @return true if the node was inserted (false if already existed)
	 * @throws Exception 
	 */
	private static boolean AddToRequired(OWLClass node) throws Exception{
		if(chrListRejected.contains(node)) 
			throw new Exception("Characteristic \"" + cachedIRIs.get(node.getIRI().toString()) + "\" is both required and rejected");
		return chrListRequired.add(node.getIRI().toString());
	}
	
	/**
	 * Adds a node to the Rejected list (throws exception if it already exists in the Required list)
	 * @param node
	 * @throws Exception 
	 */
	private static void AddToRejected(OWLClass node) throws Exception{
		if(chrListRequired.contains(node.getIRI().toString()))
			throw new Exception("Characteristic \"" + cachedIRIs.get(node.getIRI().toString()) + "\" is both required and rejected");
		chrListRejected.add(node);
	}
	
	
}
