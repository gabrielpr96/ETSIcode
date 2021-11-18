package ejercicio4;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.XPathEvaluationException;
import org.w3c.dom.Document;

public class AdaptadorDS extends Adapter {

    @Override
    public Document sendApp(Message m) throws XPathEvaluationException {
        String minutos = m.evaluateXPathString("/llamada/duracion");
        String dni = m.evaluateXPathString("/llamada/dni");
        System.out.println("Cobrar a "+dni+" una llamada de "+minutos+" minutos.");
        return null;
    }

    @Override
    public Process.PORTS getCompatiblePortType() {
        return Process.PORTS.OUTPUT;
    }
}
