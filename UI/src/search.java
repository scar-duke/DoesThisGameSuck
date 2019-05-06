import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;

public class search extends JFrame {

	private JPanel contentPane;
	private JTextField text;
	Connection connection=null;
	private JLabel lblNewLabel;
	private static int userid;
	private static String usern="";
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					search frame = new search(userid,usern);
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
	JList list = new JList();
	DefaultListModel dlm=new DefaultListModel();
	public search(int userid,String usern) {
		this.usern=usern;
		this.userid=userid;
		int count=0;
		connection=connect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 428);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		text = new JTextField();
		text.setBounds(138, 155, 373, 22);
		contentPane.add(text);
		text.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"gameTitle", "publisher","genre"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(31, 156, 97, 20);
		contentPane.add(comboBox);
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(String.valueOf(comboBox.getSelectedItem()).equals("gameTitle")) {
					searchResult sr=new searchResult(userid,usern,"gameTitle",text.getText());
					sr.setVisible(true);
					setVisible(false);
					
				}
				
				if(String.valueOf(comboBox.getSelectedItem()).equals("publisher")) {
					searchResult sr=new searchResult(userid,usern,"publisher",text.getText());
					sr.setVisible(true);
					setVisible(false);
				}
				
				if(String.valueOf(comboBox.getSelectedItem()).equals("genre")) {
					searchResult sr=new searchResult(userid,usern,"genre",text.getText());
					sr.setVisible(true);
					setVisible(false);
				}
			}
		});
		search.setBounds(547, 154, 97, 25);
		contentPane.add(search);
		
		JButton wish = new JButton("WishList");
		wish.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				WishList wl=new WishList(userid,usern);
				wl.setVisible(true);
				
			}
		});
		wish.setBounds(10, 11, 89, 23);
		contentPane.add(wish);
		
		lblNewLabel = new JLabel("Games Search");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblNewLabel.setBounds(241, 58, 259, 25);
		contentPane.add(lblNewLabel);
		
		panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		panel.setBounds(231, 46, 202, 50);
		contentPane.add(panel);
		
		
//		list.setBounds(138, 215, 373, 152);
//		contentPane.add(list);
		toprate();
		JScrollPane scrlpane=new JScrollPane(list);
		scrlpane.setBounds(138, 215, 373, 152);
		contentPane.add(scrlpane);
		
		JButton btnNewButton = new JButton("GO");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int id=0;
				if(!list.isSelectionEmpty()) {
					
					try {
						String q="select gameID from game where gameTitle=\""+list.getSelectedValue()+"\"";
						PreparedStatement p=connection.prepareStatement(q);
						ResultSet r=p.executeQuery();
						id=r.getInt(1);
						Game g=new Game(id,userid,usern);
						g.setVisible(true);
						setVisible(false);
						
						r.close();
						p.close();
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
				else
					JOptionPane.showMessageDialog(null, "Select something");

			}
		});
		btnNewButton.setBounds(555, 293, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("<html>Top Rated Games :</html>");
		lblNewLabel_1.setBounds(10, 246, 99, 70);
		contentPane.add(lblNewLabel_1);
		

		
	}
	
	private void toprate() {
		try {
			String query="select gameTitle from game order by rating DESC";  //from user where username= xx and !(1=1 and password=1)";
			
			PreparedStatement pst=connection.prepareStatement(query);
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				dlm.addElement(rs.getString(1));
			}
			list.setModel(dlm);
			rs.close();
			
			pst.close();
		
		

		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
		
	}
}