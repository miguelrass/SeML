/**
 * generated by Xtext 2.11.0
 */
package org.xtext.seml.seML.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.seml.seML.Assignment;
import org.xtext.seml.seML.Individual;
import org.xtext.seml.seML.SeMLPackage;
import org.xtext.seml.seML.Value;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Assignment</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.seml.seML.impl.AssignmentImpl#getInd <em>Ind</em>}</li>
 *   <li>{@link org.xtext.seml.seML.impl.AssignmentImpl#getLiteral <em>Literal</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AssignmentImpl extends SentenceImpl implements Assignment
{
  /**
   * The cached value of the '{@link #getInd() <em>Ind</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInd()
   * @generated
   * @ordered
   */
  protected Individual ind;

  /**
   * The cached value of the '{@link #getLiteral() <em>Literal</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getLiteral()
   * @generated
   * @ordered
   */
  protected Value literal;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AssignmentImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return SeMLPackage.Literals.ASSIGNMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Individual getInd()
  {
    if (ind != null && ind.eIsProxy())
    {
      InternalEObject oldInd = (InternalEObject)ind;
      ind = (Individual)eResolveProxy(oldInd);
      if (ind != oldInd)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SeMLPackage.ASSIGNMENT__IND, oldInd, ind));
      }
    }
    return ind;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Individual basicGetInd()
  {
    return ind;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInd(Individual newInd)
  {
    Individual oldInd = ind;
    ind = newInd;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SeMLPackage.ASSIGNMENT__IND, oldInd, ind));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Value getLiteral()
  {
    return literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLiteral(Value newLiteral, NotificationChain msgs)
  {
    Value oldLiteral = literal;
    literal = newLiteral;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, SeMLPackage.ASSIGNMENT__LITERAL, oldLiteral, newLiteral);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLiteral(Value newLiteral)
  {
    if (newLiteral != literal)
    {
      NotificationChain msgs = null;
      if (literal != null)
        msgs = ((InternalEObject)literal).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - SeMLPackage.ASSIGNMENT__LITERAL, null, msgs);
      if (newLiteral != null)
        msgs = ((InternalEObject)newLiteral).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - SeMLPackage.ASSIGNMENT__LITERAL, null, msgs);
      msgs = basicSetLiteral(newLiteral, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SeMLPackage.ASSIGNMENT__LITERAL, newLiteral, newLiteral));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case SeMLPackage.ASSIGNMENT__LITERAL:
        return basicSetLiteral(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case SeMLPackage.ASSIGNMENT__IND:
        if (resolve) return getInd();
        return basicGetInd();
      case SeMLPackage.ASSIGNMENT__LITERAL:
        return getLiteral();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case SeMLPackage.ASSIGNMENT__IND:
        setInd((Individual)newValue);
        return;
      case SeMLPackage.ASSIGNMENT__LITERAL:
        setLiteral((Value)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case SeMLPackage.ASSIGNMENT__IND:
        setInd((Individual)null);
        return;
      case SeMLPackage.ASSIGNMENT__LITERAL:
        setLiteral((Value)null);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case SeMLPackage.ASSIGNMENT__IND:
        return ind != null;
      case SeMLPackage.ASSIGNMENT__LITERAL:
        return literal != null;
    }
    return super.eIsSet(featureID);
  }

} //AssignmentImpl