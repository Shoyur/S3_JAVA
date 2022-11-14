package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UsagerDao {
    private static Connection conn = null;
    private static UsagerDao instanceDao = null;

    private static final String URL_BD = "jdbc:mysql://sql9.freesqldatabase.com/sql9558434";
    private static final String USAGER = "sql9558434";
    private static final String PASS = "bQV64kWUMF";

    private static final String READ_ALL = "SELECT * FROM usagers";
    private static final String CREATE = "INSERT INTO usagers VALUES(?, ?, ?, ?, ?, ?, ?)";

    public UsagerDao() {  };
    
    public static synchronized UsagerDao getUsagerDao() {
        try {
            // if (instanceDao == null) {
                instanceDao = new UsagerDao();
                conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            // }
            return instanceDao;
        } 
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getUsagerDao(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    // CREATE
    public void MdlL_create(Usager usager) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(CREATE);
            // Usager(int id, String nom, String prenom, String adresse, String courriel, String telephone) {
            stmt.setInt(1, 0);
            stmt.setString(2, usager.getNom());
            stmt.setString(3, usager.getPrenom());
            stmt.setString(4, usager.getAdresse());
            stmt.setString(5, usager.getCourriel());
            stmt.setString(6, usager.getTelephone());
            stmt.setString(7, usager.getNotes());
            stmt.executeUpdate();
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlL_create(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }
    }

    // READ ALL
    public ObservableList<Usager> MdlL_readAll() {
        PreparedStatement stmt = null;
        ObservableList<Usager> listeUsagers = FXCollections.observableArrayList();
        try {
            stmt = conn.prepareStatement(READ_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usager usager = new Usager();
                // Usager(int id, String nom, String prenom, String adresse, String courriel, String telephone) 
                usager.setId(rs.getInt("id"));
                usager.setNom(rs.getString("nom"));
                usager.setPrenom(rs.getString("prenom"));
                usager.setAdresse(rs.getString("adresse"));
                usager.setCourriel(rs.getString("courriel"));
                usager.setTelephone(rs.getString("telephone"));
                usager.setNotes(rs.getString("notes"));
                listeUsagers.add(usager);
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlL_readAll()), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlL_Fermer(stmt);
            MdlL_Fermer(conn);
        }

        return listeUsagers;
    }
   
    private static void MdlL_Fermer(Connection conn) {
        if (conn != null) {
            try { conn.close(); } 
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlL_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }

    private static void MdlL_Fermer(Statement stmt) {
        if (stmt != null) {
            try { stmt.close(); }
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlL_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }
}
