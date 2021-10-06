/**
 */
package Encore;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Concierto</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link Encore.Concierto#getCanciones <em>Canciones</em>}</li>
 *   <li>{@link Encore.Concierto#getLuces <em>Luces</em>}</li>
 *   <li>{@link Encore.Concierto#getSecuencias <em>Secuencias</em>}</li>
 *   <li>{@link Encore.Concierto#getUnionesCancionesSencuencias <em>Uniones Canciones Sencuencias</em>}</li>
 *   <li>{@link Encore.Concierto#getUnionesSencuenciasLuces <em>Uniones Sencuencias Luces</em>}</li>
 * </ul>
 *
 * @see Encore.EncorePackage#getConcierto()
 * @model annotation="gmf.diagram foo='bar'"
 * @generated
 */
public interface Concierto extends EObject {
	/**
	 * Returns the value of the '<em><b>Canciones</b></em>' containment reference list.
	 * The list contents are of type {@link Encore.Cancion}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Canciones</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Canciones</em>' containment reference list.
	 * @see Encore.EncorePackage#getConcierto_Canciones()
	 * @model containment="true"
	 * @generated
	 */
	EList<Cancion> getCanciones();

	/**
	 * Returns the value of the '<em><b>Luces</b></em>' containment reference list.
	 * The list contents are of type {@link Encore.Luz}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Luces</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Luces</em>' containment reference list.
	 * @see Encore.EncorePackage#getConcierto_Luces()
	 * @model containment="true"
	 * @generated
	 */
	EList<Luz> getLuces();

	/**
	 * Returns the value of the '<em><b>Secuencias</b></em>' containment reference list.
	 * The list contents are of type {@link Encore.Secuencia}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Secuencias</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Secuencias</em>' containment reference list.
	 * @see Encore.EncorePackage#getConcierto_Secuencias()
	 * @model containment="true"
	 * @generated
	 */
	EList<Secuencia> getSecuencias();

	/**
	 * Returns the value of the '<em><b>Uniones Canciones Sencuencias</b></em>' containment reference list.
	 * The list contents are of type {@link Encore.UnionCancionSecuencia}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uniones Canciones Sencuencias</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uniones Canciones Sencuencias</em>' containment reference list.
	 * @see Encore.EncorePackage#getConcierto_UnionesCancionesSencuencias()
	 * @model containment="true"
	 * @generated
	 */
	EList<UnionCancionSecuencia> getUnionesCancionesSencuencias();

	/**
	 * Returns the value of the '<em><b>Uniones Sencuencias Luces</b></em>' containment reference list.
	 * The list contents are of type {@link Encore.UnionSecuenciaLuz}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Uniones Sencuencias Luces</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Uniones Sencuencias Luces</em>' containment reference list.
	 * @see Encore.EncorePackage#getConcierto_UnionesSencuenciasLuces()
	 * @model containment="true"
	 * @generated
	 */
	EList<UnionSecuenciaLuz> getUnionesSencuenciasLuces();

} // Concierto
