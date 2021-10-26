package com.b0ve.solucionintegraciongenerica.tareas;

import com.b0ve.solucionintegraciongenerica.utils.Avisable;
import com.b0ve.solucionintegraciongenerica.utils.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.ConfigurationException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract public class Tarea implements Runnable, Avisable{

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

    public void addEntrada(Buffer entrada) throws ConfigurationException {
        this.entradas.add(entrada);
        if(maxEntradas != 0 && this.entradas.size() > maxEntradas) throw new ConfigurationException("Error de multiplicidad entradas");
    }

    public void addSalida(Buffer salida) throws ConfigurationException {
        this.salidas.add(salida);
        if(maxSalidas != 0 && this.salidas.size() > maxSalidas) throw new ConfigurationException("Error de multiplicidad salidas");
    }
    
    @Override
    public final void signalInput(){
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
            System.out.println("Tarea detenida");
        }
    }
    
    protected abstract void procesar();

}
