package library;

 import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.sql.*;


   

public class Userlogin extends JFrame implements ActionListener {
    JButton fpassword,login,signup;
    JTextField tfusername,tfpassword;
    Userlogin(){
        setSize(900,400);
        setLocation(350,200);
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        JPanel p1=new JPanel();
        p1.setLayout(null);
        p1.setBackground(new Color(131,193,233));
        p1.setBounds(0,0,400,400);
        add(p1);
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("icons/login.png"));
        Image i2=i1.getImage().getScaledInstance(200, 200, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);
        JLabel image= new JLabel(i3);
        image.setBounds(100,120,200,200);
        p1.add(image);
        
        JPanel p2=new JPanel();
        p2.setLayout(null);
        p2.setBounds(400,30,450,300);
        add(p2);
        
        JLabel username=new JLabel("Username");
        username.setBounds(60,20,100,25);
        username.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
        p2.add(username);
         tfusername=new JTextField();
        tfusername.setBounds(60,60,300,30);
        tfusername.setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfusername);
        
        JLabel password=new JLabel("Password");
        password.setBounds(60,110,100,25);
        password.setFont(new Font("SAN_SERIF",Font.PLAIN,20));
        p2.add(password);
         tfpassword=new JTextField();
        tfpassword.setBounds(60,150,300,30);
        tfpassword.setBorder(BorderFactory.createEmptyBorder());
        p2.add(tfpassword);
        
         login=new JButton("Login");
        login.setBounds(60,200,130,30);
        login.setBackground(new Color(133,193,233));
        login.setForeground(Color.WHITE);
         login.addActionListener(this);
        login.setBorder(new LineBorder(new Color(133,193,233)));
        p2.add(login);
        
        signup=new JButton("Signup");
        signup.setBounds(230,200,130,30);
        signup.setBackground(new Color(133,193,233));
        signup.setForeground(Color.WHITE);
        signup.setBorder(new LineBorder(new Color(133,193,233)));
        signup.addActionListener(this);
        p2.add(signup);
        
           
        fpassword=new JButton("Forgot Password");
        fpassword.setBounds(130,250,130,30);
        fpassword.setBackground(new Color(133,193,233));
        fpassword.setForeground(Color.WHITE);
        fpassword.setBorder(new LineBorder(new Color(133,193,233)));
        fpassword.addActionListener(this);
        p2.add(fpassword);
        
        JLabel text=new JLabel("Trouble Loging in?");
        text.setBounds(300,250,150,20);
        text.setForeground(Color.RED);
        p2.add(text);
        
        
        setVisible(true);
        
        
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==fpassword){
            setVisible(false);
            new Forgotpassword();
        }
        else if(ae.getSource()==login){
            try{
                String username=tfusername.getText();
                String password=tfpassword.getText();
                
                String query="select * from user where username='"+username+"'AND password='"+password+"'";
                Conn c= new Conn();
               ResultSet rs= c.s.executeQuery(query);
               if(rs.next()){
                   setVisible(false);
                   new Loading(username);
               }else{
                   JOptionPane.showMessageDialog(null, "Incorrect username or password");
               }
                
            }catch(Exception e){
               e.printStackTrace();
            }
        }else{
            setVisible(false);
            new Signup();
        }
    }
    public static void main(String[] args){
        new Userlogin();
    }
    
}


