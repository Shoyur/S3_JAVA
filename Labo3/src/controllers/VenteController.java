package controllers;

import javafx.collections.ObservableList;
import models.Emprunt;
import models.Vente;
import models.VenteDao;

public class VenteController {

    private static VenteController CtrV_Instance = null;
    private static VenteDao Dao_Instance = null;

    private VenteController(){}

    public static synchronized VenteController getControleurV() {
        try {
            // if (CtrV_Instance == null) {
                CtrV_Instance = new VenteController();
                Dao_Instance = VenteDao.getVenteDao();
            // }
            return CtrV_Instance;
        }
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getControleurV(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    public void CtrV_create(Vente vente) {
        Dao_Instance.MdlV_create(vente);
    }
    
    public ObservableList<Vente> CtrV_readAllParUsager(int idU) {
    	return Dao_Instance.MdlV_readAllParUsager(idU);
    }

    /*
    public ObservableList<Exemplaire> CtrEx_readAll(int option) {
        return Dao_Instance.MdlEm_readAll(option);
    }
    
    public Exemplaire CtrEx_read(int idEx) {
    	return Dao_Instance.MdlEm_read(idEx);
    }

    public void CtrEx_update(String titreEx, String artisteEx, String categEx, int anneeEx, double prixEx, String pistesEx, int idEx, String cheminImgEx) {
        Dao_Instance.MdlEm_update(titreEx, artisteEx, categEx, anneeEx, prixEx, pistesEx, idEx, cheminImgEx);
    }
	*/
}
