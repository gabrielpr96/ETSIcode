package patronmvc;

public interface InterfazVista {
    static final String AEUROS = "pesetasAeuros",
                        APESETAS = "eurosApesetas";
    
    public void setControlador(ControlConversor c);
    public void arranca();
    public double getCantidad();
    public void escribeCambio(String s);
}
