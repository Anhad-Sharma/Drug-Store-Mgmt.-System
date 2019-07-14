
package medicalstore;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class deletecompany extends JFrame implements ActionListener
{
 Connection c1;
 PreparedStatement pst;
 ResultSet rs;
    
 JLabel l1,l2,l3;
 JComboBox cb1;
 JButton bt1,bt2;
 ImageIcon i1;   
    
deletecompany()
{
setLayout(null);
getContentPane().setBackground(Color.white);
setContentPane(new JLabel(new ImageIcon("sky.jpg")));
         
         
    l1=new JLabel("DELETE COMPANY");
    l1.setBounds(325,50,200,20);
    add(l1);
    l1.setFont(new Font("Arial Black",Font.BOLD,15));
         
    l2=new JLabel("Company Name");
    l2.setBounds(325,200,200,20);
    add(l2);
    
    cb1=new JComboBox();
    cb1.setBounds(440,200,125,20);
    add(cb1);
    
    cb1.addItem("Select");
    
    i1=new ImageIcon("well.jpg");
    
    l3=new JLabel("",i1,JLabel.CENTER);
    l3.setBounds(0,0,243,116);
    add(l3);

    bt1=new JButton("Delete Company");
    bt1.setBounds(630,500,135,40);
    add(bt1);
    
    bt2=new JButton("Back");
    bt2.setBounds(30,500,125,40);
    add(bt2);
    
    bt1.addActionListener(this);
    bt2.addActionListener(this);
    
    try
    {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        c1=DriverManager.getConnection("jdbc:mysql://localhost/medical","root","");
        
        pst=c1.prepareStatement("select CompanyName from ncompany");
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
        setTitle("Delete Company");
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
        String p=cb1.getSelectedItem().toString();
        pst=c1.prepareStatement("delete from ncompany where CompanyName=?");
        pst.setString(1, p);
        int j=pst.executeUpdate();
        if(j>0)
        {
            JOptionPane.showMessageDialog(this,"Company Deleted Successfully");
            cb1.setSelectedIndex(0);
        }
        else
        {
            JOptionPane.showMessageDialog(this,"Company Not Deleted");
        }
        }
        catch(Exception e)
        {
            
        }        
    }
    if(ae.getSource()==bt2)
    {
        Cinsert ci=new Cinsert();
        setVisible(false);
    }
}
    
public static void main(String args[])
{
    deletecompany dc=new deletecompany();
    dc.setDefaultCloseOperation(EXIT_ON_CLOSE);
}
}
