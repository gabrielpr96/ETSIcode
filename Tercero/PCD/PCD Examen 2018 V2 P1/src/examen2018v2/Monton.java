package examen2018v2;

public class Monton {

    private final CanvasMina canvas;
    private int cantidad;
    private int esperandoGrande;

    public Monton(CanvasMina canvas) {
        cantidad = 4;
        esperandoGrande = 0;
        this.canvas = canvas;
        canvas.cantidad(cantidad);
    }

    public synchronized void cargaPoco(int id) throws InterruptedException {
        canvas.estado(id, 1);
        while (cantidad == 0 || (cantidad >= 2 && esperandoGrande > 0)) {
            wait();
        }
        cantidad--;
        canvas.estado(id, 0);
        canvas.cantidad(cantidad);
    }

    public synchronized void cargaMucho() throws InterruptedException {
        canvas.estado(2, 1);
        esperandoGrande++;
        while (cantidad < 2) {
            wait();
        }
        esperandoGrande--;
        cantidad -= 2;
        canvas.estado(2, 0);
        canvas.cantidad(cantidad);
    }
    
    public synchronized void grandeSeVa(){
        esperandoGrande = 0;
        canvas.estado(2, 2);
    }
    public synchronized void pequenyaSeVa(int id){
        canvas.estado(id, 2);
    }

    public synchronized void Rellena(int tn) {
        cantidad += tn;
        canvas.cantidad(cantidad);
        notifyAll();
    }

}
