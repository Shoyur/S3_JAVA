package controleurs;

import java.util.List;

import modeles.Livre;
import modeles.LivreDao;


public class ControleurLivre implements IActionsLivre {

    private static ControleurLivre CtrL_Instance = null;
    private static LivreDao Dao_Instance = null;
    // Singleton du contrôleur
    // getControleurFilm() est devenu une zonne critique.
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion
    private ControleurLivre(){}

    public static synchronized ControleurLivre getControleurLivre() {
        try {
            if (CtrL_Instance == null) {
                CtrL_Instance = new ControleurLivre();
                Dao_Instance = LivreDao.getLivreDao();
            }
            return CtrL_Instance;
        }
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getControleurLivre(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void CtrL_create(Livre livre) {
        Dao_Instance.MdlL_create(livre);
        
    }

    @Override
    public List<Livre> CtrL_readAll() {
        return Dao_Instance.MdlL_readAll();
    }

    @Override
    public List<Livre> CtrL_readByCateg(String categ) {
        return Dao_Instance.MdlL_readByCateg(categ);
    }

    @Override
    public Livre CtrL_readByNo(int no) {
        return Dao_Instance.MdlL_readByNo(no);
    }

    @Override
    public List<Livre> CtrL_readByAuteur(int auteur) {
        return Dao_Instance.MdlL_readByAuteur(auteur);
    }

    @Override
    public List<String> CtrL_readAllCategs() {
        return Dao_Instance.MdlL_readAllCategs();
    }

    @Override
    public List<Integer> CtrL_readAllNos() {
        return Dao_Instance.MdlL_readAllNos();
    }

    @Override
    public List<Integer> CtrL_readAllAuteurs() {
        return Dao_Instance.MdlL_readAllAuteurs();
    }

    @Override
    public int CtrL_updateTitre(String titre, int no) {
        return Dao_Instance.MdlL_updateTitre(titre, no);
    }

    @Override
    public int CtrL_delete(int no) {
        return Dao_Instance.MdlL_delete(no);
    }
}
