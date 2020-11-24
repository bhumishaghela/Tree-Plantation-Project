import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.sql.* ;

/********************************************************************************************/

public class Loginscreen implements ActionListener
{	
	
	String url="jdbc:postgresql://localhost/tree_plantation";
	String sql = "";
	Connection con;
	Statement st ;
    	ResultSet rs ;
	int f1;

	
	private JFrame fr;
	private JLabel lblLoginID,lblPassword;
  	private JTextField txtLoginID;
  	private JPasswordField txtPassword;
  	private JProgressBar bar;
  	private JButton btnSignin,btnReset,btnExit;
  	final static int interval = 250;
  	Timer timer;
  	int i;
  	int f5;
  
/********************************************************************************************/
  	Loginscreen()
  	{
  		
		fr=new JFrame("Login Screen");
		fr.setTitle("SIGN IN FOR COTTON COUNTY");

		ImageIcon ic = new ImageIcon("7.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);
		
		
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
  
/**********************LOGIN********************************/
		
		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,19);
		
		Font f2;
		f2=new Font("CAMBRIA",Font.BOLD,19);
		
		Font f3;
		f3=new Font("CAMBRIA",Font.BOLD,15);
		
		
 		lblLoginID =new JLabel("LOGIN ID");
 		lblLoginID.setBounds(380,350,100,25);
 		lblLoginID.setFont(f1);
 		

		txtLoginID =new JTextField();
  		txtLoginID.setBounds(520,350,170,25);
		txtLoginID.setFont(f2);

/**********************PASSWORD********************************/

 		lblPassword =new JLabel("PASSWORD ");
 		lblPassword.setBounds(380,400,150,25);
 		lblPassword.setFont(f1);
 		

		txtPassword =new JPasswordField();
  		txtPassword.setBounds(520,400,170,25);
  		txtPassword.setFont(f2);

/**********************BUTTON********************************/

		btnSignin =new JButton("SIGN IN");
		btnSignin.setBounds(410,450,100,25);
		btnSignin.addActionListener(this);
		btnSignin.setFont(f3);
	        
 		btnReset =new JButton("RESET");
 		btnReset.setBounds(540,450,90,25);
		btnReset.addActionListener(this);
  		btnReset.setFont(f3);
  
  	     	bar = new JProgressBar(0, 20);
		bar.setValue(0);
		bar.setStringPainted(true);
		bar.setBounds(260,530,510,25);
		bar.setBackground(new Color(255,0,0));
		bar.setFont(f1);
     
/**********************ADDING OF CONTENT PANE********************************/

		fr.getContentPane().setLayout(null);

		fr.getContentPane().add(lblLoginID);

		fr.getContentPane().add(txtLoginID);

		fr.getContentPane().add(lblPassword);

  		fr.getContentPane().add(txtPassword);

  		fr.getContentPane().add(btnSignin);

  		fr.getContentPane().add(btnReset);

  		fr.getContentPane().add(pic1);
  		
  		fr.getContentPane().add(bar);
  		
  		fr.setSize(1024,768);
 		fr.setVisible(true);
 			
 		
/****************************CREATE TIMER*************************************/
 		
		timer = new Timer(interval, new ActionListener()
        		{
			public void actionPerformed(ActionEvent evt)
            			{
				if (i == 20)
				{
					new Mainscreen();
					fr.setVisible(false);
				}
				i = i + 4;
            				bar.setValue(i);
            			}
        		});
	}
	
/*****************************************************************************/
 		
 	void Reset()
 	{
 		txtLoginID.setText("");
 		txtPassword.setText("");
 	}


/**********************ACTION PERFORMERD EVENT********************************/

	public void actionPerformed(ActionEvent ae)
  	{
  		//btnSignin       
		if(ae.getSource() == btnSignin)
     		{
   			String user1= txtLoginID.getText();
		   	String password1= new String(txtPassword.getText());
		   	try
		   	{
		   		sql = "select * from Admin";
			 	ResultSet rs = st.executeQuery(sql);
			 	f5 = 0;
			 	while(rs.next())
		     		{	
		     			String user2=(rs.getString("login_id"));
					String password2=(rs.getString("password"));
					if((user1.equalsIgnoreCase(user2)) && (password1.equalsIgnoreCase(password2)))
					{
						btnSignin.setEnabled(false);
						i = 0;
						timer.start();
						f5= 1;
						break;
					}
		   		}
				rs.close();
				if(f5==0)
		   		{
		   			System.out.println("Enter the valid username and password");
					JOptionPane.showMessageDialog(null,"INCORRECT LOGINID & PASSWORD","Login Process !",JOptionPane.ERROR_MESSAGE);
				}
      			}
			catch(Exception e1)
	  		{
	  			JOptionPane.showMessageDialog(null,e1.toString(), "Confirmation",JOptionPane.ERROR_MESSAGE  ) ;
	  		}
      		}
		//btnReset
		if(ae.getSource()== btnReset)
      		{
		      	Reset();
      		}
  	}	
 
 /***************************************************************************************************************/  
 
	public static void main(String arg[])
	{
		new Loginscreen();
	}
}
