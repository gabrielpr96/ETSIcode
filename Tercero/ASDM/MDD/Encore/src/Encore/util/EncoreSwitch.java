/**
 */
package Encore.util;

import Encore.*;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see Encore.EncorePackage
 * @generated
 */
public class EncoreSwitch<T> extends Switch<T> {
	/**
	 * The cached model package
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static EncorePackage modelPackage;

	/**
	 * Creates an instance of the switch.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EncoreSwitch() {
		if (modelPackage == null) {
			modelPackage = EncorePackage.eINSTANCE;
		}
	}

	/**
	 * Checks whether this is a switch for the given package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param ePackage the package in question.
	 * @return whether this is a switch for the given package.
	 * @generated
	 */
	@Override
	protected boolean isSwitchFor(EPackage ePackage) {
		return ePackage == modelPackage;
	}

	/**
	 * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the first non-null result returned by a <code>caseXXX</code> call.
	 * @generated
	 */
	@Override
	protected T doSwitch(int classifierID, EObject theEObject) {
		switch (classifierID) {
			case EncorePackage.CONCIERTO: {
				Concierto concierto = (Concierto)theEObject;
				T result = caseConcierto(concierto);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EncorePackage.CANCION: {
				Cancion cancion = (Cancion)theEObject;
				T result = caseCancion(cancion);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EncorePackage.SECUENCIA: {
				Secuencia secuencia = (Secuencia)theEObject;
				T result = caseSecuencia(secuencia);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EncorePackage.LUZ: {
				Luz luz = (Luz)theEObject;
				T result = caseLuz(luz);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EncorePackage.FOCO: {
				Foco foco = (Foco)theEObject;
				T result = caseFoco(foco);
				if (result == null) result = caseLuz(foco);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EncorePackage.STROBO: {
				Strobo strobo = (Strobo)theEObject;
				T result = caseStrobo(strobo);
				if (result == null) result = caseLuz(strobo);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EncorePackage.UNION_CANCION_SECUENCIA: {
				UnionCancionSecuencia unionCancionSecuencia = (UnionCancionSecuencia)theEObject;
				T result = caseUnionCancionSecuencia(unionCancionSecuencia);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			case EncorePackage.UNION_SECUENCIA_LUZ: {
				UnionSecuenciaLuz unionSecuenciaLuz = (UnionSecuenciaLuz)theEObject;
				T result = caseUnionSecuenciaLuz(unionSecuenciaLuz);
				if (result == null) result = defaultCase(theEObject);
				return result;
			}
			default: return defaultCase(theEObject);
		}
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Concierto</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Concierto</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseConcierto(Concierto object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Cancion</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Cancion</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseCancion(Cancion object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Secuencia</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Secuencia</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseSecuencia(Secuencia object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Luz</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Luz</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseLuz(Luz object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Foco</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Foco</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseFoco(Foco object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Strobo</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Strobo</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseStrobo(Strobo object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Union Cancion Secuencia</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Union Cancion Secuencia</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnionCancionSecuencia(UnionCancionSecuencia object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>Union Secuencia Luz</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>Union Secuencia Luz</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
	 * @generated
	 */
	public T caseUnionSecuenciaLuz(UnionSecuenciaLuz object) {
		return null;
	}

	/**
	 * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * <!-- begin-user-doc -->
	 * This implementation returns null;
	 * returning a non-null result will terminate the switch, but this is the last case anyway.
	 * <!-- end-user-doc -->
	 * @param object the target of the switch.
	 * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
	 * @see #doSwitch(org.eclipse.emf.ecore.EObject)
	 * @generated
	 */
	@Override
	public T defaultCase(EObject object) {
		return null;
	}

} //EncoreSwitch
