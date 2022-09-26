public class Ouvrage {

    private String dateEmprunt;
    private int cote;
    
    Ouvrage () {}

    Ouvrage (String dateEmprunt, int cote) {
        this.dateEmprunt = dateEmprunt;
        this.cote = Bibliotheque.cote + 1;
    }

    public String toString() {
        return this.dateEmprunt + "\t" + this.cote;
    }

}
