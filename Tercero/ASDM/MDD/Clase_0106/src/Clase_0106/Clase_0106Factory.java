/**
 */
package Clase_0106;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see Clase_0106.Clase_0106Package
 * @generated
 */
public interface Clase_0106Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Clase_0106Factory eINSTANCE = Clase_0106.impl.Clase_0106FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Grafo</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Grafo</em>'.
	 * @generated
	 */
	Grafo createGrafo();

	/**
	 * Returns a new object of class '<em>Subnodo1</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Subnodo1</em>'.
	 * @generated
	 */
	Subnodo1 createSubnodo1();

	/**
	 * Returns a new object of class '<em>Subnodo2</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Subnodo2</em>'.
	 * @generated
	 */
	Subnodo2 createSubnodo2();

	/**
	 * Returns a new object of class '<em>Conexion</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Conexion</em>'.
	 * @generated
	 */
	Conexion createConexion();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Clase_0106Package getClase_0106Package();

} //Clase_0106Factory
