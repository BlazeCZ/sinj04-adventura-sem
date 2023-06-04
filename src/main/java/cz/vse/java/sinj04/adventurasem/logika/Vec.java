package cz.vse.java.sinj04.adventurasem.logika;

public class Vec {

    private final String nazev;
    private final boolean prenositelna;
    private boolean zvire;


    /**
     * konstruktor veci
     *
     */
    public Vec(String nazev,  boolean prenositelna, boolean zvire) {
        this.nazev = nazev;
        this.prenositelna = prenositelna;
        this.zvire = zvire;

    }
    /**
     * vrati hodnotu nazev
     *
     */
    public String getNazev() {
        return nazev;
    }

    /**
     * vrati hodnotu prenositelna
     *
     */
    public boolean isPrenositelna() {
        return prenositelna;
    }
    /**
     * vrati hodnotu zvire
     *
     */
    public boolean getZvire() {return zvire;}
    /**
     * nastavení hodnoty zvire
     *
     */
    public void setZvire(boolean zver) {zvire = zver;}

}
