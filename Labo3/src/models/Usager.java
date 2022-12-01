package models;

import java.sql.Timestamp;

public class Usager {

    private int idU;
    private String nomU;
    private String prenomU;
    private String adresseU;
    private String telephoneU;
    private String courrielU;
    private Timestamp dateAbonneU;
    private String notesU;

    public Usager() {  }

    public Usager(int idU, String nomU, String prenomU, String adresseU, String telephoneU, String courrielU,
    Timestamp dateAbonneU, String notesU) {
        this.idU = idU;
        this.nomU = nomU;
        this.prenomU = prenomU;
        this.adresseU = adresseU;
        this.telephoneU = telephoneU;
        this.courrielU = courrielU;
        this.dateAbonneU = dateAbonneU;
        this.notesU = notesU;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public String getNomU() {
        return nomU;
    }

    public void setNomU(String nomU) {
        this.nomU = nomU;
    }

    public String getPrenomU() {
        return prenomU;
    }

    public void setPrenomU(String prenomU) {
        this.prenomU = prenomU;
    }

    public String getAdresseU() {
        return adresseU;
    }

    public void setAdresseU(String adresseU) {
        this.adresseU = adresseU;
    }

    public String getTelephoneU() {
        return telephoneU;
    }

    public void setTelephoneU(String telephoneU) {
        this.telephoneU = telephoneU;
    }

    public String getCourrielU() {
        return courrielU;
    }

    public void setCourrielU(String courrielU) {
        this.courrielU = courrielU;
    }

    public Timestamp getDateAbonneU() {
        return dateAbonneU;
    }

    public void setDateAbonneU(Timestamp dateAbonneU) {
        this.dateAbonneU = dateAbonneU;
    }

    public String getNotesU() {
        return notesU;
    }

    public void setNotesU(String notesU) {
        this.notesU = notesU;
    }
    
    public String toString() {
    	return this.idU+"   "+this.nomU+", "+this.prenomU;
    }

}
