/*
 * generated by Xtext 2.10.0
 */
package org.xtext.seml.ui.quickfix

import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider
import org.xtext.seml.validation.SeMLValidator
import org.eclipse.xtext.ui.editor.quickfix.Fix
import org.eclipse.xtext.validation.Issue
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext
import org.eclipse.emf.ecore.EObject
import org.xtext.seml.seML.Model
import org.xtext.seml.seML.SeMLFactory
import org.xtext.seml.seML.Import
import org.eclipse.emf.common.util.EList
import java.util.Iterator
import org.eclipse.xtext.EcoreUtil2
import org.xtext.seml.seML.MainModel

/**
 * Custom quickfixes.
 *
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#quick-fixes
 */
class SeMLQuickfixProvider extends DefaultQuickfixProvider {

//	@Fix(SeMLValidator.INVALID_NAME)
//	def capitalizeName(Issue issue, IssueResolutionAcceptor acceptor) {
//		acceptor.accept(issue, 'Capitalize name', 'Capitalize the name.', 'upcase.png') [
//			context |
//			val xtextDocument = context.xtextDocument
//			val firstLetter = xtextDocument.get(issue.offset, 1)
//			xtextDocument.replace(issue.offset, 1, firstLetter.toUpperCase)
//		]
//	}

//	@Fix(SeMLValidator.GET_AXIOMS)
//	def get_Axioms(Issue issue, IssueResolutionAcceptor acceptor) {
//		/*acceptor.accept(issue, 'Get Axioms', 'Get Classes and DataProperties of Ontology'+issue.data.get(0), '') [
//			context |
//			val xtextDocument = context.xtextDocument
//			//val firstLetter = xtextDocument.get(issue.offset + issue.length, 1)
//			xtextDocument.replace(issue.offset + issue.length, 0, '\n' + issue.data.get(1))
//		]*/
//		acceptor.accept(issue, 'Import Axioms', 'Import Classes and DataProperties from Ontology', null, new ISemanticModification() {
//	        override apply(EObject element, IModificationContext context) {
//	        	
//	        	var Import currentImport = element as Import; 
//	        	val MainModel model = EcoreUtil2.getContainerOfType(currentImport, MainModel);
//	            val Imported newImported = SeMLFactory.eINSTANCE.createImported();
//	            newImported.importURI = issue.data.get(0);
//	            newImported.name = currentImport.name;
//	            model.imports.remove(currentImport);
//	            model.importeds.add(newImported);
//	            //(element as Include).setSuperType(newInclude); Relacionar com o componente em causa a ser validado (=element)
//	        }
//    	});
//	}
	
/* 	@Fix(SeMLValidator.FIX_GENERATED)
	def fix_GeneratedFileName(Issue issue, IssueResolutionAcceptor acceptor) {
		acceptor.accept(issue, 'Fix generated filename', 'no description', null, new ISemanticModification() {
	        override apply(EObject element, IModificationContext context) {        	
	        	var Import currentImport = element as Import; 
	        	currentImport.importURI = issue.data.get(0);
	        }
    	});
	}*/
	

}