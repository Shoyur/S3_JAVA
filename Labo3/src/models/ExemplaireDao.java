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

    private static final String READ_ALL_NON_VENDUS = "SELECT * FROM exemplaire WHERE estVendu = FALSE";
    // private static final String READ_ALL_POSS = "SELECT * FROM exemplaire";

    public ExemplaireDao() {  }
    
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
    public ObservableList<Exemplaire> MdlEx_readAll(int option) {
        PreparedStatement stmt = null;
        ObservableList<Exemplaire> listeExemplaires = FXCollections.observableArrayList();
        try {
            if (option == 0) {
                stmt = conn.prepareStatement(READ_ALL_NON_VENDUS);
            }
            else if (option == 1) {
                stmt = conn.prepareStatement(READ_ALL_NON_VENDUS);
            }
            
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Exemplaire exemplaire = new Exemplaire();
                // Exemplaire(int id, String album, String artiste, int annee, String genre, boolean possession) 
                exemplaire.setIdEx(rs.getInt("idEx"));
                exemplaire.setTitreEx(rs.getString("titreEx"));
                exemplaire.setArtisteEx(rs.getString("artisteEx"));
                exemplaire.setCategEx(rs.getString("categEx"));
                exemplaire.setAnneeEx(rs.getInt("anneeEx"));
                exemplaire.setPrixEx(rs.getDouble("prixEx"));
                exemplaire.setPistesEx(rs.getString("pistesEx"));
                exemplaire.setNbEmpruntsEx(rs.getInt("nbEmpruntsEx"));
                exemplaire.setEstEmprunte(rs.getBoolean("estEmprunte"));
                exemplaire.setEstVendu(rs.getBoolean("estVendu"));
                listeExemplaires.add(exemplaire);
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlEx_readAll()), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlEx_Fermer(stmt);
            MdlEx_Fermer(conn);
        }

        return listeExemplaires;
    }
   
    private static void MdlEx_Fermer(Connection conn) {
        if (conn != null) {
            try { conn.close(); } 
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlEx_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }

    private static void MdlEx_Fermer(Statement stmt) {
        if (stmt != null) {
            try { stmt.close(); }
            catch (SQLException e) { 
                System.out.println("================================================================================================ ERREUR, MdlEx_Fermer(), e= " + e);
                throw new RuntimeException(e); 
            }
        }
    }
}
