package models;

import java.sql.Date;

public class Historique {

    private Date quand;
    private String quoi;

    public Historique() {}
    
    public Historique(Date quand, String quoi) {
        this.quand = quand;
        this.quoi = quoi;
    }

    public Date getQuand() {
        return quand;
    }

    public void setQuand(Date quand) {
        this.quand = quand;
    }

    public String getQuoi() {
        return quoi;
    }

    public void setQuoi(String quoi) {
        this.quoi = quoi;
    }

}
