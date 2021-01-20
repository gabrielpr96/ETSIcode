package proyecto1;

public class Revision {
    
    public static final int ALUMNO_TEORIA = 1, ALUMNO_PRACTICAS = 2;
    private int libreProfesor, librePracticas, esperandoPracticas;
    private final CanvasRevision canvas;

    public Revision(CanvasRevision canvas) {
        this.canvas = canvas;
        libreProfesor = 3;
        librePracticas = 2;
        esperandoPracticas = 0;
    }

    public synchronized void entraTeoria() throws InterruptedException {
        canvas.entra(ALUMNO_TEORIA);
        while (libreProfesor < 1 || (esperandoPracticas > 0 && librePracticas > 0)) {
            wait();
        }
        libreProfesor--;
        canvas.atendiendo();
    }

    public synchronized void entraPracticas() throws InterruptedException {
        canvas.entra(ALUMNO_PRACTICAS);
        esperandoPracticas++;
        while (libreProfesor < 1 || librePracticas < 1) {
            wait();
        }
        esperandoPracticas--;
        libreProfesor--;
        librePracticas--;
        canvas.atendiendo();
    }

    public synchronized void saleTeoria() {
        libreProfesor++;
        canvas.sale();
        notifyAll();
    }

    public synchronized void salePracticas() {
        libreProfesor++;
        librePracticas++;
        canvas.sale();
        notifyAll();
    }
}
