/**
 * generated by Xtext 2.10.0
 */
package org.xtext.seml.validation;

import com.google.common.base.Objects;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.rass.ontologies.MasterOntology;
import org.rass.ontologies.Ontologies;
import org.xtext.seml.seML.Component;
import org.xtext.seml.seML.Import;
import org.xtext.seml.seML.Individual;
import org.xtext.seml.seML.MainModel;
import org.xtext.seml.seML.Model;
import org.xtext.seml.seML.Relation;
import org.xtext.seml.seML.SeMLPackage;
import org.xtext.seml.validation.AbstractSeMLValidator;

/**
 * This class contains custom validation rules.
 * 
 * See https://www.eclipse.org/Xtext/documentation/303_runtime_concepts.html#validation
 */
@SuppressWarnings("all")
public class SeMLValidator extends AbstractSeMLValidator {
  private static String local_log = "Validator Log: ";
  
  public final static String INVALID_NAME = "invalidName";
  
  public final static String GET_AXIOMS = "GetAxioms";
  
  public final static String FIX_GENERATED = "FixGeneratedName";
  
  @Check(CheckType.NORMAL)
  public Object checkRelation(final Relation rel) {
    return null;
  }
  
  @Check(CheckType.FAST)
  public void checkIndividual(final Individual ind) {
    String _name = ind.getName();
    boolean _contains = _name.contains("#");
    if (_contains) {
      this.error("Individual name cannot contain \"#\"", SeMLPackage.Literals.ANY_INDIVIDUAL__NAME);
    }
  }
  
  @Check(CheckType.FAST)
  public void checkModelImports(final MainModel m) {
    final String local_log = (SeMLValidator.local_log + "[checkModelImports] ");
    long mostRecentFile = 0;
    EList<Import> _imports = m.getImports();
    boolean _isEmpty = _imports.isEmpty();
    if (_isEmpty) {
      return;
    }
    EList<Import> _imports_1 = m.getImports();
    int _length = ((Object[])Conversions.unwrapArray(_imports_1, Object.class)).length;
    final String[] pathslist = new String[_length];
    int cnt = 0;
    EList<Import> _imports_2 = m.getImports();
    for (final Import i : _imports_2) {
      {
        String _name = i.getName();
        final File ontfile = new File(_name);
        if (((!ontfile.exists()) || ontfile.isDirectory())) {
          this.error("Ontology file was not found", i, SeMLPackage.Literals.IMPORT__NAME);
          return;
        }
        long _lastModified = ontfile.lastModified();
        boolean _lessThan = (mostRecentFile < _lastModified);
        if (_lessThan) {
          long _lastModified_1 = ontfile.lastModified();
          mostRecentFile = _lastModified_1;
        }
        int _plusPlus = cnt++;
        String _name_1 = i.getName();
        pathslist[_plusPlus] = _name_1;
      }
    }
    Arrays.sort(pathslist);
    Ontologies.populatePaths(m);
    if ((Ontologies.GENfile.exists() && Ontologies.GENfile.isFile())) {
      long _lastModified = Ontologies.GENfile.lastModified();
      int _compareTo = Long.valueOf(mostRecentFile).compareTo(Long.valueOf(_lastModified));
      boolean _lessThan = (_compareTo < 0);
      if (_lessThan) {
        try {
          final FileInputStream fis = new FileInputStream(Ontologies.GENfile);
          InputStreamReader _inputStreamReader = new InputStreamReader(fis);
          final BufferedReader br = new BufferedReader(_inputStreamReader);
          String line = br.readLine();
          cnt = 0;
          int _length_1 = Ontologies.GENfirstline.length();
          String _substring = line.substring(_length_1);
          int SourceFilesNo = (Integer.valueOf(_substring)).intValue();
          boolean different = false;
          int _size = ((List<String>)Conversions.doWrapArray(pathslist)).size();
          boolean _equals = (_size == SourceFilesNo);
          if (_equals) {
            while ((!Objects.equal((line = br.readLine()), "*/"))) {
              int _plusPlus = cnt++;
              String _get = pathslist[_plusPlus];
              boolean _equals_1 = _get.equals(line);
              boolean _not = (!_equals_1);
              if (_not) {
                different = true;
              }
            }
            if ((!different)) {
              br.close();
              return;
            } else {
              System.out.println((local_log + "Ontology sources have changed. Updating DSL keywords..."));
            }
          } else {
            System.out.println((local_log + "Number of ontology sources has changed. Updating DSL keywords..."));
          }
          br.close();
        } catch (final Throwable _t) {
          if (_t instanceof Exception) {
            final Exception e = (Exception)_t;
            String _message = e.getMessage();
            String _plus = ((local_log + "Error while reading generated file: ") + _message);
            System.out.println(_plus);
            System.out.println((local_log + "Repairing file..."));
          } else {
            throw Exceptions.sneakyThrow(_t);
          }
        }
      } else {
        System.out.println((local_log + "Changes in ontologies detected. Updating DSL keywords..."));
      }
    } else {
      System.out.println((local_log + "Importing DSL keywords for the first time..."));
    }
    try {
      Ontologies.ParseOntologies(pathslist);
    } catch (final Throwable _t_1) {
      if (_t_1 instanceof IOException) {
        final IOException e_1 = (IOException)_t_1;
        String _message_1 = e_1.getMessage();
        String _plus_1 = (local_log + _message_1);
        System.out.println(_plus_1);
        String _message_2 = e_1.getMessage();
        EList<Import> _imports_3 = m.getImports();
        Import _head = IterableExtensions.<Import>head(_imports_3);
        this.error(_message_2, _head, SeMLPackage.Literals.IMPORT__NAME);
        return;
      } else {
        throw Exceptions.sneakyThrow(_t_1);
      }
    }
  }
  
