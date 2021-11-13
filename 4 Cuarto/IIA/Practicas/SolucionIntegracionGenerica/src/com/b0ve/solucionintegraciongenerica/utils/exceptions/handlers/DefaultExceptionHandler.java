package com.b0ve.solucionintegraciongenerica.utils.exceptions.handlers;

import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

public class DefaultExceptionHandler implements ExceptionHandleable {

    private final static DefaultExceptionHandler defaultExceptionHandler = new DefaultExceptionHandler();

    @Override
    public void handleException(SIGException exception) {
        System.err.println("SIG Exception: " + exception.getMessage()
                + (exception.getAssociatedObject() == null ? "" : "\nAssociated Object: " + exception.getAssociatedObject().toString())
                + (exception.getNestedException() == null ? "" : "\nNested Exception: "));
        if (exception.getNestedException() != null) {
            exception.getNestedException().printStackTrace();
        }
    }

    public static ExceptionHandleable getHandler() {
        return defaultExceptionHandler;
    }
}
