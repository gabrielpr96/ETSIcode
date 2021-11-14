package com.b0ve.solucionintegraciongenerica.utils;

import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.ports.Port;
import com.b0ve.solucionintegraciongenerica.ports.PortInput;
import com.b0ve.solucionintegraciongenerica.ports.PortOutput;
import com.b0ve.solucionintegraciongenerica.ports.PortRequest;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.tasks.TaskDebug;
import com.b0ve.solucionintegraciongenerica.tasks.modifiers.ContextEnricher;
import com.b0ve.solucionintegraciongenerica.tasks.modifiers.ContextSlimmer;
import com.b0ve.solucionintegraciongenerica.tasks.modifiers.CorrelationIDSetter;
import com.b0ve.solucionintegraciongenerica.tasks.modifiers.Enricher;
import com.b0ve.solucionintegraciongenerica.tasks.modifiers.Slimmer;
import com.b0ve.solucionintegraciongenerica.tasks.routers.Correlator;
import com.b0ve.solucionintegraciongenerica.tasks.routers.Distributor;
import com.b0ve.solucionintegraciongenerica.tasks.routers.Filter;
import com.b0ve.solucionintegraciongenerica.tasks.routers.Merger;
import com.b0ve.solucionintegraciongenerica.tasks.routers.Replicator;
import com.b0ve.solucionintegraciongenerica.tasks.transformers.Aggregator;
import com.b0ve.solucionintegraciongenerica.tasks.transformers.Assembler;
import com.b0ve.solucionintegraciongenerica.tasks.transformers.Chopper;
import com.b0ve.solucionintegraciongenerica.tasks.transformers.Splitter;
import com.b0ve.solucionintegraciongenerica.tasks.transformers.Translator;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.Checkeable;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ConfigurationException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.handlers.DefaultExceptionHandler;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.handlers.ExceptionHandleable;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.handlers.LogSink;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller class to manage an Integration Process
 *
 * @author borja
 */
public abstract class Process {

    private final boolean debug;
    protected final List<Task> tasks;
    private final List<Buffer> buffers;
    private ExceptionHandleable exceptionHandler;
    private LogSink logSink;

    public enum TASKS {
        CORRELATOR,
        MERGER,
        FILTER,
        DISTRIBUTOR,
        REPLICATOR,
        SLIMMER,
        CONTEXT_SLIMMER,
        ENRICHER,
        CONTEXT_ENRICHER,
        CORRELATION_ID_SETTER,
        TRANSLATOR,
        SPLITTER,
        AGGREGATOR,
        CHOPPER,
        ASSEMBLER,
        DEBUG
    }

    public enum PORTS {
        INPUT,
        OUTPUT,
        REQUEST
    }

    public Process() {
        this(false);
    }

    public Process(boolean debug) {
        this.debug = debug;
        tasks = new ArrayList<>();
        buffers = new ArrayList<>();
        exceptionHandler = DefaultExceptionHandler.getHandler();
    }

    /**
     * Starts the process. Tasks, ports and adapters
     */
    public abstract void execute();

    /**
     * Waits for the process to finish
     *
     * @throws InterruptedException
     */
    public abstract void waitToEnd() throws InterruptedException;

    /**
     * Stops the process. Tasks, ports and adapters
     */
    public abstract void shutdown();

    /**
     * Creates a task without configuration
     *
     * @param tipo
     * @return Task added
     */
    public Task createTask(TASKS tipo) {
        return createTask(tipo, null);
    }

    /**
     * Creates a task specified by type with certain configuration
     *
     * @param type
     * @param configuration
     * @return Task added
     */
    public Task createTask(TASKS type, Object configuration) {
        Task task;
        switch (type) {
            case CORRELATOR:
                task = new Correlator((String) configuration);
                break;
            case MERGER:
                task = new Merger();
                break;
            case FILTER:
                task = new Filter((Checkeable) configuration);
                break;
            case DISTRIBUTOR:
                task = new Distributor((Checkeable[]) configuration);
                break;
            case REPLICATOR:
                task = new Replicator();
                break;
            case SLIMMER:
                task = new Slimmer((String[]) configuration);
                break;
            case CONTEXT_SLIMMER:
                task = new ContextSlimmer();
                break;
            case ENRICHER:
                task = new Enricher(configuration);
                break;
            case CONTEXT_ENRICHER:
                task = new ContextEnricher();
                break;
            case CORRELATION_ID_SETTER:
                task = new CorrelationIDSetter();
                break;
            case TRANSLATOR:
                task = new Translator((String) configuration);
                break;
            case SPLITTER:
                task = new Splitter((String) configuration);
                break;
            case AGGREGATOR:
                task = new Aggregator(configuration);
                break;
            case CHOPPER:
                task = new Chopper((String) configuration);
                break;
            case ASSEMBLER:
                task = new Assembler(configuration);
                break;
            case DEBUG:
                task = new TaskDebug((boolean) configuration);
                break;
            default:
                task = null;
        }
        if (task != null) {
            addTask(task);
        }
        return task;
    }

    /**
     * Adds an existing task to the process
     *
     * @param task
     * @return Task added
     */
    public Task addTask(Task task) {
        tasks.add(task);
        task.setProcess(this);
        return task;
    }

    /**
     * Creates and adds a port for an adapter.
     *
     * @param adapter
     * @return
     * @throws ConfigurationException
     */
    public Port createPort(Adapter adapter) throws ConfigurationException {
        Port puerto;
        switch (adapter.getCompatiblePortType()) {
            case INPUT:
                puerto = new PortInput(adapter);
                break;
            case OUTPUT:
                puerto = new PortOutput(adapter);
                break;
            case REQUEST:
                puerto = new PortRequest(adapter);
                break;
            default:
                throw new ConfigurationException("Adapter did not provide a valid compatible port type.", "Option given by adapter: " + adapter.getCompatiblePortType(), null);
        }
        addTask(puerto);
        return puerto;
    }

    /**
     * Adds a buffer from one task to another
     *
     * @param t1 Source
     * @param t2 Destination
     */
    public void connect(Task t1, Task t2) {
        Buffer b = new Buffer(t1, t2);
        buffers.add(b);
        t1.addOutput(b);
        t2.addInput(b);
    }

    /**
     * Validates all tasks
     *
     * @throws ConfigurationException
     */
    public void validate() throws ConfigurationException {
        for (Task tarea : tasks) {
            tarea.validate();
        }
    }
    
    /**
     * Stablishes a LogSink. Debug log will be sent to this sink.
     * @param sink 
     */
    public void setLogSink(LogSink sink){
        this.logSink = sink;
    }

    /**
     * Shows a debug log if debugging is enabled
     *
     * @param log
     */
    public void debugLog(String log) {
        if (debug) {
            if (logSink == null) {
                System.out.println("DEBUG: " + log);
            } else {
                logSink.debugLog(log);
            }
        }
    }

    /**
     * Stablishes the exception handler for the process
     *
     * @param handler
     */
    public void setHandler(ExceptionHandleable handler) {
        this.exceptionHandler = handler;
    }

    /**
     * Handles an exception
     *
     * @param exception
     */
    public void handleException(SIGException exception) {
        exceptionHandler.handleException(exception);
    }
    
    /**
     * Counts the number of messages that are stored in buffers. A way to find stuck messages or measure load.
     * @return Number of messages in buffers
     */
    public int messageCount(){
        int nMensajes = 0;
        for (Buffer buffer : buffers) {
            nMensajes += buffer.size();
        }
        return nMensajes;
    }
}
