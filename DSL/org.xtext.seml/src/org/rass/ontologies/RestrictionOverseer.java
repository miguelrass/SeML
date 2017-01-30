package org.rass.ontologies;

import org.semanticweb.owlapi.model.*;
import org.xtext.seml.seML.Individual;

import com.clarkparsia.pellet.owlapi.PelletReasoner;

import javax.annotation.Nonnull;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class RestrictionOverseer implements OWLClassExpressionVisitor {
	
	private OWLNamedIndividual individual;
	private PelletReasoner reasoner;
	private String report;
	private static final String OWL_OP_demands = Ontologies.OWL_Upper + "demands";
	private static final String OWL_OP_hasValue = Ontologies.OWL_Upper + "hasValue";
	private static final String OWL_OP_characterizes = Ontologies.OWL_Upper + "characterizes";
	private static final String OWL_OP_isCharacterizedBy = Ontologies.OWL_Upper + "isCharacterizedBy";
	List<String> IgnoreOPs = Arrays.asList(OWL_OP_hasValue, OWL_OP_characterizes, OWL_OP_isCharacterizedBy);
	
	public enum RestrType {
	    MIN, EXACT, SOME
	}

    public RestrictionOverseer(OWLNamedIndividual i, PelletReasoner r) {
    	individual = i;
    	reasoner = r;
    	report = "";
    }

    public String getReport() {
    	if(report.isEmpty()) return null;
        return report;
    }
    


    @Override
    public void visit(@Nonnull OWLClass owlClass) {
        //property = null;
        //filler = null;
    }

    @Override
    public void visit(@Nonnull OWLObjectIntersectionOf owlObjectIntersectionOf) {
//        property = null;
//        filler = null;
    }

    @Override
    public void visit(@Nonnull OWLObjectUnionOf owlObjectUnionOf) {
//        property = null;
//        filler = null;
    }

    @Override
    public void visit(@Nonnull OWLObjectComplementOf owlObjectComplementOf) {
//        property = null;
//        filler = null;
    }




    @Override
    public void visit(@Nonnull OWLObjectHasValue owlObjectHasValue) {
//        property = owlObjectHasValue.getProperty().getNamedProperty();
//        filler = owlObjectHasValue.getFiller();
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
    	OWLClassExpression second = owlObjectMinCardinality.getFiller(); //second individual   
    	long MinCardinality = owlObjectMinCardinality.getCardinality();
    	long cardinality;
    	
    	//-------------------------------------------------------------------------- Process "characteristic" related tasks
    	if(IgnoreOPs.contains(propertyIRI)) return; //ignore these ObjProperties
    	
    	if(propertyIRI.equals(OWL_OP_demands)) //if ObjProp is "demands" cardinality is not calculated based on no. of relations
    		cardinality = reasoner.getInstances(second, false).entities().count(); //get no. of instances of the restricted component
    	else
    	//-----------------------------------------------------------------------------------------------------------------
    		cardinality = reasoner.getObjectPropertyValues(individual, property).entities().count(); //no. of relations
    	
        if(cardinality < MinCardinality){
        	report += "Restriction: " + property.getNamedProperty() + " min " + MinCardinality + " " + second
        			+ "\nIssue: " + cardinality + " < " + MinCardinality + "\n";
        }
    }

    @Override
    public void visit(@Nonnull OWLObjectExactCardinality owlObjectExactCardinality) {
    	OWLObjectPropertyExpression property = owlObjectExactCardinality.getProperty(); //object property
    	String propertyIRI = property.getNamedProperty().getIRI().toString(); //object property IRI
    	OWLClassExpression second = owlObjectExactCardinality.getFiller(); //second individual
    	long ExactCardinality = owlObjectExactCardinality.getCardinality();
    	long cardinality;
    	
    	//-------------------------------------------------------------------------- Process "characteristic" related tasks
    	if(IgnoreOPs.contains(propertyIRI)) return; //ignore these ObjProperties
    	
    	if(propertyIRI.equals(OWL_OP_demands)) //if ObjProp is "demands" cardinality is not calculated based on no. of relations
    		cardinality = reasoner.getInstances(second, false).entities().count(); //get no. of instances of the restricted component
    	else
    	//-----------------------------------------------------------------------------------------------------------------
    		cardinality = reasoner.getObjectPropertyValues(individual, property).entities().count(); //no. of relations
    	
        if(cardinality != ExactCardinality){ //if cardinality > restriction the ontology is inconsistent anyway
        	report += "Restriction: " + property.getNamedProperty() + " exactly " + ExactCardinality + " " + second
        			+ "\nIssue: " + cardinality + " =/= " + ExactCardinality + "\n";
        }
    }

    @Override
    public void visit(@Nonnull OWLObjectSomeValuesFrom owlObjectSomeValuesFrom) {
    	OWLObjectPropertyExpression property = owlObjectSomeValuesFrom.getProperty(); //object property
    	String propertyIRI = property.getNamedProperty().getIRI().toString(); //object property IRI
    	OWLClassExpression second = owlObjectSomeValuesFrom.getFiller(); //second individual        
    	long cardinality;
    	
    	//-------------------------------------------------------------------------- Process "characteristic" related tasks
    	if(IgnoreOPs.contains(propertyIRI)) return; //ignore these ObjProperties
    	
    	if(propertyIRI.equals(OWL_OP_demands)) //if ObjProp is "demands" cardinality is not calculated based on no. of relations
    		cardinality = reasoner.getInstances(second, false).entities().count(); //get no. of instances of the restricted component
    	else
    	//-----------------------------------------------------------------------------------------------------------------
    		cardinality = reasoner.getObjectPropertyValues(individual, property).entities().count(); //no. of relations
        
        if(cardinality == 0){
        	report += "Restriction: " + property.getNamedProperty() + " some " + second + "\nIssue: There is no relation of this type\n";
        }
    }
    
    //======================================================================== Restrictions which are not an Open World problem, except for characteristics
    
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
