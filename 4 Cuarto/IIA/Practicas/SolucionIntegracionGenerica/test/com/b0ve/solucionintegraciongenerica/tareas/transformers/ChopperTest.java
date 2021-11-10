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
public class ChopperTest {

    @Test
    public void testChopper1() {
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

}
