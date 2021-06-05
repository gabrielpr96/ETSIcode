/**
 */
package Encore;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Union Secuencia Luz</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Encore.UnionSecuenciaLuz#getSecuencia <em>Secuencia</em>}</li>
 *   <li>{@link Encore.UnionSecuenciaLuz#getLuz <em>Luz</em>}</li>
 * </ul>
 *
 * @see Encore.EncorePackage#getUnionSecuenciaLuz()
 * @model annotation="gmf.link label.placement='none' source='secuencia' target='luz' target.decoration='arrow' tool.description='Conecta una luz a una secuencia'"
 * @generated
 */
public interface UnionSecuenciaLuz extends EObject {
	/**
	 * Returns the value of the '<em><b>Secuencia</b></em>' reference list.
	 * The list contents are of type {@link Encore.Secuencia}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Secuencia</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secuencia</em>' reference list.
	 * @see Encore.EncorePackage#getUnionSecuenciaLuz_Secuencia()
	 * @model required="true"
	 * @generated
	 */
	EList<Secuencia> getSecuencia();

	/**
	 * Returns the value of the '<em><b>Luz</b></em>' reference list.
	 * The list contents are of type {@link Encore.Luz}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Luz</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Luz</em>' reference list.
	 * @see Encore.EncorePackage#getUnionSecuenciaLuz_Luz()
	 * @model
	 * @generated
	 */
	EList<Luz> getLuz();

} // UnionSecuenciaLuz
