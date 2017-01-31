package org.rass.ontologies;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.*;
import org.xtext.seml.seML.Individual;

import com.clarkparsia.pellet.owlapi.PelletReasoner;

import javax.annotation.Nonnull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RestrictionOverseer implements OWLClassExpressionVisitor {
	
	private OWLNamedIndividual individual;
	private PelletReasoner reasoner;
	private String report;
	private String solution;
	private HashMap<String, String> cachedComponentIRIs;
	private static final String OWL_OP_demands = Ontologies.OWL_Upper + "demands";
	private static final String OWL_OP_hasValue = Ontologies.OWL_Upper + "hasValue";
	private static final String OWL_OP_characterizes = Ontologies.OWL_Upper + "characterizes";
	private static final String OWL_OP_isCharacterizedBy = Ontologies.OWL_Upper + "isCharacterizedBy";
	private static final List<String> IgnoreOPs = Arrays.asList(OWL_OP_hasValue, OWL_OP_characterizes, OWL_OP_isCharacterizedBy);
	
	
	public enum RestrType {
	    MIN, EXACT, SOME
	}

    public RestrictionOverseer(OWLNamedIndividual i, PelletReasoner r, HashMap<String, String> c) {
    	individual = i;
    	reasoner = r;
    	cachedComponentIRIs = c;
    	report = "";
    	solution = "";
    }

    public String getReport() {
    	if(report.isEmpty()) return null;
        return report;
    }
    
    public String getSolution() {
    	if(solution.isEmpty()) return null;
        return solution;
    }
    
    public void resetReport(){
    	report = "";
    	solution = "";
    }

    @Override
    public void visit(@Nonnull OWLClass owlClass) {
    	System.out.print("CLASS WAS RECEIVED!\n");
        //property = null;
        //filler = null;
    }



    @Override
    public void visit(@Nonnull OWLObjectHasValue owlObjectHasValue) {
//        property = owlObjectHasValue.getProperty().getNamedProperty();
//        filler = owlObjectHasValue.getFiller();
    }

    
    private int getRelationCardinality(OWLNamedIndividual i, OWLObjectPropertyExpression p, OWLClassExpression e){ 	
    	
    	Stream<OWLNamedIndividual> relatives = reasoner.getObjectPropertyValues(i, p).entities(); //Individuals that are related to "i" via "p"
    	List<OWLNamedIndividual> targets = reasoner.getInstances(e, false).entities().collect(Collectors.toList()); //(get indirect) Individuals of the provided Class Expression
    	
    	//Check if every relative is a target of the current restriction
    	int cardinality = 0;
    	Iterator<OWLNamedIndividual> it = relatives.iterator();
    	while(it.hasNext()){
    		OWLNamedIndividual rel = it.next();
    		if(targets.contains(rel)) cardinality++;
    	}
    	
    	return cardinality;	
    }
    
    
    
    //======================================================================== Check if restriction is being met
    //----------------------------------- Count number of relations using the restricted object property
    // reasoner.getObjectPropertyValues(individual, property).entities().count();
    //-----------------------------------
	// This reasoner method successfully deals with the following issues:
	//- Equivalent object Properties (and duplicate relations)
	//- Inverse object Properties
	//- Mix of the preceding characteristics
    //-----------------------------------
    
    //======================================================================== Restrictions which close the World
    
    @Override
    public void visit(@Nonnull OWLObjectMinCardinality owlObjectMinCardinality) {
    	OWLObjectPropertyExpression property = owlObjectMinCardinality.getProperty(); //object property
    	String propertyIRI = property.getNamedProperty().getIRI().toString(); //object property IRI
    	OWLClassExpression target = owlObjectMinCardinality.getFiller(); //target Class Expression  
    	long MinCardinality = owlObjectMinCardinality.getCardinality();
    	long cardinality;
    	
    	//-------------------------------------------------------------------------- Process "characteristic" related tasks
    	if(IgnoreOPs.contains(propertyIRI)) return; //ignore these ObjProperties
    	
    	if(propertyIRI.equals(OWL_OP_demands)) //if ObjProp is "demands" cardinality is not calculated based on no. of relations
    		cardinality = reasoner.getInstances(target, false).entities().count(); //get no. of instances of the restricted component
    	else
    	//-----------------------------------------------------------------------------------------------------------------
    		//cardinality = reasoner.getObjectPropertyValues(individual, property).entities().count(); //no. of relations
    		cardinality = getRelationCardinality(individual, property, target);
    	
        if(cardinality < MinCardinality){
        	report += "Restriction: " + property.getNamedProperty() + " min " + MinCardinality + " " + target
        			+ "\nIssue: " + cardinality + " < " + MinCardinality + "\n";
        	//---------------------------------------------------------------------- Compute solution
        	//check if there is any individual of the target class expression
        	if(reasoner.getInstances(target, false).isEmpty()){
        		Random randomGenerator = new Random();
        		solution += "new " + cachedComponentIRIs.get(target.getClassesInSignature().iterator().next().getIRI().toString()) 
        				 + " ind" + String.valueOf(randomGenerator.nextInt(1000)) + "\n";
        		//fazer mais uma linha com a devida relacao em falta
        		//ver se era pa fazer apenas 1 individuo.. (nao era lol)
        	}
        }
    }

    @Override
    public void visit(@Nonnull OWLObjectExactCardinality owlObjectExactCardinality) {
    	OWLObjectPropertyExpression property = owlObjectExactCardinality.getProperty(); //object property
    	String propertyIRI = property.getNamedProperty().getIRI().toString(); //object property IRI
    	OWLClassExpression target = owlObjectExactCardinality.getFiller(); //target Class Expression  
    	long ExactCardinality = owlObjectExactCardinality.getCardinality();
    	long cardinality;
    	
    	//-------------------------------------------------------------------------- Process "characteristic" related tasks
    	if(IgnoreOPs.contains(propertyIRI)) return; //ignore these ObjProperties
    	
    	if(propertyIRI.equals(OWL_OP_demands)) //if ObjProp is "demands" cardinality is not calculated based on no. of relations
    		cardinality = reasoner.getInstances(target, false).entities().count(); //get no. of instances of the restricted component
    	else
    	//-----------------------------------------------------------------------------------------------------------------
    		cardinality = getRelationCardinality(individual, property, target);
    	
        if(cardinality != ExactCardinality){ //if cardinality > restriction the ontology is inconsistent anyway
        	report += "Restriction: " + property.getNamedProperty() + " exactly " + ExactCardinality + " " + target
        			+ "\nIssue: " + cardinality + " =/= " + ExactCardinality + "\n";
        }
    }

    @Override
    public void visit(@Nonnull OWLObjectSomeValuesFrom owlObjectSomeValuesFrom) {
    	OWLObjectPropertyExpression property = owlObjectSomeValuesFrom.getProperty(); //object property
    	String propertyIRI = property.getNamedProperty().getIRI().toString(); //object property IRI
    	OWLClassExpression target = owlObjectSomeValuesFrom.getFiller(); //target Class Expression        
    	long cardinality;
    	
    	//-------------------------------------------------------------------------- Process "characteristic" related tasks
    	if(IgnoreOPs.contains(propertyIRI)) return; //ignore these ObjProperties
    	
    	if(propertyIRI.equals(OWL_OP_demands)) //if ObjProp is "demands" cardinality is not calculated based on no. of relations
    		cardinality = reasoner.getInstances(target, false).entities().count(); //get no. of instances of the restricted component
    	else
    	//-----------------------------------------------------------------------------------------------------------------
    		//cardinality = reasoner.getObjectPropertyValues(individual, property).entities().count(); //no. of relations
    		cardinality = getRelationCardinality(individual, property, target);
        
    	//isto obtem relacoes X do individuo Y, mas precisa de obter relacoes X do individuo Y ao individuo Z
    	
        if(cardinality == 0){
        	report += "Restriction: " + property.getNamedProperty() + " some " + target + "\nIssue: There is no relation of this type\n";
        }
    }
    
    
    //======================================================================== Dispatch Complex Restrictions
    
    @Override
    public void visit(@Nonnull OWLObjectUnionOf owlObjectUnionOf) {
    	
    	String temp_report = "Composed Restriction. At least one restriction has to be met:\n";
    	
    	//Get all relations and create a RestrictionOverseer object
    	RestrictionOverseer RO = new RestrictionOverseer(individual, reasoner, cachedComponentIRIs);
    			
    	//Evaluate each restriction with the visitor pattern
    	Iterator<OWLClassExpression> it = owlObjectUnionOf.operands().iterator();
    	while(it.hasNext()){
    		OWLClassExpression ce = it.next();
    		ce.accept(RO);
    		if(RO.getReport() == null) return; //If one restriction is met, there is no problem to report
    		temp_report += RO.getReport();
    		RO.resetReport();
    		if(it.hasNext()) temp_report += "OR\n"; //Connect each restriction with an OR
    	}
    	
    	//Report error if no restriction was met
    	report += temp_report;
    }
    
    @Override
    public void visit(@Nonnull OWLObjectIntersectionOf owlObjectIntersectionOf) {   	
    	//Get all relations and create a RestrictionOverseer object
    	RestrictionOverseer RO = new RestrictionOverseer(individual, reasoner, cachedComponentIRIs);
    			
    	//Evaluate each restriction with the visitor pattern
    	owlObjectIntersectionOf.operands().forEach(r -> r.accept(RO));
    	
    	//Report error if no restriction was met
    	if(RO.getReport() != null) report += RO.getReport();
    }
    
    @Override
    public void visit(@Nonnull OWLObjectComplementOf owlObjectComplementOf) {
    	
    	//Get all relations and create a RestrictionOverseer object
    	RestrictionOverseer RO = new RestrictionOverseer(individual, reasoner, cachedComponentIRIs);
    			
    	//Evaluate each restriction with the visitor pattern
    	owlObjectComplementOf.getOperand().accept(RO);

    	//Report error if no error was reported
    	if(RO.getReport() == null) report += "Restriction which should not be met: " + owlObjectComplementOf.getOperand().toString();
    	
    }
    
    //======================================================================== Restrictions which are not an Open World problem, except for characteristics (which have no instances)
    
    @Override public void visit(@Nonnull OWLObjectMaxCardinality owlObjectMaxCardinality) {
    	OWLObjectPropertyExpression property = owlObjectMaxCardinality.getProperty(); //object property
    	String propertyIRI = property.getNamedProperty().getIRI().toString(); //object property IRI
    	OWLClassExpression second = owlObjectMaxCardinality.getFiller(); //second individual    
    	long MaxCardinality = owlObjectMaxCardinality.getCardinality();
    	long cardinality;
    	
    	//-------------------------------------------------------------------------- Process "characteristic" related tasks
    	if(IgnoreOPs.contains(propertyIRI) || (!propertyIRI.equals(OWL_OP_demands))) return; //ignore these ObjProperties
    	cardinality = reasoner.getInstances(second, false).entities().count(); //get no. of instances of the restricted component
    	
        if(cardinality > MaxCardinality){
        	report += "Restriction: " + property.getNamedProperty() + " max " + MaxCardinality + " " + second
        			+ "\nIssue: " + cardinality + " > " + MaxCardinality + "\n";
        }
    }
    
    //======================================================================== Restrictions which are not an Open World problem
    
    @Override public void visit(@Nonnull OWLDataAllValuesFrom owlDataAllValuesFrom) {}
    @Override public void visit(@Nonnull OWLDataMaxCardinality owlDataMaxCardinality) {}
    
    
    @Override public void visit(@Nonnull OWLObjectAllValuesFrom owlObjectAllValuesFrom) {}
//    	OWLObjectPropertyExpression property = owlObjectAllValuesFrom.getProperty(); //object property
//    	OWLClassExpression second = owlObjectAllValuesFrom.getFiller(); //second individual        
//    	List<OWLNamedIndividual> allowedindividuals = reasoner.getInstances(second, false).entities().collect(Collectors.toList());//all direct/indirect individuals of the required class
//    	List<OWLNamedIndividual> currentindividuals = reasoner.getObjectPropertyValues(individual, property).entities().collect(Collectors.toList()); //all individuals connected by that property
//    	
//    	currentindividuals.removeAll(allowedindividuals);//currentindividuals now holds forbidden individuals
//    	if(!currentindividuals.isEmpty()){
//    		report += "Restriction: " + property.getNamedProperty() + " allvaluesfrom " + second + "\nIssue: Some individuals are not instances of the restricted class\n";
//    		currentindividuals.forEach(i -> report += i.getIRI().toString() + "\n");
//    	}
//    }
    

    
  //======================================================================== Not yet analyzed 

    @Override
    public void visit(@Nonnull OWLObjectHasSelf owlObjectHasSelf) {
//        property = owlObjectHasSelf.getProperty().getNamedProperty();
        // TODO
    }

    @Override
    public void visit(@Nonnull OWLObjectOneOf owlObjectOneOf) {
//        property = null;
//        filler = null;
    }

    @Override
    public void visit(@Nonnull OWLDataSomeValuesFrom owlDataSomeValuesFrom) {
//        property = owlDataSomeValuesFrom.getProperty().asOWLDataProperty();
//        filler = owlDataSomeValuesFrom.getFiller();
    }

    @Override
    public void visit(@Nonnull OWLDataHasValue owlDataHasValue) {
//        property = owlDataHasValue.getProperty().asOWLDataProperty();
//        filler = owlDataHasValue.getFiller();
    }

    @Override
    public void visit(@Nonnull OWLDataMinCardinality owlDataMinCardinality) {
//        property = owlDataMinCardinality.getProperty().asOWLDataProperty();
//        filler = owlDataMinCardinality.getFiller();
    }

    @Override
    public void visit(@Nonnull OWLDataExactCardinality owlDataExactCardinality) {
//        property = owlDataExactCardinality.getProperty().asOWLDataProperty();
//        filler = owlDataExactCardinality.getFiller();
    }


}
