package com.b0ve.solucionintegraciongenerica.utils.exceptions;

import com.b0ve.solucionintegraciongenerica.flow.Message;

public interface ExceptionHandleable {
    public void handleException(String message, Message associatedMessage, Exception associatedException);
}
