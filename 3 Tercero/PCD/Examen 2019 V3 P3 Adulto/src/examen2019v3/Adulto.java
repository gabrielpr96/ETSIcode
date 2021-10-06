package examen2019v3;

import interfaz.IPiscina;
import static java.lang.Thread.sleep;
import java.net.MalformedURLException;
import java.util.Random;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Adulto {

    public static void main(String[] args) throws NotBoundException, MalformedURLException, RemoteException, InterruptedException {
        IPiscina piscina = (IPiscina) Naming.lookup("rmi://localhost:5144/piscinaremota");

        Random r = new Random();
        r.setSeed(System.nanoTime());
        int id = r.nextInt(100);
        System.out.println("Se crea Adulto " + id);
        piscina.entraAdulto(id);
        sleep(3000 + r.nextInt(3000));
        piscina.saleAdulto(id);
    }

}
