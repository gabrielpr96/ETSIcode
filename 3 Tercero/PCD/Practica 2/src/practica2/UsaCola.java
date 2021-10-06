package practica2;

/**
 * Clase principal de la primera práctica de PCD
 * @author Borja López
 */
public class UsaCola {

    /**
     * Método que crea una cola de 4 elementos y aleatoriamente introduce números del 0 al 9 o los saca.
     * @param args No se utilizan
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        ColaLenta c = new ColaLenta(10);
        
        Thread productor = new Productor(c);
        Consumidor consumidor = new Consumidor(c);
        Thread consumidor1 = new Thread(consumidor), consumidor2 = new Thread(consumidor);
        
        productor.start();
        productor.join();
        
        System.out.println("Cola: "+c);
        
        consumidor1.start();
        consumidor2.start();
        consumidor1.join();
        consumidor2.join();
        
        System.out.println("Cola: "+c);
    }

}
