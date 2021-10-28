package com.b0ve.solucionintegraciongenerica.test;

import com.b0ve.solucionintegraciongenerica.tareas.modifiers.*;
import com.b0ve.solucionintegraciongenerica.tareas.routers.*;
import com.b0ve.solucionintegraciongenerica.tareas.transformers.*;
import com.b0ve.solucionintegraciongenerica.test.ConsoleColors.Color;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Buffer;
import com.b0ve.solucionintegraciongenerica.utils.condiciones.FilterConditionEquals;
import com.b0ve.solucionintegraciongenerica.utils.flujo.Mensaje;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

public class Tests {

    public static void test() throws Exception {
        System.out.print(Color.BLUE);
        System.out.println("Testing Routers");
        System.out.print(Color.RESET);
        printRes("Testing Correlator (Default): ", test1());
        printRes("Testing Correlator (XPath): ", test2());
        printRes("Testing Distributor: ", test3());
        printRes("Testing Filter (FilterCondition): ", test4());
        printRes("Testing Filter (Custom): ", test5());
        printRes("Testing Merge: ", test6());
        printRes("Testing Replicator: ", test7());
        
        System.out.println();
        System.out.print(Color.BLUE);
        System.out.println("Testing Modifiers");
        System.out.print(Color.RESET);
        printRes("Testing Splimmer: ", test8());
        printRes("Testing ContentSplimmer: ", test9());
        printRes("Testing ContentEnricher: ", test10());
        printRes("Testing CorrelationIDSetter: ", test11());
        
        System.out.println();
        System.out.print(Color.BLUE);
        System.out.println("Testing Transformers");
        System.out.print(Color.RESET);
        printRes("Testing Translator: ", test12());
        printRes("Testing Splitter: ", test13());
        printRes("Testing Aggregator: ", test14());
        printRes("Testing Chopper: ", test15());
        printRes("Testing Assembler: ", test16());
    }
    private static void printRes(String nombre, boolean b){
        System.out.print(nombre);
        if(b){
            System.out.print(Color.GREEN);
            System.out.print("Ok");
            System.out.print(Color.RESET);
        }else{
            System.out.print(Color.RED);
            System.out.print("Fail");
            System.out.print(Color.RESET);
        }
        System.out.println();
    }

