// import org.scenicview.ScenicView;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application { 
    @Override
    public void start(Stage stagePrincipal) throws Exception {
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("scenePrincipale.fxml")));
        stagePrincipal.setScene(scene);
        stagePrincipal.getIcons().add(new Image("medias/frameIcon.png"));
        stagePrincipal.setTitle("[Gestionnaire pour Suzy la disquaire]");
        stagePrincipal.show();
        // ScenicView.show(scene);
    }
    public static void main(String[] args) { launch(args); }
}
