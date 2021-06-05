/**
 */
package Practica2.impl;

import Practica2.Arista;
import Practica2.Diagrama;
import Practica2.Nodo;
import Practica2.Practica2Package;

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
 * An implementation of the model object '<em><b>Diagrama</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Practica2.impl.DiagramaImpl#getNodos <em>Nodos</em>}</li>
 *   <li>{@link Practica2.impl.DiagramaImpl#getAristas <em>Aristas</em>}</li>
 * </ul>
 *
 * @generated
 */
public class DiagramaImpl extends EObjectImpl implements Diagrama {
	/**
	 * The cached value of the '{@link #getNodos() <em>Nodos</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNodos()
	 * @generated
	 * @ordered
	 */
	protected EList<Nodo> nodos;

	/**
	 * The cached value of the '{@link #getAristas() <em>Aristas</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAristas()
	 * @generated
	 * @ordered
	 */
	protected EList<Arista> aristas;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected DiagramaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Practica2Package.Literals.DIAGRAMA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Nodo> getNodos() {
		if (nodos == null) {
			nodos = new EObjectContainmentEList<Nodo>(Nodo.class, this, Practica2Package.DIAGRAMA__NODOS);
		}
		return nodos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Arista> getAristas() {
		if (aristas == null) {
			aristas = new EObjectContainmentEList<Arista>(Arista.class, this, Practica2Package.DIAGRAMA__ARISTAS);
		}
		return aristas;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Practica2Package.DIAGRAMA__NODOS:
				return ((InternalEList<?>)getNodos()).basicRemove(otherEnd, msgs);
			case Practica2Package.DIAGRAMA__ARISTAS:
				return ((InternalEList<?>)getAristas()).basicRemove(otherEnd, msgs);
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
			case Practica2Package.DIAGRAMA__NODOS:
				return getNodos();
			case Practica2Package.DIAGRAMA__ARISTAS:
				return getAristas();
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
			case Practica2Package.DIAGRAMA__NODOS:
				getNodos().clear();
				getNodos().addAll((Collection<? extends Nodo>)newValue);
				return;
			case Practica2Package.DIAGRAMA__ARISTAS:
				getAristas().clear();
				getAristas().addAll((Collection<? extends Arista>)newValue);
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
			case Practica2Package.DIAGRAMA__NODOS:
				getNodos().clear();
				return;
			case Practica2Package.DIAGRAMA__ARISTAS:
				getAristas().clear();
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
			case Practica2Package.DIAGRAMA__NODOS:
				return nodos != null && !nodos.isEmpty();
			case Practica2Package.DIAGRAMA__ARISTAS:
				return aristas != null && !aristas.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //DiagramaImpl
