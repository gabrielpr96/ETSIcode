package ejercicio3;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import org.w3c.dom.Document;

public class AdaptadorCRM2Salida extends Adapter {

    @Override
    public Document sendApp(Message m) {
        System.out.println("CAMBIO: "+m.getBodyString());
        return null;
    }

    @Override
    public com.b0ve.solucionintegraciongenerica.utils.Process.PORTS getCompatiblePortType() {
        return com.b0ve.solucionintegraciongenerica.utils.Process.PORTS.OUTPUT;
    }
}
