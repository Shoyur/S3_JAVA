import java.util.ArrayList;

public class FenetreDev {

    public static ArrayList<Professeur> listeProfs = new ArrayList<Professeur>();

    public static void initialiser() {
        Professeur prof1 = new Professeur(0, "Robert", 36, 44);
        Professeur prof2 = new Professeur(1, "Joseph", 45, 46);
        Professeur prof3 = new Professeur(2, "Daniel", 42, 48);
        listeProfs.add(prof1);
        listeProfs.add(prof2);
        listeProfs.add(prof3);
    }

    public static String afficherTous() {
        String resultat = "";
        resultat += "Tous les professeurs :\n\n";
        for (Professeur unProfesseur : listeProfs) {
            resultat += unProfesseur.toString() + "\n";
        }
        return resultat;
    }

    public static String afficherStats() {
        String resultat = "";
        resultat += "Statistiques :\n\n";
        int nbProfs = 1;
        int moyenne = 0;
        int basse = 50;
        int haute = 0;
        for (Professeur unProfesseur : listeProfs) {
            nbProfs++;
            moyenne += unProfesseur.calculerTache();
            if (unProfesseur.getAutomne() < basse) { basse = unProfesseur.getAutomne(); }
            if (unProfesseur.getHiver() < basse) { basse = unProfesseur.getHiver(); }
            if (unProfesseur.getAutomne() > haute) { haute = unProfesseur.getAutomne(); }
            if (unProfesseur.getHiver() > haute) { haute = unProfesseur.getHiver(); }
        }
        moyenne = moyenne / nbProfs;
        resultat += "Moyenne des tâches :   " + moyenne + "\n";
        resultat += "Tâche la plus basse :  " + basse + "\n";
        resultat += "Tâche la plus haute :  " + haute + "\n";
        return resultat;
    }

    public static void ajouter(int no, String nom, int automne, int hiver) {
        listeProfs.add(new Professeur(no, nom, automne, hiver));
    }

    public static void effacer(int no) {
        for (Professeur unProfesseur : listeProfs) {
            if (unProfesseur.getNo() == no) {
                listeProfs.remove(unProfesseur);
                return;
            }
        }
    }

    public static void modifier(int no, String nom, int automne, int hiver) {
        for (Professeur unProfesseur : listeProfs) {
            if (unProfesseur.getNo() == no) {
                unProfesseur.setNom(nom);
                unProfesseur.setAutomne(automne);
                unProfesseur.setHiver(hiver);
                return;
            }
        }
    }
    
}
