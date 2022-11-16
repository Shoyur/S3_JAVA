import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ScenePrincipaleController implements Initializable{

    double mouseX = 0;
    double mouseY = 0;

    @FXML
    private AnchorPane anchor;

    @FXML
    private ComboBox<Integer> comboNo;

    @FXML
    private TextField texteFieldNo, texteFieldNom, texteFieldAutomne, texteFieldHiver;

    @FXML
    private Button boutonAjouter, boutonEffacer, boutonModifier, boutonListerTous, boutonStats;

    @FXML
    private TextArea liste;

    @FXML
    private Hyperlink lienMini, lienQuitter;


    @FXML
    void boutonAjouter(ActionEvent event) {
        FenetreDev.ajouter(
            Integer.parseInt(texteFieldNo.getText()), 
            texteFieldNom.getText(), 
            Integer.parseInt(texteFieldAutomne.getText()), 
            Integer.parseInt(texteFieldHiver.getText()));
            refreshCombo();
    }

    @FXML
    void boutonEffacer(ActionEvent event) {
        FenetreDev.effacer(Integer.parseInt(texteFieldNo.getText()));
        refreshCombo();
    }

    @FXML
    void boutonModifier(ActionEvent event) {
        FenetreDev.modifier(
            Integer.parseInt(texteFieldNo.getText()), 
            texteFieldNom.getText(), 
            Integer.parseInt(texteFieldAutomne.getText()), 
            Integer.parseInt(texteFieldHiver.getText()));
        refreshCombo();
    }

    @FXML
    void boutonListerTous(ActionEvent event) {
        liste.setText(FenetreDev.afficherTous());
    }

    @FXML
    void boutonStats(ActionEvent event) {
        liste.setText(FenetreDev.afficherStats());
    }

    @FXML
    void comboNo(ActionEvent event) {
        if (comboNo.getValue() == null) { return; }
        for (Professeur unProfesseur : FenetreDev.listeProfs) {
            if (unProfesseur.getNo() == comboNo.getValue()) {
                texteFieldNo.setText(Integer.toString(unProfesseur.getNo()));
                texteFieldNom.setText(unProfesseur.getNom());
                texteFieldAutomne.setText(Integer.toString(unProfesseur.getAutomne()));
                texteFieldHiver.setText(Integer.toString(unProfesseur.getHiver()));
                return;
            }
        }
    }

    void refreshCombo() {
        comboNo.getSelectionModel().clearSelection();
        comboNo.getItems().clear();
        texteFieldNo.setText(null);
        texteFieldNom.setText(null);
        texteFieldAutomne.setText(null);
        texteFieldHiver.setText(null);
        for (Professeur unProfesseur : FenetreDev.listeProfs) {
            comboNo.getItems().add(unProfesseur.getNo());
        }
    }
        


    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node)event.getSource();
        Stage stage = (Stage)node.getScene().getWindow();
        stage.setX(event.getScreenX() - mouseX);
        stage.setY(event.getScreenY() - mouseY);
    }

    @FXML
    void mini(ActionEvent event) {
        Stage stage = (Stage)anchor.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void pressed(MouseEvent event) {
        mouseX = event.getSceneX();
        mouseY = event.getSceneY();
    }

    @FXML
    void quitter(ActionEvent event) {
        System.exit(0);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FenetreDev.initialiser();
        refreshCombo();
    }

}
