package edu.java.dao.models.modelFilm;

public class Film {
	private int idf;
	private String titre;
	private int duree;
	private String res;
	private String pochette;

	public int getIdf() {
		return idf;
	}
	public void setIdf(int idf) {
		this.idf = idf;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre= titre;
	}
    public int getDuree() {
        return duree;
    }
    public void setDuree(int duree) {
        this.duree = duree;
    }
    public String getRes() {
        return res;
    }
    public void setRes(String res) {
        this.res = res;
    }

    public String getPochette() {
        return pochette;
    }

    public void setPochette(String pochette) {
        this.pochette = pochette;
    }
}
