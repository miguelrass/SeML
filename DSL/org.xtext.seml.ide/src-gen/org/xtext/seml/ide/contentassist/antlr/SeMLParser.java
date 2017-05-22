/*
 * generated by Xtext 2.11.0
 */
package org.xtext.seml.ide.contentassist.antlr;

import com.google.inject.Inject;
import java.util.HashMap;
import java.util.Map;
import org.eclipse.xtext.AbstractElement;
import org.eclipse.xtext.ide.editor.contentassist.antlr.AbstractContentAssistParser;
import org.xtext.seml.ide.contentassist.antlr.internal.InternalSeMLParser;
import org.xtext.seml.services.SeMLGrammarAccess;

public class SeMLParser extends AbstractContentAssistParser {

	@Inject
	private SeMLGrammarAccess grammarAccess;

	private Map<AbstractElement, String> nameMappings;

	@Override
	protected InternalSeMLParser createParser() {
		InternalSeMLParser result = new InternalSeMLParser(null);
		result.setGrammarAccess(grammarAccess);
		return result;
	}

	@Override
	protected String getRuleName(AbstractElement element) {
		if (nameMappings == null) {
			nameMappings = new HashMap<AbstractElement, String>() {
				private static final long serialVersionUID = 1L;
				{
					put(grammarAccess.getModelAccess().getAlternatives(), "rule__Model__Alternatives");
					put(grammarAccess.getSentenceAccess().getAlternatives(), "rule__Sentence__Alternatives");
					put(grammarAccess.getValueAccess().getAlternatives(), "rule__Value__Alternatives");
					put(grammarAccess.getIndividualAccess().getAlternatives(), "rule__Individual__Alternatives");
					put(grammarAccess.getMainModelAccess().getGroup(), "rule__MainModel__Group__0");
					put(grammarAccess.getMainModelAccess().getGroup_1(), "rule__MainModel__Group_1__0");
					put(grammarAccess.getMainModelAccess().getGroup_1_2(), "rule__MainModel__Group_1_2__0");
					put(grammarAccess.getImportModelAccess().getGroup(), "rule__ImportModel__Group__0");
					put(grammarAccess.getImportAccess().getGroup(), "rule__Import__Group__0");
					put(grammarAccess.getRelationAccess().getGroup(), "rule__Relation__Group__0");
					put(grammarAccess.getRelationAccess().getGroup_3(), "rule__Relation__Group_3__0");
					put(grammarAccess.getAssignmentAccess().getGroup(), "rule__Assignment__Group__0");
					put(grammarAccess.getValueAccess().getGroup_0(), "rule__Value__Group_0__0");
					put(grammarAccess.getValueAccess().getGroup_1(), "rule__Value__Group_1__0");
					put(grammarAccess.getValueAccess().getGroup_2(), "rule__Value__Group_2__0");
					put(grammarAccess.getValueAccess().getGroup_3(), "rule__Value__Group_3__0");
					put(grammarAccess.getFreeIndividualAccess().getGroup(), "rule__FreeIndividual__Group__0");
					put(grammarAccess.getObjectPropertyAccess().getGroup(), "rule__ObjectProperty__Group__0");
					put(grammarAccess.getCharacteristicAccess().getGroup(), "rule__Characteristic__Group__0");
					put(grammarAccess.getStaticIndividualAccess().getGroup(), "rule__StaticIndividual__Group__0");
					put(grammarAccess.getMainModelAccess().getImportsAssignment_0(), "rule__MainModel__ImportsAssignment_0");
					put(grammarAccess.getMainModelAccess().getUseChAssignment_1_1(), "rule__MainModel__UseChAssignment_1_1");
					put(grammarAccess.getMainModelAccess().getUseChAssignment_1_2_1(), "rule__MainModel__UseChAssignment_1_2_1");
					put(grammarAccess.getMainModelAccess().getSentencesAssignment_2(), "rule__MainModel__SentencesAssignment_2");
					put(grammarAccess.getImportModelAccess().getStaticIndividualsAssignment_0(), "rule__ImportModel__StaticIndividualsAssignment_0");
					put(grammarAccess.getImportModelAccess().getIndividualOptionsAssignment_1(), "rule__ImportModel__IndividualOptionsAssignment_1");
					put(grammarAccess.getImportModelAccess().getCharacteristicsAssignment_2(), "rule__ImportModel__CharacteristicsAssignment_2");
					put(grammarAccess.getImportModelAccess().getObjectPropertiesAssignment_3(), "rule__ImportModel__ObjectPropertiesAssignment_3");
					put(grammarAccess.getImportAccess().getNameAssignment_1(), "rule__Import__NameAssignment_1");
					put(grammarAccess.getRelationAccess().getInd1Assignment_0(), "rule__Relation__Ind1Assignment_0");
					put(grammarAccess.getRelationAccess().getObjAssignment_1(), "rule__Relation__ObjAssignment_1");
					put(grammarAccess.getRelationAccess().getInd2Assignment_2(), "rule__Relation__Ind2Assignment_2");
					put(grammarAccess.getRelationAccess().getInd2Assignment_3_1(), "rule__Relation__Ind2Assignment_3_1");
					put(grammarAccess.getAssignmentAccess().getIndAssignment_0(), "rule__Assignment__IndAssignment_0");
					put(grammarAccess.getAssignmentAccess().getLiteralAssignment_2(), "rule__Assignment__LiteralAssignment_2");
					put(grammarAccess.getValueAccess().getValAssignment_0_1(), "rule__Value__ValAssignment_0_1");
					put(grammarAccess.getValueAccess().getValAssignment_1_1(), "rule__Value__ValAssignment_1_1");
					put(grammarAccess.getValueAccess().getValAssignment_2_1(), "rule__Value__ValAssignment_2_1");
					put(grammarAccess.getValueAccess().getValAssignment_3_1(), "rule__Value__ValAssignment_3_1");
					put(grammarAccess.getFreeIndividualAccess().getNameAssignment_1(), "rule__FreeIndividual__NameAssignment_1");
					put(grammarAccess.getFreeIndividualAccess().getIriAssignment_2(), "rule__FreeIndividual__IriAssignment_2");
					put(grammarAccess.getObjectPropertyAccess().getNameAssignment_1(), "rule__ObjectProperty__NameAssignment_1");
					put(grammarAccess.getObjectPropertyAccess().getIriAssignment_2(), "rule__ObjectProperty__IriAssignment_2");
					put(grammarAccess.getCharacteristicAccess().getNameAssignment_1(), "rule__Characteristic__NameAssignment_1");
					put(grammarAccess.getCharacteristicAccess().getIriAssignment_2(), "rule__Characteristic__IriAssignment_2");
					put(grammarAccess.getStaticIndividualAccess().getNameAssignment_1(), "rule__StaticIndividual__NameAssignment_1");
					put(grammarAccess.getStaticIndividualAccess().getIriAssignment_2(), "rule__StaticIndividual__IriAssignment_2");
				}
			};
		}
		return nameMappings.get(element);
	}
			
	@Override
	protected String[] getInitialHiddenTokens() {
		return new String[] { "RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT" };
	}

	public SeMLGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(SeMLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
