/**
 */
package Practica2.impl;

import Practica2.Arista;
import Practica2.Nodo;
import Practica2.Practica2Package;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Arista</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Practica2.impl.AristaImpl#getNombre <em>Nombre</em>}</li>
 *   <li>{@link Practica2.impl.AristaImpl#getOrigen <em>Origen</em>}</li>
 *   <li>{@link Practica2.impl.AristaImpl#getDestino <em>Destino</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AristaImpl extends EObjectImpl implements Arista {
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
	protected AristaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return Practica2Package.Literals.ARISTA;
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
			eNotify(new ENotificationImpl(this, Notification.SET, Practica2Package.ARISTA__NOMBRE, oldNombre, nombre));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Practica2Package.ARISTA__ORIGEN, oldOrigen, origen));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Practica2Package.ARISTA__ORIGEN, oldOrigen, newOrigen);
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
				msgs = ((InternalEObject)origen).eInverseRemove(this, Practica2Package.NODO__SALIENTES, Nodo.class, msgs);
			if (newOrigen != null)
				msgs = ((InternalEObject)newOrigen).eInverseAdd(this, Practica2Package.NODO__SALIENTES, Nodo.class, msgs);
			msgs = basicSetOrigen(newOrigen, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Practica2Package.ARISTA__ORIGEN, newOrigen, newOrigen));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, Practica2Package.ARISTA__DESTINO, oldDestino, destino));
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
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, Practica2Package.ARISTA__DESTINO, oldDestino, newDestino);
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
				msgs = ((InternalEObject)destino).eInverseRemove(this, Practica2Package.NODO__ENTRANTES, Nodo.class, msgs);
			if (newDestino != null)
				msgs = ((InternalEObject)newDestino).eInverseAdd(this, Practica2Package.NODO__ENTRANTES, Nodo.class, msgs);
			msgs = basicSetDestino(newDestino, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, Practica2Package.ARISTA__DESTINO, newDestino, newDestino));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case Practica2Package.ARISTA__ORIGEN:
				if (origen != null)
					msgs = ((InternalEObject)origen).eInverseRemove(this, Practica2Package.NODO__SALIENTES, Nodo.class, msgs);
				return basicSetOrigen((Nodo)otherEnd, msgs);
			case Practica2Package.ARISTA__DESTINO:
				if (destino != null)
					msgs = ((InternalEObject)destino).eInverseRemove(this, Practica2Package.NODO__ENTRANTES, Nodo.class, msgs);
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
			case Practica2Package.ARISTA__ORIGEN:
				return basicSetOrigen(null, msgs);
			case Practica2Package.ARISTA__DESTINO:
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
			case Practica2Package.ARISTA__NOMBRE:
				return getNombre();
			case Practica2Package.ARISTA__ORIGEN:
				if (resolve) return getOrigen();
				return basicGetOrigen();
			case Practica2Package.ARISTA__DESTINO:
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
			case Practica2Package.ARISTA__NOMBRE:
				setNombre((String)newValue);
				return;
			case Practica2Package.ARISTA__ORIGEN:
				setOrigen((Nodo)newValue);
				return;
			case Practica2Package.ARISTA__DESTINO:
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
			case Practica2Package.ARISTA__NOMBRE:
				setNombre(NOMBRE_EDEFAULT);
				return;
			case Practica2Package.ARISTA__ORIGEN:
				setOrigen((Nodo)null);
				return;
			case Practica2Package.ARISTA__DESTINO:
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
			case Practica2Package.ARISTA__NOMBRE:
				return NOMBRE_EDEFAULT == null ? nombre != null : !NOMBRE_EDEFAULT.equals(nombre);
			case Practica2Package.ARISTA__ORIGEN:
				return origen != null;
			case Practica2Package.ARISTA__DESTINO:
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

} //AristaImpl
