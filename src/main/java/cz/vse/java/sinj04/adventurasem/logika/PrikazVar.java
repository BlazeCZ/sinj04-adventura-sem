package cz.vse.java.sinj04.adventurasem.logika;

public class PrikazVar implements IPrikaz {
    private static final String NAZEV = "var";
    private final HerniPlan herniPlan;

    public PrikazVar(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * metoda varí ze zadaných surovin
     *
     * @return
     * @parametry jaké dvě přísady používáme
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Co mám uvařit?";
        }
        if (parametry.length == 1) {
            // pokud chybí druhé slovo, tak ....
            return "Jedna prisada nestaci.";
        }
        if (herniPlan.getAktualniProstor().obsahujeVec("alchymisticky_stul")) {
            //pokud se v prostoru nachází alchymisticky_stul
            if (parametry.length == 2 && (parametry[0].equals("vlastovicnik") || parametry[0].equals("vrani_oko")) && (parametry[1].equals("vlastovicnik") || parametry[1].equals("vrani_oko"))) {
                //jsou zadávány správné přísady
                if (herniPlan.getBatoh().obsahujeVec("vrani_oko") && herniPlan.getBatoh().obsahujeVec("vlastovicnik")) {
                    //máme zadané přísady v batohu
                    herniPlan.getBatoh().odeberVec("vrani_oko");
                    herniPlan.getBatoh().odeberVec("vlastovicnik");
                    herniPlan.getBatoh().vlozVec(herniPlan.getAktualniProstor().vratVec("elixir"));
                    return "Výroba elixíru se povedla a byl přidán do batohu.";
                } else {
                    //nejsou přísady v batohu
                    return "Nemáš příslušné suroviny.";
                }
            } else {
                //špatné použití přísad
                return "Jsi si jistý přísadami?";
            }
        } else {
            //není stul
            return "A kde to chceš uvařit?";
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
