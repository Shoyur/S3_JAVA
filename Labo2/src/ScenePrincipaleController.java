import java.util.List;
import java.util.Objects;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modeles.Livre;

public class ScenePrincipaleController { 
// 
    public String modifOuAjout = "";
    double mouseX = 0;
    double mouseY = 0;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ComboBox<String> comboCateg;
    @FXML
    private ComboBox<Integer> comboNumLivre, comboNumAuteur;
    @FXML
    private TextArea liste;
    @FXML
    private Button boutonListerTous, boutonAjouterLivre, boutonSupprimer, boutonSauvegarder, boutonAnnuler, boutonOui, boutonNon;
    @FXML
    private Label labelNoLivre, labelTitre, labelNoAuteur, labelAnnee, labelPages, labelCateg,
        valeurAnnee, valeurCateg, valeurNoAuteur, valeurNoLivre, valeurPages,
        titreCombo1, titreCombo2, titreCombo3,
        erreur1, erreur2, erreur3, erreur4, erreur5, erreur6, erreur7, 
        ouinon;
    @FXML
    private TextField texteFieldNoLivre, texteFieldTitre, texteFieldNoAuteur, texteFieldAnnee, texteFieldPages, texteFieldCateg;
    @FXML
    private Hyperlink lienMini, lienQuitter;


    // ----------------------------------------------------------------------------------------------------------------------------
    // MÉTHODES FXML
    // ----------------------------------------------------------------------------------------------------------------------------
    @FXML
    void boutonListerTous() throws Exception {
        liste.clear();
        liste.setText(App.lister("tout", null));
        comboCateg.getSelectionModel().clearSelection();
        comboNumLivre.getSelectionModel().clearSelection();
        comboNumAuteur.getSelectionModel().clearSelection();        
        visibilite("L");
        combosRemplir();
    }

    @FXML
    void comboClicCateg(ActionEvent event) throws Exception {
        visibilite("L");
        EventHandler<ActionEvent> handlerL = comboNumLivre.getOnAction();
        comboNumLivre.setOnAction(null);
        comboNumLivre.getSelectionModel().clearSelection();
        comboNumLivre.setOnAction(handlerL);
        EventHandler<ActionEvent> handlerA = comboNumAuteur.getOnAction();
        comboNumAuteur.setOnAction(null);
        comboNumAuteur.getSelectionModel().clearSelection();
        comboNumAuteur.setOnAction(handlerA);
        if (comboCateg.getValue() != null) { liste.setText(App.lister("cat", comboCateg.getValue())); }
    }

    @FXML
    void comboClicNumLivre(ActionEvent event) throws Exception {
        visibilite("M");
        EventHandler<ActionEvent> handlerC = comboCateg.getOnAction();
        comboCateg.setOnAction(null);
        comboCateg.getSelectionModel().clearSelection();
        comboCateg.setOnAction(handlerC);
        EventHandler<ActionEvent> handlerA = comboNumAuteur.getOnAction();
        comboNumAuteur.setOnAction(null);
        comboNumAuteur.getSelectionModel().clearSelection();
        comboNumAuteur.setOnAction(handlerA);
        if (comboNumLivre.getValue() == null) { return; }
        else {
            Livre livre = App.unLivre((Integer)comboNumLivre.getValue());
            valeurNoLivre.setText(Integer.toString(livre.getNo()));
            texteFieldTitre.setText(livre.getTitre());
            valeurNoAuteur.setText(Integer.toString(livre.getAuteur()));
            valeurAnnee.setText(Integer.toString(livre.getAnnee()));
            valeurPages.setText(Integer.toString(livre.getPages()));
            valeurCateg.setText(livre.getCateg());
        }        
    }
    
    @FXML
    void comboClicNumAuteur(ActionEvent event) throws Exception {
        visibilite("L");
        EventHandler<ActionEvent> handlerC = comboCateg.getOnAction();
        comboCateg.setOnAction(null);
        comboCateg.getSelectionModel().clearSelection();
        comboCateg.setOnAction(handlerC);
        EventHandler<ActionEvent> handlerL = comboNumLivre.getOnAction();
        comboNumLivre.setOnAction(null);
        comboNumLivre.getSelectionModel().clearSelection();
        comboNumLivre.setOnAction(handlerL);
        if (comboNumAuteur.getValue() != null) { liste.setText(App.lister("auteur", comboNumAuteur.getValue())); }
    }

    @FXML
    void boutonAjouterLivre(MouseEvent event) throws Exception {
        comboCateg.getSelectionModel().clearSelection();
        comboCateg.setValue(null);
        comboNumLivre.getSelectionModel().clearSelection();
        comboNumLivre.setValue(null);
        comboNumAuteur.getSelectionModel().clearSelection();
        comboNumAuteur.setValue(null);
        visibilite("A");
    }

    @FXML
    void boutonSupprimer(MouseEvent event) throws Exception {
        boutonOui.setVisible(true);
        boutonNon.setVisible(true);
        ouinon.setVisible(true);
    }

    @FXML
    void boutonOui(MouseEvent event) throws Exception {
        boutonOui.setVisible(true);
        boutonNon.setVisible(true);
        ouinon.setVisible(true);
        App.supprimerLivre(comboNumLivre.getValue());
        combosRemplir();
        boutonListerTous();
    }

    @FXML
    void boutonNon(MouseEvent event) throws Exception {
        boutonOui.setVisible(false);
        boutonNon.setVisible(false);
        ouinon.setVisible(false);
        
    }

