import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;

public class ProductMaster implements ActionListener
{

	String url="jdbc:postgresql://localhost/tree_plantation";
	String sql = "";
	Connection con;
	Statement st ;
	ResultSet rs ;

	private JFrame fr;
	private JLabel lblProdName,lblProdID,lblRate;
	private JTextField txtProdName,txtProdID,txtRate;
	private JButton btnNew,btnSubmit,btnExit,btnReset;

	ProductMaster()
	{
		fr = new JFrame("Product Creation");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Entry Form For Product");
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
		ImageIcon ic = new ImageIcon("1.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);
		
		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);
            
/*********************************** ADDING LABEL & TEXTBOXES ******************************************/

		lblProdID = new JLabel("PRODUCT ID :");
		lblProdID.setBounds(80,200,130,25);
		lblProdID.setFont(f1);

		txtProdID = new JTextField();
		txtProdID.setBounds(250,200,50,25);
		txtProdID.setFont(f1);
		
		lblProdName = new JLabel("PRODUCT NAME :");
		lblProdName.setBounds(80,300,170,25);
		lblProdName.setFont(f1);
		
		txtProdName = new JTextField();
		txtProdName.setBounds(250,300,150,25);
		txtProdName.setFont(f1);

		lblRate = new JLabel("RATE :");
		lblRate.setBounds(80,400,100,25);
		lblRate.setFont(f1);
		
		txtRate = new JTextField();
		txtRate.setBounds(250,400,100,25);
		txtRate.setFont(f1);

/*********************************** ADDING BUTTON ******************************************************/

		btnNew = new JButton("[NEW]");
		btnNew.setBounds(80,500,110,25);
		btnNew.addActionListener(this);
		btnNew.setFont(f1);

		btnSubmit = new JButton("[SUBMIT]");
		btnSubmit.setBounds(220,500,130,25);
		btnSubmit.addActionListener(this);
		btnSubmit.setFont(f1);

		btnExit = new JButton("[EXIT]");
		btnExit.setBounds(500,500,110,25);
		btnExit.addActionListener(this);
		btnExit.setFont(f1);

		btnReset = new JButton("[RESET]");
		btnReset.setBounds(360,500,110,25);
		btnReset.addActionListener(this);
		btnReset.setFont(f1);


/*********************************** ADDING OF CONTENT PANE ********************************************/

		fr.getContentPane().add(lblProdID);
		fr.getContentPane().add(txtProdID);
		fr.getContentPane().add(lblProdName);
		fr.getContentPane().add(txtProdName);
		fr.getContentPane().add(lblRate);
		fr.getContentPane().add(txtRate);
		fr.getContentPane().add(btnNew);
		fr.getContentPane().add(btnSubmit);
		fr.getContentPane().add(btnExit);
		fr.getContentPane().add(btnReset);
		fr.getContentPane().add(pic1);
		fr.setSize(1024,768);
		fr.setVisible(true);
	}

/*******************************************************************************************************/	
	
	void Reset()
     	{
            		txtProdID.setText("");
		txtProdName.setText("");
		txtRate.setText("");
     	}

/*******************************************************************************************************/

	public void actionPerformed(ActionEvent e)
	{
		 if(e.getSource() == btnSubmit)
		 {
			try
			{
				sql = "insert into product(pid,p_name,price_in_kg) values (" + txtProdID.getText() + ", '" + txtProdName.getText() + "', '" + txtRate.getText() + "')";
				int n = st.executeUpdate(sql);
				System.out.println(n + " Record Added in ProductRecord ");
				JOptionPane.showMessageDialog(null,"RECORD SUCCESSFULLY SAVED  ! ! !   ", "Confirmation",JOptionPane.INFORMATION_MESSAGE  ) ;
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
				ResultSet rsid = st.executeQuery("select max(pid) as cid from product");
     		    		while(rsid.next())
				{
					id = Integer.parseInt(rsid.getString("cid")) + 1;
					System.out.println("");
				}
				txtProdID.setText("" + id);
			}
			catch(SQLException e1)
			{
				System.out.println("SQL ProjectID" + e1);
			}
		}
		if(e.getSource() == btnExit)
		{
		             fr.setVisible(false);
		             fr.dispose();
		}
		if(e.getSource() == btnReset)
		{
			txtProdID.setText("");
			txtProdName.setText("");
			txtRate.setText("");
		}
	}

/*******************************************************************************************************/

	public static void main(String arg[])
	{
		new ProductMaster();
	}
}
