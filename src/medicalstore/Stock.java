
package medicalstore;
import javax.swing.*;
import java.sql.*;
import java.awt.event.*;

public class Stock extends JFrame implements ActionListener
{
 Connection c1;
 PreparedStatement pst;
 ResultSet rs;
 
  JLabel l1;  
 JTable tb1;
 JButton bt1;
 Stock()
{
 setLayout(null);
    setContentPane(new JLabel(new ImageIcon("sky.jpg")));
    
    l1=new JLabel("STOCK IN HAND");
    l1.setBounds(450,20,120,20);
    add(l1);
    
    bt1=new JButton("Back");
    bt1.setBounds(450,530,100,30);
    add(bt1);
    
    String colhead[]={"ProductName","CompanyName","Product Category","ProductQuantity","ProductPrice"};
    Object data[][]=new Object[500][5];
    tb1=new JTable(data,colhead);
    int v=ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS;
    int h=ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS;
    JScrollPane jsp=new JScrollPane(tb1,v,h);
    jsp.setBounds(80,80,830,400);
    add(jsp);
    
    try
        {
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        c1=DriverManager.getConnection("jdbc:mysql://localhost/medical","root","");
        pst=c1.prepareStatement("select * from nproduct");
        rs=pst.executeQuery();
        int r=0;
        
        while(rs.next())
        {
            data[r][0]=rs.getString(1);
            data[r][1]=rs.getString(2);
            data[r][2]=rs.getString(3);
            data[r][3]=rs.getString(4);
            data[r][4]=rs.getString(5);
            r++;
        }
         }
        catch (Exception e)
        {
            
        }
bt1.addActionListener(this);
    
    setSize(1000,600);
    setTitle("SaleRecords");
    setVisible(true);
    setLocation(200,80);
    setResizable(false);
}   

public void actionPerformed(ActionEvent ae)
{
    if(ae.getSource()==bt1)
    {
         MedicalStore2 aa=new MedicalStore2();
         setVisible(false);
    }

}
public static void main(String args[])
{
    Stock sk=new Stock();
    
}
}
