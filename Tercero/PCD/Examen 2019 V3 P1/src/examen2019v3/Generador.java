package examen2019v3;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.rmi.RemoteException;
import java.util.Random;
import javax.swing.JFrame;
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class Generador {

    private static final int N_HILOS = 20;

    public static void main(String[] args) throws InterruptedException, RemoteException {
        JFrame f = new JFrame();
        f.setTitle("Examen 2019 V1 B0vE");
        CanvasPiscina c = new CanvasPiscina();
        c.setBackground(Color.white);
        c.setSize(350, 500);
        f.add(c);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        Random r = new Random();
        r.setSeed(System.nanoTime());

        Piscina p = new Piscina(c);

        Thread[] hilos = new Thread[N_HILOS];
        for (int i = 0; i < N_HILOS; i++) {
            if (r.nextDouble() < 0.4d) {
                hilos[i] = new Ninyo(p);
            } else {
                hilos[i] = new Thread(new Adulto(p));
            }
            hilos[i].start();
            Thread.sleep(1000 + r.nextInt(1000));
        }
        for (int i = 0; i < N_HILOS; i++) {
            hilos[i].join();
        }

        sleep(1000);
        System.exit(0);
    }

}
