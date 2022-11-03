package modeles;

import java.util.List;

public interface ILivreDao {

    // CRUD
    
    // CREATE
    public void MdlL_create(Livre livre);
    // READ
    public List<Livre> MdlL_readAll();
    public List<Livre> MdlL_readByCateg(String categ);
    public Livre MdlL_readByNo(int no);
    public List<Livre> MdlL_readByAuteur(int auteur);
    public List<String> MdlL_readAllCategs();
    public List<Integer> MdlL_readAllNos();
    public List<Integer> MdlL_readAllAuteurs();
    // UPDATE
    public int MdlL_updateTitre(String titre, int no);
    // DELETE
    public int MdlL_delete(int no);

}
