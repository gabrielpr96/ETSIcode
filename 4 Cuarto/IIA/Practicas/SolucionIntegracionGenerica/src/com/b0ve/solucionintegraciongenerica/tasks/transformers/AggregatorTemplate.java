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

public abstract class AggregatorTemplate extends Task {

    public AggregatorTemplate() {
        super(1, 1);
    }

    @Override
    public final void procesar() {
        //Bloquear las nuevas entradas
        lockPushes();
        Map<Long, List<Message>> fragmentos = new HashMap<>();
        Buffer salida = salidas.get(0);
        Buffer entrada = entradas.get(0);
        for (Iterator<Message> iterator = entrada.getIterator(); iterator.hasNext();) {
            Message mensaje = iterator.next();
            List<Message> lista = fragmentos.get(mensaje.getFragmentID());
            if (lista == null) {
                lista = new ArrayList<>();
                fragmentos.put(mensaje.getFragmentID(), lista);
            }
            lista.add(mensaje);
        }
        for (Map.Entry<Long, List<Message>> fragmento : fragmentos.entrySet()) {
            List<Message> mensajes = fragmento.getValue();
            if (mensajes.get(0).getFragmentSize()== mensajes.size()) {
                for (Message mensaje : mensajes) {
                    entrada.deleteMessage(mensaje);
                }
                Message mensaje = new Message(join(mensajes.toArray(new Message[0])));
                mensajes.get(0).removeFragmentInfo();
                mensaje.addFragmentInfo(mensajes.get(0).getFragmentInfoStack());
                salida.push(mensaje);
            }
        }
        //Desbloquear las nuevas entradas
        unlockPushes();
    }

    protected abstract Document join(Message[] mensajes);

}
