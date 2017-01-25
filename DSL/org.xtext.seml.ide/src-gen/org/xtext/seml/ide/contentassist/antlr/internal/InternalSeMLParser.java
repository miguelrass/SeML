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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_IRI", "RULE_INT", "RULE_FLOAT", "RULE_BOOL", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'import'", "'new'", "'\\n'", "'use'", "'CompProcess'", "'CompEvent'", "'CompProperty'", "'CompEntity'", "'ObjectProperty'", "'Characteristic'", "'MetaIndividual'"
    };
    public static final int RULE_IRI=5;
    public static final int RULE_STRING=4;
    public static final int RULE_SL_COMMENT=10;
    public static final int T__19=19;
    public static final int T__15=15;
    public static final int T__16=16;
    public static final int T__17=17;
    public static final int T__18=18;
    public static final int T__13=13;
    public static final int T__14=14;
    public static final int EOF=-1;
    public static final int RULE_WS=11;
    public static final int RULE_ANY_OTHER=12;
    public static final int RULE_BOOL=8;
    public static final int RULE_INT=6;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=9;
    public static final int T__23=23;
    public static final int RULE_FLOAT=7;
    public static final int T__20=20;
    public static final int T__21=21;

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


    // $ANTLR start "entryRuleIndividual"
    // InternalSeML.g:178:1: entryRuleIndividual : ruleIndividual EOF ;
    public final void entryRuleIndividual() throws RecognitionException {
        try {
            // InternalSeML.g:179:1: ( ruleIndividual EOF )
            // InternalSeML.g:180:1: ruleIndividual EOF
            {
             before(grammarAccess.getIndividualRule()); 
            pushFollow(FOLLOW_1);
            ruleIndividual();

            state._fsp--;

             after(grammarAccess.getIndividualRule()); 
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
    // $ANTLR end "entryRuleIndividual"


    // $ANTLR start "ruleIndividual"
    // InternalSeML.g:187:1: ruleIndividual : ( ( rule__Individual__Group__0 ) ) ;
    public final void ruleIndividual() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:191:2: ( ( ( rule__Individual__Group__0 ) ) )
            // InternalSeML.g:192:2: ( ( rule__Individual__Group__0 ) )
            {
            // InternalSeML.g:192:2: ( ( rule__Individual__Group__0 ) )
            // InternalSeML.g:193:3: ( rule__Individual__Group__0 )
            {
             before(grammarAccess.getIndividualAccess().getGroup()); 
            // InternalSeML.g:194:3: ( rule__Individual__Group__0 )
            // InternalSeML.g:194:4: rule__Individual__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Individual__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getIndividualAccess().getGroup()); 

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
    // $ANTLR end "ruleIndividual"


    // $ANTLR start "entryRuleUseCharacteristic"
    // InternalSeML.g:203:1: entryRuleUseCharacteristic : ruleUseCharacteristic EOF ;
    public final void entryRuleUseCharacteristic() throws RecognitionException {
        try {
            // InternalSeML.g:204:1: ( ruleUseCharacteristic EOF )
            // InternalSeML.g:205:1: ruleUseCharacteristic EOF
            {
             before(grammarAccess.getUseCharacteristicRule()); 
            pushFollow(FOLLOW_1);
            ruleUseCharacteristic();

            state._fsp--;

             after(grammarAccess.getUseCharacteristicRule()); 
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
    // $ANTLR end "entryRuleUseCharacteristic"


    // $ANTLR start "ruleUseCharacteristic"
    // InternalSeML.g:212:1: ruleUseCharacteristic : ( ( rule__UseCharacteristic__Group__0 ) ) ;
    public final void ruleUseCharacteristic() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:216:2: ( ( ( rule__UseCharacteristic__Group__0 ) ) )
            // InternalSeML.g:217:2: ( ( rule__UseCharacteristic__Group__0 ) )
            {
            // InternalSeML.g:217:2: ( ( rule__UseCharacteristic__Group__0 ) )
            // InternalSeML.g:218:3: ( rule__UseCharacteristic__Group__0 )
            {
             before(grammarAccess.getUseCharacteristicAccess().getGroup()); 
            // InternalSeML.g:219:3: ( rule__UseCharacteristic__Group__0 )
            // InternalSeML.g:219:4: rule__UseCharacteristic__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__UseCharacteristic__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getUseCharacteristicAccess().getGroup()); 

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
    // $ANTLR end "ruleUseCharacteristic"


    // $ANTLR start "entryRuleRelation"
    // InternalSeML.g:228:1: entryRuleRelation : ruleRelation EOF ;
    public final void entryRuleRelation() throws RecognitionException {
        try {
            // InternalSeML.g:229:1: ( ruleRelation EOF )
            // InternalSeML.g:230:1: ruleRelation EOF
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
    // InternalSeML.g:237:1: ruleRelation : ( ( rule__Relation__Group__0 ) ) ;
    public final void ruleRelation() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:241:2: ( ( ( rule__Relation__Group__0 ) ) )
            // InternalSeML.g:242:2: ( ( rule__Relation__Group__0 ) )
            {
            // InternalSeML.g:242:2: ( ( rule__Relation__Group__0 ) )
            // InternalSeML.g:243:3: ( rule__Relation__Group__0 )
            {
             before(grammarAccess.getRelationAccess().getGroup()); 
            // InternalSeML.g:244:3: ( rule__Relation__Group__0 )
            // InternalSeML.g:244:4: rule__Relation__Group__0
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


    // $ANTLR start "entryRuleComponent"
    // InternalSeML.g:253:1: entryRuleComponent : ruleComponent EOF ;
    public final void entryRuleComponent() throws RecognitionException {
        try {
            // InternalSeML.g:254:1: ( ruleComponent EOF )
            // InternalSeML.g:255:1: ruleComponent EOF
            {
             before(grammarAccess.getComponentRule()); 
            pushFollow(FOLLOW_1);
            ruleComponent();

            state._fsp--;

             after(grammarAccess.getComponentRule()); 
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
    // $ANTLR end "entryRuleComponent"


    // $ANTLR start "ruleComponent"
    // InternalSeML.g:262:1: ruleComponent : ( ( rule__Component__Alternatives ) ) ;
    public final void ruleComponent() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:266:2: ( ( ( rule__Component__Alternatives ) ) )
            // InternalSeML.g:267:2: ( ( rule__Component__Alternatives ) )
            {
            // InternalSeML.g:267:2: ( ( rule__Component__Alternatives ) )
            // InternalSeML.g:268:3: ( rule__Component__Alternatives )
            {
             before(grammarAccess.getComponentAccess().getAlternatives()); 
            // InternalSeML.g:269:3: ( rule__Component__Alternatives )
            // InternalSeML.g:269:4: rule__Component__Alternatives
            {
            pushFollow(FOLLOW_2);
            rule__Component__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getComponentAccess().getAlternatives()); 

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
    // $ANTLR end "ruleComponent"


    // $ANTLR start "entryRuleComponent_Process"
    // InternalSeML.g:278:1: entryRuleComponent_Process : ruleComponent_Process EOF ;
    public final void entryRuleComponent_Process() throws RecognitionException {
        try {
            // InternalSeML.g:279:1: ( ruleComponent_Process EOF )
            // InternalSeML.g:280:1: ruleComponent_Process EOF
            {
             before(grammarAccess.getComponent_ProcessRule()); 
            pushFollow(FOLLOW_1);
            ruleComponent_Process();

            state._fsp--;

             after(grammarAccess.getComponent_ProcessRule()); 
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
    // $ANTLR end "entryRuleComponent_Process"


    // $ANTLR start "ruleComponent_Process"
    // InternalSeML.g:287:1: ruleComponent_Process : ( ( rule__Component_Process__Group__0 ) ) ;
    public final void ruleComponent_Process() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:291:2: ( ( ( rule__Component_Process__Group__0 ) ) )
            // InternalSeML.g:292:2: ( ( rule__Component_Process__Group__0 ) )
            {
            // InternalSeML.g:292:2: ( ( rule__Component_Process__Group__0 ) )
            // InternalSeML.g:293:3: ( rule__Component_Process__Group__0 )
            {
             before(grammarAccess.getComponent_ProcessAccess().getGroup()); 
            // InternalSeML.g:294:3: ( rule__Component_Process__Group__0 )
            // InternalSeML.g:294:4: rule__Component_Process__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Component_Process__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getComponent_ProcessAccess().getGroup()); 

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
    // $ANTLR end "ruleComponent_Process"


    // $ANTLR start "entryRuleComponent_Event"
    // InternalSeML.g:303:1: entryRuleComponent_Event : ruleComponent_Event EOF ;
    public final void entryRuleComponent_Event() throws RecognitionException {
        try {
            // InternalSeML.g:304:1: ( ruleComponent_Event EOF )
            // InternalSeML.g:305:1: ruleComponent_Event EOF
            {
             before(grammarAccess.getComponent_EventRule()); 
            pushFollow(FOLLOW_1);
            ruleComponent_Event();

            state._fsp--;

             after(grammarAccess.getComponent_EventRule()); 
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
    // $ANTLR end "entryRuleComponent_Event"


    // $ANTLR start "ruleComponent_Event"
    // InternalSeML.g:312:1: ruleComponent_Event : ( ( rule__Component_Event__Group__0 ) ) ;
    public final void ruleComponent_Event() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:316:2: ( ( ( rule__Component_Event__Group__0 ) ) )
            // InternalSeML.g:317:2: ( ( rule__Component_Event__Group__0 ) )
            {
            // InternalSeML.g:317:2: ( ( rule__Component_Event__Group__0 ) )
            // InternalSeML.g:318:3: ( rule__Component_Event__Group__0 )
            {
             before(grammarAccess.getComponent_EventAccess().getGroup()); 
            // InternalSeML.g:319:3: ( rule__Component_Event__Group__0 )
            // InternalSeML.g:319:4: rule__Component_Event__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Component_Event__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getComponent_EventAccess().getGroup()); 

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
    // $ANTLR end "ruleComponent_Event"


    // $ANTLR start "entryRuleComponent_Property"
    // InternalSeML.g:328:1: entryRuleComponent_Property : ruleComponent_Property EOF ;
    public final void entryRuleComponent_Property() throws RecognitionException {
        try {
            // InternalSeML.g:329:1: ( ruleComponent_Property EOF )
            // InternalSeML.g:330:1: ruleComponent_Property EOF
            {
             before(grammarAccess.getComponent_PropertyRule()); 
            pushFollow(FOLLOW_1);
            ruleComponent_Property();

            state._fsp--;

             after(grammarAccess.getComponent_PropertyRule()); 
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
    // $ANTLR end "entryRuleComponent_Property"


    // $ANTLR start "ruleComponent_Property"
    // InternalSeML.g:337:1: ruleComponent_Property : ( ( rule__Component_Property__Group__0 ) ) ;
    public final void ruleComponent_Property() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:341:2: ( ( ( rule__Component_Property__Group__0 ) ) )
            // InternalSeML.g:342:2: ( ( rule__Component_Property__Group__0 ) )
            {
            // InternalSeML.g:342:2: ( ( rule__Component_Property__Group__0 ) )
            // InternalSeML.g:343:3: ( rule__Component_Property__Group__0 )
            {
             before(grammarAccess.getComponent_PropertyAccess().getGroup()); 
            // InternalSeML.g:344:3: ( rule__Component_Property__Group__0 )
            // InternalSeML.g:344:4: rule__Component_Property__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Component_Property__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getComponent_PropertyAccess().getGroup()); 

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
    // $ANTLR end "ruleComponent_Property"


    // $ANTLR start "entryRuleComponent_Entity"
    // InternalSeML.g:353:1: entryRuleComponent_Entity : ruleComponent_Entity EOF ;
    public final void entryRuleComponent_Entity() throws RecognitionException {
        try {
            // InternalSeML.g:354:1: ( ruleComponent_Entity EOF )
            // InternalSeML.g:355:1: ruleComponent_Entity EOF
            {
             before(grammarAccess.getComponent_EntityRule()); 
            pushFollow(FOLLOW_1);
            ruleComponent_Entity();

            state._fsp--;

             after(grammarAccess.getComponent_EntityRule()); 
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
    // $ANTLR end "entryRuleComponent_Entity"


    // $ANTLR start "ruleComponent_Entity"
    // InternalSeML.g:362:1: ruleComponent_Entity : ( ( rule__Component_Entity__Group__0 ) ) ;
    public final void ruleComponent_Entity() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:366:2: ( ( ( rule__Component_Entity__Group__0 ) ) )
            // InternalSeML.g:367:2: ( ( rule__Component_Entity__Group__0 ) )
            {
            // InternalSeML.g:367:2: ( ( rule__Component_Entity__Group__0 ) )
            // InternalSeML.g:368:3: ( rule__Component_Entity__Group__0 )
            {
             before(grammarAccess.getComponent_EntityAccess().getGroup()); 
            // InternalSeML.g:369:3: ( rule__Component_Entity__Group__0 )
            // InternalSeML.g:369:4: rule__Component_Entity__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__Component_Entity__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getComponent_EntityAccess().getGroup()); 

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
    // $ANTLR end "ruleComponent_Entity"


    // $ANTLR start "entryRuleObjectProperty"
    // InternalSeML.g:378:1: entryRuleObjectProperty : ruleObjectProperty EOF ;
    public final void entryRuleObjectProperty() throws RecognitionException {
        try {
            // InternalSeML.g:379:1: ( ruleObjectProperty EOF )
            // InternalSeML.g:380:1: ruleObjectProperty EOF
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
    // InternalSeML.g:387:1: ruleObjectProperty : ( ( rule__ObjectProperty__Group__0 ) ) ;
    public final void ruleObjectProperty() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:391:2: ( ( ( rule__ObjectProperty__Group__0 ) ) )
            // InternalSeML.g:392:2: ( ( rule__ObjectProperty__Group__0 ) )
            {
            // InternalSeML.g:392:2: ( ( rule__ObjectProperty__Group__0 ) )
            // InternalSeML.g:393:3: ( rule__ObjectProperty__Group__0 )
            {
             before(grammarAccess.getObjectPropertyAccess().getGroup()); 
            // InternalSeML.g:394:3: ( rule__ObjectProperty__Group__0 )
            // InternalSeML.g:394:4: rule__ObjectProperty__Group__0
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
    // InternalSeML.g:403:1: entryRuleCharacteristic : ruleCharacteristic EOF ;
    public final void entryRuleCharacteristic() throws RecognitionException {
        try {
            // InternalSeML.g:404:1: ( ruleCharacteristic EOF )
            // InternalSeML.g:405:1: ruleCharacteristic EOF
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
    // InternalSeML.g:412:1: ruleCharacteristic : ( ( rule__Characteristic__Group__0 ) ) ;
    public final void ruleCharacteristic() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:416:2: ( ( ( rule__Characteristic__Group__0 ) ) )
            // InternalSeML.g:417:2: ( ( rule__Characteristic__Group__0 ) )
            {
            // InternalSeML.g:417:2: ( ( rule__Characteristic__Group__0 ) )
            // InternalSeML.g:418:3: ( rule__Characteristic__Group__0 )
            {
             before(grammarAccess.getCharacteristicAccess().getGroup()); 
            // InternalSeML.g:419:3: ( rule__Characteristic__Group__0 )
            // InternalSeML.g:419:4: rule__Characteristic__Group__0
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


    // $ANTLR start "entryRuleMetaIndividual"
    // InternalSeML.g:428:1: entryRuleMetaIndividual : ruleMetaIndividual EOF ;
    public final void entryRuleMetaIndividual() throws RecognitionException {
        try {
            // InternalSeML.g:429:1: ( ruleMetaIndividual EOF )
            // InternalSeML.g:430:1: ruleMetaIndividual EOF
            {
             before(grammarAccess.getMetaIndividualRule()); 
            pushFollow(FOLLOW_1);
            ruleMetaIndividual();

            state._fsp--;

             after(grammarAccess.getMetaIndividualRule()); 
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
    // $ANTLR end "entryRuleMetaIndividual"


    // $ANTLR start "ruleMetaIndividual"
    // InternalSeML.g:437:1: ruleMetaIndividual : ( ( rule__MetaIndividual__Group__0 ) ) ;
    public final void ruleMetaIndividual() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:441:2: ( ( ( rule__MetaIndividual__Group__0 ) ) )
            // InternalSeML.g:442:2: ( ( rule__MetaIndividual__Group__0 ) )
            {
            // InternalSeML.g:442:2: ( ( rule__MetaIndividual__Group__0 ) )
            // InternalSeML.g:443:3: ( rule__MetaIndividual__Group__0 )
            {
             before(grammarAccess.getMetaIndividualAccess().getGroup()); 
            // InternalSeML.g:444:3: ( rule__MetaIndividual__Group__0 )
            // InternalSeML.g:444:4: rule__MetaIndividual__Group__0
            {
            pushFollow(FOLLOW_2);
            rule__MetaIndividual__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMetaIndividualAccess().getGroup()); 

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
    // $ANTLR end "ruleMetaIndividual"


    // $ANTLR start "rule__Model__Alternatives"
    // InternalSeML.g:452:1: rule__Model__Alternatives : ( ( ruleMainModel ) | ( ruleImportModel ) );
    public final void rule__Model__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:456:1: ( ( ruleMainModel ) | ( ruleImportModel ) )
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==13) ) {
                alt1=1;
            }
            else if ( ((LA1_0>=17 && LA1_0<=20)) ) {
                alt1=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }
            switch (alt1) {
                case 1 :
                    // InternalSeML.g:457:2: ( ruleMainModel )
                    {
                    // InternalSeML.g:457:2: ( ruleMainModel )
                    // InternalSeML.g:458:3: ruleMainModel
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
                    // InternalSeML.g:463:2: ( ruleImportModel )
                    {
                    // InternalSeML.g:463:2: ( ruleImportModel )
                    // InternalSeML.g:464:3: ruleImportModel
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
    // InternalSeML.g:473:1: rule__Sentence__Alternatives : ( ( ruleIndividual ) | ( ruleRelation ) | ( ruleUseCharacteristic ) );
    public final void rule__Sentence__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:477:1: ( ( ruleIndividual ) | ( ruleRelation ) | ( ruleUseCharacteristic ) )
            int alt2=3;
            switch ( input.LA(1) ) {
            case 14:
                {
                alt2=1;
                }
                break;
            case RULE_IRI:
                {
                alt2=2;
                }
                break;
            case 16:
                {
                alt2=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // InternalSeML.g:478:2: ( ruleIndividual )
                    {
                    // InternalSeML.g:478:2: ( ruleIndividual )
                    // InternalSeML.g:479:3: ruleIndividual
                    {
                     before(grammarAccess.getSentenceAccess().getIndividualParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleIndividual();

                    state._fsp--;

                     after(grammarAccess.getSentenceAccess().getIndividualParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalSeML.g:484:2: ( ruleRelation )
                    {
                    // InternalSeML.g:484:2: ( ruleRelation )
                    // InternalSeML.g:485:3: ruleRelation
                    {
                     before(grammarAccess.getSentenceAccess().getRelationParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleRelation();

                    state._fsp--;

                     after(grammarAccess.getSentenceAccess().getRelationParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalSeML.g:490:2: ( ruleUseCharacteristic )
                    {
                    // InternalSeML.g:490:2: ( ruleUseCharacteristic )
                    // InternalSeML.g:491:3: ruleUseCharacteristic
                    {
                     before(grammarAccess.getSentenceAccess().getUseCharacteristicParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleUseCharacteristic();

                    state._fsp--;

                     after(grammarAccess.getSentenceAccess().getUseCharacteristicParserRuleCall_2()); 

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


    // $ANTLR start "rule__Component__Alternatives"
    // InternalSeML.g:500:1: rule__Component__Alternatives : ( ( ruleComponent_Process ) | ( ruleComponent_Event ) | ( ruleComponent_Property ) | ( ruleComponent_Entity ) );
    public final void rule__Component__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:504:1: ( ( ruleComponent_Process ) | ( ruleComponent_Event ) | ( ruleComponent_Property ) | ( ruleComponent_Entity ) )
            int alt3=4;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt3=1;
                }
                break;
            case 18:
                {
                alt3=2;
                }
                break;
            case 19:
                {
                alt3=3;
                }
                break;
            case 20:
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
                    // InternalSeML.g:505:2: ( ruleComponent_Process )
                    {
                    // InternalSeML.g:505:2: ( ruleComponent_Process )
                    // InternalSeML.g:506:3: ruleComponent_Process
                    {
                     before(grammarAccess.getComponentAccess().getComponent_ProcessParserRuleCall_0()); 
                    pushFollow(FOLLOW_2);
                    ruleComponent_Process();

                    state._fsp--;

                     after(grammarAccess.getComponentAccess().getComponent_ProcessParserRuleCall_0()); 

                    }


                    }
                    break;
                case 2 :
                    // InternalSeML.g:511:2: ( ruleComponent_Event )
                    {
                    // InternalSeML.g:511:2: ( ruleComponent_Event )
                    // InternalSeML.g:512:3: ruleComponent_Event
                    {
                     before(grammarAccess.getComponentAccess().getComponent_EventParserRuleCall_1()); 
                    pushFollow(FOLLOW_2);
                    ruleComponent_Event();

                    state._fsp--;

                     after(grammarAccess.getComponentAccess().getComponent_EventParserRuleCall_1()); 

                    }


                    }
                    break;
                case 3 :
                    // InternalSeML.g:517:2: ( ruleComponent_Property )
                    {
                    // InternalSeML.g:517:2: ( ruleComponent_Property )
                    // InternalSeML.g:518:3: ruleComponent_Property
                    {
                     before(grammarAccess.getComponentAccess().getComponent_PropertyParserRuleCall_2()); 
                    pushFollow(FOLLOW_2);
                    ruleComponent_Property();

                    state._fsp--;

                     after(grammarAccess.getComponentAccess().getComponent_PropertyParserRuleCall_2()); 

                    }


                    }
                    break;
                case 4 :
                    // InternalSeML.g:523:2: ( ruleComponent_Entity )
                    {
                    // InternalSeML.g:523:2: ( ruleComponent_Entity )
                    // InternalSeML.g:524:3: ruleComponent_Entity
                    {
                     before(grammarAccess.getComponentAccess().getComponent_EntityParserRuleCall_3()); 
                    pushFollow(FOLLOW_2);
                    ruleComponent_Entity();

                    state._fsp--;

                     after(grammarAccess.getComponentAccess().getComponent_EntityParserRuleCall_3()); 

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
    // $ANTLR end "rule__Component__Alternatives"


    // $ANTLR start "rule__MainModel__Group__0"
    // InternalSeML.g:533:1: rule__MainModel__Group__0 : rule__MainModel__Group__0__Impl rule__MainModel__Group__1 ;
    public final void rule__MainModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:537:1: ( rule__MainModel__Group__0__Impl rule__MainModel__Group__1 )
            // InternalSeML.g:538:2: rule__MainModel__Group__0__Impl rule__MainModel__Group__1
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
    // InternalSeML.g:545:1: rule__MainModel__Group__0__Impl : ( ( ( rule__MainModel__ImportsAssignment_0 ) ) ( ( rule__MainModel__ImportsAssignment_0 )* ) ) ;
    public final void rule__MainModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:549:1: ( ( ( ( rule__MainModel__ImportsAssignment_0 ) ) ( ( rule__MainModel__ImportsAssignment_0 )* ) ) )
            // InternalSeML.g:550:1: ( ( ( rule__MainModel__ImportsAssignment_0 ) ) ( ( rule__MainModel__ImportsAssignment_0 )* ) )
            {
            // InternalSeML.g:550:1: ( ( ( rule__MainModel__ImportsAssignment_0 ) ) ( ( rule__MainModel__ImportsAssignment_0 )* ) )
            // InternalSeML.g:551:2: ( ( rule__MainModel__ImportsAssignment_0 ) ) ( ( rule__MainModel__ImportsAssignment_0 )* )
            {
            // InternalSeML.g:551:2: ( ( rule__MainModel__ImportsAssignment_0 ) )
            // InternalSeML.g:552:3: ( rule__MainModel__ImportsAssignment_0 )
            {
             before(grammarAccess.getMainModelAccess().getImportsAssignment_0()); 
            // InternalSeML.g:553:3: ( rule__MainModel__ImportsAssignment_0 )
            // InternalSeML.g:553:4: rule__MainModel__ImportsAssignment_0
            {
            pushFollow(FOLLOW_4);
            rule__MainModel__ImportsAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getMainModelAccess().getImportsAssignment_0()); 

            }

            // InternalSeML.g:556:2: ( ( rule__MainModel__ImportsAssignment_0 )* )
            // InternalSeML.g:557:3: ( rule__MainModel__ImportsAssignment_0 )*
            {
             before(grammarAccess.getMainModelAccess().getImportsAssignment_0()); 
            // InternalSeML.g:558:3: ( rule__MainModel__ImportsAssignment_0 )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==13) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalSeML.g:558:4: rule__MainModel__ImportsAssignment_0
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
    // InternalSeML.g:567:1: rule__MainModel__Group__1 : rule__MainModel__Group__1__Impl ;
    public final void rule__MainModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:571:1: ( rule__MainModel__Group__1__Impl )
            // InternalSeML.g:572:2: rule__MainModel__Group__1__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MainModel__Group__1__Impl();

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
    // InternalSeML.g:578:1: rule__MainModel__Group__1__Impl : ( ( rule__MainModel__SentencesAssignment_1 )* ) ;
    public final void rule__MainModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:582:1: ( ( ( rule__MainModel__SentencesAssignment_1 )* ) )
            // InternalSeML.g:583:1: ( ( rule__MainModel__SentencesAssignment_1 )* )
            {
            // InternalSeML.g:583:1: ( ( rule__MainModel__SentencesAssignment_1 )* )
            // InternalSeML.g:584:2: ( rule__MainModel__SentencesAssignment_1 )*
            {
             before(grammarAccess.getMainModelAccess().getSentencesAssignment_1()); 
            // InternalSeML.g:585:2: ( rule__MainModel__SentencesAssignment_1 )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==RULE_IRI||LA5_0==14||LA5_0==16) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalSeML.g:585:3: rule__MainModel__SentencesAssignment_1
            	    {
            	    pushFollow(FOLLOW_5);
            	    rule__MainModel__SentencesAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

             after(grammarAccess.getMainModelAccess().getSentencesAssignment_1()); 

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


    // $ANTLR start "rule__ImportModel__Group__0"
    // InternalSeML.g:594:1: rule__ImportModel__Group__0 : rule__ImportModel__Group__0__Impl rule__ImportModel__Group__1 ;
    public final void rule__ImportModel__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:598:1: ( rule__ImportModel__Group__0__Impl rule__ImportModel__Group__1 )
            // InternalSeML.g:599:2: rule__ImportModel__Group__0__Impl rule__ImportModel__Group__1
            {
            pushFollow(FOLLOW_6);
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
    // InternalSeML.g:606:1: rule__ImportModel__Group__0__Impl : ( ( ( rule__ImportModel__ComponentsAssignment_0 ) ) ( ( rule__ImportModel__ComponentsAssignment_0 )* ) ) ;
    public final void rule__ImportModel__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:610:1: ( ( ( ( rule__ImportModel__ComponentsAssignment_0 ) ) ( ( rule__ImportModel__ComponentsAssignment_0 )* ) ) )
            // InternalSeML.g:611:1: ( ( ( rule__ImportModel__ComponentsAssignment_0 ) ) ( ( rule__ImportModel__ComponentsAssignment_0 )* ) )
            {
            // InternalSeML.g:611:1: ( ( ( rule__ImportModel__ComponentsAssignment_0 ) ) ( ( rule__ImportModel__ComponentsAssignment_0 )* ) )
            // InternalSeML.g:612:2: ( ( rule__ImportModel__ComponentsAssignment_0 ) ) ( ( rule__ImportModel__ComponentsAssignment_0 )* )
            {
            // InternalSeML.g:612:2: ( ( rule__ImportModel__ComponentsAssignment_0 ) )
            // InternalSeML.g:613:3: ( rule__ImportModel__ComponentsAssignment_0 )
            {
             before(grammarAccess.getImportModelAccess().getComponentsAssignment_0()); 
            // InternalSeML.g:614:3: ( rule__ImportModel__ComponentsAssignment_0 )
            // InternalSeML.g:614:4: rule__ImportModel__ComponentsAssignment_0
            {
            pushFollow(FOLLOW_7);
            rule__ImportModel__ComponentsAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getImportModelAccess().getComponentsAssignment_0()); 

            }

            // InternalSeML.g:617:2: ( ( rule__ImportModel__ComponentsAssignment_0 )* )
            // InternalSeML.g:618:3: ( rule__ImportModel__ComponentsAssignment_0 )*
            {
             before(grammarAccess.getImportModelAccess().getComponentsAssignment_0()); 
            // InternalSeML.g:619:3: ( rule__ImportModel__ComponentsAssignment_0 )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( ((LA6_0>=17 && LA6_0<=20)) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalSeML.g:619:4: rule__ImportModel__ComponentsAssignment_0
            	    {
            	    pushFollow(FOLLOW_7);
            	    rule__ImportModel__ComponentsAssignment_0();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

             after(grammarAccess.getImportModelAccess().getComponentsAssignment_0()); 

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
    // InternalSeML.g:628:1: rule__ImportModel__Group__1 : rule__ImportModel__Group__1__Impl rule__ImportModel__Group__2 ;
    public final void rule__ImportModel__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:632:1: ( rule__ImportModel__Group__1__Impl rule__ImportModel__Group__2 )
            // InternalSeML.g:633:2: rule__ImportModel__Group__1__Impl rule__ImportModel__Group__2
            {
            pushFollow(FOLLOW_6);
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
    // InternalSeML.g:640:1: rule__ImportModel__Group__1__Impl : ( ( rule__ImportModel__CharacteristicsAssignment_1 )* ) ;
    public final void rule__ImportModel__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:644:1: ( ( ( rule__ImportModel__CharacteristicsAssignment_1 )* ) )
            // InternalSeML.g:645:1: ( ( rule__ImportModel__CharacteristicsAssignment_1 )* )
            {
            // InternalSeML.g:645:1: ( ( rule__ImportModel__CharacteristicsAssignment_1 )* )
            // InternalSeML.g:646:2: ( rule__ImportModel__CharacteristicsAssignment_1 )*
            {
             before(grammarAccess.getImportModelAccess().getCharacteristicsAssignment_1()); 
            // InternalSeML.g:647:2: ( rule__ImportModel__CharacteristicsAssignment_1 )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==22) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalSeML.g:647:3: rule__ImportModel__CharacteristicsAssignment_1
            	    {
            	    pushFollow(FOLLOW_8);
            	    rule__ImportModel__CharacteristicsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

             after(grammarAccess.getImportModelAccess().getCharacteristicsAssignment_1()); 

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
    // InternalSeML.g:655:1: rule__ImportModel__Group__2 : rule__ImportModel__Group__2__Impl rule__ImportModel__Group__3 ;
    public final void rule__ImportModel__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:659:1: ( rule__ImportModel__Group__2__Impl rule__ImportModel__Group__3 )
            // InternalSeML.g:660:2: rule__ImportModel__Group__2__Impl rule__ImportModel__Group__3
            {
            pushFollow(FOLLOW_6);
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
    // InternalSeML.g:667:1: rule__ImportModel__Group__2__Impl : ( ( rule__ImportModel__ObjectPropertiesAssignment_2 )* ) ;
    public final void rule__ImportModel__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:671:1: ( ( ( rule__ImportModel__ObjectPropertiesAssignment_2 )* ) )
            // InternalSeML.g:672:1: ( ( rule__ImportModel__ObjectPropertiesAssignment_2 )* )
            {
            // InternalSeML.g:672:1: ( ( rule__ImportModel__ObjectPropertiesAssignment_2 )* )
            // InternalSeML.g:673:2: ( rule__ImportModel__ObjectPropertiesAssignment_2 )*
            {
             before(grammarAccess.getImportModelAccess().getObjectPropertiesAssignment_2()); 
            // InternalSeML.g:674:2: ( rule__ImportModel__ObjectPropertiesAssignment_2 )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==21) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalSeML.g:674:3: rule__ImportModel__ObjectPropertiesAssignment_2
            	    {
            	    pushFollow(FOLLOW_9);
            	    rule__ImportModel__ObjectPropertiesAssignment_2();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

             after(grammarAccess.getImportModelAccess().getObjectPropertiesAssignment_2()); 

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
    // InternalSeML.g:682:1: rule__ImportModel__Group__3 : rule__ImportModel__Group__3__Impl ;
    public final void rule__ImportModel__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:686:1: ( rule__ImportModel__Group__3__Impl )
            // InternalSeML.g:687:2: rule__ImportModel__Group__3__Impl
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
    // InternalSeML.g:693:1: rule__ImportModel__Group__3__Impl : ( ( rule__ImportModel__MetaIndividualsAssignment_3 )* ) ;
    public final void rule__ImportModel__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:697:1: ( ( ( rule__ImportModel__MetaIndividualsAssignment_3 )* ) )
            // InternalSeML.g:698:1: ( ( rule__ImportModel__MetaIndividualsAssignment_3 )* )
            {
            // InternalSeML.g:698:1: ( ( rule__ImportModel__MetaIndividualsAssignment_3 )* )
            // InternalSeML.g:699:2: ( rule__ImportModel__MetaIndividualsAssignment_3 )*
            {
             before(grammarAccess.getImportModelAccess().getMetaIndividualsAssignment_3()); 
            // InternalSeML.g:700:2: ( rule__ImportModel__MetaIndividualsAssignment_3 )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==23) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalSeML.g:700:3: rule__ImportModel__MetaIndividualsAssignment_3
            	    {
            	    pushFollow(FOLLOW_10);
            	    rule__ImportModel__MetaIndividualsAssignment_3();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

             after(grammarAccess.getImportModelAccess().getMetaIndividualsAssignment_3()); 

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
    // InternalSeML.g:709:1: rule__Import__Group__0 : rule__Import__Group__0__Impl rule__Import__Group__1 ;
    public final void rule__Import__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:713:1: ( rule__Import__Group__0__Impl rule__Import__Group__1 )
            // InternalSeML.g:714:2: rule__Import__Group__0__Impl rule__Import__Group__1
            {
            pushFollow(FOLLOW_11);
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
    // InternalSeML.g:721:1: rule__Import__Group__0__Impl : ( 'import' ) ;
    public final void rule__Import__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:725:1: ( ( 'import' ) )
            // InternalSeML.g:726:1: ( 'import' )
            {
            // InternalSeML.g:726:1: ( 'import' )
            // InternalSeML.g:727:2: 'import'
            {
             before(grammarAccess.getImportAccess().getImportKeyword_0()); 
            match(input,13,FOLLOW_2); 
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
    // InternalSeML.g:736:1: rule__Import__Group__1 : rule__Import__Group__1__Impl ;
    public final void rule__Import__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:740:1: ( rule__Import__Group__1__Impl )
            // InternalSeML.g:741:2: rule__Import__Group__1__Impl
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
    // InternalSeML.g:747:1: rule__Import__Group__1__Impl : ( ( rule__Import__NameAssignment_1 ) ) ;
    public final void rule__Import__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:751:1: ( ( ( rule__Import__NameAssignment_1 ) ) )
            // InternalSeML.g:752:1: ( ( rule__Import__NameAssignment_1 ) )
            {
            // InternalSeML.g:752:1: ( ( rule__Import__NameAssignment_1 ) )
            // InternalSeML.g:753:2: ( rule__Import__NameAssignment_1 )
            {
             before(grammarAccess.getImportAccess().getNameAssignment_1()); 
            // InternalSeML.g:754:2: ( rule__Import__NameAssignment_1 )
            // InternalSeML.g:754:3: rule__Import__NameAssignment_1
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


    // $ANTLR start "rule__Individual__Group__0"
    // InternalSeML.g:763:1: rule__Individual__Group__0 : rule__Individual__Group__0__Impl rule__Individual__Group__1 ;
    public final void rule__Individual__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:767:1: ( rule__Individual__Group__0__Impl rule__Individual__Group__1 )
            // InternalSeML.g:768:2: rule__Individual__Group__0__Impl rule__Individual__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__Individual__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Individual__Group__1();

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
    // $ANTLR end "rule__Individual__Group__0"


    // $ANTLR start "rule__Individual__Group__0__Impl"
    // InternalSeML.g:775:1: rule__Individual__Group__0__Impl : ( 'new' ) ;
    public final void rule__Individual__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:779:1: ( ( 'new' ) )
            // InternalSeML.g:780:1: ( 'new' )
            {
            // InternalSeML.g:780:1: ( 'new' )
            // InternalSeML.g:781:2: 'new'
            {
             before(grammarAccess.getIndividualAccess().getNewKeyword_0()); 
            match(input,14,FOLLOW_2); 
             after(grammarAccess.getIndividualAccess().getNewKeyword_0()); 

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
    // $ANTLR end "rule__Individual__Group__0__Impl"


    // $ANTLR start "rule__Individual__Group__1"
    // InternalSeML.g:790:1: rule__Individual__Group__1 : rule__Individual__Group__1__Impl rule__Individual__Group__2 ;
    public final void rule__Individual__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:794:1: ( rule__Individual__Group__1__Impl rule__Individual__Group__2 )
            // InternalSeML.g:795:2: rule__Individual__Group__1__Impl rule__Individual__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__Individual__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Individual__Group__2();

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
    // $ANTLR end "rule__Individual__Group__1"


    // $ANTLR start "rule__Individual__Group__1__Impl"
    // InternalSeML.g:802:1: rule__Individual__Group__1__Impl : ( ( ( rule__Individual__ClsAssignment_1 ) ) ( ( rule__Individual__ClsAssignment_1 )* ) ) ;
    public final void rule__Individual__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:806:1: ( ( ( ( rule__Individual__ClsAssignment_1 ) ) ( ( rule__Individual__ClsAssignment_1 )* ) ) )
            // InternalSeML.g:807:1: ( ( ( rule__Individual__ClsAssignment_1 ) ) ( ( rule__Individual__ClsAssignment_1 )* ) )
            {
            // InternalSeML.g:807:1: ( ( ( rule__Individual__ClsAssignment_1 ) ) ( ( rule__Individual__ClsAssignment_1 )* ) )
            // InternalSeML.g:808:2: ( ( rule__Individual__ClsAssignment_1 ) ) ( ( rule__Individual__ClsAssignment_1 )* )
            {
            // InternalSeML.g:808:2: ( ( rule__Individual__ClsAssignment_1 ) )
            // InternalSeML.g:809:3: ( rule__Individual__ClsAssignment_1 )
            {
             before(grammarAccess.getIndividualAccess().getClsAssignment_1()); 
            // InternalSeML.g:810:3: ( rule__Individual__ClsAssignment_1 )
            // InternalSeML.g:810:4: rule__Individual__ClsAssignment_1
            {
            pushFollow(FOLLOW_13);
            rule__Individual__ClsAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getIndividualAccess().getClsAssignment_1()); 

            }

            // InternalSeML.g:813:2: ( ( rule__Individual__ClsAssignment_1 )* )
            // InternalSeML.g:814:3: ( rule__Individual__ClsAssignment_1 )*
            {
             before(grammarAccess.getIndividualAccess().getClsAssignment_1()); 
            // InternalSeML.g:815:3: ( rule__Individual__ClsAssignment_1 )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==RULE_IRI) ) {
                    int LA10_1 = input.LA(2);

                    if ( (LA10_1==RULE_IRI) ) {
                        alt10=1;
                    }


                }


                switch (alt10) {
            	case 1 :
            	    // InternalSeML.g:815:4: rule__Individual__ClsAssignment_1
            	    {
            	    pushFollow(FOLLOW_13);
            	    rule__Individual__ClsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

             after(grammarAccess.getIndividualAccess().getClsAssignment_1()); 

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
    // $ANTLR end "rule__Individual__Group__1__Impl"


    // $ANTLR start "rule__Individual__Group__2"
    // InternalSeML.g:824:1: rule__Individual__Group__2 : rule__Individual__Group__2__Impl rule__Individual__Group__3 ;
    public final void rule__Individual__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:828:1: ( rule__Individual__Group__2__Impl rule__Individual__Group__3 )
            // InternalSeML.g:829:2: rule__Individual__Group__2__Impl rule__Individual__Group__3
            {
            pushFollow(FOLLOW_14);
            rule__Individual__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Individual__Group__3();

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
    // $ANTLR end "rule__Individual__Group__2"


    // $ANTLR start "rule__Individual__Group__2__Impl"
    // InternalSeML.g:836:1: rule__Individual__Group__2__Impl : ( ( rule__Individual__NameAssignment_2 ) ) ;
    public final void rule__Individual__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:840:1: ( ( ( rule__Individual__NameAssignment_2 ) ) )
            // InternalSeML.g:841:1: ( ( rule__Individual__NameAssignment_2 ) )
            {
            // InternalSeML.g:841:1: ( ( rule__Individual__NameAssignment_2 ) )
            // InternalSeML.g:842:2: ( rule__Individual__NameAssignment_2 )
            {
             before(grammarAccess.getIndividualAccess().getNameAssignment_2()); 
            // InternalSeML.g:843:2: ( rule__Individual__NameAssignment_2 )
            // InternalSeML.g:843:3: rule__Individual__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Individual__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getIndividualAccess().getNameAssignment_2()); 

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
    // $ANTLR end "rule__Individual__Group__2__Impl"


    // $ANTLR start "rule__Individual__Group__3"
    // InternalSeML.g:851:1: rule__Individual__Group__3 : rule__Individual__Group__3__Impl ;
    public final void rule__Individual__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:855:1: ( rule__Individual__Group__3__Impl )
            // InternalSeML.g:856:2: rule__Individual__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Individual__Group__3__Impl();

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
    // $ANTLR end "rule__Individual__Group__3"


    // $ANTLR start "rule__Individual__Group__3__Impl"
    // InternalSeML.g:862:1: rule__Individual__Group__3__Impl : ( '\\n' ) ;
    public final void rule__Individual__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:866:1: ( ( '\\n' ) )
            // InternalSeML.g:867:1: ( '\\n' )
            {
            // InternalSeML.g:867:1: ( '\\n' )
            // InternalSeML.g:868:2: '\\n'
            {
             before(grammarAccess.getIndividualAccess().getControl000aKeyword_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getIndividualAccess().getControl000aKeyword_3()); 

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
    // $ANTLR end "rule__Individual__Group__3__Impl"


    // $ANTLR start "rule__UseCharacteristic__Group__0"
    // InternalSeML.g:878:1: rule__UseCharacteristic__Group__0 : rule__UseCharacteristic__Group__0__Impl rule__UseCharacteristic__Group__1 ;
    public final void rule__UseCharacteristic__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:882:1: ( rule__UseCharacteristic__Group__0__Impl rule__UseCharacteristic__Group__1 )
            // InternalSeML.g:883:2: rule__UseCharacteristic__Group__0__Impl rule__UseCharacteristic__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__UseCharacteristic__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UseCharacteristic__Group__1();

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
    // $ANTLR end "rule__UseCharacteristic__Group__0"


    // $ANTLR start "rule__UseCharacteristic__Group__0__Impl"
    // InternalSeML.g:890:1: rule__UseCharacteristic__Group__0__Impl : ( 'use' ) ;
    public final void rule__UseCharacteristic__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:894:1: ( ( 'use' ) )
            // InternalSeML.g:895:1: ( 'use' )
            {
            // InternalSeML.g:895:1: ( 'use' )
            // InternalSeML.g:896:2: 'use'
            {
             before(grammarAccess.getUseCharacteristicAccess().getUseKeyword_0()); 
            match(input,16,FOLLOW_2); 
             after(grammarAccess.getUseCharacteristicAccess().getUseKeyword_0()); 

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
    // $ANTLR end "rule__UseCharacteristic__Group__0__Impl"


    // $ANTLR start "rule__UseCharacteristic__Group__1"
    // InternalSeML.g:905:1: rule__UseCharacteristic__Group__1 : rule__UseCharacteristic__Group__1__Impl rule__UseCharacteristic__Group__2 ;
    public final void rule__UseCharacteristic__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:909:1: ( rule__UseCharacteristic__Group__1__Impl rule__UseCharacteristic__Group__2 )
            // InternalSeML.g:910:2: rule__UseCharacteristic__Group__1__Impl rule__UseCharacteristic__Group__2
            {
            pushFollow(FOLLOW_14);
            rule__UseCharacteristic__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__UseCharacteristic__Group__2();

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
    // $ANTLR end "rule__UseCharacteristic__Group__1"


    // $ANTLR start "rule__UseCharacteristic__Group__1__Impl"
    // InternalSeML.g:917:1: rule__UseCharacteristic__Group__1__Impl : ( ( rule__UseCharacteristic__NameAssignment_1 ) ) ;
    public final void rule__UseCharacteristic__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:921:1: ( ( ( rule__UseCharacteristic__NameAssignment_1 ) ) )
            // InternalSeML.g:922:1: ( ( rule__UseCharacteristic__NameAssignment_1 ) )
            {
            // InternalSeML.g:922:1: ( ( rule__UseCharacteristic__NameAssignment_1 ) )
            // InternalSeML.g:923:2: ( rule__UseCharacteristic__NameAssignment_1 )
            {
             before(grammarAccess.getUseCharacteristicAccess().getNameAssignment_1()); 
            // InternalSeML.g:924:2: ( rule__UseCharacteristic__NameAssignment_1 )
            // InternalSeML.g:924:3: rule__UseCharacteristic__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__UseCharacteristic__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getUseCharacteristicAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__UseCharacteristic__Group__1__Impl"


    // $ANTLR start "rule__UseCharacteristic__Group__2"
    // InternalSeML.g:932:1: rule__UseCharacteristic__Group__2 : rule__UseCharacteristic__Group__2__Impl ;
    public final void rule__UseCharacteristic__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:936:1: ( rule__UseCharacteristic__Group__2__Impl )
            // InternalSeML.g:937:2: rule__UseCharacteristic__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__UseCharacteristic__Group__2__Impl();

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
    // $ANTLR end "rule__UseCharacteristic__Group__2"


    // $ANTLR start "rule__UseCharacteristic__Group__2__Impl"
    // InternalSeML.g:943:1: rule__UseCharacteristic__Group__2__Impl : ( '\\n' ) ;
    public final void rule__UseCharacteristic__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:947:1: ( ( '\\n' ) )
            // InternalSeML.g:948:1: ( '\\n' )
            {
            // InternalSeML.g:948:1: ( '\\n' )
            // InternalSeML.g:949:2: '\\n'
            {
             before(grammarAccess.getUseCharacteristicAccess().getControl000aKeyword_2()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getUseCharacteristicAccess().getControl000aKeyword_2()); 

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
    // $ANTLR end "rule__UseCharacteristic__Group__2__Impl"


    // $ANTLR start "rule__Relation__Group__0"
    // InternalSeML.g:959:1: rule__Relation__Group__0 : rule__Relation__Group__0__Impl rule__Relation__Group__1 ;
    public final void rule__Relation__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:963:1: ( rule__Relation__Group__0__Impl rule__Relation__Group__1 )
            // InternalSeML.g:964:2: rule__Relation__Group__0__Impl rule__Relation__Group__1
            {
            pushFollow(FOLLOW_12);
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
    // InternalSeML.g:971:1: rule__Relation__Group__0__Impl : ( ( rule__Relation__Instance1Assignment_0 ) ) ;
    public final void rule__Relation__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:975:1: ( ( ( rule__Relation__Instance1Assignment_0 ) ) )
            // InternalSeML.g:976:1: ( ( rule__Relation__Instance1Assignment_0 ) )
            {
            // InternalSeML.g:976:1: ( ( rule__Relation__Instance1Assignment_0 ) )
            // InternalSeML.g:977:2: ( rule__Relation__Instance1Assignment_0 )
            {
             before(grammarAccess.getRelationAccess().getInstance1Assignment_0()); 
            // InternalSeML.g:978:2: ( rule__Relation__Instance1Assignment_0 )
            // InternalSeML.g:978:3: rule__Relation__Instance1Assignment_0
            {
            pushFollow(FOLLOW_2);
            rule__Relation__Instance1Assignment_0();

            state._fsp--;


            }

             after(grammarAccess.getRelationAccess().getInstance1Assignment_0()); 

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
    // InternalSeML.g:986:1: rule__Relation__Group__1 : rule__Relation__Group__1__Impl rule__Relation__Group__2 ;
    public final void rule__Relation__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:990:1: ( rule__Relation__Group__1__Impl rule__Relation__Group__2 )
            // InternalSeML.g:991:2: rule__Relation__Group__1__Impl rule__Relation__Group__2
            {
            pushFollow(FOLLOW_12);
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
    // InternalSeML.g:998:1: rule__Relation__Group__1__Impl : ( ( rule__Relation__ObjAssignment_1 ) ) ;
    public final void rule__Relation__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1002:1: ( ( ( rule__Relation__ObjAssignment_1 ) ) )
            // InternalSeML.g:1003:1: ( ( rule__Relation__ObjAssignment_1 ) )
            {
            // InternalSeML.g:1003:1: ( ( rule__Relation__ObjAssignment_1 ) )
            // InternalSeML.g:1004:2: ( rule__Relation__ObjAssignment_1 )
            {
             before(grammarAccess.getRelationAccess().getObjAssignment_1()); 
            // InternalSeML.g:1005:2: ( rule__Relation__ObjAssignment_1 )
            // InternalSeML.g:1005:3: rule__Relation__ObjAssignment_1
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
    // InternalSeML.g:1013:1: rule__Relation__Group__2 : rule__Relation__Group__2__Impl rule__Relation__Group__3 ;
    public final void rule__Relation__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1017:1: ( rule__Relation__Group__2__Impl rule__Relation__Group__3 )
            // InternalSeML.g:1018:2: rule__Relation__Group__2__Impl rule__Relation__Group__3
            {
            pushFollow(FOLLOW_14);
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
    // InternalSeML.g:1025:1: rule__Relation__Group__2__Impl : ( ( rule__Relation__Instance2Assignment_2 ) ) ;
    public final void rule__Relation__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1029:1: ( ( ( rule__Relation__Instance2Assignment_2 ) ) )
            // InternalSeML.g:1030:1: ( ( rule__Relation__Instance2Assignment_2 ) )
            {
            // InternalSeML.g:1030:1: ( ( rule__Relation__Instance2Assignment_2 ) )
            // InternalSeML.g:1031:2: ( rule__Relation__Instance2Assignment_2 )
            {
             before(grammarAccess.getRelationAccess().getInstance2Assignment_2()); 
            // InternalSeML.g:1032:2: ( rule__Relation__Instance2Assignment_2 )
            // InternalSeML.g:1032:3: rule__Relation__Instance2Assignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Relation__Instance2Assignment_2();

            state._fsp--;


            }

             after(grammarAccess.getRelationAccess().getInstance2Assignment_2()); 

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
    // InternalSeML.g:1040:1: rule__Relation__Group__3 : rule__Relation__Group__3__Impl ;
    public final void rule__Relation__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1044:1: ( rule__Relation__Group__3__Impl )
            // InternalSeML.g:1045:2: rule__Relation__Group__3__Impl
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
    // InternalSeML.g:1051:1: rule__Relation__Group__3__Impl : ( '\\n' ) ;
    public final void rule__Relation__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1055:1: ( ( '\\n' ) )
            // InternalSeML.g:1056:1: ( '\\n' )
            {
            // InternalSeML.g:1056:1: ( '\\n' )
            // InternalSeML.g:1057:2: '\\n'
            {
             before(grammarAccess.getRelationAccess().getControl000aKeyword_3()); 
            match(input,15,FOLLOW_2); 
             after(grammarAccess.getRelationAccess().getControl000aKeyword_3()); 

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


    // $ANTLR start "rule__Component_Process__Group__0"
    // InternalSeML.g:1067:1: rule__Component_Process__Group__0 : rule__Component_Process__Group__0__Impl rule__Component_Process__Group__1 ;
    public final void rule__Component_Process__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1071:1: ( rule__Component_Process__Group__0__Impl rule__Component_Process__Group__1 )
            // InternalSeML.g:1072:2: rule__Component_Process__Group__0__Impl rule__Component_Process__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__Component_Process__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component_Process__Group__1();

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
    // $ANTLR end "rule__Component_Process__Group__0"


    // $ANTLR start "rule__Component_Process__Group__0__Impl"
    // InternalSeML.g:1079:1: rule__Component_Process__Group__0__Impl : ( 'CompProcess' ) ;
    public final void rule__Component_Process__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1083:1: ( ( 'CompProcess' ) )
            // InternalSeML.g:1084:1: ( 'CompProcess' )
            {
            // InternalSeML.g:1084:1: ( 'CompProcess' )
            // InternalSeML.g:1085:2: 'CompProcess'
            {
             before(grammarAccess.getComponent_ProcessAccess().getCompProcessKeyword_0()); 
            match(input,17,FOLLOW_2); 
             after(grammarAccess.getComponent_ProcessAccess().getCompProcessKeyword_0()); 

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
    // $ANTLR end "rule__Component_Process__Group__0__Impl"


    // $ANTLR start "rule__Component_Process__Group__1"
    // InternalSeML.g:1094:1: rule__Component_Process__Group__1 : rule__Component_Process__Group__1__Impl rule__Component_Process__Group__2 ;
    public final void rule__Component_Process__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1098:1: ( rule__Component_Process__Group__1__Impl rule__Component_Process__Group__2 )
            // InternalSeML.g:1099:2: rule__Component_Process__Group__1__Impl rule__Component_Process__Group__2
            {
            pushFollow(FOLLOW_11);
            rule__Component_Process__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component_Process__Group__2();

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
    // $ANTLR end "rule__Component_Process__Group__1"


    // $ANTLR start "rule__Component_Process__Group__1__Impl"
    // InternalSeML.g:1106:1: rule__Component_Process__Group__1__Impl : ( ( rule__Component_Process__NameAssignment_1 ) ) ;
    public final void rule__Component_Process__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1110:1: ( ( ( rule__Component_Process__NameAssignment_1 ) ) )
            // InternalSeML.g:1111:1: ( ( rule__Component_Process__NameAssignment_1 ) )
            {
            // InternalSeML.g:1111:1: ( ( rule__Component_Process__NameAssignment_1 ) )
            // InternalSeML.g:1112:2: ( rule__Component_Process__NameAssignment_1 )
            {
             before(grammarAccess.getComponent_ProcessAccess().getNameAssignment_1()); 
            // InternalSeML.g:1113:2: ( rule__Component_Process__NameAssignment_1 )
            // InternalSeML.g:1113:3: rule__Component_Process__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Component_Process__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getComponent_ProcessAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__Component_Process__Group__1__Impl"


    // $ANTLR start "rule__Component_Process__Group__2"
    // InternalSeML.g:1121:1: rule__Component_Process__Group__2 : rule__Component_Process__Group__2__Impl ;
    public final void rule__Component_Process__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1125:1: ( rule__Component_Process__Group__2__Impl )
            // InternalSeML.g:1126:2: rule__Component_Process__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Component_Process__Group__2__Impl();

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
    // $ANTLR end "rule__Component_Process__Group__2"


    // $ANTLR start "rule__Component_Process__Group__2__Impl"
    // InternalSeML.g:1132:1: rule__Component_Process__Group__2__Impl : ( ( rule__Component_Process__IriAssignment_2 ) ) ;
    public final void rule__Component_Process__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1136:1: ( ( ( rule__Component_Process__IriAssignment_2 ) ) )
            // InternalSeML.g:1137:1: ( ( rule__Component_Process__IriAssignment_2 ) )
            {
            // InternalSeML.g:1137:1: ( ( rule__Component_Process__IriAssignment_2 ) )
            // InternalSeML.g:1138:2: ( rule__Component_Process__IriAssignment_2 )
            {
             before(grammarAccess.getComponent_ProcessAccess().getIriAssignment_2()); 
            // InternalSeML.g:1139:2: ( rule__Component_Process__IriAssignment_2 )
            // InternalSeML.g:1139:3: rule__Component_Process__IriAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Component_Process__IriAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getComponent_ProcessAccess().getIriAssignment_2()); 

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
    // $ANTLR end "rule__Component_Process__Group__2__Impl"


    // $ANTLR start "rule__Component_Event__Group__0"
    // InternalSeML.g:1148:1: rule__Component_Event__Group__0 : rule__Component_Event__Group__0__Impl rule__Component_Event__Group__1 ;
    public final void rule__Component_Event__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1152:1: ( rule__Component_Event__Group__0__Impl rule__Component_Event__Group__1 )
            // InternalSeML.g:1153:2: rule__Component_Event__Group__0__Impl rule__Component_Event__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__Component_Event__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component_Event__Group__1();

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
    // $ANTLR end "rule__Component_Event__Group__0"


    // $ANTLR start "rule__Component_Event__Group__0__Impl"
    // InternalSeML.g:1160:1: rule__Component_Event__Group__0__Impl : ( 'CompEvent' ) ;
    public final void rule__Component_Event__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1164:1: ( ( 'CompEvent' ) )
            // InternalSeML.g:1165:1: ( 'CompEvent' )
            {
            // InternalSeML.g:1165:1: ( 'CompEvent' )
            // InternalSeML.g:1166:2: 'CompEvent'
            {
             before(grammarAccess.getComponent_EventAccess().getCompEventKeyword_0()); 
            match(input,18,FOLLOW_2); 
             after(grammarAccess.getComponent_EventAccess().getCompEventKeyword_0()); 

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
    // $ANTLR end "rule__Component_Event__Group__0__Impl"


    // $ANTLR start "rule__Component_Event__Group__1"
    // InternalSeML.g:1175:1: rule__Component_Event__Group__1 : rule__Component_Event__Group__1__Impl rule__Component_Event__Group__2 ;
    public final void rule__Component_Event__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1179:1: ( rule__Component_Event__Group__1__Impl rule__Component_Event__Group__2 )
            // InternalSeML.g:1180:2: rule__Component_Event__Group__1__Impl rule__Component_Event__Group__2
            {
            pushFollow(FOLLOW_11);
            rule__Component_Event__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component_Event__Group__2();

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
    // $ANTLR end "rule__Component_Event__Group__1"


    // $ANTLR start "rule__Component_Event__Group__1__Impl"
    // InternalSeML.g:1187:1: rule__Component_Event__Group__1__Impl : ( ( rule__Component_Event__NameAssignment_1 ) ) ;
    public final void rule__Component_Event__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1191:1: ( ( ( rule__Component_Event__NameAssignment_1 ) ) )
            // InternalSeML.g:1192:1: ( ( rule__Component_Event__NameAssignment_1 ) )
            {
            // InternalSeML.g:1192:1: ( ( rule__Component_Event__NameAssignment_1 ) )
            // InternalSeML.g:1193:2: ( rule__Component_Event__NameAssignment_1 )
            {
             before(grammarAccess.getComponent_EventAccess().getNameAssignment_1()); 
            // InternalSeML.g:1194:2: ( rule__Component_Event__NameAssignment_1 )
            // InternalSeML.g:1194:3: rule__Component_Event__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Component_Event__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getComponent_EventAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__Component_Event__Group__1__Impl"


    // $ANTLR start "rule__Component_Event__Group__2"
    // InternalSeML.g:1202:1: rule__Component_Event__Group__2 : rule__Component_Event__Group__2__Impl ;
    public final void rule__Component_Event__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1206:1: ( rule__Component_Event__Group__2__Impl )
            // InternalSeML.g:1207:2: rule__Component_Event__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Component_Event__Group__2__Impl();

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
    // $ANTLR end "rule__Component_Event__Group__2"


    // $ANTLR start "rule__Component_Event__Group__2__Impl"
    // InternalSeML.g:1213:1: rule__Component_Event__Group__2__Impl : ( ( rule__Component_Event__IriAssignment_2 ) ) ;
    public final void rule__Component_Event__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1217:1: ( ( ( rule__Component_Event__IriAssignment_2 ) ) )
            // InternalSeML.g:1218:1: ( ( rule__Component_Event__IriAssignment_2 ) )
            {
            // InternalSeML.g:1218:1: ( ( rule__Component_Event__IriAssignment_2 ) )
            // InternalSeML.g:1219:2: ( rule__Component_Event__IriAssignment_2 )
            {
             before(grammarAccess.getComponent_EventAccess().getIriAssignment_2()); 
            // InternalSeML.g:1220:2: ( rule__Component_Event__IriAssignment_2 )
            // InternalSeML.g:1220:3: rule__Component_Event__IriAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Component_Event__IriAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getComponent_EventAccess().getIriAssignment_2()); 

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
    // $ANTLR end "rule__Component_Event__Group__2__Impl"


    // $ANTLR start "rule__Component_Property__Group__0"
    // InternalSeML.g:1229:1: rule__Component_Property__Group__0 : rule__Component_Property__Group__0__Impl rule__Component_Property__Group__1 ;
    public final void rule__Component_Property__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1233:1: ( rule__Component_Property__Group__0__Impl rule__Component_Property__Group__1 )
            // InternalSeML.g:1234:2: rule__Component_Property__Group__0__Impl rule__Component_Property__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__Component_Property__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component_Property__Group__1();

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
    // $ANTLR end "rule__Component_Property__Group__0"


    // $ANTLR start "rule__Component_Property__Group__0__Impl"
    // InternalSeML.g:1241:1: rule__Component_Property__Group__0__Impl : ( 'CompProperty' ) ;
    public final void rule__Component_Property__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1245:1: ( ( 'CompProperty' ) )
            // InternalSeML.g:1246:1: ( 'CompProperty' )
            {
            // InternalSeML.g:1246:1: ( 'CompProperty' )
            // InternalSeML.g:1247:2: 'CompProperty'
            {
             before(grammarAccess.getComponent_PropertyAccess().getCompPropertyKeyword_0()); 
            match(input,19,FOLLOW_2); 
             after(grammarAccess.getComponent_PropertyAccess().getCompPropertyKeyword_0()); 

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
    // $ANTLR end "rule__Component_Property__Group__0__Impl"


    // $ANTLR start "rule__Component_Property__Group__1"
    // InternalSeML.g:1256:1: rule__Component_Property__Group__1 : rule__Component_Property__Group__1__Impl rule__Component_Property__Group__2 ;
    public final void rule__Component_Property__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1260:1: ( rule__Component_Property__Group__1__Impl rule__Component_Property__Group__2 )
            // InternalSeML.g:1261:2: rule__Component_Property__Group__1__Impl rule__Component_Property__Group__2
            {
            pushFollow(FOLLOW_11);
            rule__Component_Property__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component_Property__Group__2();

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
    // $ANTLR end "rule__Component_Property__Group__1"


    // $ANTLR start "rule__Component_Property__Group__1__Impl"
    // InternalSeML.g:1268:1: rule__Component_Property__Group__1__Impl : ( ( rule__Component_Property__NameAssignment_1 ) ) ;
    public final void rule__Component_Property__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1272:1: ( ( ( rule__Component_Property__NameAssignment_1 ) ) )
            // InternalSeML.g:1273:1: ( ( rule__Component_Property__NameAssignment_1 ) )
            {
            // InternalSeML.g:1273:1: ( ( rule__Component_Property__NameAssignment_1 ) )
            // InternalSeML.g:1274:2: ( rule__Component_Property__NameAssignment_1 )
            {
             before(grammarAccess.getComponent_PropertyAccess().getNameAssignment_1()); 
            // InternalSeML.g:1275:2: ( rule__Component_Property__NameAssignment_1 )
            // InternalSeML.g:1275:3: rule__Component_Property__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Component_Property__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getComponent_PropertyAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__Component_Property__Group__1__Impl"


    // $ANTLR start "rule__Component_Property__Group__2"
    // InternalSeML.g:1283:1: rule__Component_Property__Group__2 : rule__Component_Property__Group__2__Impl ;
    public final void rule__Component_Property__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1287:1: ( rule__Component_Property__Group__2__Impl )
            // InternalSeML.g:1288:2: rule__Component_Property__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Component_Property__Group__2__Impl();

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
    // $ANTLR end "rule__Component_Property__Group__2"


    // $ANTLR start "rule__Component_Property__Group__2__Impl"
    // InternalSeML.g:1294:1: rule__Component_Property__Group__2__Impl : ( ( rule__Component_Property__IriAssignment_2 ) ) ;
    public final void rule__Component_Property__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1298:1: ( ( ( rule__Component_Property__IriAssignment_2 ) ) )
            // InternalSeML.g:1299:1: ( ( rule__Component_Property__IriAssignment_2 ) )
            {
            // InternalSeML.g:1299:1: ( ( rule__Component_Property__IriAssignment_2 ) )
            // InternalSeML.g:1300:2: ( rule__Component_Property__IriAssignment_2 )
            {
             before(grammarAccess.getComponent_PropertyAccess().getIriAssignment_2()); 
            // InternalSeML.g:1301:2: ( rule__Component_Property__IriAssignment_2 )
            // InternalSeML.g:1301:3: rule__Component_Property__IriAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Component_Property__IriAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getComponent_PropertyAccess().getIriAssignment_2()); 

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
    // $ANTLR end "rule__Component_Property__Group__2__Impl"


    // $ANTLR start "rule__Component_Entity__Group__0"
    // InternalSeML.g:1310:1: rule__Component_Entity__Group__0 : rule__Component_Entity__Group__0__Impl rule__Component_Entity__Group__1 ;
    public final void rule__Component_Entity__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1314:1: ( rule__Component_Entity__Group__0__Impl rule__Component_Entity__Group__1 )
            // InternalSeML.g:1315:2: rule__Component_Entity__Group__0__Impl rule__Component_Entity__Group__1
            {
            pushFollow(FOLLOW_12);
            rule__Component_Entity__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component_Entity__Group__1();

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
    // $ANTLR end "rule__Component_Entity__Group__0"


    // $ANTLR start "rule__Component_Entity__Group__0__Impl"
    // InternalSeML.g:1322:1: rule__Component_Entity__Group__0__Impl : ( 'CompEntity' ) ;
    public final void rule__Component_Entity__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1326:1: ( ( 'CompEntity' ) )
            // InternalSeML.g:1327:1: ( 'CompEntity' )
            {
            // InternalSeML.g:1327:1: ( 'CompEntity' )
            // InternalSeML.g:1328:2: 'CompEntity'
            {
             before(grammarAccess.getComponent_EntityAccess().getCompEntityKeyword_0()); 
            match(input,20,FOLLOW_2); 
             after(grammarAccess.getComponent_EntityAccess().getCompEntityKeyword_0()); 

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
    // $ANTLR end "rule__Component_Entity__Group__0__Impl"


    // $ANTLR start "rule__Component_Entity__Group__1"
    // InternalSeML.g:1337:1: rule__Component_Entity__Group__1 : rule__Component_Entity__Group__1__Impl rule__Component_Entity__Group__2 ;
    public final void rule__Component_Entity__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1341:1: ( rule__Component_Entity__Group__1__Impl rule__Component_Entity__Group__2 )
            // InternalSeML.g:1342:2: rule__Component_Entity__Group__1__Impl rule__Component_Entity__Group__2
            {
            pushFollow(FOLLOW_11);
            rule__Component_Entity__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__Component_Entity__Group__2();

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
    // $ANTLR end "rule__Component_Entity__Group__1"


    // $ANTLR start "rule__Component_Entity__Group__1__Impl"
    // InternalSeML.g:1349:1: rule__Component_Entity__Group__1__Impl : ( ( rule__Component_Entity__NameAssignment_1 ) ) ;
    public final void rule__Component_Entity__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1353:1: ( ( ( rule__Component_Entity__NameAssignment_1 ) ) )
            // InternalSeML.g:1354:1: ( ( rule__Component_Entity__NameAssignment_1 ) )
            {
            // InternalSeML.g:1354:1: ( ( rule__Component_Entity__NameAssignment_1 ) )
            // InternalSeML.g:1355:2: ( rule__Component_Entity__NameAssignment_1 )
            {
             before(grammarAccess.getComponent_EntityAccess().getNameAssignment_1()); 
            // InternalSeML.g:1356:2: ( rule__Component_Entity__NameAssignment_1 )
            // InternalSeML.g:1356:3: rule__Component_Entity__NameAssignment_1
            {
            pushFollow(FOLLOW_2);
            rule__Component_Entity__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getComponent_EntityAccess().getNameAssignment_1()); 

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
    // $ANTLR end "rule__Component_Entity__Group__1__Impl"


    // $ANTLR start "rule__Component_Entity__Group__2"
    // InternalSeML.g:1364:1: rule__Component_Entity__Group__2 : rule__Component_Entity__Group__2__Impl ;
    public final void rule__Component_Entity__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1368:1: ( rule__Component_Entity__Group__2__Impl )
            // InternalSeML.g:1369:2: rule__Component_Entity__Group__2__Impl
            {
            pushFollow(FOLLOW_2);
            rule__Component_Entity__Group__2__Impl();

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
    // $ANTLR end "rule__Component_Entity__Group__2"


    // $ANTLR start "rule__Component_Entity__Group__2__Impl"
    // InternalSeML.g:1375:1: rule__Component_Entity__Group__2__Impl : ( ( rule__Component_Entity__IriAssignment_2 ) ) ;
    public final void rule__Component_Entity__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1379:1: ( ( ( rule__Component_Entity__IriAssignment_2 ) ) )
            // InternalSeML.g:1380:1: ( ( rule__Component_Entity__IriAssignment_2 ) )
            {
            // InternalSeML.g:1380:1: ( ( rule__Component_Entity__IriAssignment_2 ) )
            // InternalSeML.g:1381:2: ( rule__Component_Entity__IriAssignment_2 )
            {
             before(grammarAccess.getComponent_EntityAccess().getIriAssignment_2()); 
            // InternalSeML.g:1382:2: ( rule__Component_Entity__IriAssignment_2 )
            // InternalSeML.g:1382:3: rule__Component_Entity__IriAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__Component_Entity__IriAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getComponent_EntityAccess().getIriAssignment_2()); 

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
    // $ANTLR end "rule__Component_Entity__Group__2__Impl"


    // $ANTLR start "rule__ObjectProperty__Group__0"
    // InternalSeML.g:1391:1: rule__ObjectProperty__Group__0 : rule__ObjectProperty__Group__0__Impl rule__ObjectProperty__Group__1 ;
    public final void rule__ObjectProperty__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1395:1: ( rule__ObjectProperty__Group__0__Impl rule__ObjectProperty__Group__1 )
            // InternalSeML.g:1396:2: rule__ObjectProperty__Group__0__Impl rule__ObjectProperty__Group__1
            {
            pushFollow(FOLLOW_12);
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
    // InternalSeML.g:1403:1: rule__ObjectProperty__Group__0__Impl : ( 'ObjectProperty' ) ;
    public final void rule__ObjectProperty__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1407:1: ( ( 'ObjectProperty' ) )
            // InternalSeML.g:1408:1: ( 'ObjectProperty' )
            {
            // InternalSeML.g:1408:1: ( 'ObjectProperty' )
            // InternalSeML.g:1409:2: 'ObjectProperty'
            {
             before(grammarAccess.getObjectPropertyAccess().getObjectPropertyKeyword_0()); 
            match(input,21,FOLLOW_2); 
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
    // InternalSeML.g:1418:1: rule__ObjectProperty__Group__1 : rule__ObjectProperty__Group__1__Impl rule__ObjectProperty__Group__2 ;
    public final void rule__ObjectProperty__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1422:1: ( rule__ObjectProperty__Group__1__Impl rule__ObjectProperty__Group__2 )
            // InternalSeML.g:1423:2: rule__ObjectProperty__Group__1__Impl rule__ObjectProperty__Group__2
            {
            pushFollow(FOLLOW_11);
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
    // InternalSeML.g:1430:1: rule__ObjectProperty__Group__1__Impl : ( ( rule__ObjectProperty__NameAssignment_1 ) ) ;
    public final void rule__ObjectProperty__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1434:1: ( ( ( rule__ObjectProperty__NameAssignment_1 ) ) )
            // InternalSeML.g:1435:1: ( ( rule__ObjectProperty__NameAssignment_1 ) )
            {
            // InternalSeML.g:1435:1: ( ( rule__ObjectProperty__NameAssignment_1 ) )
            // InternalSeML.g:1436:2: ( rule__ObjectProperty__NameAssignment_1 )
            {
             before(grammarAccess.getObjectPropertyAccess().getNameAssignment_1()); 
            // InternalSeML.g:1437:2: ( rule__ObjectProperty__NameAssignment_1 )
            // InternalSeML.g:1437:3: rule__ObjectProperty__NameAssignment_1
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
    // InternalSeML.g:1445:1: rule__ObjectProperty__Group__2 : rule__ObjectProperty__Group__2__Impl ;
    public final void rule__ObjectProperty__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1449:1: ( rule__ObjectProperty__Group__2__Impl )
            // InternalSeML.g:1450:2: rule__ObjectProperty__Group__2__Impl
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
    // InternalSeML.g:1456:1: rule__ObjectProperty__Group__2__Impl : ( ( rule__ObjectProperty__IriAssignment_2 ) ) ;
    public final void rule__ObjectProperty__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1460:1: ( ( ( rule__ObjectProperty__IriAssignment_2 ) ) )
            // InternalSeML.g:1461:1: ( ( rule__ObjectProperty__IriAssignment_2 ) )
            {
            // InternalSeML.g:1461:1: ( ( rule__ObjectProperty__IriAssignment_2 ) )
            // InternalSeML.g:1462:2: ( rule__ObjectProperty__IriAssignment_2 )
            {
             before(grammarAccess.getObjectPropertyAccess().getIriAssignment_2()); 
            // InternalSeML.g:1463:2: ( rule__ObjectProperty__IriAssignment_2 )
            // InternalSeML.g:1463:3: rule__ObjectProperty__IriAssignment_2
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
    // InternalSeML.g:1472:1: rule__Characteristic__Group__0 : rule__Characteristic__Group__0__Impl rule__Characteristic__Group__1 ;
    public final void rule__Characteristic__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1476:1: ( rule__Characteristic__Group__0__Impl rule__Characteristic__Group__1 )
            // InternalSeML.g:1477:2: rule__Characteristic__Group__0__Impl rule__Characteristic__Group__1
            {
            pushFollow(FOLLOW_12);
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
    // InternalSeML.g:1484:1: rule__Characteristic__Group__0__Impl : ( 'Characteristic' ) ;
    public final void rule__Characteristic__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1488:1: ( ( 'Characteristic' ) )
            // InternalSeML.g:1489:1: ( 'Characteristic' )
            {
            // InternalSeML.g:1489:1: ( 'Characteristic' )
            // InternalSeML.g:1490:2: 'Characteristic'
            {
             before(grammarAccess.getCharacteristicAccess().getCharacteristicKeyword_0()); 
            match(input,22,FOLLOW_2); 
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
    // InternalSeML.g:1499:1: rule__Characteristic__Group__1 : rule__Characteristic__Group__1__Impl rule__Characteristic__Group__2 ;
    public final void rule__Characteristic__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1503:1: ( rule__Characteristic__Group__1__Impl rule__Characteristic__Group__2 )
            // InternalSeML.g:1504:2: rule__Characteristic__Group__1__Impl rule__Characteristic__Group__2
            {
            pushFollow(FOLLOW_11);
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
    // InternalSeML.g:1511:1: rule__Characteristic__Group__1__Impl : ( ( rule__Characteristic__NameAssignment_1 ) ) ;
    public final void rule__Characteristic__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1515:1: ( ( ( rule__Characteristic__NameAssignment_1 ) ) )
            // InternalSeML.g:1516:1: ( ( rule__Characteristic__NameAssignment_1 ) )
            {
            // InternalSeML.g:1516:1: ( ( rule__Characteristic__NameAssignment_1 ) )
            // InternalSeML.g:1517:2: ( rule__Characteristic__NameAssignment_1 )
            {
             before(grammarAccess.getCharacteristicAccess().getNameAssignment_1()); 
            // InternalSeML.g:1518:2: ( rule__Characteristic__NameAssignment_1 )
            // InternalSeML.g:1518:3: rule__Characteristic__NameAssignment_1
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
    // InternalSeML.g:1526:1: rule__Characteristic__Group__2 : rule__Characteristic__Group__2__Impl ;
    public final void rule__Characteristic__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1530:1: ( rule__Characteristic__Group__2__Impl )
            // InternalSeML.g:1531:2: rule__Characteristic__Group__2__Impl
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
    // InternalSeML.g:1537:1: rule__Characteristic__Group__2__Impl : ( ( rule__Characteristic__IriAssignment_2 ) ) ;
    public final void rule__Characteristic__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1541:1: ( ( ( rule__Characteristic__IriAssignment_2 ) ) )
            // InternalSeML.g:1542:1: ( ( rule__Characteristic__IriAssignment_2 ) )
            {
            // InternalSeML.g:1542:1: ( ( rule__Characteristic__IriAssignment_2 ) )
            // InternalSeML.g:1543:2: ( rule__Characteristic__IriAssignment_2 )
            {
             before(grammarAccess.getCharacteristicAccess().getIriAssignment_2()); 
            // InternalSeML.g:1544:2: ( rule__Characteristic__IriAssignment_2 )
            // InternalSeML.g:1544:3: rule__Characteristic__IriAssignment_2
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


    // $ANTLR start "rule__MetaIndividual__Group__0"
    // InternalSeML.g:1553:1: rule__MetaIndividual__Group__0 : rule__MetaIndividual__Group__0__Impl rule__MetaIndividual__Group__1 ;
    public final void rule__MetaIndividual__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1557:1: ( rule__MetaIndividual__Group__0__Impl rule__MetaIndividual__Group__1 )
            // InternalSeML.g:1558:2: rule__MetaIndividual__Group__0__Impl rule__MetaIndividual__Group__1
            {
            pushFollow(FOLLOW_11);
            rule__MetaIndividual__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MetaIndividual__Group__1();

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
    // $ANTLR end "rule__MetaIndividual__Group__0"


    // $ANTLR start "rule__MetaIndividual__Group__0__Impl"
    // InternalSeML.g:1565:1: rule__MetaIndividual__Group__0__Impl : ( 'MetaIndividual' ) ;
    public final void rule__MetaIndividual__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1569:1: ( ( 'MetaIndividual' ) )
            // InternalSeML.g:1570:1: ( 'MetaIndividual' )
            {
            // InternalSeML.g:1570:1: ( 'MetaIndividual' )
            // InternalSeML.g:1571:2: 'MetaIndividual'
            {
             before(grammarAccess.getMetaIndividualAccess().getMetaIndividualKeyword_0()); 
            match(input,23,FOLLOW_2); 
             after(grammarAccess.getMetaIndividualAccess().getMetaIndividualKeyword_0()); 

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
    // $ANTLR end "rule__MetaIndividual__Group__0__Impl"


    // $ANTLR start "rule__MetaIndividual__Group__1"
    // InternalSeML.g:1580:1: rule__MetaIndividual__Group__1 : rule__MetaIndividual__Group__1__Impl rule__MetaIndividual__Group__2 ;
    public final void rule__MetaIndividual__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1584:1: ( rule__MetaIndividual__Group__1__Impl rule__MetaIndividual__Group__2 )
            // InternalSeML.g:1585:2: rule__MetaIndividual__Group__1__Impl rule__MetaIndividual__Group__2
            {
            pushFollow(FOLLOW_12);
            rule__MetaIndividual__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MetaIndividual__Group__2();

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
    // $ANTLR end "rule__MetaIndividual__Group__1"


    // $ANTLR start "rule__MetaIndividual__Group__1__Impl"
    // InternalSeML.g:1592:1: rule__MetaIndividual__Group__1__Impl : ( ( ( rule__MetaIndividual__ClsAssignment_1 ) ) ( ( rule__MetaIndividual__ClsAssignment_1 )* ) ) ;
    public final void rule__MetaIndividual__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1596:1: ( ( ( ( rule__MetaIndividual__ClsAssignment_1 ) ) ( ( rule__MetaIndividual__ClsAssignment_1 )* ) ) )
            // InternalSeML.g:1597:1: ( ( ( rule__MetaIndividual__ClsAssignment_1 ) ) ( ( rule__MetaIndividual__ClsAssignment_1 )* ) )
            {
            // InternalSeML.g:1597:1: ( ( ( rule__MetaIndividual__ClsAssignment_1 ) ) ( ( rule__MetaIndividual__ClsAssignment_1 )* ) )
            // InternalSeML.g:1598:2: ( ( rule__MetaIndividual__ClsAssignment_1 ) ) ( ( rule__MetaIndividual__ClsAssignment_1 )* )
            {
            // InternalSeML.g:1598:2: ( ( rule__MetaIndividual__ClsAssignment_1 ) )
            // InternalSeML.g:1599:3: ( rule__MetaIndividual__ClsAssignment_1 )
            {
             before(grammarAccess.getMetaIndividualAccess().getClsAssignment_1()); 
            // InternalSeML.g:1600:3: ( rule__MetaIndividual__ClsAssignment_1 )
            // InternalSeML.g:1600:4: rule__MetaIndividual__ClsAssignment_1
            {
            pushFollow(FOLLOW_15);
            rule__MetaIndividual__ClsAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getMetaIndividualAccess().getClsAssignment_1()); 

            }

            // InternalSeML.g:1603:2: ( ( rule__MetaIndividual__ClsAssignment_1 )* )
            // InternalSeML.g:1604:3: ( rule__MetaIndividual__ClsAssignment_1 )*
            {
             before(grammarAccess.getMetaIndividualAccess().getClsAssignment_1()); 
            // InternalSeML.g:1605:3: ( rule__MetaIndividual__ClsAssignment_1 )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_STRING) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalSeML.g:1605:4: rule__MetaIndividual__ClsAssignment_1
            	    {
            	    pushFollow(FOLLOW_15);
            	    rule__MetaIndividual__ClsAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop11;
                }
            } while (true);

             after(grammarAccess.getMetaIndividualAccess().getClsAssignment_1()); 

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
    // $ANTLR end "rule__MetaIndividual__Group__1__Impl"


    // $ANTLR start "rule__MetaIndividual__Group__2"
    // InternalSeML.g:1614:1: rule__MetaIndividual__Group__2 : rule__MetaIndividual__Group__2__Impl rule__MetaIndividual__Group__3 ;
    public final void rule__MetaIndividual__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1618:1: ( rule__MetaIndividual__Group__2__Impl rule__MetaIndividual__Group__3 )
            // InternalSeML.g:1619:2: rule__MetaIndividual__Group__2__Impl rule__MetaIndividual__Group__3
            {
            pushFollow(FOLLOW_11);
            rule__MetaIndividual__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_2);
            rule__MetaIndividual__Group__3();

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
    // $ANTLR end "rule__MetaIndividual__Group__2"


    // $ANTLR start "rule__MetaIndividual__Group__2__Impl"
    // InternalSeML.g:1626:1: rule__MetaIndividual__Group__2__Impl : ( ( rule__MetaIndividual__NameAssignment_2 ) ) ;
    public final void rule__MetaIndividual__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1630:1: ( ( ( rule__MetaIndividual__NameAssignment_2 ) ) )
            // InternalSeML.g:1631:1: ( ( rule__MetaIndividual__NameAssignment_2 ) )
            {
            // InternalSeML.g:1631:1: ( ( rule__MetaIndividual__NameAssignment_2 ) )
            // InternalSeML.g:1632:2: ( rule__MetaIndividual__NameAssignment_2 )
            {
             before(grammarAccess.getMetaIndividualAccess().getNameAssignment_2()); 
            // InternalSeML.g:1633:2: ( rule__MetaIndividual__NameAssignment_2 )
            // InternalSeML.g:1633:3: rule__MetaIndividual__NameAssignment_2
            {
            pushFollow(FOLLOW_2);
            rule__MetaIndividual__NameAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getMetaIndividualAccess().getNameAssignment_2()); 

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
    // $ANTLR end "rule__MetaIndividual__Group__2__Impl"


    // $ANTLR start "rule__MetaIndividual__Group__3"
    // InternalSeML.g:1641:1: rule__MetaIndividual__Group__3 : rule__MetaIndividual__Group__3__Impl ;
    public final void rule__MetaIndividual__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1645:1: ( rule__MetaIndividual__Group__3__Impl )
            // InternalSeML.g:1646:2: rule__MetaIndividual__Group__3__Impl
            {
            pushFollow(FOLLOW_2);
            rule__MetaIndividual__Group__3__Impl();

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
    // $ANTLR end "rule__MetaIndividual__Group__3"


    // $ANTLR start "rule__MetaIndividual__Group__3__Impl"
    // InternalSeML.g:1652:1: rule__MetaIndividual__Group__3__Impl : ( ( rule__MetaIndividual__IriAssignment_3 ) ) ;
    public final void rule__MetaIndividual__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1656:1: ( ( ( rule__MetaIndividual__IriAssignment_3 ) ) )
            // InternalSeML.g:1657:1: ( ( rule__MetaIndividual__IriAssignment_3 ) )
            {
            // InternalSeML.g:1657:1: ( ( rule__MetaIndividual__IriAssignment_3 ) )
            // InternalSeML.g:1658:2: ( rule__MetaIndividual__IriAssignment_3 )
            {
             before(grammarAccess.getMetaIndividualAccess().getIriAssignment_3()); 
            // InternalSeML.g:1659:2: ( rule__MetaIndividual__IriAssignment_3 )
            // InternalSeML.g:1659:3: rule__MetaIndividual__IriAssignment_3
            {
            pushFollow(FOLLOW_2);
            rule__MetaIndividual__IriAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getMetaIndividualAccess().getIriAssignment_3()); 

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
    // $ANTLR end "rule__MetaIndividual__Group__3__Impl"


    // $ANTLR start "rule__MainModel__ImportsAssignment_0"
    // InternalSeML.g:1668:1: rule__MainModel__ImportsAssignment_0 : ( ruleImport ) ;
    public final void rule__MainModel__ImportsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1672:1: ( ( ruleImport ) )
            // InternalSeML.g:1673:2: ( ruleImport )
            {
            // InternalSeML.g:1673:2: ( ruleImport )
            // InternalSeML.g:1674:3: ruleImport
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


    // $ANTLR start "rule__MainModel__SentencesAssignment_1"
    // InternalSeML.g:1683:1: rule__MainModel__SentencesAssignment_1 : ( ruleSentence ) ;
    public final void rule__MainModel__SentencesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1687:1: ( ( ruleSentence ) )
            // InternalSeML.g:1688:2: ( ruleSentence )
            {
            // InternalSeML.g:1688:2: ( ruleSentence )
            // InternalSeML.g:1689:3: ruleSentence
            {
             before(grammarAccess.getMainModelAccess().getSentencesSentenceParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleSentence();

            state._fsp--;

             after(grammarAccess.getMainModelAccess().getSentencesSentenceParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__MainModel__SentencesAssignment_1"


    // $ANTLR start "rule__ImportModel__ComponentsAssignment_0"
    // InternalSeML.g:1698:1: rule__ImportModel__ComponentsAssignment_0 : ( ruleComponent ) ;
    public final void rule__ImportModel__ComponentsAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1702:1: ( ( ruleComponent ) )
            // InternalSeML.g:1703:2: ( ruleComponent )
            {
            // InternalSeML.g:1703:2: ( ruleComponent )
            // InternalSeML.g:1704:3: ruleComponent
            {
             before(grammarAccess.getImportModelAccess().getComponentsComponentParserRuleCall_0_0()); 
            pushFollow(FOLLOW_2);
            ruleComponent();

            state._fsp--;

             after(grammarAccess.getImportModelAccess().getComponentsComponentParserRuleCall_0_0()); 

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
    // $ANTLR end "rule__ImportModel__ComponentsAssignment_0"


    // $ANTLR start "rule__ImportModel__CharacteristicsAssignment_1"
    // InternalSeML.g:1713:1: rule__ImportModel__CharacteristicsAssignment_1 : ( ruleCharacteristic ) ;
    public final void rule__ImportModel__CharacteristicsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1717:1: ( ( ruleCharacteristic ) )
            // InternalSeML.g:1718:2: ( ruleCharacteristic )
            {
            // InternalSeML.g:1718:2: ( ruleCharacteristic )
            // InternalSeML.g:1719:3: ruleCharacteristic
            {
             before(grammarAccess.getImportModelAccess().getCharacteristicsCharacteristicParserRuleCall_1_0()); 
            pushFollow(FOLLOW_2);
            ruleCharacteristic();

            state._fsp--;

             after(grammarAccess.getImportModelAccess().getCharacteristicsCharacteristicParserRuleCall_1_0()); 

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
    // $ANTLR end "rule__ImportModel__CharacteristicsAssignment_1"


    // $ANTLR start "rule__ImportModel__ObjectPropertiesAssignment_2"
    // InternalSeML.g:1728:1: rule__ImportModel__ObjectPropertiesAssignment_2 : ( ruleObjectProperty ) ;
    public final void rule__ImportModel__ObjectPropertiesAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1732:1: ( ( ruleObjectProperty ) )
            // InternalSeML.g:1733:2: ( ruleObjectProperty )
            {
            // InternalSeML.g:1733:2: ( ruleObjectProperty )
            // InternalSeML.g:1734:3: ruleObjectProperty
            {
             before(grammarAccess.getImportModelAccess().getObjectPropertiesObjectPropertyParserRuleCall_2_0()); 
            pushFollow(FOLLOW_2);
            ruleObjectProperty();

            state._fsp--;

             after(grammarAccess.getImportModelAccess().getObjectPropertiesObjectPropertyParserRuleCall_2_0()); 

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
    // $ANTLR end "rule__ImportModel__ObjectPropertiesAssignment_2"


    // $ANTLR start "rule__ImportModel__MetaIndividualsAssignment_3"
    // InternalSeML.g:1743:1: rule__ImportModel__MetaIndividualsAssignment_3 : ( ruleMetaIndividual ) ;
    public final void rule__ImportModel__MetaIndividualsAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1747:1: ( ( ruleMetaIndividual ) )
            // InternalSeML.g:1748:2: ( ruleMetaIndividual )
            {
            // InternalSeML.g:1748:2: ( ruleMetaIndividual )
            // InternalSeML.g:1749:3: ruleMetaIndividual
            {
             before(grammarAccess.getImportModelAccess().getMetaIndividualsMetaIndividualParserRuleCall_3_0()); 
            pushFollow(FOLLOW_2);
            ruleMetaIndividual();

            state._fsp--;

             after(grammarAccess.getImportModelAccess().getMetaIndividualsMetaIndividualParserRuleCall_3_0()); 

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
    // $ANTLR end "rule__ImportModel__MetaIndividualsAssignment_3"


    // $ANTLR start "rule__Import__NameAssignment_1"
    // InternalSeML.g:1758:1: rule__Import__NameAssignment_1 : ( RULE_STRING ) ;
    public final void rule__Import__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1762:1: ( ( RULE_STRING ) )
            // InternalSeML.g:1763:2: ( RULE_STRING )
            {
            // InternalSeML.g:1763:2: ( RULE_STRING )
            // InternalSeML.g:1764:3: RULE_STRING
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


    // $ANTLR start "rule__Individual__ClsAssignment_1"
    // InternalSeML.g:1773:1: rule__Individual__ClsAssignment_1 : ( ( RULE_IRI ) ) ;
    public final void rule__Individual__ClsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1777:1: ( ( ( RULE_IRI ) ) )
            // InternalSeML.g:1778:2: ( ( RULE_IRI ) )
            {
            // InternalSeML.g:1778:2: ( ( RULE_IRI ) )
            // InternalSeML.g:1779:3: ( RULE_IRI )
            {
             before(grammarAccess.getIndividualAccess().getClsComponentCrossReference_1_0()); 
            // InternalSeML.g:1780:3: ( RULE_IRI )
            // InternalSeML.g:1781:4: RULE_IRI
            {
             before(grammarAccess.getIndividualAccess().getClsComponentIRITerminalRuleCall_1_0_1()); 
            match(input,RULE_IRI,FOLLOW_2); 
             after(grammarAccess.getIndividualAccess().getClsComponentIRITerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getIndividualAccess().getClsComponentCrossReference_1_0()); 

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
    // $ANTLR end "rule__Individual__ClsAssignment_1"


    // $ANTLR start "rule__Individual__NameAssignment_2"
    // InternalSeML.g:1792:1: rule__Individual__NameAssignment_2 : ( RULE_IRI ) ;
    public final void rule__Individual__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1796:1: ( ( RULE_IRI ) )
            // InternalSeML.g:1797:2: ( RULE_IRI )
            {
            // InternalSeML.g:1797:2: ( RULE_IRI )
            // InternalSeML.g:1798:3: RULE_IRI
            {
             before(grammarAccess.getIndividualAccess().getNameIRITerminalRuleCall_2_0()); 
            match(input,RULE_IRI,FOLLOW_2); 
             after(grammarAccess.getIndividualAccess().getNameIRITerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__Individual__NameAssignment_2"


    // $ANTLR start "rule__UseCharacteristic__NameAssignment_1"
    // InternalSeML.g:1807:1: rule__UseCharacteristic__NameAssignment_1 : ( ( RULE_IRI ) ) ;
    public final void rule__UseCharacteristic__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1811:1: ( ( ( RULE_IRI ) ) )
            // InternalSeML.g:1812:2: ( ( RULE_IRI ) )
            {
            // InternalSeML.g:1812:2: ( ( RULE_IRI ) )
            // InternalSeML.g:1813:3: ( RULE_IRI )
            {
             before(grammarAccess.getUseCharacteristicAccess().getNameCharacteristicCrossReference_1_0()); 
            // InternalSeML.g:1814:3: ( RULE_IRI )
            // InternalSeML.g:1815:4: RULE_IRI
            {
             before(grammarAccess.getUseCharacteristicAccess().getNameCharacteristicIRITerminalRuleCall_1_0_1()); 
            match(input,RULE_IRI,FOLLOW_2); 
             after(grammarAccess.getUseCharacteristicAccess().getNameCharacteristicIRITerminalRuleCall_1_0_1()); 

            }

             after(grammarAccess.getUseCharacteristicAccess().getNameCharacteristicCrossReference_1_0()); 

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
    // $ANTLR end "rule__UseCharacteristic__NameAssignment_1"


    // $ANTLR start "rule__Relation__Instance1Assignment_0"
    // InternalSeML.g:1826:1: rule__Relation__Instance1Assignment_0 : ( ( RULE_IRI ) ) ;
    public final void rule__Relation__Instance1Assignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1830:1: ( ( ( RULE_IRI ) ) )
            // InternalSeML.g:1831:2: ( ( RULE_IRI ) )
            {
            // InternalSeML.g:1831:2: ( ( RULE_IRI ) )
            // InternalSeML.g:1832:3: ( RULE_IRI )
            {
             before(grammarAccess.getRelationAccess().getInstance1AnyIndividualCrossReference_0_0()); 
            // InternalSeML.g:1833:3: ( RULE_IRI )
            // InternalSeML.g:1834:4: RULE_IRI
            {
             before(grammarAccess.getRelationAccess().getInstance1AnyIndividualIRITerminalRuleCall_0_0_1()); 
            match(input,RULE_IRI,FOLLOW_2); 
             after(grammarAccess.getRelationAccess().getInstance1AnyIndividualIRITerminalRuleCall_0_0_1()); 

            }

             after(grammarAccess.getRelationAccess().getInstance1AnyIndividualCrossReference_0_0()); 

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
    // $ANTLR end "rule__Relation__Instance1Assignment_0"


    // $ANTLR start "rule__Relation__ObjAssignment_1"
    // InternalSeML.g:1845:1: rule__Relation__ObjAssignment_1 : ( ( RULE_IRI ) ) ;
    public final void rule__Relation__ObjAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1849:1: ( ( ( RULE_IRI ) ) )
            // InternalSeML.g:1850:2: ( ( RULE_IRI ) )
            {
            // InternalSeML.g:1850:2: ( ( RULE_IRI ) )
            // InternalSeML.g:1851:3: ( RULE_IRI )
            {
             before(grammarAccess.getRelationAccess().getObjObjectPropertyCrossReference_1_0()); 
            // InternalSeML.g:1852:3: ( RULE_IRI )
            // InternalSeML.g:1853:4: RULE_IRI
            {
             before(grammarAccess.getRelationAccess().getObjObjectPropertyIRITerminalRuleCall_1_0_1()); 
            match(input,RULE_IRI,FOLLOW_2); 
             after(grammarAccess.getRelationAccess().getObjObjectPropertyIRITerminalRuleCall_1_0_1()); 

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


    // $ANTLR start "rule__Relation__Instance2Assignment_2"
    // InternalSeML.g:1864:1: rule__Relation__Instance2Assignment_2 : ( ( RULE_IRI ) ) ;
    public final void rule__Relation__Instance2Assignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1868:1: ( ( ( RULE_IRI ) ) )
            // InternalSeML.g:1869:2: ( ( RULE_IRI ) )
            {
            // InternalSeML.g:1869:2: ( ( RULE_IRI ) )
            // InternalSeML.g:1870:3: ( RULE_IRI )
            {
             before(grammarAccess.getRelationAccess().getInstance2AnyIndividualCrossReference_2_0()); 
            // InternalSeML.g:1871:3: ( RULE_IRI )
            // InternalSeML.g:1872:4: RULE_IRI
            {
             before(grammarAccess.getRelationAccess().getInstance2AnyIndividualIRITerminalRuleCall_2_0_1()); 
            match(input,RULE_IRI,FOLLOW_2); 
             after(grammarAccess.getRelationAccess().getInstance2AnyIndividualIRITerminalRuleCall_2_0_1()); 

            }

             after(grammarAccess.getRelationAccess().getInstance2AnyIndividualCrossReference_2_0()); 

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
    // $ANTLR end "rule__Relation__Instance2Assignment_2"


    // $ANTLR start "rule__Component_Process__NameAssignment_1"
    // InternalSeML.g:1883:1: rule__Component_Process__NameAssignment_1 : ( RULE_IRI ) ;
    public final void rule__Component_Process__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1887:1: ( ( RULE_IRI ) )
            // InternalSeML.g:1888:2: ( RULE_IRI )
            {
            // InternalSeML.g:1888:2: ( RULE_IRI )
            // InternalSeML.g:1889:3: RULE_IRI
            {
             before(grammarAccess.getComponent_ProcessAccess().getNameIRITerminalRuleCall_1_0()); 
            match(input,RULE_IRI,FOLLOW_2); 
             after(grammarAccess.getComponent_ProcessAccess().getNameIRITerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__Component_Process__NameAssignment_1"


    // $ANTLR start "rule__Component_Process__IriAssignment_2"
    // InternalSeML.g:1898:1: rule__Component_Process__IriAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Component_Process__IriAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1902:1: ( ( RULE_STRING ) )
            // InternalSeML.g:1903:2: ( RULE_STRING )
            {
            // InternalSeML.g:1903:2: ( RULE_STRING )
            // InternalSeML.g:1904:3: RULE_STRING
            {
             before(grammarAccess.getComponent_ProcessAccess().getIriSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getComponent_ProcessAccess().getIriSTRINGTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__Component_Process__IriAssignment_2"


    // $ANTLR start "rule__Component_Event__NameAssignment_1"
    // InternalSeML.g:1913:1: rule__Component_Event__NameAssignment_1 : ( RULE_IRI ) ;
    public final void rule__Component_Event__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1917:1: ( ( RULE_IRI ) )
            // InternalSeML.g:1918:2: ( RULE_IRI )
            {
            // InternalSeML.g:1918:2: ( RULE_IRI )
            // InternalSeML.g:1919:3: RULE_IRI
            {
             before(grammarAccess.getComponent_EventAccess().getNameIRITerminalRuleCall_1_0()); 
            match(input,RULE_IRI,FOLLOW_2); 
             after(grammarAccess.getComponent_EventAccess().getNameIRITerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__Component_Event__NameAssignment_1"


    // $ANTLR start "rule__Component_Event__IriAssignment_2"
    // InternalSeML.g:1928:1: rule__Component_Event__IriAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Component_Event__IriAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1932:1: ( ( RULE_STRING ) )
            // InternalSeML.g:1933:2: ( RULE_STRING )
            {
            // InternalSeML.g:1933:2: ( RULE_STRING )
            // InternalSeML.g:1934:3: RULE_STRING
            {
             before(grammarAccess.getComponent_EventAccess().getIriSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getComponent_EventAccess().getIriSTRINGTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__Component_Event__IriAssignment_2"


    // $ANTLR start "rule__Component_Property__NameAssignment_1"
    // InternalSeML.g:1943:1: rule__Component_Property__NameAssignment_1 : ( RULE_IRI ) ;
    public final void rule__Component_Property__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1947:1: ( ( RULE_IRI ) )
            // InternalSeML.g:1948:2: ( RULE_IRI )
            {
            // InternalSeML.g:1948:2: ( RULE_IRI )
            // InternalSeML.g:1949:3: RULE_IRI
            {
             before(grammarAccess.getComponent_PropertyAccess().getNameIRITerminalRuleCall_1_0()); 
            match(input,RULE_IRI,FOLLOW_2); 
             after(grammarAccess.getComponent_PropertyAccess().getNameIRITerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__Component_Property__NameAssignment_1"


    // $ANTLR start "rule__Component_Property__IriAssignment_2"
    // InternalSeML.g:1958:1: rule__Component_Property__IriAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Component_Property__IriAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1962:1: ( ( RULE_STRING ) )
            // InternalSeML.g:1963:2: ( RULE_STRING )
            {
            // InternalSeML.g:1963:2: ( RULE_STRING )
            // InternalSeML.g:1964:3: RULE_STRING
            {
             before(grammarAccess.getComponent_PropertyAccess().getIriSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getComponent_PropertyAccess().getIriSTRINGTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__Component_Property__IriAssignment_2"


    // $ANTLR start "rule__Component_Entity__NameAssignment_1"
    // InternalSeML.g:1973:1: rule__Component_Entity__NameAssignment_1 : ( RULE_IRI ) ;
    public final void rule__Component_Entity__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1977:1: ( ( RULE_IRI ) )
            // InternalSeML.g:1978:2: ( RULE_IRI )
            {
            // InternalSeML.g:1978:2: ( RULE_IRI )
            // InternalSeML.g:1979:3: RULE_IRI
            {
             before(grammarAccess.getComponent_EntityAccess().getNameIRITerminalRuleCall_1_0()); 
            match(input,RULE_IRI,FOLLOW_2); 
             after(grammarAccess.getComponent_EntityAccess().getNameIRITerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__Component_Entity__NameAssignment_1"


    // $ANTLR start "rule__Component_Entity__IriAssignment_2"
    // InternalSeML.g:1988:1: rule__Component_Entity__IriAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Component_Entity__IriAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:1992:1: ( ( RULE_STRING ) )
            // InternalSeML.g:1993:2: ( RULE_STRING )
            {
            // InternalSeML.g:1993:2: ( RULE_STRING )
            // InternalSeML.g:1994:3: RULE_STRING
            {
             before(grammarAccess.getComponent_EntityAccess().getIriSTRINGTerminalRuleCall_2_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getComponent_EntityAccess().getIriSTRINGTerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__Component_Entity__IriAssignment_2"


    // $ANTLR start "rule__ObjectProperty__NameAssignment_1"
    // InternalSeML.g:2003:1: rule__ObjectProperty__NameAssignment_1 : ( RULE_IRI ) ;
    public final void rule__ObjectProperty__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:2007:1: ( ( RULE_IRI ) )
            // InternalSeML.g:2008:2: ( RULE_IRI )
            {
            // InternalSeML.g:2008:2: ( RULE_IRI )
            // InternalSeML.g:2009:3: RULE_IRI
            {
             before(grammarAccess.getObjectPropertyAccess().getNameIRITerminalRuleCall_1_0()); 
            match(input,RULE_IRI,FOLLOW_2); 
             after(grammarAccess.getObjectPropertyAccess().getNameIRITerminalRuleCall_1_0()); 

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
    // InternalSeML.g:2018:1: rule__ObjectProperty__IriAssignment_2 : ( RULE_STRING ) ;
    public final void rule__ObjectProperty__IriAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:2022:1: ( ( RULE_STRING ) )
            // InternalSeML.g:2023:2: ( RULE_STRING )
            {
            // InternalSeML.g:2023:2: ( RULE_STRING )
            // InternalSeML.g:2024:3: RULE_STRING
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
    // InternalSeML.g:2033:1: rule__Characteristic__NameAssignment_1 : ( RULE_IRI ) ;
    public final void rule__Characteristic__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:2037:1: ( ( RULE_IRI ) )
            // InternalSeML.g:2038:2: ( RULE_IRI )
            {
            // InternalSeML.g:2038:2: ( RULE_IRI )
            // InternalSeML.g:2039:3: RULE_IRI
            {
             before(grammarAccess.getCharacteristicAccess().getNameIRITerminalRuleCall_1_0()); 
            match(input,RULE_IRI,FOLLOW_2); 
             after(grammarAccess.getCharacteristicAccess().getNameIRITerminalRuleCall_1_0()); 

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
    // InternalSeML.g:2048:1: rule__Characteristic__IriAssignment_2 : ( RULE_STRING ) ;
    public final void rule__Characteristic__IriAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:2052:1: ( ( RULE_STRING ) )
            // InternalSeML.g:2053:2: ( RULE_STRING )
            {
            // InternalSeML.g:2053:2: ( RULE_STRING )
            // InternalSeML.g:2054:3: RULE_STRING
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


    // $ANTLR start "rule__MetaIndividual__ClsAssignment_1"
    // InternalSeML.g:2063:1: rule__MetaIndividual__ClsAssignment_1 : ( RULE_STRING ) ;
    public final void rule__MetaIndividual__ClsAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:2067:1: ( ( RULE_STRING ) )
            // InternalSeML.g:2068:2: ( RULE_STRING )
            {
            // InternalSeML.g:2068:2: ( RULE_STRING )
            // InternalSeML.g:2069:3: RULE_STRING
            {
             before(grammarAccess.getMetaIndividualAccess().getClsSTRINGTerminalRuleCall_1_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getMetaIndividualAccess().getClsSTRINGTerminalRuleCall_1_0()); 

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
    // $ANTLR end "rule__MetaIndividual__ClsAssignment_1"


    // $ANTLR start "rule__MetaIndividual__NameAssignment_2"
    // InternalSeML.g:2078:1: rule__MetaIndividual__NameAssignment_2 : ( RULE_IRI ) ;
    public final void rule__MetaIndividual__NameAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:2082:1: ( ( RULE_IRI ) )
            // InternalSeML.g:2083:2: ( RULE_IRI )
            {
            // InternalSeML.g:2083:2: ( RULE_IRI )
            // InternalSeML.g:2084:3: RULE_IRI
            {
             before(grammarAccess.getMetaIndividualAccess().getNameIRITerminalRuleCall_2_0()); 
            match(input,RULE_IRI,FOLLOW_2); 
             after(grammarAccess.getMetaIndividualAccess().getNameIRITerminalRuleCall_2_0()); 

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
    // $ANTLR end "rule__MetaIndividual__NameAssignment_2"


    // $ANTLR start "rule__MetaIndividual__IriAssignment_3"
    // InternalSeML.g:2093:1: rule__MetaIndividual__IriAssignment_3 : ( RULE_STRING ) ;
    public final void rule__MetaIndividual__IriAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
        	
        try {
            // InternalSeML.g:2097:1: ( ( RULE_STRING ) )
            // InternalSeML.g:2098:2: ( RULE_STRING )
            {
            // InternalSeML.g:2098:2: ( RULE_STRING )
            // InternalSeML.g:2099:3: RULE_STRING
            {
             before(grammarAccess.getMetaIndividualAccess().getIriSTRINGTerminalRuleCall_3_0()); 
            match(input,RULE_STRING,FOLLOW_2); 
             after(grammarAccess.getMetaIndividualAccess().getIriSTRINGTerminalRuleCall_3_0()); 

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
    // $ANTLR end "rule__MetaIndividual__IriAssignment_3"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000014020L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000002002L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000014022L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000E00000L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x00000000001E0002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000400002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000200002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000000022L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_15 = new BitSet(new long[]{0x0000000000000012L});

}