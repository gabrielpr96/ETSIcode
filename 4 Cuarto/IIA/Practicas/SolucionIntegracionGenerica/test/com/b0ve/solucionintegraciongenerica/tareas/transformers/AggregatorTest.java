/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author borja
 */
public class AggregatorTest {

    @Test
    public void testAggregator1() {
        Mensaje m1 = new Mensaje(0, 0, "<libros>\n"
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
        Buffer mid = new Buffer(null);
        splitter.addSalida(mid);

        Aggregator aggregator = new Aggregator("coleccion");
        aggregator.addEntrada(mid);
        Buffer out = new Buffer(null);
        aggregator.addSalida(out);

        in.push(m1);
        in.push(new Mensaje(m1));

        splitter.procesar();
        mid.retrive();
        aggregator.procesar();

        assertEquals(out.retrive().evaluateXPath("/coleccion/libro").getLength(),3);
        assertTrue(out.empty());
        assertNotNull(mid.retrive());
        assertNotNull(mid.retrive());
        assertNull(mid.retrive());
    }

}
