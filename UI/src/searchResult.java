import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JList;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class searchResult extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	static String title="";
	static int userid;
	static String usern="";
	static String result="";
	Connection connection=null;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					searchResult frame = new searchResult(userid,usern,title,result);
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
	public searchResult(int userid,String usern,String title,String result) {
		this.userid=userid;
		this.usern=usern;
		this.title=title;
		this.result=result;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 754, 498);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Search Results");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(237, 22, 229, 38);
		contentPane.add(lblNewLabel);
		
		JComboBox sort = new JComboBox();
		sort.setModel(new DefaultComboBoxModel(new String[] {"rating", "releaseDate"}));
		sort.setSelectedIndex(0);
		sort.setBounds(10, 70, 75, 20);
		contentPane.add(sort);
		
		getresult( title, result,"rating");
		
		sort.addItemListener(new ItemListener() {
	        public void itemStateChanged(ItemEvent e) {
	           if(String.valueOf(sort.getSelectedItem()).equals("rating"))
	            getresult( title, result,"rating");
	           if(String.valueOf(sort.getSelectedItem()).equals("releaseDate"))
	        	   getresult( title, result,"releaseDate");
	            
	            
	        }
	    });

		if(String.valueOf(sort.getSelectedItem()).equals("releaseDate")) {
			getresult( title, result,"releaseDate");
			
		}
		
		else {
			getresult( title, result,"rating");
			
		}
		
		JScrollPane scrlpane=new JScrollPane(list);
		scrlpane.setBounds(93, 71, 562, 232);
		contentPane.add(scrlpane);
		
		JButton go = new JButton("GO");
		go.addActionListener(new ActionListener() {
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
		go.setBounds(305, 342, 89, 23);
		contentPane.add(go);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search s=new search(userid,usern);
				s.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setBounds(0, 11, 89, 23);
		contentPane.add(btnBack);
		
		
	}
	
	public void getresult(String title,String result,String sort) {
		dlm.removeAllElements();
		connection=connect.dbConnector();
		if(sort.equals("rating")) {
		try {
			int gameid = 0;
			int count=0;;
			String query="select gameTitle from game where "+title+" = ? ORDER BY "+sort+" DESC";
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setString(1, result);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				dlm.addElement(rs.getString(1));
			}
			list.setModel(dlm);
			rs.close();
			pst.close();
			
		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}}
		else {
			if(sort.equals("releaseDate")) {
				try {
					int gameid = 0;
					int count=0;;
					String query="select gameTitle from game where "+title+" = ? order by cast(releaseDate as datetime) DESC";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, result);
					ResultSet rs=pst.executeQuery();
					while(rs.next()) {
						dlm.addElement(rs.getString(1));
					}
					list.setModel(dlm);
					rs.close();
					pst.close();
					
				}catch (Exception a)
				{
					JOptionPane.showMessageDialog(null, a);
				}
		}
	}
	}
}
