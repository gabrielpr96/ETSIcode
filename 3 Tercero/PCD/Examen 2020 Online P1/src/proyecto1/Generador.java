package proyecto1;

import java.awt.Color;
import java.util.Random;
import javax.swing.JFrame;

public class Generador {

    private static final int N_COCHES = 4, N_FURGONETAS = 3;

    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame("PCD 2020 ONLINE P1");
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CanvasTunel canvas = new CanvasTunel();
        canvas.setBackground(Color.WHITE);
        canvas.setSize(500, 500);
        f.add(canvas);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        Tunel revision = new Tunel(canvas);

        Random r = new Random();
        r.setSeed(System.nanoTime());
        Thread[] hilos = new Thread[N_COCHES+N_FURGONETAS];
        for (int i = 0; i < N_COCHES; i++) {
            hilos[i] = new Thread(new Coche(revision));
            hilos[i].start();
        }
        for (int i = 0; i < N_FURGONETAS; i++) {
            hilos[N_COCHES+i] = new Thread(new Furgoneta(revision));
            hilos[N_COCHES+i].start();
        }
        for (int i = 0; i < N_COCHES+N_FURGONETAS; i++) {
            hilos[i].join();
        }

        Thread.sleep(1000);
        System.exit(0);
    }

}
