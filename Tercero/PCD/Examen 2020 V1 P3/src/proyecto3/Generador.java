package proyecto3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JFrame;

public class Generador {

    private static final int N_HILOS = 10;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        JFrame f = new JFrame("PCD Borja Lopez 2020 P3");
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
        ExecutorService thp = Executors.newFixedThreadPool(5);
        ArrayList<Thread> hTeoria = new ArrayList<>();
        ArrayList<Future> hPracticas = new ArrayList<>();
        for (int i = 0; i < N_HILOS; i++) {
            if (r.nextFloat() < 0.4f) {
                hPracticas.add(thp.submit(new Practicas(revision)));
            } else {
                Thread tmp = new Teoria(revision);
                tmp.start();
                hTeoria.add(tmp);
            }
            Thread.sleep(1000 + r.nextInt(2001));
        }
        int tiempoTotal = 0;
        for (Future h : hPracticas) {
            tiempoTotal += (int) h.get();
        }
        for (Thread h : hTeoria) {
            h.join();
        }
        System.out.println("Lo sestudiante han estado revisando practicas: "+tiempoTotal+" ms.");

        thp.shutdown();
        
        Thread.sleep(1000);
        System.exit(0);
    }

}
