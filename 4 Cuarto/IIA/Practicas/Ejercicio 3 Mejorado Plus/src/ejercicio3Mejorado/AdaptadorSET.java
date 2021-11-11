package ejercicio3Mejorado;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import org.xml.sax.SAXException;

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
                set.add(valor);
            }
        } else if (accion.equals("eliminar")) {
            resultado = !set.contains(valor);
            if (!resultado) {
                set.remove(valor);
            }
        }
        System.out.println("Me preguntan por: " + accion + " " + valor + " le digo que " + resultado);
        try {
            Mensaje mensaje = new Mensaje("<contenido>" + (resultado ? "true" : "false") + "</contenido>");
            mensaje.setCorrelationID(m.getCorrelationID());
            enviarPuerto(mensaje);
        } catch (ParserConfigurationException | SAXException | IOException ex) {
            Logger.getLogger(AdaptadorSET.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
