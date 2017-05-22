package org.xtext.seml.ide.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ide.editor.contentassist.antlr.internal.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalSeMLLexer extends Lexer {
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
            // InternalSeML.g:11:7: ( 'use' )
            // InternalSeML.g:11:9: 'use'
            {
            match("use"); 


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
            // InternalSeML.g:12:7: ( ',' )
            // InternalSeML.g:12:9: ','
            {
            match(','); 

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
            // InternalSeML.g:13:7: ( 'import' )
            // InternalSeML.g:13:9: 'import'
            {
            match("import"); 


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
            // InternalSeML.g:14:7: ( '=' )
            // InternalSeML.g:14:9: '='
            {
            match('='); 

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
            // InternalSeML.g:15:7: ( 'FreeIndividual' )
            // InternalSeML.g:15:9: 'FreeIndividual'
            {
            match("FreeIndividual"); 


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
            // InternalSeML.g:16:7: ( 'ObjectProperty' )
            // InternalSeML.g:16:9: 'ObjectProperty'
            {
            match("ObjectProperty"); 


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
            // InternalSeML.g:17:7: ( 'Characteristic' )
            // InternalSeML.g:17:9: 'Characteristic'
            {
            match("Characteristic"); 


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
            // InternalSeML.g:18:7: ( 'StaticIndividual' )
            // InternalSeML.g:18:9: 'StaticIndividual'
            {
            match("StaticIndividual"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "RULE_FLOAT"
    public final void mRULE_FLOAT() throws RecognitionException {
        try {
            int _type = RULE_FLOAT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:2035:12: ( RULE_INT '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) RULE_INT )? )
            // InternalSeML.g:2035:14: RULE_INT '.' ( '0' .. '9' )+ ( ( 'e' | 'E' ) RULE_INT )?
            {
            mRULE_INT(); 
            match('.'); 
            // InternalSeML.g:2035:27: ( '0' .. '9' )+
            int cnt1=0;
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( ((LA1_0>='0' && LA1_0<='9')) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // InternalSeML.g:2035:28: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt1 >= 1 ) break loop1;
                        EarlyExitException eee =
                            new EarlyExitException(1, input);
                        throw eee;
                }
                cnt1++;
            } while (true);

            // InternalSeML.g:2035:39: ( ( 'e' | 'E' ) RULE_INT )?
            int alt2=2;
            int LA2_0 = input.LA(1);

            if ( (LA2_0=='E'||LA2_0=='e') ) {
                alt2=1;
            }
            switch (alt2) {
                case 1 :
                    // InternalSeML.g:2035:40: ( 'e' | 'E' ) RULE_INT
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
            // InternalSeML.g:2037:11: ( ( 'true' | 'false' ) )
            // InternalSeML.g:2037:13: ( 'true' | 'false' )
            {
            // InternalSeML.g:2037:13: ( 'true' | 'false' )
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='t') ) {
                alt3=1;
            }
            else if ( (LA3_0=='f') ) {
                alt3=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 3, 0, input);

                throw nvae;
            }
            switch (alt3) {
                case 1 :
                    // InternalSeML.g:2037:14: 'true'
                    {
                    match("true"); 


                    }
                    break;
                case 2 :
                    // InternalSeML.g:2037:21: 'false'
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
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:2039:10: ( ( '-' )? ( '0' .. '9' )+ )
            // InternalSeML.g:2039:12: ( '-' )? ( '0' .. '9' )+
            {
            // InternalSeML.g:2039:12: ( '-' )?
            int alt4=2;
            int LA4_0 = input.LA(1);

            if ( (LA4_0=='-') ) {
                alt4=1;
            }
            switch (alt4) {
                case 1 :
                    // InternalSeML.g:2039:12: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // InternalSeML.g:2039:17: ( '0' .. '9' )+
            int cnt5=0;
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( ((LA5_0>='0' && LA5_0<='9')) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // InternalSeML.g:2039:18: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt5 >= 1 ) break loop5;
                        EarlyExitException eee =
                            new EarlyExitException(5, input);
                        throw eee;
                }
                cnt5++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
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
            // InternalSeML.g:2041:13: ( ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // InternalSeML.g:2041:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // InternalSeML.g:2041:15: ( '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt8=2;
            int LA8_0 = input.LA(1);

            if ( (LA8_0=='\"') ) {
                alt8=1;
            }
            else if ( (LA8_0=='\'') ) {
                alt8=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 8, 0, input);

                throw nvae;
            }
            switch (alt8) {
                case 1 :
                    // InternalSeML.g:2041:16: '\"' ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // InternalSeML.g:2041:20: ( '\\\\' . | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop6:
                    do {
                        int alt6=3;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0=='\\') ) {
                            alt6=1;
                        }
                        else if ( ((LA6_0>='\u0000' && LA6_0<='!')||(LA6_0>='#' && LA6_0<='[')||(LA6_0>=']' && LA6_0<='\uFFFF')) ) {
                            alt6=2;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // InternalSeML.g:2041:21: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalSeML.g:2041:28: ~ ( ( '\\\\' | '\"' ) )
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
                    	    break loop6;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // InternalSeML.g:2041:48: '\\'' ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // InternalSeML.g:2041:53: ( '\\\\' . | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop7:
                    do {
                        int alt7=3;
                        int LA7_0 = input.LA(1);

                        if ( (LA7_0=='\\') ) {
                            alt7=1;
                        }
                        else if ( ((LA7_0>='\u0000' && LA7_0<='&')||(LA7_0>='(' && LA7_0<='[')||(LA7_0>=']' && LA7_0<='\uFFFF')) ) {
                            alt7=2;
                        }


                        switch (alt7) {
                    	case 1 :
                    	    // InternalSeML.g:2041:54: '\\\\' .
                    	    {
                    	    match('\\'); 
                    	    matchAny(); 

                    	    }
                    	    break;
                    	case 2 :
                    	    // InternalSeML.g:2041:61: ~ ( ( '\\\\' | '\\'' ) )
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
                    	    break loop7;
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
            // InternalSeML.g:2043:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // InternalSeML.g:2043:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // InternalSeML.g:2043:24: ( options {greedy=false; } : . )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0=='*') ) {
                    int LA9_1 = input.LA(2);

                    if ( (LA9_1=='/') ) {
                        alt9=2;
                    }
                    else if ( ((LA9_1>='\u0000' && LA9_1<='.')||(LA9_1>='0' && LA9_1<='\uFFFF')) ) {
                        alt9=1;
                    }


                }
                else if ( ((LA9_0>='\u0000' && LA9_0<=')')||(LA9_0>='+' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // InternalSeML.g:2043:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop9;
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
            // InternalSeML.g:2045:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // InternalSeML.g:2045:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // InternalSeML.g:2045:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( ((LA10_0>='\u0000' && LA10_0<='\t')||(LA10_0>='\u000B' && LA10_0<='\f')||(LA10_0>='\u000E' && LA10_0<='\uFFFF')) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // InternalSeML.g:2045:24: ~ ( ( '\\n' | '\\r' ) )
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
            	    break loop10;
                }
            } while (true);

            // InternalSeML.g:2045:40: ( ( '\\r' )? '\\n' )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0=='\n'||LA12_0=='\r') ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // InternalSeML.g:2045:41: ( '\\r' )? '\\n'
                    {
                    // InternalSeML.g:2045:41: ( '\\r' )?
                    int alt11=2;
                    int LA11_0 = input.LA(1);

                    if ( (LA11_0=='\r') ) {
                        alt11=1;
                    }
                    switch (alt11) {
                        case 1 :
                            // InternalSeML.g:2045:41: '\\r'
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

    // $ANTLR start "RULE_ALIAS"
    public final void mRULE_ALIAS() throws RecognitionException {
        try {
            int _type = RULE_ALIAS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:2047:12: ( ( '-' )? ( 'A' .. 'Z' | 'a' .. 'z' | '*' | '_' | '.' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '-' | '*' | '_' | '.' )* )
            // InternalSeML.g:2047:14: ( '-' )? ( 'A' .. 'Z' | 'a' .. 'z' | '*' | '_' | '.' ) ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '-' | '*' | '_' | '.' )*
            {
            // InternalSeML.g:2047:14: ( '-' )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0=='-') ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // InternalSeML.g:2047:14: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            if ( input.LA(1)=='*'||input.LA(1)=='.'||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // InternalSeML.g:2047:51: ( 'A' .. 'Z' | 'a' .. 'z' | '0' .. '9' | '-' | '*' | '_' | '.' )*
            loop14:
            do {
                int alt14=2;
                int LA14_0 = input.LA(1);

                if ( (LA14_0=='*'||(LA14_0>='-' && LA14_0<='.')||(LA14_0>='0' && LA14_0<='9')||(LA14_0>='A' && LA14_0<='Z')||LA14_0=='_'||(LA14_0>='a' && LA14_0<='z')) ) {
                    alt14=1;
                }


                switch (alt14) {
            	case 1 :
            	    // InternalSeML.g:
            	    {
            	    if ( input.LA(1)=='*'||(input.LA(1)>='-' && input.LA(1)<='.')||(input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop14;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ALIAS"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // InternalSeML.g:2049:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // InternalSeML.g:2049:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // InternalSeML.g:2049:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt15=0;
            loop15:
            do {
                int alt15=2;
                int LA15_0 = input.LA(1);

                if ( ((LA15_0>='\t' && LA15_0<='\n')||LA15_0=='\r'||LA15_0==' ') ) {
                    alt15=1;
                }


                switch (alt15) {
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
            	    if ( cnt15 >= 1 ) break loop15;
                        EarlyExitException eee =
                            new EarlyExitException(15, input);
                        throw eee;
                }
                cnt15++;
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
            // InternalSeML.g:2051:16: ( . )
            // InternalSeML.g:2051:18: .
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
        // InternalSeML.g:1:8: ( T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | RULE_FLOAT | RULE_BOOL | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_ALIAS | RULE_WS | RULE_ANY_OTHER )
        int alt16=17;
        alt16 = dfa16.predict(input);
        switch (alt16) {
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
                // InternalSeML.g:1:58: RULE_FLOAT
                {
                mRULE_FLOAT(); 

                }
                break;
            case 10 :
                // InternalSeML.g:1:69: RULE_BOOL
                {
                mRULE_BOOL(); 

                }
                break;
            case 11 :
                // InternalSeML.g:1:79: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 12 :
                // InternalSeML.g:1:88: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 13 :
                // InternalSeML.g:1:100: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 14 :
                // InternalSeML.g:1:116: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 15 :
                // InternalSeML.g:1:132: RULE_ALIAS
                {
                mRULE_ALIAS(); 

                }
                break;
            case 16 :
                // InternalSeML.g:1:143: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 17 :
                // InternalSeML.g:1:151: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA16 dfa16 = new DFA16(this);
    static final String DFA16_eotS =
        "\1\uffff\1\24\1\uffff\1\24\1\uffff\4\24\1\22\1\35\2\24\3\22\3\uffff\1\24\2\uffff\1\24\1\uffff\4\24\1\35\2\uffff\2\24\4\uffff\1\55\7\24\1\uffff\5\24\1\72\6\24\1\uffff\1\72\1\101\4\24\1\uffff\34\24\1\142\1\143\1\144\1\24\3\uffff\1\24\1\147\1\uffff";
    static final String DFA16_eofS =
        "\150\uffff";
    static final String DFA16_minS =
        "\1\0\1\163\1\uffff\1\155\1\uffff\1\162\1\142\1\150\1\164\1\52\1\56\1\162\1\141\2\0\1\52\3\uffff\1\145\2\uffff\1\160\1\uffff\1\145\1\152\2\141\1\56\2\uffff\1\165\1\154\4\uffff\1\52\1\157\2\145\1\162\1\164\1\145\1\163\1\uffff\1\162\1\111\1\143\1\141\1\151\1\52\1\145\1\164\1\156\1\164\2\143\1\uffff\2\52\1\144\1\120\1\164\1\111\1\uffff\1\151\1\162\1\145\1\156\1\166\1\157\1\162\1\144\1\151\1\160\2\151\1\144\1\145\1\163\1\166\1\165\1\162\1\164\1\151\1\141\1\164\1\151\1\144\1\154\1\171\1\143\1\165\3\52\1\141\3\uffff\1\154\1\52\1\uffff";
    static final String DFA16_maxS =
        "\1\uffff\1\163\1\uffff\1\155\1\uffff\1\162\1\142\1\150\1\164\1\172\1\71\1\162\1\141\2\uffff\1\57\3\uffff\1\145\2\uffff\1\160\1\uffff\1\145\1\152\2\141\1\71\2\uffff\1\165\1\154\4\uffff\1\172\1\157\2\145\1\162\1\164\1\145\1\163\1\uffff\1\162\1\111\1\143\1\141\1\151\1\172\1\145\1\164\1\156\1\164\2\143\1\uffff\2\172\1\144\1\120\1\164\1\111\1\uffff\1\151\1\162\1\145\1\156\1\166\1\157\1\162\1\144\1\151\1\160\2\151\1\144\1\145\1\163\1\166\1\165\1\162\1\164\1\151\1\141\1\164\1\151\1\144\1\154\1\171\1\143\1\165\3\172\1\141\3\uffff\1\154\1\172\1\uffff";
    static final String DFA16_acceptS =
        "\2\uffff\1\2\1\uffff\1\4\13\uffff\1\17\1\20\1\21\1\uffff\1\17\1\2\1\uffff\1\4\5\uffff\1\13\1\11\2\uffff\1\14\1\15\1\16\1\20\10\uffff\1\1\14\uffff\1\12\6\uffff\1\3\40\uffff\1\5\1\6\1\7\2\uffff\1\10";
    static final String DFA16_specialS =
        "\1\0\14\uffff\1\2\1\1\131\uffff}>";
    static final String[] DFA16_transitionS = {
            "\11\22\2\21\2\22\1\21\22\22\1\21\1\22\1\15\4\22\1\16\2\22\1\20\1\22\1\2\1\11\1\20\1\17\12\12\3\22\1\4\3\22\2\20\1\7\2\20\1\5\10\20\1\6\3\20\1\10\7\20\4\22\1\20\1\22\5\20\1\14\2\20\1\3\12\20\1\13\1\1\5\20\uff85\22",
            "\1\23",
            "",
            "\1\26",
            "",
            "\1\30",
            "\1\31",
            "\1\32",
            "\1\33",
            "\1\24\3\uffff\1\24\1\uffff\12\34\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\36\1\uffff\12\34",
            "\1\37",
            "\1\40",
            "\0\41",
            "\0\41",
            "\1\42\4\uffff\1\43",
            "",
            "",
            "",
            "\1\45",
            "",
            "",
            "\1\46",
            "",
            "\1\47",
            "\1\50",
            "\1\51",
            "\1\52",
            "\1\36\1\uffff\12\34",
            "",
            "",
            "\1\53",
            "\1\54",
            "",
            "",
            "",
            "",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\56",
            "\1\57",
            "\1\60",
            "\1\61",
            "\1\62",
            "\1\63",
            "\1\64",
            "",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\73",
            "\1\74",
            "\1\75",
            "\1\76",
            "\1\77",
            "\1\100",
            "",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "",
            "\1\106",
            "\1\107",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\1\114",
            "\1\115",
            "\1\116",
            "\1\117",
            "\1\120",
            "\1\121",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "\1\126",
            "\1\127",
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
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            "\1\145",
            "",
            "",
            "",
            "\1\146",
            "\1\24\2\uffff\2\24\1\uffff\12\24\7\uffff\32\24\4\uffff\1\24\1\uffff\32\24",
            ""
    };

    static final short[] DFA16_eot = DFA.unpackEncodedString(DFA16_eotS);
    static final short[] DFA16_eof = DFA.unpackEncodedString(DFA16_eofS);
    static final char[] DFA16_min = DFA.unpackEncodedStringToUnsignedChars(DFA16_minS);
    static final char[] DFA16_max = DFA.unpackEncodedStringToUnsignedChars(DFA16_maxS);
    static final short[] DFA16_accept = DFA.unpackEncodedString(DFA16_acceptS);
    static final short[] DFA16_special = DFA.unpackEncodedString(DFA16_specialS);
    static final short[][] DFA16_transition;

    static {
        int numStates = DFA16_transitionS.length;
        DFA16_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA16_transition[i] = DFA.unpackEncodedString(DFA16_transitionS[i]);
        }
    }

    class DFA16 extends DFA {

        public DFA16(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 16;
            this.eot = DFA16_eot;
            this.eof = DFA16_eof;
            this.min = DFA16_min;
            this.max = DFA16_max;
            this.accept = DFA16_accept;
            this.special = DFA16_special;
            this.transition = DFA16_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__13 | T__14 | T__15 | T__16 | T__17 | T__18 | T__19 | T__20 | RULE_FLOAT | RULE_BOOL | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_ALIAS | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA16_0 = input.LA(1);

                        s = -1;
                        if ( (LA16_0=='u') ) {s = 1;}

                        else if ( (LA16_0==',') ) {s = 2;}

                        else if ( (LA16_0=='i') ) {s = 3;}

                        else if ( (LA16_0=='=') ) {s = 4;}

                        else if ( (LA16_0=='F') ) {s = 5;}

                        else if ( (LA16_0=='O') ) {s = 6;}

                        else if ( (LA16_0=='C') ) {s = 7;}

                        else if ( (LA16_0=='S') ) {s = 8;}

                        else if ( (LA16_0=='-') ) {s = 9;}

                        else if ( ((LA16_0>='0' && LA16_0<='9')) ) {s = 10;}

                        else if ( (LA16_0=='t') ) {s = 11;}

                        else if ( (LA16_0=='f') ) {s = 12;}

                        else if ( (LA16_0=='\"') ) {s = 13;}

                        else if ( (LA16_0=='\'') ) {s = 14;}

                        else if ( (LA16_0=='/') ) {s = 15;}

                        else if ( (LA16_0=='*'||LA16_0=='.'||(LA16_0>='A' && LA16_0<='B')||(LA16_0>='D' && LA16_0<='E')||(LA16_0>='G' && LA16_0<='N')||(LA16_0>='P' && LA16_0<='R')||(LA16_0>='T' && LA16_0<='Z')||LA16_0=='_'||(LA16_0>='a' && LA16_0<='e')||(LA16_0>='g' && LA16_0<='h')||(LA16_0>='j' && LA16_0<='s')||(LA16_0>='v' && LA16_0<='z')) ) {s = 16;}

                        else if ( ((LA16_0>='\t' && LA16_0<='\n')||LA16_0=='\r'||LA16_0==' ') ) {s = 17;}

                        else if ( ((LA16_0>='\u0000' && LA16_0<='\b')||(LA16_0>='\u000B' && LA16_0<='\f')||(LA16_0>='\u000E' && LA16_0<='\u001F')||LA16_0=='!'||(LA16_0>='#' && LA16_0<='&')||(LA16_0>='(' && LA16_0<=')')||LA16_0=='+'||(LA16_0>=':' && LA16_0<='<')||(LA16_0>='>' && LA16_0<='@')||(LA16_0>='[' && LA16_0<='^')||LA16_0=='`'||(LA16_0>='{' && LA16_0<='\uFFFF')) ) {s = 18;}

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA16_14 = input.LA(1);

                        s = -1;
                        if ( ((LA16_14>='\u0000' && LA16_14<='\uFFFF')) ) {s = 33;}

                        else s = 18;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA16_13 = input.LA(1);

                        s = -1;
                        if ( ((LA16_13>='\u0000' && LA16_13<='\uFFFF')) ) {s = 33;}

                        else s = 18;

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 16, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}