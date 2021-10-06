package examen2017g2;

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
        f.setTitle("Examen 2017 G2 B0vE");
        CanvasPuente c = new CanvasPuente();
        c.setBackground(Color.white);
        c.setSize(320, 500);
        f.add(c);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        Random r = new Random();
        r.setSeed(System.nanoTime());

        Puente p = new Puente(c);

        ArrayList<Thread> hAdulto = new ArrayList<>();
        ArrayList<Future> hNino = new ArrayList<>();
        ExecutorService thp = Executors.newFixedThreadPool(3);

        for (int i = 0; i < N_HILOS; i++) {
            if (r.nextDouble() < 0.4d) {
                hNino.add(thp.submit(new Nino(p)));
            } else {
                Thread tmp = new Adulto(p);
                tmp.start();
                hAdulto.add(tmp);
            }
            Thread.sleep(1000 + r.nextInt(2000));
        }

        int total = 0;
        for (Future hn : hNino) {
            total += (int) hn.get();
        }
        for (Thread ha : hAdulto) {
            ha.join();
        }
        System.out.println("Total de tiempo empleado los ninos en cruzar " + total + " ms");

        sleep(1000);
        System.exit(0);
    }

}
