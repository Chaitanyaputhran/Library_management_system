package library;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {
    JButton addbooks, deletebooks, borrowedbooks;
    JPanel mainContentPanel;
  
    Dashboard() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        // Header Panel
        JPanel p1 = new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(0, 0, 102));
        p1.setBounds(0, 0, 1600, 65);
        add(p1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/dashboard.png"));
        Image i2 = i1.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel icon = new JLabel(i3);
        icon.setBounds(5, 0, 70, 70);
        p1.add(icon);

        JLabel heading = new JLabel("Dashboard");
        heading.setBounds(80, 10, 300, 30);
        heading.setForeground(Color.WHITE);
        heading.setFont(new Font("Tahoma", Font.BOLD, 30));
        p1.add(heading);

        // Side Panel
        JPanel p2 = new JPanel();
        p2.setLayout(null);
        p2.setBackground(new Color(0, 0, 102));
        p2.setBounds(0, 65, 300, 900);
        add(p2);

        JButton home = new JButton("Home");
        home.setBounds(0, 0, 300, 50);
        home.setBackground(new Color(0, 0, 102));
        home.setForeground(Color.WHITE);
        home.setFont(new Font("Tahoma", Font.PLAIN, 20));
        p2.add(home);

        addbooks = new JButton("Add Books");
        addbooks.setBounds(0, 60, 300, 50);
        addbooks.setBackground(new Color(0, 0, 102));
        addbooks.setForeground(Color.WHITE);
        addbooks.setFont(new Font("Tahoma", Font.PLAIN, 20));
        addbooks.addActionListener(this);
        p2.add(addbooks);

        deletebooks = new JButton("Edit Books");
        deletebooks.setBounds(0, 120, 300, 50);
        deletebooks.setBackground(new Color(0, 0, 102));
        deletebooks.setForeground(Color.WHITE);
        deletebooks.setFont(new Font("Tahoma", Font.PLAIN, 20));
        deletebooks.addActionListener(this);
        p2.add(deletebooks);

        borrowedbooks = new JButton("Borrowed Books");
        borrowedbooks.setBounds(0, 180, 300, 50);
        borrowedbooks.setBackground(new Color(0, 0, 102));
        borrowedbooks.setForeground(Color.WHITE);
        borrowedbooks.setFont(new Font("Tahoma", Font.PLAIN, 20));
        borrowedbooks.addActionListener(this);
        p2.add(borrowedbooks);

        // Main content panel (for switching views)
        mainContentPanel = new JPanel(new CardLayout());
        mainContentPanel.setBounds(300, 65, 1300, 900);
        add(mainContentPanel);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        CardLayout cl = (CardLayout) (mainContentPanel.getLayout());
        if (ae.getSource() == addbooks) {
            mainContentPanel.removeAll(); // Clear any previous content
            Newbook addBooksForm = new Newbook();
            mainContentPanel.add(addBooksForm);
            mainContentPanel.revalidate();
            mainContentPanel.repaint();
        } else if (ae.getSource() == deletebooks) {
            mainContentPanel.removeAll(); // Clear any previous content
            DeleteBook deleteBooksForm = new DeleteBook();
            mainContentPanel.add(deleteBooksForm);
            mainContentPanel.revalidate();
            mainContentPanel.repaint();
            
        } else if (ae.getSource() == borrowedbooks) {
            mainContentPanel.removeAll(); 
            Borrowedbooks borrowedBooksForm = new Borrowedbooks();
            mainContentPanel.add(borrowedBooksForm);
            mainContentPanel.revalidate();
            mainContentPanel.repaint();
        }
    }

    public static void main(String[] args) {
        new Dashboard();
    }
}
