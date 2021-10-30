package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class AssemblerTemplate extends Tarea {

    public AssemblerTemplate() {
        super(0, 1);
    }

    @Override
    public final void procesar() {
        Map<Long, Mensaje[]> fragmentos = new HashMap<>();
        Buffer salida = salidas.get(0);
        for (int i = 0; i < entradas.size(); i++) {
            for (Iterator<Mensaje> iterator = entradas.get(i).getIterator(); iterator.hasNext();) {
                Mensaje mensaje = iterator.next();
                Mensaje[] lista = fragmentos.get(mensaje.getSequenceID());
                if (lista == null) {
                    lista = new Mensaje[entradas.size()];
                    fragmentos.put(mensaje.getSequenceID(), lista);
                }
                lista[i] = mensaje;
            }
        }
        for (Map.Entry<Long, Mensaje[]> fragmento : fragmentos.entrySet()) {
            Mensaje[] mensajes = fragmento.getValue();
            boolean completo = true;
            int i = 0;
            while (completo && i < mensajes.length) {
                if (mensajes[i] == null) {
                    completo = false;
                } else {
                    i++;
                }
            }
            if (completo) {
                for (i = 0; i < mensajes.length; i++) {
                    entradas.get(i).deleteMessage(mensajes[i]);
                }
                salida.push(new Mensaje(join(mensajes)));
            }
        }
    }

    protected abstract String join(Mensaje[] mensajes);

}
