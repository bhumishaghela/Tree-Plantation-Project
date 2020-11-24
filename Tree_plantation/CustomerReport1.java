import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.* ;

public class CustomerReport1 implements ActionListener
{
      
      String url="jdbc:postgresql://localhost/cottoncounty";
      String sql = "";
      String sql1 = "";
      Connection con;
      Statement st ;
      ResultSet rs ;
     
      
	private JFrame fr;
	private JButton btnCash,btnCheque;
	private JComboBox dblb1;
	private JLabel lblPayment;
	private DefaultTableModel model;
	private JTable  etab;
	int i = 0;

	CustomerReport1()
	{
		fr = new JFrame("Customer Report");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Customer Report");
	 	fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                   
		ImageIcon ic = new ImageIcon("Reportcust.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);
                
		try
		{
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url,"postgres","classic");
			st = con.createStatement();
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("Error " + e);
		}
		catch(SQLException e)
		{
			System.out.println("Error " + e);
		}
		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);
		
		Font f2;
		f2=new Font("CAMBRIA",Font.BOLD,14);
			
		lblPayment = new JLabel("Payment Type");
	 	lblPayment.setBounds(350,150,140,25);
	            	lblPayment.setFont(f1);

		dblb1= new JComboBox();
		dblb1.setBounds(500,150,140,25);
		dblb1.setFont(f1);
		dblb1.addItem("Cash");
		dblb1.addItem("Cheque");

		/*	btnCash = new JButton(" [CASH] ");
		 	btnCash.setBounds(450,150,140,25);
			btnCash.addActionListener(this);
			btnCash.setFont(f1);
			
			btnCheque = new JButton(" [CHEQUE] ");
		 	btnCheque.setBounds(450,150,140,25);
		            	btnCheque.addActionListener(this);
            			btnCheque.setFont(f1);
		*/

/*********************************** TABLE SETTING *****************************************************/

		model = new DefaultTableModel();
	        	model.addColumn("CUSTOMER ID");
	        	model.addColumn("CUSTOMER NAME");
	       	model.addColumn("TYPE");
	        	model.addColumn("CONTACT");
	        	model.addColumn("PAYMENT MADE");

	        	etab = new JTable(model);

	  	JScrollPane jp = new JScrollPane(etab , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jp.setBounds(120,250,780,300);
	        
	        	etab.setFont(f2);

/*********************************** ADDING OF CONTENT PANE *********************************************/

	        	fr.getContentPane().add(jp);
	        	fr.getContentPane().add(lblPayment);
	        	fr.getContentPane().add(dblb1);
	        	fr.getContentPane().add(pic1);

	        	fr.setSize(1024,768);
	        	fr.setVisible(true);
	}
   
   
   /*******************************************************************************************************/

	public void actionPerformed(ActionEvent e)
       	{
                    if(e.getSource()==btnCash)
       	   {
               		try
            		{
		//	sql = "select * from CustomerBill where PaymentType='"+ Cash +"'";
			ResultSet rs = st.executeQuery(sql);
            			int i = 1 ;
			int r = 0;
		            
			while(rs.next()) 
                   	                {
				int SrNo=i++;
			                model.insertRow(r++,new Object[] { rs.getInt(1) ,rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5) }  );
			}
               		}
		catch(SQLException e1)
		{
			JOptionPane.showMessageDialog(null,e1.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
		}
		catch(NumberFormatException e1)
		{
			JOptionPane.showMessageDialog(null,e1.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
		}  
           	}
	}   
        
/*******************************************************************************************************/

	public static void main(String arg[])
	{
		new CustomerReport1();
	}
}
