package patronfactorypractica;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RelojISO extends Reloj{
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    
    @Override
    public String obtenerHora() {
        return formato.format(new Date());
    }

    @Override
    public String toString() {
        return "Reloj ISO";
    }
}
