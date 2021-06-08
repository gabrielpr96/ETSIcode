package examen2020;

public class Estacion implements IObservador{
    public static final int[] DISTANCIAS = new int[]{13, 48, 23, 29};
    public static final String[] NOMBRES = new String[]{"Huelva", "Gibraleon", "La Palma", "Bollullos", "Sevilla"};

    private final String nombre;
    private final int distanciaSiguiente, distanciaAnterior, nEstacion;

    public Estacion(String nombre, int distanciaSiguiente, int distanciaAnterior, int nEstacion) {
        this.nombre = nombre;
        this.distanciaSiguiente = distanciaSiguiente;
        this.distanciaAnterior = distanciaAnterior;
        this.nEstacion = nEstacion;
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
        if(eActual > nEstacion){
            if(direccion){
                //Ir a 4 y venir
                for (int i = eActual; i < 4; i++) {
                    distancia += DISTANCIAS[i];
                }
                for (int i = 3; i >= nEstacion; i--) {
                    distancia += DISTANCIAS[i];
                }
            }else{
                //Volver
                for (int i = eActual-1; i >= nEstacion; i--) {
                    distancia += DISTANCIAS[i];
                }
            }
        }else if(eActual < nEstacion){
            if(direccion){
                //Ir
                for (int i = eActual; i < nEstacion; i++) {
                    distancia += DISTANCIAS[i];
                }
            }else{
                //Ir a 0 y volver
                for (int i = eActual-1; i >= 0; i--) {
                    distancia += DISTANCIAS[i];
                }
                for (int i = 0; i < nEstacion; i++) {
                    distancia += DISTANCIAS[i];
                }
            }
        }
        
        cartelera.append("ESTACION: ").append(nombre.toUpperCase()).append("\n----------------------\nPlazas disponibles: ")
                .append(plazasDisponibles).append("\nEstado: ").append(estado?"en marcha":"parado")
                .append("\nDireccion: ").append(direccion?"Huelva-Sevilla":"Sevilla-Huelva")
                .append("\n\nESTACION: ").append(nombre.toUpperCase()).append("\n----------------------\nDistancia llegada tren: ")
                .append(distancia).append(" Km\nTiempo estimado de llegada: ").append(Math.round((distancia/53f)*60f)).append(" minutos\n");
        
        System.out.println(cartelera.toString());
    }
    
    
}
