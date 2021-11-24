package com.b0ve.iia.cafe;

import com.b0ve.sig.adapters.Adapter;
import com.b0ve.sig.adapters.basic.AdapterDirOutputter;
import com.b0ve.sig.adapters.basic.AdapterDirWhatcher;
import com.b0ve.sig.adapters.basic.AdapterMySQL;
import com.b0ve.sig.adapters.basic.AdapterWebAPI;
import com.b0ve.sig.ports.Port;
import com.b0ve.sig.tasks.Task;
import com.b0ve.sig.utils.ProcessAsync;
import com.b0ve.sig.utils.Process;
import static com.b0ve.sig.utils.Process.TASKS.*;
import com.b0ve.sig.utils.condiciones.Checkeable;
import com.b0ve.sig.utils.condiciones.FilterConditionConfigurable;

public class Cafe {
    public static void main(String[] args) throws Exception {
        Process p = new ProcessAsync();
        
        Adapter comandas = new AdapterDirWhatcher("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\cafe\\comandas");
        Adapter camarero = new AdapterDirOutputter("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\cafe\\camarero");
        Adapter barmanFrio = new AdapterMySQL("b0ve.com", 3306, "cafe", "cafe", "TSVn3KZPje4TYthd");
        Adapter barmanCaliente = new AdapterWebAPI("https://cafe.b0ve.com/api.php");
        
        Port pComandas = p.createPort(comandas);
        Port pCamarero = p.createPort(camarero);
        Port pBarmanFrio = p.createPort(barmanFrio);
        Port pBarmanCaliente = p.createPort(barmanCaliente);
        
        Task divisor = p.createTask(SPLITTER, "/cafe_order/drinks/drink");
        Task encauzador = p.createTask(DISTRIBUTOR, new Checkeable[]{new FilterConditionConfigurable("/drink/type", FilterConditionConfigurable.CONDITIONS.STR_EQUALS, "cold")});
        
        Task replicadorFrio = p.createTask(REPLICATOR);
        Task traductorQuerryFrio = p.createTask(TRANSLATOR, "<?xml version=\"1.0\"?><xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"><xsl:template match=\"/drink\">"
                + "<sql>SELECT `Stock` FROM `drinks_cold` WHERE `Name` = '<xsl:value-of select=\"name\"/>'</sql>"
                + "</xsl:template></xsl:stylesheet>");
        Task traductorResultadoFrio = p.createTask(TRANSLATOR, "<?xml version=\"1.0\"?><xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"><xsl:template match=\"/Results\">"
                + "<drink><stock><xsl:value-of select=\"Row/Stock\"/></stock></drink>"
                + "</xsl:template></xsl:stylesheet>");
        Task juntadorFrio = p.createTask(CORRELATOR);
        Task combinadorFrio = p.createTask(CONTEXT_ENRICHER);
        
        Task replicadorCaliente = p.createTask(REPLICATOR);
        Task traductorQuerryCaliente = p.createTask(TRANSLATOR, "<?xml version=\"1.0\"?><xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"><xsl:template match=\"/drink\">"
                + "<call><action>consultarStock</action><nombre><xsl:value-of select=\"name\"/></nombre></call>"
                + "</xsl:template></xsl:stylesheet>");
        Task traductorResultadoCaliente = p.createTask(TRANSLATOR, "<?xml version=\"1.0\"?><xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"><xsl:template match=\"/response\">"
                + "<drink><stock><xsl:value-of select=\"msg\"/></stock></drink>"
                + "</xsl:template></xsl:stylesheet>");
        Task juntadorCaliente = p.createTask(CORRELATOR);
        Task combinadorCaliente = p.createTask(CONTEXT_ENRICHER);
        
        Task reunidor = p.createTask(MERGER);
        Task arrejuntador = p.createTask(AGGREGATOR, new String[]{"cafe_order", "drinks"});
        
        p.connect(pComandas, divisor);
        p.connect(divisor, encauzador);
        p.connect(encauzador, replicadorFrio);
        p.connect(encauzador, replicadorCaliente);
        
        p.connect(replicadorFrio, traductorQuerryFrio);
        p.connect(traductorQuerryFrio, pBarmanFrio);
        p.connect(replicadorFrio, juntadorFrio);
        p.connect(pBarmanFrio, traductorResultadoFrio);
        p.connect(traductorResultadoFrio, juntadorFrio);
        p.connect(juntadorFrio, combinadorFrio);
        p.connect(juntadorFrio, combinadorFrio);
        p.connect(combinadorFrio, reunidor);
        
        p.connect(replicadorCaliente, traductorQuerryCaliente);
        p.connect(traductorQuerryCaliente, pBarmanCaliente);
        p.connect(replicadorCaliente, juntadorCaliente);
        p.connect(pBarmanCaliente, traductorResultadoCaliente);
        p.connect(traductorResultadoCaliente, juntadorCaliente);
        p.connect(juntadorCaliente, combinadorCaliente);
        p.connect(juntadorCaliente, combinadorCaliente);
        p.connect(combinadorCaliente, reunidor);
        
        p.connect(reunidor, arrejuntador);
        p.connect(arrejuntador, pCamarero);
        
        
        p.validate();
        p.execute();
        p.waitToEnd();
    }
}
