package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Splash extends JFrame implements ActionListener {
 JButton userLoginBtn,librarianLoginBtn;

    Splash() {
        setSize(1200, 600);
        setLocation(200, 100);
        setLayout(null);

        // Add a welcome label
        JLabel welcomeLabel = new JLabel("Welcome to the Library Management System", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 30));
        welcomeLabel.setBounds(200, 50, 800, 50);
        add(welcomeLabel);

        // Add buttons for login options
        userLoginBtn = new JButton("Login as User");
        userLoginBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        userLoginBtn.setBounds(400, 300, 200, 50);
        add(userLoginBtn);
        
        librarianLoginBtn = new JButton("Login as Librarian");
        librarianLoginBtn.setFont(new Font("Arial", Font.PLAIN, 20));
        librarianLoginBtn.setBounds(650, 300, 200, 50);
        add(librarianLoginBtn);

        // Action listeners for the buttons
        userLoginBtn.addActionListener(this);

        librarianLoginBtn.addActionListener(this);

        setVisible(true);
    
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==userLoginBtn){
            new Userlogin();
            
        setVisible(false);
            
        }
            if(ae.getSource()==librarianLoginBtn){
            new Login();
           setVisible(false);
            
        }
        
    }
    

 

    public static void main(String[] args) {
        new Splash();
    }
}
