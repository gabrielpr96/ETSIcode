package com.b0ve.solucionintegraciongenerica.utils.exceptions;

/**
 * Use SIGException instead.
 * @author borja
 * @deprecated
 */
@Deprecated
public class XPathEvaluationException extends SIGException {

    public XPathEvaluationException(String string, Object associatedObject, Exception nestedException) {
        super("XPath Evaluation Exception: " + string, associatedObject, nestedException);
    }
}
