package com.b0ve.solucionintegraciongenerica.ports;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ConfigurationException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ParseException;
import org.w3c.dom.Document;

public class PortInput extends Port {

    public PortInput(Adapter adaptador) {
        super(-1, 1, adaptador);
    }

    public void sendProcess(Document doc) {
        try {
            output(0).push(new Message(doc));
        } catch (ConfigurationException | ParseException ex) {
            handleException(ex);
        }
    }

    @Override
    protected void sendAdapter(Message m) {
        throw new UnsupportedOperationException("Input ports cannot send messages from process to the adapter.");
    }

}
