/**
 * generated by Xtext 2.10.0
 */
package org.xtext.seml.ui;

import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtend.lib.annotations.FinalFieldsConstructor;
import org.eclipse.xtext.ide.editor.syntaxcoloring.ISemanticHighlightingCalculator;
import org.xtext.seml.ui.AbstractSeMLUiModule;
import org.xtext.seml.ui.SeMLSemanticHighlightingCalculator;

/**
 * Use this class to register components to be used within the Eclipse IDE.
 */
@FinalFieldsConstructor
@SuppressWarnings("all")
public class SeMLUiModule extends AbstractSeMLUiModule {
  public Class<? extends ISemanticHighlightingCalculator> bindISemanticHighlightingCalculator() {
    return SeMLSemanticHighlightingCalculator.class;
  }
  
  public SeMLUiModule(final AbstractUIPlugin plugin) {
    super(plugin);
  }
}
