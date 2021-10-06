/**
 */
package Clase_0106;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Grafo</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Clase_0106.Grafo#getConexiones <em>Conexiones</em>}</li>
 *   <li>{@link Clase_0106.Grafo#getNodos <em>Nodos</em>}</li>
 * </ul>
 *
 * @see Clase_0106.Clase_0106Package#getGrafo()
 * @model annotation="gmf.diagram foo='bar'"
 * @generated
 */
public interface Grafo extends EObject {
	/**
	 * Returns the value of the '<em><b>Conexiones</b></em>' containment reference list.
	 * The list contents are of type {@link Clase_0106.Conexion}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Conexiones</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Conexiones</em>' containment reference list.
	 * @see Clase_0106.Clase_0106Package#getGrafo_Conexiones()
	 * @model containment="true"
	 * @generated
	 */
	EList<Conexion> getConexiones();

	/**
	 * Returns the value of the '<em><b>Nodos</b></em>' containment reference list.
	 * The list contents are of type {@link Clase_0106.Nodo}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodos</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodos</em>' containment reference list.
	 * @see Clase_0106.Clase_0106Package#getGrafo_Nodos()
	 * @model containment="true"
	 * @generated
	 */
	EList<Nodo> getNodos();

} // Grafo
