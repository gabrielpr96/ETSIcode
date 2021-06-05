/**
 */
package Clase_0106;

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
 * @see Clase_0106.Clase_0106Factory
 * @model kind="package"
 * @generated
 */
public interface Clase_0106Package extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Clase_0106";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "Clase_0106";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Clase_0106";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	Clase_0106Package eINSTANCE = Clase_0106.impl.Clase_0106PackageImpl.init();

	/**
	 * The meta object id for the '{@link Clase_0106.impl.GrafoImpl <em>Grafo</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Clase_0106.impl.GrafoImpl
	 * @see Clase_0106.impl.Clase_0106PackageImpl#getGrafo()
	 * @generated
	 */
	int GRAFO = 0;

	/**
	 * The feature id for the '<em><b>Conexiones</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAFO__CONEXIONES = 0;

	/**
	 * The feature id for the '<em><b>Nodos</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAFO__NODOS = 1;

	/**
	 * The number of structural features of the '<em>Grafo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int GRAFO_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link Clase_0106.impl.NodoImpl <em>Nodo</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Clase_0106.impl.NodoImpl
	 * @see Clase_0106.impl.Clase_0106PackageImpl#getNodo()
	 * @generated
	 */
	int NODO = 1;

	/**
	 * The feature id for the '<em><b>Entrante</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODO__ENTRANTE = 0;

	/**
	 * The feature id for the '<em><b>Saliente</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODO__SALIENTE = 1;

	/**
	 * The number of structural features of the '<em>Nodo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODO_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link Clase_0106.impl.Subnodo1Impl <em>Subnodo1</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Clase_0106.impl.Subnodo1Impl
	 * @see Clase_0106.impl.Clase_0106PackageImpl#getSubnodo1()
	 * @generated
	 */
	int SUBNODO1 = 2;

	/**
	 * The feature id for the '<em><b>Entrante</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBNODO1__ENTRANTE = NODO__ENTRANTE;

	/**
	 * The feature id for the '<em><b>Saliente</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBNODO1__SALIENTE = NODO__SALIENTE;

	/**
	 * The feature id for the '<em><b>Nombre</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBNODO1__NOMBRE = NODO_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Subnodo1</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBNODO1_FEATURE_COUNT = NODO_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link Clase_0106.impl.Subnodo2Impl <em>Subnodo2</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Clase_0106.impl.Subnodo2Impl
	 * @see Clase_0106.impl.Clase_0106PackageImpl#getSubnodo2()
	 * @generated
	 */
	int SUBNODO2 = 3;

	/**
	 * The feature id for the '<em><b>Entrante</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBNODO2__ENTRANTE = NODO__ENTRANTE;

	/**
	 * The feature id for the '<em><b>Saliente</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBNODO2__SALIENTE = NODO__SALIENTE;

	/**
	 * The number of structural features of the '<em>Subnodo2</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SUBNODO2_FEATURE_COUNT = NODO_FEATURE_COUNT + 0;

	/**
	 * The meta object id for the '{@link Clase_0106.impl.ConexionImpl <em>Conexion</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Clase_0106.impl.ConexionImpl
	 * @see Clase_0106.impl.Clase_0106PackageImpl#getConexion()
	 * @generated
	 */
	int CONEXION = 4;

	/**
	 * The feature id for the '<em><b>Nombre</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONEXION__NOMBRE = 0;

	/**
	 * The feature id for the '<em><b>Origen</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONEXION__ORIGEN = 1;

	/**
	 * The feature id for the '<em><b>Destino</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONEXION__DESTINO = 2;

	/**
	 * The number of structural features of the '<em>Conexion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONEXION_FEATURE_COUNT = 3;


	/**
	 * Returns the meta object for class '{@link Clase_0106.Grafo <em>Grafo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Grafo</em>'.
	 * @see Clase_0106.Grafo
	 * @generated
	 */
	EClass getGrafo();

	/**
	 * Returns the meta object for the containment reference list '{@link Clase_0106.Grafo#getConexiones <em>Conexiones</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Conexiones</em>'.
	 * @see Clase_0106.Grafo#getConexiones()
	 * @see #getGrafo()
	 * @generated
	 */
	EReference getGrafo_Conexiones();

	/**
	 * Returns the meta object for the containment reference list '{@link Clase_0106.Grafo#getNodos <em>Nodos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodos</em>'.
	 * @see Clase_0106.Grafo#getNodos()
	 * @see #getGrafo()
	 * @generated
	 */
	EReference getGrafo_Nodos();

	/**
	 * Returns the meta object for class '{@link Clase_0106.Nodo <em>Nodo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Nodo</em>'.
	 * @see Clase_0106.Nodo
	 * @generated
	 */
	EClass getNodo();

	/**
	 * Returns the meta object for the reference list '{@link Clase_0106.Nodo#getEntrante <em>Entrante</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Entrante</em>'.
	 * @see Clase_0106.Nodo#getEntrante()
	 * @see #getNodo()
	 * @generated
	 */
	EReference getNodo_Entrante();

	/**
	 * Returns the meta object for the reference list '{@link Clase_0106.Nodo#getSaliente <em>Saliente</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Saliente</em>'.
	 * @see Clase_0106.Nodo#getSaliente()
	 * @see #getNodo()
	 * @generated
	 */
	EReference getNodo_Saliente();

	/**
	 * Returns the meta object for class '{@link Clase_0106.Subnodo1 <em>Subnodo1</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Subnodo1</em>'.
	 * @see Clase_0106.Subnodo1
	 * @generated
	 */
	EClass getSubnodo1();

	/**
	 * Returns the meta object for the attribute '{@link Clase_0106.Subnodo1#getNombre <em>Nombre</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nombre</em>'.
	 * @see Clase_0106.Subnodo1#getNombre()
	 * @see #getSubnodo1()
	 * @generated
	 */
	EAttribute getSubnodo1_Nombre();

	/**
	 * Returns the meta object for class '{@link Clase_0106.Subnodo2 <em>Subnodo2</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Subnodo2</em>'.
	 * @see Clase_0106.Subnodo2
	 * @generated
	 */
	EClass getSubnodo2();

	/**
	 * Returns the meta object for class '{@link Clase_0106.Conexion <em>Conexion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Conexion</em>'.
	 * @see Clase_0106.Conexion
	 * @generated
	 */
	EClass getConexion();

	/**
	 * Returns the meta object for the attribute '{@link Clase_0106.Conexion#getNombre <em>Nombre</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Nombre</em>'.
	 * @see Clase_0106.Conexion#getNombre()
	 * @see #getConexion()
	 * @generated
	 */
	EAttribute getConexion_Nombre();

	/**
	 * Returns the meta object for the reference '{@link Clase_0106.Conexion#getOrigen <em>Origen</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Origen</em>'.
	 * @see Clase_0106.Conexion#getOrigen()
	 * @see #getConexion()
	 * @generated
	 */
	EReference getConexion_Origen();

	/**
	 * Returns the meta object for the reference '{@link Clase_0106.Conexion#getDestino <em>Destino</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destino</em>'.
	 * @see Clase_0106.Conexion#getDestino()
	 * @see #getConexion()
	 * @generated
	 */
	EReference getConexion_Destino();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	Clase_0106Factory getClase_0106Factory();

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
		 * The meta object literal for the '{@link Clase_0106.impl.GrafoImpl <em>Grafo</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Clase_0106.impl.GrafoImpl
		 * @see Clase_0106.impl.Clase_0106PackageImpl#getGrafo()
		 * @generated
		 */
		EClass GRAFO = eINSTANCE.getGrafo();

		/**
		 * The meta object literal for the '<em><b>Conexiones</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAFO__CONEXIONES = eINSTANCE.getGrafo_Conexiones();

		/**
		 * The meta object literal for the '<em><b>Nodos</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference GRAFO__NODOS = eINSTANCE.getGrafo_Nodos();

		/**
		 * The meta object literal for the '{@link Clase_0106.impl.NodoImpl <em>Nodo</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Clase_0106.impl.NodoImpl
		 * @see Clase_0106.impl.Clase_0106PackageImpl#getNodo()
		 * @generated
		 */
		EClass NODO = eINSTANCE.getNodo();

		/**
		 * The meta object literal for the '<em><b>Entrante</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODO__ENTRANTE = eINSTANCE.getNodo_Entrante();

		/**
		 * The meta object literal for the '<em><b>Saliente</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference NODO__SALIENTE = eINSTANCE.getNodo_Saliente();

		/**
		 * The meta object literal for the '{@link Clase_0106.impl.Subnodo1Impl <em>Subnodo1</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Clase_0106.impl.Subnodo1Impl
		 * @see Clase_0106.impl.Clase_0106PackageImpl#getSubnodo1()
		 * @generated
		 */
		EClass SUBNODO1 = eINSTANCE.getSubnodo1();

		/**
		 * The meta object literal for the '<em><b>Nombre</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SUBNODO1__NOMBRE = eINSTANCE.getSubnodo1_Nombre();

		/**
		 * The meta object literal for the '{@link Clase_0106.impl.Subnodo2Impl <em>Subnodo2</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Clase_0106.impl.Subnodo2Impl
		 * @see Clase_0106.impl.Clase_0106PackageImpl#getSubnodo2()
		 * @generated
		 */
		EClass SUBNODO2 = eINSTANCE.getSubnodo2();

		/**
		 * The meta object literal for the '{@link Clase_0106.impl.ConexionImpl <em>Conexion</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Clase_0106.impl.ConexionImpl
		 * @see Clase_0106.impl.Clase_0106PackageImpl#getConexion()
		 * @generated
		 */
		EClass CONEXION = eINSTANCE.getConexion();

		/**
		 * The meta object literal for the '<em><b>Nombre</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CONEXION__NOMBRE = eINSTANCE.getConexion_Nombre();

		/**
		 * The meta object literal for the '<em><b>Origen</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONEXION__ORIGEN = eINSTANCE.getConexion_Origen();

		/**
		 * The meta object literal for the '<em><b>Destino</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONEXION__DESTINO = eINSTANCE.getConexion_Destino();

	}

} //Clase_0106Package
