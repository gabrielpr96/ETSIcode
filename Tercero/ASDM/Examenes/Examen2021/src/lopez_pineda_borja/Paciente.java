package lopez_pineda_borja;

public class Paciente {

    private final Fecha horaDeLlegada;
    private final String nombre;
    private final int gravedad;

    public Paciente(String nombre, int gravedad, Fecha horaDeLlegada) {
        this.nombre = nombre;
        this.gravedad = gravedad;
        this.horaDeLlegada = horaDeLlegada;
    }

    public Fecha getHoraDeLlegada() {
        return horaDeLlegada;
    }

    public String getNombre() {
        return nombre;
    }

    public int getGravedad() {
        return gravedad;
    }

}
