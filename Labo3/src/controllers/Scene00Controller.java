package controllers;

import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
// import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
// import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
// import javafx.scene.layout.AnchorPane;

public class Scene00Controller implements Initializable {

    @FXML
    private Tab 
        tab01, tab02, tab03, tab04, tab05, 
        tab06, tab07, tab08, tab09, tab10;

    @FXML
    private TabPane scene00;

    // SingleSelectionModel<Tab> selectionModel = scene00.getSelectionModel();

    
    public void test() {
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

        // System.out.println("00-CTRL-test() = oui");
        // scene00.getSelectionModel().select(tab07);
        // scene00.getSelectionModel().select(6);
        

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // try {
        //     AnchorPane node01 = FXMLLoader.load(getClass().getResource("../views/Scene01.fxml"));
        //     tab01.setContent(node01);
        //     AnchorPane node02 = FXMLLoader.load(getClass().getResource("../views/Scene02.fxml"));
        //     tab02.setContent(node02);
        //     AnchorPane node03 = FXMLLoader.load(getClass().getResource("../views/Scene03.fxml"));
        //     tab03.setContent(node03);
        //     AnchorPane node04 = FXMLLoader.load(getClass().getResource("../views/Scene04.fxml"));
        //     tab04.setContent(node04);
        //     AnchorPane node05 = FXMLLoader.load(getClass().getResource("../views/Scene05.fxml"));
        //     tab05.setContent(node05);
        //     AnchorPane node06 = FXMLLoader.load(getClass().getResource("../views/Scene06.fxml"));
        //     tab06.setContent(node06);
        //     AnchorPane node07 = FXMLLoader.load(getClass().getResource("../views/Scene07.fxml"));
        //     tab07.setContent(node07);
        //     AnchorPane node08 = FXMLLoader.load(getClass().getResource("../views/Scene08.fxml"));
        //     tab08.setContent(node08);
        //     AnchorPane node09 = FXMLLoader.load(getClass().getResource("../views/Scene09.fxml"));
        //     tab09.setContent(node09);
        //     AnchorPane node10 = FXMLLoader.load(getClass().getResource("../views/Scene10.fxml"));
        //     tab10.setContent(node10);
        // } catch (Exception e) { System.out.println(e); }

        System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Timestamp(System.currentTimeMillis())));

        
    }

}
