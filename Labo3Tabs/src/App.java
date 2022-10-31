import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application { 
    @Override
    public void start(Stage stagePrincipal) throws Exception {
        stagePrincipal.setScene(new Scene(FXMLLoader.load(getClass().getResource("scenePrincipale.fxml"))));
        stagePrincipal.getIcons().add(new Image("medias/frameIcon.png"));
        stagePrincipal.setTitle("[Gestionnaire pour Suzy la disquaire]");
        stagePrincipal.show();
    }
    public static void main(String[] args) { Application.launch(args); }
}
