package practica6;

public class Semaforo {
    private int estado;
    public Semaforo(int init){
        estado = init;
    }
    public synchronized void WAIT() throws InterruptedException{
        Thread.sleep(500);
        while (estado == 0) {
            wait();
        }
        estado--;
    }
    public synchronized void SIGNAL() throws InterruptedException{
        Thread.sleep(100);
        estado++;
        notify();
    }
}
