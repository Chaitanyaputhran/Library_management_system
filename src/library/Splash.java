package library;
import javax.swing.*;
public  class Splash extends JFrame implements Runnable{
    Thread thread;
    Splash(){
        setSize(1200,600);
       setLocation(200,100);
        setVisible(true);
        JLabel image=new JLabel("Hi");
        add(image);
        thread=new Thread(this);
        thread.start();
      
        
    }
    public void run(){
        try{
            Thread.sleep(7000);
//            new Login();
setVisible(false);
        }catch(Exception e){
            
        }
    }
    
    
    public static void main(String[] args){
        new Splash();
       
    }
    
    
}
