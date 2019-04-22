import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.sql.Array;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextField;



import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class Login {

	private static JFrame frame;
	private JTextField usename;
	private JPasswordField password;
	private static String[] account;
	int xx,xy;
	int userid;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection=null;
	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
		connection=connect.dbConnector();
		
		
	}
	

	/**
	 * Initialize the contents of the frame.
	 */
	
	private int getcount() {
		int count=0;
		try {
			String query="select * from user where username=? and password=?";  //from user where username= xx and !(1=1 and password=1)";
			String nam="select userid from user where username=?";
			PreparedStatement pst=connection.prepareStatement(query);
			PreparedStatement pst1=connection.prepareStatement(nam);
			pst1.setString(1, usename.getText());
			pst.setString(1, usename.getText());
			pst.setString(2, password.getText());
			ResultSet rs=pst.executeQuery();
			ResultSet rs1=pst1.executeQuery();
			
			while(rs.next()) {
				count+=1;
			}
			userid=rs1.getInt(count);
			rs1.close();
			pst1.close();
			rs.close();
			pst.close();
			
			
		}catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		return count;
	}
	
	
	
	
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.DARK_GRAY);
		frame.getContentPane().setForeground(Color.GRAY);
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.setBounds(100, 100, 712, 466);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setForeground(SystemColor.desktop);
		btnNewButton.setBackground(SystemColor.activeCaption);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

					if(getcount()==1) {
						JOptionPane.showMessageDialog(null,"Username and password is correct");
						frame.setVisible(false);
						search search=new search(userid,usename.getText());
						search.setVisible(true);
					}
					else if(getcount()>1)
					{
						JOptionPane.showMessageDialog(null, "Duplicate Username and password");
					}
					else {
						JOptionPane.showMessageDialog(null, "Username and password is not correct Try Again..");
					}


				
			}
		});
		btnNewButton.setBounds(361, 320, 89, 23);
		frame.getContentPane().add(btnNewButton);
		
		usename = new JTextField();
		usename.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
				}catch(Exception e) {
					JOptionPane.showMessageDialog(null,e);
				}
			}
		});
		usename.setBounds(361, 155, 254, 29);
		frame.getContentPane().add(usename);
		usename.setColumns(10);
		
		password = new JPasswordField();
		password.setBounds(361, 239, 254, 29);
		frame.getContentPane().add(password);
		

		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBounds(361, 115, 160, 29);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(361, 202, 125, 19);
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBackground(SystemColor.activeCaption);
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Reg reg=new Reg();
				reg.setVisible(true);
			}
		});
		btnRegister.setBounds(547, 319, 97, 25);
		frame.getContentPane().add(btnRegister);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				
				xx =e.getX();
				xy =e.getY();
			}
		});
		panel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e) {
				int x = e.getXOnScreen();
				int y = e.getYOnScreen();
				
				
			}
		});
		
		panel.setBackground(Color.ORANGE);
		panel.setForeground(Color.ORANGE);
		panel.setBounds(0, 0, 292, 456);
		frame.getContentPane().add(panel);
		SpringLayout sl_panel = new SpringLayout();
		panel.setLayout(sl_panel);
		
		JLabel label = new JLabel("Does");
		sl_panel.putConstraint(SpringLayout.NORTH, label, 35, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, label, -93, SpringLayout.EAST, panel);
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 38));
		panel.add(label);
		
		JLabel label_1 = new JLabel("This");
		sl_panel.putConstraint(SpringLayout.NORTH, label_1, 100, SpringLayout.NORTH, panel);
		sl_panel.putConstraint(SpringLayout.WEST, label_1, 46, SpringLayout.WEST, panel);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 38));
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Game");
		sl_panel.putConstraint(SpringLayout.SOUTH, label_2, -196, SpringLayout.SOUTH, panel);
		sl_panel.putConstraint(SpringLayout.EAST, label_2, -53, SpringLayout.EAST, panel);
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 38));
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Suck?");
		sl_panel.putConstraint(SpringLayout.NORTH, label_3, 37, SpringLayout.SOUTH, label_2);
		sl_panel.putConstraint(SpringLayout.WEST, label_3, 83, SpringLayout.WEST, panel);
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 38));
		panel.add(label_3);
		
		JLabel lblLogin = new JLabel("DoesThisGameSuck Login");
		lblLogin.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblLogin.setForeground(SystemColor.text);
		lblLogin.setBounds(347, 44, 347, 40);
		frame.getContentPane().add(lblLogin);
		
		JLabel lbl_close = new JLabel("X");
		lbl_close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				System.exit(0);
				
			}
		});
		lbl_close.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lbl_close.setForeground(Color.RED);
		lbl_close.setBounds(698, 11, 46, 14);
		frame.getContentPane().add(lbl_close);
	}
}