package com.b0ve.solucionintegraciongenerica.utils;

import java.util.LinkedList;
import java.util.Queue;

public final class Buffer {
    private final Queue<Mensaje> mensajes;
    private final Avisable salida;

    public Buffer(Avisable salida) {
        this.mensajes = new LinkedList<>();
        this.salida = salida;
    }
    
    public boolean empty(){
        return this.mensajes.isEmpty();
    }
    
    public Mensaje retrive(){
        return mensajes.poll();
    }
    
    public void push(Mensaje mensaje){
        mensajes.add(mensaje);
        salida.signalInput();
    }
}
