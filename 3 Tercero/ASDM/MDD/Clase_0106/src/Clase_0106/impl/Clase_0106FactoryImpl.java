/**
 */
package Clase_0106.impl;

import Clase_0106.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Clase_0106FactoryImpl extends EFactoryImpl implements Clase_0106Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Clase_0106Factory init() {
		try {
			Clase_0106Factory theClase_0106Factory = (Clase_0106Factory)EPackage.Registry.INSTANCE.getEFactory(Clase_0106Package.eNS_URI);
			if (theClase_0106Factory != null) {
				return theClase_0106Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Clase_0106FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clase_0106FactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case Clase_0106Package.GRAFO: return createGrafo();
			case Clase_0106Package.SUBNODO1: return createSubnodo1();
			case Clase_0106Package.SUBNODO2: return createSubnodo2();
			case Clase_0106Package.CONEXION: return createConexion();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Grafo createGrafo() {
		GrafoImpl grafo = new GrafoImpl();
		return grafo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Subnodo1 createSubnodo1() {
		Subnodo1Impl subnodo1 = new Subnodo1Impl();
		return subnodo1;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Subnodo2 createSubnodo2() {
		Subnodo2Impl subnodo2 = new Subnodo2Impl();
		return subnodo2;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Conexion createConexion() {
		ConexionImpl conexion = new ConexionImpl();
		return conexion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clase_0106Package getClase_0106Package() {
		return (Clase_0106Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Clase_0106Package getPackage() {
		return Clase_0106Package.eINSTANCE;
	}

} //Clase_0106FactoryImpl
