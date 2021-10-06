package Implementacion;

import Interfaz.Calculadora;
import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.Iterator;
import java.util.LinkedList;

public class CalculadoraImp implements Calculadora, Serializable {

    @Override
    public float sumar(float a, float b) throws RemoteException {
        System.out.println("SERVIDOR ha hecho: " + a + "+" + b + "=" + (a + b));
        return a + b;
    }

    @Override
    public float restar(float a, float b) throws RemoteException {
        System.out.println("SERVIDOR ha hecho: " + a + "-" + b + "=" + (a - b));
        return a - b;
    }

    @Override
    public float multiplicar(float a, float b) throws RemoteException {
        System.out.println("SERVIDOR ha hecho: " + a + "*" + b + "=" + (a * b));
        return a * b;
    }

    @Override
    public float dividir(float a, float b) throws RemoteException {
        System.out.println("SERVIDOR ha hecho: " + a + "/" + b + "=" + (a / b));
        return a / b;
    }

    @Override
    public float Operacion(float a, char o, float b) throws RemoteException {
        float resultado = 0;
        switch (o) {
            case '+':
                resultado = a + b;
                break;
            case '-':
                resultado = a - b;
                break;
            case '*':
                resultado = a * b;
                break;
            case '/':
                resultado = a / b;
                break;
            default:
                System.out.println("Error En SERVIDOR Calculadora: Operación " + o + " desconocida.");
        }
        System.out.println("SERVIDOR ha hecho: " + a + o + b + "=" + resultado);
        return resultado;
    }

    @Override
    public float Sumatorio(LinkedList l) throws RemoteException {
        System.out.println("Numero de elementos = " + l.size());
        float Suma = 0, a, b;
        char operacion;
        Object object;
        for (Iterator it = l.iterator(); it.hasNext();) {
            object = it.next();
            a = Float.parseFloat(object.toString());
            object = it.next();
            b = Float.parseFloat(object.toString());
            object = it.next();
            operacion = object.toString().charAt(0);
            Suma = Suma + Operacion(a, operacion, b);
        }
        System.out.println("SERVIDOR ha hecho: Suma total = " + Suma);
        return Suma;
    }
}
