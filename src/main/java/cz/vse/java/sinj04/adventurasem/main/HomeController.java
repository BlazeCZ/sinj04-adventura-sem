package cz.vse.java.sinj04.adventurasem.main;

import cz.vse.java.sinj04.adventurasem.logika.Hra;
import cz.vse.java.sinj04.adventurasem.logika.IHra;
import cz.vse.java.sinj04.adventurasem.logika.PrikazJdi;
import cz.vse.java.sinj04.adventurasem.logika.Prostor;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Třída HomeController - controller souboru home.fxml
 */
public class HomeController implements Observer {
    @FXML
    private ImageView batohObrazek;
    @FXML
    private ListView panelBatohu;
    @FXML
    private ImageView hrac;
    @FXML
    private ListView panelVychodu;
    @FXML
    private Button odesli;
    @FXML
    private TextField vstup;
    @FXML
    private TextArea vystup;

    private IHra hra;
    private Map<String, Point2D> souradnice;
    private final Image zajic = new Image("Zajic.png");
    private final Image elixir = new Image("elixir.png");
    private final Image jelen = new Image("jelen.png");
    private final Image kopriva = new Image("kopriva.png");
    private final Image mec = new Image("mec.png");
    private final Image pampeliska = new Image("pampeliska.png");
    private final Image vlastovicnik = new Image("Vlastovicnik.png");
    private final Image vrani_oko = new Image("vrani_oko.png");

    private Image[] ikony = {zajic, elixir, jelen, kopriva, mec, pampeliska, vlastovicnik, vrani_oko};

