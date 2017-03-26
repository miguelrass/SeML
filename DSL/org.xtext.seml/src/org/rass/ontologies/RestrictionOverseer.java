package org.rass.ontologies;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLClassExpressionVisitor;
import org.semanticweb.owlapi.model.OWLDataAllValuesFrom;
import org.semanticweb.owlapi.model.OWLDataExactCardinality;
import org.semanticweb.owlapi.model.OWLDataHasValue;
import org.semanticweb.owlapi.model.OWLDataMaxCardinality;
import org.semanticweb.owlapi.model.OWLDataMinCardinality;
import org.semanticweb.owlapi.model.OWLDataSomeValuesFrom;
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
import org.semanticweb.owlapi.model.OWLObjectPropertyExpression;
import org.semanticweb.owlapi.model.OWLObjectSomeValuesFrom;
import org.semanticweb.owlapi.model.OWLObjectUnionOf;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;


public class RestrictionOverseer implements OWLClassExpressionVisitor {
	
	private static final String local_log = "RestrictionOverseer Log: ";
	private OWLNamedIndividual individual;
	private PelletReasoner reasoner;
	private String report;
	private String solution;
	private boolean isCharacteristic;
	private HashMap<String, String> cachedIRIs; //Key: Full IRI
	private HashMap<String, String> cachedIRIsInverse; //Key: Short IRI
	//private static final String OWL_OP_demands = Ontologies.OWL_Upper + "demands";
	private static final String OWL_OP_hasValue = Ontologies.OWL_Upper + "hasValue";
	private static final String OWL_OP_requires = Ontologies.OWL_Upper + "requires";
	private static final String OWL_OP_characterizes = Ontologies.OWL_Upper + "characterizes";
	private static final String OWL_OP_isCharacterizedBy = Ontologies.OWL_Upper + "isCharacterizedBy";
	private static final List<String> IgnoreOPs = Arrays.asList(OWL_OP_hasValue, OWL_OP_requires, OWL_OP_characterizes, OWL_OP_isCharacterizedBy);
	
	
	public enum RestrType {
	    MIN, EXACT, SOME
	}

    public RestrictionOverseer(OWLNamedIndividual i, PelletReasoner r, HashMap<String, String> ci, HashMap<String, String> cii) {
    	individual = i;
    	reasoner = r;
    	cachedIRIs = ci;
    	cachedIRIsInverse = cii;
    	report = "";
    	solution = "";
    	isCharacteristic = (individual==null);
    }

    public String[] getFinalReport() {
    	String FinalReport[] = new String[2];
    	if(report.isEmpty()) return null;
    	FinalReport[0] = report;
    	FinalReport[1] = solution;
        return FinalReport;
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
    	
    	Set<OWLNamedIndividual> relatives = reasoner.getObjectPropertyValues(i, p).getFlattened(); //Individuals that are related to "i" via "p"
    	Set<OWLNamedIndividual> targets;
    	
    	try {
			targets = reasoner.getInstances(e, false).getFlattened(); //(get indirect) Individuals of the provided Class Expression
		} catch (Exception ex) {
			return 0; // The getInstances() procedure fails when no individuals are found (only when using complex Class Expressions)
		}
    	
    	//Check if every relative is a target of the current restriction
    	int cardinality = 0;
    	for(OWLNamedIndividual r : relatives){ if(targets.contains(r)) cardinality++; }

    	return cardinality;	
    }
    
