package examen2020;

import static examen2020.Estacion.*;
import java.util.ArrayList;
import java.util.List;

public class Estaciones implements Iterable {

    private final List<Estacion> estaciones;

    public Estaciones() {
        estaciones = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            estaciones.add(new Estacion(NOMBRES[i], i == 4 ? 0 : DISTANCIAS[i], i == 0 ? 0 : DISTANCIAS[i - 1], i, this));
        }
    }

    @Override
    public Iterador getIterador() {
        return new IteradorEstaciones(estaciones.toArray(new Estacion[0]));
    }
}
