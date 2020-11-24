import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.*;

public class CustomerBill implements ActionListener,FocusListener,ItemListener
{
	String url="jdbc:postgresql://localhost/tree_plantation";
	String sql = "";
	String sql1 = "";
	Connection con;
	Statement st ;
    	ResultSet rs ;


	private JFrame fr;
	private JLabel lblCustomerName,lblCustomerID,lblAddress,lblPaymentType,lblChequeNo,lblChequeDate,lblChequeAmount,lblBillNo,lblBankName,lblSalesNo,lblBillDate,lblday,lbldeposit,lblrup;
	private JTextField txtCustomerName,txtCustomerID,txtChequeNo,txtChequeDate,txtChequeAmount,txtBillNo,txtBankName,txtSalesNo,txtBillDate;
	private JTextArea txtAddress;
	private JComboBox dblb1,dblb2;
	private JButton btnSubmit,btnReset,btnExit;
	
	//String CustomerName = "";
	//String Type = "";
	String Address = "";
	String BillNo = "" ;
	String BillDate = "";
	String ChequeAmount = "";
	String SalesNo = "";
	

	CustomerBill()
	{
		fr = new JFrame("Customer bill");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Bill For Customer");
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
		ImageIcon ic = new ImageIcon("t22.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);
		
		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);
		
		Font f2;
		f2=new Font("CAMBRIA",Font.BOLD,12);


/*********************************** ADDING LABEL & TEXTBOXES ******************************************/

		/*lblCustomerName = new JLabel("CUSTOMER NAME");
		lblCustomerName.setBounds(50,150,150,25);
		lblCustomerName.setFont(f1);

		txtCustomerName = new JTextField();
		txtCustomerName.setBounds(200,150,180,25);
		txtCustomerName.setFont(f1);
		//txtCustomerName.setEditable(false);*/
 		
   		lblCustomerID = new JLabel("CUSTOMER ID");
		lblCustomerID.setBounds(50,150,150,25);
		lblCustomerID.setFont(f1);

		txtCustomerID = new JTextField();
		txtCustomerID.setBounds(200,150,60,25);
		txtCustomerID.setFont(f1);
		//txtCustomerID.setEditable(false);	   		

		/*lblType = new JLabel("TYPE");
		lblType.setBounds(750,350,100,25);
		lblType.setFont(f1);

		txtType = new JTextField();
		txtType.setBounds(850,350,110,25);
		txtType.setFont(f1);
		txtType.setEditable(false);*/
		
		lblSalesNo = new JLabel("Product NO");
		lblSalesNo.setBounds(430,150,100,25);
		lblSalesNo.setFont(f1);

		txtSalesNo = new JTextField();
		txtSalesNo.setBounds(550,150,50,25);
		txtSalesNo.setFont(f1);
		//txtSalesNo.setEditable(false);
			
		try
		{
			int id=0;
			sql="Select max(pid) as ID from product";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				id=rs.getInt("ID");
			}
			txtSalesNo.setText(""+id);
			rs.close();
		}
		catch(SQLException e1)
		{
	 		JOptionPane.showMessageDialog(null,e1.toString(), "SQL Error ",JOptionPane.ERROR_MESSAGE  ) ;
		}
		lblAddress = new JLabel("ADDRESS");
		lblAddress.setBounds(50,250,100,25);
		lblAddress.setFont(f1);
			
		txtAddress = new JTextArea();
		txtAddress.setBounds(200,250,200,60);
		txtAddress.setFont(f1);
		//txtAddress.setEditable(false);
			
