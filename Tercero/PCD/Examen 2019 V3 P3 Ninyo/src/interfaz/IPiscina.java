package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPiscina extends Remote{
    public void entraAdulto(int id) throws InterruptedException, RemoteException;
    public boolean entraNinyo(int id) throws InterruptedException, RemoteException;
    public void saleAdulto(int id) throws RemoteException;
    public void saleNinyo(int id) throws RemoteException;
}
