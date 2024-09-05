package library;

import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class DeleteBook extends JFrame implements ActionListener {

    JTextField tfSearch;
    JLabel lblTitle, lblAuthor, lblPublisher, lblISBN, lblYear, lblCopies;
    JButton searchBtn, deleteBtn;
    String bookID;

    DeleteBook() {
        setTitle("Search and Delete Book");
        setVisible(true);
        setBounds(450, 200, 800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        // Search label
        JLabel searchLabel = new JLabel("Enter Book ID or Title:");
        searchLabel.setBounds(50, 50, 200, 25);
        searchLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(searchLabel);

        // Search text field
        tfSearch = new JTextField();
        tfSearch.setBounds(250, 50, 400, 25);
        add(tfSearch);

        // Search button
        searchBtn = new JButton("Search");
        searchBtn.setBounds(250, 100, 100, 30);
        add(searchBtn);
        searchBtn.addActionListener(this);

        // Labels for book details
        lblTitle = new JLabel("Title: ");
        lblTitle.setBounds(50, 150, 400, 25);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblTitle);

        lblAuthor = new JLabel("Author: ");
        lblAuthor.setBounds(50, 200, 400, 25);
        lblAuthor.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblAuthor);

        lblPublisher = new JLabel("Publisher: ");
        lblPublisher.setBounds(50, 250, 400, 25);
        lblPublisher.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblPublisher);

        lblISBN = new JLabel("ISBN: ");
        lblISBN.setBounds(50, 300, 400, 25);
        lblISBN.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblISBN);

        lblYear = new JLabel("Year: ");
        lblYear.setBounds(50, 350, 400, 25);
        lblYear.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblYear);

        lblCopies = new JLabel("Copies: ");
        lblCopies.setBounds(50, 400, 400, 25);
        lblCopies.setFont(new Font("Tahoma", Font.PLAIN, 14));
        add(lblCopies);

        // Delete button (Initially disabled)
        deleteBtn = new JButton("Delete Book");
        deleteBtn.setBounds(250, 450, 150, 30);
        deleteBtn.setEnabled(false);
        add(deleteBtn);
        deleteBtn.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == searchBtn) {
            String searchValue = tfSearch.getText();
            if (searchValue.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please enter Book ID or Title to search.");
                return;
            }

            // Query to search book by ID or Title
            String query = "SELECT * FROM Books WHERE book_id = '" + searchValue + "' OR title LIKE '%" + searchValue + "%'";

            try {
                Conn c = new Conn();
                ResultSet rs = c.s.executeQuery(query);

                if (rs.next()) {
                    // Book found, display details
                    bookID = rs.getString("book_id");
                    lblTitle.setText("Title: " + rs.getString("title"));
                    lblAuthor.setText("Author: " + rs.getString("author"));
                    lblPublisher.setText("Publisher: " + rs.getString("publisher"));
                    lblISBN.setText("ISBN: " + rs.getString("isbn"));
                    lblYear.setText("Year: " + rs.getString("year_of_publication"));
                    lblCopies.setText("Copies: " + rs.getString("copies_available"));

                    // Enable delete button
                    deleteBtn.setEnabled(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No book found with the given ID or Title.");
                    clearFields();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if (ae.getSource() == deleteBtn) {
            if (bookID != null) {
                String query = "DELETE FROM Books WHERE book_id = '" + bookID + "'";

                try {
                    Conn c = new Conn();
                    int result = c.s.executeUpdate(query);
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Book deleted successfully.");
                        clearFields();
                        deleteBtn.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete the book.");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method to clear the displayed book details
    private void clearFields() {
        lblTitle.setText("Title: ");
        lblAuthor.setText("Author: ");
        lblPublisher.setText("Publisher: ");
        lblISBN.setText("ISBN: ");
        lblYear.setText("Year: ");
        lblCopies.setText("Copies: ");
        tfSearch.setText("");
    }

    public static void main(String[] args) {
        new DeleteBook();
    }
}




