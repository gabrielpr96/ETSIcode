package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;

public abstract class AggregatorTemplate extends Tarea {

    public AggregatorTemplate() {
        super(1, 1);
    }

    @Override
    public final void procesar() {
        //Bloquear las nuevas entradas
        lockPushes();
        Map<Long, List<Mensaje>> fragmentos = new HashMap<>();
        Buffer salida = salidas.get(0);
        Buffer entrada = entradas.get(0);
        for (Iterator<Mensaje> iterator = entrada.getIterator(); iterator.hasNext();) {
            Mensaje mensaje = iterator.next();
            List<Mensaje> lista = fragmentos.get(mensaje.getFragmentID());
            if (lista == null) {
                lista = new ArrayList<>();
                fragmentos.put(mensaje.getFragmentID(), lista);
            }
            lista.add(mensaje);
        }
        for (Map.Entry<Long, List<Mensaje>> fragmento : fragmentos.entrySet()) {
            List<Mensaje> mensajes = fragmento.getValue();
            if (mensajes.get(0).getFragmentSize()== mensajes.size()) {
                for (Mensaje mensaje : mensajes) {
                    entrada.deleteMessage(mensaje);
                }
                salida.push(new Mensaje(join(mensajes.toArray(new Mensaje[0]))));
            }
        }
        //Desbloquear las nuevas entradas
        unlockPushes();
    }

    protected abstract Document join(Mensaje[] mensajes);

}
