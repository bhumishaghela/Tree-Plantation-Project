import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.* ;

public class OutstandingFarmer implements FocusListener,ActionListener
{

	String url="jdbc:postgresql://localhost/cottoncounty";
	String sql = "";
	String sql1 = "";
	Connection con;
	Statement st ;
	ResultSet rs ;

	private JFrame fr;
	private JLabel lblFarmerName,lblFarmerID,lblBalance;
	private JTextField txtFarmerName,txtFarmerID,txtBalance;
	private JButton btnSearch;
	private DefaultTableModel model,model1;
	private JComboBox dblb1;
	private JTable  etab,etab1;

	OutstandingFarmer()
	{

		fr = new JFrame("OutStanding Farmer Report");
		fr.getContentPane().setLayout(null);
		fr.setTitle("OutStanding Farmer Report");
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

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
		ImageIcon ic = new ImageIcon("Outfarmer.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);
		
		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);
			
		Font f2;
		f2=new Font("CAMBRIA",Font.BOLD,14);


/*********************************** ADDING LABEL & TEXTBOXES ******************************************/

		lblFarmerName = new JLabel("FARMER NAME");
		lblFarmerName.setBounds(160,250,130,25);
		lblFarmerName.setFont(f1);

		txtFarmerName = new JTextField();
		txtFarmerName.setBounds(310,250,180,25);
		txtFarmerName.setFont(f1);
		
		lblFarmerID = new JLabel("FARMER ID");
		lblFarmerID.setBounds(160,180,130,25);
		lblFarmerID.setFont(f1);
		
		txtFarmerID = new JTextField();
		txtFarmerID.setBounds(310,180,50,25);
		txtFarmerID.setFont(f1);

		lblBalance = new JLabel("BALANCE REMAINING");
		lblBalance.setBounds(520,600,180,25);
		lblBalance.setFont(f1);
		
		txtBalance = new JTextField();
		txtBalance.setBounds(710,600,100,25);
		txtBalance.setFont(f1);
		
		btnSearch = new JButton(" [SHOW] ");
		btnSearch.setBounds(710,180,100,25);
		btnSearch.addActionListener(this);
		btnSearch.setFont(f1);

/*********************************** TABLE SETTING *****************************************************/

		  model = new DefaultTableModel();
	        
		model.addColumn("INWARD NO");
	      	model.addColumn("PAYMENT DATE");
	      	model.addColumn("PAYMENT");
	      	model.addColumn("ADVANCE AMOUNT");
	      	model.addColumn("TOTAL AMOUNT");

		etab = new JTable(model);

		JScrollPane jp = new JScrollPane(etab , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	      	jp.setBounds(150,300,700,250);

		etab.setFont(f2);

/*********************************** ADDING OF CONTENT PANE *********************************************/

		fr.getContentPane().add(lblFarmerName);
		fr.getContentPane().add(txtFarmerName);
		fr.getContentPane().add(lblFarmerID);
		fr.getContentPane().add(txtFarmerID);
		fr.getContentPane().add(lblBalance);
		fr.getContentPane().add(txtBalance);
		fr.getContentPane().add(btnSearch);
		fr.getContentPane().add(jp);
		fr.getContentPane().add(pic1);
		fr.setSize(1024,768);
		fr.setVisible(true);
	}

/*******************************************************************************************************/
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnSearch)
             		{
               			try
               			{
               				for(int i=model.getRowCount()-1;i>=0;i--)
				{
					model.removeRow(i);
				}
            				sql = "select * from FarmerReceipt where FarmerID = "+ txtFarmerID.getText() +" ";
		            		ResultSet rsp = st.executeQuery(sql);
				int i=0;
        		    		int r=0;
             				while(rsp.next())
                    			{
                    				txtFarmerName.setText( "" +rsp.getString("FarmerName"));
		 			txtBalance.setText( "" +rsp.getString("BalanceRemained"));
                  				model.insertRow(r++,new Object[] { rsp.getInt(3) ,rsp.getString(4),rsp.getString(7),rsp.getString(9),rsp.getString(8) }  );
	                		}
                 			rsp.close();
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
	
	public void focusLost(FocusEvent fe)
	{
		if(fe.getSource() == dblb1)
	   	{
    			try
    			{
				for(int i=model.getRowCount()-1;i>=0;i--)
				{
					model.removeRow(i);
				}
				sql = "select * from FarmerRecord where FarmerName = '" + dblb1.getSelectedItem()  + "'";
				ResultSet rsvm = st.executeQuery(sql);
				while(rsvm.next())
    				{
					txtFarmerID.setText( "" +rsvm.getInt("FarmerID"));
				}
				rsvm.close();
    			}
			catch(SQLException e1)
			{
		 		JOptionPane.showMessageDialog(null,e1.toString(), "SQL Error ",JOptionPane.ERROR_MESSAGE  ) ;
	  		}
    		}
    	}

	public void focusGained(FocusEvent fe)
      	{
       	}


/*******************************************************************************************************/

	public static void main(String arg[])
	{
		new OutstandingFarmer();
	}
}
