package controllers;

import models.Exemplaire;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;


public class Scene01Controller implements Initializable {
    
    private Scene00Controller scene00Controller;
    
    public void injectScene00Controller(Scene00Controller scene00Controller) {
        this.scene00Controller = scene00Controller;
    }

    @FXML private TableView<Exemplaire> tableView01;

    @FXML private TableColumn<Exemplaire, Integer> tableView01_Col01;
    @FXML private TableColumn<Exemplaire, String> tableView01_Col02;
    @FXML private TableColumn<Exemplaire, String> tableView01_Col03;
    @FXML private TableColumn<Exemplaire, String> tableView01_Col04;
    @FXML private TableColumn<Exemplaire, Integer> tableView01_Col05;
    @FXML private TableColumn<Exemplaire, Boolean> tableView01_Col06;

    @FXML private Button btnRefreshTblView01;

    @FXML private ImageView ImgVLoading01;

    @FXML private TextField tblViewFilterAlbum, tblViewFilterArtiste, tblViewFilterAnnee, tblViewFilterGenre;

    private ObservableList<Exemplaire> exemplaires = FXCollections.observableArrayList();

    
    @Override public void initialize(URL arg0, ResourceBundle arg1) {
        // Exemplaire(int idEx, String titreEx, String artisteEx, String categEx, int anneeEx, double prixEx,
        //     String pistesEx, int nbEmpruntsEx, boolean estEmprunte, boolean estVendu) 
        tableView01_Col01.setCellValueFactory(new PropertyValueFactory<Exemplaire, Integer>("idEx"));
        tableView01_Col02.setCellValueFactory(new PropertyValueFactory<Exemplaire, String>("titreEx"));
        tableView01_Col03.setCellValueFactory(new PropertyValueFactory<Exemplaire, String>("artisteEx"));
        tableView01_Col04.setCellValueFactory(new PropertyValueFactory<Exemplaire, String>("categEx"));
        tableView01_Col05.setCellValueFactory(new PropertyValueFactory<Exemplaire, Integer>("anneeEx"));
        tableView01_Col06.setCellValueFactory(new PropertyValueFactory<Exemplaire, Boolean>("estEmprunte"));
        tableView01_Col06.setCellFactory(tableCell -> new TableCell<Exemplaire, Boolean>() {
            @Override
            protected void updateItem(Boolean item, boolean vide) {
                super.updateItem(item, vide);
                setText(vide ? null : item.booleanValue() ? "OUI" : "");
            }
        });
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
        tblViewFilterGenre.textProperty().addListener((observable, avant, apres) -> {
            tableView01.setItems(ListeExemplairesFiltree(exemplaires)); 
        });

        tableView01.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    if (tableView01.getSelectionModel().getSelectedItem() != null) {
                        scene00Controller.afficherExSel(tableView01.getSelectionModel().getSelectedItem());
                        scene00Controller.switchTab(1);
                    }  
                }
            }
        });
        
    }

    @FXML void btnRefreshTblView01(ActionEvent event) {
        ImgVLoading01.setVisible(true);
        tblViewFilterAlbum.setText(null);
        tblViewFilterArtiste.setText(null);
        tblViewFilterAnnee.setText(null);
        tblViewFilterGenre.setText(null);
        Thread async_refreshTblView01 = new Thread(() -> {
            try { 
                Thread.sleep(500);
                exemplaires = (ExemplaireController.getControleurEx()).CtrEx_readAll(0);
                tableView01.setItems(exemplaires); 
            }  
            catch (InterruptedException e) { e.printStackTrace(); } 
            ImgVLoading01.setVisible(false);
        });
        async_refreshTblView01.start();
    }

    public void refreshTblView01() {
        Thread async_refreshTblView01 = new Thread(() -> {
                exemplaires = (ExemplaireController.getControleurEx()).CtrEx_readAll(0);
                Platform.runLater(() -> { 
                    this.tableView01.setItems(exemplaires);
                    this.tblViewFilterAlbum.setText(null);
                    this.tblViewFilterArtiste.setText(null);
                    this.tblViewFilterAnnee.setText(null);
                    this.tblViewFilterGenre.setText(null);  
                });

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
            !exemplaire.getTitreEx().toLowerCase().contains(tblViewFilterAlbum.getText().toLowerCase())) { 
                resultat = false; 
            }
        else if (tblViewFilterArtiste.getText() != null && 
            !tblViewFilterArtiste.getText().isEmpty() && 
            !exemplaire.getArtisteEx().toLowerCase().contains(tblViewFilterArtiste.getText().toLowerCase())) { 
                resultat = false;
            }
        else if (tblViewFilterAnnee.getText() != null && 
            !tblViewFilterAnnee.getText().isEmpty() && 
            !Integer.valueOf(exemplaire.getAnneeEx()).toString().contains(tblViewFilterAnnee.getText())) { 
                resultat = false; 
            }
        else if (tblViewFilterGenre.getText() != null && 
            !tblViewFilterGenre.getText().isEmpty() && 
            !exemplaire.getCategEx().toLowerCase().contains(tblViewFilterGenre.getText().toLowerCase())) { 
                resultat = false;
            }    
        return resultat;
    }
}