package practica6;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RobotA extends Thread{
    private final Semaforo SiloM1, SiloM2;
    private final CanvasFabrica canvas;

    RobotA(Semaforo SiloM1, Semaforo SiloM2, CanvasFabrica canvas) {
        this.SiloM1 = SiloM1;
        this.SiloM2 = SiloM2;
        this.canvas = canvas;
    }
    
    @Override
    public void run(){
        Random r = new Random();
        r.setSeed(System.nanoTime());
        try {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 3; j++) {
                    SiloM1.WAIT();
                    canvas.tomarM1A();
                }
                for (int j = 0; j < 2; j++) {
                    SiloM2.WAIT();
                    canvas.tomarM2A();
                }
                canvas.fabricarA();
                Thread.sleep(1000+r.nextInt(2000));
                canvas.fabricadoA();
            }
            canvas.terminaA();
        } catch (InterruptedException ex) {
            System.out.println("Interrumpido RobotA");
        }
    }
}
