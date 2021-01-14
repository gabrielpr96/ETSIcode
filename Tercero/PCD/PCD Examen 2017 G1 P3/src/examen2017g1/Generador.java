package examen2017g1;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JFrame;

public class Generador {

    private static final int N_HILOS = 20;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        JFrame f = new JFrame();
        f.setTitle("Examen 2017 G1 B0vE");
        CanvasParking c = new CanvasParking();
        c.setBackground(Color.white);
        c.setSize(500, 420);
        f.add(c);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        Random r = new Random();
        r.setSeed(System.nanoTime());

        Parking p = new Parking(c);

        ArrayList<Thread> hBus = new ArrayList<>();
        ArrayList<Future> hCoche = new ArrayList<>();
        ExecutorService thp = Executors.newFixedThreadPool(3);

        for (int i = 0; i < N_HILOS; i++) {
            if (r.nextDouble() < 0.7d) {
                hCoche.add(thp.submit(new Turismo(p)));
            } else {
                Thread tmp = new Autobus(p);
                tmp.start();
                hBus.add(tmp);
            }
            Thread.sleep(1000 + r.nextInt(2000));
        }

        int total = 0;
        for (Future fu : hCoche) {
            total += (int) fu.get();
        }
        
        for (Thread th : hBus) {
            th.join();
        }
        
        System.out.println("Total tiempo de ocupacion de los coches "+total+" ms.");

        thp.shutdown();
        sleep(1000);
        System.exit(0);
    }

}
