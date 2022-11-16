public class Professeur {

	private int no;
	private String nom;
	private int automne;
	private int hiver;
    
    public Professeur(int no, String nom, int automne, int hiver) {
        setNo(no);
        setNom(nom);
        setAutomne(automne);
        setHiver(hiver);
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getAutomne() {
        return automne;
    }

    public void setAutomne(int automne) {
        if (automne >= 0 && automne <= 50) {
            this.automne = automne;
        }
        else { 
            this.automne = 0; 
        }
    }

    public int getHiver() {
        return hiver;
    }

    public void setHiver(int hiver) {
        if (hiver >= 0 && hiver <= 50) {
            this.hiver = hiver;
        }
        else { 
            this.hiver = 0; 
        }
    }

    public int calculerTache() {
        return getAutomne() + getHiver();
    }

    public String toString() {
        return 
        "No.: " + getNo() + 
        ", Nom: " + getNom() + 
        ", Automne: " + getAutomne() + 
        ", Hiver: " + getHiver() + 
        ", Total: " + calculerTache();
    }

    public String message() {
        if (calculerTache() >= 88) { return "plein"; }
        else { return "partiel"; } 
    }

}
