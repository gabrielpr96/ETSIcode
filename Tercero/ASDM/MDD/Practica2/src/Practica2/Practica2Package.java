/**
 */
package Practica2;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see Practica2.Practica2Factory
 * @model kind="package"
 * @generated
 */
public interface Practica2Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Practica2";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "Practica2";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Practica2";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Practica2Package eINSTANCE = Practica2.impl.Practica2PackageImpl.init();

	/**
	 * The meta object id for the '{@link Practica2.impl.DiagramaImpl <em>Diagrama</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Practica2.impl.DiagramaImpl
	 * @see Practica2.impl.Practica2PackageImpl#getDiagrama()
	 * @generated
	 */
	int DIAGRAMA = 0;

	/**
	 * The feature id for the '<em><b>Nodos</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAMA__NODOS = 0;

	/**
	 * The feature id for the '<em><b>Aristas</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAMA__ARISTAS = 1;

	/**
	 * The number of structural features of the '<em>Diagrama</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAMA_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link Practica2.impl.NodoImpl <em>Nodo</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Practica2.impl.NodoImpl
	 * @see Practica2.impl.Practica2PackageImpl#getNodo()
	 * @generated
	 */
	int NODO = 1;

	/**
	 * The feature id for the '<em><b>Salientes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODO__SALIENTES = 0;

	/**
	 * The feature id for the '<em><b>Entrantes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODO__ENTRANTES = 1;

	/**
	 * The number of structural features of the '<em>Nodo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODO_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link Practica2.impl.AristaImpl <em>Arista</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Practica2.impl.AristaImpl
	 * @see Practica2.impl.Practica2PackageImpl#getArista()
	 * @generated
	 */
	int ARISTA = 2;

	/**
	 * The feature id for the '<em><b>Nombre</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARISTA__NOMBRE = 0;

	/**
	 * The feature id for the '<em><b>Origen</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARISTA__ORIGEN = 1;

	/**
	 * The feature id for the '<em><b>Destino</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARISTA__DESTINO = 2;

	/**
	 * The number of structural features of the '<em>Arista</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ARISTA_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link Practica2.impl.ActividadImpl <em>Actividad</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Practica2.impl.ActividadImpl
	 * @see Practica2.impl.Practica2PackageImpl#getActividad()
	 * @generated
	 */
	int ACTIVIDAD = 3;

	/**
	 * The feature id for the '<em><b>Salientes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVIDAD__SALIENTES = NODO__SALIENTES;

	/**
	 * The feature id for the '<em><b>Entrantes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVIDAD__ENTRANTES = NODO__ENTRANTES;

	/**
	 * The feature id for the '<em><b>Nombre</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVIDAD__NOMBRE = NODO_FEATURE_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Subdiag</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVIDAD__SUBDIAG = NODO_FEATURE_COUNT + 1;

	/**
	 * The number of structural features of the '<em>Actividad</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ACTIVIDAD_FEATURE_COUNT = NODO_FEATURE_COUNT + 2;

	/**
	 * The meta object id for the '{@link Practica2.impl.DecisionImpl <em>Decision</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Practica2.impl.DecisionImpl
	 * @see Practica2.impl.Practica2PackageImpl#getDecision()
	 * @generated
	 */
	int DECISION = 4;

	/**
	 * The feature id for the '<em><b>Salientes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__SALIENTES = NODO__SALIENTES;

	/**
	 * The feature id for the '<em><b>Entrantes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION__ENTRANTES = NODO__ENTRANTES;

	/**
	 * The number of structural features of the '<em>Decision</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DECISION_FEATURE_COUNT = NODO_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link Practica2.impl.RamificacionImpl <em>Ramificacion</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Practica2.impl.RamificacionImpl
	 * @see Practica2.impl.Practica2PackageImpl#getRamificacion()
	 * @generated
	 */
	int RAMIFICACION = 5;

	/**
	 * The feature id for the '<em><b>Salientes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAMIFICACION__SALIENTES = NODO__SALIENTES;

	/**
	 * The feature id for the '<em><b>Entrantes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAMIFICACION__ENTRANTES = NODO__ENTRANTES;

	/**
	 * The number of structural features of the '<em>Ramificacion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int RAMIFICACION_FEATURE_COUNT = NODO_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link Practica2.impl.NodoInicialImpl <em>Nodo Inicial</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Practica2.impl.NodoInicialImpl
	 * @see Practica2.impl.Practica2PackageImpl#getNodoInicial()
	 * @generated
	 */
	int NODO_INICIAL = 6;

	/**
	 * The feature id for the '<em><b>Salientes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODO_INICIAL__SALIENTES = NODO__SALIENTES;

	/**
	 * The feature id for the '<em><b>Entrantes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODO_INICIAL__ENTRANTES = NODO__ENTRANTES;

	/**
	 * The number of structural features of the '<em>Nodo Inicial</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODO_INICIAL_FEATURE_COUNT = NODO_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link Practica2.impl.NodoFinalImpl <em>Nodo Final</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Practica2.impl.NodoFinalImpl
	 * @see Practica2.impl.Practica2PackageImpl#getNodoFinal()
	 * @generated
	 */
	int NODO_FINAL = 7;

	/**
	 * The feature id for the '<em><b>Salientes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODO_FINAL__SALIENTES = NODO__SALIENTES;

	/**
	 * The feature id for the '<em><b>Entrantes</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODO_FINAL__ENTRANTES = NODO__ENTRANTES;

	/**
	 * The number of structural features of the '<em>Nodo Final</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODO_FINAL_FEATURE_COUNT = NODO_FEATURE_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link Practica2.Diagrama <em>Diagrama</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagrama</em>'.
	 * @see Practica2.Diagrama
	 * @generated
	 */
	EClass getDiagrama();

	/**
	 * Returns the meta object for the containment reference list '{@link Practica2.Diagrama#getNodos <em>Nodos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodos</em>'.
	 * @see Practica2.Diagrama#getNodos()
	 * @see #getDiagrama()
	 * @generated
	 */
	EReference getDiagrama_Nodos();

	/**
	 * Returns the meta object for the containment reference list '{@link Practica2.Diagrama#getAristas <em>Aristas</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Aristas</em>'.
	 * @see Practica2.Diagrama#getAristas()
	 * @see #getDiagrama()
	 * @generated
	 */
	EReference getDiagrama_Aristas();

	/**
	 * Returns the meta object for class '{@link Practica2.Nodo <em>Nodo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nodo</em>'.
	 * @see Practica2.Nodo
	 * @generated
	 */
	EClass getNodo();

	/**
	 * Returns the meta object for the reference list '{@link Practica2.Nodo#getSalientes <em>Salientes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Salientes</em>'.
	 * @see Practica2.Nodo#getSalientes()
	 * @see #getNodo()
	 * @generated
	 */
	EReference getNodo_Salientes();

	/**
	 * Returns the meta object for the reference list '{@link Practica2.Nodo#getEntrantes <em>Entrantes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Entrantes</em>'.
	 * @see Practica2.Nodo#getEntrantes()
	 * @see #getNodo()
	 * @generated
	 */
	EReference getNodo_Entrantes();

	/**
	 * Returns the meta object for class '{@link Practica2.Arista <em>Arista</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Arista</em>'.
	 * @see Practica2.Arista
	 * @generated
	 */
	EClass getArista();

	/**
	 * Returns the meta object for the attribute '{@link Practica2.Arista#getNombre <em>Nombre</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nombre</em>'.
	 * @see Practica2.Arista#getNombre()
	 * @see #getArista()
	 * @generated
	 */
	EAttribute getArista_Nombre();

	/**
	 * Returns the meta object for the reference '{@link Practica2.Arista#getOrigen <em>Origen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Origen</em>'.
	 * @see Practica2.Arista#getOrigen()
	 * @see #getArista()
	 * @generated
	 */
	EReference getArista_Origen();

	/**
	 * Returns the meta object for the reference '{@link Practica2.Arista#getDestino <em>Destino</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destino</em>'.
	 * @see Practica2.Arista#getDestino()
	 * @see #getArista()
	 * @generated
	 */
	EReference getArista_Destino();

	/**
	 * Returns the meta object for class '{@link Practica2.Actividad <em>Actividad</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Actividad</em>'.
	 * @see Practica2.Actividad
	 * @generated
	 */
	EClass getActividad();

	/**
	 * Returns the meta object for the attribute '{@link Practica2.Actividad#getNombre <em>Nombre</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nombre</em>'.
	 * @see Practica2.Actividad#getNombre()
	 * @see #getActividad()
	 * @generated
	 */
	EAttribute getActividad_Nombre();

	/**
	 * Returns the meta object for the containment reference list '{@link Practica2.Actividad#getSubdiag <em>Subdiag</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Subdiag</em>'.
	 * @see Practica2.Actividad#getSubdiag()
	 * @see #getActividad()
	 * @generated
	 */
	EReference getActividad_Subdiag();

	/**
	 * Returns the meta object for class '{@link Practica2.Decision <em>Decision</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Decision</em>'.
	 * @see Practica2.Decision
	 * @generated
	 */
	EClass getDecision();

	/**
	 * Returns the meta object for class '{@link Practica2.Ramificacion <em>Ramificacion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Ramificacion</em>'.
	 * @see Practica2.Ramificacion
	 * @generated
	 */
	EClass getRamificacion();

	/**
	 * Returns the meta object for class '{@link Practica2.NodoInicial <em>Nodo Inicial</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nodo Inicial</em>'.
	 * @see Practica2.NodoInicial
	 * @generated
	 */
	EClass getNodoInicial();

	/**
	 * Returns the meta object for class '{@link Practica2.NodoFinal <em>Nodo Final</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nodo Final</em>'.
	 * @see Practica2.NodoFinal
	 * @generated
	 */
	EClass getNodoFinal();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Practica2Factory getPractica2Factory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link Practica2.impl.DiagramaImpl <em>Diagrama</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Practica2.impl.DiagramaImpl
		 * @see Practica2.impl.Practica2PackageImpl#getDiagrama()
		 * @generated
		 */
		EClass DIAGRAMA = eINSTANCE.getDiagrama();

		/**
		 * The meta object literal for the '<em><b>Nodos</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAMA__NODOS = eINSTANCE.getDiagrama_Nodos();

		/**
		 * The meta object literal for the '<em><b>Aristas</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAMA__ARISTAS = eINSTANCE.getDiagrama_Aristas();

		/**
		 * The meta object literal for the '{@link Practica2.impl.NodoImpl <em>Nodo</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Practica2.impl.NodoImpl
		 * @see Practica2.impl.Practica2PackageImpl#getNodo()
		 * @generated
		 */
		EClass NODO = eINSTANCE.getNodo();

		/**
		 * The meta object literal for the '<em><b>Salientes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODO__SALIENTES = eINSTANCE.getNodo_Salientes();

		/**
		 * The meta object literal for the '<em><b>Entrantes</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODO__ENTRANTES = eINSTANCE.getNodo_Entrantes();

		/**
		 * The meta object literal for the '{@link Practica2.impl.AristaImpl <em>Arista</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Practica2.impl.AristaImpl
		 * @see Practica2.impl.Practica2PackageImpl#getArista()
		 * @generated
		 */
		EClass ARISTA = eINSTANCE.getArista();

		/**
		 * The meta object literal for the '<em><b>Nombre</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ARISTA__NOMBRE = eINSTANCE.getArista_Nombre();

		/**
		 * The meta object literal for the '<em><b>Origen</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARISTA__ORIGEN = eINSTANCE.getArista_Origen();

		/**
		 * The meta object literal for the '<em><b>Destino</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ARISTA__DESTINO = eINSTANCE.getArista_Destino();

		/**
		 * The meta object literal for the '{@link Practica2.impl.ActividadImpl <em>Actividad</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Practica2.impl.ActividadImpl
		 * @see Practica2.impl.Practica2PackageImpl#getActividad()
		 * @generated
		 */
		EClass ACTIVIDAD = eINSTANCE.getActividad();

		/**
		 * The meta object literal for the '<em><b>Nombre</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ACTIVIDAD__NOMBRE = eINSTANCE.getActividad_Nombre();

		/**
		 * The meta object literal for the '<em><b>Subdiag</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ACTIVIDAD__SUBDIAG = eINSTANCE.getActividad_Subdiag();

		/**
		 * The meta object literal for the '{@link Practica2.impl.DecisionImpl <em>Decision</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Practica2.impl.DecisionImpl
		 * @see Practica2.impl.Practica2PackageImpl#getDecision()
		 * @generated
		 */
		EClass DECISION = eINSTANCE.getDecision();

		/**
		 * The meta object literal for the '{@link Practica2.impl.RamificacionImpl <em>Ramificacion</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Practica2.impl.RamificacionImpl
		 * @see Practica2.impl.Practica2PackageImpl#getRamificacion()
		 * @generated
		 */
		EClass RAMIFICACION = eINSTANCE.getRamificacion();

		/**
		 * The meta object literal for the '{@link Practica2.impl.NodoInicialImpl <em>Nodo Inicial</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Practica2.impl.NodoInicialImpl
		 * @see Practica2.impl.Practica2PackageImpl#getNodoInicial()
		 * @generated
		 */
		EClass NODO_INICIAL = eINSTANCE.getNodoInicial();

		/**
		 * The meta object literal for the '{@link Practica2.impl.NodoFinalImpl <em>Nodo Final</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Practica2.impl.NodoFinalImpl
		 * @see Practica2.impl.Practica2PackageImpl#getNodoFinal()
		 * @generated
		 */
		EClass NODO_FINAL = eINSTANCE.getNodoFinal();

	}

} //Practica2Package
