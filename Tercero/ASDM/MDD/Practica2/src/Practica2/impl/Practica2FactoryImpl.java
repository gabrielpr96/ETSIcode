/**
 */
package Practica2.impl;

import Practica2.*;

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
public class Practica2FactoryImpl extends EFactoryImpl implements Practica2Factory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static Practica2Factory init() {
		try {
			Practica2Factory thePractica2Factory = (Practica2Factory)EPackage.Registry.INSTANCE.getEFactory(Practica2Package.eNS_URI);
			if (thePractica2Factory != null) {
				return thePractica2Factory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new Practica2FactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Practica2FactoryImpl() {
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
			case Practica2Package.DIAGRAMA: return createDiagrama();
			case Practica2Package.ARISTA: return createArista();
			case Practica2Package.ACTIVIDAD: return createActividad();
			case Practica2Package.DECISION: return createDecision();
			case Practica2Package.RAMIFICACION: return createRamificacion();
			case Practica2Package.NODO_INICIAL: return createNodoInicial();
			case Practica2Package.NODO_FINAL: return createNodoFinal();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Diagrama createDiagrama() {
		DiagramaImpl diagrama = new DiagramaImpl();
		return diagrama;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Arista createArista() {
		AristaImpl arista = new AristaImpl();
		return arista;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Actividad createActividad() {
		ActividadImpl actividad = new ActividadImpl();
		return actividad;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Decision createDecision() {
		DecisionImpl decision = new DecisionImpl();
		return decision;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Ramificacion createRamificacion() {
		RamificacionImpl ramificacion = new RamificacionImpl();
		return ramificacion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodoInicial createNodoInicial() {
		NodoInicialImpl nodoInicial = new NodoInicialImpl();
		return nodoInicial;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NodoFinal createNodoFinal() {
		NodoFinalImpl nodoFinal = new NodoFinalImpl();
		return nodoFinal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Practica2Package getPractica2Package() {
		return (Practica2Package)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static Practica2Package getPackage() {
		return Practica2Package.eINSTANCE;
	}

} //Practica2FactoryImpl
