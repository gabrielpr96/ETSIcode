package com.b0ve.solucionintegraciongenerica.ports;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import org.w3c.dom.Document;

public class PortOutput extends Port{
    
    
    public PortOutput(Adapter adaptador) {
        super(1, 0, adaptador);
    }
    
    public void enviarProceso(Document doc){
        throw new UnsupportedOperationException("Output ports cannot send messages from adapter to the process.");
    }

    @Override
    protected void sendAdapter(Message m) {
        adapter.sendApp(m);
    }
    
}
