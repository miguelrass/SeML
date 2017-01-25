package org.xtext.seml.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSeMLLexer extends Lexer {
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

    public InternalSeMLLexer() {;} 
    public InternalSeMLLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalSeMLLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "InternalSeML.g"; }

    // $ANTLR start "T__13"
    public final void mT__13() throws RecognitionException {
        try {
            int _type = T__13;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:11:7: ( 'import' )
            // InternalSeML.g:11:9: 'import'
            {
            match("import"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__13"

    // $ANTLR start "T__14"
    public final void mT__14() throws RecognitionException {
        try {
            int _type = T__14;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:12:7: ( 'new' )
            // InternalSeML.g:12:9: 'new'
            {
            match("new"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__14"

    // $ANTLR start "T__15"
    public final void mT__15() throws RecognitionException {
        try {
            int _type = T__15;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:13:7: ( '\\n' )
            // InternalSeML.g:13:9: '\\n'
            {
            match('\n'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__15"

    // $ANTLR start "T__16"
    public final void mT__16() throws RecognitionException {
        try {
            int _type = T__16;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:14:7: ( 'use' )
            // InternalSeML.g:14:9: 'use'
            {
            match("use"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__16"

    // $ANTLR start "T__17"
    public final void mT__17() throws RecognitionException {
        try {
            int _type = T__17;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:15:7: ( 'CompProcess' )
            // InternalSeML.g:15:9: 'CompProcess'
            {
            match("CompProcess"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__17"

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:16:7: ( 'CompEvent' )
            // InternalSeML.g:16:9: 'CompEvent'
            {
            match("CompEvent"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:17:7: ( 'CompProperty' )
            // InternalSeML.g:17:9: 'CompProperty'
            {
            match("CompProperty"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:18:7: ( 'CompEntity' )
            // InternalSeML.g:18:9: 'CompEntity'
            {
            match("CompEntity"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:19:7: ( 'ObjectProperty' )
            // InternalSeML.g:19:9: 'ObjectProperty'
            {
            match("ObjectProperty"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:20:7: ( 'Characteristic' )
            // InternalSeML.g:20:9: 'Characteristic'
            {
            match("Characteristic"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:21:7: ( 'MetaIndividual' )
            // InternalSeML.g:21:9: 'MetaIndividual'
            {
            match("MetaIndividual"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "RULE_FLOAT"
    public final void mRULE_FLOAT() throws RecognitionException {
        try {
            int _type = RULE_FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:982:12: ( RULE_INT '.' RULE_INT ( ( 'e' | 'E' ) RULE_INT )? )
            // InternalSeML.g:982:14: RULE_INT '.' RULE_INT ( ( 'e' | 'E' ) RULE_INT )?
            {
            mRULE_INT(); 
            match('.'); 
            mRULE_INT(); 
            // InternalSeML.g:982:36: ( ( 'e' | 'E' ) RULE_INT )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='E'||LA1_0=='e') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // InternalSeML.g:982:37: ( 'e' | 'E' ) RULE_INT
                    {
                    if ( input.LA(1)=='E'||input.LA(1)=='e' ) {
                        input.consume();

                    }
                    else {
                        MismatchedSetException mse = new MismatchedSetException(null,input);
                        recover(mse);
                        throw mse;}

                    mRULE_INT(); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_FLOAT"

    // $ANTLR start "RULE_BOOL"
    public final void mRULE_BOOL() throws RecognitionException {
        try {
            int _type = RULE_BOOL;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:984:11: ( ( 'true' | 'false' ) )
            // InternalSeML.g:984:13: ( 'true' | 'false' )
            {
            // InternalSeML.g:984:13: ( 'true' | 'false' )
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='t') ) {
                alt2=1;
            }
            else if ( (LA2_0=='f') ) {
                alt2=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }
            switch (alt2) {
                case 1 :
                    // InternalSeML.g:984:14: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // InternalSeML.g:984:21: 'false'
                    {
                    match("false"); 


                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_BOOL"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            // InternalSeML.g:986:19: ( ( '0' .. '9' )+ )
            // InternalSeML.g:986:21: ( '0' .. '9' )+
            {
            // InternalSeML.g:986:21: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // InternalSeML.g:986:22: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:988:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // InternalSeML.g:988:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // InternalSeML.g:988:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\"') ) {
                alt6=1;
            }
            else if ( (LA6_0=='\'') ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // InternalSeML.g:988:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalSeML.g:988:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop4:
                    do {
                        int alt4=3;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0=='\\') ) {
                            alt4=1;
                        }
                        else if ( ((LA4_0>='\u0000' && LA4_0<='!')||(LA4_0>='#' && LA4_0<='[')||(LA4_0>=']' && LA4_0<='\uFFFF')) ) {
                            alt4=2;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // InternalSeML.g:988:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalSeML.g:988:28: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalSeML.g:988:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // InternalSeML.g:988:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop5:
                    do {
                        int alt5=3;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0=='\\') ) {
                            alt5=1;
                        }
                        else if ( ((LA5_0>='\u0000' && LA5_0<='&')||(LA5_0>='(' && LA5_0<='[')||(LA5_0>=']' && LA5_0<='\uFFFF')) ) {
                            alt5=2;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // InternalSeML.g:988:54: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalSeML.g:988:61: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:990:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalSeML.g:990:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalSeML.g:990:24: ( options {greedy=false; } : . )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='*') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='/') ) {
                        alt7=2;
                    }
                    else if ( ((LA7_1>='\u0000' && LA7_1<='.')||(LA7_1>='0' && LA7_1<='\uFFFF')) ) {
                        alt7=1;
                    }


                }
                else if ( ((LA7_0>='\u0000' && LA7_0<=')')||(LA7_0>='+' && LA7_0<='\uFFFF')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // InternalSeML.g:990:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:992:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalSeML.g:992:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalSeML.g:992:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // InternalSeML.g:992:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // InternalSeML.g:992:40: ( ( '\\r' )? '\\n' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\n'||LA10_0=='\r') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // InternalSeML.g:992:41: ( '\\r' )? '\\n'
                    {
                    // InternalSeML.g:992:41: ( '\\r' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='\r') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // InternalSeML.g:992:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_IRI"
    public final void mRULE_IRI() throws RecognitionException {
        try {
            int _type = RULE_IRI;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:994:10: ( ( '#' | '&' .. ';' | '?' .. 'Z' | 'a' .. 'z' | '_' )+ )
            // InternalSeML.g:994:12: ( '#' | '&' .. ';' | '?' .. 'Z' | 'a' .. 'z' | '_' )+
            {
            // InternalSeML.g:994:12: ( '#' | '&' .. ';' | '?' .. 'Z' | 'a' .. 'z' | '_' )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0=='#'||(LA11_0>='&' && LA11_0<=';')||(LA11_0>='?' && LA11_0<='Z')||LA11_0=='_'||(LA11_0>='a' && LA11_0<='z')) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // InternalSeML.g:
            	    {
            	    if ( input.LA(1)=='#'||(input.LA(1)>='&' && input.LA(1)<=';')||(input.LA(1)>='?' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


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


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_IRI"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:996:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalSeML.g:996:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalSeML.g:996:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // InternalSeML.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:998:16: ( . )
            // InternalSeML.g:998:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // InternalSeML.g:1:8: ( T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | RULE_FLOAT | RULE_BOOL | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_IRI | RULE_WS | RULE_ANY_OTHER )
        int alt13=19;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1 :
                // InternalSeML.g:1:10: T__13
                {
                mT__13(); 

                }
                break;
            case 2 :
                // InternalSeML.g:1:16: T__14
                {
                mT__14(); 

                }
                break;
            case 3 :
                // InternalSeML.g:1:22: T__15
                {
                mT__15(); 

                }
                break;
            case 4 :
                // InternalSeML.g:1:28: T__16
                {
                mT__16(); 

                }
                break;
            case 5 :
                // InternalSeML.g:1:34: T__17
                {
                mT__17(); 

                }
                break;
            case 6 :
                // InternalSeML.g:1:40: T__18
                {
                mT__18(); 

                }
                break;
            case 7 :
                // InternalSeML.g:1:46: T__19
                {
                mT__19(); 

                }
                break;
            case 8 :
                // InternalSeML.g:1:52: T__20
                {
                mT__20(); 

                }
                break;
            case 9 :
                // InternalSeML.g:1:58: T__21
                {
                mT__21(); 

                }
                break;
            case 10 :
                // InternalSeML.g:1:64: T__22
                {
                mT__22(); 

                }
                break;
            case 11 :
                // InternalSeML.g:1:70: T__23
                {
                mT__23(); 

                }
                break;
            case 12 :
                // InternalSeML.g:1:76: RULE_FLOAT
                {
                mRULE_FLOAT(); 

                }
                break;
            case 13 :
                // InternalSeML.g:1:87: RULE_BOOL
                {
                mRULE_BOOL(); 

                }
                break;
            case 14 :
                // InternalSeML.g:1:97: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 15 :
                // InternalSeML.g:1:109: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 16 :
                // InternalSeML.g:1:125: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 17 :
                // InternalSeML.g:1:141: RULE_IRI
                {
                mRULE_IRI(); 

                }
                break;
            case 18 :
                // InternalSeML.g:1:150: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 19 :
                // InternalSeML.g:1:158: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA13_eotS =
        "\1\uffff\2\22\1\24\7\22\1\20\2\22\3\uffff\1\22\1\uffff\1\22\2\uffff\11\22\1\uffff\1\22\1\37\1\22\1\62\1\22\1\64\1\65\4\22\1\72\4\22\1\uffff\1\62\1\uffff\1\22\2\uffff\4\22\1\uffff\1\22\1\106\1\22\1\60\6\22\1\72\1\uffff\1\106\1\117\6\22\1\uffff\17\22\1\146\6\22\1\uffff\1\155\3\22\1\161\1\22\1\uffff\3\22\1\uffff\1\166\3\22\1\uffff\3\22\1\175\1\176\1\177\3\uffff";
    static final String DFA13_eofS =
        "\u0080\uffff";
    static final String DFA13_minS =
        "\1\0\1\155\1\145\1\11\1\163\1\150\1\142\1\145\1\56\1\162\1\141\2\0\1\52\3\uffff\1\160\1\uffff\1\167\2\uffff\1\145\1\155\1\141\1\152\1\164\1\60\1\56\1\165\1\154\1\uffff\1\0\1\43\1\0\1\43\1\157\2\43\1\160\1\162\1\145\1\141\1\43\1\145\1\163\2\0\1\uffff\1\43\1\uffff\1\162\2\uffff\1\105\1\141\1\143\1\111\1\uffff\1\60\1\43\1\145\1\43\1\164\1\162\1\156\1\143\1\164\1\156\1\43\1\uffff\2\43\1\157\1\145\2\164\1\120\1\144\1\uffff\1\143\1\156\1\151\1\145\1\162\1\151\2\145\2\164\1\162\1\157\1\166\1\163\1\162\1\43\1\171\1\151\1\160\1\151\1\163\1\164\1\uffff\1\43\1\163\1\145\1\144\1\43\1\171\1\uffff\1\164\1\162\1\165\1\uffff\1\43\1\151\1\164\1\141\1\uffff\1\143\1\171\1\154\3\43\3\uffff";
    static final String DFA13_maxS =
        "\1\uffff\1\155\1\145\1\40\1\163\1\157\1\142\1\145\1\71\1\162\1\141\2\uffff\1\57\3\uffff\1\160\1\uffff\1\167\2\uffff\1\145\1\155\1\141\1\152\1\164\2\71\1\165\1\154\1\uffff\1\uffff\1\172\1\uffff\1\172\1\157\2\172\1\160\1\162\1\145\1\141\1\172\1\145\1\163\2\uffff\1\uffff\1\172\1\uffff\1\162\2\uffff\1\120\1\141\1\143\1\111\1\uffff\1\71\1\172\1\145\1\172\1\164\1\162\1\166\1\143\1\164\1\156\1\172\1\uffff\2\172\1\157\1\145\2\164\1\120\1\144\1\uffff\1\160\1\156\1\151\1\145\1\162\1\151\2\145\2\164\1\162\1\157\1\166\1\163\1\162\1\172\1\171\1\151\1\160\1\151\1\163\1\164\1\uffff\1\172\1\163\1\145\1\144\1\172\1\171\1\uffff\1\164\1\162\1\165\1\uffff\1\172\1\151\1\164\1\141\1\uffff\1\143\1\171\1\154\3\172\3\uffff";
    static final String DFA13_acceptS =
        "\16\uffff\1\21\1\22\1\23\1\uffff\1\21\1\uffff\1\3\1\22\11\uffff\1\16\20\uffff\1\17\1\uffff\1\20\1\uffff\1\2\1\4\4\uffff\1\14\13\uffff\1\15\10\uffff\1\1\26\uffff\1\6\6\uffff\1\10\3\uffff\1\5\4\uffff\1\7\6\uffff\1\12\1\11\1\13";
    static final String DFA13_specialS =
        "\1\0\12\uffff\1\1\1\3\23\uffff\1\2\1\uffff\1\5\13\uffff\1\6\1\4\120\uffff}>";
    static final String[] DFA13_transitionS = {
            "\11\20\1\17\1\3\2\20\1\17\22\20\1\17\1\20\1\13\1\16\2\20\1\16\1\14\7\16\1\15\12\10\2\16\3\20\4\16\1\5\11\16\1\7\1\16\1\6\13\16\4\20\1\16\1\20\5\16\1\12\2\16\1\1\4\16\1\2\5\16\1\11\1\4\5\16\uff85\20",
            "\1\21",
            "\1\23",
            "\2\25\2\uffff\1\25\22\uffff\1\25",
            "\1\26",
            "\1\30\6\uffff\1\27",
            "\1\31",
            "\1\32",
            "\1\33\1\uffff\12\34",
            "\1\35",
            "\1\36",
            "\0\37",
            "\43\37\1\40\2\37\1\40\1\41\24\40\3\37\34\40\4\37\1\40\1\37\32\40\uff85\37",
            "\1\42\4\uffff\1\43",
            "",
            "",
            "",
            "\1\44",
            "",
            "\1\45",
            "",
            "",
            "\1\46",
            "\1\47",
            "\1\50",
            "\1\51",
            "\1\52",
            "\12\53",
            "\1\33\1\uffff\12\34",
            "\1\54",
            "\1\55",
            "",
            "\43\37\1\40\2\37\1\40\1\41\24\40\3\37\34\40\4\37\1\40\1\37\32\40\uff85\37",
            "\1\22\2\uffff\26\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "\43\60\1\57\2\60\4\57\1\56\21\57\3\60\34\57\4\60\1\57\1\60\32\57\uff85\60",
            "\1\61\2\uffff\26\61\3\uffff\34\61\4\uffff\1\61\1\uffff\32\61",
            "\1\63",
            "\1\22\2\uffff\26\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "\1\22\2\uffff\26\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\22\2\uffff\12\22\12\53\2\22\3\uffff\6\22\1\73\25\22\4\uffff\1\22\1\uffff\4\22\1\73\25\22",
            "\1\74",
            "\1\75",
            "\43\60\1\57\2\60\4\57\1\56\4\57\1\76\14\57\3\60\34\57\4\60\1\57\1\60\32\57\uff85\60",
            "\43\60\1\57\2\60\4\57\1\56\21\57\3\60\34\57\4\60\1\57\1\60\32\57\uff85\60",
            "",
            "\1\61\2\uffff\26\61\3\uffff\34\61\4\uffff\1\61\1\uffff\32\61",
            "",
            "\1\77",
            "",
            "",
            "\1\101\12\uffff\1\100",
            "\1\102",
            "\1\103",
            "\1\104",
            "",
            "\12\105",
            "\1\22\2\uffff\26\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "\1\107",
            "\1\57\2\uffff\4\57\1\56\21\57\3\uffff\34\57\4\uffff\1\57\1\uffff\32\57",
            "\1\110",
            "\1\111",
            "\1\113\7\uffff\1\112",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\22\2\uffff\12\22\12\105\2\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "",
            "\1\22\2\uffff\26\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "\1\22\2\uffff\26\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "",
            "\1\126\14\uffff\1\127",
            "\1\130",
            "\1\131",
            "\1\132",
            "\1\133",
            "\1\134",
            "\1\135",
            "\1\136",
            "\1\137",
            "\1\140",
            "\1\141",
            "\1\142",
            "\1\143",
            "\1\144",
            "\1\145",
            "\1\22\2\uffff\26\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "\1\147",
            "\1\150",
            "\1\151",
            "\1\152",
            "\1\153",
            "\1\154",
            "",
            "\1\22\2\uffff\26\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "\1\156",
            "\1\157",
            "\1\160",
            "\1\22\2\uffff\26\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "\1\162",
            "",
            "\1\163",
            "\1\164",
            "\1\165",
            "",
            "\1\22\2\uffff\26\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "\1\167",
            "\1\170",
            "\1\171",
            "",
            "\1\172",
            "\1\173",
            "\1\174",
            "\1\22\2\uffff\26\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "\1\22\2\uffff\26\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "\1\22\2\uffff\26\22\3\uffff\34\22\4\uffff\1\22\1\uffff\32\22",
            "",
            "",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | RULE_FLOAT | RULE_BOOL | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_IRI | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_0 = input.LA(1);

                        s = -1;
                        if ( (LA13_0=='i') ) {s = 1;}

                        else if ( (LA13_0=='n') ) {s = 2;}

                        else if ( (LA13_0=='\n') ) {s = 3;}

                        else if ( (LA13_0=='u') ) {s = 4;}

                        else if ( (LA13_0=='C') ) {s = 5;}

                        else if ( (LA13_0=='O') ) {s = 6;}

                        else if ( (LA13_0=='M') ) {s = 7;}

                        else if ( ((LA13_0>='0' && LA13_0<='9')) ) {s = 8;}

                        else if ( (LA13_0=='t') ) {s = 9;}

                        else if ( (LA13_0=='f') ) {s = 10;}

                        else if ( (LA13_0=='\"') ) {s = 11;}

                        else if ( (LA13_0=='\'') ) {s = 12;}

                        else if ( (LA13_0=='/') ) {s = 13;}

                        else if ( (LA13_0=='#'||LA13_0=='&'||(LA13_0>='(' && LA13_0<='.')||(LA13_0>=':' && LA13_0<=';')||(LA13_0>='?' && LA13_0<='B')||(LA13_0>='D' && LA13_0<='L')||LA13_0=='N'||(LA13_0>='P' && LA13_0<='Z')||LA13_0=='_'||(LA13_0>='a' && LA13_0<='e')||(LA13_0>='g' && LA13_0<='h')||(LA13_0>='j' && LA13_0<='m')||(LA13_0>='o' && LA13_0<='s')||(LA13_0>='v' && LA13_0<='z')) ) {s = 14;}

                        else if ( (LA13_0=='\t'||LA13_0=='\r'||LA13_0==' ') ) {s = 15;}

                        else if ( ((LA13_0>='\u0000' && LA13_0<='\b')||(LA13_0>='\u000B' && LA13_0<='\f')||(LA13_0>='\u000E' && LA13_0<='\u001F')||LA13_0=='!'||(LA13_0>='$' && LA13_0<='%')||(LA13_0>='<' && LA13_0<='>')||(LA13_0>='[' && LA13_0<='^')||LA13_0=='`'||(LA13_0>='{' && LA13_0<='\uFFFF')) ) {s = 16;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA13_11 = input.LA(1);

                        s = -1;
                        if ( ((LA13_11>='\u0000' && LA13_11<='\uFFFF')) ) {s = 31;}

                        else s = 16;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA13_32 = input.LA(1);

                        s = -1;
                        if ( (LA13_32=='\'') ) {s = 33;}

                        else if ( (LA13_32=='#'||LA13_32=='&'||(LA13_32>='(' && LA13_32<=';')||(LA13_32>='?' && LA13_32<='Z')||LA13_32=='_'||(LA13_32>='a' && LA13_32<='z')) ) {s = 32;}

                        else if ( ((LA13_32>='\u0000' && LA13_32<='\"')||(LA13_32>='$' && LA13_32<='%')||(LA13_32>='<' && LA13_32<='>')||(LA13_32>='[' && LA13_32<='^')||LA13_32=='`'||(LA13_32>='{' && LA13_32<='\uFFFF')) ) {s = 31;}

                        else s = 18;

                        if ( s>=0 ) return s;
                        break;
                    case 3 : 
                        int LA13_12 = input.LA(1);

                        s = -1;
                        if ( ((LA13_12>='\u0000' && LA13_12<='\"')||(LA13_12>='$' && LA13_12<='%')||(LA13_12>='<' && LA13_12<='>')||(LA13_12>='[' && LA13_12<='^')||LA13_12=='`'||(LA13_12>='{' && LA13_12<='\uFFFF')) ) {s = 31;}

                        else if ( (LA13_12=='#'||LA13_12=='&'||(LA13_12>='(' && LA13_12<=';')||(LA13_12>='?' && LA13_12<='Z')||LA13_12=='_'||(LA13_12>='a' && LA13_12<='z')) ) {s = 32;}

                        else if ( (LA13_12=='\'') ) {s = 33;}

                        else s = 18;

                        if ( s>=0 ) return s;
                        break;
                    case 4 : 
                        int LA13_47 = input.LA(1);

                        s = -1;
                        if ( (LA13_47=='*') ) {s = 46;}

                        else if ( (LA13_47=='#'||(LA13_47>='&' && LA13_47<=')')||(LA13_47>='+' && LA13_47<=';')||(LA13_47>='?' && LA13_47<='Z')||LA13_47=='_'||(LA13_47>='a' && LA13_47<='z')) ) {s = 47;}

                        else if ( ((LA13_47>='\u0000' && LA13_47<='\"')||(LA13_47>='$' && LA13_47<='%')||(LA13_47>='<' && LA13_47<='>')||(LA13_47>='[' && LA13_47<='^')||LA13_47=='`'||(LA13_47>='{' && LA13_47<='\uFFFF')) ) {s = 48;}

                        else s = 18;

                        if ( s>=0 ) return s;
                        break;
                    case 5 : 
                        int LA13_34 = input.LA(1);

                        s = -1;
                        if ( (LA13_34=='*') ) {s = 46;}

                        else if ( (LA13_34=='#'||(LA13_34>='&' && LA13_34<=')')||(LA13_34>='+' && LA13_34<=';')||(LA13_34>='?' && LA13_34<='Z')||LA13_34=='_'||(LA13_34>='a' && LA13_34<='z')) ) {s = 47;}

                        else if ( ((LA13_34>='\u0000' && LA13_34<='\"')||(LA13_34>='$' && LA13_34<='%')||(LA13_34>='<' && LA13_34<='>')||(LA13_34>='[' && LA13_34<='^')||LA13_34=='`'||(LA13_34>='{' && LA13_34<='\uFFFF')) ) {s = 48;}

                        else s = 18;

                        if ( s>=0 ) return s;
                        break;
                    case 6 : 
                        int LA13_46 = input.LA(1);

                        s = -1;
                        if ( (LA13_46=='/') ) {s = 62;}

                        else if ( (LA13_46=='*') ) {s = 46;}

                        else if ( (LA13_46=='#'||(LA13_46>='&' && LA13_46<=')')||(LA13_46>='+' && LA13_46<='.')||(LA13_46>='0' && LA13_46<=';')||(LA13_46>='?' && LA13_46<='Z')||LA13_46=='_'||(LA13_46>='a' && LA13_46<='z')) ) {s = 47;}

                        else if ( ((LA13_46>='\u0000' && LA13_46<='\"')||(LA13_46>='$' && LA13_46<='%')||(LA13_46>='<' && LA13_46<='>')||(LA13_46>='[' && LA13_46<='^')||LA13_46=='`'||(LA13_46>='{' && LA13_46<='\uFFFF')) ) {s = 48;}

                        else s = 18;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}