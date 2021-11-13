package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.utils.exceptions.ConfigurationException;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

public final class Correlator extends CorrelatorTemplate {

    private final String expresion;

    public Correlator(String expresion) {
        super();
        this.expresion = expresion;
    }

    public Correlator() {
        this(null);
    }

    @Override
    protected boolean route(Message m1, Message m2) throws SIGException {
        if (expresion == null) {
            return super.route(m1, m2);
        } else {
            return m1.evaluateXPath(expresion).item(0).getTextContent().equals(m2.evaluateXPath(expresion).item(0).getTextContent());
        }
    }

    @Override
    public void validate() throws ConfigurationException {
        super.validate();
        if(nInputs() != nOutputs()) throw new ConfigurationException("Correlator requires the same number of inputs and outputs", null, null);
    }
    
    

}
