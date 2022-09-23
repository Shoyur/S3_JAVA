import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class CasesACocher extends JFrame implements ActionListener{
    String optionChoisie="";
    private JCheckBox js = new JCheckBox("JavaScript");
    private JCheckBox html = new JCheckBox("HTML");
    private JCheckBox css = new JCheckBox("CSS");
    private JCheckBox php = new JCheckBox("PHP");
    private JCheckBox node = new JCheckBox("Node");

    public CasesACocher() {
		super("Exemple de cases à cocher");
		setLayout(new FlowLayout());
		
		// add the check boxes to this frame
		add(js);
		add(html);
		add(css);
		add(php);
		add(node);

        pack();
        
        js.addActionListener(this);
        html.addActionListener(this);
        css.addActionListener(this);
        php.addActionListener(this);
        node.addActionListener(this);
    }	
        @Override
    public void actionPerformed(ActionEvent e) {
        {
            optionChoisie = "";
            if (js.isSelected()) {
                optionChoisie += "JavaScript ";
            }
            if (html.isSelected()) {
                optionChoisie += "HTML ";
            }
            if (css.isSelected()) {
                optionChoisie += "CSS ";
            }
            if (php.isSelected()) {
                optionChoisie += "PHP ";
            } 
            if(node.isSelected()) {
                optionChoisie += "Node";
            }
            System.out.println("Vous avez choisit : " + optionChoisie);
        }
		
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	}
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new CasesACocher().setVisible(true);
            }
        });
    }
}
