import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import modeles.Livre;
import controleurs.ControleurLivre;


public class App extends Application { 

    @Override
    public void start(Stage stagePrincipal) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("scenePrincipale.fxml"));
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        scene.getStylesheets().add("style.css");
        stagePrincipal.setScene(scene);
        stagePrincipal.getIcons().add(new Image("medias/frameIcon.png"));
        stagePrincipal.setTitle("[Gestionnaire de bibliothèque]");
        stagePrincipal.setResizable(false);
        stagePrincipal.initStyle(StageStyle.TRANSPARENT);
        stagePrincipal.show();
    }
    public static void main(String[] args) { launch(args); }

 
    public static void ajouterTout(int no, String titre, int auteur, int annee, int pages, String categ) {
        Livre livre = new Livre();
        livre.setNo(no);
        livre.setTitre(titre);
        livre.setAuteur(auteur);
        livre.setAnnee(annee);
        livre.setPages(pages);
        livre.setCateg(categ);
        (ControleurLivre.getControleurLivre()).CtrL_create(livre);
    }

    public static String lister(String flag, Object clic) {
        List<Livre> listeLivres;
        if (flag == "cat") { listeLivres = (ControleurLivre.getControleurLivre()).CtrL_readByCateg((String)clic); }
        else if (flag == "auteur") { listeLivres = (ControleurLivre.getControleurLivre()).CtrL_readByAuteur((Integer)clic); }
        else { listeLivres = (ControleurLivre.getControleurLivre()).CtrL_readAll(); }
        String lignes = "No.\tTitre\t\t\t\t\t\t\tAuteur\tAnnée\tPages\tCatégorie\n\n";
        for (Livre livre : listeLivres) {
            lignes += livre.getNo() + "\t" + 
            String.format("%1$-55.55s", livre.getTitre()) + livre.getAuteur() + "\t" + 
                livre.getAnnee() + "\t" + livre.getPages() + "\t" + 
                livre.getCateg() + "\n";
        }
        return lignes;
    }

    public static Livre unLivre(int no) {
        return (ControleurLivre.getControleurLivre()).CtrL_readByNo(no); 
    }

    public static void modifierTitre(int no, String titre) {
        (ControleurLivre.getControleurLivre()).CtrL_updateTitre(titre, no);
    }

    public static void supprimerLivre(int no) {
        (ControleurLivre.getControleurLivre()).CtrL_delete(no);
    }

    public static List<?>[] listesCombos() throws Exception {
        List<String> listeCateg = new ArrayList<String>();
        List<Integer> listeNumLivre = new ArrayList<Integer>();
        List<Integer> listeNumAuteur = new ArrayList<Integer>();
        listeCateg = (ControleurLivre.getControleurLivre()).CtrL_readAllCategs();
        listeNumLivre = (ControleurLivre.getControleurLivre()).CtrL_readAllNos();
        listeNumAuteur = (ControleurLivre.getControleurLivre()).CtrL_readAllAuteurs();
        listeCateg.sort(null);
        listeNumLivre.sort(null);
        listeNumAuteur.sort(null);
        List<?>[] listesCombos = { listeCateg, listeNumLivre, listeNumAuteur };
        return listesCombos;
    }

}
