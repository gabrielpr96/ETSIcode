package pcd2919v2p1;

public class Piscina {

    private int librePiscina, libreAletas, librePalas;
    private final CanvasPiscina canvas;

    public Piscina(CanvasPiscina canvas) {
        this.canvas = canvas;
        librePiscina = 5;
        libreAletas = 6;
        librePalas = 5;
    }

    public synchronized void entraPiscina() throws InterruptedException {
        canvas.entra();
        while (librePiscina < 1) {
            wait();
        }
        librePiscina--;
        canvas.calienta();
    }

    public synchronized void cogeMaterial() throws InterruptedException {
        canvas.coge();
        while (libreAletas < 2) {
            wait();
        }
        libreAletas -= 2;
        canvas.obtieneAletas();
        while (librePalas < 2) {
            wait();
        }
        librePalas -= 2;
        canvas.obtienePalas();
    }

    public synchronized void sueltaMaterial() {
        libreAletas+=2;
        librePalas+=2;
        notifyAll();
    }

    public synchronized void salePiscina() {
        librePiscina++;
        canvas.sale();
        notifyAll();
    }
}
