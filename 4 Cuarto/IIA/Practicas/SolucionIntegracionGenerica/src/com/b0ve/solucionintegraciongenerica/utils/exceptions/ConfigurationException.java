package com.b0ve.solucionintegraciongenerica.utils.exceptions;

public class ConfigurationException extends SIGException {

    public ConfigurationException(String string, Object associatedObject, Exception nestedException) {
        super("Configuration Exception: " + string, associatedObject, nestedException);
    }

}
