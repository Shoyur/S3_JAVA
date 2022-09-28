import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class App extends Application {

    @Override
    public void start(Stage scenePrincipale) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ScenePrincipale.fxml"));
            Scene scene = new Scene(root);
            scenePrincipale.setScene(scene);
        } catch (Exception e) {}
        scenePrincipale.getIcons().add(new Image("medias/frameIcon.png"));
        scenePrincipale.setTitle("[Gestionnaire de bibliothèque]");
        scenePrincipale.show();

    }

    public String toutLister() {
        return "test1 retourné !!!";
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
