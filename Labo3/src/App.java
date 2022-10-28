// import javax.swing.JOptionPane;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
// import javafx.scene.paint.Color;
import javafx.stage.Stage;
// import javafx.stage.StageStyle;

public class App extends Application { 

    // ---------------------------------------------------------------------------------------------------------------
    // POUR INITIALISER LE FXML ET CHARGER LA FENÊTRE ETC. + LE MAIN. -> launch() est une méthode de "JavaFX.Application".
    // ---------------------------------------------------------------------------------------------------------------
    @Override
    public void start(Stage stagePrincipal) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scenePrincipale.fxml"));
        Scene scene = new Scene(root);
        // scene.setFill(Color.TRANSPARENT);
        // scene.getStylesheets().add("style.css");
        stagePrincipal.setScene(scene);
        stagePrincipal.getIcons().add(new Image("medias/frameIcon.png"));
        stagePrincipal.setTitle("[Gestionnaire pour Suzy la disquaire]");
        // stagePrincipal.setResizable(false);
        // stagePrincipal.initStyle(StageStyle.TRANSPARENT);
        stagePrincipal.show();
    }

    public static void main(String[] args) { launch(args); }

}
