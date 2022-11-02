package edu.java.dao.controleurs.controleurFilm;
import edu.java.dao.models.modelFilm.Film;
import edu.java.dao.models.modelFilm.DaoFilm;
// import edu.java.dao.models.modelFilm.DaoFilm;
public class ControleurFilm implements IActionsFilm {

    private static ControleurFilm CtrF_Instance = null;
    private static DaoFilm Dao_Instance = null;
    // Singleton du contrôleur
    // getControleurFilm() est devenu une zonne critique.
    // Pour ne pas avoir deux processus légers (threads) qui
    // appellent au même temps getConnexion
    private ControleurFilm(){}

    public static synchronized ControleurFilm getControleurFilm() {
        try {
            if (CtrF_Instance == null) {
                CtrF_Instance = new ControleurFilm();
                Dao_Instance = DaoFilm.getFilmDao();
            }
            return CtrF_Instance;
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    public String CtrF_Enregistrer(Film film) {
        String message = null;
        message = Dao_Instance.MdlF_Enregistrer(film);
        return message;
    }
}
