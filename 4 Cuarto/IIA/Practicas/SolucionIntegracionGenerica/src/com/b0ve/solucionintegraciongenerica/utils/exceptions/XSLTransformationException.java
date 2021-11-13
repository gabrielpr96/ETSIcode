package com.b0ve.solucionintegraciongenerica.utils.exceptions;

public class XSLTransformationException extends SIGException {

    public XSLTransformationException(String string, Object associatedObject, Exception nestedException) {
        super("SXLTransformation Exception: " + string, associatedObject, nestedException);
    }
}
