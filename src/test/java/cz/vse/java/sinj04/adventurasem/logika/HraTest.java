package cz.vse.java.sinj04.adventurasem.logika;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/*******************************************************************************
 * Testovací třída HraTest slouží ke komplexnímu otestování
 * třídy Hra
 *
 * @author    Jarmila Pavlíčková
 * @version  pro školní rok 2016/2017
 */
public class HraTest {
    private Hra hra1;

    //== Datové atributy (statické i instancí)======================================

    //== Konstruktory a tovární metody =============================================
    //-- Testovací třída vystačí s prázdným implicitním konstruktorem ----------

    //== Příprava a úklid přípravku ================================================

    /***************************************************************************
     * Metoda se provede před spuštěním každé testovací metody. Používá se
     * k vytvoření tzv. přípravku (fixture), což jsou datové atributy (objekty),
     * s nimiž budou testovací metody pracovat.
     */
    @BeforeEach
    public void setUp() {
        hra1 = new Hra();
    }

    /***************************************************************************
     * Úklid po testu - tato metoda se spustí po vykonání každé testovací metody.
     */
    @AfterEach
    public void tearDown() {
    }

    //== Soukromé metody používané v testovacích metodách ==========================

    //== Vlastní testovací metody ==================================================

    /***************************************************************************
     * Testuje průběh hry, po zavolání každěho příkazu testuje, zda hra končí
     * a v jaké aktuální místnosti se hráč nachází.
     * Při dalším rozšiřování hry doporučujeme testovat i jaké věci nebo osoby
     * jsou v místnosti a jaké věci jsou v batohu hráče.
     * 
     */
    @Test
    public void testPrubehHryPrikazKonec() {
        assertEquals("domov", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi namesti");
        assertEquals(false, hra1.konecHry());
        assertEquals("namesti", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi les");
        assertEquals(false, hra1.konecHry());
        assertEquals("les", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("konec");
        assertEquals(true, hra1.konecHry());
    }

    @Test
    public void testPrubehHryŠpatnýKonec() {
        assertEquals("domov", hra1.getHerniPlan().getAktualniProstor().getNazev());
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi les");
        hra1.zpracujPrikaz("jdi mytina");
        hra1.zpracujPrikaz("jdi sluj");
        hra1.zpracujPrikaz("bojuj Drak");
        assertEquals(true, hra1.konecHry());
    }

    @Test
    public void testPrubehHryDobrýKonec() {
        assertEquals("domov", hra1.getHerniPlan().getAktualniProstor().getNazev());
        // sebrani batohu
        assertEquals(0,hra1.getHerniPlan().getMaBatoh());
        hra1.zpracujPrikaz("seber batoh");
        assertEquals(1,hra1.getHerniPlan().getMaBatoh());
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi hospoda");
        hra1.zpracujPrikaz("mluv Hospodsky");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi les");
        //uloveni zajice
        hra1.zpracujPrikaz("ulov zajic");
        assertEquals(true,hra1.getHerniPlan().getBatoh().obsahujeVec("zajic"));
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi hospoda");
        //predani Hospodskému
        hra1.zpracujPrikaz("predej Hospodsky");
        assertEquals(false,hra1.getHerniPlan().getBatoh().obsahujeVec("zajic"));
        assertEquals(true,hra1.getHerniPlan().getBatoh().obsahujeVec("mec"));
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi kolbiste");
        hra1.zpracujPrikaz("mluv Veteran");
        //trénování
        hra1.zpracujPrikaz("trenuj Veteran");
        assertEquals(1,hra1.getHerniPlan().getUnaveny());
        assertEquals(1,hra1.getHerniPlan().getSila());
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi domov");
        hra1.zpracujPrikaz("spi");
        assertEquals(0,hra1.getHerniPlan().getUnaveny());
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi kolbiste");
        hra1.zpracujPrikaz("trenuj Veteran");
        assertEquals(2,hra1.getHerniPlan().getSila());
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi les");
        hra1.zpracujPrikaz("jdi mytina");
        //sbírání surovin na elixir
        hra1.zpracujPrikaz("seber vrani_oko");
        assertEquals(true,hra1.getHerniPlan().getBatoh().obsahujeVec("vrani_oko"));
        hra1.zpracujPrikaz("seber vlastovicnik");
        assertEquals(true,hra1.getHerniPlan().getBatoh().obsahujeVec("vlastovicnik"));
        hra1.zpracujPrikaz("jdi les");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi chatrc");
        hra1.zpracujPrikaz("jdi laborator");
        //vareni elixiru
        hra1.zpracujPrikaz("var vlastovicnik vrani_oko");
        assertEquals(false,hra1.getHerniPlan().getBatoh().obsahujeVec("vlastovicnik"));
        assertEquals(false,hra1.getHerniPlan().getBatoh().obsahujeVec("vrani_oko"));
        assertEquals(true,hra1.getHerniPlan().getBatoh().obsahujeVec("elixir"));
        //vypití elixiru
        assertEquals(false,hra1.getHerniPlan().getElixir());
        hra1.zpracujPrikaz("vypij elixir");
        assertEquals(true,hra1.getHerniPlan().getElixir());
        hra1.zpracujPrikaz("jdi chatrc");
        hra1.zpracujPrikaz("jdi namesti");
        hra1.zpracujPrikaz("jdi les");
        hra1.zpracujPrikaz("jdi mytina");
        hra1.zpracujPrikaz("jdi sluj");
        //boj s drakem
        hra1.zpracujPrikaz("bojuj Drak");
        assertEquals(true, hra1.konecHry());
    }

}
