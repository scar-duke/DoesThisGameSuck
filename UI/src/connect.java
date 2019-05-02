import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.*;
// From youtube video
public class connect {
	Connection conn=null;
	
	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:database\\database.sqlite.db");
			return conn;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
		
	}
}