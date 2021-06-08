package examen2020;

import static examen2020.Estacion.*;

public class FabricaEstaciones {
    public Estacion crearEstacion(int n){
        if(n < 0 || n > 4) return null;
        return new Estacion(NOMBRES[n], n==4?0:DISTANCIAS[n], n==0?0:DISTANCIAS[n-1], n);
    }
}
