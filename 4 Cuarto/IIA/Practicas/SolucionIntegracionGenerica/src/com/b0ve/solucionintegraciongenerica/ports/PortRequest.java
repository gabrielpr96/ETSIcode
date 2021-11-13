package com.b0ve.solucionintegraciongenerica.ports;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import org.w3c.dom.Document;

public class PortRequest extends Port {

    public PortRequest(Adapter adaptador) {
        super(1, 1, adaptador);
    }

    public void enviarProceso(Message doc) {
        throw new UnsupportedOperationException("Output ports cannot send direct messages from adapter to the process.");
    }

    @Override
    protected void sendAdapter(Message m) throws SIGException {
        Document doc = adapter.sendApp(m);
        if (doc != null) {
            Message response = new Message(doc);
            response.setCorrelationID(m.getCorrelationID());
            output(0).push(response);
        }
    }

}
