package waitNotify;

public class WaitNotify {
    public static void main(String[] args) throws InterruptedException{
        Recurso r = new Recurso(3);
        Hilo[] h = new Hilo[5];
        for (int i = 0; i < 5; i++) {
            h[i] = new Hilo(i, r);
        }
        for (int i = 0; i < 5; i++) {
            h[i].start();
        }
        for (int i = 1; i < 5; i++) {
            h[i].join();
        }
        System.out.println("Fin de los hilos");
        Thread.sleep(1000);
        synchronized(r){
            r.notify();
        }
        Thread.sleep(1);
        synchronized(r){
            r.notify();
        }
    }
}
