/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.modifiers;

import com.b0ve.solucionintegraciongenerica.tasks.modifiers.ContextEnricher;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.xml.sax.SAXException;
import static com.b0ve.solucionintegraciongenerica.flow.Message.newMessage;

/**
 *
 * @author borja
 */
public class ContextEnricherTest {

    @Test
    public void testContextEnricher1() throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {
        Message m1 = newMessage(0, 0, "<pelicula><titulo>Crimen Ferpecto</titulo></pelicula>");
        Message m2 = newMessage(0, 0, "<pelicula><precio>10.5</precio></pelicula>");

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

        assertEquals(Message.evaluateXPath(m1.getBody(), "/pelicula").item(0).getTextContent(), "Crimen Ferpecto10.5");
    }

}
