import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import javax.swing.table.* ;

public class VolunteerUpdate extends JFrame implements ActionListener
{
	String url="jdbc:postgresql://localhost/tree_plantation";
	String sql = "";
	Connection con;
	Statement st ;
    	ResultSet rs ;
      
	private JFrame fr;
	private JLabel lblVolunteerName,lblVolunteerID,lblAddress,lblContact,lblAge;
	private JTextField txtVolunteerName,txtVolunteerID,txtContact,txtAge;
	private JTextArea txtAddress;
	private JComboBox dblb1;
	private DefaultTableModel model;
	private JTable  etab;
	private JButton btnModify,btnSearch,btnExit,btnReset;

	VolunteerUpdate()
	{
		fr = new JFrame("Volunteer update");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Update Volunteer");
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
         		ImageIcon ic = new ImageIcon("a16.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);    
         
        		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);
			
		Font f2;
		f2=new Font("CAMBRIA",Font.BOLD,14);

/*********************************** ADDING LABEL & TEXTBOXES ******************************************/

		lblVolunteerName = new JLabel("Volunteer NAME : ");
		lblVolunteerName.setBounds(450,150,150,25);
		lblVolunteerName.setFont(f1);

		txtVolunteerName = new JTextField();
		txtVolunteerName.setBounds(600,150,180,25);
		txtVolunteerName.setFont(f1);

		lblVolunteerID = new JLabel("VOLUNTEER ID : ");
		lblVolunteerID.setBounds(50,150,120,25);
		lblVolunteerID.setFont(f1);

		txtVolunteerID = new JTextField();
		txtVolunteerID.setBounds(220,150,50,25);
		txtVolunteerID.setFont(f1);
		
		/*lblType = new JLabel("AGE :");
		lblType.setBounds(50,220,100,25);
		lblType.setFont(f1);
		
		dblb1 = new JComboBox();													//DROPDOWN LIST-BOX
		dblb1.addItem("Company");
		dblb1.addItem("Party");
		dblb1.setBounds(220,220,100,24);
		dblb1.setFont(f1);*/

		lblAge = new JLabel("AGE : ");
		lblAge.setBounds(50,650,120,25);
		lblAge.setFont(f1);

		txtAge = new JTextField();
		txtAge.setBounds(180,650,50,25);
		txtAge.setFont(f1);
	

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
		model.addColumn("VOLUNTEER ID");
		model.addColumn("VOLUNTEER NAME");
		model.addColumn("AGE");
		model.addColumn("ADDRESS");
		model.addColumn("CONTACT");
	
		etab=new JTable(model);
	
        		JScrollPane j =new JScrollPane(etab , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		j.setBounds(70,380,850,200);
				
		int k = 0 ;
		try
		{
			sql = "select * from volunteer";
       		 	ResultSet rs = st.executeQuery(sql);
       			while(rs.next())
               		 	{
               		 		int cid=rs.getInt("vid");
				String cname=(rs.getString("v_name"));
				int ctype=(rs.getInt("age"));
				String cadd=(rs.getString("address"));
				String ccont=(rs.getString("contact"));
           		     		model.insertRow(k++,new Object[]   {new Integer(cid) , cname , ctype , cadd , ccont});
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

		fr.getContentPane().add(lblVolunteerName);
		fr.getContentPane().add(txtVolunteerName);
		fr.getContentPane().add(lblVolunteerID);
		fr.getContentPane().add(txtVolunteerID);
		/*fr.getContentPane().add(lblType);
		fr.getContentPane().add(dblb1);*/
		fr.getContentPane().add(lblAge);
		fr.getContentPane().add(txtAge);
		fr.getContentPane().add(lblAddress);
		fr.getContentPane().add(jp);
		fr.getContentPane().add(j);
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
		txtVolunteerName.setText("");
		txtVolunteerID.setText("");
		txtAge.setText("");
		txtAddress.setText("");
		txtContact.setText("");
	}	

/*******************************************************************************************************/

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnModify)
		{
			try
		         	{
				 //sql = "Update volunteer set v_name='"+txtVolunteerName.getText()+"',age='"+txtAge.getText()+"',address='"+txtAddress.getText()+"',contact='"+txtContact.getText()+"' where vid= '+ txtVolunteerID.getText() +')";
			         	int n = st.executeUpdate("update volunteer set v_name='"+txtVolunteerName.getText()+"',age='"+txtAge.getText()+"',address='"+txtAddress.getText()+"',contact='"+txtContact.getText()+"' where vid="+txtVolunteerID.getText());
			         	System.out.println(n + " Record Modified in volunteer");
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
				sql = "select * from volunteer where vid=" + txtVolunteerID.getText() + "";
			            	ResultSet rs = st.executeQuery(sql);
			            	while(rs.next()) 
               	 			{
  					txtVolunteerName.setText(rs.getString("v_name"));
				             txtAddress.setText(rs.getString("address"));
                  		txtContact.setText(rs.getString("contact"));
                	                	}
			}
			catch(SQLException e1)
			{
				System.out.println("SQL VolunteerID" + e1);
			}
		}
		if(e.getSource() == btnExit)
		{
			fr.setVisible(false);
			fr.dispose();
               		}
		if(e.getSource() == btnReset)
		{
			txtVolunteerID.setText("");	
			txtVolunteerName.setText("");
			txtAge.setText("");
			txtAddress.setText("");
			txtContact.setText("");
		}
		/*if(e.getSource() == btnSale)
		{
			new CustomerScreen();
			fr.setVisible(false);
		}*/
	}	

/*******************************************************************************************************/

	public static void main(String arg[])
	{
		new VolunteerUpdate();
	}
}

