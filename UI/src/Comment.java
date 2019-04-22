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

public class Comment extends JFrame {

	Connection connection=null;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	private static String gname="";
	private static int gameid;
	private static int userid;
	private static String usern="";
	private String text="";
	private JTextField textField;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Comment frame = new Comment(gameid,gname,userid,usern);
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
	
	
		
	private String getusername(int id) {
		String x="";
		try {
			String query="select username from user where userid=?";

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
	
	public Comment(int gameid,String gname,int userid,String usern) {
		connection=connect.dbConnector();
		this.gameid=1;
		this.gname=gname;
		this.userid=8;
		this.usern=usern;
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
		DefaultListModel dlm=new DefaultListModel();
		try {
			String query="select reviewtext, userid from reviews where gameid=?";
			int count=0;

			PreparedStatement pst=connection.prepareStatement(query);
			pst.setInt(1, gameid);
			ResultSet rs=pst.executeQuery();
			while(rs.next()) {
				dlm.addElement(getusername(rs.getInt(2))+" : "+rs.getString(1)+"\n");
			}
			
			list.setModel(dlm);
			
			rs.close();
			pst.close();

		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}
		
		textField = new JTextField();
		textField.setBounds(10, 387, 518, 35);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement stmt = null;
				try {
					stmt=connection.createStatement();
					String q="INSERT INTO reviews (gameid, userid,reviewtext) VALUES ("+gameid+", "+userid+", \'"+textField.getText()+"\')";
					stmt.executeUpdate(q);
					dlm.addElement(usern+" : "+textField.getText()+"\n");
					list.setModel(dlm);
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
		submit.setBounds(554, 393, 89, 23);
		contentPane.add(submit);
		
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
		
	}
}
