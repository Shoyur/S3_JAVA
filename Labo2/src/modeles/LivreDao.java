package modeles;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class LivreDao implements ILivreDao {
    private static Connection conn = null;
    private static LivreDao instanceDao = null;

    private static final String URL_BD = "jdbc:mysql://localhost/bdlivres";
    private static final String USAGER = "root";
    private static final String PASS = "";


    private static final String CREATE = "INSERT INTO livres VALUES(?, ?, ?, ?, ?, ?)";

    private static final String READ_ALL = "SELECT * FROM livres";
    private static final String READ_BY_CATEG = "SELECT * FROM livres WHERE categ=?";
    private static final String READ_BY_NO = "SELECT * FROM livres WHERE no=? LIMIT 1";
    private static final String READ_BY_AUTEUR = "SELECT * FROM livres WHERE auteur=?";
    private static final String READ_ALL_CATEGS = "SELECT DISTINCT categ FROM livres";
    private static final String READ_ALL_NOS = "SELECT DISTINCT no FROM livres";
    private static final String READ_ALL_AUTEURS = "SELECT DISTINCT auteur FROM livres";

    private static final String UPDATE_TITRE = "UPDATE livres SET titre=? WHERE no=?";

    private static final String DELETE = "DELETE FROM livres WHERE no=? LIMIT 1";
    
    // Singleton de connexion à la BD
    // getConnexion() est devenu une zonne critique. 
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion

    public LivreDao() {  };
    
    public static synchronized LivreDao getLivreDao() {
        try {
            // if (instanceDao == null) {
                instanceDao = new LivreDao();
                conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            // }
            return instanceDao;
        } 
        catch (Exception e) { 
            System.out.println("================================================================================================ ERREUR, getLivreDao(), e= " + e);
            throw new RuntimeException(e);
        }
    }
    
    // CREATE
    public void MdlL_create(Livre livre) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(CREATE, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, livre.getNo());
            stmt.setString(2, livre.getTitre());
            stmt.setInt(3, livre.getAuteur());
            stmt.setInt(4, livre.getAnnee());
            stmt.setInt(5, livre.getPages());
            stmt.setString(6, livre.getCateg());
            stmt.executeUpdate();
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlL_create(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
    }

    // READ
    // READ ALL
    public List<Livre> MdlL_readAll() {
        PreparedStatement stmt = null;
        List<Livre> listeLivres = new ArrayList<Livre>();
        try {
            stmt = conn.prepareStatement(READ_ALL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Livre livre = new Livre();
                livre.setNo(rs.getInt("no"));
                livre.setTitre(rs.getString("titre"));
                livre.setAuteur(rs.getInt("auteur"));
                livre.setAnnee(rs.getInt("annee"));
                livre.setPages(rs.getInt("pages"));
                livre.setCateg(rs.getString("categ"));
                listeLivres.add(livre);
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlL_readAll()), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }

        return listeLivres;
    }
    
    // READ BY CATEG
    public List<Livre> MdlL_readByCateg(String categ) {
        PreparedStatement stmt = null;
        
        List<Livre> listeLivres = new ArrayList<Livre>();
        try {
            stmt = conn.prepareStatement(READ_BY_CATEG);
            stmt.setString(1, categ);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Livre livre = new Livre();
                livre.setNo(rs.getInt("no"));
                livre.setTitre(rs.getString("titre"));
                livre.setAuteur(rs.getInt("auteur"));
                livre.setAnnee(rs.getInt("annee"));
                livre.setPages(rs.getInt("pages"));
                livre.setCateg(rs.getString("categ"));
                listeLivres.add(livre);
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlL_readByCateg(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
        return listeLivres;
    }

    // READ BY NO
    public Livre MdlL_readByNo(int no) {
        PreparedStatement stmt = null;
        Livre livre = new Livre();
        try {
            stmt = conn.prepareStatement(READ_BY_NO);
            stmt.setInt(1, no);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                livre.setNo(rs.getInt("no"));
                livre.setTitre(rs.getString("titre"));
                livre.setAuteur(rs.getInt("auteur"));
                livre.setAnnee(rs.getInt("annee"));
                livre.setPages(rs.getInt("pages"));
                livre.setCateg(rs.getString("categ"));
            }
            else { return null; }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlL_readByNo(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
        return livre;
    }

    // READ BY AUTEUR
    public List<Livre> MdlL_readByAuteur(int auteur) {
        PreparedStatement stmt = null;
        List<Livre> listeLivres = new ArrayList<Livre>();
        try {
            stmt = conn.prepareStatement(READ_BY_AUTEUR);
            stmt.setInt(1, auteur);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Livre livre = new Livre();
                livre.setNo(rs.getInt("no"));
                livre.setTitre(rs.getString("titre"));
                livre.setAuteur(rs.getInt("auteur"));
                livre.setAnnee(rs.getInt("annee"));
                livre.setPages(rs.getInt("pages"));
                livre.setCateg(rs.getString("categ"));
                listeLivres.add(livre);
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlL_readByAuteur(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
        return listeLivres;
    }

    // READ ALL CATEGS
    public List<String> MdlL_readAllCategs() {
        PreparedStatement stmt = null;
        List<String> listeCategs = new ArrayList<String>();
        try {
            stmt = conn.prepareStatement(READ_ALL_CATEGS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listeCategs.add(rs.getString("categ"));
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlL_readAllCategs(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
        return listeCategs;
    }

    // READ ALL NOS
    public List<Integer> MdlL_readAllNos() {
        PreparedStatement stmt = null;
        List<Integer> listeNos = new ArrayList<Integer>();
        try {
            stmt = conn.prepareStatement(READ_ALL_NOS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listeNos.add(rs.getInt("no"));
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlL_readAllNos(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
        return listeNos;
    }

    // READ ALL AUTEURS
    public List<Integer> MdlL_readAllAuteurs() {
        PreparedStatement stmt = null;
        List<Integer> listeAuteurs = new ArrayList<Integer>();
        try {
            stmt = conn.prepareStatement(READ_ALL_AUTEURS);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                listeAuteurs.add(rs.getInt("auteur"));
            }
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlL_readAllAuteurs(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
        return listeAuteurs;
    }

    // UPDATE BY TITRE
    public int MdlL_updateTitre(String titre, int no) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(UPDATE_TITRE);
            stmt.setString(1, titre);
            stmt.setInt(2, no);
            return stmt.executeUpdate();
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlL_updateTitre(), e= " + e);
            throw new RuntimeException(e); 
        }  
        finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
    }

    // DELETE
    public int MdlL_delete(int no) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(DELETE);
            stmt.setInt(1, no);
            return stmt.executeUpdate();
        } 
        catch (SQLException e) { 
            System.out.println("================================================================================================ ERREUR, MdlL_delete(), e= " + e);
            throw new RuntimeException(e); 
        } 
        finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
    }
   
    private static void MdlF_Fermer(Connection conn) {
        // if (conn != null) {
        //     try { conn.close(); } 
        //     catch (SQLException e) { 
        //         System.out.println("================================================================================================ ERREUR, MdlF_Fermer(), e= " + e);
        //         throw new RuntimeException(e); 
        //     }
        // }
    }

    private static void MdlF_Fermer(Statement stmt) {
        // if (stmt != null) {
        //     try { stmt.close(); }
        //     catch (SQLException e) { 
        //         System.out.println("================================================================================================ ERREUR, MdlF_Fermer(), e= " + e);
        //         throw new RuntimeException(e); 
        //     }
        // }
    }
}
