
package medicalstore;
import java.awt.Color;
import java.awt.Font;
import java.sql.*;
import javax.swing.*;
import java.awt.event.*;

public class Enterexpiry extends JFrame implements ActionListener
{
Connection c1;
PreparedStatement pst;
ResultSet rs;

    
 ImageIcon i1;    
JLabel  l1,l2,l3,l4,l5,l6,l7;
JComboBox cb1,cb2;
JButton bt1,bt2,bt3;
JTextField t1,t2,t3;   
 
Enterexpiry()
{
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);
    setContentPane(new JLabel(new ImageIcon("sky.jpg")));
    
    l1=new JLabel("EXPIRY CHECK");
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
    
    l3=new JLabel("Company Name");
    l3.setBounds(325,200,200,20);
    add(l3);
    
    cb2=new JComboBox();
    cb2.setBounds(440,200,125,20);
    add(cb2);
    cb2.addItem("Select");
    
    i1=new ImageIcon("well.jpg");
    
    l4=new JLabel("",i1,JLabel.CENTER);
    l4.setBounds(0,0,243,116);
    add(l4);
    
    
    l5=new JLabel("Mfg. Date");
    l5.setBounds(325,250,200,20);
    add(l5);
    
    t1=new JTextField();
    t1.setBounds(440,250,125,20);
    add(t1);
    
    l6=new JLabel("Expiry Date");
    l6.setBounds(325,300,200,20);
    add(l6);
    
    t2=new JTextField();
    t2.setBounds(440,300,125,20);
    add(t2);
    
    l7=new JLabel("Batch Number");
    l7.setBounds(325,350,200,20);
    add(l7);
    
    t3=new JTextField();
    t3.setBounds(440,350,125,20);
    add(t3);
    
    bt1=new JButton("Save");
    bt1.setBounds(270,450,110,30);
    add(bt1);
    
    bt2=new JButton("Check");
    bt2.setBounds(400,450,110,30);
    add(bt2);
    
    bt3=new JButton("Back");
    bt3.setBounds(530,450,110,30);
    add(bt3);
    
    
    
    bt1.addActionListener(this);
    bt2.addActionListener(this);
    bt3.addActionListener(this);
    
    try
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        c1=DriverManager.getConnection("jdbc:mysql://localhost/medical","root","");
       
        
        pst=c1.prepareStatement("select ProductName,CompanyName from nproduct");
        rs=pst.executeQuery();
        while(rs.next())
        {
            cb1.addItem(rs.getString(1));
            cb2.addItem(rs.getString(2));    
        }
    }
    catch(Exception e)
    {
        
    }
    
    
    setSize(800,600);
    setTitle("Company Update");
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
        String a=cb1.getSelectedItem().toString();
        String b=cb2.getSelectedItem().toString();
        String c=t1.getText();
        String d=t2.getText();
        String e=t3.getText();
        if(c.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Enter Mfg Date");
        }
        else if(d.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Enter Expiry Date");
        }
        else if(e.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Enter Batch Number");
        }
        
        else
        {
        pst=c1.prepareStatement("insert into expirychk values(?,?,?,?,?)");
        pst.setString(1,a);
        pst.setString(2,b);
        pst.setString(3,c);
        pst.setString(4,d);
        pst.setString(5,e);
        int j=pst.executeUpdate();
        if(j>0)
        {
            JOptionPane.showMessageDialog(this,"Record Saved");
            
            cb1.setSelectedIndex(0);
            cb2.setSelectedIndex(0);
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t1.requestFocus();
        }
        else
            JOptionPane.showMessageDialog(this,"Record Not Saved");
        }
        }
        catch(Exception e)
        {
            
        }
    }
    if(ae.getSource()==bt2)
    {
        Check ck=new Check();
        setVisible(false);
    }
    
    if(ae.getSource()==bt3)
    {
        MedicalStore2 aa=new MedicalStore2();
        setVisible(false);
    }
}   
public static void main(String args[])
{
    Enterexpiry ee=new Enterexpiry();
    ee.setDefaultCloseOperation(EXIT_ON_CLOSE);
}
}
