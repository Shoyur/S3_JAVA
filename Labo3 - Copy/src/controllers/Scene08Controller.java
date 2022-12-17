package controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;

import models.Historique;


public class Scene08Controller implements Initializable {
    
    private Scene00Controller scene00Controller;
    
    public void injectScene00Controller(Scene00Controller scene00Controller) {
        this.scene00Controller = scene00Controller;
    }

    @FXML private ImageView ImgVLoading;

    @FXML private Button btnRefreshTblView08;

    @FXML private TableView<Historique> tableView;
    @FXML private TableColumn<Historique, Timestamp> tableView_Col01;
    @FXML private TableColumn<Historique, String> tableView_Col02;

    private ObservableList<Historique> historique = FXCollections.observableArrayList();


    @FXML
    void btnRefreshTblView08(ActionEvent event) {
        ImgVLoading.setVisible(true);
        Thread async_refreshTblView08 = new Thread(() -> {
            try { Thread.sleep(500); } 
            catch (InterruptedException e) { e.printStackTrace(); } 
            historique = (HistoriqueController.getControleurH()).CtrH_readAll();
            tableView.setItems(historique);
            ImgVLoading.setVisible(false);
        });
        async_refreshTblView08.start();  
    }

    public void refreshTblView08() {
        Thread async_refreshTblView08 = new Thread(() -> {
            historique = (HistoriqueController.getControleurH()).CtrH_readAll();
            Platform.runLater(() -> { this.tableView.setItems(historique); });
        });
        async_refreshTblView08.start();  
    }   

    public void ajouterHistorique(Timestamp quand, String quoi) {
        Thread async_ajouterHistorique = new Thread(() -> {
            (HistoriqueController.getControleurH()).CtrH_create(new Historique(quand, quoi));
            Platform.runLater(() -> { refreshTblView08(); });
        });
        async_ajouterHistorique.start();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView_Col01.setCellValueFactory(new PropertyValueFactory<Historique, Timestamp>("quand"));
        tableView_Col01.setCellFactory(tableCell -> new TableCell<Historique, Timestamp>() {
            @Override
            protected void updateItem(Timestamp timestamp, boolean vide) {
                super.updateItem(timestamp, vide);
                if (vide) { setText(null); }
                else { setText(new SimpleDateFormat("yyyy-MM-dd    HH:mm").format(timestamp)); }
            }
        });
        tableView_Col02.setCellValueFactory(new PropertyValueFactory<Historique, String>("quoi"));
        refreshTblView08();
    }

}
