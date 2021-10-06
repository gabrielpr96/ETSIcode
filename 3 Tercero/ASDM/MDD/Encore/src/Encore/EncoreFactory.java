/**
 */
package Encore;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see Encore.EncorePackage
 * @generated
 */
public interface EncoreFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EncoreFactory eINSTANCE = Encore.impl.EncoreFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Concierto</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Concierto</em>'.
	 * @generated
	 */
	Concierto createConcierto();

	/**
	 * Returns a new object of class '<em>Cancion</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Cancion</em>'.
	 * @generated
	 */
	Cancion createCancion();

	/**
	 * Returns a new object of class '<em>Secuencia</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Secuencia</em>'.
	 * @generated
	 */
	Secuencia createSecuencia();

	/**
	 * Returns a new object of class '<em>Foco</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Foco</em>'.
	 * @generated
	 */
	Foco createFoco();

	/**
	 * Returns a new object of class '<em>Strobo</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Strobo</em>'.
	 * @generated
	 */
	Strobo createStrobo();

	/**
	 * Returns a new object of class '<em>Union Cancion Secuencia</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Union Cancion Secuencia</em>'.
	 * @generated
	 */
	UnionCancionSecuencia createUnionCancionSecuencia();

	/**
	 * Returns a new object of class '<em>Union Secuencia Luz</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Union Secuencia Luz</em>'.
	 * @generated
	 */
	UnionSecuenciaLuz createUnionSecuenciaLuz();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	EncorePackage getEncorePackage();

} //EncoreFactory
