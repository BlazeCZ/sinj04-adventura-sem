package cz.vse.java.sinj04.adventurasem.logika;

public class PrikazBojuj implements IPrikaz {
    private static final String NAZEV = "bojuj";
    private final HerniPlan herniPlan;
    private Hra hra;
    /**
     *
     *Class PrikazBojuj
     *
     */
    public PrikazBojuj(HerniPlan herniPlan, Hra hra) {
        this.herniPlan = herniPlan;
        this.hra = hra;
    }
    /**
     *
     * metoda bojuje s postavou - cílená na draka
     *
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "S kým mám bojovat?";
        }
        if (parametry.length == 1 && (herniPlan.getAktualniProstor().obsahujePostavu("Drak"))){
            if (herniPlan.getSila()==2 && herniPlan.getElixir()==true){
                //pokud jsou předpoklady splněny
                hra.setKonecHry(true);
                return "Díky tvé pilné přípavě jsi dokázal porazit draka. Tímto jsi se stal hrdinou a řečeným drakobijcem.";
            } else {
                // pokud nejsou předpoklady splněny
                hra.setKonecHry(true);
                return "Umíráš při tvém pokusu porazit draka, jelikož jsi nebyl dostatečně připraven.";}

        } else {return "S touto postavou nemůžeš bojovat.";}
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