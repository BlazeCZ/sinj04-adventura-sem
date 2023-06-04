package cz.vse.java.sinj04.adventurasem.logika;

public class PrikazPredat implements IPrikaz {

    private static final String NAZEV = "predej";
    private final HerniPlan herniPlan;

    public PrikazPredat(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * metoda předává věci dané postavě
     *
     * @return
     * @parametry komu chceme předávat
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Co by jsi chtěl předat?";
        } else if (parametry.length == 1 && herniPlan.getAktualniProstor().obsahujePostavu(parametry[0])) {
            //zadaná postava se nachází v prosotru
            Postava postava = herniPlan.getAktualniProstor().vratPostavu(parametry[0]);
            Batoh batoh = herniPlan.getBatoh();
            Vec vec = postava.getCoChce();
            Boolean obsahuje = batoh.obsahujeVec(postava.getCoChce().getNazev());
            if (postava.getProbehlaVymena() == true) {
                //pokud již výměna proběhla...
                return postava.getJmeno() + ": " + postava.getPoVymene();
            }
            if (obsahuje == true) {
                //pokud se věcy žádané postavou nacházejí v batohu...
                herniPlan.getBatoh().odeberVec(vec.getNazev());
                herniPlan.getBatoh().vlozVec(postava.getCoMa());
                postava.setProbehlaVymena(true);
                return postava.getJmeno() + ": " + postava.getRecChce();
            } else return postava.getJmeno() + ": " + postava.getRecNechce();
        } else {
            return "Tato postavu tu není.";
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
