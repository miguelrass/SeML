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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.function.Consumer;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.xtext.EcoreUtil2;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.CheckType;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.IteratorExtensions;
import org.rass.ontologies.Anomaly;
import org.rass.ontologies.MasterOntology;
import org.rass.ontologies.Ontologies;
import org.xtext.seml.seML.Component;
import org.xtext.seml.seML.Import;
import org.xtext.seml.seML.ImportModel;
import org.xtext.seml.seML.Individual;
import org.xtext.seml.seML.MainModel;
import org.xtext.seml.seML.MetaIndividual;
import org.xtext.seml.seML.Relation;
import org.xtext.seml.seML.SeMLPackage;
import org.xtext.seml.seML.UseCharacteristic;
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
  
  public final static String GENERATE_SOLUTION = "GenerateSolution";
  
  @Check(CheckType.FAST)
  public void checkIndividual(final Individual ind) {
    boolean _contains = ind.getName().contains("#");
    if (_contains) {
      this.error("Individual name cannot contain \"#\"", SeMLPackage.Literals.ANY_INDIVIDUAL__NAME);
    }
  }
  
  @Check(CheckType.FAST)
  public void checkModel(final MainModel m) {
    try {
      final String local_log = (SeMLValidator.local_log + "[checkModel] ");
      String[] inconsistencyReport = null;
      boolean _CheckImports = this.CheckImports(m);
      boolean _not = (!_CheckImports);
      if (_not) {
        return;
      }
      System.out.print((local_log + "Validating model..."));
      final DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
      final Date date = new Date();
      String _format = dateFormat.format(date);
      String _plus = ("(" + _format);
      String _plus_1 = (_plus + ")");
      System.out.println(_plus_1);
      final List<Individual> individualsList = EcoreUtil2.<Individual>getAllContentsOfType(m, Individual.class);
      final List<Relation> relationsList = EcoreUtil2.<Relation>getAllContentsOfType(m, Relation.class);
      final List<UseCharacteristic> useList = EcoreUtil2.<UseCharacteristic>getAllContentsOfType(m, UseCharacteristic.class);
      for (final Individual i : individualsList) {
        {
          String _name = i.getName();
          boolean _tripleEquals = (_name == null);
          if (_tripleEquals) {
            System.out.println((local_log + "Aborted. Model contains errors."));
            return;
          }
          EList<Component> _cls = i.getCls();
          for (final Component c : _cls) {
            String _iri = c.getIri();
            boolean _tripleEquals_1 = (_iri == null);
            if (_tripleEquals_1) {
              System.out.println((local_log + "Aborted. Model contains errors."));
              return;
            }
          }
        }
      }
      for (final Relation r : relationsList) {
        if ((((((r.getInstance1() == null) || (r.getInstance2() == null)) || (r.getInstance1().getName() == null)) || (r.getInstance2().getName() == null)) || (r.getObj().getName() == null))) {
          System.out.println((local_log + "Aborted. Model contains relation errors."));
          return;
        }
      }
      try {
        File _file = new File((Ontologies.GENfolder + Ontologies.masterNAME));
        MasterOntology.loadMasterOntology(_file);
      } catch (final Throwable _t) {
        if (_t instanceof IOException) {
          final IOException e = (IOException)_t;
          String _message = e.getMessage();
          String _plus_2 = ("Error loading master ontology file: " + _message);
          this.error(_plus_2, IterableExtensions.<Import>last(m.getImports()), SeMLPackage.Literals.IMPORT__NAME);
          return;
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      final Consumer<Individual> _function = (Individual i_1) -> {
        MasterOntology.addIndividual(i_1);
      };
      individualsList.forEach(_function);
      for (final Relation r_1 : relationsList) {
        boolean _addRelation = MasterOntology.addRelation(r_1);
        boolean _not_1 = (!_addRelation);
        if (_not_1) {
          System.out.println((local_log + "Aborted. Model contains relation errors."));
          return;
        }
      }
      boolean _ReasonAndExplainMaster = MasterOntology.ReasonAndExplainMaster();
      if (_ReasonAndExplainMaster) {
        boolean _ReportAnomalies = this.ReportAnomalies(m, individualsList, relationsList);
        boolean _not_2 = (!_ReportAnomalies);
        if (_not_2) {
          return;
        }
      }
      final ImportModel importRoot = this.getImportModel(m.eResource(), Ontologies.GENfile_relpath);
      if ((importRoot == null)) {
        String _absolutePath = Ontologies.GENfile.getAbsolutePath();
        String _plus_3 = ("Error loading keywords file: " + _absolutePath);
        this.error(_plus_3, IterableExtensions.<Import>last(m.getImports()), SeMLPackage.Literals.IMPORT__NAME);
        return;
      }
      final EList<MetaIndividual> MetaIndividualsList = importRoot.getMetaIndividuals();
      MasterOntology.cacheIRIs(importRoot, individualsList);
      System.out.print((local_log + "Checking class restrictions"));
      for (final MetaIndividual i_1 : MetaIndividualsList) {
        EList<String> _cls = i_1.getCls();
        for (final String s : _cls) {
          {
            inconsistencyReport = MasterOntology.checkRelationRestrictions(s, i_1.getIri());
            if ((inconsistencyReport != null)) {
              boolean _isEmpty = inconsistencyReport[1].isEmpty();
              if (_isEmpty) {
                String _iri = i_1.getIri();
                String _plus_4 = ("Metamodel Individual: " + _iri);
                String _plus_5 = (_plus_4 + "\n");
                String _get = inconsistencyReport[0];
                String _plus_6 = (_plus_5 + _get);
                this.error(_plus_6, IterableExtensions.<Import>last(m.getImports()), SeMLPackage.Literals.IMPORT__NAME);
              } else {
                String _iri_1 = i_1.getIri();
                String _plus_7 = ("Metamodel Individual: " + _iri_1);
                String _plus_8 = (_plus_7 + "\n");
                String _get_1 = inconsistencyReport[0];
                String _plus_9 = (_plus_8 + _get_1);
                this.error(_plus_9, IterableExtensions.<Import>last(m.getImports()), SeMLPackage.Literals.IMPORT__NAME, SeMLValidator.GENERATE_SOLUTION, inconsistencyReport[1]);
              }
              return;
            }
          }
        }
      }
      for (final Individual i_2 : individualsList) {
        EList<Component> _cls_1 = i_2.getCls();
        for (final Component c : _cls_1) {
          {
            String _iri = c.getIri();
            String _name = i_2.getName();
            String _plus_4 = ((MasterOntology.OWL_Master + "#") + _name);
            inconsistencyReport = MasterOntology.checkRelationRestrictions(_iri, _plus_4);
            if ((inconsistencyReport != null)) {
              boolean _isEmpty = inconsistencyReport[1].isEmpty();
              if (_isEmpty) {
                this.error(inconsistencyReport[0], i_2, SeMLPackage.Literals.ANY_INDIVIDUAL__NAME);
              } else {
                this.error(inconsistencyReport[0], i_2, SeMLPackage.Literals.ANY_INDIVIDUAL__NAME, SeMLValidator.GENERATE_SOLUTION, inconsistencyReport[1]);
              }
              return;
            }
          }
        }
      }
      for (final UseCharacteristic u : useList) {
        {
          inconsistencyReport = MasterOntology.checkRelationRestrictions(u.getName().getIri(), "");
          if ((inconsistencyReport != null)) {
            boolean _isEmpty = inconsistencyReport[1].isEmpty();
            if (_isEmpty) {
              String _iri = u.getName().getIri();
              String _plus_4 = ("Characteristic: " + _iri);
              String _plus_5 = (_plus_4 + "\n");
              String _get = inconsistencyReport[0];
              String _plus_6 = (_plus_5 + _get);
              this.error(_plus_6, u, SeMLPackage.Literals.USE_CHARACTERISTIC__NAME);
            } else {
              String _iri_1 = u.getName().getIri();
              String _plus_7 = ("Characteristic: " + _iri_1);
              String _plus_8 = (_plus_7 + "\n");
              String _get_1 = inconsistencyReport[0];
              String _plus_9 = (_plus_8 + _get_1);
              this.error(_plus_9, u, SeMLPackage.Literals.USE_CHARACTERISTIC__NAME, SeMLValidator.GENERATE_SOLUTION, inconsistencyReport[1]);
            }
            return;
          }
        }
      }
      inconsistencyReport = MasterOntology.checkRelationRestrictions(Ontologies.OWL_DefaultC, "");
      if ((inconsistencyReport != null)) {
        boolean _isEmpty = inconsistencyReport[1].isEmpty();
        if (_isEmpty) {
          String _get = inconsistencyReport[0];
          String _plus_4 = ("Default Characteristic\n" + _get);
          this.error(_plus_4, IterableExtensions.<Import>last(m.getImports()), SeMLPackage.Literals.IMPORT__NAME);
        } else {
          String _get_1 = inconsistencyReport[0];
          String _plus_5 = ("Default Characteristic\n" + _get_1);
          this.error(_plus_5, IterableExtensions.<Import>last(m.getImports()), SeMLPackage.Literals.IMPORT__NAME, SeMLValidator.GENERATE_SOLUTION, inconsistencyReport[1]);
        }
        return;
      }
      System.out.println((("\n" + local_log) + "Done."));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Auxiliary function to return anomalies for individual creation and relation instantiation
   * @return returns false if the model is inconsistent
   */
  public boolean ReportAnomalies(final MainModel m, final List<Individual> individualsList, final List<Relation> relationsList) {
    final String local_log = (SeMLValidator.local_log + "[checkModel] ");
    String issue = Anomaly.getAnomalies();
    if ((issue != null)) {
      this.RouteToAgent(m, issue, individualsList, relationsList, 1);
      return false;
    }
    issue = Anomaly.getErrors();
    if ((issue != null)) {
      this.RouteToAgent(m, issue, individualsList, relationsList, 2);
    }
    issue = Anomaly.getWarnings();
    if ((issue != null)) {
      this.RouteToAgent(m, issue, individualsList, relationsList, 3);
    }
    issue = Anomaly.getInfos();
    if ((issue != null)) {
      this.RouteToAgent(m, issue, individualsList, relationsList, 4);
    }
    return true;
  }
  
  /**
   * Very basic function to dispatch the issue to its agent (only implemented for non-meta individuals)
   */
  public void RouteToAgent(final MainModel m, final String issue, final List<Individual> individualsList, final List<Relation> relationsList, final int type) {
    for (final Individual i : individualsList) {
      boolean _contains = issue.contains(i.getName());
      if (_contains) {
        this.DisplayAnomalies((" (related with this individual):\n" + issue), i, SeMLPackage.Literals.ANY_INDIVIDUAL__NAME, type);
        return;
      }
    }
    this.DisplayAnomalies((":\n" + issue), IterableExtensions.<Import>last(m.getImports()), SeMLPackage.Literals.IMPORT__NAME, type);
  }
  
  public void DisplayAnomalies(final String s, final EObject eo, final EStructuralFeature eRef, final int type) {
    final String local_log = (SeMLValidator.local_log + "[checkModel] ");
    switch (type) {
      case 1:
        this.error(("Anomaly detected" + s), eo, eRef);
        break;
      case 2:
        this.error(("Error inferred" + s), eo, eRef);
        break;
      case 3:
        this.warning(("Warning inferred" + s), eo, eRef);
        break;
      case 4:
        this.warning(("Information inferred" + s), eo, eRef);
        break;
    }
  }
  
  /**
   * Load and parse ImportModel. This method loads the file on-demand
   * if the model contains no cross-references.
   * 
   * @param contextResource		Absolute file path of the ImportsModel
   * @param importURIAsString		Absolute file path of the ImportsModel
   * @return	the ImportModel or null in case of failure
   */
  public ImportModel getImportModel(final Resource contextResource, final String importURIAsString) {
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
      _resource=contextResourceSet.getResource(resolvedURI, true);
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
    return ((ImportModel) _head);
  }
  
  /**
   * Auxiliary function of checkModel, to check imports and create the master ontology
   * 
   * @param m		MainModel
   * @return		True if imports are valid
   */
  public boolean CheckImports(final MainModel m) {
    final String local_log = (SeMLValidator.local_log + "[checkModelImports] ");
    long mostRecentFile = 0;
    boolean _isEmpty = m.getImports().isEmpty();
    if (_isEmpty) {
      return false;
    }
    final String[] pathslist = new String[((Object[])Conversions.unwrapArray(m.getImports(), Object.class)).length];
    int cnt = 0;
    EList<Import> _imports = m.getImports();
    for (final Import i : _imports) {
      {
        String _name = i.getName();
        final File ontfile = new File(_name);
        if (((!ontfile.exists()) || ontfile.isDirectory())) {
          this.error("Ontology file was not found", i, SeMLPackage.Literals.IMPORT__NAME);
          return false;
        }
        long _lastModified = ontfile.lastModified();
        boolean _lessThan = (mostRecentFile < _lastModified);
        if (_lessThan) {
          mostRecentFile = ontfile.lastModified();
        }
        int _plusPlus = cnt++;
        pathslist[_plusPlus] = i.getName();
      }
    }
    Arrays.sort(pathslist);
    Ontologies.populatePaths(m);
    if ((Ontologies.GENfile.exists() && Ontologies.GENfile.isFile())) {
      int _compareTo = Long.valueOf(mostRecentFile).compareTo(Long.valueOf(Ontologies.GENfile.lastModified()));
      boolean _lessThan = (_compareTo < 0);
      if (_lessThan) {
        try {
          final FileInputStream fis = new FileInputStream(Ontologies.GENfile);
          InputStreamReader _inputStreamReader = new InputStreamReader(fis);
          final BufferedReader br = new BufferedReader(_inputStreamReader);
          String line = br.readLine();
          cnt = 0;
          int SourceFilesNo = (Integer.valueOf(line.substring(Ontologies.GENfirstline.length()))).intValue();
          boolean different = false;
          int _size = ((List<String>)Conversions.doWrapArray(pathslist)).size();
          boolean _equals = (_size == SourceFilesNo);
          if (_equals) {
            while ((!Objects.equal((line = br.readLine()), "*/"))) {
              int _plusPlus = cnt++;
              boolean _equals_1 = pathslist[_plusPlus].equals(line);
              boolean _not = (!_equals_1);
              if (_not) {
                different = true;
              }
            }
            if ((!different)) {
              final File masterfile = new File((Ontologies.GENfolder + Ontologies.masterNAME));
              if ((masterfile.exists() && masterfile.isFile())) {
                br.close();
                return true;
              } else {
                System.out.println((local_log + "Master Ontology file was deleted. Creating a new one..."));
              }
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
        this.error(e_1.getMessage(), IterableExtensions.<Import>last(m.getImports()), SeMLPackage.Literals.IMPORT__NAME);
        return false;
      } else {
        throw Exceptions.sneakyThrow(_t_1);
      }
    }
    return true;
  }
}
