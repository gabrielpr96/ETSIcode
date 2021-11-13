package com.b0ve.solucionintegraciongenerica.utils.exceptions;

public class XPathEvaluationException extends SIGException {

    public XPathEvaluationException(String string, Object associatedObject, Exception nestedException) {
        super("XPath Evaluation Exception: " + string, associatedObject, nestedException);
    }
}
