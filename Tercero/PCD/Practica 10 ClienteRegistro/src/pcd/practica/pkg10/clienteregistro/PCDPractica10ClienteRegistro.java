package pcd.practica.pkg10.clienteregistro;

import interfaz.IRegistro;
import java.net.MalformedURLException;
import java.util.Random;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class PCDPractica10ClienteRegistro {

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException {
        IRegistro registro = (IRegistro) Naming.lookup("rmi://localhost:5144/registroremoto");
        
        Random r = new Random();
        int id = r.nextInt(100);
        r.setSeed(System.nanoTime());
        try {
            registro.entraRegistro(id);
            Thread.sleep(3000 + r.nextInt(2000));
            registro.sale(id);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
    
}
