package org.xtext.seml.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.validation.ValidationMessageAcceptor;

public class FeaturePlace {
	
	public EObject eo;
	public EStructuralFeature sf;
	public int index; 
	
	FeaturePlace(EObject eo, EStructuralFeature sf, int index){
		this.eo = eo;
		this.sf = sf;
		this.index = index;		
	}
	
	FeaturePlace(EObject eo, EStructuralFeature sf){
		this.eo = eo;
		this.sf = sf;
		this.index = ValidationMessageAcceptor.INSIGNIFICANT_INDEX;		
	}
	
}
