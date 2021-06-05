package patronfactorypractica;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class RelojISOUTC extends Reloj{
    SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss'Z'");
    
    public RelojISOUTC(){
        super();
        formato.setTimeZone(TimeZone.getTimeZone("UTC"));
    }
    
    @Override
    public String obtenerHora() {
        return formato.format(new Date());
    }

    @Override
    public String toString() {
        return "Reloj ISO UTC";
    }
}
