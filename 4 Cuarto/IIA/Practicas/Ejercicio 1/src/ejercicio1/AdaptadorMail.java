package ejercicio1;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import org.w3c.dom.Document;

public class AdaptadorMail extends Adapter {
    @Override
    public Document sendApp(Message m) {
        String asignatura = m.evaluateXPathString("/alumno/asignatura");
        String email = m.evaluateXPathString("/alumno/email");
        String id = m.evaluateXPathString("/alumno/id");
        String nota = m.evaluateXPathString("/alumno/nota");
        System.out.println("Enviar email ("+email+"): Has sacado un "+nota+" en la asignatura "+asignatura+". "+id);
        return null;
    }

    @Override
    public Process.PORTS getCompatiblePortType() {
        return Process.PORTS.OUTPUT;
    }
}
