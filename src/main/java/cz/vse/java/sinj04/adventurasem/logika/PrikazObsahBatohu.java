package cz.vse.java.sinj04.adventurasem.logika;


public class PrikazObsahBatohu implements IPrikaz {

    private static final String NAZEV = "obsahbatohu";
    private final HerniPlan herniPlan;

    public PrikazObsahBatohu(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }


    /**
     * zobrazi predmety v batohu
     * metoda se používá bez parametru
     *
     * @return
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (herniPlan.getMaBatoh() == 0) {
            return "Nemáš batoh.";
        }
        if (parametry.length > 0) {
            return "Zde nepoužíváme parametr.";
        }
        return herniPlan.getBatoh().zobrazitObsah();
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








