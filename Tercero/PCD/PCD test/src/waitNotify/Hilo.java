package waitNotify;

public class Hilo extends Thread {

    private final int id;
    private final Recurso recurso;

    public Hilo(int id, Recurso recurso) {
        this.id = id;
        this.recurso = recurso;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            recurso.cogerTurno(id);
            System.out.println("Soy el hilo: " + id);
            recurso.cederTurno();
        }
        if(id == 0){
            recurso.cogerTurno(id);
            System.out.println("Soy el hilo: " + id);
            recurso.cederTurno();
        }
    }
}
