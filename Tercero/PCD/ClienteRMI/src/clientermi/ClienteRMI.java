package clientermi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import IRemoto.IEjemplo;

public class ClienteRMI {

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        IEjemplo objremoto = (IEjemplo) Naming.lookup("rmi://localhost:2021/ejemremoto");//217.217.77.253
        objremoto.incrementa("Borja López", -392);
    }
    
}
