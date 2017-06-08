package org.rass.restrictions;


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
 * This class analyzes an open-world restriction of a characteristic (Class Expressions)
 * to test if a certain solution yields excess errors
 * 
 * @goal Perform open-world reasoning in a model perspective
 * 
 * If a problem is found, the "problem" variable in the Model Solution won't be null
 * and a solution may be provided. A solution can only add knowledge.
 * 
 * @author Miguel Abreu
 * 
 */
public class RestrictionAnalyzer3 implements OWLClassExpressionVisitor {
	
	private static final String local_log = "Restriction Analyser Log: ";
	
	private boolean hasFailed = false;		//Flag to indicate that an excess problem has occurred
	private PelletReasoner reasoner;		//Reasoner to perform ontology related operations
	
	//=================================================== Ignore certain restrictions
	private static final String OP_demands = Ontologies.OWL_OP_demands;
	
	//=================================================== Expressions which are not supported
	@Override public void visit(OWLClass exp) {System.err.println(local_log + "Internal Error: no class can exist at this point!");}
	@Override public void visit(OWLObjectHasSelf exp) {}
	@Override public void visit(@Nonnull OWLObjectOneOf exp){System.err.println(local_log + "Internal Error -> "+exp);} //this should never happen (its just a list of individuals)
	@Override public void visit(OWLObjectComplementOf exp) {System.out.println(local_log + "Warning: Logical negation is only evaluated if nested inside a primary.");}
	
    //=================================================== Data Property restrictions are currently not being evaluated
    @Override public void visit(@Nonnull OWLDataSomeValuesFrom exp) 	{}
    @Override public void visit(@Nonnull OWLDataHasValue exp) 			{}
    @Override public void visit(@Nonnull OWLDataMinCardinality exp) 	{}
    @Override public void visit(@Nonnull OWLDataExactCardinality exp) 	{}
	@Override public void visit(@Nonnull OWLDataAllValuesFrom exp)		{}
    @Override public void visit(@Nonnull OWLDataMaxCardinality exp)		{} 
    
    //=================================================== Close-world reasoning is not needed for solutions
	@Override public void visit(OWLObjectSomeValuesFrom exp) {}
	@Override public void visit(OWLObjectMinCardinality exp) {}
	@Override public void visit(OWLObjectHasValue exp) 		 {}

    //=================================================== Constructor
	public RestrictionAnalyzer3(PelletReasoner r){ reasoner = r; }
	public boolean HasFailed(){ return hasFailed; }
    
    
    //=============================================================================================
    //====================================================== Primary Functions (Logical Expression)
    //=============================================================================================
    /**
     * Evaluates logical expression: (Restriction AND Restriction AND ...)
     * If one restriction is not met, restriction has failed
     */
	@Override public void visit(OWLObjectIntersectionOf exp) {
		
    	for(OWLClassExpression ce : exp.getOperands()){   		
    		RestrictionAnalyzer3 RA = new RestrictionAnalyzer3(reasoner);
    		ce.accept(RA);
    		if(RA.hasFailed){hasFailed = true; return;}
    	}
	}

	/**
	 * Evaluates logical expression: (Restriction OR Restriction OR ...)
     * If one restriction is met, there is no problem
	 */
	@Override public void visit(OWLObjectUnionOf exp) {

    	for(OWLClassExpression ce : exp.getOperands()){

    		RestrictionAnalyzer3 RA = new RestrictionAnalyzer3(reasoner);
    		ce.accept(RA);	
    		if(!RA.hasFailed) return;
    	}
    	hasFailed = true;
	}

	//=============================================================================================
    //====================================================== Primary Functions (Basic Restrictions)
    //=============================================================================================
	
    @Override public void visit(@Nonnull OWLObjectMaxCardinality exp) {

    	if(!OP_demands.equals(exp.getProperty().getNamedProperty().getIRI().toString())) return; //ignore other OBJ Properties

    	//Check no. of instances of the restricted component
        if(reasoner.getInstances(exp.getFiller(), false).getFlattened().size() > exp.getCardinality()) hasFailed = true;        
    
    }

	@Override public void visit(OWLObjectExactCardinality exp) {

    	if(!OP_demands.equals(exp.getProperty().getNamedProperty().getIRI().toString())) return; //ignore other OBJ Properties

    	//Check no. of instances of the restricted component
        if(reasoner.getInstances(exp.getFiller(), false).getFlattened().size() > exp.getCardinality()) hasFailed = true;        
    
	}

    @Override public void visit(@Nonnull OWLObjectAllValuesFrom exp) {
    	
    	OWLClassExpression target = exp.getFiller(); //target Class Expression        
    	
    	if(!OP_demands.equals(exp.getProperty().getNamedProperty().getIRI().toString())) return; //ignore other OBJ Properties
    	int targetIs = reasoner.getInstances(target, false).getFlattened().size(); //get instances of the restricted CE
    	int realIs = reasoner.getInstances(Ontologies.OWLC_Component, false).getFlattened().size(); //get instances of Component
    	
    	if(targetIs != realIs) hasFailed = true;        

    }
    

	
	
}
