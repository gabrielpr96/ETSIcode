package com.b0ve.solucionintegraciongenerica.tareas.routers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class CorrelatorTemplate extends Tarea {

    public CorrelatorTemplate() {
        super(0, 0);
    }

    @Override
    public final void procesar() {
        //Buscar en los mensajes del primer buffer, uno a uno en los otros buffers
        for (Iterator<Mensaje> iteradorPrincipal = entradas.get(0).getIterator(); iteradorPrincipal.hasNext();) {
            Mensaje objetivo = iteradorPrincipal.next();
            //Lista de mensajes que coinciden, el primero ya lo sabemos
            List<Mensaje> relacionados = new ArrayList<>();
            relacionados.add(objetivo);
            //Buscar en cada buffer
            for (int i = 1; i < entradas.size(); i++) {
                //Recorrer los mensajes del buffer
                boolean encontrado = false;
                Iterator<Mensaje> iteratorSecundario = entradas.get(i).getIterator();
                while (iteratorSecundario.hasNext() && !encontrado) {
                    Mensaje buscado = iteratorSecundario.next();
                    if (comparar(objetivo, buscado)) {
                        relacionados.add(buscado);
                        encontrado = true;
                        //No importa si dejamos huecos, porque entonces los numeros no cuendran y no se hace nada con los mensajes
                    }
                }
            }
            if (relacionados.size() == entradas.size()) {
                for (int i = 0; i < entradas.size(); i++) {
                    //Nunca modificar la lista mientras se itera, hay que usar el metodo del iterador
                    if(i == 0)
                        iteradorPrincipal.remove();
                    else
                        entradas.get(i).deleteMessage(relacionados.get(i));
                    salidas.get(i).push(relacionados.get(i));
                }
            }
        }
    }

    protected boolean comparar(Mensaje m1, Mensaje m2) {
        return m1.getCorrelationID() == m2.getCorrelationID();
    }

}
