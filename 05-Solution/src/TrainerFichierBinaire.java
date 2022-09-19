import java.io.*;

public class TrainerFichierBinaire {

    public static BufferedReader tmpReadTexte;
    public static RandomAccessFile tmpWriteBin;
    static final int TAILLE_ENREG = 4+40+24+4 +4; // 4 de plus pour des infos de fichier

    public static void ecrireFichierBinaire() throws Exception {

        tmpReadTexte = new BufferedReader(new FileReader("src/films.txt"));
        tmpWriteBin = new RandomAccessFile("src/films.bin", "rw");
        String titre = "", categ = ""; // taille 20 et 12
        String ligne = tmpReadTexte.readLine();
        String elements [] = new String [4];
        int num, duree;

        while (ligne != null) {

            elements = ligne.split(";");
            
            num = Integer.parseInt(elements[0]);
            String.format("%20.20s", elements[1]); // 20 20 (min max)
            String.format("%12.12s", elements[2]);
            duree = Integer.parseInt(elements[3]);

            tmpWriteBin.writeInt(num);
            tmpWriteBin.writeUTF(elements[1]);
            tmpWriteBin.writeUTF(elements[2]);
            tmpWriteBin.writeInt(duree);

            ligne = tmpReadTexte.readLine();
        }
        tmpReadTexte.close();
        tmpWriteBin.close();
    }

    public static long obtenirAdresse(int cle) {
        long adr = (cle / 100 - 1) * TAILLE_ENREG;
        return adr;
    }

    public static void main(String[] args) throws Exception {
        ecrireFichierBinaire();
        System.out.println(obtenirAdresse(300));
        tmpWriteBin = new RandomAccessFile("src/films.bin", "rw");
        System.out.println("tmpWriteBin.getFilePointer() = " + tmpWriteBin.getFilePointer());
        tmpWriteBin.seek(144);
        System.out.println(tmpWriteBin.readInt());
        System.out.println(tmpWriteBin.readUTF());
    }
}
