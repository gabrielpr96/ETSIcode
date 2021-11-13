package com.b0ve.solucionintegraciongenerica.adapters;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import java.util.HashSet;
import java.util.Set;
import org.w3c.dom.Document;

public class AdapterSET extends Adapter {

    private final Set<String> set;

    public AdapterSET() {
        set = new HashSet<>();
    }

    @Override
    public Document sendApp(Message m) {
        try {
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
            return Message.parseXML("<contenido>" + (resultado ? "true" : "false") + "</contenido>");
        } catch (SIGException ex) {
            handleException(ex);
        }
        return null;
    }

    @Override
    public Process.PORTS getCompatiblePortType() {
        return Process.PORTS.REQUEST;
    }

}
