package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowedBooksPage extends JFrame implements ActionListener {
    String userId;
    
    BorrowedBooksPage(String userId) {
        this.userId = userId;

        setTitle("Borrowed Books");
        setSize(800, 400);
        setLocation(300, 200);
        setLayout(new BorderLayout());
        
        JLabel heading = new JLabel("Borrowed Books", JLabel.CENTER);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 24));
        add(heading, BorderLayout.NORTH);
        
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 5, 10, 10));  // 5 columns: book details and a renew button
        add(new JScrollPane(panel), BorderLayout.CENTER);

        // Fetch borrowed books for the user
        try {
            Conn c = new Conn();
            String query = "SELECT b.book_id, bk.title, b.borrow_date, b.return_date, b.fine " +
                           "FROM borrowed_books b INNER JOIN Books bk ON b.book_id = bk.book_id " +
                           "WHERE b.user_id = '" + userId + "'";
            ResultSet rs = c.s.executeQuery(query);
            
            while (rs.next()) {
                String bookId = rs.getString("book_id");
                String title = rs.getString("title");
                String borrowDate = rs.getString("borrow_date");
                String returnDate = rs.getString("return_date");
                String fine = rs.getString("fine");

                // Display book details
                panel.add(new JLabel("Title: " + title));
                panel.add(new JLabel("Borrowed: " + borrowDate));
                panel.add(new JLabel("Return by: " + returnDate));
                panel.add(new JLabel("Fine: $" + fine));

                // Add "Renew" button for each book
                JButton renewBtn = new JButton("Renew");
                renewBtn.addActionListener(e -> renewBook(bookId, returnDate));
                panel.add(renewBtn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    // Method to renew the book (extend return date by 7 days)
    private void renewBook(String bookId, String currentReturnDate) {
        try {
            Conn c = new Conn();
            LocalDate newReturnDate = LocalDate.parse(currentReturnDate).plus(7, ChronoUnit.DAYS);
            String query = "UPDATE borrowed_books SET return_date = '" + newReturnDate + "' WHERE book_id = '" + bookId + "' AND user_id = '" + userId + "'";
            c.s.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Return date extended by 7 days");
            dispose();  // Close and refresh the window
            new BorrowedBooksPage(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // No other action needed for now
    }
}
