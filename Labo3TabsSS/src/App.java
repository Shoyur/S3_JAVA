import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application { 

    
    // Un Stage = une window du OS.
    // Un Scene = un conteneur pour les objets à mettre.
    @Override
    public void start(Stage stagePrincipal) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("scenePrincipale.fxml")));
        stagePrincipal.setScene(scene);
        scene.getStylesheets().add("style.css");
        stagePrincipal.getIcons().add(new Image("medias/frameIcon.png"));
        stagePrincipal.setTitle("[Gestionnaire pour Suzy la disquaire]");
        stagePrincipal.show();
    }
    public static void main(String[] args) { Application.launch(args); }
}
