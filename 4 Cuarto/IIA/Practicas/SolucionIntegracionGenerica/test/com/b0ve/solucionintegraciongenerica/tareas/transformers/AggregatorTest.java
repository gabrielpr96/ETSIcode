/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b0ve.solucionintegraciongenerica.tareas.transformers;

import com.b0ve.solucionintegraciongenerica.tasks.transformers.Aggregator;
import com.b0ve.solucionintegraciongenerica.tasks.transformers.Splitter;
import com.b0ve.solucionintegraciongenerica.flow.Buffer;
import com.b0ve.solucionintegraciongenerica.flow.Message;
import java.util.Iterator;
import org.junit.Test;
import static org.junit.Assert.*;
import static com.b0ve.solucionintegraciongenerica.flow.Message.newMessage;
import com.b0ve.solucionintegraciongenerica.utils.exceptions.SIGException;

/**
 *
 * @author borja
 */
public class AggregatorTest {

    @Test
    public void testAggregator1() throws SIGException {
        Message m1 = newMessage(0, 0, "<libros>\n"
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
        Splitter splitter = new Splitter("/libros/libro");
        Buffer in = new Buffer(null);
        splitter.addInput(in);
        Buffer mid = new Buffer(null);
        splitter.addOutput(mid);

        Aggregator aggregator = new Aggregator("coleccion");
        aggregator.addInput(mid);
        Buffer out = new Buffer(null);
        aggregator.addOutput(out);

        in.push(m1);
        in.push(new Message(m1));

        splitter.process();
        mid.retrive();
        aggregator.process();

        assertEquals(out.retrive().evaluateXPath("/coleccion/libro").getLength(),3);
        assertTrue(out.empty());
        assertNotNull(mid.retrive());
        assertNotNull(mid.retrive());
        assertNull(mid.retrive());
    }
    
    @Test
    public void testAggregator2() throws SIGException {
        Message m1 = newMessage(0, 0, "<a><b><c>b1c1</c><c>b1c2</c></b><b><c>b2c1</c></b></a>");
        
        Splitter s1 = new Splitter("/a/b");
        Buffer sin = new Buffer(null);
        s1.addInput(sin);
        Buffer smid = new Buffer(null);
        s1.addOutput(smid);
        Splitter s2 = new Splitter("/b/c");
        s2.addInput(smid);
        Buffer sout = new Buffer(null);
        s2.addOutput(sout);
        
        Aggregator a2 = new Aggregator("b");
        a2.addInput(sout);
        Buffer amid = new Buffer(null);
        a2.addOutput(amid);
        Aggregator a1 = new Aggregator("a");
        a1.addInput(amid);
        Buffer aout = new Buffer(null);
        a1.addOutput(aout);

        sin.push(m1);
        sin.push(new Message(m1));

        s1.process();
        s2.process();
        
        Iterator<Message> iter = sout.getIterator();
        iter.next();
        iter.next();
        sout.deleteMessage(iter.next());
        
        a2.process();
        a1.process();

        assertTrue(aout.retrive().getBodyString().contains("<a><b><c>b1c1</c><c>b1c2</c></b><b><c>b2c1</c></b></a>"));
        assertTrue(aout.empty());
        assertTrue(sout.empty());
        assertTrue(amid.retrive().getBodyString().contains("<b><c>b1c1</c><c>b1c2</c></b>"));
        assertNull(amid.retrive());
    }

}
