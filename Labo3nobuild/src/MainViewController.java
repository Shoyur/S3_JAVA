import javafx.fxml.FXML;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainViewController {

    @FXML
    private Tab 
        tab01, tab02, tab03, tab04, tab05, 
        tab06, tab07, tab08, tab09, tab10;

    @FXML
    private TabPane tabPane0;

    public void change() {
        System.out.println("test.......................");
        tabPane0.getSelectionModel().selectNext();
    }

}
