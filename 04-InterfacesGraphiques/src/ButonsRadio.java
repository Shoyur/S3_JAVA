import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.SwingUtilities;

public class ButonsRadio extends JFrame implements ActionListener{

    
    String optionChoisie = "";

    private JRadioButton js = new JRadioButton("JavaScript");
    private JRadioButton html = new JRadioButton("HTML");
    private JRadioButton css = new JRadioButton("CSS");
    private JRadioButton php = new JRadioButton("PHP");
    private JRadioButton node = new JRadioButton("Node");
    private JLabel imageTitre = new JLabel();
    private ImageIcon web = new ImageIcon(getClass().getResource(
            "web.png"));
    public ButonsRadio() {
        super("Exemple de butons radio");

        ButtonGroup groupeWeb = new ButtonGroup();
        groupeWeb.add(js);
        groupeWeb.add(html);
        groupeWeb.add(css);
        groupeWeb.add(php);
        groupeWeb.add(node);
        js.setSelected(true);
        imageTitre.setIcon(web);

        setLayout(new GridBagLayout());
        GridBagConstraints grille = new GridBagConstraints();
   
        
        grille.anchor = GridBagConstraints.CENTER;
        grille.insets = new Insets(10, 10, 10, 10);
        
        add(imageTitre, grille);
        grille.gridy = 1;
        
       

        
        add(js, grille);
        grille.gridx = 1;
        add(html, grille);
        grille.gridx = 2;
        add(css, grille);
        grille.gridx = 3;
        add(php, grille);
        grille.gridx = 4;
        add(node, grille);
        grille.gridx = 5;

        pack();

        js.addActionListener(this);
        html.addActionListener(this);
        css.addActionListener(this);
        php.addActionListener(this);
        node.addActionListener(this);
    }
        @Override
            public void actionPerformed(ActionEvent e) { {
                optionChoisie = "";
                if (js.isSelected()) {
                    optionChoisie = "JavaScript";
                } else if (html.isSelected()) {
                    optionChoisie = "HTML";
                } else if (css.isSelected()) {
                    optionChoisie = "CSS";
                }else if (php.isSelected()) {
                    optionChoisie = "PHP";
                }else if (node.isSelected()) {
                    optionChoisie = "Node";
                }
                System.out.println("Vous avez choisit : " + optionChoisie);
            }
            
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
    }

    public static void main(String[] args) {
       
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                new ButonsRadio().setVisible(true);
            }
        });
    }
}

