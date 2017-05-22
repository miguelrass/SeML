package org.rass.ontologies;

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
import java.util.Map.Entry;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;

/**
 * HDD Cache Class
 * @author Miguel Abreu
 *
 */
public class MasterCache {
	
	private static final String local_log = "MasterCache Log: ";
	private static String CacheFileName = "/m.cache";
	
	/**
	 * Non-static individuals
	 * Is used by MasterOntology.java to remove individuals from loaded ontology
	 */
	public static HashSet<OWLNamedIndividual> freeIndividuals = null; //non-static individuals
	
	/**
	 * Information about all component individuals
	 * > allIndividuals - list of all individuals
	 * > individualsClassSet - number of the individual's class set
	 * > classSets - list of class sets
	 * > individualsByClassSet - individuals ordered by class set (each individual entry is unique)
	 * Is used by RestrictionAnalyzer.java to restrict individual targets and generate solutions
	 */
	public static HashMap<Integer, Set<OWLClass>> classSets = null;//Class sets
	public static HashMap<OWLNamedIndividual, Integer> individualsClassSet = null;//all Component subclasses of an individual
	public static List<ArrayList<OWLNamedIndividual>> individualsByClassSet = null;//individuals ordered by class set
	public static Set<OWLNamedIndividual> allIndividuals = null;//Subset of previous map
	
	/**
	 * Restrictions of each Characteristic
	 * Is used by MasterOntology.java to validate the model and by RestrictionAnalyzer.java to test solutions
	 */
	public static HashMap<OWLClass,HashSet<OWLClassExpression>> chrRestrictions = null; //Restrictions of each Characteristic
	
	
	public static boolean CreateCache(String folder, PelletReasoner reasoner){
		File RpClsFile = new File(folder + CacheFileName); //generated file object (absolute path)
		
		if(freeIndividuals==null){System.out.println(local_log + "Internal Error: extraIndividuals is null"); return false;}

		FileOutputStream fout=null;
    	ObjectOutputStream oos=null;
    	try{
    		PrepareIndividualsClasses(reasoner);
    		PrepareCharacteristicRestrictions(reasoner);
    		fout = new FileOutputStream(RpClsFile);
        	oos = new ObjectOutputStream(fout);
        	oos.writeObject(freeIndividuals); 
        	oos.writeObject(classSets); oos.writeObject(individualsClassSet);
        	oos.writeObject(individualsByClassSet); oos.writeObject(chrRestrictions);
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
	
	private static void PrepareCharacteristicRestrictions(PelletReasoner reasoner){ 
		OWLOntology ontology = reasoner.getRootOntology();
		chrRestrictions.put(Ontologies.OWLC_DefaultCh, null);
		
		for(Entry<OWLClass, HashSet<OWLClassExpression>> ch : chrRestrictions.entrySet()){
			
			//get all restriction for the given characteristic class
			HashSet<OWLClassExpression> restrictions = new HashSet<OWLClassExpression>();
			
			Set<OWLClass> clss = reasoner.getSubClasses(ch.getKey(), false).getFlattened();
			clss.add(ch.getKey());
			
			clss.forEach(c -> restrictions.addAll(Ontologies.GetAnonymousSuperClasses(ontology, c)));
			ch.setValue(restrictions); //map restrictions to current Characteristic
		}
	    
	}
	
	private static void PrepareIndividualsClasses(PelletReasoner reasoner){
		
		int setCount = -1;
		HashMap<Set<OWLClass>, Integer> foundClassSets = new HashMap<Set<OWLClass>, Integer>();
		classSets = new HashMap<Integer, Set<OWLClass>>();
		
		//individuals ordered by class set
		individualsByClassSet = new ArrayList<ArrayList<OWLNamedIndividual>>(); 
		
		//individualsClasses already contains static individuals, add the rest
		freeIndividuals.forEach(f -> individualsClassSet.put(f, null)); //Populating 2/2 (first in Ontologies.java)
		
		//get all allowed classes (subclasses of Component)
		Set<OWLClass> allowed = reasoner.getSubClasses(Ontologies.OWLC_Component, false).getFlattened();
		
		//get all classes of individual and intersect with allowed group
		for(Entry<OWLNamedIndividual, Integer> entry: individualsClassSet.entrySet()){
			Set<OWLClass> clss = reasoner.getTypes(entry.getKey(), false).getFlattened();
			clss.retainAll(allowed);
			Integer setNo = foundClassSets.get(clss);
			if(setNo == null) { //Set doesn't exist, store it and get its index
				foundClassSets.put(clss, ++setCount);
				classSets.put(setCount, clss);
				setNo = setCount;
			}
			entry.setValue(setNo); //map the set index to the individual
			
			//Populate individuals by class set
			if(individualsByClassSet.size()==setNo) individualsByClassSet.add(new ArrayList<OWLNamedIndividual>());
			individualsByClassSet.get(setNo).add(entry.getKey());
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public static boolean LoadCache(String folder){
		final String local_log = MasterCache.local_log + "[loadReportClasses] "; 
		
		File RpClsFile = new File(folder + CacheFileName); //generated file object (absolute path)
		if(!RpClsFile.exists()){ 
			System.out.println(local_log + "Error: File could not be loaded: " + RpClsFile.getAbsolutePath()); return false;}
		   	
    	ObjectInputStream objectinputstream = null;
    	try {
    		FileInputStream streamIn = new FileInputStream(RpClsFile);
    	    objectinputstream = new ObjectInputStream(streamIn);

	    	freeIndividuals = (HashSet<OWLNamedIndividual>) objectinputstream.readObject();
	    	classSets =       (HashMap<Integer, Set<OWLClass>>) objectinputstream.readObject();
	    	individualsClassSet=(HashMap<OWLNamedIndividual, Integer>) objectinputstream.readObject();
	    	allIndividuals = individualsClassSet.keySet();
	    	individualsByClassSet=(List<ArrayList<OWLNamedIndividual>>) objectinputstream.readObject();
	    	chrRestrictions = (HashMap<OWLClass,HashSet<OWLClassExpression>>) objectinputstream.readObject(); 

	    	objectinputstream .close();
    	} catch (Exception e) {
    	    e.printStackTrace();
    	    if(objectinputstream != null){
    	        try {objectinputstream .close();} catch (IOException e1) {e1.printStackTrace();}
    	    } 
    	    return false;
    	}
    	return true;
	}
	
	
	
}