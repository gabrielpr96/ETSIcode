/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.routers;

import com.b0ve.solucionintegraciongenerica.tasks.routers.Replicator;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.b0ve.solucionintegraciongenerica.flow.Message.newMessage;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

/**
 *
 * @author borja
 */
public class ReplicatorTest {

    @Test
    public void testReplicator1() throws SIGException {
        Message m1 = newMessage(0, 0, "<cid>0</cid>");

        Replicator replicator = new Replicator();
        Buffer in1 = new Buffer(null);
        replicator.addInput(in1);
        Buffer out1 = new Buffer(null);
        Buffer out2 = new Buffer(null);
        replicator.addOutput(out1);
        replicator.addOutput(out2);

        in1.push(m1);

        replicator.process();

        assertEquals(out1.retrive().getCorrelationID(), out2.retrive().getCorrelationID());
    }

}
