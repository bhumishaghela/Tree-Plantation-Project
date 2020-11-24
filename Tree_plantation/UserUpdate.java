import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import javax.swing.table.* ;

public class UserUpdate extends JFrame implements ActionListener
{
	String url="jdbc:postgresql://localhost/tree_plantation";
	String sql = "";
	Connection con;
	Statement st ;
    	ResultSet rs ;
      
	private JFrame fr;
	private JLabel lblUserName,lblUserID,lblAddress,lblEmailid,lblContact;
	private JTextField txtUserName,txtUserID,txtContact,txtEmailid;
	private JTextArea txtAddress;
	private JComboBox dblb1;
	private DefaultTableModel model;
	private JTable  etab;
	private JButton btnModify,btnSearch,btnExit,btnReset;

	UserUpdate()
	{
		fr = new JFrame("User update");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Update USER");
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
         		ImageIcon ic = new ImageIcon("t20.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);    
         
        		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);
			
		Font f2;
		f2=new Font("CAMBRIA",Font.BOLD,14);

/*********************************** ADDING LABEL & TEXTBOXES ******************************************/

		lblUserName = new JLabel("USER NAME : ");
		lblUserName.setBounds(450,150,150,25);
		lblUserName.setFont(f1);

		txtUserName = new JTextField();
		txtUserName.setBounds(600,150,180,25);
		txtUserName.setFont(f1);

		lblUserID = new JLabel("USER ID : ");
		lblUserID.setBounds(50,150,120,25);
		lblUserID.setFont(f1);

		txtUserID = new JTextField();
		txtUserID.setBounds(220,150,50,25);
		txtUserID.setFont(f1);
		
		/*lblType = new JLabel("AGE :");
		lblType.setBounds(50,220,100,25);
		lblType.setFont(f1);
		
		dblb1 = new JComboBox();													//DROPDOWN LIST-BOX
		dblb1.addItem("Company");
		dblb1.addItem("Party");
		dblb1.setBounds(220,220,100,24);
		dblb1.setFont(f1);*/

		lblEmailid = new JLabel("EMAIL ID : ");
		lblEmailid.setBounds(50,650,170,25);
		lblEmailid.setFont(f1);

		txtEmailid = new JTextField();
		txtEmailid.setBounds(180,650,170,25);
		txtEmailid.setFont(f1);
	

		lblAddress = new JLabel("ADDRESS :");
		lblAddress.setBounds(450,220,100,25);
		lblAddress.setFont(f1);
	
		txtAddress = new JTextArea();
		txtAddress.setBounds(600,220,150,35);
		txtAddress.setFont(f1);
	
		lblContact = new JLabel("CONTACT :");
		lblContact.setBounds(50,320,100,25);
		lblContact.setFont(f1);

		txtContact = new JTextField();
		txtContact.setBounds(220,320,130,25);
		txtContact.setFont(f1);
			
		JScrollPane jp = new JScrollPane(txtAddress , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jp.setBounds(600,220,200,70);
			    
		model=new DefaultTableModel();
		model.addColumn("USER ID");
		model.addColumn("USER NAME");
		
		model.addColumn("ADDRESS");
		model.addColumn("EMAIL ID");
		model.addColumn("CONTACT");
	
		etab=new JTable(model);
	
        		JScrollPane j =new JScrollPane(etab , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		j.setBounds(70,380,850,200);
				
		int k = 0 ;
		try
		{
			sql = "select * from user1";
       		 	ResultSet rs = st.executeQuery(sql);
       			while(rs.next())
               		 	{
               		 		int cid=rs.getInt("uid");
				String cname=(rs.getString("u_name"));
				String cadd=(rs.getString("address"));
				String cem=(rs.getString("email_id"));
				String ccont=(rs.getString("contact"));
           		     		model.insertRow(k++,new Object[]   {new Integer(cid) , cname , cadd , cem, ccont});
			}
       			rs.close();
		}
		catch(SQLException e1)
		{
			 JOptionPane.showMessageDialog(null,e1.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
            		}
       		//etab.setFont(f2);

/*********************************** BUTTON ************************************************************/

		btnSearch = new JButton("[SEARCH]");
		btnSearch.setBounds(280,150,110,25);
		btnSearch.addActionListener(this);
		btnSearch.setFont(f1);

		btnModify = new JButton("[MODIFY]");
		btnModify.setBounds(180,600,110,25);
		btnModify.addActionListener(this);
		btnModify.setFont(f1);
		
		btnExit = new JButton("[EXIT]");
		btnExit.setBounds(580,600,110,25);
		btnExit.addActionListener(this);
		btnExit.setFont(f1);
			
		btnReset = new JButton("[RESET]");
		btnReset.setBounds(380,600,110,25);
		btnReset.addActionListener(this);
		btnReset.setFont(f1);
							
/*********************************** ADDING OF CONTENT PANE ********************************************/

		fr.getContentPane().add(lblUserName);
		fr.getContentPane().add(txtUserName);
		fr.getContentPane().add(lblUserID);
		fr.getContentPane().add(txtUserID);
		/*fr.getContentPane().add(lblType);
		fr.getContentPane().add(dblb1);*/
		//fr.getContentPane().add(lblAge);
		//fr.getContentPane().add(txtAge);
		fr.getContentPane().add(lblAddress);
		fr.getContentPane().add(jp);
		fr.getContentPane().add(j);
		fr.getContentPane().add(lblEmailid);
		fr.getContentPane().add(txtEmailid);
		fr.getContentPane().add(lblContact);
		fr.getContentPane().add(txtContact);
		fr.getContentPane().add(btnSearch);
		fr.getContentPane().add(btnModify);
		fr.getContentPane().add(btnExit);
		fr.getContentPane().add(btnReset);
		fr.getContentPane().add(pic1);
		fr.setSize(1024,768);
		fr.setVisible(true);
	}		

/*******************************************************************************************************/	

	void Reset()
	{
		txtUserName.setText("");
		txtUserID.setText("");
		txtAddress.setText("");
		txtEmailid.setText("");
		txtContact.setText("");
	}	

/*******************************************************************************************************/

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnModify)
		{
			try
		         	{
				 //sql = "Update volunteer set v_name='"+txtUserName.getText()+"',age='"+txtAge.getText()+"',address='"+txtAddress.getText()+"',contact='"+txtContact.getText()+"' where vid= '+ txtUserID.getText() +')";
			         	int n = st.executeUpdate("update user1 set u_name='"+txtUserName.getText()+"',address='"+txtAddress.getText()+"',email_id='"+txtEmailid.getText()+"',contact='"+txtContact.getText()+"' where uid="+txtUserID.getText());
			         	System.out.println(n + " Record Modified in user");
			         	JOptionPane.showMessageDialog(null,"RECORD SUCCESSFULLY MODIFIED  ! ! !   ", "Confirmation",JOptionPane.INFORMATION_MESSAGE  ) ;
		         		Reset();	
			}
			catch(SQLException e1)
	    		{
				System.out.println("Error " + e1);
			}
        		}	
		if(e.getSource() == btnSearch)
        		{
			try
			{
				sql = "select * from user1 where uid=" + txtUserID.getText() + "";
			            	ResultSet rs = st.executeQuery(sql);
			            	while(rs.next()) 
               	 			{
  					txtUserName.setText(rs.getString("u_name"));
				             txtAddress.setText(rs.getString("address"));
                  		txtContact.setText(rs.getString("contact"));
                	                	}
			}
			catch(SQLException e1)
			{
				System.out.println("SQL UserID" + e1);
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
			txtEmailid.setText("");
			txtContact.setText("");
		}
		
	}	

/*******************************************************************************************************/

	public static void main(String arg[])
	{
		new UserUpdate();
	}
}

