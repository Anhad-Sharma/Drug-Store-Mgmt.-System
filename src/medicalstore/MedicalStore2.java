
package medicalstore;
import java.awt.Color;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Date;

public class MedicalStore2 extends JFrame implements ActionListener
{
    Connection c1;
    PreparedStatement pst;
    ResultSet rs;
    
    ImageIcon i1;
    JLabel l1,l2;
    JButton bt1,bt2,bt3,bt4,bt5,bt6,bt7,bt8,bt9;
    JTextField t1;
    JTable tb1;    
    
    
MedicalStore2()
{
    setLayout(null);
    getContentPane().setBackground(Color.WHITE);
    setContentPane(new JLabel(new ImageIcon("sky.jpg")));    
    
    
    i1=new ImageIcon("697.jpg");
    
    l1=new JLabel("",i1,JLabel.CENTER);
    l1.setBounds(0,0,390,177);
    add(l1);
    
    l2=new JLabel();
    l2.setBounds(200,250,150,20);
    add(l2);
    
    
    bt1=new JButton("Medicines");
    bt1.setBounds(20,250,150,20);
    add(bt1);
    
    bt2=new JButton("Companies");
    bt2.setBounds(20,300,150,20);
    add(bt2);
    
    bt3=new JButton("View Sale Records");
    bt3.setBounds(20,350,150,20);
    add(bt3);
    
     bt4=new JButton("New Sales");
    bt4.setBounds(20,400,150,20);
    add(bt4);
    
    bt5=new JButton("LogOut");
    bt5.setBounds(20,600,150,50);
    add(bt5);
    
    t1=new JTextField();
    t1.setBounds(450,70,700,20);
    add(t1);
    
    bt7=new JButton("Enter Expiry");
    bt7.setBounds(20,450,150,20);
    add(bt7);
   
    bt8=new JButton("Stock In Hand");
    bt8.setBounds(20,500,150,20);
    add(bt8);
   
    
    bt6=new JButton("Search");
    bt6.setBounds(1180,70,100,20);
    add(bt6);
    
    bt9=new JButton("About");
    bt9.setBounds(1180,10,100,20);
    add(bt9);
    
  
    bt1.addActionListener(this);
    bt2.addActionListener(this);
    bt5.addActionListener(this);
    bt4.addActionListener(this);
    bt3.addActionListener(this);
    bt7.addActionListener(this);
    bt8.addActionListener(this);
    bt9.addActionListener(this);
    
    setSize(1500,750);
    setTitle("Medicine Cupboard");
    setVisible(true);
    
}
public void actionPerformed(ActionEvent ae)
{
    if(ae.getSource()==bt1)
    {
         MedicalStore3 ms3=new MedicalStore3();
         setVisible(false);
    }
    if(ae.getSource()==bt2)
    {
        Cinsert ci=new Cinsert();
        setVisible(false);
    }
    if(ae.getSource()==bt5)
    {
          MedicalStore1 ms=new MedicalStore1();
          setVisible(false);
    }
    if(ae.getSource()==bt4)
    {
         Newsales ns=new Newsales();
         setVisible(false);
    }
    if(ae.getSource()==bt3)
    {
        Salerecords sr=new Salerecords();
        setVisible(false);
    }
    if(ae.getSource()==bt7)
    {
        Enterexpiry ee=new Enterexpiry();
        setVisible(false);
    }
    if(ae.getSource()==bt8)
    {
        Stock sk=new Stock();
        setVisible(false);
    }
    if(ae.getSource()==bt9)
    {
        String msg = "<html><h2>MEDICAL STORE</h2><img src='12.JPG' width='100' height='100'> <br> Developed By:&nbsp;Gurdeep Singh & Bhupinder Singh , Yamunanagar <br> Contact :9991427366";

    JOptionPane optionPane = new JOptionPane();
    optionPane.setMessage(msg);
    optionPane.setMessageType(JOptionPane.INFORMATION_MESSAGE);
    JDialog dialog = optionPane.createDialog(null, "About Us");
    dialog.setVisible(true);
    }
}
public static void main(String args[])
{
    MedicalStore2 aa=new MedicalStore2();
    aa.setDefaultCloseOperation(EXIT_ON_CLOSE);
}
}
