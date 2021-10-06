/**
 */
package Encore.util;

import Encore.*;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see Encore.EncorePackage
 * @generated
 */
public class EncoreAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EncorePackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EncoreAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = EncorePackage.eINSTANCE;
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
	protected EncoreSwitch<Adapter> modelSwitch =
		new EncoreSwitch<Adapter>() {
			@Override
			public Adapter caseConcierto(Concierto object) {
				return createConciertoAdapter();
			}
			@Override
			public Adapter caseCancion(Cancion object) {
				return createCancionAdapter();
			}
			@Override
			public Adapter caseSecuencia(Secuencia object) {
				return createSecuenciaAdapter();
			}
			@Override
			public Adapter caseLuz(Luz object) {
				return createLuzAdapter();
			}
			@Override
			public Adapter caseFoco(Foco object) {
				return createFocoAdapter();
			}
			@Override
			public Adapter caseStrobo(Strobo object) {
				return createStroboAdapter();
			}
			@Override
			public Adapter caseUnionCancionSecuencia(UnionCancionSecuencia object) {
				return createUnionCancionSecuenciaAdapter();
			}
			@Override
			public Adapter caseUnionSecuenciaLuz(UnionSecuenciaLuz object) {
				return createUnionSecuenciaLuzAdapter();
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
	 * Creates a new adapter for an object of class '{@link Encore.Concierto <em>Concierto</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Encore.Concierto
	 * @generated
	 */
	public Adapter createConciertoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Encore.Cancion <em>Cancion</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Encore.Cancion
	 * @generated
	 */
	public Adapter createCancionAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Encore.Secuencia <em>Secuencia</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Encore.Secuencia
	 * @generated
	 */
	public Adapter createSecuenciaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Encore.Luz <em>Luz</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Encore.Luz
	 * @generated
	 */
	public Adapter createLuzAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Encore.Foco <em>Foco</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Encore.Foco
	 * @generated
	 */
	public Adapter createFocoAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Encore.Strobo <em>Strobo</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Encore.Strobo
	 * @generated
	 */
	public Adapter createStroboAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Encore.UnionCancionSecuencia <em>Union Cancion Secuencia</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Encore.UnionCancionSecuencia
	 * @generated
	 */
	public Adapter createUnionCancionSecuenciaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link Encore.UnionSecuenciaLuz <em>Union Secuencia Luz</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see Encore.UnionSecuenciaLuz
	 * @generated
	 */
	public Adapter createUnionSecuenciaLuzAdapter() {
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

} //EncoreAdapterFactory
