package models;

import java.sql.Timestamp;

public class Emprunt {

    private int idEm;
    private int idEx;
    private int idU;
    private Timestamp dateEm;
    private int nbJoursEm;
    
    public Emprunt() {  }

    public Emprunt(int idEm, int idEx, int idU, Timestamp dateEm, int nbJoursEm) {
        this.idEm = idEm;
        this.idEx = idEx;
        this.idU = idU;
        this.dateEm = dateEm;
        this.nbJoursEm = nbJoursEm;
    }

    public int getIdEm() {
        return idEm;
    }

    public void setIdEm(int idEm) {
        this.idEm = idEm;
    }

    public int getIdEx() {
        return idEx;
    }

    public void setIdEx(int idEx) {
        this.idEx = idEx;
    }

    public int getIdU() {
        return idU;
    }

    public void setIdU(int idU) {
        this.idU = idU;
    }

    public Timestamp getDateEm() {
        return dateEm;
    }

    public void setDateEm(Timestamp dateEm) {
        this.dateEm = dateEm;
    }

    public int getNbJoursEm() {
        return nbJoursEm;
    }

    public void setNbJoursEm(int nbJoursEm) {
        this.nbJoursEm = nbJoursEm;
    }
    
}
