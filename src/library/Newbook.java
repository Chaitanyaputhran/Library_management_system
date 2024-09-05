package library;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;

public class Newbook extends JFrame implements ActionListener{
       JTextField tfTitle, tfAuthor, tfPublisher, tfISBN, tfYear, tfCopies;
    JLabel lblBookID;
    JButton submitBtn;
    static int bookIDCounter = 1;
    Newbook(){
        setTitle("Add New Book");
        setVisible(true);
        setBounds(450, 200, 850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null); 
        
        // Book ID label (Auto-generated)
        JLabel bookIDLabel = new JLabel("Book ID:");
        bookIDLabel.setBounds(50, 50, 200, 25);
        bookIDLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(bookIDLabel);
        
        // Display auto-generated Book ID
        lblBookID = new JLabel(String.valueOf(bookIDCounter));
        lblBookID.setBounds(210, 50, 400, 25);
        lblBookID.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblBookID);
        
        // Title label
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(50, 100, 200, 25);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(titleLabel);
        
        // Text field for Title
        tfTitle = new JTextField();
        tfTitle.setBounds(210, 100, 400, 25);
        tfTitle.setBorder(BorderFactory.createEmptyBorder());
        add(tfTitle);
        
        // Author label
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(50, 150, 200, 25);
        authorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(authorLabel);
        
        // Text field for Author
        tfAuthor = new JTextField();
        tfAuthor.setBounds(210, 150, 400, 25);
        tfAuthor.setBorder(BorderFactory.createEmptyBorder());
        add(tfAuthor);
        
        // Publisher label
        JLabel publisherLabel = new JLabel("Publisher:");
        publisherLabel.setBounds(50, 200, 200, 25);
        publisherLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(publisherLabel);
        
        // Text field for Publisher
        tfPublisher = new JTextField();
        tfPublisher.setBounds(210, 200, 400, 25);
        tfPublisher.setBorder(BorderFactory.createEmptyBorder());
        add(tfPublisher);
        
        // ISBN label
        JLabel isbnLabel = new JLabel("ISBN:");
        isbnLabel.setBounds(50, 250, 200, 25);
        isbnLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(isbnLabel);
        
        // Text field for ISBN
        tfISBN = new JTextField();
        tfISBN.setBounds(210, 250, 400, 25);
        tfISBN.setBorder(BorderFactory.createEmptyBorder());
        add(tfISBN);
        
        // Year of Publication label
        JLabel yearLabel = new JLabel("Year of Publication:");
        yearLabel.setBounds(50, 300, 200, 25);
        yearLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(yearLabel);
        
        // Text field for Year of Publication
        tfYear = new JTextField();
        tfYear.setBounds(210, 300, 400, 25);
        tfYear.setBorder(BorderFactory.createEmptyBorder());
        add(tfYear);
        
        // Copies Available label
        JLabel copiesLabel = new JLabel("Copies Available:");
        copiesLabel.setBounds(50, 350, 200, 25);
        copiesLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(copiesLabel);
        
        // Text field for Copies Available
        tfCopies = new JTextField();
        tfCopies.setBounds(210, 350, 400, 25);
        tfCopies.setBorder(BorderFactory.createEmptyBorder());
        add(tfCopies);
        
        // Submit button
        submitBtn = new JButton("Add Book");
        submitBtn.setBounds(310, 400, 120, 30);
        add(submitBtn);
        submitBtn.addActionListener(this);
    }
    public void 
    public static void main(String[] args){
        new Newbook();
    }
}
