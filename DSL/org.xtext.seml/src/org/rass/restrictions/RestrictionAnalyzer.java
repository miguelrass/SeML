package org.rass.restrictions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Nonnull;

import org.eclipse.xtext.xbase.lib.Pair;
import org.rass.ontologies.MasterCache;
import org.rass.ontologies.MasterOntology;
import org.rass.ontologies.Ontologies;
import org.rass.restrictions.Problem.TypeE;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.xtext.seml.validation.SeMLValidator;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;


/**
 * This class analyzes a restriction of an individual (Class Expressions)
 * These restrictions come from the individual's classes and super-classes
 * and are expressed through Anonymous Classes 
 * 
 * @goal Mitigate open world limitations
 * 
 * If a problem is found, the "problem" variable in the Model Solution won't be null
 * and a solution may be provided. A solution can only add knowledge.
 * 
 * @author Miguel Abreu
 * 
 */
public class RestrictionAnalyzer implements OWLClassExpressionVisitor {
	
	private static final String local_log = "Restriction Analyser Log: ";
	
	private ArrayList<Problem> problems;				//Problems/Solutions for the Validator/Quickfix
	private OWLNamedIndividual individual;				//Individual which is being evaluated
	private String individualAlias;						//Individual's Alias
	private PelletReasoner reasoner;					//Reasoner to perform ontology related operations
	private PelletReasoner fullReasoner;				//Reasoner to get individuals from full master
	private HashSet<List<String>> authorizatedRels;			//Relations which are authorized by restrictions (except max/only)
	private static final OWLDataFactory factory = OWLManager.getOWLDataFactory();
	
	//=================================================== Ignore certain restrictions
	private static final String OWL_OP_isCharacterizedBy = Ontologies.OWL_Upper + "isCharacterizedBy";
	private static final HashSet<String> IgnoreOPs = new HashSet<String>(Arrays.asList(OWL_OP_isCharacterizedBy));
	private static final String DP_hasValue = Ontologies.OWL_DP_hasValue;

	//=================================================== Expressions which are not supported
	@Override public void visit(OWLClass arg0) {System.err.println(local_log + "Internal Error: no class can exist at this point!");}
	@Override public void visit(@Nonnull OWLObjectOneOf exp){System.err.println(local_log + "Internal Error -> "+exp);} //this should never happen (its just a list of individuals)
	@Override public void visit(OWLObjectComplementOf exp) {System.err.println(local_log + "\"Not\" is not allowed here -> "+exp);}
	
	//=================================================== Data Property which are not supported
    @Override public void visit(@Nonnull OWLDataHasValue exp) 			{UnsupportedDP(exp.getProperty(), exp.toString());}
    @Override public void visit(@Nonnull OWLDataMinCardinality exp) 	{UnsupportedDP(exp.getProperty(), exp.toString());}
    @Override public void visit(@Nonnull OWLDataExactCardinality exp) 	{UnsupportedDP(exp.getProperty(), exp.toString());}
    @Override public void visit(@Nonnull OWLDataMaxCardinality exp)		{UnsupportedDP(exp.getProperty(), exp.toString());}
    private void UnsupportedDP(OWLDataPropertyExpression pe, String exp){
    	if(DP_hasValue.equals(pe.asOWLDataProperty().getIRI().toString()))
    		System.err.println(local_log + "This Data Property is not supported! -> " + exp);
    }
    //=================================================== Constructor
    public RestrictionAnalyzer(ArrayList<Problem> ps, HashSet<List<String>> ar, OWLNamedIndividual i, PelletReasoner r, PelletReasoner fr){
    	problems = ps; individual = i; reasoner = r; fullReasoner = fr; 
    	individualAlias = MasterOntology.cachedIRIs.get(individual.getIRI().toString());
    	authorizatedRels = ar;
    }
    
