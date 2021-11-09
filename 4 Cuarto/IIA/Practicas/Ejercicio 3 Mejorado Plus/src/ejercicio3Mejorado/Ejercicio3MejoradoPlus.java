package ejercicio3Mejorado;

import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import com.b0ve.solucionintegraciongenerica.utils.Proceso;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.*;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionEquals;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionNotEquals;
import ejercicio3.AdaptadorCRM1Entrada;
import ejercicio3.AdaptadorCRM1Salida;
import ejercicio3.AdaptadorCRM2Entrada;
import ejercicio3.AdaptadorCRM2Salida;
import ejercicio3.AdaptadorCRM3Entrada;

public class Ejercicio3MejoradoPlus {
//<cambios><cambio><tipo>eliminar</tipo><datos><dni>40144663C</dni></datos></cambio></cambios>
//<cambios><cambio><tipo>crear</tipo><datos><dni>40144664S</dni><nombre>Manolo</nombre><direccion>Alan Turing 2</direccion></datos></cambio></cambios>
//Crear el mismo que el de la BD
//<cambios><cambio><tipo>crear</tipo><datos><dni>40144662V</dni><nombre>Carlos</nombre><direccion>Avenida Guatemala</direccion><direccion>Perez Quintero</direccion></datos></cambio></cambios>

