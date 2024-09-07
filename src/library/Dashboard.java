
package library;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener{
    JButton addbooks,deletebooks;
  
    Dashboard(){
//        setBounds(0,0,1600,1000);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
         setLayout(null);
         JPanel  p1= new JPanel();
         p1.setLayout(null);
         p1.setBackground(new Color(0,0,102));
         p1.setBounds(0,0,1600,65);
         add(p1);
         ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/dashboard.png"));
         Image i2=i1.getImage().getScaledInstance(70,70,Image.SCALE_DEFAULT);
         ImageIcon i3=new ImageIcon(i2);
         JLabel icon=new JLabel(i3);
         icon.setBounds(5,0,70,70);
         p1.add(icon);
         
         JLabel heading = new JLabel("Dashboard");
         heading.setBounds(80,10,300,10);
         heading.setForeground(Color.WHITE);
         heading.setFont(new Font("Tahoma",Font.BOLD,30));
         p1.add(heading);
         
         JPanel  p2= new JPanel();
         p2.setLayout(null);
         p2.setBackground(new Color(0,0,102));
         p2.setBounds(0,65,300,900);
         add(p2);
         
         JButton home = new JButton("Home");
         home.setBounds(0,0,300,50);
         home.setBackground(new Color(0,0,102));
         home.setForeground(Color.WHITE);
         home.setFont(new Font("Tahoma",Font.PLAIN,20));
         p2.add(home);
         
        addbooks = new JButton("Add Books");
         addbooks.setBounds(0,60,300,50);
         addbooks.setBackground(new Color(0,0,102));
         addbooks.setForeground(Color.WHITE);
         addbooks.setFont(new Font("Tahoma",Font.PLAIN,20));
         addbooks.addActionListener(this);
         p2.add(addbooks);
         
         deletebooks = new JButton("Add Books");
         deletebooks.setBounds(0,120,300,50);
         deletebooks.setBackground(new Color(0,0,102));
         deletebooks.setForeground(Color.WHITE);
         deletebooks.setFont(new Font("Tahoma",Font.PLAIN,20));
         deletebooks.addActionListener(this);
         p2.add(deletebooks);
         
         
         
        
         
         
         
         
         
         
         
        setVisible(true);
       
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==addbooks){
            new Newbook();
        }
        if(ae.getSource()==deletebooks){
            new DeleteBook();
        }
    }
    public static void main(String [] args){
        new Dashboard();
    }
}
