package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import connection.DB1;

public class VenteDao {
    private static Connection conn = null;
    private static VenteDao instanceDao = null;

    private static final String URL_BD = DB1.URL_BD;
    private static final String USAGER = DB1.USAGER;
    private static final String PASS = DB1.PASS;

    private static final String CREATE = "INSERT INTO vente VALUES(?, ?, ?, ?)";
    private static final String READ_ALL = "SELECT * FROM vente";
    private static final String READ_ALL_PAR_USAGER = "SELECT * FROM vente WHERE idU=?";

    public VenteDao() {  }
    
    public static synchronized VenteDao getVenteDao() {
        try {
            // if (instanceDao == null) {
                instanceDao = new VenteDao();
                conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            // }
            return instanceDao;
        } 
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getVenteDao(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    // CREATE
    public void MdlV_create(Vente vente) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(CREATE);
            stmt.setInt(1, vente.getIdV());
            stmt.setInt(2, vente.getIdEx());
            stmt.setInt(3, vente.getIdU());
            stmt.setTimestamp(4, vente.getDateV());
            stmt.executeUpdate();
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlV_create(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlV_Fermer(stmt);
            MdlV_Fermer(conn);
        }
    }

    // READ ALL
    public ObservableList<Vente> MdlV_readAll() {
        PreparedStatement stmt = null;
        ObservableList<Vente> listeVentes = FXCollections.observableArrayList();
        try {
            stmt = conn.prepareStatement(READ_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Vente Vente = new Vente();
                Vente.setIdV(rs.getInt("idV"));
                Vente.setIdEx(rs.getInt("idEx"));
                Vente.setIdU(rs.getInt("idU"));
                Vente.setDateV(rs.getTimestamp("dateV"));
                listeVentes.add(Vente);
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlV_readAll()), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlV_Fermer(stmt);
            MdlV_Fermer(conn);
        }

        return listeVentes;
    }

    // READ ALL PAR USAGER
    public ObservableList<Vente> MdlV_readAllParUsager(int idU) {
        PreparedStatement stmt = null;
        ObservableList<Vente> listeVentesParUsager = FXCollections.observableArrayList();
        try {
			stmt = conn.prepareStatement(READ_ALL_PAR_USAGER);
            stmt.setInt(1, idU);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
            	Vente vente = new Vente();
                vente.setIdV(rs.getInt("idV"));
                vente.setIdEx(rs.getInt("idEx"));
                vente.setIdU(rs.getInt("idU"));
                vente.setDateV(rs.getTimestamp("dateV"));
                listeVentesParUsager.add(vente);
            }
		} catch (SQLException e) {
            System.out.println("================================================================================================ ERREUR, MdlEm_readAllParUsager(), e= " + e);
            throw new RuntimeException(e); 
		}
        finally {
            MdlV_Fermer(stmt);
            MdlV_Fermer(conn);
        }
        return listeVentesParUsager;
    }
   
    private static void MdlV_Fermer(Connection conn) {
        if (conn != null) {
            try { conn.close(); } 
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlV_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }

    private static void MdlV_Fermer(Statement stmt) {
        if (stmt != null) {
            try { stmt.close(); }
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlV_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }
}
