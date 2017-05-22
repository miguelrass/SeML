package org.xtext.seml.ide.contentassist.antlr.internal;

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.DFA;
import org.xtext.seml.services.SeMLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSeMLParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_ALIAS", "RULE_STRING", "RULE_FLOAT", "RULE_BOOL", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'use'", "','", "'import'", "'='", "'FreeIndividual'", "'ObjectProperty'", "'Characteristic'", "'StaticIndividual'"
    };
    public static final int RULE_STRING=5;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int RULE_ALIAS=4;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_WS=11;
    public static final int RULE_ANY_OTHER=12;
    public static final int RULE_BOOL=7;
    public static final int RULE_INT=8;
    public static final int RULE_ML_COMMENT=9;
    public static final int RULE_FLOAT=6;
    public static final int T__20=20;

    // delegates
    // delegators


        public InternalSeMLParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalSeMLParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalSeMLParser.tokenNames; }
    public String getGrammarFileName() { return "InternalSeML.g"; }


    	private SeMLGrammarAccess grammarAccess;

    	public void setGrammarAccess(SeMLGrammarAccess grammarAccess) {
    		this.grammarAccess = grammarAccess;
    	}

    	@Override
    	protected Grammar getGrammar() {
    		return grammarAccess.getGrammar();
    	}

    	@Override
    	protected String getValueForTokenName(String tokenName) {
    		return tokenName;
    	}



    // $ANTLR start "entryRuleModel"
    // InternalSeML.g:53:1: entryRuleModel : ruleModel EOF ;
    public final void entryRuleModel() throws RecognitionException {
        try {
            // InternalSeML.g:54:1: ( ruleModel EOF )
            // InternalSeML.g:55:1: ruleModel EOF
            {
             before(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            ruleModel();

            state._fsp--;

             after(grammarAccess.getModelRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalSeML.g:62:1: ruleModel : ( ( rule__Model__Alternatives ) ) ;
    public final void ruleModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:66:2: ( ( ( rule__Model__Alternatives ) ) )
            // InternalSeML.g:67:2: ( ( rule__Model__Alternatives ) )
            {
            // InternalSeML.g:67:2: ( ( rule__Model__Alternatives ) )
            // InternalSeML.g:68:3: ( rule__Model__Alternatives )
            {
             before(grammarAccess.getModelAccess().getAlternatives()); 
            // InternalSeML.g:69:3: ( rule__Model__Alternatives )
            // InternalSeML.g:69:4: rule__Model__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Model__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getModelAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleMainModel"
    // InternalSeML.g:78:1: entryRuleMainModel : ruleMainModel EOF ;
    public final void entryRuleMainModel() throws RecognitionException {
        try {
            // InternalSeML.g:79:1: ( ruleMainModel EOF )
            // InternalSeML.g:80:1: ruleMainModel EOF
            {
             before(grammarAccess.getMainModelRule()); 
            pushFollow(FOLLOW_1);
            ruleMainModel();

            state._fsp--;

             after(grammarAccess.getMainModelRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMainModel"


    // $ANTLR start "ruleMainModel"
    // InternalSeML.g:87:1: ruleMainModel : ( ( rule__MainModel__Group__0 ) ) ;
    public final void ruleMainModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:91:2: ( ( ( rule__MainModel__Group__0 ) ) )
            // InternalSeML.g:92:2: ( ( rule__MainModel__Group__0 ) )
            {
            // InternalSeML.g:92:2: ( ( rule__MainModel__Group__0 ) )
            // InternalSeML.g:93:3: ( rule__MainModel__Group__0 )
            {
             before(grammarAccess.getMainModelAccess().getGroup()); 
            // InternalSeML.g:94:3: ( rule__MainModel__Group__0 )
            // InternalSeML.g:94:4: rule__MainModel__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__MainModel__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMainModelAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMainModel"


    // $ANTLR start "entryRuleImportModel"
    // InternalSeML.g:103:1: entryRuleImportModel : ruleImportModel EOF ;
    public final void entryRuleImportModel() throws RecognitionException {
        try {
            // InternalSeML.g:104:1: ( ruleImportModel EOF )
            // InternalSeML.g:105:1: ruleImportModel EOF
            {
             before(grammarAccess.getImportModelRule()); 
            pushFollow(FOLLOW_1);
            ruleImportModel();

            state._fsp--;

             after(grammarAccess.getImportModelRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleImportModel"


    // $ANTLR start "ruleImportModel"
    // InternalSeML.g:112:1: ruleImportModel : ( ( rule__ImportModel__Group__0 ) ) ;
    public final void ruleImportModel() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:116:2: ( ( ( rule__ImportModel__Group__0 ) ) )
            // InternalSeML.g:117:2: ( ( rule__ImportModel__Group__0 ) )
            {
            // InternalSeML.g:117:2: ( ( rule__ImportModel__Group__0 ) )
            // InternalSeML.g:118:3: ( rule__ImportModel__Group__0 )
            {
             before(grammarAccess.getImportModelAccess().getGroup()); 
            // InternalSeML.g:119:3: ( rule__ImportModel__Group__0 )
            // InternalSeML.g:119:4: rule__ImportModel__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ImportModel__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getImportModelAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleImportModel"


    // $ANTLR start "entryRuleImport"
    // InternalSeML.g:128:1: entryRuleImport : ruleImport EOF ;
    public final void entryRuleImport() throws RecognitionException {
        try {
            // InternalSeML.g:129:1: ( ruleImport EOF )
            // InternalSeML.g:130:1: ruleImport EOF
            {
             before(grammarAccess.getImportRule()); 
            pushFollow(FOLLOW_1);
            ruleImport();

            state._fsp--;

             after(grammarAccess.getImportRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleImport"


    // $ANTLR start "ruleImport"
    // InternalSeML.g:137:1: ruleImport : ( ( rule__Import__Group__0 ) ) ;
    public final void ruleImport() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:141:2: ( ( ( rule__Import__Group__0 ) ) )
            // InternalSeML.g:142:2: ( ( rule__Import__Group__0 ) )
            {
            // InternalSeML.g:142:2: ( ( rule__Import__Group__0 ) )
            // InternalSeML.g:143:3: ( rule__Import__Group__0 )
            {
             before(grammarAccess.getImportAccess().getGroup()); 
            // InternalSeML.g:144:3: ( rule__Import__Group__0 )
            // InternalSeML.g:144:4: rule__Import__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Import__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getImportAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleImport"


    // $ANTLR start "entryRuleSentence"
    // InternalSeML.g:153:1: entryRuleSentence : ruleSentence EOF ;
    public final void entryRuleSentence() throws RecognitionException {
        try {
            // InternalSeML.g:154:1: ( ruleSentence EOF )
            // InternalSeML.g:155:1: ruleSentence EOF
            {
             before(grammarAccess.getSentenceRule()); 
            pushFollow(FOLLOW_1);
            ruleSentence();

            state._fsp--;

             after(grammarAccess.getSentenceRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleSentence"


    // $ANTLR start "ruleSentence"
    // InternalSeML.g:162:1: ruleSentence : ( ( rule__Sentence__Alternatives ) ) ;
    public final void ruleSentence() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:166:2: ( ( ( rule__Sentence__Alternatives ) ) )
            // InternalSeML.g:167:2: ( ( rule__Sentence__Alternatives ) )
            {
            // InternalSeML.g:167:2: ( ( rule__Sentence__Alternatives ) )
            // InternalSeML.g:168:3: ( rule__Sentence__Alternatives )
            {
             before(grammarAccess.getSentenceAccess().getAlternatives()); 
            // InternalSeML.g:169:3: ( rule__Sentence__Alternatives )
            // InternalSeML.g:169:4: rule__Sentence__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Sentence__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getSentenceAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleSentence"


    // $ANTLR start "entryRuleRelation"
    // InternalSeML.g:178:1: entryRuleRelation : ruleRelation EOF ;
    public final void entryRuleRelation() throws RecognitionException {
        try {
            // InternalSeML.g:179:1: ( ruleRelation EOF )
            // InternalSeML.g:180:1: ruleRelation EOF
            {
             before(grammarAccess.getRelationRule()); 
            pushFollow(FOLLOW_1);
            ruleRelation();

            state._fsp--;

             after(grammarAccess.getRelationRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRelation"


    // $ANTLR start "ruleRelation"
    // InternalSeML.g:187:1: ruleRelation : ( ( rule__Relation__Group__0 ) ) ;
    public final void ruleRelation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:191:2: ( ( ( rule__Relation__Group__0 ) ) )
            // InternalSeML.g:192:2: ( ( rule__Relation__Group__0 ) )
            {
            // InternalSeML.g:192:2: ( ( rule__Relation__Group__0 ) )
            // InternalSeML.g:193:3: ( rule__Relation__Group__0 )
            {
             before(grammarAccess.getRelationAccess().getGroup()); 
            // InternalSeML.g:194:3: ( rule__Relation__Group__0 )
            // InternalSeML.g:194:4: rule__Relation__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Relation__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRelationAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRelation"


    // $ANTLR start "entryRuleAssignment"
    // InternalSeML.g:203:1: entryRuleAssignment : ruleAssignment EOF ;
    public final void entryRuleAssignment() throws RecognitionException {
        try {
            // InternalSeML.g:204:1: ( ruleAssignment EOF )
            // InternalSeML.g:205:1: ruleAssignment EOF
            {
             before(grammarAccess.getAssignmentRule()); 
            pushFollow(FOLLOW_1);
            ruleAssignment();

            state._fsp--;

             after(grammarAccess.getAssignmentRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleAssignment"


    // $ANTLR start "ruleAssignment"
    // InternalSeML.g:212:1: ruleAssignment : ( ( rule__Assignment__Group__0 ) ) ;
    public final void ruleAssignment() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:216:2: ( ( ( rule__Assignment__Group__0 ) ) )
            // InternalSeML.g:217:2: ( ( rule__Assignment__Group__0 ) )
            {
            // InternalSeML.g:217:2: ( ( rule__Assignment__Group__0 ) )
            // InternalSeML.g:218:3: ( rule__Assignment__Group__0 )
            {
             before(grammarAccess.getAssignmentAccess().getGroup()); 
            // InternalSeML.g:219:3: ( rule__Assignment__Group__0 )
            // InternalSeML.g:219:4: rule__Assignment__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleAssignment"


    // $ANTLR start "entryRuleValue"
    // InternalSeML.g:228:1: entryRuleValue : ruleValue EOF ;
    public final void entryRuleValue() throws RecognitionException {
        try {
            // InternalSeML.g:229:1: ( ruleValue EOF )
            // InternalSeML.g:230:1: ruleValue EOF
            {
             before(grammarAccess.getValueRule()); 
            pushFollow(FOLLOW_1);
            ruleValue();

            state._fsp--;

             after(grammarAccess.getValueRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // InternalSeML.g:237:1: ruleValue : ( ( rule__Value__Alternatives ) ) ;
    public final void ruleValue() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:241:2: ( ( ( rule__Value__Alternatives ) ) )
            // InternalSeML.g:242:2: ( ( rule__Value__Alternatives ) )
            {
            // InternalSeML.g:242:2: ( ( rule__Value__Alternatives ) )
            // InternalSeML.g:243:3: ( rule__Value__Alternatives )
            {
             before(grammarAccess.getValueAccess().getAlternatives()); 
            // InternalSeML.g:244:3: ( rule__Value__Alternatives )
            // InternalSeML.g:244:4: rule__Value__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Value__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getValueAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleValue"


    // $ANTLR start "entryRuleFreeIndividual"
    // InternalSeML.g:253:1: entryRuleFreeIndividual : ruleFreeIndividual EOF ;
    public final void entryRuleFreeIndividual() throws RecognitionException {
        try {
            // InternalSeML.g:254:1: ( ruleFreeIndividual EOF )
            // InternalSeML.g:255:1: ruleFreeIndividual EOF
            {
             before(grammarAccess.getFreeIndividualRule()); 
            pushFollow(FOLLOW_1);
            ruleFreeIndividual();

            state._fsp--;

             after(grammarAccess.getFreeIndividualRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFreeIndividual"


    // $ANTLR start "ruleFreeIndividual"
    // InternalSeML.g:262:1: ruleFreeIndividual : ( ( rule__FreeIndividual__Group__0 ) ) ;
    public final void ruleFreeIndividual() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:266:2: ( ( ( rule__FreeIndividual__Group__0 ) ) )
            // InternalSeML.g:267:2: ( ( rule__FreeIndividual__Group__0 ) )
            {
            // InternalSeML.g:267:2: ( ( rule__FreeIndividual__Group__0 ) )
            // InternalSeML.g:268:3: ( rule__FreeIndividual__Group__0 )
            {
             before(grammarAccess.getFreeIndividualAccess().getGroup()); 
            // InternalSeML.g:269:3: ( rule__FreeIndividual__Group__0 )
            // InternalSeML.g:269:4: rule__FreeIndividual__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__FreeIndividual__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFreeIndividualAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFreeIndividual"


    // $ANTLR start "entryRuleObjectProperty"
    // InternalSeML.g:278:1: entryRuleObjectProperty : ruleObjectProperty EOF ;
    public final void entryRuleObjectProperty() throws RecognitionException {
        try {
            // InternalSeML.g:279:1: ( ruleObjectProperty EOF )
            // InternalSeML.g:280:1: ruleObjectProperty EOF
            {
             before(grammarAccess.getObjectPropertyRule()); 
            pushFollow(FOLLOW_1);
            ruleObjectProperty();

            state._fsp--;

             after(grammarAccess.getObjectPropertyRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleObjectProperty"


    // $ANTLR start "ruleObjectProperty"
    // InternalSeML.g:287:1: ruleObjectProperty : ( ( rule__ObjectProperty__Group__0 ) ) ;
    public final void ruleObjectProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:291:2: ( ( ( rule__ObjectProperty__Group__0 ) ) )
            // InternalSeML.g:292:2: ( ( rule__ObjectProperty__Group__0 ) )
            {
            // InternalSeML.g:292:2: ( ( rule__ObjectProperty__Group__0 ) )
            // InternalSeML.g:293:3: ( rule__ObjectProperty__Group__0 )
            {
             before(grammarAccess.getObjectPropertyAccess().getGroup()); 
            // InternalSeML.g:294:3: ( rule__ObjectProperty__Group__0 )
            // InternalSeML.g:294:4: rule__ObjectProperty__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__ObjectProperty__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getObjectPropertyAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleObjectProperty"


    // $ANTLR start "entryRuleCharacteristic"
    // InternalSeML.g:303:1: entryRuleCharacteristic : ruleCharacteristic EOF ;
    public final void entryRuleCharacteristic() throws RecognitionException {
        try {
            // InternalSeML.g:304:1: ( ruleCharacteristic EOF )
            // InternalSeML.g:305:1: ruleCharacteristic EOF
            {
             before(grammarAccess.getCharacteristicRule()); 
            pushFollow(FOLLOW_1);
            ruleCharacteristic();

            state._fsp--;

             after(grammarAccess.getCharacteristicRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleCharacteristic"


    // $ANTLR start "ruleCharacteristic"
    // InternalSeML.g:312:1: ruleCharacteristic : ( ( rule__Characteristic__Group__0 ) ) ;
    public final void ruleCharacteristic() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:316:2: ( ( ( rule__Characteristic__Group__0 ) ) )
            // InternalSeML.g:317:2: ( ( rule__Characteristic__Group__0 ) )
            {
            // InternalSeML.g:317:2: ( ( rule__Characteristic__Group__0 ) )
            // InternalSeML.g:318:3: ( rule__Characteristic__Group__0 )
            {
             before(grammarAccess.getCharacteristicAccess().getGroup()); 
            // InternalSeML.g:319:3: ( rule__Characteristic__Group__0 )
            // InternalSeML.g:319:4: rule__Characteristic__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Characteristic__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getCharacteristicAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleCharacteristic"


    // $ANTLR start "entryRuleStaticIndividual"
    // InternalSeML.g:328:1: entryRuleStaticIndividual : ruleStaticIndividual EOF ;
    public final void entryRuleStaticIndividual() throws RecognitionException {
        try {
            // InternalSeML.g:329:1: ( ruleStaticIndividual EOF )
            // InternalSeML.g:330:1: ruleStaticIndividual EOF
            {
             before(grammarAccess.getStaticIndividualRule()); 
            pushFollow(FOLLOW_1);
            ruleStaticIndividual();

            state._fsp--;

             after(grammarAccess.getStaticIndividualRule()); 
            match(input,EOF,FOLLOW_2); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleStaticIndividual"


    // $ANTLR start "ruleStaticIndividual"
    // InternalSeML.g:337:1: ruleStaticIndividual : ( ( rule__StaticIndividual__Group__0 ) ) ;
    public final void ruleStaticIndividual() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:341:2: ( ( ( rule__StaticIndividual__Group__0 ) ) )
            // InternalSeML.g:342:2: ( ( rule__StaticIndividual__Group__0 ) )
            {
            // InternalSeML.g:342:2: ( ( rule__StaticIndividual__Group__0 ) )
            // InternalSeML.g:343:3: ( rule__StaticIndividual__Group__0 )
            {
             before(grammarAccess.getStaticIndividualAccess().getGroup()); 
            // InternalSeML.g:344:3: ( rule__StaticIndividual__Group__0 )
            // InternalSeML.g:344:4: rule__StaticIndividual__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__StaticIndividual__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getStaticIndividualAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleStaticIndividual"


    // $ANTLR start "rule__Model__Alternatives"
    // InternalSeML.g:352:1: rule__Model__Alternatives : ( ( ruleMainModel ) | ( ruleImportModel ) );
    public final void rule__Model__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:356:1: ( ( ruleMainModel ) | ( ruleImportModel ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==15) ) {
                alt1=1;
            }
            else if ( (LA1_0==20) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalSeML.g:357:2: ( ruleMainModel )
                    {
                    // InternalSeML.g:357:2: ( ruleMainModel )
                    // InternalSeML.g:358:3: ruleMainModel
                    {
                     before(grammarAccess.getModelAccess().getMainModelParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleMainModel();

                    state._fsp--;

                     after(grammarAccess.getModelAccess().getMainModelParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalSeML.g:363:2: ( ruleImportModel )
                    {
                    // InternalSeML.g:363:2: ( ruleImportModel )
                    // InternalSeML.g:364:3: ruleImportModel
                    {
                     before(grammarAccess.getModelAccess().getImportModelParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleImportModel();

                    state._fsp--;

                     after(grammarAccess.getModelAccess().getImportModelParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Model__Alternatives"


    // $ANTLR start "rule__Sentence__Alternatives"
    // InternalSeML.g:373:1: rule__Sentence__Alternatives : ( ( ruleRelation ) | ( ruleAssignment ) );
    public final void rule__Sentence__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:377:1: ( ( ruleRelation ) | ( ruleAssignment ) )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0==RULE_ALIAS) ) {
                int LA2_1 = input.LA(2);

                if ( (LA2_1==RULE_ALIAS) ) {
                    alt2=1;
                }
                else if ( (LA2_1==16) ) {
                    alt2=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 2, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalSeML.g:378:2: ( ruleRelation )
                    {
                    // InternalSeML.g:378:2: ( ruleRelation )
                    // InternalSeML.g:379:3: ruleRelation
                    {
                     before(grammarAccess.getSentenceAccess().getRelationParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleRelation();

                    state._fsp--;

                     after(grammarAccess.getSentenceAccess().getRelationParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalSeML.g:384:2: ( ruleAssignment )
                    {
                    // InternalSeML.g:384:2: ( ruleAssignment )
                    // InternalSeML.g:385:3: ruleAssignment
                    {
                     before(grammarAccess.getSentenceAccess().getAssignmentParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleAssignment();

                    state._fsp--;

                     after(grammarAccess.getSentenceAccess().getAssignmentParserRuleCall_1()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Sentence__Alternatives"


    // $ANTLR start "rule__Value__Alternatives"
    // InternalSeML.g:394:1: rule__Value__Alternatives : ( ( ( rule__Value__Group_0__0 ) ) | ( ( rule__Value__Group_1__0 ) ) | ( ( rule__Value__Group_2__0 ) ) | ( ( rule__Value__Group_3__0 ) ) );
    public final void rule__Value__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:398:1: ( ( ( rule__Value__Group_0__0 ) ) | ( ( rule__Value__Group_1__0 ) ) | ( ( rule__Value__Group_2__0 ) ) | ( ( rule__Value__Group_3__0 ) ) )
            int alt3=4;
            switch ( input.LA(1) ) {
            case RULE_FLOAT:
                {
                alt3=1;
                }
                break;
            case RULE_BOOL:
                {
                alt3=2;
                }
                break;
            case RULE_INT:
                {
                alt3=3;
                }
                break;
            case RULE_STRING:
                {
                alt3=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }

            switch (alt3) {
                case 1 :
                    // InternalSeML.g:399:2: ( ( rule__Value__Group_0__0 ) )
                    {
                    // InternalSeML.g:399:2: ( ( rule__Value__Group_0__0 ) )
                    // InternalSeML.g:400:3: ( rule__Value__Group_0__0 )
                    {
                     before(grammarAccess.getValueAccess().getGroup_0()); 
                    // InternalSeML.g:401:3: ( rule__Value__Group_0__0 )
                    // InternalSeML.g:401:4: rule__Value__Group_0__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Value__Group_0__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getValueAccess().getGroup_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalSeML.g:405:2: ( ( rule__Value__Group_1__0 ) )
                    {
                    // InternalSeML.g:405:2: ( ( rule__Value__Group_1__0 ) )
                    // InternalSeML.g:406:3: ( rule__Value__Group_1__0 )
                    {
                     before(grammarAccess.getValueAccess().getGroup_1()); 
                    // InternalSeML.g:407:3: ( rule__Value__Group_1__0 )
                    // InternalSeML.g:407:4: rule__Value__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Value__Group_1__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getValueAccess().getGroup_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalSeML.g:411:2: ( ( rule__Value__Group_2__0 ) )
                    {
                    // InternalSeML.g:411:2: ( ( rule__Value__Group_2__0 ) )
                    // InternalSeML.g:412:3: ( rule__Value__Group_2__0 )
                    {
                     before(grammarAccess.getValueAccess().getGroup_2()); 
                    // InternalSeML.g:413:3: ( rule__Value__Group_2__0 )
                    // InternalSeML.g:413:4: rule__Value__Group_2__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Value__Group_2__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getValueAccess().getGroup_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalSeML.g:417:2: ( ( rule__Value__Group_3__0 ) )
                    {
                    // InternalSeML.g:417:2: ( ( rule__Value__Group_3__0 ) )
                    // InternalSeML.g:418:3: ( rule__Value__Group_3__0 )
                    {
                     before(grammarAccess.getValueAccess().getGroup_3()); 
                    // InternalSeML.g:419:3: ( rule__Value__Group_3__0 )
                    // InternalSeML.g:419:4: rule__Value__Group_3__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__Value__Group_3__0();

                    state._fsp--;


                    }

                     after(grammarAccess.getValueAccess().getGroup_3()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Alternatives"


    // $ANTLR start "rule__MainModel__Group__0"
    // InternalSeML.g:427:1: rule__MainModel__Group__0 : rule__MainModel__Group__0__Impl rule__MainModel__Group__1 ;
    public final void rule__MainModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:431:1: ( rule__MainModel__Group__0__Impl rule__MainModel__Group__1 )
            // InternalSeML.g:432:2: rule__MainModel__Group__0__Impl rule__MainModel__Group__1
            {
            pushFollow(FOLLOW_3);
            rule__MainModel__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MainModel__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group__0"


    // $ANTLR start "rule__MainModel__Group__0__Impl"
    // InternalSeML.g:439:1: rule__MainModel__Group__0__Impl : ( ( ( rule__MainModel__ImportsAssignment_0 ) ) ( ( rule__MainModel__ImportsAssignment_0 )* ) ) ;
    public final void rule__MainModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:443:1: ( ( ( ( rule__MainModel__ImportsAssignment_0 ) ) ( ( rule__MainModel__ImportsAssignment_0 )* ) ) )
            // InternalSeML.g:444:1: ( ( ( rule__MainModel__ImportsAssignment_0 ) ) ( ( rule__MainModel__ImportsAssignment_0 )* ) )
            {
            // InternalSeML.g:444:1: ( ( ( rule__MainModel__ImportsAssignment_0 ) ) ( ( rule__MainModel__ImportsAssignment_0 )* ) )
            // InternalSeML.g:445:2: ( ( rule__MainModel__ImportsAssignment_0 ) ) ( ( rule__MainModel__ImportsAssignment_0 )* )
            {
            // InternalSeML.g:445:2: ( ( rule__MainModel__ImportsAssignment_0 ) )
            // InternalSeML.g:446:3: ( rule__MainModel__ImportsAssignment_0 )
            {
             before(grammarAccess.getMainModelAccess().getImportsAssignment_0()); 
            // InternalSeML.g:447:3: ( rule__MainModel__ImportsAssignment_0 )
            // InternalSeML.g:447:4: rule__MainModel__ImportsAssignment_0
            {
            pushFollow(FOLLOW_4);
            rule__MainModel__ImportsAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getMainModelAccess().getImportsAssignment_0()); 

            }

            // InternalSeML.g:450:2: ( ( rule__MainModel__ImportsAssignment_0 )* )
            // InternalSeML.g:451:3: ( rule__MainModel__ImportsAssignment_0 )*
            {
             before(grammarAccess.getMainModelAccess().getImportsAssignment_0()); 
            // InternalSeML.g:452:3: ( rule__MainModel__ImportsAssignment_0 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==15) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalSeML.g:452:4: rule__MainModel__ImportsAssignment_0
            	    {
            	    pushFollow(FOLLOW_4);
            	    rule__MainModel__ImportsAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

             after(grammarAccess.getMainModelAccess().getImportsAssignment_0()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group__0__Impl"


    // $ANTLR start "rule__MainModel__Group__1"
    // InternalSeML.g:461:1: rule__MainModel__Group__1 : rule__MainModel__Group__1__Impl rule__MainModel__Group__2 ;
    public final void rule__MainModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:465:1: ( rule__MainModel__Group__1__Impl rule__MainModel__Group__2 )
            // InternalSeML.g:466:2: rule__MainModel__Group__1__Impl rule__MainModel__Group__2
            {
            pushFollow(FOLLOW_3);
            rule__MainModel__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MainModel__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group__1"


    // $ANTLR start "rule__MainModel__Group__1__Impl"
    // InternalSeML.g:473:1: rule__MainModel__Group__1__Impl : ( ( rule__MainModel__Group_1__0 )? ) ;
    public final void rule__MainModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:477:1: ( ( ( rule__MainModel__Group_1__0 )? ) )
            // InternalSeML.g:478:1: ( ( rule__MainModel__Group_1__0 )? )
            {
            // InternalSeML.g:478:1: ( ( rule__MainModel__Group_1__0 )? )
            // InternalSeML.g:479:2: ( rule__MainModel__Group_1__0 )?
            {
             before(grammarAccess.getMainModelAccess().getGroup_1()); 
            // InternalSeML.g:480:2: ( rule__MainModel__Group_1__0 )?
            int alt5=2;
            int LA5_0 = input.LA(1);

            if ( (LA5_0==13) ) {
                alt5=1;
            }
            switch (alt5) {
                case 1 :
                    // InternalSeML.g:480:3: rule__MainModel__Group_1__0
                    {
                    pushFollow(FOLLOW_2);
                    rule__MainModel__Group_1__0();

                    state._fsp--;


                    }
                    break;

            }

             after(grammarAccess.getMainModelAccess().getGroup_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group__1__Impl"


    // $ANTLR start "rule__MainModel__Group__2"
    // InternalSeML.g:488:1: rule__MainModel__Group__2 : rule__MainModel__Group__2__Impl ;
    public final void rule__MainModel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:492:1: ( rule__MainModel__Group__2__Impl )
            // InternalSeML.g:493:2: rule__MainModel__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MainModel__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group__2"


    // $ANTLR start "rule__MainModel__Group__2__Impl"
    // InternalSeML.g:499:1: rule__MainModel__Group__2__Impl : ( ( rule__MainModel__SentencesAssignment_2 )* ) ;
    public final void rule__MainModel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:503:1: ( ( ( rule__MainModel__SentencesAssignment_2 )* ) )
            // InternalSeML.g:504:1: ( ( rule__MainModel__SentencesAssignment_2 )* )
            {
            // InternalSeML.g:504:1: ( ( rule__MainModel__SentencesAssignment_2 )* )
            // InternalSeML.g:505:2: ( rule__MainModel__SentencesAssignment_2 )*
            {
             before(grammarAccess.getMainModelAccess().getSentencesAssignment_2()); 
            // InternalSeML.g:506:2: ( rule__MainModel__SentencesAssignment_2 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==RULE_ALIAS) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalSeML.g:506:3: rule__MainModel__SentencesAssignment_2
            	    {
            	    pushFollow(FOLLOW_5);
            	    rule__MainModel__SentencesAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

             after(grammarAccess.getMainModelAccess().getSentencesAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group__2__Impl"


    // $ANTLR start "rule__MainModel__Group_1__0"
    // InternalSeML.g:515:1: rule__MainModel__Group_1__0 : rule__MainModel__Group_1__0__Impl rule__MainModel__Group_1__1 ;
    public final void rule__MainModel__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:519:1: ( rule__MainModel__Group_1__0__Impl rule__MainModel__Group_1__1 )
            // InternalSeML.g:520:2: rule__MainModel__Group_1__0__Impl rule__MainModel__Group_1__1
            {
            pushFollow(FOLLOW_6);
            rule__MainModel__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MainModel__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group_1__0"


    // $ANTLR start "rule__MainModel__Group_1__0__Impl"
    // InternalSeML.g:527:1: rule__MainModel__Group_1__0__Impl : ( 'use' ) ;
    public final void rule__MainModel__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:531:1: ( ( 'use' ) )
            // InternalSeML.g:532:1: ( 'use' )
            {
            // InternalSeML.g:532:1: ( 'use' )
            // InternalSeML.g:533:2: 'use'
            {
             before(grammarAccess.getMainModelAccess().getUseKeyword_1_0()); 
            match(input,13,FOLLOW_2); 
             after(grammarAccess.getMainModelAccess().getUseKeyword_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group_1__0__Impl"


    // $ANTLR start "rule__MainModel__Group_1__1"
    // InternalSeML.g:542:1: rule__MainModel__Group_1__1 : rule__MainModel__Group_1__1__Impl rule__MainModel__Group_1__2 ;
    public final void rule__MainModel__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:546:1: ( rule__MainModel__Group_1__1__Impl rule__MainModel__Group_1__2 )
            // InternalSeML.g:547:2: rule__MainModel__Group_1__1__Impl rule__MainModel__Group_1__2
            {
            pushFollow(FOLLOW_7);
            rule__MainModel__Group_1__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MainModel__Group_1__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group_1__1"


    // $ANTLR start "rule__MainModel__Group_1__1__Impl"
    // InternalSeML.g:554:1: rule__MainModel__Group_1__1__Impl : ( ( rule__MainModel__UseChAssignment_1_1 ) ) ;
    public final void rule__MainModel__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:558:1: ( ( ( rule__MainModel__UseChAssignment_1_1 ) ) )
            // InternalSeML.g:559:1: ( ( rule__MainModel__UseChAssignment_1_1 ) )
            {
            // InternalSeML.g:559:1: ( ( rule__MainModel__UseChAssignment_1_1 ) )
            // InternalSeML.g:560:2: ( rule__MainModel__UseChAssignment_1_1 )
            {
             before(grammarAccess.getMainModelAccess().getUseChAssignment_1_1()); 
            // InternalSeML.g:561:2: ( rule__MainModel__UseChAssignment_1_1 )
            // InternalSeML.g:561:3: rule__MainModel__UseChAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__MainModel__UseChAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getMainModelAccess().getUseChAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group_1__1__Impl"


    // $ANTLR start "rule__MainModel__Group_1__2"
    // InternalSeML.g:569:1: rule__MainModel__Group_1__2 : rule__MainModel__Group_1__2__Impl ;
    public final void rule__MainModel__Group_1__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:573:1: ( rule__MainModel__Group_1__2__Impl )
            // InternalSeML.g:574:2: rule__MainModel__Group_1__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MainModel__Group_1__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group_1__2"


    // $ANTLR start "rule__MainModel__Group_1__2__Impl"
    // InternalSeML.g:580:1: rule__MainModel__Group_1__2__Impl : ( ( rule__MainModel__Group_1_2__0 )* ) ;
    public final void rule__MainModel__Group_1__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:584:1: ( ( ( rule__MainModel__Group_1_2__0 )* ) )
            // InternalSeML.g:585:1: ( ( rule__MainModel__Group_1_2__0 )* )
            {
            // InternalSeML.g:585:1: ( ( rule__MainModel__Group_1_2__0 )* )
            // InternalSeML.g:586:2: ( rule__MainModel__Group_1_2__0 )*
            {
             before(grammarAccess.getMainModelAccess().getGroup_1_2()); 
            // InternalSeML.g:587:2: ( rule__MainModel__Group_1_2__0 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==14) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalSeML.g:587:3: rule__MainModel__Group_1_2__0
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__MainModel__Group_1_2__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getMainModelAccess().getGroup_1_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group_1__2__Impl"


    // $ANTLR start "rule__MainModel__Group_1_2__0"
    // InternalSeML.g:596:1: rule__MainModel__Group_1_2__0 : rule__MainModel__Group_1_2__0__Impl rule__MainModel__Group_1_2__1 ;
    public final void rule__MainModel__Group_1_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:600:1: ( rule__MainModel__Group_1_2__0__Impl rule__MainModel__Group_1_2__1 )
            // InternalSeML.g:601:2: rule__MainModel__Group_1_2__0__Impl rule__MainModel__Group_1_2__1
            {
            pushFollow(FOLLOW_6);
            rule__MainModel__Group_1_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MainModel__Group_1_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group_1_2__0"


    // $ANTLR start "rule__MainModel__Group_1_2__0__Impl"
    // InternalSeML.g:608:1: rule__MainModel__Group_1_2__0__Impl : ( ',' ) ;
    public final void rule__MainModel__Group_1_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:612:1: ( ( ',' ) )
            // InternalSeML.g:613:1: ( ',' )
            {
            // InternalSeML.g:613:1: ( ',' )
            // InternalSeML.g:614:2: ','
            {
             before(grammarAccess.getMainModelAccess().getCommaKeyword_1_2_0()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getMainModelAccess().getCommaKeyword_1_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group_1_2__0__Impl"


    // $ANTLR start "rule__MainModel__Group_1_2__1"
    // InternalSeML.g:623:1: rule__MainModel__Group_1_2__1 : rule__MainModel__Group_1_2__1__Impl ;
    public final void rule__MainModel__Group_1_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:627:1: ( rule__MainModel__Group_1_2__1__Impl )
            // InternalSeML.g:628:2: rule__MainModel__Group_1_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MainModel__Group_1_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group_1_2__1"


    // $ANTLR start "rule__MainModel__Group_1_2__1__Impl"
    // InternalSeML.g:634:1: rule__MainModel__Group_1_2__1__Impl : ( ( rule__MainModel__UseChAssignment_1_2_1 ) ) ;
    public final void rule__MainModel__Group_1_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:638:1: ( ( ( rule__MainModel__UseChAssignment_1_2_1 ) ) )
            // InternalSeML.g:639:1: ( ( rule__MainModel__UseChAssignment_1_2_1 ) )
            {
            // InternalSeML.g:639:1: ( ( rule__MainModel__UseChAssignment_1_2_1 ) )
            // InternalSeML.g:640:2: ( rule__MainModel__UseChAssignment_1_2_1 )
            {
             before(grammarAccess.getMainModelAccess().getUseChAssignment_1_2_1()); 
            // InternalSeML.g:641:2: ( rule__MainModel__UseChAssignment_1_2_1 )
            // InternalSeML.g:641:3: rule__MainModel__UseChAssignment_1_2_1
            {
            pushFollow(FOLLOW_2);
            rule__MainModel__UseChAssignment_1_2_1();

            state._fsp--;


            }

             after(grammarAccess.getMainModelAccess().getUseChAssignment_1_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__Group_1_2__1__Impl"


    // $ANTLR start "rule__ImportModel__Group__0"
    // InternalSeML.g:650:1: rule__ImportModel__Group__0 : rule__ImportModel__Group__0__Impl rule__ImportModel__Group__1 ;
    public final void rule__ImportModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:654:1: ( rule__ImportModel__Group__0__Impl rule__ImportModel__Group__1 )
            // InternalSeML.g:655:2: rule__ImportModel__Group__0__Impl rule__ImportModel__Group__1
            {
            pushFollow(FOLLOW_9);
            rule__ImportModel__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportModel__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportModel__Group__0"


    // $ANTLR start "rule__ImportModel__Group__0__Impl"
    // InternalSeML.g:662:1: rule__ImportModel__Group__0__Impl : ( ( ( rule__ImportModel__StaticIndividualsAssignment_0 ) ) ( ( rule__ImportModel__StaticIndividualsAssignment_0 )* ) ) ;
    public final void rule__ImportModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:666:1: ( ( ( ( rule__ImportModel__StaticIndividualsAssignment_0 ) ) ( ( rule__ImportModel__StaticIndividualsAssignment_0 )* ) ) )
            // InternalSeML.g:667:1: ( ( ( rule__ImportModel__StaticIndividualsAssignment_0 ) ) ( ( rule__ImportModel__StaticIndividualsAssignment_0 )* ) )
            {
            // InternalSeML.g:667:1: ( ( ( rule__ImportModel__StaticIndividualsAssignment_0 ) ) ( ( rule__ImportModel__StaticIndividualsAssignment_0 )* ) )
            // InternalSeML.g:668:2: ( ( rule__ImportModel__StaticIndividualsAssignment_0 ) ) ( ( rule__ImportModel__StaticIndividualsAssignment_0 )* )
            {
            // InternalSeML.g:668:2: ( ( rule__ImportModel__StaticIndividualsAssignment_0 ) )
            // InternalSeML.g:669:3: ( rule__ImportModel__StaticIndividualsAssignment_0 )
            {
             before(grammarAccess.getImportModelAccess().getStaticIndividualsAssignment_0()); 
            // InternalSeML.g:670:3: ( rule__ImportModel__StaticIndividualsAssignment_0 )
            // InternalSeML.g:670:4: rule__ImportModel__StaticIndividualsAssignment_0
            {
            pushFollow(FOLLOW_10);
            rule__ImportModel__StaticIndividualsAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getImportModelAccess().getStaticIndividualsAssignment_0()); 

            }

            // InternalSeML.g:673:2: ( ( rule__ImportModel__StaticIndividualsAssignment_0 )* )
            // InternalSeML.g:674:3: ( rule__ImportModel__StaticIndividualsAssignment_0 )*
            {
             before(grammarAccess.getImportModelAccess().getStaticIndividualsAssignment_0()); 
            // InternalSeML.g:675:3: ( rule__ImportModel__StaticIndividualsAssignment_0 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==20) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalSeML.g:675:4: rule__ImportModel__StaticIndividualsAssignment_0
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ImportModel__StaticIndividualsAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

             after(grammarAccess.getImportModelAccess().getStaticIndividualsAssignment_0()); 

            }


            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportModel__Group__0__Impl"


    // $ANTLR start "rule__ImportModel__Group__1"
    // InternalSeML.g:684:1: rule__ImportModel__Group__1 : rule__ImportModel__Group__1__Impl rule__ImportModel__Group__2 ;
    public final void rule__ImportModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:688:1: ( rule__ImportModel__Group__1__Impl rule__ImportModel__Group__2 )
            // InternalSeML.g:689:2: rule__ImportModel__Group__1__Impl rule__ImportModel__Group__2
            {
            pushFollow(FOLLOW_9);
            rule__ImportModel__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportModel__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportModel__Group__1"


    // $ANTLR start "rule__ImportModel__Group__1__Impl"
    // InternalSeML.g:696:1: rule__ImportModel__Group__1__Impl : ( ( rule__ImportModel__IndividualOptionsAssignment_1 )* ) ;
    public final void rule__ImportModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:700:1: ( ( ( rule__ImportModel__IndividualOptionsAssignment_1 )* ) )
            // InternalSeML.g:701:1: ( ( rule__ImportModel__IndividualOptionsAssignment_1 )* )
            {
            // InternalSeML.g:701:1: ( ( rule__ImportModel__IndividualOptionsAssignment_1 )* )
            // InternalSeML.g:702:2: ( rule__ImportModel__IndividualOptionsAssignment_1 )*
            {
             before(grammarAccess.getImportModelAccess().getIndividualOptionsAssignment_1()); 
            // InternalSeML.g:703:2: ( rule__ImportModel__IndividualOptionsAssignment_1 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==17) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalSeML.g:703:3: rule__ImportModel__IndividualOptionsAssignment_1
            	    {
            	    pushFollow(FOLLOW_11);
            	    rule__ImportModel__IndividualOptionsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

             after(grammarAccess.getImportModelAccess().getIndividualOptionsAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportModel__Group__1__Impl"


    // $ANTLR start "rule__ImportModel__Group__2"
    // InternalSeML.g:711:1: rule__ImportModel__Group__2 : rule__ImportModel__Group__2__Impl rule__ImportModel__Group__3 ;
    public final void rule__ImportModel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:715:1: ( rule__ImportModel__Group__2__Impl rule__ImportModel__Group__3 )
            // InternalSeML.g:716:2: rule__ImportModel__Group__2__Impl rule__ImportModel__Group__3
            {
            pushFollow(FOLLOW_9);
            rule__ImportModel__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ImportModel__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportModel__Group__2"


    // $ANTLR start "rule__ImportModel__Group__2__Impl"
    // InternalSeML.g:723:1: rule__ImportModel__Group__2__Impl : ( ( rule__ImportModel__CharacteristicsAssignment_2 )* ) ;
    public final void rule__ImportModel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:727:1: ( ( ( rule__ImportModel__CharacteristicsAssignment_2 )* ) )
            // InternalSeML.g:728:1: ( ( rule__ImportModel__CharacteristicsAssignment_2 )* )
            {
            // InternalSeML.g:728:1: ( ( rule__ImportModel__CharacteristicsAssignment_2 )* )
            // InternalSeML.g:729:2: ( rule__ImportModel__CharacteristicsAssignment_2 )*
            {
             before(grammarAccess.getImportModelAccess().getCharacteristicsAssignment_2()); 
            // InternalSeML.g:730:2: ( rule__ImportModel__CharacteristicsAssignment_2 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==19) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalSeML.g:730:3: rule__ImportModel__CharacteristicsAssignment_2
            	    {
            	    pushFollow(FOLLOW_12);
            	    rule__ImportModel__CharacteristicsAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getImportModelAccess().getCharacteristicsAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportModel__Group__2__Impl"


    // $ANTLR start "rule__ImportModel__Group__3"
    // InternalSeML.g:738:1: rule__ImportModel__Group__3 : rule__ImportModel__Group__3__Impl ;
    public final void rule__ImportModel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:742:1: ( rule__ImportModel__Group__3__Impl )
            // InternalSeML.g:743:2: rule__ImportModel__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ImportModel__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportModel__Group__3"


    // $ANTLR start "rule__ImportModel__Group__3__Impl"
    // InternalSeML.g:749:1: rule__ImportModel__Group__3__Impl : ( ( rule__ImportModel__ObjectPropertiesAssignment_3 )* ) ;
    public final void rule__ImportModel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:753:1: ( ( ( rule__ImportModel__ObjectPropertiesAssignment_3 )* ) )
            // InternalSeML.g:754:1: ( ( rule__ImportModel__ObjectPropertiesAssignment_3 )* )
            {
            // InternalSeML.g:754:1: ( ( rule__ImportModel__ObjectPropertiesAssignment_3 )* )
            // InternalSeML.g:755:2: ( rule__ImportModel__ObjectPropertiesAssignment_3 )*
            {
             before(grammarAccess.getImportModelAccess().getObjectPropertiesAssignment_3()); 
            // InternalSeML.g:756:2: ( rule__ImportModel__ObjectPropertiesAssignment_3 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==18) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalSeML.g:756:3: rule__ImportModel__ObjectPropertiesAssignment_3
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__ImportModel__ObjectPropertiesAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getImportModelAccess().getObjectPropertiesAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportModel__Group__3__Impl"


    // $ANTLR start "rule__Import__Group__0"
    // InternalSeML.g:765:1: rule__Import__Group__0 : rule__Import__Group__0__Impl rule__Import__Group__1 ;
    public final void rule__Import__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:769:1: ( rule__Import__Group__0__Impl rule__Import__Group__1 )
            // InternalSeML.g:770:2: rule__Import__Group__0__Impl rule__Import__Group__1
            {
            pushFollow(FOLLOW_14);
            rule__Import__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Import__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__Group__0"


    // $ANTLR start "rule__Import__Group__0__Impl"
    // InternalSeML.g:777:1: rule__Import__Group__0__Impl : ( 'import' ) ;
    public final void rule__Import__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:781:1: ( ( 'import' ) )
            // InternalSeML.g:782:1: ( 'import' )
            {
            // InternalSeML.g:782:1: ( 'import' )
            // InternalSeML.g:783:2: 'import'
            {
             before(grammarAccess.getImportAccess().getImportKeyword_0()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getImportAccess().getImportKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__Group__0__Impl"


    // $ANTLR start "rule__Import__Group__1"
    // InternalSeML.g:792:1: rule__Import__Group__1 : rule__Import__Group__1__Impl ;
    public final void rule__Import__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:796:1: ( rule__Import__Group__1__Impl )
            // InternalSeML.g:797:2: rule__Import__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Import__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__Group__1"


    // $ANTLR start "rule__Import__Group__1__Impl"
    // InternalSeML.g:803:1: rule__Import__Group__1__Impl : ( ( rule__Import__NameAssignment_1 ) ) ;
    public final void rule__Import__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:807:1: ( ( ( rule__Import__NameAssignment_1 ) ) )
            // InternalSeML.g:808:1: ( ( rule__Import__NameAssignment_1 ) )
            {
            // InternalSeML.g:808:1: ( ( rule__Import__NameAssignment_1 ) )
            // InternalSeML.g:809:2: ( rule__Import__NameAssignment_1 )
            {
             before(grammarAccess.getImportAccess().getNameAssignment_1()); 
            // InternalSeML.g:810:2: ( rule__Import__NameAssignment_1 )
            // InternalSeML.g:810:3: rule__Import__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Import__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getImportAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__Group__1__Impl"


    // $ANTLR start "rule__Relation__Group__0"
    // InternalSeML.g:819:1: rule__Relation__Group__0 : rule__Relation__Group__0__Impl rule__Relation__Group__1 ;
    public final void rule__Relation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:823:1: ( rule__Relation__Group__0__Impl rule__Relation__Group__1 )
            // InternalSeML.g:824:2: rule__Relation__Group__0__Impl rule__Relation__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__Relation__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Relation__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__0"


    // $ANTLR start "rule__Relation__Group__0__Impl"
    // InternalSeML.g:831:1: rule__Relation__Group__0__Impl : ( ( rule__Relation__Ind1Assignment_0 ) ) ;
    public final void rule__Relation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:835:1: ( ( ( rule__Relation__Ind1Assignment_0 ) ) )
            // InternalSeML.g:836:1: ( ( rule__Relation__Ind1Assignment_0 ) )
            {
            // InternalSeML.g:836:1: ( ( rule__Relation__Ind1Assignment_0 ) )
            // InternalSeML.g:837:2: ( rule__Relation__Ind1Assignment_0 )
            {
             before(grammarAccess.getRelationAccess().getInd1Assignment_0()); 
            // InternalSeML.g:838:2: ( rule__Relation__Ind1Assignment_0 )
            // InternalSeML.g:838:3: rule__Relation__Ind1Assignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Relation__Ind1Assignment_0();

            state._fsp--;


            }

             after(grammarAccess.getRelationAccess().getInd1Assignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__0__Impl"


    // $ANTLR start "rule__Relation__Group__1"
    // InternalSeML.g:846:1: rule__Relation__Group__1 : rule__Relation__Group__1__Impl rule__Relation__Group__2 ;
    public final void rule__Relation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:850:1: ( rule__Relation__Group__1__Impl rule__Relation__Group__2 )
            // InternalSeML.g:851:2: rule__Relation__Group__1__Impl rule__Relation__Group__2
            {
            pushFollow(FOLLOW_6);
            rule__Relation__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Relation__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__1"


    // $ANTLR start "rule__Relation__Group__1__Impl"
    // InternalSeML.g:858:1: rule__Relation__Group__1__Impl : ( ( rule__Relation__ObjAssignment_1 ) ) ;
    public final void rule__Relation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:862:1: ( ( ( rule__Relation__ObjAssignment_1 ) ) )
            // InternalSeML.g:863:1: ( ( rule__Relation__ObjAssignment_1 ) )
            {
            // InternalSeML.g:863:1: ( ( rule__Relation__ObjAssignment_1 ) )
            // InternalSeML.g:864:2: ( rule__Relation__ObjAssignment_1 )
            {
             before(grammarAccess.getRelationAccess().getObjAssignment_1()); 
            // InternalSeML.g:865:2: ( rule__Relation__ObjAssignment_1 )
            // InternalSeML.g:865:3: rule__Relation__ObjAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Relation__ObjAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getRelationAccess().getObjAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__1__Impl"


    // $ANTLR start "rule__Relation__Group__2"
    // InternalSeML.g:873:1: rule__Relation__Group__2 : rule__Relation__Group__2__Impl rule__Relation__Group__3 ;
    public final void rule__Relation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:877:1: ( rule__Relation__Group__2__Impl rule__Relation__Group__3 )
            // InternalSeML.g:878:2: rule__Relation__Group__2__Impl rule__Relation__Group__3
            {
            pushFollow(FOLLOW_7);
            rule__Relation__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Relation__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__2"


    // $ANTLR start "rule__Relation__Group__2__Impl"
    // InternalSeML.g:885:1: rule__Relation__Group__2__Impl : ( ( rule__Relation__Ind2Assignment_2 ) ) ;
    public final void rule__Relation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:889:1: ( ( ( rule__Relation__Ind2Assignment_2 ) ) )
            // InternalSeML.g:890:1: ( ( rule__Relation__Ind2Assignment_2 ) )
            {
            // InternalSeML.g:890:1: ( ( rule__Relation__Ind2Assignment_2 ) )
            // InternalSeML.g:891:2: ( rule__Relation__Ind2Assignment_2 )
            {
             before(grammarAccess.getRelationAccess().getInd2Assignment_2()); 
            // InternalSeML.g:892:2: ( rule__Relation__Ind2Assignment_2 )
            // InternalSeML.g:892:3: rule__Relation__Ind2Assignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Relation__Ind2Assignment_2();

            state._fsp--;


            }

             after(grammarAccess.getRelationAccess().getInd2Assignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__2__Impl"


    // $ANTLR start "rule__Relation__Group__3"
    // InternalSeML.g:900:1: rule__Relation__Group__3 : rule__Relation__Group__3__Impl ;
    public final void rule__Relation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:904:1: ( rule__Relation__Group__3__Impl )
            // InternalSeML.g:905:2: rule__Relation__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Relation__Group__3__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__3"


    // $ANTLR start "rule__Relation__Group__3__Impl"
    // InternalSeML.g:911:1: rule__Relation__Group__3__Impl : ( ( rule__Relation__Group_3__0 )* ) ;
    public final void rule__Relation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:915:1: ( ( ( rule__Relation__Group_3__0 )* ) )
            // InternalSeML.g:916:1: ( ( rule__Relation__Group_3__0 )* )
            {
            // InternalSeML.g:916:1: ( ( rule__Relation__Group_3__0 )* )
            // InternalSeML.g:917:2: ( rule__Relation__Group_3__0 )*
            {
             before(grammarAccess.getRelationAccess().getGroup_3()); 
            // InternalSeML.g:918:2: ( rule__Relation__Group_3__0 )*
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( (LA12_0==14) ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalSeML.g:918:3: rule__Relation__Group_3__0
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__Relation__Group_3__0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop12;
                }
            } while (true);

             after(grammarAccess.getRelationAccess().getGroup_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group__3__Impl"


    // $ANTLR start "rule__Relation__Group_3__0"
    // InternalSeML.g:927:1: rule__Relation__Group_3__0 : rule__Relation__Group_3__0__Impl rule__Relation__Group_3__1 ;
    public final void rule__Relation__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:931:1: ( rule__Relation__Group_3__0__Impl rule__Relation__Group_3__1 )
            // InternalSeML.g:932:2: rule__Relation__Group_3__0__Impl rule__Relation__Group_3__1
            {
            pushFollow(FOLLOW_6);
            rule__Relation__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Relation__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group_3__0"


    // $ANTLR start "rule__Relation__Group_3__0__Impl"
    // InternalSeML.g:939:1: rule__Relation__Group_3__0__Impl : ( ',' ) ;
    public final void rule__Relation__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:943:1: ( ( ',' ) )
            // InternalSeML.g:944:1: ( ',' )
            {
            // InternalSeML.g:944:1: ( ',' )
            // InternalSeML.g:945:2: ','
            {
             before(grammarAccess.getRelationAccess().getCommaKeyword_3_0()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getRelationAccess().getCommaKeyword_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group_3__0__Impl"


    // $ANTLR start "rule__Relation__Group_3__1"
    // InternalSeML.g:954:1: rule__Relation__Group_3__1 : rule__Relation__Group_3__1__Impl ;
    public final void rule__Relation__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:958:1: ( rule__Relation__Group_3__1__Impl )
            // InternalSeML.g:959:2: rule__Relation__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Relation__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group_3__1"


    // $ANTLR start "rule__Relation__Group_3__1__Impl"
    // InternalSeML.g:965:1: rule__Relation__Group_3__1__Impl : ( ( rule__Relation__Ind2Assignment_3_1 ) ) ;
    public final void rule__Relation__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:969:1: ( ( ( rule__Relation__Ind2Assignment_3_1 ) ) )
            // InternalSeML.g:970:1: ( ( rule__Relation__Ind2Assignment_3_1 ) )
            {
            // InternalSeML.g:970:1: ( ( rule__Relation__Ind2Assignment_3_1 ) )
            // InternalSeML.g:971:2: ( rule__Relation__Ind2Assignment_3_1 )
            {
             before(grammarAccess.getRelationAccess().getInd2Assignment_3_1()); 
            // InternalSeML.g:972:2: ( rule__Relation__Ind2Assignment_3_1 )
            // InternalSeML.g:972:3: rule__Relation__Ind2Assignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__Relation__Ind2Assignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getRelationAccess().getInd2Assignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Group_3__1__Impl"


    // $ANTLR start "rule__Assignment__Group__0"
    // InternalSeML.g:981:1: rule__Assignment__Group__0 : rule__Assignment__Group__0__Impl rule__Assignment__Group__1 ;
    public final void rule__Assignment__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:985:1: ( rule__Assignment__Group__0__Impl rule__Assignment__Group__1 )
            // InternalSeML.g:986:2: rule__Assignment__Group__0__Impl rule__Assignment__Group__1
            {
            pushFollow(FOLLOW_15);
            rule__Assignment__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Assignment__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__0"


    // $ANTLR start "rule__Assignment__Group__0__Impl"
    // InternalSeML.g:993:1: rule__Assignment__Group__0__Impl : ( ( rule__Assignment__IndAssignment_0 ) ) ;
    public final void rule__Assignment__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:997:1: ( ( ( rule__Assignment__IndAssignment_0 ) ) )
            // InternalSeML.g:998:1: ( ( rule__Assignment__IndAssignment_0 ) )
            {
            // InternalSeML.g:998:1: ( ( rule__Assignment__IndAssignment_0 ) )
            // InternalSeML.g:999:2: ( rule__Assignment__IndAssignment_0 )
            {
             before(grammarAccess.getAssignmentAccess().getIndAssignment_0()); 
            // InternalSeML.g:1000:2: ( rule__Assignment__IndAssignment_0 )
            // InternalSeML.g:1000:3: rule__Assignment__IndAssignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__IndAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentAccess().getIndAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__0__Impl"


    // $ANTLR start "rule__Assignment__Group__1"
    // InternalSeML.g:1008:1: rule__Assignment__Group__1 : rule__Assignment__Group__1__Impl rule__Assignment__Group__2 ;
    public final void rule__Assignment__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1012:1: ( rule__Assignment__Group__1__Impl rule__Assignment__Group__2 )
            // InternalSeML.g:1013:2: rule__Assignment__Group__1__Impl rule__Assignment__Group__2
            {
            pushFollow(FOLLOW_16);
            rule__Assignment__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Assignment__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__1"


    // $ANTLR start "rule__Assignment__Group__1__Impl"
    // InternalSeML.g:1020:1: rule__Assignment__Group__1__Impl : ( '=' ) ;
    public final void rule__Assignment__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1024:1: ( ( '=' ) )
            // InternalSeML.g:1025:1: ( '=' )
            {
            // InternalSeML.g:1025:1: ( '=' )
            // InternalSeML.g:1026:2: '='
            {
             before(grammarAccess.getAssignmentAccess().getEqualsSignKeyword_1()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getAssignmentAccess().getEqualsSignKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__1__Impl"


    // $ANTLR start "rule__Assignment__Group__2"
    // InternalSeML.g:1035:1: rule__Assignment__Group__2 : rule__Assignment__Group__2__Impl ;
    public final void rule__Assignment__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1039:1: ( rule__Assignment__Group__2__Impl )
            // InternalSeML.g:1040:2: rule__Assignment__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__2"


    // $ANTLR start "rule__Assignment__Group__2__Impl"
    // InternalSeML.g:1046:1: rule__Assignment__Group__2__Impl : ( ( rule__Assignment__LiteralAssignment_2 ) ) ;
    public final void rule__Assignment__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1050:1: ( ( ( rule__Assignment__LiteralAssignment_2 ) ) )
            // InternalSeML.g:1051:1: ( ( rule__Assignment__LiteralAssignment_2 ) )
            {
            // InternalSeML.g:1051:1: ( ( rule__Assignment__LiteralAssignment_2 ) )
            // InternalSeML.g:1052:2: ( rule__Assignment__LiteralAssignment_2 )
            {
             before(grammarAccess.getAssignmentAccess().getLiteralAssignment_2()); 
            // InternalSeML.g:1053:2: ( rule__Assignment__LiteralAssignment_2 )
            // InternalSeML.g:1053:3: rule__Assignment__LiteralAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Assignment__LiteralAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getAssignmentAccess().getLiteralAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__Group__2__Impl"


    // $ANTLR start "rule__Value__Group_0__0"
    // InternalSeML.g:1062:1: rule__Value__Group_0__0 : rule__Value__Group_0__0__Impl rule__Value__Group_0__1 ;
    public final void rule__Value__Group_0__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1066:1: ( rule__Value__Group_0__0__Impl rule__Value__Group_0__1 )
            // InternalSeML.g:1067:2: rule__Value__Group_0__0__Impl rule__Value__Group_0__1
            {
            pushFollow(FOLLOW_17);
            rule__Value__Group_0__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Value__Group_0__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_0__0"


    // $ANTLR start "rule__Value__Group_0__0__Impl"
    // InternalSeML.g:1074:1: rule__Value__Group_0__0__Impl : ( () ) ;
    public final void rule__Value__Group_0__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1078:1: ( ( () ) )
            // InternalSeML.g:1079:1: ( () )
            {
            // InternalSeML.g:1079:1: ( () )
            // InternalSeML.g:1080:2: ()
            {
             before(grammarAccess.getValueAccess().getFloatValAction_0_0()); 
            // InternalSeML.g:1081:2: ()
            // InternalSeML.g:1081:3: 
            {
            }

             after(grammarAccess.getValueAccess().getFloatValAction_0_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_0__0__Impl"


    // $ANTLR start "rule__Value__Group_0__1"
    // InternalSeML.g:1089:1: rule__Value__Group_0__1 : rule__Value__Group_0__1__Impl ;
    public final void rule__Value__Group_0__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1093:1: ( rule__Value__Group_0__1__Impl )
            // InternalSeML.g:1094:2: rule__Value__Group_0__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Value__Group_0__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_0__1"


    // $ANTLR start "rule__Value__Group_0__1__Impl"
    // InternalSeML.g:1100:1: rule__Value__Group_0__1__Impl : ( ( rule__Value__ValAssignment_0_1 ) ) ;
    public final void rule__Value__Group_0__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1104:1: ( ( ( rule__Value__ValAssignment_0_1 ) ) )
            // InternalSeML.g:1105:1: ( ( rule__Value__ValAssignment_0_1 ) )
            {
            // InternalSeML.g:1105:1: ( ( rule__Value__ValAssignment_0_1 ) )
            // InternalSeML.g:1106:2: ( rule__Value__ValAssignment_0_1 )
            {
             before(grammarAccess.getValueAccess().getValAssignment_0_1()); 
            // InternalSeML.g:1107:2: ( rule__Value__ValAssignment_0_1 )
            // InternalSeML.g:1107:3: rule__Value__ValAssignment_0_1
            {
            pushFollow(FOLLOW_2);
            rule__Value__ValAssignment_0_1();

            state._fsp--;


            }

             after(grammarAccess.getValueAccess().getValAssignment_0_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_0__1__Impl"


    // $ANTLR start "rule__Value__Group_1__0"
    // InternalSeML.g:1116:1: rule__Value__Group_1__0 : rule__Value__Group_1__0__Impl rule__Value__Group_1__1 ;
    public final void rule__Value__Group_1__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1120:1: ( rule__Value__Group_1__0__Impl rule__Value__Group_1__1 )
            // InternalSeML.g:1121:2: rule__Value__Group_1__0__Impl rule__Value__Group_1__1
            {
            pushFollow(FOLLOW_18);
            rule__Value__Group_1__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Value__Group_1__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_1__0"


    // $ANTLR start "rule__Value__Group_1__0__Impl"
    // InternalSeML.g:1128:1: rule__Value__Group_1__0__Impl : ( () ) ;
    public final void rule__Value__Group_1__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1132:1: ( ( () ) )
            // InternalSeML.g:1133:1: ( () )
            {
            // InternalSeML.g:1133:1: ( () )
            // InternalSeML.g:1134:2: ()
            {
             before(grammarAccess.getValueAccess().getBoolValAction_1_0()); 
            // InternalSeML.g:1135:2: ()
            // InternalSeML.g:1135:3: 
            {
            }

             after(grammarAccess.getValueAccess().getBoolValAction_1_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_1__0__Impl"


    // $ANTLR start "rule__Value__Group_1__1"
    // InternalSeML.g:1143:1: rule__Value__Group_1__1 : rule__Value__Group_1__1__Impl ;
    public final void rule__Value__Group_1__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1147:1: ( rule__Value__Group_1__1__Impl )
            // InternalSeML.g:1148:2: rule__Value__Group_1__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Value__Group_1__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_1__1"


    // $ANTLR start "rule__Value__Group_1__1__Impl"
    // InternalSeML.g:1154:1: rule__Value__Group_1__1__Impl : ( ( rule__Value__ValAssignment_1_1 ) ) ;
    public final void rule__Value__Group_1__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1158:1: ( ( ( rule__Value__ValAssignment_1_1 ) ) )
            // InternalSeML.g:1159:1: ( ( rule__Value__ValAssignment_1_1 ) )
            {
            // InternalSeML.g:1159:1: ( ( rule__Value__ValAssignment_1_1 ) )
            // InternalSeML.g:1160:2: ( rule__Value__ValAssignment_1_1 )
            {
             before(grammarAccess.getValueAccess().getValAssignment_1_1()); 
            // InternalSeML.g:1161:2: ( rule__Value__ValAssignment_1_1 )
            // InternalSeML.g:1161:3: rule__Value__ValAssignment_1_1
            {
            pushFollow(FOLLOW_2);
            rule__Value__ValAssignment_1_1();

            state._fsp--;


            }

             after(grammarAccess.getValueAccess().getValAssignment_1_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_1__1__Impl"


    // $ANTLR start "rule__Value__Group_2__0"
    // InternalSeML.g:1170:1: rule__Value__Group_2__0 : rule__Value__Group_2__0__Impl rule__Value__Group_2__1 ;
    public final void rule__Value__Group_2__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1174:1: ( rule__Value__Group_2__0__Impl rule__Value__Group_2__1 )
            // InternalSeML.g:1175:2: rule__Value__Group_2__0__Impl rule__Value__Group_2__1
            {
            pushFollow(FOLLOW_19);
            rule__Value__Group_2__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Value__Group_2__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_2__0"


    // $ANTLR start "rule__Value__Group_2__0__Impl"
    // InternalSeML.g:1182:1: rule__Value__Group_2__0__Impl : ( () ) ;
    public final void rule__Value__Group_2__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1186:1: ( ( () ) )
            // InternalSeML.g:1187:1: ( () )
            {
            // InternalSeML.g:1187:1: ( () )
            // InternalSeML.g:1188:2: ()
            {
             before(grammarAccess.getValueAccess().getIntValAction_2_0()); 
            // InternalSeML.g:1189:2: ()
            // InternalSeML.g:1189:3: 
            {
            }

             after(grammarAccess.getValueAccess().getIntValAction_2_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_2__0__Impl"


    // $ANTLR start "rule__Value__Group_2__1"
    // InternalSeML.g:1197:1: rule__Value__Group_2__1 : rule__Value__Group_2__1__Impl ;
    public final void rule__Value__Group_2__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1201:1: ( rule__Value__Group_2__1__Impl )
            // InternalSeML.g:1202:2: rule__Value__Group_2__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Value__Group_2__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_2__1"


    // $ANTLR start "rule__Value__Group_2__1__Impl"
    // InternalSeML.g:1208:1: rule__Value__Group_2__1__Impl : ( ( rule__Value__ValAssignment_2_1 ) ) ;
    public final void rule__Value__Group_2__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1212:1: ( ( ( rule__Value__ValAssignment_2_1 ) ) )
            // InternalSeML.g:1213:1: ( ( rule__Value__ValAssignment_2_1 ) )
            {
            // InternalSeML.g:1213:1: ( ( rule__Value__ValAssignment_2_1 ) )
            // InternalSeML.g:1214:2: ( rule__Value__ValAssignment_2_1 )
            {
             before(grammarAccess.getValueAccess().getValAssignment_2_1()); 
            // InternalSeML.g:1215:2: ( rule__Value__ValAssignment_2_1 )
            // InternalSeML.g:1215:3: rule__Value__ValAssignment_2_1
            {
            pushFollow(FOLLOW_2);
            rule__Value__ValAssignment_2_1();

            state._fsp--;


            }

             after(grammarAccess.getValueAccess().getValAssignment_2_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_2__1__Impl"


    // $ANTLR start "rule__Value__Group_3__0"
    // InternalSeML.g:1224:1: rule__Value__Group_3__0 : rule__Value__Group_3__0__Impl rule__Value__Group_3__1 ;
    public final void rule__Value__Group_3__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1228:1: ( rule__Value__Group_3__0__Impl rule__Value__Group_3__1 )
            // InternalSeML.g:1229:2: rule__Value__Group_3__0__Impl rule__Value__Group_3__1
            {
            pushFollow(FOLLOW_16);
            rule__Value__Group_3__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Value__Group_3__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_3__0"


    // $ANTLR start "rule__Value__Group_3__0__Impl"
    // InternalSeML.g:1236:1: rule__Value__Group_3__0__Impl : ( () ) ;
    public final void rule__Value__Group_3__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1240:1: ( ( () ) )
            // InternalSeML.g:1241:1: ( () )
            {
            // InternalSeML.g:1241:1: ( () )
            // InternalSeML.g:1242:2: ()
            {
             before(grammarAccess.getValueAccess().getStringValAction_3_0()); 
            // InternalSeML.g:1243:2: ()
            // InternalSeML.g:1243:3: 
            {
            }

             after(grammarAccess.getValueAccess().getStringValAction_3_0()); 

            }


            }

        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_3__0__Impl"


    // $ANTLR start "rule__Value__Group_3__1"
    // InternalSeML.g:1251:1: rule__Value__Group_3__1 : rule__Value__Group_3__1__Impl ;
    public final void rule__Value__Group_3__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1255:1: ( rule__Value__Group_3__1__Impl )
            // InternalSeML.g:1256:2: rule__Value__Group_3__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Value__Group_3__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_3__1"


    // $ANTLR start "rule__Value__Group_3__1__Impl"
    // InternalSeML.g:1262:1: rule__Value__Group_3__1__Impl : ( ( rule__Value__ValAssignment_3_1 ) ) ;
    public final void rule__Value__Group_3__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1266:1: ( ( ( rule__Value__ValAssignment_3_1 ) ) )
            // InternalSeML.g:1267:1: ( ( rule__Value__ValAssignment_3_1 ) )
            {
            // InternalSeML.g:1267:1: ( ( rule__Value__ValAssignment_3_1 ) )
            // InternalSeML.g:1268:2: ( rule__Value__ValAssignment_3_1 )
            {
             before(grammarAccess.getValueAccess().getValAssignment_3_1()); 
            // InternalSeML.g:1269:2: ( rule__Value__ValAssignment_3_1 )
            // InternalSeML.g:1269:3: rule__Value__ValAssignment_3_1
            {
            pushFollow(FOLLOW_2);
            rule__Value__ValAssignment_3_1();

            state._fsp--;


            }

             after(grammarAccess.getValueAccess().getValAssignment_3_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__Group_3__1__Impl"


    // $ANTLR start "rule__FreeIndividual__Group__0"
    // InternalSeML.g:1278:1: rule__FreeIndividual__Group__0 : rule__FreeIndividual__Group__0__Impl rule__FreeIndividual__Group__1 ;
    public final void rule__FreeIndividual__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1282:1: ( rule__FreeIndividual__Group__0__Impl rule__FreeIndividual__Group__1 )
            // InternalSeML.g:1283:2: rule__FreeIndividual__Group__0__Impl rule__FreeIndividual__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__FreeIndividual__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FreeIndividual__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FreeIndividual__Group__0"


    // $ANTLR start "rule__FreeIndividual__Group__0__Impl"
    // InternalSeML.g:1290:1: rule__FreeIndividual__Group__0__Impl : ( 'FreeIndividual' ) ;
    public final void rule__FreeIndividual__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1294:1: ( ( 'FreeIndividual' ) )
            // InternalSeML.g:1295:1: ( 'FreeIndividual' )
            {
            // InternalSeML.g:1295:1: ( 'FreeIndividual' )
            // InternalSeML.g:1296:2: 'FreeIndividual'
            {
             before(grammarAccess.getFreeIndividualAccess().getFreeIndividualKeyword_0()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getFreeIndividualAccess().getFreeIndividualKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FreeIndividual__Group__0__Impl"


    // $ANTLR start "rule__FreeIndividual__Group__1"
    // InternalSeML.g:1305:1: rule__FreeIndividual__Group__1 : rule__FreeIndividual__Group__1__Impl rule__FreeIndividual__Group__2 ;
    public final void rule__FreeIndividual__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1309:1: ( rule__FreeIndividual__Group__1__Impl rule__FreeIndividual__Group__2 )
            // InternalSeML.g:1310:2: rule__FreeIndividual__Group__1__Impl rule__FreeIndividual__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__FreeIndividual__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__FreeIndividual__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FreeIndividual__Group__1"


    // $ANTLR start "rule__FreeIndividual__Group__1__Impl"
    // InternalSeML.g:1317:1: rule__FreeIndividual__Group__1__Impl : ( ( rule__FreeIndividual__NameAssignment_1 ) ) ;
    public final void rule__FreeIndividual__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1321:1: ( ( ( rule__FreeIndividual__NameAssignment_1 ) ) )
            // InternalSeML.g:1322:1: ( ( rule__FreeIndividual__NameAssignment_1 ) )
            {
            // InternalSeML.g:1322:1: ( ( rule__FreeIndividual__NameAssignment_1 ) )
            // InternalSeML.g:1323:2: ( rule__FreeIndividual__NameAssignment_1 )
            {
             before(grammarAccess.getFreeIndividualAccess().getNameAssignment_1()); 
            // InternalSeML.g:1324:2: ( rule__FreeIndividual__NameAssignment_1 )
            // InternalSeML.g:1324:3: rule__FreeIndividual__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__FreeIndividual__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getFreeIndividualAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FreeIndividual__Group__1__Impl"


    // $ANTLR start "rule__FreeIndividual__Group__2"
    // InternalSeML.g:1332:1: rule__FreeIndividual__Group__2 : rule__FreeIndividual__Group__2__Impl ;
    public final void rule__FreeIndividual__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1336:1: ( rule__FreeIndividual__Group__2__Impl )
            // InternalSeML.g:1337:2: rule__FreeIndividual__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__FreeIndividual__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FreeIndividual__Group__2"


    // $ANTLR start "rule__FreeIndividual__Group__2__Impl"
    // InternalSeML.g:1343:1: rule__FreeIndividual__Group__2__Impl : ( ( rule__FreeIndividual__IriAssignment_2 ) ) ;
    public final void rule__FreeIndividual__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1347:1: ( ( ( rule__FreeIndividual__IriAssignment_2 ) ) )
            // InternalSeML.g:1348:1: ( ( rule__FreeIndividual__IriAssignment_2 ) )
            {
            // InternalSeML.g:1348:1: ( ( rule__FreeIndividual__IriAssignment_2 ) )
            // InternalSeML.g:1349:2: ( rule__FreeIndividual__IriAssignment_2 )
            {
             before(grammarAccess.getFreeIndividualAccess().getIriAssignment_2()); 
            // InternalSeML.g:1350:2: ( rule__FreeIndividual__IriAssignment_2 )
            // InternalSeML.g:1350:3: rule__FreeIndividual__IriAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__FreeIndividual__IriAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getFreeIndividualAccess().getIriAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FreeIndividual__Group__2__Impl"


    // $ANTLR start "rule__ObjectProperty__Group__0"
    // InternalSeML.g:1359:1: rule__ObjectProperty__Group__0 : rule__ObjectProperty__Group__0__Impl rule__ObjectProperty__Group__1 ;
    public final void rule__ObjectProperty__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1363:1: ( rule__ObjectProperty__Group__0__Impl rule__ObjectProperty__Group__1 )
            // InternalSeML.g:1364:2: rule__ObjectProperty__Group__0__Impl rule__ObjectProperty__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__ObjectProperty__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ObjectProperty__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectProperty__Group__0"


    // $ANTLR start "rule__ObjectProperty__Group__0__Impl"
    // InternalSeML.g:1371:1: rule__ObjectProperty__Group__0__Impl : ( 'ObjectProperty' ) ;
    public final void rule__ObjectProperty__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1375:1: ( ( 'ObjectProperty' ) )
            // InternalSeML.g:1376:1: ( 'ObjectProperty' )
            {
            // InternalSeML.g:1376:1: ( 'ObjectProperty' )
            // InternalSeML.g:1377:2: 'ObjectProperty'
            {
             before(grammarAccess.getObjectPropertyAccess().getObjectPropertyKeyword_0()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getObjectPropertyAccess().getObjectPropertyKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectProperty__Group__0__Impl"


    // $ANTLR start "rule__ObjectProperty__Group__1"
    // InternalSeML.g:1386:1: rule__ObjectProperty__Group__1 : rule__ObjectProperty__Group__1__Impl rule__ObjectProperty__Group__2 ;
    public final void rule__ObjectProperty__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1390:1: ( rule__ObjectProperty__Group__1__Impl rule__ObjectProperty__Group__2 )
            // InternalSeML.g:1391:2: rule__ObjectProperty__Group__1__Impl rule__ObjectProperty__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__ObjectProperty__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__ObjectProperty__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectProperty__Group__1"


    // $ANTLR start "rule__ObjectProperty__Group__1__Impl"
    // InternalSeML.g:1398:1: rule__ObjectProperty__Group__1__Impl : ( ( rule__ObjectProperty__NameAssignment_1 ) ) ;
    public final void rule__ObjectProperty__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1402:1: ( ( ( rule__ObjectProperty__NameAssignment_1 ) ) )
            // InternalSeML.g:1403:1: ( ( rule__ObjectProperty__NameAssignment_1 ) )
            {
            // InternalSeML.g:1403:1: ( ( rule__ObjectProperty__NameAssignment_1 ) )
            // InternalSeML.g:1404:2: ( rule__ObjectProperty__NameAssignment_1 )
            {
             before(grammarAccess.getObjectPropertyAccess().getNameAssignment_1()); 
            // InternalSeML.g:1405:2: ( rule__ObjectProperty__NameAssignment_1 )
            // InternalSeML.g:1405:3: rule__ObjectProperty__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__ObjectProperty__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getObjectPropertyAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectProperty__Group__1__Impl"


    // $ANTLR start "rule__ObjectProperty__Group__2"
    // InternalSeML.g:1413:1: rule__ObjectProperty__Group__2 : rule__ObjectProperty__Group__2__Impl ;
    public final void rule__ObjectProperty__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1417:1: ( rule__ObjectProperty__Group__2__Impl )
            // InternalSeML.g:1418:2: rule__ObjectProperty__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__ObjectProperty__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectProperty__Group__2"


    // $ANTLR start "rule__ObjectProperty__Group__2__Impl"
    // InternalSeML.g:1424:1: rule__ObjectProperty__Group__2__Impl : ( ( rule__ObjectProperty__IriAssignment_2 ) ) ;
    public final void rule__ObjectProperty__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1428:1: ( ( ( rule__ObjectProperty__IriAssignment_2 ) ) )
            // InternalSeML.g:1429:1: ( ( rule__ObjectProperty__IriAssignment_2 ) )
            {
            // InternalSeML.g:1429:1: ( ( rule__ObjectProperty__IriAssignment_2 ) )
            // InternalSeML.g:1430:2: ( rule__ObjectProperty__IriAssignment_2 )
            {
             before(grammarAccess.getObjectPropertyAccess().getIriAssignment_2()); 
            // InternalSeML.g:1431:2: ( rule__ObjectProperty__IriAssignment_2 )
            // InternalSeML.g:1431:3: rule__ObjectProperty__IriAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__ObjectProperty__IriAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getObjectPropertyAccess().getIriAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectProperty__Group__2__Impl"


    // $ANTLR start "rule__Characteristic__Group__0"
    // InternalSeML.g:1440:1: rule__Characteristic__Group__0 : rule__Characteristic__Group__0__Impl rule__Characteristic__Group__1 ;
    public final void rule__Characteristic__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1444:1: ( rule__Characteristic__Group__0__Impl rule__Characteristic__Group__1 )
            // InternalSeML.g:1445:2: rule__Characteristic__Group__0__Impl rule__Characteristic__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__Characteristic__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Characteristic__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Characteristic__Group__0"


    // $ANTLR start "rule__Characteristic__Group__0__Impl"
    // InternalSeML.g:1452:1: rule__Characteristic__Group__0__Impl : ( 'Characteristic' ) ;
    public final void rule__Characteristic__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1456:1: ( ( 'Characteristic' ) )
            // InternalSeML.g:1457:1: ( 'Characteristic' )
            {
            // InternalSeML.g:1457:1: ( 'Characteristic' )
            // InternalSeML.g:1458:2: 'Characteristic'
            {
             before(grammarAccess.getCharacteristicAccess().getCharacteristicKeyword_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getCharacteristicAccess().getCharacteristicKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Characteristic__Group__0__Impl"


    // $ANTLR start "rule__Characteristic__Group__1"
    // InternalSeML.g:1467:1: rule__Characteristic__Group__1 : rule__Characteristic__Group__1__Impl rule__Characteristic__Group__2 ;
    public final void rule__Characteristic__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1471:1: ( rule__Characteristic__Group__1__Impl rule__Characteristic__Group__2 )
            // InternalSeML.g:1472:2: rule__Characteristic__Group__1__Impl rule__Characteristic__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__Characteristic__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Characteristic__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Characteristic__Group__1"


    // $ANTLR start "rule__Characteristic__Group__1__Impl"
    // InternalSeML.g:1479:1: rule__Characteristic__Group__1__Impl : ( ( rule__Characteristic__NameAssignment_1 ) ) ;
    public final void rule__Characteristic__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1483:1: ( ( ( rule__Characteristic__NameAssignment_1 ) ) )
            // InternalSeML.g:1484:1: ( ( rule__Characteristic__NameAssignment_1 ) )
            {
            // InternalSeML.g:1484:1: ( ( rule__Characteristic__NameAssignment_1 ) )
            // InternalSeML.g:1485:2: ( rule__Characteristic__NameAssignment_1 )
            {
             before(grammarAccess.getCharacteristicAccess().getNameAssignment_1()); 
            // InternalSeML.g:1486:2: ( rule__Characteristic__NameAssignment_1 )
            // InternalSeML.g:1486:3: rule__Characteristic__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Characteristic__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getCharacteristicAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Characteristic__Group__1__Impl"


    // $ANTLR start "rule__Characteristic__Group__2"
    // InternalSeML.g:1494:1: rule__Characteristic__Group__2 : rule__Characteristic__Group__2__Impl ;
    public final void rule__Characteristic__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1498:1: ( rule__Characteristic__Group__2__Impl )
            // InternalSeML.g:1499:2: rule__Characteristic__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Characteristic__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Characteristic__Group__2"


    // $ANTLR start "rule__Characteristic__Group__2__Impl"
    // InternalSeML.g:1505:1: rule__Characteristic__Group__2__Impl : ( ( rule__Characteristic__IriAssignment_2 ) ) ;
    public final void rule__Characteristic__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1509:1: ( ( ( rule__Characteristic__IriAssignment_2 ) ) )
            // InternalSeML.g:1510:1: ( ( rule__Characteristic__IriAssignment_2 ) )
            {
            // InternalSeML.g:1510:1: ( ( rule__Characteristic__IriAssignment_2 ) )
            // InternalSeML.g:1511:2: ( rule__Characteristic__IriAssignment_2 )
            {
             before(grammarAccess.getCharacteristicAccess().getIriAssignment_2()); 
            // InternalSeML.g:1512:2: ( rule__Characteristic__IriAssignment_2 )
            // InternalSeML.g:1512:3: rule__Characteristic__IriAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Characteristic__IriAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getCharacteristicAccess().getIriAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Characteristic__Group__2__Impl"


    // $ANTLR start "rule__StaticIndividual__Group__0"
    // InternalSeML.g:1521:1: rule__StaticIndividual__Group__0 : rule__StaticIndividual__Group__0__Impl rule__StaticIndividual__Group__1 ;
    public final void rule__StaticIndividual__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1525:1: ( rule__StaticIndividual__Group__0__Impl rule__StaticIndividual__Group__1 )
            // InternalSeML.g:1526:2: rule__StaticIndividual__Group__0__Impl rule__StaticIndividual__Group__1
            {
            pushFollow(FOLLOW_6);
            rule__StaticIndividual__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__StaticIndividual__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StaticIndividual__Group__0"


    // $ANTLR start "rule__StaticIndividual__Group__0__Impl"
    // InternalSeML.g:1533:1: rule__StaticIndividual__Group__0__Impl : ( 'StaticIndividual' ) ;
    public final void rule__StaticIndividual__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1537:1: ( ( 'StaticIndividual' ) )
            // InternalSeML.g:1538:1: ( 'StaticIndividual' )
            {
            // InternalSeML.g:1538:1: ( 'StaticIndividual' )
            // InternalSeML.g:1539:2: 'StaticIndividual'
            {
             before(grammarAccess.getStaticIndividualAccess().getStaticIndividualKeyword_0()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getStaticIndividualAccess().getStaticIndividualKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StaticIndividual__Group__0__Impl"


    // $ANTLR start "rule__StaticIndividual__Group__1"
    // InternalSeML.g:1548:1: rule__StaticIndividual__Group__1 : rule__StaticIndividual__Group__1__Impl rule__StaticIndividual__Group__2 ;
    public final void rule__StaticIndividual__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1552:1: ( rule__StaticIndividual__Group__1__Impl rule__StaticIndividual__Group__2 )
            // InternalSeML.g:1553:2: rule__StaticIndividual__Group__1__Impl rule__StaticIndividual__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__StaticIndividual__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__StaticIndividual__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StaticIndividual__Group__1"


    // $ANTLR start "rule__StaticIndividual__Group__1__Impl"
    // InternalSeML.g:1560:1: rule__StaticIndividual__Group__1__Impl : ( ( rule__StaticIndividual__NameAssignment_1 ) ) ;
    public final void rule__StaticIndividual__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1564:1: ( ( ( rule__StaticIndividual__NameAssignment_1 ) ) )
            // InternalSeML.g:1565:1: ( ( rule__StaticIndividual__NameAssignment_1 ) )
            {
            // InternalSeML.g:1565:1: ( ( rule__StaticIndividual__NameAssignment_1 ) )
            // InternalSeML.g:1566:2: ( rule__StaticIndividual__NameAssignment_1 )
            {
             before(grammarAccess.getStaticIndividualAccess().getNameAssignment_1()); 
            // InternalSeML.g:1567:2: ( rule__StaticIndividual__NameAssignment_1 )
            // InternalSeML.g:1567:3: rule__StaticIndividual__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__StaticIndividual__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getStaticIndividualAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StaticIndividual__Group__1__Impl"


    // $ANTLR start "rule__StaticIndividual__Group__2"
    // InternalSeML.g:1575:1: rule__StaticIndividual__Group__2 : rule__StaticIndividual__Group__2__Impl ;
    public final void rule__StaticIndividual__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1579:1: ( rule__StaticIndividual__Group__2__Impl )
            // InternalSeML.g:1580:2: rule__StaticIndividual__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__StaticIndividual__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StaticIndividual__Group__2"


    // $ANTLR start "rule__StaticIndividual__Group__2__Impl"
    // InternalSeML.g:1586:1: rule__StaticIndividual__Group__2__Impl : ( ( rule__StaticIndividual__IriAssignment_2 ) ) ;
    public final void rule__StaticIndividual__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1590:1: ( ( ( rule__StaticIndividual__IriAssignment_2 ) ) )
            // InternalSeML.g:1591:1: ( ( rule__StaticIndividual__IriAssignment_2 ) )
            {
            // InternalSeML.g:1591:1: ( ( rule__StaticIndividual__IriAssignment_2 ) )
            // InternalSeML.g:1592:2: ( rule__StaticIndividual__IriAssignment_2 )
            {
             before(grammarAccess.getStaticIndividualAccess().getIriAssignment_2()); 
            // InternalSeML.g:1593:2: ( rule__StaticIndividual__IriAssignment_2 )
            // InternalSeML.g:1593:3: rule__StaticIndividual__IriAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__StaticIndividual__IriAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getStaticIndividualAccess().getIriAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StaticIndividual__Group__2__Impl"


    // $ANTLR start "rule__MainModel__ImportsAssignment_0"
    // InternalSeML.g:1602:1: rule__MainModel__ImportsAssignment_0 : ( ruleImport ) ;
    public final void rule__MainModel__ImportsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1606:1: ( ( ruleImport ) )
            // InternalSeML.g:1607:2: ( ruleImport )
            {
            // InternalSeML.g:1607:2: ( ruleImport )
            // InternalSeML.g:1608:3: ruleImport
            {
             before(grammarAccess.getMainModelAccess().getImportsImportParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleImport();

            state._fsp--;

             after(grammarAccess.getMainModelAccess().getImportsImportParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__ImportsAssignment_0"


    // $ANTLR start "rule__MainModel__UseChAssignment_1_1"
    // InternalSeML.g:1617:1: rule__MainModel__UseChAssignment_1_1 : ( ( RULE_ALIAS ) ) ;
    public final void rule__MainModel__UseChAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1621:1: ( ( ( RULE_ALIAS ) ) )
            // InternalSeML.g:1622:2: ( ( RULE_ALIAS ) )
            {
            // InternalSeML.g:1622:2: ( ( RULE_ALIAS ) )
            // InternalSeML.g:1623:3: ( RULE_ALIAS )
            {
             before(grammarAccess.getMainModelAccess().getUseChCharacteristicCrossReference_1_1_0()); 
            // InternalSeML.g:1624:3: ( RULE_ALIAS )
            // InternalSeML.g:1625:4: RULE_ALIAS
            {
             before(grammarAccess.getMainModelAccess().getUseChCharacteristicALIASTerminalRuleCall_1_1_0_1()); 
            match(input,RULE_ALIAS,FOLLOW_2); 
             after(grammarAccess.getMainModelAccess().getUseChCharacteristicALIASTerminalRuleCall_1_1_0_1()); 

            }

             after(grammarAccess.getMainModelAccess().getUseChCharacteristicCrossReference_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__UseChAssignment_1_1"


    // $ANTLR start "rule__MainModel__UseChAssignment_1_2_1"
    // InternalSeML.g:1636:1: rule__MainModel__UseChAssignment_1_2_1 : ( ( RULE_ALIAS ) ) ;
    public final void rule__MainModel__UseChAssignment_1_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1640:1: ( ( ( RULE_ALIAS ) ) )
            // InternalSeML.g:1641:2: ( ( RULE_ALIAS ) )
            {
            // InternalSeML.g:1641:2: ( ( RULE_ALIAS ) )
            // InternalSeML.g:1642:3: ( RULE_ALIAS )
            {
             before(grammarAccess.getMainModelAccess().getUseChCharacteristicCrossReference_1_2_1_0()); 
            // InternalSeML.g:1643:3: ( RULE_ALIAS )
            // InternalSeML.g:1644:4: RULE_ALIAS
            {
             before(grammarAccess.getMainModelAccess().getUseChCharacteristicALIASTerminalRuleCall_1_2_1_0_1()); 
            match(input,RULE_ALIAS,FOLLOW_2); 
             after(grammarAccess.getMainModelAccess().getUseChCharacteristicALIASTerminalRuleCall_1_2_1_0_1()); 

            }

             after(grammarAccess.getMainModelAccess().getUseChCharacteristicCrossReference_1_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__UseChAssignment_1_2_1"


    // $ANTLR start "rule__MainModel__SentencesAssignment_2"
    // InternalSeML.g:1655:1: rule__MainModel__SentencesAssignment_2 : ( ruleSentence ) ;
    public final void rule__MainModel__SentencesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1659:1: ( ( ruleSentence ) )
            // InternalSeML.g:1660:2: ( ruleSentence )
            {
            // InternalSeML.g:1660:2: ( ruleSentence )
            // InternalSeML.g:1661:3: ruleSentence
            {
             before(grammarAccess.getMainModelAccess().getSentencesSentenceParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleSentence();

            state._fsp--;

             after(grammarAccess.getMainModelAccess().getSentencesSentenceParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MainModel__SentencesAssignment_2"


    // $ANTLR start "rule__ImportModel__StaticIndividualsAssignment_0"
    // InternalSeML.g:1670:1: rule__ImportModel__StaticIndividualsAssignment_0 : ( ruleStaticIndividual ) ;
    public final void rule__ImportModel__StaticIndividualsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1674:1: ( ( ruleStaticIndividual ) )
            // InternalSeML.g:1675:2: ( ruleStaticIndividual )
            {
            // InternalSeML.g:1675:2: ( ruleStaticIndividual )
            // InternalSeML.g:1676:3: ruleStaticIndividual
            {
             before(grammarAccess.getImportModelAccess().getStaticIndividualsStaticIndividualParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleStaticIndividual();

            state._fsp--;

             after(grammarAccess.getImportModelAccess().getStaticIndividualsStaticIndividualParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportModel__StaticIndividualsAssignment_0"


    // $ANTLR start "rule__ImportModel__IndividualOptionsAssignment_1"
    // InternalSeML.g:1685:1: rule__ImportModel__IndividualOptionsAssignment_1 : ( ruleFreeIndividual ) ;
    public final void rule__ImportModel__IndividualOptionsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1689:1: ( ( ruleFreeIndividual ) )
            // InternalSeML.g:1690:2: ( ruleFreeIndividual )
            {
            // InternalSeML.g:1690:2: ( ruleFreeIndividual )
            // InternalSeML.g:1691:3: ruleFreeIndividual
            {
             before(grammarAccess.getImportModelAccess().getIndividualOptionsFreeIndividualParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleFreeIndividual();

            state._fsp--;

             after(grammarAccess.getImportModelAccess().getIndividualOptionsFreeIndividualParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportModel__IndividualOptionsAssignment_1"


    // $ANTLR start "rule__ImportModel__CharacteristicsAssignment_2"
    // InternalSeML.g:1700:1: rule__ImportModel__CharacteristicsAssignment_2 : ( ruleCharacteristic ) ;
    public final void rule__ImportModel__CharacteristicsAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1704:1: ( ( ruleCharacteristic ) )
            // InternalSeML.g:1705:2: ( ruleCharacteristic )
            {
            // InternalSeML.g:1705:2: ( ruleCharacteristic )
            // InternalSeML.g:1706:3: ruleCharacteristic
            {
             before(grammarAccess.getImportModelAccess().getCharacteristicsCharacteristicParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleCharacteristic();

            state._fsp--;

             after(grammarAccess.getImportModelAccess().getCharacteristicsCharacteristicParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportModel__CharacteristicsAssignment_2"


    // $ANTLR start "rule__ImportModel__ObjectPropertiesAssignment_3"
    // InternalSeML.g:1715:1: rule__ImportModel__ObjectPropertiesAssignment_3 : ( ruleObjectProperty ) ;
    public final void rule__ImportModel__ObjectPropertiesAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1719:1: ( ( ruleObjectProperty ) )
            // InternalSeML.g:1720:2: ( ruleObjectProperty )
            {
            // InternalSeML.g:1720:2: ( ruleObjectProperty )
            // InternalSeML.g:1721:3: ruleObjectProperty
            {
             before(grammarAccess.getImportModelAccess().getObjectPropertiesObjectPropertyParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleObjectProperty();

            state._fsp--;

             after(grammarAccess.getImportModelAccess().getObjectPropertiesObjectPropertyParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ImportModel__ObjectPropertiesAssignment_3"


    // $ANTLR start "rule__Import__NameAssignment_1"
    // InternalSeML.g:1730:1: rule__Import__NameAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Import__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1734:1: ( ( RULE_STRING ) )
            // InternalSeML.g:1735:2: ( RULE_STRING )
            {
            // InternalSeML.g:1735:2: ( RULE_STRING )
            // InternalSeML.g:1736:3: RULE_STRING
            {
             before(grammarAccess.getImportAccess().getNameSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getImportAccess().getNameSTRINGTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Import__NameAssignment_1"


    // $ANTLR start "rule__Relation__Ind1Assignment_0"
    // InternalSeML.g:1745:1: rule__Relation__Ind1Assignment_0 : ( ( RULE_ALIAS ) ) ;
    public final void rule__Relation__Ind1Assignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1749:1: ( ( ( RULE_ALIAS ) ) )
            // InternalSeML.g:1750:2: ( ( RULE_ALIAS ) )
            {
            // InternalSeML.g:1750:2: ( ( RULE_ALIAS ) )
            // InternalSeML.g:1751:3: ( RULE_ALIAS )
            {
             before(grammarAccess.getRelationAccess().getInd1IndividualCrossReference_0_0()); 
            // InternalSeML.g:1752:3: ( RULE_ALIAS )
            // InternalSeML.g:1753:4: RULE_ALIAS
            {
             before(grammarAccess.getRelationAccess().getInd1IndividualALIASTerminalRuleCall_0_0_1()); 
            match(input,RULE_ALIAS,FOLLOW_2); 
             after(grammarAccess.getRelationAccess().getInd1IndividualALIASTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getRelationAccess().getInd1IndividualCrossReference_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Ind1Assignment_0"


    // $ANTLR start "rule__Relation__ObjAssignment_1"
    // InternalSeML.g:1764:1: rule__Relation__ObjAssignment_1 : ( ( RULE_ALIAS ) ) ;
    public final void rule__Relation__ObjAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1768:1: ( ( ( RULE_ALIAS ) ) )
            // InternalSeML.g:1769:2: ( ( RULE_ALIAS ) )
            {
            // InternalSeML.g:1769:2: ( ( RULE_ALIAS ) )
            // InternalSeML.g:1770:3: ( RULE_ALIAS )
            {
             before(grammarAccess.getRelationAccess().getObjObjectPropertyCrossReference_1_0()); 
            // InternalSeML.g:1771:3: ( RULE_ALIAS )
            // InternalSeML.g:1772:4: RULE_ALIAS
            {
             before(grammarAccess.getRelationAccess().getObjObjectPropertyALIASTerminalRuleCall_1_0_1()); 
            match(input,RULE_ALIAS,FOLLOW_2); 
             after(grammarAccess.getRelationAccess().getObjObjectPropertyALIASTerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getRelationAccess().getObjObjectPropertyCrossReference_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__ObjAssignment_1"


    // $ANTLR start "rule__Relation__Ind2Assignment_2"
    // InternalSeML.g:1783:1: rule__Relation__Ind2Assignment_2 : ( ( RULE_ALIAS ) ) ;
    public final void rule__Relation__Ind2Assignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1787:1: ( ( ( RULE_ALIAS ) ) )
            // InternalSeML.g:1788:2: ( ( RULE_ALIAS ) )
            {
            // InternalSeML.g:1788:2: ( ( RULE_ALIAS ) )
            // InternalSeML.g:1789:3: ( RULE_ALIAS )
            {
             before(grammarAccess.getRelationAccess().getInd2IndividualCrossReference_2_0()); 
            // InternalSeML.g:1790:3: ( RULE_ALIAS )
            // InternalSeML.g:1791:4: RULE_ALIAS
            {
             before(grammarAccess.getRelationAccess().getInd2IndividualALIASTerminalRuleCall_2_0_1()); 
            match(input,RULE_ALIAS,FOLLOW_2); 
             after(grammarAccess.getRelationAccess().getInd2IndividualALIASTerminalRuleCall_2_0_1()); 

            }

             after(grammarAccess.getRelationAccess().getInd2IndividualCrossReference_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Ind2Assignment_2"


    // $ANTLR start "rule__Relation__Ind2Assignment_3_1"
    // InternalSeML.g:1802:1: rule__Relation__Ind2Assignment_3_1 : ( ( RULE_ALIAS ) ) ;
    public final void rule__Relation__Ind2Assignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1806:1: ( ( ( RULE_ALIAS ) ) )
            // InternalSeML.g:1807:2: ( ( RULE_ALIAS ) )
            {
            // InternalSeML.g:1807:2: ( ( RULE_ALIAS ) )
            // InternalSeML.g:1808:3: ( RULE_ALIAS )
            {
             before(grammarAccess.getRelationAccess().getInd2IndividualCrossReference_3_1_0()); 
            // InternalSeML.g:1809:3: ( RULE_ALIAS )
            // InternalSeML.g:1810:4: RULE_ALIAS
            {
             before(grammarAccess.getRelationAccess().getInd2IndividualALIASTerminalRuleCall_3_1_0_1()); 
            match(input,RULE_ALIAS,FOLLOW_2); 
             after(grammarAccess.getRelationAccess().getInd2IndividualALIASTerminalRuleCall_3_1_0_1()); 

            }

             after(grammarAccess.getRelationAccess().getInd2IndividualCrossReference_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Relation__Ind2Assignment_3_1"


    // $ANTLR start "rule__Assignment__IndAssignment_0"
    // InternalSeML.g:1821:1: rule__Assignment__IndAssignment_0 : ( ( RULE_ALIAS ) ) ;
    public final void rule__Assignment__IndAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1825:1: ( ( ( RULE_ALIAS ) ) )
            // InternalSeML.g:1826:2: ( ( RULE_ALIAS ) )
            {
            // InternalSeML.g:1826:2: ( ( RULE_ALIAS ) )
            // InternalSeML.g:1827:3: ( RULE_ALIAS )
            {
             before(grammarAccess.getAssignmentAccess().getIndIndividualCrossReference_0_0()); 
            // InternalSeML.g:1828:3: ( RULE_ALIAS )
            // InternalSeML.g:1829:4: RULE_ALIAS
            {
             before(grammarAccess.getAssignmentAccess().getIndIndividualALIASTerminalRuleCall_0_0_1()); 
            match(input,RULE_ALIAS,FOLLOW_2); 
             after(grammarAccess.getAssignmentAccess().getIndIndividualALIASTerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getAssignmentAccess().getIndIndividualCrossReference_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__IndAssignment_0"


    // $ANTLR start "rule__Assignment__LiteralAssignment_2"
    // InternalSeML.g:1840:1: rule__Assignment__LiteralAssignment_2 : ( ruleValue ) ;
    public final void rule__Assignment__LiteralAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1844:1: ( ( ruleValue ) )
            // InternalSeML.g:1845:2: ( ruleValue )
            {
            // InternalSeML.g:1845:2: ( ruleValue )
            // InternalSeML.g:1846:3: ruleValue
            {
             before(grammarAccess.getAssignmentAccess().getLiteralValueParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleValue();

            state._fsp--;

             after(grammarAccess.getAssignmentAccess().getLiteralValueParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Assignment__LiteralAssignment_2"


    // $ANTLR start "rule__Value__ValAssignment_0_1"
    // InternalSeML.g:1855:1: rule__Value__ValAssignment_0_1 : ( RULE_FLOAT ) ;
    public final void rule__Value__ValAssignment_0_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1859:1: ( ( RULE_FLOAT ) )
            // InternalSeML.g:1860:2: ( RULE_FLOAT )
            {
            // InternalSeML.g:1860:2: ( RULE_FLOAT )
            // InternalSeML.g:1861:3: RULE_FLOAT
            {
             before(grammarAccess.getValueAccess().getValFLOATTerminalRuleCall_0_1_0()); 
            match(input,RULE_FLOAT,FOLLOW_2); 
             after(grammarAccess.getValueAccess().getValFLOATTerminalRuleCall_0_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__ValAssignment_0_1"


    // $ANTLR start "rule__Value__ValAssignment_1_1"
    // InternalSeML.g:1870:1: rule__Value__ValAssignment_1_1 : ( RULE_BOOL ) ;
    public final void rule__Value__ValAssignment_1_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1874:1: ( ( RULE_BOOL ) )
            // InternalSeML.g:1875:2: ( RULE_BOOL )
            {
            // InternalSeML.g:1875:2: ( RULE_BOOL )
            // InternalSeML.g:1876:3: RULE_BOOL
            {
             before(grammarAccess.getValueAccess().getValBOOLTerminalRuleCall_1_1_0()); 
            match(input,RULE_BOOL,FOLLOW_2); 
             after(grammarAccess.getValueAccess().getValBOOLTerminalRuleCall_1_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__ValAssignment_1_1"


    // $ANTLR start "rule__Value__ValAssignment_2_1"
    // InternalSeML.g:1885:1: rule__Value__ValAssignment_2_1 : ( RULE_INT ) ;
    public final void rule__Value__ValAssignment_2_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1889:1: ( ( RULE_INT ) )
            // InternalSeML.g:1890:2: ( RULE_INT )
            {
            // InternalSeML.g:1890:2: ( RULE_INT )
            // InternalSeML.g:1891:3: RULE_INT
            {
             before(grammarAccess.getValueAccess().getValINTTerminalRuleCall_2_1_0()); 
            match(input,RULE_INT,FOLLOW_2); 
             after(grammarAccess.getValueAccess().getValINTTerminalRuleCall_2_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__ValAssignment_2_1"


    // $ANTLR start "rule__Value__ValAssignment_3_1"
    // InternalSeML.g:1900:1: rule__Value__ValAssignment_3_1 : ( RULE_STRING ) ;
    public final void rule__Value__ValAssignment_3_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1904:1: ( ( RULE_STRING ) )
            // InternalSeML.g:1905:2: ( RULE_STRING )
            {
            // InternalSeML.g:1905:2: ( RULE_STRING )
            // InternalSeML.g:1906:3: RULE_STRING
            {
             before(grammarAccess.getValueAccess().getValSTRINGTerminalRuleCall_3_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getValueAccess().getValSTRINGTerminalRuleCall_3_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Value__ValAssignment_3_1"


    // $ANTLR start "rule__FreeIndividual__NameAssignment_1"
    // InternalSeML.g:1915:1: rule__FreeIndividual__NameAssignment_1 : ( RULE_ALIAS ) ;
    public final void rule__FreeIndividual__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1919:1: ( ( RULE_ALIAS ) )
            // InternalSeML.g:1920:2: ( RULE_ALIAS )
            {
            // InternalSeML.g:1920:2: ( RULE_ALIAS )
            // InternalSeML.g:1921:3: RULE_ALIAS
            {
             before(grammarAccess.getFreeIndividualAccess().getNameALIASTerminalRuleCall_1_0()); 
            match(input,RULE_ALIAS,FOLLOW_2); 
             after(grammarAccess.getFreeIndividualAccess().getNameALIASTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FreeIndividual__NameAssignment_1"


    // $ANTLR start "rule__FreeIndividual__IriAssignment_2"
    // InternalSeML.g:1930:1: rule__FreeIndividual__IriAssignment_2 : ( RULE_STRING ) ;
    public final void rule__FreeIndividual__IriAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1934:1: ( ( RULE_STRING ) )
            // InternalSeML.g:1935:2: ( RULE_STRING )
            {
            // InternalSeML.g:1935:2: ( RULE_STRING )
            // InternalSeML.g:1936:3: RULE_STRING
            {
             before(grammarAccess.getFreeIndividualAccess().getIriSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getFreeIndividualAccess().getIriSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FreeIndividual__IriAssignment_2"


    // $ANTLR start "rule__ObjectProperty__NameAssignment_1"
    // InternalSeML.g:1945:1: rule__ObjectProperty__NameAssignment_1 : ( RULE_ALIAS ) ;
    public final void rule__ObjectProperty__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1949:1: ( ( RULE_ALIAS ) )
            // InternalSeML.g:1950:2: ( RULE_ALIAS )
            {
            // InternalSeML.g:1950:2: ( RULE_ALIAS )
            // InternalSeML.g:1951:3: RULE_ALIAS
            {
             before(grammarAccess.getObjectPropertyAccess().getNameALIASTerminalRuleCall_1_0()); 
            match(input,RULE_ALIAS,FOLLOW_2); 
             after(grammarAccess.getObjectPropertyAccess().getNameALIASTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectProperty__NameAssignment_1"


    // $ANTLR start "rule__ObjectProperty__IriAssignment_2"
    // InternalSeML.g:1960:1: rule__ObjectProperty__IriAssignment_2 : ( RULE_STRING ) ;
    public final void rule__ObjectProperty__IriAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1964:1: ( ( RULE_STRING ) )
            // InternalSeML.g:1965:2: ( RULE_STRING )
            {
            // InternalSeML.g:1965:2: ( RULE_STRING )
            // InternalSeML.g:1966:3: RULE_STRING
            {
             before(grammarAccess.getObjectPropertyAccess().getIriSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getObjectPropertyAccess().getIriSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__ObjectProperty__IriAssignment_2"


    // $ANTLR start "rule__Characteristic__NameAssignment_1"
    // InternalSeML.g:1975:1: rule__Characteristic__NameAssignment_1 : ( RULE_ALIAS ) ;
    public final void rule__Characteristic__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1979:1: ( ( RULE_ALIAS ) )
            // InternalSeML.g:1980:2: ( RULE_ALIAS )
            {
            // InternalSeML.g:1980:2: ( RULE_ALIAS )
            // InternalSeML.g:1981:3: RULE_ALIAS
            {
             before(grammarAccess.getCharacteristicAccess().getNameALIASTerminalRuleCall_1_0()); 
            match(input,RULE_ALIAS,FOLLOW_2); 
             after(grammarAccess.getCharacteristicAccess().getNameALIASTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Characteristic__NameAssignment_1"


    // $ANTLR start "rule__Characteristic__IriAssignment_2"
    // InternalSeML.g:1990:1: rule__Characteristic__IriAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Characteristic__IriAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1994:1: ( ( RULE_STRING ) )
            // InternalSeML.g:1995:2: ( RULE_STRING )
            {
            // InternalSeML.g:1995:2: ( RULE_STRING )
            // InternalSeML.g:1996:3: RULE_STRING
            {
             before(grammarAccess.getCharacteristicAccess().getIriSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getCharacteristicAccess().getIriSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Characteristic__IriAssignment_2"


    // $ANTLR start "rule__StaticIndividual__NameAssignment_1"
    // InternalSeML.g:2005:1: rule__StaticIndividual__NameAssignment_1 : ( RULE_ALIAS ) ;
    public final void rule__StaticIndividual__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:2009:1: ( ( RULE_ALIAS ) )
            // InternalSeML.g:2010:2: ( RULE_ALIAS )
            {
            // InternalSeML.g:2010:2: ( RULE_ALIAS )
            // InternalSeML.g:2011:3: RULE_ALIAS
            {
             before(grammarAccess.getStaticIndividualAccess().getNameALIASTerminalRuleCall_1_0()); 
            match(input,RULE_ALIAS,FOLLOW_2); 
             after(grammarAccess.getStaticIndividualAccess().getNameALIASTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StaticIndividual__NameAssignment_1"


    // $ANTLR start "rule__StaticIndividual__IriAssignment_2"
    // InternalSeML.g:2020:1: rule__StaticIndividual__IriAssignment_2 : ( RULE_STRING ) ;
    public final void rule__StaticIndividual__IriAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:2024:1: ( ( RULE_STRING ) )
            // InternalSeML.g:2025:2: ( RULE_STRING )
            {
            // InternalSeML.g:2025:2: ( RULE_STRING )
            // InternalSeML.g:2026:3: RULE_STRING
            {
             before(grammarAccess.getStaticIndividualAccess().getIriSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getStaticIndividualAccess().getIriSTRINGTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__StaticIndividual__IriAssignment_2"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000002010L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000008002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x00000000000E0000L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000100002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000020002L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000080002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_16 = new BitSet(new long[]{0x00000000000001E0L});
    public static final BitSet FOLLOW_17 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_18 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_19 = new BitSet(new long[]{0x0000000000000100L});

}