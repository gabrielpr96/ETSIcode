package Interfaz;

import java.rmi.*;
import java.util.LinkedList;

public interface Calculadora extends Remote {

    float Sumatorio(LinkedList l) throws RemoteException;

    float sumar(float a, float b) throws RemoteException;

    float restar(float a, float b) throws RemoteException;

    float multiplicar(float a, float b) throws RemoteException;

    float dividir(float a, float b) throws RemoteException;

    float Operacion(float a, char o, float b) throws RemoteException;
}