  @Check(CheckType.NORMAL)
  public void checkModel(final MainModel m) {
    try {
      final String local_log = (SeMLValidator.local_log + "[checkModel] ");
      String inconsistencyReport = null;
      System.out.println((local_log + "Validating model..."));
      final List<Import> ImportsList = EcoreUtil2.<Import>getAllContentsOfType(m, Import.class);
      boolean _isEmpty = ImportsList.isEmpty();
      if (_isEmpty) {
        System.out.println((local_log + "Warning: Model has no imported ontologies"));
        return;
      }
      final List<Individual> IndividualsList = EcoreUtil2.<Individual>getAllContentsOfType(m, Individual.class);
      final List<Relation> RelationsList = EcoreUtil2.<Relation>getAllContentsOfType(m, Relation.class);
      boolean _createMasterOntology = MasterOntology.createMasterOntology(ImportsList);
      boolean _not = (!_createMasterOntology);
      if (_not) {
        Import _head = IterableExtensions.<Import>head(ImportsList);
        this.error("The merged master ontology is inconsistent", _head, SeMLPackage.Literals.IMPORT__NAME);
        return;
      }
      for (final Individual i : IndividualsList) {
        {
          String _addIndividual = MasterOntology.addIndividual(i);
          inconsistencyReport = _addIndividual;
          boolean _notEquals = (!Objects.equal(inconsistencyReport, null));
          if (_notEquals) {
            this.error(inconsistencyReport, i, SeMLPackage.Literals.ANY_INDIVIDUAL__NAME);
            return;
          }
        }
      }
      for (final Relation r : RelationsList) {
        {
          String _addRelation = MasterOntology.addRelation(r);
          inconsistencyReport = _addRelation;
          boolean _notEquals = (!Objects.equal(inconsistencyReport, null));
          if (_notEquals) {
            this.error(inconsistencyReport, r, SeMLPackage.Literals.RELATION__OBJ);
            return;
          }
        }
      }
      for (final Individual i_1 : IndividualsList) {
        EList<Component> _cls = i_1.getCls();
        for (final Component c : _cls) {
          {
            String _name = c.getName();
            String _name_1 = i_1.getName();
            String _plus = ((MasterOntology.OWL_Master + "#") + _name_1);
            String _checkRelationRestrictions = MasterOntology.checkRelationRestrictions(_name, _plus);
            inconsistencyReport = _checkRelationRestrictions;
            boolean _notEquals = (!Objects.equal(inconsistencyReport, null));
            if (_notEquals) {
              this.error(inconsistencyReport, i_1, SeMLPackage.Literals.ANY_INDIVIDUAL__NAME);
              return;
            }
          }
        }
      }
      System.out.println((local_log + "Done."));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public Model getImportModel(final Resource contextResource, final String importURIAsString) {
    URI _createURI = null;
    if (URI.class!=null) {
      _createURI=URI.createURI(importURIAsString);
    }
    final URI importURI = _createURI;
    URI _uRI = null;
    if (contextResource!=null) {
      _uRI=contextResource.getURI();
    }
    final URI contextURI = _uRI;
    URI _resolve = null;
    if (importURI!=null) {
      _resolve=importURI.resolve(contextURI);
    }
    final URI resolvedURI = _resolve;
    ResourceSet _resourceSet = null;
    if (contextResource!=null) {
      _resourceSet=contextResource.getResourceSet();
    }
    final ResourceSet contextResourceSet = _resourceSet;
    Resource _resource = null;
    if (contextResourceSet!=null) {
      _resource=contextResourceSet.getResource(resolvedURI, false);
    }
    final Resource resource = _resource;
    TreeIterator<EObject> _allContents = null;
    if (resource!=null) {
      _allContents=resource.getAllContents();
    }
    EObject _head = null;
    if (_allContents!=null) {
      _head=IteratorExtensions.<EObject>head(_allContents);
    }
    return ((Model) _head);
  }
}