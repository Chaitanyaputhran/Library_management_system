package library;

import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class DeleteBook extends JPanel implements ActionListener {

    JTextField tfSearch, tfTitle, tfAuthor, tfPublisher, tfISBN, tfYear, tfCopies;
    JButton searchBtn, deleteBtn, updateBtn;
    String bookID;

    DeleteBook() {
       
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

        // Title label and text field
        JLabel lblTitle = new JLabel("Title:");
        lblTitle.setBounds(50, 150, 100, 25);
        lblTitle.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblTitle);
        tfTitle = new JTextField();
        tfTitle.setBounds(250, 150, 400, 25);
        tfTitle.setEditable(false);
        add(tfTitle);

        // Author label and text field
        JLabel lblAuthor = new JLabel("Author:");
        lblAuthor.setBounds(50, 200, 100, 25);
        lblAuthor.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblAuthor);
        tfAuthor = new JTextField();
        tfAuthor.setBounds(250, 200, 400, 25);
        tfAuthor.setEditable(false);
        add(tfAuthor);

        // Publisher label and text field
        JLabel lblPublisher = new JLabel("Publisher:");
        lblPublisher.setBounds(50, 250, 100, 25);
        lblPublisher.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblPublisher);
        tfPublisher = new JTextField();
        tfPublisher.setBounds(250, 250, 400, 25);
        tfPublisher.setEditable(false);
        add(tfPublisher);

        // ISBN label and text field
        JLabel lblISBN = new JLabel("ISBN:");
        lblISBN.setBounds(50, 300, 100, 25);
        lblISBN.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblISBN);
        tfISBN = new JTextField();
        tfISBN.setBounds(250, 300, 400, 25);
        tfISBN.setEditable(false);
        add(tfISBN);

        // Year label and text field
        JLabel lblYear = new JLabel("Year:");
        lblYear.setBounds(50, 350, 100, 25);
        lblYear.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblYear);
        tfYear = new JTextField();
        tfYear.setBounds(250, 350, 400, 25);
        tfYear.setEditable(false);
        add(tfYear);

        // Copies label and text field
        JLabel lblCopies = new JLabel("Copies:");
        lblCopies.setBounds(50, 400, 100, 25);
        lblCopies.setFont(new Font("Tahoma", Font.BOLD, 14));
        add(lblCopies);
        tfCopies = new JTextField();
        tfCopies.setBounds(250, 400, 400, 25);
        tfCopies.setEditable(false);
        add(tfCopies);

        // Delete button (Initially disabled)
        deleteBtn = new JButton("Delete Book");
        deleteBtn.setBounds(250, 450, 150, 30);
        deleteBtn.setEnabled(false);
        add(deleteBtn);
        deleteBtn.addActionListener(this);

        // Update button (Initially disabled)
        updateBtn = new JButton("Update Book");
        updateBtn.setBounds(450, 450, 150, 30);
        updateBtn.setEnabled(false);
        add(updateBtn);
        updateBtn.addActionListener(this);

        // Revalidate and repaint the JFrame to ensure proper rendering
        revalidate();
        repaint();
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
                    tfTitle.setText(rs.getString("title"));
                    tfAuthor.setText(rs.getString("author"));
                    tfPublisher.setText(rs.getString("publisher"));
                    tfISBN.setText(rs.getString("isbn"));
                    tfYear.setText(rs.getString("year_of_publication"));
                    tfCopies.setText(rs.getString("copies_available"));

                    // Enable delete and update buttons
                    deleteBtn.setEnabled(true);
                    updateBtn.setEnabled(true);

                    // Allow text fields to be editable for update
                    tfTitle.setEditable(true);
                    tfAuthor.setEditable(true);
                    tfPublisher.setEditable(true);
                    tfISBN.setEditable(true);
                    tfYear.setEditable(true);
                    tfCopies.setEditable(true);
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
                        updateBtn.setEnabled(false);
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to delete the book.");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        if (ae.getSource() == updateBtn) {
            if (bookID != null) {
                String title = tfTitle.getText();
                String author = tfAuthor.getText();
                String publisher = tfPublisher.getText();
                String isbn = tfISBN.getText();
                String year = tfYear.getText();
                String copies = tfCopies.getText();

                // Query to update the book details
                String query = "UPDATE Books SET title = '" + title + "', author = '" + author + "', publisher = '" + publisher + "', isbn = '" + isbn + "', year_of_publication = '" + year + "', copies_available = '" + copies + "' WHERE book_id = '" + bookID + "'";

                try {
                    Conn c = new Conn();
                    int result = c.s.executeUpdate(query);
                    if (result > 0) {
                        JOptionPane.showMessageDialog(null, "Book details updated successfully.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Failed to update book details.");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    // Method to clear the displayed book details
    private void clearFields() {
        tfTitle.setText("");
        tfAuthor.setText("");
        tfPublisher.setText("");
        tfISBN.setText("");
        tfYear.setText("");
        tfCopies.setText("");
        tfSearch.setText("");
    }

   
}
