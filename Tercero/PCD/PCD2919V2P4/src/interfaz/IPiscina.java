package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IPiscina extends Remote{
    public void entraPiscina() throws InterruptedException, RemoteException;
    public void cogeMaterial() throws InterruptedException, RemoteException;
    public void sueltaMaterial() throws RemoteException;
    public void salePiscina() throws RemoteException;
}
