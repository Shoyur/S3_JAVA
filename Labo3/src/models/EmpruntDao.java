package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EmpruntDao {
    private static Connection conn = null;
    private static EmpruntDao instanceDao = null;

    private static final String URL_BD = "jdbc:mysql://sql9.freesqldatabase.com/sql9558434";
    private static final String USAGER = "sql9558434";
    private static final String PASS = "bQV64kWUMF";

    private static final String CREATE = "INSERT INTO emprunt VALUES(?, ?, ?, ?, ?)";
    private static final String READ_ALL = "SELECT * FROM emprunt";

    public EmpruntDao() {  }
    
    public static synchronized EmpruntDao getEmpruntDao() {
        try {
            // if (instanceDao == null) {
                instanceDao = new EmpruntDao();
                conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            // }
            return instanceDao;
        } 
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getEmpruntDao(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    // CREATE
    public void MdlEm_create(Emprunt emprunt) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(CREATE);
            stmt.setInt(1, emprunt.getIdEm());
            stmt.setInt(2, emprunt.getIdEx());
            stmt.setInt(3, emprunt.getIdU());
            stmt.setTimestamp(4, emprunt.getDateEm());
            stmt.setInt(5, emprunt.getNbJoursEm());
            stmt.executeUpdate();
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlEm_create(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlEm_Fermer(stmt);
            MdlEm_Fermer(conn);
        }
    }

    // READ ALL
    public ObservableList<Emprunt> MdlEm_readAll() {
        PreparedStatement stmt = null;
        ObservableList<Emprunt> listeEmprunt = FXCollections.observableArrayList();
        try {
            stmt = conn.prepareStatement(READ_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Emprunt emprunt = new Emprunt();
                emprunt.setIdEm(rs.getInt("idEm"));
                emprunt.setIdEx(rs.getInt("idEx"));
                emprunt.setIdU(rs.getInt("idU"));
                emprunt.setDateEm(rs.getTimestamp("dateEm"));
                emprunt.setNbJoursEm(rs.getInt("nbJoursEm"));
                listeEmprunt.add(emprunt);
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlEm_readAll()), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlEm_Fermer(stmt);
            MdlEm_Fermer(conn);
        }

        return listeEmprunt;
    }
   
    private static void MdlEm_Fermer(Connection conn) {
        if (conn != null) {
            try { conn.close(); } 
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlEm_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }

    private static void MdlEm_Fermer(Statement stmt) {
        if (stmt != null) {
            try { stmt.close(); }
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlEm_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }
}
