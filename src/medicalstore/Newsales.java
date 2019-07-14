
package medicalstore;
import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class Newsales extends JFrame implements ActionListener 
{
    Connection c1;
    PreparedStatement pst;
    ResultSet rs;
    
    
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10;
    JTextField t1,t2,t3,t4,t5,t6;
    ImageIcon i1;
    JButton bt1,bt2;
    JComboBox cb1,cb2;
    
    
Newsales()
{
    setLayout(null);
    getContentPane().setBackground(Color.white);
    setContentPane(new JLabel(new ImageIcon("sky.jpg")));
    
    i1=new ImageIcon("images.jpg");
    
    l1=new JLabel("",i1,JLabel.CENTER);
    l1.setBounds(10,30,204,204);
    add(l1);
    
    l2=new JLabel("NEW SALES");
    l2.setBounds(325,50,200,20);
    add(l2);
    l2.setFont(new Font("ArialBlack",Font.BOLD,17));
    
     l3=new JLabel("Product Name");
     l3.setBounds(325,150,200,20);
     add(l3);
    
     cb1=new JComboBox();
     cb1.setBounds(460,150,100,20);
     add(cb1);
     cb1.addItem("Select");
    
     l4=new JLabel("Company Name");
     l4.setBounds(325,200,200,20);
     add(l4);
    
     cb2=new JComboBox();
     cb2.setBounds(460,200,100,20);
     add(cb2);
    cb2.addItem("Select");
    
     l5=new JLabel("Product Quantity");
     l5.setBounds(325,250,200,20);
     add(l5);
    
     
    t3=new JTextField();
    t3.setBounds(460,250,100,20);
    add(t3);
    
     l6=new JLabel("Product Price");
     l6.setBounds(325,300,200,20);
     add(l6);
    
     
    t4=new JTextField();
    t4.setBounds(460,300,100,20);
    add(t4);
    
    l7=new JLabel("Customer Name");
    l7.setBounds(325,350,200,20);
    add(l7);
    
     
    t5=new JTextField();
    t5.setBounds(460,350,100,20);
    add(t5);
    
    l8=new JLabel("Purchase Date");
    l8.setBounds(325,400,200,20);
    add(l8);
    
    t6=new JTextField();
    t6.setBounds(460,400,100,20);
    add(t6);
    
    l9=new JLabel("Amount Paid");
    l9.setBounds(325,450,100,20);
    add(l9);
    
    t1=new JTextField();
    t1.setBounds(460,450,100,20);
    add(t1);
    
    l10=new JLabel("Credit");
    l10.setBounds(325,500,100,20);
    add(l10);
    
    t2=new JTextField();
    t2.setBounds(460,500,100,20);
    add(t2);
    
    
    bt1=new JButton("Submit");
    bt1.setBounds(370,538,80,20);
    add(bt1);
    
    bt2=new JButton("Back");
    bt2.setBounds(460,538,80,20);
    add(bt2);
    
    bt1.addActionListener(this);
    bt2.addActionListener(this);
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
    setTitle("New Sales");
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
            String c=t3.getText();
            String d=t4.getText();
            String e=t5.getText();
            String f=t6.getText();
            String g=t1.getText();
            String h=t2.getText();
            
            if(c.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please Enter Product Quantity");
            }
            else if(d.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please Enter Product Price");
            }
            else if(e.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please Enter Customer Name");
            }
            else if(f.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please Enter Purchase Date");
            }
            else if(g.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please Enter Amount Paid");
            }
            else if(h.equals(""))
            {
                JOptionPane.showMessageDialog(this,"Please Enter Credit");
            }
            else
            {
            pst=c1.prepareStatement("insert into nsales values(?,?,?,?,?,?,?,?)");
            pst.setString(1,a);
            pst.setString(2,b);
            pst.setString(3,c);
            pst.setString(4,d);
            pst.setString(5,e);
            pst.setString(6,f);
            pst.setString(7,g);
            pst.setString(8,h);
            int j=pst.executeUpdate();
            if(j>0)
            {
                pst=c1.prepareStatement("Select * from nproduct where ProductName=?");
                pst.setString(1,a);
                rs=pst.executeQuery();
                if(rs.next())
                {
                    int quantityStored=Integer.parseInt(rs.getString("ProductQuantity"));
                    int netQuantity=Integer.parseInt(c);
                    int totalqty=quantityStored-netQuantity;
                    String updated=String.valueOf(totalqty);
                    pst=c1.prepareStatement("Update nproduct set ProductQuantity=? where ProductName=?");
                    pst.setString(1,updated);
                    pst.setString(2,a);
                    int jj=pst.executeUpdate();
                  
               JOptionPane.showMessageDialog(this,"Saved Successfully");
                 
                cb1.setSelectedIndex(0);
                 cb2.setSelectedIndex(0);
                 t3.setText("");
                 t4.setText("");
                 t5.setText("");
                 t6.setText("");
                 t1.setText("");
                 t2.setText("");
                 t3.requestFocus();
                   return;
                }
            }
            else
                JOptionPane.showMessageDialog(this,"There is Problem...");
            }
        }
        catch(Exception e)
        {
            System.out.println("Error Is "+e);
        }
    }
    if(ae.getSource()==bt2)
    {
         MedicalStore2 aa=new MedicalStore2();
         setVisible(false);
    }
    
    
}   
public static void main(String args[])
{
    Newsales ns=new Newsales();
    ns.setDefaultCloseOperation(EXIT_ON_CLOSE);
}
}
