package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
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

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		UsagerController usagerCtrl = UsagerController.getControleurU();
		ObservableList<Usager> listeUsagers = usagerCtrl.CtrU_readAll();
		ArrayList<String> listeUsgStrings = new ArrayList<>();
		for(Usager unUsager : listeUsagers) {
			listeUsgStrings.add(unUsager.toString());
		}
		comboBoxUsgCurrentTr.getItems().addAll(listeUsgStrings);
		comboBoxUsgCurrentTr.setOnAction(event -> {
			String data = comboBoxUsgCurrentTr.getSelectionModel().getSelectedItem().toString(); 
			System.out.println(data);
		});
	}

    
}
