package edu.java.dao;

import edu.java.dao.controleurs.controleurFilm.ControleurFilm;
import edu.java.dao.models.modelFilm.Film;

public final class App {
    private App() {
    }

    public static void main(String[] args) {
        // Selon le choix de l'utilisateur faudra appeler la bonne méthode
        // du contrôleur.
        // CAS 1 : Enregistrer un film
        Film film = new Film ();
        film.setTitre("Conan");
        film.setDuree(90);
        film.setRes("Arnold");
        film.setPochette("https://ia.media-imdb.com/images/M/MV5BMTYwOTEwNjA...");
        
        ControleurFilm CtrF = ControleurFilm.getControleurFilm();
        String message = CtrF.CtrF_Enregistrer(film);
        System.out.println(message);
    }
}
