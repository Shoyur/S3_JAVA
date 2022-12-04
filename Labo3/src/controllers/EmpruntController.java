package controllers;

import javafx.collections.ObservableList;
import models.Emprunt;
import models.EmpruntDao;
import models.Exemplaire;


public class EmpruntController {

    private static EmpruntController CtrEm_Instance = null;
    private static EmpruntDao Dao_Instance = null;

    private EmpruntController(){}

    public static synchronized EmpruntController getControleurEm() {
        try {
            // if (CtrEm_Instance == null) {
                CtrEm_Instance = new EmpruntController();
                Dao_Instance = EmpruntDao.getEmpruntDao();
            // }
            return CtrEm_Instance;
        }
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getControleurEm(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    public void CtrEm_create(Emprunt emprunt) {
        Dao_Instance.MdlEm_create(emprunt);
    }
    
    public ObservableList<Emprunt> CtrEm_readAllParUsager(int idU) {
    	return Dao_Instance.MdlEm_readAllParUsager(idU);
    }

    /*
    public ObservableList<Exemplaire> CtrEx_readAll(int option) {
        return Dao_Instance.MdlEm_readAll(option);
    }

    public void CtrEx_update(String titreEx, String artisteEx, String categEx, int anneeEx, double prixEx, String pistesEx, int idEx, String cheminImgEx) {
        Dao_Instance.MdlEm_update(titreEx, artisteEx, categEx, anneeEx, prixEx, pistesEx, idEx, cheminImgEx);
    }
	*/
}