    //=============================================================================================
    //================================================= Auxiliary Functions (for Object Properties)
    //=============================================================================================
    /**
     * Method that obtains the related individuals via a certain OP, within a certain range, of a certain individual
     * It also obtains relatable individuals (other individuals within the specified range, could be itself)
     * @param i	the individual which has the relations
     * @param p the type of relation
     * @param e the range of the relation
     * @return related individuals and relatable individuals
     */
	private Pair<Set<OWLNamedIndividual>,Set<OWLNamedIndividual>> getRelations(OWLNamedIndividual i, OWLObjectPropertyExpression p, OWLClassExpression e){ 	
    	
    	Set<OWLNamedIndividual> relatives = reasoner.getObjectPropertyValues(i, p).getFlattened(); //Individuals that are related to "i" via "p"
    	Set<OWLNamedIndividual> targets; // (In)direct individuals of the provided Class Expression
    	
    	try { targets = fullReasoner.getInstances(e, false).getFlattened();} 
    	catch (Exception ex) {
    		System.err.println("Internal Error: remove this print line only"); //this exception might be outdated
			return null; // The getInstances() procedure fails when no individuals are found (when using complex Class Expressions)
		}
    	
    	targets.retainAll(MasterCache.allIndividuals); //Remove targets which are not Component Individuals
    	relatives.retainAll(targets); // Intersect both groups to obtain real relatives
    	targets.removeAll(relatives); // Obtain relatable individuals (could be itself)

    	return new Pair<Set<OWLNamedIndividual>,Set<OWLNamedIndividual>>(relatives, targets);
    }
    
    /**
     * Creates problems/solutions for max cardinality restrictions
     * @param max
     * @param obj
     * @param target
     * @param restrictionStr
     */
    private void MaxCardinality(int max, OWLObjectPropertyExpression obj, OWLClassExpression target, String restrictionStr){
    	
    	String objIRI = obj.getNamedProperty().getIRI().toString(); //object property IRI
    	String objAlias = MasterOntology.cachedIRIs.get(obj.getNamedProperty().getIRI().toString());
    	String targetStr = target.toString();
    	
    	if(IgnoreOPs.contains(objIRI)) return; //ignore these ObjProperties
    	int cardinality = getRelations(individual, obj, target).getKey().size();
    	
    	if(max < cardinality){
    		if(target instanceof OWLClass) targetStr = Ontologies.GetShortIRI(target.asOWLClass().getIRI());
    		problems.add(new Problem("Restriction: " + objAlias + restrictionStr + targetStr 
    				+ "\nISSUE: " + cardinality + " relation(s) found"));
    	}
 	}

