
package medicalstore;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class updateproduct extends JFrame implements ActionListener 
{
Connection c1;
PreparedStatement pst;
ResultSet rs;
    
ImageIcon i1;    
JLabel  l1,l2,l3;
JButton b1,b2;
JComboBox cb1;
JButton bt1,bt2,bt3;
JTextField t1,t2;
    
updateproduct()
{
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);
    setContentPane(new JLabel(new ImageIcon("sky.jpg")));
    
    l1=new JLabel("PRODUCT UPDATE");
    l1.setBounds(325,50,200,20);
    add(l1);
    l1.setFont(new Font("Arial Black",Font.BOLD,15));
    
    l2=new JLabel("Product Name");
    l2.setBounds(325,150,200,20);
    add(l2);
    
    cb1=new JComboBox();
    cb1.setBounds(440,150,125,20);
    add(cb1);
    cb1.addItem("Select");
    
    i1=new ImageIcon("well.jpg");
    
    l3=new JLabel("",i1,JLabel.CENTER);
    l3.setBounds(0,0,243,116);
    add(l3);
    
    
    t1=new JTextField();
    t1.setBounds(325,250,100,20);
    add(t1);
    
    bt1=new JButton("Update Price");
    bt1.setBounds(440,250,125,20);
    add(bt1);
    
    t2=new JTextField();
    t2.setBounds(325,300,100,20);
    add(t2);
    
    bt2=new JButton("Update Quantity");
    bt2.setBounds(440,300,125,20);
    add(bt2);
    
    bt3=new JButton("Back");
    bt3.setBounds(20,525,125,30);
    add(bt3);
    
    bt1.addActionListener(this);
    bt2.addActionListener(this);
    bt3.addActionListener(this);
    try
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        c1=DriverManager.getConnection("jdbc:mysql://localhost/medical","root","");
       
        
        pst=c1.prepareStatement("select ProductName from nproduct");
        rs=pst.executeQuery();
        while(rs.next())
        {
            cb1.addItem(rs.getString(1));
                
        }
    }
    catch(Exception e)
    {
        
    }
    
    setSize(800,600);
    setTitle("Product Update");
    setVisible(true);
    setLocation(300,100);
    setResizable(false);
            
}
public void actionPerformed(ActionEvent ae)
{
    if(ae.getSource()==bt1)
    {
        try
        {
             String r=t1.getText();
            String p=cb1.getSelectedItem().toString();
           
            if(r.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please Enter Price to be update");
            }
            else
            {
            pst=c1.prepareStatement("update nproduct set ProductPrice=? where ProductName=?");
            pst.setString(1, r);
            pst.setString(2, p);
            
        int j=pst.executeUpdate();
        
        if(j>0)
        {
            JOptionPane.showMessageDialog(this, "Price Updated");
            cb1.setSelectedIndex(0);
             t1.setText("");
        }
        else
            JOptionPane.showMessageDialog(this, "There is some problem...");
        }
        }
        catch(Exception e)
        {
            
        }
        
    }
        if(ae.getSource()==bt2)
        {
            try
                 {
                       String u=t2.getText();
                   String o=cb1.getSelectedItem().toString();
                 
                  if(u.equals(""))
                  {
                      JOptionPane.showMessageDialog(this,"Please Enter The Quantity To Be Updated");
                  }
                else
                  {
                pst=c1.prepareStatement("update nproduct set ProductQuantity=? where ProductName=?");
                
                pst.setString(1, u);
                pst.setString(2, o);
                
                int h=pst.executeUpdate();
                
                if(h>0)
                {
                    JOptionPane.showMessageDialog(this,"Quantity Updated");
                     t2.setText("");
                     cb1.setSelectedIndex(0);
                }
                else
                    JOptionPane.showMessageDialog(this,"Quantiy Not Updated");
            }
                 }
            catch(Exception e)
            {
                System.out.println("The error is "+e);
            }
        }
        
   
    if(ae.getSource()==bt3)
    {
        MedicalStore3 ms3=new MedicalStore3();
        setVisible(false);
    }
}

public static void main(String args[])
{
    updateproduct up=new updateproduct();
    up.setDefaultCloseOperation(EXIT_ON_CLOSE);
}
}
