package cz.vse.java.sinj04.adventurasem.logika;


public class PrikazSeber implements IPrikaz {

    private static final String NAZEV = "seber";
    private final HerniPlan herniPlan;


    public PrikazSeber(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;

    }
    /**
     * metoda sbírá věci, které sa nacházejí v aktuálním prostoru
     *
     * @return
     * @parametry co chceme sbírat
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            return "Nevim co mam sebrat.";
        }
        if (parametry.length > 1) {
            //je zadáno víc paramterů
            return "Můžeš sebrat jen jednu věc najednou.";
        }
        int maBatoh = herniPlan.getMaBatoh();
        String nazevVeci = parametry[0];
        Prostor aktualniProstor = herniPlan.getAktualniProstor();

        if (maBatoh == 0 && nazevVeci.equals("batoh") && aktualniProstor.obsahujeVec(nazevVeci)) {
            //pokud sbíráme batoh
            aktualniProstor.odstranVec(nazevVeci);
            herniPlan.setMaBatoh(1);
            return "Nasadil jsi si batoh.";
        } else if (maBatoh == 0) {
            //pokud se snažíme něco sebrat, ale nemáme batoh
            return "Nemáš batoh, nemůžeš přenášet věci.";
        }


        if (!aktualniProstor.obsahujeVec(nazevVeci) || nazevVeci.equals("elixir")) {
            return "Vec s nazvem " + nazevVeci + " v prostoru neexistuje";
        } else {
            if (herniPlan.getBatoh().jePlny()==true){return "Už toho víc neuneseš.";}
            Vec vec = aktualniProstor.vratVec(nazevVeci);
            if (vec.isPrenositelna()) {
                if (vec.getZvire()) {
                    //pokud je věc zvíře...
                    return "Zviře musíš ulovit.";
                }
                //pokud je věc přenositelné
                aktualniProstor.odstranVec(nazevVeci);
                herniPlan.getBatoh().vlozVec(vec);
                //vlozeni do batohu sem
                return "Sebral jsi vec " + nazevVeci;
            } else {

                    return "Tuto vec neni mozne prenest";
                }
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