    /**
     * Creates problems/solutions for requirement restrictions
     * @param min
     * @param obj
     * @param target
     * @param restrictionStr
     */
    private void Requirement(int min, OWLObjectPropertyExpression obj, OWLClassExpression target, String restrictionStr){
    	
    	String objIRI = obj.getNamedProperty().getIRI().toString(); //object property IRI
    	String objAlias = MasterOntology.cachedIRIs.get(obj.getNamedProperty().getIRI().toString());
    	String targetStr = target.toString();
    	int cardinality, defect;
    	
    	if(IgnoreOPs.contains(objIRI)) return; //ignore these ObjProperties
    	Pair<Set<OWLNamedIndividual>, Set<OWLNamedIndividual>> relatives;
    	relatives =	getRelations(individual, obj, target); //get related individuals
    	cardinality = relatives.getKey().size(); //get real cardinality
    	defect = min-cardinality;
    			
    	//Authorize existing relations
    	for (OWLNamedIndividual i : relatives.getKey())
    		authorizatedRels.add(Arrays.asList(individualAlias, objAlias, MasterOntology.cachedIRIs.get(i.getIRI().toString())));
    	
    	if(target instanceof OWLClass) targetStr = Ontologies.GetShortIRI(target.asOWLClass().getIRI());
    	
    	//================================================================================== Analyze possible solutions
    	//=============================================================================================================
        if(defect>0){
        	
        	//---------------------------------------------- Impossible restriction (but may become possible)
        	if(defect > relatives.getValue().size()){
        		problems.add(new Problem("Restriction: " + objAlias + restrictionStr + targetStr
        				+ "\nISSUE: " + cardinality + " relation(s) found" + "\nSOLUTION: None. "+ 
						"There aren't enough individuals left: " + relatives.getValue() + "\n"));	
        	
        	//---------------------------------------------- Solution may be possible
        	}else{

        		//- - - - - - - - - - - - - Get all combinations of individual kinds (individuals with the same class set)
        		
        		List<Integer> individualKinds = new ArrayList<Integer>();
        		relatives.getValue().forEach(r -> individualKinds.add(MasterCache.individualsClassSet.get(r)));
        		List<int[]> combs = Multiset.Get(individualKinds, defect); 
        		  		
    			//- - - - - - - - - - - - - Test all combinations
        		
        		List<int[]> okCombs = new ArrayList<int[]>(); //List of combinations which passed the test

				for(int[] comb : combs){
					
					OWLOntologyManager testMan = OWLManager.createOWLOntologyManager();

					OWLOntology testOnt;
					try {	
		        		testOnt = testMan.createOntology(MasterOntology.GetFullMaster(),IRI.create("Te:st"));
	        		} catch (OWLOntologyCreationException e) {
						System.out.println(local_log + "Error creating test Ontology");
	    		    	continue; //don't break so that every result is registered as a failure
					}
						
					//Get individuals to be tested (these might be static, already related, already referenced or free)
					List<OWLNamedIndividual> testInds = new ArrayList<OWLNamedIndividual>();
					int cnt = 0, setN = -1;
					for(int i=0;i<comb.length; i++){
						if(setN != comb[i]){ setN = comb[i]; cnt = 0; }
						testInds.add(MasterCache.individualsByClassSet.get(setN).get(cnt++));
					}
					
					//Get list of individuals to remove from testOnt
					@SuppressWarnings("unchecked")
					HashSet<OWLNamedIndividual> indsToRemove = (HashSet<OWLNamedIndividual>) MasterOntology.indsToRemove.clone();
					indsToRemove.removeAll(testInds); //keep individuals to be tested
					
					//Remove elements from testOnt
					OWLEntityRemover remover = new OWLEntityRemover(Collections.singleton(testOnt));
					indsToRemove.forEach(i -> i.accept(remover));
					testMan.applyChanges(remover.getChanges());
					
					//Insert relations with new individuals in testOnt
					for(OWLNamedIndividual i :  testInds){
						OWLObjectPropertyAssertionAxiom axiom = factory.getOWLObjectPropertyAssertionAxiom(obj, individual, i);
						testMan.addAxiom(testOnt, axiom);
					}

					//Create reasoner and test combination
					PelletReasoner rea = PelletReasonerFactory.getInstance().createNonBufferingReasoner(testOnt);
					boolean ok = rea.isConsistent();

					if(ok){ //only if consistent (adding individuals also adds static relations or literals)
						for(OWLClass ch: CharacteristicsSolver.GetRequiredCharacteristics()){
		    			
			    			RestrictionAnalyzer3 RA = new RestrictionAnalyzer3(rea);
			    		    MasterCache.chrRestrictions.get(ch).forEach(r -> r.accept(RA)); //Evaluate each Restr.
			    		    if(RA.HasFailed()){
			    		    	ok = false;
			    		    	break; //if one restriction fails, the combination has failed
			    		    }
			    		}
			    		
			    		if(ok) okCombs.add(comb); //this combination passed the test
					}
				}
				
				//- - - - - - - - - - - - - Enumerate all possible solutions
				
				Problem p = new Problem();
				for(int[] comb : okCombs){ //iterate all kind-combinations
					
					List<Integer> individuals = new ArrayList<Integer>();
					
					//List all individuals of each kind (only those which are relatable)
					for(int i=0; i<comb.length; i++){
						int setIndCnt = MasterCache.individualsByClassSet.get(comb[i]).size();
						for(int j=0; j<setIndCnt; j++){
							if(relatives.getValue().contains(MasterCache.individualsByClassSet.get(comb[i]).get(j))) //relatable
								individuals.add(j+1_000_000*comb[i]); //convert individual to number
						}
					}
					
					//Get all individual combinations for the current kind combination
					List<int[]> indCombs = Multiset.Get(individuals, comb.length); 
					
					//Filter individual combinations
					indCombs.removeIf(ic -> {
						for(int i=0; i<ic.length; i++){
							if(i!=0 && ic[i]==ic[i-1]) return true; //remove repeated individuals
							if(ic[i] / 1_000_000 != comb[i]) return true; //only allow a certain kind sequence 
						}
						return false;
					});

					//Filter individual combinations and add solutions
					indCombs.forEach(ic -> {
						List<OWLNamedIndividual> relatables = new ArrayList<OWLNamedIndividual>();
						
						boolean ok = true;
						for(int i=0; i<ic.length; i++){
							if(i!=0 && ic[i]==ic[i-1]) {ok=false;break;} //remove repeated individuals
							if(ic[i] / 1_000_000 != comb[i]) {ok=false;break;} //only allow a certain kind sequence 
							relatables.add(MasterCache.individualsByClassSet.get(comb[i]).get(ic[i] % 1_000_000)); //convert again
						}
						if(ok){ //Add solution
							List<String> solution = new ArrayList<String>(Arrays.asList(individualAlias, objAlias));
							relatables.forEach(r -> solution.add(MasterOntology.cachedIRIs.get(r.getIRI().toString())));
							p.AddSolution(solution);
						}
					});
				}
				
				//- - - - - - - - - - - - - Add problem label
				
        		if(p.type == TypeE.UNSOLVABLE){
        			p.problemLabel = "Restriction: " + objAlias + restrictionStr + targetStr
					+ "\nISSUE: " + cardinality + " relation(s) found"
					+ "\nSOLUTION: None. Characteristics restrictions blocked all solutions.";
        		}else{
        			p.problemLabel = "Restriction: " + objAlias + restrictionStr + targetStr
        			+ "\nISSUE: " + cardinality + " relation(s) found"
        			+ "\nSOLUTION: " + p.solutions.size() + " possible solution(s)";
        		}
        		
        		problems.add(p);
        	}
        }	
    }
    
