package proyecto2;

import java.awt.Color;
import java.util.Random;
import javax.swing.JFrame;

public class Generador {

    private static final int N_HILOS = 10;

    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame("PCD Borja Lopez 2020 P2");
        f.setResizable(false);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        CanvasRevision canvas = new CanvasRevision();
        canvas.setBackground(Color.WHITE);
        canvas.setSize(400, 500);
        f.add(canvas);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);

        Revision revision = new Revision(canvas);

        Random r = new Random();
        r.setSeed(System.nanoTime());
        Thread[] hilos = new Thread[N_HILOS];
        for (int i = 0; i < N_HILOS; i++) {
            if (r.nextFloat() < 0.4f) {
                hilos[i] = new Thread(new Practicas(revision));
            } else {
                hilos[i] = new Teoria(revision);
            }
            hilos[i].start();
            Thread.sleep(1000 + r.nextInt(2001));
        }
        for (int i = 0; i < N_HILOS; i++) {
            hilos[i].join();
        }

        Thread.sleep(1000);
        System.exit(0);
    }

}
