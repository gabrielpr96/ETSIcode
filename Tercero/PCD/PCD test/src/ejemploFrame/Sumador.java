package ejemploFrame;

public class Sumador implements Runnable {

    private final Recurso r;
    private final int cual;

    public Sumador(Recurso r, int cual) {
        this.r = r;
        this.cual = cual;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            r.incrementa(cual);
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
            }
        }
    }

}
