/**
 */
package Encore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Foco</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Encore.Foco#getPotencia <em>Potencia</em>}</li>
 * </ul>
 *
 * @see Encore.EncorePackage#getFoco()
 * @model annotation="gmf.node label='ID' label.placement='external' figure='svg' svg.uri='platform:/plugin/Encore/vectores/foco.svg' tool.name='Foco' tool.description='Agrega una luz de tipo foco'"
 * @generated
 */
public interface Foco extends Luz {
	/**
	 * Returns the value of the '<em><b>Potencia</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Potencia</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Potencia</em>' attribute.
	 * @see #setPotencia(float)
	 * @see Encore.EncorePackage#getFoco_Potencia()
	 * @model
	 * @generated
	 */
	float getPotencia();

	/**
	 * Sets the value of the '{@link Encore.Foco#getPotencia <em>Potencia</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Potencia</em>' attribute.
	 * @see #getPotencia()
	 * @generated
	 */
	void setPotencia(float value);

} // Foco
