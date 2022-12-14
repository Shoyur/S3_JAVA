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

    public Exemplaire CtrEx_read(int idEx) {
    	return Dao_Instance.MdlEx_read(idEx);
    }

    public int CtrEx_readAllEmp() {
        return Dao_Instance.MdlEx_readAllEmp();
    }

    public void CtrEx_update(Exemplaire exemplaire) {
        Dao_Instance.MdlEx_update(exemplaire);
    }

}
