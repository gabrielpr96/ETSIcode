package lopez_pineda_borja;

public class Fecha {

    private final int hora, minuto;

    public Fecha(int hora, int minuto) {
        this.hora = hora;
        this.minuto = minuto;
    }

    /**
     * Compara si la fecha es anterior que la pasada por parametro
     *
     * @param f2 Fecha con la que se compara
     * @return True si f2 es mayor que this
     */
    public boolean anterior(Fecha f2) {
        return hora < f2.hora || (hora == f2.hora && minuto < f2.minuto);
    }

    @Override
    public String toString() {
        return hora + ":=" + minuto;
    }

}