	private void HasValue(OWLObjectPropertyExpression obj, OWLNamedIndividual target) { 
		
		String objIRI = obj.getNamedProperty().getIRI().toString(); //object property IRI
		if(IgnoreOPs.contains(objIRI)) return; //ignore these ObjProperties
		
		boolean hasValue = reasoner.getObjectPropertyValues(individual, obj).getFlattened()//Individuals that are related to "individual" via "obj"
    			.contains(target);
		
		String objAlias = MasterOntology.cachedIRIs.get(obj.getNamedProperty().getIRI().toString());
		String targetAlias = MasterOntology.cachedIRIs.get(target.getIRI().toString());
		
		if(hasValue){ //No problem, authorize and return
			authorizatedRels.add(Arrays.asList(individualAlias, objAlias, targetAlias));
			return; 
		}

		//Create Problem
		problems.add(new Problem("Restriction: " + objAlias + " " + targetAlias + "\nISSUE: relation not found\n",
				Arrays.asList(individualAlias, objAlias, targetAlias) ));

	}
    
    //=============================================================================================
    //====================================================== Primary Functions (Logical Expression)
    //=============================================================================================
    /**
     * Evaluates logical expression: (Restriction AND Restriction AND ...)
     * If one restriction is not met, a problem is reported
     * This means than all problems that are added to the list are valid
     */
	@Override public void visit(OWLObjectIntersectionOf exp) {
		
		for(OWLClassExpression ce : exp.getOperands()){   		
    		RestrictionAnalyzer RA = new RestrictionAnalyzer(problems, authorizatedRels, individual, reasoner, fullReasoner);
    		ce.accept(RA);
    	}
	}

