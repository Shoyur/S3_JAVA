import controleurs.ControleurPatient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ScenePrincipaleController {

    public String modifOuAjout = "";
    double mouseX = 0;
    double mouseY = 0;

    @FXML
    private AnchorPane anchor;

    @FXML
    private Button boutonListerTous, boutonAnnuler;

    @FXML
    private Button boutonNon;

    @FXML
    private Button boutonOui;

    @FXML
    private Button boutonSauvegarder;

    @FXML
    private Button boutonSupprimer;

    @FXML
    private CheckBox checkFumeur;

    @FXML
    private Label labelNew1;

    @FXML
    private Label labelNew2;

    @FXML
    private Label labelNew3;

    @FXML
    private Label labelNew4;

    @FXML
    private Label labelNew5;

    @FXML
    private Label labelNew6;

    @FXML
    private Label labelNew7;

    @FXML
    private Hyperlink lienMini;

    @FXML
    private Hyperlink lienQuitter;

    @FXML
    private TextArea liste;

    @FXML
    private Label ouinon;

    @FXML
    private TextField texteFieldAdresse;

    @FXML
    private TextField texteFieldCP;

    @FXML
    private TextField texteFieldNaissance;

    @FXML
    private TextField texteFieldNom;

    @FXML
    private TextField texteFieldPrenom;

    @FXML
    private TextField texteFieldSexe;

    @FXML
    private Button GONUM;

    @FXML
    private Button GOVILLE;

    @FXML
    private TextField TFNUM;

    @FXML
    private TextField TFVILLE;

    ///////////////////////////
    // Méthodes FXML :
    ///////////////////////////

    @FXML
    void GONUM(MouseEvent event) throws Exception {
        liste.clear();
        ControleurPatient.getControleurPatient().CtrP_delete(Integer.parseInt(TFNUM.getText()));
        boutonListerTous();
        TFNUM.setText(null);
    }

    @FXML
    void GOVILLE(MouseEvent event) {
        liste.clear();
        liste.setText(App.lister("ville", TFVILLE.getText()));
    }

    @FXML
    void initialize() throws Exception {
        boutonListerTous();
        boutonListerTous.setOnMouseClicked((event) -> { try { boutonListerTous(); } catch (Exception e) {}});
    }

    @FXML
    void boutonListerTous() throws Exception {
        liste.clear();
        liste.setText(App.lister("tout", null));
        visibilite("L");
    }

    @FXML
    void boutonListerNonFumeurs(MouseEvent event) {
        liste.clear();
        liste.setText(App.lister("nonFumeurs", null));
    }

    @FXML
    void boutonAjouter(MouseEvent event) {
        visibilite("A");
    }



    @FXML
    void boutonNon(MouseEvent event) {
        boutonOui.setVisible(false);
        boutonNon.setVisible(false);
        ouinon.setVisible(false);
    }

    @FXML
    void boutonOui(MouseEvent event) throws Exception {
        App.supprimerPatient(Integer.parseInt(TFNUM.getText()));
        boutonListerTous();
    }

    @FXML
    void boutonSauvegarder(MouseEvent event) throws Exception {
        App.ajouterTout(
            texteFieldNom.getText(),
            texteFieldPrenom.getText(),
            texteFieldNaissance.getText(),
            texteFieldAdresse.getText(),
            texteFieldCP.getText(),
            texteFieldSexe.getText(),
            checkFumeur.isSelected()
        );
        boutonListerTous();
    }

    @FXML
    void boutonSupprimer(MouseEvent event) {
        boutonOui.setVisible(true);
        boutonNon.setVisible(true);
        ouinon.setVisible(true);
    }

    @FXML
    void boutonAnnuler(MouseEvent event) throws Exception {
        boutonListerTous();
    }


    // SOURIS
    @FXML
    void pressed(MouseEvent event) {
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
    }

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.setX(event.getScreenX() - mouseX);
        stage.setY(event.getScreenY() - mouseY);
    }

    // WINDOW
    @FXML
    void mini(ActionEvent event) {
        Stage stage = (Stage)anchor.getScene().getWindow();
        stage.setIconified(true);
    }
    @FXML
    void quitter(ActionEvent event) {
        System.exit(0);
    }

    ///////////////////////////////////
    // AUTRES MÉTHODES  NON-FXML
    ///////////////////////////////////

    // "L"=Liste, "A"=AjouterUnLivre
    public void visibilite(String mode) {

        // modifOuAjout = (mode == "M") ? mode : (mode == "A") ? mode : "";

        texteFieldNom.setText(null);
        texteFieldPrenom.setText(null);
        texteFieldNaissance.setText(null);
        texteFieldAdresse.setText(null);
        texteFieldCP.setText(null);
        texteFieldSexe.setText(null);
        checkFumeur.setSelected(false);

        liste.setVisible((mode == "L") ? true : false);

        texteFieldNom.setVisible((mode == "A") ? true : false);
        texteFieldPrenom.setVisible((mode == "A") ? true : false);
        texteFieldNaissance.setVisible((mode == "A") ? true : false);
        texteFieldAdresse.setVisible((mode == "A") ? true : false);
        texteFieldCP.setVisible((mode == "A") ? true : false);
        texteFieldSexe.setVisible((mode == "A") ? true : false);
        checkFumeur.setVisible((mode == "A") ? true : false);

        labelNew1.setVisible((mode == "A") ? true : false);
        labelNew2.setVisible((mode == "A") ? true : false);
        labelNew3.setVisible((mode == "A") ? true : false);
        labelNew4.setVisible((mode == "A") ? true : false);
        labelNew5.setVisible((mode == "A") ? true : false);
        labelNew6.setVisible((mode == "A") ? true : false);
        labelNew7.setVisible((mode == "A") ? true : false);

        boutonSupprimer.setVisible((mode == "M") ? true : false);
        boutonSauvegarder.setVisible((mode == "A") ? true : false);
        boutonAnnuler.setVisible((mode == "A") ? true : false);


        boutonOui.setVisible(false);
        boutonNon.setVisible(false);
        ouinon.setVisible(false);

    }

}
