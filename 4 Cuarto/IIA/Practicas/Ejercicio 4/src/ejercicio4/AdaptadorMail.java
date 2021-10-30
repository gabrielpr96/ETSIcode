package ejercicio4;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public class AdaptadorMail extends Adaptador {

    @Override
    public void enviarApp(Mensaje m) {
        String destino = m.evaluateXPathString("/llamada/destino");
        String email = m.evaluateXPathString("/llamada/email");
        String minutos = m.evaluateXPathString("/llamada/duracion");
        System.out.println("Enviar email ("+email+"): Has hablado "+minutos+" minutos con "+destino+".");
    }
}
