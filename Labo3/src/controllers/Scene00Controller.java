package controllers;

import java.net.URL;

import java.sql.Timestamp;
// import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
// import javafx.fxml.Initializable;
// import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
// import javafx.scene.layout.AnchorPane;
import models.Historique;

public class Scene00Controller {

    @FXML private Scene01Controller scene01Controller;
    @FXML private Scene02Controller scene02Controller;
    @FXML private Scene03Controller scene03Controller;
    @FXML private Scene04Controller scene04Controller;
    @FXML private Scene05Controller scene05Controller;
    @FXML private Scene06Controller scene06Controller;
    @FXML private Scene07Controller scene07Controller;
    @FXML private Scene08Controller scene08Controller;
    @FXML private Scene09Controller scene09Controller;

    @FXML
    private Tab 
        tab01, tab02, tab03, tab04, tab05, 
        tab06, tab07, tab08, tab09, tab10;

    @FXML
    private TabPane scene00;

    // SingleSelectionModel<Tab> selectionModel = scene00.getSelectionModel();

    
    public void switchTab(int tab) {
        scene00.getSelectionModel().select(tab);
    }

    // void ajouterHistorique(Timestamp quand, String quoi) {
    //     // Thread async_ajouterHistorique = new Thread(() -> {
    //         (HistoriqueController.getControleurH()).CtrH_create(new Historique(quand, quoi));
    //         scene08Controller.refreshTblView();
    //     // });
    //     // async_ajouterHistorique.start();
    // }

    @FXML private void initialize() {
        scene01Controller.injectScene00Controller(this);        
        scene02Controller.injectScene00Controller(this);        
        scene03Controller.injectScene00Controller(this);     
        scene04Controller.injectScene00Controller(this);     
        scene05Controller.injectScene00Controller(this);     
        scene06Controller.injectScene00Controller(this);     
        scene07Controller.injectScene00Controller(this);     
        scene08Controller.injectScene00Controller(this);     
        scene09Controller.injectScene00Controller(this);         
    }

}
