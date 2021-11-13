package com.b0ve.solucionintegraciongenerica.utils.exceptions;

/**
 * Use SIGException instead.
 * @author borja
 * @deprecated
 */
@Deprecated
public class XSLTransformationException extends SIGException {

    public XSLTransformationException(String string, Object associatedObject, Exception nestedException) {
        super("SXLTransformation Exception: " + string, associatedObject, nestedException);
    }
}
