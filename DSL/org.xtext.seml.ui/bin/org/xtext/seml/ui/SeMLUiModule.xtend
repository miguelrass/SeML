/*
 * generated by Xtext 2.10.0
 */
package org.xtext.seml.ui

import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor
import com.google.inject.Inject
import org.xtext.seml.services.SeMLGrammarAccess
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator
import org.eclipse.xtext.ide.editor.syntaxcoloring.DefaultSemanticHighlightingCalculator
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.nodemodel.ICompositeNode
import org.eclipse.xtext.ide.editor.syntaxcoloring.IHighlightedPositionAcceptor
import org.eclipse.xtext.ui.editor.syntaxcoloring.DefaultHighlightingConfiguration
import org.eclipse.xtext.nodemodel.INode
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.resource.EObjectAtOffsetHelper
import org.xtext.seml.seML.MetaIndividual

/**
 * Use this class to register components to be used within the Eclipse IDE.
 */
@FinalFieldsConstructor
class SeMLUiModule extends AbstractSeMLUiModule {
	
	def Class<? extends ISemanticHighlightingCalculator> bindISemanticHighlightingCalculator() {
		SeMLSemanticHighlightingCalculator
	}
}
	
public class SeMLSemanticHighlightingCalculator extends DefaultSemanticHighlightingCalculator{
	
		@Inject
		SeMLGrammarAccess ga;
		
		@Inject
		private EObjectAtOffsetHelper helper;
		
		//@Override
		override public void provideHighlightingFor(XtextResource resource, IHighlightedPositionAcceptor acceptor, CancelIndicator cancelIndicator) {
			var ICompositeNode rootNode = resource.getParseResult().getRootNode();
			
			for (INode node : rootNode.getAsTreeIterable()) {
				if (node.getGrammarElement() == ga.relationAccess.objObjectPropertyCrossReference_1_0) {
					acceptor.addPosition(node.getOffset(), node.getLength(), DefaultHighlightingConfiguration.KEYWORD_ID);
				}

				if (node.getGrammarElement() == ga.individualAccess.clsComponentCrossReference_1_0 || node.getGrammarElement() == ga.individualAccess.clsComponentCrossReference_2_1_0) {
					acceptor.addPosition(node.getOffset(), node.getLength(), DefaultHighlightingConfiguration.NUMBER_ID);
				}
				
				if (node.getGrammarElement() == ga.relationAccess.instance1AnyIndividualCrossReference_0_0 || node.getGrammarElement() == ga.relationAccess.instance2AnyIndividualCrossReference_2_0) {
					val EObject target = helper.resolveElementAt(resource, node.getOffset());
					if(target instanceof MetaIndividual){
						acceptor.addPosition(node.getOffset(), node.getLength(), DefaultHighlightingConfiguration.TASK_ID);
					}
				}
			
			}
			super.provideHighlightingFor(resource, acceptor, cancelIndicator);
		
		}
}


