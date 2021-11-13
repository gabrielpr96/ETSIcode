package com.b0ve.solucionintegraciongenerica.utils.exceptions;

import com.b0ve.solucionintegraciongenerica.flow.Message;

public class XSLTransformationException extends SIGException {

    public XSLTransformationException(String string, Object associatedObject, Exception nestedException) {
        super("SXLTransformation Exception: "+string, associatedObject, nestedException);
    }
}
