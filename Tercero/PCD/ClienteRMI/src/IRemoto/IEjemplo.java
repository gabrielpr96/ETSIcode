package IRemoto;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IEjemplo extends Remote{
    public int incrementa(String quien, int n) throws RemoteException;
}
