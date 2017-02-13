/**
 * generated by Xtext 2.10.0
 */
package org.xtext.seml.ui.quickfix;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.ui.editor.model.IXtextDocument;
import org.eclipse.xtext.ui.editor.model.edit.IModificationContext;
import org.eclipse.xtext.ui.editor.model.edit.ISemanticModification;
import org.eclipse.xtext.ui.editor.quickfix.DefaultQuickfixProvider;
import org.eclipse.xtext.ui.editor.quickfix.Fix;
import org.eclipse.xtext.ui.editor.quickfix.IssueResolutionAcceptor;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.xtext.seml.validation.SeMLValidator;

/**
 * Custom quickfixes.
 * 
 * See https://www.eclipse.org/Xtext/documentation/310_eclipse_support.html#quick-fixes
 */
@SuppressWarnings("all")
public class SeMLQuickfixProvider extends DefaultQuickfixProvider {
  /**
   * @Fix(SeMLValidator.FIX_GENERATED)
   * def fix_GeneratedFileName(Issue issue, IssueResolutionAcceptor acceptor) {
   * acceptor.accept(issue, 'Fix generated filename', 'no description', null, new ISemanticModification() {
   * override apply(EObject element, IModificationContext context) {
   * var Import currentImport = element as Import;
   * currentImport.importURI = issue.data.get(0);
   * }
   * });
   * }
   */
  @Fix(SeMLValidator.GENERATE_SOLUTION)
  public void fix_GeneratedFileName(final Issue issue, final IssueResolutionAcceptor acceptor) {
    acceptor.accept(issue, "Try to generate solution", "no description", null, new ISemanticModification() {
      @Override
      public void apply(final EObject element, final IModificationContext context) {
        try {
          final IXtextDocument xtextDocument = context.getXtextDocument();
          String[] _data = issue.getData();
          final String s = _data[0];
          Integer _offset = issue.getOffset();
          Integer _length = issue.getLength();
          int _plus = ((_offset).intValue() + (_length).intValue());
          int _length_1 = s.length();
          int _minus = (_length_1 - 1);
          String _substring = s.substring(0, _minus);
          String _plus_1 = ("\n" + _substring);
          xtextDocument.replace(_plus, 0, _plus_1);
        } catch (Throwable _e) {
          throw Exceptions.sneakyThrow(_e);
        }
      }
    });
  }
}
