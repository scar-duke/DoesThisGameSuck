import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Reg {

	private JFrame frame;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reg window = new Reg();
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
	public Reg() {
		initialize();
		connection=connect.dbConnector();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel reg = new JLabel("Register");
		reg.setFont(new Font("Tahoma", Font.BOLD, 13));
		reg.setBounds(176, 11, 66, 39);
		frame.getContentPane().add(reg);
		
		username = new JTextField();
		username.setBounds(156, 77, 171, 20);
		frame.getContentPane().add(username);
		username.setColumns(10);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblUsername.setBounds(76, 80, 48, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblPassword.setBounds(78, 128, 46, 14);
		frame.getContentPane().add(lblPassword);
		
		password = new JTextField();
		password.setBounds(156, 125, 171, 20);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="insert into user(username,password) values(?,?);";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, username.getText());
					pst.setString(2, password.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "Register success");
					pst.close();
				}catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
				
			}
			
		});
		submit.setBounds(156, 192, 89, 23);
		frame.getContentPane().add(submit);
	}
}
