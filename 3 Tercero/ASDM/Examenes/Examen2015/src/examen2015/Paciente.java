package examen2015;

import java.util.Calendar;

public class Paciente {

    private final String nombre;
    private final int gravedad;
    private final Calendar entrada;

    public Paciente(String nombre, int gravedad) {
        this.nombre = nombre;
        this.gravedad = gravedad;
        entrada = Calendar.getInstance();
    }

    public int getGravedad() {
        return gravedad;
    }

    @Override
    public String toString() {
        return nombre + " [" + gravedad + "] " + entrada.get(Calendar.HOUR) + ":" + entrada.get(Calendar.MINUTE) + ":" + entrada.get(Calendar.SECOND);
    }
}
