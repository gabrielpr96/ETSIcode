package cafe;

import com.b0ve.solucionintegraciongenerica.adaptadores.*;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.Proceso;
import com.b0ve.solucionintegraciongenerica.utils.ProcesoSync;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.*;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionEquals;

public class Cafe {

    public static void main(String[] args) throws Exception {
        Proceso p = new Proceso();
        
        AdaptadorFicheroWhatcher comandas = new AdaptadorFicheroWhatcher("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\cafe\\comandas", null);
        AdaptadorFicheroWhatcher camarero = new AdaptadorFicheroWhatcher(null, "C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\cafe\\camarero");
        AdaptadorMySQL barmanFrio = new AdaptadorMySQL("localhost", 3306, "cafe", "root", "");
        AdaptadorPHP barmanCaliente = new AdaptadorPHP("http://localhost/cafe/api.php");
        
        Puerto pComandas = p.crearPuerto(comandas);
        Puerto pCamarero = p.crearPuerto(camarero);
        Puerto pBarmanFrio = p.crearPuerto(barmanFrio);
        Puerto pBarmanCaliente = p.crearPuerto(barmanCaliente);
        
        Tarea divisor = p.crearTarea(SPLITTER, "/cafe_order/drinks/drink");
        Tarea encauzador = p.crearTarea(DISTRIBUTOR, new FilterConditionEquals[]{new FilterConditionEquals("/drink/type", "cold"), new FilterConditionEquals("/drink/type", "hot")});
        
        Tarea replicadorFrio = p.crearTarea(REPLICATOR);
        Tarea traductorQuerryFrio = p.crearTarea(TRANSLATOR, "<?xml version=\"1.0\"?><xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"><xsl:template match=\"/drink\">"
                + "<sql>SELECT `Stock` FROM `bebidas` WHERE `Nombre` = '<xsl:value-of select=\"name\"/>'</sql>"
                + "</xsl:template></xsl:stylesheet>");
        Tarea traductorResultadoFrio = p.crearTarea(TRANSLATOR, "<?xml version=\"1.0\"?><xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"><xsl:template match=\"/Results\">"
                + "<drink><stock><xsl:value-of select=\"Row/Stock\"/></stock></drink>"
                + "</xsl:template></xsl:stylesheet>");
        Tarea juntadorFrio = p.crearTarea(CORRELATOR);
        Tarea combinadorFrio = p.crearTarea(CONTEXT_ENRICHER);
        
        Tarea replicadorCaliente = p.crearTarea(REPLICATOR);
        Tarea traductorQuerryCaliente = p.crearTarea(TRANSLATOR, "<?xml version=\"1.0\"?><xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"><xsl:template match=\"/drink\">"
                + "<call><action>consultarStock</action><nombre><xsl:value-of select=\"name\"/></nombre></call>"
                + "</xsl:template></xsl:stylesheet>");
        Tarea traductorResultadoCaliente = p.crearTarea(TRANSLATOR, "<?xml version=\"1.0\"?><xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"><xsl:template match=\"/response\">"
                + "<drink><stock><xsl:value-of select=\"msg\"/></stock></drink>"
                + "</xsl:template></xsl:stylesheet>");
        Tarea juntadorCaliente = p.crearTarea(CORRELATOR);
        Tarea combinadorCaliente = p.crearTarea(CONTEXT_ENRICHER);
        
        Tarea reunidor = p.crearTarea(MERGER);
        Tarea arrejuntador = p.crearTarea(AGGREGATOR, new String[]{"cafe_order", "drinks"});
        
        p.encadenar(pComandas, divisor);
        p.encadenar(divisor, encauzador);
        p.encadenar(encauzador, replicadorFrio);
        p.encadenar(encauzador, replicadorCaliente);
        
        p.encadenar(replicadorFrio, traductorQuerryFrio);
        p.encadenar(traductorQuerryFrio, pBarmanFrio);
        p.encadenar(replicadorFrio, juntadorFrio);
        p.encadenar(pBarmanFrio, traductorResultadoFrio);
        p.encadenar(traductorResultadoFrio, juntadorFrio);
        p.encadenar(juntadorFrio, combinadorFrio);
        p.encadenar(juntadorFrio, combinadorFrio);
        p.encadenar(combinadorFrio, reunidor);
        
        p.encadenar(replicadorCaliente, traductorQuerryCaliente);
        p.encadenar(traductorQuerryCaliente, pBarmanCaliente);
        p.encadenar(replicadorCaliente, juntadorCaliente);
        p.encadenar(pBarmanCaliente, traductorResultadoCaliente);
        p.encadenar(traductorResultadoCaliente, juntadorCaliente);
        p.encadenar(juntadorCaliente, combinadorCaliente);
        p.encadenar(juntadorCaliente, combinadorCaliente);
        p.encadenar(combinadorCaliente, reunidor);
        
        p.encadenar(reunidor, arrejuntador);
        p.encadenar(arrejuntador, pCamarero);
        
        
        p.validar();
        p.ejecutar();
        p.esperar();
    }
    
}
