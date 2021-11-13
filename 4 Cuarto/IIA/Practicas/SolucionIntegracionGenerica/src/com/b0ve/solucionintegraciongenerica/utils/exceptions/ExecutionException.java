package com.b0ve.solucionintegraciongenerica.utils.exceptions;

import com.b0ve.solucionintegraciongenerica.flow.Message;

public class ExecutionException extends SIGException {

    public ExecutionException(String string, Object associatedObject, Exception nestedException) {
        super("Execution Exception: "+string, associatedObject, nestedException);
    }
}
