package com.b0ve.solucionintegraciongenerica.utils.exceptions;

import com.b0ve.solucionintegraciongenerica.flow.Message;

public class ConfigurationException extends SIGException{

    public ConfigurationException(String string, Object associatedObject, Exception nestedException) {
        super("Configuration Exception: "+string, associatedObject, nestedException);
    }
    
}
