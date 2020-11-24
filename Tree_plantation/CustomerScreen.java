import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;
import javax.swing.table.* ;
import java.util.*;

public class CustomerScreen implements ActionListener,FocusListener
{
	String url="jdbc:postgresql://localhost/cottoncounty";
	String sql = "";
	String sql1 = "";
	Connection con;
	Statement st ;
	ResultSet rs ;

	private JFrame fr;
	private JMenuItem mHelp,mExit;
	private JLabel lblCustomerName,lblType,lblAddress,lblBillNo,lblCustomerID,lblBillDate,lblPaymentmode,lblSrno,lblProdName,lblUnit,lblRate,lblVat,lblTotal,lblSaleNo,lblInwardDate,lbldate,lblGrandTotal;
	private JTextField txtCustomerName,txtType,txtBillNo,txtCustomerID,txtBillDate,txtVillageName,txtSrno,txtProdName,txtUnit,txtRate,txtTotal,txtVat,txtSaleNo,txtInwardDate,txtGrandTotal;
	private JTextArea txtAddress;
	private JComboBox dblb1,dblb2,dblb3;
	private JButton btnAdd,btnClear,btnSubmit,btnReset,btnExit,btnSearch,btnMakeBill;
	private DefaultTableModel model;
	private JTable  etab;
	int r=0;

