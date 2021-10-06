package interfaz;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IRegistro extends Remote{
    public void entraRegistro(int id) throws RemoteException;
    public void entraNota(int id) throws RemoteException;
    public void sale(int id) throws RemoteException;
}
