/**
 */
package Practica2.util;

import Practica2.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see Practica2.Practica2Package
 * @generated
 */
public class Practica2AdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static Practica2Package modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Practica2AdapterFactory() {
		if (modelPackage == null) {
			modelPackage = Practica2Package.eINSTANCE;
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
	protected Practica2Switch<Adapter> modelSwitch =
		new Practica2Switch<Adapter>() {
			@Override
			public Adapter caseDiagrama(Diagrama object) {
				return createDiagramaAdapter();
			}
			@Override
			public Adapter caseNodo(Nodo object) {
				return createNodoAdapter();
			}
			@Override
			public Adapter caseArista(Arista object) {
				return createAristaAdapter();
			}
			@Override
			public Adapter caseActividad(Actividad object) {
				return createActividadAdapter();
			}
			@Override
			public Adapter caseDecision(Decision object) {
				return createDecisionAdapter();
			}
			@Override
			public Adapter caseRamificacion(Ramificacion object) {
				return createRamificacionAdapter();
			}
			@Override
			public Adapter caseNodoInicial(NodoInicial object) {
				return createNodoInicialAdapter();
			}
			@Override
			public Adapter caseNodoFinal(NodoFinal object) {
				return createNodoFinalAdapter();
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
	 * Creates a new adapter for an object of class '{@link Practica2.Diagrama <em>Diagrama</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Practica2.Diagrama
	 * @generated
	 */
	public Adapter createDiagramaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Practica2.Nodo <em>Nodo</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Practica2.Nodo
	 * @generated
	 */
	public Adapter createNodoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Practica2.Arista <em>Arista</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Practica2.Arista
	 * @generated
	 */
	public Adapter createAristaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Practica2.Actividad <em>Actividad</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Practica2.Actividad
	 * @generated
	 */
	public Adapter createActividadAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Practica2.Decision <em>Decision</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Practica2.Decision
	 * @generated
	 */
	public Adapter createDecisionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Practica2.Ramificacion <em>Ramificacion</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Practica2.Ramificacion
	 * @generated
	 */
	public Adapter createRamificacionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Practica2.NodoInicial <em>Nodo Inicial</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Practica2.NodoInicial
	 * @generated
	 */
	public Adapter createNodoInicialAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Practica2.NodoFinal <em>Nodo Final</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Practica2.NodoFinal
	 * @generated
	 */
	public Adapter createNodoFinalAdapter() {
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

} //Practica2AdapterFactory
