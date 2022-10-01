import java.io.IOException;

import java.util.List;
import java.util.Optional;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseEvent;

public class ScenePrincipaleController {

    @FXML
    public ComboBox<String> comboCateg;
    @FXML
    private ComboBox<Integer> comboNumLivre, comboNumAuteur;
    @FXML
    private TextArea liste;
    @FXML
    private Button boutonListerTous, boutonAjouterLivre, boutonSupprimer, boutonSauvegarder, boutonAnnuler;
    @FXML
    private Label labelNoLivre, labelTitre, labelNoAuteur, labelAnnee, labelPages, labelCateg,
        valeurAnnee, valeurCateg, valeurNoAuteur, valeurNoLivre, valeurPages;
    @FXML
    private TextField texteFieldNoLivre, texteFieldTitre, texteFieldNoAuteur, texteFieldAnnee, texteFieldPages, texteFieldCateg;
    
    // ----------------------------------------------------------------------------------------------------------------------------
    // MÉTHODES FXML
    // ----------------------------------------------------------------------------------------------------------------------------
    @FXML
    void boutonListerTous(MouseEvent event) throws IOException {
        System.out.println("début méthode boutonlistertous");

        visibilite("L");

        liste.clear();
        liste.setText(App.lister("tout", null));
        comboCateg.getSelectionModel().clearSelection();
        comboCateg.setValue(null);
        comboNumLivre.getSelectionModel().clearSelection();
        comboNumLivre.setValue(null);
        comboNumAuteur.getSelectionModel().clearSelection();
        comboNumAuteur.setValue(null);
        
    }

    @FXML
    void comboClicCateg(ActionEvent event) throws IOException {

        visibilite("L");

        EventHandler<ActionEvent> handlerL = comboNumLivre.getOnAction();
        comboNumLivre.setOnAction(null);
        comboNumLivre.getSelectionModel().clearSelection();
        comboNumLivre.setValue(null);
        comboNumLivre.setOnAction(handlerL);

        EventHandler<ActionEvent> handlerA = comboNumAuteur.getOnAction();
        comboNumAuteur.setOnAction(null);
        comboNumAuteur.getSelectionModel().clearSelection();
        comboNumAuteur.setValue(null);
        comboNumAuteur.setOnAction(handlerA);

        if (comboCateg.getValue() != null) {
            liste.setText(App.lister("cat", comboCateg.getValue()));
        }
    }

    @FXML
    void comboClicNumLivre(ActionEvent event) throws IOException {
        
        visibilite("M");

        EventHandler<ActionEvent> handlerC = comboCateg.getOnAction();
        comboCateg.setOnAction(null);
        comboCateg.getSelectionModel().clearSelection();
        comboCateg.setValue(null);
        comboCateg.setOnAction(handlerC);

        EventHandler<ActionEvent> handlerA = comboNumAuteur.getOnAction();
        comboNumAuteur.setOnAction(null);
        comboNumAuteur.getSelectionModel().clearSelection();
        comboNumAuteur.setValue(null);
        comboNumAuteur.setOnAction(handlerA);

        if (comboNumLivre.getValue() == null) { return; }
        else {
            Object[] les6valeurs = App.infosModifs(comboNumLivre.getValue());
            // System.out.println("OUAIS : " + Integer.toString((Integer)les6valeurs[0]));
            valeurNoLivre.setText(Integer.toString((Integer)les6valeurs[0]));
            texteFieldTitre.setText((String)les6valeurs[1]);
            valeurNoAuteur.setText(Integer.toString((Integer)les6valeurs[2]));
            valeurAnnee.setText(Integer.toString((Integer)les6valeurs[3]));
            valeurPages.setText(Integer.toString((Integer)les6valeurs[4]));
            valeurCateg.setText((String)les6valeurs[5]);
        }
        texteFieldTitre.textProperty().addListener((e, b, a) -> { if (b != a) { boutonSauvegarder.setDisable(false); boutonAnnuler.setDisable(false); }});
        
    }
    
    @FXML
    void comboClicNumAuteur(ActionEvent event) throws IOException {

        visibilite("L");

        EventHandler<ActionEvent> handlerC = comboCateg.getOnAction();
        comboCateg.setOnAction(null);
        comboCateg.getSelectionModel().clearSelection();
        comboCateg.setValue(null);
        comboCateg.setOnAction(handlerC);

        EventHandler<ActionEvent> handlerL = comboNumLivre.getOnAction();
        comboNumLivre.setOnAction(null);
        comboNumLivre.getSelectionModel().clearSelection();
        comboNumLivre.setValue(null);
        comboNumLivre.setOnAction(handlerL);

        if (comboNumAuteur.getValue() != null) {
            liste.setText(App.lister("auteur", comboNumAuteur.getValue()));
        }
    }

