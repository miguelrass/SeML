package org.xtext.seml.parser.antlr.internal;

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.xtext.seml.services.SeMLGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSeMLParser extends AbstractInternalAntlrParser {
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

        public InternalSeMLParser(TokenStream input, SeMLGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }

        @Override
        protected String getFirstRuleName() {
        	return "Model";
       	}

       	@Override
       	protected SeMLGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}




    // $ANTLR start "entryRuleModel"
    // InternalSeML.g:64:1: entryRuleModel returns [EObject current=null] : iv_ruleModel= ruleModel EOF ;
    public final EObject entryRuleModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleModel = null;


        try {
            // InternalSeML.g:64:46: (iv_ruleModel= ruleModel EOF )
            // InternalSeML.g:65:2: iv_ruleModel= ruleModel EOF
            {
             newCompositeNode(grammarAccess.getModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleModel=ruleModel();

            state._fsp--;

             current =iv_ruleModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleModel"


    // $ANTLR start "ruleModel"
    // InternalSeML.g:71:1: ruleModel returns [EObject current=null] : (this_MainModel_0= ruleMainModel | this_ImportModel_1= ruleImportModel ) ;
    public final EObject ruleModel() throws RecognitionException {
        EObject current = null;

        EObject this_MainModel_0 = null;

        EObject this_ImportModel_1 = null;



        	enterRule();

        try {
            // InternalSeML.g:77:2: ( (this_MainModel_0= ruleMainModel | this_ImportModel_1= ruleImportModel ) )
            // InternalSeML.g:78:2: (this_MainModel_0= ruleMainModel | this_ImportModel_1= ruleImportModel )
            {
            // InternalSeML.g:78:2: (this_MainModel_0= ruleMainModel | this_ImportModel_1= ruleImportModel )
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
                    // InternalSeML.g:79:3: this_MainModel_0= ruleMainModel
                    {

                    			newCompositeNode(grammarAccess.getModelAccess().getMainModelParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_MainModel_0=ruleMainModel();

                    state._fsp--;


                    			current = this_MainModel_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalSeML.g:88:3: this_ImportModel_1= ruleImportModel
                    {

                    			newCompositeNode(grammarAccess.getModelAccess().getImportModelParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_ImportModel_1=ruleImportModel();

                    state._fsp--;


                    			current = this_ImportModel_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleModel"


    // $ANTLR start "entryRuleMainModel"
    // InternalSeML.g:100:1: entryRuleMainModel returns [EObject current=null] : iv_ruleMainModel= ruleMainModel EOF ;
    public final EObject entryRuleMainModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMainModel = null;


        try {
            // InternalSeML.g:100:50: (iv_ruleMainModel= ruleMainModel EOF )
            // InternalSeML.g:101:2: iv_ruleMainModel= ruleMainModel EOF
            {
             newCompositeNode(grammarAccess.getMainModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMainModel=ruleMainModel();

            state._fsp--;

             current =iv_ruleMainModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMainModel"


    // $ANTLR start "ruleMainModel"
    // InternalSeML.g:107:1: ruleMainModel returns [EObject current=null] : ( ( (lv_imports_0_0= ruleImport ) )+ ( (lv_sentences_1_0= ruleSentence ) )* ) ;
    public final EObject ruleMainModel() throws RecognitionException {
        EObject current = null;

        EObject lv_imports_0_0 = null;

        EObject lv_sentences_1_0 = null;



        	enterRule();

        try {
            // InternalSeML.g:113:2: ( ( ( (lv_imports_0_0= ruleImport ) )+ ( (lv_sentences_1_0= ruleSentence ) )* ) )
            // InternalSeML.g:114:2: ( ( (lv_imports_0_0= ruleImport ) )+ ( (lv_sentences_1_0= ruleSentence ) )* )
            {
            // InternalSeML.g:114:2: ( ( (lv_imports_0_0= ruleImport ) )+ ( (lv_sentences_1_0= ruleSentence ) )* )
            // InternalSeML.g:115:3: ( (lv_imports_0_0= ruleImport ) )+ ( (lv_sentences_1_0= ruleSentence ) )*
            {
            // InternalSeML.g:115:3: ( (lv_imports_0_0= ruleImport ) )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==13) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // InternalSeML.g:116:4: (lv_imports_0_0= ruleImport )
            	    {
            	    // InternalSeML.g:116:4: (lv_imports_0_0= ruleImport )
            	    // InternalSeML.g:117:5: lv_imports_0_0= ruleImport
            	    {

            	    					newCompositeNode(grammarAccess.getMainModelAccess().getImportsImportParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_3);
            	    lv_imports_0_0=ruleImport();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getMainModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"imports",
            	    						lv_imports_0_0,
            	    						"org.xtext.seml.SeML.Import");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);

            // InternalSeML.g:134:3: ( (lv_sentences_1_0= ruleSentence ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==RULE_IRI||LA3_0==14||LA3_0==16) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalSeML.g:135:4: (lv_sentences_1_0= ruleSentence )
            	    {
            	    // InternalSeML.g:135:4: (lv_sentences_1_0= ruleSentence )
            	    // InternalSeML.g:136:5: lv_sentences_1_0= ruleSentence
            	    {

            	    					newCompositeNode(grammarAccess.getMainModelAccess().getSentencesSentenceParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_4);
            	    lv_sentences_1_0=ruleSentence();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getMainModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"sentences",
            	    						lv_sentences_1_0,
            	    						"org.xtext.seml.SeML.Sentence");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMainModel"


    // $ANTLR start "entryRuleImportModel"
    // InternalSeML.g:157:1: entryRuleImportModel returns [EObject current=null] : iv_ruleImportModel= ruleImportModel EOF ;
    public final EObject entryRuleImportModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImportModel = null;


        try {
            // InternalSeML.g:157:52: (iv_ruleImportModel= ruleImportModel EOF )
            // InternalSeML.g:158:2: iv_ruleImportModel= ruleImportModel EOF
            {
             newCompositeNode(grammarAccess.getImportModelRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleImportModel=ruleImportModel();

            state._fsp--;

             current =iv_ruleImportModel; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleImportModel"


    // $ANTLR start "ruleImportModel"
    // InternalSeML.g:164:1: ruleImportModel returns [EObject current=null] : ( ( (lv_components_0_0= ruleComponent ) )+ ( (lv_characteristics_1_0= ruleCharacteristic ) )* ( (lv_objectProperties_2_0= ruleObjectProperty ) )* ( (lv_metaIndividuals_3_0= ruleMetaIndividual ) )* ) ;
    public final EObject ruleImportModel() throws RecognitionException {
        EObject current = null;

        EObject lv_components_0_0 = null;

        EObject lv_characteristics_1_0 = null;

        EObject lv_objectProperties_2_0 = null;

        EObject lv_metaIndividuals_3_0 = null;



        	enterRule();

        try {
            // InternalSeML.g:170:2: ( ( ( (lv_components_0_0= ruleComponent ) )+ ( (lv_characteristics_1_0= ruleCharacteristic ) )* ( (lv_objectProperties_2_0= ruleObjectProperty ) )* ( (lv_metaIndividuals_3_0= ruleMetaIndividual ) )* ) )
            // InternalSeML.g:171:2: ( ( (lv_components_0_0= ruleComponent ) )+ ( (lv_characteristics_1_0= ruleCharacteristic ) )* ( (lv_objectProperties_2_0= ruleObjectProperty ) )* ( (lv_metaIndividuals_3_0= ruleMetaIndividual ) )* )
            {
            // InternalSeML.g:171:2: ( ( (lv_components_0_0= ruleComponent ) )+ ( (lv_characteristics_1_0= ruleCharacteristic ) )* ( (lv_objectProperties_2_0= ruleObjectProperty ) )* ( (lv_metaIndividuals_3_0= ruleMetaIndividual ) )* )
            // InternalSeML.g:172:3: ( (lv_components_0_0= ruleComponent ) )+ ( (lv_characteristics_1_0= ruleCharacteristic ) )* ( (lv_objectProperties_2_0= ruleObjectProperty ) )* ( (lv_metaIndividuals_3_0= ruleMetaIndividual ) )*
            {
            // InternalSeML.g:172:3: ( (lv_components_0_0= ruleComponent ) )+
            int cnt4=0;
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>=17 && LA4_0<=20)) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // InternalSeML.g:173:4: (lv_components_0_0= ruleComponent )
            	    {
            	    // InternalSeML.g:173:4: (lv_components_0_0= ruleComponent )
            	    // InternalSeML.g:174:5: lv_components_0_0= ruleComponent
            	    {

            	    					newCompositeNode(grammarAccess.getImportModelAccess().getComponentsComponentParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_5);
            	    lv_components_0_0=ruleComponent();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getImportModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"components",
            	    						lv_components_0_0,
            	    						"org.xtext.seml.SeML.Component");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt4 >= 1 ) break loop4;
                        EarlyExitException eee =
                            new EarlyExitException(4, input);
                        throw eee;
                }
                cnt4++;
            } while (true);

            // InternalSeML.g:191:3: ( (lv_characteristics_1_0= ruleCharacteristic ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==22) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalSeML.g:192:4: (lv_characteristics_1_0= ruleCharacteristic )
            	    {
            	    // InternalSeML.g:192:4: (lv_characteristics_1_0= ruleCharacteristic )
            	    // InternalSeML.g:193:5: lv_characteristics_1_0= ruleCharacteristic
            	    {

            	    					newCompositeNode(grammarAccess.getImportModelAccess().getCharacteristicsCharacteristicParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_6);
            	    lv_characteristics_1_0=ruleCharacteristic();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getImportModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"characteristics",
            	    						lv_characteristics_1_0,
            	    						"org.xtext.seml.SeML.Characteristic");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);

            // InternalSeML.g:210:3: ( (lv_objectProperties_2_0= ruleObjectProperty ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==21) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalSeML.g:211:4: (lv_objectProperties_2_0= ruleObjectProperty )
            	    {
            	    // InternalSeML.g:211:4: (lv_objectProperties_2_0= ruleObjectProperty )
            	    // InternalSeML.g:212:5: lv_objectProperties_2_0= ruleObjectProperty
            	    {

            	    					newCompositeNode(grammarAccess.getImportModelAccess().getObjectPropertiesObjectPropertyParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_objectProperties_2_0=ruleObjectProperty();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getImportModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"objectProperties",
            	    						lv_objectProperties_2_0,
            	    						"org.xtext.seml.SeML.ObjectProperty");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);

            // InternalSeML.g:229:3: ( (lv_metaIndividuals_3_0= ruleMetaIndividual ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==23) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalSeML.g:230:4: (lv_metaIndividuals_3_0= ruleMetaIndividual )
            	    {
            	    // InternalSeML.g:230:4: (lv_metaIndividuals_3_0= ruleMetaIndividual )
            	    // InternalSeML.g:231:5: lv_metaIndividuals_3_0= ruleMetaIndividual
            	    {

            	    					newCompositeNode(grammarAccess.getImportModelAccess().getMetaIndividualsMetaIndividualParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_metaIndividuals_3_0=ruleMetaIndividual();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getImportModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"metaIndividuals",
            	    						lv_metaIndividuals_3_0,
            	    						"org.xtext.seml.SeML.MetaIndividual");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleImportModel"


    // $ANTLR start "entryRuleImport"
    // InternalSeML.g:252:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // InternalSeML.g:252:47: (iv_ruleImport= ruleImport EOF )
            // InternalSeML.g:253:2: iv_ruleImport= ruleImport EOF
            {
             newCompositeNode(grammarAccess.getImportRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleImport=ruleImport();

            state._fsp--;

             current =iv_ruleImport; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleImport"


    // $ANTLR start "ruleImport"
    // InternalSeML.g:259:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( (lv_name_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;


        	enterRule();

        try {
            // InternalSeML.g:265:2: ( (otherlv_0= 'import' ( (lv_name_1_0= RULE_STRING ) ) ) )
            // InternalSeML.g:266:2: (otherlv_0= 'import' ( (lv_name_1_0= RULE_STRING ) ) )
            {
            // InternalSeML.g:266:2: (otherlv_0= 'import' ( (lv_name_1_0= RULE_STRING ) ) )
            // InternalSeML.g:267:3: otherlv_0= 'import' ( (lv_name_1_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,13,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
            		
            // InternalSeML.g:271:3: ( (lv_name_1_0= RULE_STRING ) )
            // InternalSeML.g:272:4: (lv_name_1_0= RULE_STRING )
            {
            // InternalSeML.g:272:4: (lv_name_1_0= RULE_STRING )
            // InternalSeML.g:273:5: lv_name_1_0= RULE_STRING
            {
            lv_name_1_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_name_1_0, grammarAccess.getImportAccess().getNameSTRINGTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getImportRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.xtext.seml.SeML.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleImport"


    // $ANTLR start "entryRuleSentence"
    // InternalSeML.g:293:1: entryRuleSentence returns [EObject current=null] : iv_ruleSentence= ruleSentence EOF ;
    public final EObject entryRuleSentence() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSentence = null;


        try {
            // InternalSeML.g:293:49: (iv_ruleSentence= ruleSentence EOF )
            // InternalSeML.g:294:2: iv_ruleSentence= ruleSentence EOF
            {
             newCompositeNode(grammarAccess.getSentenceRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleSentence=ruleSentence();

            state._fsp--;

             current =iv_ruleSentence; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleSentence"


    // $ANTLR start "ruleSentence"
    // InternalSeML.g:300:1: ruleSentence returns [EObject current=null] : (this_Individual_0= ruleIndividual | this_Relation_1= ruleRelation | this_UseCharacteristic_2= ruleUseCharacteristic ) ;
    public final EObject ruleSentence() throws RecognitionException {
        EObject current = null;

        EObject this_Individual_0 = null;

        EObject this_Relation_1 = null;

        EObject this_UseCharacteristic_2 = null;



        	enterRule();

        try {
            // InternalSeML.g:306:2: ( (this_Individual_0= ruleIndividual | this_Relation_1= ruleRelation | this_UseCharacteristic_2= ruleUseCharacteristic ) )
            // InternalSeML.g:307:2: (this_Individual_0= ruleIndividual | this_Relation_1= ruleRelation | this_UseCharacteristic_2= ruleUseCharacteristic )
            {
            // InternalSeML.g:307:2: (this_Individual_0= ruleIndividual | this_Relation_1= ruleRelation | this_UseCharacteristic_2= ruleUseCharacteristic )
            int alt8=3;
            switch ( input.LA(1) ) {
            case 14:
                {
                alt8=1;
                }
                break;
            case RULE_IRI:
                {
                alt8=2;
                }
                break;
            case 16:
                {
                alt8=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // InternalSeML.g:308:3: this_Individual_0= ruleIndividual
                    {

                    			newCompositeNode(grammarAccess.getSentenceAccess().getIndividualParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Individual_0=ruleIndividual();

                    state._fsp--;


                    			current = this_Individual_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalSeML.g:317:3: this_Relation_1= ruleRelation
                    {

                    			newCompositeNode(grammarAccess.getSentenceAccess().getRelationParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Relation_1=ruleRelation();

                    state._fsp--;


                    			current = this_Relation_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalSeML.g:326:3: this_UseCharacteristic_2= ruleUseCharacteristic
                    {

                    			newCompositeNode(grammarAccess.getSentenceAccess().getUseCharacteristicParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_UseCharacteristic_2=ruleUseCharacteristic();

                    state._fsp--;


                    			current = this_UseCharacteristic_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleSentence"


    // $ANTLR start "entryRuleIndividual"
    // InternalSeML.g:338:1: entryRuleIndividual returns [EObject current=null] : iv_ruleIndividual= ruleIndividual EOF ;
    public final EObject entryRuleIndividual() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleIndividual = null;


        try {
            // InternalSeML.g:338:51: (iv_ruleIndividual= ruleIndividual EOF )
            // InternalSeML.g:339:2: iv_ruleIndividual= ruleIndividual EOF
            {
             newCompositeNode(grammarAccess.getIndividualRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleIndividual=ruleIndividual();

            state._fsp--;

             current =iv_ruleIndividual; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleIndividual"


    // $ANTLR start "ruleIndividual"
    // InternalSeML.g:345:1: ruleIndividual returns [EObject current=null] : (otherlv_0= 'new' ( (otherlv_1= RULE_IRI ) )+ ( (lv_name_2_0= RULE_IRI ) ) otherlv_3= '\\n' ) ;
    public final EObject ruleIndividual() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token lv_name_2_0=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalSeML.g:351:2: ( (otherlv_0= 'new' ( (otherlv_1= RULE_IRI ) )+ ( (lv_name_2_0= RULE_IRI ) ) otherlv_3= '\\n' ) )
            // InternalSeML.g:352:2: (otherlv_0= 'new' ( (otherlv_1= RULE_IRI ) )+ ( (lv_name_2_0= RULE_IRI ) ) otherlv_3= '\\n' )
            {
            // InternalSeML.g:352:2: (otherlv_0= 'new' ( (otherlv_1= RULE_IRI ) )+ ( (lv_name_2_0= RULE_IRI ) ) otherlv_3= '\\n' )
            // InternalSeML.g:353:3: otherlv_0= 'new' ( (otherlv_1= RULE_IRI ) )+ ( (lv_name_2_0= RULE_IRI ) ) otherlv_3= '\\n'
            {
            otherlv_0=(Token)match(input,14,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getIndividualAccess().getNewKeyword_0());
            		
            // InternalSeML.g:357:3: ( (otherlv_1= RULE_IRI ) )+
            int cnt9=0;
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==RULE_IRI) ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1==RULE_IRI) ) {
                        alt9=1;
                    }


                }


                switch (alt9) {
            	case 1 :
            	    // InternalSeML.g:358:4: (otherlv_1= RULE_IRI )
            	    {
            	    // InternalSeML.g:358:4: (otherlv_1= RULE_IRI )
            	    // InternalSeML.g:359:5: otherlv_1= RULE_IRI
            	    {

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getIndividualRule());
            	    					}
            	    				
            	    otherlv_1=(Token)match(input,RULE_IRI,FOLLOW_10); 

            	    					newLeafNode(otherlv_1, grammarAccess.getIndividualAccess().getClsComponentCrossReference_1_0());
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt9 >= 1 ) break loop9;
                        EarlyExitException eee =
                            new EarlyExitException(9, input);
                        throw eee;
                }
                cnt9++;
            } while (true);

            // InternalSeML.g:370:3: ( (lv_name_2_0= RULE_IRI ) )
            // InternalSeML.g:371:4: (lv_name_2_0= RULE_IRI )
            {
            // InternalSeML.g:371:4: (lv_name_2_0= RULE_IRI )
            // InternalSeML.g:372:5: lv_name_2_0= RULE_IRI
            {
            lv_name_2_0=(Token)match(input,RULE_IRI,FOLLOW_11); 

            					newLeafNode(lv_name_2_0, grammarAccess.getIndividualAccess().getNameIRITerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getIndividualRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.xtext.seml.SeML.IRI");
            				

            }


            }

            otherlv_3=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getIndividualAccess().getControl000aKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleIndividual"


    // $ANTLR start "entryRuleUseCharacteristic"
    // InternalSeML.g:396:1: entryRuleUseCharacteristic returns [EObject current=null] : iv_ruleUseCharacteristic= ruleUseCharacteristic EOF ;
    public final EObject entryRuleUseCharacteristic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUseCharacteristic = null;


        try {
            // InternalSeML.g:396:58: (iv_ruleUseCharacteristic= ruleUseCharacteristic EOF )
            // InternalSeML.g:397:2: iv_ruleUseCharacteristic= ruleUseCharacteristic EOF
            {
             newCompositeNode(grammarAccess.getUseCharacteristicRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleUseCharacteristic=ruleUseCharacteristic();

            state._fsp--;

             current =iv_ruleUseCharacteristic; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleUseCharacteristic"


    // $ANTLR start "ruleUseCharacteristic"
    // InternalSeML.g:403:1: ruleUseCharacteristic returns [EObject current=null] : (otherlv_0= 'use' ( (otherlv_1= RULE_IRI ) ) otherlv_2= '\\n' ) ;
    public final EObject ruleUseCharacteristic() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;


        	enterRule();

        try {
            // InternalSeML.g:409:2: ( (otherlv_0= 'use' ( (otherlv_1= RULE_IRI ) ) otherlv_2= '\\n' ) )
            // InternalSeML.g:410:2: (otherlv_0= 'use' ( (otherlv_1= RULE_IRI ) ) otherlv_2= '\\n' )
            {
            // InternalSeML.g:410:2: (otherlv_0= 'use' ( (otherlv_1= RULE_IRI ) ) otherlv_2= '\\n' )
            // InternalSeML.g:411:3: otherlv_0= 'use' ( (otherlv_1= RULE_IRI ) ) otherlv_2= '\\n'
            {
            otherlv_0=(Token)match(input,16,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getUseCharacteristicAccess().getUseKeyword_0());
            		
            // InternalSeML.g:415:3: ( (otherlv_1= RULE_IRI ) )
            // InternalSeML.g:416:4: (otherlv_1= RULE_IRI )
            {
            // InternalSeML.g:416:4: (otherlv_1= RULE_IRI )
            // InternalSeML.g:417:5: otherlv_1= RULE_IRI
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getUseCharacteristicRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_IRI,FOLLOW_11); 

            					newLeafNode(otherlv_1, grammarAccess.getUseCharacteristicAccess().getNameCharacteristicCrossReference_1_0());
            				

            }


            }

            otherlv_2=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_2, grammarAccess.getUseCharacteristicAccess().getControl000aKeyword_2());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleUseCharacteristic"


    // $ANTLR start "entryRuleRelation"
    // InternalSeML.g:436:1: entryRuleRelation returns [EObject current=null] : iv_ruleRelation= ruleRelation EOF ;
    public final EObject entryRuleRelation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelation = null;


        try {
            // InternalSeML.g:436:49: (iv_ruleRelation= ruleRelation EOF )
            // InternalSeML.g:437:2: iv_ruleRelation= ruleRelation EOF
            {
             newCompositeNode(grammarAccess.getRelationRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleRelation=ruleRelation();

            state._fsp--;

             current =iv_ruleRelation; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRelation"


    // $ANTLR start "ruleRelation"
    // InternalSeML.g:443:1: ruleRelation returns [EObject current=null] : ( ( (otherlv_0= RULE_IRI ) ) ( (otherlv_1= RULE_IRI ) ) ( (otherlv_2= RULE_IRI ) ) otherlv_3= '\\n' ) ;
    public final EObject ruleRelation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;


        	enterRule();

        try {
            // InternalSeML.g:449:2: ( ( ( (otherlv_0= RULE_IRI ) ) ( (otherlv_1= RULE_IRI ) ) ( (otherlv_2= RULE_IRI ) ) otherlv_3= '\\n' ) )
            // InternalSeML.g:450:2: ( ( (otherlv_0= RULE_IRI ) ) ( (otherlv_1= RULE_IRI ) ) ( (otherlv_2= RULE_IRI ) ) otherlv_3= '\\n' )
            {
            // InternalSeML.g:450:2: ( ( (otherlv_0= RULE_IRI ) ) ( (otherlv_1= RULE_IRI ) ) ( (otherlv_2= RULE_IRI ) ) otherlv_3= '\\n' )
            // InternalSeML.g:451:3: ( (otherlv_0= RULE_IRI ) ) ( (otherlv_1= RULE_IRI ) ) ( (otherlv_2= RULE_IRI ) ) otherlv_3= '\\n'
            {
            // InternalSeML.g:451:3: ( (otherlv_0= RULE_IRI ) )
            // InternalSeML.g:452:4: (otherlv_0= RULE_IRI )
            {
            // InternalSeML.g:452:4: (otherlv_0= RULE_IRI )
            // InternalSeML.g:453:5: otherlv_0= RULE_IRI
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRelationRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_IRI,FOLLOW_10); 

            					newLeafNode(otherlv_0, grammarAccess.getRelationAccess().getInstance1AnyIndividualCrossReference_0_0());
            				

            }


            }

            // InternalSeML.g:464:3: ( (otherlv_1= RULE_IRI ) )
            // InternalSeML.g:465:4: (otherlv_1= RULE_IRI )
            {
            // InternalSeML.g:465:4: (otherlv_1= RULE_IRI )
            // InternalSeML.g:466:5: otherlv_1= RULE_IRI
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRelationRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_IRI,FOLLOW_10); 

            					newLeafNode(otherlv_1, grammarAccess.getRelationAccess().getObjObjectPropertyCrossReference_1_0());
            				

            }


            }

            // InternalSeML.g:477:3: ( (otherlv_2= RULE_IRI ) )
            // InternalSeML.g:478:4: (otherlv_2= RULE_IRI )
            {
            // InternalSeML.g:478:4: (otherlv_2= RULE_IRI )
            // InternalSeML.g:479:5: otherlv_2= RULE_IRI
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRelationRule());
            					}
            				
            otherlv_2=(Token)match(input,RULE_IRI,FOLLOW_11); 

            					newLeafNode(otherlv_2, grammarAccess.getRelationAccess().getInstance2AnyIndividualCrossReference_2_0());
            				

            }


            }

            otherlv_3=(Token)match(input,15,FOLLOW_2); 

            			newLeafNode(otherlv_3, grammarAccess.getRelationAccess().getControl000aKeyword_3());
            		

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRelation"


    // $ANTLR start "entryRuleComponent"
    // InternalSeML.g:498:1: entryRuleComponent returns [EObject current=null] : iv_ruleComponent= ruleComponent EOF ;
    public final EObject entryRuleComponent() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponent = null;


        try {
            // InternalSeML.g:498:50: (iv_ruleComponent= ruleComponent EOF )
            // InternalSeML.g:499:2: iv_ruleComponent= ruleComponent EOF
            {
             newCompositeNode(grammarAccess.getComponentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComponent=ruleComponent();

            state._fsp--;

             current =iv_ruleComponent; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComponent"


    // $ANTLR start "ruleComponent"
    // InternalSeML.g:505:1: ruleComponent returns [EObject current=null] : (this_Component_Process_0= ruleComponent_Process | this_Component_Event_1= ruleComponent_Event | this_Component_Property_2= ruleComponent_Property | this_Component_Entity_3= ruleComponent_Entity ) ;
    public final EObject ruleComponent() throws RecognitionException {
        EObject current = null;

        EObject this_Component_Process_0 = null;

        EObject this_Component_Event_1 = null;

        EObject this_Component_Property_2 = null;

        EObject this_Component_Entity_3 = null;



        	enterRule();

        try {
            // InternalSeML.g:511:2: ( (this_Component_Process_0= ruleComponent_Process | this_Component_Event_1= ruleComponent_Event | this_Component_Property_2= ruleComponent_Property | this_Component_Entity_3= ruleComponent_Entity ) )
            // InternalSeML.g:512:2: (this_Component_Process_0= ruleComponent_Process | this_Component_Event_1= ruleComponent_Event | this_Component_Property_2= ruleComponent_Property | this_Component_Entity_3= ruleComponent_Entity )
            {
            // InternalSeML.g:512:2: (this_Component_Process_0= ruleComponent_Process | this_Component_Event_1= ruleComponent_Event | this_Component_Property_2= ruleComponent_Property | this_Component_Entity_3= ruleComponent_Entity )
            int alt10=4;
            switch ( input.LA(1) ) {
            case 17:
                {
                alt10=1;
                }
                break;
            case 18:
                {
                alt10=2;
                }
                break;
            case 19:
                {
                alt10=3;
                }
                break;
            case 20:
                {
                alt10=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }

            switch (alt10) {
                case 1 :
                    // InternalSeML.g:513:3: this_Component_Process_0= ruleComponent_Process
                    {

                    			newCompositeNode(grammarAccess.getComponentAccess().getComponent_ProcessParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Component_Process_0=ruleComponent_Process();

                    state._fsp--;


                    			current = this_Component_Process_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalSeML.g:522:3: this_Component_Event_1= ruleComponent_Event
                    {

                    			newCompositeNode(grammarAccess.getComponentAccess().getComponent_EventParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Component_Event_1=ruleComponent_Event();

                    state._fsp--;


                    			current = this_Component_Event_1;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 3 :
                    // InternalSeML.g:531:3: this_Component_Property_2= ruleComponent_Property
                    {

                    			newCompositeNode(grammarAccess.getComponentAccess().getComponent_PropertyParserRuleCall_2());
                    		
                    pushFollow(FOLLOW_2);
                    this_Component_Property_2=ruleComponent_Property();

                    state._fsp--;


                    			current = this_Component_Property_2;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 4 :
                    // InternalSeML.g:540:3: this_Component_Entity_3= ruleComponent_Entity
                    {

                    			newCompositeNode(grammarAccess.getComponentAccess().getComponent_EntityParserRuleCall_3());
                    		
                    pushFollow(FOLLOW_2);
                    this_Component_Entity_3=ruleComponent_Entity();

                    state._fsp--;


                    			current = this_Component_Entity_3;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;

            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComponent"


    // $ANTLR start "entryRuleComponent_Process"
    // InternalSeML.g:552:1: entryRuleComponent_Process returns [EObject current=null] : iv_ruleComponent_Process= ruleComponent_Process EOF ;
    public final EObject entryRuleComponent_Process() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponent_Process = null;


        try {
            // InternalSeML.g:552:58: (iv_ruleComponent_Process= ruleComponent_Process EOF )
            // InternalSeML.g:553:2: iv_ruleComponent_Process= ruleComponent_Process EOF
            {
             newCompositeNode(grammarAccess.getComponent_ProcessRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComponent_Process=ruleComponent_Process();

            state._fsp--;

             current =iv_ruleComponent_Process; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComponent_Process"


    // $ANTLR start "ruleComponent_Process"
    // InternalSeML.g:559:1: ruleComponent_Process returns [EObject current=null] : (otherlv_0= 'CompProcess' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleComponent_Process() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_iri_2_0=null;


        	enterRule();

        try {
            // InternalSeML.g:565:2: ( (otherlv_0= 'CompProcess' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) )
            // InternalSeML.g:566:2: (otherlv_0= 'CompProcess' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            {
            // InternalSeML.g:566:2: (otherlv_0= 'CompProcess' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            // InternalSeML.g:567:3: otherlv_0= 'CompProcess' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getComponent_ProcessAccess().getCompProcessKeyword_0());
            		
            // InternalSeML.g:571:3: ( (lv_name_1_0= RULE_IRI ) )
            // InternalSeML.g:572:4: (lv_name_1_0= RULE_IRI )
            {
            // InternalSeML.g:572:4: (lv_name_1_0= RULE_IRI )
            // InternalSeML.g:573:5: lv_name_1_0= RULE_IRI
            {
            lv_name_1_0=(Token)match(input,RULE_IRI,FOLLOW_9); 

            					newLeafNode(lv_name_1_0, grammarAccess.getComponent_ProcessAccess().getNameIRITerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComponent_ProcessRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.xtext.seml.SeML.IRI");
            				

            }


            }

            // InternalSeML.g:589:3: ( (lv_iri_2_0= RULE_STRING ) )
            // InternalSeML.g:590:4: (lv_iri_2_0= RULE_STRING )
            {
            // InternalSeML.g:590:4: (lv_iri_2_0= RULE_STRING )
            // InternalSeML.g:591:5: lv_iri_2_0= RULE_STRING
            {
            lv_iri_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_iri_2_0, grammarAccess.getComponent_ProcessAccess().getIriSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComponent_ProcessRule());
            					}
            					setWithLastConsumed(
            						current,
            						"iri",
            						lv_iri_2_0,
            						"org.xtext.seml.SeML.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComponent_Process"


    // $ANTLR start "entryRuleComponent_Event"
    // InternalSeML.g:611:1: entryRuleComponent_Event returns [EObject current=null] : iv_ruleComponent_Event= ruleComponent_Event EOF ;
    public final EObject entryRuleComponent_Event() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponent_Event = null;


        try {
            // InternalSeML.g:611:56: (iv_ruleComponent_Event= ruleComponent_Event EOF )
            // InternalSeML.g:612:2: iv_ruleComponent_Event= ruleComponent_Event EOF
            {
             newCompositeNode(grammarAccess.getComponent_EventRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComponent_Event=ruleComponent_Event();

            state._fsp--;

             current =iv_ruleComponent_Event; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComponent_Event"


    // $ANTLR start "ruleComponent_Event"
    // InternalSeML.g:618:1: ruleComponent_Event returns [EObject current=null] : (otherlv_0= 'CompEvent' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleComponent_Event() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_iri_2_0=null;


        	enterRule();

        try {
            // InternalSeML.g:624:2: ( (otherlv_0= 'CompEvent' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) )
            // InternalSeML.g:625:2: (otherlv_0= 'CompEvent' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            {
            // InternalSeML.g:625:2: (otherlv_0= 'CompEvent' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            // InternalSeML.g:626:3: otherlv_0= 'CompEvent' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,18,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getComponent_EventAccess().getCompEventKeyword_0());
            		
            // InternalSeML.g:630:3: ( (lv_name_1_0= RULE_IRI ) )
            // InternalSeML.g:631:4: (lv_name_1_0= RULE_IRI )
            {
            // InternalSeML.g:631:4: (lv_name_1_0= RULE_IRI )
            // InternalSeML.g:632:5: lv_name_1_0= RULE_IRI
            {
            lv_name_1_0=(Token)match(input,RULE_IRI,FOLLOW_9); 

            					newLeafNode(lv_name_1_0, grammarAccess.getComponent_EventAccess().getNameIRITerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComponent_EventRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.xtext.seml.SeML.IRI");
            				

            }


            }

            // InternalSeML.g:648:3: ( (lv_iri_2_0= RULE_STRING ) )
            // InternalSeML.g:649:4: (lv_iri_2_0= RULE_STRING )
            {
            // InternalSeML.g:649:4: (lv_iri_2_0= RULE_STRING )
            // InternalSeML.g:650:5: lv_iri_2_0= RULE_STRING
            {
            lv_iri_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_iri_2_0, grammarAccess.getComponent_EventAccess().getIriSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComponent_EventRule());
            					}
            					setWithLastConsumed(
            						current,
            						"iri",
            						lv_iri_2_0,
            						"org.xtext.seml.SeML.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComponent_Event"


    // $ANTLR start "entryRuleComponent_Property"
    // InternalSeML.g:670:1: entryRuleComponent_Property returns [EObject current=null] : iv_ruleComponent_Property= ruleComponent_Property EOF ;
    public final EObject entryRuleComponent_Property() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponent_Property = null;


        try {
            // InternalSeML.g:670:59: (iv_ruleComponent_Property= ruleComponent_Property EOF )
            // InternalSeML.g:671:2: iv_ruleComponent_Property= ruleComponent_Property EOF
            {
             newCompositeNode(grammarAccess.getComponent_PropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComponent_Property=ruleComponent_Property();

            state._fsp--;

             current =iv_ruleComponent_Property; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComponent_Property"


    // $ANTLR start "ruleComponent_Property"
    // InternalSeML.g:677:1: ruleComponent_Property returns [EObject current=null] : (otherlv_0= 'CompProperty' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleComponent_Property() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_iri_2_0=null;


        	enterRule();

        try {
            // InternalSeML.g:683:2: ( (otherlv_0= 'CompProperty' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) )
            // InternalSeML.g:684:2: (otherlv_0= 'CompProperty' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            {
            // InternalSeML.g:684:2: (otherlv_0= 'CompProperty' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            // InternalSeML.g:685:3: otherlv_0= 'CompProperty' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,19,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getComponent_PropertyAccess().getCompPropertyKeyword_0());
            		
            // InternalSeML.g:689:3: ( (lv_name_1_0= RULE_IRI ) )
            // InternalSeML.g:690:4: (lv_name_1_0= RULE_IRI )
            {
            // InternalSeML.g:690:4: (lv_name_1_0= RULE_IRI )
            // InternalSeML.g:691:5: lv_name_1_0= RULE_IRI
            {
            lv_name_1_0=(Token)match(input,RULE_IRI,FOLLOW_9); 

            					newLeafNode(lv_name_1_0, grammarAccess.getComponent_PropertyAccess().getNameIRITerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComponent_PropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.xtext.seml.SeML.IRI");
            				

            }


            }

            // InternalSeML.g:707:3: ( (lv_iri_2_0= RULE_STRING ) )
            // InternalSeML.g:708:4: (lv_iri_2_0= RULE_STRING )
            {
            // InternalSeML.g:708:4: (lv_iri_2_0= RULE_STRING )
            // InternalSeML.g:709:5: lv_iri_2_0= RULE_STRING
            {
            lv_iri_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_iri_2_0, grammarAccess.getComponent_PropertyAccess().getIriSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComponent_PropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"iri",
            						lv_iri_2_0,
            						"org.xtext.seml.SeML.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComponent_Property"


    // $ANTLR start "entryRuleComponent_Entity"
    // InternalSeML.g:729:1: entryRuleComponent_Entity returns [EObject current=null] : iv_ruleComponent_Entity= ruleComponent_Entity EOF ;
    public final EObject entryRuleComponent_Entity() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleComponent_Entity = null;


        try {
            // InternalSeML.g:729:57: (iv_ruleComponent_Entity= ruleComponent_Entity EOF )
            // InternalSeML.g:730:2: iv_ruleComponent_Entity= ruleComponent_Entity EOF
            {
             newCompositeNode(grammarAccess.getComponent_EntityRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleComponent_Entity=ruleComponent_Entity();

            state._fsp--;

             current =iv_ruleComponent_Entity; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleComponent_Entity"


    // $ANTLR start "ruleComponent_Entity"
    // InternalSeML.g:736:1: ruleComponent_Entity returns [EObject current=null] : (otherlv_0= 'CompEntity' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleComponent_Entity() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_iri_2_0=null;


        	enterRule();

        try {
            // InternalSeML.g:742:2: ( (otherlv_0= 'CompEntity' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) )
            // InternalSeML.g:743:2: (otherlv_0= 'CompEntity' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            {
            // InternalSeML.g:743:2: (otherlv_0= 'CompEntity' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            // InternalSeML.g:744:3: otherlv_0= 'CompEntity' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,20,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getComponent_EntityAccess().getCompEntityKeyword_0());
            		
            // InternalSeML.g:748:3: ( (lv_name_1_0= RULE_IRI ) )
            // InternalSeML.g:749:4: (lv_name_1_0= RULE_IRI )
            {
            // InternalSeML.g:749:4: (lv_name_1_0= RULE_IRI )
            // InternalSeML.g:750:5: lv_name_1_0= RULE_IRI
            {
            lv_name_1_0=(Token)match(input,RULE_IRI,FOLLOW_9); 

            					newLeafNode(lv_name_1_0, grammarAccess.getComponent_EntityAccess().getNameIRITerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComponent_EntityRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.xtext.seml.SeML.IRI");
            				

            }


            }

            // InternalSeML.g:766:3: ( (lv_iri_2_0= RULE_STRING ) )
            // InternalSeML.g:767:4: (lv_iri_2_0= RULE_STRING )
            {
            // InternalSeML.g:767:4: (lv_iri_2_0= RULE_STRING )
            // InternalSeML.g:768:5: lv_iri_2_0= RULE_STRING
            {
            lv_iri_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_iri_2_0, grammarAccess.getComponent_EntityAccess().getIriSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getComponent_EntityRule());
            					}
            					setWithLastConsumed(
            						current,
            						"iri",
            						lv_iri_2_0,
            						"org.xtext.seml.SeML.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleComponent_Entity"


    // $ANTLR start "entryRuleObjectProperty"
    // InternalSeML.g:788:1: entryRuleObjectProperty returns [EObject current=null] : iv_ruleObjectProperty= ruleObjectProperty EOF ;
    public final EObject entryRuleObjectProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObjectProperty = null;


        try {
            // InternalSeML.g:788:55: (iv_ruleObjectProperty= ruleObjectProperty EOF )
            // InternalSeML.g:789:2: iv_ruleObjectProperty= ruleObjectProperty EOF
            {
             newCompositeNode(grammarAccess.getObjectPropertyRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleObjectProperty=ruleObjectProperty();

            state._fsp--;

             current =iv_ruleObjectProperty; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleObjectProperty"


    // $ANTLR start "ruleObjectProperty"
    // InternalSeML.g:795:1: ruleObjectProperty returns [EObject current=null] : (otherlv_0= 'ObjectProperty' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleObjectProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_iri_2_0=null;


        	enterRule();

        try {
            // InternalSeML.g:801:2: ( (otherlv_0= 'ObjectProperty' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) )
            // InternalSeML.g:802:2: (otherlv_0= 'ObjectProperty' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            {
            // InternalSeML.g:802:2: (otherlv_0= 'ObjectProperty' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            // InternalSeML.g:803:3: otherlv_0= 'ObjectProperty' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,21,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getObjectPropertyAccess().getObjectPropertyKeyword_0());
            		
            // InternalSeML.g:807:3: ( (lv_name_1_0= RULE_IRI ) )
            // InternalSeML.g:808:4: (lv_name_1_0= RULE_IRI )
            {
            // InternalSeML.g:808:4: (lv_name_1_0= RULE_IRI )
            // InternalSeML.g:809:5: lv_name_1_0= RULE_IRI
            {
            lv_name_1_0=(Token)match(input,RULE_IRI,FOLLOW_9); 

            					newLeafNode(lv_name_1_0, grammarAccess.getObjectPropertyAccess().getNameIRITerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getObjectPropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.xtext.seml.SeML.IRI");
            				

            }


            }

            // InternalSeML.g:825:3: ( (lv_iri_2_0= RULE_STRING ) )
            // InternalSeML.g:826:4: (lv_iri_2_0= RULE_STRING )
            {
            // InternalSeML.g:826:4: (lv_iri_2_0= RULE_STRING )
            // InternalSeML.g:827:5: lv_iri_2_0= RULE_STRING
            {
            lv_iri_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_iri_2_0, grammarAccess.getObjectPropertyAccess().getIriSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getObjectPropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"iri",
            						lv_iri_2_0,
            						"org.xtext.seml.SeML.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleObjectProperty"


    // $ANTLR start "entryRuleCharacteristic"
    // InternalSeML.g:847:1: entryRuleCharacteristic returns [EObject current=null] : iv_ruleCharacteristic= ruleCharacteristic EOF ;
    public final EObject entryRuleCharacteristic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCharacteristic = null;


        try {
            // InternalSeML.g:847:55: (iv_ruleCharacteristic= ruleCharacteristic EOF )
            // InternalSeML.g:848:2: iv_ruleCharacteristic= ruleCharacteristic EOF
            {
             newCompositeNode(grammarAccess.getCharacteristicRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleCharacteristic=ruleCharacteristic();

            state._fsp--;

             current =iv_ruleCharacteristic; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleCharacteristic"


    // $ANTLR start "ruleCharacteristic"
    // InternalSeML.g:854:1: ruleCharacteristic returns [EObject current=null] : (otherlv_0= 'Characteristic' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleCharacteristic() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_iri_2_0=null;


        	enterRule();

        try {
            // InternalSeML.g:860:2: ( (otherlv_0= 'Characteristic' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) )
            // InternalSeML.g:861:2: (otherlv_0= 'Characteristic' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            {
            // InternalSeML.g:861:2: (otherlv_0= 'Characteristic' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            // InternalSeML.g:862:3: otherlv_0= 'Characteristic' ( (lv_name_1_0= RULE_IRI ) ) ( (lv_iri_2_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,22,FOLLOW_10); 

            			newLeafNode(otherlv_0, grammarAccess.getCharacteristicAccess().getCharacteristicKeyword_0());
            		
            // InternalSeML.g:866:3: ( (lv_name_1_0= RULE_IRI ) )
            // InternalSeML.g:867:4: (lv_name_1_0= RULE_IRI )
            {
            // InternalSeML.g:867:4: (lv_name_1_0= RULE_IRI )
            // InternalSeML.g:868:5: lv_name_1_0= RULE_IRI
            {
            lv_name_1_0=(Token)match(input,RULE_IRI,FOLLOW_9); 

            					newLeafNode(lv_name_1_0, grammarAccess.getCharacteristicAccess().getNameIRITerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCharacteristicRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.xtext.seml.SeML.IRI");
            				

            }


            }

            // InternalSeML.g:884:3: ( (lv_iri_2_0= RULE_STRING ) )
            // InternalSeML.g:885:4: (lv_iri_2_0= RULE_STRING )
            {
            // InternalSeML.g:885:4: (lv_iri_2_0= RULE_STRING )
            // InternalSeML.g:886:5: lv_iri_2_0= RULE_STRING
            {
            lv_iri_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_iri_2_0, grammarAccess.getCharacteristicAccess().getIriSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCharacteristicRule());
            					}
            					setWithLastConsumed(
            						current,
            						"iri",
            						lv_iri_2_0,
            						"org.xtext.seml.SeML.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleCharacteristic"


    // $ANTLR start "entryRuleMetaIndividual"
    // InternalSeML.g:906:1: entryRuleMetaIndividual returns [EObject current=null] : iv_ruleMetaIndividual= ruleMetaIndividual EOF ;
    public final EObject entryRuleMetaIndividual() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMetaIndividual = null;


        try {
            // InternalSeML.g:906:55: (iv_ruleMetaIndividual= ruleMetaIndividual EOF )
            // InternalSeML.g:907:2: iv_ruleMetaIndividual= ruleMetaIndividual EOF
            {
             newCompositeNode(grammarAccess.getMetaIndividualRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleMetaIndividual=ruleMetaIndividual();

            state._fsp--;

             current =iv_ruleMetaIndividual; 
            match(input,EOF,FOLLOW_2); 

            }

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMetaIndividual"


    // $ANTLR start "ruleMetaIndividual"
    // InternalSeML.g:913:1: ruleMetaIndividual returns [EObject current=null] : (otherlv_0= 'MetaIndividual' ( (lv_cls_1_0= RULE_STRING ) )+ ( (lv_name_2_0= RULE_IRI ) ) ( (lv_iri_3_0= RULE_STRING ) ) ) ;
    public final EObject ruleMetaIndividual() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_cls_1_0=null;
        Token lv_name_2_0=null;
        Token lv_iri_3_0=null;


        	enterRule();

        try {
            // InternalSeML.g:919:2: ( (otherlv_0= 'MetaIndividual' ( (lv_cls_1_0= RULE_STRING ) )+ ( (lv_name_2_0= RULE_IRI ) ) ( (lv_iri_3_0= RULE_STRING ) ) ) )
            // InternalSeML.g:920:2: (otherlv_0= 'MetaIndividual' ( (lv_cls_1_0= RULE_STRING ) )+ ( (lv_name_2_0= RULE_IRI ) ) ( (lv_iri_3_0= RULE_STRING ) ) )
            {
            // InternalSeML.g:920:2: (otherlv_0= 'MetaIndividual' ( (lv_cls_1_0= RULE_STRING ) )+ ( (lv_name_2_0= RULE_IRI ) ) ( (lv_iri_3_0= RULE_STRING ) ) )
            // InternalSeML.g:921:3: otherlv_0= 'MetaIndividual' ( (lv_cls_1_0= RULE_STRING ) )+ ( (lv_name_2_0= RULE_IRI ) ) ( (lv_iri_3_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,23,FOLLOW_9); 

            			newLeafNode(otherlv_0, grammarAccess.getMetaIndividualAccess().getMetaIndividualKeyword_0());
            		
            // InternalSeML.g:925:3: ( (lv_cls_1_0= RULE_STRING ) )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==RULE_STRING) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalSeML.g:926:4: (lv_cls_1_0= RULE_STRING )
            	    {
            	    // InternalSeML.g:926:4: (lv_cls_1_0= RULE_STRING )
            	    // InternalSeML.g:927:5: lv_cls_1_0= RULE_STRING
            	    {
            	    lv_cls_1_0=(Token)match(input,RULE_STRING,FOLLOW_12); 

            	    					newLeafNode(lv_cls_1_0, grammarAccess.getMetaIndividualAccess().getClsSTRINGTerminalRuleCall_1_0());
            	    				

            	    					if (current==null) {
            	    						current = createModelElement(grammarAccess.getMetaIndividualRule());
            	    					}
            	    					addWithLastConsumed(
            	    						current,
            	    						"cls",
            	    						lv_cls_1_0,
            	    						"org.xtext.seml.SeML.STRING");
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);

            // InternalSeML.g:943:3: ( (lv_name_2_0= RULE_IRI ) )
            // InternalSeML.g:944:4: (lv_name_2_0= RULE_IRI )
            {
            // InternalSeML.g:944:4: (lv_name_2_0= RULE_IRI )
            // InternalSeML.g:945:5: lv_name_2_0= RULE_IRI
            {
            lv_name_2_0=(Token)match(input,RULE_IRI,FOLLOW_9); 

            					newLeafNode(lv_name_2_0, grammarAccess.getMetaIndividualAccess().getNameIRITerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMetaIndividualRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_2_0,
            						"org.xtext.seml.SeML.IRI");
            				

            }


            }

            // InternalSeML.g:961:3: ( (lv_iri_3_0= RULE_STRING ) )
            // InternalSeML.g:962:4: (lv_iri_3_0= RULE_STRING )
            {
            // InternalSeML.g:962:4: (lv_iri_3_0= RULE_STRING )
            // InternalSeML.g:963:5: lv_iri_3_0= RULE_STRING
            {
            lv_iri_3_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_iri_3_0, grammarAccess.getMetaIndividualAccess().getIriSTRINGTerminalRuleCall_3_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getMetaIndividualRule());
            					}
            					setWithLastConsumed(
            						current,
            						"iri",
            						lv_iri_3_0,
            						"org.xtext.seml.SeML.STRING");
            				

            }


            }


            }


            }


            	leaveRule();

        }

            catch (RecognitionException re) {
                recover(input,re);
                appendSkippedTokens();
            }
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMetaIndividual"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x0000000000016022L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000014022L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000FE0002L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000E00002L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x0000000000A00002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x0000000000800002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000008000L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000000030L});

}