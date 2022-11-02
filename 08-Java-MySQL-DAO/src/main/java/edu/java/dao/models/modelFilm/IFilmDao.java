package edu.java.dao.models.modelFilm;

import java.util.List;

public interface IFilmDao {

    // Pour le CRUD - Create Read Update Delete
    
    // Create
    public String MdlF_Enregistrer(Film film);
    
    // Read
    public List<Film> MdlF_GetAll();

    public Film MdlF_GetById(int idf);

    public Film MdlF_GetByTitre(String titre);
    
    // Update
    public int MdlF_Modifier(Film user);

    // Delete
    public int MdlF_Supprimer(int idf);

}
