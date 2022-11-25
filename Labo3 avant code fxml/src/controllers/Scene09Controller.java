package controllers;

import java.util.Date;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import models.Historique;


public class Scene09Controller {

    @FXML
    private ImageView ImgVLoading;

    @FXML
    private Button btnRefreshTblView;

    @FXML
    private TableView<Historique> tableView;

    @FXML
    private TableColumn<Historique, Date> tableView_Col01;

    @FXML
    private TableColumn<Historique, String> tableView_Col02;

    @FXML
    private TextField tblViewFilterTout;

    @FXML
    void btnRefreshTblView(ActionEvent event) {

    }

}
