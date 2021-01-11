package examen2019v1;

public class Piscina {

    public static final int CALLE_CLUB = 1, CALLE_LIBRE = 2;
    public static final int CLIENTE_CLUB = 1, CLIENTE_LIBRE = 2;
    private final CanvasPiscina canvas;
    private int libreClub, libreLibre, esperandoLibre;

    public Piscina(CanvasPiscina canvas) {
        libreClub = 4;
        libreLibre = 1;
        esperandoLibre = 0;
        this.canvas = canvas;
    }

    public synchronized void entraLibre() throws InterruptedException {
        canvas.espera(CLIENTE_LIBRE);
        esperandoLibre++;
        while (libreLibre == 0) {
            wait();
        }
        esperandoLibre--;
        libreLibre--;
        canvas.entra(CLIENTE_LIBRE, CALLE_LIBRE);
    }

    public synchronized int entraClub() throws InterruptedException {
        canvas.espera(CLIENTE_CLUB);
        while (libreClub == 0 && (libreLibre == 0 || esperandoLibre != 0)) {
            wait();
        }
        if (libreClub == 0) {
            libreLibre--;
            canvas.entra(CLIENTE_CLUB, CALLE_LIBRE);
            return CALLE_LIBRE;
        } else {
            libreClub--;
            canvas.entra(CLIENTE_CLUB, CALLE_CLUB);
            return CALLE_CLUB;
        }
    }

    public synchronized void saleLibre() {
        libreLibre++;
        canvas.sale();
        notifyAll();
    }

    public synchronized void saleClub(int calle) {
        if (calle == CALLE_CLUB) {
            libreClub++;
        } else {
            libreLibre++;
        }
        canvas.sale();
        notifyAll();
    }

}
