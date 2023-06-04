package cz.vse.java.sinj04.adventurasem.logika;




import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


/*******************************************************************************
 * Testovací třída BatohTest slouží ke komplexnímu otestování
 * třídy Batoh
 *
 * @author Jiří Šindela
 * @version 2020
 */

public class BatohTest {
    private Hra hra1;

    @BeforeEach
    public void setUp() {
        hra1 = new Hra();
    }

    /**
     * Testování kapacity batohu
     *
     *
     */
    @Test
    public void testKapacita() {
        Batoh batoh = new Batoh();
        assertFalse(batoh.jePlny());
        Vec vec1 = new Vec("1", true, false);
        Vec vec2 = new Vec("2", true, false);
        Vec vec3 = new Vec("3", true, false);
        Vec vec4 = new Vec("4", true, false);
        assertTrue(batoh.vlozVec(vec1));
        assertTrue(batoh.vlozVec(vec2));
        assertTrue(batoh.vlozVec(vec3));
        assertFalse(batoh.vlozVec(vec4));
        assertTrue(batoh.jePlny());
        batoh.odeberVec("3");
        assertFalse(batoh.jePlny());
        assertTrue(batoh.vlozVec(vec4));

    }

    /**
     * Testování vypsaní obsahu batohu
     *
     *
     */
    @Test
    public void testZobrazitObsah() {
        Batoh batoh = new Batoh();
        String text = "Obsah batohu: \nnic";
        assertEquals(text,batoh.zobrazitObsah());
        Vec vec1 = new Vec("1", true, false);
        batoh.vlozVec(vec1);
        String text1 = "Obsah batohu: \n1";
        assertEquals(text1,batoh.zobrazitObsah());
    }

    /**
     * Testování jestli batoh obsahuje určitou věc
     *
     *
     */
    @Test
    public void testObsahujeVec() {
        Batoh batoh = new Batoh();
        assertFalse(batoh.obsahujeVec("1"));
        Vec vec1 = new Vec("1", true, false);
        batoh.vlozVec(vec1);
        assertTrue(batoh.obsahujeVec("1"));
    }
}
