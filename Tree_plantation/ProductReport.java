import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.* ;

public class ProductReport implements ActionListener
{
	private JFrame fr;
	private JLabel lblFromDate,lblToDate;
	private JTextField txtFromDate,txtToDate;
	private JButton btnPrint;
	private DefaultTableModel model;
	private JTable  etab;
	private String url = "jdbc:postgresql://localhost/tree_plantation";

	ProductReport()
	{
		fr = new JFrame("Product Report");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Product Report");
 		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		ImageIcon ic = new ImageIcon("a11.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);

		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);
			
		Font f2;
		f2=new Font("CAMBRIA",Font.BOLD,14);		

/*********************************** ADDING LABEL & TEXTBOXES ******************************************/

		/*lblFromDate = new JLabel("FROM DATE :");
		lblFromDate.setBounds(220,150,120,25);
		lblFromDate.setFont(f1);

		txtFromDate = new JTextField();
		txtFromDate.setBounds(320,150,100,25);
		txtFromDate.setFont(f1);
			
		lblToDate = new JLabel("TO DATE :");
		lblToDate.setBounds(470,150,100,25);
		lblToDate.setFont(f1);
			
		txtToDate = new JTextField();
		txtToDate.setBounds(550,150,100,25);
		txtToDate.setFont(f1);*/
			
		btnPrint = new JButton("[ SHOW ]");
		btnPrint.setBounds(450,200,120,25);
		btnPrint.addActionListener(this);
		btnPrint.setFont(f1);
			
/*********************************** TABLE SETTING *****************************************************/

		model = new DefaultTableModel();
		model.addColumn("PRODUCT ID");
	        	model.addColumn("PRODUCT NAME");
	        	model.addColumn("PRICE IN KG");
	        	/*model.addColumn("SALE QTY");
	        	model.addColumn("BALANCE QTY");*/

		etab = new JTable(model);

		JScrollPane jp = new JScrollPane(etab , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jp.setBounds(180,250,680,300);
	        
	       	etab.setFont(f2);

/*********************************** ADDING OF CONTENT PANE *********************************************/

	//	fr.getContentPane().add(lblFromDate);
	//	fr.getContentPane().add(txtFromDate);
	//	fr.getContentPane().add(lblToDate);
	//	fr.getContentPane().add(txtToDate);
		fr.getContentPane().add(btnPrint);
		fr.getContentPane().add(jp);
		fr.getContentPane().add(pic1);

		fr.setSize(1024,768);
		fr.setVisible(true);
	}

/*******************************************************************************************************/


	void Show()
   	{
		try
		{
			Class.forName("org.postgresql.Driver");
   			Connection con=DriverManager.getConnection(url ,"postgres","roshani");
                                     	Statement  st = con.createStatement();
        			String sql = "select * from product";
   			ResultSet rs = st.executeQuery(sql);
   			int i = 1 ;
   			int r = 0;
   			while(rs.next())
   			{
   				//int CSrNo=i++;
				int productid=(rs.getInt("pid"));
   				String pName=(rs.getString("p_name"));
				int price=(rs.getInt("price_in_kg"));	
   				/*int iqty =(rs.getInt("iqty"));
   				int sqty =(rs.getInt("sqty"));
   				int bal = iqty - sqty;*/
   				model.insertRow(r++,new Object[]  { new Integer(productid) , new String(pName),new Integer(price)  
				/*new Integer(iqty),new Integer(sqty) ,new Integer(bal)*/   });
   			}
   			rs.close();
		}
		catch(SQLException e1)
   		{
   			JOptionPane.showMessageDialog(null,e1.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
   		}
   		catch(ClassNotFoundException e1)
   		{
   			JOptionPane.showMessageDialog(null,e1.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
   		}
	}

	public void actionPerformed(ActionEvent ae)
	{
		Show();
 	}
	public static void main(String arg[])
	{
		new ProductReport();
	}
}