    public static void main(String[] args) throws Exception {
        Proceso p = new Proceso(false);

        AdaptadorCRM1Entrada crm1In = new AdaptadorCRM1Entrada("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio3\\cambios");
        AdaptadorCRM2Entrada crm2In = new AdaptadorCRM2Entrada();
        AdaptadorCRM3Entrada crm3In = new AdaptadorCRM3Entrada();
        AdaptadorCRM1Salida crm1Out = new AdaptadorCRM1Salida("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio3\\replicados");
        AdaptadorCRM2Salida crm2Out = new AdaptadorCRM2Salida();
        AdaptadorCRM3Salida crm3Out = new AdaptadorCRM3Salida();
        AdaptadorSET mem = new AdaptadorSET();

        Puerto pCrm1In = p.crearPuerto(crm1In);
        Puerto pCrm2In = p.crearPuerto(crm2In);
        Puerto pCrm3In = p.crearPuerto(crm3In);
        Puerto pCrm1Out = p.crearPuerto(crm1Out);
        Puerto pCrm2Out = p.crearPuerto(crm2Out);
        Puerto pCrm3Out = p.crearPuerto(crm3Out);
        Puerto pMem = p.crearPuerto(mem);

        Tarea translatorCrm1In = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"2.0\">\n"
                + "    <xsl:template match=\"/cambios\">\n"
                + "        <cambios>\n"
                + "            <xsl:for-each-group select=\"cambio\" group-by=\"datos/dni\">\n"
                + "                <cambio>\n"
                + "                    <tipo>\n"
                + "                        <xsl:value-of select=\"current-group()[1]/tipo\"/>\n"
                + "                    </tipo>\n"
                + "                    <fuente>CRM1</fuente>\n"
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
        Tarea translatorCrm2In = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"2.0\">\n"
                + "    <xsl:template match=\"/cambios\">\n"
                + "        <cambios>\n"
                + "            <xsl:for-each select=\"cambio\">\n"
                + "                <cambio>\n"
                + "                    <tipo>\n"
                + "                        <xsl:value-of select=\"tipo\"/>\n"
                + "                    </tipo>\n"
                + "                    <fuente>CRM2</fuente>\n"
                + "                    <datos>\n"
                + "                        <dni>\n"
                + "                            <xsl:value-of select=\"datos/dni\"/>\n"
                + "                        </dni>\n"
                + "                        <xsl:if test=\"tipo = 'crear'\">\n"
                + "                            <nombre>\n"
                + "                                <xsl:value-of select=\"datos/nombre\"/>\n"
                + "                            </nombre>\n"
                + "                            <xsl:for-each select=\"datos/direccion\">\n"
                + "                                <direccion>\n"
                + "                                    <xsl:value-of select=\".\"/>\n"
                + "                                </direccion>\n"
                + "                            </xsl:for-each>\n"
                + "                        </xsl:if>\n"
                + "                    </datos>\n"
                + "                </cambio>\n"
                + "            </xsl:for-each>\n"
                + "        </cambios>\n"
                + "    </xsl:template>\n"
                + "</xsl:stylesheet>");
        Tarea translatorCrm3In = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"2.0\">\n"
                + "    <xsl:template match=\"/cambios\">\n"
                + "        <cambios>\n"
                + "            <xsl:for-each select=\"cambio\">\n"
                + "                <cambio>\n"
                + "                    <tipo>\n"
                + "                        <xsl:value-of select=\"tipo\"/>\n"
                + "                    </tipo>\n"
                + "                    <fuente>CRM3</fuente>\n"
                + "                    <datos>\n"
                + "                        <dni>\n"
                + "                            <xsl:value-of select=\"datos/dni\"/>\n"
                + "                        </dni>\n"
                + "                        <xsl:if test=\"tipo = 'crear'\">\n"
                + "                            <nombre>\n"
                + "                                <xsl:value-of select=\"datos/nombre\"/>\n"
                + "                            </nombre>\n"
                + "                            <xsl:for-each select=\"datos/direccion\">\n"
                + "                                <direccion>\n"
                + "                                    <xsl:value-of select=\".\"/>\n"
                + "                                </direccion>\n"
                + "                            </xsl:for-each>\n"
                + "                        </xsl:if>\n"
                + "                    </datos>\n"
                + "                </cambio>\n"
                + "            </xsl:for-each>\n"
                + "        </cambios>\n"
                + "    </xsl:template>\n"
                + "</xsl:stylesheet>");
        Tarea mergerCore = p.crearTarea(MERGER);
        Tarea splitterCore = p.crearTarea(SPLITTER, "/cambios/cambio");
        Tarea replicatorMem = p.crearTarea(REPLICATOR);
        Tarea translatorMemIn = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                + "    <xsl:template match=\"/cambio\">\n"
                + "        <consulta>\n"
                + "            <accion>\n"
                + "            <xsl:value-of select=\"tipo\"/>\n"
                + "        </accion>\n"
                + "        <valor>\n"
                + "            <xsl:value-of select=\"datos/dni\"/>\n"
                + "        </valor>\n"
                + "        </consulta>\n"
                + "    </xsl:template>\n"
                + "</xsl:stylesheet>");
        Tarea translatorMemOut = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                + "    <xsl:template match=\"/contenido\">\n"
                + "        <cambio>\n"
                + "            <repetido>\n"
                + "                <xsl:value-of select=\".\"/>\n"
                + "            </repetido>\n"
                + "        </cambio>\n"
                + "    </xsl:template>\n"
                + "</xsl:stylesheet>");
        Tarea correlatorMem = p.crearTarea(CORRELATOR);
        Tarea enricherMem = p.crearTarea(CONTEXT_ENRICHER);
        Tarea filterCore = p.crearTarea(FILTER, new FilterConditionEquals("/cambio/repetido", "false"));
        Tarea replicatorCore = p.crearTarea(REPLICATOR);
        Tarea filterCrm1Out = p.crearTarea(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM1"));
        Tarea filterCrm2Out = p.crearTarea(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM2"));
        Tarea filterCrm3Out = p.crearTarea(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM3"));
        Tarea translatorCrm1Out = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
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
        Tarea translatorCrm2Out = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                + "    <xsl:template match=\"/cambio\">\n"
                + "        <cambio>\n"
                + "            <tipo><xsl:value-of select=\"tipo\"/></tipo>\n"
                + "            <datos>\n"
                + "                <dni>\n"
                + "                    <xsl:value-of select=\"datos/dni\"/>\n"
                + "                </dni>\n"
                + "                <xsl:if test=\"tipo = 'crear'\">\n"
                + "                    <nombre>\n"
                + "                        <xsl:value-of select=\"datos/nombre\"/>\n"
                + "                    </nombre>\n"
                + "                    <xsl:for-each select=\"datos/direccion\">\n"
                + "                        <direccion>\n"
                + "                            <xsl:value-of select=\".\"/>\n"
                + "                        </direccion>\n"
                + "                    </xsl:for-each>\n"
                + "                </xsl:if>\n"
                + "            </datos>\n"
                + "        </cambio>\n"
                + "    </xsl:template>\n"
                + "</xsl:stylesheet>");
        Tarea translatorCrm3Out = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                + "    <xsl:template match=\"/cambio\">\n"
                + "        <sql>\n"
                + "            <xsl:choose>\n"
                + "                <xsl:when test=\"tipo = 'crear'\">\n"
                + "                    INSERT INTO `clientes` (`DNI`, `Nombre`) VALUES ('<xsl:value-of select=\"datos/dni\"/>', '<xsl:value-of select=\"datos/nombre\"/>');\n"
                + "                    <xsl:for-each select=\"datos/direccion\">\n"
                + "                        INSERT INTO `direcciones` (`Cliente`, `Direccion`) VALUES ('<xsl:value-of select=\"../dni\"/>', '<xsl:value-of select=\".\"/>');\n"
                + "                    </xsl:for-each>\n"
                + "                </xsl:when>\n"
                + "                <xsl:when test=\"tipo = 'eliminar'\">\n"
                + "                    DELETE FROM `clientes` WHERE `DNI` = '<xsl:value-of select=\"datos/dni\"/>';\n"
                + "                </xsl:when>\n"
                + "            </xsl:choose>\n"
                + "        </sql>\n"
                + "    </xsl:template>\n"
                + "</xsl:stylesheet>");

        pCrm1In.encadenar(translatorCrm1In);
        pCrm2In.encadenar(translatorCrm2In);
        pCrm3In.encadenar(translatorCrm3In);
        translatorCrm1In.encadenar(mergerCore);
        translatorCrm2In.encadenar(mergerCore);
        translatorCrm3In.encadenar(mergerCore);
        mergerCore.encadenar(splitterCore);
        splitterCore.encadenar(replicatorMem);
        replicatorMem.encadenar(translatorMemIn);
        replicatorMem.encadenar(correlatorMem);
        translatorMemIn.encadenar(pMem);
        pMem.encadenar(translatorMemOut);
        translatorMemOut.encadenar(correlatorMem);

        correlatorMem.encadenar(enricherMem);
        correlatorMem.encadenar(enricherMem);

        enricherMem.encadenar(filterCore);

        filterCore.encadenar(replicatorCore);
        replicatorCore.encadenar(filterCrm1Out);
        replicatorCore.encadenar(filterCrm2Out);
        replicatorCore.encadenar(filterCrm3Out);
        filterCrm1Out.encadenar(translatorCrm1Out);
        filterCrm2Out.encadenar(translatorCrm2Out);
        filterCrm3Out.encadenar(translatorCrm3Out);
        translatorCrm1Out.encadenar(splitterCrm1Out);
        splitterCrm1Out.encadenar(pCrm1Out);
        translatorCrm2Out.encadenar(pCrm2Out);
        translatorCrm3Out.encadenar(pCrm3Out);

        p.validar();
        p.ejecutar();
        p.esperar();
    }
}
