package ejercicio3Mejorado;

import com.b0ve.solucionintegraciongenerica.ports.Port;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import static com.b0ve.solucionintegraciongenerica.utils.Process.TASKS.*;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionNotEquals;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import com.b0ve.solucionintegraciongenerica.utils.ProcessAsync;
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
        Process p = new ProcessAsync(false);

        AdaptadorCRM1Entrada crm1In = new AdaptadorCRM1Entrada("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio3\\cambios");
        AdaptadorCRM2Entrada crm2In = new AdaptadorCRM2Entrada();
        AdaptadorCRM3Entrada crm3In = new AdaptadorCRM3Entrada();
        AdaptadorCRM1Salida crm1Out = new AdaptadorCRM1Salida("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio3\\replicados");
        AdaptadorCRM2Salida crm2Out = new AdaptadorCRM2Salida();
        AdaptadorCRM3Salida crm3Out = new AdaptadorCRM3Salida(crm3In);

        Port pCrm1In = p.createPort(crm1In);
        Port pCrm2In = p.createPort(crm2In);
        Port pCrm3In = p.createPort(crm3In);
        Port pCrm1Out = p.createPort(crm1Out);
        Port pCrm2Out = p.createPort(crm2Out);
        Port pCrm3Out = p.createPort(crm3Out);

        Task translatorCrm1In = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"2.0\">\n"
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
        Task splitterCrm1In = p.createTask(SPLITTER, "/cambios/cambio");
        Task splitterCrm2In = p.createTask(SPLITTER, "/cambios/cambio");
        Task splitterCrm3In = p.createTask(SPLITTER, "/cambios/cambio");
        Task enricherCrm1In = p.addTask(new Enricher() {
            @Override
            protected void enrich(Message mensaje) throws Exception {
                mensaje.setBody(Message.mergeXML(mensaje.getBody(), Message.parseXML("<cambio><fuente>CRM1</fuente></cambio>")));
            }
        });
        Task enricherCrm2In = p.addTask(new Enricher() {
            @Override
            protected void enrich(Message mensaje) throws Exception {
                mensaje.setBody(Message.mergeXML(mensaje.getBody(), Message.parseXML("<cambio><fuente>CRM2</fuente></cambio>")));
            }
        });
        Task enricherCrm3In = p.addTask(new Enricher() {
            @Override
            protected void enrich(Message mensaje) throws Exception {
                mensaje.setBody(Message.mergeXML(mensaje.getBody(), Message.parseXML("<cambio><fuente>CRM3</fuente></cambio>")));
            }
        });
        Task merger = p.createTask(MERGER);
        Task replicator = p.createTask(REPLICATOR);
        Task filterCrm1Out = p.createTask(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM1"));
        Task filterCrm2Out = p.createTask(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM2"));
        Task filterCrm3Out = p.createTask(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM3"));
        Task translatorCrm1Out = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"2.0\">\n"
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
        Task splitterCrm1Out = p.createTask(SPLITTER, "/cambios/cambio");

        p.connect(pCrm1In, translatorCrm1In);
        p.connect(translatorCrm1In, splitterCrm1In);
        p.connect(splitterCrm1In, enricherCrm1In);
        p.connect(pCrm2In, splitterCrm2In);
        p.connect(splitterCrm2In, enricherCrm2In);
        p.connect(pCrm3In, splitterCrm3In);
        p.connect(splitterCrm3In, enricherCrm3In);
        p.connect(enricherCrm1In, merger);
        p.connect(enricherCrm2In, merger);
        p.connect(enricherCrm3In, merger);
        p.connect(merger, replicator);
        p.connect(replicator, filterCrm1Out);
        p.connect(replicator, filterCrm2Out);
        p.connect(replicator, filterCrm3Out);
        p.connect(filterCrm1Out, translatorCrm1Out);
        p.connect(translatorCrm1Out, splitterCrm1Out);
        p.connect(splitterCrm1Out, pCrm1Out);
        p.connect(filterCrm2Out, pCrm2Out);
        p.connect(filterCrm3Out, pCrm3Out);

        p.validate();
        p.execute();
        p.waitToEnd();
    }
}
