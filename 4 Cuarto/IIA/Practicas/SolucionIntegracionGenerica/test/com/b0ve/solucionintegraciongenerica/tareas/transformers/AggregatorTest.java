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

/**
 *
 * @author borja
 */
public class AggregatorTest {

    @Test
    public void testAggregator1() {
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
        splitter.addEntrada(in);
        Buffer mid = new Buffer(null);
        splitter.addSalida(mid);

        Aggregator aggregator = new Aggregator("coleccion");
        aggregator.addEntrada(mid);
        Buffer out = new Buffer(null);
        aggregator.addSalida(out);

        in.push(m1);
        in.push(new Message(m1));

        splitter.procesar();
        mid.retrive();
        aggregator.procesar();

        assertEquals(out.retrive().evaluateXPath("/coleccion/libro").getLength(),3);
        assertTrue(out.empty());
        assertNotNull(mid.retrive());
        assertNotNull(mid.retrive());
        assertNull(mid.retrive());
    }
    
    @Test
    public void testAggregator2() {
        Message m1 = newMessage(0, 0, "<a><b><c>b1c1</c><c>b1c2</c></b><b><c>b2c1</c></b></a>");
        
        Splitter s1 = new Splitter("/a/b");
        Buffer sin = new Buffer(null);
        s1.addEntrada(sin);
        Buffer smid = new Buffer(null);
        s1.addSalida(smid);
        Splitter s2 = new Splitter("/b/c");
        s2.addEntrada(smid);
        Buffer sout = new Buffer(null);
        s2.addSalida(sout);
        
        Aggregator a2 = new Aggregator("b");
        a2.addEntrada(sout);
        Buffer amid = new Buffer(null);
        a2.addSalida(amid);
        Aggregator a1 = new Aggregator("a");
        a1.addEntrada(amid);
        Buffer aout = new Buffer(null);
        a1.addSalida(aout);

        sin.push(m1);
        sin.push(new Message(m1));

        s1.procesar();
        s2.procesar();
        
        Iterator<Message> iter = sout.getIterator();
        iter.next();
        iter.next();
        sout.deleteMessage(iter.next());
        
        a2.procesar();
        a1.procesar();

        assertTrue(aout.retrive().getBodyString().contains("<a><b><c>b1c1</c><c>b1c2</c></b><b><c>b2c1</c></b></a>"));
        assertTrue(aout.empty());
        assertTrue(sout.empty());
        assertTrue(amid.retrive().getBodyString().contains("<b><c>b1c1</c><c>b1c2</c></b>"));
        assertNull(amid.retrive());
    }

}
