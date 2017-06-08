package org.rass.restrictions;

import java.util.ArrayList;
import java.util.Set;

import javax.annotation.Nonnull;

import org.rass.ontologies.Ontologies;
import org.semanticweb.owlapi.model.ClassExpressionType;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLClassExpressionVisitor;
import org.semanticweb.owlapi.model.OWLDataAllValuesFrom;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataHasValue;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectAllValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectComplementOf;
import org.semanticweb.owlapi.model.OWLObjectExactCardinality;
import org.semanticweb.owlapi.model.OWLObjectHasSelf;
import org.semanticweb.owlapi.model.OWLObjectHasValue;
import org.semanticweb.owlapi.model.OWLObjectIntersectionOf;
import org.semanticweb.owlapi.model.OWLObjectMaxCardinality;
import org.semanticweb.owlapi.model.OWLObjectMinCardinality;
import org.semanticweb.owlapi.model.OWLObjectOneOf;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;


/**
 * This class analyzes a restriction of a characteristic (Class Expressions)
 * These restrictions come from the individual's classes and super-classes
 * and are expressed through Anonymous Classes 
 * 
 * @goal Perform closed and open-world reasoning in a model perspective
 * 
 * If a problem is found, the "problem" variable in the Model Solution won't be null
 * and a solution may be provided. A solution can only add knowledge.
 * 
 * @author Miguel Abreu
 * 
 */
public class RestrictionAnalyzer2 implements OWLClassExpressionVisitor {
	
	private static final String local_log = "Restriction Analyser 2 Log: ";
	
	private ArrayList<Problem> problems;				//Problems/Solutions for the Validator/Quickfix
	private PelletReasoner reasoner;					//Reasoner to perform ontology related operations
	
	//=================================================== Ignore certain restrictions
	private static final String OP_demands = Ontologies.OWL_OP_demands;
	
	//=================================================== Expressions which are not supported
	@Override public void visit(OWLClass exp) {System.err.println(local_log + "Internal Error: no class can exist at this point!");}
	@Override public void visit(OWLObjectHasSelf exp) {System.err.println(local_log + "ObjectHasSelf is not supported!");}
	@Override public void visit(@Nonnull OWLObjectOneOf exp){System.err.println(local_log + "Internal Error -> "+exp);} //this should never happen (its just a list of individuals)
	@Override public void visit(OWLObjectComplementOf exp) {System.out.println(local_log + "Warning: Logical negation is only evaluated if nested inside a primary.");}
	
    //=================================================== Data Property restrictions are currently not being evaluated
    private static final String dataProp_err = local_log + "Charactertics Data Property restrictions are not supported! -> ";
    @Override public void visit(@Nonnull OWLDataSomeValuesFrom exp) 	{System.err.println(dataProp_err+exp);}
    @Override public void visit(@Nonnull OWLDataHasValue exp) 			{System.err.println(dataProp_err+exp);}
    @Override public void visit(@Nonnull OWLDataMinCardinality exp) 	{System.err.println(dataProp_err+exp);}
    @Override public void visit(@Nonnull OWLDataExactCardinality exp) 	{System.err.println(dataProp_err+exp);}
	@Override public void visit(@Nonnull OWLDataAllValuesFrom exp)		{System.err.println(dataProp_err+exp);}
    @Override public void visit(@Nonnull OWLDataMaxCardinality exp)		{System.err.println(dataProp_err+exp);} 

