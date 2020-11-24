import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.* ;
import java.util.*;

public class PurchaseScreen implements ActionListener,FocusListener
{

	String url="jdbc:postgresql://localhost/tree_plantation";
	String sql = "";
	String sql1 = "";
	Connection con;
	Statement st ;
	ResultSet rs ;

	private JFrame fr;
	private JMenuItem mHelp,mExit;
	private JLabel lblFarmerName,lblFarmerID,lblAddress,lblInwardNo,lblInwardDate,lblVillageName,lblSrno,lblProdName,lblQty,lblUnit,lblRate,lblTotal,lblReceiptNo,lblPayType,lblday,lblGrand,lblAddTotal;
	private JTextField txtFarmerName,txtFarmerID,txtInwardNo,txtInwardDate,txtVillageName,txtSrno,txtProdName,txtQty,txtUnit,txtRate,txtTotal,txtReceiptNo,txtPayType,txtGrandTotal,txtAddTotal;
	private JTextArea txtAddress;
	private JComboBox dblb1,dblb2,dblb3;
	private JButton btnAdd,btnSubmit,btnReset,btnExit,btnClear,btnSearch,btnMakeReceipt;
	private DefaultTableModel model;
	private JTable  etab;
	int r=0;

	PurchaseScreen()
	{

		fr = new JFrame("Purchase Screen");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Purchase of Material From Farmer");
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
		ImageIcon ic = new ImageIcon("purchase.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);
		
		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);
		
		Font f2;
		f2=new Font("CAMBRIA",Font.BOLD,14);


/*********************************** ADDING LABEL & TEXTBOXES ******************************************/
            
            
		JMenuBar mb = new JMenuBar();

		JMenu mFile = new JMenu("File");
        		mHelp = new JMenuItem("Help");
        		mHelp.addActionListener(this);
      		mHelp.setFont(f1);

		mExit = new JMenuItem("Exit");
        		mExit.addActionListener(this);
		mExit.setFont(f1);

		mFile.add(mHelp);
        		mFile.add(mExit);
		mFile.setFont(f1);

        		mb.add(mFile);         
            
	/*	lblFarmerName = new JLabel("FARMER NAME :");
		lblFarmerName.setBounds(400,120,120,25);
		lblFarmerName.setFont(f1);

		txtFarmerName = new JTextField();
		txtFarmerName.setBounds(560,120,180,25);
		txtFarmerName.setFont(f1);
		txtFarmerName.setEditable(false);
		
		lblFarmerID = new JLabel("FARMER ID :");
		lblFarmerID.setBounds(50,120,100,25);
		lblFarmerID.setFont(f1);
	
		txtFarmerID = new JTextField();
		txtFarmerID.setBounds(180,120,50,25);
		txtFarmerID.setFont(f1);*/

		lblInwardNo = new JLabel("INWARD NO :");
		lblInwardNo.setBounds(400,300,100,25);
		lblInwardNo.setFont(f1);
	
		txtInwardNo = new JTextField();
		txtInwardNo.setBounds(560,300,50,25);
		txtInwardNo.setFont(f1);

		int id = 0;
		try
		{
			ResultSet rsid = st.executeQuery("select max(price_in_kg) as inwno from product");
			if(rsid.next())
			{
				id = Integer.parseInt(rsid.getString("inwno")) + 1;
				System.out.println("");
			}
		}
		catch(SQLException e)
		{
			System.out.println("SQL InwardNo" + e);
		}
		/*txtInwardNo.setText("" + id);
		txtInwardNo.setEditable(false);
		

		lblAddress = new JLabel("ADDRESS :");
		lblAddress.setBounds(400,200,100,25);
		lblAddress.setFont(f1);

		txtAddress = new JTextArea();
		txtAddress.setBounds(560,200,180,60);
		txtAddress.setFont(f1);
		txtAddress.setEditable(false);

		JScrollPane ad = new JScrollPane(txtAddress , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ad.setBounds(560,200,180,60);
		ad.setFont(f1);*/
		
/*
        		lblReceiptNo = new JLabel("RECEIPT NO :");
		lblReceiptNo.setBounds(50,300,100,25);
		lblReceiptNo.setFont(f1);

		txtReceiptNo = new JTextField();
		txtReceiptNo.setBounds(180,300,60,25);
		txtReceiptNo.setFont(f1);*/

		int re = 0;

		try
		{
			ResultSet rsid = st.executeQuery("select max(receiptno) as rec from purchaserecord");
			if(rsid.next())
			{
				re = Integer.parseInt(rsid.getString("rec")) + 1;
				System.out.println("");
			}
		}
		catch(SQLException e)
		{
			System.out.println("SQL InwardNo" + e);
		}
		txtReceiptNo.setText("" + re);
		txtReceiptNo.setEditable(false);

		lblInwardDate = new JLabel("INWARD DATE :");
		lblInwardDate.setBounds(710,300,150,25);
		lblInwardDate.setFont(f1);
		
		txtInwardDate = new JTextField();
		txtInwardDate.setBounds(850,300,120,25);
		Calendar c = Calendar.getInstance();
		txtInwardDate.setText(c.get(Calendar.MONTH)+1 + "/" + c.get(Calendar.DATE) + "/" + c.get(Calendar.YEAR) );
		txtInwardDate.setFont(f1);
		txtInwardDate.setEditable(false);
		
		lblday = new JLabel("(MM/DD/YYYY)");
		lblday.setBounds(850,280,150,25);
		lblday.setFont(f1);

		lblVillageName = new JLabel("VILLAGE NAME :");
		lblVillageName.setBounds(50,200,130,25);
		lblVillageName.setFont(f1);

		txtVillageName = new JTextField();
		txtVillageName.setBounds(180,200,140,25);
		txtVillageName.setFont(f1);
		txtVillageName.setEditable(false);		
        
		lblProdName = new JLabel("PRODUCT NAME");
		lblProdName.setBounds(100,380,150,25);
		lblProdName.setFont(f1);

		dblb2 = new JComboBox();
		dblb2.setBounds(85,400,150,25);
		dblb2.addFocusListener(this);
		dblb2.setFont(f1);

		try
		{
			ResultSet rsc=st.executeQuery("select * from productrecord");
			while(rsc.next())
			{
				dblb2.addItem(rsc.getString("productname"));
			}
			rsc.close();
		}
		catch(SQLException e)
		{
			System.out.println("SQL Combo" + e);
		}
		lblRate = new JLabel("PRODUCT RATE");
		lblRate.setBounds(310,380,150,25);
		lblRate.setFont(f1);

		txtRate = new JTextField();
		txtRate.setBounds(330,400,70,25);
		txtRate.setFont(f1);
		
		lblUnit = new JLabel("TONNES");
		lblUnit.setBounds(550,380,100,25);
		lblUnit.setFont(f1);

		txtUnit = new JTextField();
		txtUnit.setBounds(555,400,50,25);
		txtUnit.addFocusListener(this);
		txtUnit.setFont(f1);
                                        
		lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(765,380,100,25);
        		lblTotal.setFont(f1);
                                               
		txtTotal = new JTextField();
		txtTotal.setBounds(740,400,100,25);
		txtTotal.setFont(f1);
		
		lblGrand = new JLabel("GRAND TOTAL");
		lblGrand.setBounds(830,560,150,25);
        		lblGrand.setFont(f1);
	
		txtGrandTotal = new JTextField("0");
		txtGrandTotal.setBounds(685,560,120,25);
		txtGrandTotal.setFont(f1);


/*********************************** TABLE SETTING *****************************************************/

	  	model = new DefaultTableModel();

		model.addColumn("PRODUCT NAME");
        		model.addColumn("PRODUCT RATE");
        		model.addColumn("TONNES");
        		model.addColumn("TOTAL");

        		etab = new JTable(model);

		JScrollPane jp = new JScrollPane(etab , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        		jp.setBounds(50,450,850,100);
		
		etab.setFont(f2);        

/*********************************** ADDING OF BUTTONS *************************************************/

		btnAdd = new JButton(" + ");
		btnAdd.setBounds(920,450,70,25);
		btnAdd.addActionListener(this);
		btnAdd.setFont(f1);

		btnClear = new JButton("CLEAR");
		btnClear.setBounds(915,500,90,25);
		btnClear.addActionListener(this);
		btnClear.setFont(f1);

		btnSubmit = new JButton("[SUBMIT]");
		btnSubmit.setBounds(50,620,110,25);
		btnSubmit.addActionListener(this);
		btnSubmit.setFont(f1);

		btnReset =  new JButton("[RESET]");
		btnReset.setBounds(170,620,110,25);
        		btnReset.addActionListener(this);
		btnReset.setFont(f1);

		btnExit = new JButton("[EXIT]");
		btnExit.setBounds(290,620,110,25);
		btnExit.addActionListener(this);
        		btnExit.setFont(f1);
            
       		btnSearch = new JButton("[SEARCH]");
		btnSearch.setBounds(240,120,110,25);
		btnSearch.addActionListener(this);
		btnSearch.setFont(f1);
            
        		btnMakeReceipt = new JButton("[MAKE RECEIPT]");
		btnMakeReceipt.setBounds(410,620,160,25);
		btnMakeReceipt.addActionListener(this);
		btnMakeReceipt.setFont(f1);

/*********************************** ADDING OF CONTENT PANE ********************************************/
		
		fr.setJMenuBar(mb);
		fr.getContentPane().add(lblFarmerName);
		fr.getContentPane().add(txtFarmerName);
		fr.getContentPane().add(lblFarmerID);
		fr.getContentPane().add(txtFarmerID);
		fr.getContentPane().add(lblInwardNo);
		fr.getContentPane().add(txtInwardNo);
		fr.getContentPane().add(lblAddress);
		fr.getContentPane().add(ad);
		fr.getContentPane().add(lblReceiptNo);
		fr.getContentPane().add(txtReceiptNo);
		fr.getContentPane().add(lblInwardDate);
		fr.getContentPane().add(txtInwardDate);
		fr.getContentPane().add(lblday);
		fr.getContentPane().add(lblVillageName);
		fr.getContentPane().add(txtVillageName);
        		fr.getContentPane().add(lblProdName);
		fr.getContentPane().add(dblb2);
		fr.getContentPane().add(lblRate);
		fr.getContentPane().add(txtRate);
		fr.getContentPane().add(lblUnit);
		fr.getContentPane().add(txtUnit);
		fr.getContentPane().add(lblTotal);
		fr.getContentPane().add(txtTotal);
		fr.getContentPane().add(jp);
		fr.getContentPane().add(btnAdd);
		fr.getContentPane().add(btnClear);
		fr.getContentPane().add(btnSubmit);
		fr.getContentPane().add(btnReset);
		fr.getContentPane().add(btnExit);
        		fr.getContentPane().add(btnSearch);
        		fr.getContentPane().add(btnMakeReceipt);
        		fr.getContentPane().add(lblGrand);
        		fr.getContentPane().add(txtGrandTotal);
               		fr.getContentPane().add(pic1);
        		fr.setSize(1024,768);
		fr.setVisible(true);
	}
	void Reset1()
	{
		txtRate.setText("");
		txtUnit.setText("");
		txtTotal.setText("");
		txtGrandTotal.setText("0");
	}
	void Reset()
	{
		txtRate.setText("");
		txtUnit.setText("");
		txtTotal.setText("");
		txtGrandTotal.setText("0");
		txtFarmerID.setText("");
		txtFarmerName.setText("");
		txtReceiptNo.setText("");
		txtInwardNo.setText("");
		txtInwardDate.setText("");
		txtAddress.setText("");
		txtVillageName.setText("");
	}


/*******************************************************************************************************/

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnAdd)
		{
			try
			{
             				if(dblb2.getSelectedItem().equals(""))
              				{
					JOptionPane.showMessageDialog(null,"Select The Type of Product ! ", 					"Confirmation",JOptionPane.ERROR_MESSAGE  ) ;
					return ;
				}
			     	if(txtRate.getText().equals(""))
				{
                				return ;
				}
				if(txtUnit.getText().equals(""))
				{
					JOptionPane.showMessageDialog(null,"Please Enter how much Tonnes of 				Cotton you required !   ", "Confirmation",JOptionPane.ERROR_MESSAGE  ) ;
					return ;
				}
				if(txtTotal.getText().equals(""))
				{
                				return ;
				}
				double rt = Double.parseDouble(txtRate.getText());
				double un = Double.parseDouble(txtUnit.getText());
				double to = Double.parseDouble(txtTotal.getText());
	
		    		model.insertRow(r++,new Object[]   {dblb2.getSelectedItem(),new Double(rt),new 				Double(un),new Double(to) });
    		
    				double Gtot = Double.parseDouble(txtGrandTotal.getText());
    				Gtot = Gtot + to;
	    			txtGrandTotal.setText(""+Gtot);
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null,e1.toString(), 			"Confirmation",JOptionPane.ERROR_MESSAGE  ) ;
			}
		}
	                if(e.getSource()==mExit)
            		{
           		     	fr.setVisible(false);
			fr.dispose();
		}
	                if(e.getSource() == btnSearch)
            		{
            			try
            			{
            				sql = "select * from farmerrecord where farmerid=" + txtFarmerID.getText() + "";
				ResultSet rs = st.executeQuery(sql);
            				if(rs.next()) 
               		              		{
                  				txtFarmerName.setText(rs.getString("farmername"));
                  				txtAddress.setText(rs.getString("address"));
                  				txtVillageName.setText(rs.getString("villagename"));
                 			}
                            		}
			catch(SQLException e1)
			{
				 JOptionPane.showMessageDialog(null,e1.toString(), 				"Error11",JOptionPane.ERROR_MESSAGE  ) ;
			}  
	             		catch(NumberFormatException e1)
			{
				JOptionPane.showMessageDialog(null,e1.toString(), 				"Error12",JOptionPane.ERROR_MESSAGE  ) ;
			}  
           		}
		if(e.getSource() == btnClear)
		{
			r=0;
			try
			{
				for(int i=model.getRowCount()-1;i>=0;i--)
				{
					model.removeRow(i);
					Reset1();
				}
			}
			catch(Exception e1)
			{
				JOptionPane.showMessageDialog(null,e1.toString(),"Error13",JOptionPane.ERROR_MESSAGE  ) ;
			}
		}
		if(e.getSource() == btnExit)
		{
			fr.setVisible(false);
			fr.dispose();
		}
		if(e.getSource() == btnReset)
		{
			txtRate.setText("");
			txtUnit.setText("");
			txtTotal.setText("");
			txtGrandTotal.setText("0");
			txtFarmerID.setText("");
			txtFarmerName.setText("");
			txtReceiptNo.setText("");
			txtInwardNo.setText("");
			txtInwardDate.setText("");
			txtAddress.setText("");
			txtVillageName.setText("");
		}
		if(e.getSource() == btnMakeReceipt)
		{
			fr.setVisible(false);
	               	 	new FarmerReceipt();
		}
		if(e.getSource() == btnSubmit)
		{
			try
		   	{
				 for (int i=0;i<model.getRowCount();i++)
				 {
sql1 = "Insert into purchaserecord(inwardno,inwarddate,receiptno,farmername,farmerid,address,villagename,total) values (" + txtInwardNo.getText() + ",'" + txtInwardDate.getText() + "'," + txtReceiptNo.getText() + ",'" + txtFarmerName.getText() + "'," + txtFarmerID.getText() + ",'" + txtAddress.getText() + "','" + txtVillageName.getText() + "','" + txtGrandTotal.getText() + "')";
					//System.out.println(sql1);
					System.out.println("Other Record Added in Purchase Record");
					st.executeUpdate(sql1);


					sql="Insert into purchasedetails(productname,productrate,unitkg,total,inwardno,receiptno) values ('" + model.getValueAt(i,0) + "'," + model.getValueAt(i,1) + "," + model.getValueAt(i,2) + "," + model.getValueAt(i,3) + "," + txtInwardNo.getText() + "," + txtReceiptNo.getText() + ")";
					st.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Saved in PurchaseRecord with Details 				", "Successfully Done",JOptionPane.INFORMATION_MESSAGE  ) ;
					//System.out.println(sql);
					System.out.println("Table Record Added in Purchase Details");
	
					
					Reset();
				}
			}
			catch(SQLException e2)
			{
				System.out.println("Err "  + e2 );
			}
			catch(ArrayIndexOutOfBoundsException e2)
			{
				JOptionPane.showMessageDialog(null,e2.toString(), 			"Error",JOptionPane.ERROR_MESSAGE  ) ;
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
       			sql = "select * from farmerrecord where farmername ='"+dblb1.getSelectedItem()+"'";
	        			ResultSet rsvm = st.executeQuery(sql);
				if(rsvm.next())
	        			{
					txtFarmerID.setText( "" +rsvm.getInt("farmerid"));
					txtAddress.setText( "" +rsvm.getString("address"));
					txtVillageName.setText( "" +rsvm.getString("villagename"));
				}
				rsvm.close();
			}
			catch(SQLException e1)
			{
			 	JOptionPane.showMessageDialog(null,e1.toString(), "SQL Error 	",JOptionPane.ERROR_MESSAGE  ) ;
		  	}
		}
		if(fe.getSource() == dblb2)   // dblb2
		{
			try
		    	{
		       		sql = "select * from productrecord where productname = '" + dblb2.getSelectedItem()  	+ "'";
		        		ResultSet rsvm = st.executeQuery(sql);
				if(rsvm.next())
				{
					txtRate.setText("" +rsvm.getInt("Rate"));
				}
				rsvm.close();
			}
			catch(SQLException e1)
			{
			 	JOptionPane.showMessageDialog(null,e1.toString(), "SQL Error11 			",JOptionPane.ERROR_MESSAGE  ) ;
		  	}
		}
		if(fe.getSource()==txtUnit)
	    	{
	    		try
	       		{
		  	 	int rate = Integer.parseInt(txtRate.getText());
		   		int unit = Integer.parseInt(txtUnit.getText());
		   		txtTotal.setText( ""+(rate*unit));
	       		}
	       		catch(NumberFormatException e1)
		 	{
				JOptionPane.showMessageDialog(null,e1.toString(), "SQL Error12 	",JOptionPane.ERROR_MESSAGE  ) ;
		 	}
		}
	}
	public void focusGained(FocusEvent fe)
	{
	}

/*******************************************************************************************************/

	public static void main(String arg[])
	{
		new PurchaseScreen();
	}
}










