package ejercicio1;

import com.b0ve.solucionintegraciongenerica.adaptadores.AdaptadorMySQL;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.Proceso;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.*;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionNotEquals;

public class Ejercicio1 {

    public static void main(String[] args) throws Exception {
        Proceso p = new Proceso();
        
        AdaptadorGMS aGMS = new AdaptadorGMS();
        AdaptadorMySQL aMySQL = new AdaptadorMySQL("localhost", 3306, "ejercicio1", "root", "");
        AdaptadorMail aMail = new AdaptadorMail();
        AdaptadorSMS aSMS = new AdaptadorSMS();
        
        Puerto pGMS = p.crearPuerto(aGMS);
        Puerto pMySQL = p.crearPuerto(aMySQL);
        Puerto pMail = p.crearPuerto(aMail);
        Puerto pSMS = p.crearPuerto(aSMS);
        
        Tarea translator1 = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
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
        Tarea splitter = p.crearTarea(SPLITTER, "/alumnos/alumno");
        Tarea replicator1 = p.crearTarea(REPLICATOR);
        Tarea translator2 = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/alumno\">\n" +
        "        <sql>\n" +
        "            SELECT `Email`, `Telefono` FROM `alumnos` WHERE `ID` = '<xsl:value-of select=\"id\"/>'\n" +
        "        </sql>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Tarea translator3 = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/Results/Row\">\n" +
        "        <alumno>\n" +
        "            <email><xsl:value-of select=\"Email\"/></email>\n" +
        "            <telefono><xsl:value-of select=\"Telefono\"/></telefono>\n" +
        "        </alumno>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Tarea correlator = p.crearTarea(CORRELATOR);
        Tarea enricher = p.crearTarea(CONTEXT_ENRICHER);
        Tarea replicator2 = p.crearTarea(REPLICATOR);
        Tarea filter = p.crearTarea(FILTER, new FilterConditionNotEquals("/alumno/telefono", "null"));
        
        p.encadenar(pGMS, translator1);
        p.encadenar(translator1, splitter);
        p.encadenar(splitter, replicator1);
        p.encadenar(replicator1, translator2);
        p.encadenar(replicator1, correlator);
        p.encadenar(translator2, pMySQL);
        p.encadenar(pMySQL, translator3);
        p.encadenar(translator3, correlator);
        p.encadenar(correlator, enricher);
        p.encadenar(correlator, enricher);
        p.encadenar(enricher, replicator2);
        p.encadenar(replicator2, pMail);
        p.encadenar(replicator2, filter);
        p.encadenar(filter, pSMS);
        
        p.validar();
        p.ejecutar();
        p.esperar();
    }
    
}
