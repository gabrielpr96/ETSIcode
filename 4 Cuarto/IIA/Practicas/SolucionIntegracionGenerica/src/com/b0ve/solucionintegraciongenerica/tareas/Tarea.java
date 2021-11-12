package com.b0ve.solucionintegraciongenerica.tareas;

import com.b0ve.solucionintegraciongenerica.utils.Proceso;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.excepciones.ConfigurationException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

abstract public class Tarea implements Runnable, Avisable {

    protected Proceso proceso;
    protected final List<Buffer> entradas, salidas;
    private final Semaphore s;
    protected final int maxEntradas, maxSalidas;

    public Tarea() {
        this(0, 0);
    }

    public Tarea(int maxEntradas, int maxSalidas) {
        this.entradas = new ArrayList<>();
        this.salidas = new ArrayList<>();
        this.s = new Semaphore(0);
        this.maxEntradas = maxEntradas;
        this.maxSalidas = maxSalidas;
    }

    public void addEntrada(Buffer entrada) {
        this.entradas.add(entrada);
    }

    public void addSalida(Buffer salida) {
        this.salidas.add(salida);
    }

    @Override
    public final void signalInput() {
        s.release();
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                while (!Thread.currentThread().isInterrupted()) {
                    s.acquire();
                    procesar();
                }
            }
        } catch (InterruptedException ex) {
            //System.out.println("Tarea detenida");
        }
    }

    public abstract void procesar();

    public void validar() throws ConfigurationException {
        if (maxEntradas != 0 && this.entradas.size() > maxEntradas) {
            throw new ConfigurationException("Error de multiplicidad entradas");
        }
        if (maxSalidas != 0 && this.salidas.size() > maxSalidas) {
            throw new ConfigurationException("Error de multiplicidad salidas");
        }
    }

    public void setProceso(Proceso p) {
        proceso = p;
    }

    protected void lockPushes() {
        for (Buffer entrada : entradas) {
            entrada.lockPushes();
        }
    }
    protected void unlockPushes() {
        for (Buffer entrada : entradas) {
            entrada.unlockPushes();
        }
    }

    protected void debugLog(String log) {
        if (proceso != null) {
            proceso.debugLog(log);
        }
    }
    
    public void encadenar(Tarea tarea) throws ConfigurationException{
        if(proceso == null) throw new ConfigurationException("Esta tarea no pertenece a ningun proceso, no se puede encadenar");
        proceso.encadenar(this, tarea);
    }

}
