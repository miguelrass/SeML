package org.rass.ontologies;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.reasoner.Node;
import org.xtext.seml.seML.Individual;

import com.clarkparsia.pellet.owlapi.PelletReasoner;

import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ClassSolver implements OWLClassExpressionVisitor {
	
	/**
	 * OWLObjectIntersectionOf contains only Classes as operands
	 */
	private List<OWLObjectIntersectionOf> AcceptedClasses;

	private static final OWLDataFactory factory = OWLManager.getOWLDataFactory();
	
	/**
	 * After the creation of this object, only one visit is allowed. 
	 */
    public ClassSolver() {}
    public List<OWLObjectIntersectionOf> getAccepted(){return AcceptedClasses;} //If empty, returns null (error)

    @Override
    public void visit(@Nonnull OWLClass owlClass) {
    	//System.out.println("__Classe " + owlClass);
    	AcceptedClasses = new ArrayList<OWLObjectIntersectionOf>(1);
    	AcceptedClasses.add(factory.getOWLObjectIntersectionOf(owlClass));
    }
    
    /**
     * ComplementOf (not)
     * Use cases:
     * - not supported -
     */
    @Override
    public void visit(@Nonnull OWLObjectComplementOf owlObjectComplementOf) {
    	System.out.println("__Complemento: Not supported " + owlObjectComplementOf);
    }

    /**
     * owlObjectIntersectionOf (and)
     * Use cases:
     * Can only intersect Classes or Intersections, i.e., lists of 1 intersection
     * Intersections will be converted into classes before being processed 
     */
    @Override
    public void visit(@Nonnull OWLObjectIntersectionOf owlObjectIntersectionOf) {  
    	//System.out.println("__Intersecao " + owlObjectIntersectionOf);
    	AcceptedClasses = new ArrayList<OWLObjectIntersectionOf>(1);
    	
    	List<OWLClassExpression> classlist = new ArrayList<OWLClassExpression>(); //List of classes to be intersected

    	//Evaluate each AND OPERAND with the visitor pattern
    	Iterator<OWLClassExpression> it = owlObjectIntersectionOf.operands().iterator();
    	while(it.hasNext()){
    		OWLClassExpression o = it.next();
    		ClassSolver cs = new ClassSolver();
    		o.accept(cs); 
    		List<OWLObjectIntersectionOf> andOperandIntersections = cs.getAccepted();
    		if(andOperandIntersections==null || andOperandIntersections.size() != 1) {
    			System.out.println("AND abortado "); AcceptedClasses = null; return;} //Abort if visit has failed
    		andOperandIntersections.get(0).operands().forEach(cls -> classlist.add(cls)); //Decompose intersection and store each Class
    	}
    	AcceptedClasses.add(factory.getOWLObjectIntersectionOf(classlist)); //add only one intersection with all classes
    }
    
    /**
     * owlObjectUnionOf (or)
     * Use cases:
     * Any kind of union is accepted
     */
    @Override
    public void visit(@Nonnull OWLObjectUnionOf owlObjectUnionOf) {
    	//System.out.println("__Uniao " + owlObjectUnionOf);
    	AcceptedClasses = new ArrayList<OWLObjectIntersectionOf>();
    	
    	//Evaluate each OR OPERAND with the visitor pattern
    	Iterator<OWLClassExpression> it = owlObjectUnionOf.operands().iterator();
    	while(it.hasNext()){
    		OWLClassExpression o = it.next();
    		ClassSolver cs = new ClassSolver();
    		o.accept(cs); 
    		List<OWLObjectIntersectionOf> list = cs.getAccepted();
    		if(list==null) {AcceptedClasses = null; return;} //Abort if visit has failed
    		AcceptedClasses.addAll(list); 
    	}
    }


    

    //======================================================================== Not supported Objects
    
    @Override public void visit(@Nonnull OWLObjectMinCardinality owlObjectMinCardinality) {System.out.println("Unexpected Object type in Class Solver: " + owlObjectMinCardinality);}
    @Override public void visit(@Nonnull OWLDataMinCardinality owlDataMinCardinality) {System.out.println("Unexpected Object type in Class Solver: " + owlDataMinCardinality);}
    @Override public void visit(@Nonnull OWLObjectMaxCardinality owlObjectMaxCardinality) {System.out.println("Unexpected Object type in Class Solver: " + owlObjectMaxCardinality);}
    @Override public void visit(@Nonnull OWLDataMaxCardinality owlDataMaxCardinality) {System.out.println("Unexpected Object type in Class Solver: " + owlDataMaxCardinality);}
    @Override public void visit(@Nonnull OWLObjectExactCardinality owlObjectExactCardinality) {System.out.println("Unexpected Object type in Class Solver: " + owlObjectExactCardinality);}
    @Override public void visit(@Nonnull OWLDataExactCardinality owlDataExactCardinality) {System.out.println("Unexpected Object type in Class Solver: " + owlDataExactCardinality);}   
    @Override public void visit(@Nonnull OWLObjectSomeValuesFrom owlObjectSomeValuesFrom) {System.out.println("Unexpected Object type in Class Solver: " + owlObjectSomeValuesFrom);}
    @Override public void visit(@Nonnull OWLDataSomeValuesFrom owlDataSomeValuesFrom) {System.out.println("Unexpected Object type in Class Solver: " + owlDataSomeValuesFrom);}
    @Override public void visit(@Nonnull OWLObjectAllValuesFrom owlObjectAllValuesFrom) {System.out.println("Unexpected Object type in Class Solver: " + owlObjectAllValuesFrom);}
    @Override public void visit(@Nonnull OWLDataAllValuesFrom owlDataAllValuesFrom) {System.out.println("Unexpected Object type in Class Solver: " + owlDataAllValuesFrom);}
   
    @Override public void visit(@Nonnull OWLObjectHasValue owlObjectHasValue) {System.out.println("Unexpected Object type in Class Solver: " + owlObjectHasValue);}
    @Override public void visit(@Nonnull OWLDataHasValue owlDataHasValue) {System.out.println("Unexpected Object type in Class Solver: " + owlDataHasValue);}
    
    @Override public void visit(@Nonnull OWLObjectHasSelf owlObjectHasSelf) {System.out.println("Unexpected Object type in Class Solver: " + owlObjectHasSelf);}
    @Override public void visit(@Nonnull OWLObjectOneOf owlObjectOneOf) {System.out.println("Unexpected Object type in Class Solver: " + owlObjectOneOf);} 
    

}
