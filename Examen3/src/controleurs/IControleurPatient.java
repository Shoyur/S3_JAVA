package controleurs;

import java.util.List;

import modeles.Patient;


public interface IControleurPatient {

    // CRUD
    
    // CREATE
    public void CtrP_create(Patient patient);
    // READ
    public List<Patient> CtrP_readAll();
    public List<Patient> CtrP_readById(String idp);
    public List<Patient> CtrP_readByNonFumeur();
    public List<Patient> CtrP_readByVille(String ville);

    // DELETE
    public int CtrP_delete(int idp);
    
}
