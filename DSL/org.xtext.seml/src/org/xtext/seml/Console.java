package org.xtext.seml;

import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.console.ConsolePlugin;
import org.eclipse.ui.console.IConsole;
import org.eclipse.ui.console.IConsoleManager;
import org.eclipse.ui.console.MessageConsole;
import org.eclipse.ui.console.MessageConsoleStream;

public class Console {

    private static MessageConsole console = new MessageConsole("SeML Console", null);
    private static MessageConsoleStream out = console.newMessageStream();
    private static MessageConsoleStream err = console.newMessageStream();
    private static MessageConsoleStream deb = console.newMessageStream();
    private static MessageConsoleStream imp = console.newMessageStream();
    
    static{

        IConsoleManager manager = ConsolePlugin.getDefault().getConsoleManager();
        /*IConsole[] existing = manager.getConsoles();
        boolean exists = false;
        for (int i = 0; i < existing.length; i++) {
            if(console == existing[i])
                exists = true;
        }*/
        //if(! exists)
        manager.addConsoles(new IConsole[] {console});
        manager.showConsoleView(console);
        ChangeConsole(new Color(null, 255, 255, 255), new Font(null,"Arial", 10, 0));
        ChangeStream(err, new Color(null, 255, 0, 0), -1);
        ChangeStream(deb, new Color(null, 127, 127, 127), -1);
        ChangeStream(imp, new Color(null, 0, 0, 255), -1);

    }
    
    public MessageConsoleStream getNewStream(){
    	return console.newMessageStream();
    }

    public static void ChangeStream(MessageConsoleStream str, Color c, int s){
    	Display.getDefault().asyncExec(new Runnable() {
            public void run() {	
            	if(c!=null) str.setColor(c);
            	if(s!=-1) str.setFontStyle(s); 	
            }
         });
    }
    
    public static void ChangeConsole(Color c, Font f){
    	Display.getDefault().asyncExec(new Runnable() {
            public void run() {	
    			if(c!=null) console.setBackground(c);
    			if(f!=null) console.setFont(f);
            }
         });
    }
    
    public static void OutPairLn(String s1, String s2){
    	out.println(s2); System.out.println(s1+s2);
    }
    
    public static void ErrPairLn(String s1, String s2){
    	err.println(s2); System.err.println(s1+s2);
    }
    
    public static void DebPairLn(String s1, String s2){
    	deb.println(s2); System.out.println(s1+s2);
    }
    
    public static void ImpPairLn(String s1, String s2){
    	imp.println(s2); System.out.println(s1+s2);
    }
    
    public static void DebPair(String s1, String s2){
    	deb.print(s2); System.out.print(s1+s2);
    }
    
    public static void OutLn(String s){
    	out.println(s); System.out.println(s);
    }
    
    public static void ErrLn(String s){
    	err.println(s); System.err.println(s);
    }

}
