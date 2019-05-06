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
import java.awt.Color;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

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
		setBounds(100, 100, 720, 428);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setBounds(412, 116, 78, 16);
		contentPane.add(lblUsername);
		
		username = new JTextField();
		username.setBounds(412, 143, 254, 29);
		contentPane.add(username);
		username.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(412, 187, 78, 16);
		contentPane.add(lblPassword);
		
		password = new JPasswordField();
		password.setBounds(412, 214, 254, 29);
		contentPane.add(password);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					String query="insert into userAccount(username,password) values(?,?);";
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
		btnSubmit.setBounds(415, 299, 97, 25);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBackground(SystemColor.activeCaption);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancel.setBounds(547, 299, 97, 25);
		contentPane.add(btnCancel);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBackground(Color.ORANGE);
		panel.setBounds(0, 0, 346, 499);
		contentPane.add(panel);
		
		JLabel label = new JLabel("Does");
		label.setHorizontalAlignment(SwingConstants.LEFT);
		label.setFont(new Font("Tahoma", Font.PLAIN, 38));
		label.setBounds(134, 31, 231, 46);
		panel.add(label);
		
		JLabel label_1 = new JLabel("This");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 38));
		label_1.setBounds(64, 74, 116, 90);
		panel.add(label_1);
		
		JLabel label_2 = new JLabel("Game");
		label_2.setFont(new Font("Tahoma", Font.PLAIN, 38));
		label_2.setBounds(179, 155, 124, 46);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("Suck?");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 38));
		label_3.setBounds(82, 212, 144, 69);
		panel.add(label_3);
		
		JLabel lblRegister = new JLabel("DoesThisGameSuck Register");
		lblRegister.setForeground(Color.WHITE);
		lblRegister.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRegister.setBounds(378, 33, 347, 40);
		contentPane.add(lblRegister);
	}
}