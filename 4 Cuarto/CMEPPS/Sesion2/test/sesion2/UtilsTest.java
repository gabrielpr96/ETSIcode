/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sesion2;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author borja
 */
public class UtilsTest {
    
    @BeforeClass
    public static void setUpClass() {
        System.out.println("Una vez por clase");
    }
    
    @AfterClass
    public static void tearDownClass() {
        System.out.println("Cierre una vez por clase");
    }
    
    @Before
    public void setUp() {
        System.out.println("\tUna vez por prueba");
    }
    
    @After
    public void tearDown() {
        System.out.println("\tCierre una vez por prueba");
    }
    
    @Test
    public void testConcatWords() {
        System.out.println("\t\tconcatWords");
        String[] words = new String[]{"Foo", "Bar", "Baz"};
        String expResult = "FooBarBaz";
        String result = Utils.concatWords(words);
        assertEquals(expResult, result);
    }

    @Test
    public void testComputeFactorial() {
        System.out.println("\t\tcomputeFactorial");
        int number = 5;
        String expResult = "120";
        String result = Utils.computeFactorial(number);
        assertEquals(expResult, result);
    }

    @Test
    public void testComputeFactorialGrande() {
        System.out.println("\t\tcomputeFactorial grande");
        int number = 11;
        String expResult = "39916800";
        String result = Utils.computeFactorial(number);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testComputeFactorialNegativo() {
        System.out.println("\t\tcomputeFactorial negativo");
        int number = -5;
        Utils.computeFactorial(number);
    }

    @Test
    public void testNormalizeWord() {
        System.out.println("\t\tnormalizeWord");
        String word = "no termino de entender que hace esta funcion";
        String expResult = "no termino de entender que hace esta funcion";
        String result = Utils.normalizeWord(word);
        assertEquals(expResult, result);
    }
    
}
