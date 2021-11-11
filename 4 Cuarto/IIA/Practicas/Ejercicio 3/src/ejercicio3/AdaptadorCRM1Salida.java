package ejercicio3;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdaptadorCRM1Salida extends Adaptador {

    private final String dir;

    public AdaptadorCRM1Salida(String dir) {
        this.dir = dir;
    }

    @Override
    public void enviarApp(Mensaje m) {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(dir + "/" + m.getID() + ".xml");
            myWriter.write(m.getBodyString());
            myWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(AdaptadorCRM1Salida.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
