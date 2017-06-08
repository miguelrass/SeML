package org.rass.restrictions;

import java.util.ArrayList;
import java.util.List;

import org.rass.ontologies.MasterOntology;
import org.rass.ontologies.Ontologies;
import org.semanticweb.owlapi.model.OWLClass;

public class Problem {
	
	public enum TypeE{
		SOLVED, SOLVABLE, UNSOLVABLE, OrPROBLEM, CHAR_EXCESS, CHAR_DEFECT, REPORT, UPPER_PROBLEM
	}
	public enum LevelE{
		INFO, WARNING, ERROR
	}
	public TypeE type;
	public String problemLabel;
	public OWLClass restrictor; //Class which applied the restriction
	public LevelE level = LevelE.ERROR;
	
	public String GetLabel(){
		//Report or Upper Problem:
		if(type == TypeE.REPORT || type == TypeE.UPPER_PROBLEM) return problemLabel;
		
		//Characteristic problem: 
		//Gets Alias of characteristic 
		if(type == TypeE.CHAR_EXCESS || type == TypeE.CHAR_DEFECT)
			return "Characteristic: " + MasterOntology.cachedIRIs.get(restrictor.getIRI().toString()) + "\n" + problemLabel;
				
		//Individual problem:
		//Gets short IRI to show names of classes, no need to generate an Alias for these
		//Unlike characteristics, classes are not referred in the DSL
		String s = "Source: " + Ontologies.GetShortIRI(restrictor.getIRI());
		return s + "\n" + problemLabel;
	}
	
	//========================================================= Report
	
	public Problem(String pL, LevelE lev){
		type = TypeE.REPORT;
		level = lev;
		problemLabel = pL;
	}
	
	//========================================================= Characteristic problem
	
	//Excess Problems are not caught by the reasoner because Characteristics have no individuals
	//Excess Problems have high priority and interrupt the validation
	//Defect Problems are low priority

	Problem(String pL, boolean isExcess){
		problemLabel = pL;
		if(isExcess) type = TypeE.CHAR_EXCESS;
		else type = TypeE.CHAR_DEFECT;
	}
	
	//========================================================= Individual problem
	
	/**
	 * Problem Types:
	 * 0 Solutions:  "UNSOLVABLE"
	 * 1 Solution:   "SOLVED"
	 * >1 Solutions: "SOLVABLE"
	 * n Solutions but can't be fixed automatically: "OrPROBLEM"
	 * 
	 * Solutions: (Individual 1 Alias, Object Property Alias, Individual 2,3,4,5,.. Alias)
	 * 1st {{"i1", "obj", "i2", "i3", "i4", "i5"},
	 * 2nd  {"i1", "obj", "i2", "i5", "i6", "i7"},
	 * 3rd  {"i1", "obj", "i2", "i7", "i8", "i9"}
	 * ...
	 * nth  {"i1", "x"+"y"} For assignments (
	 */
	public ArrayList<List<String>> solutions = new ArrayList<List<String>>(); 

	public Problem(String pL){ 
		problemLabel = pL;
		type = TypeE.UNSOLVABLE;
	}
	
	Problem(String pL, List<String> solution){
		problemLabel = pL;
		type = TypeE.SOLVED;
		solutions.add(solution);
	}
	
	Problem(){
		type = TypeE.UNSOLVABLE;
	}

	void AddSolution(List<String> solution){
		if(solutions.isEmpty()) type = TypeE.SOLVED;
		else type = TypeE.SOLVABLE;
		solutions.add(solution);
	}
	
	//========================================================= Upper Problem
	
	public Problem(boolean dummy, String pL){ 
		problemLabel = pL;
		type = TypeE.UPPER_PROBLEM;
	}
}
