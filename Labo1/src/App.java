import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;


public class App extends Application {

    static BufferedReader tmpReadTexte;   
    static RandomAccessFile tmpWriteBin;
    public static File fichierBin;


    // ---------------------------------------------------------------------------------------------------------------
    // POPUP DU JavaFX FileChooser, POUR SÉLECTIONNER LE livres.txt.
    // ---------------------------------------------------------------------------------------------------------------
    private static String choixFichierTexte() {
        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().add(new ExtensionFilter("*.txt", "*.txt"));
        fc.setInitialDirectory(new File(System.getProperty("user.dir")));
        File f = fc.showOpenDialog(null);
        String fichier_txt = "";
        if (f != null) { fichier_txt = f.getAbsolutePath(); }
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
    private static void convertirTxtEnBinaire(String fichier_txt) throws Exception {
        File ancien = new File("src/donnees/livres.bin");
        ancien.delete();
        String elems[] = new String[6];
        try{
            tmpReadTexte = new BufferedReader(new FileReader(fichier_txt));
            tmpWriteBin = new RandomAccessFile("src/donnees/livres.bin", "rw");
            tmpWriteBin.seek(0);
            String ligne = tmpReadTexte.readLine();
            while(ligne != null ){
                elems = ligne.split(";");
                tmpWriteBin.writeInt(Integer.parseInt(elems[0]));
                tmpWriteBin.writeUTF(String.format("%1$-55.55s", elems[1]));
                tmpWriteBin.writeInt(Integer.parseInt(elems[2]));
                tmpWriteBin.writeInt(Integer.parseInt(elems[3]));
                tmpWriteBin.writeInt(Integer.parseInt(elems[4]));
                tmpWriteBin.writeUTF(String.format("%1$-20.20s", elems[5]));
                ligne = tmpReadTexte.readLine();
            }
            tmpReadTexte.close();
            tmpWriteBin.close();
        } catch (Exception e) { 
            System.out.println("Erreur à la génération du fichier binaire : " + e.getMessage());
            tmpReadTexte.close();
            tmpWriteBin.close();
            System.exit(0);
        }
    }

    // ---------------------------------------------------------------------------------------------------------------
    // POUR RETOURNER UN TABLEAU DE 3 LISTES (categLivre, numAuteur, numLivre).
    // ---------------------------------------------------------------------------------------------------------------
    public static List<?>[] listesCombos() throws Exception {
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
                if (numLivre != 0) {
                    if (!listeCateg.contains(categLivre)) { listeCateg.add(categLivre); }
                    if (!listeNumLivre.contains(numLivre)) { listeNumLivre.add(numLivre); }
                    if (!listeNumAuteur.contains(numAuteur)) { listeNumAuteur.add(numAuteur); }
                }
            }
        } catch (Exception e) {
            tmpWriteBin.close();
        }
        listeCateg.sort(null);
        listeNumLivre.sort(null);
        listeNumAuteur.sort(null);
        List<?>[] listesCombos = {listeCateg, listeNumLivre, listeNumAuteur};
        return listesCombos;
    }


    // ---------------------------------------------------------------------------------------------------------------
    // POUR INITIALISER LE FXML ET CHARGER LA FENÊTRE ETC.
    // ---------------------------------------------------------------------------------------------------------------
    @Override
    public void start(Stage stagePrincipal) throws Exception {
        convertirTxtEnBinaire(choixFichierTexte());
        Parent root = FXMLLoader.load(getClass().getResource("scenePrincipale.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("style.css");
        stagePrincipal.setScene(scene);
        stagePrincipal.getIcons().add(new Image("medias/frameIcon.png"));
        stagePrincipal.setTitle("[Gestionnaire de bibliothèque]");
        stagePrincipal.setResizable(false);
        stagePrincipal.initStyle(StageStyle.TRANSPARENT);
        stagePrincipal.show();
        
    }

    // ---------------------------------------------------------------------------------------------------------------
    // RETOURNE AU BOUTON [Lister tous les livres], LA LISTE COMPLÈTE DES LIVRES.
    // ---------------------------------------------------------------------------------------------------------------
    public static String lister(String flag, Object clic) throws Exception {
        int numLivre, numAuteur, annee, nbPages;
        String titre, categLivre;
        String lignes = "No.\tTitre\t\t\t\t\t\t\tAuteur\tAnnée\tPages\tCatégorie\n\n";
        tmpWriteBin = new RandomAccessFile("src/donnees/livres.bin", "rw");
        try {
            while (true) {
                numLivre = tmpWriteBin.readInt();
                titre = String.format("%1$-56.56s", tmpWriteBin.readUTF());
                numAuteur = tmpWriteBin.readInt();
                annee = tmpWriteBin.readInt();
                nbPages = tmpWriteBin.readInt();
                categLivre = tmpWriteBin.readUTF();
                if (numLivre != 0) {
                    if ((flag == "tout") || 
                    (flag == "cat" && ((String)clic).equals(categLivre)) || 
                    (flag == "auteur" && ((Integer)clic).equals(numAuteur)) || 
                    (flag == "livre" && ((Integer)clic).equals(numLivre))) { 
                        lignes += numLivre + "\t" + titre + numAuteur + "\t" + annee + "\t" + nbPages + "\t" + categLivre + "\n";
                    }

                    
                }
            }
        } catch (Exception e) { tmpWriteBin.close(); } 
        tmpWriteBin.close();
        return lignes;
    }

    // ---------------------------------------------------------------------------------------------------------------
    // Retourner tout d'un seul livre pour la page modif (après avoir sélectionné un livre dans comboNumLivre).
    // ---------------------------------------------------------------------------------------------------------------
    public static Object[] infosModifs(int leLivre) throws Exception {
        int numLivre, numAuteur, annee, nbPages;
        String titre, categ;
        tmpWriteBin = new RandomAccessFile("src/donnees/livres.bin", "rw");
        try {
            while (true) { // Boucle infinie
                numLivre = tmpWriteBin.readInt();
                titre = tmpWriteBin.readUTF();
                // titre = String.format("%1$-56.56s", tmpWriteBin.readUTF());
                numAuteur = tmpWriteBin.readInt();
                annee = tmpWriteBin.readInt();
                nbPages = tmpWriteBin.readInt();
                categ = tmpWriteBin.readUTF();
                if ((numLivre != 0) && ((Integer)leLivre).equals(numLivre)) {
                    Object[] infosModifs = {numLivre, titre, numAuteur, annee, nbPages, categ};
                    tmpWriteBin.close();
                    return infosModifs;
                }
            }
        } catch (Exception e) { tmpWriteBin.close(); }
        Object[] infosModifs = {"Erreur", "Erreur", "Erreur", "Erreur", "Erreur", "Erreur"};
        tmpWriteBin.close();
        return infosModifs;
    }

    // ---------------------------------------------------------------------------------------------------------------
    // SUPPRIMER UN LIVRE.
    // ---------------------------------------------------------------------------------------------------------------
    public static void supprimerLivre(int leLivre) throws Exception {
        int numLivre;
        long position;
        tmpWriteBin = new RandomAccessFile("src/donnees/livres.bin", "rw");
        try {
            while (true) {
                position = tmpWriteBin.getFilePointer();
                numLivre = tmpWriteBin.readInt();
                if (numLivre == leLivre) {
                    tmpWriteBin.seek(position);
                    tmpWriteBin.writeInt(0);
                    tmpWriteBin.close();
                    return;
                }
                tmpWriteBin.readUTF();
                tmpWriteBin.readInt();
                tmpWriteBin.readInt();
                tmpWriteBin.readInt();
                tmpWriteBin.readUTF();
            }
        } catch (Exception e) { tmpWriteBin.close(); }
        tmpWriteBin.close();

    }
 
    // ---------------------------------------------------------------------------------------------------------------
    // SUPPRIMER UN LIVRE.
    // ---------------------------------------------------------------------------------------------------------------
    public static void modifierTitre(int leLivre, String nouveauTitre) throws Exception {
        int numLivre;
        String temp;
        tmpWriteBin = new RandomAccessFile("src/donnees/livres.bin", "rw");
        try {
            while (true) {
                numLivre = tmpWriteBin.readInt();
                if (numLivre == leLivre) {
                    temp = String.format("%1$-55.55s", nouveauTitre);
                    tmpWriteBin.writeUTF(temp);
                    tmpWriteBin.close();
                    return;
                }
                tmpWriteBin.readUTF();
                tmpWriteBin.readInt();
                tmpWriteBin.readInt();
                tmpWriteBin.readInt();
                tmpWriteBin.readUTF();
            }
        } catch (Exception e) { tmpWriteBin.close(); }
        tmpWriteBin.close();
    }
 
    // ---------------------------------------------------------------------------------------------------------------
    // SUPPRIMER UN LIVRE.
    // ---------------------------------------------------------------------------------------------------------------
    public static void ajouterTout(int numLivre, String titre, int numAuteur, int annee, int nbPages, String categ) throws Exception {
        tmpWriteBin = new RandomAccessFile("src/donnees/livres.bin", "rw");
        try {
            tmpWriteBin.seek(tmpWriteBin.length());
            tmpWriteBin.writeInt(numLivre);
            tmpWriteBin.writeUTF(String.format("%1$-55.55s", titre));
            tmpWriteBin.writeInt(numAuteur);
            tmpWriteBin.writeInt(annee);
            tmpWriteBin.writeInt(nbPages);
            tmpWriteBin.writeUTF(String.format("%1$-20.20s", categ));
            tmpWriteBin.close();
            return;
        } catch (Exception e) { tmpWriteBin.close(); }
        tmpWriteBin.close();
    }

    // ---------------------------------------------------------------------------------------------------------------
    // LE MAIN. -> launch() est une méthode de "JavaFX.Application".
    // ---------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {

        launch(args);
        
    }
}
