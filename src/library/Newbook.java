package library;

import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;

public class Newbook extends JFrame implements ActionListener {

    JTextField tfTitle, tfAuthor, tfPublisher, tfISBN, tfYear, tfCopies;
    JLabel lblBookID;
    JButton submitBtn;
  

    Newbook() {
        setTitle("Add New Book");
        setVisible(true);
        setBounds(450, 200, 850, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

  

       

        // Title label
        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(50, 100, 200, 25);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(titleLabel);

        // Text field for Title
        tfTitle = new JTextField();
        tfTitle.setBounds(210, 100, 400, 25);
        add(tfTitle);

        // Author label
        JLabel authorLabel = new JLabel("Author:");
        authorLabel.setBounds(50, 150, 200, 25);
        authorLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(authorLabel);

        // Text field for Author
        tfAuthor = new JTextField();
        tfAuthor.setBounds(210, 150, 400, 25);
        add(tfAuthor);

        // Publisher label
        JLabel publisherLabel = new JLabel("Publisher:");
        publisherLabel.setBounds(50, 200, 200, 25);
        publisherLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(publisherLabel);

        // Text field for Publisher
        tfPublisher = new JTextField();
        tfPublisher.setBounds(210, 200, 400, 25);
        add(tfPublisher);

        // ISBN label
        JLabel isbnLabel = new JLabel("ISBN:");
        isbnLabel.setBounds(50, 250, 200, 25);
        isbnLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(isbnLabel);

        // Text field for ISBN
        tfISBN = new JTextField();
        tfISBN.setBounds(210, 250, 400, 25);
        add(tfISBN);

        // Year of Publication label
        JLabel yearLabel = new JLabel("Year of Publication:");
        yearLabel.setBounds(50, 300, 200, 25);
        yearLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(yearLabel);

        // Text field for Year of Publication
        tfYear = new JTextField();
        tfYear.setBounds(210, 300, 400, 25);
        add(tfYear);

        // Copies Available label
        JLabel copiesLabel = new JLabel("Copies Available:");
        copiesLabel.setBounds(50, 350, 200, 25);
        copiesLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(copiesLabel);

        // Text field for Copies Available
        tfCopies = new JTextField();
        tfCopies.setBounds(210, 350, 400, 25);
        add(tfCopies);

        // Submit button
        submitBtn = new JButton("Add Book");
        submitBtn.setBounds(310, 400, 120, 30);
        add(submitBtn);
        submitBtn.addActionListener(this);

        // Refresh/repaint the frame to ensure visibility
        repaint();
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == submitBtn) {
            String title = tfTitle.getText();
            String author = tfAuthor.getText();
            String publisher = tfPublisher.getText();
            String isbn = tfISBN.getText();
            String year = tfYear.getText();
            String copies = tfCopies.getText();

            if (title.isEmpty() || author.isEmpty() || year.isEmpty() || copies.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please fill in all the required fields.");
                return;
            }

            String query = "INSERT INTO Books (title, author, publisher, isbn, year_of_publication, copies_available) VALUES ('"
                    + title + "', '"
                    + author + "', '"
                    + publisher + "', '"
                    + isbn + "', '"
                    + year + "', '"
                    + copies + "')";

            try {
                Conn c = new Conn();
                c.s.executeUpdate(query);
                JOptionPane.showMessageDialog(null, "Book added successfully.");
                setVisible(false);
                new Newbook();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Newbook();
    }
}
