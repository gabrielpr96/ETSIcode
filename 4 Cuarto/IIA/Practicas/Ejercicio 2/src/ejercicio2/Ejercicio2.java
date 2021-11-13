package ejercicio2;

import com.b0ve.solucionintegraciongenerica.adapters.AdapterDirWhatcher;
import com.b0ve.solucionintegraciongenerica.adapters.AdapterMySQL;
import com.b0ve.solucionintegraciongenerica.ports.Port;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.tasks.modifiers.SlimmerTemplate;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import static com.b0ve.solucionintegraciongenerica.utils.Process.TASKS.*;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterCondition;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.ProcessAsync;
import java.time.Instant;

public class Ejercicio2 {

    public static void main(String[] args) throws Exception {
        Process p = new ProcessAsync(true);
        
        AdapterDirWhatcher aEMS1 = new AdapterDirWhatcher("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio2\\in");
        AdaptadorBD aEMS2 = new AdaptadorBD();
        AdaptadorExchange aEMS3 = new AdaptadorExchange();
        AdaptadorEstimador aEstimador = new AdaptadorEstimador();
        AdapterMySQL aToGPS = new AdapterMySQL("localhost", 3306, "ejercicio2", "root", "");
        
        Port pEMS1 = p.createPort(aEMS1);
        Port pEMS2 = p.createPort(aEMS2);
        Port pEMS3 = p.createPort(aEMS3);
        Port pEstimador = p.createPort(aEstimador);
        Port pToGPS = p.createPort(aToGPS);
        
        Task merger1 = p.createTask(MERGER);
        Task replicator = p.createTask(REPLICATOR);
        Task translator1 = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/medida\">\n" +
        "        <sql>\n" +
        "            SELECT `Coordenadas` FROM `lugares` WHERE `Lugar` = '<xsl:value-of select=\"lugar\"/>'\n" +
        "        </sql>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Task translator2 = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/Results/Row\">\n" +
        "        <medida>\n" +
        "            <lugar><xsl:value-of select=\"Coordenadas\"/></lugar>\n" +
        "        </medida>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Task slimmer = p.addTask(new SlimmerTemplate() {
            @Override
            protected void slim(Message mensaje) {
                mensaje.transformBody("<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
                "    <xsl:template match=\"/medida\">\n" +
                "        <medida>\n" +
                "            <ts><xsl:value-of select=\"ts\"/></ts>\n" +
                "            <valor><xsl:value-of select=\"valor\"/></valor>\n" +
                "        </medida>\n" +
                "    </xsl:template>\n" +
                "</xsl:stylesheet>");
            }
        });
        Task correlator = p.createTask(CORRELATOR);
        Task enricher = p.createTask(CONTEXT_ENRICHER);
        Task merger2 = p.createTask(MERGER);
        Task filter = p.createTask(FILTER, new FilterCondition("/medida/ts") {
            @Override
            protected boolean testValue(String text) {
                return Long.parseLong(text) > Instant.now().getEpochSecond()-(15*60);
            }
        });
        
        p.connect(pEMS1, merger1);
        p.connect(pEMS2, merger1);
        p.connect(merger1, replicator);
        p.connect(replicator, translator1);
        p.connect(replicator, slimmer);
        p.connect(slimmer, correlator);
        p.connect(translator1, pToGPS);
        p.connect(pToGPS, translator2);
        p.connect(translator2, correlator);
        p.connect(correlator, enricher);
        p.connect(correlator, enricher);
        p.connect(enricher, merger2);
        p.connect(pEMS3, merger2);
        p.connect(merger2, filter);
        p.connect(filter, pEstimador);
        
        p.validate();
        p.execute();
        p.waitToEnd();
    }
    
}
