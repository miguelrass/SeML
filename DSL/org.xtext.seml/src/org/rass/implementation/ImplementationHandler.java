package org.rass.implementation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.ui.handlers.HandlerUtil;
import org.eclipse.xtext.xbase.lib.Pair;
import org.rass.ontologies.Ontologies;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAnnotationAssertionAxiom;
import org.semanticweb.owlapi.model.OWLAnnotationProperty;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.xtext.seml.Console;
import org.xtext.seml.validation.SeMLValidator;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;



public class ImplementationHandler implements IHandler {
	
	private static final String local_log = "Implementation Log: ";
	private static OWLDataFactory factory = OWLManager.getOWLDataFactory();
	
	private static PelletReasoner reasoner = null;
	private static OWLOntology ontology = null;
	
	public static void SetReasoner(PelletReasoner r){
		reasoner = r; ontology = r.getRootOntology();
	}


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final String local_log = ImplementationHandler.local_log + "[Execute] ";

		Console.ImpPairLn(local_log, "Running Implementation...");
		
		// (Tool,Function) -> List of Commands
		// Command = List of Argument Packs by Source
		// Argument Pack = List of Arguments
		HashMap<Pair<String,String>, ArrayList<ArrayList<List<String>>>> commands = new HashMap<Pair<String,String>, ArrayList<ArrayList<List<String>>>>();
		
		Set<OWLNamedIndividual> inds = reasoner.getInstances(Ontologies.OWLC_Component, false).getFlattened(); //get instances of Component

