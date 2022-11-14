package controllers;

import models.Exemplaire;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;


public class Scene01Controller implements Initializable {

    @FXML private TableView<Exemplaire> tableView01;

    @FXML private TableColumn<Exemplaire, Integer> tableView01_Col01;
    @FXML private TableColumn<Exemplaire, String> tableView01_Col02;
    @FXML private TableColumn<Exemplaire, String> tableView01_Col03;
    @FXML private TableColumn<Exemplaire, Integer> tableView01_Col04;
    @FXML private TableColumn<Exemplaire, String> tableView01_Col05;
    @FXML private TableColumn<Exemplaire, Boolean> tableView01_Col06;

    @FXML private Button btnRefreshTblView01;

    @FXML private ImageView ImgVLoading01;

    @FXML private TextField tblViewFilterAlbum, tblViewFilterArtiste, tblViewFilterAnnee;

    private ObservableList<Exemplaire> exemplaires = FXCollections.observableArrayList();

    
    @Override public void initialize(URL arg0, ResourceBundle arg1) {
        tableView01_Col01.setCellValueFactory(new PropertyValueFactory<Exemplaire, Integer>("Id"));
        tableView01_Col02.setCellValueFactory(new PropertyValueFactory<Exemplaire, String>("Album"));
        tableView01_Col03.setCellValueFactory(new PropertyValueFactory<Exemplaire, String>("Artiste"));
        tableView01_Col04.setCellValueFactory(new PropertyValueFactory<Exemplaire, Integer>("Annee"));
        tableView01_Col05.setCellValueFactory(new PropertyValueFactory<Exemplaire, String>("Genre"));
        tableView01_Col06.setCellValueFactory(new PropertyValueFactory<Exemplaire, Boolean>("Possession"));
        refreshTblView01();
        tblViewFilterAlbum.textProperty().addListener((observable, avant, apres) -> {
            tableView01.setItems(ListeExemplairesFiltree(exemplaires)); 
        });
        tblViewFilterArtiste.textProperty().addListener((observable, avant, apres) -> {
            tableView01.setItems(ListeExemplairesFiltree(exemplaires)); 
        });
        tblViewFilterAnnee.textProperty().addListener((observable, avant, apres) -> {
            tableView01.setItems(ListeExemplairesFiltree(exemplaires)); 
        });
    }

    @FXML void btnRefreshTblView01(ActionEvent event) {
        refreshTblView01();
    }

    void refreshTblView01() {
        ImgVLoading01.setVisible(true);
        tblViewFilterAlbum.setText(null);
        tblViewFilterArtiste.setText(null);
        tblViewFilterAnnee.setText(null);
        Thread async_refreshTblView01 = new Thread(() -> {
            exemplaires = (ExemplaireController.getControleurE()).CtrL_readAll();
            tableView01.setItems(exemplaires);
            ImgVLoading01.setVisible(false);
        });
        async_refreshTblView01.start();
    }

    private ObservableList<Exemplaire> ListeExemplairesFiltree(List<Exemplaire> list){
        List<Exemplaire> listeExemplairesFiltree = new ArrayList<>();
        for (Exemplaire exemplaire : list){
            if (rechercheExemplaireFiltre(exemplaire)) { listeExemplairesFiltree.add(exemplaire); }
        }
        return FXCollections.observableList(listeExemplairesFiltree);
    }

    private boolean rechercheExemplaireFiltre(Exemplaire exemplaire) {
        boolean resultat = true;
        if (tblViewFilterAlbum.getText() != null && 
            !tblViewFilterAlbum.getText().isEmpty() && 
            !exemplaire.getAlbum().toLowerCase().contains(tblViewFilterAlbum.getText().toLowerCase())) { 
                resultat = false; 
            }
        else if (tblViewFilterArtiste.getText() != null && 
            !tblViewFilterArtiste.getText().isEmpty() && 
            !exemplaire.getArtiste().toLowerCase().contains(tblViewFilterArtiste.getText().toLowerCase())) { 
                resultat = false;
            }
        else if (tblViewFilterAnnee.getText() != null && 
            !tblViewFilterAnnee.getText().isEmpty() && 
            !Integer.valueOf(exemplaire.getAnnee()).toString().contains(tblViewFilterAnnee.getText())) { 
                resultat = false; 
            }
        return resultat;
    }
}