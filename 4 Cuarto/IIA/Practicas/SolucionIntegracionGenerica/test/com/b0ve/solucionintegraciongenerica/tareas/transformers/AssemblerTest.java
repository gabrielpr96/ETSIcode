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
public class AssemblerTest {

    @Test
    public void testAssembler1() {
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
        Chopper chopper = new Chopper("/libros/libro");
        Buffer in = new Buffer(null);
        chopper.addEntrada(in);
        Buffer mid1 = new Buffer(null);
        Buffer mid2 = new Buffer(null);
        Buffer mid3 = new Buffer(null);
        chopper.addSalida(mid1);
        chopper.addSalida(mid2);
        chopper.addSalida(mid3);

        Assembler assembler = new Assembler("coleccion");
        assembler.addEntrada(mid1);
        assembler.addEntrada(mid2);
        assembler.addEntrada(mid3);
        Buffer out = new Buffer(null);
        assembler.addSalida(out);

        in.push(m1);
        in.push(new Mensaje(m1));

        chopper.procesar();
        mid2.retrive();
        assembler.procesar();

        assertEquals(out.retrive().evaluateXPath("/coleccion/libro").getLength(), 3);
        assertTrue(out.empty());
        assertNotNull(mid1.retrive());
        assertNull(mid2.retrive());
        assertNotNull(mid3.retrive());
    }

}
