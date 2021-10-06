package examen2018v3;

public class Linea {

    public static final int TIPO_PANTALON = 1, TIPO_CAMISA = 2;
    private final CanvasLinea canvas;
    private int libreCorte, libreCosido, esperandoPantalones;

    public Linea(CanvasLinea canvas) {
        libreCorte = 2;
        libreCosido = 1;
        esperandoPantalones = 0;
        this.canvas = canvas;
    }

    public synchronized void entraCorte(int tipo) throws InterruptedException {
        canvas.espera(tipo);
        while (libreCorte < 1) {
            wait();
        }
        libreCorte--;
        canvas.corte();
    }

    public synchronized void coserPantalon() throws InterruptedException {
        canvas.corteTermina();
        esperandoPantalones++;
        while (libreCosido < 1) {
            wait();
        }
        esperandoPantalones--;
        libreCosido--;
        libreCorte++;
        canvas.cosido();
        notifyAll();
    }

    public synchronized void coserCamisa() throws InterruptedException {
        canvas.corteTermina();
        while (libreCosido < 1 || esperandoPantalones > 0) {
            wait();
        }
        libreCosido--;
        libreCorte++;
        canvas.cosido();
        notifyAll();
    }

    public synchronized void saleCoser() {
        libreCosido++;
        canvas.salida();
        notifyAll();
    }

}
