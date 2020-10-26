package practica2;


public class Consumidor implements Runnable{
    private final ICola c;
    public Consumidor(ICola c){
        this.c = c;
    }
    
    @Override
    public void run(){
        for (int i = 0; i < 10; i++) {
            try {
             System.out.println("Hilo "+Thread.currentThread().getId()+" Extraigo: "+c.Desacola());
            } catch (Exception ex) {
                System.out.println("Hilo "+Thread.currentThread().getId()+" Error al extraer"+ex.getMessage());
            }
        }
    }
}
