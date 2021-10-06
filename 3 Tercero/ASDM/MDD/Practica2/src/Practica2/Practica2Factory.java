/**
 */
package Practica2;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see Practica2.Practica2Package
 * @generated
 */
public interface Practica2Factory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Practica2Factory eINSTANCE = Practica2.impl.Practica2FactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Diagrama</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Diagrama</em>'.
	 * @generated
	 */
	Diagrama createDiagrama();

	/**
	 * Returns a new object of class '<em>Arista</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Arista</em>'.
	 * @generated
	 */
	Arista createArista();

	/**
	 * Returns a new object of class '<em>Actividad</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Actividad</em>'.
	 * @generated
	 */
	Actividad createActividad();

	/**
	 * Returns a new object of class '<em>Decision</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Decision</em>'.
	 * @generated
	 */
	Decision createDecision();

	/**
	 * Returns a new object of class '<em>Ramificacion</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Ramificacion</em>'.
	 * @generated
	 */
	Ramificacion createRamificacion();

	/**
	 * Returns a new object of class '<em>Nodo Inicial</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Nodo Inicial</em>'.
	 * @generated
	 */
	NodoInicial createNodoInicial();

	/**
	 * Returns a new object of class '<em>Nodo Final</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Nodo Final</em>'.
	 * @generated
	 */
	NodoFinal createNodoFinal();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	Practica2Package getPractica2Package();

} //Practica2Factory
