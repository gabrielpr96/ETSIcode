/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.routers;

import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import static com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje.newMensaje;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author borja
 */
public class MergerTest {

    @Test
    public void testMerger1() {
        Mensaje m1 = newMensaje(0, 0, "<cid>0</cid>"),
                m2 = newMensaje(1, 1, "<cid>1</cid>"),
                m3 = newMensaje(2, 2, "<cid>2</cid>");

        Merger merger = new Merger();
        Buffer in1 = new Buffer(null);
        Buffer in2 = new Buffer(null);
        merger.addEntrada(in1);
        merger.addEntrada(in2);
        Buffer out1 = new Buffer(null);
        merger.addSalida(out1);

        in1.push(m1);
        in2.push(m2);
        in1.push(m3);

        merger.procesar();

        assertEquals(out1.retrive().getID(), 0);
        assertEquals(out1.retrive().getID(), 2);
        assertEquals(out1.retrive().getID(), 1);
        assertNull(out1.retrive());
    }

}
