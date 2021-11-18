package ejercicio3Mejorado;

import com.b0ve.solucionintegraciongenerica.adapters.AdapterSET;
import com.b0ve.solucionintegraciongenerica.ports.Port;
import com.b0ve.solucionintegraciongenerica.tasks.Task;
import com.b0ve.solucionintegraciongenerica.utils.Process;
import static com.b0ve.solucionintegraciongenerica.utils.Process.TASKS.*;
import com.b0ve.solucionintegraciongenerica.utils.ProcessAsync;
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
        Process p = new ProcessAsync(false);

        AdaptadorCRM1Entrada crm1In = new AdaptadorCRM1Entrada("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio3\\cambios");
        AdaptadorCRM2Entrada crm2In = new AdaptadorCRM2Entrada();
        AdaptadorCRM3Entrada crm3In = new AdaptadorCRM3Entrada();
        AdaptadorCRM1Salida crm1Out = new AdaptadorCRM1Salida("C:\\PROYECTOS\\UNI\\IIA\\Simulaciones\\ejercicio3\\replicados");
        AdaptadorCRM2Salida crm2Out = new AdaptadorCRM2Salida();
        AdaptadorCRM3Salida crm3Out = new AdaptadorCRM3Salida();
        AdapterSET mem = new AdapterSET();

        Port pCrm1In = p.createPort(crm1In);
        Port pCrm2In = p.createPort(crm2In);
        Port pCrm3In = p.createPort(crm3In);
        Port pCrm1Out = p.createPort(crm1Out);
        Port pCrm2Out = p.createPort(crm2Out);
        Port pCrm3Out = p.createPort(crm3Out);
        Port pMem = p.createPort(mem);

        Task translatorCrm1In = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"2.0\">\n"
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
        Task translatorCrm2In = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"2.0\">\n"
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
        Task translatorCrm3In = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"2.0\">\n"
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
        Task mergerCore = p.createTask(MERGER);
        Task splitterCore = p.createTask(SPLITTER, "/cambios/cambio");
        Task replicatorMem = p.createTask(REPLICATOR);
        Task translatorMemIn = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                + "    <xsl:template match=\"/cambio\">\n"
                + "        <query>\n"
                + "            <action>\n"
                + "                <xsl:if test=\"tipo = 'crear'\">\n"
                + "                    create\n"
                + "                </xsl:if>\n"
                + "                <xsl:if test=\"tipo = 'eliminar'\">\n"
                + "                    delete\n"
                + "                </xsl:if>\n"
                + "            </action>\n"
                + "            <value>\n"
                + "                <xsl:value-of select=\"datos/dni\"/>\n"
                + "            </value>\n"
                + "        </query>\n"
                + "    </xsl:template>\n"
                + "</xsl:stylesheet>");
        Task translatorMemOut = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                + "    <xsl:template match=\"/response\">\n"
                + "        <cambio>\n"
                + "            <repetido>\n"
                + "                <xsl:value-of select=\".\"/>\n"
                + "            </repetido>\n"
                + "        </cambio>\n"
                + "    </xsl:template>\n"
                + "</xsl:stylesheet>");
        Task correlatorMem = p.createTask(CORRELATOR);
        Task enricherMem = p.createTask(CONTEXT_ENRICHER);
        Task filterCore = p.createTask(FILTER, new FilterConditionEquals("/cambio/repetido", "false"));
        Task replicatorCore = p.createTask(REPLICATOR);
        Task filterCrm1Out = p.createTask(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM1"));
        Task filterCrm2Out = p.createTask(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM2"));
        Task filterCrm3Out = p.createTask(FILTER, new FilterConditionNotEquals("/cambio/fuente", "CRM3"));
        Task translatorCrm1Out = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
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
        Task translatorCrm2Out = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
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
        Task translatorCrm3Out = p.createTask(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
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

        pCrm1In.connect(translatorCrm1In);
        pCrm2In.connect(translatorCrm2In);
        pCrm3In.connect(translatorCrm3In);
        translatorCrm1In.connect(mergerCore);
        translatorCrm2In.connect(mergerCore);
        translatorCrm3In.connect(mergerCore);
        mergerCore.connect(splitterCore);
        splitterCore.connect(replicatorMem);
        replicatorMem.connect(translatorMemIn);
        replicatorMem.connect(correlatorMem);
        translatorMemIn.connect(pMem);
        pMem.connect(translatorMemOut);
        translatorMemOut.connect(correlatorMem);
        correlatorMem.connect(enricherMem);
        correlatorMem.connect(enricherMem);
        enricherMem.connect(filterCore);
        filterCore.connect(replicatorCore);
        replicatorCore.connect(filterCrm1Out);
        replicatorCore.connect(filterCrm2Out);
        replicatorCore.connect(filterCrm3Out);
        filterCrm1Out.connect(translatorCrm1Out);
        filterCrm2Out.connect(translatorCrm2Out);
        filterCrm3Out.connect(translatorCrm3Out);
        translatorCrm1Out.connect(splitterCrm1Out);
        splitterCrm1Out.connect(pCrm1Out);
        translatorCrm2Out.connect(pCrm2Out);
        translatorCrm3Out.connect(pCrm3Out);

        p.validate();
        p.execute();
        p.waitToEnd();
    }
}
