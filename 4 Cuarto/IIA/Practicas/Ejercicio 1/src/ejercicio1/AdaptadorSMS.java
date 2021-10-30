package ejercicio1;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public class AdaptadorSMS extends Adaptador {

    @Override
    public void enviarApp(Mensaje m) {
        String asignatura = m.evaluateXPathString("/alumno/asignatura");
        String email = m.evaluateXPathString("/alumno/email");
        String telefono = m.evaluateXPathString("/alumno/telefono");
        String id = m.evaluateXPathString("/alumno/id");
        String nota = m.evaluateXPathString("/alumno/nota");
        System.out.println("Enviar SMS ("+telefono+"): Has sacado un "+nota+" en la asignatura "+asignatura+". "+id);
    }
}
