package cz.vse.java.sinj04.adventurasem.logika;


public class Postava {
    private String jmeno;
    private Vec coChce;
    private Vec coMa;
    private String mluvPrvni;
    private String recNechce;
    private String recChce;
    private String mluvPote;
    private String poVymene;
    private Boolean probehlaVymena = false;
    private Boolean mluvil = false;

    /**
     * konstruktor postav
     *
     */

    public Postava(String jmeno, Vec coChce, Vec coMa, String mluvPrvni, String mluvPote, String recNechce, String recChce, String poVymene) {
        this.jmeno = jmeno;
        this.coChce = coChce;
        this.coMa = coMa;
        this.mluvPrvni = mluvPrvni;
        this.mluvPote = mluvPote;
        this.recChce = recChce;
        this.recNechce = recNechce;
        this.poVymene = poVymene;


    }

    /**
     * metoda mluv...
     * vrací určité fráze podle postupu
     *
     */

    public String mluv() {
        if (probehlaVymena == false){
        if (mluvil==false){
            mluvil = true;
        return jmeno + ": " + mluvPrvni;} else {return jmeno + ": " + mluvPote;}}
        else{return jmeno + ": " + poVymene;}
    }
    /**
     * vrati hodnotu poVymene
     *
     */
    public String getPoVymene(){return poVymene;}
    /**
     * vrati hodnotu recChce
     *
     */
    public String getRecChce() {return recChce;}
    /**
     * vrati hodnotu recNechce
     *
     */
    public String getRecNechce() {return recNechce;}
    /**
     * vrati hodnotu jmeno
     *
     */
    public String getJmeno() {return jmeno;}
    /**
     * vrati hodnotu probhlaVymena
     *
     */
    public Boolean getProbehlaVymena() {
        return probehlaVymena;
    }
    /**
     * vrati hodnotu coMa
     *
     */
    public Vec getCoMa() {
        return coMa;
    }
    /**
     * vrati hodnotu coChce
     *
     */
    public Vec getCoChce() {return coChce;}
    /**
     * nastavení hodnoty probehlaVymena
     *
     */
    public void setProbehlaVymena(Boolean probehlaVymena) {
        this.probehlaVymena = probehlaVymena;
    }

}