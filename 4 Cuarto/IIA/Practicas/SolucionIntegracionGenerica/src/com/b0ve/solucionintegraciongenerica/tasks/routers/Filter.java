package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.Checkeable;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.XPathEvaluationException;

/**
 * Applies a Checkeable condition to messages.
 * @author borja
 */
public class Filter extends FilterTemplate {

    private final Checkeable condicion;

    public Filter(Checkeable condicion) {
        super();
        this.condicion = condicion;
    }

    @Override
    protected boolean check(Message mensaje) throws XPathEvaluationException {
        return condicion.checkCondition(mensaje);
    }

}
