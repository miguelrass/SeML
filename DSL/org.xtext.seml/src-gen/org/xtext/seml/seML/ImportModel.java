/**
 * generated by Xtext 2.11.0
 */
package org.xtext.seml.seML;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Import Model</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.seml.seML.ImportModel#getStaticIndividuals <em>Static Individuals</em>}</li>
 *   <li>{@link org.xtext.seml.seML.ImportModel#getIndividualOptions <em>Individual Options</em>}</li>
 *   <li>{@link org.xtext.seml.seML.ImportModel#getCharacteristics <em>Characteristics</em>}</li>
 *   <li>{@link org.xtext.seml.seML.ImportModel#getObjectProperties <em>Object Properties</em>}</li>
 * </ul>
 *
 * @see org.xtext.seml.seML.SeMLPackage#getImportModel()
 * @model
 * @generated
 */
public interface ImportModel extends Model
{
  /**
   * Returns the value of the '<em><b>Static Individuals</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.seml.seML.StaticIndividual}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Static Individuals</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Static Individuals</em>' containment reference list.
   * @see org.xtext.seml.seML.SeMLPackage#getImportModel_StaticIndividuals()
   * @model containment="true"
   * @generated
   */
  EList<StaticIndividual> getStaticIndividuals();

  /**
   * Returns the value of the '<em><b>Individual Options</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.seml.seML.FreeIndividual}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Individual Options</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Individual Options</em>' containment reference list.
   * @see org.xtext.seml.seML.SeMLPackage#getImportModel_IndividualOptions()
   * @model containment="true"
   * @generated
   */
  EList<FreeIndividual> getIndividualOptions();

  /**
   * Returns the value of the '<em><b>Characteristics</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.seml.seML.Characteristic}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Characteristics</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Characteristics</em>' containment reference list.
   * @see org.xtext.seml.seML.SeMLPackage#getImportModel_Characteristics()
   * @model containment="true"
   * @generated
   */
  EList<Characteristic> getCharacteristics();

  /**
   * Returns the value of the '<em><b>Object Properties</b></em>' containment reference list.
   * The list contents are of type {@link org.xtext.seml.seML.ObjectProperty}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Object Properties</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Object Properties</em>' containment reference list.
   * @see org.xtext.seml.seML.SeMLPackage#getImportModel_ObjectProperties()
   * @model containment="true"
   * @generated
   */
  EList<ObjectProperty> getObjectProperties();

} // ImportModel
