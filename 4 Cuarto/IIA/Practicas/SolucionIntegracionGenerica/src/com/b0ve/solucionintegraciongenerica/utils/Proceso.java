package com.b0ve.solucionintegraciongenerica.utils;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.excepciones.ConfigurationException;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.Comprobable;
import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.tareas.routers.*;
import com.b0ve.solucionintegraciongenerica.tareas.modifiers.*;
import com.b0ve.solucionintegraciongenerica.tareas.transformers.*;
import java.util.ArrayList;
import java.util.List;

public final class Proceso {

    private final List<Tarea> tareas;
    private final List<Thread> hilos;

    public enum TipoTarea {
        CORRELATOR,
        MERGER,
        FILTER,
        DISTRIBUTOR,
        REPLICATOR,
        SLIMMER,
        CONTEXT_SLIMMER,
        CONTEXT_ENRICHER,
        CORRELATION_ID_SETTER,
        TRANSLATOR,
        SPLITTER,
        AGGREGATOR,
        CHOPPER,
        ASSEMBLER
    }

    public Proceso() {
        this.tareas = new ArrayList<>();
        this.hilos = new ArrayList<>();
    }

    public void ejecutar() {
        for (int i = 0; i < tareas.size(); i++) {
            Thread hilo = new Thread(tareas.get(i));
            hilos.add(hilo);
            hilo.start();
        }
    }

    public void esperar() throws InterruptedException {
        for (Thread hilo : hilos) {
            hilo.join();
        }
    }

    public void terminar() {
        for (Thread hilo : hilos) {
            hilo.interrupt();
        }
    }

    public Tarea crearTarea(TipoTarea tipo) {
        return crearTarea(tipo, null);
    }

    public Tarea crearTarea(TipoTarea tipo, Object configuracion) {
        Tarea tarea;
        switch (tipo) {
            case CORRELATOR:
                tarea = new Correlator((String) configuracion);
                break;
            case MERGER:
                tarea = new Merger();
                break;
            case FILTER:
                tarea = new Filter((Comprobable) configuracion);
                break;
            case DISTRIBUTOR:
                tarea = new Distributor((Comprobable[]) configuracion);
                break;
            case REPLICATOR:
                tarea = new Replicator();
                break;
            case CONTEXT_SLIMMER:
                tarea = new ContextSlimmer();
                break;
            case CONTEXT_ENRICHER:
                tarea = new ContextEnricher();
                break;
            case CORRELATION_ID_SETTER:
                tarea = new CorrelationIDSetter();
                break;
            case TRANSLATOR:
                tarea = new Translator((String) configuracion);
                break;
            case SPLITTER:
                tarea = new Splitter((String) configuracion);
                break;
            case AGGREGATOR:
                tarea = new Aggregator((String) configuracion);
                break;
            case CHOPPER:
                tarea = new Chopper((String) configuracion);
                break;
            case ASSEMBLER:
                tarea = new Assembler((String) configuracion);
                break;
            default:
                tarea = null;
        }
        if (tarea != null) {
            tareas.add(tarea);
        }
        return tarea;
    }

    public void addTarea(Tarea tarea) {
        tareas.add(tarea);
    }

    public Puerto crearPuerto(Adaptador adaptador) {
        Puerto puerto = new Puerto(adaptador);
        tareas.add(puerto);
        return puerto;
    }

    public void encadenar(Tarea t1, Tarea t2) {
        Buffer b = new Buffer(t2);
        t1.addSalida(b);
        t2.addEntrada(b);
    }

    public void validar() throws ConfigurationException{
        for (Tarea tarea : tareas) {
            tarea.validar();
        }
    }
}
