/*
 * generated by Xtext 2.11.0
 */
package org.xtext.seml.formatting2

import com.google.inject.Inject
import org.eclipse.xtext.formatting2.AbstractFormatter2
import org.eclipse.xtext.formatting2.IFormattableDocument
import org.xtext.seml.seML.Import
import org.xtext.seml.seML.MainModel
import org.xtext.seml.seML.Sentence
import org.xtext.seml.services.SeMLGrammarAccess
import org.xtext.seml.seML.SeMLPackage
import org.xtext.seml.seML.Relation

class SeMLFormatter extends AbstractFormatter2 {
	
	//@Inject extension SeMLGrammarAccess
	@Inject SeMLGrammarAccess g

	def dispatch void format(MainModel mainModel, extension IFormattableDocument document) {

		mainModel.regionFor.keywords(",").forEach[k| k.prepend[noSpace].append[oneSpace]];
		mainModel.regionFor.keyword("use").prepend[newLine];
		
		for (Import _import : mainModel.getImports()) {
			//_import.format;
			_import.prepend[newLine];
		}
		for (Sentence sentence : mainModel.getSentences()) {
			//sentence.format;
			sentence.prepend[newLine];
			sentence.regionFor.keywords(",").forEach[k| k.prepend[noSpace].append[oneSpace]];

			//println(textRegionAccess.toString)
			//if(sentence instanceof Individual) sentence.regionFor.assignment(g.individualAccess.nameAssignment_4).prepend[oneSpace];
		}
	}
	
	//1st EObj or textRegionAccess.regionForEObject(EObj) or regionForRootEObject
	//(2nd) regionFor[para local EObj], allRegionsFor[EObj & children]
	//(3rd) keyword("")/(baKeyword_2_1), feature, assignment, rule call
	//4th append, prepend, surround (a+p), interior
	//5th autowrap, setpriority, indent, newLine, setNewLines(x), noSpace, oneSpace, setSpace(" ")

	/*def dispatch void format(ImportModel importModel, extension IFormattableDocument document) {
		for (Component component : importModel.getComponents()) {
			component.format;
		}
		for (Characteristic characteristic : importModel.getCharacteristics()) {
			characteristic.format;
		}
		for (ObjectProperty objectProperty : importModel.getObjectProperties()) {
			objectProperty.format;
		}
		for (MetaIndividual metaIndividual : importModel.getMetaIndividuals()) {
			metaIndividual.format;
		}
	}*/

}
