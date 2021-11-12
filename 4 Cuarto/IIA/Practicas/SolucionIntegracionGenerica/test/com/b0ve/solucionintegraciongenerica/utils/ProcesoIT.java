/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.utils;

import com.b0ve.solucionintegraciongenerica.adaptadores.Adaptador;
import com.b0ve.solucionintegraciongenerica.puertos.Puerto;
import com.b0ve.solucionintegraciongenerica.tareas.Tarea;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.CONTEXT_ENRICHER;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.CORRELATOR;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.FILTER;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.REPLICATOR;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.SPLITTER;
import static com.b0ve.solucionintegraciongenerica.utils.Proceso.TipoTarea.TRANSLATOR;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionNotEquals;
import com.b0ve.solucionintegraciongenerica.utils.excepciones.ConfigurationException;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

/**
 *
 * @author borja
 */
public class ProcesoIT {

    @Test
    public void testProcesoEjercicio1() {
        Proceso p = new Proceso();

        ArrayList<String> correosEnviados = new ArrayList<>();
        ArrayList<String> smssEnviados = new ArrayList<>();

        Adaptador aGMS = new Adaptador() {
            @Override
            public void enviarApp(Mensaje m) {
            }
            @Override
            public void iniciar() {
                try {
                    enviarPuerto(new Mensaje("<acta>\n"
                            + "    <asignatura>PVWB</asignatura>\n"
                            + "    <alumnos>\n"
                            + "        <alumno>\n"
                            + "            <id>borja.lopez248</id>\n"
                            + "            <nota>2</nota>\n"
                            + "        </alumno>\n"
                            + "        <alumno>\n"
                            + "            <id>spit.fire144</id>\n"
                            + "            <nota>10</nota>\n"
                            + "        </alumno>\n"
                            + "    </alumnos>\n"
                            + "</acta>"));
                } catch (ParserConfigurationException | SAXException | IOException ex) {
                    Logger.getLogger(ProcesoIT.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        Adaptador aMySQL = new Adaptador() {
            @Override
            public void enviarApp(Mensaje m) {
                try {
                    Document doc;
                    if (m.evaluateXPathString("/sql").contains("borja.lopez248")) {
                        doc = Mensaje.parseXML("<Results><Row><Email>email1@example.org</Email><Telefono>telf1</Telefono></Row></Results>");
                    } else {
                        doc = Mensaje.parseXML("<Results><Row><Email>email2@example.org</Email><Telefono>null</Telefono></Row></Results>");
                    }
                    Mensaje r = new Mensaje(doc);
                    r.setCorrelationID(m.getCorrelationID());
                    enviarPuerto(r);
                } catch (ParserConfigurationException | SAXException | IOException ex) {
                    Logger.getLogger(ProcesoIT.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        Adaptador aMail = new Adaptador() {
            @Override
            public void enviarApp(Mensaje m) {
                correosEnviados.add(m.evaluateXPathString("/alumno/email"));
            }
        };
        Adaptador aSMS = new Adaptador() {
            @Override
            public void enviarApp(Mensaje m) {
                smssEnviados.add(m.evaluateXPathString("/alumno/telefono"));
            }
        };

        Puerto pGMS = p.crearPuerto(aGMS);
        Puerto pMySQL = p.crearPuerto(aMySQL);
        Puerto pMail = p.crearPuerto(aMail);
        Puerto pSMS = p.crearPuerto(aSMS);

        Tarea translator1 = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"2.0\">\n"
                + "    <xsl:template match=\"/acta\">\n"
                + "        <alumnos>\n"
                + "            <xsl:for-each select=\"alumnos/alumno\">\n"
                + "                <alumno>\n"
                + "                    <asignatura>\n"
                + "                        <xsl:value-of select=\"/acta/asignatura\"/>\n"
                + "                    </asignatura>\n"
                + "                    <id>\n"
                + "                        <xsl:value-of select=\"id\"/>\n"
                + "                    </id>\n"
                + "                    <nota>\n"
                + "                        <xsl:value-of select=\"nota\"/>\n"
                + "                    </nota>\n"
                + "                </alumno>\n"
                + "            </xsl:for-each>\n"
                + "        </alumnos>\n"
                + "    </xsl:template>\n"
                + "</xsl:stylesheet>");
        Tarea splitter = p.crearTarea(SPLITTER, "/alumnos/alumno");
        Tarea replicator1 = p.crearTarea(REPLICATOR);
        Tarea translator2 = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                + "    <xsl:template match=\"/alumno\">\n"
                + "        <sql>\n"
                + "            SELECT `Email`, `Telefono` FROM `alumnos` WHERE `ID` = '<xsl:value-of select=\"id\"/>'\n"
                + "        </sql>\n"
                + "    </xsl:template>\n"
                + "</xsl:stylesheet>");
        Tarea translator3 = p.crearTarea(TRANSLATOR, "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                + "    <xsl:template match=\"/Results/Row\">\n"
                + "        <alumno>\n"
                + "            <email><xsl:value-of select=\"Email\"/></email>\n"
                + "            <telefono><xsl:value-of select=\"Telefono\"/></telefono>\n"
                + "        </alumno>\n"
                + "    </xsl:template>\n"
                + "</xsl:stylesheet>");
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

        try {
            p.validar();
        } catch (ConfigurationException ex) {
            Logger.getLogger(ProcesoIT.class.getName()).log(Level.SEVERE, null, ex);
            fail("Proceso invalido");
        }
        p.ejecutar();
        try {
            Thread.sleep(5000);
            p.terminar();
            p.esperar();
        } catch (InterruptedException ex) {
            Logger.getLogger(ProcesoIT.class.getName()).log(Level.SEVERE, null, ex);
            fail("Problema esperando a que termine el proceso");
        }
        
        assertTrue(correosEnviados.contains("email1@example.org"));
        assertTrue(correosEnviados.contains("email2@example.org"));
        assertTrue(smssEnviados.contains("telf1"));
        assertFalse(smssEnviados.contains("null"));
    }
    
}
