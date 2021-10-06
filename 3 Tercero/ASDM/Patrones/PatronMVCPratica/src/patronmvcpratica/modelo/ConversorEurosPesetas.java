package patronmvcpratica.modelo;

public class ConversorEurosPesetas extends ConversorMoneda{
    public ConversorEurosPesetas(){
        super(166.368d);
    }
    
    public double euroApeseta(double cantidad){
        return eurosAmoneda(cantidad);
    }
    
    public double pesetaAeuro(double cantidad){
        return monedaAeuro(cantidad);
    }
}
