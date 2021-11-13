package com.b0ve.solucionintegraciongenerica.tasks;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ConfigurationException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

/**
 * Base tasks to implement all other taks.
 * @author borja
 */
abstract public class Task implements Runnable, Notifiable {

    protected Process process;
    private final List<Buffer> inputs, outputs;
    private final Semaphore s;
    protected final int maxInputs, maxOutputs;

    public Task() {
        this(0, 0);
    }

    /**
     * If a param equals -1, no buffers are ollowed to connect.
     * If it equals 0m there is no limitation to the number of buffers connected, can be even 0.
     * Any other number is treated as a exact match.
     * For more precise validation of connections, please refer to the validate method.
     * @param maxEntradas 
     * @param maxSalidas 
     */
    public Task(int maxEntradas, int maxSalidas) {
        this.inputs = new ArrayList<>();
        this.outputs = new ArrayList<>();
        this.s = new Semaphore(0);
        this.maxInputs = maxEntradas;
        this.maxOutputs = maxSalidas;
    }

    /**
     * Ads a new input buffer to the task
     * @param input 
     */
    public void addInput(Buffer input) {
        this.inputs.add(input);
    }

    /**
     * Ads a new output buffer to the task
     * @param output 
     */
    public void addOutput(Buffer output) {
        this.outputs.add(output);
    }

    /**
     * Notify the task that there are new messages in its input.
     */
    @Override
    public final void signalInput() {
        s.release();
    }

    /**
     * Execute the process of the app in a new thread that waits for new messages on the inputs.
     */
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

    /**
     * Method that does the work of the task.
     * Reads inputs, writes to outputs.
     * @throws SIGException 
     */
    public abstract void process() throws SIGException;

    /**
     * Validates the configuration of the task
     * @throws ConfigurationException 
     */
    public void validate() throws ConfigurationException {
        if (maxInputs == -1 && !inputs.isEmpty()) {
            throw new ConfigurationException("Input multiplicity error", "No inputs allowed, Actual: " + inputs.size(), null);
        }
        if (maxInputs > 0 && inputs.size() > maxInputs) {
            throw new ConfigurationException("Input multiplicity error", "Max: " + maxInputs + ", Actual: " + inputs.size(), null);
        }
        if (maxOutputs == -1 && !outputs.isEmpty()) {
            throw new ConfigurationException("Output multiplicity error", "No outputs allowed, Actual: " + outputs.size(), null);
        }
        if (maxOutputs > 0 && outputs.size() > maxOutputs) {
            throw new ConfigurationException("Output multiplicity error", "Max: " + maxOutputs + ", Actual: " + outputs.size(), null);
        }
    }

    /**
     * Returns a input buffer by number. Throws exception if it does not exist.
     * @param n
     * @return
     * @throws ConfigurationException
     */
    protected final Buffer input(int n) throws ConfigurationException {
        if (inputs.size() <= n) {
            throw new ConfigurationException("Input number " + n + " does not exist in this task", n, null);
        }
        return inputs.get(n);
    }

    /**
     * Returns true if the task has at least one input
     * @return 
     */
    protected final boolean hasInputs() {
        return !inputs.isEmpty();
    }

    /**
     * Returns the number of inputs of the task
     * @return 
     */
    protected final int nInputs() {
        return inputs.size();
    }

    /**
     * Returns a output buffer by number. Throws exception if it does not exist.
     * @param n
     * @return
     * @throws ConfigurationException 
     */
    protected final Buffer output(int n) throws ConfigurationException {
        if (outputs.size() <= n) {
            throw new ConfigurationException("Output number " + n + " does not exist in this task", n, null);
        }
        return outputs.get(n);
    }

    /**
     * Returns true if the task has at least one output
     * @return 
     */
    protected final boolean hasOutputs() {
        return !outputs.isEmpty();
    }

    /**
     * Returns the number of outputs of the task
     * @return 
     */
    protected final int nOutputs() {
        return outputs.size();
    }

    /**
     * Stalishes the process to whoom the tasks belongs
     * @param p 
     */
    public final void setProcess(Process p) {
        process = p;
    }

    /**
     * Locks all the inputs
     */
    protected void lockPushes() {
        for (Buffer entrada : inputs) {
            entrada.lockPushes();
        }
    }

    /**
     * Unlocks all the inputs
     */
    protected void unlockPushes() {
        for (Buffer entrada : inputs) {
            entrada.unlockPushes();
        }
    }

    /**
     * Sends a debugg message to the process
     * @param log 
     */
    protected void debugLog(String log) {
        if (process != null) {
            process.debugLog(log);
        }
    }

    /**
     * Sends an exception to the process exception handler
     * @param exception 
     */
    public void handleException(SIGException exception) {
        if (process != null) {
            process.handleException(exception);
        }
    }

    /**
     * Syntactic sugar for the connect method of process
     * @param tarea
     * @throws ConfigurationException 
     */
    public void connect(Task tarea) throws ConfigurationException {
        if (process == null) {
            throw new ConfigurationException("Esta tarea no pertenece a ningun proceso, no se puede encadenar", null, null);
        }
        process.connect(this, tarea);
    }

}
