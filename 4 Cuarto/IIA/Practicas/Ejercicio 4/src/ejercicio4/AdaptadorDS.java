package ejercicio4;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;

public class AdaptadorDS extends Adaptador {

    @Override
    public void enviarApp(Mensaje m) {
        String minutos = m.evaluateXPathString("/llamada/duracion");
        String dni = m.evaluateXPathString("/llamada/dni");
        System.out.println("Cobrar a "+dni+" una llamada de "+minutos+" minutos.");
    }
}
