/**
 */
package Encore;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Union Cancion Secuencia</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Encore.UnionCancionSecuencia#getCancion <em>Cancion</em>}</li>
 *   <li>{@link Encore.UnionCancionSecuencia#getSecuencia <em>Secuencia</em>}</li>
 * </ul>
 *
 * @see Encore.EncorePackage#getUnionCancionSecuencia()
 * @model annotation="gmf.link label.placement='none' source='cancion' target='secuencia' target.decoration='arrow' tool.description='Conecta una secuencia a una cancion'"
 * @generated
 */
public interface UnionCancionSecuencia extends EObject {
	/**
	 * Returns the value of the '<em><b>Cancion</b></em>' reference list.
	 * The list contents are of type {@link Encore.Cancion}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Cancion</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Cancion</em>' reference list.
	 * @see Encore.EncorePackage#getUnionCancionSecuencia_Cancion()
	 * @model
	 * @generated
	 */
	EList<Cancion> getCancion();

	/**
	 * Returns the value of the '<em><b>Secuencia</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Secuencia</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secuencia</em>' reference.
	 * @see #setSecuencia(Secuencia)
	 * @see Encore.EncorePackage#getUnionCancionSecuencia_Secuencia()
	 * @model required="true"
	 * @generated
	 */
	Secuencia getSecuencia();

	/**
	 * Sets the value of the '{@link Encore.UnionCancionSecuencia#getSecuencia <em>Secuencia</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Secuencia</em>' reference.
	 * @see #getSecuencia()
	 * @generated
	 */
	void setSecuencia(Secuencia value);

} // UnionCancionSecuencia
