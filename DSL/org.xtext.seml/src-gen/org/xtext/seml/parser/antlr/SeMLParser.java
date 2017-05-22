/*
 * generated by Xtext 2.11.0
 */
package org.xtext.seml.parser.antlr;

import com.google.inject.Inject;
import org.eclipse.xtext.parser.antlr.AbstractAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.xtext.seml.parser.antlr.internal.InternalSeMLParser;
import org.xtext.seml.services.SeMLGrammarAccess;

public class SeMLParser extends AbstractAntlrParser {

	@Inject
	private SeMLGrammarAccess grammarAccess;

	@Override
	protected void setInitialHiddenTokens(XtextTokenStream tokenStream) {
		tokenStream.setInitialHiddenTokens("RULE_WS", "RULE_ML_COMMENT", "RULE_SL_COMMENT");
	}
	

	@Override
	protected InternalSeMLParser createParser(XtextTokenStream stream) {
		return new InternalSeMLParser(stream, getGrammarAccess());
	}

	@Override 
	protected String getDefaultRuleName() {
		return "Model";
	}

	public SeMLGrammarAccess getGrammarAccess() {
		return this.grammarAccess;
	}

	public void setGrammarAccess(SeMLGrammarAccess grammarAccess) {
		this.grammarAccess = grammarAccess;
	}
}
