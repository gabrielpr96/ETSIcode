package com.b0ve.solucionintegraciongenerica.adapters;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.ports.Port;
import com.b0ve.solucionintegraciongenerica.ports.PortInput;
import com.b0ve.solucionintegraciongenerica.utils.Process.PORTS;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ExecutionException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ParseException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import org.w3c.dom.Document;

public abstract class Adapter {

    private Port port;

    public Adapter() {
    }

    public void setPuerto(Port puerto) {
        this.port = puerto;
    }

    public Document sendApp(Message m) throws SIGException {
        throw new ExecutionException("This port does not support sending direct messages to the app", null, null);
    }

    protected void sendPort(Document doc) {
        if (port != null && port instanceof PortInput) {
            ((PortInput) port).sendProcess(doc);
        }
    }

    protected void sendPort(String xml) throws ParseException {
        sendPort(Message.parseXML(xml));
    }

    public void halt() {
    }

    public void iniciate() {
    }

    public abstract PORTS getCompatiblePortType();

    public void handleException(SIGException exception) {
        if (port != null) {
            port.handleException(exception);
        }
    }
}
