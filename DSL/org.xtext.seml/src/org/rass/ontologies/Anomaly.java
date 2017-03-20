package org.rass.ontologies; 

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.osgi.framework.FrameworkUtil;
import org.rass.swrl.ExternalSWRLBuiltins;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.AxiomType;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.SWRLRule;
import org.semanticweb.owlapi.reasoner.Node;
import org.semanticweb.owlapi.reasoner.NodeSet;

import com.clarkparsia.owlapi.explanation.PelletExplanation;
import com.clarkparsia.owlapi.explanation.io.manchester.ManchesterSyntaxExplanationRenderer;
import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
 

public class Anomaly {
	
	private static final String local_log = "Anomaly Log: ";
	private static final ManchesterSyntaxExplanationRenderer renderer = new ManchesterSyntaxExplanationRenderer();
	
	private static StringWriter outputWriter = null;
	private static String errorList = null;
	private static String warningList = null;
	private static String infoList = null;
	
	private static boolean hasAnomalies = false;
	private static boolean hasErrors = false;
	private static boolean hasWarnings = false;
	private static boolean hasInfos = false;
		
			
	public enum ReportLevel {
	    CONSISTENCY, SATISFIABILITY, ERROR, WARNING, INFORMATION 
	}
	
	public static String getAnomalies(){if(hasAnomalies)return outputWriter.toString(); else return null;}
	public static String getErrors(){ 	if(hasErrors)	return errorList; 	else return null;}
	public static String getWarnings(){ if(hasWarnings) return warningList; else return null;}
	public static String getInfos(){ 	if(hasInfos) 	return infoList; 	else return null;}
	//public static boolean hasAnyReport(){return (hasErrors || hasWarnings || hasInfos);}
	
	/*private static ReportLevel mainReportType = null;
	public static String getMainReport(){
		if(hasAnomalies){mainReportType=ReportLevel.CONSISTENCY; 	return outputWriter.toString();	}
		if(hasErrors) 	{mainReportType=ReportLevel.ERROR; 			return errorList;				}
		if(hasWarnings) {mainReportType=ReportLevel.WARNING; 		return warningList;				}
		if(hasInfos) 	{mainReportType=ReportLevel.INFORMATION; 	return infoList;				}
		return null;
	}*/
	

	
	/**
	 * Analyze the ontology
	 * 
	 * @param reasoner
	 * @param ontology
	 * @param RL
	 * @return true if any kind of report was detected (for the chosen ReportLevel)
	 */
	public static boolean ReasonAndExplain(PelletReasoner reasoner, OWLOntology ontology, ReportLevel RL){
		final String local_log = Anomaly.local_log + "[ReasonAndExplain] ";
		
		//reset outputWriter
		hasAnomalies = false; hasErrors = false; hasWarnings = false; hasInfos = false; 
		
		//================================================ Test for consistency before SWRL (to protect built-ins)
		System.out.print(local_log + "Checking for anomalies");
		
		if(!reasoner.isConsistent()){
			System.out.write('\n');
			ExplainInconsistencies(ontology);
			hasAnomalies = true;
			return true; //inconsistent ontologies cannot be further evaluated by a reasoner
		}
		System.out.write('\n');
		
		//------------------------------------------------ Ontology is consistent, return if goal is met
		if(RL == ReportLevel.CONSISTENCY) return false; //no problem was detected

		//================================================ Test for unsatisfiability
		Node<OWLClass> bottomNode = reasoner.getUnsatisfiableClasses(); //classes equivalent to owl:Nothing
        Set<OWLClass> unsatisfiable = bottomNode.getEntitiesMinusBottom(); //unsatisfiable classes excluding owl:Nothing
        if (!unsatisfiable.isEmpty()) {
        	ExplainUnsatisfiability(ontology, unsatisfiable);
        	hasAnomalies = true;
        	return true; //unsatisfiable classes generate false reports
        }
        
        //------------------------------------------------ Ontology is satisfiable, return if goal is met
      	if(RL == ReportLevel.SATISFIABILITY) return false; //no problem was detected
        
        //================================================ Test for Error Class individuals
      	String rep = HandleReports(reasoner, MasterCache.errorClasses);
      	if(rep.length()!=0){
      		errorList = /*"Error reports were found:\n" +*/ rep;
      		hasErrors = true;
      	}
      	//------------------------------------------------ Ontology might contain errors, return if goal is met
      	if(RL == ReportLevel.ERROR) return hasErrors;
      	
      	 //================================================ Test for Warning Class individuals
      	rep = HandleReports(reasoner, MasterCache.warningClasses);
      	if(rep.length()!=0){
      		warningList = /*"Warning reports were found:\n" +*/ rep;
      		hasWarnings = true;
      	}
      	//------------------------------------------------ Ontology might contain errors/warnings, return if goal is met
      	if(RL == ReportLevel.WARNING) return (hasErrors || hasWarnings);
      	
      	 //================================================ Test for Information Class individuals
      	rep = HandleReports(reasoner, MasterCache.infoClasses);
      	if(rep.length()!=0){
      		infoList = /*"Information reports were found:\n" +*/ rep;
      		hasInfos = true;
      	}
      	//------------------------------------------------ Ontology might contain errors/warnings/informations
      	return (hasErrors || hasWarnings || hasInfos);
      	
	}
	
	
	private static String HandleReports(PelletReasoner reasoner, Set<OWLClass> reportClasses){
		final String local_log = Anomaly.local_log + "[HandleReports] ";
		
		StringBuilder reports = new StringBuilder(100); //set initial capacity to 100 chars
		
		if(reportClasses==null) {System.out.println(local_log + "Error: master cache is not loaded."); return reports.toString();}
        
        for(OWLClass c : reportClasses){
        	Set<OWLNamedIndividual> agents = reasoner.getInstances(c, true).getFlattened();
        	if(!agents.isEmpty()){
        		reports.append(c.getIRI().getShortForm() + " - Instance Agents: ");
	        	for(OWLNamedIndividual a : agents){ reports.append(a.getIRI().toString() + " "); }
        	}
        }

        return reports.toString();
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
		
		System.out.print(local_log + "Explaining inconsistencies");
		
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
			System.out.write('\n');
			if(explanationAxioms.isEmpty()){outputWriter.write("No explanation found.");}
			while(!explanationAxioms.isEmpty()) {
				renderer.render( explanationAxioms);
				explanationAxioms.remove(explanationAxioms.iterator().next());
			}
			renderer.endRendering();
	
		//In case of failure try to get only the first explanation
		} catch (Throwable e) {
			System.out.println("\n" + local_log + "Unable to render >1 explanation. Error: " + e.getMessage());
			System.out.print(local_log + "Rendering 1 explanation");
			try {
				explanationAxioms = expGen.getInconsistencyExplanations(1);
				System.out.write('\n');
				renderer.render( explanationAxioms);
				renderer.endRendering();
			} catch (Throwable e1) {
				
		//In case of failure report error in console
				System.out.println('\n' + local_log + "Error: " + e1.getMessage());
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
	
	
	