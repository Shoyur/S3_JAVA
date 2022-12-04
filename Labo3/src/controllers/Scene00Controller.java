package controllers;

import java.sql.Timestamp;

import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import models.Exemplaire;


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

    public void switchTab(int tab) {
        scene00.getSelectionModel().select(tab);
    }

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

    public void afficherExSel(Exemplaire exemplaire) {
        scene02Controller.afficherExSel(exemplaire);
    }

    public void ajouterHistorique(Timestamp quand, String quoi) {
        scene08Controller.ajouterHistorique(quand, quoi);
    }

    public void refreshTblView01() {
        scene01Controller.refreshTblView01();
    }
    
    public void transactionAfficherHistorique(int idU) {
    	scene05Controller.afficherTransactionHistorique(idU);
    }

    public void transactionAjoutVente(int idEx) {
    	scene05Controller.ajouterVente(idEx);
    }

    public void transactionAjoutEmprunt(int idEx) {
    	scene05Controller.ajouterEmprunt(idEx);
    }
    

}
