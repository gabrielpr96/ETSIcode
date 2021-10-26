package com.b0ve.solucionintegraciongenerica.utils;

public class Mensaje {
    private final int ID, correlationID;
    private final String body;
    private static int counter = 0;
    
    
    public Mensaje(int ID, int correlationID, String body) {
        this.ID = ID;
        this.correlationID = correlationID;
        this.body = body;
    }

    public Mensaje(String body) {
        this(counter, counter, body);
    }
    
    public Mensaje(Mensaje m) {
        this(m.ID, m.correlationID, m.body);
    }
    
    

    public int getID() {
        return ID;
    }

    public int getCorrelationID() {
        return correlationID;
    }

    public String getBody() {
        return body;
    }
    
    
}
