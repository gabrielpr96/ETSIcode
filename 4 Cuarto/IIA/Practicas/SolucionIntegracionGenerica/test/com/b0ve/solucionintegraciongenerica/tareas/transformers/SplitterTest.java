/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import static com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje.newMensaje;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author borja
 */
public class SplitterTest {

    @Test
    public void testSplitter1() {
        Mensaje m1 = newMensaje(0, 0, "<libros>\n"
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
        Splitter splitter = new Splitter("/libros/libro");
        Buffer in = new Buffer(null);
        splitter.addEntrada(in);
        Buffer out = new Buffer(null);
        splitter.addSalida(out);

        in.push(m1);

        splitter.procesar();

        assertTrue(out.retrive().evaluateXPath("/libro").item(0).getTextContent().contains("Robotica Vision y Control"));
        assertTrue(out.retrive().evaluateXPath("/libro").item(0).getTextContent().contains("Interspecies Reviewers"));
        assertTrue(out.retrive().evaluateXPath("/libro").item(0).getTextContent().contains("No lunch break"));
    }

    @Test
    public void testSplitter2() {
        Mensaje m1 = newMensaje(0, 0, "<a><b><c>b1c1</c><c>b1c2</c></b><b><c>b2c1</c></b></a>");
        Splitter s1 = new Splitter("/a/b");
        Buffer in = new Buffer(null);
        s1.addEntrada(in);
        Buffer mid = new Buffer(null);
        s1.addSalida(mid);
        Splitter s2 = new Splitter("/b/c");
        s2.addEntrada(mid);
        Buffer out = new Buffer(null);
        s2.addSalida(out);

        in.push(m1);

        s1.procesar();
        s2.procesar();

        assertEquals(out.retrive().evaluateXPath("/c").item(0).getTextContent(), "b1c1");
        assertEquals(out.retrive().evaluateXPath("/c").item(0).getTextContent(), "b1c2");
        assertEquals(out.retrive().evaluateXPath("/c").item(0).getTextContent(), "b2c1");
    }
}
