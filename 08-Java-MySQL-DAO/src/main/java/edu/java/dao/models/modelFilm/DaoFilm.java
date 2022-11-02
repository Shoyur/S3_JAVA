package edu.java.dao.models.modelFilm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DaoFilm implements IFilmDao {
    private static Connection conn = null;
    private static DaoFilm instanceDao = null;

    // MySQL
    //private static final String PILOTE = "com.mysql.jdbc.Driver";
    private static final String URL_BD = "jdbc:mysql://localhost/mabdfilms";
    private static final String USAGER = "root";
    private static final String PASS = "";

    private static final String SUPPRIMER = "DELETE FROM films WHERE idf=?";
    private static final String GET_ALL = "SELECT * FROM films ORDER BY idf";
    private static final String GET_BY_ID = "SELECT * FROM films WHERE idf=?";
    private static final String GET_BY_TITRE = "SELECT * FROM films WHERE titre=?";
    private static final String ENREGISTRER = "INSERT INTO films VALUES(0,?, ?, ?, ?)";
    private static final String MODIFIER = "UPDATE films SET titre=?, duree=?, res=?, pochette=? WHERE idf=?";

    // Singleton de connexion à la BD
    // getConnexion() est devenu une zonne critique. 
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion

    private DaoFilm(){};
    
    public static synchronized DaoFilm getFilmDao () {
        try {
            // Class.forName(PILOTE);
            if (instanceDao == null) {
                instanceDao = new DaoFilm();
                conn = DriverManager.getConnection(URL_BD, USAGER, PASS);
            }
            return instanceDao;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    // Create
    public String MdlF_Enregistrer(Film film) {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(ENREGISTRER, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, film.getTitre());
            stmt.setInt(2, film.getDuree());
            stmt.setString(3, film.getRes());
            stmt.setString(4, film.getPochette());
           
            stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            if (rs.next()) {
                film.setIdf(rs.getInt(1));
            }
            return "Film bien enregistré";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
    }

    // Read
    public List<Film> MdlF_GetAll() {
        PreparedStatement stmt = null;
        List<Film> listeFilms = new ArrayList<Film>();

        try {
            stmt = conn.prepareStatement(GET_ALL);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Film film = new Film();
                film.setIdf(rs.getInt("idf"));
                film.setTitre(rs.getString("titre"));
                film.setDuree(rs.getInt("duree"));
                film.setRes(rs.getString("res"));
                film.setPochette(rs.getString("pochette"));

                listeFilms.add(film);
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }

        return listeFilms;
    }

    public Film MdlF_GetById(int idf) {
        PreparedStatement stmt = null;

        try {

            stmt = conn.prepareStatement(GET_BY_ID);
            stmt.setInt(1, idf);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Film film = new Film();
                film.setIdf(rs.getInt("idf"));
                film.setTitre(rs.getString("titre"));
                film.setDuree(rs.getInt("duree"));
                film.setRes(rs.getString("res"));
                film.setPochette(rs.getString("pochette"));

                return film;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
    }

    public Film MdlF_GetByTitre(String titre) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(GET_BY_TITRE);
            stmt.setString(1, titre);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Film film = new Film();
                film.setIdf(rs.getInt("idf"));
                film.setTitre(rs.getString("titre"));
                film.setDuree(rs.getInt("duree"));
                film.setRes(rs.getString("res"));
                film.setPochette(rs.getString("pochette"));

                return film;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
    }

    // Update, faudrat avant appeler MdlF_GetById(idf) pour obtenir
    // les données du film à modifier via une interface et après envoyer 
    // ce film à MdlF_Modifier(film) pour faire la mise à  jour.
    public int MdlF_Modifier(Film film) {
        PreparedStatement stmt = null;
       ;
        try {
            stmt = conn.prepareStatement(MODIFIER);
            stmt.setString(1, film.getTitre());
            stmt.setInt(2, film.getDuree());
            stmt.setString(3, film.getRes());
            stmt.setString(4, film.getPochette());

            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
    }

    // Delete
    public int MdlF_Supprimer(int idf) {
        PreparedStatement stmt = null;

        try {
            stmt = conn.prepareStatement(SUPPRIMER);
            stmt.setInt(1, idf);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            MdlF_Fermer(stmt);
            MdlF_Fermer(conn);
        }
    }
   
    private static void MdlF_Fermer(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

    private static void MdlF_Fermer(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }
}
