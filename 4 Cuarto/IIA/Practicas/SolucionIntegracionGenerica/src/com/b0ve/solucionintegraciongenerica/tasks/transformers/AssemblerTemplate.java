package com.b0ve.solucionintegraciongenerica.tasks.transformers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;

public abstract class AssemblerTemplate extends Task {

    public AssemblerTemplate() {
        super(0, 1);
    }

    @Override
    public final void procesar() {
        Map<Long, Message[]> fragmentos = new HashMap<>();
        Buffer salida = salidas.get(0);
        for (int i = 0; i < entradas.size(); i++) {
            for (Iterator<Message> iterator = entradas.get(i).getIterator(); iterator.hasNext();) {
                Message mensaje = iterator.next();
                Message[] lista = fragmentos.get(mensaje.getFragmentID());
                if (lista == null) {
                    lista = new Message[entradas.size()];
                    fragmentos.put(mensaje.getFragmentID(), lista);
                }
                lista[i] = mensaje;
            }
        }
        for (Map.Entry<Long, Message[]> fragmento : fragmentos.entrySet()) {
            Message[] mensajes = fragmento.getValue();
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
                salida.push(new Message(join(mensajes)));
            }
        }
    }

    protected abstract Document join(Message[] mensajes);

}
