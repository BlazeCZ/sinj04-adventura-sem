package cz.vse.java.sinj04.adventurasem.logika;

public class PrikazSpi implements IPrikaz {
    private static final String NAZEV = "spi";
    private HerniPlan plan;


    public PrikazSpi(HerniPlan plan) {
        this.plan = plan;
    }
    /**
     * metoda na vyspání se
     * nezávislá na parametru, jen na prostoru
     * @return
     *
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length > 0) {
            // pokud byl zadán s parametrem
            return "Zde nepoužíváme parametr.";
        }
        if (parametry.length == 0 && plan.getAktualniProstor().getNazev() == "domov") {
            //pokud se nacházíme v prosotru "domov"...
            plan.setUnaveny(0);
            return ("Vyspal jsi se.");
        }
        return ("Zde spát nemůžeš.");
    }

    /**
     * Metoda vrací název příkazu (slovo které používá hráč pro jeho vyvolání)
     *
     * @ return nazev prikazu
     */
    @Override
    public String getNazev() {
        return NAZEV;
    }
}
