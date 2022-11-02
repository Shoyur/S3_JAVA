package edu.java.dao.controleurs.controleurFilm;
import edu.java.dao.models.modelFilm.Film;

public interface IActionsFilm {
    // Pour le CRUD - Create Read Update Delete

    // Create
    public String CtrF_Enregistrer(Film film);
    
    // // Read
    // public List<Film> CtrF_GetAllFilms();

    // public Film CtrF_GetFilmById(int idf);

    // public Film CtrF_GetFilmByTitre(String titre);

    // // Update
    // public int CtrF_Modifier(Film user);

    // // Delete
    // public int CtrF_Enlever(int idf); 
}
