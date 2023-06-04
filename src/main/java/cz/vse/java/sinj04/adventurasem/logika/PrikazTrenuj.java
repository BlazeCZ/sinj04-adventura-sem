package cz.vse.java.sinj04.adventurasem.logika;

public class PrikazTrenuj implements IPrikaz {
    private static final String NAZEV = "trenuj";
    private final HerniPlan herniPlan;

    public PrikazTrenuj(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * metoda trénuje se zadanou postavou
     *
     * @return
     * @parametry s kým
     */

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "S kým mám trénovat?";
        } else if (parametry.length == 1 && herniPlan.getAktualniProstor().obsahujePostavu("Veteran")) {
            //pokud se v prosotru nachází veterán(žádná postava se nijak nepohybuje)
            Postava postava = herniPlan.getAktualniProstor().vratPostavu(parametry[0]);
            Batoh batoh = herniPlan.getBatoh();
            if (herniPlan.getSila() == 2) {
                //pokud je již síla na maximum
                return postava.getJmeno() + ": To je vše. " + postava.getPoVymene();
            }
            boolean obsahuje = batoh.obsahujeVec("mec");
            if (herniPlan.getUnaveny() == 0 && obsahuje == true) {
                //hráč není unaven a má u sebe meč
                herniPlan.setSila(herniPlan.getSila() + 1);
                herniPlan.setUnaveny(1);
                if (herniPlan.getSila() == 2) {
                    //pokud je již síla na maximum
                    return postava.getJmeno() + ": To je vše. " + postava.getPoVymene();
                }
                return postava.getJmeno() + ": To by pro dnešek stačilo. " + postava.getRecChce();
            } else if (obsahuje == false) {
                //nemáme meč
                return postava.getJmeno() + ": " + postava.getRecNechce();
            } else {
                return postava.getJmeno() + ": " + postava.getRecChce();
            }


        } else {
            return "S touto postavou nemůžeš trénovat.";
        }


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
