package controllers;

import javafx.collections.ObservableList;
import models.Exemplaire;
import models.ExemplaireDao;


public class ExemplaireController {

    private static ExemplaireController CtrL_Instance = null;
    private static ExemplaireDao Dao_Instance = null;

    private ExemplaireController(){}

    public static synchronized ExemplaireController getControleurE() {
        try {
            // if (CtrL_Instance == null) {
                CtrL_Instance = new ExemplaireController();
                Dao_Instance = ExemplaireDao.getExemplaireDao();
            // }
            return CtrL_Instance;
        }
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getControleurLivre(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    public ObservableList<Exemplaire> CtrL_readAll() {
        return Dao_Instance.MdlL_readAll();
    }

}
