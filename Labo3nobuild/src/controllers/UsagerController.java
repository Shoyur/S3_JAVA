package controllers;

import javafx.collections.ObservableList;
import models.Usager;
import models.UsagerDao;


public class UsagerController {

    private static UsagerController CtrL_Instance = null;
    private static UsagerDao Dao_Instance = null;

    private UsagerController(){}

    public static synchronized UsagerController getControleurU() {
        try {
            // if (CtrL_Instance == null) {
                CtrL_Instance = new UsagerController();
                Dao_Instance = UsagerDao.getUsagerDao();
            // }
            return CtrL_Instance;
        }
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getControleurLivre(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    public void CtrL_create(Usager usager) {
        Dao_Instance.MdlL_create(usager);
    }

    public ObservableList<Usager> CtrL_readAll() {
        return Dao_Instance.MdlL_readAll();
    }

}
