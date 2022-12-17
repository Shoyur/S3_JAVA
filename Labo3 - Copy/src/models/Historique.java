package models;

import java.sql.Timestamp;

public class Historique {

    private Timestamp quand;
    private String quoi;

    public Historique() {}
    
    public Historique(Timestamp quand, String quoi) {
        this.quand = quand;
        this.quoi = quoi;
    }

    public Timestamp getQuand() {
        return quand;
    }

    public void setQuand(Timestamp quand) {
        this.quand = quand;
    }

    public String getQuoi() {
        return quoi;
    }

    public void setQuoi(String quoi) {
        this.quoi = quoi;
    }

}
