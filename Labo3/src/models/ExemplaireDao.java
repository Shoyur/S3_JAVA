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

    private static final String CREATE = "INSERT INTO exemplaire VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String READ_ALL_NON_VENDUS = "SELECT * FROM exemplaire WHERE estVendu = FALSE";
    private static final String READ = "SELECT * FROM exemplaire WHERE idEx=?";
    private static final String UPDATE = "UPDATE exemplaire SET titreEx=?, artisteEx=?, categEx=?, anneeEx=?, prixEx=?, pistesEx=?, cheminImg=? where idEx=?";

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

    // CREATE
    // Exemplaire(int idEx, String titreEx, String artisteEx, String categEx, int anneeEx, double prixEx,
    //         String pistesEx, int nbEmpruntsEx, boolean estEmprunte, boolean estVendu, String cheminImg)
    public void MdlEx_create(Exemplaire exemplaire) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(CREATE);
            stmt.setInt(1, 0);
            stmt.setString(2, exemplaire.getTitreEx());
            stmt.setString(3, exemplaire.getArtisteEx());
            stmt.setString(4, exemplaire.getCategEx());
            stmt.setInt(5, exemplaire.getAnneeEx());
            stmt.setDouble(6, exemplaire.getPrixEx());
            stmt.setString(7, exemplaire.getPistesEx());
            stmt.setInt(8, exemplaire.getNbEmpruntsEx());
            stmt.setBoolean(9, exemplaire.isEstEmprunte());
            stmt.setBoolean(10, exemplaire.isEstVendu());
            stmt.setString(11, exemplaire.getCheminImgEx());
            stmt.executeUpdate();
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlEx_create(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlEx_Fermer(stmt);
            MdlEx_Fermer(conn);
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
                exemplaire.setCheminImgEx(rs.getString("cheminImg"));
                listeExemplaires.add(exemplaire);
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlEx_readAll(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlEx_Fermer(stmt);
            MdlEx_Fermer(conn);
        }
        return listeExemplaires;
    }
    
    // READ
    public Exemplaire MdlEx_read(int idEx) {
        Exemplaire exemplaire = new Exemplaire();
        PreparedStatement stmt = null;
        try {
			stmt = conn.prepareStatement(READ);
            stmt.setInt(1, idEx);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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
                exemplaire.setCheminImgEx(rs.getString("cheminImg"));
            }
		} catch (SQLException e) {
            System.out.println("================================================================================================ ERREUR, MdlEx_read(), e= " + e);
            throw new RuntimeException(e); 
		}
        finally {
            MdlEx_Fermer(stmt);
            MdlEx_Fermer(conn);
        }
        return exemplaire;
    }

    // UPDATE
    // UPDATE exemplaire SET titreEx=?, artisteEx=?, categEx=?, anneeEx=?, prixEx=?, pistesEx=?, cheminImgEx? where idEx=?";
    public void MdlEx_update(String titreEx, String artisteEx, String categEx, int anneeEx, double prixEx, String pistesEx, int idEx, String cheminImgEx) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UPDATE);
            stmt.setString(1, titreEx);
            stmt.setString(2, artisteEx);
            stmt.setString(3, categEx);
            stmt.setInt(4, anneeEx);
            stmt.setDouble(5, prixEx);
            stmt.setString(6, pistesEx);
            stmt.setString(7, cheminImgEx);
            stmt.setInt(8, idEx);
            stmt.executeUpdate();
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlEx_update(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlEx_Fermer(stmt);
            MdlEx_Fermer(conn);
        }
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