    private boolean areRelated(OWLNamedIndividual i, OWLNamedIndividual i2, OWLObjectPropertyExpression p){ 	
    	
    	Set<OWLNamedIndividual> relatives = reasoner.getObjectPropertyValues(i, p).getFlattened(); //Individuals that are related to "i" via "p"
    	for(OWLNamedIndividual r : relatives){
    		if (r.equals(i2)) return true;
    	}
    	return false;
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
    	
    	if(isCharacteristic) //if ObjProp is "demands" cardinality is not calculated based on no. of relations
    		cardinality = reasoner.getInstances(target, false).getFlattened().size(); //get no. of instances of the restricted component
    	else
    	//-----------------------------------------------------------------------------------------------------------------
    		//cardinality = reasoner.getObjectPropertyValues(individual, property).entities().count(); //no. of relations
    		cardinality = getRelationCardinality(individual, property, target);
    	
        if(cardinality < MinCardinality){
        	report += "Restriction: " + property.getNamedProperty() + " min " + MinCardinality + " " + target
        			+ "    ISSUE: " + cardinality + " < " + MinCardinality + "\n";
        	
        	computeSolution(property, target, cardinality, MinCardinality);
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
    	
    	if(isCharacteristic) //if ObjProp is "demands" cardinality is not calculated based on no. of relations
    		cardinality = reasoner.getInstances(target, false).getFlattened().size(); //get no. of instances of the restricted component
    	else
    	//-----------------------------------------------------------------------------------------------------------------
    		cardinality = getRelationCardinality(individual, property, target);
    	
        if(cardinality != ExactCardinality){ //if cardinality > restriction the ontology is inconsistent anyway
        	report += "Restriction: " + property.getNamedProperty() + " exactly " + ExactCardinality + " " + target
        			+ "    ISSUE: " + cardinality + " =/= " + ExactCardinality + "\n";
        	if(cardinality < ExactCardinality) computeSolution(property, target, cardinality, ExactCardinality);
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
    	
    	if(isCharacteristic) //if ObjProp is "demands" cardinality is not calculated based on no. of relations
    		cardinality = reasoner.getInstances(target, false).getFlattened().size(); //get no. of instances of the restricted component
    	else
    	//-----------------------------------------------------------------------------------------------------------------
    		//cardinality = reasoner.getObjectPropertyValues(individual, property).entities().count(); //no. of relations
    		cardinality = getRelationCardinality(individual, property, target);
        
    	//isto obtem relacoes X do individuo Y, mas precisa de obter relacoes X do individuo Y ao individuo Z
    	
        if(cardinality == 0){
        	report += "Restriction: " + property.getNamedProperty() + " some " + target + "    ISSUE: There is no relation of this type\n";
        }
    }
    

    @Override
    public void visit(@Nonnull OWLObjectHasSelf owlObjectHasSelf) { 
    	
    	OWLObjectPropertyExpression property = owlObjectHasSelf.getProperty(); //object property
    	String individualShortIRI = cachedIRIs.get(individual.getIRI().toString());
    	
    	if(reasoner.getObjectPropertyValues(individual, property).getFlattened()//Individuals that are related to "individual" via "property"
    			.contains(individual)) return; //Check if there is any self relation

    	report += "Restriction: " + property.getNamedProperty() + " hasSelf"
    			+ "    ISSUE: relation not found\n";
    	
    	solution += individualShortIRI + " " + cachedIRIs.get(property.getNamedProperty().getIRI().toString()) + " " + individualShortIRI + "\n";
    
    }
    
    
    //======================================================================== Dispatch Complex Restrictions
    
    @Override
    public void visit(@Nonnull OWLObjectUnionOf owlObjectUnionOf) {
    	
    	StringBuilder report_sb = new StringBuilder(1000); //set initial capacity to 1000 chars
    	report_sb.append("Composed Restriction. At least one restriction has to be met:\n");
    	
    	//Get all relations and create a RestrictionOverseer object
    	RestrictionOverseer RO = new RestrictionOverseer(individual, reasoner, cachedIRIs, cachedIRIsInverse);
    			
    	//Evaluate each restriction with the visitor pattern
    	for(OWLClassExpression ce : owlObjectUnionOf.getOperands()){
    		ce.accept(RO);
    		if(RO.getFinalReport() == null) return; //If one restriction is met, there is no problem to report
    		report_sb.append(RO.getFinalReport() + "OR\n"); //Connect each restriction with an OR
    		RO.resetReport();
    	}
    	report_sb.setLength(report_sb.length()-3);
    	
    	//Report error if no restriction was met
    	report += report_sb.toString();
    }
    
    @Override
    public void visit(@Nonnull OWLObjectIntersectionOf owlObjectIntersectionOf) {   	
    	//Get all relations and create a RestrictionOverseer object
    	RestrictionOverseer RO = new RestrictionOverseer(individual, reasoner, cachedIRIs, cachedIRIsInverse);
    			
    	//Evaluate each restriction with the visitor pattern
    	owlObjectIntersectionOf.getOperands().forEach(r -> r.accept(RO));
    	
    	//Report error if no restriction was met
    	if(RO.getFinalReport() != null) {report += RO.getFinalReport()[0]; solution += RO.getFinalReport()[1];}
    }
    
    @Override
    public void visit(@Nonnull OWLObjectComplementOf owlObjectComplementOf) {
    	
    	//Get all relations and create a RestrictionOverseer object
    	RestrictionOverseer RO = new RestrictionOverseer(individual, reasoner, cachedIRIs, cachedIRIsInverse);
    			
    	//Evaluate each restriction with the visitor pattern
    	owlObjectComplementOf.getOperand().accept(RO);

    	//Report error if no error was reported
    	if(RO.getFinalReport() == null) report += "Restriction which should not be met: " + owlObjectComplementOf.getOperand().toString();
    	
    }
    
    //======================================================================== Restrictions which are not an Open World problem, except for characteristics (which have no instances)
    
    @Override public void visit(@Nonnull OWLObjectMaxCardinality owlObjectMaxCardinality) {
    	OWLObjectPropertyExpression property = owlObjectMaxCardinality.getProperty(); //object property
    	String propertyIRI = property.getNamedProperty().getIRI().toString(); //object property IRI
    	OWLClassExpression second = owlObjectMaxCardinality.getFiller(); //second individual    
    	long MaxCardinality = owlObjectMaxCardinality.getCardinality();
    	long cardinality;
    	
    	//-------------------------------------------------------------------------- Process "characteristic" related tasks
    	if(IgnoreOPs.contains(propertyIRI) || (!isCharacteristic)) return; //ignore these ObjProperties
    	cardinality = reasoner.getInstances(second, false).getFlattened().size(); //get no. of instances of the restricted component
    	
        if(cardinality > MaxCardinality){
        	report += "Restriction: " + property.getNamedProperty() + " max " + MaxCardinality + " " + second
        			+ "    ISSUE: " + cardinality + " > " + MaxCardinality + "\n";
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
//    		report += "Restriction: " + property.getNamedProperty() + " allvaluesfrom " + second + "    ISSUE: Some individuals are not instances of the restricted class\n";
//    		currentindividuals.forEach(i -> report += i.getIRI().toString() + "\n");
//    	}
//    }
    
    //======================================================================== Auxiliary functions
    /**
     * Gets the name for a new individual, which cannot be in use in any other kind of object
     * @return
     */
    private String getNextIndIRI(String prefix){
    	int counter = 1;
    	while(cachedIRIsInverse.get(prefix + String.valueOf(counter)) != null) counter++;  
    	cachedIRIsInverse.put(prefix + String.valueOf(counter), "");
    	return prefix + String.valueOf(counter);
    }
    
    
    private void computeSolution(OWLObjectPropertyExpression property, OWLClassExpression target, long cardinality, long cardinalityGoal){
    	String local_log = RestrictionOverseer.local_log + "[computeSolution] ";
    	StringBuilder solutionSB = new StringBuilder(1000); //set initial capacity to 1000 chars
    	
    	//=========================================================== Create relations between existent individuals if current individual is not a Characteristic
    	if(!isCharacteristic){
	    	try {
	    		
				//Get all existent individuals which are not yet related with the current individual (by the object property)
		    	Stream<OWLNamedIndividual> freeIndividuals = reasoner.getInstances(target, false).getFlattened().stream().filter(i -> !areRelated(individual, i, property));
		    	
		    	//Create relations with the previously filtered individuals
		    	Iterator<OWLNamedIndividual> it = freeIndividuals.iterator();
		    	while(it.hasNext()){
		    		solutionSB.append(cachedIRIs.get(individual.getIRI().toString()) + " " + cachedIRIs.get(property.getNamedProperty().getIRI().toString()) 
		    				 + " " + cachedIRIs.get(it.next().getIRI().toString()) + "\n"); 
		    		cardinality++;
		    		if(cardinality == cardinalityGoal) break;
		    	}		
		    	
			} catch (Exception e) {} //The getInstances() procedure fails when no individuals are found (only when using complex Class Expressions)
    	}
    	
    	//=========================================================== Get the target classes (if any) for the creation of new individuals
    	
    	//Get the target classes to be used upon the creation of new individuals
    	ClassSolver cs = new ClassSolver();
    	target.accept(cs);
    	List<OWLObjectIntersectionOf> targetClasses = cs.getAccepted();
    	
    	//Check if there are any classes (some Class Expressions are impossible to break down by the class solver) 
    	if(targetClasses == null || targetClasses.size() == 0) {
    		System.out.println(local_log + "Unbreakable target expression: " + target);
    		return;
    	}



    	/* The following code gets the Range of the OBJProp and checks if the target options are valid in that range (entailment)

	    //====== 0th Get intersection of range Class Expressions (including inheritance)
	    
	    OWLDataFactory factory = OWLManager.getOWLDataFactory();
	    List<OWLObjectPropertyExpression> propertyFamily = new ArrayList<OWLObjectPropertyExpression>();
	    propertyFamily.add(property);
	    reasoner.getSuperObjectProperties(property, false).entities().forEach(p -> propertyFamily.add(p));
	    List<OWLClassExpression> rangeClsExprs = new ArrayList<OWLClassExpression>();
	    
	    for(OWLObjectPropertyExpression p : propertyFamily){
	    	Stream<OWLObjectPropertyRangeAxiom> rangeAxioms = reasoner.getOntology().objectPropertyRangeAxioms(property);
		    rangeAxioms.forEach(Ax -> rangeClsExprs.add(Ax.getRange()));
	    }
	        
	    //======= 1st Range Class Expressions
	    OWLObjectIntersectionOf FinalRange = factory.getOWLObjectIntersectionOf(rangeClsExprs);
	    System.out.println("SOLVED RANGE: " + FinalRange);

	    //======= 2nd Check if the target options are contained in the Range Expression
	    for(OWLObjectIntersectionOf targ: targetClasses){
			 System.out.println("Checking entailment for target: " + targ);
			 OWLSubClassOfAxiom testAxiom = factory.getOWLSubClassOfAxiom(targ, FinalRange);
			 System.out.println(reasoner.isEntailed(testAxiom));
			 testAxiom = factory.getOWLSubClassOfAxiom(FinalRange,targ);
			 System.out.println(reasoner.isEntailed(testAxiom));
			 
		}
	    
    	 */
	  
    	
    	//=========================================================== Create the remaining relations with new individuals
    	
    	StringBuilder auxSB = new StringBuilder(500); //set initial capacity to 500 chars
    	for(int j=1; j<= cardinalityGoal-cardinality; j++){
		      
		    solutionSB.append("new "); //First keyword to create an individual
		      		    
		    // Instantiate first group of classes
		    Iterator<OWLObjectIntersectionOf> itt = targetClasses.iterator();
		    OWLObjectIntersectionOf c = itt.next();
		    String indname = getNextIndIRI(cachedIRIs.get(c.getOperands().iterator().next().asOWLClass().getIRI().toString())); //Individual name is incrementally generated
		    c.getOperands().forEach(o -> solutionSB.append(cachedIRIs.get(o.asOWLClass().getIRI().toString()) + ", "));
		    solutionSB.setLength(solutionSB.length()-2);
		    
		    // Comment instantiation of other groups of classes
		    if(itt.hasNext()){ solutionSB.append(" /* or ");
		    
    		    while(itt.hasNext()){ c = itt.next();
    		    	c.getOperands().forEach(o -> solutionSB.append(cachedIRIs.get(o.asOWLClass().getIRI().toString()) + ", "));
    		    	solutionSB.setLength(solutionSB.length()-2);
    		    	solutionSB.append(" or ");
    		    }
    		    solutionSB.setLength(solutionSB.length()-3); solutionSB.append("*/");
		    }

		    solutionSB.append(" " + indname + "\n"); //Insert name of new individual
		    
		    //Create a relation with the newly created individual
		    if(!isCharacteristic) auxSB.append(cachedIRIs.get(individual.getIRI().toString()) + " " + cachedIRIs.get(property.getNamedProperty().getIRI().toString()) 
			 + " " + indname + "\n"); 

    	}
    	
    	solutionSB.append(auxSB);
    	solution += solutionSB.toString();

    }
    
  //======================================================================== Not yet analyzed 


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
