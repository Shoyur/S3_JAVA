
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class ScenePrincipaleController implements Initializable {

    App app = new App();

    @FXML
    private TextFlow liste;

    @FXML
    void boutonListerTous(ActionEvent event) {
        Text texte = new Text(app.toutLister());
        texte.setFont(Font.font("Calibri", FontPosture.REGULAR, 13));
        liste.getChildren().clear();
        liste.getChildren().addAll(texte);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("*.txt", "*.txt"));
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        File f = fc.showOpenDialog(null);
        if (f != null) {
            App.fichier_txt = f.getAbsolutePath();
        }
        else {
            String titre = "Erreur";
            String message = "\nVous deviez absolument sélectionner un fichier texte compatible.\n";
            message += "Ce programme va s'arrêter.\n";
            JOptionPane.showMessageDialog(null, message, titre, JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
        
    }

}