package controleurs;

import java.util.List;

import modeles.Patient;
import modeles.DaoPatient;


public class ControleurPatient implements IControleurPatient {

    private static ControleurPatient CtrP_Instance = null;
    private static DaoPatient instanceDao = null;
    // Singleton du contrôleur
    // getControleurFilm() est devenu une zonne critique.
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion
    private ControleurPatient(){}

    public static synchronized ControleurPatient getControleurPatient() {
        try {
            if (CtrP_Instance == null) {
                CtrP_Instance = new ControleurPatient();
                instanceDao = DaoPatient.getDaoPatient();
            }
            return CtrP_Instance;
        }
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getControleurPatient(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void CtrP_create(Patient patient) {
        CtrP_Instance = null;
        instanceDao.MdlP_create(patient);
    }

    @Override
    public List<Patient> CtrP_readAll() {
        CtrP_Instance = null;
        return instanceDao.MdlP_readAll();
    }

    @Override
    public List<Patient> CtrP_readById(String idp) {
        CtrP_Instance = null;
        return instanceDao.MdlP_readById(idp);
    }

    @Override
    public List<Patient> CtrP_readByNonFumeur() {
        CtrP_Instance = null;
        return instanceDao.MdlP_readByNonFumeur();
    }

    @Override
    public List<Patient> CtrP_readByVille(String ville) {
        CtrP_Instance = null;
        return instanceDao.MdlP_readByVille(ville);
    }

    @Override
    public int CtrP_delete(int idp) {
        CtrP_Instance = null;
        return instanceDao.MdlP_delete(idp);
    }
}
