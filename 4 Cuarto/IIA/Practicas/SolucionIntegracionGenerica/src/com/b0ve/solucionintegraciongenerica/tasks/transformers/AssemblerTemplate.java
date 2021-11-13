package com.b0ve.solucionintegraciongenerica.tasks.transformers;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.w3c.dom.Document;

public abstract class AssemblerTemplate extends Task {

    public AssemblerTemplate() {
        super(0, 1);
    }

    @Override
    public final void process() throws SIGException {
        Map<Long, Message[]> fragments = new HashMap<>();
        Buffer salida = output(0);
        for (int i = 0; i < nInputs(); i++) {
            for (Iterator<Message> iterator = input(i).getIterator(); iterator.hasNext();) {
                Message m = iterator.next();
                Message[] list = fragments.get(m.getFragmentID());
                if (list == null) {
                    list = new Message[nInputs()];
                    fragments.put(m.getFragmentID(), list);
                }
                list[i] = m;
            }
        }
        for (Map.Entry<Long, Message[]> fragment : fragments.entrySet()) {
            Message[] messages = fragment.getValue();
            boolean complete = true;
            int i = 0;
            while (complete && i < messages.length) {
                if (messages[i] == null) {
                    complete = false;
                } else {
                    i++;
                }
            }
            if (complete) {
                for (i = 0; i < messages.length; i++) {
                    input(i).deleteMessage(messages[i]);
                }
                salida.push(new Message(join(messages)));
            }
        }
    }

    protected abstract Document join(Message[] messages) throws SIGException;

}
