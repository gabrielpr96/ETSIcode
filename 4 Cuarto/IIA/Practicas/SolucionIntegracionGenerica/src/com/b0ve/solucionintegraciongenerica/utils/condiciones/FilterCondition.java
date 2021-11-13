package com.b0ve.solucionintegraciongenerica.utils.condiciones;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.XPathEvaluationException;

/**
 * Simple base class to use in Filter and Distributor Task.
 * @author borja
 */
public abstract class FilterCondition implements Checkeable {

    private final String xpath;

    public FilterCondition(String xpath) {
        this.xpath = xpath;
    }

    @Override
    public final boolean checkCondition(Message mensaje) throws XPathEvaluationException {
        return testValue(mensaje.evaluateXPathString(xpath));
    }

    protected abstract boolean testValue(String text);
}
