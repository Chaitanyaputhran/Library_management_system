package library;
import javax.swing.*;
import java.awt.*;

public class Boorowedbooks extends JPanel {

    public Boorowedbooks() {
        setLayout(null);

        // Create form labels and fields
        JLabel l1 = new JLabel("Book ID:");
        l1.setBounds(50, 50, 100, 30);
        add(l1);

        JTextField t1 = new JTextField();
        t1.setBounds(150, 50, 200, 30);
        add(t1);

        JLabel l2 = new JLabel("Borrower's Name:");
        l2.setBounds(50, 100, 150, 30);
        add(l2);

        JTextField t2 = new JTextField();
        t2.setBounds(150, 100, 200, 30);
        add(t2);

        JLabel l3 = new JLabel("Borrow Date:");
        l3.setBounds(50, 150, 100, 30);
        add(l3);

        JTextField t3 = new JTextField();
        t3.setBounds(150, 150, 200, 30);
        add(t3);

        // Add more form fields as needed
        
        JButton submit = new JButton("Submit");
        submit.setBounds(150, 200, 100, 30);
        add(submit);
    }
}





