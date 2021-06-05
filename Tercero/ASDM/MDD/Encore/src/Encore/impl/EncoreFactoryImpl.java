/**
 */
package Encore.impl;

import Encore.*;

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
public class EncoreFactoryImpl extends EFactoryImpl implements EncoreFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EncoreFactory init() {
		try {
			EncoreFactory theEncoreFactory = (EncoreFactory)EPackage.Registry.INSTANCE.getEFactory(EncorePackage.eNS_URI);
			if (theEncoreFactory != null) {
				return theEncoreFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new EncoreFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EncoreFactoryImpl() {
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
			case EncorePackage.CONCIERTO: return createConcierto();
			case EncorePackage.CANCION: return createCancion();
			case EncorePackage.SECUENCIA: return createSecuencia();
			case EncorePackage.FOCO: return createFoco();
			case EncorePackage.STROBO: return createStrobo();
			case EncorePackage.UNION_CANCION_SECUENCIA: return createUnionCancionSecuencia();
			case EncorePackage.UNION_SECUENCIA_LUZ: return createUnionSecuenciaLuz();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Concierto createConcierto() {
		ConciertoImpl concierto = new ConciertoImpl();
		return concierto;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Cancion createCancion() {
		CancionImpl cancion = new CancionImpl();
		return cancion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Secuencia createSecuencia() {
		SecuenciaImpl secuencia = new SecuenciaImpl();
		return secuencia;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Foco createFoco() {
		FocoImpl foco = new FocoImpl();
		return foco;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Strobo createStrobo() {
		StroboImpl strobo = new StroboImpl();
		return strobo;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnionCancionSecuencia createUnionCancionSecuencia() {
		UnionCancionSecuenciaImpl unionCancionSecuencia = new UnionCancionSecuenciaImpl();
		return unionCancionSecuencia;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public UnionSecuenciaLuz createUnionSecuenciaLuz() {
		UnionSecuenciaLuzImpl unionSecuenciaLuz = new UnionSecuenciaLuzImpl();
		return unionSecuenciaLuz;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EncorePackage getEncorePackage() {
		return (EncorePackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static EncorePackage getPackage() {
		return EncorePackage.eINSTANCE;
	}

} //EncoreFactoryImpl
