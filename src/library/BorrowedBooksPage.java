package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BorrowedBooksPage extends JFrame implements ActionListener {
    String userId;
    String userName;

    BorrowedBooksPage(String userId) {
        this.userId = userId;

        // Fetch user's name from database
        try {
            Conn c = new Conn();
            String userQuery = "SELECT name FROM users WHERE user_id = '" + userId + "'";
            ResultSet rs = c.s.executeQuery(userQuery);
            if (rs.next()) {
                userName = rs.getString("name");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Set full-screen mode
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Borrowed Books");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Heading panel with greeting
        JPanel headingPanel = new JPanel();
        headingPanel.setLayout(new BorderLayout());
        headingPanel.setBackground(new Color(70, 130, 180));

        JLabel heading = new JLabel("Borrowed Books", JLabel.CENTER);
        heading.setFont(new Font("SAN_SERIF", Font.BOLD, 32));
        heading.setForeground(Color.WHITE);

        JLabel greeting = new JLabel("Hi, " + userName, JLabel.LEFT);
        greeting.setFont(new Font("SAN_SERIF", Font.PLAIN, 24));
        greeting.setForeground(Color.WHITE);
        greeting.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 0));

        headingPanel.add(greeting, BorderLayout.WEST);
        headingPanel.add(heading, BorderLayout.CENTER);
        add(headingPanel, BorderLayout.NORTH);

        // Main panel for borrowed books
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 5, 20, 20));  // 5 columns for book details and renew button
        panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
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

                // Book details
                JLabel bookTitle = new JLabel("Title: " + title);
                bookTitle.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
                panel.add(bookTitle);

                JLabel borrowedOn = new JLabel("Borrowed: " + borrowDate);
                borrowedOn.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
                panel.add(borrowedOn);

                JLabel returnBy = new JLabel("Return by: " + returnDate);
                returnBy.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
                panel.add(returnBy);

                JLabel fineLabel = new JLabel("Fine: $" + fine);
                fineLabel.setFont(new Font("SAN_SERIF", Font.PLAIN, 18));
                panel.add(fineLabel);

                // Renew button
                JButton renewBtn = new JButton("Renew");
                renewBtn.setFont(new Font("SAN_SERIF", Font.BOLD, 16));
                renewBtn.setBackground(new Color(144, 238, 144));
                renewBtn.addActionListener(e -> {
                    renewBook(bookId, returnDate, returnBy, renewBtn);
                });
                panel.add(renewBtn);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        setVisible(true);
    }

    // Method to renew the book (extend return date by 7 days)
    private void renewBook(String bookId, String currentReturnDate, JLabel returnBy, JButton renewBtn) {
        try {
            Conn c = new Conn();
            LocalDate newReturnDate = LocalDate.parse(currentReturnDate).plus(7, ChronoUnit.DAYS);
            String query = "UPDATE borrowed_books SET return_date = '" + newReturnDate + "' WHERE book_id = '" + bookId + "' AND user_id = '" + userId + "'";
            c.s.executeUpdate(query);
            returnBy.setText("Return by: " + newReturnDate.toString());  // Update only the return date label
            renewBtn.setEnabled(false);  // Disable the renew button after renewal
            JOptionPane.showMessageDialog(null, "Return date extended by 7 days for " + bookId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // No other action needed for now
    }
}
