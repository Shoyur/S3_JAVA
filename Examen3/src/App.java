import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import modeles.Patient;
import controleurs.ControleurPatient;


public class App extends Application { 

    @Override
    public void start(Stage stagePrincipal) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scenePrincipale.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("style.css");
        stagePrincipal.setScene(scene);
        stagePrincipal.getIcons().add(new Image("medias/frameIcon.png"));
        stagePrincipal.setTitle("[L'Hôpital Chez Ayoye]");
        stagePrincipal.setResizable(false);
        stagePrincipal.initStyle(StageStyle.TRANSPARENT);
        stagePrincipal.show();
    }
    public static void main(String[] args) { launch(args); }

 
    public static void ajouterTout(String nom, String prenom, String daten, String sexe, String adresse, String cp, Boolean fumeur) {
        Patient patient = new Patient();
        patient.setNom(nom);
        patient.setPrenom(prenom);
        patient.setDaten(daten);
        patient.setSexe(sexe);
        patient.setAdresse(adresse);
        patient.setCp(cp);
        patient.setFumeur(fumeur);
        (ControleurPatient.getControleurPatient()).CtrP_create(patient);
    }

    public static String lister(String flag, Object clic) {
        List<Patient> listePatients;
        if (flag == "nonFumeurs") { listePatients = (ControleurPatient.getControleurPatient()).CtrP_readByNonFumeur(); }
        else if (flag == "ville") { listePatients = (ControleurPatient.getControleurPatient()).CtrP_readByVille((String)clic); }
        else if (flag == "id") { listePatients = (ControleurPatient.getControleurPatient()).CtrP_readById((String)clic); }
        else { listePatients = (ControleurPatient.getControleurPatient()).CtrP_readAll(); }
        String lignes = "ID\tNom\t\tPrénom\t\tNaissance\tAdresse\t\t\t\tCP\t\tSexe\tFumeur\n\n";
        for (Patient patient : listePatients) {
            lignes += 
                patient.getIdp() + "\t" +
                String.format("%1$-16.16s", patient.getNom()) + "" +  
                String.format("%1$-16.16s", patient.getPrenom()) + "" +  
                String.format("%1$-16.16s", patient.getDaten()) + "" +  
                String.format("%1$-31.31s", patient.getAdresse()) + "\t" + 
                patient.getCp() + "\t\t" + 
                patient.getSexe() + "\t" + 
                (patient.isFumeur() ? "Oui" : "") + "\n";
        }
        return lignes;
    }

    public static List<Patient> unPatient(String idp) {
        return (ControleurPatient.getControleurPatient()).CtrP_readById(idp); 
    }

    public static void supprimerPatient(int idp) {
        (ControleurPatient.getControleurPatient()).CtrP_delete(idp);
    }

}
