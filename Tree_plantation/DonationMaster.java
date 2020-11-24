import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.* ;
import javax.swing.table.* ;

public class DonationMaster extends JFrame implements ActionListener
{

	String url="jdbc:postgresql://localhost/tree_plantation";
	String sql = "";
	Connection con;
	Statement st ;
	ResultSet rs ;

	private JFrame fr;
	private JLabel lblDonorName,lblDonorID,lblContact,lblAddress,lblAmount,lblBirthDate,lblGender,lbldate;;
	private JTextField txtDonorName,txtDonorID,txtContact,txtAddress,txtAmount,txtDate,txtBirthDate;
	private JButton btnNew,btnAdd,btnSubmit,btnExit,btnReset,btnShow;
	private DefaultTableModel model;
	private JTable etab;
	private JScrollPane jp;
	private JComboBox dblb1,dblb2;
	int r=0;

	DonationMaster()
	{
		fr=new JFrame(" Donation Form " );
		fr.getContentPane().setLayout(null);
 		fr.setTitle("Entry Form for donation");
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
		ImageIcon ic = new ImageIcon("a8.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);
		
		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);


/*********************************** ADDING LABEL & TEXTBOXES ******************************************/

		lblDonorID =new JLabel("DONOR ID :");
		lblDonorID.setBounds(80,200,120,25);
		lblDonorID.setFont(f1);

		txtDonorID =new JTextField();
		txtDonorID.setBounds(220,200,50,25);
		txtDonorID.setFont(f1);

		lblDonorName =new JLabel("DONOR NAME :");
		lblDonorName.setBounds(80,300,200,25);
		lblDonorName.setFont(f1);

		txtDonorName =new JTextField();
		txtDonorName.setBounds(220,300,150,25);
		txtDonorName.setFont(f1);
		
		lblAddress =new JLabel("ADDRESS :");
		lblAddress.setBounds(80,400,120,25);
		lblAddress.setFont(f1);
		
		txtAddress =new JTextField();
		txtAddress.setBounds(220,400,100,25);
		txtAddress.setFont(f1);
            
                lblContact =new JLabel("CONTACT :");
		lblContact.setBounds(80,500,120,25);
		lblContact.setFont(f1);
		
		txtContact =new JTextField();
		txtContact.setBounds(220,500,100,25);
		txtContact.setFont(f1);

		lblAmount =new JLabel("AMOUNT :");
		lblAmount.setBounds(450,200,120,25);
		lblAmount.setFont(f1);
		
		txtAmount =new JTextField();
		txtAmount.setBounds(590,200,100,25);
		txtAmount.setFont(f1);

		
		lblBirthDate = new JLabel("DONATION DATE :");
		lblBirthDate.setBounds(500,450,100,25);
		lblBirthDate.setFont(f1);
		
		txtBirthDate = new JTextField();
		txtBirthDate.setBounds(630,450,100,25);
        		txtBirthDate.setFont(f1);    
        
       		lbldate = new JLabel("(mm/dd/yyyy)");
        		lbldate.setBounds(730,450,130,25);
		lbldate.setFont(f1);

		lblGender = new JLabel("GENDER :");
		lblGender.setBounds(500,300,100,25);
		lblGender.setFont(f1);

		dblb2 = new JComboBox();
		dblb2.addItem("Male");
		dblb2.addItem("Female");
		dblb2.setBounds(630,300,100,25);
		dblb2.setFont(f1);

/*********************************** ADDING BUTTON *****************************************************/

		btnNew =new JButton("[NEW]");
		btnNew.setBounds(80,650,90,25);
		btnNew.addActionListener(this);
		btnNew.setFont(f1);

		btnSubmit =new JButton("[SUBMIT]");
		btnSubmit.setBounds(200,650,130,25);
		btnSubmit.addActionListener(this);
		btnSubmit.setFont(f1);

		btnReset =new JButton("[RESET]");
		btnReset.setBounds(340,650,110,25);
		btnReset.addActionListener(this);
		btnReset.setFont(f1);
		
		btnExit =new JButton("[EXIT]");
		btnExit.setBounds(480,650,110,25);
		btnExit.addActionListener(this);
		btnExit.setFont(f1);
         

/*********************************** ADDING OF CONTENT PANE ********************************************/

		fr.getContentPane().add(lblDonorID);
		fr.getContentPane().add(txtDonorID);
		
		fr.getContentPane().add(lblDonorName);
		fr.getContentPane().add(txtDonorName);
		fr.getContentPane().add(lblAddress);
  		fr.getContentPane().add(txtAddress);
		fr.getContentPane().add(lblContact);
  		fr.getContentPane().add(txtContact);
		fr.getContentPane().add(lblAmount);
  		fr.getContentPane().add(txtAmount);
		fr.getContentPane().add(lblBirthDate);
		fr.getContentPane().add(txtBirthDate);
        		fr.getContentPane().add(lbldate);
		fr.getContentPane().add(lblGender);
		fr.getContentPane().add(dblb2);
                
		
  		fr.getContentPane().add(btnSubmit);
  		fr.getContentPane().add(btnNew);
  		fr.getContentPane().add(btnExit);
  		fr.getContentPane().add(btnReset);
		fr.getContentPane().add(pic1);
  		fr.setSize(1024,768);
		fr.setVisible(true);
	}
	void makeTableEmpty()
	{
		try
		{
	  		for(int i=model.getRowCount()-1;i>=0;i--)
			{
				model.removeRow(i);
			}
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null,e.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
		}
		int k = 0 ;
                    	try
                    	{
			sql = "select * from donation";
       		 	ResultSet rs = st.executeQuery(sql);
       			while(rs.next())
                  		{
                  			int vid=rs.getInt("did");
				String Vname=(rs.getString("donor_name"));
				String Cname=(rs.getString("address"));
                                       int con=rs.getInt("contact");
				int em=(rs.getInt("donation_amt"));
				Date dt=(rs.getDate("donation_date"));
				
				model.insertRow(k++,new Object[]   {new Integer(vid) , Vname , Cname, con , em , dt});
			}
       		   	rs.close();
		}
		catch(SQLException e1)
		{
	   		JOptionPane.showMessageDialog(null,e1.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
                  	}
	}
       
	void Reset()
     	{
            		txtDonorID.setText("");
            		txtDonorName.setText("");
			txtAddress.setText(""); 
                	txtContact.setText("");
			txtAmount.setText("");
			txtBirthDate.setText("");
	      	
	}
     
/*******************************************************************************************************/

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnSubmit)
		{
			try
			{
	             			sql = "insert into donation(did,donor_name,address,contact,donation_amt,donation_date,gender) values (" + txtDonorID.getText() + ", '" + txtDonorName.getText() + "', '"+ txtAddress.getText()+"','"+ txtContact.getText()+"','"+ txtAmount.getText()+"','" + txtBirthDate.getText() + "','" + dblb2.getSelectedItem() + "')";
		         		int n = st.executeUpdate(sql);
		         		System.out.println(n + " Record Added in Donation Record");
		         		JOptionPane.showMessageDialog(null,"RECORD SUCCESSFULLY SAVED  ! ! !   ", "Confirmation",JOptionPane.INFORMATION_MESSAGE  ) ;
		        		// makeTableEmpty();
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
				ResultSet rsid = st.executeQuery("select max(did) as vid from donation");
				while(rsid.next())
				{
					id = Integer.parseInt(rsid.getString("vid")) + 1;
					System.out.println("");
				}
				txtDonorID.setText("" + id);
			}
			catch(SQLException e1)
			{
				System.out.println("SQL DonorID" + e1);
				}
			}
			if(e.getSource() == btnExit)
			{
		             		fr.setVisible(false);
		             		fr.dispose();
		          	}
			if(e.getSource() == btnReset)
		 	{
		 		txtDonorID.setText("");
	      			txtDonorName.setText("");
				txtAddress.setText("");
				txtContact.setText("");
				txtAmount.setText("");
				txtBirthDate.setText("");
		      	}
			/*if(e.getSource() == btnShow)
		 	{
               				try
  				{
					int r=0;
					sql = "select * from donation";
					ResultSet rs = st.executeQuery(sql);
			      		while(rs.next())
					{
				    		int vid=rs.getInt("did");
			          		String Vname=(rs.getString("donor_name"));
				    		String Cname=(rs.getString("address"));
						int con=rs.getInt("contact");
						int em=(rs.getInt("amount"));
						Date dt=(rs.getDate("donation_date"));
				    		model.insertRow(r++,new Object[]   {new Integer(vid) , Vname , Cname , con , em , dt});
					}
			      		rs.close();
			 	}
				catch(SQLException e1)
				{
		   			 JOptionPane.showMessageDialog(null,e1.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
				}  
                   		}*/
             			/*if(e.getSource() == btnDel)
               			{
               				try
                 			{
					sql="delete from donation where did= " +txtDonorID.getText()+ "";
				            	int n =st.executeUpdate(sql);
					System.out.print(n +"Record Deleted");
					JOptionPane.showMessageDialog(null,"One Record Deleted Successfully", "Error",JOptionPane.INFORMATION_MESSAGE  ) ;
					makeTableEmpty();
					Reset();
		     		}
				catch(SQLException e1)
				{
					JOptionPane.showMessageDialog(null,e1.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
				}
				catch(ArrayIndexOutOfBoundsException e1)
				{
						JOptionPane.showMessageDialog(null,e.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
			      	}
			      	catch(Exception e1)
			      	{
	                        			JOptionPane.showMessageDialog(null,e.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
			      	}
			}*/
		}
   
 /*******************************************************************************************************/

		public static void main(String arg[])
		{
			new DonationMaster();
		}
}
