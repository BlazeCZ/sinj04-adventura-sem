package cz.vse.java.sinj04.adventurasem.logika;


import cz.vse.java.sinj04.adventurasem.main.Observer;
import cz.vse.java.sinj04.adventurasem.main.Subject;

import java.util.HashSet;
import java.util.Set;

/**
 * Class HerniPlan - třída představující mapu a stav adventury.
 * <p>
 * Tato třída inicializuje prvky ze kterých se hra skládá:
 * vytváří všechny prostory,
 * propojuje je vzájemně pomocí východů
 * a pamatuje si aktuální prostor, ve kterém se hráč právě nachází.
 *
 * @author Michael Kolling, Lubos Pavlicek, Jarmila Pavlickova
 * @version pro školní rok 2016/2017
 */
public class HerniPlan implements Subject {
    private int maBatoh = 0;
    private int unaveny = 0;
    private int sila = 0;
    private boolean elixir = false;
    private Prostor aktualniProstor;
    private Batoh batoh = new Batoh();

    private Set<Observer> seznamPozorovatelu;

    /**
     * Konstruktor který vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví halu.
     */
    public HerniPlan() {
        zalozProstoryHry();
        seznamPozorovatelu = new HashSet<>();
    }

    /**
     * Vytváří jednotlivé prostory a propojuje je pomocí východů.
     * Jako výchozí aktuální prostor nastaví domeček.
     */
    private void zalozProstoryHry() {
        // vytvářejí se jednotlivé prostory
        Prostor hospoda = new Prostor("hospoda", "vesnická hodpoda, kde se nachází hospodský.");
        Prostor kolbiste = new Prostor("kolbiste", "tréninkové kolbiště, je zde veterán.");
        Prostor domov = new Prostor("domov", "tvůj domov.");
        Prostor namesti = new Prostor("namesti", "náměstí vesnice.");
        Prostor chatrc = new Prostor("chatrc", "chatrč, ve které žije bylinářka.");
        Prostor laborator = new Prostor("laborator", "místnost na výrobu lektvarů.");
        Prostor les = new Prostor("les", "les, běhá tu divoká zvěř.");
        Prostor mytina = new Prostor("mytina", "mýtina na které roste vraní oko,vlastovičník, pampelišky a kopřivy.");
        Prostor sluj = new Prostor("sluj", "slůj zlého draka.");

        // přiřazují se průchody mezi prostory (sousedící prostory)
        namesti.setVychod(domov);
        namesti.setVychod(hospoda);
        namesti.setVychod(kolbiste);
        namesti.setVychod(chatrc);
        namesti.setVychod(les);
        hospoda.setVychod(namesti);
        kolbiste.setVychod(namesti);
        chatrc.setVychod(laborator);
        chatrc.setVychod(namesti);
        domov.setVychod(namesti);
        les.setVychod(mytina);
        les.setVychod(namesti);
        mytina.setVychod(les);
        mytina.setVychod(sluj);
        laborator.setVychod(chatrc);

        //vytváření věcí
        Vec batoh = new Vec("batoh", true, false);
        Vec vrani_oko = new Vec("vrani_oko", true, false);
        Vec vlastovicnik = new Vec("vlastovicnik", true, false);
        Vec pampeliska = new Vec("pampeliska", true, false);
        Vec kopriva = new Vec("kopriva", true, false);
        Vec zajic = new Vec("zajic", true, true);
        Vec jelen = new Vec("jelen", true, true);
        Vec mec = new Vec("mec", true, false);
        Vec nic = new Vec("nic", false, false);
        Vec alchymisticky_stul = new Vec("alchymisticky_stul", false, false);
        Vec elixir = new Vec("elixir", false, false);
        domov.pridejVec(batoh);
        mytina.pridejVec(vrani_oko);
        mytina.pridejVec(vlastovicnik);
        mytina.pridejVec(pampeliska);
        mytina.pridejVec(kopriva);
        les.pridejVec(zajic);
        les.pridejVec(jelen);
        laborator.pridejVec(alchymisticky_stul);
        laborator.pridejVec(elixir);

        //vytváření postav
        Postava hospodsky = new Postava("Hospodsky", zajic, mec, "Zdravím tě, slyšel jsem že by jsi se rád vypravil na draka.\n" +
                " Je mi jasné že ne takovou výpravu potřebuješ nějakou zbraň.\n"
                + "Proto ti tu nabízím, pokud mi ulovíš zajíce. Tak ti jeden mohu dát.", "Již jsem ti řekl.\n" + "Meč za zajíce.",
                "Nemáš u sebee věc, kterou jsem požadoval.", "Díky, zde máš slíbený meč.", "Víc toho pro tebe už nemám.");
        Postava bylinarka = new Postava("Bylinarka", nic, nic, "Ále kdo pak nám tu přišel. Potřebuješ se připravit na draka, co?\n" +
                "Propůjčím ti mou laboratoř. Když smícháš vlaštovičník s vraníkm okem. Vznikne ti posilující elixír.", "Víc ti pomoct nemohu. Elixír - vlaštovičník a vraní oko.",
                "Já ti to nevyrobím, musíš sám.", " ", " ");
        Postava veteran = new Postava("Veteran", nic, nic, "Tak ty by jsi rád porazil draka?\n" + "Já tě mohu naučit zacházet s mečem, ale trénink není na jeden den.",
                "Pokud máš meč, můžeme začít.", "Musíš mít meč.", "Musíš si odpočinout.", "Už tě toho víc nenaučím.");
        Postava drak = new Postava("Drak", nic, nic, "Roar!!", "", "", "", "");
        kolbiste.pridejPostavu(veteran);
        chatrc.pridejPostavu(bylinarka);
        hospoda.pridejPostavu(hospodsky);
        sluj.pridejPostavu(drak);
        aktualniProstor = domov;  // hra začíná v domě
    }

