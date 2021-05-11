package patronmvcpratica;

public interface InterfazVista {
    static final String SALIR = "salir",
                        CCAMBIO_INTERFAZ_CLI = "cambioInterfazTexo",
                        CCAMBIO_INTERFAZ_GUI = "cambioInterfazGrafica",
                        PESETAS_A_EUROS = "pesetasAeuros",
                        EUROS_A_PESETAS = "eurosApesetas",
                        LIBRAS_A_EUROS = "libreasAeuros",
                        EUROS_A_LIBRAS = "eurosAlibreas",
                        DOLARES_A_EUROS = "dolaresAeuros",
                        EUROS_A_DOLARES = "eurosAdolares";
    
    public void setControlador(ControlConversor c);
    public void arranca();
    public void detener();
    public double getCantidad();
    public void escribeCambio(String s);
}
