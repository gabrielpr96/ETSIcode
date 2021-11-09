package ejercicio3Mejorado;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.transform.TransformerException;

class AdaptadorSET extends Adaptador {

    private final Set<String> set;

    public AdaptadorSET() {
        set = new HashSet<>();
    }

    @Override
    public void enviarApp(Mensaje m) {
        String accion = m.evaluateXPathString("consulta/accion");
        String valor = m.evaluateXPathString("consulta/valor");
        boolean resultado = false;
        if (accion.equals("crear")) {
            resultado = set.contains(valor);
            if (!resultado) {
                set.add(accion);
            }
        } else if (accion.equals("eliminar")) {
            resultado = !set.contains(valor);
            if (!resultado) {
                set.remove(valor);
            }
        }
        System.out.println("Me preguntan por: " + accion + " " + valor + " le digo que " + resultado);
        try {
            enviarPuerto(new Mensaje("<contenido>" + (resultado ? "true" : "false") + "</contenido>", m.getCorrelationID()));
        } catch (TransformerException ex) {
            Logger.getLogger(AdaptadorSET.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
