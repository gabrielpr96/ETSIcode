/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package micalculadora;

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

    @Test
    public void testSuma() {
        System.out.println("suma normal");
        int a = 3;
        int b = 5;
        int expResult = 8;
        int result = Calculadora.suma(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumaLimite() {
        System.out.println("suma limite");
        int a = 2147483647;
        int b = 1;
        int expResult = -2147483648;
        int result = Calculadora.suma(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testSumaNegativa() {
        System.out.println("suma negativa");
        int a = -3;
        int b = -5;
        int expResult = -8;
        int result = Calculadora.suma(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testResta() {
        System.out.println("resta");
        int a = 3;
        int b = -5;
        int expResult = 8;
        int result = Calculadora.resta(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testMultiplicacion() {
        System.out.println("multiplicacion");
        int a = 5;
        int b = -9;
        int expResult = -45;
        int result = Calculadora.multiplica(a, b);
        assertEquals(expResult, result);
    }

    @Test
    public void testDivision() {
        System.out.println("division");
        int a = 15;
        int b = 3;
        int expResult = 5;
        int result = Calculadora.divide(a, b);
        assertEquals(expResult, result);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivisionPorCero() {
        System.out.println("division entre cero");
        int a = 15;
        int b = 0;
        try {
            Calculadora.divide(a, b);
            fail("Deberia haber lanzado una excepcion");
        } catch (Exception e) {
            assertEquals(e.getClass(), ArithmeticException.class);
            assertEquals(e.getMessage(), "Division entre 0");
            throw e;
        }
    }

}
