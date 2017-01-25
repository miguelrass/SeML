/**
 * generated by Xtext 2.10.0
 */
package org.xtext.seml.seML.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.xtext.seml.seML.AnyIndividual;
import org.xtext.seml.seML.ObjectProperty;
import org.xtext.seml.seML.Relation;
import org.xtext.seml.seML.SeMLPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Relation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.xtext.seml.seML.impl.RelationImpl#getInstance1 <em>Instance1</em>}</li>
 *   <li>{@link org.xtext.seml.seML.impl.RelationImpl#getObj <em>Obj</em>}</li>
 *   <li>{@link org.xtext.seml.seML.impl.RelationImpl#getInstance2 <em>Instance2</em>}</li>
 * </ul>
 *
 * @generated
 */
public class RelationImpl extends SentenceImpl implements Relation
{
  /**
   * The cached value of the '{@link #getInstance1() <em>Instance1</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInstance1()
   * @generated
   * @ordered
   */
  protected AnyIndividual instance1;

  /**
   * The cached value of the '{@link #getObj() <em>Obj</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getObj()
   * @generated
   * @ordered
   */
  protected ObjectProperty obj;

  /**
   * The cached value of the '{@link #getInstance2() <em>Instance2</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInstance2()
   * @generated
   * @ordered
   */
  protected AnyIndividual instance2;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RelationImpl()
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
    return SeMLPackage.Literals.RELATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnyIndividual getInstance1()
  {
    if (instance1 != null && instance1.eIsProxy())
    {
      InternalEObject oldInstance1 = (InternalEObject)instance1;
      instance1 = (AnyIndividual)eResolveProxy(oldInstance1);
      if (instance1 != oldInstance1)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SeMLPackage.RELATION__INSTANCE1, oldInstance1, instance1));
      }
    }
    return instance1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnyIndividual basicGetInstance1()
  {
    return instance1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInstance1(AnyIndividual newInstance1)
  {
    AnyIndividual oldInstance1 = instance1;
    instance1 = newInstance1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SeMLPackage.RELATION__INSTANCE1, oldInstance1, instance1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ObjectProperty getObj()
  {
    if (obj != null && obj.eIsProxy())
    {
      InternalEObject oldObj = (InternalEObject)obj;
      obj = (ObjectProperty)eResolveProxy(oldObj);
      if (obj != oldObj)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SeMLPackage.RELATION__OBJ, oldObj, obj));
      }
    }
    return obj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ObjectProperty basicGetObj()
  {
    return obj;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setObj(ObjectProperty newObj)
  {
    ObjectProperty oldObj = obj;
    obj = newObj;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SeMLPackage.RELATION__OBJ, oldObj, obj));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnyIndividual getInstance2()
  {
    if (instance2 != null && instance2.eIsProxy())
    {
      InternalEObject oldInstance2 = (InternalEObject)instance2;
      instance2 = (AnyIndividual)eResolveProxy(oldInstance2);
      if (instance2 != oldInstance2)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, SeMLPackage.RELATION__INSTANCE2, oldInstance2, instance2));
      }
    }
    return instance2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AnyIndividual basicGetInstance2()
  {
    return instance2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInstance2(AnyIndividual newInstance2)
  {
    AnyIndividual oldInstance2 = instance2;
    instance2 = newInstance2;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, SeMLPackage.RELATION__INSTANCE2, oldInstance2, instance2));
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
      case SeMLPackage.RELATION__INSTANCE1:
        if (resolve) return getInstance1();
        return basicGetInstance1();
      case SeMLPackage.RELATION__OBJ:
        if (resolve) return getObj();
        return basicGetObj();
      case SeMLPackage.RELATION__INSTANCE2:
        if (resolve) return getInstance2();
        return basicGetInstance2();
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
      case SeMLPackage.RELATION__INSTANCE1:
        setInstance1((AnyIndividual)newValue);
        return;
      case SeMLPackage.RELATION__OBJ:
        setObj((ObjectProperty)newValue);
        return;
      case SeMLPackage.RELATION__INSTANCE2:
        setInstance2((AnyIndividual)newValue);
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
      case SeMLPackage.RELATION__INSTANCE1:
        setInstance1((AnyIndividual)null);
        return;
      case SeMLPackage.RELATION__OBJ:
        setObj((ObjectProperty)null);
        return;
      case SeMLPackage.RELATION__INSTANCE2:
        setInstance2((AnyIndividual)null);
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
      case SeMLPackage.RELATION__INSTANCE1:
        return instance1 != null;
      case SeMLPackage.RELATION__OBJ:
        return obj != null;
      case SeMLPackage.RELATION__INSTANCE2:
        return instance2 != null;
    }
    return super.eIsSet(featureID);
  }

} //RelationImpl