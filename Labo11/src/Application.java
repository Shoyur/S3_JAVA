import javax.swing.*;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;  
import java.awt.*;

public class Application {

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

    public static void main(String[] args) throws Exception {


        JFrame framePrincipal = new JFrame("[ La bibliothèque ]");
        framePrincipal.setLayout(new BorderLayout());
        JToolBar toolbar = new JToolBar();

        JMenuBar menubar = new JMenuBar();
        framePrincipal.setJMenuBar(menubar);

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JComboBox comboBox = new JComboBox(new String[] { "item 1", "item 2", "item 3" });

        JButton bouton1 = new JButton("Lister tous les livres");
        JButton bouton2 = new JButton("bouton 2");
        panel.add(bouton1);
        panel.add(comboBox);
        panel.add(bouton2);
        
        toolbar.add(panel);
        toolbar.setFloatable(false);
        toolbar.setOrientation(SwingConstants.VERTICAL);
        framePrincipal.add(toolbar, BorderLayout.WEST);

        JTable table = new JTable();
        framePrincipal.add(table);

        ImageIcon frameIcon = new ImageIcon("src/frameIcon.png");
        framePrincipal.setBackground(new java.awt.Color(204, 166, 166));
        framePrincipal.setIconImage(frameIcon.getImage());
        framePrincipal.setSize(800, 500);
        framePrincipal.setVisible(true);
        framePrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    

        chargerFichierTexte(framePrincipal);
        convertirTxtEnBinaire();
        System.out.println("yes sir !");


    }
}
