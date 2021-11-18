package com.b0ve.solucionintegraciongenerica.tasks.modifiers;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ParseException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import org.w3c.dom.Document;

/**
 * Merges a document with the body of a message.
 * @author borja
 */
public class Enricher extends EnricherTemplate {

    private Document staticContent;

    public Enricher(Object staticContent) {
        super();
        if (staticContent instanceof Document) {
            this.staticContent = (Document) staticContent;
        } else {
            try {
                this.staticContent = Message.parseXML((String) staticContent);
            } catch (ParseException ex) {
                handleException(new SIGException(ex.getMessage(), staticContent, ex));
            }
        }
    }

    @Override
    protected void enrich(Message m) throws SIGException {
        m.setBody(Message.mergeXML(m.getBody(), staticContent));
    }

}
