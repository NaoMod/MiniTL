/**
 * generated by Xtext 2.27.0
 */
package org.tetrabox.example.minitl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Transformation</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.tetrabox.example.minitl.Transformation#getName <em>Name</em>}</li>
 *   <li>{@link org.tetrabox.example.minitl.Transformation#getInputMetamodel <em>Input Metamodel</em>}</li>
 *   <li>{@link org.tetrabox.example.minitl.Transformation#getOutputMetamodel <em>Output Metamodel</em>}</li>
 *   <li>{@link org.tetrabox.example.minitl.Transformation#getRules <em>Rules</em>}</li>
 * </ul>
 *
 * @see org.tetrabox.example.minitl.MinitlPackage#getTransformation()
 * @model
 * @generated
 */
public interface Transformation extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see org.tetrabox.example.minitl.MinitlPackage#getTransformation_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link org.tetrabox.example.minitl.Transformation#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Input Metamodel</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Input Metamodel</em>' containment reference.
   * @see #setInputMetamodel(Metamodel)
   * @see org.tetrabox.example.minitl.MinitlPackage#getTransformation_InputMetamodel()
   * @model containment="true"
   * @generated
   */
  Metamodel getInputMetamodel();

  /**
   * Sets the value of the '{@link org.tetrabox.example.minitl.Transformation#getInputMetamodel <em>Input Metamodel</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Input Metamodel</em>' containment reference.
   * @see #getInputMetamodel()
   * @generated
   */
  void setInputMetamodel(Metamodel value);

  /**
   * Returns the value of the '<em><b>Output Metamodel</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output Metamodel</em>' containment reference.
   * @see #setOutputMetamodel(Metamodel)
   * @see org.tetrabox.example.minitl.MinitlPackage#getTransformation_OutputMetamodel()
   * @model containment="true"
   * @generated
   */
  Metamodel getOutputMetamodel();

  /**
   * Sets the value of the '{@link org.tetrabox.example.minitl.Transformation#getOutputMetamodel <em>Output Metamodel</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output Metamodel</em>' containment reference.
   * @see #getOutputMetamodel()
   * @generated
   */
  void setOutputMetamodel(Metamodel value);

  /**
   * Returns the value of the '<em><b>Rules</b></em>' containment reference list.
   * The list contents are of type {@link org.tetrabox.example.minitl.Rule}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Rules</em>' containment reference list.
   * @see org.tetrabox.example.minitl.MinitlPackage#getTransformation_Rules()
   * @model containment="true"
   * @generated
   */
  EList<Rule> getRules();

} // Transformation