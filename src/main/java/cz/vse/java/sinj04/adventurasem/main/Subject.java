package cz.vse.java.sinj04.adventurasem.main;

public interface Subject {

    /**
     * registrace pozorovatele
     * pozorovatelům se posílají změny
     * @param observer
     */
    void register(Observer observer);

    /**
     * odebrání pozorovatele ze seznamu
     * @param observer
     */
    void unregister(Observer observer);

    /**
     * upozorní všechny registrované pozorovatele na změny
     * není vhodná jako veřejná metoda do rozhraní
     */
    void notifyObservers();
}
