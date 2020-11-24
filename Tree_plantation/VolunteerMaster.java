import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;


public class VolunteerMaster implements ActionListener

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

	private JButton btnSubmit,btnNew,btnExit,btnReset;

	VolunteerMaster()
	{
		fr = new JFrame("volunteer form");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Entry Form For New volunteer");
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
        		ImageIcon ic = new ImageIcon("a7.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);    
		
		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);

/*********************************** ADDING LABEL & TEXTBOXES ******************************************/

		lblVolunteerName = new JLabel("VOL NAME : ");
		lblVolunteerName.setBounds(50,150,150,25);
		lblVolunteerName.setFont(f1);

		txtVolunteerName = new JTextField();
		txtVolunteerName.setBounds(220,150,180,25);
		txtVolunteerName.setFont(f1);

		lblVolunteerID = new JLabel("VOL ID : ");
		lblVolunteerID.setBounds(450,150,120,25);
		lblVolunteerID.setFont(f1);

		txtVolunteerID = new JTextField();
		txtVolunteerID.setBounds(580,150,50,25);
		txtVolunteerID.setFont(f1);
		
		lblAge = new JLabel("AGE : ");
		lblAge.setBounds(450,350,120,25);
		lblAge.setFont(f1);

		txtAge = new JTextField();
		txtAge.setBounds(580,350,50,25);
		txtAge.setFont(f1);
	

		lblAddress = new JLabel("ADDRESS :");
		lblAddress.setBounds(50,350,100,25);
		lblAddress.setFont(f1);

		txtAddress = new JTextArea();
		txtAddress.setBounds(220,350,150,35);
		txtAddress.setFont(f1);

		lblContact = new JLabel("CONTACT :");
		lblContact.setBounds(50,450,100,25);
		lblContact.setFont(f1);

		txtContact = new JTextField();
		txtContact.setBounds(220,450,130,25);
		txtContact.setFont(f1);
		
		JScrollPane jp = new JScrollPane(txtAddress , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jp.setBounds(220,350,200,70);

/*********************************** BUTTON ************************************************************/

		btnNew = new JButton("[NEW]");
		btnNew.setBounds(180,550,110,25);
		btnNew.addActionListener(this);
		btnNew.setFont(f1);


		btnSubmit = new JButton("[SUBMIT]");
		btnSubmit.setBounds(300,550,110,25);
		btnSubmit.addActionListener(this);
		btnSubmit.setFont(f1);

		btnExit = new JButton("[EXIT]");
		btnExit.setBounds(450,550,110,25);
		btnExit.addActionListener(this);
		btnExit.setFont(f1);
		
		btnReset = new JButton("[RESET]");
		btnReset.setBounds(600,550,110,25);
		btnReset.addActionListener(this);
		btnReset.setFont(f1);

/*********************************** ADDING OF CONTENT PANE ********************************************/

		fr.getContentPane().add(lblVolunteerName);
		fr.getContentPane().add(txtVolunteerName);
		fr.getContentPane().add(lblVolunteerID);
		fr.getContentPane().add(txtVolunteerID);
		fr.getContentPane().add(lblAge);
		fr.getContentPane().add(txtAge);
		
		fr.getContentPane().add(lblAddress);
		fr.getContentPane().add(jp);
		fr.getContentPane().add(lblContact);
		fr.getContentPane().add(txtContact);
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
            		txtVolunteerName.setText("");
		txtVolunteerID.setText("");
			txtAge.setText("");
	        	txtAddress.setText("");
	        	txtContact.setText("");
     	}

/*******************************************************************************************************/

	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnSubmit)
		{
			try
			{
				sql = "insert into volunteer (vid,v_name,age,address,contact) values (" + txtVolunteerID.getText() + ",'" + txtVolunteerName.getText() + "','" + txtAge.getText() + "','" + txtAddress.getText() + "','" + txtContact.getText() + "')";
		         		int n = st.executeUpdate(sql);
		         		System.out.println(n + " Record Added in volunteer");
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
				ResultSet rsid = st.executeQuery("select max(vid) as cno from volunteer");
				while(rsid.next())
				{
					id = Integer.parseInt(rsid.getString("cno")) + 1;
					System.out.println("");
				}
				txtVolunteerID.setText("" + id);
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
		
	}

/*******************************************************************************************************/

	public static void main(String arg[])
	{
		new VolunteerMaster();
	}
}
