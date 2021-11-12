package com.b0ve.solucionintegraciongenerica.tareas.routers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class CorrelatorTemplate extends Tarea {

    public CorrelatorTemplate() {
        super(0, 0);
    }

    @Override
    public final void procesar() {
        //Bloquear las nuevas entradas
        lockPushes();
        //Buscar en los mensajes del primer buffer, uno a uno en los otros buffers
        Map<Mensaje, List<Mensaje>> relacionados = new HashMap<>();
        for (Iterator<Mensaje> iteradorPrincipal = entradas.get(0).getIterator(); iteradorPrincipal.hasNext();) {
            Mensaje objetivo = iteradorPrincipal.next();
            //Lista de mensajes que coinciden, el primero ya lo sabemos
            relacionados.put(objetivo, new ArrayList<>());
            //Buscar en cada buffer
            for (int i = 1; i < entradas.size(); i++) {
                //Recorrer los mensajes del buffer
                boolean encontrado = false;
                Iterator<Mensaje> iteratorSecundario = entradas.get(i).getIterator();
                while (iteratorSecundario.hasNext() && !encontrado) {
                    Mensaje buscado = iteratorSecundario.next();
                    if (comparar(objetivo, buscado)) {
                        relacionados.get(objetivo).add(buscado);
                        encontrado = true;
                        //No importa si dejamos huecos, porque entonces los numeros no cuendran y no se hace nada con los mensajes
                    }
                }
            }
        }
        
        for (Map.Entry<Mensaje, List<Mensaje>> relacion : relacionados.entrySet()) {
            Mensaje primero = relacion.getKey();
            List<Mensaje> otros = relacion.getValue();
            
            if (otros.size() == entradas.size()-1) {
                entradas.get(0).deleteMessage(primero);
                salidas.get(0).push(primero);
                for (int i = 0; i < otros.size(); i++) {
                    entradas.get(i+1).deleteMessage(otros.get(i));
                    salidas.get(i+1).push(otros.get(i));
                }
            }
        }
        //Desbloquear las nuevas entradas
        unlockPushes();
    }

    protected boolean comparar(Mensaje m1, Mensaje m2) {
        return m1.getCorrelationID() == m2.getCorrelationID();
    }

}
