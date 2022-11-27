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

    
    private static final String CREATE = "INSERT INTO usager VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String READ_ALL = "SELECT * FROM usager";
    private static final String UPDATE = "UPDATE usager SET nomU=?, prenomU=?, adresseU=?, telephoneU=?, courrielU=?, notesU=? where idU=?";

    public UsagerDao() {  }
    
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
    // Usager(int idU, String nomU, String prenomU, String adresseU, String telephoneU, String courrielU,
    //         Date dateAbonneU, String notesU)
    public void MdlU_create(Usager usager) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(CREATE);
            // Usager(int id, String nom, String prenom, String adresse, String courriel, String telephone) {
            stmt.setInt(1, 0);
            stmt.setString(2, usager.getNomU());
            stmt.setString(3, usager.getPrenomU());
            stmt.setString(4, usager.getAdresseU());
            stmt.setString(5, usager.getTelephoneU());
            stmt.setString(6, usager.getCourrielU());
            stmt.setTimestamp(7, usager.getDateAbonneU());
            stmt.setString(8, usager.getNotesU());
            stmt.executeUpdate();
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlU_create(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlU_Fermer(stmt);
            MdlU_Fermer(conn);
        }
    }

    // READ ALL
    // Usager(int idU, String nomU, String prenomU, String adresseU, String telephoneU, String courrielU,
    //         Date dateAbonneU, String notesU)
    public ObservableList<Usager> MdlU_readAll() {
        PreparedStatement stmt = null;
        ObservableList<Usager> listeUsagers = FXCollections.observableArrayList();
        try {
            stmt = conn.prepareStatement(READ_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Usager usager = new Usager();
                // Usager(int id, String nom, String prenom, String adresse, String courriel, String telephone) 
                usager.setIdU(rs.getInt("idU"));
                usager.setNomU(rs.getString("nomU"));
                usager.setPrenomU(rs.getString("prenomU"));
                usager.setAdresseU(rs.getString("adresseU"));
                usager.setTelephoneU(rs.getString("telephoneU"));
                usager.setCourrielU(rs.getString("courrielU"));
                usager.setDateAbonneU(rs.getTimestamp("dateAbonneU"));
                usager.setNotesU(rs.getString("notesU"));
                listeUsagers.add(usager);
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlU_readAll()), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlU_Fermer(stmt);
            MdlU_Fermer(conn);
        }

        return listeUsagers;
    }

    // UPDATE
    // "UPDATE usager SET nomU=?, prenomU=?, adresseU=?, telephoneU=?, courrielU=?, notesU=? where idU=?";
    public void MdlU_update(String nomU, String prenomU, String adresseU, String telephoneU, String courrielU, String notesU, int idU) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, nomU);
            stmt.setString(2, prenomU);
            stmt.setString(3, adresseU);
            stmt.setString(4, telephoneU);
            stmt.setString(5, courrielU);
            stmt.setString(6, notesU);
            stmt.setInt(7, idU);
            stmt.executeUpdate();
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlU_update(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlU_Fermer(stmt);
            MdlU_Fermer(conn);
        }
    }
   
    private static void MdlU_Fermer(Connection conn) {
        if (conn != null) {
            try { conn.close(); } 
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlU_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }

    private static void MdlU_Fermer(Statement stmt) {
        if (stmt != null) {
            try { stmt.close(); }
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlU_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }
}
