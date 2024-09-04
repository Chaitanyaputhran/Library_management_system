
package library;
import java.awt.*;
import javax.swing.*;

public class Loading extends JFrame implements Runnable{
    Thread t;
    JProgressBar bar;
    String username;
    public void run(){
        try{
            for(int i=1;i<=101;i++){
                int max=bar.getMaximum();
                int value=bar.getValue();
                if(value<max){
                    bar.setValue(bar.getValue()+1);
                }else{
                    setVisible(false);
                    //new class object
                }
                Thread.sleep(50);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    Loading(String username){
        this.username=username;
        t=new Thread(this);
        setBounds(500,200,650,400);
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);
        JLabel text= new JLabel("Libraray Management System");
        text.setBounds(50,20,600,40);
        text.setForeground(Color.RED);
        text.setFont(new Font("Railway",Font.BOLD,35));
        
        add(text);
        
        bar=new JProgressBar();
        bar.setBounds(150,100,300,16);
        bar.setStringPainted(true);
        add(bar);
        
        JLabel pleasewait= new JLabel("Loading,please wait...");
        pleasewait.setBounds(230,130,250,20);
        pleasewait.setForeground(Color.BLUE);
        pleasewait.setFont(new Font("Railway",Font.BOLD,16));
        add(pleasewait);
        
        JLabel lblusername= new JLabel("Welcome "+username);
        lblusername.setBounds(20,310,400,40);
        lblusername.setForeground(Color.RED);
        lblusername.setFont(new Font("Railway",Font.BOLD,16));
        add(lblusername);
        
        t.start();
        
        setVisible(true);
    }
    public static void main(String [] args){
    new Loading("");
    }
}
