package com.b0ve.solucionintegraciongenerica.utils.condiciones;

import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.XPathEvaluationException;

public interface Checkeable {

    boolean checkCondition(Message mensaje) throws XPathEvaluationException;
}
