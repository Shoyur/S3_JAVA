package models;

import java.sql.Date;

public class Vente {

    private int idV;
    private int idEx;
    private int idU;
    private Date dateV;
    
    public Vente() {  }

    public Vente(int idV, int idEx, int idU, Date dateV) {
        this.idV = idV;
        this.idEx = idEx;
        this.idU = idU;
        this.dateV = dateV;
    }

    public int getIdV() {
        return idV;
    }

    public void setIdV(int idV) {
        this.idV = idV;
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

    public Date getDateV() {
        return dateV;
    }

    public void setDateV(Date dateV) {
        this.dateV = dateV;
    }
    
}
