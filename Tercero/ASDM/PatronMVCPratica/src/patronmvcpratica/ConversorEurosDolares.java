package patronmvcpratica;

public class ConversorEurosDolares extends ConversorMoneda{
    public ConversorEurosDolares(){
        super(1.22d);
    }
    
    public double euroAdolar(double cantidad){
        return eurosAmoneda(cantidad);
    }
    
    public double dolarAeuro(double cantidad){
        return monedaAeuro(cantidad);
    }
}
