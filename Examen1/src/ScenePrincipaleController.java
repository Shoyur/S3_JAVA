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
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ScenePrincipaleController { 
// 
    public String modifOuAjout = "";
    double mouseX = 0;
    double mouseY = 0;

    @FXML
    private AnchorPane anchor;

    @FXML
    private TextArea liste;
    @FXML
    private Button boutonListerTous, boutonAjouter, boutonSupprimer,boutonModifier, boutonOui, boutonNon, 
        boutonSauvegarder, boutonSupprimerVrai;
    @FXML
    private Label labelNo, labelMarque, labelModele, labelOptions, labelPrix,
        ouinon,
        labelTitlebar,
        labelProchainNum;
    @FXML
    private TextField texteFieldNo, texteFieldMarque, texteFieldModele, texteFieldPrix;
    @FXML
    private Hyperlink lienMini, lienQuitter;
    @FXML
    private CheckBox check1, check2, check3;
    @FXML
    private Text fleche1, fleche2, fleche3;


    // ----------------------------------------------------------------------------------------------------------------------------
    // MÉTHODES FXML
    // ----------------------------------------------------------------------------------------------------------------------------
    @FXML
    void boutonListerTous() throws Exception {
        liste.clear();
        liste.setText(App.lister("tout"));    
        visibilite("L");

        String texte = "[Radio Snack]";
        texte += "                                                    ";
        texte += "Le total de votre inventaire est de ";
        Double total = App.total;
        texte += String.format("%.2f",total) + "$";
        labelTitlebar.setText(texte);
    }

    @FXML
    void boutonAjouter(MouseEvent event) throws Exception {
        visibilite("A");
        labelProchainNum.setText("" + (App.dernierNumero + 1000));
    }

    @FXML
    void boutonSupprimer(MouseEvent event) throws Exception {
        visibilite("S");
    }
    @FXML
    void boutonSupprimerVrai(MouseEvent event) throws Exception {
        if (texteFieldNo.getText() == null) { return; }
        boutonOui.setVisible(true);
        boutonNon.setVisible(true);
        ouinon.setVisible(true);
    }

    @FXML
    void boutonModifier(MouseEvent event) throws Exception {
        visibilite("M");
    }

    @FXML
    void boutonSauvegarder(MouseEvent event) throws Exception {

        // Code pour ajouter une nouvelle radio :
        if (modifOuAjout == "A") {
            String options = "";
            options += (check1.isSelected() ? "1" : "0");
            options += (check2.isSelected() ? "1" : "0");
            options += (check3.isSelected() ? "1" : "0");
            App.ajouterTout((App.dernierNumero + 1000), texteFieldMarque.getText(), texteFieldModele.getText(), options, Double.parseDouble(texteFieldPrix.getText()));
            boutonListerTous();
        }

        // Code pour modifier les options :
        else {
            // check1.setSelected(true) ..................... 

            String options = "";
            options += (check1.isSelected() ? "1" : "0");
            options += (check2.isSelected() ? "1" : "0");
            options += (check3.isSelected() ? "1" : "0");
            App.modifierOptions(Integer.parseInt(texteFieldNo.getText()), options);
            boutonListerTous();
        }

    }

    @FXML
    void boutonOui(MouseEvent event) throws Exception {
        App.supprimer(Integer.parseInt(texteFieldNo.getText()));
        boutonListerTous();
    }

    @FXML
    void boutonNon(MouseEvent event) throws Exception {
        boutonOui.setVisible(false);
        boutonNon.setVisible(false);
        ouinon.setVisible(false);
        
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
        texteFieldNo.textProperty().addListener((observable, oldValue, newValue) -> {
            boutonOui.setVisible(false);
            boutonNon.setVisible(false);
            ouinon.setVisible(false);
            try {
                if (App.verifierNoExistant(newValue) == true) {
                    // modif options :
                    boutonSauvegarder.setVisible((modifOuAjout == "M") ? true : false);
                    labelOptions.setVisible((modifOuAjout == "M") ? true : false);
                    check1.setVisible((modifOuAjout == "M") ? true : false);
                    check2.setVisible((modifOuAjout == "M") ? true : false);
                    check3.setVisible((modifOuAjout == "M") ? true : false);
                    check1.setSelected((App.optionsTemp.charAt(0) == '0') ? false : true);
                    check2.setSelected((App.optionsTemp.charAt(1) == '0') ? false : true);
                    check3.setSelected((App.optionsTemp.charAt(2) == '0') ? false : true);


                    // supprimer radio :
                    boutonSupprimerVrai.setVisible((modifOuAjout == "S") ? true : false);
                }
                else {
                    boutonOui.setVisible(false);
                    boutonNon.setVisible(false);
                    ouinon.setVisible(false);
                    boutonSupprimerVrai.setVisible(false);
                    labelOptions.setVisible(false);
                    check1.setVisible(false);
                    check2.setVisible(false);
                    check3.setVisible(false);
                    boutonSauvegarder.setVisible(false);
                }
            } catch (Exception e) { }
        });
    }


    // ----------------------------------------------------------------------------------------------------------------------------
    // AUTRES MÉTHODES NON-FXML
    // ----------------------------------------------------------------------------------------------------------------------------

    // "L"=Liste, "A"=Ajouter, "S"=Supprimer, "M"=Modifier, 
    public void visibilite(String mode) {

        modifOuAjout = (mode == "M") ? mode : (mode == "A") ? mode : (mode == "S") ? mode : "";

        texteFieldNo.setText(null);
        texteFieldMarque.setText(null);
        texteFieldModele.setText(null);
        texteFieldPrix.setText(null);

        liste.setVisible((mode == "L") ? true : false);
        texteFieldNo.setVisible((mode == "S" || mode == "M") ? true : false);
        texteFieldMarque.setVisible((mode == "A") ? true : false);
        texteFieldModele.setVisible((mode == "A") ? true : false);
        texteFieldPrix.setVisible((mode == "A") ? true : false);
        labelOptions.setVisible((mode == "A") ? true : false);
        labelModele.setVisible((mode == "A") ? true : false);
        labelNo.setVisible((mode == "A" || mode == "S" || mode == "M") ? true : false);
        labelPrix.setVisible((mode == "A") ? true : false);
        labelMarque.setVisible((mode == "A") ? true : false);
        boutonAjouter.setVisible(true);
        boutonSupprimer.setVisible(true);
        boutonModifier.setVisible(true);

        boutonOui.setVisible(false);
        boutonNon.setVisible(false);
        ouinon.setVisible(false);
        boutonSupprimerVrai.setVisible(false);

        labelProchainNum.setVisible((mode == "A") ? true : false);
        boutonSauvegarder.setVisible((mode == "A") ? true : false);
        check1.setVisible((mode == "A") ? true : false);
        check2.setVisible((mode == "A") ? true : false);
        check3.setVisible((mode == "A") ? true : false);
        check1.setSelected(false);
        check2.setSelected(false);
        check3.setSelected(false);

        fleche1.setVisible((mode == "A") ? true : false);
        fleche2.setVisible((mode == "S") ? true : false);
        fleche3.setVisible((mode == "M") ? true : false);

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