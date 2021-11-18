package ejercicio3;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;

public class AdaptadorCRM1Salida extends Adapter {

    private final String dir;

    public AdaptadorCRM1Salida(String dir) {
        this.dir = dir;
    }

    @Override
    public Document sendApp(Message m) {
        FileWriter myWriter = null;
        try {
            myWriter = new FileWriter(dir + "/" + m.getID() + ".xml");
            myWriter.write(m.getBodyString());
            myWriter.close();
        } catch (IOException ex) {
            Logger.getLogger(AdaptadorCRM1Salida.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public com.b0ve.solucionintegraciongenerica.utils.Process.PORTS getCompatiblePortType() {
        return com.b0ve.solucionintegraciongenerica.utils.Process.PORTS.OUTPUT;
    }
}
