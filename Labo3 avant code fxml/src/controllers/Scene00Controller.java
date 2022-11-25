package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
// import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class Scene00Controller implements Initializable {

    @FXML
    private Tab 
        tab01, tab02, tab03, tab04, tab05, 
        tab06, tab07, tab08, tab09, tab10;

    @FXML
    public static TabPane scene00;

    // SingleSelectionModel<Tab> selectionModel = scene00.getSelectionModel();

    @FXML
    public static void test() {
        // System.out.println(scene00.getSelectionModel().getSelectedItem());
        // System.out.println("TEST-OUI" + scene00.getSelectionModel().getSelectedIndex());

        // System.out.println("scene00.getTabs().size() = " + scene00.getTabs().size());
        // System.out.println("scene00.getSelectionModel() = " + scene00.getSelectionModel());
        // System.out.println("scene00.getTabs() = " + scene00.getTabs());
        // selectionModel.select(1);
        // selectionModel = scene00.getSelectionModel();
        // selectionModel.select(tab04);
        // scene00.setSelectionModel(SingleSelectionModel<Tab> tab07);
        // scene00.getTabs().get(7).getContent().requestFocus();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // selectionModel = scene00.getSelectionModel();
        
        
    }

}
