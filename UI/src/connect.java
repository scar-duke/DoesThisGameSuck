import java.sql.Connection;
import java.sql.DriverManager;

import javax.swing.*;

public class connect {
	Connection conn=null;
	
	public static Connection dbConnector() {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn=DriverManager.getConnection("jdbc:sqlite:database\\app.sqlite");
			return conn;
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null,e);
			return null;
		}
		
	}
}