	CustomerScreen()
	{
		fr = new JFrame("Customer Screen");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Sale of Material to Customer");
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
         
		ImageIcon ic = new ImageIcon("sales.jpg");
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

       		mb.add(mFile);
        		mFile.setFont(f1);         

		lblCustomerName = new JLabel("CUSTOMER NAME");
		lblCustomerName.setBounds(400,120,170,25);
		lblCustomerName.setFont(f1);

		txtCustomerName = new JTextField();
		txtCustomerName.setBounds(560,120,160,25);
		txtCustomerName.setFont(f1);
		txtCustomerName.setEditable(false);
		
		lblCustomerID = new JLabel("CUSTOMER ID");
		lblCustomerID.setBounds(50,120,140,25);
		lblCustomerID.setFont(f1);
		
		txtCustomerID = new JTextField();
		txtCustomerID.setBounds(180,120,50,25);
		txtCustomerID.setFont(f1);
		
		lblSaleNo = new JLabel("SALE NO");
		lblSaleNo.setBounds(400,300,100,25);
		lblSaleNo.setFont(f1);

		txtSaleNo = new JTextField();
		txtSaleNo.setBounds(560,300,70,25);
		txtSaleNo.setFont(f1);
	
		int inw = 0;
		try
		{
			 ResultSet rsid = st.executeQuery("select max(SalesNo) as inwd from SalesRecord");
			 if(rsid.next())
			{
				inw = rsid.getInt("inwd") + 1;
				System.out.println("");
			}
		}
		catch(SQLException e)
		{
			System.out.println("SQL SrNo" + e);
		}
		txtSaleNo.setText("" + inw);
		txtSaleNo.setEditable(false);

		lblAddress = new JLabel("ADDRESS");
		lblAddress.setBounds(400,200,100,25);
		lblAddress.setFont(f1);
		
		txtAddress = new JTextArea();
		txtAddress.setBounds(560,200,180,60);
		txtAddress.setFont(f1);
		txtAddress.setEditable(false);		
		
		JScrollPane ad = new JScrollPane(txtAddress , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		ad.setBounds(560,200,180,60);
		ad.setFont(f1);
		
		lblType = new JLabel("TYPE");
		lblType.setBounds(50,200,150,25);
		lblType.setFont(f1);
		
		txtType = new JTextField();
		txtType.setBounds(180,200,80,25);
		txtType.setFont(f1);
		txtType.setEditable(false);
		                                             
		lblBillDate = new JLabel("BILL DATE");
		lblBillDate.setBounds(710,300,150,25);
		lblBillDate.setFont(f1);

		txtBillDate = new JTextField();
		txtBillDate.setBounds(850,300,120,25);
		Calendar c = Calendar.getInstance();
		txtBillDate.setText(c.get(Calendar.MONTH)+1 + "/" + c.get(Calendar.DATE) + "/" + c.get(Calendar.YEAR) );
        		txtBillDate.setFont(f1);
		txtBillDate.setEditable(false);        
            
        		lbldate = new JLabel("(MM/DD/YYYY)");
		lbldate.setBounds(850,280,150,25);
		lbldate.setFont(f1);

		lblBillNo = new JLabel("BILL NO");
		lblBillNo.setBounds(50,300,100,25);
		lblBillNo.setFont(f1);

		txtBillNo = new JTextField();
		txtBillNo.setBounds(180,300,80,25);
		txtBillNo.setFont(f1);
		txtBillNo.setEditable(false);

		int bi = 0;
		try
		{
			ResultSet rsid = st.executeQuery("select max(BillNo) as bill from SalesRecord");
			if(rsid.next())
			{
				 bi = rsid.getInt("bill") + 1;
				 System.out.println("");
			}
		}
		catch(SQLException e)
		{
			System.out.println("SQL SrNo" + e);
		}
		txtBillNo.setText("" + bi);

		lblProdName = new JLabel("PRODUCT NAME");
		lblProdName.setBounds(60,380,150,25);
		lblProdName.setFont(f1);

		dblb2 = new JComboBox();
		dblb2.setBounds(65,400,150,25);
		dblb2.addFocusListener(this);
		dblb2.setFont(f1);
		
		try
		{
			ResultSet rsc=st.executeQuery("select * from ProductRecord");
			while(rsc.next())
			{
				dblb2.addItem(rsc.getString("ProductName"));
			}
			rsc.close();
		}
		catch(SQLException e)
		{
			System.out.println("SQL Combo" + e);
		}
		lblRate = new JLabel("PRODUCT RATE");
		lblRate.setBounds(240,380,150,25);
		lblRate.setFont(f1);
		
		txtRate = new JTextField();
		txtRate.setBounds(265,400,70,25);
		txtRate.setFont(f1);
		
		lblUnit = new JLabel("TONNES");
		lblUnit.setBounds(445,380,100,25);
		lblUnit.setFont(f1);
		
		txtUnit = new JTextField();
		txtUnit.setBounds(450,400,50,25);
		txtUnit.setFont(f1);
		txtUnit.addFocusListener(this);
		
		lblVat = new JLabel("VAT %");
		lblVat.setBounds(625,380,100,25);
		lblVat.setFont(f1);
		
		txtVat = new JTextField();
		txtVat.setBounds(615,400,70,25);
		//txtVat.addFocusListener(this);
		txtVat.setFont(f1);
		
		lblTotal = new JLabel("TOTAL");
		lblTotal.setBounds(795,380,100,25);
		lblTotal.setFont(f1);
		
		txtTotal = new JTextField();
		txtTotal.setBounds(765,400,100,25);
		txtTotal.setFont(f1);
		
		lblGrandTotal = new JLabel("GRAND TOTAL");
		lblGrandTotal.setBounds(850,570,160,25);
		lblGrandTotal.setFont(f1);
		
		txtGrandTotal = new JTextField("0");
		txtGrandTotal.setBounds(725,570,100,25);
		txtGrandTotal.setFont(f1);

/*********************************** TABLE SETTING *****************************************************/

		model = new DefaultTableModel();

		model.addColumn("PRODUCT NAME");
        		model.addColumn("PRODUCT RATE");
        		model.addColumn("TONNES");
        		model.addColumn("VAT %");
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
		btnSubmit.setBounds(50,620,130,25);
		btnSubmit.addActionListener(this);
		btnSubmit.setFont(f1);
		
		btnReset =  new JButton("[RESET]");
		btnReset.setBounds(200,620,110,25);
		btnReset.addActionListener(this);
		btnReset.setFont(f1);
		
		btnExit = new JButton("[EXIT]");
		btnExit.setBounds(320,620,110,25);
		btnExit.addActionListener(this);
		btnExit.setFont(f1);
            
        		btnSearch = new JButton("[SEARCH]");
		btnSearch.setBounds(240,120,130,25);
		btnSearch.addActionListener(this);
       		btnSearch.setFont(f1);
            
        		btnMakeBill = new JButton("[MAKE BILL]");
		btnMakeBill.setBounds(450,620,150,25);
		btnMakeBill.addActionListener(this);
		btnMakeBill.setFont(f1);

/*********************************** ADDING OF CONTENT PANE ********************************************/

		fr.setJMenuBar(mb);
		fr.getContentPane().add(lblCustomerName);
		fr.getContentPane().add(txtCustomerName);
		fr.getContentPane().add(lblCustomerID);
		fr.getContentPane().add(txtCustomerID);
		fr.getContentPane().add(lblSaleNo);
		fr.getContentPane().add(txtSaleNo);
		fr.getContentPane().add(lblAddress);
		fr.getContentPane().add(ad);
		fr.getContentPane().add(lblType);
		fr.getContentPane().add(txtType);
		fr.getContentPane().add(lblBillDate);
		fr.getContentPane().add(txtBillDate);
		fr.getContentPane().add(lblBillNo);
		fr.getContentPane().add(txtBillNo);
		fr.getContentPane().add(lblProdName);
		fr.getContentPane().add(dblb2);
		fr.getContentPane().add(lblRate);
		fr.getContentPane().add(txtRate);
		fr.getContentPane().add(lblUnit);
		fr.getContentPane().add(txtUnit);
		fr.getContentPane().add(lblTotal);
		fr.getContentPane().add(txtTotal);
		fr.getContentPane().add(lblVat);
		fr.getContentPane().add(txtVat);
		fr.getContentPane().add(jp);
        		fr.getContentPane().add(lbldate);
		fr.getContentPane().add(btnAdd);
		fr.getContentPane().add(btnClear);
		fr.getContentPane().add(btnSubmit);
		fr.getContentPane().add(btnReset);
		fr.getContentPane().add(btnExit);
        		fr.getContentPane().add(btnSearch);
        		fr.getContentPane().add(btnMakeBill);
        		fr.getContentPane().add(lblGrandTotal);
        		fr.getContentPane().add(txtGrandTotal);
     	   	fr.getContentPane().add(pic1);
     
		fr.setSize(1024,768);
		fr.setVisible(true);
	}
	void Reset1()
	{
		txtRate.setText("");
		txtUnit.setText("");
		txtVat.setText("");
		txtTotal.setText("");
		txtGrandTotal.setText("0");
	
	}
	void Reset()
	{
		txtRate.setText("");
		txtUnit.setText("");
		txtVat.setText("");
		txtTotal.setText("");
		txtGrandTotal.setText("0");
		txtCustomerID.setText("");
		txtCustomerName.setText("");
		txtBillNo.setText("");
		txtSaleNo.setText("");
		txtBillDate.setText("");
		txtType.setText("");
		txtAddress.setText("");
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
                  				return ;
			 	}
			 	if(txtRate.getText().equals(""))
			 	{
			      		return ;
			 	}
		     		if(txtUnit.getText().equals(""))
			 	{
					JOptionPane.showMessageDialog(null,"Please Enter how much Tonnes of Cotton you required !   ", "Confirmation",JOptionPane.ERROR_MESSAGE  ) ;
				  	return ;
			 	}
			 	if(txtVat.getText().equals(""))
			 	{
					JOptionPane.showMessageDialog(null,"Please Enter the VAT on Selected Product!   ", "Confirmation",JOptionPane.ERROR_MESSAGE  ) ;
					return ;
			 	}
				if(txtTotal.getText().equals(""))
				{
					return ;
			 	}
				double rt = Double.parseDouble(txtRate.getText());
				double un = Double.parseDouble(txtUnit.getText());
				double vt = Double.parseDouble(txtVat.getText());
				double to = Double.parseDouble(txtTotal.getText());

				model.insertRow(r++,new Object[]   {dblb2.getSelectedItem(), new Double(rt),new Double(un),new Double(vt),new Double(to) });
				
				double Gtot = Double.parseDouble(txtGrandTotal.getText());
	    			Gtot = Gtot + to;
    				txtGrandTotal.setText(""+Gtot);
    				vt = rt*un*0.04;
    				txtTotal.setText(""+to);
				}
				catch(Exception e1)
			     	{
				              JOptionPane.showMessageDialog(null,e1.toString(), "Confirmation",JOptionPane.ERROR_MESSAGE  ) ;
				}
			}
			if(e.getSource() == btnMakeBill)
            			{
		  		fr.setVisible(false);
			                new CustomerBill();
            			}
                       		if(e.getSource()==mExit)
            			{
                			fr.setVisible(false);
			    	fr.dispose();
			}
			if(e.getSource() == btnClear)
			{
				r=0;
				try
				{
					for(int i=model.getRowCount()-1;i>=0;i--)
					{
						Reset1();
						model.removeRow(i);
						Reset1();
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
				}
			}
			if(e.getSource() == btnExit)
			{
				fr.setVisible(false);
				fr.dispose();
			}
            			if(e.getSource() == btnSearch)
            			{
            				try
            				{
					sql = "select * from CustomerRecord where CustomerID=" + txtCustomerID.getText() + "";
					ResultSet rs = st.executeQuery(sql);
					while(rs.next()) 
              					{
            			      			txtCustomerName.setText(rs.getString("CustomerName"));
                  					txtAddress.setText(rs.getString("Address"));
                  					txtType.setText(rs.getString("Type"));
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
             			if(e.getSource() == btnReset)
			{	
				txtCustomerID.setText(""); 
				txtCustomerName.setText("");
                  			txtAddress.setText("");
                  			txtType.setText("");
                 			// txtSaleNo.setText("");
                 			// txtBillDate.setText("");
                			//  txtBillNo.setText("");
                  			txtRate.setText("");
				txtUnit.setText("");
				txtVat.setText("");
			      	txtTotal.setText("");
	 			txtGrandTotal.setText("0");
	 			  
	 			r=0;
				try
				{
					for(int i=model.getRowCount()-1;i>=0;i--)
					{
						Reset1();
						model.removeRow(i);
						Reset1();
					}
				}
				catch(Exception e1)
				{
					JOptionPane.showMessageDialog(null,e1.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
				}
			}
			if(e.getSource() == btnSubmit)
			{
				try
				{
					for (int i=0;i<model.getRowCount();i++)
					{
					 	sql="Insert into SalesDetails(ProductName,ProductRate,UnitKG,Vat,Total,SalesNo,BillNo) values ('" + model.getValueAt(i,0) + "'," + model.getValueAt(i,1) + "," + model.getValueAt(i,2) + "," + model.getValueAt(i,3) + "," + model.getValueAt(i,4) + "," + txtSaleNo.getText() + "," + txtBillNo.getText() + ")";


/*sql="Insert into SalesDetails(SalesNo,BillNo,ProductName,ProductRate,UnitKG,Vat,Total) values (" + txtBillNo.getText() + ", " + txtSaleNo.getText() + ",'"+ model.getValueAt(i,0) + "'," + model.getValueAt(i,1) + "," + model.getValueAt(i,2) + "," + model.getValueAt(i,3) + "," + model.getValueAt(i,4) + ")";*/




						JOptionPane.showMessageDialog(null,"Saved in SalesRecord with Details ", "Successfully Done",JOptionPane.INFORMATION_MESSAGE  ) ;
						//System.out.println(sql);
						System.out.println("Table Record Added in Sales Details");
						st.executeUpdate(sql);


						sql1 = "Insert into SalesRecord(SalesNo,BillNo,CustomerName,CustomerID,CustomerType,Address,BillDate,Total) values (" + txtSaleNo.getText() + "," + txtBillNo.getText() + ",'" + txtCustomerName.getText() + "'," + txtCustomerID.getText() + ",'" + txtType.getText() + "','" + txtAddress.getText() + "','" + txtBillDate.getText() + "'," + txtTotal.getText() + ")";
						//System.out.println(sql1);
						System.out.println("Other Record Added in Sales Record");
						st.executeUpdate(sql1);
					
						Reset();
					}
				}
				catch(SQLException e2)
				{
					System.out.println("Err "  + e2 );
				}
			 	catch(ArrayIndexOutOfBoundsException e2)
				{
					JOptionPane.showMessageDialog(null,e2.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
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
       					sql = "select * from CustomerRecord where CustomerName = '" + dblb1.getSelectedItem()  + "'";
       					ResultSet rsvm = st.executeQuery(sql);
					if(rsvm.next())
       	 				{
						txtCustomerID.setText( "" +rsvm.getInt("CustomerID"));
						txtAddress.setText( "" +rsvm.getString("Address"));
						txtType.setText("" +rsvm.getString("Type"));
				}
				rsvm.close();
			}
			catch(SQLException e1)
			{
			 	JOptionPane.showMessageDialog(null,e1.toString(), "SQL Error ",JOptionPane.ERROR_MESSAGE  ) ;
		  	}
		}
		if(fe.getSource() == dblb2)   // dblb2
		{
			try
		    	{
		       		sql = "select * from ProductRecord where ProductName = '" + dblb2.getSelectedItem()  + "'";
		       		ResultSet rsvm = st.executeQuery(sql);
				if(rsvm.next())
		        		{
					txtRate.setText( "" +rsvm.getInt("Rate"));
				}
				rsvm.close();
			}
			catch(SQLException e1)
			{
			 	JOptionPane.showMessageDialog(null,e1.toString(), "SQL Error ",JOptionPane.ERROR_MESSAGE  ) ;
		  	}
   		}
		if(fe.getSource() == txtUnit)
   		{
      			try
   	  		{
	  	 		double rate = Double.parseDouble(txtRate.getText());
	   	 		double unit = Double.parseDouble(txtUnit.getText());
				//double vat = Double.parseInt(txtVat.getText());
				//double ans = rate*unit*vat/100;
			   	double vat = rate*unit*0.04;
	     			txtVat.setText( ""+vat);
	     			double tot = rate + vat;
   	     			txtTotal.setText( ""+tot);
   	  		}
   	  		catch(NumberFormatException e1)
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
		new CustomerScreen();
	}
}
