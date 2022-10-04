
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
//import javax.swing.SwingUtilities;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ListeDeroulante extends JFrame {

    private JList<String> listeMois;

    public ListeDeroulante() {
        
        String [] mois = {"Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"};
        listeMois  = new JList<String>(mois);
        listeMois.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    final List<String> selectedValuesList = listeMois.getSelectedValuesList();
                    System.out.println(selectedValuesList);
                }
            }
        });
//Méthodes de sélection
//SINGLE_SELECTION:
//Un seul choix possible.
//SINGLE_INTERVAL_SELECTION:
//Plusieurs éléments prevent être sélectionnés mais doivent être contigues.
//On peut choisir les éléments en pesent en premier sur la touche shift et avec la souris
//choisir les éléments. 
//MULTIPLE_INTERVAL_SELECTION:
//Ce mode est celui par défaut. Vous pouvez choisir plusieurs éléments sans
//obligatoirement qu'ils soient contigus.

        this.add(new JScrollPane(listeMois));
        listeMois.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        this.setTitle("Exemple de liste de choix");
        this.setSize(200, 200);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        ListeDeroulante lst = new ListeDeroulante();
        lst.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ListeDeroulante();
            }
        });
    }
}
