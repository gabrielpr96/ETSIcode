package waitNotify;

public class Recurso {

    private int turno = 2;
    
    public Recurso(int turnoInicial){
        turno = turnoInicial;
    }

    public synchronized void cogerTurno(int id) {
        while (turno != id)
            try {
                wait();
                System.out.println("Hilo "+id+" despertado");
        } catch (InterruptedException ex) {
        }
    }

    public synchronized void cederTurno() {
        turno = (turno + 1) % 5;
        notifyAll();
    }
}
