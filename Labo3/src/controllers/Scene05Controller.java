package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import models.Exemplaire;
import models.Usager;

public class Scene05Controller implements Initializable {
    
    private Scene00Controller scene00Controller;
    
    public void injectScene00Controller(Scene00Controller scene00Controller) {
        this.scene00Controller = scene00Controller;
    }
    
	@FXML
	private AnchorPane scene05;
	@FXML
	private ComboBox<String> comboBoxUsgCurrentTr;
	@FXML
	private TextArea textAreaUsgCurrentTr;
	@FXML
	private Label lblMontantSTCurrentTr, lblMontantATCurrentTr;
	
	Usager usagerChoisi;
	Map<Exemplaire,String> listeExDansLaTr = new HashMap<>();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		UsagerController usagerCtrl = UsagerController.getControleurU();
		ObservableList<Usager> listeUsagers = usagerCtrl.CtrU_readAll();
		for(Usager unUsager : listeUsagers) {
			comboBoxUsgCurrentTr.getItems().add(unUsager.toString());
		}
		comboBoxUsgCurrentTr.setOnAction(event -> {
			int usgPos = comboBoxUsgCurrentTr.getSelectionModel().getSelectedIndex();
			usagerChoisi = listeUsagers.get(usgPos);
		});
	}
	
	@FXML
	void btnPayerCurrentTrHandler(ActionEvent e){
		
	}
	
	@FXML
	void btnCancelCurrentTrHandler(ActionEvent e) {
		
	}
	
	public void ajouterEmprunt(int idEx) {
		ExemplaireController exemplaireCtrl = ExemplaireController.getControleurEx();
		Exemplaire exemplaire = exemplaireCtrl.CtrEx_read(idEx);
		String prixEmprunt = String.format("%.2f",exemplaire.getPrixEx()/4);
		if("".equals(textAreaUsgCurrentTr.getText())) {
			textAreaUsgCurrentTr.appendText(exemplaire.getIdEx()+"  "+exemplaire.getTitreEx()+" ="+prixEmprunt+"\n");
		}else {
			textAreaUsgCurrentTr.appendText("+\n"+exemplaire.getIdEx()+"  "+exemplaire.getTitreEx()+" ="+prixEmprunt+"\n");
		}
		listeExDansLaTr.put(exemplaire, "Emprunt");
		afficherTotals();
	}
	
	public void ajouterVente(int idEx) {
		ExemplaireController exemplaireCtrl = ExemplaireController.getControleurEx();
		Exemplaire exemplaire = exemplaireCtrl.CtrEx_read(idEx);
		double prixAjuste = exemplaire.getPrixEx() - exemplaire.getNbEmpruntsEx();
        double plancher = exemplaire.getPrixEx() / 1.25;
        if (prixAjuste < plancher) { prixAjuste = plancher; }
        String prixVente = String.format("%.2f",prixAjuste);
		if("".equals(textAreaUsgCurrentTr.getText())) {
			textAreaUsgCurrentTr.appendText(exemplaire.getIdEx()+"  "+exemplaire.getTitreEx()+" ="+prixVente+"\n");
		}else {
			textAreaUsgCurrentTr.appendText("+\n"+exemplaire.getIdEx()+"  "+exemplaire.getTitreEx()+" ="+prixVente+"\n");
		}
		listeExDansLaTr.put(exemplaire, "Vente");
		afficherTotals();
	}
	
	public void afficherTotals() {
		String data = textAreaUsgCurrentTr.getText().replace("+\n", "").replace("\n", "=");
		String[] tab = data.split("=");
		double prixAccumules = 0;
		for(int i=1; i<tab.length; i+=2) {
			prixAccumules += Double.parseDouble(tab[i]);
		}
		lblMontantSTCurrentTr.setText(String.valueOf(prixAccumules));
		lblMontantATCurrentTr.setText(String.valueOf(String.format("%.2f",prixAccumules+(prixAccumules*0.14975))));		
	}

    
}
