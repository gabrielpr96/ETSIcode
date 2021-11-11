/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.routers;

import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionEquals;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import static com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje.newMensaje;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author borja
 */
public class FilterTest {

    @Test
    public void tetFilter1() {
        Mensaje m1 = newMensaje(0, 0, "<cid>0</cid>"),
                m2 = newMensaje(1, 1, "<cid>1</cid>"),
                m3 = newMensaje(2, 2, "<cid>2</cid>");

        Filter filter = new Filter(new FilterConditionEquals("cid", "1"));
        Buffer in = new Buffer(null);
        filter.addEntrada(in);
        Buffer out = new Buffer(null);
        filter.addSalida(out);

        in.push(m1);
        in.push(m2);
        in.push(m3);

        filter.procesar();

        assertTrue(out.retrive().getID() == 1);
        assertTrue(out.retrive() == null);
    }

    @Test
    public void tetFilter2() {
        Mensaje m1 = newMensaje(0, 0, "<cid>0</cid>"),
                m2 = newMensaje(1, 1, "<cid>1</cid>"),
                m3 = newMensaje(2, 2, "<cid>2</cid>");

        Filter filter = new Filter((mensaje) -> {
            return Integer.parseInt(mensaje.evaluateXPath("/cid").item(0).getTextContent()) >= 1;
        });
        Buffer in = new Buffer(null);
        filter.addEntrada(in);
        Buffer out = new Buffer(null);
        filter.addSalida(out);

        in.push(m1);
        in.push(m2);
        in.push(m3);

        filter.procesar();

        assertEquals(out.retrive().getID(), 1);
        assertEquals(out.retrive().getID(), 2);
        assertNull(out.retrive());
    }

}
