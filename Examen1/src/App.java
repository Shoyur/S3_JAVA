import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class App extends Application { 
// 
    static BufferedReader tmpReadTexte;   
    static RandomAccessFile tmpWriteBin;
    public static File fichierBin;
    final static String FICHIER_RADIOS_TXT = "src/donnees/radios.txt";
    final static String FICHIER_RADIOS_BIN = "src/donnees/radios.bin";
    public static Double total = 0.00;
    public static int dernierNumero;
    public static String optionsTemp;

    // ---------------------------------------------------------------------------------------------------------------
    // FICHIER TEXTE VERS DATA BINAIRE EN MÉMOIRE.
    // ---------------------------------------------------------------------------------------------------------------
    private static void convertirTxtEnBinaire() throws Exception {
        File ancien = new File(FICHIER_RADIOS_BIN);
        ancien.delete();
        String elems[] = new String[6];
        try{
            tmpReadTexte = new BufferedReader(new FileReader(FICHIER_RADIOS_TXT));
            tmpWriteBin = new RandomAccessFile(FICHIER_RADIOS_BIN, "rw");
            // tmpWriteBin.seek(0); ?????????????????????????????????????????????????????????????
            String ligne = tmpReadTexte.readLine();
            while(ligne != null ){
                elems = ligne.split(";");
                tmpWriteBin.writeInt(Integer.parseInt(elems[0]));
                tmpWriteBin.writeUTF(String.format("%1$-10.10s", elems[1]));
                tmpWriteBin.writeUTF(String.format("%1$-10.10s", elems[2]));
                tmpWriteBin.writeUTF(elems[3]);
                tmpWriteBin.writeDouble((Double.parseDouble(elems[4])));
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
    // POUR INITIALISER LE FXML ET CHARGER LA FENÊTRE ETC. + LE MAIN.
    // ---------------------------------------------------------------------------------------------------------------
    @Override
    public void start(Stage stagePrincipal) throws Exception {
        convertirTxtEnBinaire();
        Parent root = FXMLLoader.load(getClass().getResource("scenePrincipale.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("style.css");
        stagePrincipal.setScene(scene);
        stagePrincipal.getIcons().add(new Image("medias/frameIcon.png"));
        stagePrincipal.setTitle("[Radio Snack]");
        stagePrincipal.setResizable(false);
        stagePrincipal.initStyle(StageStyle.TRANSPARENT);
        stagePrincipal.show();
        
    }
    public static void main(String[] args) { launch(args); }

    // ---------------------------------------------------------------------------------------------------------------
    // RETOURNE AU BOUTON [Lister toutes les radios], LA LISTE COMPLÈTE DES RADIOS.
    // ---------------------------------------------------------------------------------------------------------------
    public static String lister(String flag) throws Exception {
        int numero = 0;
        String marque, modele, options;
        Double prix;
        total = 0.00;
        String texte = "No.\tMarque\t\tModèle\t\tCD?\tCass?\tmp3?\tPrix\n\n";
        tmpWriteBin = new RandomAccessFile(FICHIER_RADIOS_BIN, "rw");
        try {
            while (true) {
                numero = tmpWriteBin.readInt();
                marque = String.format("%1$-10.10s", tmpWriteBin.readUTF());
                modele = String.format("%1$-10.10s", tmpWriteBin.readUTF());
                options = tmpWriteBin.readUTF();

                prix = tmpWriteBin.readDouble();
                if (numero != 0) {
                    texte += numero + "\t" + 
                    marque + "\t" + 
                    modele + "\t" + 
                    ((options.charAt(0) == '0') ? "Non" : "Oui") + "\t" + 
                    ((options.charAt(1) == '0') ? "Non" : "Oui") + "\t" + 
                    ((options.charAt(2) == '0') ? "Non" : "Oui") + "\t" + 
                    String.format("%.2f",prix) + " $\n";
                    total += prix;
                }

            }
        } catch (Exception e) { 
            // System.out.println("::: Erreur @ lister() :::");
            tmpWriteBin.close(); 
        } 
        tmpWriteBin.close();
        dernierNumero = numero;
        return texte;
    }

    // ---------------------------------------------------------------------------------------------------------------
    // AJOUTER UNE RADIO.
    // ---------------------------------------------------------------------------------------------------------------
    public static void ajouterTout(int numero, String marque, String modele, String options, Double prix) throws Exception {
        tmpWriteBin = new RandomAccessFile(FICHIER_RADIOS_BIN, "rw");
        try {
            tmpWriteBin.seek(tmpWriteBin.length());
            tmpWriteBin.writeInt(numero);
            tmpWriteBin.writeUTF(String.format("%1$-10.10s", marque));
            tmpWriteBin.writeUTF(String.format("%1$-10.10s", modele));
            tmpWriteBin.writeUTF(options);
            tmpWriteBin.writeDouble(prix);
            tmpWriteBin.close();
            return;
        } catch (Exception e) { tmpWriteBin.close(); }
        tmpWriteBin.close();
    }

    // ---------------------------------------------------------------------------------------------------------------
    // SUPPRIMER UNE RADIO.
    // ---------------------------------------------------------------------------------------------------------------
    public static void supprimer(int laRadio) throws Exception {
        int numRadio;
        long position;
        tmpWriteBin = new RandomAccessFile(FICHIER_RADIOS_BIN, "rw");
        try {
            while (true) {
                position = tmpWriteBin.getFilePointer();
                numRadio = tmpWriteBin.readInt(); // No
                if (numRadio == laRadio) {
                    tmpWriteBin.seek(position);
                    tmpWriteBin.writeInt(0);
                    tmpWriteBin.close();
                    return;
                }
                tmpWriteBin.readUTF(); // marque
                tmpWriteBin.readUTF(); // modele
                tmpWriteBin.readUTF(); // options
                tmpWriteBin.readDouble(); // prix
            }
        } catch (Exception e) { tmpWriteBin.close(); }
        tmpWriteBin.close();

    }

    // ---------------------------------------------------------------------------------------------------------------
    // MODIFIER LES OPTIONS D'UNE RADIO.
    // ---------------------------------------------------------------------------------------------------------------
    public static void modifierOptions(int laRadio, String nouvelleOptions) throws Exception {
        int numRadio;
        tmpWriteBin = new RandomAccessFile(FICHIER_RADIOS_BIN, "rw");
        try {
            while (true) {
                numRadio = tmpWriteBin.readInt();
                tmpWriteBin.readUTF();
                tmpWriteBin.readUTF();
                if (numRadio == laRadio) {
                    tmpWriteBin.writeUTF(nouvelleOptions);
                    tmpWriteBin.close();
                    return;
                }
                tmpWriteBin.readUTF();
                tmpWriteBin.readDouble();
            }
        } catch (Exception e) { tmpWriteBin.close(); }
        tmpWriteBin.close();
    }

    // ---------------------------------------------------------------------------------------------------------------
    // VÉRIFIER SI LE TEXTE ENTRÉE DANS LE TEXBOX DE NO. EST UN LIVRE EXISTANT.
    // ---------------------------------------------------------------------------------------------------------------
    public static boolean verifierNoExistant(String texte) throws Exception {

        if (texte == null) { return false; }
        int laRadio;
        try { laRadio = Integer.parseInt(texte); } 
        catch (NumberFormatException e) { return false; }

        tmpWriteBin = new RandomAccessFile(FICHIER_RADIOS_BIN, "rw");
        int numRadio;
        try {
            while (true) {
                numRadio = tmpWriteBin.readInt();
                tmpWriteBin.readUTF();
                tmpWriteBin.readUTF();
                if (numRadio == laRadio) { 
                    optionsTemp = tmpWriteBin.readUTF(); 
                    return true; 
                }
                tmpWriteBin.readUTF();
                tmpWriteBin.readDouble();
            }
        } catch (Exception e) { tmpWriteBin.close(); }
        tmpWriteBin.close();
        return false;
    }

}
