import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class loginFrame extends JFrame implements ActionListener
{ 
Container c;
JLabel l1=new JLabel("User Name:");
JLabel l2=new JLabel("Password:");
JTextField t1=new JTextField();
JPasswordField p=new JPasswordField();
JButton B=new JButton("login");

loginFrame()
{
c=this.getContentPane();
c.setLayout(null);
c.setBackground(Color.GREEN);
l1.setBounds(100,50,100,40);
l2.setBounds(100,150,100,40);
c.add(l1);
c.add(l2);t1.setBounds(250,50,150,40);
c.add(t1);

p.setBounds(250,150,150,40);
c.add(t1);
c.add(p);

B.setBounds(100,250,200,50);
B.addActionListener(this);
c.add(B);
}
public void actionPerformed(ActionEvent e)
{
if(e.getSource()==B)
{
String uname=t1.getText();
String password=p.getText();
if(uname.equals("ABC")&&password.equals("123"))
{
JFileChooser jc=new JFileChooser();
jc.showOpenDialog(c);
File f1=jc
//JOptionPane.showMessageDialog(null,"Login Successful","Validation",1,null);
}
else
{
JOptionPane.showMessageDialog(null,"Invalid User Name or Password","Validation",2,null);
}
}
}
}
class loginDemo
{
public static void main(String args[])
{
loginFrame f=new loginFrame();
f.setBounds(300,50,700,500);
f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
f.setVisible(true);
}
}
