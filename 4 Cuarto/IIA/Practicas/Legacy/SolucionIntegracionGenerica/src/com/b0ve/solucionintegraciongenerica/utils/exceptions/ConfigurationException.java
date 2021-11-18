package com.b0ve.solucionintegraciongenerica.utils.exceptions;

/**
 * Use SIGException instead.
 * @author borja
 * @deprecated
 */
@Deprecated
public class ConfigurationException extends SIGException {

    public ConfigurationException(String string, Object associatedObject, Exception nestedException) {
        super("Configuration Exception: " + string, associatedObject, nestedException);
    }

}
