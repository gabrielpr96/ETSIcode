package com.b0ve.solucionintegraciongenerica.ports;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import org.w3c.dom.Document;

public class PortInput extends Port{
    
    
    public PortInput(Adapter adaptador) {
        super(0, 1, adaptador);
    }
    
    public void enviarProceso(Document doc){
        salidas.get(0).push(new Message(doc));
    }

    @Override
    protected void sendAdapter(Message m) {
        throw new UnsupportedOperationException("Input ports cannot send messages from process to the adapter.");
    }
    
}
