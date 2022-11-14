package controllers;

import models.Usager;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;




public class Scene03Controller implements Initializable {

    @FXML private Button
        // Rafraichir le grand tableau
        btnRefreshTblView03,
        // Boutons droite pour ajouter.
        btnAjouterUsager, btnAjouterUsagerCancel,
        // Boutons gauche pour modifier ou copier.
        buttonUserSelCancel, buttonUserSelModifier,buttonUserSelCopier,
        // Boutons centre pour consulter infos de l'usagers.
        buttonUserSelEmprunts, buttonUserSelVentes, buttonUserSelRetards;

    @FXML private ImageView
        // Image chargement rafraichissement.
        ImgVLoading03;

    @FXML private TextField 
        // Barres de recherche.
        tblViewFilterNom, tblViewFilterPrenom,
        // Données usager sélectionné.
        textFieldUserSelNom, textFieldUserSelPrenom, textFieldUserSelAdresse, 
        textFieldUserSelCourriel, textFieldUserSelTelephone,
        // Données nouvel usager.
        newNom, newPrenom, newAdresse, newCourriel, newTelephone;

    @FXML private Label 
        // Erreurs rouges infos manquantes pour ajout user.
        labelAjoutUserErreur1, labelAjoutUserErreur2, labelAjoutUserErreur3, labelAjoutUserErreur4, labelAjoutUserErreur5,
        // Id numéro ussager sélectionné.
        labelUserSelId;
    
    @FXML private TextArea 
        // Données usager sélectionné.
        textAreaUserSelNotes,
        // Données nouvel usager.
        newNotes;

    @FXML private TableView<Usager> tableView03;
    @FXML private TableColumn<Usager, Integer> tableView03_Col01;
    @FXML private TableColumn<Usager, String> tableView03_Col02;
    @FXML private TableColumn<Usager, String> tableView03_Col03;
    @FXML private TableColumn<Usager, String> tableView03_Col04;
    @FXML private TableColumn<Usager, String> tableView03_Col05;
    @FXML private TableColumn<Usager, String> tableView03_Col06;
    @FXML private TableColumn<Usager, String> tableView03_Col07;

    // Liste populée par le serveur.
    private ObservableList<Usager> usagers = FXCollections.observableArrayList();

    

