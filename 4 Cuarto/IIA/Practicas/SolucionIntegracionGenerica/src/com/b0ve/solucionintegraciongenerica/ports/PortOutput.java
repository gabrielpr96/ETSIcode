package com.b0ve.solucionintegraciongenerica.ports;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import org.w3c.dom.Document;

public class PortOutput extends Port {

    public PortOutput(Adapter adaptador) {
        super(1, -1, adaptador);
    }

    /**
     * Output ports cannot send messages to the process.
     * @param doc 
     */
    @Override
    public void sendProcess(Document doc) {
        throw new UnsupportedOperationException("Output ports cannot send messages from adapter to the process.");
    }

    /**
     * Sends a message to the adapter so it sends it to the app.
     * @param m
     * @throws SIGException 
     */
    @Override
    protected void sendApp(Message m) throws SIGException {
        adapter.sendApp(m);
    }

}
