import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class request extends JFrame {

	Connection connection=null;
	private JPanel contentPane;
	private JTextField textField;
	static int userid;
	static String usern="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					request frame = new request(userid,usern);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public request(int userid,String usern) {
		connection=connect.dbConnector();
		this.userid=userid;
		this.usern=usern;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 512, 377);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Game Request");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(176, 28, 133, 16);
		contentPane.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(176, 100, 167, 22);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Game Title");
		lblNewLabel_1.setBounds(83, 103, 81, 16);
		contentPane.add(lblNewLabel_1);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				insertRequest(textField.getText());
			}
		});
		btnSubmit.setBounds(176, 204, 97, 25);
		contentPane.add(btnSubmit);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				WishList wl=new WishList(userid,usern);
				wl.setVisible(true);
				setVisible(false);
			}
		});
		btnCancel.setBounds(285, 204, 97, 25);
		contentPane.add(btnCancel);
	}
	
	private void insertRequest(String gameTitle) {
		Statement stmt = null;
		try {
			stmt=connection.createStatement();
			String q="INSERT INTO gameRequest (gameTitle, userID) VALUES (\'"+gameTitle+"\', "+userid+")";
			stmt.execute(q);
			JOptionPane.showMessageDialog(null, "Successful Add");
			}
		 catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			try {
				stmt.close();

			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