    /**
     * Metoda initialize provádí základní inicializaci hry
     * metoda se spouští při zapnutí hry, dále je možné ji volat pro novou inicializaci
     */
    @FXML
    private void initialize() {
        hra = new Hra();
        vystup.appendText(hra.vratUvitani() + "\n\n");
        panelVychodu.getItems().addAll(hra.getHerniPlan().getAktualniProstor().getVychody());

        hra.getHerniPlan().register(this);
        hra.getHerniPlan().getBatoh().register(this);

        souradnice = createSouradnice();

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vstup.requestFocus();
            }
        });

    }

    /**
     * Metoda zpracujVstup zpracovává zadnaný vstup do TextField
     * Aktivává se jak kliknutí na tlačítko Odešli tak při zmáčknutí klávesy Enter
     *
     * @param actionEvent
     */
    public void zpracujVstup(ActionEvent actionEvent) {
        String prikaz = vstup.getText();
        zpracujPrikaz(prikaz);
        vstup.clear();

    }

    /**
     * Metoda zpracujPrikaz zpracovává příkaz
     * Příkaz se vypíše do vystupu
     *
     * @param prikaz
     */
    private void zpracujPrikaz(String prikaz) {
        vystup.appendText("Příkaz: " + prikaz + "\n");
        vystup.appendText(hra.zpracujPrikaz(prikaz) + "\n\n");

        if (hra.getHerniPlan().getMaBatoh() == 1) {
            batohObrazek.setVisible(true);
        }
        if (hra.konecHry()) {
            vystup.appendText(hra.vratEpilog());
            vstup.setDisable(true);
            odesli.setDisable(true);
            panelVychodu.setDisable(true);
        }
    }

    /**
     * Metoda update se volá pomocí návrhového vzoru Observer
     * Po vyvolání probíhá aktualizace určitých informací
     */
    @Override
    public void update() {
        Prostor aktualniProstor = hra.getHerniPlan().getAktualniProstor();
        panelVychodu.getItems().clear();
        panelVychodu.getItems().addAll(aktualniProstor.getVychody());

        hrac.setLayoutX(souradnice.get(aktualniProstor.getNazev()).getX());
        hrac.setLayoutY(souradnice.get(aktualniProstor.getNazev()).getY());

        panelBatohu.getItems().clear();
        panelBatohu.getItems().addAll(hra.getHerniPlan().getBatoh().batoh());

        panelBatohu.setCellFactory(param -> new ListCell<String>() {
                    private ImageView ikona = new ImageView();

                    /**
                     * Metoda updateItem přiřazuje obrázky k sebraným věcem
                     * @param jmeno
                     * @param prazdny
                     */
                    @Override
                    public void updateItem(String jmeno, boolean prazdny) {
                        ikona.setFitHeight(50);
                        ikona.setFitWidth(50);
                        super.updateItem(jmeno, prazdny);
                        if (prazdny) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            if (jmeno.equals("zajic")) ikona.setImage(ikony[0]);
                            else if (jmeno.equals("elixir")) ikona.setImage(ikony[1]);
                            else if (jmeno.equals("jelen")) ikona.setImage(ikony[2]);
                            else if (jmeno.equals("kopriva")) ikona.setImage(ikony[3]);
                            else if (jmeno.equals("mec")) ikona.setImage(ikony[4]);
                            else if (jmeno.equals("pampeliska")) ikona.setImage(ikony[5]);
                            else if (jmeno.equals("vlastovicnik")) ikona.setImage(ikony[6]);
                            else if (jmeno.equals("vrani_oko")) ikona.setImage(ikony[7]);
                        }
                        setText(jmeno);
                        setGraphic(ikona);

                    }
                }
        );
    }

    /**
     * Metoda createSouradnice vytváří základní souřadnice pro pohybu po mapě
     *
     * @return
     */
    private Map<String, Point2D> createSouradnice() {
        Map<String, Point2D> souradnice = new HashMap<>();
        souradnice.put("hospoda", new Point2D(175, 14));
        souradnice.put("les", new Point2D(175, 157));
        souradnice.put("domov", new Point2D(47, 88));
        souradnice.put("namesti", new Point2D(175, 88));
        souradnice.put("chatrc", new Point2D(300, 88));
        souradnice.put("laborator", new Point2D(427, 88));
        souradnice.put("mytina", new Point2D(300, 157));
        souradnice.put("sluj", new Point2D(427, 157));
        souradnice.put("kolbiste", new Point2D(300, 14));
        return souradnice;
    }

    /**
     * Metoda vybranVychod umožnujě používat příkaz jdi pomocí klikání v listView, které zobrazuje východy
     *
     * @param mouseEvent
     */
    public void vybranVychod(MouseEvent mouseEvent) {
        Prostor prostor = (Prostor) panelVychodu.getSelectionModel().getSelectedItem();
        if (prostor != null) {
            zpracujPrikaz(PrikazJdi.NAZEV + " " + prostor.getNazev());
        }
    }

    /**
     * Metody novahra po kliknutí na talčítku Nová hra se vyvolává nová hra a vrací se herní pole do základního nastavení
     *
     * @param actionEvent
     */
    public void novahra(ActionEvent actionEvent) {
        vstup.clear();
        vystup.clear();
        batohObrazek.setVisible(false);
        panelVychodu.getItems().clear();
        panelBatohu.getItems().clear();
        vstup.setDisable(false);
        odesli.setDisable(false);
        panelVychodu.setDisable(false);
        initialize();
        hrac.setLayoutX(47);
        hrac.setLayoutY(88);
    }

    /**
     * Metoda napoveda po kliknutí na tlačítko Napověda se vyvolává nové okno, kde se zoprazí soubor html s nápovědou
     *
     * @param actionEvent
     */
    public void napoveda(ActionEvent actionEvent) {

        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        URL url = this.getClass().getResource("/napoveda.html");
        webEngine.load(url.toString());
        VBox box = new VBox();
        box.getChildren().add(webView);
        Scene scene1 = new Scene(box);
        Stage stage1 = new Stage();
        stage1.setScene(scene1);
        stage1.setTitle("Napověda");
        stage1.show();
    }

}
