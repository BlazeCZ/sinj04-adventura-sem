package cz.vse.java.sinj04.adventurasem.logika;

public class PrikazVypij implements IPrikaz {
    private static final String NAZEV = "vypij";
    private final HerniPlan herniPlan;


    public PrikazVypij(HerniPlan herniPlan) {
        this.herniPlan = herniPlan;
    }

    /**
     * metoda vypije danou vec
     *
     * @parametr jaká věc
     */
    @Override
    public String provedPrikaz(String... parametry) {
        if (parametry.length == 0) {
            // pokud chybí druhé slovo, tak ....
            return "Co mám vypít?";
        }

        if (parametry.length == 1 && parametry[0].equals("elixir")) {
            //pokud je zadaná věc elixir
            if (herniPlan.getBatoh().obsahujeVec("elixir") == true) {
                //pokud máme elixir v batohu
                herniPlan.getBatoh().odeberVec("elixir");
                herniPlan.setElixir(true);
                return "Vypil jsi elixír, cítíš se znatelně silnější.";
            } else {
                return "Elixir nemáš v batohu.";
            }
        } else {
            return "Toto vypít nemůžeš.";
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