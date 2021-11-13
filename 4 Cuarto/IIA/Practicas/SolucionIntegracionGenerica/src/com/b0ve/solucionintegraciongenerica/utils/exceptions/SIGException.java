package com.b0ve.solucionintegraciongenerica.utils.exceptions;

public class SIGException extends Exception {

    private final Object associatedObject;
    private final Exception nestedException;

    public SIGException(String text, Object associatedObject, Exception nestedException) {
        super(text);
        this.associatedObject = associatedObject;
        this.nestedException = nestedException;
    }

    public Object getAssociatedObject() {
        return associatedObject;
    }

    public Exception getNestedException() {
        return nestedException;
    }

}
