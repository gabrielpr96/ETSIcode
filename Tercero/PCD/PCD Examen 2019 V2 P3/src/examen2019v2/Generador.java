package examen2019v2;

import java.awt.Color;
import static java.lang.Thread.sleep;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import javax.swing.JFrame;

public class Generador {

    private static final int N_HILOS = 20;

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        JFrame f = new JFrame();
        f.setTitle("Examen 2019 V2 B0vE");
        CanvasPiscina c = new CanvasPiscina();
        c.setBackground(Color.white);
        c.setSize(500, 600);
        f.add(c);
        f.pack();
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);

        Random r = new Random();
        r.setSeed(System.nanoTime());

        Piscina p = new Piscina(c);

        ExecutorService thp = Executors.newFixedThreadPool(6);
        
        Future[] resultados = new Future[N_HILOS];
        for (int i = 0; i < N_HILOS; i++) {
            resultados[i] = thp.submit(new Nadador(p));
            Thread.sleep(1000 + r.nextInt(1000));
        }
        
        int tiempo = 0;
        for (int i = 0; i < N_HILOS; i++) {
            tiempo += (int)resultados[i].get();
        }
        thp.shutdown();
        System.out.println("Total nadado: "+tiempo+" ms");
        sleep(1000);
        System.exit(0);
    }

}
