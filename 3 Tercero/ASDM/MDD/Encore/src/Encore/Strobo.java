/**
 */
package Encore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Strobo</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Encore.Strobo#getFrecuencia <em>Frecuencia</em>}</li>
 * </ul>
 *
 * @see Encore.EncorePackage#getStrobo()
 * @model annotation="gmf.node label='ID' label.placement='external' figure='svg' svg.uri='platform:/plugin/Encore/vectores/estrobo.svg' tool.name='Estrobo' tool.description='Agrega una luz de tipo estrobo'"
 * @generated
 */
public interface Strobo extends Luz {
	/**
	 * Returns the value of the '<em><b>Frecuencia</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Frecuencia</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Frecuencia</em>' attribute.
	 * @see #setFrecuencia(float)
	 * @see Encore.EncorePackage#getStrobo_Frecuencia()
	 * @model
	 * @generated
	 */
	float getFrecuencia();

	/**
	 * Sets the value of the '{@link Encore.Strobo#getFrecuencia <em>Frecuencia</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Frecuencia</em>' attribute.
	 * @see #getFrecuencia()
	 * @generated
	 */
	void setFrecuencia(float value);

} // Strobo
