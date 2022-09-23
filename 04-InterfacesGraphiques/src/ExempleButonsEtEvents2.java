import java.awt.event.*; 
import javax.swing.*;

public class ExempleButonsEtEvents2 extends JFrame implements ActionListener {
    private JButton  b1 = new JButton("Buton 1"),
                     b2 = new JButton("Buton 2");
    
     JFrame f=new JFrame("Exemple butons et evénnements");  
     

    public ExempleButonsEtEvents2() {
        f.setSize(400, 400);
        b1.setBounds(20, 100, 80, 30);
        f.add(b1); 
        b2.setBounds(20, 160, 80, 30);
        f.add(b2);
        f.setLayout(null);
        f.setVisible(true);
        // ExempleButonsEtEvents écoute les composants
        b1.addActionListener(this);
        b2.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) 
    { 
        if (e.getSource() == b1){
            message("Buton 1 choisit");
        }else if(e.getSource() == b2){
            message("Buton 2 choisit");
        } else {
            message("Un buton n'a pas été choisit!");
        }
    }

    public void message(String msg) {
        JOptionPane.showMessageDialog(this, msg);
    }

    public static void main(String[] args) {
        new ExempleButonsEtEvents2();
    }
}


