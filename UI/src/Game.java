import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Game extends JFrame {

	private JPanel contentPane;
	private String gname="";
	private static int gameid;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game frame = new Game(gameid);
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
	public Game(int gameid) {
		int count=0;
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
			PreparedStatement pst=connection.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			if(rs.next()) {
				count+=1;
				gname=rs.getString(count);
			}
			pst.close();
		}catch (Exception a)
		{
			JOptionPane.showMessageDialog(null, a);
		}
		
		
		
		
		JLabel name = new JLabel(gname);
		name.setBounds(176, 60, 353, 52);
		contentPane.add(name);
	}

}
