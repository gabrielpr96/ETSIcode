package com.b0ve.solucionintegraciongenerica.adapters;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.ports.Port;
import com.b0ve.solucionintegraciongenerica.ports.PortInput;
import com.b0ve.solucionintegraciongenerica.utils.Process.PORTS;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ExecutionException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ParseException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import org.w3c.dom.Document;

/**
 * Parent class for all adaptors
 * @author borja
 */
public abstract class Adapter {

    private Port port;

    /**
     * Stablishes the port the adapter is connected to.
     * @param port 
     */
    public void setPort(Port port) {
        this.port = port;
    }

    /**
     * Sends a message to the app.
     * @param m Message to send
     * @return A document with the response of the app. Value will be null if application does not need to respond
     * @throws SIGException 
     */
    public Document sendApp(Message m) throws SIGException {
        throw new ExecutionException("This port does not support sending direct messages to the app", null, null);
    }

    /**
     * Sends a document to the port.
     * @param doc The body of the message
     */
    protected void sendPort(Document doc) {
        if (port != null && port instanceof PortInput) {
            ((PortInput) port).sendProcess(doc);
        }
    }

    /**
     * Sends a document to the port.
     * @param xml The body of the msasage as a string
     * @throws ParseException 
     */
    protected void sendPort(String xml) throws ParseException {
        sendPort(Message.parseXML(xml));
    }
    
    /**
     * Sends a message already existing to the port.
     * @param m
     */
    protected void sendPort(Message m) {
        sendPort(m);
    }

    /**
     * Stops the adapter if it has running threads
     */
    public void halt() {
    }

    /**
     * Sets up and starts the adapter 
     */
    public void iniciate() {
    }

    /**
     * Returns the type of port that the adapter needs.
     * @return 
     */
    public abstract PORTS getCompatiblePortType();

    /**
     * Sends an exception to the Exception Handle
     * @param exception 
     */
    protected void handleException(SIGException exception) {
        if (port != null) {
            port.handleException(exception);
        }
    }
}
