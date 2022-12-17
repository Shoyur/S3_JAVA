package controllers;

import javafx.collections.ObservableList;
import models.Historique;
import models.HistoriqueDao;


public class HistoriqueController {

    private static HistoriqueController CtrH_Instance = null;
    private static HistoriqueDao Dao_Instance = null;

    private HistoriqueController(){}

    public static synchronized HistoriqueController getControleurH() {
        try {
            // if (CtrH_Instance == null) {
                CtrH_Instance = new HistoriqueController();
                Dao_Instance = HistoriqueDao.getHistoriqueDao();
            // }
            return CtrH_Instance;
        }
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getControleurLivre(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    public void CtrH_create(Historique historique) {
        Dao_Instance.MdlH_create(historique);
    }

    public ObservableList<Historique> CtrH_readAll() {
        return Dao_Instance.MdlH_readAll();
    }

}
