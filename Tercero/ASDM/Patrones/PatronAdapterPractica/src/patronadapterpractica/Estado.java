package patronadapterpractica;

public final class Estado {

    private final int velocidad, revoluciones;
    private final boolean encendido, averia;

    public Estado(int velocidad, int revoluciones, boolean encendido, boolean averia) {
        this.velocidad = velocidad;
        this.revoluciones = revoluciones;
        this.encendido = encendido;
        this.averia = averia;
    }

    public int getVelocidad() {
        return velocidad;
    }

    public int getRevoluciones() {
        return revoluciones;
    }

    public boolean isEncendido() {
        return encendido;
    }

    public boolean isAveria() {
        return averia;
    }

}
