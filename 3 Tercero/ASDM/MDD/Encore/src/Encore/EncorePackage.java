/**
 */
package Encore;

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
 * @see Encore.EncoreFactory
 * @model kind="package"
 * @generated
 */
public interface EncorePackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "Encore";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "Encore";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "Encore";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	EncorePackage eINSTANCE = Encore.impl.EncorePackageImpl.init();

	/**
	 * The meta object id for the '{@link Encore.impl.ConciertoImpl <em>Concierto</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Encore.impl.ConciertoImpl
	 * @see Encore.impl.EncorePackageImpl#getConcierto()
	 * @generated
	 */
	int CONCIERTO = 0;

	/**
	 * The feature id for the '<em><b>Canciones</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCIERTO__CANCIONES = 0;

	/**
	 * The feature id for the '<em><b>Luces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCIERTO__LUCES = 1;

	/**
	 * The feature id for the '<em><b>Secuencias</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCIERTO__SECUENCIAS = 2;

	/**
	 * The feature id for the '<em><b>Uniones Canciones Sencuencias</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCIERTO__UNIONES_CANCIONES_SENCUENCIAS = 3;

	/**
	 * The feature id for the '<em><b>Uniones Sencuencias Luces</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCIERTO__UNIONES_SENCUENCIAS_LUCES = 4;

	/**
	 * The number of structural features of the '<em>Concierto</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CONCIERTO_FEATURE_COUNT = 5;

	/**
	 * The meta object id for the '{@link Encore.impl.CancionImpl <em>Cancion</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Encore.impl.CancionImpl
	 * @see Encore.impl.EncorePackageImpl#getCancion()
	 * @generated
	 */
	int CANCION = 1;

	/**
	 * The feature id for the '<em><b>Titulo</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANCION__TITULO = 0;

	/**
	 * The feature id for the '<em><b>Duracion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANCION__DURACION = 1;

	/**
	 * The number of structural features of the '<em>Cancion</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CANCION_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link Encore.impl.SecuenciaImpl <em>Secuencia</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Encore.impl.SecuenciaImpl
	 * @see Encore.impl.EncorePackageImpl#getSecuencia()
	 * @generated
	 */
	int SECUENCIA = 2;

	/**
	 * The feature id for the '<em><b>Numero</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECUENCIA__NUMERO = 0;

	/**
	 * The feature id for the '<em><b>Modificador</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECUENCIA__MODIFICADOR = 1;

	/**
	 * The feature id for the '<em><b>Angulo Z</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECUENCIA__ANGULO_Z = 2;

	/**
	 * The feature id for the '<em><b>Angulo Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECUENCIA__ANGULO_Y = 3;

	/**
	 * The feature id for the '<em><b>Inicio</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECUENCIA__INICIO = 4;

	/**
	 * The feature id for the '<em><b>Duracion</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECUENCIA__DURACION = 5;

	/**
	 * The number of structural features of the '<em>Secuencia</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SECUENCIA_FEATURE_COUNT = 6;

	/**
	 * The meta object id for the '{@link Encore.impl.LuzImpl <em>Luz</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Encore.impl.LuzImpl
	 * @see Encore.impl.EncorePackageImpl#getLuz()
	 * @generated
	 */
	int LUZ = 3;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUZ__ID = 0;

	/**
	 * The number of structural features of the '<em>Luz</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int LUZ_FEATURE_COUNT = 1;

	/**
	 * The meta object id for the '{@link Encore.impl.FocoImpl <em>Foco</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Encore.impl.FocoImpl
	 * @see Encore.impl.EncorePackageImpl#getFoco()
	 * @generated
	 */
	int FOCO = 4;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOCO__ID = LUZ__ID;

	/**
	 * The feature id for the '<em><b>Potencia</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOCO__POTENCIA = LUZ_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Foco</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOCO_FEATURE_COUNT = LUZ_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link Encore.impl.StroboImpl <em>Strobo</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Encore.impl.StroboImpl
	 * @see Encore.impl.EncorePackageImpl#getStrobo()
	 * @generated
	 */
	int STROBO = 5;

	/**
	 * The feature id for the '<em><b>ID</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STROBO__ID = LUZ__ID;

	/**
	 * The feature id for the '<em><b>Frecuencia</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STROBO__FRECUENCIA = LUZ_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Strobo</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STROBO_FEATURE_COUNT = LUZ_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link Encore.impl.UnionCancionSecuenciaImpl <em>Union Cancion Secuencia</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Encore.impl.UnionCancionSecuenciaImpl
	 * @see Encore.impl.EncorePackageImpl#getUnionCancionSecuencia()
	 * @generated
	 */
	int UNION_CANCION_SECUENCIA = 6;

	/**
	 * The feature id for the '<em><b>Cancion</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_CANCION_SECUENCIA__CANCION = 0;

	/**
	 * The feature id for the '<em><b>Secuencia</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_CANCION_SECUENCIA__SECUENCIA = 1;

	/**
	 * The number of structural features of the '<em>Union Cancion Secuencia</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_CANCION_SECUENCIA_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link Encore.impl.UnionSecuenciaLuzImpl <em>Union Secuencia Luz</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see Encore.impl.UnionSecuenciaLuzImpl
	 * @see Encore.impl.EncorePackageImpl#getUnionSecuenciaLuz()
	 * @generated
	 */
	int UNION_SECUENCIA_LUZ = 7;

	/**
	 * The feature id for the '<em><b>Secuencia</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_SECUENCIA_LUZ__SECUENCIA = 0;

	/**
	 * The feature id for the '<em><b>Luz</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_SECUENCIA_LUZ__LUZ = 1;

	/**
	 * The number of structural features of the '<em>Union Secuencia Luz</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int UNION_SECUENCIA_LUZ_FEATURE_COUNT = 2;


	/**
	 * Returns the meta object for class '{@link Encore.Concierto <em>Concierto</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Concierto</em>'.
	 * @see Encore.Concierto
	 * @generated
	 */
	EClass getConcierto();

	/**
	 * Returns the meta object for the containment reference list '{@link Encore.Concierto#getCanciones <em>Canciones</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Canciones</em>'.
	 * @see Encore.Concierto#getCanciones()
	 * @see #getConcierto()
	 * @generated
	 */
	EReference getConcierto_Canciones();

	/**
	 * Returns the meta object for the containment reference list '{@link Encore.Concierto#getLuces <em>Luces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Luces</em>'.
	 * @see Encore.Concierto#getLuces()
	 * @see #getConcierto()
	 * @generated
	 */
	EReference getConcierto_Luces();

	/**
	 * Returns the meta object for the containment reference list '{@link Encore.Concierto#getSecuencias <em>Secuencias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Secuencias</em>'.
	 * @see Encore.Concierto#getSecuencias()
	 * @see #getConcierto()
	 * @generated
	 */
	EReference getConcierto_Secuencias();

	/**
	 * Returns the meta object for the containment reference list '{@link Encore.Concierto#getUnionesCancionesSencuencias <em>Uniones Canciones Sencuencias</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Uniones Canciones Sencuencias</em>'.
	 * @see Encore.Concierto#getUnionesCancionesSencuencias()
	 * @see #getConcierto()
	 * @generated
	 */
	EReference getConcierto_UnionesCancionesSencuencias();

	/**
	 * Returns the meta object for the containment reference list '{@link Encore.Concierto#getUnionesSencuenciasLuces <em>Uniones Sencuencias Luces</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Uniones Sencuencias Luces</em>'.
	 * @see Encore.Concierto#getUnionesSencuenciasLuces()
	 * @see #getConcierto()
	 * @generated
	 */
	EReference getConcierto_UnionesSencuenciasLuces();

	/**
	 * Returns the meta object for class '{@link Encore.Cancion <em>Cancion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cancion</em>'.
	 * @see Encore.Cancion
	 * @generated
	 */
	EClass getCancion();

	/**
	 * Returns the meta object for the attribute '{@link Encore.Cancion#getTitulo <em>Titulo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Titulo</em>'.
	 * @see Encore.Cancion#getTitulo()
	 * @see #getCancion()
	 * @generated
	 */
	EAttribute getCancion_Titulo();

	/**
	 * Returns the meta object for the attribute '{@link Encore.Cancion#getDuracion <em>Duracion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duracion</em>'.
	 * @see Encore.Cancion#getDuracion()
	 * @see #getCancion()
	 * @generated
	 */
	EAttribute getCancion_Duracion();

	/**
	 * Returns the meta object for class '{@link Encore.Secuencia <em>Secuencia</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Secuencia</em>'.
	 * @see Encore.Secuencia
	 * @generated
	 */
	EClass getSecuencia();

	/**
	 * Returns the meta object for the attribute '{@link Encore.Secuencia#getNumero <em>Numero</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Numero</em>'.
	 * @see Encore.Secuencia#getNumero()
	 * @see #getSecuencia()
	 * @generated
	 */
	EAttribute getSecuencia_Numero();

	/**
	 * Returns the meta object for the attribute '{@link Encore.Secuencia#getModificador <em>Modificador</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Modificador</em>'.
	 * @see Encore.Secuencia#getModificador()
	 * @see #getSecuencia()
	 * @generated
	 */
	EAttribute getSecuencia_Modificador();

	/**
	 * Returns the meta object for the attribute '{@link Encore.Secuencia#getAnguloZ <em>Angulo Z</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Angulo Z</em>'.
	 * @see Encore.Secuencia#getAnguloZ()
	 * @see #getSecuencia()
	 * @generated
	 */
	EAttribute getSecuencia_AnguloZ();

	/**
	 * Returns the meta object for the attribute '{@link Encore.Secuencia#getAnguloY <em>Angulo Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Angulo Y</em>'.
	 * @see Encore.Secuencia#getAnguloY()
	 * @see #getSecuencia()
	 * @generated
	 */
	EAttribute getSecuencia_AnguloY();

	/**
	 * Returns the meta object for the attribute '{@link Encore.Secuencia#getInicio <em>Inicio</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Inicio</em>'.
	 * @see Encore.Secuencia#getInicio()
	 * @see #getSecuencia()
	 * @generated
	 */
	EAttribute getSecuencia_Inicio();

	/**
	 * Returns the meta object for the attribute '{@link Encore.Secuencia#getDuracion <em>Duracion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Duracion</em>'.
	 * @see Encore.Secuencia#getDuracion()
	 * @see #getSecuencia()
	 * @generated
	 */
	EAttribute getSecuencia_Duracion();

	/**
	 * Returns the meta object for class '{@link Encore.Luz <em>Luz</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Luz</em>'.
	 * @see Encore.Luz
	 * @generated
	 */
	EClass getLuz();

	/**
	 * Returns the meta object for the attribute '{@link Encore.Luz#getID <em>ID</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>ID</em>'.
	 * @see Encore.Luz#getID()
	 * @see #getLuz()
	 * @generated
	 */
	EAttribute getLuz_ID();

	/**
	 * Returns the meta object for class '{@link Encore.Foco <em>Foco</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Foco</em>'.
	 * @see Encore.Foco
	 * @generated
	 */
	EClass getFoco();

	/**
	 * Returns the meta object for the attribute '{@link Encore.Foco#getPotencia <em>Potencia</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Potencia</em>'.
	 * @see Encore.Foco#getPotencia()
	 * @see #getFoco()
	 * @generated
	 */
	EAttribute getFoco_Potencia();

	/**
	 * Returns the meta object for class '{@link Encore.Strobo <em>Strobo</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Strobo</em>'.
	 * @see Encore.Strobo
	 * @generated
	 */
	EClass getStrobo();

	/**
	 * Returns the meta object for the attribute '{@link Encore.Strobo#getFrecuencia <em>Frecuencia</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Frecuencia</em>'.
	 * @see Encore.Strobo#getFrecuencia()
	 * @see #getStrobo()
	 * @generated
	 */
	EAttribute getStrobo_Frecuencia();

	/**
	 * Returns the meta object for class '{@link Encore.UnionCancionSecuencia <em>Union Cancion Secuencia</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Union Cancion Secuencia</em>'.
	 * @see Encore.UnionCancionSecuencia
	 * @generated
	 */
	EClass getUnionCancionSecuencia();

	/**
	 * Returns the meta object for the reference list '{@link Encore.UnionCancionSecuencia#getCancion <em>Cancion</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Cancion</em>'.
	 * @see Encore.UnionCancionSecuencia#getCancion()
	 * @see #getUnionCancionSecuencia()
	 * @generated
	 */
	EReference getUnionCancionSecuencia_Cancion();

	/**
	 * Returns the meta object for the reference '{@link Encore.UnionCancionSecuencia#getSecuencia <em>Secuencia</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Secuencia</em>'.
	 * @see Encore.UnionCancionSecuencia#getSecuencia()
	 * @see #getUnionCancionSecuencia()
	 * @generated
	 */
	EReference getUnionCancionSecuencia_Secuencia();

	/**
	 * Returns the meta object for class '{@link Encore.UnionSecuenciaLuz <em>Union Secuencia Luz</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Union Secuencia Luz</em>'.
	 * @see Encore.UnionSecuenciaLuz
	 * @generated
	 */
	EClass getUnionSecuenciaLuz();

	/**
	 * Returns the meta object for the reference list '{@link Encore.UnionSecuenciaLuz#getSecuencia <em>Secuencia</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Secuencia</em>'.
	 * @see Encore.UnionSecuenciaLuz#getSecuencia()
	 * @see #getUnionSecuenciaLuz()
	 * @generated
	 */
	EReference getUnionSecuenciaLuz_Secuencia();

	/**
	 * Returns the meta object for the reference list '{@link Encore.UnionSecuenciaLuz#getLuz <em>Luz</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Luz</em>'.
	 * @see Encore.UnionSecuenciaLuz#getLuz()
	 * @see #getUnionSecuenciaLuz()
	 * @generated
	 */
	EReference getUnionSecuenciaLuz_Luz();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	EncoreFactory getEncoreFactory();

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
		 * The meta object literal for the '{@link Encore.impl.ConciertoImpl <em>Concierto</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Encore.impl.ConciertoImpl
		 * @see Encore.impl.EncorePackageImpl#getConcierto()
		 * @generated
		 */
		EClass CONCIERTO = eINSTANCE.getConcierto();

		/**
		 * The meta object literal for the '<em><b>Canciones</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCIERTO__CANCIONES = eINSTANCE.getConcierto_Canciones();

		/**
		 * The meta object literal for the '<em><b>Luces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCIERTO__LUCES = eINSTANCE.getConcierto_Luces();

		/**
		 * The meta object literal for the '<em><b>Secuencias</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCIERTO__SECUENCIAS = eINSTANCE.getConcierto_Secuencias();

		/**
		 * The meta object literal for the '<em><b>Uniones Canciones Sencuencias</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCIERTO__UNIONES_CANCIONES_SENCUENCIAS = eINSTANCE.getConcierto_UnionesCancionesSencuencias();

		/**
		 * The meta object literal for the '<em><b>Uniones Sencuencias Luces</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference CONCIERTO__UNIONES_SENCUENCIAS_LUCES = eINSTANCE.getConcierto_UnionesSencuenciasLuces();

		/**
		 * The meta object literal for the '{@link Encore.impl.CancionImpl <em>Cancion</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Encore.impl.CancionImpl
		 * @see Encore.impl.EncorePackageImpl#getCancion()
		 * @generated
		 */
		EClass CANCION = eINSTANCE.getCancion();

		/**
		 * The meta object literal for the '<em><b>Titulo</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CANCION__TITULO = eINSTANCE.getCancion_Titulo();

		/**
		 * The meta object literal for the '<em><b>Duracion</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute CANCION__DURACION = eINSTANCE.getCancion_Duracion();

		/**
		 * The meta object literal for the '{@link Encore.impl.SecuenciaImpl <em>Secuencia</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Encore.impl.SecuenciaImpl
		 * @see Encore.impl.EncorePackageImpl#getSecuencia()
		 * @generated
		 */
		EClass SECUENCIA = eINSTANCE.getSecuencia();

		/**
		 * The meta object literal for the '<em><b>Numero</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECUENCIA__NUMERO = eINSTANCE.getSecuencia_Numero();

		/**
		 * The meta object literal for the '<em><b>Modificador</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECUENCIA__MODIFICADOR = eINSTANCE.getSecuencia_Modificador();

		/**
		 * The meta object literal for the '<em><b>Angulo Z</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECUENCIA__ANGULO_Z = eINSTANCE.getSecuencia_AnguloZ();

		/**
		 * The meta object literal for the '<em><b>Angulo Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECUENCIA__ANGULO_Y = eINSTANCE.getSecuencia_AnguloY();

		/**
		 * The meta object literal for the '<em><b>Inicio</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECUENCIA__INICIO = eINSTANCE.getSecuencia_Inicio();

		/**
		 * The meta object literal for the '<em><b>Duracion</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute SECUENCIA__DURACION = eINSTANCE.getSecuencia_Duracion();

		/**
		 * The meta object literal for the '{@link Encore.impl.LuzImpl <em>Luz</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Encore.impl.LuzImpl
		 * @see Encore.impl.EncorePackageImpl#getLuz()
		 * @generated
		 */
		EClass LUZ = eINSTANCE.getLuz();

		/**
		 * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute LUZ__ID = eINSTANCE.getLuz_ID();

		/**
		 * The meta object literal for the '{@link Encore.impl.FocoImpl <em>Foco</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Encore.impl.FocoImpl
		 * @see Encore.impl.EncorePackageImpl#getFoco()
		 * @generated
		 */
		EClass FOCO = eINSTANCE.getFoco();

		/**
		 * The meta object literal for the '<em><b>Potencia</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOCO__POTENCIA = eINSTANCE.getFoco_Potencia();

		/**
		 * The meta object literal for the '{@link Encore.impl.StroboImpl <em>Strobo</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Encore.impl.StroboImpl
		 * @see Encore.impl.EncorePackageImpl#getStrobo()
		 * @generated
		 */
		EClass STROBO = eINSTANCE.getStrobo();

		/**
		 * The meta object literal for the '<em><b>Frecuencia</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STROBO__FRECUENCIA = eINSTANCE.getStrobo_Frecuencia();

		/**
		 * The meta object literal for the '{@link Encore.impl.UnionCancionSecuenciaImpl <em>Union Cancion Secuencia</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Encore.impl.UnionCancionSecuenciaImpl
		 * @see Encore.impl.EncorePackageImpl#getUnionCancionSecuencia()
		 * @generated
		 */
		EClass UNION_CANCION_SECUENCIA = eINSTANCE.getUnionCancionSecuencia();

		/**
		 * The meta object literal for the '<em><b>Cancion</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNION_CANCION_SECUENCIA__CANCION = eINSTANCE.getUnionCancionSecuencia_Cancion();

		/**
		 * The meta object literal for the '<em><b>Secuencia</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNION_CANCION_SECUENCIA__SECUENCIA = eINSTANCE.getUnionCancionSecuencia_Secuencia();

		/**
		 * The meta object literal for the '{@link Encore.impl.UnionSecuenciaLuzImpl <em>Union Secuencia Luz</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see Encore.impl.UnionSecuenciaLuzImpl
		 * @see Encore.impl.EncorePackageImpl#getUnionSecuenciaLuz()
		 * @generated
		 */
		EClass UNION_SECUENCIA_LUZ = eINSTANCE.getUnionSecuenciaLuz();

		/**
		 * The meta object literal for the '<em><b>Secuencia</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNION_SECUENCIA_LUZ__SECUENCIA = eINSTANCE.getUnionSecuenciaLuz_Secuencia();

		/**
		 * The meta object literal for the '<em><b>Luz</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference UNION_SECUENCIA_LUZ__LUZ = eINSTANCE.getUnionSecuenciaLuz_Luz();

	}

} //EncorePackage
