/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tasks.routers;

import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import static com.b0ve.solucionintegraciongenerica.flow.Message.newMessage;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author borja
 */
public class MergerTest {

    @Test
    public void testMerger1() throws SIGException {
        Message m1 = newMessage(0, 0, "<cid>0</cid>");
        Message m2 = newMessage(1, 1, "<cid>1</cid>");
        Message m3 = newMessage(2, 2, "<cid>2</cid>");

        Merger merger = new Merger();
        Buffer in1 = new Buffer(null, null);
        Buffer in2 = new Buffer(null, null);
        merger.addInput(in1);
        merger.addInput(in2);
        Buffer out1 = new Buffer(null, null);
        merger.addOutput(out1);

        in1.push(m1);
        in2.push(m2);
        in1.push(m3);

        merger.process();

        assertEquals(out1.retrive().getID(), 0);
        assertEquals(out1.retrive().getID(), 2);
        assertEquals(out1.retrive().getID(), 1);
        assertNull(out1.retrive());
    }

}
