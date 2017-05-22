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
    // InternalSeML.g:107:1: ruleMainModel returns [EObject current=null] : ( ( (lv_imports_0_0= ruleImport ) )+ (otherlv_1= 'use' ( (otherlv_2= RULE_ALIAS ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )* )? ( (lv_sentences_5_0= ruleSentence ) )* ) ;
    public final EObject ruleMainModel() throws RecognitionException {
        EObject current = null;

        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;
        EObject lv_imports_0_0 = null;

        EObject lv_sentences_5_0 = null;



        	enterRule();

        try {
            // InternalSeML.g:113:2: ( ( ( (lv_imports_0_0= ruleImport ) )+ (otherlv_1= 'use' ( (otherlv_2= RULE_ALIAS ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )* )? ( (lv_sentences_5_0= ruleSentence ) )* ) )
            // InternalSeML.g:114:2: ( ( (lv_imports_0_0= ruleImport ) )+ (otherlv_1= 'use' ( (otherlv_2= RULE_ALIAS ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )* )? ( (lv_sentences_5_0= ruleSentence ) )* )
            {
            // InternalSeML.g:114:2: ( ( (lv_imports_0_0= ruleImport ) )+ (otherlv_1= 'use' ( (otherlv_2= RULE_ALIAS ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )* )? ( (lv_sentences_5_0= ruleSentence ) )* )
            // InternalSeML.g:115:3: ( (lv_imports_0_0= ruleImport ) )+ (otherlv_1= 'use' ( (otherlv_2= RULE_ALIAS ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )* )? ( (lv_sentences_5_0= ruleSentence ) )*
            {
            // InternalSeML.g:115:3: ( (lv_imports_0_0= ruleImport ) )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==15) ) {
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

            // InternalSeML.g:134:3: (otherlv_1= 'use' ( (otherlv_2= RULE_ALIAS ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )* )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0==13) ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalSeML.g:135:4: otherlv_1= 'use' ( (otherlv_2= RULE_ALIAS ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )*
                    {
                    otherlv_1=(Token)match(input,13,FOLLOW_4); 

                    				newLeafNode(otherlv_1, grammarAccess.getMainModelAccess().getUseKeyword_1_0());
                    			
                    // InternalSeML.g:139:4: ( (otherlv_2= RULE_ALIAS ) )
                    // InternalSeML.g:140:5: (otherlv_2= RULE_ALIAS )
                    {
                    // InternalSeML.g:140:5: (otherlv_2= RULE_ALIAS )
                    // InternalSeML.g:141:6: otherlv_2= RULE_ALIAS
                    {

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getMainModelRule());
                    						}
                    					
                    otherlv_2=(Token)match(input,RULE_ALIAS,FOLLOW_5); 

                    						newLeafNode(otherlv_2, grammarAccess.getMainModelAccess().getUseChCharacteristicCrossReference_1_1_0());
                    					

                    }


                    }

                    // InternalSeML.g:152:4: (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )*
                    loop3:
                    do {
                        int alt3=2;
                        int LA3_0 = input.LA(1);

                        if ( (LA3_0==14) ) {
                            alt3=1;
                        }


                        switch (alt3) {
                    	case 1 :
                    	    // InternalSeML.g:153:5: otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) )
                    	    {
                    	    otherlv_3=(Token)match(input,14,FOLLOW_4); 

                    	    					newLeafNode(otherlv_3, grammarAccess.getMainModelAccess().getCommaKeyword_1_2_0());
                    	    				
                    	    // InternalSeML.g:157:5: ( (otherlv_4= RULE_ALIAS ) )
                    	    // InternalSeML.g:158:6: (otherlv_4= RULE_ALIAS )
                    	    {
                    	    // InternalSeML.g:158:6: (otherlv_4= RULE_ALIAS )
                    	    // InternalSeML.g:159:7: otherlv_4= RULE_ALIAS
                    	    {

                    	    							if (current==null) {
                    	    								current = createModelElement(grammarAccess.getMainModelRule());
                    	    							}
                    	    						
                    	    otherlv_4=(Token)match(input,RULE_ALIAS,FOLLOW_5); 

                    	    							newLeafNode(otherlv_4, grammarAccess.getMainModelAccess().getUseChCharacteristicCrossReference_1_2_1_0());
                    	    						

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop3;
                        }
                    } while (true);


                    }
                    break;

            }

            // InternalSeML.g:172:3: ( (lv_sentences_5_0= ruleSentence ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==RULE_ALIAS) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalSeML.g:173:4: (lv_sentences_5_0= ruleSentence )
            	    {
            	    // InternalSeML.g:173:4: (lv_sentences_5_0= ruleSentence )
            	    // InternalSeML.g:174:5: lv_sentences_5_0= ruleSentence
            	    {

            	    					newCompositeNode(grammarAccess.getMainModelAccess().getSentencesSentenceParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_6);
            	    lv_sentences_5_0=ruleSentence();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getMainModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"sentences",
            	    						lv_sentences_5_0,
            	    						"org.xtext.seml.SeML.Sentence");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
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
    // InternalSeML.g:195:1: entryRuleImportModel returns [EObject current=null] : iv_ruleImportModel= ruleImportModel EOF ;
    public final EObject entryRuleImportModel() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImportModel = null;


        try {
            // InternalSeML.g:195:52: (iv_ruleImportModel= ruleImportModel EOF )
            // InternalSeML.g:196:2: iv_ruleImportModel= ruleImportModel EOF
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
    // InternalSeML.g:202:1: ruleImportModel returns [EObject current=null] : ( ( (lv_staticIndividuals_0_0= ruleStaticIndividual ) )+ ( (lv_individualOptions_1_0= ruleFreeIndividual ) )* ( (lv_characteristics_2_0= ruleCharacteristic ) )* ( (lv_objectProperties_3_0= ruleObjectProperty ) )* ) ;
    public final EObject ruleImportModel() throws RecognitionException {
        EObject current = null;

        EObject lv_staticIndividuals_0_0 = null;

        EObject lv_individualOptions_1_0 = null;

        EObject lv_characteristics_2_0 = null;

        EObject lv_objectProperties_3_0 = null;



        	enterRule();

        try {
            // InternalSeML.g:208:2: ( ( ( (lv_staticIndividuals_0_0= ruleStaticIndividual ) )+ ( (lv_individualOptions_1_0= ruleFreeIndividual ) )* ( (lv_characteristics_2_0= ruleCharacteristic ) )* ( (lv_objectProperties_3_0= ruleObjectProperty ) )* ) )
            // InternalSeML.g:209:2: ( ( (lv_staticIndividuals_0_0= ruleStaticIndividual ) )+ ( (lv_individualOptions_1_0= ruleFreeIndividual ) )* ( (lv_characteristics_2_0= ruleCharacteristic ) )* ( (lv_objectProperties_3_0= ruleObjectProperty ) )* )
            {
            // InternalSeML.g:209:2: ( ( (lv_staticIndividuals_0_0= ruleStaticIndividual ) )+ ( (lv_individualOptions_1_0= ruleFreeIndividual ) )* ( (lv_characteristics_2_0= ruleCharacteristic ) )* ( (lv_objectProperties_3_0= ruleObjectProperty ) )* )
            // InternalSeML.g:210:3: ( (lv_staticIndividuals_0_0= ruleStaticIndividual ) )+ ( (lv_individualOptions_1_0= ruleFreeIndividual ) )* ( (lv_characteristics_2_0= ruleCharacteristic ) )* ( (lv_objectProperties_3_0= ruleObjectProperty ) )*
            {
            // InternalSeML.g:210:3: ( (lv_staticIndividuals_0_0= ruleStaticIndividual ) )+
            int cnt6=0;
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==20) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // InternalSeML.g:211:4: (lv_staticIndividuals_0_0= ruleStaticIndividual )
            	    {
            	    // InternalSeML.g:211:4: (lv_staticIndividuals_0_0= ruleStaticIndividual )
            	    // InternalSeML.g:212:5: lv_staticIndividuals_0_0= ruleStaticIndividual
            	    {

            	    					newCompositeNode(grammarAccess.getImportModelAccess().getStaticIndividualsStaticIndividualParserRuleCall_0_0());
            	    				
            	    pushFollow(FOLLOW_7);
            	    lv_staticIndividuals_0_0=ruleStaticIndividual();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getImportModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"staticIndividuals",
            	    						lv_staticIndividuals_0_0,
            	    						"org.xtext.seml.SeML.StaticIndividual");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt6 >= 1 ) break loop6;
                        EarlyExitException eee =
                            new EarlyExitException(6, input);
                        throw eee;
                }
                cnt6++;
            } while (true);

            // InternalSeML.g:229:3: ( (lv_individualOptions_1_0= ruleFreeIndividual ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==17) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalSeML.g:230:4: (lv_individualOptions_1_0= ruleFreeIndividual )
            	    {
            	    // InternalSeML.g:230:4: (lv_individualOptions_1_0= ruleFreeIndividual )
            	    // InternalSeML.g:231:5: lv_individualOptions_1_0= ruleFreeIndividual
            	    {

            	    					newCompositeNode(grammarAccess.getImportModelAccess().getIndividualOptionsFreeIndividualParserRuleCall_1_0());
            	    				
            	    pushFollow(FOLLOW_8);
            	    lv_individualOptions_1_0=ruleFreeIndividual();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getImportModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"individualOptions",
            	    						lv_individualOptions_1_0,
            	    						"org.xtext.seml.SeML.FreeIndividual");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            // InternalSeML.g:248:3: ( (lv_characteristics_2_0= ruleCharacteristic ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==19) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalSeML.g:249:4: (lv_characteristics_2_0= ruleCharacteristic )
            	    {
            	    // InternalSeML.g:249:4: (lv_characteristics_2_0= ruleCharacteristic )
            	    // InternalSeML.g:250:5: lv_characteristics_2_0= ruleCharacteristic
            	    {

            	    					newCompositeNode(grammarAccess.getImportModelAccess().getCharacteristicsCharacteristicParserRuleCall_2_0());
            	    				
            	    pushFollow(FOLLOW_9);
            	    lv_characteristics_2_0=ruleCharacteristic();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getImportModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"characteristics",
            	    						lv_characteristics_2_0,
            	    						"org.xtext.seml.SeML.Characteristic");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // InternalSeML.g:267:3: ( (lv_objectProperties_3_0= ruleObjectProperty ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==18) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalSeML.g:268:4: (lv_objectProperties_3_0= ruleObjectProperty )
            	    {
            	    // InternalSeML.g:268:4: (lv_objectProperties_3_0= ruleObjectProperty )
            	    // InternalSeML.g:269:5: lv_objectProperties_3_0= ruleObjectProperty
            	    {

            	    					newCompositeNode(grammarAccess.getImportModelAccess().getObjectPropertiesObjectPropertyParserRuleCall_3_0());
            	    				
            	    pushFollow(FOLLOW_10);
            	    lv_objectProperties_3_0=ruleObjectProperty();

            	    state._fsp--;


            	    					if (current==null) {
            	    						current = createModelElementForParent(grammarAccess.getImportModelRule());
            	    					}
            	    					add(
            	    						current,
            	    						"objectProperties",
            	    						lv_objectProperties_3_0,
            	    						"org.xtext.seml.SeML.ObjectProperty");
            	    					afterParserOrEnumRuleCall();
            	    				

            	    }


            	    }
            	    break;

            	default :
            	    break loop9;
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
    // InternalSeML.g:290:1: entryRuleImport returns [EObject current=null] : iv_ruleImport= ruleImport EOF ;
    public final EObject entryRuleImport() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleImport = null;


        try {
            // InternalSeML.g:290:47: (iv_ruleImport= ruleImport EOF )
            // InternalSeML.g:291:2: iv_ruleImport= ruleImport EOF
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
    // InternalSeML.g:297:1: ruleImport returns [EObject current=null] : (otherlv_0= 'import' ( (lv_name_1_0= RULE_STRING ) ) ) ;
    public final EObject ruleImport() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;


        	enterRule();

        try {
            // InternalSeML.g:303:2: ( (otherlv_0= 'import' ( (lv_name_1_0= RULE_STRING ) ) ) )
            // InternalSeML.g:304:2: (otherlv_0= 'import' ( (lv_name_1_0= RULE_STRING ) ) )
            {
            // InternalSeML.g:304:2: (otherlv_0= 'import' ( (lv_name_1_0= RULE_STRING ) ) )
            // InternalSeML.g:305:3: otherlv_0= 'import' ( (lv_name_1_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,15,FOLLOW_11); 

            			newLeafNode(otherlv_0, grammarAccess.getImportAccess().getImportKeyword_0());
            		
            // InternalSeML.g:309:3: ( (lv_name_1_0= RULE_STRING ) )
            // InternalSeML.g:310:4: (lv_name_1_0= RULE_STRING )
            {
            // InternalSeML.g:310:4: (lv_name_1_0= RULE_STRING )
            // InternalSeML.g:311:5: lv_name_1_0= RULE_STRING
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
    // InternalSeML.g:331:1: entryRuleSentence returns [EObject current=null] : iv_ruleSentence= ruleSentence EOF ;
    public final EObject entryRuleSentence() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSentence = null;


        try {
            // InternalSeML.g:331:49: (iv_ruleSentence= ruleSentence EOF )
            // InternalSeML.g:332:2: iv_ruleSentence= ruleSentence EOF
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
    // InternalSeML.g:338:1: ruleSentence returns [EObject current=null] : (this_Relation_0= ruleRelation | this_Assignment_1= ruleAssignment ) ;
    public final EObject ruleSentence() throws RecognitionException {
        EObject current = null;

        EObject this_Relation_0 = null;

        EObject this_Assignment_1 = null;



        	enterRule();

        try {
            // InternalSeML.g:344:2: ( (this_Relation_0= ruleRelation | this_Assignment_1= ruleAssignment ) )
            // InternalSeML.g:345:2: (this_Relation_0= ruleRelation | this_Assignment_1= ruleAssignment )
            {
            // InternalSeML.g:345:2: (this_Relation_0= ruleRelation | this_Assignment_1= ruleAssignment )
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0==RULE_ALIAS) ) {
                int LA10_1 = input.LA(2);

                if ( (LA10_1==RULE_ALIAS) ) {
                    alt10=1;
                }
                else if ( (LA10_1==16) ) {
                    alt10=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("", 10, 1, input);

                    throw nvae;
                }
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 10, 0, input);

                throw nvae;
            }
            switch (alt10) {
                case 1 :
                    // InternalSeML.g:346:3: this_Relation_0= ruleRelation
                    {

                    			newCompositeNode(grammarAccess.getSentenceAccess().getRelationParserRuleCall_0());
                    		
                    pushFollow(FOLLOW_2);
                    this_Relation_0=ruleRelation();

                    state._fsp--;


                    			current = this_Relation_0;
                    			afterParserOrEnumRuleCall();
                    		

                    }
                    break;
                case 2 :
                    // InternalSeML.g:355:3: this_Assignment_1= ruleAssignment
                    {

                    			newCompositeNode(grammarAccess.getSentenceAccess().getAssignmentParserRuleCall_1());
                    		
                    pushFollow(FOLLOW_2);
                    this_Assignment_1=ruleAssignment();

                    state._fsp--;


                    			current = this_Assignment_1;
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


    // $ANTLR start "entryRuleRelation"
    // InternalSeML.g:367:1: entryRuleRelation returns [EObject current=null] : iv_ruleRelation= ruleRelation EOF ;
    public final EObject entryRuleRelation() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRelation = null;


        try {
            // InternalSeML.g:367:49: (iv_ruleRelation= ruleRelation EOF )
            // InternalSeML.g:368:2: iv_ruleRelation= ruleRelation EOF
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
    // InternalSeML.g:374:1: ruleRelation returns [EObject current=null] : ( ( (otherlv_0= RULE_ALIAS ) ) ( (otherlv_1= RULE_ALIAS ) ) ( (otherlv_2= RULE_ALIAS ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )* ) ;
    public final EObject ruleRelation() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        Token otherlv_2=null;
        Token otherlv_3=null;
        Token otherlv_4=null;


        	enterRule();

        try {
            // InternalSeML.g:380:2: ( ( ( (otherlv_0= RULE_ALIAS ) ) ( (otherlv_1= RULE_ALIAS ) ) ( (otherlv_2= RULE_ALIAS ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )* ) )
            // InternalSeML.g:381:2: ( ( (otherlv_0= RULE_ALIAS ) ) ( (otherlv_1= RULE_ALIAS ) ) ( (otherlv_2= RULE_ALIAS ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )* )
            {
            // InternalSeML.g:381:2: ( ( (otherlv_0= RULE_ALIAS ) ) ( (otherlv_1= RULE_ALIAS ) ) ( (otherlv_2= RULE_ALIAS ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )* )
            // InternalSeML.g:382:3: ( (otherlv_0= RULE_ALIAS ) ) ( (otherlv_1= RULE_ALIAS ) ) ( (otherlv_2= RULE_ALIAS ) ) (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )*
            {
            // InternalSeML.g:382:3: ( (otherlv_0= RULE_ALIAS ) )
            // InternalSeML.g:383:4: (otherlv_0= RULE_ALIAS )
            {
            // InternalSeML.g:383:4: (otherlv_0= RULE_ALIAS )
            // InternalSeML.g:384:5: otherlv_0= RULE_ALIAS
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRelationRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ALIAS,FOLLOW_4); 

            					newLeafNode(otherlv_0, grammarAccess.getRelationAccess().getInd1IndividualCrossReference_0_0());
            				

            }


            }

            // InternalSeML.g:395:3: ( (otherlv_1= RULE_ALIAS ) )
            // InternalSeML.g:396:4: (otherlv_1= RULE_ALIAS )
            {
            // InternalSeML.g:396:4: (otherlv_1= RULE_ALIAS )
            // InternalSeML.g:397:5: otherlv_1= RULE_ALIAS
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRelationRule());
            					}
            				
            otherlv_1=(Token)match(input,RULE_ALIAS,FOLLOW_4); 

            					newLeafNode(otherlv_1, grammarAccess.getRelationAccess().getObjObjectPropertyCrossReference_1_0());
            				

            }


            }

            // InternalSeML.g:408:3: ( (otherlv_2= RULE_ALIAS ) )
            // InternalSeML.g:409:4: (otherlv_2= RULE_ALIAS )
            {
            // InternalSeML.g:409:4: (otherlv_2= RULE_ALIAS )
            // InternalSeML.g:410:5: otherlv_2= RULE_ALIAS
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getRelationRule());
            					}
            				
            otherlv_2=(Token)match(input,RULE_ALIAS,FOLLOW_12); 

            					newLeafNode(otherlv_2, grammarAccess.getRelationAccess().getInd2IndividualCrossReference_2_0());
            				

            }


            }

            // InternalSeML.g:421:3: (otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==14) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalSeML.g:422:4: otherlv_3= ',' ( (otherlv_4= RULE_ALIAS ) )
            	    {
            	    otherlv_3=(Token)match(input,14,FOLLOW_4); 

            	    				newLeafNode(otherlv_3, grammarAccess.getRelationAccess().getCommaKeyword_3_0());
            	    			
            	    // InternalSeML.g:426:4: ( (otherlv_4= RULE_ALIAS ) )
            	    // InternalSeML.g:427:5: (otherlv_4= RULE_ALIAS )
            	    {
            	    // InternalSeML.g:427:5: (otherlv_4= RULE_ALIAS )
            	    // InternalSeML.g:428:6: otherlv_4= RULE_ALIAS
            	    {

            	    						if (current==null) {
            	    							current = createModelElement(grammarAccess.getRelationRule());
            	    						}
            	    					
            	    otherlv_4=(Token)match(input,RULE_ALIAS,FOLLOW_12); 

            	    						newLeafNode(otherlv_4, grammarAccess.getRelationAccess().getInd2IndividualCrossReference_3_1_0());
            	    					

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop11;
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
    // $ANTLR end "ruleRelation"


    // $ANTLR start "entryRuleAssignment"
    // InternalSeML.g:444:1: entryRuleAssignment returns [EObject current=null] : iv_ruleAssignment= ruleAssignment EOF ;
    public final EObject entryRuleAssignment() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleAssignment = null;


        try {
            // InternalSeML.g:444:51: (iv_ruleAssignment= ruleAssignment EOF )
            // InternalSeML.g:445:2: iv_ruleAssignment= ruleAssignment EOF
            {
             newCompositeNode(grammarAccess.getAssignmentRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleAssignment=ruleAssignment();

            state._fsp--;

             current =iv_ruleAssignment; 
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
    // $ANTLR end "entryRuleAssignment"


    // $ANTLR start "ruleAssignment"
    // InternalSeML.g:451:1: ruleAssignment returns [EObject current=null] : ( ( (otherlv_0= RULE_ALIAS ) ) otherlv_1= '=' ( (lv_literal_2_0= ruleValue ) ) ) ;
    public final EObject ruleAssignment() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token otherlv_1=null;
        EObject lv_literal_2_0 = null;



        	enterRule();

        try {
            // InternalSeML.g:457:2: ( ( ( (otherlv_0= RULE_ALIAS ) ) otherlv_1= '=' ( (lv_literal_2_0= ruleValue ) ) ) )
            // InternalSeML.g:458:2: ( ( (otherlv_0= RULE_ALIAS ) ) otherlv_1= '=' ( (lv_literal_2_0= ruleValue ) ) )
            {
            // InternalSeML.g:458:2: ( ( (otherlv_0= RULE_ALIAS ) ) otherlv_1= '=' ( (lv_literal_2_0= ruleValue ) ) )
            // InternalSeML.g:459:3: ( (otherlv_0= RULE_ALIAS ) ) otherlv_1= '=' ( (lv_literal_2_0= ruleValue ) )
            {
            // InternalSeML.g:459:3: ( (otherlv_0= RULE_ALIAS ) )
            // InternalSeML.g:460:4: (otherlv_0= RULE_ALIAS )
            {
            // InternalSeML.g:460:4: (otherlv_0= RULE_ALIAS )
            // InternalSeML.g:461:5: otherlv_0= RULE_ALIAS
            {

            					if (current==null) {
            						current = createModelElement(grammarAccess.getAssignmentRule());
            					}
            				
            otherlv_0=(Token)match(input,RULE_ALIAS,FOLLOW_13); 

            					newLeafNode(otherlv_0, grammarAccess.getAssignmentAccess().getIndIndividualCrossReference_0_0());
            				

            }


            }

            otherlv_1=(Token)match(input,16,FOLLOW_14); 

            			newLeafNode(otherlv_1, grammarAccess.getAssignmentAccess().getEqualsSignKeyword_1());
            		
            // InternalSeML.g:476:3: ( (lv_literal_2_0= ruleValue ) )
            // InternalSeML.g:477:4: (lv_literal_2_0= ruleValue )
            {
            // InternalSeML.g:477:4: (lv_literal_2_0= ruleValue )
            // InternalSeML.g:478:5: lv_literal_2_0= ruleValue
            {

            					newCompositeNode(grammarAccess.getAssignmentAccess().getLiteralValueParserRuleCall_2_0());
            				
            pushFollow(FOLLOW_2);
            lv_literal_2_0=ruleValue();

            state._fsp--;


            					if (current==null) {
            						current = createModelElementForParent(grammarAccess.getAssignmentRule());
            					}
            					set(
            						current,
            						"literal",
            						lv_literal_2_0,
            						"org.xtext.seml.SeML.Value");
            					afterParserOrEnumRuleCall();
            				

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
    // $ANTLR end "ruleAssignment"


    // $ANTLR start "entryRuleValue"
    // InternalSeML.g:499:1: entryRuleValue returns [EObject current=null] : iv_ruleValue= ruleValue EOF ;
    public final EObject entryRuleValue() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleValue = null;


        try {
            // InternalSeML.g:499:46: (iv_ruleValue= ruleValue EOF )
            // InternalSeML.g:500:2: iv_ruleValue= ruleValue EOF
            {
             newCompositeNode(grammarAccess.getValueRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleValue=ruleValue();

            state._fsp--;

             current =iv_ruleValue; 
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
    // $ANTLR end "entryRuleValue"


    // $ANTLR start "ruleValue"
    // InternalSeML.g:506:1: ruleValue returns [EObject current=null] : ( ( () ( (lv_val_1_0= RULE_FLOAT ) ) ) | ( () ( (lv_val_3_0= RULE_BOOL ) ) ) | ( () ( (lv_val_5_0= RULE_INT ) ) ) | ( () ( (lv_val_7_0= RULE_STRING ) ) ) ) ;
    public final EObject ruleValue() throws RecognitionException {
        EObject current = null;

        Token lv_val_1_0=null;
        Token lv_val_3_0=null;
        Token lv_val_5_0=null;
        Token lv_val_7_0=null;


        	enterRule();

        try {
            // InternalSeML.g:512:2: ( ( ( () ( (lv_val_1_0= RULE_FLOAT ) ) ) | ( () ( (lv_val_3_0= RULE_BOOL ) ) ) | ( () ( (lv_val_5_0= RULE_INT ) ) ) | ( () ( (lv_val_7_0= RULE_STRING ) ) ) ) )
            // InternalSeML.g:513:2: ( ( () ( (lv_val_1_0= RULE_FLOAT ) ) ) | ( () ( (lv_val_3_0= RULE_BOOL ) ) ) | ( () ( (lv_val_5_0= RULE_INT ) ) ) | ( () ( (lv_val_7_0= RULE_STRING ) ) ) )
            {
            // InternalSeML.g:513:2: ( ( () ( (lv_val_1_0= RULE_FLOAT ) ) ) | ( () ( (lv_val_3_0= RULE_BOOL ) ) ) | ( () ( (lv_val_5_0= RULE_INT ) ) ) | ( () ( (lv_val_7_0= RULE_STRING ) ) ) )
            int alt12=4;
            switch ( input.LA(1) ) {
            case RULE_FLOAT:
                {
                alt12=1;
                }
                break;
            case RULE_BOOL:
                {
                alt12=2;
                }
                break;
            case RULE_INT:
                {
                alt12=3;
                }
                break;
            case RULE_STRING:
                {
                alt12=4;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 12, 0, input);

                throw nvae;
            }

            switch (alt12) {
                case 1 :
                    // InternalSeML.g:514:3: ( () ( (lv_val_1_0= RULE_FLOAT ) ) )
                    {
                    // InternalSeML.g:514:3: ( () ( (lv_val_1_0= RULE_FLOAT ) ) )
                    // InternalSeML.g:515:4: () ( (lv_val_1_0= RULE_FLOAT ) )
                    {
                    // InternalSeML.g:515:4: ()
                    // InternalSeML.g:516:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getValueAccess().getFloatValAction_0_0(),
                    						current);
                    				

                    }

                    // InternalSeML.g:522:4: ( (lv_val_1_0= RULE_FLOAT ) )
                    // InternalSeML.g:523:5: (lv_val_1_0= RULE_FLOAT )
                    {
                    // InternalSeML.g:523:5: (lv_val_1_0= RULE_FLOAT )
                    // InternalSeML.g:524:6: lv_val_1_0= RULE_FLOAT
                    {
                    lv_val_1_0=(Token)match(input,RULE_FLOAT,FOLLOW_2); 

                    						newLeafNode(lv_val_1_0, grammarAccess.getValueAccess().getValFLOATTerminalRuleCall_0_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getValueRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"val",
                    							lv_val_1_0,
                    							"org.xtext.seml.SeML.FLOAT");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 2 :
                    // InternalSeML.g:542:3: ( () ( (lv_val_3_0= RULE_BOOL ) ) )
                    {
                    // InternalSeML.g:542:3: ( () ( (lv_val_3_0= RULE_BOOL ) ) )
                    // InternalSeML.g:543:4: () ( (lv_val_3_0= RULE_BOOL ) )
                    {
                    // InternalSeML.g:543:4: ()
                    // InternalSeML.g:544:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getValueAccess().getBoolValAction_1_0(),
                    						current);
                    				

                    }

                    // InternalSeML.g:550:4: ( (lv_val_3_0= RULE_BOOL ) )
                    // InternalSeML.g:551:5: (lv_val_3_0= RULE_BOOL )
                    {
                    // InternalSeML.g:551:5: (lv_val_3_0= RULE_BOOL )
                    // InternalSeML.g:552:6: lv_val_3_0= RULE_BOOL
                    {
                    lv_val_3_0=(Token)match(input,RULE_BOOL,FOLLOW_2); 

                    						newLeafNode(lv_val_3_0, grammarAccess.getValueAccess().getValBOOLTerminalRuleCall_1_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getValueRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"val",
                    							lv_val_3_0,
                    							"org.xtext.seml.SeML.BOOL");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 3 :
                    // InternalSeML.g:570:3: ( () ( (lv_val_5_0= RULE_INT ) ) )
                    {
                    // InternalSeML.g:570:3: ( () ( (lv_val_5_0= RULE_INT ) ) )
                    // InternalSeML.g:571:4: () ( (lv_val_5_0= RULE_INT ) )
                    {
                    // InternalSeML.g:571:4: ()
                    // InternalSeML.g:572:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getValueAccess().getIntValAction_2_0(),
                    						current);
                    				

                    }

                    // InternalSeML.g:578:4: ( (lv_val_5_0= RULE_INT ) )
                    // InternalSeML.g:579:5: (lv_val_5_0= RULE_INT )
                    {
                    // InternalSeML.g:579:5: (lv_val_5_0= RULE_INT )
                    // InternalSeML.g:580:6: lv_val_5_0= RULE_INT
                    {
                    lv_val_5_0=(Token)match(input,RULE_INT,FOLLOW_2); 

                    						newLeafNode(lv_val_5_0, grammarAccess.getValueAccess().getValINTTerminalRuleCall_2_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getValueRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"val",
                    							lv_val_5_0,
                    							"org.xtext.seml.SeML.INT");
                    					

                    }


                    }


                    }


                    }
                    break;
                case 4 :
                    // InternalSeML.g:598:3: ( () ( (lv_val_7_0= RULE_STRING ) ) )
                    {
                    // InternalSeML.g:598:3: ( () ( (lv_val_7_0= RULE_STRING ) ) )
                    // InternalSeML.g:599:4: () ( (lv_val_7_0= RULE_STRING ) )
                    {
                    // InternalSeML.g:599:4: ()
                    // InternalSeML.g:600:5: 
                    {

                    					current = forceCreateModelElement(
                    						grammarAccess.getValueAccess().getStringValAction_3_0(),
                    						current);
                    				

                    }

                    // InternalSeML.g:606:4: ( (lv_val_7_0= RULE_STRING ) )
                    // InternalSeML.g:607:5: (lv_val_7_0= RULE_STRING )
                    {
                    // InternalSeML.g:607:5: (lv_val_7_0= RULE_STRING )
                    // InternalSeML.g:608:6: lv_val_7_0= RULE_STRING
                    {
                    lv_val_7_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

                    						newLeafNode(lv_val_7_0, grammarAccess.getValueAccess().getValSTRINGTerminalRuleCall_3_1_0());
                    					

                    						if (current==null) {
                    							current = createModelElement(grammarAccess.getValueRule());
                    						}
                    						setWithLastConsumed(
                    							current,
                    							"val",
                    							lv_val_7_0,
                    							"org.xtext.seml.SeML.STRING");
                    					

                    }


                    }


                    }


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
    // $ANTLR end "ruleValue"


    // $ANTLR start "entryRuleFreeIndividual"
    // InternalSeML.g:629:1: entryRuleFreeIndividual returns [EObject current=null] : iv_ruleFreeIndividual= ruleFreeIndividual EOF ;
    public final EObject entryRuleFreeIndividual() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFreeIndividual = null;


        try {
            // InternalSeML.g:629:55: (iv_ruleFreeIndividual= ruleFreeIndividual EOF )
            // InternalSeML.g:630:2: iv_ruleFreeIndividual= ruleFreeIndividual EOF
            {
             newCompositeNode(grammarAccess.getFreeIndividualRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleFreeIndividual=ruleFreeIndividual();

            state._fsp--;

             current =iv_ruleFreeIndividual; 
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
    // $ANTLR end "entryRuleFreeIndividual"


    // $ANTLR start "ruleFreeIndividual"
    // InternalSeML.g:636:1: ruleFreeIndividual returns [EObject current=null] : (otherlv_0= 'FreeIndividual' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleFreeIndividual() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_iri_2_0=null;


        	enterRule();

        try {
            // InternalSeML.g:642:2: ( (otherlv_0= 'FreeIndividual' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) )
            // InternalSeML.g:643:2: (otherlv_0= 'FreeIndividual' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            {
            // InternalSeML.g:643:2: (otherlv_0= 'FreeIndividual' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            // InternalSeML.g:644:3: otherlv_0= 'FreeIndividual' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,17,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getFreeIndividualAccess().getFreeIndividualKeyword_0());
            		
            // InternalSeML.g:648:3: ( (lv_name_1_0= RULE_ALIAS ) )
            // InternalSeML.g:649:4: (lv_name_1_0= RULE_ALIAS )
            {
            // InternalSeML.g:649:4: (lv_name_1_0= RULE_ALIAS )
            // InternalSeML.g:650:5: lv_name_1_0= RULE_ALIAS
            {
            lv_name_1_0=(Token)match(input,RULE_ALIAS,FOLLOW_11); 

            					newLeafNode(lv_name_1_0, grammarAccess.getFreeIndividualAccess().getNameALIASTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFreeIndividualRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.xtext.seml.SeML.ALIAS");
            				

            }


            }

            // InternalSeML.g:666:3: ( (lv_iri_2_0= RULE_STRING ) )
            // InternalSeML.g:667:4: (lv_iri_2_0= RULE_STRING )
            {
            // InternalSeML.g:667:4: (lv_iri_2_0= RULE_STRING )
            // InternalSeML.g:668:5: lv_iri_2_0= RULE_STRING
            {
            lv_iri_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_iri_2_0, grammarAccess.getFreeIndividualAccess().getIriSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getFreeIndividualRule());
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
    // $ANTLR end "ruleFreeIndividual"


    // $ANTLR start "entryRuleObjectProperty"
    // InternalSeML.g:688:1: entryRuleObjectProperty returns [EObject current=null] : iv_ruleObjectProperty= ruleObjectProperty EOF ;
    public final EObject entryRuleObjectProperty() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleObjectProperty = null;


        try {
            // InternalSeML.g:688:55: (iv_ruleObjectProperty= ruleObjectProperty EOF )
            // InternalSeML.g:689:2: iv_ruleObjectProperty= ruleObjectProperty EOF
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
    // InternalSeML.g:695:1: ruleObjectProperty returns [EObject current=null] : (otherlv_0= 'ObjectProperty' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleObjectProperty() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_iri_2_0=null;


        	enterRule();

        try {
            // InternalSeML.g:701:2: ( (otherlv_0= 'ObjectProperty' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) )
            // InternalSeML.g:702:2: (otherlv_0= 'ObjectProperty' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            {
            // InternalSeML.g:702:2: (otherlv_0= 'ObjectProperty' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            // InternalSeML.g:703:3: otherlv_0= 'ObjectProperty' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,18,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getObjectPropertyAccess().getObjectPropertyKeyword_0());
            		
            // InternalSeML.g:707:3: ( (lv_name_1_0= RULE_ALIAS ) )
            // InternalSeML.g:708:4: (lv_name_1_0= RULE_ALIAS )
            {
            // InternalSeML.g:708:4: (lv_name_1_0= RULE_ALIAS )
            // InternalSeML.g:709:5: lv_name_1_0= RULE_ALIAS
            {
            lv_name_1_0=(Token)match(input,RULE_ALIAS,FOLLOW_11); 

            					newLeafNode(lv_name_1_0, grammarAccess.getObjectPropertyAccess().getNameALIASTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getObjectPropertyRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.xtext.seml.SeML.ALIAS");
            				

            }


            }

            // InternalSeML.g:725:3: ( (lv_iri_2_0= RULE_STRING ) )
            // InternalSeML.g:726:4: (lv_iri_2_0= RULE_STRING )
            {
            // InternalSeML.g:726:4: (lv_iri_2_0= RULE_STRING )
            // InternalSeML.g:727:5: lv_iri_2_0= RULE_STRING
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
    // InternalSeML.g:747:1: entryRuleCharacteristic returns [EObject current=null] : iv_ruleCharacteristic= ruleCharacteristic EOF ;
    public final EObject entryRuleCharacteristic() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleCharacteristic = null;


        try {
            // InternalSeML.g:747:55: (iv_ruleCharacteristic= ruleCharacteristic EOF )
            // InternalSeML.g:748:2: iv_ruleCharacteristic= ruleCharacteristic EOF
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
    // InternalSeML.g:754:1: ruleCharacteristic returns [EObject current=null] : (otherlv_0= 'Characteristic' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleCharacteristic() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_iri_2_0=null;


        	enterRule();

        try {
            // InternalSeML.g:760:2: ( (otherlv_0= 'Characteristic' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) )
            // InternalSeML.g:761:2: (otherlv_0= 'Characteristic' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            {
            // InternalSeML.g:761:2: (otherlv_0= 'Characteristic' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            // InternalSeML.g:762:3: otherlv_0= 'Characteristic' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,19,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getCharacteristicAccess().getCharacteristicKeyword_0());
            		
            // InternalSeML.g:766:3: ( (lv_name_1_0= RULE_ALIAS ) )
            // InternalSeML.g:767:4: (lv_name_1_0= RULE_ALIAS )
            {
            // InternalSeML.g:767:4: (lv_name_1_0= RULE_ALIAS )
            // InternalSeML.g:768:5: lv_name_1_0= RULE_ALIAS
            {
            lv_name_1_0=(Token)match(input,RULE_ALIAS,FOLLOW_11); 

            					newLeafNode(lv_name_1_0, grammarAccess.getCharacteristicAccess().getNameALIASTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getCharacteristicRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.xtext.seml.SeML.ALIAS");
            				

            }


            }

            // InternalSeML.g:784:3: ( (lv_iri_2_0= RULE_STRING ) )
            // InternalSeML.g:785:4: (lv_iri_2_0= RULE_STRING )
            {
            // InternalSeML.g:785:4: (lv_iri_2_0= RULE_STRING )
            // InternalSeML.g:786:5: lv_iri_2_0= RULE_STRING
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


    // $ANTLR start "entryRuleStaticIndividual"
    // InternalSeML.g:806:1: entryRuleStaticIndividual returns [EObject current=null] : iv_ruleStaticIndividual= ruleStaticIndividual EOF ;
    public final EObject entryRuleStaticIndividual() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleStaticIndividual = null;


        try {
            // InternalSeML.g:806:57: (iv_ruleStaticIndividual= ruleStaticIndividual EOF )
            // InternalSeML.g:807:2: iv_ruleStaticIndividual= ruleStaticIndividual EOF
            {
             newCompositeNode(grammarAccess.getStaticIndividualRule()); 
            pushFollow(FOLLOW_1);
            iv_ruleStaticIndividual=ruleStaticIndividual();

            state._fsp--;

             current =iv_ruleStaticIndividual; 
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
    // $ANTLR end "entryRuleStaticIndividual"


    // $ANTLR start "ruleStaticIndividual"
    // InternalSeML.g:813:1: ruleStaticIndividual returns [EObject current=null] : (otherlv_0= 'StaticIndividual' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) ;
    public final EObject ruleStaticIndividual() throws RecognitionException {
        EObject current = null;

        Token otherlv_0=null;
        Token lv_name_1_0=null;
        Token lv_iri_2_0=null;


        	enterRule();

        try {
            // InternalSeML.g:819:2: ( (otherlv_0= 'StaticIndividual' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) ) )
            // InternalSeML.g:820:2: (otherlv_0= 'StaticIndividual' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            {
            // InternalSeML.g:820:2: (otherlv_0= 'StaticIndividual' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) ) )
            // InternalSeML.g:821:3: otherlv_0= 'StaticIndividual' ( (lv_name_1_0= RULE_ALIAS ) ) ( (lv_iri_2_0= RULE_STRING ) )
            {
            otherlv_0=(Token)match(input,20,FOLLOW_4); 

            			newLeafNode(otherlv_0, grammarAccess.getStaticIndividualAccess().getStaticIndividualKeyword_0());
            		
            // InternalSeML.g:825:3: ( (lv_name_1_0= RULE_ALIAS ) )
            // InternalSeML.g:826:4: (lv_name_1_0= RULE_ALIAS )
            {
            // InternalSeML.g:826:4: (lv_name_1_0= RULE_ALIAS )
            // InternalSeML.g:827:5: lv_name_1_0= RULE_ALIAS
            {
            lv_name_1_0=(Token)match(input,RULE_ALIAS,FOLLOW_11); 

            					newLeafNode(lv_name_1_0, grammarAccess.getStaticIndividualAccess().getNameALIASTerminalRuleCall_1_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getStaticIndividualRule());
            					}
            					setWithLastConsumed(
            						current,
            						"name",
            						lv_name_1_0,
            						"org.xtext.seml.SeML.ALIAS");
            				

            }


            }

            // InternalSeML.g:843:3: ( (lv_iri_2_0= RULE_STRING ) )
            // InternalSeML.g:844:4: (lv_iri_2_0= RULE_STRING )
            {
            // InternalSeML.g:844:4: (lv_iri_2_0= RULE_STRING )
            // InternalSeML.g:845:5: lv_iri_2_0= RULE_STRING
            {
            lv_iri_2_0=(Token)match(input,RULE_STRING,FOLLOW_2); 

            					newLeafNode(lv_iri_2_0, grammarAccess.getStaticIndividualAccess().getIriSTRINGTerminalRuleCall_2_0());
            				

            					if (current==null) {
            						current = createModelElement(grammarAccess.getStaticIndividualRule());
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
    // $ANTLR end "ruleStaticIndividual"

    // Delegated rules


 

    public static final BitSet FOLLOW_1 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_2 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_3 = new BitSet(new long[]{0x000000000000A012L});
    public static final BitSet FOLLOW_4 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_5 = new BitSet(new long[]{0x0000000000004012L});
    public static final BitSet FOLLOW_6 = new BitSet(new long[]{0x0000000000000012L});
    public static final BitSet FOLLOW_7 = new BitSet(new long[]{0x00000000001E0002L});
    public static final BitSet FOLLOW_8 = new BitSet(new long[]{0x00000000000E0002L});
    public static final BitSet FOLLOW_9 = new BitSet(new long[]{0x00000000000C0002L});
    public static final BitSet FOLLOW_10 = new BitSet(new long[]{0x0000000000040002L});
    public static final BitSet FOLLOW_11 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_12 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_13 = new BitSet(new long[]{0x0000000000010000L});
    public static final BitSet FOLLOW_14 = new BitSet(new long[]{0x00000000000001E0L});

}