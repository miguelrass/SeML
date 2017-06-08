package org.rass.implementation;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

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
	private TreeMap<Integer, ArrayList<Pair<String[],ArrayList<List<String>>>>> commands = null;
	
	private static PelletReasoner reasoner = null;
	private static OWLOntology ontology = null;
	
	public static void SetReasoner(PelletReasoner r){
		reasoner = r; ontology = r.getRootOntology();
	}


	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final String local_log = ImplementationHandler.local_log + "[Execute] ";

		Console.ImpPairLn(local_log, "Running Implementation...");
		
		//commands:
		//Priority -> Commands
		//Command = ([Tool, Function, ..], Argument Packs)
		//Argument Pack = List of Arguments
		commands = new TreeMap<Integer, ArrayList<Pair<String[],ArrayList<List<String>>>>>();
		
		Set<OWLNamedIndividual> inds = reasoner.getInstances(Ontologies.OWLC_Component, false).getFlattened(); //get instances of Component

		for(OWLNamedIndividual i : inds){
			Set<OWLAnnotationAssertionAxiom> annotations = ontology.getAnnotationAssertionAxioms(i.getIRI());
	    	for (OWLAnnotationAssertionAxiom annotation : annotations) { //gets annotations associated with the individual
	    		if(Ontologies.OWL_Ann_ImplInd.equals(annotation.getProperty().getIRI().toString())){
	    			
	        		//Parse input command
	    			Pair<String[], ArrayList<Pair<String[], ArrayList<List<String>>>>> args_cmdList = CmdParse(annotation, 2);

	    			//Check if user defined a specific annotation property for the arguments
	        		String annotationP = Ontologies.OWL_Ann_ImplArg; //arguments annotation property
	        		if(args_cmdList.getKey().length > 3) annotationP = args_cmdList.getKey()[3]; //get user-defined Annotation Property
	    			
	        		//Create new command object and insert arguments
	        		ArrayList<List<String>> command = new ArrayList<List<String>>();
	        		command.add(insertAnnPack(annotations, annotationP)); //Add arguments pack (from individual)   	
	        		
	        		//Insert command object 
	        		args_cmdList.getValue().add(new Pair<>(args_cmdList.getKey(),command));
	        		
	        	}else if(Ontologies.OWL_Ann_ImplOP.equals(annotation.getProperty().getIRI().toString())){
	        		
	        		//Parse input command
	    			Pair<String[], ArrayList<Pair<String[], ArrayList<List<String>>>>> args_cmdList = CmdParse(annotation, 3);
	        		
	        		//Get object property
	        		IRI objectIRI = IRI.create(args_cmdList.getKey()[2]);
	        		OWLObjectProperty objectP = factory.getOWLObjectProperty(objectIRI);
	        		
	        		//Check if user defined a specific annotation property for the arguments
	        		String annotationP = Ontologies.OWL_Ann_ImplArg; //arguments annotation property
	        		if(args_cmdList.getKey().length > 4) annotationP = args_cmdList.getKey()[4];
	        		
	        		//Get all individuals that are linked via the provided OP
	        		Set<OWLNamedIndividual> ind2s = reasoner.getObjectPropertyValues(i, objectP).getFlattened();
	        		
	        		//Build command for each relation
	        		for(OWLNamedIndividual i2: ind2s){
	        			ArrayList<List<String>> command = new ArrayList<List<String>>();
	        			
	        			command.add(insertAnnPack(annotations, annotationP)); //Add arguments pack (from individual)
	        			//command.add(insertAnnPack(ontology.getAnnotationAssertionAxioms(objectIRI), annotationP)); //Add arguments pack (from OP)
	        			command.add(insertAnnPack(ontology.getAnnotationAssertionAxioms(i2.getIRI()), annotationP)); //Add arguments pack (from individual2)
	        		
	        			//Insert command object 
	        			args_cmdList.getValue().add(new Pair<>(args_cmdList.getKey(),command));
	        		}

	        	}else if(Ontologies.OWL_Ann_ImplDP.equals(annotation.getProperty().getIRI().toString())){
	        		
	        		//Parse input command
	    			Pair<String[], ArrayList<Pair<String[], ArrayList<List<String>>>>> args_cmdList = CmdParse(annotation, 3);
	        		
	        		//Get data property
	        		IRI dataIRI = IRI.create(args_cmdList.getKey()[2]);
	        		OWLDataProperty dataP = factory.getOWLDataProperty(dataIRI);
	        		
	        		//Check if user defined a specific annotation property for the arguments
	        		String annotationP = Ontologies.OWL_Ann_ImplArg; //arguments annotation property
	        		if(args_cmdList.getKey().length > 4) annotationP = args_cmdList.getKey()[4];
	        		
	        		//Get all individuals that are linked via the provided DP
	        		Set<OWLLiteral> literals = reasoner.getDataPropertyValues(i, dataP);
	        		
	        		//Build command for each relation
	        		for(OWLLiteral l: literals){
	        			ArrayList<List<String>> command = new ArrayList<List<String>>();
	        			
	        			command.add(insertAnnPack(annotations, annotationP)); //Add arguments pack (from individual)
	        			//command.add(insertAnnPack(ontology.getAnnotationAssertionAxioms(dataIRI), annotationP)); //Add arguments pack (from DP)
	        			command.add(Arrays.asList(l.getLiteral())); //Add arguments pack (one literal)

	        			//Insert command object 
	        			args_cmdList.getValue().add(new Pair<>(args_cmdList.getKey(),command));
	        		}
	        	}
	        }
		}
		
		ToolDispatcher.LoadToolClasses();
		
		for(ArrayList<Pair<String[], ArrayList<List<String>>>> parallelCmds: commands.values()){ //Commands with same priority
			for(Pair<String[], ArrayList<List<String>>> cmd: parallelCmds){ //Single command

				String toolSt = cmd.getKey()[0];
				String funcSt = cmd.getKey()[1];
				
				//If class is not loaded, display call in console
				if(!ToolDispatcher.loadedClasses.containsKey(toolSt)){
					Console.ImpPairLn(local_log, "Tool: " + toolSt + "  Function: " + funcSt + "  Arguments: " + cmd.getValue());
					continue;
				}
				
				Class<?> cls = ToolDispatcher.loadedClasses.get(toolSt);			
	
				try {
					Method m = cls.getMethod(funcSt, ArrayList.class);
					
					String ret = (String) m.invoke(null,cmd.getValue());
					if(ret != null) Console.ImpPairLn(local_log, ret);
				} 
				catch (NoSuchMethodException e) 	{Console.ErrPairLn(local_log, "Method not found: " + funcSt);} 
				catch (SecurityException e)	 		{e.printStackTrace();} 
				catch (IllegalAccessException e) 	{e.printStackTrace();} 
				catch (IllegalArgumentException e) 	{e.printStackTrace();}
				catch (InvocationTargetException e) {e.printStackTrace();}
        
			}
		}

		//HandlerUtil.getActiveWorkbenchWindow(event).close();
		Console.ImpPairLn(local_log, "Done.");
		return null;
	}
	
	/**
	 * Parses command
	 * @param annotation
	 * @param priorityArgNo
	 * @return Parameters list and Command list for that priority
	 * 
	 */
	private Pair<String[],ArrayList<Pair<String[], ArrayList<List<String>>>>> CmdParse(OWLAnnotationAssertionAxiom annotation, int priorityIndex){
			
		//Get comma separated parameters from annotation
		String[] params = annotation.getValue().asLiteral().get().getLiteral().split(",");
		
		//Get command's priority
		int priority = 10; //default priority
		if(params.length > priorityIndex) priority = Integer.parseInt(params[priorityIndex]);
		
		//Get command list for the given priority
		ArrayList<Pair<String[], ArrayList<List<String>>>> cmdList = commands.get(priority);
		
		//If command list was not found, create it and add it to commands
		if(cmdList == null){ 
			cmdList = new ArrayList<Pair<String[], ArrayList<List<String>>>>();
			commands.put(priority, cmdList);
		}

		return new Pair<>(params,cmdList);
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
