/**
 */
package Encore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Secuencia</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Encore.Secuencia#getNumero <em>Numero</em>}</li>
 *   <li>{@link Encore.Secuencia#getModificador <em>Modificador</em>}</li>
 *   <li>{@link Encore.Secuencia#getAnguloZ <em>Angulo Z</em>}</li>
 *   <li>{@link Encore.Secuencia#getAnguloY <em>Angulo Y</em>}</li>
 *   <li>{@link Encore.Secuencia#getInicio <em>Inicio</em>}</li>
 *   <li>{@link Encore.Secuencia#getDuracion <em>Duracion</em>}</li>
 * </ul>
 *
 * @see Encore.EncorePackage#getSecuencia()
 * @model annotation="gmf.node label='Numero' label.placement='external' figure='svg' svg.uri='platform:/plugin/Encore/vectores/engranaje.svg' tool.name='Secuencia' tool.description='Agrega una sencuencia que cambia los valores de una luz durante un tiempo en una cancion'"
 * @generated
 */
public interface Secuencia extends EObject {
	/**
	 * Returns the value of the '<em><b>Numero</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Numero</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Numero</em>' attribute.
	 * @see #setNumero(int)
	 * @see Encore.EncorePackage#getSecuencia_Numero()
	 * @model
	 * @generated
	 */
	int getNumero();

	/**
	 * Sets the value of the '{@link Encore.Secuencia#getNumero <em>Numero</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Numero</em>' attribute.
	 * @see #getNumero()
	 * @generated
	 */
	void setNumero(int value);

	/**
	 * Returns the value of the '<em><b>Modificador</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Modificador</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Modificador</em>' attribute.
	 * @see #setModificador(float)
	 * @see Encore.EncorePackage#getSecuencia_Modificador()
	 * @model
	 * @generated
	 */
	float getModificador();

	/**
	 * Sets the value of the '{@link Encore.Secuencia#getModificador <em>Modificador</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Modificador</em>' attribute.
	 * @see #getModificador()
	 * @generated
	 */
	void setModificador(float value);

	/**
	 * Returns the value of the '<em><b>Angulo Z</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Angulo Z</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Angulo Z</em>' attribute.
	 * @see #setAnguloZ(float)
	 * @see Encore.EncorePackage#getSecuencia_AnguloZ()
	 * @model
	 * @generated
	 */
	float getAnguloZ();

	/**
	 * Sets the value of the '{@link Encore.Secuencia#getAnguloZ <em>Angulo Z</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Angulo Z</em>' attribute.
	 * @see #getAnguloZ()
	 * @generated
	 */
	void setAnguloZ(float value);

	/**
	 * Returns the value of the '<em><b>Angulo Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Angulo Y</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Angulo Y</em>' attribute.
	 * @see #setAnguloY(float)
	 * @see Encore.EncorePackage#getSecuencia_AnguloY()
	 * @model
	 * @generated
	 */
	float getAnguloY();

	/**
	 * Sets the value of the '{@link Encore.Secuencia#getAnguloY <em>Angulo Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Angulo Y</em>' attribute.
	 * @see #getAnguloY()
	 * @generated
	 */
	void setAnguloY(float value);

	/**
	 * Returns the value of the '<em><b>Inicio</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Inicio</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Inicio</em>' attribute.
	 * @see #setInicio(int)
	 * @see Encore.EncorePackage#getSecuencia_Inicio()
	 * @model
	 * @generated
	 */
	int getInicio();

	/**
	 * Sets the value of the '{@link Encore.Secuencia#getInicio <em>Inicio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Inicio</em>' attribute.
	 * @see #getInicio()
	 * @generated
	 */
	void setInicio(int value);

	/**
	 * Returns the value of the '<em><b>Duracion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Duracion</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Duracion</em>' attribute.
	 * @see #setDuracion(int)
	 * @see Encore.EncorePackage#getSecuencia_Duracion()
	 * @model
	 * @generated
	 */
	int getDuracion();

	/**
	 * Sets the value of the '{@link Encore.Secuencia#getDuracion <em>Duracion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duracion</em>' attribute.
	 * @see #getDuracion()
	 * @generated
	 */
	void setDuracion(int value);

} // Secuencia
