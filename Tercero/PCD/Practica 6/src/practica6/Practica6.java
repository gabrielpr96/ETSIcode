package practica6;

import java.awt.Color;
import static java.lang.Thread.sleep;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Practica6 {

    public static void main(String[] args) throws InterruptedException {
        JFrame f = new JFrame();
        CanvasFabrica canvas = new CanvasFabrica();
        canvas.setSize(540, 280);
        canvas.setBackground(new Color(205, 250, 212));
        f.add(canvas);
        f.setTitle("Practica 6: Borja López");
        f.setResizable(false);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.pack();
        f.setLocationRelativeTo(null);
        f.setVisible(true);
        
        Semaforo SiloM1 = new Semaforo(0), SiloM2 = new Semaforo(0);
        
        Thread RR = new RobotR(SiloM1, SiloM2, canvas);
        Thread RA = new RobotA(SiloM1, SiloM2, canvas);
        Thread RB = new Thread(new RobotB(SiloM1, SiloM2, canvas));
        RR.start();
        RA.start();
        RB.start();
        RA.join();
        RB.join();
        RR.interrupt();
        RR.join();
        
        sleep(5000);
        System.exit(0);
    }
    
}
