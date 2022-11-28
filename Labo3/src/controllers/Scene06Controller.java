package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class Scene06Controller {

    private Scene00Controller scene00Controller;
    
    public void injectScene00Controller(Scene00Controller scene00Controller) {
        this.scene00Controller = scene00Controller;
    }

    @FXML
    private ImageView ImgVLoading;

    @FXML
    private Button btnRefreshTblView06;

    @FXML
    private AnchorPane scene06;

    @FXML
    private TableView<?> tableView;

    @FXML
    private TableColumn<?, ?> tableView_Col01;

    @FXML
    private TableColumn<?, ?> tableView_Col02;

    @FXML
    void btnRefreshTblView06(ActionEvent event) {

    }

}
