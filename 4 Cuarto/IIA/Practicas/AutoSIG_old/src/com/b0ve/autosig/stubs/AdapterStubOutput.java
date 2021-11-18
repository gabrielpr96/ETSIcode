package com.b0ve.autosig.stubs;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import java.util.ArrayList;
import org.w3c.dom.Document;

public class AdapterStubOutput extends Adapter {

    private final ArrayList<Message> messages;

    public AdapterStubOutput() {
        messages = new ArrayList<>();
    }

    public Message[] getMessages() {
        Message[] data = messages.toArray(new Message[0]);
        messages.clear();
        return data;
    }

    @Override
    public Document sendApp(Message m) throws SIGException {
        messages.add(m);
        return null;
    }

    @Override
    public Process.PORTS getCompatiblePortType() {
        return Process.PORTS.OUTPUT;
    }

}
