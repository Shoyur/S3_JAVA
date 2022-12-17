package controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Emprunt;
import models.Exemplaire;
import models.Usager;
import models.Vente;

public class Scene04Controller implements Initializable {
    
    private Scene00Controller scene00Controller;
    
    public void injectScene00Controller(Scene00Controller scene00Controller) {
        this.scene00Controller = scene00Controller;
    }
    
    @FXML private TableView<Emprunt> tableView01;
    @FXML private TableColumn<Emprunt, Integer> tableView01_Col01;
    @FXML private TableColumn<Emprunt, Integer> tableView01_Col02;
    @FXML private TableColumn<Emprunt, Integer> tableView01_Col03;
    @FXML private TableColumn<Emprunt, Timestamp> tableView01_Col04;
    @FXML private TableColumn<Emprunt, Integer> tableView01_Col05;
    
    @FXML private Label lblEmpruntChoisi;
    @FXML private Label lblEstEnRetard;
    @FXML private TextArea textAreaFacture;
    @FXML private Button btnPayer;
    @FXML private Button btnAnnuler;
    @FXML private Button btnRetourner;
    
	Emprunt empruntChoisi;
	Exemplaire exemplaireChoisi;
	ObservableList<Emprunt> listeEm;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
        tableView01_Col01.setCellValueFactory(new PropertyValueFactory<Emprunt, Integer>("idEm"));
        tableView01_Col02.setCellValueFactory(new PropertyValueFactory<Emprunt, Integer>("idEx"));
        tableView01_Col03.setCellValueFactory(new PropertyValueFactory<Emprunt, Integer>("idU"));
        tableView01_Col04.setCellValueFactory(new PropertyValueFactory<Emprunt, Timestamp>("dateEm"));    
        tableView01_Col05.setCellValueFactory(new PropertyValueFactory<Emprunt, Integer>("nbJoursEm"));
        refreshTblView01();
        tableView01.setOnMousePressed((EventHandler<? super MouseEvent>) new EventHandler<MouseEvent>() {
            @Override 
            public void handle(MouseEvent event) {
                if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
                    if (tableView01.getSelectionModel().getSelectedItem() != null) {
                        int empruntPos = tableView01.getSelectionModel().getSelectedIndex();
                        empruntChoisi = listeEm.get(empruntPos);
                        exemplaireChoisi = ExemplaireController.getControleurEx().CtrEx_read(empruntChoisi.getIdEx());
                        lblEmpruntChoisi.setText(String.valueOf(empruntChoisi.getIdEm()));
                        try {
							evaluerRetourEmprunt();
						} catch (ParseException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
                    }  
                }
            }
        });
	}
	
	public void refreshTblView01() {
		listeEm = EmpruntController.getControleurEm().CtrEm_readAll();
        Thread async_refreshTblView01 = new Thread(() -> {
            Platform.runLater(() -> { 
                this.tableView01.setItems(listeEm);
            });
	    });
	    async_refreshTblView01.start();
	}
	
	public void evaluerRetourEmprunt() throws ParseException {
		textAreaFacture.setText("");
		Date dateEmpruntEnMilliSec = new Date(empruntChoisi.getDateEm().getTime());
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		//
		String dateEmpruntString = dateFormat.format(dateEmpruntEnMilliSec);
		//
        Calendar cal = Calendar.getInstance();  
        try{  
           cal.setTime(dateFormat.parse(dateEmpruntString));  
        }catch(ParseException e){  
            e.printStackTrace();  
        }
        cal.add(Calendar.DAY_OF_MONTH, empruntChoisi.getNbJoursEm());  
        String dateLimiteString = dateFormat.format(cal.getTime());
        //
        LocalDate dateObj = LocalDate.now();
        String dateAujourdhuiString = dateFormat.format(Date.from(dateObj.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        //
        Date dateAujourdhui = dateFormat.parse(dateAujourdhuiString);
        Date dateLimite = dateFormat.parse(dateLimiteString);
        int resultat = dateLimite.compareTo(dateAujourdhui);
        if(resultat >= 0) {
        	lblEstEnRetard.setText("À Temps");
            btnPayer.setDisable(true);
            btnAnnuler.setDisable(true);
            btnRetourner.setDisable(false);
        }else {
        	lblEstEnRetard.setText("En Retard");
            btnPayer.setDisable(false);
            btnAnnuler.setDisable(false);
            btnRetourner.setDisable(true);
            int joursDeRetard = (int) Duration.between(dateLimite.toInstant().atZone(ZoneId.systemDefault()), dateAujourdhui.toInstant().atZone(ZoneId.systemDefault())).toDays();        
            int penaliteAPayer = 0;
            if(joursDeRetard*2 < exemplaireChoisi.getPrixEx()) {
                for(int i = joursDeRetard; i > 0; i--) {
                	textAreaFacture.appendText(" 1 jour de retard = 2$\n");
                	penaliteAPayer += 2;
                	
                }
                textAreaFacture.appendText("------------------------\n");
                textAreaFacture.appendText(" Total à Payer: "+penaliteAPayer+"$\n\n");
            }else {
            	textAreaFacture.appendText("------------------------\n");
            	textAreaFacture.appendText(" Total à Payer: "+exemplaireChoisi.getPrixEx()+"$\n\n");
            }
        }      
	}
	
	@FXML 
	void btnPayerHandler(ActionEvent e) {
		textAreaFacture.setText("");
		btnRetourner.setDisable(false);
		btnPayer.setDisable(true);
		btnAnnuler.setDisable(true);
	}
	
	@FXML
	void btnAnnulerHandler(ActionEvent e) {
		textAreaFacture.setText("");
		btnRetourner.setDisable(true);
		btnPayer.setDisable(true);
		btnAnnuler.setDisable(true);
		lblEmpruntChoisi.setText(null);
		lblEstEnRetard.setText(null);
		empruntChoisi = null;
		exemplaireChoisi = null;		
	}
	
	@FXML
	void btnRetournerHandler(ActionEvent e) {
		EmpruntController.getControleurEm().CtrEm_delete(empruntChoisi.getIdEm());
		String texte = "L'exemplaire " +exemplaireChoisi.getTitreEx()+ " de " +exemplaireChoisi.getArtisteEx()+ " a été retourné par l'usager #"+empruntChoisi.getIdU()+".";
        scene00Controller.ajouterHistorique(new Timestamp(System.currentTimeMillis()), texte);
        scene00Controller.refreshTblView01();
		exemplaireChoisi.setEstEmprunte(false);
		ExemplaireController.getControleurEx().CtrEx_update(exemplaireChoisi);
		btnRetourner.setDisable(true);
		lblEmpruntChoisi.setText(null);
		lblEstEnRetard.setText(null);
		empruntChoisi = null;
		exemplaireChoisi = null;
		refreshTblView01();
	}
    
}
