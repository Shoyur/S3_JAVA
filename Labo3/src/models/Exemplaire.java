package models;

public class Exemplaire {

    private int idEx;
    private String titreEx;
    private String artisteEx;
    private String categEx;
    private int anneeEx;
    private double prixEx;
    private String pistesEx;
    private int nbEmpruntsEx;
    private boolean estEmprunte;
    private boolean estVendu;
    private String cheminImgEx;

    public Exemplaire() {  }

    public Exemplaire(int idEx, String titreEx, String artisteEx, String categEx, int anneeEx, double prixEx,
            String pistesEx, int nbEmpruntsEx, boolean estEmprunte, boolean estVendu, String cheminImgEx) {
        this.idEx = idEx;
        this.titreEx = titreEx;
        this.artisteEx = artisteEx;
        this.categEx = categEx;
        this.anneeEx = anneeEx;
        this.prixEx = prixEx;
        this.pistesEx = pistesEx;
        this.nbEmpruntsEx = nbEmpruntsEx;
        this.estEmprunte = estEmprunte;
        this.estVendu = estVendu;
        this.cheminImgEx = cheminImgEx;
    }

	public int getIdEx() {
        return idEx;
    }

    public void setIdEx(int idEx) {
        this.idEx = idEx;
    }

    public String getTitreEx() {
        return titreEx;
    }

    public void setTitreEx(String titreEx) {
        this.titreEx = titreEx;
    }

    public String getArtisteEx() {
        return artisteEx;
    }

    public void setArtisteEx(String artisteEx) {
        this.artisteEx = artisteEx;
    }

    public String getCategEx() {
        return categEx;
    }

    public void setCategEx(String categEx) {
        this.categEx = categEx;
    }

    public int getAnneeEx() {
        return anneeEx;
    }

    public void setAnneeEx(int anneeEx) {
        this.anneeEx = anneeEx;
    }

    public double getPrixEx() {
        return prixEx;
    }

    public void setPrixEx(double prixEx) {
        this.prixEx = prixEx;
    }

    public String getPistesEx() {
        return pistesEx;
    }

    public void setPistesEx(String pistesEx) {
        this.pistesEx = pistesEx;
    }

    public int getNbEmpruntsEx() {
        return nbEmpruntsEx;
    }

    public void setNbEmpruntsEx(int nbEmpruntsEx) {
        this.nbEmpruntsEx = nbEmpruntsEx;
    }

    public boolean isEstEmprunte() {
        return estEmprunte;
    }

    public void setEstEmprunte(boolean estEmprunte) {
        this.estEmprunte = estEmprunte;
    }

    public boolean isEstVendu() {
        return estVendu;
    }

    public void setEstVendu(boolean estVendu) {
        this.estVendu = estVendu;
    }
    
    public String getCheminImgEx() {
		return cheminImgEx;
	}
    
	public void setCheminImgEx(String cheminImgEx) {
		this.cheminImgEx = cheminImgEx;
	}
    
}
