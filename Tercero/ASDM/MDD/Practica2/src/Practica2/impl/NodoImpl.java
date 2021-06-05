/**
 */
package Practica2.impl;

import Practica2.Arista;
import Practica2.Nodo;
import Practica2.Practica2Package;

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
 *   <li>{@link Practica2.impl.NodoImpl#getSalientes <em>Salientes</em>}</li>
 *   <li>{@link Practica2.impl.NodoImpl#getEntrantes <em>Entrantes</em>}</li>
 * </ul>
 *
 * @generated
 */
public abstract class NodoImpl extends EObjectImpl implements Nodo {
	/**
	 * The cached value of the '{@link #getSalientes() <em>Salientes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSalientes()
	 * @generated
	 * @ordered
	 */
	protected EList<Arista> salientes;

	/**
	 * The cached value of the '{@link #getEntrantes() <em>Entrantes</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getEntrantes()
	 * @generated
	 * @ordered
	 */
	protected EList<Arista> entrantes;

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
		return Practica2Package.Literals.NODO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Arista> getSalientes() {
		if (salientes == null) {
			salientes = new EObjectWithInverseResolvingEList<Arista>(Arista.class, this, Practica2Package.NODO__SALIENTES, Practica2Package.ARISTA__ORIGEN);
		}
		return salientes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Arista> getEntrantes() {
		if (entrantes == null) {
			entrantes = new EObjectWithInverseResolvingEList<Arista>(Arista.class, this, Practica2Package.NODO__ENTRANTES, Practica2Package.ARISTA__DESTINO);
		}
		return entrantes;
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
			case Practica2Package.NODO__SALIENTES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getSalientes()).basicAdd(otherEnd, msgs);
			case Practica2Package.NODO__ENTRANTES:
				return ((InternalEList<InternalEObject>)(InternalEList<?>)getEntrantes()).basicAdd(otherEnd, msgs);
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
			case Practica2Package.NODO__SALIENTES:
				return ((InternalEList<?>)getSalientes()).basicRemove(otherEnd, msgs);
			case Practica2Package.NODO__ENTRANTES:
				return ((InternalEList<?>)getEntrantes()).basicRemove(otherEnd, msgs);
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
			case Practica2Package.NODO__SALIENTES:
				return getSalientes();
			case Practica2Package.NODO__ENTRANTES:
				return getEntrantes();
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
			case Practica2Package.NODO__SALIENTES:
				getSalientes().clear();
				getSalientes().addAll((Collection<? extends Arista>)newValue);
				return;
			case Practica2Package.NODO__ENTRANTES:
				getEntrantes().clear();
				getEntrantes().addAll((Collection<? extends Arista>)newValue);
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
			case Practica2Package.NODO__SALIENTES:
				getSalientes().clear();
				return;
			case Practica2Package.NODO__ENTRANTES:
				getEntrantes().clear();
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
			case Practica2Package.NODO__SALIENTES:
				return salientes != null && !salientes.isEmpty();
			case Practica2Package.NODO__ENTRANTES:
				return entrantes != null && !entrantes.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //NodoImpl
