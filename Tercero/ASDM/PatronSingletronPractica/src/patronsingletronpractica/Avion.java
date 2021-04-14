package patronsingletronpractica;

import java.util.Arrays;

public class Avion {

    private static Usuario[] pasajeros;
    private static final Avion avion = new Avion();

    public Avion() {
        pasajeros = new Usuario[180];
    }

    public static Avion getAvion() {
        return avion;
    }

    /**
     * Reserva una cantidad de billetes para un usuario. Si no hubiera
     * suficientes asientos libres se lanza una excepción y no se realizan
     * cambios. Comienza a buscar plazas libres desde el principio.
     *
     * @param billetes Cantidad de billetes a reservar
     * @param usuario Usuario que los reserva
     * @throws ReservaExceotion Si no hay suficientes asientos libres
     */
    public void vendeBilletes(int billetes, Usuario usuario) throws ReservaExceotion {
        int libres = cuantosAsientosQuedan();
        if (billetes > libres) {
            throw new ReservaExceotion("No se pueden vender " + billetes + " billete/s, solo quedan " + libres + " libre/s.");
        }

        int i = 0;
        while (billetes > 0) {
            if (pasajeros[i] == null) {
                pasajeros[i] = usuario;
                billetes--;
            }
            i++;
        }
    }

    /**
     * Elimina la reserva de una cantidad de billetes para un usuario. Si el
     * usuario ha tiene reservados menos billetes de los que se pretenden
     * devolver lanza una excepción y el método termina sin cambios. Comienza a
     * buscar plazas reservadas desde el final.
     *
     * @param billetes Cantidad de billetes a devolver
     * @param usuario Usuario que devuelve los billetes
     * @throws ReservaExceotion Si hay menos billetes reservados por el usuario
     * de los que se pretenden liberar
     */
    public void devuelveBilletes(int billetes, Usuario usuario) throws ReservaExceotion {
        int reservados = (int) Arrays.asList(pasajeros).stream().filter(pasajero -> pasajero == usuario).count();
        if (billetes > reservados) {
            throw new ReservaExceotion("No se pueden devolver " + billetes + " billete/s, el usuario solo tiene reservados " + reservados + " billete/s.");
        }

        int i = pasajeros.length-1;
        while (billetes > 0) {
            if (pasajeros[i] == usuario) {
                pasajeros[i] = null;
                billetes--;
            }
            i--;
        }
    }

    /**
     * Calcula y devuelve la cantidad de asientos libres.
     *
     * @return Cantidad de asientos libres.
     */
    public int cuantosAsientosQuedan() {
        return (int) Arrays.asList(pasajeros).stream().filter(pasajero -> pasajero == null).count();
    }

    public Usuario[] obtenerReservas() {
        return pasajeros;
    }
}
