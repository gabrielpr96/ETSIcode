/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.modifiers;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;

/**
 *
 * @author borja
 */
public class ContextEnricherTest {

    @Test
    public void testContextEnricher1() throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {
        Mensaje m1 = new Mensaje(0, 0, "<pelicula><titulo>Crimen Ferpecto</titulo></pelicula>"),
                m2 = new Mensaje(0, 0, "<pelicula><precio>10.5</precio></pelicula>");

        ContextEnricher enricher = new ContextEnricher();
        Buffer in1 = new Buffer(null);
        Buffer in2 = new Buffer(null);
        enricher.addEntrada(in1);
        enricher.addEntrada(in2);
        Buffer out = new Buffer(null);
        enricher.addSalida(out);

        in1.push(m1);
        in2.push(m2);

        enricher.procesar();

        assertEquals(Mensaje.evaluateXPath(m1.getBody(), "/pelicula").item(0).getTextContent(), "Crimen Ferpecto10.5");
    }

}
