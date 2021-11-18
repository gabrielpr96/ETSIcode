/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package calculadora;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author borja
 */
public class CalculadoraTest {
    
    public CalculadoraTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of suma method, of class Calculadora.
     */
    @Test
    public void testSuma() {
        assertEquals(5, Calculadora.suma(2, 3));
    }

    /**
     * Test of resta method, of class Calculadora.
     */
    @Test
    public void testResta() {
        assertEquals(-1, Calculadora.resta(2, 3));
    }

    /**
     * Test of multiplicacion method, of class Calculadora.
     */
    @Test
    public void testMultiplicacion() {
        assertEquals(6, Calculadora.multiplica(2, 3));
    }

    /**
     * Test of division method, of class Calculadora.
     */
    @Test
    public void testDivision() {
        assertEquals(0, Calculadora.divide(2, 3));
    }
    
}
