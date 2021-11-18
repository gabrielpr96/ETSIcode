package com.b0ve.solucionintegraciongenerica.utils.condiciones;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.XPathEvaluationException;

/**
 * Requirement for a class to be used with Filter and Distributor tasks.
 * @author borja
 */
public interface Checkeable {

    boolean checkCondition(Message mensaje) throws XPathEvaluationException;
}
