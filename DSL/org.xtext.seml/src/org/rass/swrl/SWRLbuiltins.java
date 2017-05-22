package org.rass.swrl;

import java.util.Set;

import org.mindswap.pellet.ABox;
import org.mindswap.pellet.Node;
import org.mindswap.pellet.utils.ATermUtils;
import org.rass.ontologies.Ontologies;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.vocab.OWL2Datatype;

import static org.mindswap.pellet.utils.Namespaces.XSD; 

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;

public class SWRLbuiltins {
	
	private static final String local_log = "SWRL built-ins Log: ";
	private static final String ARGerr = local_log + "Error: wrong arguments!";
	private static OWLDataFactory fac = OWLManager.getOWLDataFactory();
	
	public static class no implements CustomSWRLBuiltin.CustomSWRLFunction {
	
		/**
		 * This class checks if a relation with a certain individual doesn't exist
		 * 
		 * @param arguments (individual, objectproperty, individual)
		 * @return true if no relation was found
		 * @throws SWRLBuiltInException
		 */
		@Override public boolean apply(ABox abox, Node[] args, PelletReasoner reasoner) { System.out.print('*');

			if(!args[0].isIndividual() || !args[1].isLiteral() || !args[2].isIndividual()){
				System.out.println(ARGerr); return false;
			}
			
			//get the OWLNamedIndividual from the 1st argument and the string value of the 2nd argument 
			OWLNamedIndividual ind1 = fac.getOWLNamedIndividual(IRI.create(args[0].getNameStr()));
			OWLObjectProperty obj = fac.getOWLObjectProperty(IRI.create( ATermUtils.getLiteralValue(args[1].getTerm()) )); 

			Set<OWLNamedIndividual> targets;
			
			//Try/Catch block will avoid impossible inconsistency explanation calls to reasoner
			try {
				//Get list of related individuals
				targets = reasoner.getObjectPropertyValues(ind1, obj).getFlattened();
			} catch (Exception e) {
				System.err.println(local_log + "Error obtaining: " + ind1 + " " + obj + " {...}");
				return false; //default action against erratic behavior
			}
			
			//Return true if the required relation does NOT exist
			OWLNamedIndividual ind2 = fac.getOWLNamedIndividual(IRI.create(args[2].getNameStr()));
			return !targets.contains(ind2);

		}

		@Override public boolean isApplicable(boolean[] boundPositions) {
			return (boundPositions.length == 3 && boundPositions[0] && boundPositions[1] && boundPositions[2]);
		}

	}
	
	public static class notSame implements CustomSWRLBuiltin.CustomSWRLFunction {
		
		/**
		 * This class checks if two individuals have the same IRI
		 * 
		 * @param arguments (individual, individual)
		 * @return true if no relation was found
		 * @throws SWRLBuiltInException
		 */
		@Override public boolean apply(ABox abox, Node[] args, PelletReasoner reasoner) { System.out.print('*');

			if(!args[0].isIndividual() || !args[1].isIndividual()){
				System.out.println(ARGerr); return false;
			}
			
			//Compare the IRI of each argument
			return !args[0].getNameStr().equals(args[1].getNameStr());
		}

		@Override public boolean isApplicable(boolean[] boundPositions) {
			return (boundPositions.length == 2 && boundPositions[0] && boundPositions[1]);
		}

	}
	
	
	public static class floatListSum implements CustomSWRLBuiltin.CustomSWRLFunction {
		
		/**
		 * This class adds the numeric value of literals attached to individuals of a certain Class
		 * The literals must be bound via the "hasValue" data property and be of type xsd:float
		 * 
		 * @param arguments (class, result)
		 * @return true if no error occurred
		 * @throws SWRLBuiltInException
		 */
		@Override public boolean apply(ABox abox, Node[] args, PelletReasoner reasoner) { System.out.print('*');

			if(!args[0].isLiteral() || args[1]!=null){
				System.out.println(ARGerr); return false;
			}
			
			float result = 0;
			
			//Get the OWLClass from the first parameter
			OWLClass c = fac.getOWLClass(IRI.create( ATermUtils.getLiteralValue(args[0].getTerm()) ));
			
			Set<OWLNamedIndividual> individuals;
			
			//Try/Catch block will avoid impossible inconsistency explanation calls to reasoner
			try {
				//Get all individuals of the provided OWLClass
				individuals = reasoner.getInstances(c, false).getFlattened();
			} catch (Exception e) {
				System.err.println(local_log + "Error obtaining individuals of class: " + c);
				return false; //default action against erratic behavior
			}
			
			//Iterate individuals and get literals which are bound via the "hasValue" data property
			for(OWLNamedIndividual i : individuals){
				Set<OWLLiteral> v = reasoner.getDataPropertyValues(i, Ontologies.OWLP_DP_hasValue);
				if(!v.isEmpty()){
					OWLLiteral l = v.iterator().next();
					if(l.isFloat()) result += l.parseFloat(); 		//add the value to the final sum
				}
			}
			
			//Fill second parameter with result
			args[1] = abox.addLiteral(ATermUtils.makeTypedLiteral(String.valueOf(result), XSD + "float")); 
            return args[1] != null; 
		}

