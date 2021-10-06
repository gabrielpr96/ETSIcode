/**
 */
package Practica2;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Nodo</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Practica2.Nodo#getSalientes <em>Salientes</em>}</li>
 *   <li>{@link Practica2.Nodo#getEntrantes <em>Entrantes</em>}</li>
 * </ul>
 *
 * @see Practica2.Practica2Package#getNodo()
 * @model abstract="true"
 * @generated
 */
public interface Nodo extends EObject {
	/**
	 * Returns the value of the '<em><b>Salientes</b></em>' reference list.
	 * The list contents are of type {@link Practica2.Arista}.
	 * It is bidirectional and its opposite is '{@link Practica2.Arista#getOrigen <em>Origen</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Salientes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Salientes</em>' reference list.
	 * @see Practica2.Practica2Package#getNodo_Salientes()
	 * @see Practica2.Arista#getOrigen
	 * @model opposite="origen"
	 * @generated
	 */
	EList<Arista> getSalientes();

	/**
	 * Returns the value of the '<em><b>Entrantes</b></em>' reference list.
	 * The list contents are of type {@link Practica2.Arista}.
	 * It is bidirectional and its opposite is '{@link Practica2.Arista#getDestino <em>Destino</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entrantes</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entrantes</em>' reference list.
	 * @see Practica2.Practica2Package#getNodo_Entrantes()
	 * @see Practica2.Arista#getDestino
	 * @model opposite="destino"
	 * @generated
	 */
	EList<Arista> getEntrantes();

} // Nodo
