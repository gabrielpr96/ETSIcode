package ejercicio4;

import com.b0ve.solucionintegraciongenerica.adapters.AdapterMySQL;
import com.b0ve.solucionintegraciongenerica.ports.Port;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import static com.b0ve.solucionintegraciongenerica.utils.Process.TASKS.*;
import com.b0ve.solucionintegraciongenerica.utils.ProcessAsync;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionEquals;

public class Ejercicio4 {

    //<llamada><origen>689140827</origen><destino>401443301</destino><sentido>saliente</sentido><duracion>15</duracion></llamada>
    public static void main(String[] args) throws Exception {
        Process p = new ProcessAsync(true);
        
        AdaptadorCCS aCSS = new AdaptadorCCS();
        AdapterMySQL aAPP = new AdapterMySQL("localhost", 3306, "ejercicio4", "root", "");
        AdapterMySQL aHRS = new AdapterMySQL("localhost", 3306, "ejercicio4", "root", "");
        AdaptadorMail aMail = new AdaptadorMail();
        AdaptadorDS aDS = new AdaptadorDS();
        
        Port pCCS = p.createPort(aCSS);
        Port pAPP = p.createPort(aAPP);
        Port pHRS = p.createPort(aHRS);
        Port pMail = p.createPort(aMail);
        Port pDS = p.createPort(aDS);
        
        Task filter1 = p.createTask(FILTER, new FilterConditionEquals("/llamada/sentido", "saliente"));
        Task replicator1 = p.createTask(REPLICATOR);
        Task translator1 = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/llamada\">\n" +
        "        <sql>\n" +
        "            SELECT `Tipo`FROM `telefonos` WHERE `Telefono` = '<xsl:value-of select=\"destino\"/>'\n" +
        "        </sql>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Task translator2 = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/Results/Row\">\n" +
        "        <llamada>\n" +
        "            <tipo><xsl:value-of select=\"Tipo\"/></tipo>\n" +
        "        </llamada>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Task correlator1 = p.createTask(CORRELATOR);
        Task enricher1 = p.createTask(CONTEXT_ENRICHER);
        Task filter2 = p.createTask(FILTER, new FilterConditionEquals("/llamada/tipo", "publico"));
        Task replicator2 = p.createTask(REPLICATOR);
        Task translator3 = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/llamada\">\n" +
        "        <sql>\n" +
        "            SELECT `Email`, `DNI` FROM `trabajadores` WHERE `Telefono` = '<xsl:value-of select=\"origen\"/>'\n" +
        "        </sql>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Task translator4 = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/Results/Row\">\n" +
        "        <llamada>\n" +
        "            <email><xsl:value-of select=\"Email\"/></email>\n" +
        "            <dni><xsl:value-of select=\"DNI\"/></dni>\n" +
        "        </llamada>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Task correlator2 = p.createTask(CORRELATOR);
        Task enricher2 = p.createTask(CONTEXT_ENRICHER);
        Task replicator3 = p.createTask(REPLICATOR);
        
        p.connect(pCCS, filter1);
        p.connect(filter1, replicator1);
        p.connect(replicator1, translator1);
        p.connect(translator1, pAPP);
        p.connect(pAPP, translator2);
        p.connect(translator2, correlator1);
        p.connect(replicator1, correlator1);
        p.connect(correlator1, enricher1);
        p.connect(correlator1, enricher1);
        p.connect(enricher1, filter2);
        p.connect(filter2, replicator2);
        p.connect(replicator2, translator3);
        p.connect(translator3, pHRS);
        p.connect(pHRS, translator4);
        p.connect(translator4, correlator2);
        p.connect(replicator2, correlator2);
        p.connect(correlator2, enricher2);
        p.connect(correlator2, enricher2);
        p.connect(enricher2, replicator3);
        p.connect(replicator3, pMail);
        p.connect(replicator3, pDS);
        
        p.validate();
        p.execute();
        p.waitToEnd();
    }
    
}
