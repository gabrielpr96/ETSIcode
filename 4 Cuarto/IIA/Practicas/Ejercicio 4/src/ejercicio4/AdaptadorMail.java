package ejercicio4;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import org.w3c.dom.Document;

public class AdaptadorMail extends Adapter {

    @Override
    public Document sendApp(Message m) {
        String destino = m.evaluateXPathString("/llamada/destino");
        String email = m.evaluateXPathString("/llamada/email");
        String minutos = m.evaluateXPathString("/llamada/duracion");
        System.out.println("Enviar email ("+email+"): Has hablado "+minutos+" minutos con "+destino+".");
        return null;
    }

    @Override
    public Process.PORTS getCompatiblePortType() {
        return Process.PORTS.OUTPUT;
    }
}