    private static boolean test1() throws Exception {
        Mensaje m11 = new Mensaje(0, 0, "11"),
                m12 = new Mensaje(1, 1, "12"),
                m13 = new Mensaje(2, 2, "13"),
                m21 = new Mensaje(3, 2, "21"),
                m22 = new Mensaje(4, 1, "22"),
                m23 = new Mensaje(5, 0, "23");
        Correlator correlator = new Correlator();
        Buffer in1 = new Buffer(null);
        Buffer in2 = new Buffer(null);
        correlator.addEntrada(in1);
        correlator.addEntrada(in2);
        Buffer out1 = new Buffer(null);
        Buffer out2 = new Buffer(null);
        correlator.addSalida(out1);
        correlator.addSalida(out2);
        
        in1.push(m11);
        in1.push(m12);
        in1.push(m13);
        in2.push(m21);
        in2.push(m22);
        in2.push(m23);
        
        correlator.procesar();
        
        return out1.retrive().getCorrelationID() == out2.retrive().getCorrelationID() && out1.retrive().getCorrelationID() == out2.retrive().getCorrelationID() && out1.retrive().getCorrelationID() == out2.retrive().getCorrelationID();
    }
    private static boolean test2() throws Exception {
        Mensaje m11 = new Mensaje(0, 0, "<cid>0</cid>"),
                m12 = new Mensaje(1, 1, "<cid>1</cid>"),
                m13 = new Mensaje(2, 2, "<cid>2</cid>"),
                m21 = new Mensaje(3, 3, "<cid>2</cid>"),
                m22 = new Mensaje(4, 4, "<cid>1</cid>"),
                m23 = new Mensaje(5, 5, "<cid>0</cid>");
        Correlator correlator = new Correlator("/cid");
        Buffer in1 = new Buffer(null);
        Buffer in2 = new Buffer(null);
        correlator.addEntrada(in1);
        correlator.addEntrada(in2);
        Buffer out1 = new Buffer(null);
        Buffer out2 = new Buffer(null);
        correlator.addSalida(out1);
        correlator.addSalida(out2);
        
        in1.push(m11);
        in1.push(m12);
        in1.push(m13);
        in2.push(m21);
        in2.push(m22);
        in2.push(m23);
        
        correlator.procesar();
        
        return out1.retrive().getBody().equals(out2.retrive().getBody()) && out1.retrive().getBody().equals(out2.retrive().getBody()) && out1.retrive().getBody().equals(out2.retrive().getBody());
    }
    private static boolean test3() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "<cid>0</cid>"),
                m2 = new Mensaje(1, 1, "<cid>1</cid>"),
                m3 = new Mensaje(2, 2, "<cid>err</cid>"),
                m4 = new Mensaje(3, 3, "<cid>2</cid>"),
                m5 = new Mensaje(4, 5, "<cid>0</cid>"),
                m6 = new Mensaje(5, 4, "<cid>err</cid>");
        Distributor distributor = new Distributor(new FilterConditionEquals[]{new FilterConditionEquals("/cid", "0"), new FilterConditionEquals("/cid", "1")});
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
        
        return out1.retrive().getID() == 0 &&
               out1.retrive().getID() == 4 &&
               out1.retrive() == null &&
               out2.retrive().getID() == 1 &&
               out2.retrive() == null &&
               out3.retrive().getID() == 2 &&
               out3.retrive().getID() == 3 &&
               out3.retrive().getID() == 5 &&
               out3.retrive() == null;
    }
    private static boolean test4() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "<cid>0</cid>"),
                m2 = new Mensaje(1, 1, "<cid>1</cid>"),
                m3 = new Mensaje(2, 2, "<cid>2</cid>");
        
        Filter filter = new Filter(new FilterConditionEquals("cid", "1"));
        Buffer in = new Buffer(null);
        filter.addEntrada(in);
        Buffer out = new Buffer(null);
        filter.addSalida(out);
        
        in.push(m1);
        in.push(m2);
        in.push(m3);
        
        filter.procesar();
        
        return out.retrive().getID() == 1 &&
               out.retrive() == null;
    }
    private static boolean test5() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "<cid>0</cid>"),
                m2 = new Mensaje(1, 1, "<cid>1</cid>"),
                m3 = new Mensaje(2, 2, "<cid>2</cid>");
        
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
        
        return out.retrive().getID() == 1 &&
               out.retrive().getID() == 2 &&
               out.retrive() == null;
    }
    private static boolean test6() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "<cid>0</cid>"),
                m2 = new Mensaje(1, 1, "<cid>1</cid>"),
                m3 = new Mensaje(2, 2, "<cid>2</cid>");
        
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
        
        return out1.retrive().getID() == 0 &&
               out1.retrive().getID() == 2 &&
               out1.retrive().getID() == 1 &&
               out1.retrive() == null;
    }
    private static boolean test7() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "<cid>0</cid>");
        
        Replicator replicator = new Replicator();
        Buffer in1 = new Buffer(null);
        replicator.addEntrada(in1);
        Buffer out1 = new Buffer(null);
        Buffer out2 = new Buffer(null);
        replicator.addSalida(out1);
        replicator.addSalida(out2);
        
        in1.push(m1);
        
        replicator.procesar();
        
        return out1.retrive().getCorrelationID() == out2.retrive().getCorrelationID();
    }
    private static boolean test8() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "<pelicula><titulo>Crimen Ferpecto</titulo><precio>10.5</precio></pelicula>");
        
        SlimmerTemplate slimer = new SlimmerTemplate(){
            @Override
            protected void slim(Mensaje mensaje) {
                try {
                    Document xml = Mensaje.parseXML(mensaje.getBody());
                    Node precio = xml.getElementsByTagName("precio").item(0);
                    Node pelicula = xml.getElementsByTagName("pelicula").item(0);
                    pelicula.removeChild(precio);
                    mensaje.setBody(Mensaje.serialiceXML(xml));
                } catch (ParserConfigurationException | SAXException | IOException | TransformerException ex) {
                    Logger.getLogger(Tests.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        };
        Buffer in = new Buffer(null);
        slimer.addEntrada(in);
        Buffer out = new Buffer(null);
        slimer.addSalida(out);
        
        in.push(m1);
        //System.out.println(Mensaje.evaluateXPath(m1.getBody(), "/pelicula/precio").item(0).getTextContent());
        
        slimer.procesar();
        
        return Mensaje.evaluateXPath(m1.getBody(), "/pelicula/precio").getLength() == 0 && Mensaje.evaluateXPath(m1.getBody(), "/pelicula/titulo").item(0).getTextContent().equals("Crimen Ferpecto");
    }
    private static boolean test9() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "<pelicula><titulo>Crimen Ferpecto</titulo><precio>10.5</precio></pelicula>"),
                m2 = new Mensaje(0, 0, "<pelicula><titulo>Crimen Ferpecto</titulo><precio>10.5</precio></pelicula>");
        
        ContextSlimmer slimer = new ContextSlimmer();
        Buffer in1 = new Buffer(null);
        Buffer in2 = new Buffer(null);
        slimer.addEntrada(in1);
        slimer.addEntrada(in2);
        Buffer out = new Buffer(null);
        slimer.addSalida(out);
        
        in1.push(m1);
        in2.push(m2);
        
        slimer.procesar();
        
        return false;
    }
    private static boolean test10() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "<pelicula><titulo>Crimen Ferpecto</titulo></pelicula>"),
                m2 = new Mensaje(0, 0, "<pelicula><precio>10.5</precio></pelicula>");
        
        ContextEnricher enricher = new ContextEnricher();
        Buffer in1 = new Buffer(null);
        Buffer in2 = new Buffer(null);
        enricher.addEntrada(in1);
        enricher.addEntrada(in2);
        Buffer out = new Buffer(null);
        enricher.addSalida(out);
        
        in1.push(m1);
        in2.push(m2);
        
        enricher.procesar();
        
        return Mensaje.evaluateXPath(m1.getBody(), "/pelicula").item(0).getTextContent().equals("Crimen Ferpecto10.5");
    }
    private static boolean test11() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "1"),
                m2 = new Mensaje(1, 0, "2"),
                m3 = new Mensaje(2, 0, "3");
        CorrelationIDSetter cidSetter = new CorrelationIDSetter();
        Buffer in = new Buffer(null);
        cidSetter.addEntrada(in);
        Buffer out = new Buffer(null);
        cidSetter.addSalida(out);
        
        in.push(m1);
        in.push(m2);
        in.push(m3);
        
        cidSetter.procesar();
        
        return out.retrive().getCorrelationID() == 0 && out.retrive().getCorrelationID() == 1 && out.retrive().getCorrelationID() == 2;
    }
    private static boolean test12() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "<libro><titulo>Robotica Vision y Control</titulo><autor>Peter Corke</autor><precio>70</precio></libro>");
        Translator translator = new Translator("<?xml version=\"1.0\"?>\n" +
                    "<xsl:stylesheet xmlns:xsl=\"http://www.w3.org/1999/XSL/Transform\" version=\"1.0\">\n" +
                    "<xsl:template match=\"/libro\">\n" +
                    "<libro>\n" +
                    "<nombre>\n" +
                    "<xsl:value-of select=\"autor\"/> - <xsl:value-of select=\"titulo\"/>\n" +
                    "</nombre>\n" +
                    "<precio>\n" +
                    "<xsl:value-of select=\"precio\"/>\n" +
                    "</precio>\n" +
                    "</libro>\n" +
                    "</xsl:template>\n" +
                    "</xsl:stylesheet>");
        Buffer in = new Buffer(null);
        translator.addEntrada(in);
        Buffer out = new Buffer(null);
        translator.addSalida(out);
        
        in.push(m1);
        
        translator.procesar();
        
        return out.retrive().evaluateXPath("/libro/nombre").item(0).getTextContent().equals("Peter Corke - Robotica Vision y Control");
    }
    private static boolean test13() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "<libros>\n" +
            "	<libro>\n" +
            "		<titulo>Robotica Vision y Control</titulo>\n" +
            "		<precio>70</precio>\n" +
            "	</libro>\n" +
            "	<libro>\n" +
            "		<titulo>Interspecies Reviewers</titulo>\n" +
            "		<precio>12.5</precio>\n" +
            "	</libro>\n" +
            "	<libro>\n" +
            "		<titulo>No lunch break</titulo>\n" +
            "		<precio>25</precio>\n" +
            "	</libro>\n" +
            "</libros>");
        Splitter splitter = new Splitter("/libros/libro");
        Buffer in = new Buffer(null);
        splitter.addEntrada(in);
        Buffer out = new Buffer(null);
        splitter.addSalida(out);
        
        in.push(m1);
        
        splitter.procesar();
        
        return out.retrive().evaluateXPath("/libro").item(0).getTextContent().contains("Robotica Vision y Control") && out.retrive().evaluateXPath("/libro").item(0).getTextContent().contains("Interspecies Reviewers") && out.retrive().evaluateXPath("/libro").item(0).getTextContent().contains("No lunch break");
    }
    private static boolean test14() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "<libros>\n" +
            "	<libro>\n" +
            "		<titulo>Robotica Vision y Control</titulo>\n" +
            "		<precio>70</precio>\n" +
            "	</libro>\n" +
            "	<libro>\n" +
            "		<titulo>Interspecies Reviewers</titulo>\n" +
            "		<precio>12.5</precio>\n" +
            "	</libro>\n" +
            "	<libro>\n" +
            "		<titulo>No lunch break</titulo>\n" +
            "		<precio>25</precio>\n" +
            "	</libro>\n" +
            "</libros>");
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
        in.push(new Mensaje(m1));
        
        splitter.procesar();
        mid.retrive();
        aggregator.procesar();
        
        return out.retrive().evaluateXPath("/coleccion/libro").getLength() == 3 && out.empty() && mid.retrive() != null && mid.retrive() != null && mid.retrive() == null;
    }
    private static boolean test15() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "<libros>\n" +
            "	<libro>\n" +
            "		<titulo>Robotica Vision y Control</titulo>\n" +
            "		<precio>70</precio>\n" +
            "	</libro>\n" +
            "	<libro>\n" +
            "		<titulo>Interspecies Reviewers</titulo>\n" +
            "		<precio>12.5</precio>\n" +
            "	</libro>\n" +
            "	<libro>\n" +
            "		<titulo>No lunch break</titulo>\n" +
            "		<precio>25</precio>\n" +
            "	</libro>\n" +
            "</libros>");
        Chopper chopper = new Chopper("/libros/libro");
        Buffer in = new Buffer(null);
        chopper.addEntrada(in);
        Buffer out1 = new Buffer(null);
        Buffer out2 = new Buffer(null);
        Buffer out3 = new Buffer(null);
        chopper.addSalida(out1);
        chopper.addSalida(out2);
        chopper.addSalida(out3);
        
        in.push(m1);
        
        chopper.procesar();
        
        return out1.retrive().evaluateXPath("/libro").item(0).getTextContent().contains("Robotica Vision y Control") && out2.retrive().evaluateXPath("/libro").item(0).getTextContent().contains("Interspecies Reviewers") && out3.retrive().evaluateXPath("/libro").item(0).getTextContent().contains("No lunch break");
    }
    private static boolean test16() throws Exception {
        Mensaje m1 = new Mensaje(0, 0, "<libros>\n" +
            "	<libro>\n" +
            "		<titulo>Robotica Vision y Control</titulo>\n" +
            "		<precio>70</precio>\n" +
            "	</libro>\n" +
            "	<libro>\n" +
            "		<titulo>Interspecies Reviewers</titulo>\n" +
            "		<precio>12.5</precio>\n" +
            "	</libro>\n" +
            "	<libro>\n" +
            "		<titulo>No lunch break</titulo>\n" +
            "		<precio>25</precio>\n" +
            "	</libro>\n" +
            "</libros>");
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
        
        return out.retrive().evaluateXPath("/coleccion/libro").getLength() == 3 && out.empty() && mid1.retrive() != null && mid2.retrive() == null && mid3.retrive() != null;
    }
}
