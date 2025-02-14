package patronfactorypractica;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RelojDMA12 extends Reloj{
    SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss a dd/MM/yyyy");
    
    @Override
    public String obtenerHora() {
        return formato.format(new Date());
    }

    @Override
    public String toString() {
        return "Reloj DMA 12h";
    }
}
