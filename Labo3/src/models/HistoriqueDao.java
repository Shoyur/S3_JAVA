package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class HistoriqueDao {
    private static Connection conn = null;
    private static HistoriqueDao instanceDao = null;

    private static final String URL_BD = "jdbc:mysql://sql9.freesqldatabase.com/sql9558434";
    private static final String USAGER = "sql9558434";
    private static final String PASS = "bQV64kWUMF";

    private static final String CREATE = "INSERT INTO historique VALUES(?, ?)";
    private static final String READ_ALL = "SELECT * FROM historique ORDER BY quand DESC";

    public HistoriqueDao() {  }
    
    public static synchronized HistoriqueDao getHistoriqueDao() {
        try {
            // if (instanceDao == null) {
                instanceDao = new HistoriqueDao();
                conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            // }
            return instanceDao;
        } 
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getHistoriqueDao(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    // CREATE
    public void MdlH_create(Historique historique) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(CREATE);
            stmt.setTimestamp(1, historique.getQuand());
            stmt.setString(2, historique.getQuoi());
            stmt.executeUpdate();
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlH_create(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlH_Fermer(stmt);
            MdlH_Fermer(conn);
        }
    }

    // READ ALL
    public ObservableList<Historique> MdlH_readAll() {
        PreparedStatement stmt = null;
        ObservableList<Historique> listeHistorique = FXCollections.observableArrayList();
        try {
            stmt = conn.prepareStatement(READ_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Historique historique = new Historique();
                historique.setQuand(rs.getTimestamp("quand"));
                // historique.setQuand(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(rs.getTimestamp("quand")));
                historique.setQuoi(rs.getString("quoi"));
                listeHistorique.add(historique);
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlH_readAll()), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlH_Fermer(stmt);
            MdlH_Fermer(conn);
        }

        return listeHistorique;
    }
   
    private static void MdlH_Fermer(Connection conn) {
        if (conn != null) {
            try { conn.close(); } 
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlH_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }

    private static void MdlH_Fermer(Statement stmt) {
        if (stmt != null) {
            try { stmt.close(); }
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlH_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }
}
