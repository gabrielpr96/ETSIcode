package com.b0ve.solucionintegraciongenerica.utils.exceptions;

import com.b0ve.solucionintegraciongenerica.flow.Message;

public class DefaultExceptionHandler implements ExceptionHandleable{

    private final static DefaultExceptionHandler defaultExceptionHandler = new DefaultExceptionHandler();
    
    @Override
    public void handleException(String message, Message associatedMessage, Exception associatedException) {
        System.err.println("SIG Exception: "+message
                +(associatedException==null?"":"\nAssociated Message: "+associatedException.toString())
                +(associatedException==null?"":"\nNested Exception: "));
        if(associatedException != null) associatedException.printStackTrace();
    }

    public static ExceptionHandleable getHandler() {
        return defaultExceptionHandler;
    }
}
