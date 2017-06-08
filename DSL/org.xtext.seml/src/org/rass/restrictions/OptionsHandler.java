package org.rass.restrictions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.commands.Command;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.commands.IHandler;
import org.eclipse.core.commands.IHandlerListener;
import org.eclipse.core.commands.common.NotDefinedException;
import org.eclipse.swt.widgets.Event;
import org.rass.ontologies.MasterCache;
import org.rass.ontologies.Ontologies;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.xtext.seml.Console;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;



public class OptionsHandler implements IHandler {
	
	private static final String local_log = "Options handler Log: ";
	public static int quickfixMode = 2; //Default: Smart
	public static int validationMode = 3; //Default: Disabled
	private static String settingsFileName = "/settings.bin";

	/**
	 * Change SeML Options
	 * 
	 * quickfixMode:
	 * - (0)Fast 		(All quickfixes are generated but none is tested)
	 * - (1)Smart 		(All quickfixes are generated and tested)
	 * 
	 * validationMode:
	 * - (0)Fast 		Edit/Save/Build/Request
	 * - (1)Normal 		Save/Build/Request
	 * - (2)Expensive	Request
	 */
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		final String local_log = OptionsHandler.local_log + "[execute] "; 
		
		String cmd;
		try {
			cmd = event.getCommand().getName();
		} catch (NotDefinedException e) {
			e.printStackTrace();
			return null;
		}
		
		String displayStr="[internal error]";

		switch (cmd) {
			case "Fast": 			quickfixMode=0;		displayStr="Quickfix Fast Mode"; 		break;
			case "Smart":			quickfixMode=1; 	displayStr="Quickfix Smart Mode"; 		break;
			case "FastVal": 		validationMode=0; 	displayStr="Validation Fast Mode"; 		break;
			case "NormalVal":		validationMode=1; 	displayStr="Validation Normal Mode"; 	break;
			case "ExpensiveVal": 	validationMode=2; 	displayStr="Validation Expensive Mode"; break;
		}
		
		if(CreateSettingsFile()) Console.DebPairLn(local_log, "Option saved: " + displayStr);

		return null;
	}
	
	private boolean CreateSettingsFile(){
		
		//Check if path was already created (needs Resource access to be created)
		if(Ontologies.GENfolder == null) return false; 
		
		File settingsFile = new File(Ontologies.GENfolder + settingsFileName); //generated file object (absolute path)
		
		FileOutputStream fout=null;
    	ObjectOutputStream oos=null;
    	try{
    		fout = new FileOutputStream(settingsFile);
        	oos = new ObjectOutputStream(fout);
        	oos.writeObject(quickfixMode); 
        	oos.writeObject(validationMode); 
        	oos.close();
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	    if(oos  != null){
    	        try {oos.close();} catch (IOException e) {e.printStackTrace();}
    	    } 
    	    return false;
    	} 
    	return true;
	}
	
	@SuppressWarnings("unchecked")
	public static void LoadSettings(){
		final String local_log = OptionsHandler.local_log + "[LoadSettings] "; 
		
		File settingsFile = new File(Ontologies.GENfolder + settingsFileName); //generated file object (absolute path)
		
		//If file is not found, keep default values
		if(!settingsFile.exists()) {validationMode=0; return;}
		   	
    	ObjectInputStream objectinputstream = null;
    	try {
    		FileInputStream streamIn = new FileInputStream(settingsFile);
    	    objectinputstream = new ObjectInputStream(streamIn);

    	    quickfixMode = (int) objectinputstream.readObject();
    	    validationMode = (int) objectinputstream.readObject();
	    	objectinputstream .close();
	    	
    	} catch (Exception e) {
    	    e.printStackTrace();
    	    if(objectinputstream != null){
    	        try {objectinputstream .close();} catch (IOException e1) {e1.printStackTrace();}
    	    } 
    	}
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
		return true;
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
