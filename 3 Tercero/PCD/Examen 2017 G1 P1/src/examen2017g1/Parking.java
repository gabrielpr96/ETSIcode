package examen2017g1;

public class Parking {

    public static final int TIPO_COCHE = 1, TIPO_BUS = 2;
    private final CanvasParking canvas;
    private int libreBus, libreCoche, esperandoBus;

    public Parking(CanvasParking canvas) {
        libreBus = 2;
        libreCoche = 5;
        esperandoBus = 0;
        this.canvas = canvas;
    }

    public synchronized void entraBus() throws InterruptedException {
        canvas.espera(TIPO_BUS);
        esperandoBus++;
        while (libreBus < 1) {
            wait();
        }
        esperandoBus--;
        libreBus--;
        canvas.entra(TIPO_BUS);
    }

    public synchronized int entraCoche() throws InterruptedException {
        canvas.espera(TIPO_COCHE);
        while (libreCoche < 1 && (libreBus < 1 || esperandoBus > 0)) {
            wait();
        }
        if (libreCoche > 0) {
            canvas.entra(TIPO_COCHE);
            libreCoche--;
            return TIPO_COCHE;
        } else {
            canvas.entra(TIPO_BUS);
            libreBus--;
            return TIPO_BUS;
        }
    }

    public synchronized void saleBus() {
        libreBus++;
        canvas.sale();
        notifyAll();
    }

    public synchronized void saleCoche(int lugar) {
        if (lugar == TIPO_COCHE) {
            libreCoche++;
        } else {
            libreBus++;
        }
        canvas.sale();
        notifyAll();
    }

}
