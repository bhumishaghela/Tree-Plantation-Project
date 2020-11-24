import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.sql.* ;
import javax.swing.table.* ;

public class UserMaster extends JFrame implements ActionListener
{

	String url="jdbc:postgresql://localhost/tree_plantation";
	String sql = "";
	Connection con;
	Statement st ;
	ResultSet rs ;

	private JFrame fr;
	private JLabel lblUserName,lblUserID,lblContact,lblAddress,lblEmailid;
	private JTextField txtUserName,txtUserID,txtContact,txtAddress,txtEmailid;
	private JButton btnNew,btnAdd,btnSubmit,btnExit,btnReset,btnShow,btnDel;
	private DefaultTableModel model;
	private JTable etab;
	private JScrollPane jp;
	int r=0;

	UserMaster()
	{
		fr=new JFrame(" ADOPT PLANT " );
		fr.getContentPane().setLayout(null);
 		fr.setTitle("Entry Form To Adopt Plant");
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
		ImageIcon ic = new ImageIcon("a6.jpeg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);
		
		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);


/*********************************** ADDING LABEL & TEXTBOXES ******************************************/

		lblUserID =new JLabel("USER ID :");
		lblUserID.setBounds(80,200,120,25);
		lblUserID.setFont(f1);

		txtUserID =new JTextField();
		txtUserID.setBounds(220,200,50,25);
		txtUserID.setFont(f1);

		lblUserName =new JLabel("USER NAME :");
		lblUserName.setBounds(80,300,200,25);
		lblUserName.setFont(f1);

		txtUserName =new JTextField();
		txtUserName.setBounds(220,300,150,25);
		txtUserName.setFont(f1);
		
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

		lblEmailid =new JLabel("EMAIL_ID :");
		lblEmailid.setBounds(80,600,120,25);
		lblEmailid.setFont(f1);
		
		txtEmailid =new JTextField();
		txtEmailid.setBounds(220,600,100,25);
		txtEmailid.setFont(f1);
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

		fr.getContentPane().add(lblUserID);
		fr.getContentPane().add(txtUserID);
		fr.getContentPane().add(lblUserName);
		fr.getContentPane().add(txtUserName);
		fr.getContentPane().add(lblAddress);
  		fr.getContentPane().add(txtAddress);
                fr.getContentPane().add(lblContact);
  		fr.getContentPane().add(txtContact);
		fr.getContentPane().add(lblEmailid);
  		fr.getContentPane().add(txtEmailid);
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
			sql = "select * from user1";
       		 	ResultSet rs = st.executeQuery(sql);
       			while(rs.next())
                  		{
                  			int vid=rs.getInt("uid");
				String Vname=(rs.getString("u_name"));
				String Cname=(rs.getString("address"));
                                       int con=rs.getInt("contact");
				String em=(rs.getString("email_id"));
				model.insertRow(k++,new Object[]   {new Integer(vid) , Vname , Cname,con , em});
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
            		txtUserID.setText("");
            		txtUserName.setText("");
			txtAddress.setText(""); 
                	txtContact.setText("");
			txtEmailid.setText("");
	      	
	}
     
/*******************************************************************************************************/

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnSubmit)
		{
			try
			{
	             			sql = "insert into user1(uid,u_name,address,contact,email_id) values (" + txtUserID.getText() + ", '" + txtUserName.getText() + "', '"+ txtAddress.getText()+"','"+ txtContact.getText()+"','"+ txtEmailid.getText()+"')";
		         		int n = st.executeUpdate(sql);
		         		System.out.println(n + " Record Added in USER Record");
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
				ResultSet rsid = st.executeQuery("select max(uid) as vid from user1");
				while(rsid.next())
				{
					id = Integer.parseInt(rsid.getString("vid")) + 1;
					System.out.println("");
				}
				txtUserID.setText("" + id);
			}
			catch(SQLException e1)
			{
				System.out.println("SQL USERID" + e1);
				}
			}
			if(e.getSource() == btnExit)
			{
		             		fr.setVisible(false);
		             		fr.dispose();
		          	}
			if(e.getSource() == btnReset)
		 	{
		 		txtUserID.setText("");
	      			txtUserName.setText("");
				txtAddress.setText("");
				txtContact.setText("");
				txtEmailid.setText("");
		      	}
			if(e.getSource() == btnShow)
		 	{
               				try
  				{
					int r=0;
					sql = "select * from user1";
					ResultSet rs = st.executeQuery(sql);
			      		while(rs.next())
					{
				    		int vid=rs.getInt("uid");
			          		String Vname=(rs.getString("u_name"));
				    		String Cname=(rs.getString("address"));
						int con=rs.getInt("contact");
						String em=(rs.getString("email_id"));
				    		model.insertRow(r++,new Object[]   {new Integer(vid) , Vname , Cname , con , em});
					}
			      		rs.close();
			 	}
				catch(SQLException e1)
				{
		   			 JOptionPane.showMessageDialog(null,e1.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
				}  
                   		}
             			if(e.getSource() == btnDel)
               			{
               				try
                 			{
					sql="delete from user1 where uid= " +txtUserID.getText()+ "";
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
			}
		}
   
 /*******************************************************************************************************/

		public static void main(String arg[])
		{
			new UserMaster();
		}
}
