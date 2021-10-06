package patronfactorypractica;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RelojMDA24 extends Reloj{
    SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss MM/dd/yyyy");
    
    @Override
    public String obtenerHora() {
        return formato.format(new Date());
    }
    
    @Override
    public String toString() {
        return "Reloj DMA 24h";
    }
}
