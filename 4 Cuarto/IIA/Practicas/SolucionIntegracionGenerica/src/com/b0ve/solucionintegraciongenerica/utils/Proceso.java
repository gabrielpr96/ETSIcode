package com.b0ve.solucionintegraciongenerica.utils;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Replicator;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import java.util.ArrayList;
import java.util.List;

public final class Proceso {
    private final List<Tarea> tareas;
    private final List<Thread> hilos;
    
    public enum TipoTarea {
        REPLICATOR
    }

    public Proceso() {
        this.tareas = new ArrayList<>();
        this.hilos = new ArrayList<>();
    }
    
    public void ejecutar(){
        for (int i = 0; i < tareas.size(); i++) {
            Thread hilo = new Thread(tareas.get(i));
            hilos.add(hilo);
            hilo.start();
        }
    }
    
    public void esperar() throws InterruptedException{
        for (Thread hilo : hilos) {
            hilo.join();
        }
    }
    
    public void terminar(){
        for (Thread hilo : hilos) {
            hilo.interrupt();
        }
    }
    
    public Tarea crearTarea(TipoTarea tipo){
        return crearTarea(tipo, null);
    }
    public Tarea crearTarea(TipoTarea tipo, Object configuracion){
        Tarea tarea;
        switch(tipo){
            case REPLICATOR:
                tarea = new Replicator();
                break;
            default:
                tarea = null;
        }
        if(tarea != null) tareas.add(tarea);
        return tarea;
    }
    
    public Puerto crearPuerto(Adaptador adaptador){
        Puerto puerto = new Puerto(adaptador);
        tareas.add(puerto);
        return puerto;
    }
    
    public void encadenar(Tarea t1, Tarea t2) throws ConfigurationException{
        Buffer b = new Buffer(t2);
        t1.addSalida(b);
        t2.addEntrada(b);
    }
}
