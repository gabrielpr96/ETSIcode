package examen2018v4;

public class Vibradora {

    public static final int TIPO_INOX = 1, TIPO_HIERRO = 2, TIPO_VACIO = 0, MAX_INOX = 3, MAX_HIERRO = 2;
    private final CanvasVibradora canvas;
    private int espacioOcupado, dentro;

    public Vibradora(CanvasVibradora canvas) {
        espacioOcupado = 0;
        dentro = TIPO_VACIO;
        this.canvas = canvas;
    }

    public synchronized void entraInox() throws InterruptedException {
        canvas.espera(TIPO_INOX);
        while (dentro == TIPO_HIERRO || espacioOcupado >= MAX_INOX) {
            wait();
        }
        espacioOcupado++;
        dentro = TIPO_INOX;
        canvas.entra();
    }

    public synchronized void entraHierro() throws InterruptedException {
        canvas.espera(TIPO_HIERRO);
        while (dentro == TIPO_INOX || espacioOcupado >= MAX_HIERRO) {
            wait();
        }
        espacioOcupado++;
        dentro = TIPO_HIERRO;
        canvas.entra();
    }

    public synchronized void saleInox() {
        canvas.sale();
        espacioOcupado--;
        if(espacioOcupado == 0)
            dentro = TIPO_VACIO;
        notifyAll();
    }

    public synchronized void saleHierro() {
        canvas.sale();
        espacioOcupado--;
        if(espacioOcupado == 0)
            dentro = TIPO_VACIO;
        notifyAll();
    }

}
