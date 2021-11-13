package com.b0ve.solucionintegraciongenerica.tasks.transformers;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.w3c.dom.Document;

/**
 * Creates original message composed from the fragments collected.
 * @author borja
 */
public abstract class AggregatorTemplate extends Task {

    public AggregatorTemplate() {
        super(1, 1);
    }

    @Override
    public final void process() throws SIGException {
        //Bloquear las nuevas entradas
        lockPushes();
        Map<Long, List<Message>> fragments = new HashMap<>();
        Buffer output = output(0);
        Buffer input = input(0);
        for (Iterator<Message> iterator = input.getIterator(); iterator.hasNext();) {
            Message m = iterator.next();
            List<Message> list = fragments.get(m.getFragmentID());
            if (list == null) {
                list = new ArrayList<>();
                fragments.put(m.getFragmentID(), list);
            }
            list.add(m);
        }
        for (Map.Entry<Long, List<Message>> fragment : fragments.entrySet()) {
            List<Message> messages = fragment.getValue();
            if (messages.get(0).getFragmentSize() == messages.size()) {
                for (Message mensaje : messages) {
                    input.deleteMessage(mensaje);
                }
                Message mensaje = new Message(join(messages.toArray(new Message[0])));
                messages.get(0).removeFragmentInfo();
                mensaje.addFragmentInfo(messages.get(0).getFragmentInfoStack());
                output.push(mensaje);
            }
        }
        //Desbloquear las nuevas entradas
        unlockPushes();
    }

    protected abstract Document join(Message[] messages) throws SIGException;

}