    @FXML
    void boutonSauvegarder(MouseEvent event) throws Exception {
        if (modifOuAjout == "M") {
            if (texteFieldTitre.getText() == null || texteFieldTitre.getText().length() < 1) { 
                // System.out.println("Vous devez entrer un titre!");
                erreur2.setVisible(true); 
            }
            else {
                App.modifierTitre(comboNumLivre.getValue(), texteFieldTitre.getText());
                combosRemplir();
                boutonListerTous();
            }
        }
        else {
            if (estUnNombreEntre(texteFieldNoLivre.getText(), 100, 999) == false) { 
                // System.out.println("Le numéro du livre doit être entre 100 et 999!");
                enleverErreurs();
                erreur1.setVisible(true);
            }
            else if (texteFieldTitre.getText() == null || texteFieldTitre.getText().length() < 1) {
                // System.out.println("Vous devez entrer un titre!");
                enleverErreurs();
                erreur2.setVisible(true); 
            }
            else if (estUnNombreEntre(texteFieldNoAuteur.getText(), 1, 999) == false) { 
                // System.out.println("Le numéro de l'auteur doit être entre 1 et 999!"); 
                enleverErreurs();
                erreur3.setVisible(true);
            }
            else if (estUnNombreEntre(texteFieldAnnee.getText(), 1000, 3000) == false) { 
                // System.out.println("L'année doit être entre l'an 1000 et l'an 3000!"); 
                enleverErreurs();
                erreur4.setVisible(true);
            }
            else if (estUnNombreEntre(texteFieldPages.getText(), 0, 99999) == false) { 
                // System.out.println("Le nombre de pages doit être entre 0 et 99999!"); 
                enleverErreurs();
                erreur5.setVisible(true);
            }
            else if (texteFieldCateg.getText() == null || texteFieldTitre.getText().length() < 1) { 
                // System.out.println("Vous devez entrer une catégorie!"); 
                enleverErreurs();
                erreur6.setVisible(true);
            }
            else if (Objects.nonNull(App.unLivre(Integer.parseInt(texteFieldNoLivre.getText())))) {
                enleverErreurs();
                erreur7.setVisible(true);
            }
            else { 
                App.ajouterTout(
                    Integer.parseInt(texteFieldNoLivre.getText()), 
                    texteFieldTitre.getText(),
                    Integer.parseInt(texteFieldNoAuteur.getText()), 
                    Integer.parseInt(texteFieldAnnee.getText()), 
                    Integer.parseInt(texteFieldPages.getText()), 
                    texteFieldCateg.getText()
                );
                combosRemplir();
                boutonListerTous();
            }
            
        }
    }

    @FXML
    void boutonAnnuler(MouseEvent event) throws Exception {
        boutonListerTous();
    }

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.setX(event.getScreenX() - mouseX);
        stage.setY(event.getScreenY() - mouseY);
    }

    @FXML
    void pressed(MouseEvent event) {
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
    }

    @FXML
    void mini(ActionEvent event) {
        Stage stage = (Stage)anchor.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void quitter(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void initialize() throws Exception {
        boutonListerTous();
        boutonListerTous.setOnMouseClicked((event) -> { try { boutonListerTous(); } catch (Exception e) {}});
        comboCateg.setStyle("-fx-background-color: #505050; -fx-control-inner-background: #505050");
        comboNumAuteur.setStyle("-fx-background-color: #505050; -fx-control-inner-background: #505050");
        comboNumLivre.setStyle("-fx-background-color: #505050; -fx-control-inner-background: #505050");
    }


    // ----------------------------------------------------------------------------------------------------------------------------
    // AUTRES MÉTHODES NON-FXML
    // ----------------------------------------------------------------------------------------------------------------------------

    // "L"=Liste, "M"=ModifierUnLivre, "A"=AjouterUnLivre
    public void visibilite(String mode) {

        modifOuAjout = (mode == "M") ? mode : (mode == "A") ? mode : "";

        texteFieldNoLivre.setText(null);
        texteFieldTitre.setText(null);
        texteFieldNoAuteur.setText(null);
        texteFieldAnnee.setText(null);
        texteFieldPages.setText(null);
        texteFieldCateg.setText(null);

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
        comboCateg.setVisible(true);
        comboNumAuteur.setVisible(true);
        comboNumLivre.setVisible(true);
        boutonAjouterLivre.setVisible(true);
        titreCombo1.setVisible(true);
        titreCombo2.setVisible(true);
        titreCombo3.setVisible(true);

        enleverErreurs();

        boutonOui.setVisible(false);
        boutonNon.setVisible(false);
        ouinon.setVisible(false);

    }

    void enleverErreurs() {
        erreur1.setVisible(false);
        erreur2.setVisible(false);
        erreur3.setVisible(false);
        erreur4.setVisible(false);
        erreur5.setVisible(false);
        erreur6.setVisible(false);
        erreur7.setVisible(false);
    }

    void combosRemplir() throws Exception {
        List troisListes[] = App.listesCombos();
        comboCateg.getItems().clear();
        comboCateg.getItems().addAll(troisListes[0]);
        comboNumLivre.getItems().clear();
        comboNumLivre.getItems().addAll(troisListes[1]);
        comboNumAuteur.getItems().clear();
        comboNumAuteur.getItems().addAll(troisListes[2]);
    }

    boolean estUnNombreEntre(String texte, int debutInclus, int finInclus) {
        if (texte == null) { return false; }
        int i;
        try { i = Integer.parseInt(texte); } 
        catch (NumberFormatException e) { return false; }
        if (i < debutInclus || i > finInclus) { return false; }
        return true;
    }

}