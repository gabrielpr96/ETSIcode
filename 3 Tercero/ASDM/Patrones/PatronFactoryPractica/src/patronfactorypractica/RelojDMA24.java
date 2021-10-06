package patronfactorypractica;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RelojDMA24 extends Reloj{
    SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
    
    @Override
    public String obtenerHora() {
        return formato.format(new Date());
    }
    
    @Override
    public String toString() {
        return "Reloj DMA 24h";
    }
}
