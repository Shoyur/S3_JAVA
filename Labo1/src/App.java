import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;


public class App extends Application {

    static BufferedReader tmpReadTexte;   
    static RandomAccessFile tmpWriteBin;
    public static File fichierBin;
    static final int TAILLE_ENREG = 4+45+2+2+4+4+20+2; // numLivre + titre + 2 + numAuteur + annee + nbPages + categLivre + 2


    // ---------------------------------------------------------------------------------------------------------------
    // POPUP DU JavaFX FileChooser DANS LA CLASSE CONTROLLER, POUR SÉLECTIONNER LE livres.txt.
    // ---------------------------------------------------------------------------------------------------------------
    private static String choixFichierTexte() {
        ScenePrincipaleController choixFichier = new ScenePrincipaleController();
        File f = choixFichier.choixFichier("*.txt");
        String fichier_txt = "";
        if (f != null) {
            fichier_txt = f.getAbsolutePath();
        }
        else {
            String titre = "Erreur";
            String message = "\nVous deviez absolument sélectionner un fichier texte compatible.\n";
            message += "Ce programme va s'arrêter.\n";
            JOptionPane.showMessageDialog(null, message, titre, JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
        return fichier_txt;
    }

    // ---------------------------------------------------------------------------------------------------------------
    // FICHIER TEXTE VERS DATA BINAIRE EN MÉMOIRE.
    // ---------------------------------------------------------------------------------------------------------------
    private static void convertirTxtEnBinaire(String fichier_txt) {
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
                titre = elems[1];
                // titre = String.format("%1$-55.55s", elems[1]);
                numAuteur = Integer.parseInt(elems[2]);
                annee = Integer.parseInt(elems[3]);
                nbPages = Integer.parseInt(elems[4]);
                categLivre = elems[5];
                // categLivre = String.format("%1$-20.20s", elems[5]);

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

    // ---------------------------------------------------------------------------------------------------------------
    // POUR RETOURNER L'ADRESSE EN MÉMOIRE AVEC POSITION CLÉ VS TAILLE_ENREG.
    // ---------------------------------------------------------------------------------------------------------------
    public static long obtenirAdresse(int cle) {
        long adr = (cle / 100 - 1) * TAILLE_ENREG;
        return adr;
    }

    // ---------------------------------------------------------------------------------------------------------------
    // POUR RETOURNER UN TABLEAU DE 3 LISTES (categLivre, numAuteur, numLivre).
    // ---------------------------------------------------------------------------------------------------------------
    public static List[] listesCombos() throws IOException {
        List<String> listeCateg = new ArrayList<String>();
        List<Integer> listeNumLivre = new ArrayList<Integer>();
        List<Integer> listeNumAuteur = new ArrayList<Integer>();
        int numLivre, numAuteur;
        String categLivre;
        tmpWriteBin = new RandomAccessFile("src/donnees/livres.bin", "rw");
        try {
            while (true) {
                // numLivre + titre + 2 + numAuteur + annee + nbPages + categLivre + 2
                numLivre = tmpWriteBin.readInt();
                tmpWriteBin.readUTF();
                numAuteur = tmpWriteBin.readInt();
                tmpWriteBin.readInt();
                tmpWriteBin.readInt();
                categLivre = tmpWriteBin.readUTF();
                if (!listeCateg.contains(categLivre)) { listeCateg.add(categLivre); }
                if (!listeNumLivre.contains(numLivre)) { listeNumLivre.add(numLivre); }
                if (!listeNumAuteur.contains(numAuteur)) { listeNumAuteur.add(numAuteur); }
            }
        } catch (Exception e) {
            tmpWriteBin.close();
        }
        listeCateg.sort(null);
        listeNumLivre.sort(null);
        listeNumAuteur.sort(null);
        List[] listesCombos = {listeCateg, listeNumLivre, listeNumAuteur};
        return listesCombos;
    }


    // ---------------------------------------------------------------------------------------------------------------
    // POUR INITIALISER LE FXML ET CHARGER LA FENÊTRE ETC.
    // ---------------------------------------------------------------------------------------------------------------
    // @Override
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
        // convertirTxtEnBinaire(choixFichierTexte()); ///////////////////////////////////////////////////////////////////////////////////////////

    }

    // ---------------------------------------------------------------------------------------------------------------
    // RETOURNE AU BOUTON [Lister tous les livres], LA LISTE COMPLÈTE DES LIVRES.
    // ---------------------------------------------------------------------------------------------------------------
    public static String lister(String flag, Object clic) throws IOException {

        // System.out.println("lister(" + flag + " " + clic + ")");
        // if ((clic != null) && (((String)clic).equals("roman"))) { System.out.println("clic == roman"); }
        
        // numLivre + titre + 2 + numAuteur + annee + nbPages + categLivre + 2
        // TAILLE_ENREG = 4+45+2+2+4+4+20+2;
        int numLivre, numAuteur, annee, nbPages;
        String titre, categLivre;
        String lignes = "No.\tTitre\t\t\t\t\t\t\tAuteur\tAnnée\tPages\tCatégorie\n\n";
        tmpWriteBin = new RandomAccessFile("src/donnees/livres.bin", "rw");
        try {
            while (true) { // Boucle infinie
                numLivre = tmpWriteBin.readInt();
                titre = String.format("%1$-56.56s", tmpWriteBin.readUTF());
                numAuteur = tmpWriteBin.readInt();
                annee = tmpWriteBin.readInt();
                nbPages = tmpWriteBin.readInt();
                categLivre = tmpWriteBin.readUTF();
                // -1 indique que le livre a été supprimé mais il a fallut lire
                // les autres données pour se positionner au prochain enregistrement.
                if (numLivre != -1) {
                    if ((flag == "tout") || 
                    (flag == "cat" && ((String)clic).equals(categLivre)) || 
                    (flag == "auteur" && ((Integer)clic).equals(numAuteur)) || 
                    (flag == "livre" && ((Integer)clic).equals(numLivre))) { 
                        lignes += numLivre + "\t" + titre + numAuteur + "\t" + annee + "\t" + nbPages + "\t" + categLivre + "\n";
                    }

                    
                }
            }
        } catch (Exception e) {
            tmpWriteBin.close();
        } 
        return lignes;
    }
 
    // ---------------------------------------------------------------------------------------------------------------
    // LE MAIN. -> launch() est une méthode de "JavaFX.Application".
    // ---------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        launch(args);
        
    }
}
