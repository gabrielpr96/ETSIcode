package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public abstract class CorrelatorTemplate extends Task {

    public CorrelatorTemplate() {
        super(0, 0);
    }

    @Override
    public final void process() throws SIGException {
        //Bloquear las nuevas entradas
        lockPushes();
        //Buscar en los mensajes del primer buffer, uno a uno en los otros buffers
        Map<Message, List<Message>> relations = new HashMap<>();
        for (Iterator<Message> iteratorMain = input(0).getIterator(); iteratorMain.hasNext();) {
            Message objective = iteratorMain.next();
            //Lista de mensajes que coinciden, el primero ya lo sabemos
            relations.put(objective, new ArrayList<>());
            //Buscar en cada buffer
            for (int i = 1; i < nInputs(); i++) {
                //Recorrer los mensajes del buffer
                boolean found = false;
                Iterator<Message> iteratorSecondary = input(i).getIterator();
                while (iteratorSecondary.hasNext() && !found) {
                    Message searched = iteratorSecondary.next();
                    if (route(objective, searched)) {
                        relations.get(objective).add(searched);
                        found = true;
                        //No importa si dejamos huecos, porque entonces los numeros no cuendran y no se hace nada con los mensajes
                    }
                }
            }
        }

        for (Map.Entry<Message, List<Message>> relation : relations.entrySet()) {
            Message first = relation.getKey();
            List<Message> others = relation.getValue();

            if (others.size() == nInputs() - 1) {
                input(0).deleteMessage(first);
                output(0).push(first);
                for (int i = 0; i < others.size(); i++) {
                    input(i + 1).deleteMessage(others.get(i));
                    output(i + 1).push(others.get(i));
                }
            }
        }
        //Desbloquear las nuevas entradas
        unlockPushes();
    }

    protected boolean route(Message m1, Message m2) throws SIGException {
        return m1.getCorrelationID() == m2.getCorrelationID();
    }

}
