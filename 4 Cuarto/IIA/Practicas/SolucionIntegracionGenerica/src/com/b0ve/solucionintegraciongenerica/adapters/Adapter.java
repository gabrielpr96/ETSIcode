package com.b0ve.solucionintegraciongenerica.adapters;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.ports.Port;
import com.b0ve.solucionintegraciongenerica.ports.PortInput;
import com.b0ve.solucionintegraciongenerica.utils.Process.PORTS;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ExecutionException;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public abstract class Adapter {

    private Port puerto;

    public Adapter() {
    }

    public void setPuerto(Port puerto) {
        this.puerto = puerto;
    }

    public Document sendApp(Message m){
        throw new ExecutionException("This port does not support sending direct messages to the app");
    }

    protected void sendPort(Document doc) {
        if (puerto != null && puerto instanceof PortInput) {
            ((PortInput) puerto).enviarProceso(doc);
        }
    }

    protected void sendPort(String xml) throws ParserConfigurationException, SAXException, IOException {
        sendPort(Message.parseXML(xml));
    }

    public void halt() {
    }

    public void iniciate() {
    }
    
    public abstract PORTS getCompatiblePortType();
}
