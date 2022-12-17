package modeles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoPatient implements IDaoPatient {
    private static Connection conn = null;
    private static DaoPatient instanceDao = null;

    private static final String URL_BD = "jdbc:mysql://localhost/bdhopital";
    private static final String USAGER = "root";
    private static final String PASS = "";

    private static final String CREATE = "INSERT INTO patients VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String READ_ALL = "SELECT * FROM patients";
    private static final String READ_BY_ID = "SELECT * FROM patients WHERE idp=? LIMIT 1";
    private static final String READ_BY_NON_FUMEUR = "SELECT * FROM patients WHERE fumeur=false";
    // private static final String READ_BY_VILLE = "SELECT * FROM patients WHERE adresse = ?";
    // private static final String READ_BY_VILLE = "SELECT * FROM patients WHERE adresse LIKE = ?";
    private static final String READ_BY_VILLE = "SELECT * FROM patients WHERE adresse LIKE ?";
    private static final String DELETE = "DELETE FROM patients WHERE idp=?";
    
    public DaoPatient() {  };
    
    public static synchronized DaoPatient getDaoPatient() {
        try {
            if (instanceDao == null) {
                instanceDao = new DaoPatient();
                conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            }
            return instanceDao;
        } 
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getpatientDao(), e= " + e);
            throw new RuntimeException(e);
        }
    }
    
    // CREATE
    public void MdlP_create(Patient patient) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, 0);
            stmt.setString(2, patient.getNom());
            stmt.setString(3, patient.getPrenom());
            stmt.setString(4, patient.getDaten());
            stmt.setString(5, patient.getAdresse());
            stmt.setString(6, patient.getCp());
            stmt.setString(7, patient.getSexe());
            stmt.setBoolean(8, patient.isFumeur());
            stmt.executeUpdate();
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlP_create(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlP_Fermer(stmt);
            MdlP_Fermer(conn);
        }
    }

    // READ
    // READ ALL
    public List<Patient> MdlP_readAll() {
        PreparedStatement stmt = null;
        List<Patient> listepatients = new ArrayList<Patient>();
        try {
            stmt = conn.prepareStatement(READ_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setIdp(rs.getInt("idp"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setDaten(rs.getString("daten"));
                patient.setAdresse(rs.getString("adresse"));
                patient.setCp(rs.getString("cp"));
                patient.setSexe(rs.getString("sexe"));
                patient.setFumeur(rs.getBoolean("fumeur"));
                listepatients.add(patient);
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlP_readAll()), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlP_Fermer(stmt);
            MdlP_Fermer(conn);
        }

        return listepatients;
    }

    // READ BY IDP
    public List<Patient> MdlP_readById(String idp) {
        PreparedStatement stmt = null;
        List<Patient> listepatients = new ArrayList<Patient>();
        
        try {
            stmt = conn.prepareStatement(READ_BY_ID);
            stmt.setString(1, idp);
            ResultSet rs = stmt.executeQuery();
                Patient patient = new Patient();
                patient.setIdp(rs.getInt("idp"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setDaten(rs.getString("daten"));
                patient.setAdresse(rs.getString("adresse"));
                patient.setCp(rs.getString("cp"));
                patient.setSexe(rs.getString("sexe"));
                patient.setFumeur(rs.getBoolean("fumeur"));
                listepatients.add(patient);
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlP_readAll()), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlP_Fermer(stmt);
            MdlP_Fermer(conn);
        }
        return listepatients;
    }

    // READ BY NON-FUMEURS
    public List<Patient> MdlP_readByNonFumeur() {
        PreparedStatement stmt = null;
        List<Patient> listepatients = new ArrayList<Patient>();
        try {
            stmt = conn.prepareStatement(READ_BY_NON_FUMEUR);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setIdp(rs.getInt("idp"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setDaten(rs.getString("daten"));
                patient.setAdresse(rs.getString("adresse"));
                patient.setCp(rs.getString("cp"));
                patient.setSexe(rs.getString("sexe"));
                patient.setFumeur(rs.getBoolean("fumeur"));
                listepatients.add(patient);
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlP_readAll()), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlP_Fermer(stmt);
            MdlP_Fermer(conn);
        }

        return listepatients;
    }

    // READ BY VILLE
    public List<Patient> MdlP_readByVille(String ville) {
        PreparedStatement stmt = null;
        List<Patient> listepatients = new ArrayList<Patient>();
        try {
            stmt = conn.prepareStatement(READ_BY_VILLE);
            stmt.setString(1, "%" + ville + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Patient patient = new Patient();
                patient.setIdp(rs.getInt("idp"));
                patient.setNom(rs.getString("nom"));
                patient.setPrenom(rs.getString("prenom"));
                patient.setDaten(rs.getString("daten"));
                patient.setAdresse(rs.getString("adresse"));
                patient.setCp(rs.getString("cp"));
                patient.setSexe(rs.getString("sexe"));
                patient.setFumeur(rs.getBoolean("fumeur"));
                listepatients.add(patient);
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlP_readAll()), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlP_Fermer(stmt);
            MdlP_Fermer(conn);
        }

        return listepatients;
    }

    // DELETE
    public int MdlP_delete(int idp) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, idp);
            return stmt.executeUpdate();
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlP_delete(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlP_Fermer(stmt);
            MdlP_Fermer(conn);
        }
    }
   
    private static void MdlP_Fermer(Connection conn) {
        if (conn != null) {
            try { 
                
                conn.close();
                instanceDao = null;
            } 
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlP_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }

    private static void MdlP_Fermer(Statement stmt) {
        if (stmt != null) {
            try { stmt.close(); }
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlP_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }
}
