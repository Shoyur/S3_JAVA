import java.awt.event.*;
import javax.swing.*;

public class ExempleButonsEtEvents1 extends JFrame{
    private JButton b1 = new JButton("Buton 1"),
                    b2 = new JButton("Buton 2");

    JFrame f = new JFrame("Exemple butons et evénnements");

    public ExempleButonsEtEvents1() {
        f.setSize(300, 300);
        b1.setBounds(20, 100, 80, 30);
        f.add(b1);
        b2.setBounds(20, 160, 80, 30);
        f.add(b2);
        f.setLayout(null);
        f.setVisible(true);
        // ExempleButonsEtEvents écoute les composants
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                message("Buton 1 choisit");
            }
        });
        b2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                message("Buton 2 choisit");
            }
        });
    }

    public void message(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public static void main(String[] args) {
       new ExempleButonsEtEvents1();
    }
}