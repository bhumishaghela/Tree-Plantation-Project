import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import javax.swing.text.html.*;

public class Mainscreen extends JFrame implements ActionListener
{
	private JFrame fr ;
	private JMenuItem mCustomermaster,mVolunteer,mProductmaster,mCustomerBill,mPurchase,mSales,mProductReport,mOutCorporateReport,mEventReport,mProductPurchase,mCustomerSale,mUpVol,mDelVol,mUpUser,mDelUser,mUser;   
	private JLabel lblCustomer,lblVillage,lblFarmer,lblProduct,label;
	private JButton btnSignup,btnSignup1,btnSignup2,btnSignup3;

	Mainscreen()
	{
		fr =  new JFrame("GREEN HOPE NGO");
		fr.getContentPane().setLayout(null);
		fr.setTitle("GREEN HOPE NGO");
		fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


		ImageIcon ic = new ImageIcon("5.jpg");
		JLabel pic1 = new JLabel(ic,JLabel.CENTER);
		pic1.setBounds(0,0,1024,768);
		
		Font f1;
		f1=new Font("CAMBRIA",Font.BOLD,16);
		
		Font f2;
		f2=new Font("CAMBRIA",Font.BOLD,19);
		
		Font f3;
		f3=new Font("CAMBRIA",Font.BOLD,15);



		JMenuBar mb = new JMenuBar();

		//About Us
		JMenu mAbout = new JMenu("About Us");
		mAbout.setMnemonic('A');
		mAbout.setFont(f1);

		//Purchase
		JMenu mPurchase = new JMenu("Purchase");
		mProductPurchase = new JMenuItem("Purchase From Farmer ");
		mProductPurchase.addActionListener(this);
		mProductPurchase.setFont(f1);
		mPurchase.add(mProductPurchase);
		mPurchase.setMnemonic('P');
		mPurchase.setFont(f1);
		
		//Sales
		JMenu mSales = new JMenu("Sales");
		mCustomerBill = new JMenuItem("Sales To Customer ");
		mCustomerBill.addActionListener(this);
		mCustomerBill.setFont(f1);
		mSales.add(mCustomerBill);
		mSales.setMnemonic('S');
		mSales.setFont(f1);		
		
		//Reports		
		JMenu mReport = new JMenu("Reports");
		mProductReport = new JMenuItem("Product Report");
		mProductReport.addActionListener(this);
		mProductReport.setFont(f1);


		mOutCorporateReport = new JMenuItem("Corporate Report");
		mOutCorporateReport.addActionListener(this);
		mOutCorporateReport.setFont(f1);

		mEventReport = new JMenuItem("Event Report");
		mEventReport.addActionListener(this);
		mEventReport.setFont(f1);


		mReport.add(mProductReport);
		
		mReport.add(mOutCorporateReport);
		mReport.add(mEventReport);
		
		mReport.setMnemonic('R');
		mReport.setFont(f1);
		
		//Help
		JMenu mHelp = new JMenu("Help");
		mHelp.setMnemonic('H');
		mHelp.setFont(f1);
		
		//Edit
		JMenu mEdit = new JMenu("Edit");
		mEdit.setMnemonic('E');
		mEdit.setFont(f1);

		mVolunteer = new JMenu("Volunteer Record");
		mVolunteer.add(mUpVol=new JMenuItem("UpDate Volunteer "));
		mUpVol.addActionListener(this);
		
		mVolunteer.add(mDelVol=new JMenuItem("Delete Volunteer "));
		mDelVol.addActionListener(this);
		
		mVolunteer.setFont(f1);
		mUpVol.setFont(f1);
		mDelVol.setFont(f1);
		mUser = new JMenu("User Record");
		
		mUser.add(mUpUser=new JMenuItem("UpDate User "));
		mUpUser.addActionListener(this);
		
		mUser.add(mDelUser=new JMenuItem("Delete User "));
		mDelUser.addActionListener(this);
		
		mUser.setFont(f1);
		mUpUser.setFont(f1);
		mDelUser.setFont(f1);
		mEdit.add(mVolunteer);
		mEdit.add(mUser);
		mb.add(mPurchase);
		mb.add(mSales);
		mb.add(mReport);
		mb.add(mEdit);

		
		btnSignup = new JButton("1) User Form!");
		btnSignup.setBounds(50,150,380,25);
		btnSignup.addActionListener(this);
		btnSignup.setFont(f1);
		
		btnSignup1 = new JButton("2) For New Product!");
		btnSignup1.setBounds(50,250,380,25);
		btnSignup1.addActionListener(this);
		btnSignup1.setFont(f1);

		btnSignup2 = new JButton("3) Donation!");
		btnSignup2.setBounds(50,350,380,25);
		btnSignup2.addActionListener(this);
		btnSignup2.setFont(f1);
				
		btnSignup3 = new JButton("4) Enter Entry For New Volunteer!");
		btnSignup3.setBounds(50,450,380,25);
		btnSignup3.addActionListener(this);
		btnSignup3.setFont(f1);

/****************************************END OF MAKING BUTTONS**********************************/

		fr.setJMenuBar(mb);
		fr.getContentPane().add(btnSignup);
		fr.getContentPane().add(btnSignup1);
		fr.getContentPane().add(btnSignup2);
		fr.getContentPane().add(btnSignup3);
		fr.getContentPane().add(pic1);
		fr.setSize(1024,768);
       		fr.setVisible(true);
	}

/**************************************** ACTION EVENT ****************************************/

	public void actionPerformed(ActionEvent ae)
 	{
		if(ae.getSource()==mProductPurchase)
		{
	   	new PurchaseScreen();
	   	}
		if(ae.getSource()==mCustomerBill)
	   	{
	   	new CustomerBill();
	   	}
		if(ae.getSource()==mProductReport)
	   	{
	   	new ProductReport();
	   	}
		if(ae.getSource()==mOutCorporateReport)
	   	{
	   	new CorporateReport();
		}
		if(ae.getSource()==mEventReport)
	   	{
	   	new EventReport();
		}
		if(ae.getSource()==mUpVol)
	   	{
	   	new VolunteerUpdate();
		}
		if(ae.getSource()==mUpUser)
	   	{
	   	new UserUpdate();
		}
		if(ae.getSource()==mDelVol)
	   	{
	   	new VolunteerDelete();
		}		
	   	if(ae.getSource()==mDelUser)
	    	{
	      	new UserDelete();
		}	
		if(ae.getSource() == btnSignup)
		{
		new UserMaster();
		}
		if(ae.getSource() == btnSignup1)
		{
		new ProductMaster();
		}
		if(ae.getSource() == btnSignup2)
		{
		new DonationMaster();
		}
		if(ae.getSource() == btnSignup3)
		{
		new VolunteerMaster();
		}
	}
	


	public static void main(String arg[])
	{
		new Mainscreen();
	}
}
