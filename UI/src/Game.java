    
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JComboBox;

public class Game extends JFrame {

	private JPanel contentPane;
	private int rate=0;
	private static int gameid;
	private static int userid;
	private String gname="";
	private String described="";
	private String dated="";
	private static String usern="";
	private String year="";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game(gameid,userid,usern);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	Connection connection=null;
	private JTextField comment;
	/**
	 * Create the frame.
	 */
	
	private void gamename() {//get the game name from database by game id
		try {
			String query="select gametitle from game where gameid="+gameid;
			int count=0;
			
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				count+=1;
				
			}
			gname=rs.getString(count);

			
			rs.close();
			pst.close();

		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}
	}
	
	private String years() { //get the releasedata of the game from database by gameid
		try {
			String query="select releasedata from game where gameid="+gameid;
			int count=0;
			
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				count+=1;
				
			}
			year=rs.getString(count);

			
			rs.close();
			pst.close();

		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}
		return year;
	}
	
	private void data() {//get the description of the game from database by game id
		int count=0;
		try {

			String queryd="select description from game where gameid="+gameid;

			

			
			
			PreparedStatement pstd=connection.prepareStatement(queryd);
			ResultSet rsd=pstd.executeQuery();
			if(rsd.next()) {
				count+=1;
				
			}
			described=rsd.getString(count);
			
			

			rsd.close();

			pstd.close();

		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}
	}
	
	private void getpublish() {//get the publisher of the game from database by game id
		int count2=0;
		try {

			String querydate="select publisher from game where gameid="+gameid;

			PreparedStatement pstdate=connection.prepareStatement(querydate);
			ResultSet rsdate=pstdate.executeQuery();
			if(rsdate.next()) {
				count2+=1;

			}
			
			dated=rsdate.getString(count2);
			rsdate.close();

			pstdate.close();
			
		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}
	}
	
	public Game(int gameid, int userid,String usern) {//Game class carry a game id, user id and user name
		this.userid=userid;
		this.usern=usern;
		this.gameid=gameid;
		connection=connect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 753, 581);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		data();
		getpublish();
		gamename();

		
		JLabel title = new JLabel(gname);
		title.setFont(new Font("Tahoma", Font.BOLD, 18));
		title.setBounds(271, 28, 128, 26);
		contentPane.add(title);
		
		JLabel date = new JLabel(dated);
		date.setBounds(507, 31, 110, 23);
		contentPane.add(date);
		
		JLabel de = new JLabel("<html>"+described+"</html>");// show the describtion in JLabel
		de.setBackground(Color.WHITE);
		de.setBounds(114, 87, 503, 395);
		contentPane.add(de);
		
		JButton Search = new JButton("Search");
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				search();//back to search page
			}
		});
		Search.setBounds(10, 11, 89, 23);
		contentPane.add(Search);
		
		JButton add = new JButton("Add ");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Statement stmt = null;
				if(exist()) {//if user doesn't have this game in wishlist
				try {
					stmt=connection.createStatement();
					String q="INSERT INTO wishlist (gameid, userid) VALUES ("+gameid+", "+userid+")";
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
				else
					JOptionPane.showMessageDialog(null, "Already have this game in your wish list");
			}
			
		});
		add.setBounds(622, 11, 89, 23);
		contentPane.add(add);
		
		JLabel publish = new JLabel("PUBLISHER:");
		publish.setFont(new Font("Tahoma", Font.PLAIN, 10));
		publish.setBounds(417, 31, 67, 26);
		contentPane.add(publish);
		
		JLabel lblNewLabel = new JLabel("Year of Pulish:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel.setBounds(417, 66, 69, 14);
		contentPane.add(lblNewLabel);
		
		
		JLabel year = new JLabel(years());
		year.setBounds(507, 66, 110, 14);
		contentPane.add(year);
		
		
		
		JComboBox stars = new JComboBox();
		stars.setModel(new DefaultComboBoxModel(new String[] {"0/5","1/5", "2/5","3/5", "4/5","5/5"}));
		stars.setBounds(20, 508, 89, 22);
		contentPane.add(stars);
		
		
		comment = new JTextField();
		comment.setBounds(119, 509, 503, 22);
		contentPane.add(comment);
		comment.setColumns(10);
		
		
		
		JButton btnNewButton = new JButton("Reviews");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(String.valueOf(stars.getSelectedItem()).equals("1/5")) {
					rate=1;
				}
				if(String.valueOf(stars.getSelectedItem()).equals("2/5")) {
					rate=2;
				}
				if(String.valueOf(stars.getSelectedItem()).equals("3/5")) {
					rate=3;
				}
				if(String.valueOf(stars.getSelectedItem()).equals("4/5")) {
					rate=4;
				}
				if(String.valueOf(stars.getSelectedItem()).equals("5/5")) {
					rate=5;
				}
				else
					rate=0;//depend on the combo box above decide what's the value of rate.
				
				Review c=new Review(gameid,gname,userid,usern,rate,comment.getText());
				c.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton.setBounds(638, 508, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel rating = new JLabel(getrate());
		rating.setBounds(10, 45, 128, 31);
		contentPane.add(rating);
		

		
	}
	
	private String getrate() {
		int rate=0;
		int count=0;
		try {
			String query="select rating from game where gameid=?";
			
			int totalrate=0;
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setInt(1, gameid);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				count++;
				rate=rs.getInt(1);
			}
			if(count==0) {
				return "Rate : No reviews yet";}
			
			
			rs.close();
			pst.close();

		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}
		return "Rate : "+rate;
	}
	
	private boolean exist() {// check does the user already has this game in their wish list
		int count2=0;
		try {

			String querydate="select * from wishlist where gameid="+gameid+" and userid="+userid;

			PreparedStatement pstdate=connection.prepareStatement(querydate);
			ResultSet rsdate=pstdate.executeQuery();
			if(rsdate.next()) {
				count2+=1;

			}
			
			dated=rsdate.getString(count2);
			rsdate.close();

			pstdate.close();
			
		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}
		if(count2==0) {
			return true;
		}
		return false;
	}
	
	private void search() {// back to search page
		setVisible(false);
		search search=new search(userid,usern);
		search.setVisible(true);
	}
}


