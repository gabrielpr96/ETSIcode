package examen2018v1;

public class Tayer {

    public static final int COCHE_TURISMO = 1, COCHE_CAMION = 2;
    private final CanvasTayer canvas;
    private int libreOperarios, esperandoCamiones;

    public Tayer(CanvasTayer canvas) {
        libreOperarios = 4;
        esperandoCamiones = 0;
        this.canvas = canvas;
    }

    public synchronized void entraCamion() throws InterruptedException {
        canvas.espera(COCHE_CAMION);
        esperandoCamiones++;
        while (libreOperarios < 2) {
            wait();
        }
        esperandoCamiones--;
        libreOperarios -= 2;
        canvas.entra();
    }

    public synchronized void entraCoche() throws InterruptedException {
        canvas.espera(COCHE_TURISMO);
        while (libreOperarios < 1 || esperandoCamiones > 0) {
            wait();
        }
        libreOperarios--;
        canvas.entra();
    }

    public synchronized void saleCamion() {
        libreOperarios += 2;
        canvas.sale();
        notifyAll();
    }

    public synchronized void saleCoche() {
        libreOperarios++;
        canvas.sale();
        notifyAll();
    }

}
