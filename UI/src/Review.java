import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JScrollBar;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JList;

public class Review extends JFrame {

	static Connection connection=null;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	private static String gname="";
	private static int gameid;
	private static int userid;
	private static String usern="";
	private String text="";
	private static int rate;
	private static String comment="";
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Review frame = new Review(gameid,gname,userid,usern,rate,comment);
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
	
	private String getusername(int userid) {//get username by user id
		String x="";
		try {
			String query="select username from userAccount where userID=?";

			PreparedStatement pst=connection.prepareStatement(query);
			pst.setInt(1, userid);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				x=rs.getString(1);
			}
			
			rs.close();
			pst.close();

		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}
		return x;
	}
		

	DefaultListModel dlm=new DefaultListModel();
	
	public Review(int gameid,String gname,int userid,String usern,int rate,String comment) {//Review class carry game id, game name, userid, user name and rate
		connection=connect.dbConnector();
		this.gameid=gameid;
		this.gname=gname;
		this.userid=userid;
		this.usern=usern;
		this.rate=rate;
		this.comment=comment;
		if(!isadd()) {//check does this user add review to this game before
			addreview(rate,comment);
		}//if this user doesn't add review before than add review to the game
		int r=getRating(gameid);
		
		addratetogame(r);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 681, 492);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel(gname);
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Tahoma", Font.BOLD, 20));
		title.setBounds(244, 11, 117, 35);
		contentPane.add(title);
		
		
		
		JList list = new JList();
		list.setBounds(10, 57, 633, 280);
		contentPane.add(list);
		
		try {// show all the reviews of this game
			String query="select reviewText,rating,userID from review where gameID=?";
			int count=0;

			PreparedStatement pst=connection.prepareStatement(query);
			pst.setInt(1, gameid);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
			    if(rs.getString(1)!=null)
				dlm.addElement(getusername(rs.getInt(3))+"("+rs.getInt(2)+") : "+rs.getString(1)+"\n");// with username and their rate for this game
			}
			
			list.setModel(dlm);
			
			rs.close();
			pst.close();

		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Game g=new Game(gameid,userid,usern);
				g.setVisible(true);
				setVisible(false);
			}
		});
		btnBack.setBounds(10, 11, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lblNewLabel = new JLabel("Rate :");
		lblNewLabel.setBounds(497, 22, 37, 21);
		contentPane.add(lblNewLabel);
		
		JLabel rr = new JLabel("<html>"+r+"<html>");
		rr.setBounds(544, 25, 46, 14);
		contentPane.add(rr);
		
		
		
	}
	
	private void writeReview(String review) {//add review text to the game
		Statement stmt = null;
		try {
			stmt=connection.createStatement();
			String q="UPDATE review set reviewText= \'"+review+"\'"+" where gameID="+gameid+" and userID="+userid;
			stmt.executeUpdate(q);
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
	
	private void addreview(int rate,String comment) {
		Statement stmt = null;
		try {
			stmt=connection.createStatement();
			String q="INSERT INTO review (gameID,userID,rating,reviewText) VALUES ("+gameid+", "+userid+","+rate+",\'"+comment+"\')";
			stmt.executeUpdate(q);
			
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
	
	static int getRating(int gameid) {// get rating by id
		int averagerate=0;
		int count=0;
		try {
			String query="select rating from review where gameID=?";
			
			int totalrate=0;
			PreparedStatement pst=connection.prepareStatement(query);
			pst.setInt(1, gameid);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				count+=1;
				totalrate+=rs.getInt(1);
			}
			if(count==0) {
				averagerate=0;}
			else {
			averagerate=totalrate/count;}
			
			rs.close();
			pst.close();

		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}
		return averagerate;
	}
	
	private boolean isadd() {//check this user have review before or not
		boolean is=true;
		int count=0;
		try {
			String query="select * from review where gameID=? and userID=?";
			

			PreparedStatement pst=connection.prepareStatement(query);
			pst.setInt(1, gameid);
			pst.setInt(2, userid);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				count++;
			}
			if(count==0)
				is=false;
			
			rs.close();
			pst.close();

		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}
		return is;
	}
	
	private String gettext() {
		String text="";
		try {
			String query="select reviewText from review where gameID=? and userID=?";
			int count=0;

			PreparedStatement pst=connection.prepareStatement(query);
			pst.setInt(1, gameid);
			pst.setInt(2, userid);
			ResultSet rs=pst.executeQuery();
			if(rs.next())
				text=rs.getString(1);
			
			rs.close();
			pst.close();

		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}
		return text;
	}
	
	private void addratetogame(int rate) {
		Statement stmt = null;
		try {
			stmt=connection.createStatement();
			String q="UPDATE game set rating="+rate+" where gameID="+gameid;
			stmt.executeUpdate(q);
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

