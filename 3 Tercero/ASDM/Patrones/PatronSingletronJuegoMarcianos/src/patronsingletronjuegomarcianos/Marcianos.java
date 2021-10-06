package patronsingletronjuegomarcianos;

public final class Marcianos {

    private static int nMarcianos;
    private static final Marcianos marcianos = new Marcianos();

    public Marcianos() {
        nMarcianos = 10;
    }

    public static Marcianos obtenerMarcianos() {
        return marcianos;
    }

    public void derribaMarcianos(int derribados) {
        nMarcianos = Math.max(0, nMarcianos - derribados);
        System.out.println("Derribados: " + derribados + " Quedan: " + nMarcianos);
    }

    public void creaMarcianos(int creados) {
        if (nMarcianos > 0) {
            nMarcianos += creados;
        }
        System.out.println("Creados: " + creados + " Quedan: " + nMarcianos);
    }

    public int cuantosMarcianosQuedan() {
        System.out.println("Quedan: " + nMarcianos);
        return nMarcianos;
    }
}
