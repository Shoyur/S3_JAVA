package controllers;

import java.sql.Timestamp;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import models.Exemplaire;

public class Scene02Controller {

    private Scene00Controller scene00Controller;
    
    public void injectScene00Controller(Scene00Controller scene00Controller) {
        this.scene00Controller = scene00Controller;
    }

    @FXML private AnchorPane scene02;

    @FXML private ImageView ImgVLoading02Sel, ImgVLoading02New, ImgVExSelPochette, ImgVExNewPochette;

    @FXML private Button buttonExSelCancel, buttonExSelModifier, buttonExSelCopier,
                    buttonExSelEmprunter, buttonExSelAcheter,
                    btnAjouterExCancel, btnAjouterEx;

    @FXML private Label labelExSelId, labelExSelNbEmprunts, labelExSelPrixAjuste, 
                    labelAjoutExNewErreur1, labelAjoutExNewErreur2, labelAjoutExNewErreur3,
                    labelAjoutExNewErreur4, labelAjoutExNewErreur5, labelAjoutExNewErreur6;

    @FXML private TextField textFieldExSelTitre, textFieldExSelArtiste, textFieldExSelGenre,
                    textFieldExSelAnnee, textFieldExSelPrix,
                    textFieldExNewTitre, textFieldExNewArtiste, textFieldExNewGenre,
                    textFieldExNewAnnee, textFieldExNewPrix;

    @FXML private TextArea textAreaExSelPistes, textAreaExNewPistes;

    @FXML
    void buttonExSelCancel(ActionEvent event) {
        labelExSelId.setText(null);
        textFieldExSelTitre.setText(null);
        textFieldExSelTitre.setDisable(true);
        textFieldExSelArtiste.setText(null);
        textFieldExSelArtiste.setDisable(true);
        textFieldExSelGenre.setText(null);
        textFieldExSelGenre.setDisable(true);
        textFieldExSelAnnee.setText(null);
        textFieldExSelAnnee.setDisable(true);
        textFieldExSelPrix.setText(null);
        textFieldExSelPrix.setDisable(true);
        labelExSelNbEmprunts.setText(null);
        labelExSelPrixAjuste.setText(null);
        textAreaExSelPistes.setText(null);
        textAreaExSelPistes.setDisable(true);
        ImgVExSelPochette.setVisible(false);
        buttonExSelCancel.setDisable(true);
        buttonExSelModifier.setDisable(true);
        buttonExSelCopier.setDisable(true);
        buttonExSelEmprunter.setDisable(true);
        buttonExSelAcheter.setDisable(true);
    }

    @FXML
    void buttonExSelModifier(ActionEvent event) {
        ImgVLoading02Sel.setVisible(true);
        Thread async_modifierExemplaire = new Thread(() -> {
            (ExemplaireController.getControleurEx()).CtrEx_update(
                textFieldExSelTitre.getText(), 
                textFieldExSelArtiste.getText(), 
                textFieldExSelGenre.getText(), 
                Integer.parseInt(textFieldExSelAnnee.getText()), 
                Double.parseDouble(textFieldExSelPrix.getText()), 
                textAreaExSelPistes.getText(), 
                Integer.parseInt(labelExSelId.getText()));
            Platform.runLater(() -> { ImgVLoading02Sel.setVisible(false); });
        });
        async_modifierExemplaire.start();
        String texte = "L'exemplaire " + textFieldExSelTitre.getText() + " de " + textFieldExSelArtiste.getText() + " a été modifié.";
        scene00Controller.ajouterHistorique(new Timestamp(System.currentTimeMillis()), texte);
        scene00Controller.refreshTblView01();
    }

    @FXML
    void buttonExSelCopier(ActionEvent event) {
        textFieldExNewTitre.setText(textFieldExSelTitre.getText());
        textFieldExNewArtiste.setText(textFieldExSelArtiste.getText());
        textFieldExNewGenre.setText(textFieldExSelGenre.getText());
        textFieldExNewAnnee.setText(textFieldExSelAnnee.getText());
        textFieldExNewPrix.setText(textFieldExSelPrix.getText());
        textAreaExNewPistes.setText(textAreaExSelPistes.getText());
    }

    @FXML
    void buttonExSelEmprunter(ActionEvent event) {
        scene00Controller.refreshTblView01();
    }

    @FXML
    void buttonExSelAcheter(ActionEvent event) {
        scene00Controller.refreshTblView01();
    }

    @FXML
    void btnAjouterExCancel(ActionEvent event) {
        cacherErreursAjouterEx();
        textFieldExNewTitre.setText(null);
        textFieldExNewArtiste.setText(null);
        textFieldExNewGenre.setText(null);
        textFieldExNewAnnee.setText(null);
        textFieldExNewPrix.setText(null);
        textAreaExNewPistes.setText(null);
    }

    private void cacherErreursAjouterEx() {
        labelAjoutExNewErreur1.setVisible(false);
        labelAjoutExNewErreur2.setVisible(false);
        labelAjoutExNewErreur3.setVisible(false);
        labelAjoutExNewErreur4.setVisible(false);
        labelAjoutExNewErreur5.setVisible(false);
        labelAjoutExNewErreur6.setVisible(false);
    }

