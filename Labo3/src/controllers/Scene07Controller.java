package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class Scene07Controller implements Initializable{

    private Scene00Controller scene00Controller;
    
    public void injectScene00Controller(Scene00Controller scene00Controller) {
        this.scene00Controller = scene00Controller;
    }

    @FXML
    private PieChart StatsPieChart01;

    @FXML
    private AnchorPane scene07;

    @FXML 
    public Label labelStats1, labelStats2, labelStats3, labelStats4;

    
    public int statsUsagers = 1;
    public int statsExemplaires = 666;
    public int statsEmpruntes = 1;
    public int statsVendus = 1;

    @FXML
    ObservableList<PieChart.Data> pieChartData =
        FXCollections.observableArrayList(
        new PieChart.Data("Empruntés", statsEmpruntes),
        new PieChart.Data("Vendus", statsVendus));



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // ObservableList<PieChart.Data> pieChartData =
        //         FXCollections.observableArrayList(
        //         new PieChart.Data("Empruntés", statsEmpruntes),
        //         new PieChart.Data("Vendus", statsVendus));
        // StatsPieChart01.setData(pieChartData);
        StatsPieChart01.setData(pieChartData);

        pieChartData.forEach(data ->
            data.nameProperty().bind(
                Bindings.concat(data.getName(), " (", Bindings.format("%.0f", data.pieValueProperty()), ")")
        ));
        // ObservableList ventes = (UsagerController.getControleurU()).CtrU_readAll();
        
    }

    public void refreshStats1(int valeur) {
        labelStats1.setText(String.valueOf(valeur));
    }

    public void refreshStats2(int valeur) {
        labelStats2.setText(String.valueOf(valeur));
    }

    public void refreshStats3(int valeur) {
        labelStats3.setText(String.valueOf(valeur));
        StatsPieChart01.getData().get(0).setPieValue(valeur);
    }

    public void refreshStats4(int valeur) {
        labelStats4.setText(String.valueOf(valeur));
        StatsPieChart01.getData().get(1).setPieValue(valeur);
    }

}
