package ejemploFrame;

public class Recurso {

    private final int[] contadores = {0, 0};
    private final VistaCanvas cv;
    
    public Recurso(VistaCanvas canvas){
        this.cv = canvas;
    }

    public synchronized void incrementa(int cual) {
        contadores[cual]++;
        System.out.println("Los contadores valen: " + contadores[0] + " y " + contadores[1]);
        cv.representa(contadores);
    }

    public int[] getContadores() {
        return contadores;
    }
}
