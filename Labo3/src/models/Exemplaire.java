package models;

public class Exemplaire {

    private int id;
    private String album;
    private String artiste;
    private int annee;
    private String genre;
    private boolean possession;

    public Exemplaire() {}
    
    public Exemplaire(int id, String album, String artiste, int annee, String genre, boolean possession) {
        this.id = id;
        this.album = album;
        this.artiste = artiste;
        this.annee = annee;
        this.genre = genre;
        this.possession = possession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getArtiste() {
        return artiste;
    }

    public void setArtiste(String artiste) {
        this.artiste = artiste;
    }

    public int getAnnee() {
        return annee;
    }

    public void setAnnee(int annee) {
        this.annee = annee;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isPossession() {
        return possession;
    }

    public void setPossession(boolean possession) {
        this.possession = possession;
    }    
    
}
