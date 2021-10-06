/**
 */
package Encore.impl;

import Encore.Cancion;
import Encore.Concierto;
import Encore.EncorePackage;
import Encore.Luz;

import Encore.Secuencia;
import Encore.UnionCancionSecuencia;
import Encore.UnionSecuenciaLuz;
import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Concierto</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Encore.impl.ConciertoImpl#getCanciones <em>Canciones</em>}</li>
 *   <li>{@link Encore.impl.ConciertoImpl#getLuces <em>Luces</em>}</li>
 *   <li>{@link Encore.impl.ConciertoImpl#getSecuencias <em>Secuencias</em>}</li>
 *   <li>{@link Encore.impl.ConciertoImpl#getUnionesCancionesSencuencias <em>Uniones Canciones Sencuencias</em>}</li>
 *   <li>{@link Encore.impl.ConciertoImpl#getUnionesSencuenciasLuces <em>Uniones Sencuencias Luces</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConciertoImpl extends EObjectImpl implements Concierto {
	/**
	 * The cached value of the '{@link #getCanciones() <em>Canciones</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCanciones()
	 * @generated
	 * @ordered
	 */
	protected EList<Cancion> canciones;

	/**
	 * The cached value of the '{@link #getLuces() <em>Luces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLuces()
	 * @generated
	 * @ordered
	 */
	protected EList<Luz> luces;

	/**
	 * The cached value of the '{@link #getSecuencias() <em>Secuencias</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecuencias()
	 * @generated
	 * @ordered
	 */
	protected EList<Secuencia> secuencias;

	/**
	 * The cached value of the '{@link #getUnionesCancionesSencuencias() <em>Uniones Canciones Sencuencias</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnionesCancionesSencuencias()
	 * @generated
	 * @ordered
	 */
	protected EList<UnionCancionSecuencia> unionesCancionesSencuencias;

	/**
	 * The cached value of the '{@link #getUnionesSencuenciasLuces() <em>Uniones Sencuencias Luces</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getUnionesSencuenciasLuces()
	 * @generated
	 * @ordered
	 */
	protected EList<UnionSecuenciaLuz> unionesSencuenciasLuces;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConciertoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EncorePackage.Literals.CONCIERTO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Cancion> getCanciones() {
		if (canciones == null) {
			canciones = new EObjectContainmentEList<Cancion>(Cancion.class, this, EncorePackage.CONCIERTO__CANCIONES);
		}
		return canciones;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Luz> getLuces() {
		if (luces == null) {
			luces = new EObjectContainmentEList<Luz>(Luz.class, this, EncorePackage.CONCIERTO__LUCES);
		}
		return luces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Secuencia> getSecuencias() {
		if (secuencias == null) {
			secuencias = new EObjectContainmentEList<Secuencia>(Secuencia.class, this, EncorePackage.CONCIERTO__SECUENCIAS);
		}
		return secuencias;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UnionCancionSecuencia> getUnionesCancionesSencuencias() {
		if (unionesCancionesSencuencias == null) {
			unionesCancionesSencuencias = new EObjectContainmentEList<UnionCancionSecuencia>(UnionCancionSecuencia.class, this, EncorePackage.CONCIERTO__UNIONES_CANCIONES_SENCUENCIAS);
		}
		return unionesCancionesSencuencias;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<UnionSecuenciaLuz> getUnionesSencuenciasLuces() {
		if (unionesSencuenciasLuces == null) {
			unionesSencuenciasLuces = new EObjectContainmentEList<UnionSecuenciaLuz>(UnionSecuenciaLuz.class, this, EncorePackage.CONCIERTO__UNIONES_SENCUENCIAS_LUCES);
		}
		return unionesSencuenciasLuces;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case EncorePackage.CONCIERTO__CANCIONES:
				return ((InternalEList<?>)getCanciones()).basicRemove(otherEnd, msgs);
			case EncorePackage.CONCIERTO__LUCES:
				return ((InternalEList<?>)getLuces()).basicRemove(otherEnd, msgs);
			case EncorePackage.CONCIERTO__SECUENCIAS:
				return ((InternalEList<?>)getSecuencias()).basicRemove(otherEnd, msgs);
			case EncorePackage.CONCIERTO__UNIONES_CANCIONES_SENCUENCIAS:
				return ((InternalEList<?>)getUnionesCancionesSencuencias()).basicRemove(otherEnd, msgs);
			case EncorePackage.CONCIERTO__UNIONES_SENCUENCIAS_LUCES:
				return ((InternalEList<?>)getUnionesSencuenciasLuces()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EncorePackage.CONCIERTO__CANCIONES:
				return getCanciones();
			case EncorePackage.CONCIERTO__LUCES:
				return getLuces();
			case EncorePackage.CONCIERTO__SECUENCIAS:
				return getSecuencias();
			case EncorePackage.CONCIERTO__UNIONES_CANCIONES_SENCUENCIAS:
				return getUnionesCancionesSencuencias();
			case EncorePackage.CONCIERTO__UNIONES_SENCUENCIAS_LUCES:
				return getUnionesSencuenciasLuces();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EncorePackage.CONCIERTO__CANCIONES:
				getCanciones().clear();
				getCanciones().addAll((Collection<? extends Cancion>)newValue);
				return;
			case EncorePackage.CONCIERTO__LUCES:
				getLuces().clear();
				getLuces().addAll((Collection<? extends Luz>)newValue);
				return;
			case EncorePackage.CONCIERTO__SECUENCIAS:
				getSecuencias().clear();
				getSecuencias().addAll((Collection<? extends Secuencia>)newValue);
				return;
			case EncorePackage.CONCIERTO__UNIONES_CANCIONES_SENCUENCIAS:
				getUnionesCancionesSencuencias().clear();
				getUnionesCancionesSencuencias().addAll((Collection<? extends UnionCancionSecuencia>)newValue);
				return;
			case EncorePackage.CONCIERTO__UNIONES_SENCUENCIAS_LUCES:
				getUnionesSencuenciasLuces().clear();
				getUnionesSencuenciasLuces().addAll((Collection<? extends UnionSecuenciaLuz>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case EncorePackage.CONCIERTO__CANCIONES:
				getCanciones().clear();
				return;
			case EncorePackage.CONCIERTO__LUCES:
				getLuces().clear();
				return;
			case EncorePackage.CONCIERTO__SECUENCIAS:
				getSecuencias().clear();
				return;
			case EncorePackage.CONCIERTO__UNIONES_CANCIONES_SENCUENCIAS:
				getUnionesCancionesSencuencias().clear();
				return;
			case EncorePackage.CONCIERTO__UNIONES_SENCUENCIAS_LUCES:
				getUnionesSencuenciasLuces().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case EncorePackage.CONCIERTO__CANCIONES:
				return canciones != null && !canciones.isEmpty();
			case EncorePackage.CONCIERTO__LUCES:
				return luces != null && !luces.isEmpty();
			case EncorePackage.CONCIERTO__SECUENCIAS:
				return secuencias != null && !secuencias.isEmpty();
			case EncorePackage.CONCIERTO__UNIONES_CANCIONES_SENCUENCIAS:
				return unionesCancionesSencuencias != null && !unionesCancionesSencuencias.isEmpty();
			case EncorePackage.CONCIERTO__UNIONES_SENCUENCIAS_LUCES:
				return unionesSencuenciasLuces != null && !unionesSencuenciasLuces.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //ConciertoImpl
