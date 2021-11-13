package com.b0ve.solucionintegraciongenerica.tasks;

import com.b0ve.solucionintegraciongenerica.utils.Process;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ConfigurationException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

abstract public class Task implements Runnable, Notifiable {

    protected Process proceso;
    protected final List<Buffer> entradas, salidas;
    private final Semaphore s;
    protected final int maxEntradas, maxSalidas;

    public Task() {
        this(0, 0);
    }

    public Task(int maxEntradas, int maxSalidas) {
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

    public void setProcess(Process p) {
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
    
    public void encadenar(Task tarea) throws ConfigurationException{
        if(proceso == null) throw new ConfigurationException("Esta tarea no pertenece a ningun proceso, no se puede encadenar");
        proceso.connect(this, tarea);
    }

}
