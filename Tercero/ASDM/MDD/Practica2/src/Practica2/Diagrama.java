/**
 */
package Practica2;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagrama</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Practica2.Diagrama#getNodos <em>Nodos</em>}</li>
 *   <li>{@link Practica2.Diagrama#getAristas <em>Aristas</em>}</li>
 * </ul>
 *
 * @see Practica2.Practica2Package#getDiagrama()
 * @model annotation="gmf.diagram foo='bar'"
 * @generated
 */
public interface Diagrama extends EObject {
	/**
	 * Returns the value of the '<em><b>Nodos</b></em>' containment reference list.
	 * The list contents are of type {@link Practica2.Nodo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodos</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodos</em>' containment reference list.
	 * @see Practica2.Practica2Package#getDiagrama_Nodos()
	 * @model containment="true"
	 * @generated
	 */
	EList<Nodo> getNodos();

	/**
	 * Returns the value of the '<em><b>Aristas</b></em>' containment reference list.
	 * The list contents are of type {@link Practica2.Arista}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Aristas</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Aristas</em>' containment reference list.
	 * @see Practica2.Practica2Package#getDiagrama_Aristas()
	 * @model containment="true"
	 * @generated
	 */
	EList<Arista> getAristas();

} // Diagrama
