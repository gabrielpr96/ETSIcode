package patronsingletronjuegomarcianos;

public class Jugador {

    private final Marcianos marcianos;

    public Jugador() {
        marcianos = Marcianos.obtenerMarcianos();
    }

    public void destruirMarcianos(int derribados) {
        marcianos.derribaMarcianos(derribados);
    }
}
