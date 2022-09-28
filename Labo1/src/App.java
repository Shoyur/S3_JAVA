import java.io.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;



 
public class App extends Application {

    private static String fichier_txt = "";
    static BufferedReader tmpReadTexte;   
    static RandomAccessFile tmpWriteBin;
    static File fichierBin;
    // numLivre + titre + 2 + numAuteur + annee + nbPages + categLivre + 2
    static final int TAILLE_ENREG = 3+45+2+2+4+4+20+2;

    public static void chargerFichierTexte(JFrame framePrincipal) {
        JFileChooser choix = new JFileChooser();
        // choix.setoolbarackground(new java.awt.Color(204, 166, 166));
        choix.setFileFilter(new FileNameExtensionFilter("*.txt", "txt"));
        choix.setAcceptAllFileFilterUsed(false);
        choix.setCurrentDirectory(new File(System.getProperty("user.dir")));
        choix.setDialogTitle("Choisissez le fichier texte pour la bibliothèque...");
        if (choix.showOpenDialog(framePrincipal) == JFileChooser.APPROVE_OPTION) {
            fichier_txt = choix.getSelectedFile().getAbsolutePath();
        }
        else {
            String titre = "Erreur";
            String message = "\nVous deviez absolument sélectionner un fichier texte compatible.\n";
            message += "Ce programme va s'arrêter.\n";
            JOptionPane.showMessageDialog(null, message, titre, JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
    }

    public static void convertirTxtEnBinaire() {
        String elems[] = new String[6];
        // numLivre + titre + 2 + numAuteur + annee + nbPages + categLivre + 2
        // TAILLE_ENREG = 3+45+2+2+4+4+20+2;
        int numLivre, numAuteur, annee, nbPages;
        String titre="", categLivre="";
        try{
            tmpReadTexte = new BufferedReader(new FileReader(fichier_txt));
            tmpWriteBin = new RandomAccessFile("src/donnees/livres.bin", "rw");
            String ligne = tmpReadTexte.readLine();
            while(ligne != null ){
                elems = ligne.split(";");

                numLivre = Integer.parseInt(elems[0]);
                titre = String.format("%1$-45.45s", elems[1]);
                numAuteur = Integer.parseInt(elems[2]);
                annee = Integer.parseInt(elems[3]);
                nbPages = Integer.parseInt(elems[4]);
                categLivre = String.format("%1$-20.20s", elems[5]);

                tmpWriteBin.writeInt(numLivre);
                tmpWriteBin.writeUTF(titre);
                tmpWriteBin.writeInt(numAuteur);
                tmpWriteBin.writeInt(annee);
                tmpWriteBin.writeInt(nbPages);
                tmpWriteBin.writeUTF(categLivre);

                ligne = tmpReadTexte.readLine();

            }
            tmpReadTexte.close();
            tmpWriteBin.close();
        } catch (Exception e) { System.out.println("Erreur à la génération du fichier binaire : " + e.getMessage()); }
    }
    @Override
    public void start(Stage scenePrincipale) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource("ScenePrincipale.fxml"));
            Scene scene = new Scene(root);
            scenePrincipale.setScene(scene);

        } catch (Exception e) {}
        
        scenePrincipale.getIcons().add(new Image("medias/frameIcon.png"));
        scenePrincipale.setTitle("[Gestionnaire de bibliothèque]");
        scenePrincipale.show();

    }
 
 public static void main(String[] args) {
        launch(args);
    }
}
