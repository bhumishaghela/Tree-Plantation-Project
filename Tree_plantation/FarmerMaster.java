import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import java.io.*;

public class FarmerMaster implements ActionListener
{

	String url="jdbc:postgresql://localhost/tree_plantation";
	String sql = "";
	Connection con;
	Statement st ;
	ResultSet rs ;


	private JFrame fr;
	private JLabel lblFarmerID,lblFarmerName,lblVillageName,lblVillageID,lblVillageCity,lblAddress,lblContact,lblBirthDate,lblGender,lbldate;
	private JTextField txtFarmerID,txtFarmerName,txtVillageName,txtVillageID,txtVillageCity,txtContact,txtBirthDate;
	private JTextArea txtAddress;
	private JButton btnSubmit,btnNew,btnReset,btnExit;
	private JComboBox dblb1,dblb2;

	FarmerMaster()
	{
		fr = new JFrame("Farmer Creation");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Entry Form For New Farmer Creation");
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
		ImageIcon ic = new ImageIcon("farmer.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);
		
		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);

/*********************************** ADDING LABEL & TEXTBOXES ******************************************/

		lblFarmerName = new JLabel("DONOR NAME : ");
		lblFarmerName.setBounds(50,150,140,25);
		lblFarmerName.setFont(f1);

		txtFarmerName = new JTextField();
		txtFarmerName.setBounds(220,150,200,25);
		txtFarmerName.setFont(f1);
		
		lblFarmerID = new JLabel("DONOR ID : ");
		lblFarmerID.setBounds(500,150,100,25);
		lblFarmerID.setFont(f1);
		
		txtFarmerID = new JTextField();
		txtFarmerID.setBounds(630,150,50,25);
		txtFarmerID.setFont(f1);
		txtFarmerID.setFont(f1);

		/*lblVillageName = new JLabel(" VILLAGE NAME :");
		lblVillageName.setBounds(50,250,140,25);
		lblVillageName.setFont(f1);
		
		dblb1 = new JComboBox();													//DROPDOWN LIST-BOX
		dblb1.setBounds(220,250,150,25);
		dblb1.addFocusListener(this);
		dblb1.setFont(f1);*/

	/*	try
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
	   	}*/

		lblVillageID = new JLabel("DONATION AMOUNT :");
		lblVillageID.setBounds(500,250,100,25);
		lblVillageID.setFont(f1);
		
		txtVillageID = new JTextField();
		txtVillageID.setBounds(630,250,50,25);
		txtVillageID.setFont(f1);
		
		/*lblVillageCity = new JLabel(" VILLAGE CITY :");
		lblVillageCity.setBounds(50,350,140,25);
		lblVillageCity.setFont(f1);

		txtVillageCity = new JTextField();
		txtVillageCity.setBounds(220,350,120,25);
		txtVillageCity.setFont(f1);*/

		lblAddress = new JLabel(" ADDRESS :");
		lblAddress.setBounds(50,450,100,25);
		lblAddress.setFont(f1);
		
		txtAddress = new JTextArea();
		txtAddress.setBounds(180,450,200,60);
		txtAddress.setFont(f1);

		lblBirthDate = new JLabel("DONATION DATE :");
		lblBirthDate.setBounds(500,450,100,25);
		lblBirthDate.setFont(f1);
		
		txtBirthDate = new JTextField();
		txtBirthDate.setBounds(630,450,100,25);
        		txtBirthDate.setFont(f1);    
        
       		lbldate = new JLabel("(mm/dd/yyyy)");
        		lbldate.setBounds(730,450,130,25);
		lbldate.setFont(f1);

		lblContact = new JLabel(" CONTACT :");
		lblContact.setBounds(50,550,100,25);
		lblContact.setFont(f1);
		
		txtContact = new JTextField();
		txtContact.setBounds(220,550,120,25);
		txtContact.setFont(f1);
		
		lblGender = new JLabel("GENDER :");
		lblGender.setBounds(500,550,100,25);
		lblGender.setFont(f1);

		dblb2 = new JComboBox();
		dblb2.addItem("Male");
		dblb2.addItem("Female");
		dblb2.setBounds(630,550,100,25);
		dblb2.setFont(f1);

		JScrollPane jp = new JScrollPane(txtAddress , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jp.setBounds(220,450,200,60);

/*********************************** BUTTON ************************************************************/

		btnNew = new JButton("[NEW]");
		btnNew.setBounds(690,150,100,25);
		btnNew.addActionListener(this);
		btnNew.setFont(f1);
		
		btnSubmit = new JButton("[SUBMIT]");
		btnSubmit.setBounds(160,620,110,25);
		btnSubmit.addActionListener(this);
		btnSubmit.setFont(f1);
		
		btnReset = new JButton("[RESET]");
		btnReset.setBounds(280,620,110,25);
		btnReset.addActionListener(this);
		btnReset.setFont(f1);

		btnExit = new JButton("[EXIT]");
		btnExit.setBounds(400,620,110,25);
		btnExit.addActionListener(this);
		btnExit.setFont(f1);
		
		/*btnPurchase = new JButton("[PURCHASE]");
		btnPurchase.setBounds(20,620,130,25);
		btnPurchase.addActionListener(this);
		btnPurchase.setFont(f1);



/*********************************** ADDING OF CONTENT PANE ********************************************/

		fr.getContentPane().add(lblFarmerID);
		fr.getContentPane().add(txtFarmerID);
		fr.getContentPane().add(lblFarmerName);
		fr.getContentPane().add(txtFarmerName);
		fr.getContentPane().add(lblVillageID);
		fr.getContentPane().add(txtVillageID);
		fr.getContentPane().add(lblVillageName);
		fr.getContentPane().add(dblb1);
		/*fr.getContentPane().add(lblVillageCity);
		fr.getContentPane().add(txtVillageCity);*/
		fr.getContentPane().add(lblAddress);
		fr.getContentPane().add(jp);
		fr.getContentPane().add(lblBirthDate);
		fr.getContentPane().add(txtBirthDate);
        		fr.getContentPane().add(lbldate);
		fr.getContentPane().add(lblContact);
		fr.getContentPane().add(txtContact);
		fr.getContentPane().add(lblGender);
		fr.getContentPane().add(dblb2);
		fr.getContentPane().add(btnSubmit);
		fr.getContentPane().add(btnNew);
		fr.getContentPane().add(btnExit);
		fr.getContentPane().add(btnReset);
		//fr.getContentPane().add(btnPurchase);
        		fr.getContentPane().add(pic1);
		fr.setSize(1024,768);
		fr.setVisible(true);
	}

/*******************************************************************************************************/	

	void Reset()
	{
            		txtFarmerID.setText("");
            		txtFarmerName.setText("");
	        	txtVillageID.setText("");
	        //	txtVillageCity.setText("");
	        	txtAddress.setText("");
	        	txtBirthDate.setText("");
	        	txtContact.setText("");
	}

/*******************************************************************************************************/

	public void actionPerformed(ActionEvent e)
	{
		 if(e.getSource() == btnSubmit)
		 {
			try
	         		{
				sql = "insert into donation(did,donor_name,donation_amt,Address,BirthDate,Contact,Gender) values (" + txtFarmerID.getText() + ", '" + txtFarmerName.getText() +"'," + txtVillageID.getText() + ",'" + txtAddress.getText() + "','" + txtBirthDate.getText() + "'," + txtContact.getText() + ",'" + dblb2.getSelectedItem() + "')";
		         		int n = st.executeUpdate(sql);
		         		System.out.println(n + " Record Added in FarmerRecord");
		         		JOptionPane.showMessageDialog(null,"RECORD SUCCESSFULLY SAVED IN FARMER RECORD ! ! !   ", "Confirmation",JOptionPane.INFORMATION_MESSAGE  ) ;
		         		Reset();
		     	}
			catch(SQLException e1)
	    	 	{
				System.out.println("Error " + e1);
		     	}
		 }
		 if(e.getSource() == btnNew)
		 {
			try
			{
				int id = 0;
				ResultSet rsid = st.executeQuery("select max(did) as fid from donation");
				while(rsid.next())
				{
					id = Integer.parseInt(rsid.getString("fid")) + 1;
					System.out.println("");
				}
				txtFarmerID.setText("" + id);
			}
			catch(SQLException e1)
			{
				System.out.println("SQL FarmerID" + e1);
			}
		}
		if(e.getSource() == btnExit)
		{
			fr.setVisible(false);
		             	fr.dispose();
	          	}
		if(e.getSource() == btnReset)
		{
	            		txtFarmerID.setText("");
		   	txtFarmerName.setText("");
			txtVillageID.setText("");
			txtAddress.setText("");
		//	txtVillageCity.setText("");
			txtBirthDate.setText("");
			txtContact.setText("");
		}
		/*if(e.getSource() == btnPurchase)
		{
		            new PurchaseScreen();
		            fr.setVisible(false);
		}*/
	}
	/*public void focusLost(FocusEvent fe)
	{
		if(fe.getSource() == dblb1)
	   	{
    			try
    			{
       				sql = "select * from VillageRecord where VillageName = '" + dblb1.getSelectedItem()  + "'";
        				ResultSet rsvm = st.executeQuery(sql);
				if(rsvm.next())
        				{
					txtVillageID.setText( "" +rsvm.getInt("VillageID"));
					txtVillageCity.setText( "" +rsvm.getString("CityName"));
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
   	}*/

/***********************************************************************/

 	public static void main(String arg[])
	{
		new FarmerMaster();
	}
}
