/**
 */
package Clase_0106;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Conexion</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Clase_0106.Conexion#getNombre <em>Nombre</em>}</li>
 *   <li>{@link Clase_0106.Conexion#getOrigen <em>Origen</em>}</li>
 *   <li>{@link Clase_0106.Conexion#getDestino <em>Destino</em>}</li>
 * </ul>
 *
 * @see Clase_0106.Clase_0106Package#getConexion()
 * @model annotation="gmf.link label='nombre' source='origen' target='destino' target.decoration='arrow' tool.description='Agrega una arista entre nodos'"
 * @generated
 */
public interface Conexion extends EObject {
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
	 * @see Clase_0106.Clase_0106Package#getConexion_Nombre()
	 * @model
	 * @generated
	 */
	String getNombre();

	/**
	 * Sets the value of the '{@link Clase_0106.Conexion#getNombre <em>Nombre</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nombre</em>' attribute.
	 * @see #getNombre()
	 * @generated
	 */
	void setNombre(String value);

	/**
	 * Returns the value of the '<em><b>Origen</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link Clase_0106.Nodo#getSaliente <em>Saliente</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Origen</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origen</em>' reference.
	 * @see #setOrigen(Nodo)
	 * @see Clase_0106.Clase_0106Package#getConexion_Origen()
	 * @see Clase_0106.Nodo#getSaliente
	 * @model opposite="saliente" required="true"
	 * @generated
	 */
	Nodo getOrigen();

	/**
	 * Sets the value of the '{@link Clase_0106.Conexion#getOrigen <em>Origen</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origen</em>' reference.
	 * @see #getOrigen()
	 * @generated
	 */
	void setOrigen(Nodo value);

	/**
	 * Returns the value of the '<em><b>Destino</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link Clase_0106.Nodo#getEntrante <em>Entrante</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Destino</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destino</em>' reference.
	 * @see #setDestino(Nodo)
	 * @see Clase_0106.Clase_0106Package#getConexion_Destino()
	 * @see Clase_0106.Nodo#getEntrante
	 * @model opposite="entrante" required="true"
	 * @generated
	 */
	Nodo getDestino();

	/**
	 * Sets the value of the '{@link Clase_0106.Conexion#getDestino <em>Destino</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destino</em>' reference.
	 * @see #getDestino()
	 * @generated
	 */
	void setDestino(Nodo value);

} // Conexion
