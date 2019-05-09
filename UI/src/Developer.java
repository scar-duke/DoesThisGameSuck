import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Developer extends JFrame {

	private JPanel contentPane;
	Connection connection=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Developer frame = new Developer();
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
	JList requestGame = new JList();
	JList users = new JList();
	DefaultListModel dlm=new DefaultListModel();
	DefaultListModel dlm2=new DefaultListModel();
	public Developer() {
		connection=connect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 756, 488);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		getrequest();
		JScrollPane scrlpane=new JScrollPane(requestGame);
		scrlpane.setBounds(52, 71, 228, 242);
		contentPane.add(scrlpane);
		
		getusers();
		JScrollPane scrlpane2=new JScrollPane(users);
		scrlpane2.setBounds(447, 71, 234, 242);
		contentPane.add(scrlpane2);
		
		JButton btnNewButton = new JButton("Block");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!users.isSelectionEmpty()) {
					blockUser((String) users.getSelectedValue());
					
				}
				else
					JOptionPane.showMessageDialog(null, "Select something");
			}
		});
		btnNewButton.setBounds(463, 347, 97, 25);
		contentPane.add(btnNewButton);
		
		
		
		JLabel lblNewLabel = new JLabel("Request Games");
		lblNewLabel.setBounds(109, 37, 114, 20);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Unblock");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!users.isSelectionEmpty()) {
					unblockUser((String) users.getSelectedValue());
				}
				else
					JOptionPane.showMessageDialog(null, "Select something");
			}
		});
		btnNewButton_1.setBounds(572, 347, 97, 25);
		contentPane.add(btnNewButton_1);
		
		JLabel lblUsers = new JLabel("Users");
		lblUsers.setBounds(538, 37, 114, 20);
		contentPane.add(lblUsers);
	}
	
	private void getrequest() {
		try {
			String query="SELECT gameTitle from gameRequest";//from user where username= xx and !(1=1 and password=1)";
			
			PreparedStatement pst=connection.prepareStatement(query);
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				dlm.addElement(rs.getString(1));
			}
			requestGame.setModel(dlm);
			rs.close();
			
			pst.close();
		
		

		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	private void blockUser(String usern) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String query="UPDATE userAccount SET userRole = \'block\' where username= \'"+usern+"\'";//from user where username= xx and !(1=1 and password=1)";
			stmt.execute(query);
			JOptionPane.showMessageDialog(null, "Block Successful");
			
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	private void unblockUser(String usern) {
		Statement stmt = null;
		try {
			stmt = connection.createStatement();
			String query="UPDATE userAccount SET userRole = \'User\' where username= \'"+usern+"\'";//from user where username= xx and !(1=1 and password=1)";
			stmt.execute(query);
			JOptionPane.showMessageDialog(null, "Unblock Successful");
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	private void getusers() {
		try {
			String query="SELECT username from userAccount where userRole <> \'Developer\'";//from user where username= xx and !(1=1 and password=1)";
			
			PreparedStatement pst=connection.prepareStatement(query);
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				dlm2.addElement(rs.getString(1));
			}
			users.setModel(dlm2);
			rs.close();
			
			pst.close();
		
		

		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
	
	
}
