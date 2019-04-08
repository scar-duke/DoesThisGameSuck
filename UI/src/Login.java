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

public class Login {

	private JFrame frame;
	private JTextField usename;
	private JPasswordField password;
	private static String[] account;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		account=new String[2];
		account[0]="caoz7";
		account[1]="123456789";
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
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Tahoma", Font.BOLD, 12));
		frame.setBounds(100, 100, 574, 437);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="select * from user where username=? and password=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, usename.getText());
					pst.setString(2, password.getText());
					ResultSet rs=pst.executeQuery();
					int count=0;
					while(rs.next()) {
						count+=1;
					}
					if(count==1) {
						JOptionPane.showMessageDialog(null,"Username and password is correct");
					}
					else if(count>1)
					{
						JOptionPane.showMessageDialog(null, "Duplicate Username and password");
					}
					else {
						JOptionPane.showMessageDialog(null, "Username and password is not correct. Try Again");
					}
					rs.close();
					pst.close();
				}catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
		});
		btnNewButton.setBounds(160, 232, 89, 23);
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
		usename.setBounds(160, 113, 264, 23);
		frame.getContentPane().add(usename);
		usename.setColumns(10);
		
		password = new JPasswordField();
		password.setEchoChar('*');
		password.setBounds(160, 167, 264, 23);
		frame.getContentPane().add(password);
		
		JLabel lblDoesThisGames = new JLabel("Does This Game Suck");
		lblDoesThisGames.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblDoesThisGames.setBounds(174, 37, 234, 36);
		frame.getContentPane().add(lblDoesThisGames);
		
		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setBounds(87, 115, 69, 19);
		frame.getContentPane().add(lblUserName);
		
		JLabel lblNewLabel = new JLabel("Password");
		lblNewLabel.setBounds(87, 169, 63, 19);
		frame.getContentPane().add(lblNewLabel);
	}
}