		@Override public boolean isApplicable(boolean[] boundPositions) {
			return (boundPositions.length == 2 && boundPositions[0] && !boundPositions[1]);
		}

	}
	
public static class intListSum implements CustomSWRLBuiltin.CustomSWRLFunction {
		
		/**
		 * This class adds the numeric value of literals attached to individuals of a certain Class
		 * The literals must be bound via the "hasValue" data property and be of type:
		 *  xsd:integer, xsd:nonNegativeInteger, xsd:nonPositiveInteger, xsd:positiveInteger, xsd:int, xsd:float, xsd:double
		 * The result is returned as xsd:integer in the result variable
		 * 
		 * @param arguments (class, result)
		 * @return true if no error occurred
		 * @throws SWRLBuiltInException
		 */
		@Override public boolean apply(ABox abox, Node[] args, PelletReasoner reasoner) { System.out.print('*');

			if(!args[0].isLiteral() || args[1]!=null){
				System.out.println(ARGerr); return false;
			}
			
			int result = 0;
			
			//Get the OWLClass from the first parameter
			OWLClass c = fac.getOWLClass(IRI.create( ATermUtils.getLiteralValue(args[0].getTerm()) ));
			
			Set<OWLNamedIndividual> individuals;
			
			//Try/Catch block will avoid impossible inconsistency explanation calls to reasoner
			try {
				//Get all individuals of the provided OWLClass
				individuals = reasoner.getInstances(c, false).getFlattened();
			} catch (Exception e) {
				System.err.println(local_log + "Error obtaining individuals of class: " + c);
				return false; //default action against erratic behavior
			}
			
			//Iterate individuals and get literals which are bound via the "hasValue" data property
			for(OWLNamedIndividual i : individuals){
				Set<OWLLiteral> v = reasoner.getDataPropertyValues(i, Ontologies.OWLP_DP_hasValue);
				if(!v.isEmpty()){
					OWLLiteral l = v.iterator().next();
					OWL2Datatype d = l.getDatatype().getBuiltInDatatype();
					if(l.isInteger() |
						d.equals(OWL2Datatype.XSD_NON_NEGATIVE_INTEGER) |
						d.equals(OWL2Datatype.XSD_NON_POSITIVE_INTEGER) |
						d.equals(OWL2Datatype.XSD_POSITIVE_INTEGER) |
						d.equals(OWL2Datatype.XSD_INT)) result += l.parseInteger();
					else if(l.isFloat()) result += (int)l.parseFloat();
					else if(l.isDouble()) result += (int)l.parseDouble();
				}
			}
			
			//Fill second parameter with result
			args[1] = abox.addLiteral(ATermUtils.makeTypedLiteral(String.valueOf(result), XSD + "integer")); 
            return args[1] != null; 
		}

		@Override public boolean isApplicable(boolean[] boundPositions) {
			return (boundPositions.length == 2 && boundPositions[0] && !boundPositions[1]);
		}

	}
	
