/**
 */
package Clase_0106;

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
 *   <li>{@link Clase_0106.Nodo#getEntrante <em>Entrante</em>}</li>
 *   <li>{@link Clase_0106.Nodo#getSaliente <em>Saliente</em>}</li>
 * </ul>
 *
 * @see Clase_0106.Clase_0106Package#getNodo()
 * @model abstract="true"
 * @generated
 */
public interface Nodo extends EObject {
	/**
	 * Returns the value of the '<em><b>Entrante</b></em>' reference list.
	 * The list contents are of type {@link Clase_0106.Conexion}.
	 * It is bidirectional and its opposite is '{@link Clase_0106.Conexion#getDestino <em>Destino</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Entrante</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Entrante</em>' reference list.
	 * @see Clase_0106.Clase_0106Package#getNodo_Entrante()
	 * @see Clase_0106.Conexion#getDestino
	 * @model opposite="destino"
	 * @generated
	 */
	EList<Conexion> getEntrante();

	/**
	 * Returns the value of the '<em><b>Saliente</b></em>' reference list.
	 * The list contents are of type {@link Clase_0106.Conexion}.
	 * It is bidirectional and its opposite is '{@link Clase_0106.Conexion#getOrigen <em>Origen</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Saliente</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Saliente</em>' reference list.
	 * @see Clase_0106.Clase_0106Package#getNodo_Saliente()
	 * @see Clase_0106.Conexion#getOrigen
	 * @model opposite="origen"
	 * @generated
	 */
	EList<Conexion> getSaliente();

} // Nodo
