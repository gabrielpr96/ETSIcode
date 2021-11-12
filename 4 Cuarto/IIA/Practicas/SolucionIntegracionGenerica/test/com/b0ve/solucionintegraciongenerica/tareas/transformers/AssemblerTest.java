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
    
    @Test
    public void testAssembler2() {
        Mensaje m1 = newMensaje(0, 0, "<a><b><c>b1c1</c><c>b1c2</c></b><b><c>b2c1</c><c>b2c2</c></b></a>");
        Chopper c1 = new Chopper("/a/b");
        Buffer in = new Buffer(null);
        c1.addEntrada(in);
        Buffer cmid1 = new Buffer(null);
        Buffer cmid2 = new Buffer(null);
        c1.addSalida(cmid1);
        c1.addSalida(cmid2);
        Chopper c2a = new Chopper("/b/c");
        Chopper c2b = new Chopper("/b/c");
        c2a.addEntrada(cmid1);
        c2b.addEntrada(cmid2);
        Buffer cout1 = new Buffer(null);
        Buffer cout2 = new Buffer(null);
        Buffer cout3 = new Buffer(null);
        Buffer cout4 = new Buffer(null);
        c2a.addSalida(cout1);
        c2a.addSalida(cout2);
        c2b.addSalida(cout3);
        c2b.addSalida(cout4);

        Assembler a1a = new Assembler("b");
        Assembler a1b = new Assembler("b");
        a1a.addEntrada(cout1);
        a1a.addEntrada(cout2);
        a1b.addEntrada(cout3);
        a1b.addEntrada(cout4);
        Buffer amid1 = new Buffer(null);
        Buffer amid2 = new Buffer(null);
        a1a.addSalida(amid1);
        a1b.addSalida(amid2);
        Assembler a2 = new Assembler("a");
        a2.addEntrada(amid1);
        a2.addEntrada(amid2);
        Buffer aout = new Buffer(null);
        a2.addSalida(aout);

        in.push(m1);
        in.push(new Mensaje(m1));

        c1.procesar();
        c2a.procesar();
        c2b.procesar();
        cout4.retrive();
        a1a.procesar();
        a1b.procesar();
        a2.procesar();

        assertTrue(aout.retrive().getBodyString().contains("<a><b><c>b1c1</c><c>b1c2</c></b><b><c>b2c1</c><c>b2c2</c></b></a>"));
        assertTrue(aout.empty());
        assertTrue(cout1.empty());
        assertTrue(cout2.empty());
        assertFalse(cout3.empty());
        assertTrue(cout4.empty());
        assertFalse(amid1.empty());
        assertTrue(amid2.empty());
    }

}
