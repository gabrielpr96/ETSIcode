/**
 */
package Clase_0106.impl;

import Clase_0106.Clase_0106Package;
import Clase_0106.Conexion;
import Clase_0106.Nodo;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Conexion</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Clase_0106.impl.ConexionImpl#getNombre <em>Nombre</em>}</li>
 *   <li>{@link Clase_0106.impl.ConexionImpl#getOrigen <em>Origen</em>}</li>
 *   <li>{@link Clase_0106.impl.ConexionImpl#getDestino <em>Destino</em>}</li>
 * </ul>
 *
 * @generated
 */
public class ConexionImpl extends EObjectImpl implements Conexion {
	/**
	 * The default value of the '{@link #getNombre() <em>Nombre</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNombre()
	 * @generated
	 * @ordered
	 */
	protected static final String NOMBRE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getNombre() <em>Nombre</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNombre()
	 * @generated
	 * @ordered
	 */
	protected String nombre = NOMBRE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getOrigen() <em>Origen</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOrigen()
	 * @generated
	 * @ordered
	 */
	protected Nodo origen;

	/**
	 * The cached value of the '{@link #getDestino() <em>Destino</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestino()
	 * @generated
	 * @ordered
	 */
	protected Nodo destino;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ConexionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Clase_0106Package.Literals.CONEXION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNombre(String newNombre) {
		String oldNombre = nombre;
		nombre = newNombre;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Clase_0106Package.CONEXION__NOMBRE, oldNombre, nombre));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Nodo getOrigen() {
		if (origen != null && origen.eIsProxy()) {
			InternalEObject oldOrigen = (InternalEObject)origen;
			origen = (Nodo)eResolveProxy(oldOrigen);
			if (origen != oldOrigen) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Clase_0106Package.CONEXION__ORIGEN, oldOrigen, origen));
			}
		}
		return origen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Nodo basicGetOrigen() {
		return origen;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetOrigen(Nodo newOrigen, NotificationChain msgs) {
		Nodo oldOrigen = origen;
		origen = newOrigen;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Clase_0106Package.CONEXION__ORIGEN, oldOrigen, newOrigen);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOrigen(Nodo newOrigen) {
		if (newOrigen != origen) {
			NotificationChain msgs = null;
			if (origen != null)
				msgs = ((InternalEObject)origen).eInverseRemove(this, Clase_0106Package.NODO__SALIENTE, Nodo.class, msgs);
			if (newOrigen != null)
				msgs = ((InternalEObject)newOrigen).eInverseAdd(this, Clase_0106Package.NODO__SALIENTE, Nodo.class, msgs);
			msgs = basicSetOrigen(newOrigen, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Clase_0106Package.CONEXION__ORIGEN, newOrigen, newOrigen));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Nodo getDestino() {
		if (destino != null && destino.eIsProxy()) {
			InternalEObject oldDestino = (InternalEObject)destino;
			destino = (Nodo)eResolveProxy(oldDestino);
			if (destino != oldDestino) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Clase_0106Package.CONEXION__DESTINO, oldDestino, destino));
			}
		}
		return destino;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Nodo basicGetDestino() {
		return destino;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDestino(Nodo newDestino, NotificationChain msgs) {
		Nodo oldDestino = destino;
		destino = newDestino;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Clase_0106Package.CONEXION__DESTINO, oldDestino, newDestino);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDestino(Nodo newDestino) {
		if (newDestino != destino) {
			NotificationChain msgs = null;
			if (destino != null)
				msgs = ((InternalEObject)destino).eInverseRemove(this, Clase_0106Package.NODO__ENTRANTE, Nodo.class, msgs);
			if (newDestino != null)
				msgs = ((InternalEObject)newDestino).eInverseAdd(this, Clase_0106Package.NODO__ENTRANTE, Nodo.class, msgs);
			msgs = basicSetDestino(newDestino, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Clase_0106Package.CONEXION__DESTINO, newDestino, newDestino));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Clase_0106Package.CONEXION__ORIGEN:
				if (origen != null)
					msgs = ((InternalEObject)origen).eInverseRemove(this, Clase_0106Package.NODO__SALIENTE, Nodo.class, msgs);
				return basicSetOrigen((Nodo)otherEnd, msgs);
			case Clase_0106Package.CONEXION__DESTINO:
				if (destino != null)
					msgs = ((InternalEObject)destino).eInverseRemove(this, Clase_0106Package.NODO__ENTRANTE, Nodo.class, msgs);
				return basicSetDestino((Nodo)otherEnd, msgs);
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
			case Clase_0106Package.CONEXION__ORIGEN:
				return basicSetOrigen(null, msgs);
			case Clase_0106Package.CONEXION__DESTINO:
				return basicSetDestino(null, msgs);
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
			case Clase_0106Package.CONEXION__NOMBRE:
				return getNombre();
			case Clase_0106Package.CONEXION__ORIGEN:
				if (resolve) return getOrigen();
				return basicGetOrigen();
			case Clase_0106Package.CONEXION__DESTINO:
				if (resolve) return getDestino();
				return basicGetDestino();
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
			case Clase_0106Package.CONEXION__NOMBRE:
				setNombre((String)newValue);
				return;
			case Clase_0106Package.CONEXION__ORIGEN:
				setOrigen((Nodo)newValue);
				return;
			case Clase_0106Package.CONEXION__DESTINO:
				setDestino((Nodo)newValue);
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
			case Clase_0106Package.CONEXION__NOMBRE:
				setNombre(NOMBRE_EDEFAULT);
				return;
			case Clase_0106Package.CONEXION__ORIGEN:
				setOrigen((Nodo)null);
				return;
			case Clase_0106Package.CONEXION__DESTINO:
				setDestino((Nodo)null);
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
			case Clase_0106Package.CONEXION__NOMBRE:
				return NOMBRE_EDEFAULT == null ? nombre != null : !NOMBRE_EDEFAULT.equals(nombre);
			case Clase_0106Package.CONEXION__ORIGEN:
				return origen != null;
			case Clase_0106Package.CONEXION__DESTINO:
				return destino != null;
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
		result.append(" (nombre: ");
		result.append(nombre);
		result.append(')');
		return result.toString();
	}

} //ConexionImpl
