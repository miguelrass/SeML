package org.rass.ontologies;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Set;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;

/**
 * HDD Cache Class
 * @author Miguel
 *
 */
public class MasterCache {
	
	private static final String local_log = "MasterCache Log: ";
	
	public static Set<OWLClass> errorClasses =   null;
	public static Set<OWLClass> warningClasses = null;
	public static Set<OWLClass> infoClasses = null;
	private static String RpClsFileName = "/RpCls.cache";
	
	public static void cacheReportClasses(String folder, PelletReasoner reasoner){
		File RpClsFile = new File(folder + RpClsFileName); //generated file object (absolute path)
		
    	errorClasses =   reasoner.getSubClasses(OWLManager.getOWLDataFactory().getOWLClass(IRI.create(Ontologies.OWL_Error)),false).getFlattened();
    	warningClasses = reasoner.getSubClasses(OWLManager.getOWLDataFactory().getOWLClass(IRI.create(Ontologies.OWL_Warning)),false).getFlattened();
    	infoClasses =    reasoner.getSubClasses(OWLManager.getOWLDataFactory().getOWLClass(IRI.create(Ontologies.OWL_Information)),false).getFlattened();
    	
    	FileOutputStream fout=null;
    	ObjectOutputStream oos=null;
    	try{
    		fout = new FileOutputStream(RpClsFile);
        	oos = new ObjectOutputStream(fout);
        	oos.writeObject(errorClasses); oos.writeObject(warningClasses); oos.writeObject(infoClasses);
    	} catch (Exception ex) {
    	    ex.printStackTrace();
    	} finally {
    	    if(oos  != null){
    	        try {oos.close();} catch (IOException e) {e.printStackTrace();}
    	    } 
    	}
	}
	
	@SuppressWarnings("unchecked")
	public static void loadReportClasses(String folder){
		final String local_log = MasterCache.local_log + "[loadReportClasses] "; 
		
		File RpClsFile = new File(folder + RpClsFileName); //generated file object (absolute path)
		if(!RpClsFile.exists()){ 
			System.out.println(local_log + "Error: File could not be loaded: " + RpClsFile.getAbsolutePath()); return;}
		   	
    	ObjectInputStream objectinputstream = null;
    	try {
    		FileInputStream streamIn = new FileInputStream(RpClsFile);
    	    objectinputstream = new ObjectInputStream(streamIn);

	    	errorClasses =   (Set<OWLClass>) objectinputstream.readObject();
	    	warningClasses = (Set<OWLClass>) objectinputstream.readObject();
	    	infoClasses =    (Set<OWLClass>) objectinputstream.readObject();

    	} catch (Exception e) {
    	    e.printStackTrace();
    	} finally {
    	    if(objectinputstream != null){
    	        try {objectinputstream .close();} catch (IOException e) {e.printStackTrace();}
    	    } 
    	}
	}
	
	
	
}