    @FXML
    void boutonAjouterLivre(MouseEvent event) throws IOException {
        comboCateg.getSelectionModel().clearSelection();
        comboCateg.setValue(null);
        comboNumLivre.getSelectionModel().clearSelection();
        comboNumLivre.setValue(null);
        comboNumAuteur.getSelectionModel().clearSelection();
        comboNumAuteur.setValue(null);
        visibilite("A");

    }

    @FXML
    void boutonSupprimer(MouseEvent event) throws IOException {
		Alert alerte = new Alert(AlertType.WARNING);
		alerte.setTitle("Confirmation");
		alerte.setHeaderText(null);
		alerte.setContentText("Êtes vous certain de vouloir effacer le livre no. " + comboNumLivre.getValue() + " ?");
        alerte.getButtonTypes().clear();
        ButtonType oui = new ButtonType("OUI");
		ButtonType non = new ButtonType("NON");
        alerte.getButtonTypes().addAll(oui, non);
        Optional<ButtonType> reponse = alerte.showAndWait();
        if (reponse.get() == oui) {
            App.supprimerLivre(comboNumLivre.getValue());
            combosRemplir();
            boutonListerTous(event);
        }


    }

    @FXML
    void boutonSauvegarder(MouseEvent event) throws IOException {
        boutonListerTous(event);
    }

    @FXML
    void boutonAnnuler(MouseEvent event) throws IOException {
        System.out.println("J'ai clic annuler et ensuite je call boutonTous");
        boutonListerTous(event);
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        // assert comboNumAuteur != null : "fx:id=\"comboNumAuteur\" was not injected: check your FXML file 'ScenePrincipale.fxml'.";
        // assert comboNumLivre != null : "fx:id=\"comboNumLivre\" was not injected: check your FXML file 'ScenePrincipale.fxml'.";
        // assert liste != null : "fx:id=\"liste\" was not injected: check your FXML file 'ScenePrincipale.fxml'.";
        combosRemplir();
    }

    // ----------------------------------------------------------------------------------------------------------------------------
    // AUTRES MÉTHODES NON-FXML
    // ----------------------------------------------------------------------------------------------------------------------------

    // "L"=Liste, "M"=ModifierUnLivre, "A"=AjouterUnLivre
    public void visibilite(String mode) {
        liste.setVisible((mode == "L") ? true : false);
        texteFieldNoLivre.setVisible((mode == "A") ? true : false);
        texteFieldTitre.setVisible((mode == "M" || mode == "A") ? true : false);
        texteFieldNoAuteur.setVisible((mode == "A") ? true : false);
        texteFieldAnnee.setVisible((mode == "A") ? true : false);
        texteFieldPages.setVisible((mode == "A") ? true : false);
        texteFieldCateg.setVisible((mode == "A") ? true : false);
        labelAnnee.setVisible((mode == "M" || mode == "A") ? true : false);
        labelCateg.setVisible((mode == "M" || mode == "A") ? true : false);
        labelNoAuteur.setVisible((mode == "M" || mode == "A") ? true : false);
        labelNoLivre.setVisible((mode == "M" || mode == "A") ? true : false);
        labelPages.setVisible((mode == "M" || mode == "A") ? true : false);
        labelTitre.setVisible((mode == "M" || mode == "A") ? true : false);
        boutonSupprimer.setVisible((mode == "M") ? true : false);
        boutonSauvegarder.setVisible((mode == "M" || mode == "A") ? true : false);
        boutonAnnuler.setVisible((mode == "M" || mode == "A") ? true : false);
        valeurAnnee.setVisible((mode == "M") ? true : false);
        valeurCateg.setVisible((mode == "M") ? true : false);
        valeurNoAuteur.setVisible((mode == "M") ? true : false);
        valeurNoLivre.setVisible((mode == "M") ? true : false);
        valeurPages.setVisible((mode == "M") ? true : false);
        texteFieldNoLivre.setText(null);
        texteFieldTitre.setText(null);
        texteFieldNoAuteur.setText(null);
        texteFieldAnnee.setText(null);
        texteFieldPages.setText(null);
        texteFieldCateg.setText(null);
    }

    public void combosRemplir() throws IOException {
        List troisListes[] = App.listesCombos();
        comboCateg.getItems().clear();
        comboCateg.getItems().addAll(troisListes[0]);
        comboNumLivre.getItems().clear();
        comboNumLivre.getItems().addAll(troisListes[1]);
        comboNumAuteur.getItems().clear();
        comboNumAuteur.getItems().addAll(troisListes[2]);
    }

}