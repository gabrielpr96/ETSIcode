package Remoto;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import IRemoto.IEjemplo;


public class Ejemplo extends UnicastRemoteObject implements IEjemplo{
    private volatile int contador;
    
    public Ejemplo(int n) throws RemoteException{
        super();
        contador = n;
    }
    
    @Override
    public synchronized int incrementa(String quien, int n) throws RemoteException{
        contador += n;
        System.out.println(quien + " ha incrementado el contador en "+n+" y ahora vale "+contador+".");
        return contador;
    }

}