		for(OWLNamedIndividual i : inds){
			Set<OWLAnnotationAssertionAxiom> annotations = ontology.getAnnotationAssertionAxioms(i.getIRI());
	    	for (OWLAnnotationAssertionAxiom annotation : annotations) { //gets annotations associated with the individual
	    		if(Ontologies.OWL_Ann_ImplInd.equals(annotation.getProperty().getIRI().toString())){
	    			
	    			String annotationP = Ontologies.OWL_Ann_ImplArg; //arguments annotation property
	    			
	    			//Get comma separated parameters from annotation
	        		String[] params = annotation.getValue().asLiteral().get().getLiteral().split(",");
	        		
	        		//Search (Tool,Function) entry or create a new one
	        		Pair<String,String> key = new Pair<String,String>(params[0],params[1]);
	        		ArrayList<ArrayList<List<String>>> toolFunc = commands.get(key);
	        		if(toolFunc==null){toolFunc = new ArrayList<ArrayList<List<String>>>(); commands.put(key, toolFunc);}
	        		
	        		//Check if user defined a specific annotation property for the arguments
	        		if(params.length == 3) annotationP = params[2];
	        		
	        		//Build command with all arguments
	        		ArrayList<List<String>> command = new ArrayList<List<String>>();
	        		command.add(insertAnnPack(annotations, annotationP)); //Add arguments pack (from individual)   		
	        		
	        		toolFunc.add(command); //add command to (Tool,Function) entry
	        		
	        	}else if(Ontologies.OWL_Ann_ImplOP.equals(annotation.getProperty().getIRI().toString())){
	        		
	    			String annotationP = Ontologies.OWL_Ann_ImplArg; //arguments annotation property
	    			
	    			//Get comma separated parameters from annotation
	        		String[] params = annotation.getValue().asLiteral().get().getLiteral().split(",");
	        		
	        		//Search (Tool,Function) entry or create a new one
	        		Pair<String,String> key = new Pair<String,String>(params[0],params[1]);
	        		ArrayList<ArrayList<List<String>>> toolFunc = commands.get(key);
	        		if(toolFunc==null){toolFunc = new ArrayList<ArrayList<List<String>>>(); commands.put(key, toolFunc);}
	        		
	        		//Get object property
	        		IRI objectIRI = IRI.create(params[2]);
	        		OWLObjectProperty objectP = factory.getOWLObjectProperty(objectIRI);
	        		
	        		//Check if user defined a specific annotation property for the arguments
	        		if(params.length == 4) annotationP = params[3];
	        		
	        		//Get all individuals that are linked via the provided OP
	        		Set<OWLNamedIndividual> ind2s = reasoner.getObjectPropertyValues(i, objectP).getFlattened();
	        		
	        		//Build command for each relation
	        		for(OWLNamedIndividual i2: ind2s){
	        			ArrayList<List<String>> command = new ArrayList<List<String>>();
	        			
	        			command.add(insertAnnPack(annotations, annotationP)); //Add arguments pack (from individual)
	        			//command.add(insertAnnPack(ontology.getAnnotationAssertionAxioms(objectIRI), annotationP)); //Add arguments pack (from OP)
	        			command.add(insertAnnPack(ontology.getAnnotationAssertionAxioms(i2.getIRI()), annotationP)); //Add arguments pack (from individual2)
	        		
	        			toolFunc.add(command); //add command to (Tool,Function) entry
	        		}

	        	}else if(Ontologies.OWL_Ann_ImplDP.equals(annotation.getProperty().getIRI().toString())){
	        		
	        		String annotationP = Ontologies.OWL_Ann_ImplArg; //arguments annotation property
	    			
	    			//Get comma separated parameters from annotation
	        		String[] params = annotation.getValue().asLiteral().get().getLiteral().split(",");
	        		
	        		//Search (Tool,Function) entry or create a new one
	        		Pair<String,String> key = new Pair<String,String>(params[0],params[1]);
	        		ArrayList<ArrayList<List<String>>> toolFunc = commands.get(key);
	        		if(toolFunc==null){toolFunc = new ArrayList<ArrayList<List<String>>>(); commands.put(key, toolFunc);}
	        		
	        		//Get data property
	        		IRI dataIRI = IRI.create(params[2]);
	        		OWLDataProperty dataP = factory.getOWLDataProperty(dataIRI);
	        		
	        		//Check if user defined a specific annotation property for the arguments
	        		if(params.length == 4) annotationP = params[3];
	        		
	        		//Get all individuals that are linked via the provided DP
	        		Set<OWLLiteral> literals = reasoner.getDataPropertyValues(i, dataP);
	        		
	        		//Build command for each relation
	        		for(OWLLiteral l: literals){
	        			ArrayList<List<String>> command = new ArrayList<List<String>>();
	        			
	        			command.add(insertAnnPack(annotations, annotationP)); //Add arguments pack (from individual)
	        			//command.add(insertAnnPack(ontology.getAnnotationAssertionAxioms(dataIRI), annotationP)); //Add arguments pack (from DP)
	        			command.add(Arrays.asList(l.getLiteral())); //Add arguments pack (one literal)

	        			toolFunc.add(command); //add command to (Tool,Function) entry
	        		}
	        	}
	        }
		}
		
		
		for(Entry<Pair<String, String>, ArrayList<ArrayList<List<String>>>> cmds: commands.entrySet()){
			Console.ImpPairLn(local_log, "Commands for tool: " + cmds.getKey().getKey() + "  Function: " + cmds.getKey().getValue());

			for(ArrayList<List<String>> cmd : cmds.getValue())
				Console.ImpPairLn(local_log, "\t" + cmd.toString());
		}

		//HandlerUtil.getActiveWorkbenchWindow(event).close();
		Console.ImpPairLn(local_log, "Done.");
		return null;
	}
	
	private ArrayList<String> insertAnnPack(Set<OWLAnnotationAssertionAxiom> annotations, String annP){
		
		ArrayList<String> pack = new ArrayList<String>();
		
		for (OWLAnnotationAssertionAxiom argAnn : annotations) //gets annotations associated with the OP
			if(annP.equals(argAnn.getProperty().getIRI().toString()))
				pack.add(argAnn.getValue().asLiteral().get().getLiteral()); //add arguments from OP
		
		return pack;
	}
	
	@Override
	public void addHandlerListener(IHandlerListener handlerListener) {
		//Is called once, in the beginning
	}

	@Override
	public void dispose() {
		//never called
	}

	@Override
	public boolean isEnabled() {
		//called every time, the pop-up menu is opened
		return SeMLValidator.validationState == 3;
	}

	@Override
	public boolean isHandled() {
		//called before each execution
		return true;
	}

	@Override
	public void removeHandlerListener(IHandlerListener handlerListener) {
		//never called
	}

}
