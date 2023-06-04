package cz.vse.java.sinj04.adventurasem.logika;

public class PrikazUlov implements IPrikaz {

    private static final String NAZEV = "ulov";
    private final HerniPlan herniPlan;

    public PrikazUlov(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * metoda na ulovení zvířete
     *
     * @return
     * @parametr zvíře které chceme ulovit
     */
    @Override
    public String provedPrikaz(String... parametry) {
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();
        Vec vec = aktualniProstor.vratVec(nazevVeci);

        if (parametry.length == 0) {
            // nebyl zadán parametr
            return "Nevim co mam ulovit.";
        }
        if (parametry.length > 1) {
            // bylo zadáno více parametrů
            return "Můžeš ulovit jen jednu věc najednou.";
        }

        int maBatoh = herniPlan.getMaBatoh();

        if (maBatoh == 0) {
            //nemáme batoh
            return "Nemáš kam uložit úlovek.";
        } else if (vec.getZvire() == true) {
            //kontrola místa v batohu
            if (herniPlan.getBatoh().jePlny()==true){return "Nemáš místo na svůj úlovek.";}
            //pokud je daná věc zvíře
            aktualniProstor.odstranVec(nazevVeci);
            herniPlan.getBatoh().vlozVec(vec);
            return "Ulovil jsi zvíře " + nazevVeci + " a uložil jsi ho do batohu.";
        } else return "Lovit můžeš jen živá zvířata.";
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
