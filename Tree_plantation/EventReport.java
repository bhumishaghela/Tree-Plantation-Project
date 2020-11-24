import java.io.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import java.sql.*;
import javax.swing.table.* ;

public class EventReport implements ActionListener
{
      
	String url="jdbc:postgresql://localhost/tree_plantation";
	String sql = "";
	String sql1 = "";
	Connection con;
	Statement st ;
	ResultSet rs ;
     
      
	private JFrame fr;
	private JButton btnShow;
	private DefaultTableModel model;
	private JTable  etab;
	int i = 0;

	EventReport()
	{
		fr = new JFrame("Event Information");
		fr.getContentPane().setLayout(null);
		fr.setTitle("Event Information");
	 	fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                   
		ImageIcon ic = new ImageIcon("a15.jpg");
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
		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);
			
		Font f2;
		f2=new Font("CAMBRIA",Font.BOLD,14);
			
	 	btnShow = new JButton(" [SHOW ALL] ");
	 	btnShow.setBounds(450,150,140,25);
		btnShow.addActionListener(this);
		btnShow.setFont(f1);

/*********************************** TABLE SETTING *****************************************************/

		model = new DefaultTableModel();
		model.addColumn("EVENT ID");
	        	model.addColumn("EVENT NAME");
	        	model.addColumn("DATE");
	        	model.addColumn("VENUE");
	        	model.addColumn("DURATION");
			model.addColumn("BUDGET");	
	        	etab = new JTable(model);

	  	JScrollPane jp = new JScrollPane(etab , ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED,ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	        	jp.setBounds(120,250,780,300);
		        
	        	etab.setFont(f2);

/*********************************** ADDING OF CONTENT PANE *********************************************/

	        	fr.getContentPane().add(jp);
	   	fr.getContentPane().add(btnShow);
            	   	fr.getContentPane().add(pic1);

	        	fr.setSize(1024,768);
	        	fr.setVisible(true);
	}
   
/*******************************************************************************************************/
	public void actionPerformed(ActionEvent e)
       	{
       		if(e.getSource()==btnShow)
		{
               			try
            			{
				sql = "select * from event";
				ResultSet rs = st.executeQuery(sql);
				int i = 1 ;
   	 			
				int r = 0;
            				while(rs.next()) 
				{
					//int SrNo=i++;
					int eventid=(rs.getInt("eid"));
   				String eName=(rs.getString("e_name"));
				Date dt=(rs.getDate("date"));
				String Venue=(rs.getString("venue"));
				int Duration=(rs.getInt("duration"));
				float Budget=(rs.getFloat("budget"));	
					model.insertRow(r++,new Object[] { rs.getInt(1) ,rs.getString(2),rs.getDate(3),rs.getString(4),rs.getInt(5),rs.getFloat(6) });
				}
               			}
			catch(SQLException e1)
			{
				 JOptionPane.showMessageDialog(null,e1.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
			}  
			catch(NumberFormatException e1)
			{
				 JOptionPane.showMessageDialog(null,e1.toString(), "Error",JOptionPane.ERROR_MESSAGE  ) ;
			}  
		}     
	}   
        
/*******************************************************************************************************/

	public static void main(String arg[])
	{
		new EventReport();
	}
}
