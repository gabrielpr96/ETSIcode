package com.b0ve.solucionintegraciongenerica.utils.flujo;

import com.b0ve.solucionintegraciongenerica.tareas.Avisable;
import java.util.Iterator;
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
        if(salida != null) salida.signalInput();
    }
    
    public Iterator<Mensaje> getIterator(){
        return mensajes.iterator();
    }
    
    public void deleteMessage(Mensaje mensaje){
        mensajes.remove(mensaje);
    }
}
