/**
 */
package Clase_0106.impl;

import Clase_0106.Clase_0106Package;
import Clase_0106.Conexion;
import Clase_0106.Nodo;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Nodo</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Clase_0106.impl.NodoImpl#getEntrante <em>Entrante</em>}</li>
 *   <li>{@link Clase_0106.impl.NodoImpl#getSaliente <em>Saliente</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class NodoImpl extends EObjectImpl implements Nodo {
	/**
	 * The cached value of the '{@link #getEntrante() <em>Entrante</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntrante()
	 * @generated
	 * @ordered
	 */
	protected EList<Conexion> entrante;

	/**
	 * The cached value of the '{@link #getSaliente() <em>Saliente</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSaliente()
	 * @generated
	 * @ordered
	 */
	protected EList<Conexion> saliente;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected NodoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Clase_0106Package.Literals.NODO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Conexion> getEntrante() {
		if (entrante == null) {
			entrante = new EObjectWithInverseResolvingEList<Conexion>(Conexion.class, this, Clase_0106Package.NODO__ENTRANTE, Clase_0106Package.CONEXION__DESTINO);
		}
		return entrante;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Conexion> getSaliente() {
		if (saliente == null) {
			saliente = new EObjectWithInverseResolvingEList<Conexion>(Conexion.class, this, Clase_0106Package.NODO__SALIENTE, Clase_0106Package.CONEXION__ORIGEN);
		}
		return saliente;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Clase_0106Package.NODO__ENTRANTE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEntrante()).basicAdd(otherEnd, msgs);
			case Clase_0106Package.NODO__SALIENTE:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSaliente()).basicAdd(otherEnd, msgs);
		}
		return super.eInverseAdd(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Clase_0106Package.NODO__ENTRANTE:
				return ((InternalEList<?>)getEntrante()).basicRemove(otherEnd, msgs);
			case Clase_0106Package.NODO__SALIENTE:
				return ((InternalEList<?>)getSaliente()).basicRemove(otherEnd, msgs);
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
			case Clase_0106Package.NODO__ENTRANTE:
				return getEntrante();
			case Clase_0106Package.NODO__SALIENTE:
				return getSaliente();
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
			case Clase_0106Package.NODO__ENTRANTE:
				getEntrante().clear();
				getEntrante().addAll((Collection<? extends Conexion>)newValue);
				return;
			case Clase_0106Package.NODO__SALIENTE:
				getSaliente().clear();
				getSaliente().addAll((Collection<? extends Conexion>)newValue);
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
			case Clase_0106Package.NODO__ENTRANTE:
				getEntrante().clear();
				return;
			case Clase_0106Package.NODO__SALIENTE:
				getSaliente().clear();
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
			case Clase_0106Package.NODO__ENTRANTE:
				return entrante != null && !entrante.isEmpty();
			case Clase_0106Package.NODO__SALIENTE:
				return saliente != null && !saliente.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //NodoImpl
