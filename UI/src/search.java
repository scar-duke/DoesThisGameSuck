import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class search extends JFrame {

	private JPanel contentPane;
	private JTextField text;
	Connection connection=null;
	private JLabel lblNewLabel;
	private static int userid;
	private static String usern="";

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
	public search(int userid,String usern) {
		this.usern=usern;
		this.userid=userid;
		int count=0;
		connection=connect.dbConnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 602, 425);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		text = new JTextField();
		text.setBounds(166, 110, 199, 22);
		contentPane.add(text);
		text.setColumns(10);
		
		JButton search = new JButton("Search");
		search.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int gameid = 0;
					int count=0;;
					String query="select gameid from game where gametitle=?";
					PreparedStatement pst=connection.prepareStatement(query);
					pst.setString(1, text.getText());
					ResultSet rs=pst.executeQuery();
					if(rs.next()) {
						count+=1;
						gameid=rs.getInt(count);
						Game game=new Game(gameid,userid,usern);
						game.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(null, "Can't find any results");
					}
					pst.close();
					setVisible(false);
				}catch (Exception a)
				{
					JOptionPane.showMessageDialog(null, a);
				}
			}
		});
		search.setBounds(420, 109, 97, 25);
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
		
		lblNewLabel = new JLabel("Search");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 17));
		lblNewLabel.setBounds(228, 35, 137, 25);
		contentPane.add(lblNewLabel);
	}

}
