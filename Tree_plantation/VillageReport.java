import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.* ;


/****************************************************************************************************/

public class VillageReport implements FocusListener,ActionListener
{
	String url="jdbc:postgresql://localhost/cottoncounty";
	String sql = "";
	String sql1 = "";
	Connection con;
	Statement st ;
	ResultSet rs ;
	
	private JFrame fr;
	private JLabel lblVillageName,lblVillageID;
	private JTextField txtVillageName,txtVillageID;
	private JButton btnShow,btnPrint;
   	private JComboBox dblb1;
	private DefaultTableModel model;
	private JTable  etab;
	
/****************************************************************************************************/

	VillageReport()
	{
                	fr = new JFrame("Village Report");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Village Report");
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                	
		ImageIcon ic = new ImageIcon("Reportfarmer.jpg");
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

/*********************************** ADDING LABEL & TEXTBOXES ******************************************/

		lblVillageName = new JLabel("VILLAGE NAME :");
		lblVillageName.setBounds(120,150,120,25);
		lblVillageName.setFont(f1);

		dblb1 = new JComboBox();
		dblb1.setBounds(250,150,140,25);
	            	dblb1.addFocusListener(this);
           		dblb1.setFont(f1);

      		try
	       	{
			ResultSet rsc=st.executeQuery("select * from VillageRecord");
			while(rsc.next())
			{
				dblb1.addItem(rsc.getString("VillageName"));
		           	}
			rsc.close();
		}
	            	catch(SQLException e)
		{
			System.out.println("SQL Combo" + e);
	   	}
		lblVillageID = new JLabel("VILLAGE ID :");
		lblVillageID.setBounds(420,150,100,25);
		lblVillageID.setFont(f1);
		
		txtVillageID = new JTextField();
		txtVillageID.setBounds(530,150,80,25);
		txtVillageID.setFont(f1);

		btnShow = new JButton(" [SHOW ALL] ");
	 	btnShow.setBounds(700,150,150,25);
		btnShow.addActionListener(this);
		btnShow.setFont(f1);
			            
		btnPrint = new JButton(" [PRINT] ");
		btnPrint.setBounds(450,600,120,25);
		btnPrint.addActionListener(this);
		btnPrint.setFont(f1);
			
/*********************************** TABLE SETTING *****************************************************/

		model = new DefaultTableModel();
	      	model.addColumn("FARMER ID");
	      	model.addColumn("FARMER NAME");
	      	model.addColumn("ADDRESS");
	      	model.addColumn("GENDER");
	      	model.addColumn("BIRTHDATE");
		  
		etab = new JTable(model);
		
		JScrollPane jp = new JScrollPane(etab , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	      	jp.setBounds(120,250,780,300);

		etab.setFont(f2);
/*********************************** ADDING OF CONTENT PANE *********************************************/

		fr.getContentPane().add(lblVillageName);
		fr.getContentPane().add(dblb1);
		fr.getContentPane().add(lblVillageID);
		fr.getContentPane().add(txtVillageID);
		fr.getContentPane().add(jp);
         		fr.getContentPane().add(btnShow);
        		// 	fr.getContentPane().add(btnPrint);                              
            		fr.getContentPane().add(pic1);                              
           		fr.setSize(1024,768);
	        	fr.setVisible(true);
	}
            
/*******************************************************************************************************/

	public void actionPerformed(ActionEvent e)
       	{
       		//btnShow	
		if(e.getSource()==btnShow)
             		{
               			try
            			{
            				sql = "select * from FarmerRecord where VillageName = '"+ dblb1.getSelectedItem() +"' ";
            				ResultSet rs = st.executeQuery(sql);
            				int r=0;
            				while(rs.next()) 
                             			{
	               				model.insertRow(r++,new Object[] { rs.getInt(1) ,rs.getString(2),rs.getString(9),rs.getString(6),rs.getString(8) }  );
                			}
                			rs.close();
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
                      	/* //btnPrint
           		if(e.getSource()== btnPrint)
             		{
             			new RptDemo4(etab,"Sachin Developers","Chakan","Customer Details Report", "");
             		}*/
	} 
        
/****************************************************************************************************/
 	
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
				sql = "select * from VillageRecord where VillageName = '" + dblb1.getSelectedItem()  + "'";
				ResultSet rsvm = st.executeQuery(sql);
    				if(rsvm.next())
    				{
					txtVillageID.setText( "" +rsvm.getInt("VillageID"));
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
		new VillageReport();
	}
}
