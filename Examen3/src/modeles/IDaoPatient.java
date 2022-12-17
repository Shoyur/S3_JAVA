package modeles;

import java.util.List;

public interface IDaoPatient {

    // CRUD
    
    // CREATE
    public void MdlP_create(Patient patient);
    // READ
    public List<Patient> MdlP_readAll();
    public List<Patient> MdlP_readById(String idp);
    public List<Patient> MdlP_readByNonFumeur();
    public List<Patient> MdlP_readByVille(String ville);

    // DELETE
    public int MdlP_delete(int no);

}
