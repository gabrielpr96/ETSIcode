/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.routers;

import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionEquals;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author borja
 */
public class DistributorTest {

    @Test
    public void testDistributor1() {
        Distributor distributor = new Distributor(new FilterConditionEquals[]{new FilterConditionEquals("/cid", "0"), new FilterConditionEquals("/cid", "1")});

        Mensaje m1 = new Mensaje(0, 0, "<cid>0</cid>"),
                m2 = new Mensaje(1, 1, "<cid>1</cid>"),
                m3 = new Mensaje(2, 2, "<cid>err</cid>"),
                m4 = new Mensaje(3, 3, "<cid>2</cid>"),
                m5 = new Mensaje(4, 5, "<cid>0</cid>"),
                m6 = new Mensaje(5, 4, "<cid>err</cid>");

        Buffer in1 = new Buffer(null);
        distributor.addEntrada(in1);
        Buffer out1 = new Buffer(null);
        Buffer out2 = new Buffer(null);
        Buffer out3 = new Buffer(null);
        distributor.addSalida(out1);
        distributor.addSalida(out2);
        distributor.addSalida(out3);

        in1.push(m1);
        in1.push(m2);
        in1.push(m3);
        in1.push(m4);
        in1.push(m5);
        in1.push(m6);

        distributor.procesar();

        assertEquals(out1.retrive().getID(), 0);
        assertEquals(out1.retrive().getID(), 4);
        assertNull(out1.retrive());
        assertEquals(out2.retrive().getID(), 1);
        assertNull(out2.retrive());
        assertEquals(out3.retrive().getID(), 2);
        assertEquals(out3.retrive().getID(), 3);
        assertEquals(out3.retrive().getID(), 5);
        assertNull(out3.retrive());
    }

}
