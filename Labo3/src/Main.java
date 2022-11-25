import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application { 
    @Override
    public void start(Stage stagePrincipal) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("views/Scene00.fxml")));
        stagePrincipal.setScene(scene);
        stagePrincipal.getIcons().add(new Image("medias/frameIcon.png"));
        stagePrincipal.setTitle("[Gestionnaire pour Suzy la disquaire]");
        stagePrincipal.setResizable(false);
        stagePrincipal.show();
    }
    public static void main(String[] args) { launch(args); }
}
