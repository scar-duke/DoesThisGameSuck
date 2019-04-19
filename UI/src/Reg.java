import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class Reg extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JPasswordField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reg frame = new Reg();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	Connection connection=null;
	/**
	 * Create the frame.
	 */
	public Reg() {
		connection=connect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 611, 422);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRegistor = new JLabel("Register");
		lblRegistor.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblRegistor.setBounds(237, 40, 123, 35);
		contentPane.add(lblRegistor);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(110, 119, 78, 16);
		contentPane.add(lblUsername);
		
		username = new JTextField();
		username.setBounds(218, 116, 196, 22);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(110, 191, 78, 16);
		contentPane.add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(218, 191, 196, 22);
		contentPane.add(password);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="insert into user(username,password) values(?,?)";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, username.getText());
					pst.setString(2, password.getText());
					pst.execute();
					JOptionPane.showMessageDialog(null, "Register success");
					pst.close();
					setVisible(false);
				}catch (Exception e)
				{
					JOptionPane.showMessageDialog(null, e);
				}
			}
		});
		btnSubmit.setBounds(218, 288, 97, 25);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(338, 288, 97, 25);
		contentPane.add(btnCancel);
	}
}
