/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.tasks.transformers.Chopper;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.b0ve.solucionintegraciongenerica.flow.Message.newMessage;

/**
 *
 * @author borja
 */
public class ChopperTest {

    @Test
    public void testChopper1() {
        Message m1 = newMessage(0, 0, "<libros>\n"
                + "	<libro>\n"
                + "		<titulo>Robotica Vision y Control</titulo>\n"
                + "		<precio>70</precio>\n"
                + "	</libro>\n"
                + "	<libro>\n"
                + "		<titulo>Interspecies Reviewers</titulo>\n"
                + "		<precio>12.5</precio>\n"
                + "	</libro>\n"
                + "	<libro>\n"
                + "		<titulo>No lunch break</titulo>\n"
                + "		<precio>25</precio>\n"
                + "	</libro>\n"
                + "</libros>");
        Chopper chopper = new Chopper("/libros/libro");
        Buffer in = new Buffer(null);
        chopper.addEntrada(in);
        Buffer out1 = new Buffer(null);
        Buffer out2 = new Buffer(null);
        Buffer out3 = new Buffer(null);
        chopper.addSalida(out1);
        chopper.addSalida(out2);
        chopper.addSalida(out3);

        in.push(m1);

        chopper.procesar();

        assertTrue(out1.retrive().evaluateXPath("/libro").item(0).getTextContent().contains("Robotica Vision y Control"));
        assertTrue(out2.retrive().evaluateXPath("/libro").item(0).getTextContent().contains("Interspecies Reviewers"));
        assertTrue(out3.retrive().evaluateXPath("/libro").item(0).getTextContent().contains("No lunch break"));
    }
    
    @Test
    public void testChopper2() {
        Message m1 = newMessage(0, 0, "<a><b><c>b1c1</c><c>b1c2</c></b><b><c>b2c1</c><c>b2c2</c></b></a>");
        Chopper c1 = new Chopper("/a/b");
        Buffer in = new Buffer(null);
        c1.addEntrada(in);
        Buffer mid1 = new Buffer(null);
        Buffer mid2 = new Buffer(null);
        c1.addSalida(mid1);
        c1.addSalida(mid2);
        Chopper c2a = new Chopper("/b/c");
        Chopper c2b = new Chopper("/b/c");
        c2a.addEntrada(mid1);
        c2b.addEntrada(mid2);
        Buffer out1 = new Buffer(null);
        Buffer out2 = new Buffer(null);
        Buffer out3 = new Buffer(null);
        Buffer out4 = new Buffer(null);
        c2a.addSalida(out1);
        c2a.addSalida(out2);
        c2b.addSalida(out3);
        c2b.addSalida(out4);

        in.push(m1);

        c1.procesar();
        c2a.procesar();
        c2b.procesar();

        assertEquals(out1.retrive().evaluateXPath("/c").item(0).getTextContent(), "b1c1");
        assertEquals(out2.retrive().evaluateXPath("/c").item(0).getTextContent(), "b1c2");
        assertEquals(out3.retrive().evaluateXPath("/c").item(0).getTextContent(), "b2c1");
        assertEquals(out4.retrive().evaluateXPath("/c").item(0).getTextContent(), "b2c2");
    }

}
