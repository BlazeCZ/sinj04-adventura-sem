package cz.vse.java.sinj04.adventurasem.logika;


import cz.vse.java.sinj04.adventurasem.main.Observer;
import cz.vse.java.sinj04.adventurasem.main.Subject;

import java.util.*;

/**
 * Batoh - batoh, kde se uchovavani sebrane predmety
 * muzememe si ho kdykoliv zobrazit prikazem obsahbatohu
 */

public class Batoh implements Subject {
    private Map<String, Vec> veci = new HashMap<>();
    private Set<Observer> seznamPozorovatelu = new HashSet<>();
    private Set<String> veciObserver = new HashSet<>();

    /**
     * @return vraci vec, ktera byla vlozena, null pokud vlozena nebyla
     * @parametr vec vec kterou vkladame do batohu
     */
    public boolean vlozVec(Vec vec) {
        if (jePlny() == false) {
            veciObserver.add(vec.getNazev());
            veci.put(vec.getNazev(), vec); //vloží klíč a hodnotu do mapy
            notifyObservers();
            if (veci.containsKey(vec.getNazev())) return true;
            else return false;
        } else return false;

    }

    /**
     * zobrazi obsah batohu
     *
     * @return obsah batohu
     */
    public String zobrazitObsah() {

        String vracenyText = "Obsah batohu: \n";
        if (veci.size() > 0) {
            for (Map.Entry<String, Vec> vec : veci.entrySet()) {
                vracenyText += vec.getKey() + ", ";
            }
            vracenyText = vracenyText.substring(0, vracenyText.length() - 2);
        } else {
            vracenyText += "nic";
        }

        return vracenyText;
    }

    /**
     * Metoda batoh vrací kolekci ve které je uložen seznam věcí v batohu
     * @return
     */
    public Collection<String> batoh() {
        return Collections.unmodifiableCollection(veciObserver);
    }

    /**
     * zkouška zda batoh obsahuje zadanou věc
     *
     * @return true/false
     */
    public Boolean obsahujeVec(String nazev) {
        notifyObservers();
        return veci.containsKey(nazev);
    }

    /**
     * odebírání věci z batohu
     *
     * @return
     */
    public Vec odeberVec(String nazev) {
        veciObserver.remove(nazev);
        notifyObservers();
        return veci.remove(nazev);
    }

    /**
     * zjisti jestli je batoh plny
     *
     * @return vraci true, pokud plny je a false pokud neni
     */
    public Boolean jePlny() {
        if (veci.size() > 2) {
            return true;
        }
        return false;
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
