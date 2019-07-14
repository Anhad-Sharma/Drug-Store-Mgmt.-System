
package medicalstore;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Company extends JFrame implements ActionListener
{
    Connection c1;
    PreparedStatement pst;
    ResultSet rs;
    
    
     JLabel l1,l2,l3,l4,l5,l6,l7;
    JTextField t1,t2,t3,t4,t5;
    ImageIcon i1;
    JButton bt1,bt2; 
    
    
Company()
{
     setLayout(null);
    getContentPane().setBackground(Color.white);
    setContentPane(new JLabel(new ImageIcon("sky.jpg")));
    
    i1=new ImageIcon("images.jpg");
    
    l1=new JLabel("",i1,JLabel.CENTER);
    l1.setBounds(10,30,204,204);
    add(l1);
    
    l2=new JLabel("NEW COMPANY");
    l2.setBounds(325,50,200,20);
    add(l2);
    l2.setFont(new Font("ArialBlack",Font.BOLD,17));
    
     l3=new JLabel("Company Name");
    l3.setBounds(325,150,200,20);
    add(l3);
    
    t1=new JTextField();
    t1.setBounds(460,150,100,20);
    add(t1);
    
     l4=new JLabel("Company Country");
    l4.setBounds(325,200,200,20);
    add(l4);
    
     
    t2=new JTextField();
    t2.setBounds(460,200,100,20);
    add(t2);
    
     l5=new JLabel("Company Email");
    l5.setBounds(325,250,200,20);
    add(l5);
    
     
    t3=new JTextField();
    t3.setBounds(460,250,100,20);
    add(t3);
    
     l6=new JLabel("Company Contact No.");
    l6.setBounds(325,300,200,20);
    add(l6);
    
     
    t4=new JTextField();
    t4.setBounds(460,300,100,20);
    add(t4);
    
    l7=new JLabel("Company Address");
    l7.setBounds(325,350,200,20);
    add(l7);
    
     
    t5=new JTextField();
    t5.setBounds(460,350,100,20);
    add(t5);
    
    bt1=new JButton("Insert");
    bt1.setBounds(370,430,80,20);
    add(bt1);
    
    bt2=new JButton("Back");
    bt2.setBounds(460,430,80,20);
    add(bt2);
    
    bt1.addActionListener(this);
    bt2.addActionListener(this);
    
    
    setSize(800,600);
    setTitle("New Company");
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
        String a=t1.getText();
        String b=t2.getText();
        String c=t3.getText();
        String d=t4.getText();
        String e=t5.getText();
        
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        c1=DriverManager.getConnection("jdbc:mysql://localhost/medical","root","");
        if(a.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Company Name Required");
        }
        else if(b.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Company Country Required");
        }
        else if(c.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Company Email Required");
        }
        else if(d.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Company Contact Required");
        }
        else if(e.equals(""))
        {
            JOptionPane.showMessageDialog(this,"Company Address Required");
        }
        else
        {
        pst=c1.prepareStatement("insert into ncompany values(?,?,?,?,?)");
        pst.setString(1, a);
        pst.setString(2, b);
        pst.setString(3, c);
        pst.setString(4, d);
        pst.setString(5, e);
        
        int j=pst.executeUpdate();
        if(j>0)
        {
            JOptionPane.showMessageDialog(this,"New Company Added");
            
            t1.setText("");
            t2.setText("");
            t3.setText("");
            t4.setText("");
            t5.setText("");
            t1.requestFocus();
        }
        else
            JOptionPane.showMessageDialog(this,"Company Not Added");
        }
        }
        catch(Exception e)
        {
            System.out.print("The error is "+e);
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
Company cn=new Company();
cn.setDefaultCloseOperation(EXIT_ON_CLOSE);
}
}