	/**
	 * Evaluates logical expression: (Restriction OR Restriction OR ...)
	 * 
	 * Rules:
	 * 1. 	If one restrictions is met, the problems list is not changed
	 * 1.1. If there >1 faulty operand, all solutions are manual (this is guaranteed if all operands fail)
	 * 2.	All operands must fill authorizations as needed (it's an OR, not XOR)
	 */
	@Override public void visit(OWLObjectUnionOf exp) {
		
		ArrayList<Problem> problemsTemp = new ArrayList<Problem>();
		int lastState = 0; //Number of problems/solutions of the temporary list
		int faultyOperands = 0; //Number of operands with problems
    			
    	for(OWLClassExpression ce : exp.getOperands()){
    		
    		RestrictionAnalyzer RA = new RestrictionAnalyzer(problemsTemp, authorizatedRels, individual, reasoner, fullReasoner);
    		ce.accept(RA);
    		
    		if(problemsTemp.size() != lastState) faultyOperands++; //If one restriction is met, there is no problem to report
    		lastState = problemsTemp.size();
    	}
    	
    	if(faultyOperands == exp.getOperands().size()){ // All OR operands generated optional solutions
    		//TODO: era preciso diferenciar unsolvable de impossible, pq so devia ser manual se houvesse >1 solucao !impossible
    		problemsTemp.forEach(p -> {p.type = TypeE.OrPROBLEM; p.problemLabel += "\n(The solution is optional)";}); 
    		problems.addAll(problemsTemp); //update real solutions list
    	}

	}

	/**
	 * Evaluates logical expression: not(Restriction OR Restriction AND ...)
     * Tries to normalize expression with DeMorgan laws
	 */
	/*@Override public void visit(OWLObjectComplementOf exp) {
		
		RestrictionAnalyzer RA = new RestrictionAnalyzer(problems, authorizatedRels, individual, reasoner, fullReasoner);
		
		//Complement of "has self" is not simplifiable
		if(exp.getOperand().getClassExpressionType() == ClassExpressionType.OBJECT_HAS_SELF)
			{HasValue(((OWLObjectHasSelf) exp.getOperand()).getProperty(), individual, true); return;}
		
		//Complement of "has value" is simplifiable but this way is faster
		if(exp.getOperand().getClassExpressionType() == ClassExpressionType.OBJECT_HAS_VALUE){
			OWLObjectHasValue ohv = (OWLObjectHasValue) exp.getOperand();
			HasValue(ohv.getProperty(), ohv.getFiller().asOWLNamedIndividual(), true); return;}
			
		OWLClassExpression ce = exp.getNNF();
		
		//If the normalized version is still an OWLObjectComplementOf
		if(ce.getClassExpressionType() == ClassExpressionType.OBJECT_COMPLEMENT_OF)
			{System.err.println(local_log + "Unexpected expression!"); return;}
		
		//Analyze normalized expression instead		
		ce.accept(RA);
		
	}*/

	//=============================================================================================
    //===================================================== Primary Functions (Object Restrictions)
    //=============================================================================================
	@Override public void visit(OWLObjectSomeValuesFrom exp) { //Requirement 
		
    	OWLObjectPropertyExpression obj = exp.getProperty(); 	//object property
    	OWLClassExpression target = exp.getFiller(); 			//target Class Expression  	
    	Requirement(1, obj, target, " some ");
		
	}

	
	@Override public void visit(OWLObjectMinCardinality exp) { //Requirement 
		
    	OWLObjectPropertyExpression obj = exp.getProperty(); 	//object property
    	OWLClassExpression target = exp.getFiller(); 			//target Class Expression  	
    	int min = exp.getCardinality(); 						//get desired cardinality
    	Requirement(min, obj, target, " min " + min + " ");
    	
	}

	
	@Override public void visit(OWLObjectExactCardinality exp) { //Requirement + Restriction
		
    	OWLObjectPropertyExpression obj = exp.getProperty(); 			//object property
    	OWLClassExpression target = exp.getFiller(); 					//target Class Expression  	
    	int exact = exp.getCardinality(); 								//get desired cardinality
    	Requirement(exact, obj, target, " exactly " + exact + " "); 	//Requirement
    	MaxCardinality(exact, obj, target, " exactly " + exact + " "); 	//Restriction
		
	}

	
	@Override public void visit(OWLObjectHasSelf exp) { //Requirement

    	OWLObjectPropertyExpression obj = exp.getProperty(); 	//object property
    	HasValue(obj, individual);						//auxiliary method
    	
	}

	@Override public void visit(OWLObjectHasValue exp) { //Requirement
		
		OWLObjectPropertyExpression obj = exp.getProperty(); 			//object property
		HasValue(obj, exp.getFiller().asOWLNamedIndividual());	//auxiliary method

	}
	
