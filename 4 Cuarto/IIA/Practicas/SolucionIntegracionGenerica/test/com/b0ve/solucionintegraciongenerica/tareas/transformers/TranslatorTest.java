/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.tasks.transformers.Translator;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.b0ve.solucionintegraciongenerica.flow.Message.newMessage;

/**
 *
 * @author borja
 */
public class TranslatorTest {

    @Test
    public void testTranslator1() {
        Message m1 = newMessage(0, 0, "<libro><titulo>Robotica Vision y Control</titulo><autor>Peter Corke</autor><precio>70</precio></libro>");
        Translator translator = new Translator("<?xml version=\"1.0\"?>\n"
                + "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n"
                + "<xsl:template match=\"/libro\">\n"
                + "<libro>\n"
                + "<nombre>\n"
                + "<xsl:value-of select=\"autor\"/> - <xsl:value-of select=\"titulo\"/>\n"
                + "</nombre>\n"
                + "<precio>\n"
                + "<xsl:value-of select=\"precio\"/>\n"
                + "</precio>\n"
                + "</libro>\n"
                + "</xsl:template>\n"
                + "</xsl:stylesheet>");
        Buffer in = new Buffer(null);
        translator.addEntrada(in);
        Buffer out = new Buffer(null);
        translator.addSalida(out);

        in.push(m1);

        translator.procesar();

        assertEquals(out.retrive().evaluateXPath("/libro/nombre").item(0).getTextContent(), "Peter Corke - Robotica Vision y Control");
    }

}