		JScrollPane jp = new JScrollPane(txtAddress , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jp.setBounds(200,250,200,70);
		jp.setFont(f1);

		lblBillNo = new JLabel("BILL NO");
		lblBillNo.setBounds(430,250,100,25);
		lblBillNo.setFont(f1);

		txtBillNo = new JTextField();
		txtBillNo.setBounds(550,250,70,25);
		txtBillNo.setFont(f1);
		//txtBillNo.setEditable(false);
			
			
		lblBillDate = new JLabel("BILL DATE");
		lblBillDate.setBounds(750,250,100,25);
		lblBillDate.setFont(f1);

			
		txtBillDate = new JTextField();
		txtBillDate.setBounds(850,250,115,25);
		txtBillDate.setFont(f1);
		//txtBillDate.setEditable(false);
			
		lblday = new JLabel("(MM/DD/YYYY)");
		lblday.setBounds(850,230,120,25);
		lblday.setFont(f1);
			
		lblPaymentType = new JLabel("PAYMENT TYPE");
		lblPaymentType.setBounds(50,350,120,25);
		lblPaymentType.setFont(f1);

		dblb2 = new JComboBox();
		dblb2.addItem("Cash");
		dblb2.addItem("Cheque");
		dblb2.setBounds(200,350,100,25);
		dblb2.addItemListener(this);
		dblb2.setFont(f1);

		lblChequeNo = new JLabel("CHEQUE NO");
		lblChequeNo.setBounds(50,450,120,25);
		lblChequeNo.setVisible(false);
		lblChequeNo.setFont(f1);
			
		txtChequeNo = new JTextField();
		txtChequeNo.setBounds(200,450,100,25);
		txtChequeNo.setVisible(false);
		txtChequeNo.setFont(f1);

		lblChequeDate = new JLabel("CHEQUE DATE");
		lblChequeDate.setBounds(430,450,130,25);
		lblChequeDate.setVisible(false);
		lblChequeDate.setFont(f1);

		txtChequeDate = new JTextField();
		txtChequeDate.setBounds(550,450,100,25);
		txtChequeDate.setVisible(false);
		txtChequeDate.setFont(f1);
			
		lbldeposit = new JLabel("(Enter the date of depositing the Cheque)");
		lbldeposit.setBounds(650,450,240,25);
		lbldeposit.setVisible(false);
		lbldeposit.setFont(f2);

		lblChequeAmount = new JLabel("AMOUNT");
		lblChequeAmount.setBounds(430,350,120,25);
		lblChequeAmount.setFont(f1);

		txtChequeAmount = new JTextField();
		txtChequeAmount.setBounds(550,350,90,25);
		txtChequeAmount.setFont(f1);
		//txtChequeAmount.setEditable(false);
			
		lblrup = new JLabel("(In Rupees)");
		lblrup.setBounds(640,350,130,25);
		lblrup.setFont(f2);

			
		lblBankName = new JLabel("BANK NAME");
		lblBankName.setBounds(50,550,150,25);
		lblBankName.setVisible(false);
		lblBankName.setFont(f1);
			
		txtBankName = new JTextField();
		txtBankName.setBounds(200,550,350,25);
		txtBankName.setVisible(false);
		txtBankName.setFont(f1);

			
/*********************************** ADDING BUTTON *****************************************************/


		btnSubmit = new JButton("[SUBMIT]");
		btnSubmit.setBounds(50,620,110,25);
		btnSubmit.addActionListener(this);
		btnSubmit.setFont(f1);

		btnReset = new JButton("[RESET]");
		btnReset.setBounds(180,620,110,25);
		btnReset.addActionListener(this);
		btnReset.setFont(f1);
			
		btnExit = new JButton("[EXIT]");
		btnExit.setBounds(310,620,110,25);
		btnExit.addActionListener(this);
		btnExit.setFont(f1);
			
		/*btnSale = new JButton("[BACK TO SALE]");
		btnSale.setBounds(760,620,180,25);
		btnSale.addActionListener(this);
		btnSale.setFont(f1);*/
			
		try
		{
			int SalesNo = Integer.parseInt(txtSalesNo.getText());
			sql = "select * from product where pid = " + SalesNo + "";
			rs = st.executeQuery(sql);
			while(rs.next())
			{
				
				SalesNo = (rs.getInt("pid"));
				//txtSalesNo.setText(rs.getInt("pid"));
				//txtCustomerName.setText(rs.getString("cname"));
				txtCustomerID.setText(rs.getString("cid"));
			 	//txtType.setText(rs.getString("CustomerType"));
				txtAddress.setText(rs.getString("address"));
				txtBillNo.setText(rs.getString("billno"));
				txtBillDate.setText(rs.getString("date"));
				txtChequeAmount.setText(rs.getString("amount"));
			}
			if(dblb2.getSelectedItem() =="Cheque") 
			{
				dblb2.setSelectedItem("Cheque");
			}
			else
			{
				dblb2.setSelectedItem("Cash");
			}
			rs.close();
		}
		catch(SQLException e1)
		{
			 JOptionPane.showMessageDialog(null,e1.toString(), "SQL Error ",JOptionPane.ERROR_MESSAGE  ) ;
		}

/*********************************** ADDING OF CONTENT PANE ********************************************/

		/*fr.getContentPane().add(lblCustomerName);
		fr.getContentPane().add(txtCustomerName);*/
		fr.getContentPane().add(lblCustomerID);
		fr.getContentPane().add(txtCustomerID);
		/*fr.getContentPane().add(lblType);
		fr.getContentPane().add(txtType);*/
		fr.getContentPane().add(lblAddress);
		fr.getContentPane().add(jp);
		fr.getContentPane().add(lblBillNo);
		fr.getContentPane().add(txtBillNo);
		fr.getContentPane().add(lblPaymentType);
		fr.getContentPane().add(dblb2);
		fr.getContentPane().add(lblChequeNo);
		fr.getContentPane().add(txtChequeNo);
		fr.getContentPane().add(lblChequeDate);
		fr.getContentPane().add(txtChequeDate);
		fr.getContentPane().add(lblChequeAmount);
		fr.getContentPane().add(txtChequeAmount);
		fr.getContentPane().add(lblrup);
		fr.getContentPane().add(lblBankName);
		fr.getContentPane().add(txtBankName);
		fr.getContentPane().add(lblSalesNo);
		fr.getContentPane().add(txtSalesNo);
		fr.getContentPane().add(lblBillDate);
		fr.getContentPane().add(lblday);
		fr.getContentPane().add(lbldeposit);
		fr.getContentPane().add(txtBillDate);
		fr.getContentPane().add(btnSubmit);
		fr.getContentPane().add(btnReset);
		fr.getContentPane().add(btnExit);
		//fr.getContentPane().add(btnSale);
		fr.getContentPane().add(pic1);
            

		fr.setSize(1020,768);
		fr.setVisible(true);
	}
	void Reset1()
	{
		//txtCustomerName.setText("");
		txtCustomerID.setText("");
		txtSalesNo.setText("");
		txtBillNo.setText("");
		txtBillDate.setText("");
		txtAddress.setText("");
		txtChequeAmount.setText("");
		//txtType.setText("");
		txtChequeDate.setText("");
		txtChequeNo.setText("");
		txtBankName.setText("");
	}

/*******************************************************************************************************/
	public void actionPerformed(ActionEvent e)
	{
	   if(e.getSource() == btnReset)
	   {
		 // txtType.setText("");
		  txtSalesNo.setText("");
		  txtBillDate.setText("");
		  txtAddress.setText("");
		  txtBillNo.setText("");
		  txtChequeNo.setText("");
		  txtChequeDate.setText("");
		  txtChequeAmount.setText("");
		  txtBankName.setText("");
	   }
	   if(e.getSource() == btnExit)
	   {
	          fr.setVisible(false);
	          fr.dispose();
	   }
	   /*if(e.getSource() == btnSale)
	   {
	          new CustomerScreen();
	          fr.setVisible(false);
	   }*/
	   if(e.getSource() == btnSubmit)
	   {
			try
			{	
				if(dblb2.getSelectedItem() == "Cheque")
				{
				sql = "insert into purchased_product(pid,cid,billno,date,address,payment_type,cheque_no,cheque_date,amount,bank_name) values (" + txtSalesNo.getText() + "," + txtCustomerID.getText() + ",'" + txtBillNo.getText() + "','" + txtBillDate.getText() + "','" + txtAddress.getText() + "','" + dblb2.getSelectedItem() + "','" + txtChequeNo.getText() + "','" + txtChequeDate.getText() + "','" + txtChequeAmount.getText() + "','" + txtBankName.getText() + "')";
			    	int n = st.executeUpdate(sql);
			    	System.out.println(n + " Record Added in Customer Bill");
			    	JOptionPane.showMessageDialog(null,"RECORD SUCCESSFULLY SAVED IN CUSTOMER BILL !   ", "Confirmation",JOptionPane.INFORMATION_MESSAGE  ) ;
				Reset1();
			}
				else
				{
				sql = "insert into purchased_product (pid,cid,billno,date,address,payment_type,amount) values (" + txtSalesNo.getText() + "," + txtCustomerID.getText() + ",'" + txtBillNo.getText() + "','" + txtBillDate.getText() + "','" + txtAddress.getText() + "','" + dblb2.getSelectedItem() + "','" + txtChequeAmount.getText() + "')";
			    	int n = st.executeUpdate(sql);
			    	System.out.println(n + " Record Added in Customer Bill");
			    	JOptionPane.showMessageDialog(null,"RECORD SUCCESSFULLY SAVED IN CUSTOMER BILL !   ", "Confirmation",JOptionPane.INFORMATION_MESSAGE  ) ;
				Reset1();
				}			
			}
			catch(SQLException e1)
			{
			   System.out.println("Error " + e1);
			}
	   }

}

/*******************************************************************************************************/

public void itemStateChanged(ItemEvent ie)
{
	if(ie.getSource()== dblb2)
	{
		if(dblb2.getSelectedItem()=="Cheque")
		{
			lblChequeNo.setVisible(true);
			txtChequeNo.setVisible(true);
			lblChequeDate.setVisible(true);
			txtChequeDate.setVisible(true);
			lbldeposit.setVisible(true);
			lblBankName.setVisible(true);
			txtBankName.setVisible(true);
		}
		if(dblb2.getSelectedItem()=="Cash")
		{
			lblChequeNo.setVisible(false);
			txtChequeNo.setVisible(false);
			lblChequeDate.setVisible(false);
			txtChequeDate.setVisible(false);
			lblBankName.setVisible(false);
			txtBankName.setVisible(false);
			lbldeposit.setVisible(false);
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
		sql = "select * from purchased_product where cid = '" + dblb1.getSelectedItem()  + "'";
			ResultSet rsvm = st.executeQuery(sql);
			if(rsvm.next())
    			{
				txtChequeAmount.setText( "" +rsvm.getInt("amount"));
				txtAddress.setText( "" +rsvm.getString("address"));
				//txtType.setText("" +rsvm.getString("CustomerType"));
				txtSalesNo.setText("" +rsvm.getInt("pid"));
				txtBillNo.setText("" +rsvm.getInt("billno"));
				txtBillDate.setText("" +rsvm.getString("date"));
			}
			rsvm.close();
   		}
		catch(SQLException e1)
   		{
 			JOptionPane.showMessageDialog(null,e1.toString(), "SQL Error ",JOptionPane.ERROR_MESSAGE  ) ;
		}
	}
	if(fe.getSource() == btnReset)
	{
		//txtCustomerName.setText("");
		txtCustomerID.setText("");
		//txtType.setText("");
		txtSalesNo.setText("");
		txtBillDate.setText("");
		txtAddress.setText("");
		txtBillNo.setText("");
		txtChequeNo.setText("");
		txtChequeDate.setText("");
		txtChequeAmount.setText("");
		txtBankName.setText("");
	}
	/*if(fe.getSource() == btnSale)
	{
	      	fr.setVisible(false);
		new CustomerScreen();
	}*/
}
public void focusGained(FocusEvent fe)
{
}

/*******************************************************************************************************/

public static void main(String arg[])
{
	new CustomerBill();
}
}
