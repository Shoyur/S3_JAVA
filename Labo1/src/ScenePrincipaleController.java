
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class ScenePrincipaleController {


    // CE QUE JE DOIS FAIRE : https://www.youtube.com/watch?v=4RNhPZJ84P0 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    // numLivre + titre + 2 + numAuteur + annee + nbPages + categLivre + 2
    @FXML private TableView<String> listeTable;
    @FXML private TableColumn<User, String> numLivre;
    @FXML private TableColumn<User, String> titre;
    @FXML private TableColumn<User, String> numAuteur;
    @FXML private TableColumn<User, String> annee;
    @FXML private TableColumn<User, String> nbPages;
    @FXML private TableColumn<User, String> categLivre;

    @FXML
    void boutonListerTous(ActionEvent event) {
        System.out.println("Tous les livres !!!!!!!!!!!!!!!!!!");
    }

}