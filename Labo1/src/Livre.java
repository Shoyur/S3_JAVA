public class Livre extends Ouvrage {

    private String auteur;
    private String titre;
    private String editeur;

    Livre () {}

    Livre (String auteur, String titre, String editeur) {
        this.auteur = auteur;
        this.titre = titre;
        this.editeur = editeur;
    }

    public String toString() {
        return super.toString() + "\t" + this.auteur + "\t" + this.titre + "\t" + this.editeur;
    }

}
