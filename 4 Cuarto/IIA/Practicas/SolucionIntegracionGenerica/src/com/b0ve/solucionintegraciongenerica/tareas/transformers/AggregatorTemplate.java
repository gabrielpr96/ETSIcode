package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AggregatorTemplate extends Tarea {

    public AggregatorTemplate() {
        super(1, 1);
    }

    @Override
    public final void procesar() {
        Map<Integer, List<Mensaje>> fragmentos = new HashMap<>();
        Buffer salida = salidas.get(0);
        Buffer entrada = entradas.get(0);
        for (Iterator<Mensaje> iterator = entrada.getIterator(); iterator.hasNext();) {
            Mensaje mensaje = iterator.next();
            List<Mensaje> lista = fragmentos.get(mensaje.getSequenceID());
            if (lista == null) {
                lista = new ArrayList<>();
                fragmentos.put(mensaje.getSequenceID(), lista);
            }
            lista.add(mensaje);
        }
        for (Map.Entry<Integer, List<Mensaje>> fragmento : fragmentos.entrySet()) {
            List<Mensaje> mensajes = fragmento.getValue();
            if (mensajes.get(0).getSequenceSize() == mensajes.size()) {
                for (Mensaje mensaje : mensajes) {
                    entrada.deleteMessage(mensaje);
                }
                salida.push(new Mensaje(join(mensajes.toArray(new Mensaje[0]))));
            }
        }
    }

    protected abstract String join(Mensaje[] mensajes);

}
