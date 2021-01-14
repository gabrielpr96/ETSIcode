package examen2017g2;

public class Puente {

    public static final int TIPO_ADULTO = 1, TIPO_NINO = 2;
    private final CanvasPuente canvas;
    private int dentroAdultos, dentroNinos, esperandoNinos;

    public Puente(CanvasPuente canvas) {
        dentroAdultos = 0;
        dentroNinos = 0;
        esperandoNinos = 0;
        this.canvas = canvas;
    }

    public synchronized void entraAdulto() throws InterruptedException {
        canvas.espera(TIPO_ADULTO);
        while (esperandoNinos > 0 || dentroAdultos >= 2 || dentroNinos >= 3 || (dentroNinos >= 1 && dentroAdultos >= 1)) {
            wait();
        }
        dentroAdultos++;
        canvas.entra();
    }

    public synchronized void entraNino() throws InterruptedException {
        canvas.espera(TIPO_NINO);
        esperandoNinos++;
        while (dentroAdultos >= 2 || dentroNinos >= 3 || (dentroNinos >= 1 && dentroAdultos >= 1)) {
            wait();
        }
        esperandoNinos--;
        dentroNinos++;
        canvas.entra();
    }

    public synchronized void saleAdulto() {
        dentroAdultos--;
        canvas.sale();
        notifyAll();
    }

    public synchronized void saleNino() {
        dentroNinos--;
        canvas.sale();
        notifyAll();
    }

}