    //=================================================== Constructor
	public RestrictionAnalyzer2(ArrayList<Problem> ps, PelletReasoner r){
    	problems = ps; reasoner = r;
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
    		RestrictionAnalyzer2 RA = new RestrictionAnalyzer2(problems, reasoner);
    		ce.accept(RA);
    	}
	}

	/**
	 * Evaluates logical expression: (Restriction OR Restriction OR ...)
     * If one restriction is met, there is no problem
     * The real solutions list will only be updated if no restriction is met
	 */
	@Override public void visit(OWLObjectUnionOf exp) {
		
		ArrayList<Problem> problemsTemp = new ArrayList<Problem>();
		int lastState = 0; //Number of problems/solutions of the temporary list

    	for(OWLClassExpression ce : exp.getOperands()){

    		RestrictionAnalyzer2 RA = new RestrictionAnalyzer2(problemsTemp, reasoner);
    		ce.accept(RA);
    		
    		//If one restriction is met, there is no problem to report
    		if(problemsTemp.size() == lastState) return;
    		lastState = problemsTemp.size();
    	}
    	
    	problems.addAll(problemsTemp); //update real solutions list
	}

	//=============================================================================================
    //====================================================== Primary Functions (Basic Restrictions)
    //=============================================================================================
	@Override public void visit(OWLObjectSomeValuesFrom exp) {
		
    	OWLClassExpression target = exp.getFiller(); //target Class Expression        
    	long cardinality;
    	
    	if(!OP_demands.equals(exp.getProperty().getNamedProperty().getIRI().toString())) return; //ignore other OBJ Properties
    	cardinality = reasoner.getInstances(target, false).getFlattened().size(); //get no. of instances of the restricted component
        
        if(cardinality == 0)
        	problems.add(new Problem("Restriction: Model demands some " + target 
       			+ "\nISSUE: There is no component of this type", false));	
		
	}
	
    @Override public void visit(@Nonnull OWLObjectMaxCardinality exp) {

    	OWLClassExpression target = exp.getFiller(); //target Class Expression        
    	long MaxCardinality = exp.getCardinality();
    	long cardinality;
    	
    	if(!OP_demands.equals(exp.getProperty().getNamedProperty().getIRI().toString())) return; //ignore other OBJ Properties
    	cardinality = reasoner.getInstances(target, false).getFlattened().size(); //get no. of instances of the restricted component
        	
        if(cardinality > MaxCardinality)
        	problems.add(new Problem("Restriction: Model demands max " + MaxCardinality + " " + target 
        		+ "\nISSUE: Model contains " + cardinality, true));
        
    }

	
	@Override public void visit(OWLObjectMinCardinality exp) {
		
		OWLClassExpression target = exp.getFiller(); //target Class Expression        
    	long MinCardinality = exp.getCardinality();
    	long cardinality;
    	
    	if(!OP_demands.equals(exp.getProperty().getNamedProperty().getIRI().toString())) return; //ignore other OBJ Properties
    	cardinality = reasoner.getInstances(target, false).getFlattened().size(); //get no. of instances of the restricted component
    	
        if(cardinality < MinCardinality)
        	problems.add(new Problem("Restriction: Model demands min " + MinCardinality + " " + target 
    			+ "\nISSUE: Model contains " + cardinality, false));	
        
	}

	
	@Override public void visit(OWLObjectExactCardinality exp) {
		
		OWLClassExpression target = exp.getFiller(); //target Class Expression        
    	long ExactCardinality = exp.getCardinality();
    	long cardinality;
    	
    	if(!OP_demands.equals(exp.getProperty().getNamedProperty().getIRI().toString())) return; //ignore other OBJ Properties
    	cardinality = reasoner.getInstances(target, false).getFlattened().size(); //get no. of instances of the restricted component
    	
        if(cardinality != ExactCardinality)
        	problems.add(new Problem("Restriction: Model demands exactly " + ExactCardinality + " " + target 
    			+ "\nISSUE: Model contains " + cardinality, cardinality > ExactCardinality));	
	
	}

    @Override public void visit(@Nonnull OWLObjectAllValuesFrom exp) {
    	
    	OWLClassExpression target = exp.getFiller(); //target Class Expression        
    	
    	if(!OP_demands.equals(exp.getProperty().getNamedProperty().getIRI().toString())) return; //ignore other OBJ Properties
    	Set<OWLNamedIndividual> targetIs = reasoner.getInstances(target, false).getFlattened(); //get instances of the restricted CE
    	Set<OWLNamedIndividual> realIs = reasoner.getInstances(Ontologies.OWLC_Component, false).getFlattened(); //get instances of Component
    	
    	if(targetIs.size() != realIs.size()){ //if target is outside Component, this might not work, but that's user's fault
    		realIs.removeAll(targetIs);
    		
    		problems.add(new Problem("Restriction: Model demands all " + target 
    			+ "\nISSUE: Model contains: " + realIs, true));	
    	}	
    	
    }
    
    // =/= static individual because it isn't a model entry point
	@Override public void visit(OWLObjectHasValue exp) {
		
		OWLNamedIndividual target = exp.getFiller().asOWLNamedIndividual(); //target individual        
    	
    	if(!OP_demands.equals(exp.getProperty().getNamedProperty().getIRI().toString())) return; //ignore other OBJ Properties
    	
        if(!reasoner.getInstances(Ontologies.OWLC_Component, false).containsEntity(target))
        	problems.add(new Problem("Restriction: Model demands " + target
    			+ "\nISSUE: There is no such individual", false));	
	}
	
}
