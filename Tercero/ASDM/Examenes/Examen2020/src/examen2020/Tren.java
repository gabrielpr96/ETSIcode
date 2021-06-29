package examen2020;

import java.util.ArrayList;
import java.util.List;

public class Tren implements IPublicador {

    private final int capacidad;
    private int plazasDisponibles;
    private boolean direccion, estado;
    private int nEstacion;

    private static final Tren tren = new Tren();

    private final List<IObservador> suscritos;

    private Tren() {
        capacidad = plazasDisponibles = 100;
        direccion = true;
        estado = false;
        nEstacion = 0;
        suscritos = new ArrayList<>();
    }

    public static Tren getTren() {
        return tren;
    }

    public void moverse() {
        estado = true;
        if (direccion && nEstacion < 4) {
            nEstacion++;
            if (nEstacion == 4) {
                direccion = false;
            }
        } else if (!direccion && nEstacion > 0) {
            nEstacion--;
            if (nEstacion == 0) {
                direccion = true;
            }
        }
        notificar();
    }

    public int getPlazasDisponibles() {
        return plazasDisponibles;
    }

    public void setPlazasDisponibles(int suben, int bajan) {
        estado = false;
        int diff = suben - bajan;
        if (diff <= plazasDisponibles) {
            plazasDisponibles -= diff;
        }
        notificar();
    }

    public void establecerDireccion(boolean d) {
        direccion = d;
    }

    public boolean getDireccion() {
        return direccion;
    }

    public int getNEstacion() {
        return nEstacion;
    }

    @Override
    public void agregarObservador(IObservador observador) {
        suscritos.add(observador);
    }

    @Override
    public void eliminarObservador(IObservador observador) {
        suscritos.remove(suscritos);
    }

    @Override
    public void notificar() {
        for (IObservador suscrito : suscritos) {
            suscrito.actualizar(plazasDisponibles, direccion, estado);
        }
    }
}
