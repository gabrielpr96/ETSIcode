package ejercicio4;

import com.b0ve.solucionintegraciongenerica.adaptadores.AdaptadorMySQL;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.Proceso;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.*;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionEquals;

public class Ejercicio4 {

    public static void main(String[] args) throws Exception {
        Proceso p = new Proceso(true);
        
        AdaptadorCCS aCSS = new AdaptadorCCS();
        AdaptadorMySQL aAPP = new AdaptadorMySQL("localhost", 3306, "ejercicio4", "root", "");
        AdaptadorMySQL aHRS = new AdaptadorMySQL("localhost", 3306, "ejercicio4", "root", "");
        AdaptadorMail aMail = new AdaptadorMail();
        AdaptadorDS aDS = new AdaptadorDS();
        
        Puerto pCCS = p.crearPuerto(aCSS);
        Puerto pAPP = p.crearPuerto(aAPP);
        Puerto pHRS = p.crearPuerto(aHRS);
        Puerto pMail = p.crearPuerto(aMail);
        Puerto pDS = p.crearPuerto(aDS);
        
        Tarea filter1 = p.crearTarea(FILTER, new FilterConditionEquals("/llamada/sentido", "saliente"));
        Tarea replicator1 = p.crearTarea(REPLICATOR);
        Tarea translator1 = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/llamada\">\n" +
        "        <sql>\n" +
        "            SELECT `Tipo`FROM `telefonos` WHERE `Telefono` = '<xsl:value-of select=\"destino\"/>'\n" +
        "        </sql>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Tarea translator2 = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/Results/Row\">\n" +
        "        <llamada>\n" +
        "            <tipo><xsl:value-of select=\"Tipo\"/></tipo>\n" +
        "        </llamada>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Tarea correlator1 = p.crearTarea(CORRELATOR);
        Tarea enricher1 = p.crearTarea(CONTEXT_ENRICHER);
        Tarea filter2 = p.crearTarea(FILTER, new FilterConditionEquals("/llamada/tipo", "publico"));
        Tarea replicator2 = p.crearTarea(REPLICATOR);
        Tarea translator3 = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/llamada\">\n" +
        "        <sql>\n" +
        "            SELECT `Email`, `DNI` FROM `trabajadores` WHERE `Telefono` = '<xsl:value-of select=\"origen\"/>'\n" +
        "        </sql>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Tarea translator4 = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/Results/Row\">\n" +
        "        <llamada>\n" +
        "            <email><xsl:value-of select=\"Email\"/></email>\n" +
        "            <dni><xsl:value-of select=\"DNI\"/></dni>\n" +
        "        </llamada>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Tarea correlator2 = p.crearTarea(CORRELATOR);
        Tarea enricher2 = p.crearTarea(CONTEXT_ENRICHER);
        Tarea replicator3 = p.crearTarea(REPLICATOR);
        
        p.encadenar(pCCS, filter1);
        p.encadenar(filter1, replicator1);
        p.encadenar(replicator1, translator1);
        p.encadenar(translator1, pAPP);
        p.encadenar(pAPP, translator2);
        p.encadenar(translator2, correlator1);
        p.encadenar(replicator1, correlator1);
        p.encadenar(correlator1, enricher1);
        p.encadenar(correlator1, enricher1);
        p.encadenar(enricher1, filter2);
        p.encadenar(filter2, replicator2);
        p.encadenar(replicator2, translator3);
        p.encadenar(translator3, pHRS);
        p.encadenar(pHRS, translator4);
        p.encadenar(translator4, correlator2);
        p.encadenar(replicator2, correlator2);
        p.encadenar(correlator2, enricher2);
        p.encadenar(correlator2, enricher2);
        p.encadenar(enricher2, replicator3);
        p.encadenar(replicator3, pMail);
        p.encadenar(replicator3, pDS);
        
        p.validar();
        p.ejecutar();
        p.esperar();
    }
    
}