	/**
	 * These classes checks if the number of relations is
	 * EQUAL, GREATER THAN, GREATER or EQUAL, LESS THAN, LESS or EQUAL
	 * (than/to) the provided value
	 * 
	 * @param arguments  (value, individual, objectproperty, [targetClass]) 
	 * @return true if number of relations is equal to the provided value
	 * @throws SWRLBuiltInException
	 */
	public static class relEQ implements CustomSWRLBuiltin.CustomSWRLFunction {
		@Override public boolean apply(ABox abox, Node[] args, PelletReasoner reasoner) {
			try { return (relCount(args, reasoner) == 0); } catch (Exception e) {return false;}
		}
		@Override public boolean isApplicable(boolean[] boundPositions) {
			return ((boundPositions.length == 3 && boundPositions[0] && boundPositions[1] && boundPositions[2]) ||
					(boundPositions.length == 4 && boundPositions[0] && boundPositions[1] && boundPositions[2] && boundPositions[3]));}
	}
	public static class relGT implements CustomSWRLBuiltin.CustomSWRLFunction {
		@Override public boolean apply(ABox abox, Node[] args, PelletReasoner reasoner) {
			try { return (relCount(args, reasoner) > 0); } catch (Exception e) {return false;}
		}
		@Override public boolean isApplicable(boolean[] boundPositions) {
			return ((boundPositions.length == 3 && boundPositions[0] && boundPositions[1] && boundPositions[2]) ||
					(boundPositions.length == 4 && boundPositions[0] && boundPositions[1] && boundPositions[2] && boundPositions[3]));}
	}
	public static class relGE implements CustomSWRLBuiltin.CustomSWRLFunction {
		@Override public boolean apply(ABox abox, Node[] args, PelletReasoner reasoner) {
			try { return (relCount(args, reasoner) >= 0); } catch (Exception e) {return false;}
		}
		@Override public boolean isApplicable(boolean[] boundPositions) {
			return ((boundPositions.length == 3 && boundPositions[0] && boundPositions[1] && boundPositions[2]) ||
					(boundPositions.length == 4 && boundPositions[0] && boundPositions[1] && boundPositions[2] && boundPositions[3]));}
	}
	public static class relLT implements CustomSWRLBuiltin.CustomSWRLFunction {
		@Override public boolean apply(ABox abox, Node[] args, PelletReasoner reasoner) {
			try { return (relCount(args, reasoner) < 0); } catch (Exception e) {return false;}
		}
		@Override public boolean isApplicable(boolean[] boundPositions) {
			return ((boundPositions.length == 3 && boundPositions[0] && boundPositions[1] && boundPositions[2]) ||
					(boundPositions.length == 4 && boundPositions[0] && boundPositions[1] && boundPositions[2] && boundPositions[3]));}
	}
	public static class relLE implements CustomSWRLBuiltin.CustomSWRLFunction {
		@Override public boolean apply(ABox abox, Node[] args, PelletReasoner reasoner) {
			try { return (relCount(args, reasoner) <= 0); } catch (Exception e) {return false;}
		}
		@Override public boolean isApplicable(boolean[] boundPositions) {
			return ((boundPositions.length == 3 && boundPositions[0] && boundPositions[1] && boundPositions[2]) ||
					(boundPositions.length == 4 && boundPositions[0] && boundPositions[1] && boundPositions[2] && boundPositions[3]));}
	}
	
	/**
	 * This is a private method to compute the difference between the no. of relations and the provided value
	 * 
	 * @param arguments (value, individual, objectproperty, [targetClass]) 
	 * @return (number of related individuals)-(provided value)
	 * @throws Exception 
	 */
	private static int relCount(Node[] args, PelletReasoner reasoner) throws Exception { System.out.print('*');
		
		if(!args[0].isLiteral() || !args[1].isIndividual() || !args[2].isLiteral()){
			System.out.println(ARGerr); throw new Exception();
		}

		//get the OWLNamedIndividual from the 1st argument and the string value of the 2nd argument 
		OWLNamedIndividual ind1 = fac.getOWLNamedIndividual(IRI.create(args[1].getNameStr()));
		OWLObjectProperty obj = fac.getOWLObjectProperty(IRI.create( ATermUtils.getLiteralValue(args[2].getTerm()) )); 
		int relTargetValue;
		try {
			relTargetValue = Integer.parseInt(ATermUtils.getLiteralValue(args[0].getTerm()));
		} catch (NumberFormatException e) {
			System.out.println("The 1st argument of the Relation's Number Comparison Built-in is not an integer."); throw new Exception();
		}
		
		//Get related individuals
		Set<OWLNamedIndividual> linkedIndividuals = reasoner.getObjectPropertyValues(ind1, obj).getFlattened();
		
		//Get number of related individuals
		int relationsNo = linkedIndividuals.size();
		
		//If no relations were found, return immediately
		if(relationsNo==0) return (-relTargetValue);
		
		//Otherwise, return (number of related individuals)-(provided value) if no class was specified
		if(args.length == 3) return relationsNo - relTargetValue;

		//Get all target individuals from the target class provided by the 4th argument
		if(!args[3].isLiteral()){ System.out.println(ARGerr); throw new Exception(); }
		OWLClass targetCls = fac.getOWLClass( IRI.create( ATermUtils.getLiteralValue( args[3].getTerm() ))); 
		NodeSet<OWLNamedIndividual> targetInd = reasoner.getInstances(targetCls, false);
		
		//Remove linked individuals which do not belong to the target class
		for(OWLNamedIndividual i :  linkedIndividuals){
			if(!targetInd.containsEntity(i)) relationsNo--;
		}
		return relationsNo - relTargetValue;

	}

}
