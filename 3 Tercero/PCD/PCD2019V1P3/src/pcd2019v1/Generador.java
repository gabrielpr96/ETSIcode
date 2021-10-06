package pcd2019v1;

import piscina.Piscina;
import java.awt.Color;
import java.net.MalformedURLException;
import java.rmi.NotBoundException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Random;
import javax.swing.JFrame;

public class Generador {

    private static final int N_HILOS = 20;

    public static void main(String[] args) throws InterruptedException, RemoteException, NotBoundException, MalformedURLException {
        JFrame f = new JFrame("PCD 2019 V1 P3");
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CanvasPiscina canvas = new CanvasPiscina();
        canvas.setBackground(Color.white);
        canvas.setSize(400, 500);
        f.add(canvas);
        f.pack();
        f.setVisible(true);

        Random r = new Random();
        r.setSeed(System.nanoTime());
        
        Piscina piscina = new Piscina(canvas);
        Registry registro = LocateRegistry.createRegistry(1999);
        registro.rebind("recurso", piscina);

        Thread[] hilos = new Thread[N_HILOS];
        for (int i = 0; i < N_HILOS; i++) {
            if (r.nextFloat() < 0.2f) {
                hilos[i] = new Libre(piscina);
            } else {
                hilos[i] = new Thread(new Club(piscina));
            }
            hilos[i].start();
            Thread.sleep(1000 + r.nextInt(1001));
        }
        for (int i = 0; i < N_HILOS; i++) {
            hilos[i].join();
        }
        Thread.sleep(1000);
        System.exit(0);
    }

}
