/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.modifiers;

import com.b0ve.solucionintegraciongenerica.tasks.modifiers.CorrelationIDSetter;
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
public class CorrelationIDSetterTest {

    @Test
    public void testCorrelationIDSetter1() throws SIGException {
        Message m1 = newMessage(0, 0, "<m>1</m>");
        Message m2 = newMessage(1, 0, "<m>2</m>");
        Message m3 = newMessage(2, 0, "<m>3</m>");
        CorrelationIDSetter cidSetter = new CorrelationIDSetter();
        Buffer in = new Buffer(null);
        cidSetter.addInput(in);
        Buffer out = new Buffer(null);
        cidSetter.addOutput(out);

        in.push(m1);
        in.push(m2);
        in.push(m3);

        cidSetter.process();

        assertEquals(out.retrive().getCorrelationID(), 0);
        assertEquals(out.retrive().getCorrelationID(), 1);
        assertEquals(out.retrive().getCorrelationID(), 2);
    }

}
