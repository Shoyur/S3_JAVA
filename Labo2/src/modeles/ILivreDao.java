package modeles;

import java.util.List;

public interface ILivreDao {

    // CRUD
    
    // CREATE
    public void create(Livre livre);
    // READ
    public List<Livre> readAll();
    public List<Livre> readByCateg(String categ);
    public Livre readByNo(int no);
    public List<Livre> readByAuteur(int auteur);
    // UPDATE
    public int updateTitre(String titre, int no);
    // DELETE
    public int delete(int no);

}
