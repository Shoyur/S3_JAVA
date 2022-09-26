import java.io.*;
public class TraiterFichierBinaire {
    static BufferedReader tmpReadTexte;   
    static RandomAccessFile tmpWriteBin;
    static BufferedReader clavier;
    static File fichierBin;

    // 4 octets pour numéro du film, 20 octets pour le titre,
    // 12 octets pour la catégorie et 4 octets pour la durée. 
    // Le système ajoute au début de chaque string 2 octets '\\u'
    // pour indiquer l'encodage (UTF). Comme nous avons 2 strings
    // alors faudra ajouter à ta taillle de l'enregistrement 4 octets de plus.
    // Voilà donc le dernier 4 dans le calcul de la taille.
    static final int TAILLE_ENREG = 4+20+12+4+4;

    // Ajouter au string lu dans le fichier le nombre d'espaces nécessaire
    // piur compléter sa taille.
    public static String formaterString(String leString, int tailleMax){
        int taille = tailleMax-leString.length();
        for(int i=0;i<taille;i++){
            leString+=" ";
        }
        return leString;
    }

    public static void ecrireDansFichierBinaire(){
        String elems[] = new String[4];
        int num, duree;
        String titre="", categ="";
        try{
            tmpReadTexte = new BufferedReader(new FileReader("src/films.txt"));
            tmpWriteBin = new RandomAccessFile("src/films.bin", "rw");
            String ligne = tmpReadTexte.readLine();
            while(ligne != null ){
                elems = ligne.split(";");
                num = Integer.parseInt(elems[0]);
                // Ceci fait la même chose que notre fonction formaterString
                // String.format("%20.20s",elems[1]);//20.20 min et max
                // String.format("%12.12s",elems[2]);
                titre = formaterString(elems[1],20);
                categ = formaterString(elems[2],12);
                duree= Integer.parseInt(elems[3]);

                //System.out.println(tmpWriteBin.getFilePointer());
                tmpWriteBin.writeInt(num);
                tmpWriteBin.writeUTF(titre);
                tmpWriteBin.writeUTF(categ);
                tmpWriteBin.writeInt(duree);
                ligne = tmpReadTexte.readLine();
            }
            tmpReadTexte.close();
            tmpWriteBin.close();
    }catch(Exception e){
        System.out.println("Gros probléme! " + e.getMessage());
    }
    }

    public static long obtenirAdresse(int cle) {
        long adr = (cle / 100 - 1) * TAILLE_ENREG;
        return adr;
    }

 public static void afficherInfosFilm(int cle) throws IOException {
     int num, duree;
     String titre, categ;
     long adr = obtenirAdresse(cle);
     System.out.println(adr);
     try {
         tmpWriteBin = new RandomAccessFile("src/films.bin", "rw");
         tmpWriteBin.seek(adr);
         num = tmpWriteBin.readInt();
         titre = tmpWriteBin.readUTF();
         categ = tmpWriteBin.readUTF();
         duree = tmpWriteBin.readInt();
         System.out.println("NUMÉRO = " + num + "\nTITRE = " + titre);
         System.out.println("CATÉGORIE = " + categ + "\nDURÉE = " + duree);
         System.out.println("*****************************");
     } catch (Exception e) {
         System.out.println("Gros probléme! " + e.getMessage());
     } finally{
        tmpWriteBin.close();
     }
 }

 public static void rechercherUnFilm() throws IOException {
     char choix;
     int cle;
     do {
         System.out.print("Entre le numéro du film : ");
         cle = Integer.parseInt(clavier.readLine());
         afficherInfosFilm(cle);

         System.out.print("Un autre (O-oui, autre non) : ");
         choix = clavier.readLine().trim().toUpperCase().charAt(0);
         
     } while (choix == 'O');
 }

 public static void remplacerTitre() throws IOException {
     String titre;
     int cle;
 
    System.out.print("Entrez le numéro du film : ");
    cle = Integer.parseInt(clavier.readLine());
    System.out.print("Entrez le nouveau titre du film : ");
    titre = formaterString(clavier.readLine(), 20);
    long adr = obtenirAdresse(cle);
     try {
         tmpWriteBin = new RandomAccessFile("src/films.bin", "rw");
         tmpWriteBin.seek(adr+4); // Passer sur le numéro du film et tomber sur le titre
         tmpWriteBin.writeUTF(titre);
     }catch(IOException e) {
         System.out.println("Gros probléme! " + e.getMessage());
     } finally{
        tmpWriteBin.close();
     }
         
 }

 public static void supprimerUnFilm() throws IOException {
     int cle;

     System.out.print("Entrez le numéro du film : ");
     cle = Integer.parseInt(clavier.readLine());
     
     long adr = obtenirAdresse(cle);
     try {
         tmpWriteBin = new RandomAccessFile("src/films.bin", "rw");
         tmpWriteBin.seek(adr); // Passer sur le numéro du film et tomber sur le titre
         tmpWriteBin.writeInt(-1);
         tmpWriteBin.writeUTF("                    ");
         tmpWriteBin.writeUTF("            ");
         tmpWriteBin.writeInt(0);
     } catch (IOException e) {
         System.out.println("Gros probléme! " + e.getMessage());
     } finally {
         tmpWriteBin.close();
     }

 }

 public static void listerFilms() throws IOException {
     int num, duree;
     String titre, categ;
     tmpWriteBin = new RandomAccessFile("src/films.bin", "rw");
     try {
        // Les fichiers binaires n'ont pas de fin de fichier. Donc
        // lorsqu'il n'aura plus de données à lire il va déclancher 
        // une exception que sera prise dans le catch et y ont ferme 
        // le fichier.
        System.out.println("\n********** LISTE DES FILMS **********\n");
        while (true) {// Boucle infinie
                num = tmpWriteBin.readInt();
                titre = tmpWriteBin.readUTF();
                categ = tmpWriteBin.readUTF();
                duree = tmpWriteBin.readInt();
                // -1 indique que le film a été supprimé mais il a fallut lire
                // les autres données pour se positionner au prochain enregistrement.
                if (num != -1) {
                    System.out.println("NUMÉRO = " + num + "\nTITRE = " + titre);
                    System.out.println("CATÉGORIE = " + categ + "\nDURÉE = " + duree);
                    System.out.println("*****************************");
                }
        }
     } catch (Exception e) {
         tmpWriteBin.close();
     } 
 }
 
public static void main(String args[]) throws IOException{
    
    clavier = new BufferedReader(new InputStreamReader(System.in));
    fichierBin = new File("src/films.bin");
    if(!fichierBin.exists()){
        ecrireDansFichierBinaire();
    }
    System.out.println("\nRecherche d'un film.\n");
    rechercherUnFilm();
    System.out.println("\nLister tous les films.\n");
    listerFilms();
    System.out.println("\nChanger le titre d'un film.\n"); 
    remplacerTitre(); 
    System.out.println("\nListe des films après changement du titre.\n");
    listerFilms();
    System.out.println("\nSupprimer un film.\n"); 
    supprimerUnFilm();
    System.out.println("\nListe des films après suppression d'un film.\n");
    listerFilms();
}
}