    @FXML
    void btnAjouterEx(ActionEvent event) {
        cacherErreursAjouterEx();
        boolean erreur = false;
        if (textFieldExNewTitre.getText() == null || textFieldExNewTitre.getText().isEmpty()) {
            labelAjoutExNewErreur1.setVisible(true);
            if (erreur == false) { textFieldExNewTitre.requestFocus(); }
            erreur = true;
        }
        if (textFieldExNewArtiste.getText() == null || textFieldExNewArtiste.getText().isEmpty()) {
            labelAjoutExNewErreur2.setVisible(true);
            if (erreur == false) { textFieldExNewArtiste.requestFocus(); }
            erreur = true;
        }
        if (textFieldExNewGenre.getText() == null || textFieldExNewGenre.getText().isEmpty()) {
            labelAjoutExNewErreur3.setVisible(true);
            if (erreur == false) { textFieldExNewGenre.requestFocus(); }
            erreur = true;
        }
        if (textFieldExNewAnnee.getText() == null || textFieldExNewAnnee.getText().isEmpty()) {
            labelAjoutExNewErreur4.setVisible(true);
            if (erreur == false) { textFieldExNewAnnee.requestFocus(); }
            erreur = true;
        }
        if (textFieldExNewPrix.getText() == null || textFieldExNewPrix.getText().isEmpty()) {
            labelAjoutExNewErreur5.setVisible(true);
            if (erreur == false) { textFieldExNewPrix.requestFocus(); }
            erreur = true;
        }
        if (textAreaExNewPistes.getText() == null || textAreaExNewPistes.getText().isEmpty()) {
            labelAjoutExNewErreur6.setVisible(true);
            if (erreur == false) { textAreaExNewPistes.requestFocus(); }
            erreur = true;
        }
        if (erreur == true) { return; }
        ImgVLoading02New.setVisible(true);
        // Exemplaire(int idEx, String titreEx, String artisteEx, String categEx, int anneeEx, double prixEx,
        //     String pistesEx, int nbEmpruntsEx, boolean estEmprunte, boolean estVendu)
        Exemplaire exemplaire = new Exemplaire(
            0, 
            textFieldExNewTitre.getText(),
            textFieldExNewArtiste.getText(), 
            textFieldExNewGenre.getText(), 
            Integer.parseInt(textFieldExNewAnnee.getText()), 
            Double.parseDouble(textFieldExNewPrix.getText()), 
            textAreaExNewPistes.getText(), 
            0, false, false);
        Thread async_ajouterEx = new Thread(() -> {
            (ExemplaireController.getControleurEx()).CtrEx_create(exemplaire);
            Platform.runLater(() -> { ImgVLoading02New.setVisible(false); });
        });
        async_ajouterEx.start();
        String texte = "L'exemplaire " + textFieldExNewTitre.getText() + " de " + textFieldExNewArtiste.getText() + " a été ajouté.";
        scene00Controller.ajouterHistorique(new Timestamp(System.currentTimeMillis()), texte);
        btnAjouterExCancel(null);
    }

    public void afficherExSel(Exemplaire exemplaire) {
        labelExSelId.setText(Integer.toString(exemplaire.getIdEx()));
        textFieldExSelTitre.setText(exemplaire.getTitreEx());
        textFieldExSelTitre.setDisable(false);
        textFieldExSelArtiste.setText(exemplaire.getArtisteEx());
        textFieldExSelArtiste.setDisable(false);
        textFieldExSelGenre.setText(exemplaire.getCategEx());
        textFieldExSelGenre.setDisable(false);
        textFieldExSelAnnee.setText(Integer.toString(exemplaire.getAnneeEx()));
        textFieldExSelAnnee.setDisable(false);
        textFieldExSelPrix.setText(Double.toString(exemplaire.getPrixEx()));
        textFieldExSelPrix.setDisable(false);
        labelExSelNbEmprunts.setText(Integer.toString(exemplaire.getNbEmpruntsEx()));
        double prixAjuste = exemplaire.getPrixEx() - exemplaire.getNbEmpruntsEx();
        double moitie = exemplaire.getPrixEx() / 2;
        if (prixAjuste < moitie) { prixAjuste = moitie; }
        labelExSelPrixAjuste.setText(String.format("%.2f", prixAjuste));
        textAreaExSelPistes.setText(exemplaire.getPistesEx());
        textAreaExSelPistes.setDisable(false);
        ImgVExSelPochette.setVisible(true);
        buttonExSelCancel.setDisable(false);
        buttonExSelModifier.setDisable(false);
        buttonExSelCopier.setDisable(false);
        buttonExSelEmprunter.setDisable((exemplaire.isEstEmprunte() == false) ? false : true);
        buttonExSelAcheter.setDisable((exemplaire.isEstEmprunte() == false) ? false : true);
    }

}
