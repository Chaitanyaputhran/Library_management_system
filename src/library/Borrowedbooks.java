package library;
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Borrowedbooks extends JPanel implements ActionListener {

    JTextField  tfUserId, tfBookId, tfBorrowDate, tfReturnDate, tfFine;
    JButton submitBtn;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public Borrowedbooks() {
        setLayout(null);

        

      
        JLabel l1 = new JLabel("User ID:");
        l1.setBounds(50, 100, 100, 30);
        add(l1);

        tfUserId = new JTextField();
        tfUserId.setBounds(150, 100, 200, 30);
        add(tfUserId);

        JLabel l2 = new JLabel("Book ID:");
        l2.setBounds(50, 150, 100, 30);
        add(l2);

        tfBookId = new JTextField();
        tfBookId.setBounds(150, 150, 200, 30);
        add(tfBookId);

        JLabel l3 = new JLabel("Borrow Date:");
        l3.setBounds(50, 200, 100, 30);
        add(l3);

        tfBorrowDate = new JTextField(sdf.format(new Date()));
        tfBorrowDate.setBounds(150, 200, 200, 30);
        tfBorrowDate.setEditable(false);  // Set borrow date uneditable
        add(tfBorrowDate);

        JLabel l4 = new JLabel("Return Date:");
        l4.setBounds(50, 250, 100, 30);
        add(l4);

        tfReturnDate = new JTextField(getDefaultReturnDate());
        tfReturnDate.setBounds(150, 250, 200, 30);
        add(tfReturnDate);

        JLabel l5 = new JLabel("Fine:");
        l5.setBounds(50, 300, 100, 30);
        add(l5);

        tfFine = new JTextField("0.00");
        tfFine.setBounds(150, 300, 200, 30);
        tfFine.setEditable(false);  // Fine is automatically calculated
        add(tfFine);

        submitBtn = new JButton("Submit");
        submitBtn.setBounds(150, 350, 100, 30);
        submitBtn.addActionListener(this);  // Add action listener
        add(submitBtn);
    }

 
    @Override
public void actionPerformed(ActionEvent ae) {
    if (ae.getSource() == submitBtn) {
        try {
            String userId = tfUserId.getText();
            String bookId = tfBookId.getText();

            // Check if userId or bookId fields are empty
            if (userId.isEmpty() || bookId.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please fill in all the required fields.");
                return;
            }

            // SQL query to count the number of borrowed books for the user
            String queryCheckBorrowedBooks = "SELECT COUNT(*) as book_count FROM borrowed_books WHERE user_id = '" + userId + "' AND return_date >= CURDATE()";
            
            // Create connection and execute the query
            Conn c = new Conn();
            ResultSet rs = c.s.executeQuery(queryCheckBorrowedBooks);
            
            if (rs.next()) {
                int bookCount = rs.getInt("book_count");
                
                // Check if user has already borrowed 2 books
                if (bookCount >= 2) {
                    JOptionPane.showMessageDialog(this, "You have already borrowed 2 books. Please return one before borrowing another.");
                    return;
                }
            }

            Date currentDate = new Date();
            
            // Fine calculation based on return date
            Date userReturnDate = sdf.parse(tfReturnDate.getText());
            if (currentDate.after(userReturnDate)) {
                long diffInMillies = Math.abs(currentDate.getTime() - userReturnDate.getTime());
                long diffDays = diffInMillies / (1000 * 60 * 60 * 24);  // Calculate difference in days
                double fineAmount = diffDays * 5.0;  // Assuming fine is 5 per day after the return date
                tfFine.setText(String.valueOf(fineAmount));
            } else {
                tfFine.setText("0.00");  // No fine if returned on time
            }

            String borrowDate = tfBorrowDate.getText();
            String returnDateStr = tfReturnDate.getText();
            String fine = tfFine.getText();

            // SQL queries to insert borrowed book and update available copies
            String queryInsert = "INSERT INTO borrowed_books (user_id, book_id, borrow_date, return_date, fine) VALUES ('"
                    + userId + "', '"
                    + bookId + "', '"
                    + borrowDate + "', '"
                    + returnDateStr + "', '"
                    + fine + "')";

            String queryUpdate = "UPDATE Books SET copies_available = copies_available - 1 WHERE book_id = '" + bookId + "'";

            // Execute SQL queries
            c.s.executeUpdate(queryInsert);
            c.s.executeUpdate(queryUpdate);

            // Display success message
            JOptionPane.showMessageDialog(this, "Book borrowed successfully. Copies available updated.");

            // Clear the form fields and update dates
            tfUserId.setText("");
            tfBookId.setText("");
            tfBorrowDate.setText(sdf.format(new Date()));  // Update borrow date to current date
            tfReturnDate.setText(getDefaultReturnDate());  // Update return date
            tfFine.setText("0.00");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }


    }

    // Method to get the default return date (7 days after current date)
    private String getDefaultReturnDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DAY_OF_MONTH, 7);
        return sdf.format(cal.getTime());
    }
}
