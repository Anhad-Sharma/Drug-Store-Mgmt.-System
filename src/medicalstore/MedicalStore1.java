
package medicalstore;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class MedicalStore1 extends JFrame implements ActionListener
 {
 Connection c1;
 PreparedStatement pst;
 ResultSet rs;
    
    
JLabel l1,l2,l3,l4;
ImageIcon i1;
JTextField t1;
JPasswordField pf;
JButton bt1,bt2;
    
    MedicalStore1()
    {
   
        getContentPane().setBackground(Color.WHITE);
        setContentPane(new JLabel(new ImageIcon("sky.jpg")));
        
        l1=new JLabel("MEDICAL STORE");
        l1.setBounds(170,10,100,20);
        add(l1);
        
        l2=new JLabel("Username");
        l2.setBounds(170,70,100,20);
        add(l2);
        
        t1=new JTextField();
        t1.setBounds(250,70,150,20);
        add(t1);
        
        l4=new JLabel("Password");
        l4.setBounds(170,110,100,20);
        add(l4);
        
        pf=new JPasswordField();
        pf.setBounds(250,110,150,20);
        add(pf);
        
           
        i1=new ImageIcon("999.jpg");
        
        l3=new JLabel("",i1,JLabel.CENTER);
        l3.setBounds(30,50,100,100);
        add(l3);
        
        bt1=new JButton("Login");
        bt1.setBounds(160,160,75,20);
        add(bt1);
        
        bt2=new JButton("Cancel");
        bt2.setBounds(250,160,75,20);
        add(bt2);
        
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        
        setSize(450,250);
        setTitle("Login");
        setVisible(true);
        setResizable(false);
        setLocation(450,250);
    }
    
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getSource()==bt1)
        {
          try
          {
              
          String un=t1.getText();
          String pwd=pf.getText();
          
          Class.forName("com.mysql.jdbc.Driver").newInstance();
          c1=DriverManager.getConnection("jdbc:mysql://localhost/medical","root","");
          if(un.equals(""))
          {
              JOptionPane.showMessageDialog(this,"Username Required");
          }
          else if(pwd.equals(""))
          {
              JOptionPane.showMessageDialog(this,"Password Required");
          }
          else
          { 
          pst=c1.prepareStatement("Select * from login where Username=? and Password=?");
          pst.setString(1,un);
          pst.setString(2,pwd);
          rs=pst.executeQuery();
          
         if(rs.next())
         {
             JOptionPane.showMessageDialog(this,"Username/Password Accepted");
             MedicalStore2 aa=new MedicalStore2();
             setVisible(false);
         }
         else
             JOptionPane.showMessageDialog(this,"Username/Password Not Accepted"); 
          }                  
          }
          catch(Exception e)
          {
          System.out.println("error is "+e );    
          }
       
        }
    
        if(ae.getSource()==bt2)
        {
            t1.setText("");
            pf.setText("");
            t1.requestFocus();
        }
    }
    
    
    public static void main(String[] args) 
    {
        MedicalStore1 ms=new MedicalStore1();
        ms.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
    }
}
