public class Periodique extends Ouvrage {

    private String nom;
    private int numero;
    private int periodicite;
    
    Periodique () {}

    Periodique (String nom, int numero, int periodicite) {
        this.nom = nom;
        this.numero = numero;
        this.periodicite = periodicite;
    }

    public String toString() {
        return super.toString() + "\t" + this.nom + "\t" + this.numero + "\t" + this.periodicite;
    }

}
