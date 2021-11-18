package ejercicio2;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.XPathEvaluationException;
import org.w3c.dom.Document;

public class AdaptadorEstimador extends Adapter {

    @Override
    public Document sendApp(Message m) throws XPathEvaluationException {
        String ts = m.evaluateXPathString("/medida/ts");
        String lugar = m.evaluateXPathString("/medida/lugar");
        String valor = m.evaluateXPathString("/medida/valor");
        System.out.println("Procesar medida: "+ts+" "+lugar+" -> "+valor);
        return null;
    }

    @Override
    public Process.PORTS getCompatiblePortType() {
        return Process.PORTS.OUTPUT;
    }
}
