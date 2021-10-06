package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPiscina extends Remote{
    public void entraLibre(int id) throws InterruptedException, RemoteException;
    public int entraClub(int id) throws InterruptedException, RemoteException;
    public void saleLibre(int id) throws RemoteException;
    public void saleClub(int id, int calle) throws RemoteException;
}
