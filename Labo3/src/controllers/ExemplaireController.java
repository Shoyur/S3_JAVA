package controllers;

import javafx.collections.ObservableList;
import models.Exemplaire;
import models.ExemplaireDao;


public class ExemplaireController {

    private static ExemplaireController CtrL_Instance = null;
    private static ExemplaireDao Dao_Instance = null;

    private ExemplaireController(){}

    public static synchronized ExemplaireController getControleurEx() {
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

    public void CtrEx_create(Exemplaire exemplaire) {
        Dao_Instance.MdlEx_create(exemplaire);
    }

    public ObservableList<Exemplaire> CtrEx_readAll(int option) {
        return Dao_Instance.MdlEx_readAll(option);
    }

    public void CtrEx_update(String titreEx, String artisteEx, String categEx, int anneeEx, double prixEx, String pistesEx, int idEx, String cheminImgEx) {
        Dao_Instance.MdlEx_update(titreEx, artisteEx, categEx, anneeEx, prixEx, pistesEx, idEx, cheminImgEx);
    }

}
