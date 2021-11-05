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
public class VectorsTest {
    
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
    public void testEqual() {
        System.out.println("\t\tequal");
        int[] a = new int[]{1, 2, 3, 4};
        int[] b = new int[]{1, 2, 3, 4};
        boolean expResult = true;
        boolean result = Vectors.equal(a, b);
        assertEquals(expResult, result);
    }
    @Test
    public void testEqualDistinto() {
        System.out.println("\t\tequal distinto");
        int[] a = new int[]{1, 2, 5, 4};
        int[] b = new int[]{1, 2, 3, 4};
        boolean expResult = false;
        boolean result = Vectors.equal(a, b);
        assertEquals(expResult, result);
    }
    @Test
    public void testEqualDistintaLongitud() {
        System.out.println("\t\tequal distinta longitud");
        int[] a = new int[]{1, 2, 3, 4, 5};
        int[] b = new int[]{1, 2, 3, 4};
        boolean expResult = false;
        boolean result = Vectors.equal(a, b);
        assertEquals(expResult, result);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testEqualParametroInvalido() {
        System.out.println("\t\tequal parametro invalido");
        int[] a = null;
        int[] b = new int[]{1, 2, 3, 4};
        Vectors.equal(a, b);
    }

    @Test
    public void testScalarMultiplication() {
        System.out.println("\t\tscalarMultiplication");
        int[] a = new int[]{2, 4, 5, 6};
        int[] b = new int[]{1, 2, 3, 4};
        int expResult = 49;
        int result = Vectors.scalarMultiplication(a, b);
        assertEquals(expResult, result);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScalarMultiplicationDistintoTamano() {
        System.out.println("\t\tscalarMultiplication distinto tamano");
        int[] a = new int[]{2, 4, 5, 6};
        int[] b = new int[]{2, 4, 5, 6, 7};
        Vectors.scalarMultiplication(a, b);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testScalarMultiplicationParametrosIncorrectos() {
        System.out.println("\t\tscalarMultiplication parametros incorrectos");
        int[] a = new int[]{2, 4, 5, 6};
        int[] b = null;
        Vectors.scalarMultiplication(a, b);
    }
    
}
