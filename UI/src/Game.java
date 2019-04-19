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
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Game extends JFrame {

	private JPanel contentPane;
	private static int gameid;
	private static int userid;
	private String gname="";
	private String described="";
	private String dated="";
	private static String usern="";
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
	/**
	 * Create the frame.
	 */
	public Game(int gameid, int userid,String usern) {
		this.userid=userid;
		this.usern=usern;
		int count=0;
		int count1=0;
		int count2=0;
		this.gameid=gameid;
		connection=connect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 748, 581);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		try {
			String query="select gametitle from game where gameid="+gameid;
			String queryd="select description from game where gameid="+gameid;
			String querydate="select publisher from game where gameid="+gameid;
			
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				count+=1;
				gname=rs.getString(count);
			}
			
			
			PreparedStatement pstd=connection.prepareStatement(queryd);
			ResultSet rsd=pstd.executeQuery();
			if(rsd.next()) {
				count1+=1;
				described=rs.getString(count1);
			}
			
			PreparedStatement pstdate=connection.prepareStatement(querydate);
			ResultSet rsdate=pstdate.executeQuery();
			if(rsdate.next()) {
				count2+=1;
				dated=rsd.getString(count2);
			}
			
			
			rs.close();
			rsd.close();
			rsdate.close();
			pst.close();
			pstd.close();
			pstdate.close();
			
		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}

		
		JLabel title = new JLabel(gname);
		title.setFont(new Font("Tahoma", Font.BOLD, 17));
		title.setBounds(147, 11, 128, 26);
		contentPane.add(title);
		
//		JLabel date = new JLabel(described);
//		date.setBounds(319, 20, 46, 14);
//		contentPane.add(date);
		
		JLabel de = new JLabel("<html>"+dated+"</html>");
		de.setBounds(21, 108, 648, 378);
		contentPane.add(de);
		
		JButton Search = new JButton("Search");
		Search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				setVisible(false);
				search search=new search(userid,usern);
				search.setVisible(true);
				
			}
		});
		Search.setBounds(10, 11, 89, 23);
		contentPane.add(Search);
		
		JButton add = new JButton("Add ");
		add.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Statement stmt = null;
					
					try {
						stmt=connection.createStatement();
						String q="INSERT INTO wishlist (gameid, userid) VALUES ("+gameid+", "+userid+")";
						stmt.execute(q);
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
			
		});
		add.setBounds(622, 11, 89, 23);
		contentPane.add(add);
	}
}

