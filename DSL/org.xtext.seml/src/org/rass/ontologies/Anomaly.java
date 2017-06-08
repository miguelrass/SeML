package org.rass.ontologies; 

import java.io.StringWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.Node;

import com.clarkparsia.owlapi.explanation.PelletExplanation;
import com.clarkparsia.owlapi.explanation.io.manchester.ManchesterSyntaxExplanationRenderer;
import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
 

public class Anomaly {
	
	private static final String local_log = "Anomaly Log: ";
	private static final ManchesterSyntaxExplanationRenderer renderer = new ManchesterSyntaxExplanationRenderer();
	
	private static StringWriter outputWriter = null;	
	private static boolean hasAnomalies = false;

	public static HashMap<String, HashSet<OWLClass>> IndividualReports = null;
		
	public static String getAnomalies(){if(hasAnomalies)return outputWriter.toString(); else return null;}


	
	/**
	 * Check for inconsistencies and unsatisfiabilities
	 * 
	 * @param reasoner
	 * @param ontology
	 * @param RL
	 * @return true if inconsistencies or unsatisfiabilities exist
	 */
	public static boolean ReasonAndExplain(PelletReasoner reasoner, OWLOntology ontology){
		final String local_log = Anomaly.local_log + "[ReasonAndExplain] ";
		
		//reset outputWriter
		hasAnomalies = false; 
		
		//================================================ Test for consistency before SWRL (to protect built-ins)
		System.out.println(local_log + "Checking for anomalies");
		
		if(!reasoner.isConsistent()){
			ExplainInconsistencies(ontology);
			hasAnomalies = true;
			return true; //inconsistent ontologies cannot be further evaluated by a reasoner
		}

		//================================================ Test for unsatisfiability
		Node<OWLClass> bottomNode = reasoner.getUnsatisfiableClasses(); //classes equivalent to owl:Nothing
        Set<OWLClass> unsatisfiable = bottomNode.getEntitiesMinusBottom(); //unsatisfiable classes excluding owl:Nothing
        if (!unsatisfiable.isEmpty()) {
        	ExplainUnsatisfiability(ontology, unsatisfiable);
        	hasAnomalies = true;
        	return true; //unsatisfiable classes generate false reports
        }
        
        return false; //no problem was detected
	}
	

	
    private static void ExplainInconsistencies(OWLOntology ontology){
		final String local_log = Anomaly.local_log + "[ExplainInconsistencies] ";

		//================================================================ Isolated Ontology Approach
		OWLOntology ont_iso = null;
		OWLOntologyManager man_iso = OWLManager.createOWLOntologyManager();
		try {
			ont_iso =  man_iso.createOntology(IRI.create("Explanation:Ontology"));
		} catch (OWLOntologyCreationException e2) {e2.printStackTrace();}
		man_iso.addAxioms(ont_iso, ontology.getAxioms());
		//================================================================ 
		
		System.out.println(local_log + "Explaining inconsistencies");
		
		// Create an explanation generator
		PelletExplanation expGen = new PelletExplanation(ont_iso); //Must be created before Built-in-enabled Pellet Reasoner
		
		//Initialize outputWriter
		outputWriter = new StringWriter(2000);
				
		//Start Renderer
		renderer.startRendering(outputWriter );
		
		//Try to get all explanations (up to 2)
		Set<Set<OWLAxiom>> explanationAxioms = null;
		try {
			explanationAxioms = expGen.getInconsistencyExplanations(2);
			if(explanationAxioms.isEmpty()){outputWriter.write("No explanation found.");}
			while(!explanationAxioms.isEmpty()) {
				renderer.render( explanationAxioms);
				explanationAxioms.remove(explanationAxioms.iterator().next());
			}
			renderer.endRendering();
	
		//In case of failure try to get only the first explanation
		} catch (Throwable e) {
			System.out.println(local_log + "Unable to render >1 explanation. Error: " + e.getMessage());
			System.out.println(local_log + "Rendering 1 explanation");
			try {
				explanationAxioms = expGen.getInconsistencyExplanations(1);
				renderer.render( explanationAxioms);
				renderer.endRendering();
			} catch (Throwable e1) {
				
		//In case of failure report error in console
				System.out.println(local_log + "Error: " + e1.getMessage());
				outputWriter.write("The inconsistencies explanation could not be rendered");
			}
		}
		
	}
    
    /**
     * Explains unsatisfiability of the provided classes
     * Isolation is not needed, since it is used when loading a new ontology (no mods)
     * @param ontology
     * @param unsatClasses
     */
    private static void ExplainUnsatisfiability(OWLOntology ontology, Set<OWLClass> unsatClasses){
  		final String local_log = Anomaly.local_log + "[ExplainUnsatisfiability] ";
  		
  		// Create an explanation generator
  		PelletExplanation expGen = new PelletExplanation(ontology); //Must be created before Built-in-enabled Pellet Reasoner
  		
  		//Initialize outputWriter
  		outputWriter = new StringWriter(2000);
  			
  		//Start Renderer
  		renderer.startRendering(outputWriter );
  		
  		// Explain the unsatisfiable class
  		for(OWLClass c : unsatClasses){
  			try {
  				outputWriter.write("Unsatisfiable Class: " + c.getIRI().toString() + "\n");
				Set<Set<OWLAxiom>> explanationAxioms = expGen.getUnsatisfiableExplanations(c, 1);
				if(explanationAxioms.isEmpty()){outputWriter.write("No explanation found.");}
	  			if(!explanationAxioms.isEmpty()) {
	  				renderer.render(explanationAxioms);
	  			}
	  			renderer.endRendering();
			}catch(Exception e){
				System.out.println(local_log + "Error while explaining " + c + " unsatisfiability: " + e.getMessage());
			}
  		}
	
  	}
	
}
	
	
	