package matematica;

import org.junit.Test;
import static org.junit.Assert.*;

public class MatematicaTest {
    @Test
    public void testSuma() {
        assertEquals(5, Matematica.suma(2, 3));
    }

    /**
     * Test of resta method, of class Calculadora.
     */
    @Test
    public void testResta() {
        assertEquals(-1, Matematica.resta(2, 3));
    }

    /**
     * Test of multiplicacion method, of class Calculadora.
     */
    @Test
    public void testMultiplicacion() {
        assertEquals(6, Matematica.multiplica(2, 3));
    }

    /**
     * Test of division method, of class Calculadora.
     */
    @Test
    public void testDivision() {
        assertEquals(0, Matematica.divide(2, 3));
    }
}
