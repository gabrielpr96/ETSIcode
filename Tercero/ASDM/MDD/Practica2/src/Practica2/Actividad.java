/**
 */
package Practica2;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Actividad</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Practica2.Actividad#getNombre <em>Nombre</em>}</li>
 *   <li>{@link Practica2.Actividad#getSubdiag <em>Subdiag</em>}</li>
 * </ul>
 *
 * @see Practica2.Practica2Package#getActividad()
 * @model annotation="gmf.node label='nombre' label.icon='false' tool.description='Agrega una actividad, que puede contener subactividades'"
 * @generated
 */
public interface Actividad extends Nodo {
	/**
	 * Returns the value of the '<em><b>Nombre</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nombre</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nombre</em>' attribute.
	 * @see #setNombre(String)
	 * @see Practica2.Practica2Package#getActividad_Nombre()
	 * @model
	 * @generated
	 */
	String getNombre();

	/**
	 * Sets the value of the '{@link Practica2.Actividad#getNombre <em>Nombre</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nombre</em>' attribute.
	 * @see #getNombre()
	 * @generated
	 */
	void setNombre(String value);

	/**
	 * Returns the value of the '<em><b>Subdiag</b></em>' containment reference list.
	 * The list contents are of type {@link Practica2.Nodo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subdiag</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subdiag</em>' containment reference list.
	 * @see Practica2.Practica2Package#getActividad_Subdiag()
	 * @model containment="true"
	 *        annotation="gmf.compartment foo='bar'"
	 * @generated
	 */
	EList<Nodo> getSubdiag();

} // Actividad
