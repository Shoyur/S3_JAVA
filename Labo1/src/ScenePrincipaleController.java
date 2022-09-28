
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ScenePrincipaleController {

    @FXML
    private TextFlow liste;

    @FXML
    void boutonListerTous(ActionEvent event) {
        liste.getChildren().addAll(new Text(new App().toutLister()));
    }
}