	@Override public void visit(OWLObjectAllValuesFrom exp) { //Restriction
		
		OWLObjectPropertyExpression obj = exp.getProperty(); 	//object property
    	OWLClassExpression target = exp.getFiller(); 			//target Class Expression  	
    	
    	String objIRI = obj.getNamedProperty().getIRI().toString(); //object property IRI
    	String objAlias = MasterOntology.cachedIRIs.get(obj.getNamedProperty().getIRI().toString());
    	String targetStr = target.toString(); //auxiliary string to display target
    	
    	Set<OWLNamedIndividual> desired = getRelations(individual, obj, target).getKey();
    	Set<OWLNamedIndividual> all = getRelations(individual, obj, Ontologies.OWLC_Component).getKey();
    	
    	if(IgnoreOPs.contains(objIRI)) return; //ignore these ObjProperties

    	//Check if number of relations with components is the same as the number of desired relations
    	if(desired.size() != all.size()){
    		all.removeAll(desired);
    		if(target instanceof OWLClass) targetStr = Ontologies.GetShortIRI(target.asOWLClass().getIRI());
    		problems.add(new Problem("Restriction: " +objAlias+ " only " +targetStr+ "\nISSUE: noncompliant targets: " + all));
    	}
    	
	}
	
	@Override public void visit(OWLObjectMaxCardinality exp) { //Restriction
		
		OWLObjectPropertyExpression obj = exp.getProperty(); 	//object property
    	OWLClassExpression target = exp.getFiller();			//target Class Expression  	
    	int max = exp.getCardinality();							//get desired cardinality
		MaxCardinality(max, obj, target, " max " + max + " ");	//Restriction

	}
	
	//=============================================================================================
    //======================================================= Primary Functions (Data Restrictions)
    //=============================================================================================
	
    @Override public void visit(OWLDataSomeValuesFrom exp) 	{
    	
    	if(!DP_hasValue.equals(exp.getProperty().asOWLDataProperty().getIRI().toString())) return; //ignore other OBJ Properties
    	
    	if(reasoner.getDataPropertyValues(individual, Ontologies.OWLP_DP_hasValue).isEmpty()){
    		
    		Problem p = new Problem();
    		
    		//For each default value, create a solution
    		for (OWLAnnotationAssertionAxiom a : reasoner.getRootOntology().getAnnotationAssertionAxioms(individual.getIRI())) { //gets annotations associated with the individual
            	if(Ontologies.OWL_Ann_DefaultValue.equals(a.getProperty().getIRI().toString())){
            		
            		OWLLiteral l = a.getValue().asLiteral().get(); 
            		String lval = l.getLiteral();
            		
            		if(lval.isEmpty()) 		p.AddSolution(Arrays.asList(individualAlias, "s"));
            		else if(l.isBoolean()) 	p.AddSolution(Arrays.asList(individualAlias, "b"+lval));
            		else if(l.isDouble()) 	p.AddSolution(Arrays.asList(individualAlias, "f"+lval));
            		else if(l.isFloat()) 	p.AddSolution(Arrays.asList(individualAlias, "f"+lval));
            		else if(l.isInteger())	p.AddSolution(Arrays.asList(individualAlias, "i"+lval));
            		else p.AddSolution(Arrays.asList(individualAlias, "s"+lval));
            	}
            }
    		
    		p.problemLabel = "Restriction: " + individualAlias + " hasValue"
        			+ "\nISSUE: individual has not been assigned a literal"
        			+ "\nSOLUTION: " + p.solutions.size() + " default value(s)";
    		problems.add(p);
    		
    	}else authorizatedRels.add(Arrays.asList(individualAlias)); //If literal is assigned, authorize it

    	
    }

	@Override public void visit(OWLDataAllValuesFrom exp) {
		
		if(DP_hasValue.equals(exp.getProperty().asOWLDataProperty().getIRI().toString())) //ignore other OBJ Properties
			authorizatedRels.add(Arrays.asList(individualAlias)); //If literal is assigned, authorize it, if not do it just to simplify
	}


	
}
