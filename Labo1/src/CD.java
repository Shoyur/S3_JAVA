public class CD extends Ouvrage {

    private String auteur;
    private String titre;

    CD () {}

    CD (String auteur, String titre) {
        this.auteur = auteur;
        this.titre = titre;
    }

    public String toString() {
        return super.toString() + "\t" + this.auteur + "\t" + this.titre;
    }

}