    @Override public void initialize(URL arg0, ResourceBundle arg1) {
        tableView03_Col01.setCellValueFactory(new PropertyValueFactory<Usager, Integer>("Id"));
        tableView03_Col02.setCellValueFactory(new PropertyValueFactory<Usager, String>("Nom"));
        tableView03_Col03.setCellValueFactory(new PropertyValueFactory<Usager, String>("Prenom"));
        tableView03_Col04.setCellValueFactory(new PropertyValueFactory<Usager, String>("Adresse"));
        tableView03_Col05.setCellValueFactory(new PropertyValueFactory<Usager, String>("Courriel"));
        tableView03_Col06.setCellValueFactory(new PropertyValueFactory<Usager, String>("Telephone"));
        tableView03_Col07.setCellValueFactory(new PropertyValueFactory<Usager, String>("Notes"));

        refreshTblView03();

        tblViewFilterNom.textProperty().addListener((observable, avant, apres) -> {
            tableView03.setItems(ListeUsagersFiltree(usagers)); 
        });
        tblViewFilterPrenom.textProperty().addListener((observable, avant, apres) -> {
            tableView03.setItems(ListeUsagersFiltree(usagers)); 
        });

        tableView03.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    labelUserSelId.setText(Integer.toString(tableView03.getSelectionModel().getSelectedItem().getId()));
                    textFieldUserSelNom.setText(tableView03.getSelectionModel().getSelectedItem().getNom());                
                    textFieldUserSelPrenom.setText(tableView03.getSelectionModel().getSelectedItem().getPrenom());                
                    textFieldUserSelAdresse.setText(tableView03.getSelectionModel().getSelectedItem().getAdresse());                
                    textFieldUserSelCourriel.setText(tableView03.getSelectionModel().getSelectedItem().getCourriel());                
                    textFieldUserSelTelephone.setText(tableView03.getSelectionModel().getSelectedItem().getTelephone());                
                    textAreaUserSelNotes.setText(tableView03.getSelectionModel().getSelectedItem().getNotes());                
                }
            }
        });

        

    }

    @FXML void btnRefreshTblView03(ActionEvent event) {
        refreshTblView03();
    }

    void refreshTblView03() {
        ImgVLoading03.setVisible(true);
        tblViewFilterNom.setText(null);
        tblViewFilterPrenom.setText(null);
        Thread async_refreshTblView03 = new Thread(() -> {
            usagers = (UsagerController.getControleurU()).CtrL_readAll();
            tableView03.setItems(usagers);
            ImgVLoading03.setVisible(false);
        });
        async_refreshTblView03.start();
        labelUserSelId.setText(null);
        textFieldUserSelNom.setText(null);                
        textFieldUserSelPrenom.setText(null);                
        textFieldUserSelAdresse.setText(null);                
        textFieldUserSelCourriel.setText(null);                
        textFieldUserSelTelephone.setText(null);                
        textAreaUserSelNotes.setText(null);    
    }    
    
    private ObservableList<Usager> ListeUsagersFiltree(List<Usager> list){
        List<Usager> listeUsagersFiltree = new ArrayList<>();
        for (Usager Usager : list){
            if (rechercheUsagerFiltre(Usager)) { listeUsagersFiltree.add(Usager); }
        }
        return FXCollections.observableList(listeUsagersFiltree);
    }

    private boolean rechercheUsagerFiltre(Usager Usager) {
        boolean resultat = true;
        if (tblViewFilterNom.getText() != null && 
            !tblViewFilterNom.getText().isEmpty() && 
            !Usager.getNom().toLowerCase().contains(tblViewFilterNom.getText().toLowerCase())) { 
                resultat = false; 
            }
        else if (tblViewFilterPrenom.getText() != null && 
            !tblViewFilterPrenom.getText().isEmpty() && 
            !Usager.getPrenom().toLowerCase().contains(tblViewFilterPrenom.getText().toLowerCase())) { 
                resultat = false;
            }
        return resultat;
    }

    @FXML void btnAjouterUsager(ActionEvent event) {
        cacherErreursAjouterUsager();
        boolean erreur = false;
        if (newNom.getText() == null || newNom.getText().isEmpty()) {
            labelAjoutUserErreur1.setVisible(true);
            erreur = true;
        }
        if (newPrenom.getText() == null || newPrenom.getText().isEmpty()) {
            labelAjoutUserErreur2.setVisible(true);
            erreur = true;
        }
        if (newAdresse.getText() == null || newAdresse.getText().isEmpty()) {
            labelAjoutUserErreur3.setVisible(true);
            erreur = true;
        }
        if (newCourriel.getText() == null || newCourriel.getText().isEmpty()) {
            labelAjoutUserErreur4.setVisible(true);
            erreur = true;
        }
        if (newTelephone.getText() == null || newTelephone.getText().isEmpty()) {
            labelAjoutUserErreur5.setVisible(true);
            erreur = true;
        }
        if (erreur == true) { return; }
        ImgVLoading03.setVisible(true);
        tblViewFilterNom.setText(null);
        tblViewFilterPrenom.setText(null);
        Usager usager = new Usager(0, newNom.getText(), newPrenom.getText(), newAdresse.getText(), newCourriel.getText(), newTelephone.getText(), newNotes.getText());
        Thread async_ajouterUsager = new Thread(() -> {
            (UsagerController.getControleurU()).CtrL_create(usager);
            refreshTblView03();

        });
        async_ajouterUsager.start();
        btnAjouterUsagerCancel(null);
    }

    @FXML void btnAjouterUsagerCancel(ActionEvent event) {
        cacherErreursAjouterUsager();
        newNom.setText(null);
        newPrenom.setText(null);
        newAdresse.setText(null);
        newCourriel.setText(null);
        newTelephone.setText(null);
        newNotes.setText(null);
    }

    void cacherErreursAjouterUsager() {
        labelAjoutUserErreur1.setVisible(false);
        labelAjoutUserErreur2.setVisible(false);
        labelAjoutUserErreur3.setVisible(false);
        labelAjoutUserErreur4.setVisible(false);
        labelAjoutUserErreur5.setVisible(false);
    }

    @FXML void buttonUserSelCancel(ActionEvent event) {

    }

    @FXML void buttonUserSelModifier(ActionEvent event) {

    }

    @FXML void buttonUserSelCopier(ActionEvent event) {
        newNom.setText(textFieldUserSelNom.getText());
        newPrenom.setText(textFieldUserSelPrenom.getText());
        newAdresse.setText(textFieldUserSelAdresse.getText());
        newCourriel.setText(textFieldUserSelCourriel.getText());
        newTelephone.setText(textFieldUserSelTelephone.getText());
        newNotes.setText(textAreaUserSelNotes.getText());
    }

    @FXML void buttonUserSelEmprunts(ActionEvent event) {
        // controllers.MainViewController.tabPane0.getSelectionModel().select();
        // try {
        //     Class.forName("MainViewController").getMethod("change");

        // } catch (NoSuchMethodException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (SecurityException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // } catch (ClassNotFoundException e) {
        //     // TODO Auto-generated catch block
        //     e.printStackTrace();
        // }
    }

    @FXML void buttonUserSelVentes(ActionEvent event) {

    }

    @FXML void buttonUserSelRetards(ActionEvent event) {

    }

}
