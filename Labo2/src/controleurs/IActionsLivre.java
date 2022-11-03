package controleurs;

import java.util.List;

import modeles.Livre;


public interface IActionsLivre {

    // CRUD
    
    // CREATE
    public void CtrL_create(Livre livre);
    // READ
    public List<Livre> CtrL_readAll();
    public List<Livre> CtrL_readByCateg(String categ);
    public Livre CtrL_readByNo(int no);
    public List<Livre> CtrL_readByAuteur(int auteur);
    public List<String> CtrL_readAllCategs();
    public List<Integer> CtrL_readAllNos();
    public List<Integer> CtrL_readAllAuteurs();
    // UPDATE
    public int CtrL_updateTitre(String titre, int no);
    // DELETE
    public int CtrL_delete(int no);
    
}
