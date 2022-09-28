import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

public class ScenePrincipaleController {

    @FXML
    public static TextFlow liste;

    @FXML
    private static void boutonListerTous() {
            // App main = new App();
            // String texteTest = main.test1();
            // System.out.println(texteTest);
            // Text texte = new Text(texteTest);
            // liste.getChildren().addAll(texte);

            Text texte = new Text("TEST 1 2 \t\t tab tab");
            liste.getChildren().addAll(texte);

    }
}

