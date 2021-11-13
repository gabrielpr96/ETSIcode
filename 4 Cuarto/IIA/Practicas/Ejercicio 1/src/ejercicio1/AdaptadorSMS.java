package ejercicio1;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import org.w3c.dom.Document;

public class AdaptadorSMS extends Adapter {

    @Override
    public Document sendApp(Message m) {
        String asignatura = m.evaluateXPathString("/alumno/asignatura");
        String telefono = m.evaluateXPathString("/alumno/telefono");
        String id = m.evaluateXPathString("/alumno/id");
        String nota = m.evaluateXPathString("/alumno/nota");
        System.out.println("Enviar SMS ("+telefono+"): Has sacado un "+nota+" en la asignatura "+asignatura+". "+id);
        return null;
    }

    @Override
    public Process.PORTS getCompatiblePortType() {
        return Process.PORTS.OUTPUT;
    }
}
