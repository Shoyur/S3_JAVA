package labo3;

import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        // Scene scene = new Scene(loadFXML("primary"), 640, 480);
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("views/MainView.fxml")));
        stage.setScene(scene);
        stage.getIcons().add(new Image(getClass().getResource("images/frameIcon.png").toExternalForm()));
        stage.setTitle("[Gestionnaire pour Suzy la disquaire]");
        stage.show();
    }

    public static void main(String[] args) { launch(); }

}