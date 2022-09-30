import java.io.File;
import java.io.IOException;

import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ScenePrincipaleController {
    
    @FXML
    public ComboBox<String> comboCateg;

    @FXML
    private ComboBox<Integer> comboNumLivre;

    @FXML
    private ComboBox<Integer> comboNumAuteur;

    @FXML
    private TextArea liste;

    @FXML
    void boutonListerTous(ActionEvent event) throws IOException {
        liste.clear();
        liste.setText(App.lister("tout", null));
        liste.setVisible(true);
        comboCateg.getSelectionModel().clearSelection();
        comboCateg.setValue(null);
        comboNumLivre.getSelectionModel().clearSelection();
        comboNumLivre.setValue(null);
        comboNumAuteur.getSelectionModel().clearSelection();
        comboNumAuteur.setValue(null);
        
    }
    
    // ----------------------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------------------
    @FXML
    void comboClicCateg(ActionEvent event) throws IOException {

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
            liste.setVisible(true);
        }
    }

    @FXML
    void comboClicNumLivre(ActionEvent event) throws IOException {
        
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

        if (comboNumLivre.getValue() != null) {
            liste.setText(App.lister("livre", comboNumLivre.getValue()));
            liste.setVisible(true);
        }
        
    }
    
    @FXML
    void comboClicNumAuteur(ActionEvent event) throws IOException {

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
            liste.setVisible(true);
        }
    }
    // ----------------------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------------------
    // ----------------------------------------------------------------------------------------------------------------------------

    @FXML
    void boutonAjouterLivre(ActionEvent event) throws IOException {
        liste.setVisible(false);
        comboCateg.getSelectionModel().clearSelection();
        comboCateg.setValue(null);
        comboNumLivre.getSelectionModel().clearSelection();
        comboNumLivre.setValue(null);
        comboNumAuteur.getSelectionModel().clearSelection();
        comboNumAuteur.setValue(null);
        
    }

    public void combosPop() throws IOException {
        List troisListes[] = App.listesCombos();
        comboCateg.getItems().clear();
        comboCateg.getItems().addAll(troisListes[0]);
        comboNumLivre.getItems().clear();
        comboNumLivre.getItems().addAll(troisListes[1]);
        comboNumAuteur.getItems().clear();
        comboNumAuteur.getItems().addAll(troisListes[2]);
    }

    public File choixFichier(String extension) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter(extension, extension));
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        File f = fc.showOpenDialog(null);
        return f;
    }


    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() throws IOException {
        // assert comboNumAuteur != null : "fx:id=\"comboNumAuteur\" was not injected: check your FXML file 'ScenePrincipale.fxml'.";
        // assert comboNumLivre != null : "fx:id=\"comboNumLivre\" was not injected: check your FXML file 'ScenePrincipale.fxml'.";
        // assert liste != null : "fx:id=\"liste\" was not injected: check your FXML file 'ScenePrincipale.fxml'.";
        combosPop();

    }

}