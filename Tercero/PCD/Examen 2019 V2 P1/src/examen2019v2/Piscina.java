package examen2019v2;

public class Piscina {

    private final CanvasPiscina canvas;
    private int librePiscina, libreAletas, librePalas;

    public Piscina(CanvasPiscina canvas) {
        librePiscina = 5;
        libreAletas = 6;
        librePalas = 5;
        this.canvas = canvas;
    }

    public synchronized void entraPiscina() throws InterruptedException {
        canvas.entraSistema();
        while (librePiscina == 0) {
            wait();
        }
        librePiscina--;
        canvas.entraPiscina();
    }

    public synchronized void cogeMaterial() throws InterruptedException {
        canvas.comienzaBusqueda();
        while (libreAletas < 2) {
            wait();
        }
        libreAletas -= 2;
        canvas.obtieneAletas();
        while (librePalas < 2) {
            wait();
        }
        canvas.obtienePalas();
        librePalas -= 2;
    }

    public synchronized void sueltaMaterial() {
        libreAletas += 2;
        librePalas += 2;
        notifyAll();   }

    public synchronized void salePiscina() {
        librePiscina++;
        canvas.saleSistema();
        notifyAll();
    }

}
