package examen2018v2;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.Semaphore;
import javax.swing.JFrame;

public class Mina {

    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame();
        f.setTitle("Examen 2018 V2 B0vE");
        CanvasMina c = new CanvasMina();
        c.setBackground(Color.white);
        c.setSize(560, 300);
        f.add(c);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        Random r = new Random();
        r.setSeed(System.nanoTime());

        Semaphore s = new Semaphore(4);

        Thread[] hilos = new Thread[4];
        hilos[0] = new Pequenya(s, 0, c);
        hilos[1] = new Pequenya(s, 1, c);
        hilos[2] = new Thread(new Grande(s, c));
        hilos[3] = new Thread(new Cinta(s, c));
        
        for (int i = 0; i < 4; i++) {
            hilos[i].start();
        }

        for (int i = 0; i < 3; i++) {
            hilos[i].join();
        }
        
        hilos[3].interrupt();
        hilos[3].join();

        sleep(1000);
        System.exit(0);
    }

}
