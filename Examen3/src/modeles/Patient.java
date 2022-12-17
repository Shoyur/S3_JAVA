package modeles;

public class Patient {
    
	private int idp;
	private String nom;
	private String prenom;
	private String daten;
	private String sexe;
	private String adresse;
    private String cp;
    private boolean fumeur;

    public int getIdp() {
        return idp;
    }
    public void setIdp(int idp) {
        this.idp = idp;
    }
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public String getDaten() {
        return daten;
    }
    public void setDaten(String daten) {
        this.daten = daten;
    }
    public String getSexe() {
        return sexe;
    }
    public void setSexe(String sexe) {
        this.sexe = sexe;
    }
    public String getAdresse() {
        return adresse;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public String getCp() {
        return cp;
    }
    public void setCp(String cp) {
        this.cp = cp;
    }
    public boolean isFumeur() {
        return fumeur;
    }
    public void setFumeur(boolean fumeur) {
        this.fumeur = fumeur;
    }
    



}
