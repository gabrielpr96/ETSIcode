/**
 */
package Encore;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Cancion</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Encore.Cancion#getTitulo <em>Titulo</em>}</li>
 *   <li>{@link Encore.Cancion#getDuracion <em>Duracion</em>}</li>
 * </ul>
 *
 * @see Encore.EncorePackage#getCancion()
 * @model annotation="gmf.node label='Titulo' label.placement='external' figure='svg' svg.uri='platform:/plugin/Encore/vectores/nota.svg' tool.name='Cancion' tool.description='Agrega una cancion'"
 * @generated
 */
public interface Cancion extends EObject {
	/**
	 * Returns the value of the '<em><b>Titulo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Titulo</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Titulo</em>' attribute.
	 * @see #setTitulo(String)
	 * @see Encore.EncorePackage#getCancion_Titulo()
	 * @model
	 * @generated
	 */
	String getTitulo();

	/**
	 * Sets the value of the '{@link Encore.Cancion#getTitulo <em>Titulo</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Titulo</em>' attribute.
	 * @see #getTitulo()
	 * @generated
	 */
	void setTitulo(String value);

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
	 * @see Encore.EncorePackage#getCancion_Duracion()
	 * @model
	 * @generated
	 */
	int getDuracion();

	/**
	 * Sets the value of the '{@link Encore.Cancion#getDuracion <em>Duracion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Duracion</em>' attribute.
	 * @see #getDuracion()
	 * @generated
	 */
	void setDuracion(int value);

} // Cancion
