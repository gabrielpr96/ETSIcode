/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.modifiers;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import static com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje.newMensaje;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import org.junit.Test;
import static org.junit.Assert.*;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author borja
 */
public class SlimmerTemplateTest {

    @Test
    public void testSlimmerTemplate1() throws ParserConfigurationException, XPathExpressionException, SAXException, IOException {
        Mensaje m1 = newMensaje(0, 0, "<pelicula><titulo>Crimen Ferpecto</titulo><precio>10.5</precio></pelicula>");

        SlimmerTemplate slimer = new SlimmerTemplate() {
            @Override
            protected void slim(Mensaje mensaje) {
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

        assertEquals(Mensaje.evaluateXPath(m1.getBody(), "/pelicula/precio").getLength(), 0);
        assertEquals(Mensaje.evaluateXPath(m1.getBody(), "/pelicula/titulo").item(0).getTextContent(), "Crimen Ferpecto");
    }

}
