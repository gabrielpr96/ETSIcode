package com.b0ve.autosig;

import com.b0ve.sig.connectors.Connector;
import com.b0ve.sig.connectors.test.Console;
import com.b0ve.sig.connectors.basic.DirOutputter;
import com.b0ve.sig.connectors.basic.DirWhatcher;
import com.b0ve.sig.connectors.basic.MySQL;
import com.b0ve.sig.connectors.basic.MySQLmultyQuery;
import com.b0ve.sig.connectors.basic.REST;
import com.b0ve.sig.connectors.meta.SET;
import com.b0ve.sig.connectors.test.Screen;
import com.b0ve.sig.connectors.test.StubInput;
import com.b0ve.sig.connectors.test.StubOutput;
import com.b0ve.sig.connectors.basic.WebAPI;
import com.b0ve.sig.connectors.extra.SFTPUploader;
import com.b0ve.sig.connectors.extra.SendMail;
import com.b0ve.sig.connectors.extra.TumblrWatcher;
import com.b0ve.sig.connectors.extra.TwitterPutter;
import com.b0ve.sig.connectors.extra.TwitterWatcher;
import com.b0ve.sig.connectors.extra.WordpressWatcher;
import com.b0ve.sig.connectors.meta.Clock;
import com.b0ve.sig.ports.Port;
import com.b0ve.sig.tasks.Task;
import com.b0ve.sig.tasks.TaskBlackhole;
import com.b0ve.sig.tasks.TaskDebug;
import com.b0ve.sig.tasks.TaskFlowInterrupter;
import com.b0ve.sig.tasks.modifiers.ContextEnricher;
import com.b0ve.sig.tasks.modifiers.ContextSlimmer;
import com.b0ve.sig.tasks.modifiers.CorrelationIDSetter;
import com.b0ve.sig.tasks.modifiers.Enricher;
import com.b0ve.sig.tasks.modifiers.Slimmer;
import com.b0ve.sig.tasks.routers.Correlator;
import com.b0ve.sig.tasks.routers.Distributor;
import com.b0ve.sig.tasks.routers.Filter;
import com.b0ve.sig.tasks.routers.Merger;
import com.b0ve.sig.tasks.routers.Replicator;
import com.b0ve.sig.tasks.transformers.Aggregator;
import com.b0ve.sig.tasks.transformers.Assembler;
import com.b0ve.sig.tasks.transformers.Chopper;
import com.b0ve.sig.tasks.transformers.Splitter;
import com.b0ve.sig.tasks.transformers.Translator;
import com.b0ve.sig.utils.Process;
import com.b0ve.sig.utils.ProcessAsync;
import com.b0ve.sig.utils.ProcessSync;
import com.b0ve.sig.utils.XMLUtils;
import com.b0ve.sig.utils.condiciones.Checkeable;
import com.b0ve.sig.utils.condiciones.FilterConditionConfigurable;
import com.b0ve.sig.utils.condiciones.FilterConditionConfigurable.CONDITIONS;
import com.b0ve.sig.utils.exceptions.SIGException;
import com.b0ve.sig.utils.exceptions.LogSink;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.Map;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class AutoProcess {

    private Document lastDoc;
    
    private String name;
    private Process p;
    private final BidiMap<String, Task> tasks;
    private final BidiMap<String, Port> ports;
    private final BidiMap<String, Connector> adapters;

    private final AutoExceptionHandle exceptionHandle;

    public AutoProcess(AutoExceptionHandle exceptionHandle) {
        tasks = new DualHashBidiMap<>();
        ports = new DualHashBidiMap<>();
        adapters = new DualHashBidiMap<>();
        this.exceptionHandle = exceptionHandle;
    }

    public void build(Path filePath) throws SIGException {
        String xml;
        try {
            xml = new String(Files.readAllBytes(filePath));
        } catch (IOException ex) {
            throw new SIGException("File could no be oppened to read", filePath, ex);
        }
        build(XMLUtils.parse(xml));
    }

    private void build(Document doc) throws SIGException {
        lastDoc = doc;
        try {
            //Leer los datos basicos
            name = eval(doc, "/process/name", "Unnamed");
            boolean debug = bool(doc, "/process/config/debug", false);
            boolean async = bool(doc, "/process/config/async", false);

            //Crear el proceso
            if (async) {
                p = new ProcessAsync(debug);
            } else {
                p = new ProcessSync(debug);
            }
            p.setHandler(exceptionHandle);

            //Crear tareas con su configuración
            NodeList list = XMLUtils.eval(doc, "/process/tasks/task");
            for (int i = 0; i < list.getLength(); i++) {
                Document taskElem = XMLUtils.node2document(list.item(i));
                Task task;
                switch (Process.TASKS.valueOf(eval(taskElem, "/task/type").toUpperCase())) {
                    case CORRELATOR:
                        task = new Correlator(eval(taskElem, "/task/config", null));
                        break;
                    case MERGER:
                        task = new Merger();
                        break;
                    case FILTER:
                        task = new Filter(new FilterConditionConfigurable(eval(taskElem, "/task/config/selector"), CONDITIONS.valueOf(eval(taskElem, "/task/config/condition").toUpperCase()), eval(taskElem, "/task/config/value")));
                        break;
                    case DISTRIBUTOR:
                        NodeList caseList = XMLUtils.eval(taskElem, "/task/config/case");
                        Checkeable[] cases = new Checkeable[caseList.getLength()];
                        for (int j = 0; j < caseList.getLength(); j++) {
                            Document caseElem = XMLUtils.node2document(caseList.item(j));
                            cases[j] = new FilterConditionConfigurable(eval(caseElem, "/case/selector"), CONDITIONS.valueOf(eval(caseElem, "/case/condition").toUpperCase()), eval(caseElem, "/case/value"));
                        }
                        task = new Distributor(cases);
                        break;
                    case REPLICATOR:
                        task = new Replicator();
                        break;
                    case SLIMMER:
                        NodeList slimList = XMLUtils.eval(taskElem, "/task/config/selector");
                        String[] slims = new String[slimList.getLength()];
                        for (int j = 0; j < slimList.getLength(); j++) {
                            slims[j] = slimList.item(j).getTextContent().trim();
                        }
                        task = new Slimmer(slims);
                        break;
                    case CONTEXT_SLIMMER:
                        task = new ContextSlimmer();
                        break;
                    case ENRICHER:
                        task = new Enricher(XMLUtils.node2document(XMLUtils.eval(taskElem, "/task/config/*[1]").item(0)));
                        break;
                    case CONTEXT_ENRICHER:
                        task = new ContextEnricher();
                        break;
                    case CORRELATION_ID_SETTER:
                        task = new CorrelationIDSetter();
                        break;
                    case TRANSLATOR:
                        task = new Translator(XMLUtils.serialize(XMLUtils.node2document(XMLUtils.eval(taskElem, "/task/config/*[1]").item(0))));
                        break;
                    case SPLITTER:
                        task = new Splitter(eval(taskElem, "/task/config"));
                        break;
                    case AGGREGATOR:
                        task = new Aggregator();
                        break;
                    case CHOPPER:
                        task = new Chopper(eval(taskElem, "/task/config"));
                        break;
                    case ASSEMBLER:
                        task = new Assembler();
                        break;
                    case BLACKHOLE:
                        task = new TaskBlackhole();
                        break;
                    case DEBUG:
                        task = new TaskDebug(bool(taskElem, "/task/config", true));
                        break;
                    case FLOW_INTERRUPTER:
                        task = new TaskFlowInterrupter();
                        break;
                    default:
                        throw new SIGException("Task type not recognized", eval(taskElem, "/task/type"), null);
                }
                tasks.put(eval(taskElem, "/task/name"), task);
                p.addTask(task);
            }

            //Crear los adaptadores y sus puertos
            list = XMLUtils.eval(doc, "/process/adapters/adapter");
            for (int i = 0; i < list.getLength(); i++) {
                Document adapterElem = XMLUtils.node2document(list.item(i));
                Connector adapter;
                switch (eval(adapterElem, "/adapter/type").toLowerCase()) {
                    case "dir_watcher":
                        adapter = new DirWhatcher(eval(adapterElem, "/adapter/config"));
                        break;
                    case "dir_outputter":
                        adapter = new DirOutputter(eval(adapterElem, "/adapter/config"));
                        break;
                    case "mysql":
                        adapter = new MySQL(eval(adapterElem, "/adapter/config/host"), Integer.parseInt(eval(adapterElem, "/adapter/config/port")), eval(adapterElem, "/adapter/config/db"), eval(adapterElem, "/adapter/config/user"), eval(adapterElem, "/adapter/config/pass"));
                        break;
                    case "mysql_multi_query":
                        adapter = new MySQLmultyQuery(eval(adapterElem, "/adapter/config/host"), Integer.parseInt(eval(adapterElem, "/adapter/config/port")), eval(adapterElem, "/adapter/config/db"), eval(adapterElem, "/adapter/config/user"), eval(adapterElem, "/adapter/config/pass"));
                        break;
                    case "web_api":
                        adapter = new WebAPI(eval(adapterElem, "/adapter/config"));
                        break;
                    case "rest_api":
                        adapter = new REST();
                        break;
                    case "set":
                        adapter = new SET();
                        break;
                    case "clock":
                        adapter = new Clock(Long.parseLong(eval(adapterElem, "/adapter/config")));
                        break;
                    case "console":
                        adapter = new Console();
                        break;
                    case "screen":
                        adapter = new Screen();
                        break;
                    case "stub_input":
                        adapter = new StubInput();
                        break;
                    case "stub_output":
                        adapter = new StubOutput();
                        break;
                    case "wordpress_watcher":
                        adapter = new WordpressWatcher(eval(adapterElem, "/adapter/config/url"), eval(adapterElem, "/adapter/config/username"), eval(adapterElem, "/adapter/config/password"));
                        break;
                    case "tumblr_watcher":
                        adapter = new TumblrWatcher(eval(adapterElem, "/adapter/config/consumer-key"), eval(adapterElem, "/adapter/config/consumer-secret"), eval(adapterElem, "/adapter/config/oauth-token"), eval(adapterElem, "/adapter/config/oauth-token-secret"));
                        break;
                    case "twitter_watcher":
                        adapter = new TwitterWatcher(eval(adapterElem, "/adapter/config/consumer-key"), eval(adapterElem, "/adapter/config/consumer-secret"), eval(adapterElem, "/adapter/config/oauth-token"), eval(adapterElem, "/adapter/config/oauth-token-secret"));
                        break;
                    case "twitter_putter":
                        adapter = new TwitterPutter(eval(adapterElem, "/adapter/config/consumer-key"), eval(adapterElem, "/adapter/config/consumer-secret"), eval(adapterElem, "/adapter/config/oauth-token"), eval(adapterElem, "/adapter/config/oauth-token-secret"));
                        break;
                    case "send_mail":
                        adapter = new SendMail(eval(adapterElem, "/adapter/config/smtp-host"), Integer.parseInt(eval(adapterElem, "/adapter/config/smtp-port")), eval(adapterElem, "/adapter/config/uses-tls").toLowerCase().equals("true"), eval(adapterElem, "/adapter/config/from"), eval(adapterElem, "/adapter/config/from-name"), eval(adapterElem, "/adapter/config/from-pass"), eval(adapterElem, "/adapter/config/reply"), eval(adapterElem, "/adapter/config/reply-name"));
                        break;
                    case "sftp_uploader":
                        adapter = new SFTPUploader(eval(adapterElem, "/adapter/config/host"), Integer.parseInt(eval(adapterElem, "/adapter/config/port")), eval(adapterElem, "/adapter/config/username"), eval(adapterElem, "/adapter/config/password"));
                        break;
                    default:
                        throw new SIGException("Adapter type not recognized", eval(adapterElem, "/adapter/type"), null);
                }
                String adapterName = eval(adapterElem, "/adapter/name", "");
                adapters.put(adapterName, adapter);
                ports.put("p" + adapterName, p.createPort(adapter));
            }

            //Establecer las conexiones tarea - tarea o tarea - puerto
            list = XMLUtils.eval(doc, "/process/tasks/task");
            for (int i = 0; i < list.getLength(); i++) {
                Document taskElem = XMLUtils.node2document(list.item(i));
                String task1 = eval(taskElem, "/task/name");
                NodeList outputs = XMLUtils.eval(taskElem, "/task/outputs/output");
                for (int j = 0; j < outputs.getLength(); j++) {
                    String task2 = outputs.item(j).getTextContent().trim();
                    Task other;
                    if (tasks.containsKey(task2)) {
                        other = tasks.get(task2);
                    } else {
                        other = ports.get("p" + task2);
                    }
                    if(tasks.get(task1) == null){
                        throw new SIGException("Could not find task or adapter to connect to", task1, null);
                    }
                    if(other == null){
                        throw new SIGException("Could not find task to connect to task or port", task2, null);
                    }
                    p.connect(tasks.get(task1), other);
                }
            }

            //Establecer conexiones puerto - tarea (puerto - puerto no esta permitido)
            list = XMLUtils.eval(doc, "/process/adapters/adapter");
            for (int i = 0; i < list.getLength(); i++) {
                Document adapterElem = XMLUtils.node2document(list.item(i));
                String port1 = "p" + eval(adapterElem, "/adapter/name");
                NodeList outputs = XMLUtils.eval(adapterElem, "/adapter/outputs/output");
                for (int j = 0; j < outputs.getLength(); j++) {
                    String task2 = outputs.item(j).getTextContent().trim();
                    if(ports.get(port1) == null){
                        throw new SIGException("Could not find source port to connect to", port1, null);
                    }
                    if(tasks.get(task2) == null){
                        throw new SIGException("Could not find task to connect to port", task2, null);
                    }
                    p.connect(ports.get(port1), tasks.get(task2));
                }
            }
        } catch (NumberFormatException | DOMException | NullPointerException e) {
            throw new SIGException("Unhandled exception while parsing document", doc, e);
        }
    }

    private String eval(Document doc, String xpath) throws SIGException {
        return eval(doc, xpath, null);
    }

    private String eval(Document doc, String xpath, String def) throws SIGException {
        NodeList res = XMLUtils.eval(doc, xpath);
        if (res.getLength() > 0) {
            return res.item(0).getTextContent().strip();
        } else {
            return def;
        }
    }

    private boolean bool(Document doc, String xpath, boolean def) throws SIGException {
        NodeList res = XMLUtils.eval(doc, xpath);
        if (res.getLength() > 0) {
            return res.item(0).getTextContent().strip().toLowerCase().equals("true");
        } else {
            return def;
        }
    }

    public void start() {
        p.execute();
    }

    public void stop() throws InterruptedException, SIGException {
        p.shutdown();
        p.waitToEnd();
        build(lastDoc);
    }

    public void validate() {
        try {
            p.validate();
            p.debugLog("Validation complete. Process seems valid.");
        } catch (SIGException ex) {
            exceptionHandle.handleException(ex);
        }
    }

    public void handleException(SIGException ex) {
        exceptionHandle.handleException(ex);
    }

    public Iterator<Map.Entry<String, Task>> getTasks() {
        return tasks.entrySet().iterator();
    }

    public Iterator<Map.Entry<String, Connector>> getAdapters() {
        return adapters.entrySet().iterator();
    }

    public Iterator<Map.Entry<String, Port>> getPorts() {
        return ports.entrySet().iterator();
    }

    public Object getByName(String name) {
        Object val = tasks.get(name);
        if (val == null) {
            val = ports.get(name);
        }
        if (val == null) {
            val = adapters.get(name);
        }
        return val;
    }

    public String findName(Object task) {
        String val = tasks.getKey(task);
        if (val == null) {
            val = ports.getKey(task);
        }
        if (val == null) {
            val = adapters.getKey(task);
        }
        return val;
    }

    public String getName() {
        return name;
    }

    public void setLogSink(LogSink logSink) {
        p.setLogSink(logSink);
    }

    public int messageCount() {
        return p.messageCount();
    }

}
