package controllers;

import javafx.collections.ObservableList;
import models.Usager;
import models.UsagerDao;


public class UsagerController {

    private static UsagerController CtrU_Instance = null;
    private static UsagerDao Dao_Instance = null;

    private UsagerController(){}

    public static synchronized UsagerController getControleurU() {
        try {
            // if (CtrU_Instance == null) {
                CtrU_Instance = new UsagerController();
                Dao_Instance = UsagerDao.getUsagerDao();
            // }
            return CtrU_Instance;
        }
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getControleurLivre(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    public void CtrU_create(Usager usager) {
        Dao_Instance.MdlU_create(usager);
    }

    public ObservableList<Usager> CtrU_readAll() {
        return Dao_Instance.MdlU_readAll();
    }

    public void CtrU_update(String nomU, String prenomU, String adresseU, String telephoneU, String courrielU, String notesU, int idU) {
        Dao_Instance.MdlU_update(nomU, prenomU, adresseU, telephoneU, courrielU, notesU, idU);
    }

}
