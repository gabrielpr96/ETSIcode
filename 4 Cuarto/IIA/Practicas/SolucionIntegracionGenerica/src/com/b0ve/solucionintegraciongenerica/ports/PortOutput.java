package com.b0ve.solucionintegraciongenerica.ports;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import org.w3c.dom.Document;

public class PortOutput extends Port {

    public PortOutput(Adapter adaptador) {
        super(1, -1, adaptador);
    }

    public void enviarProceso(Document doc) {
        throw new UnsupportedOperationException("Output ports cannot send messages from adapter to the process.");
    }

    @Override
    protected void sendAdapter(Message m) throws SIGException {
        adapter.sendApp(m);
    }

}
