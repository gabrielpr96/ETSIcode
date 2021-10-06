package examen2020;

public class Estacion implements IObservador {

    public static final int[] DISTANCIAS = new int[]{13, 48, 23, 29};
    public static final String[] NOMBRES = new String[]{"Huelva", "Gibraleon", "La Palma", "Bollullos", "Sevilla"};

    private final String nombre;
    private final int distanciaSiguiente, distanciaAnterior, nEstacion;
    private final Estaciones estaciones;

    public Estacion(String nombre, int distanciaSiguiente, int distanciaAnterior, int nEstacion, Estaciones estaciones) {
        this.nombre = nombre;
        this.distanciaSiguiente = distanciaSiguiente;
        this.distanciaAnterior = distanciaAnterior;
        this.nEstacion = nEstacion;
        this.estaciones = estaciones;
    }

    public String getNombre() {
        return nombre;
    }

    public int getDistanciaSiguiente() {
        return distanciaSiguiente;
    }

    public int getDistanciaAnterior() {
        return distanciaAnterior;
    }

    @Override
    public void actualizar(int plazasDisponibles, boolean direccion, boolean estado) {
        Tren tren = Tren.getTren();
        StringBuilder cartelera = new StringBuilder();

        int eActual = tren.getNEstacion();
        int distancia = 0;
        Iterador iter = estaciones.getIterador();
        if (eActual > nEstacion) {
            if (direccion) {
                //Ir a 4 y venir
                for (int i = 0; i < eActual; i++) {
                    iter.siguiente();
                }
                while (iter.haySiguiente()) {
                    distancia += iter.siguiente().distanciaSiguiente;
                }
                for (int i = 4; i > nEstacion; i--) {
                    distancia += iter.anterior().distanciaAnterior;
                }
            } else {
                //Volver
                for (int i = 0; i < nEstacion; i++) {
                    iter.siguiente();
                }
                for (int i = nEstacion; i < eActual; i++) {
                    distancia += iter.siguiente().distanciaSiguiente;
                }
            }
        } else if (eActual < nEstacion) {
            if (direccion) {
                //Ir
                for (int i = 0; i < eActual; i++) {
                    System.out.println("AVANZA");
                    iter.siguiente();
                }
                for (int i = eActual; i < nEstacion; i++) {
                    distancia += iter.siguiente().distanciaSiguiente;
                    System.out.println("IR " + distancia);
                }
            } else {
                //Ir a 0 y volver
                for (int i = 0; i < eActual; i++) {
                    distancia += iter.siguiente().distanciaSiguiente;
                }
                distancia *= 2;
                for (int i = eActual; i < nEstacion; i++) {
                    distancia += iter.siguiente().distanciaSiguiente;
                }
            }
        }

        cartelera.append("ESTACION: ").append(nombre.toUpperCase()).append("\n----------------------\nPlazas disponibles: ")
                .append(plazasDisponibles).append("\nEstado: ").append(estado ? "en marcha" : "parado")
                .append("\nDireccion: ").append(direccion ? "Huelva-Sevilla" : "Sevilla-Huelva")
                .append("\n\nESTACION: ").append(nombre.toUpperCase()).append("\n----------------------\nDistancia llegada tren: ")
                .append(distancia).append(" Km\nTiempo estimado de llegada: ").append(Math.round((distancia / 53f) * 60f)).append(" minutos\n");

        System.out.println(cartelera.toString());
    }

}
