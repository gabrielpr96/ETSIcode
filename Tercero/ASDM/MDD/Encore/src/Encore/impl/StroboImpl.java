/**
 */
package Encore.impl;

import Encore.EncorePackage;
import Encore.Strobo;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Strobo</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Encore.impl.StroboImpl#getFrecuencia <em>Frecuencia</em>}</li>
 * </ul>
 *
 * @generated
 */
public class StroboImpl extends LuzImpl implements Strobo {
	/**
	 * The default value of the '{@link #getFrecuencia() <em>Frecuencia</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrecuencia()
	 * @generated
	 * @ordered
	 */
	protected static final float FRECUENCIA_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getFrecuencia() <em>Frecuencia</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFrecuencia()
	 * @generated
	 * @ordered
	 */
	protected float frecuencia = FRECUENCIA_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected StroboImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EncorePackage.Literals.STROBO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getFrecuencia() {
		return frecuencia;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setFrecuencia(float newFrecuencia) {
		float oldFrecuencia = frecuencia;
		frecuencia = newFrecuencia;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EncorePackage.STROBO__FRECUENCIA, oldFrecuencia, frecuencia));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EncorePackage.STROBO__FRECUENCIA:
				return getFrecuencia();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case EncorePackage.STROBO__FRECUENCIA:
				setFrecuencia((Float)newValue);
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
			case EncorePackage.STROBO__FRECUENCIA:
				setFrecuencia(FRECUENCIA_EDEFAULT);
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
			case EncorePackage.STROBO__FRECUENCIA:
				return frecuencia != FRECUENCIA_EDEFAULT;
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuilder result = new StringBuilder(super.toString());
		result.append(" (Frecuencia: ");
		result.append(frecuencia);
		result.append(')');
		return result.toString();
	}

} //StroboImpl
