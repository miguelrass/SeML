package org.rass.restrictions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.xtext.xbase.lib.Pair;
import org.rass.ontologies.MasterCache;
import org.rass.ontologies.MasterOntology;
import org.rass.ontologies.Ontologies;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.ClassExpressionType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;
import org.semanticweb.owlapi.model.OWLOntology;
import org.xtext.seml.Console;
import org.xtext.seml.seML.Characteristic;

public class CharacteristicsSolver {
	
	private static final String local_log = "Characteristics Solver Log: ";
	private static Set<OWLClass> chrListRequired = null;
	private static Set<OWLClass> chrListRejected = null;
	private static ArrayList<Pair<Set<OWLClassExpression>,Boolean>> orListPending = null;
	
	private static final OWLDataFactory factory = OWLManager.getOWLDataFactory();
	private static OWLOntology ontology = null;
	private static OWLObjectProperty OWL_OP_Requires = factory.getOWLObjectProperty(IRI.create(Ontologies.OWL_Requires));
	
	public static String chrProblem = null;
	
	public static Set<OWLClass> GetRequiredCharacteristics(){
		return chrListRequired;
	}
	
	private static void WriteProblem(){
		
		StringBuilder problem = new StringBuilder(200); //set initial capacity to 200 chars
		
		for(Pair<Set<OWLClassExpression>, Boolean> orList : orListPending){
			problem.append("Should use one of: ");
			
			Iterator<OWLClassExpression> it = orList.getKey().iterator();
			
			//there's always at least one alternative
			while(it.hasNext()){
				String ch = MasterOntology.cachedIRIs.get(it.next().asOWLClass().getIRI().toString());
				if(ch==null) ch = "Characteristic_Not_Found";
				problem.append(ch + " or ");
			}
			
			problem.setLength(problem.length()-4);
			problem.append("\n");
		}
		
		chrProblem = problem.substring(0, problem.length()-1);//remove "\n"
	}
	
	/**
	 * This method will create a list with all the Characteristics which the model should abide by
	 * @param chrList
	 * @param ont
	 * @return true if tree was built and all variabilities are solved
	 * @throws Exception
	 */
	public static boolean BuildCharacteristicsTree(List<Characteristic> chrList, OWLOntology ont) throws Exception{
		
		ontology = ont;
		chrListRequired = new HashSet<OWLClass>();
		chrListRejected = new HashSet<OWLClass>();
		orListPending = new ArrayList<Pair<Set<OWLClassExpression>,Boolean>>();
		
		ParseNode( factory.getOWLClass( IRI.create(Ontologies.OWL_DefaultCh))); //add default characteristic
		
		for(Characteristic ch : chrList){
			ParseNode( factory.getOWLClass( IRI.create(ch.getIri()) ) );
		}
			
		boolean progressFlag;
		
		//Iteratively solve OR variabilities until the iteration yields no result
		do{
			
			//Check if algorithm has ended
			if(orListPending.isEmpty()){
				Console.DebPair(local_log, "Characteristics being used: ");
				StringBuilder strChrList = new StringBuilder(100); //list of characteristics
				chrListRequired.forEach(ch -> strChrList.append(MasterOntology.cachedIRIs.get(ch.getIRI().toString()) + ", "));
				Console.DebPairLn(local_log, strChrList.substring(0, strChrList.length()-2));
				return true; //the tree was built and all variabilities are solved
			}
			
			ArrayList<Pair<Set<OWLClassExpression>,Boolean>> currentOrList = orListPending;
			orListPending = new ArrayList<Pair<Set<OWLClassExpression>,Boolean>>();
			progressFlag = false;
			
			for(Pair<Set<OWLClassExpression>,Boolean> orList : currentOrList){
				if(ParseOR(orList.getKey(), orList.getValue())) progressFlag = true;
			}
		}while(progressFlag);
		
		WriteProblem();
		return false; //some variabilities are unsolved
		
	}
	
	private static void ParseNode(OWLClass node) throws Exception{
		
		AddToRequired(node);
 
		//Iterate through all "requires" Object Properties
		for(OWLClassExpression ce : MasterCache.chrRestrictions.get(node)){
			
			//If "SOME"
			if(ce.getClassExpressionType() == ClassExpressionType.OBJECT_SOME_VALUES_FROM){
				OWLObjectSomeValuesFrom requiresLink = (OWLObjectSomeValuesFrom) ce;
				
				//Jump non-"requires" Object Properties
				if(!requiresLink.getProperty().equals(OWL_OP_Requires)) continue; 
				
				//Get target
				OWLClassExpression target = requiresLink.getFiller();
				
				//Check if target class expression is a union of classes
				if(target.getClassExpressionType() != ClassExpressionType.OBJECT_UNION_OF) 
					throw new Exception("Illegal target class expression (only a Union is accepted): " + requiresLink.toString());
				
				//Attempt to solve variability (OR)
				ParseOR(((OWLObjectUnionOf)target).getOperands(), false);
				
				continue;
			}
			
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
					if(!chrListRequired.contains(targetClass)) ParseNode(targetClass);
				}else{
					
					//Check if target class expression is a union of classes
					if(target.getClassExpressionType() != ClassExpressionType.OBJECT_UNION_OF) 
						throw new Exception("Illegal target class expression (only a Union is accepted): " + requiresLink.toString());
					
					//Attempt to solve variability (XOR)
					ParseOR(((OWLObjectUnionOf)target).getOperands(), true);
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
	private static boolean ParseOR(Set<OWLClassExpression> operands, boolean XOR) throws Exception{
		
		Set<OWLClass> candidates = new HashSet<OWLClass>();

		if(operands.isEmpty()) throw new Exception("Illegal empty operands Union");
		
		//Iterate through all operands
		for(OWLClassExpression orCE : operands){
			if(orCE.getClassExpressionType() != ClassExpressionType.OWL_CLASS) 
				throw new Exception("Illegal union operand (only a Class is accepted): " + orCE.toString());
			
			OWLClass orClass = orCE.asOWLClass();
			
			//Check if operand is already required (Job done)
			if(chrListRequired.contains(orClass)){
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

			if(XOR){//Reject all other classes (if XOR)
				for(OWLClassExpression orCE : operands){
					if(!orCE.equals(chosenOne)) AddToRejected(orCE.asOWLClass());
				}
			}
			
			//Keep traversing if the node was not already inserted
			if(!chrListRequired.contains(chosenOne)) ParseNode(chosenOne);
			return true;
			
		}else{
			orListPending.add(new Pair<Set<OWLClassExpression>,Boolean>(operands, XOR)); //store
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
			throw new Exception("Characteristic \"" + MasterOntology.cachedIRIs.get(node.getIRI().toString()) + "\" is both required and rejected");
		return chrListRequired.add(node);
	}
	
	/**
	 * Adds a node to the Rejected list (throws exception if it already exists in the Required list)
	 * @param node
	 * @throws Exception 
	 */
	private static void AddToRejected(OWLClass node) throws Exception{
		if(chrListRequired.contains(node))
			throw new Exception("Characteristic \"" + MasterOntology.cachedIRIs.get(node.getIRI().toString()) + "\" is both required and rejected");
		chrListRejected.add(node);
	}
	
	
}
