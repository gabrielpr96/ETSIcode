package com.b0ve.solucionintegraciongenerica.utils;

import com.b0ve.solucionintegraciongenerica.tasks.transformers.Chopper;
import com.b0ve.solucionintegraciongenerica.tasks.transformers.Aggregator;
import com.b0ve.solucionintegraciongenerica.tasks.transformers.Assembler;
import com.b0ve.solucionintegraciongenerica.tasks.transformers.Translator;
import com.b0ve.solucionintegraciongenerica.tasks.transformers.Splitter;
import com.b0ve.solucionintegraciongenerica.tasks.routers.Merger;
import com.b0ve.solucionintegraciongenerica.tasks.routers.Filter;
import com.b0ve.solucionintegraciongenerica.tasks.routers.Distributor;
import com.b0ve.solucionintegraciongenerica.tasks.routers.Replicator;
import com.b0ve.solucionintegraciongenerica.tasks.routers.Correlator;
import com.b0ve.solucionintegraciongenerica.tasks.modifiers.CorrelationIDSetter;
import com.b0ve.solucionintegraciongenerica.tasks.modifiers.ContextSlimmer;
import com.b0ve.solucionintegraciongenerica.tasks.modifiers.ContextEnricher;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.ConfigurationException;
import com.b0ve.solucionintegraciongenerica.adapters.Adapter;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.ports.Port;
import com.b0ve.solucionintegraciongenerica.ports.PortInput;
import com.b0ve.solucionintegraciongenerica.ports.PortOutput;
import com.b0ve.solucionintegraciongenerica.ports.PortRequest;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.tasks.TaskDebug;
import java.util.ArrayList;
import java.util.List;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.Checkeable;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.handlers.DefaultExceptionHandler;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.handlers.ExceptionHandleable;

public abstract class Process {

    private final boolean debug;
    protected final List<Task> tasks;
    private ExceptionHandleable exceptionHandler;

    public enum TASKS {
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
        this.tasks = new ArrayList<>();
        exceptionHandler = DefaultExceptionHandler.getHandler();
    }

    public abstract void execute();

    public abstract void waitToEnd() throws InterruptedException;

    public abstract void shutdown();

    public Task createTask(TASKS tipo) {
        return createTask(tipo, null);
    }

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
            case CONTEXT_SLIMMER:
                task = new ContextSlimmer();
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
                task = new Assembler((String) configuration);
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

    public Task addTask(Task task) {
        tasks.add(task);
        task.setProcess(this);
        return task;
    }

    public Port createPort(Adapter adapter) throws ConfigurationException {
        Port puerto;
        switch(adapter.getCompatiblePortType()){
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
                throw new ConfigurationException("Adapter did not provide a valid compatible port type.", "Option given by adapter: "+adapter.getCompatiblePortType(), null);
        }
        addTask(puerto);
        return puerto;
    }

    public void connect(Task t1, Task t2) {
        Buffer b = new Buffer(t2);
        t1.addOutput(b);
        t2.addInput(b);
    }

    public void validate() throws ConfigurationException {
        for (Task tarea : tasks) {
            tarea.validate();
        }
    }

    public void debugLog(String log) {
        if (debug) {
            System.out.println("DEBUG: " + log);
        }
    }
    
    public void setHandler(ExceptionHandleable handler){
        this.exceptionHandler = handler;
    }
    
    public void handleException(SIGException exception){
        exceptionHandler.handleException(exception);
    }
}
