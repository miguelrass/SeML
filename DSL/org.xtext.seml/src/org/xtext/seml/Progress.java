package org.xtext.seml;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

public class Progress implements IRunnableWithProgress{
	
	 private int workload;
	 private int displayedState; //Displayed state
	 public int state; //Current state, <= workload
	 public String task; //Main task being executed
	 public String subTask; //Sub task being executed
	 public final static Shell shell = PlatformUI.getWorkbench().getWorkbenchWindows()[0].getShell();
	 	 
	 /*static{
		 
     	// create a dialog with ok and cancel buttons and a question icon
     	MessageBox dialog = new MessageBox(shell, SWT.ICON_QUESTION | SWT.OK| SWT.CANCEL);
     	dialog.setText("My info");
     	dialog.setMessage("Do you really want to do this?");

     	// open dialog and await user selection
     	int returnCode = dialog.open();
	 }*/
	 

     public Progress(int workld, String tsk, String subTsk)
     {
    	 state = 0;
    	 workload = workld;
    	 task = tsk;
    	 subTask = subTsk;
    	 
    	 IRunnableWithProgress rp = this;
    	 ProgressMonitorDialog a = new ProgressMonitorDialog(Progress.shell);
    	 Display.getDefault().asyncExec(new Runnable() {
    		 public void run() {	

    			 try {
    				 new ProgressMonitorDialog(Progress.shell).run(true, true, rp);
                 } 
    			 catch (InvocationTargetException ex) {ex.printStackTrace();} 
    			 catch (InterruptedException ex) {ex.printStackTrace();}
    		 }
    	 });
     }
     
     public void stop(){
    	 state = workload;
     }

     @Override
     public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
     {
         // Main Task
         monitor.beginTask(task, workload);

         // Monitor progress
         while(displayedState != workload && !monitor.isCanceled()){
        	 
        	 //Update progress if state has changed
        	 if(displayedState != state){
        		 monitor.subTask(subTask);
        		 monitor.worked(state - displayedState);
        		 displayedState = state;
        	 }  
        	 Thread.sleep(100); //yield
        	 
         }

         monitor.done();
     }

}
