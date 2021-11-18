package ejercicio1;

import com.b0ve.solucionintegraciongenerica.adapters.AdapterMySQL;
import com.b0ve.solucionintegraciongenerica.ports.Port;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import static com.b0ve.solucionintegraciongenerica.utils.Process.TASKS.*;
import com.b0ve.solucionintegraciongenerica.utils.ProcessAsync;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionNotEquals;

public class Ejercicio1 {

    //<acta><asignatura>PVWB</asignatura><alumnos><alumno><id>borja.lopez248</id><nota>2</nota></alumno><alumno><id>spit.fire144</id><nota>10</nota></alumno></alumnos></acta>
    public static void main(String[] args) throws Exception {
        Process p = new ProcessAsync();
        
        AdaptadorGMS aGMS = new AdaptadorGMS();
        AdapterMySQL aMySQL = new AdapterMySQL("localhost", 3306, "ejercicio1", "root", "");
        AdaptadorMail aMail = new AdaptadorMail();
        AdaptadorSMS aSMS = new AdaptadorSMS();
        
        Port pGMS = p.createPort(aGMS);
        Port pMySQL = p.createPort(aMySQL);
        Port pMail = p.createPort(aMail);
        Port pSMS = p.createPort(aSMS);
        
        Task translator1 = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/acta\">\n" +
        "        <alumnos>\n" +
        "            <xsl:for-each select=\"alumnos/alumno\">\n" +
        "                <alumno>\n" +
        "                    <asignatura>\n" +
        "                        <xsl:value-of select=\"/acta/asignatura\"/>\n" +
        "                    </asignatura>\n" +
        "                    <id>\n" +
        "                        <xsl:value-of select=\"id\"/>\n" +
        "                    </id>\n" +
        "                    <nota>\n" +
        "                        <xsl:value-of select=\"nota\"/>\n" +
        "                    </nota>\n" +
        "                </alumno>\n" +
        "            </xsl:for-each>\n" +
        "        </alumnos>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Task splitter = p.createTask(SPLITTER, "/alumnos/alumno");
        Task replicator1 = p.createTask(REPLICATOR);
        Task translator2 = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/alumno\">\n" +
        "        <sql>\n" +
        "            SELECT `Email`, `Telefono` FROM `alumnos` WHERE `ID` = '<xsl:value-of select=\"id\"/>'\n" +
        "        </sql>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Task translator3 = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/Results/Row\">\n" +
        "        <alumno>\n" +
        "            <email><xsl:value-of select=\"Email\"/></email>\n" +
        "            <telefono><xsl:value-of select=\"Telefono\"/></telefono>\n" +
        "        </alumno>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Task correlator = p.createTask(CORRELATOR);
        Task enricher = p.createTask(CONTEXT_ENRICHER);
        Task replicator2 = p.createTask(REPLICATOR);
        Task filter = p.createTask(FILTER, new FilterConditionNotEquals("/alumno/telefono", "null"));
        
        p.connect(pGMS, translator1);
        p.connect(translator1, splitter);
        p.connect(splitter, replicator1);
        p.connect(replicator1, translator2);
        p.connect(replicator1, correlator);
        p.connect(translator2, pMySQL);
        p.connect(pMySQL, translator3);
        p.connect(translator3, correlator);
        p.connect(correlator, enricher);
        p.connect(correlator, enricher);
        p.connect(enricher, replicator2);
        p.connect(replicator2, pMail);
        p.connect(replicator2, filter);
        p.connect(filter, pSMS);
        
        p.validate();
        p.execute();
        p.waitToEnd();
    }
    
}
