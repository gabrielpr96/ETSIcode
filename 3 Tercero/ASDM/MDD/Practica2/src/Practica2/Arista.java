/**
 */
package Practica2;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Arista</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Practica2.Arista#getNombre <em>Nombre</em>}</li>
 *   <li>{@link Practica2.Arista#getOrigen <em>Origen</em>}</li>
 *   <li>{@link Practica2.Arista#getDestino <em>Destino</em>}</li>
 * </ul>
 *
 * @see Practica2.Practica2Package#getArista()
 * @model annotation="gmf.link label='nombre' source='origen' target='destino' target.decoration='arrow' tool.description='Agrega una arista entre dos nodos'"
 * @generated
 */
public interface Arista extends EObject {
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
	 * @see Practica2.Practica2Package#getArista_Nombre()
	 * @model
	 * @generated
	 */
	String getNombre();

	/**
	 * Sets the value of the '{@link Practica2.Arista#getNombre <em>Nombre</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Nombre</em>' attribute.
	 * @see #getNombre()
	 * @generated
	 */
	void setNombre(String value);

	/**
	 * Returns the value of the '<em><b>Origen</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link Practica2.Nodo#getSalientes <em>Salientes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Origen</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Origen</em>' reference.
	 * @see #setOrigen(Nodo)
	 * @see Practica2.Practica2Package#getArista_Origen()
	 * @see Practica2.Nodo#getSalientes
	 * @model opposite="salientes" required="true"
	 * @generated
	 */
	Nodo getOrigen();

	/**
	 * Sets the value of the '{@link Practica2.Arista#getOrigen <em>Origen</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Origen</em>' reference.
	 * @see #getOrigen()
	 * @generated
	 */
	void setOrigen(Nodo value);

	/**
	 * Returns the value of the '<em><b>Destino</b></em>' reference.
	 * It is bidirectional and its opposite is '{@link Practica2.Nodo#getEntrantes <em>Entrantes</em>}'.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Destino</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destino</em>' reference.
	 * @see #setDestino(Nodo)
	 * @see Practica2.Practica2Package#getArista_Destino()
	 * @see Practica2.Nodo#getEntrantes
	 * @model opposite="entrantes" required="true"
	 * @generated
	 */
	Nodo getDestino();

	/**
	 * Sets the value of the '{@link Practica2.Arista#getDestino <em>Destino</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destino</em>' reference.
	 * @see #getDestino()
	 * @generated
	 */
	void setDestino(Nodo value);

} // Arista
