package cafe;

import com.b0ve.solucionintegraciongenerica.adaptadores.*;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.Proceso;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.*;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterCondition;

public class Cafe {

    public static void main(String[] args) throws Exception {
        Proceso p = new Proceso();
        
        AdaptadorFicheroWhatcher comandas = new AdaptadorFicheroWhatcher("C:\\Users\\borja\\Downloads\\comandas", null);
        AdaptadorFicheroWhatcher camarero = new AdaptadorFicheroWhatcher(null, "C:\\Users\\borja\\Downloads\\camarero");
        AdaptadorMySQL barmanFrio = new AdaptadorMySQL("localhost", 3306, "cafe", "root", "");
        AdaptadorMySQL barmanCaliente = new AdaptadorMySQL("localhost", 3306, "cafe", "root", "");
        
        Puerto pComandas = p.crearPuerto(comandas);
        Puerto pCamarero = p.crearPuerto(camarero);
        Puerto pBarmanFrio = p.crearPuerto(barmanFrio);
        Puerto pBarmanCaliente = p.crearPuerto(barmanCaliente);
        
        Tarea divisor = p.crearTarea(SPLITTER, "/cafeorder/drink");
        Tarea encauzador = p.crearTarea(DISTRIBUTOR, new FilterCondition[]{new FilterCondition("/drink/type", "cold"), new FilterCondition("/drink/type", "hot")});
        
        Tarea replicadorFrio = p.crearTarea(REPLICATOR);
        Tarea traductorQuerryFrio = p.crearTarea(TRANSLATOR, "<?xml version=\"1.0\"?><xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"><xsl:template match=\"/drink\">"
                + "<sql>SELECT `Stock` FROM `cafe` WHERE `Nombre` = '<xsl:value-of select=\"name\"/>'</sql>"
                + "</xsl:template></xsl:stylesheet>");
        Tarea traductorResultadoFrio = p.crearTarea(TRANSLATOR, "<?xml version=\"1.0\"?><xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"><xsl:template match=\"/Results\">"
                + "<drink><stock><xsl:value-of select=\"Row/Stock\"/></stock></drink>"
                + "</xsl:template></xsl:stylesheet>");
        Tarea juntadorFrio = p.crearTarea(CORRELATOR);
        Tarea combinadorFrio = p.crearTarea(CONTEXT_ENRICHER);
        
        Tarea replicadorCaliente = p.crearTarea(REPLICATOR);
        Tarea traductorQuerryCaliente = p.crearTarea(TRANSLATOR, "<?xml version=\"1.0\"?><xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"><xsl:template match=\"/drink\">"
                + "<sql>SELECT `Stock` FROM `cafe` WHERE `Nombre` = '<xsl:value-of select=\"name\"/>'</sql>"
                + "</xsl:template></xsl:stylesheet>");
        Tarea traductorResultadoCaliente = p.crearTarea(TRANSLATOR, "<?xml version=\"1.0\"?><xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\"><xsl:template match=\"/Results\">"
                + "<drink><stock><xsl:value-of select=\"Row/Stock\"/></stock></drink>"
                + "</xsl:template></xsl:stylesheet>");
        Tarea juntadorCaliente = p.crearTarea(CORRELATOR);
        Tarea combinadorCaliente = p.crearTarea(CONTEXT_ENRICHER);
        
        Tarea reunidor = p.crearTarea(MERGER);
        Tarea arrejuntador = p.crearTarea(AGGREGATOR, "cafeorder");
        
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
        
        comandas.iniciar();
        camarero.iniciar();
        
        p.esperar();
        System.out.println("Terminado");
    }
    
}
