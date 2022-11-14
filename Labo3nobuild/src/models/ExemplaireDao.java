package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExemplaireDao {
    private static Connection conn = null;
    private static ExemplaireDao instanceDao = null;

    private static final String URL_BD = "jdbc:mysql://sql9.freesqldatabase.com/sql9558434";
    private static final String USAGER = "sql9558434";
    private static final String PASS = "bQV64kWUMF";

    private static final String READ_ALL = "SELECT * FROM exemplaires";

    public ExemplaireDao() {  };
    
    public static synchronized ExemplaireDao getExemplaireDao() {
        try {
            // if (instanceDao == null) {
                instanceDao = new ExemplaireDao();
                conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            // }
            return instanceDao;
        } 
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getexemplaireDao(), e= " + e);
            throw new RuntimeException(e);
        }
    }

    // READ ALL
    public ObservableList<Exemplaire> MdlL_readAll() {
        PreparedStatement stmt = null;
        ObservableList<Exemplaire> listeExemplaires = FXCollections.observableArrayList();
        try {
            stmt = conn.prepareStatement(READ_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Exemplaire exemplaire = new Exemplaire();
                // Exemplaire(int id, String album, String artiste, int annee, String genre, boolean possession) 
                exemplaire.setId(rs.getInt("id"));
                exemplaire.setAlbum(rs.getString("album"));
                exemplaire.setArtiste(rs.getString("artiste"));
                exemplaire.setAnnee(rs.getInt("annee"));
                exemplaire.setGenre(rs.getString("genre"));
                exemplaire.setPossession(rs.getBoolean("possession"));
                listeExemplaires.add(exemplaire);
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

        return listeExemplaires;
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
