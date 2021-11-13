package com.b0ve.solucionintegraciongenerica.tasks;

import com.b0ve.solucionintegraciongenerica.utils.Process;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ConfigurationException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

abstract public class Task implements Runnable, Notifiable {

    protected Process process;
    private final List<Buffer> inputs, outputs;
    private final Semaphore s;
    protected final int maxInputs, maxOutputs;

    public Task() {
        this(0, 0);
    }

    public Task(int maxEntradas, int maxSalidas) {
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.s = new Semaphore(0);
        this.maxInputs = maxEntradas;
        this.maxOutputs = maxSalidas;
    }

    public void addInput(Buffer input) {
        this.inputs.add(input);
    }

    public void addOutput(Buffer output) {
        this.outputs.add(output);
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
                    try {
                        process();
                    } catch (SIGException ex) {
                        handleException(ex);
                    }
                }
            }
        } catch (InterruptedException ex) {
            //System.out.println("Tarea detenida");
        }
    }

    public abstract void process() throws SIGException;

    public void validate() throws ConfigurationException {
        if(maxInputs == -1 && !inputs.isEmpty()){
            throw new ConfigurationException("Input multiplicity error", "No inputs allowed, Actual: "+inputs.size(), null);
        }
        if (maxInputs > 0 && inputs.size() > maxInputs) {
            throw new ConfigurationException("Input multiplicity error", "Max: "+maxInputs+", Actual: "+inputs.size(), null);
        }
        if(maxOutputs == -1 && !outputs.isEmpty()){
            throw new ConfigurationException("Output multiplicity error", "No outputs allowed, Actual: "+outputs.size(), null);
        }
        if (maxOutputs > 0 && outputs.size() > maxOutputs) {
            throw new ConfigurationException("Output multiplicity error", "Max: "+maxOutputs+", Actual: "+outputs.size(), null);
        }
    }
    
    protected final Buffer input(int n) throws ConfigurationException{
        if(inputs.size() <= n) throw new ConfigurationException("Input number "+n+" does not exist in this task", n, null);
        return inputs.get(n);
    }
    protected final boolean hasInputs(){
        return !inputs.isEmpty();
    }
    protected final int nInputs(){
        return inputs.size();
    }
    protected final Buffer output(int n) throws ConfigurationException{
        if(outputs.size() <= n) throw new ConfigurationException("Output number "+n+" does not exist in this task", n, null);
        return outputs.get(n);
    }
    protected final boolean hasOutputs(){
        return !outputs.isEmpty();
    }
    protected final int nOutputs(){
        return outputs.size();
    }

    public final void setProcess(Process p) {
        process = p;
    }

    protected void lockPushes() {
        for (Buffer entrada : inputs) {
            entrada.lockPushes();
        }
    }
    protected void unlockPushes() {
        for (Buffer entrada : inputs) {
            entrada.unlockPushes();
        }
    }

    protected void debugLog(String log) {
        if (process != null) {
            process.debugLog(log);
        }
    }
    public void handleException(SIGException exception) {
        if (process != null) {
            process.handleException(exception);
        }
    }
    
    public void connect(Task tarea) throws ConfigurationException{
        if(process == null) throw new ConfigurationException("Esta tarea no pertenece a ningun proceso, no se puede encadenar", null, null);
        process.connect(this, tarea);
    }

}
