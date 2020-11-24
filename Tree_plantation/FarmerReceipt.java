import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class FarmerReceipt implements ActionListener,FocusListener
{
	String url="jdbc:postgresql://localhost/cottoncounty";
	String sql = "";
	String sql1 = "";
	Connection con;
	Statement st ;
    	ResultSet rs ;

	private JFrame fr;
	private JLabel lblFarmerName,lblFarmerID,lblAddress,lblPaymentType,lblReceiptNo,lblAmount,lblInwardNo,lblInwardDate,lblRs,lblRs1,lblRs2,lblAdvancePaid,lblBalanceRemain;
	private JTextField txtFarmerName,txtFarmerID,txtReceiptNo,txtPaymentType,txtAmount,txtInwardNo,txtInwardDate,txtAdvancePaid,txtBalanceRemain;
	private JTextArea txtAddress;
	private JComboBox dblb1,dblb2,dblb3;
	private JButton btnSubmit,btnReset,btnExit,btnPurchase;

	FarmerReceipt()
	{
		fr = new JFrame("Farmer Receipt");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Receipt For Farmer");
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
             		ImageIcon ic = new ImageIcon("receipt.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);
			
		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);
			
		Font f2;
		f2=new Font("CAMBRIA",Font.BOLD,12);

/*********************************** ADDING LABEL & TEXTBOXES ******************************************/

		lblFarmerName = new JLabel("FARMER NAME :");
		lblFarmerName.setBounds(50,150,150,25);
		lblFarmerName.setFont(f1);
			
		txtFarmerName = new JTextField();
		txtFarmerName.setBounds(200,150,180,25);
		txtFarmerName.setFont(f1);
		txtFarmerName.setEditable(false);
	
		lblFarmerID = new JLabel("FARMER ID");
		lblFarmerID.setBounds(440,150,100,25);
		lblFarmerID.setFont(f1);
		
		txtFarmerID = new JTextField();
		txtFarmerID.setBounds(580,150,80,25);
		txtFarmerID.setFont(f1);
		txtFarmerID.setEditable(false);
			
		lblInwardNo = new JLabel("INWARD NO");
		lblInwardNo.setBounds(710,150,100,25);
		lblInwardNo.setFont(f1);
		
		txtInwardNo = new JTextField();
		txtInwardNo.setBounds(860,150,50,25);
		txtInwardNo.setFont(f1);
		txtInwardNo.setEditable(false);
			
		try
		{
			int id=0;
			sql="Select max(InwardNo) as id from purchaserecord";
			ResultSet rs = st.executeQuery(sql);
			while(rs.next())
			{
				id=rs.getInt("id");
			}
			txtInwardNo.setText(""+id);
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
		txtAddress.setEditable(false);
		
		JScrollPane jp = new JScrollPane(txtAddress , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jp.setBounds(200,250,200,60);
		jp.setFont(f1);

		lblReceiptNo = new JLabel("RECEIPT NO");
		lblReceiptNo.setBounds(440,250,100,25);
		lblReceiptNo.setFont(f1);
		
		txtReceiptNo = new JTextField();
		txtReceiptNo.setBounds(580,250,80,25);
		txtReceiptNo.setFont(f1);
		txtReceiptNo.setEditable(false);
			
		lblInwardDate = new JLabel("INWARD DATE");
		lblInwardDate.setBounds(710,250,120,25);
		lblInwardDate.setFont(f1);
		
		txtInwardDate = new JTextField();
		txtInwardDate.setBounds(860,250,120,25);
		txtInwardDate.setFont(f1);
		txtInwardDate.setEditable(false);
		
		lblPaymentType = new JLabel("PAYMENT TYPE");
		lblPaymentType.setBounds(50,360,120,25);
		lblPaymentType.setFont(f1);
			
		dblb3 = new JComboBox();
		dblb3.addItem("Advance");
           	 	dblb3.addItem("Full Pay");
		dblb3.setBounds(200,360,120,25);
            		dblb3.setFont(f1);     

		lblAmount = new JLabel("TOTAL AMOUNT");
		lblAmount.setBounds(440,360,130,25);
		lblAmount.setFont(f1);
			
		lblRs = new JLabel("(in Rupees)");
		lblRs.setBounds(720,360,100,25);
		lblRs.setFont(f1);
		
		txtAmount = new JTextField("");
		txtAmount.setBounds(610,360,110,25);
		txtAmount.setFont(f1);
		txtAmount.setEditable(false);
			
		lblAdvancePaid = new JLabel("ADVANCE PAID");
		lblAdvancePaid.setBounds(440,420,120,25);
		lblAdvancePaid.setFont(f1);
			
		lblRs1 = new JLabel("(in Rupees)");
		lblRs1.setBounds(720,420,100,25);
		lblRs1.setFont(f1);
		
		txtAdvancePaid = new JTextField();
		txtAdvancePaid.setBounds(610,420,110,25);
		txtAdvancePaid.addFocusListener(this);
		txtAdvancePaid.setFont(f1);
				
		lblBalanceRemain = new JLabel("BALANCE REMAINED");
		lblBalanceRemain.setBounds(440,480,180,25);
		lblBalanceRemain.setFont(f1);
		
		lblRs2 = new JLabel("(in Rupees)");
		lblRs2.setBounds(720,480,100,25);
		lblRs2.setFont(f1);
		
		txtBalanceRemain = new JTextField();
		txtBalanceRemain.setBounds(610,480,110,25);
		txtBalanceRemain.setFont(f1);
			
/*********************************** ADDING BUTTON *****************************************************/

		btnSubmit = new JButton("[SUBMIT]");
		btnSubmit.setBounds(50,550,110,25);
		btnSubmit.addActionListener(this);
		btnSubmit.setFont(f1);
		
		btnReset = new JButton("[RESET]");
		btnReset.setBounds(180,550,110,25);
		btnReset.addActionListener(this);
		btnReset.setFont(f1);
		
		btnExit = new JButton("[EXIT]");
		btnExit.setBounds(310,550,110,25);
		btnExit.addActionListener(this);
		btnExit.setFont(f1);
		
		btnPurchase = new JButton("[BACK TO PURCHASE]");
		btnPurchase.setBounds(770,550,200,25);
		btnPurchase.addActionListener(this);
		btnPurchase.setFont(f1);
		
		try
		{
			int InwardNo = Integer.parseInt(txtInwardNo.getText());
			sql = "select * from purchaserecord where inwardno = " + InwardNo + "";
			rs = st.executeQuery(sql);
			while(rs.next())
			{
				InwardNo = (rs.getInt("inwardno"));
				txtFarmerName.setText(rs.getString("farmername"));
				txtFarmerID.setText(rs.getString("farmerid"));
			 	txtAddress.setText(rs.getString("address"));
				txtReceiptNo.setText(rs.getString("receiptno"));
				txtInwardDate.setText(rs.getString("inwarddate"));
				txtAmount.setText(rs.getString("total"));
			
			}
		}
		catch(SQLException e1)
		{
			JOptionPane.showMessageDialog(null,e1.toString(), "SQL Error1 ",JOptionPane.ERROR_MESSAGE  ) ;
		}	

/*********************************** ADDING OF CONTENT PANE ********************************************/

		fr.getContentPane().add(lblFarmerName);
		fr.getContentPane().add(txtFarmerName);
		fr.getContentPane().add(lblFarmerID);
		fr.getContentPane().add(txtFarmerID);
		fr.getContentPane().add(lblInwardNo);
		fr.getContentPane().add(txtInwardNo);
		fr.getContentPane().add(lblAddress);
		fr.getContentPane().add(jp);
		fr.getContentPane().add(lblReceiptNo);
		fr.getContentPane().add(txtReceiptNo);
		fr.getContentPane().add(lblInwardDate);
		fr.getContentPane().add(txtInwardDate);
		fr.getContentPane().add(lblPaymentType);
		fr.getContentPane().add(dblb3);
		fr.getContentPane().add(lblAmount);
		fr.getContentPane().add(txtAmount);
		fr.getContentPane().add(lblAdvancePaid);
		fr.getContentPane().add(txtAdvancePaid);
		fr.getContentPane().add(lblBalanceRemain);
		fr.getContentPane().add(txtBalanceRemain);
		fr.getContentPane().add(lblRs);
		fr.getContentPane().add(lblRs1);
		fr.getContentPane().add(lblRs2);
		fr.getContentPane().add(btnSubmit);
		fr.getContentPane().add(btnReset);
		fr.getContentPane().add(btnExit);
		fr.getContentPane().add(btnPurchase);
	           	fr.getContentPane().add(pic1);
		fr.setSize(1024,768);
		fr.setVisible(true);
	}
	void Reset1()
	{
		txtFarmerName.setText("");
		txtFarmerID.setText("");
		txtInwardNo.setText("");
		txtReceiptNo.setText("");
		txtInwardDate.setText("");
		txtAddress.setText("");
		txtAmount.setText("");
		txtAdvancePaid.setText("");
		txtBalanceRemain.setText("");
	}			

/*******************************************************************************************************/

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnReset)
		{
		txtFarmerID.setText("");
		txtInwardNo.setText("");
		txtInwardDate.setText("");
		txtAddress.setText("");
		txtReceiptNo.setText("");
		txtAmount.setText("");
		txtBalanceRemain.setText("");
		txtAdvancePaid.setText("");
		txtPaymentType.setText("");
	}
	if(e.getSource() == btnExit)
	{
		fr.setVisible(false);
		fr.dispose();
	}
	if(e.getSource() == btnPurchase)
	{
		new PurchaseScreen();  
		fr.setVisible(false);
	}
	if(e.getSource() == btnSubmit)
	{
		try
		{
			sql = "Insert into farmerreceipt(farmerid,farmername,inwardno,inwarddate,receiptno,address,paymenttype,totalamount,advancepaid,balanceremained) values (" + txtFarmerID.getText() + ",'" + txtFarmerName.getText() + "',"+ txtInwardNo.getText()+",'" + txtInwardDate.getText() + "'," + txtReceiptNo.getText() + ",'" + txtAddress.getText() + "','" + dblb3.getSelectedItem() + "'," + txtAmount.getText() + "," + txtAdvancePaid.getText() + "," + txtBalanceRemain.getText() + ")";
			int n = st.executeUpdate(sql);
			System.out.println(n + " Record Added in FarmerReceipt");
			JOptionPane.showMessageDialog(null,"RECORD SUCCESSFULLY SAVED IN FARMER RECEIPT!   ", "Confirmation",JOptionPane.INFORMATION_MESSAGE  ) ;
			Reset1();
		}	
		catch(SQLException e1)
		{
			System.out.println("Error11 " + e1);
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
			sql = "select * from purchaserecord where farmername = '" + dblb1.getSelectedItem()  + "'";
			ResultSet rsvm = st.executeQuery(sql);
			if(rsvm.next())
			{
				txtFarmerID.setText( "" +rsvm.getInt("farmerid"));
				txtAddress.setText( "" +rsvm.getString("address"));
				txtReceiptNo.setText( "" +rsvm.getInt("receiptno"));
				txtInwardNo.setText( "" +rsvm.getInt("inwardno"));
				txtInwardDate.setText( "" +rsvm.getString("inwarddate"));
				txtAmount.setText("" +rsvm.getInt("total") );
			}
			rsvm.close();
		}
		catch(SQLException e1)
		{
		 	JOptionPane.showMessageDialog(null,e1.toString(), "SQL Error133 ",JOptionPane.ERROR_MESSAGE  ) ;
	  	}
	}
      	if(fe.getSource() == txtAdvancePaid)
       	{
		double amt = Double.parseDouble(txtAmount.getText());
	   	double adv = Double.parseDouble(txtAdvancePaid.getText());
	   	double bal = amt - adv;
	   	txtBalanceRemain.setText( ""+bal);
       	}
}
public void focusGained(FocusEvent fe)
{
}

/*******************************************************************************************************/

public static void main(String arg[])
{
	new FarmerReceipt();
}
}

