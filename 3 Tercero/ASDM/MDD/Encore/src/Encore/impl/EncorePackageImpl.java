/**
 */
package Encore.impl;

import Encore.Cancion;
import Encore.Concierto;
import Encore.EncoreFactory;
import Encore.EncorePackage;
import Encore.Foco;
import Encore.Luz;
import Encore.Secuencia;
import Encore.Strobo;
import Encore.UnionCancionSecuencia;
import Encore.UnionSecuenciaLuz;

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
public class EncorePackageImpl extends EPackageImpl implements EncorePackage {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass conciertoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass cancionEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass secuenciaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass luzEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass focoEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass stroboEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unionCancionSecuenciaEClass = null;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private EClass unionSecuenciaLuzEClass = null;

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
	 * @see Encore.EncorePackage#eNS_URI
	 * @see #init()
	 * @generated
	 */
	private EncorePackageImpl() {
		super(eNS_URI, EncoreFactory.eINSTANCE);
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
	 * <p>This method is used to initialize {@link EncorePackage#eINSTANCE} when that field is accessed.
	 * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #eNS_URI
	 * @see #createPackageContents()
	 * @see #initializePackageContents()
	 * @generated
	 */
	public static EncorePackage init() {
		if (isInited) return (EncorePackage)EPackage.Registry.INSTANCE.getEPackage(EncorePackage.eNS_URI);

		// Obtain or create and register package
		Object registeredEncorePackage = EPackage.Registry.INSTANCE.get(eNS_URI);
		EncorePackageImpl theEncorePackage = registeredEncorePackage instanceof EncorePackageImpl ? (EncorePackageImpl)registeredEncorePackage : new EncorePackageImpl();

		isInited = true;

		// Create package meta-data objects
		theEncorePackage.createPackageContents();

		// Initialize created meta-data
		theEncorePackage.initializePackageContents();

		// Mark meta-data to indicate it can't be changed
		theEncorePackage.freeze();

		// Update the registry and return the package
		EPackage.Registry.INSTANCE.put(EncorePackage.eNS_URI, theEncorePackage);
		return theEncorePackage;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getConcierto() {
		return conciertoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcierto_Canciones() {
		return (EReference)conciertoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcierto_Luces() {
		return (EReference)conciertoEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcierto_Secuencias() {
		return (EReference)conciertoEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcierto_UnionesCancionesSencuencias() {
		return (EReference)conciertoEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getConcierto_UnionesSencuenciasLuces() {
		return (EReference)conciertoEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getCancion() {
		return cancionEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCancion_Titulo() {
		return (EAttribute)cancionEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getCancion_Duracion() {
		return (EAttribute)cancionEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getSecuencia() {
		return secuenciaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecuencia_Numero() {
		return (EAttribute)secuenciaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecuencia_Modificador() {
		return (EAttribute)secuenciaEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecuencia_AnguloZ() {
		return (EAttribute)secuenciaEClass.getEStructuralFeatures().get(2);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecuencia_AnguloY() {
		return (EAttribute)secuenciaEClass.getEStructuralFeatures().get(3);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecuencia_Inicio() {
		return (EAttribute)secuenciaEClass.getEStructuralFeatures().get(4);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getSecuencia_Duracion() {
		return (EAttribute)secuenciaEClass.getEStructuralFeatures().get(5);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getLuz() {
		return luzEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getLuz_ID() {
		return (EAttribute)luzEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getFoco() {
		return focoEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getFoco_Potencia() {
		return (EAttribute)focoEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getStrobo() {
		return stroboEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EAttribute getStrobo_Frecuencia() {
		return (EAttribute)stroboEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnionCancionSecuencia() {
		return unionCancionSecuenciaEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnionCancionSecuencia_Cancion() {
		return (EReference)unionCancionSecuenciaEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnionCancionSecuencia_Secuencia() {
		return (EReference)unionCancionSecuenciaEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EClass getUnionSecuenciaLuz() {
		return unionSecuenciaLuzEClass;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnionSecuenciaLuz_Secuencia() {
		return (EReference)unionSecuenciaLuzEClass.getEStructuralFeatures().get(0);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EReference getUnionSecuenciaLuz_Luz() {
		return (EReference)unionSecuenciaLuzEClass.getEStructuralFeatures().get(1);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EncoreFactory getEncoreFactory() {
		return (EncoreFactory)getEFactoryInstance();
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
		conciertoEClass = createEClass(CONCIERTO);
		createEReference(conciertoEClass, CONCIERTO__CANCIONES);
		createEReference(conciertoEClass, CONCIERTO__LUCES);
		createEReference(conciertoEClass, CONCIERTO__SECUENCIAS);
		createEReference(conciertoEClass, CONCIERTO__UNIONES_CANCIONES_SENCUENCIAS);
		createEReference(conciertoEClass, CONCIERTO__UNIONES_SENCUENCIAS_LUCES);

		cancionEClass = createEClass(CANCION);
		createEAttribute(cancionEClass, CANCION__TITULO);
		createEAttribute(cancionEClass, CANCION__DURACION);

		secuenciaEClass = createEClass(SECUENCIA);
		createEAttribute(secuenciaEClass, SECUENCIA__NUMERO);
		createEAttribute(secuenciaEClass, SECUENCIA__MODIFICADOR);
		createEAttribute(secuenciaEClass, SECUENCIA__ANGULO_Z);
		createEAttribute(secuenciaEClass, SECUENCIA__ANGULO_Y);
		createEAttribute(secuenciaEClass, SECUENCIA__INICIO);
		createEAttribute(secuenciaEClass, SECUENCIA__DURACION);

		luzEClass = createEClass(LUZ);
		createEAttribute(luzEClass, LUZ__ID);

		focoEClass = createEClass(FOCO);
		createEAttribute(focoEClass, FOCO__POTENCIA);

		stroboEClass = createEClass(STROBO);
		createEAttribute(stroboEClass, STROBO__FRECUENCIA);

		unionCancionSecuenciaEClass = createEClass(UNION_CANCION_SECUENCIA);
		createEReference(unionCancionSecuenciaEClass, UNION_CANCION_SECUENCIA__CANCION);
		createEReference(unionCancionSecuenciaEClass, UNION_CANCION_SECUENCIA__SECUENCIA);

		unionSecuenciaLuzEClass = createEClass(UNION_SECUENCIA_LUZ);
		createEReference(unionSecuenciaLuzEClass, UNION_SECUENCIA_LUZ__SECUENCIA);
		createEReference(unionSecuenciaLuzEClass, UNION_SECUENCIA_LUZ__LUZ);
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
		focoEClass.getESuperTypes().add(this.getLuz());
		stroboEClass.getESuperTypes().add(this.getLuz());

		// Initialize classes and features; add operations and parameters
		initEClass(conciertoEClass, Concierto.class, "Concierto", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getConcierto_Canciones(), this.getCancion(), null, "canciones", null, 0, -1, Concierto.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcierto_Luces(), this.getLuz(), null, "luces", null, 0, -1, Concierto.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcierto_Secuencias(), this.getSecuencia(), null, "secuencias", null, 0, -1, Concierto.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcierto_UnionesCancionesSencuencias(), this.getUnionCancionSecuencia(), null, "unionesCancionesSencuencias", null, 0, -1, Concierto.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getConcierto_UnionesSencuenciasLuces(), this.getUnionSecuenciaLuz(), null, "UnionesSencuenciasLuces", null, 0, -1, Concierto.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, IS_COMPOSITE, !IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(cancionEClass, Cancion.class, "Cancion", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getCancion_Titulo(), ecorePackage.getEString(), "Titulo", null, 0, 1, Cancion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getCancion_Duracion(), ecorePackage.getEInt(), "Duracion", null, 0, 1, Cancion.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(secuenciaEClass, Secuencia.class, "Secuencia", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getSecuencia_Numero(), ecorePackage.getEInt(), "Numero", null, 0, 1, Secuencia.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSecuencia_Modificador(), ecorePackage.getEFloat(), "Modificador", null, 0, 1, Secuencia.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSecuencia_AnguloZ(), ecorePackage.getEFloat(), "AnguloZ", null, 0, 1, Secuencia.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSecuencia_AnguloY(), ecorePackage.getEFloat(), "AnguloY", null, 0, 1, Secuencia.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSecuencia_Inicio(), ecorePackage.getEInt(), "Inicio", null, 0, 1, Secuencia.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEAttribute(getSecuencia_Duracion(), ecorePackage.getEInt(), "Duracion", null, 0, 1, Secuencia.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(luzEClass, Luz.class, "Luz", IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getLuz_ID(), ecorePackage.getEInt(), "ID", null, 0, 1, Luz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(focoEClass, Foco.class, "Foco", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getFoco_Potencia(), ecorePackage.getEFloat(), "Potencia", null, 0, 1, Foco.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(stroboEClass, Strobo.class, "Strobo", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEAttribute(getStrobo_Frecuencia(), ecorePackage.getEFloat(), "Frecuencia", null, 0, 1, Strobo.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_UNSETTABLE, !IS_ID, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unionCancionSecuenciaEClass, UnionCancionSecuencia.class, "UnionCancionSecuencia", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnionCancionSecuencia_Cancion(), this.getCancion(), null, "cancion", null, 0, -1, UnionCancionSecuencia.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUnionCancionSecuencia_Secuencia(), this.getSecuencia(), null, "secuencia", null, 1, 1, UnionCancionSecuencia.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

		initEClass(unionSecuenciaLuzEClass, UnionSecuenciaLuz.class, "UnionSecuenciaLuz", !IS_ABSTRACT, !IS_INTERFACE, IS_GENERATED_INSTANCE_CLASS);
		initEReference(getUnionSecuenciaLuz_Secuencia(), this.getSecuencia(), null, "secuencia", null, 1, -1, UnionSecuenciaLuz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);
		initEReference(getUnionSecuenciaLuz_Luz(), this.getLuz(), null, "luz", null, 0, -1, UnionSecuenciaLuz.class, !IS_TRANSIENT, !IS_VOLATILE, IS_CHANGEABLE, !IS_COMPOSITE, IS_RESOLVE_PROXIES, !IS_UNSETTABLE, IS_UNIQUE, !IS_DERIVED, IS_ORDERED);

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
		  (conciertoEClass,
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
		  (cancionEClass,
		   source,
		   new String[] {
			   "label", "Titulo",
			   "label.placement", "external",
			   "figure", "svg",
			   "svg.uri", "platform:/plugin/Encore/vectores/nota.svg",
			   "tool.name", "Cancion",
			   "tool.description", "Agrega una cancion"
		   });
		addAnnotation
		  (secuenciaEClass,
		   source,
		   new String[] {
			   "label", "Numero",
			   "label.placement", "external",
			   "figure", "svg",
			   "svg.uri", "platform:/plugin/Encore/vectores/engranaje.svg",
			   "tool.name", "Secuencia",
			   "tool.description", "Agrega una sencuencia que cambia los valores de una luz durante un tiempo en una cancion"
		   });
		addAnnotation
		  (focoEClass,
		   source,
		   new String[] {
			   "label", "ID",
			   "label.placement", "external",
			   "figure", "svg",
			   "svg.uri", "platform:/plugin/Encore/vectores/foco.svg",
			   "tool.name", "Foco",
			   "tool.description", "Agrega una luz de tipo foco"
		   });
		addAnnotation
		  (stroboEClass,
		   source,
		   new String[] {
			   "label", "ID",
			   "label.placement", "external",
			   "figure", "svg",
			   "svg.uri", "platform:/plugin/Encore/vectores/estrobo.svg",
			   "tool.name", "Estrobo",
			   "tool.description", "Agrega una luz de tipo estrobo"
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
		  (unionCancionSecuenciaEClass,
		   source,
		   new String[] {
			   "label.placement", "none",
			   "source", "cancion",
			   "target", "secuencia",
			   "target.decoration", "arrow",
			   "tool.description", "Conecta una secuencia a una cancion"
		   });
		addAnnotation
		  (unionSecuenciaLuzEClass,
		   source,
		   new String[] {
			   "label.placement", "none",
			   "source", "secuencia",
			   "target", "luz",
			   "target.decoration", "arrow",
			   "tool.description", "Conecta una luz a una secuencia"
		   });
	}

} //EncorePackageImpl
