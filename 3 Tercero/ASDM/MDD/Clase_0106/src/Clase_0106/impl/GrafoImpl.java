/**
 */
package Clase_0106.impl;

import Clase_0106.Clase_0106Package;
import Clase_0106.Conexion;
import Clase_0106.Grafo;
import Clase_0106.Nodo;

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
 * An implementation of the model object '<em><b>Grafo</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Clase_0106.impl.GrafoImpl#getConexiones <em>Conexiones</em>}</li>
 *   <li>{@link Clase_0106.impl.GrafoImpl#getNodos <em>Nodos</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GrafoImpl extends EObjectImpl implements Grafo {
	/**
	 * The cached value of the '{@link #getConexiones() <em>Conexiones</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getConexiones()
	 * @generated
	 * @ordered
	 */
	protected EList<Conexion> conexiones;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected GrafoImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Clase_0106Package.Literals.GRAFO;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Conexion> getConexiones() {
		if (conexiones == null) {
			conexiones = new EObjectContainmentEList<Conexion>(Conexion.class, this, Clase_0106Package.GRAFO__CONEXIONES);
		}
		return conexiones;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Nodo> getNodos() {
		if (nodos == null) {
			nodos = new EObjectContainmentEList<Nodo>(Nodo.class, this, Clase_0106Package.GRAFO__NODOS);
		}
		return nodos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Clase_0106Package.GRAFO__CONEXIONES:
				return ((InternalEList<?>)getConexiones()).basicRemove(otherEnd, msgs);
			case Clase_0106Package.GRAFO__NODOS:
				return ((InternalEList<?>)getNodos()).basicRemove(otherEnd, msgs);
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
			case Clase_0106Package.GRAFO__CONEXIONES:
				return getConexiones();
			case Clase_0106Package.GRAFO__NODOS:
				return getNodos();
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
			case Clase_0106Package.GRAFO__CONEXIONES:
				getConexiones().clear();
				getConexiones().addAll((Collection<? extends Conexion>)newValue);
				return;
			case Clase_0106Package.GRAFO__NODOS:
				getNodos().clear();
				getNodos().addAll((Collection<? extends Nodo>)newValue);
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
			case Clase_0106Package.GRAFO__CONEXIONES:
				getConexiones().clear();
				return;
			case Clase_0106Package.GRAFO__NODOS:
				getNodos().clear();
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
			case Clase_0106Package.GRAFO__CONEXIONES:
				return conexiones != null && !conexiones.isEmpty();
			case Clase_0106Package.GRAFO__NODOS:
				return nodos != null && !nodos.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //GrafoImpl
