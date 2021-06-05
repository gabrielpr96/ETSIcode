/**
 */
package Clase_0106.util;

import Clase_0106.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see Clase_0106.Clase_0106Package
 * @generated
 */
public class Clase_0106AdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Clase_0106Package modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clase_0106AdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Clase_0106Package.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected Clase_0106Switch<Adapter> modelSwitch =
		new Clase_0106Switch<Adapter>() {
			@Override
			public Adapter caseGrafo(Grafo object) {
				return createGrafoAdapter();
			}
			@Override
			public Adapter caseNodo(Nodo object) {
				return createNodoAdapter();
			}
			@Override
			public Adapter caseSubnodo1(Subnodo1 object) {
				return createSubnodo1Adapter();
			}
			@Override
			public Adapter caseSubnodo2(Subnodo2 object) {
				return createSubnodo2Adapter();
			}
			@Override
			public Adapter caseConexion(Conexion object) {
				return createConexionAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link Clase_0106.Grafo <em>Grafo</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Clase_0106.Grafo
	 * @generated
	 */
	public Adapter createGrafoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Clase_0106.Nodo <em>Nodo</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Clase_0106.Nodo
	 * @generated
	 */
	public Adapter createNodoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Clase_0106.Subnodo1 <em>Subnodo1</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Clase_0106.Subnodo1
	 * @generated
	 */
	public Adapter createSubnodo1Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Clase_0106.Subnodo2 <em>Subnodo2</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Clase_0106.Subnodo2
	 * @generated
	 */
	public Adapter createSubnodo2Adapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Clase_0106.Conexion <em>Conexion</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Clase_0106.Conexion
	 * @generated
	 */
	public Adapter createConexionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //Clase_0106AdapterFactory
