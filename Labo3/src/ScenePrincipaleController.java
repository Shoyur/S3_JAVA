
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

public class ScenePrincipaleController implements Initializable {

    @FXML
    private BorderPane mainPanel;

    @FXML
    private Button nav_butt_1, nav_butt_2, nav_butt_3, nav_butt_4, nav_butt_5,
                nav_butt_6, nav_butt_7, nav_butt_8, nav_butt_9, nav_butt_10;


    @FXML
    void nav_butt_1_action(ActionEvent event) throws Exception {
        mainPanel.setCenter((new FXMLLoader(getClass().getResource("/vues/Scene1.fxml"))).load());
    }

    @FXML
    void nav_butt_2_action(ActionEvent event) throws Exception {
        mainPanel.setCenter((new FXMLLoader(getClass().getResource("/vues/Scene2.fxml"))).load());

    }

    @FXML
    void nav_butt_3_action(ActionEvent event) throws Exception {
        mainPanel.setCenter((new FXMLLoader(getClass().getResource("/vues/Scene3.fxml"))).load());
    }

    @FXML
    void nav_butt_4_action(ActionEvent event) throws Exception {
        mainPanel.setCenter((new FXMLLoader(getClass().getResource("/vues/Scene4.fxml"))).load());
    }

    @FXML
    void nav_butt_5_action(ActionEvent event) throws Exception {
        mainPanel.setCenter((new FXMLLoader(getClass().getResource("/vues/Scene5.fxml"))).load());
    }

    @FXML
    void nav_butt_6_action(ActionEvent event) throws Exception {
        mainPanel.setCenter((new FXMLLoader(getClass().getResource("/vues/Scene6.fxml"))).load());
    }

    @FXML
    void nav_butt_7_action(ActionEvent event) throws Exception {
        mainPanel.setCenter((new FXMLLoader(getClass().getResource("/vues/Scene7.fxml"))).load());
    }

    @FXML
    void nav_butt_8_action(ActionEvent event) throws Exception {
        mainPanel.setCenter((new FXMLLoader(getClass().getResource("/vues/Scene8.fxml"))).load());
    }

    @FXML
    void nav_butt_9_action(ActionEvent event) throws Exception {
        mainPanel.setCenter((new FXMLLoader(getClass().getResource("/vues/Scene9.fxml"))).load());
    }

    @FXML
    void nav_butt_10_action(ActionEvent event) throws Exception {
        mainPanel.setCenter((new FXMLLoader(getClass().getResource("/vues/Scene10.fxml"))).load());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        
    }

}
