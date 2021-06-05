/**
 */
package Clase_0106.impl;

import Clase_0106.Clase_0106Factory;
import Clase_0106.Clase_0106Package;
import Clase_0106.Conexion;
import Clase_0106.Grafo;
import Clase_0106.Nodo;
import Clase_0106.Subnodo1;
import Clase_0106.Subnodo2;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

import org.eclipse.emf.ecore.impl.EPackageImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Package</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class Clase_0106PackageImpl extends EPackageImpl implements Clase_0106Package {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass grafoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass nodoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subnodo1EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass subnodo2EClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conexionEClass = null;

	/**
	 * Creates an instance of the model <b>Package</b>, registered with
	 * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package
	 * package URI value.
	 * <p>Note: the correct way to create the package is via the static
	 * factory method {@link #init init()}, which also performs
	 * initialization of the package, or returns the registered package,
	 * if one already exists.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see org.eclipse.emf.ecore.EPackage.Registry
	 * @see Clase_0106.Clase_0106Package#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private Clase_0106PackageImpl() {
		super(eNS_URI, Clase_0106Factory.eINSTANCE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static boolean isInited = false;

	/**
	 * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends.
	 *
	 * <p>This method is used to initialize {@link Clase_0106Package#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static Clase_0106Package init() {
		if (isInited) return (Clase_0106Package)EPackage.Registry.INSTANCE.getEPackage(Clase_0106Package.eNS_URI);

		// Obtain or create and register package
		Object registeredClase_0106Package = EPackage.Registry.INSTANCE.get(eNS_URI);
		Clase_0106PackageImpl theClase_0106Package = registeredClase_0106Package instanceof Clase_0106PackageImpl ? (Clase_0106PackageImpl)registeredClase_0106Package : new Clase_0106PackageImpl();

		isInited = true;

		// Create package meta-data objects
		theClase_0106Package.createPackageContents();

		// Initialize created meta-data
		theClase_0106Package.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theClase_0106Package.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(Clase_0106Package.eNS_URI, theClase_0106Package);
		return theClase_0106Package;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getGrafo() {
		return grafoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGrafo_Conexiones() {
		return (EReference)grafoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getGrafo_Nodos() {
		return (EReference)grafoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getNodo() {
		return nodoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodo_Entrante() {
		return (EReference)nodoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getNodo_Saliente() {
		return (EReference)nodoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubnodo1() {
		return subnodo1EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSubnodo1_Nombre() {
		return (EAttribute)subnodo1EClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSubnodo2() {
		return subnodo2EClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConexion() {
		return conexionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getConexion_Nombre() {
		return (EAttribute)conexionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConexion_Origen() {
		return (EReference)conexionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConexion_Destino() {
		return (EReference)conexionEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Clase_0106Factory getClase_0106Factory() {
		return (Clase_0106Factory)getEFactoryInstance();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isCreated = false;

	/**
	 * Creates the meta-model objects for the package.  This method is
	 * guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void createPackageContents() {
		if (isCreated) return;
		isCreated = true;

		// Create classes and their features
		grafoEClass = createEClass(GRAFO);
		createEReference(grafoEClass, GRAFO__CONEXIONES);
		createEReference(grafoEClass, GRAFO__NODOS);

		nodoEClass = createEClass(NODO);
		createEReference(nodoEClass, NODO__ENTRANTE);
		createEReference(nodoEClass, NODO__SALIENTE);

		subnodo1EClass = createEClass(SUBNODO1);
		createEAttribute(subnodo1EClass, SUBNODO1__NOMBRE);

		subnodo2EClass = createEClass(SUBNODO2);

		conexionEClass = createEClass(CONEXION);
		createEAttribute(conexionEClass, CONEXION__NOMBRE);
		createEReference(conexionEClass, CONEXION__ORIGEN);
		createEReference(conexionEClass, CONEXION__DESTINO);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private boolean isInitialized = false;

	/**
	 * Complete the initialization of the package and its meta-model.  This
	 * method is guarded to have no affect on any invocation but its first.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void initializePackageContents() {
		if (isInitialized) return;
		isInitialized = true;

		// Initialize package
		setName(eNAME);
		setNsPrefix(eNS_PREFIX);
		setNsURI(eNS_URI);

		// Create type parameters

		// Set bounds for type parameters

		// Add supertypes to classes
		subnodo1EClass.getESuperTypes().add(this.getNodo());
		subnodo2EClass.getESuperTypes().add(this.getNodo());

		// Initialize classes and features; add operations and parameters
		initEClass(grafoEClass, Grafo.class, "Grafo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getGrafo_Conexiones(), this.getConexion(), null, "conexiones", null, 0, -1, Grafo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getGrafo_Nodos(), this.getNodo(), null, "nodos", null, 0, -1, Grafo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(nodoEClass, Nodo.class, "Nodo", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getNodo_Entrante(), this.getConexion(), this.getConexion_Destino(), "entrante", null, 0, -1, Nodo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getNodo_Saliente(), this.getConexion(), this.getConexion_Origen(), "saliente", null, 0, -1, Nodo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(subnodo1EClass, Subnodo1.class, "Subnodo1", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSubnodo1_Nombre(), ecorePackage.getEString(), "nombre", null, 0, 1, Subnodo1.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(subnodo2EClass, Subnodo2.class, "Subnodo2", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);

		initEClass(conexionEClass, Conexion.class, "Conexion", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getConexion_Nombre(), ecorePackage.getEString(), "nombre", null, 0, 1, Conexion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConexion_Origen(), this.getNodo(), this.getNodo_Saliente(), "origen", null, 1, 1, Conexion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConexion_Destino(), this.getNodo(), this.getNodo_Entrante(), "destino", null, 1, 1, Conexion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		// Create resource
		createResource(eNS_URI);

		// Create annotations
		// gmf.diagram
		createGmfAnnotations();
		// gmf.node
		createGmf_1Annotations();
		// gmf.link
		createGmf_2Annotations();
	}

	/**
	 * Initializes the annotations for <b>gmf.diagram</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGmfAnnotations() {
		String source = "gmf.diagram";
		addAnnotation
		  (grafoEClass,
		   source,
		   new String[] {
			   "foo", "bar"
		   });
	}

	/**
	 * Initializes the annotations for <b>gmf.node</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGmf_1Annotations() {
		String source = "gmf.node";
		addAnnotation
		  (subnodo1EClass,
		   source,
		   new String[] {
			   "label", "nombre",
			   "label.icon", "false",
			   "tool.description", "Agrega un subnodo con nombre"
		   });
		addAnnotation
		  (subnodo2EClass,
		   source,
		   new String[] {
			   "label.placement", "none",
			   "figure", "polygon",
			   "polygon.x", "20 40 20 0",
			   "polygon.y", "0 20 40 20",
			   "tool.name", "Decision",
			   "tool.description", "Agrega un subnodo diferente"
		   });
	}

	/**
	 * Initializes the annotations for <b>gmf.link</b>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void createGmf_2Annotations() {
		String source = "gmf.link";
		addAnnotation
		  (conexionEClass,
		   source,
		   new String[] {
			   "label", "nombre",
			   "source", "origen",
			   "target", "destino",
			   "target.decoration", "arrow",
			   "tool.description", "Agrega una arista entre nodos"
		   });
	}

} //Clase_0106PackageImpl
