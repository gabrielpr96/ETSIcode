package patronmvcpratica;

public class ConversorEurosLibras extends ConversorMoneda{
    public ConversorEurosLibras(){
        super(0.86d);
    }
    
    public double euroAlibra(double cantidad){
        return eurosAmoneda(cantidad);
    }
    
    public double libraAeuro(double cantidad){
        return monedaAeuro(cantidad);
    }
}
