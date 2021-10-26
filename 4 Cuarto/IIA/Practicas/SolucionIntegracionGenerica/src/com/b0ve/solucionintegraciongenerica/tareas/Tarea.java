package com.b0ve.solucionintegraciongenerica.tareas;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.excepciones.ConfigurationException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

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

    public void addEntrada(Buffer entrada) {
        this.entradas.add(entrada);
    }

    public void addSalida(Buffer salida) {
        this.salidas.add(salida);
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
    
    public abstract void procesar();
    
    public void validar() throws ConfigurationException{
        if(maxEntradas != 0 && this.entradas.size() > maxEntradas) throw new ConfigurationException("Error de multiplicidad entradas");
        if(maxSalidas != 0 && this.salidas.size() > maxSalidas) throw new ConfigurationException("Error de multiplicidad salidas");
    }

}
