package models;

<<<<<<< HEAD
import java.sql.Date;
=======
import java.sql.Timestamp;
>>>>>>> origin/Davideh

public class Vente {

    private int idV;
    private int idEx;
    private int idU;
<<<<<<< HEAD
    private Date dateV;
    
    public Vente() {  }

    public Vente(int idV, int idEx, int idU, Date dateV) {
=======
    private Timestamp dateV;
    
    public Vente() {  }

    public Vente(int idV, int idEx, int idU, Timestamp dateV) {
>>>>>>> origin/Davideh
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

<<<<<<< HEAD
    public Date getDateV() {
        return dateV;
    }

    public void setDateV(Date dateV) {
=======
    public Timestamp getDateV() {
        return dateV;
    }

    public void setDateV(Timestamp dateV) {
>>>>>>> origin/Davideh
        this.dateV = dateV;
    }
    
}
