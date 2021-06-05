/**
 */
package Encore.impl;

import Encore.EncorePackage;
import Encore.Luz;
import Encore.Secuencia;
import Encore.UnionSecuenciaLuz;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Union Secuencia Luz</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Encore.impl.UnionSecuenciaLuzImpl#getSecuencia <em>Secuencia</em>}</li>
 *   <li>{@link Encore.impl.UnionSecuenciaLuzImpl#getLuz <em>Luz</em>}</li>
 * </ul>
 *
 * @generated
 */
public class UnionSecuenciaLuzImpl extends EObjectImpl implements UnionSecuenciaLuz {
	/**
	 * The cached value of the '{@link #getSecuencia() <em>Secuencia</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSecuencia()
	 * @generated
	 * @ordered
	 */
	protected EList<Secuencia> secuencia;
	/**
	 * The cached value of the '{@link #getLuz() <em>Luz</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLuz()
	 * @generated
	 * @ordered
	 */
	protected EList<Luz> luz;
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected UnionSecuenciaLuzImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EncorePackage.Literals.UNION_SECUENCIA_LUZ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Secuencia> getSecuencia() {
		if (secuencia == null) {
			secuencia = new EObjectResolvingEList<Secuencia>(Secuencia.class, this, EncorePackage.UNION_SECUENCIA_LUZ__SECUENCIA);
		}
		return secuencia;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Luz> getLuz() {
		if (luz == null) {
			luz = new EObjectResolvingEList<Luz>(Luz.class, this, EncorePackage.UNION_SECUENCIA_LUZ__LUZ);
		}
		return luz;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EncorePackage.UNION_SECUENCIA_LUZ__SECUENCIA:
				return getSecuencia();
			case EncorePackage.UNION_SECUENCIA_LUZ__LUZ:
				return getLuz();
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
			case EncorePackage.UNION_SECUENCIA_LUZ__SECUENCIA:
				getSecuencia().clear();
				getSecuencia().addAll((Collection<? extends Secuencia>)newValue);
				return;
			case EncorePackage.UNION_SECUENCIA_LUZ__LUZ:
				getLuz().clear();
				getLuz().addAll((Collection<? extends Luz>)newValue);
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
			case EncorePackage.UNION_SECUENCIA_LUZ__SECUENCIA:
				getSecuencia().clear();
				return;
			case EncorePackage.UNION_SECUENCIA_LUZ__LUZ:
				getLuz().clear();
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
			case EncorePackage.UNION_SECUENCIA_LUZ__SECUENCIA:
				return secuencia != null && !secuencia.isEmpty();
			case EncorePackage.UNION_SECUENCIA_LUZ__LUZ:
				return luz != null && !luz.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //UnionSecuenciaLuzImpl
