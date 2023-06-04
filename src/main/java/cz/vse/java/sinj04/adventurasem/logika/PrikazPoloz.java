package cz.vse.java.sinj04.adventurasem.logika;

public class PrikazPoloz implements IPrikaz {
    private static final String NAZEV = "poloz";
    private final HerniPlan herniPlan;

    public PrikazPoloz(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // nebyl zadán parametr
            return "Co mám položit.";
        }
        if (parametry.length > 1) {
            // bylo zadáno více parametrů
            return "Můžeš polozit jen jednu věc najednou.";
        }
        if (herniPlan.getMaBatoh()==0){return "Nemáš batoh co by jsi chtěl pokládat.";}
        if (herniPlan.getBatoh().obsahujeVec(parametry[0])){
            Vec vec = herniPlan.getBatoh().odeberVec(parametry[0]);
            if (vec.getZvire()){
                vec.setZvire(false);
            }
            herniPlan.getAktualniProstor().pridejVec(vec);
            return "Polozil jsi vec na zem.";
        }
        else {return "Takovou věc u sebe nemáš.";}
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
