import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.* ;

public class CorporateReport implements FocusListener,ActionListener
{

	String url="jdbc:postgresql://localhost/tree_plantation";
	String sql = "";
	String sql1 = "";
	Connection con;
	Statement st ;
    	ResultSet rs ;

	private JFrame fr;
	private JLabel lblCorporateName,lblCorporateID;
	private JTextField txtCorporateName,txtCorporateID;
	private JButton btnSearch;
	private DefaultTableModel model,model1;
	private JComboBox dblb1;
	private JTable  etab,etab1;

	CorporateReport()
	{
		fr = new JFrame("Corporate Information");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Corporate Information");
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		try
		{
            			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(url,"postgres","roshani");
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
		ImageIcon ic = new ImageIcon("a13.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);

		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);
			
		Font f2;
		f2=new Font("CAMBRIA",Font.BOLD,14);

		
/*********************************** ADDING LABEL & TEXTBOXES ******************************************/

		lblCorporateName = new JLabel("CORPORATE NAME");
		lblCorporateName.setBounds(200,230,190,25);
		lblCorporateName.setFont(f1);

		txtCorporateName = new JTextField();
		txtCorporateName.setBounds(370,230,180,25);
		txtCorporateName.setFont(f1);
		
		/*try
		{
			ResultSet rsc=st.executeQuery("select * from CustomerBill");
			while(rsc.next())
			{
				dblb1.addItem(rsc.getString("CustomerName"));
			}
			rsc.close();
		}
	      	catch(SQLException e)
		{
		      System.out.println("SQL Combo" + e);
	   	}*/
                     
                     
        		lblCorporateID = new JLabel("CORPORATE ID");
		lblCorporateID.setBounds(190,180,190,25);
        		lblCorporateID.setFont(f1);
            
    	    	txtCorporateID = new JTextField();
		txtCorporateID.setBounds(350,180,50,25);
		txtCorporateID.setFont(f1);
          
        		btnSearch = new JButton(" [SHOW] ");
		btnSearch.setBounds(680,180,100,25);
		btnSearch.addActionListener(this);
		btnSearch.setFont(f1);


/*********************************** TABLE SETTING *****************************************************/

		model = new DefaultTableModel();
	      
	      	model.addColumn("CORPORATE ID");
	     	model.addColumn("CORPORATE NAME");
	      	model.addColumn("INVESTMENT RATE");
	      /*	model.addColumn("PAYMENT TYPE");
	      	model.addColumn("TOTAL AMOUNT");*/

	      	etab = new JTable(model);

		JScrollPane jp = new JScrollPane(etab , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	      	jp.setBounds(150,300,700,250);

		etab.setFont(f2);


/*********************************** ADDING OF CONTENT PANE *********************************************/

		
		fr.getContentPane().add(lblCorporateName);
		fr.getContentPane().add(txtCorporateName);
		fr.getContentPane().add(lblCorporateID);
		fr.getContentPane().add(txtCorporateID);
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
				sql = "select * from corporate where cid = "+ txtCorporateID.getText() +" ";
				ResultSet rsp = st.executeQuery(sql);
				int i=0;
				int r=0;
				while(rsp.next())
			            	{	
		      			txtCorporateName.setText(rsp.getString("c_name"));
					//Int inw=txtSalesNo.getText();
					model.insertRow(r++,new Object[] { rsp.getInt(1) ,rsp.getString(2),rsp.getFloat(3) }  );
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
				sql = "select * from corporate where c_name = '" + dblb1.getSelectedItem()  + "'";
				ResultSet rsvm = st.executeQuery(sql);
				if(rsvm.next())
    				{
					txtCorporateID.setText( "" +rsvm.getInt("cid"));
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
		new CorporateReport();
	}
}
