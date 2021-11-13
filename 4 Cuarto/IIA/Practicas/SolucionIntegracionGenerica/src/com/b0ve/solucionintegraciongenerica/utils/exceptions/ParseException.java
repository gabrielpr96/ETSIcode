package com.b0ve.solucionintegraciongenerica.utils.exceptions;

public class ParseException extends SIGException {

    public ParseException(String string, Object associatedObject, Exception nestedException) {
        super("XML Parser Exception: " + string, associatedObject, nestedException);
    }
}
