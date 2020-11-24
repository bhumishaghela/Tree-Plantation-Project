import javax.swing.*;
import java.awt.event.*;
import java.sql.*;
import java.awt.*;
import javax.swing.table.* ;

public class VolunteerDelete extends JFrame implements ActionListener
{

	String url="jdbc:postgresql://localhost/tree_plantation";
	String sql = "";
	Connection con;
	Statement st ;
	ResultSet rs ;
      
	private JFrame fr;
	private JLabel lblVolunteerID;
	private JTextField txtVolunteerID;
	private DefaultTableModel model;
	private JTable  etab;
	private JButton btnDelete,btnShow;
	
	VolunteerDelete()
	{
		fr = new JFrame("Volunteer Delete");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Delete of Volunteer Record");
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
		ImageIcon ic = new ImageIcon("t6.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);    
         
        		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);
		
		Font f2;
		f2=new Font("CAMBRIA",Font.BOLD,14);

/*********************************** ADDING LABEL & TEXTBOXES ******************************************/
		
		
		lblVolunteerID = new JLabel("VOLUNTEER ID : ");
		lblVolunteerID.setBounds(320,200,120,25);
		lblVolunteerID.setFont(f1);


		txtVolunteerID = new JTextField();
		txtVolunteerID.setBounds(450,200,50,25);
		txtVolunteerID.setFont(f1);
		
		
		model=new DefaultTableModel();
		model.addColumn("VOLUNTEER ID");
		model.addColumn("VOLUNTEER NAME");
		model.addColumn("AGE");
		model.addColumn("ADDRESS");
		model.addColumn("CONTACT");


		etab=new JTable(model);
		

	       	JScrollPane j =new JScrollPane(etab , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		j.setBounds(100,300,850,200);
		
		
	
/*********************************** BUTTON ************************************************************/
		
		btnDelete = new JButton("[DELETE]");
		btnDelete.setBounds(550,200,130,25);
		btnDelete.addActionListener(this);
		btnDelete.setFont(f1);
		
		btnShow = new JButton("[SHOW]");
		btnShow.setBounds(700,200,130,25);
		btnShow.addActionListener(this);
		btnShow.setFont(f1);
		
		fr.getContentPane().add(lblVolunteerID);
		fr.getContentPane().add(txtVolunteerID);
		fr.getContentPane().add(btnDelete);
		fr.getContentPane().add(btnShow);
		fr.getContentPane().add(j);
		fr.getContentPane().add(pic1);


		fr.setSize(1024,768);
		fr.setVisible(true);
	}

	void Empty()
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
	}
	void Show()
	{
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
	}
		
/*********************************************************************************************************/
	
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnDelete)
		{
			try
			{
	         		
				// sql = "Update CustomerRecord set CustomerName='"+txtCustomerName.getText()+"',Type='"+dblb1.getSelectedItem()+"',Address='"+txtAddress.getText()+"',Contact='"+txtContact.getText()+"' where CustomerID= '+ txtVolunteerID.getText() +')";
				int n = st.executeUpdate("delete from volunteer where vid="+txtVolunteerID.getText()+"");
				System.out.println(n + " Record Deleted volunteer");
		        		JOptionPane.showMessageDialog(null,"RECORD SUCCESSFULLY DELETED  ! ! !   ", "Confirmation",JOptionPane.INFORMATION_MESSAGE  ) ;
		     		Empty();
		     	}
			catch(SQLException e1)
	    	 	{
				System.out.println("Error " + e1);
			}
        		}
        		if(e.getSource() == btnShow)
        		{
			Show();
        		}
	}
	
	public static void main(String arg[])
	{
		new VolunteerDelete();
	}
}
