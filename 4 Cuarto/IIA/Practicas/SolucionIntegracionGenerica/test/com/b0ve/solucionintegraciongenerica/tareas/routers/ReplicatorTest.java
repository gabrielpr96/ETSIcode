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
public class ReplicatorTest {

    @Test
    public void testReplicator1() {
        Mensaje m1 = newMensaje(0, 0, "<cid>0</cid>");

        Replicator replicator = new Replicator();
        Buffer in1 = new Buffer(null);
        replicator.addEntrada(in1);
        Buffer out1 = new Buffer(null);
        Buffer out2 = new Buffer(null);
        replicator.addSalida(out1);
        replicator.addSalida(out2);

        in1.push(m1);

        replicator.procesar();

        assertEquals(out1.retrive().getCorrelationID(), out2.retrive().getCorrelationID());
    }

}
