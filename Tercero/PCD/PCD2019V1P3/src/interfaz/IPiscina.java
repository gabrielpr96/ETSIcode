package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPiscina extends Remote{
    public void entraLibre() throws InterruptedException, RemoteException;
    public int entraClub() throws InterruptedException, RemoteException;
    public void saleLibre() throws RemoteException;
    public void saleClub(int cual) throws RemoteException;
}
