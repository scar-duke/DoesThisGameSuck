import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;

public class WishList extends JFrame {

	private JPanel contentPane;
	private static int userid;
	private static String usern="";
	int id=0;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WishList frame = new WishList(userid,usern);
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
	Connection connection=null;
	public WishList(int userid,String usern) {
		this.usern=usern;
		connection=connect.dbConnector();
		this.userid=userid;
		String username1=""; 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 726, 603);
		contentPane = new JPanel();
		contentPane.setBackground(Color.ORANGE);
		
		
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel username = new JLabel(usern);
		username.setBounds(10, 11, 151, 14);
		contentPane.add(username);
		
		JLabel lblNewLabel = new JLabel("WishList");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(248, 30, 168, 25);
		contentPane.add(lblNewLabel);
		
		JList list = new JList();
		list.setBounds(223, 83, 226, 405);
		contentPane.add(list);
		DefaultListModel dlm=new DefaultListModel();
		try {
			ArrayList<Integer> gid=new ArrayList<Integer>();
			String query="select g.gametitle from game as g join wishlist as w where w.gameid=g.gameid AND w.userid=?";  //from user where username= xx and !(1=1 and password=1)";
			
			PreparedStatement pst=connection.prepareStatement(query);
			
			pst.setInt(1, userid);
			
			ResultSet rs=pst.executeQuery();
			
			while(rs.next()) {
				dlm.addElement(rs.getString(1));
			}
			list.setModel(dlm);
			
			JButton btnNewButton = new JButton("Go");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					if(!list.isSelectionEmpty()) {
						
						try {
							String q="select gameid from game where gametitle=\""+list.getSelectedValue()+"\"";
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
			btnNewButton.setBounds(533, 147, 136, 84);
			contentPane.add(btnNewButton);
			
			
			
			rs.close();
	
			pst.close();

			}
		catch (Exception e)
			{
		JOptionPane.showMessageDialog(null, e);
			}
		
		JButton delete = new JButton("Delete");
		delete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!list.isSelectionEmpty()) {
					PreparedStatement p=null;
					ResultSet r=null;
					PreparedStatement p1=null;
					Statement stmt = null;
					
					try {
						String q="select gameid from game where gametitle=\""+list.getSelectedValue()+"\"";
						p=connection.prepareStatement(q);
						r=p.executeQuery();
						id=r.getInt(1);
						q="delete from wishlist where gameid="+id+" and userid= "+userid;
						stmt=connection.createStatement();
						stmt.execute(q);
						JOptionPane.showMessageDialog(null, "Delete successfull");
						int index=list.getSelectedIndex();
						if(index==-1) {
							JOptionPane.showMessageDialog(null, "Select Something");
						}
						else {
							dlm.remove(index);
							list.setModel(dlm);
						}
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}finally {
						try {
							stmt.close();
							p.close();
							r.close();

						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}else
					JOptionPane.showMessageDialog(null, "Select something");

			}
		});
		delete.setBounds(43, 147, 118, 84);
		contentPane.add(delete);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				search s=new search(userid, usern);
				s.setVisible(true);
				setVisible(false);
			}
		});
		btnNewButton_1.setBounds(611, 7, 89, 23);
		contentPane.add(btnNewButton_1);
	}
}

