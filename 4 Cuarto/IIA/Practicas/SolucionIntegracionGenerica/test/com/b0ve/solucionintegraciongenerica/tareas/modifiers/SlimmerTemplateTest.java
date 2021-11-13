/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.modifiers;

import com.b0ve.solucionintegraciongenerica.tasks.modifiers.SlimmerTemplate;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import static com.b0ve.solucionintegraciongenerica.flow.Message.newMessage;

/**
 *
 * @author borja
 */
public class SlimmerTemplateTest {

    @Test
    public void testSlimmerTemplate1() throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {
        Message m1 = newMessage(0, 0, "<pelicula><titulo>Crimen Ferpecto</titulo><precio>10.5</precio></pelicula>");

        SlimmerTemplate slimer = new SlimmerTemplate() {
            @Override
            protected void slim(Message mensaje) {
                Document xml = mensaje.getBody();
                Node precio = xml.getElementsByTagName("precio").item(0);
                Node pelicula = xml.getElementsByTagName("pelicula").item(0);
                pelicula.removeChild(precio);
                mensaje.setBody(xml);
            }
        };
        Buffer in = new Buffer(null);
        slimer.addEntrada(in);
        Buffer out = new Buffer(null);
        slimer.addSalida(out);

        in.push(m1);

        slimer.procesar();

        assertEquals(Message.evaluateXPath(m1.getBody(), "/pelicula/precio").getLength(), 0);
        assertEquals(Message.evaluateXPath(m1.getBody(), "/pelicula/titulo").item(0).getTextContent(), "Crimen Ferpecto");
    }

}
