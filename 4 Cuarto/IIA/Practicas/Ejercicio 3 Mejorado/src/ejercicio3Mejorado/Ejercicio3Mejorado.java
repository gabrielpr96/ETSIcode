package ejercicio3Mejorado;

import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.Proceso;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.*;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionNotEquals;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import ejercicio3.AdaptadorCRM1Entrada;
import ejercicio3.AdaptadorCRM1Salida;
import ejercicio3.AdaptadorCRM2Entrada;
import ejercicio3.AdaptadorCRM2Salida;
import ejercicio3.AdaptadorCRM3Entrada;
import ejercicio3.AdaptadorCRM3Salida;

public class Ejercicio3Mejorado {
//<cambios><cambio><tipo>eliminar</tipo><datos><dni>40144663C</dni></datos></cambio></cambios>
//<cambios><cambio><tipo>crear</tipo><datos><dni>40144664S</dni><nombre>Manolo</nombre><direccion>Alan Turing 2</direccion></datos></cambio></cambios>

    public static void main(String[] args) throws Exception {
        Proceso p = new Proceso(false);

        AdaptadorCRM1Entrada crm1In = new AdaptadorCRM1Entrada("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio3\\cambios");
        AdaptadorCRM2Entrada crm2In = new AdaptadorCRM2Entrada();
        AdaptadorCRM3Entrada crm3In = new AdaptadorCRM3Entrada();
        AdaptadorCRM1Salida crm1Out = new AdaptadorCRM1Salida("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio3\\replicados");
        AdaptadorCRM2Salida crm2Out = new AdaptadorCRM2Salida();
        AdaptadorCRM3Salida crm3Out = new AdaptadorCRM3Salida(crm3In);

        Puerto pCrm1In = p.crearPuerto(crm1In);
        Puerto pCrm2In = p.crearPuerto(crm2In);
        Puerto pCrm3In = p.crearPuerto(crm3In);
        Puerto pCrm1Out = p.crearPuerto(crm1Out);
        Puerto pCrm2Out = p.crearPuerto(crm2Out);
        Puerto pCrm3Out = p.crearPuerto(crm3Out);

        Tarea translatorCrm1In = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"2.0\">\n"
                + "    <xsl:template match=\"/cambios\">\n"
                + "        <cambios>\n"
                + "            <xsl:for-each-group select=\"cambio\" group-by=\"datos/dni\">\n"
                + "                <cambio>\n"
                + "                    <tipo>\n"
                + "                        <xsl:value-of select=\"current-group()[1]/tipo\"/>\n"
                + "                    </tipo>\n"
                + "                    <datos>\n"
                + "                        <dni>\n"
                + "                            <xsl:value-of select=\"current-grouping-key()\"/>\n"
                + "                        </dni>\n"
                + "                        <xsl:if test=\"current-group()[1]/tipo = 'crear'\">\n"
                + "                            <nombre>\n"
                + "                                <xsl:value-of select=\"current-group()[1]/datos/nombre\"/>\n"
                + "                            </nombre>\n"
                + "                            <xsl:for-each select=\"current-group()/datos/direccion\">\n"
                + "                                <direccion>\n"
                + "                                    <xsl:value-of select=\".\"/>\n"
                + "                                </direccion>\n"
                + "                            </xsl:for-each>\n"
                + "                        </xsl:if>\n"
                + "                    </datos>\n"
                + "                </cambio>\n"
                + "            </xsl:for-each-group>\n"
                + "        </cambios>\n"
                + "    </xsl:template>\n"
                + "</xsl:stylesheet>");
        Tarea splitterCrm1In = p.crearTarea(SPLITTER, "/cambios/cambio");
        Tarea splitterCrm2In = p.crearTarea(SPLITTER, "/cambios/cambio");
        Tarea splitterCrm3In = p.crearTarea(SPLITTER, "/cambios/cambio");
        Tarea enricherCrm1In = p.addTarea(new Enricher() {
            @Override
            protected void enrich(Mensaje mensaje) throws Exception {
                mensaje.setBody(Mensaje.mergeXML(mensaje.getBody(), "<cambio><fuente>CRM1</fuente></cambio>"));
            }
        });
        Tarea enricherCrm2In = p.addTarea(new Enricher() {
            @Override
            protected void enrich(Mensaje mensaje) throws Exception {
                mensaje.setBody(Mensaje.mergeXML(mensaje.getBody(), "<cambio><fuente>CRM2</fuente></cambio>"));
            }
        });
        Tarea enricherCrm3In = p.addTarea(new Enricher() {
            @Override
            protected void enrich(Mensaje mensaje) throws Exception {
                mensaje.setBody(Mensaje.mergeXML(mensaje.getBody(), "<cambio><fuente>CRM3</fuente></cambio>"));
            }
        });
        Tarea merger = p.crearTarea(MERGER);
        Tarea replicator = p.crearTarea(REPLICATOR);
        Tarea filterCrm1Out = p.crearTarea(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM1"));
        Tarea filterCrm2Out = p.crearTarea(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM2"));
        Tarea filterCrm3Out = p.crearTarea(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM3"));
        Tarea translatorCrm1Out = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"2.0\">\n"
                + "    <xsl:template match=\"/cambio\">\n"
                + "        <cambios>\n"
                + "            <xsl:choose>\n"
                + "                <xsl:when test=\"tipo = 'crear'\">\n"
                + "                    <xsl:for-each select=\"datos/direccion\">\n"
                + "                        <cambio>\n"
                + "                            <tipo>crear</tipo>\n"
                + "                            <datos>\n"
                + "                                <dni>\n"
                + "                                    <xsl:value-of select=\"../dni\"/>\n"
                + "                                </dni>\n"
                + "                                <nombre>\n"
                + "                                    <xsl:value-of select=\"../nombre\"/>\n"
                + "                                </nombre>\n"
                + "                                <direccion>\n"
                + "                                    <xsl:value-of select=\".\"/>\n"
                + "                                </direccion>\n"
                + "                            </datos>\n"
                + "                        </cambio>\n"
                + "                    </xsl:for-each>\n"
                + "                </xsl:when>\n"
                + "                <xsl:when test=\"tipo = 'eliminar'\">\n"
                + "                    <cambio>\n"
                + "                        <tipo>eliminar</tipo>\n"
                + "                        <datos>\n"
                + "                            <dni>\n"
                + "                                <xsl:value-of select=\"datos/dni\"/>\n"
                + "                            </dni>\n"
                + "                        </datos>\n"
                + "                    </cambio>\n"
                + "                </xsl:when>\n"
                + "            </xsl:choose>\n"
                + "        </cambios>\n"
                + "    </xsl:template>\n"
                + "</xsl:stylesheet>");
        Tarea splitterCrm1Out = p.crearTarea(SPLITTER, "/cambios/cambio");

        p.encadenar(pCrm1In, translatorCrm1In);
        Tarea debug = p.crearTarea(DEBUG, true);
        p.encadenar(translatorCrm1In, debug);
        p.encadenar(debug, splitterCrm1In);
        //p.encadenar(translatorCrm1In, splitterCrm1In);
        p.encadenar(splitterCrm1In, enricherCrm1In);
        p.encadenar(pCrm2In, splitterCrm2In);
        p.encadenar(splitterCrm2In, enricherCrm2In);
        p.encadenar(pCrm3In, splitterCrm3In);
        p.encadenar(splitterCrm3In, enricherCrm3In);
        p.encadenar(enricherCrm1In, merger);
        p.encadenar(enricherCrm2In, merger);
        p.encadenar(enricherCrm3In, merger);
        p.encadenar(merger, replicator);
        p.encadenar(replicator, filterCrm1Out);
        p.encadenar(replicator, filterCrm2Out);
        p.encadenar(replicator, filterCrm3Out);
        p.encadenar(filterCrm1Out, translatorCrm1Out);
        p.encadenar(translatorCrm1Out, splitterCrm1Out);
        p.encadenar(splitterCrm1Out, pCrm1Out);
        p.encadenar(filterCrm2Out, pCrm2Out);
        p.encadenar(filterCrm3Out, pCrm3Out);

        p.validar();
        p.ejecutar();
        p.esperar();
    }
}
