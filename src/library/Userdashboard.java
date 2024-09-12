package library;

import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Userdashboard extends JFrame{
    Userdashboard(){
         setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        // Header Panel
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(0, 0, 102));
        p1.setBounds(0, 0, 1600, 65);
        add(p1);
    }
    
}
