package examen2019v1;


import interfaz.IPiscina;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.util.Random;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Libre {

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, InterruptedException {
        IPiscina piscina = (IPiscina) Naming.lookup("rmi://localhost:5144/piscinaremoto");
        
        Random r = new Random();
        int id = r.nextInt(100);
        r.setSeed(System.nanoTime());
        System.out.println("Se crea Libre "+id);
        piscina.entraLibre(id);
        sleep(2000 + r.nextInt(2000));
        piscina.saleLibre(id);
    }
}
