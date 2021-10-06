/**
 */
package Encore.impl;

import Encore.EncorePackage;
import Encore.Secuencia;

import Encore.UnionSecuenciaLuz;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Secuencia</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link Encore.impl.SecuenciaImpl#getNumero <em>Numero</em>}</li>
 *   <li>{@link Encore.impl.SecuenciaImpl#getModificador <em>Modificador</em>}</li>
 *   <li>{@link Encore.impl.SecuenciaImpl#getAnguloZ <em>Angulo Z</em>}</li>
 *   <li>{@link Encore.impl.SecuenciaImpl#getAnguloY <em>Angulo Y</em>}</li>
 *   <li>{@link Encore.impl.SecuenciaImpl#getInicio <em>Inicio</em>}</li>
 *   <li>{@link Encore.impl.SecuenciaImpl#getDuracion <em>Duracion</em>}</li>
 * </ul>
 *
 * @generated
 */
public class SecuenciaImpl extends EObjectImpl implements Secuencia {
	/**
	 * The default value of the '{@link #getNumero() <em>Numero</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumero()
	 * @generated
	 * @ordered
	 */
	protected static final int NUMERO_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getNumero() <em>Numero</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getNumero()
	 * @generated
	 * @ordered
	 */
	protected int numero = NUMERO_EDEFAULT;

	/**
	 * The default value of the '{@link #getModificador() <em>Modificador</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModificador()
	 * @generated
	 * @ordered
	 */
	protected static final float MODIFICADOR_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getModificador() <em>Modificador</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getModificador()
	 * @generated
	 * @ordered
	 */
	protected float modificador = MODIFICADOR_EDEFAULT;

	/**
	 * The default value of the '{@link #getAnguloZ() <em>Angulo Z</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnguloZ()
	 * @generated
	 * @ordered
	 */
	protected static final float ANGULO_Z_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getAnguloZ() <em>Angulo Z</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnguloZ()
	 * @generated
	 * @ordered
	 */
	protected float anguloZ = ANGULO_Z_EDEFAULT;

	/**
	 * The default value of the '{@link #getAnguloY() <em>Angulo Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnguloY()
	 * @generated
	 * @ordered
	 */
	protected static final float ANGULO_Y_EDEFAULT = 0.0F;

	/**
	 * The cached value of the '{@link #getAnguloY() <em>Angulo Y</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAnguloY()
	 * @generated
	 * @ordered
	 */
	protected float anguloY = ANGULO_Y_EDEFAULT;

	/**
	 * The default value of the '{@link #getInicio() <em>Inicio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInicio()
	 * @generated
	 * @ordered
	 */
	protected static final int INICIO_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getInicio() <em>Inicio</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getInicio()
	 * @generated
	 * @ordered
	 */
	protected int inicio = INICIO_EDEFAULT;

	/**
	 * The default value of the '{@link #getDuracion() <em>Duracion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuracion()
	 * @generated
	 * @ordered
	 */
	protected static final int DURACION_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getDuracion() <em>Duracion</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDuracion()
	 * @generated
	 * @ordered
	 */
	protected int duracion = DURACION_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SecuenciaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return EncorePackage.Literals.SECUENCIA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getNumero() {
		return numero;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setNumero(int newNumero) {
		int oldNumero = numero;
		numero = newNumero;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EncorePackage.SECUENCIA__NUMERO, oldNumero, numero));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getModificador() {
		return modificador;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setModificador(float newModificador) {
		float oldModificador = modificador;
		modificador = newModificador;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EncorePackage.SECUENCIA__MODIFICADOR, oldModificador, modificador));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getAnguloZ() {
		return anguloZ;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnguloZ(float newAnguloZ) {
		float oldAnguloZ = anguloZ;
		anguloZ = newAnguloZ;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EncorePackage.SECUENCIA__ANGULO_Z, oldAnguloZ, anguloZ));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public float getAnguloY() {
		return anguloY;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAnguloY(float newAnguloY) {
		float oldAnguloY = anguloY;
		anguloY = newAnguloY;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EncorePackage.SECUENCIA__ANGULO_Y, oldAnguloY, anguloY));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getInicio() {
		return inicio;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setInicio(int newInicio) {
		int oldInicio = inicio;
		inicio = newInicio;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EncorePackage.SECUENCIA__INICIO, oldInicio, inicio));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getDuracion() {
		return duracion;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDuracion(int newDuracion) {
		int oldDuracion = duracion;
		duracion = newDuracion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, EncorePackage.SECUENCIA__DURACION, oldDuracion, duracion));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case EncorePackage.SECUENCIA__NUMERO:
				return getNumero();
			case EncorePackage.SECUENCIA__MODIFICADOR:
				return getModificador();
			case EncorePackage.SECUENCIA__ANGULO_Z:
				return getAnguloZ();
			case EncorePackage.SECUENCIA__ANGULO_Y:
				return getAnguloY();
			case EncorePackage.SECUENCIA__INICIO:
				return getInicio();
			case EncorePackage.SECUENCIA__DURACION:
				return getDuracion();
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
			case EncorePackage.SECUENCIA__NUMERO:
				setNumero((Integer)newValue);
				return;
			case EncorePackage.SECUENCIA__MODIFICADOR:
				setModificador((Float)newValue);
				return;
			case EncorePackage.SECUENCIA__ANGULO_Z:
				setAnguloZ((Float)newValue);
				return;
			case EncorePackage.SECUENCIA__ANGULO_Y:
				setAnguloY((Float)newValue);
				return;
			case EncorePackage.SECUENCIA__INICIO:
				setInicio((Integer)newValue);
				return;
			case EncorePackage.SECUENCIA__DURACION:
				setDuracion((Integer)newValue);
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
			case EncorePackage.SECUENCIA__NUMERO:
				setNumero(NUMERO_EDEFAULT);
				return;
			case EncorePackage.SECUENCIA__MODIFICADOR:
				setModificador(MODIFICADOR_EDEFAULT);
				return;
			case EncorePackage.SECUENCIA__ANGULO_Z:
				setAnguloZ(ANGULO_Z_EDEFAULT);
				return;
			case EncorePackage.SECUENCIA__ANGULO_Y:
				setAnguloY(ANGULO_Y_EDEFAULT);
				return;
			case EncorePackage.SECUENCIA__INICIO:
				setInicio(INICIO_EDEFAULT);
				return;
			case EncorePackage.SECUENCIA__DURACION:
				setDuracion(DURACION_EDEFAULT);
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
			case EncorePackage.SECUENCIA__NUMERO:
				return numero != NUMERO_EDEFAULT;
			case EncorePackage.SECUENCIA__MODIFICADOR:
				return modificador != MODIFICADOR_EDEFAULT;
			case EncorePackage.SECUENCIA__ANGULO_Z:
				return anguloZ != ANGULO_Z_EDEFAULT;
			case EncorePackage.SECUENCIA__ANGULO_Y:
				return anguloY != ANGULO_Y_EDEFAULT;
			case EncorePackage.SECUENCIA__INICIO:
				return inicio != INICIO_EDEFAULT;
			case EncorePackage.SECUENCIA__DURACION:
				return duracion != DURACION_EDEFAULT;
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
		result.append(" (Numero: ");
		result.append(numero);
		result.append(", Modificador: ");
		result.append(modificador);
		result.append(", AnguloZ: ");
		result.append(anguloZ);
		result.append(", AnguloY: ");
		result.append(anguloY);
		result.append(", Inicio: ");
		result.append(inicio);
		result.append(", Duracion: ");
		result.append(duracion);
		result.append(')');
		return result.toString();
	}

} //SecuenciaImpl
