package ejercicio2;

import com.b0ve.solucionintegraciongenerica.adaptadores.AdaptadorFicheroWhatcher;
import com.b0ve.solucionintegraciongenerica.adaptadores.AdaptadorMySQL;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.tareas.modifiers.SlimmerTemplate;
import com.b0ve.solucionintegraciongenerica.utils.Proceso;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.*;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterCondition;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.time.Instant;

public class Ejercicio2 {

    public static void main(String[] args) throws Exception {
        Proceso p = new Proceso(true);
        
        AdaptadorFicheroWhatcher aEMS1 = new AdaptadorFicheroWhatcher("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio2\\in", null);
        AdaptadorBD aEMS2 = new AdaptadorBD();
        AdaptadorExchange aEMS3 = new AdaptadorExchange();
        AdaptadorEstimador aEstimador = new AdaptadorEstimador();
        AdaptadorMySQL aToGPS = new AdaptadorMySQL("localhost", 3306, "ejercicio2", "root", "");
        
        Puerto pEMS1 = p.crearPuerto(aEMS1);
        Puerto pEMS2 = p.crearPuerto(aEMS2);
        Puerto pEMS3 = p.crearPuerto(aEMS3);
        Puerto pEstimador = p.crearPuerto(aEstimador);
        Puerto pToGPS = p.crearPuerto(aToGPS);
        
        Tarea merger1 = p.crearTarea(MERGER);
        Tarea replicator = p.crearTarea(REPLICATOR);
        Tarea translator1 = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/medida\">\n" +
        "        <sql>\n" +
        "            SELECT `Coordenadas` FROM `lugares` WHERE `Lugar` = '<xsl:value-of select=\"lugar\"/>'\n" +
        "        </sql>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Tarea translator2 = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
        "    <xsl:template match=\"/Results/Row\">\n" +
        "        <medida>\n" +
        "            <lugar><xsl:value-of select=\"Coordenadas\"/></lugar>\n" +
        "        </medida>\n" +
        "    </xsl:template>\n" +
        "</xsl:stylesheet>");
        Tarea slimmer = p.addTarea(new SlimmerTemplate() {
            @Override
            protected void slim(Mensaje mensaje) {
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
        Tarea correlator = p.crearTarea(CORRELATOR);
        Tarea enricher = p.crearTarea(CONTEXT_ENRICHER);
        Tarea merger2 = p.crearTarea(MERGER);
        Tarea filter = p.crearTarea(FILTER, new FilterCondition("/medida/ts") {
            @Override
            protected boolean testValue(String text) {
                return Long.parseLong(text) > Instant.now().getEpochSecond()-(15*60);
            }
        });
        
        p.encadenar(pEMS1, merger1);
        p.encadenar(pEMS2, merger1);
        p.encadenar(merger1, replicator);
        p.encadenar(replicator, translator1);
        p.encadenar(replicator, slimmer);
        p.encadenar(slimmer, correlator);
        p.encadenar(translator1, pToGPS);
        p.encadenar(pToGPS, translator2);
        p.encadenar(translator2, correlator);
        p.encadenar(correlator, enricher);
        p.encadenar(correlator, enricher);
        p.encadenar(enricher, merger2);
        p.encadenar(pEMS3, merger2);
        p.encadenar(merger2, filter);
        p.encadenar(filter, pEstimador);
        
        p.validar();
        p.ejecutar();
        p.esperar();
    }
    
}
