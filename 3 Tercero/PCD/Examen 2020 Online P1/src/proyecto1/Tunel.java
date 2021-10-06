package proyecto1;

public class Tunel {
    
    public static final int VEHICULO_COCHE = 1, VEHICULO_FURGONETA = 2;
    private boolean librePrelavado, libreLavado, libreSecado, furgonetaEnSecado;
    private final CanvasTunel canvas;

    public Tunel(CanvasTunel canvas) {
        this.canvas = canvas;
        librePrelavado = true;
        libreLavado = true;
        libreSecado = true;
        furgonetaEnSecado = false;
    }

    public synchronized void entraPrelavadoCoche() throws InterruptedException {
        canvas.entraEspera(VEHICULO_COCHE);
        while (!librePrelavado) {
            wait();
        }
        librePrelavado = false;
        canvas.entraPrelavado();
    }

    public synchronized void entraLavadoCoche() throws InterruptedException {
        while (!libreLavado) {
            wait();
        }
        librePrelavado = true;
        libreLavado = false;
        canvas.entraLavado();
        notifyAll();
    }

    public synchronized void entraSecadoCoche() throws InterruptedException {
        while (!libreSecado) {            
            wait();
        }
        libreLavado = true;
        libreSecado = false;
        canvas.entraSecado();
        notifyAll();
    }

    public synchronized void saleSecadoCoche() {
        libreSecado = true;
        canvas.saleSecado();
        notifyAll();
    }

    public synchronized void entraPrelavadoFurgoneta() throws InterruptedException {
        canvas.entraEspera(VEHICULO_FURGONETA);
        while (!librePrelavado) {            
            wait();
        }
        librePrelavado = false;
        canvas.entraPrelavado();
    }

    public synchronized void entraLavadoFurgoneta() throws InterruptedException {
        while (!libreLavado || furgonetaEnSecado) {            
            wait();
        }
        librePrelavado = true;
        libreLavado = false;
        canvas.entraLavado();
        notifyAll();
    }

    public synchronized void entraSecadoFurgoneta() throws InterruptedException {
        while (!libreSecado) {            
            wait();
        }
        furgonetaEnSecado = true;
        libreLavado = true;
        libreSecado = false;
        canvas.entraSecado();
        notifyAll();
    }

    public synchronized void saleSecadoFurgoneta() {
        furgonetaEnSecado = false;
        libreSecado = true;
        canvas.saleSecado();
        notifyAll();
    }

}
