package controllers;

import models.Usager;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;




public class Scene03Controller implements Initializable {

    private Scene00Controller scene00Controller;
    
    public void injectScene00Controller(Scene00Controller scene00Controller) {
        this.scene00Controller = scene00Controller;
    }

    @FXML private Button
        // Rafraichir le grand tableau
        btnRefreshTblView03,
        // Boutons droite pour ajouter.
        btnAjouterUsager, btnAjouterUsagerCancel,
        // Boutons gauche pour modifier ou copier.
        buttonUserSelCancel, buttonUserSelModifier,buttonUserSelCopier,
        // Boutons centre pour créer une transaction ou consulter les infos de l'usagers.
        buttonUserSelTransaction, buttonUserSelEmprunts, buttonUserSelVentes, buttonUserSelRetards;

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
        // Id numéro usager sélectionné + date Abonnement.
        labelUserSelId, labelUserSelDate;
    
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

    // Liste populée par le serveur.
    @FXML
    private ObservableList<Usager> usagers = FXCollections.observableArrayList();

    
    @Override public void initialize(URL arg0, ResourceBundle arg1) {
    //     Usager(int idU, String nomU, String prenomU, String adresseU, String telephoneU, String courrielU,
    // Timestamp dateAbonneU, String notesU)
        tableView03_Col01.setCellValueFactory(new PropertyValueFactory<Usager, Integer>("idU"));
        tableView03_Col02.setCellValueFactory(new PropertyValueFactory<Usager, String>("nomU"));
        tableView03_Col03.setCellValueFactory(new PropertyValueFactory<Usager, String>("prenomU"));
        tableView03_Col04.setCellValueFactory(new PropertyValueFactory<Usager, String>("adresseU"));
        tableView03_Col05.setCellValueFactory(new PropertyValueFactory<Usager, String>("telephoneU"));
        tableView03_Col06.setCellValueFactory(new PropertyValueFactory<Usager, String>("courrielU"));

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
                    remplirUsagerSel();                
                }
            }
        });
        
    }

    void remplirUsagerSel() {
        if (tableView03.getSelectionModel().getSelectedItem() != null) {
            labelUserSelId.setText(Integer.toString(tableView03.getSelectionModel().getSelectedItem().getIdU()));
            textFieldUserSelNom.setText(tableView03.getSelectionModel().getSelectedItem().getNomU());                
            textFieldUserSelPrenom.setText(tableView03.getSelectionModel().getSelectedItem().getPrenomU());                
            textFieldUserSelAdresse.setText(tableView03.getSelectionModel().getSelectedItem().getAdresseU());
            textFieldUserSelTelephone.setText(tableView03.getSelectionModel().getSelectedItem().getTelephoneU());                  
            textFieldUserSelCourriel.setText(tableView03.getSelectionModel().getSelectedItem().getCourrielU());
            labelUserSelDate.setText((new SimpleDateFormat("yyyy-mm-dd hh:mm:ss")).format(tableView03.getSelectionModel().getSelectedItem().getDateAbonneU()));  
            textAreaUserSelNotes.setText(tableView03.getSelectionModel().getSelectedItem().getNotesU());
            textFieldUserSelNom.setDisable(false);
            textFieldUserSelPrenom.setDisable(false);
            textFieldUserSelAdresse.setDisable(false);
            textFieldUserSelTelephone.setDisable(false);
            textFieldUserSelCourriel.setDisable(false);
            textAreaUserSelNotes.setDisable(false);
            buttonUserSelCancel.setDisable(false);
            buttonUserSelModifier.setDisable(false);
            buttonUserSelCopier.setDisable(false);
            buttonUserSelTransaction.setDisable(false);
            buttonUserSelEmprunts.setDisable(false);
            buttonUserSelVentes.setDisable(false);
            buttonUserSelRetards.setDisable(false);
        }
    }

    @FXML void btnRefreshTblView03(ActionEvent event) {
        refreshTblView03();
    }

    void refreshTblView03() {
        ImgVLoading03.setVisible(true);
        tblViewFilterNom.setText(null);
        tblViewFilterPrenom.setText(null);
        Thread async_refreshTblView03 = new Thread(() -> {
            try { Thread.sleep(500); } 
            catch (InterruptedException e) { e.printStackTrace(); } 
            usagers = (UsagerController.getControleurU()).CtrU_readAll();
            tableView03.setItems(usagers);
            ImgVLoading03.setVisible(false);
        });
        async_refreshTblView03.start();
        labelUserSelId.setText(null);
        textFieldUserSelNom.setText(null);                
        textFieldUserSelPrenom.setText(null);                
        textFieldUserSelAdresse.setText(null);    
        textFieldUserSelTelephone.setText(null);              
        textFieldUserSelCourriel.setText(null);                
        textAreaUserSelNotes.setText(null);
        labelUserSelDate.setText(null);

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
            !Usager.getNomU().toLowerCase().contains(tblViewFilterNom.getText().toLowerCase())) { 
                resultat = false; 
            }
        else if (tblViewFilterPrenom.getText() != null && 
            !tblViewFilterPrenom.getText().isEmpty() && 
            !Usager.getPrenomU().toLowerCase().contains(tblViewFilterPrenom.getText().toLowerCase())) { 
                resultat = false;
            }
        return resultat;
    }

    @FXML void btnAjouterUsager(ActionEvent event) {
        cacherErreursAjouterUsager();
        boolean erreur = false;
        if (newNom.getText() == null || newNom.getText().isEmpty()) {
            labelAjoutUserErreur1.setVisible(true);
            if (erreur == false) { newNom.requestFocus(); }
            erreur = true;
        }
        if (newPrenom.getText() == null || newPrenom.getText().isEmpty()) {
            labelAjoutUserErreur2.setVisible(true);
            if (erreur == false) { newPrenom.requestFocus(); }
            erreur = true;
        }
        if (newAdresse.getText() == null || newAdresse.getText().isEmpty()) {
            labelAjoutUserErreur3.setVisible(true);
            if (erreur == false) { newAdresse.requestFocus(); }
            erreur = true;
        }
        if (newTelephone.getText() == null || newTelephone.getText().isEmpty()) {
            labelAjoutUserErreur4.setVisible(true);
            if (erreur == false) { newTelephone.requestFocus(); }
            erreur = true;
        }
        if (newCourriel.getText() == null || newCourriel.getText().isEmpty()) {
            labelAjoutUserErreur5.setVisible(true);
            if (erreur == false) { newCourriel.requestFocus(); }
            erreur = true;
        }
        if (erreur == true) { return; }
        ImgVLoading03.setVisible(true);
        tblViewFilterNom.setText(null);
        tblViewFilterPrenom.setText(null);
        Usager usager = new Usager(0, newNom.getText(), newPrenom.getText(), newAdresse.getText(), newTelephone.getText(), newCourriel.getText(), new Timestamp(System.currentTimeMillis()), newNotes.getText());
        Thread async_ajouterUsager = new Thread(() -> {
            (UsagerController.getControleurU()).CtrU_create(usager);
            Platform.runLater(() -> { refreshTblView03(); });
        });
        async_ajouterUsager.start();
        String texte = "L'usager " + newPrenom.getText() + " " + newNom.getText() + " a été ajouté.";
        scene00Controller.ajouterHistorique(new Timestamp(System.currentTimeMillis()), texte);
        btnAjouterUsagerCancel(null);
    }

    @FXML void btnAjouterUsagerCancel(ActionEvent event) {
        cacherErreursAjouterUsager();
        newNom.setText(null);
        newPrenom.setText(null);
        newAdresse.setText(null);
        newTelephone.setText(null);
        newCourriel.setText(null);
        newNotes.setText(null);
    }

    private void cacherErreursAjouterUsager() {
        labelAjoutUserErreur1.setVisible(false);
        labelAjoutUserErreur2.setVisible(false);
        labelAjoutUserErreur3.setVisible(false);
        labelAjoutUserErreur4.setVisible(false);
        labelAjoutUserErreur5.setVisible(false);
    }

    @FXML void buttonUserSelCancel(ActionEvent event) {
        remplirUsagerSel();
    }

    @FXML void buttonUserSelModifier(ActionEvent event) {
        ImgVLoading03.setVisible(true);
        Thread async_modifierUsager = new Thread(() -> {
            (UsagerController.getControleurU()).CtrU_update(
                textFieldUserSelNom.getText(), 
                textFieldUserSelPrenom.getText(), 
                textFieldUserSelAdresse.getText(), 
                textFieldUserSelTelephone.getText(), 
                textFieldUserSelCourriel.getText(), 
                textAreaUserSelNotes.getText(), 
                Integer.parseInt(labelUserSelId.getText()));
            Platform.runLater(() -> { refreshTblView03(); });
        });
        async_modifierUsager.start();
        String texte = "Le profil de l'usager " + textFieldUserSelPrenom.getText() + " " + textFieldUserSelNom.getText() + " a été modifié.";
        scene00Controller.ajouterHistorique(new Timestamp(System.currentTimeMillis()), texte);
    }

    @FXML void buttonUserSelCopier(ActionEvent event) {
        newNom.setText(textFieldUserSelNom.getText());
        newPrenom.setText(textFieldUserSelPrenom.getText());
        newAdresse.setText(textFieldUserSelAdresse.getText());
        newTelephone.setText(textFieldUserSelTelephone.getText());
        newCourriel.setText(textFieldUserSelCourriel.getText());
        newNotes.setText(textAreaUserSelNotes.getText());
    }

    @FXML void buttonUserSelTransaction(ActionEvent event) {
        scene00Controller.switchTab(4);
    }

    @FXML void buttonUserSelEmprunts(ActionEvent event) {
        scene00Controller.switchTab(3);
    }

    @FXML void buttonUserSelVentes(ActionEvent event) {

    }

    @FXML void buttonUserSelRetards(ActionEvent event) {

    }

}