    /**
     * Metoda vrací odkaz na aktuální prostor, ve ktetém se hráč právě nachází.
     *
     * @return aktuální prostor
     */

    public Prostor getAktualniProstor() {
        return aktualniProstor;
    }

    /**
     * vrati batoh
     *
     * @return
     */
    public Batoh getBatoh() {
        return this.batoh;
    }

    /**
     * vrati silu
     *
     * @return
     */
    public int getSila() {
        return this.sila;
    }

    /**
     * vrati unavenost
     *
     * @return
     */
    public int getUnaveny() {
        return this.unaveny;
    }

    /**
     * již byl sebrán batoh?
     *
     * @return
     */
    public int getMaBatoh() {
        return this.maBatoh;
    }

    /**
     * vrati elixir
     *
     * @return
     */
    public boolean getElixir() {
        return this.elixir;
    }

    /**
     * Metoda nastaví aktuální prostor, používá se nejčastěji při přechodu mezi prostory
     *
     * @param prostor nový aktuální prostor
     */
    public void setAktualniProstor(Prostor prostor) {
        aktualniProstor = prostor;
        notifyObservers();
    }

    /**
     * nastavení sebrani batohu
     *
     * @return
     */
    public void setMaBatoh(int mbatoh) {
        maBatoh = mbatoh;
    }

    /**
     * nastavení unavenosti
     *
     * @return
     */
    public void setUnaveny(int unaven) {
        unaveny = unaven;
    }

    /**
     * nastavení sily
     *
     * @return
     */
    public void setSila(int sil) {
        sila = sil;
    }

    /**
     * nastavení elixiru
     *
     * @return
     */
    public void setElixir(boolean elixir1) {
        elixir = elixir1;
    }


    /**
     * Metoda register registruje nového observera
     *
     * @param observer
     */
    @Override
    public void register(Observer observer) {
        seznamPozorovatelu.add(observer);
    }

    /**
     * Metoda unregister zruší již zaregistrovaného observera
     *
     * @param observer
     */
    @Override
    public void unregister(Observer observer) {
        seznamPozorovatelu.remove(observer);
    }

    /**
     * Metoda notifyObservers upozorňuje všechny observery na změnu
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : seznamPozorovatelu) {
            observer.update();
        }
    }
}
