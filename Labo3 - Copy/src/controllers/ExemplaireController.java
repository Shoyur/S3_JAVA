package controllers;

import javafx.collections.ObservableList;
import models.Exemplaire;
import models.ExemplaireDao;


public class ExemplaireController {

    private static ExemplaireController CtrEx_Instance = null;
    private static ExemplaireDao Dao_Instance = null;

    private ExemplaireController(){}

    public static synchronized ExemplaireController getControleurEx() {
        try {
            // if (CtrEx_Instance == null) {
                CtrEx_Instance = new ExemplaireController();
                Dao_Instance = ExemplaireDao.getExemplaireDao();
            // }
            return CtrEx_Instance;
        }
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getControleurEx(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    public void CtrEx_create(Exemplaire exemplaire) {
        Dao_Instance.MdlEx_create(exemplaire);
    }

    public ObservableList<Exemplaire> CtrEx_readAll(int option) {
        return Dao_Instance.MdlEx_readAll(option);
    }
<<<<<<< HEAD

    public void CtrEx_update(String titreEx, String artisteEx, String categEx, int anneeEx, double prixEx, String pistesEx, int idEx) {
        Dao_Instance.MdlEx_update(titreEx, artisteEx, categEx, anneeEx, prixEx, pistesEx, idEx);
=======
    
    public Exemplaire CtrEx_read(int idEx) {
    	return Dao_Instance.MdlEx_read(idEx);
    }

    public void CtrEx_update(Exemplaire exemplaire) {
        Dao_Instance.MdlEx_update(exemplaire);
>>>>>>> origin/Davideh
    }

}
