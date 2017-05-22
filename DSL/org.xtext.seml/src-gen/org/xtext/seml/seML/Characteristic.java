/**
 * generated by Xtext 2.11.0
 */
package org.xtext.seml.seML;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Characteristic</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.seml.seML.Characteristic#getName <em>Name</em>}</li>
 *   <li>{@link org.xtext.seml.seML.Characteristic#getIri <em>Iri</em>}</li>
 * </ul>
 *
 * @see org.xtext.seml.seML.SeMLPackage#getCharacteristic()
 * @model
 * @generated
 */
public interface Characteristic extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.xtext.seml.seML.SeMLPackage#getCharacteristic_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.xtext.seml.seML.Characteristic#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Iri</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Iri</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Iri</em>' attribute.
   * @see #setIri(String)
   * @see org.xtext.seml.seML.SeMLPackage#getCharacteristic_Iri()
   * @model
   * @generated
   */
  String getIri();

  /**
   * Sets the value of the '{@link org.xtext.seml.seML.Characteristic#getIri <em>Iri</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Iri</em>' attribute.
   * @see #getIri()
   * @generated
   */
  void setIri(String value);

} // Characteristic
