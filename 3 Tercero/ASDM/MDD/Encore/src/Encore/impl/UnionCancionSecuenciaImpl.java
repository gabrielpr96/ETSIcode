/**
 */
package Encore.impl;

import Encore.Cancion;
import Encore.EncorePackage;
import Encore.Secuencia;
import Encore.UnionCancionSecuencia;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Union Cancion Secuencia</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Encore.impl.UnionCancionSecuenciaImpl#getCancion <em>Cancion</em>}</li>
 *   <li>{@link Encore.impl.UnionCancionSecuenciaImpl#getSecuencia <em>Secuencia</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UnionCancionSecuenciaImpl extends EObjectImpl implements UnionCancionSecuencia {
	/**
	 * The cached value of the '{@link #getCancion() <em>Cancion</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCancion()
	 * @generated
	 * @ordered
	 */
	protected EList<Cancion> cancion;
	/**
	 * The cached value of the '{@link #getSecuencia() <em>Secuencia</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecuencia()
	 * @generated
	 * @ordered
	 */
	protected Secuencia secuencia;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnionCancionSecuenciaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EncorePackage.Literals.UNION_CANCION_SECUENCIA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Cancion> getCancion() {
		if (cancion == null) {
			cancion = new EObjectResolvingEList<Cancion>(Cancion.class, this, EncorePackage.UNION_CANCION_SECUENCIA__CANCION);
		}
		return cancion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Secuencia getSecuencia() {
		if (secuencia != null && secuencia.eIsProxy()) {
			InternalEObject oldSecuencia = (InternalEObject)secuencia;
			secuencia = (Secuencia)eResolveProxy(oldSecuencia);
			if (secuencia != oldSecuencia) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, EncorePackage.UNION_CANCION_SECUENCIA__SECUENCIA, oldSecuencia, secuencia));
			}
		}
		return secuencia;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Secuencia basicGetSecuencia() {
		return secuencia;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSecuencia(Secuencia newSecuencia) {
		Secuencia oldSecuencia = secuencia;
		secuencia = newSecuencia;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EncorePackage.UNION_CANCION_SECUENCIA__SECUENCIA, oldSecuencia, secuencia));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EncorePackage.UNION_CANCION_SECUENCIA__CANCION:
				return getCancion();
			case EncorePackage.UNION_CANCION_SECUENCIA__SECUENCIA:
				if (resolve) return getSecuencia();
				return basicGetSecuencia();
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
			case EncorePackage.UNION_CANCION_SECUENCIA__CANCION:
				getCancion().clear();
				getCancion().addAll((Collection<? extends Cancion>)newValue);
				return;
			case EncorePackage.UNION_CANCION_SECUENCIA__SECUENCIA:
				setSecuencia((Secuencia)newValue);
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
			case EncorePackage.UNION_CANCION_SECUENCIA__CANCION:
				getCancion().clear();
				return;
			case EncorePackage.UNION_CANCION_SECUENCIA__SECUENCIA:
				setSecuencia((Secuencia)null);
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
			case EncorePackage.UNION_CANCION_SECUENCIA__CANCION:
				return cancion != null && !cancion.isEmpty();
			case EncorePackage.UNION_CANCION_SECUENCIA__SECUENCIA:
				return secuencia != null;
		}
		return super.eIsSet(featureID);
	}

} //UnionCancionSecuenciaImpl
