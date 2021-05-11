package patronmvcpratica;

public class ConversorMoneda {
    private final double cambio;
    
    public ConversorMoneda(double valorCambio){
        cambio = valorCambio;
    }
    
    protected double eurosAmoneda(double cantidad){
        return cantidad*cambio;
    }
    
    protected double monedaAeuro(double cantidad){
        return cantidad/cambio;
    }
}
