/*
 * generated by Xtext 2.10.0
 */
package org.xtext.seml.ui.contentassist

import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.ui.editor.contentassist.ICompletionProposalAcceptor
import org.eclipse.xtext.Assignment
import org.eclipse.xtext.ui.editor.contentassist.ContentAssistContext
import org.xtext.seml.seML.Individual
import org.eclipse.xtext.RuleCall
import org.xtext.seml.seML.Import
import java.io.File
import org.rass.ontologies.Ontologies

/**
 * See https://www.eclipse.org/Xtext/documentation/304_ide_concepts.html#content-assist
 * on how to customize the content assistant.
 */
class SeMLProposalProvider extends AbstractSeMLProposalProvider {
	
//	 override void completeIndividual_Name(EObject model, Assignment assignment,
//	 	ContentAssistContext context, ICompletionProposalAcceptor acceptor){
//      // call implementation of superclass
//      super.completeIndividual_Name(model, assignment, context, acceptor)
//     
//      // compute the plain proposal
//      val String proposal = (model as Individual).getName() + "ID"
//     
//      // Create and register the completion proposal:
//      // The proposal may be null as the createCompletionProposal(..) 
//      // methods check for valid prefixes and terminal token conflicts.
//      // The acceptor handles null-values gracefully.
//      acceptor.accept(createCompletionProposal(proposal, context))
//    }
    
   /*  override void completeImport_ImportURI(EObject model, Assignment assignment, ContentAssistContext context, ICompletionProposalAcceptor acceptor) {
		// call implementation of superclass
      	super.completeImport_ImportURI(model, assignment, context, acceptor)
      	
      	var Import imp = (model as Import);
      	
      	//Declare a File object for the proposed Ontology
      	val File ontfile = new File(imp.getName());
      	
      	//Check if ontology file exists
		if(!ontfile.exists || ontfile.isDirectory) {acceptor.accept(createCompletionProposal("\"File was not found\"", context)); return;}
		
		//Generate paths for current SEML file and generated SEML file, for a given ontology
		Ontologies.populatePaths(imp, ontfile);
 
		//Propose correctly generated file path
		acceptor.accept(createCompletionProposal("\"" + Ontologies.SEMLGENfile_relpath + "\"", context))
	}*/
	
	
    
}