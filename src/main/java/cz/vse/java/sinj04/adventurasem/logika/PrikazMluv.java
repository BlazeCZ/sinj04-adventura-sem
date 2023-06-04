package cz.vse.java.sinj04.adventurasem.logika;

public class PrikazMluv implements IPrikaz {

    private static final String NAZEV = "mluv";
    private final HerniPlan herniPlan;
    public PrikazMluv(HerniPlan herniPlan) {this.herniPlan = herniPlan;}
    /**
     * metoda mluví s určitou postavou
     * @parametr s kým má mluvit
     *
    */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Na koho mam mluvit?";
        }
        else if (parametry.length == 1 && herniPlan.getAktualniProstor().obsahujePostavu(parametry[0])) {
            //pokud se postava nachází v aktuálním prostoru...
            return herniPlan.getAktualniProstor().vratPostavu(parametry[0]).mluv();
        }
        return "Takový člověk tu není.";

    }
    /**
     *  Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     *  @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
