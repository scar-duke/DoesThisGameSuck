import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

public class SQLRequest {
	private static Connection connection = connect.dbConnector();

	public static String getpassword(String username) {
		try {
			String query = "SELECT password FROM user WHERE username = " + username +" ;";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			String pass = rs.getString(1);
			rs.close();
			pst.close();
			return pass;
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	public static boolean addGameWishlist(int userID, int gameID) {
		if(!checkWishlist(userID, gameID))
			return false;
		
		try {
			String query = "INSERT INTO wishlist(userid, gameid) values(" + userID + ", " + gameID + ") ;";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			rs.close();
			pst.close();
			return true;
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static boolean removeGameWishlist(int userID, int gameID) {
		if(!checkWishlist(userID, gameID))
			return false;
		
		try {
			String query = "DELETE FROM wishlist WHERE userid = " + userID + " AND gameid = " + gameID + " ;";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			rs.close();
			pst.close();
			return true;
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	private static boolean checkWishlist(int userID, int gameID) {
		try {
			String query = "SELECT userid, gameid FROM wishlist WHERE userid = " + userID + " AND gameid = " + gameID + " ;";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			rs.next();
			String result = rs.getString(1);
			rs.close();
			pst.close();
			
			if(result.equals(""))
				return false;
			else
				return true;
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static ArrayList<Integer> getWishlist(int userID) {
		try {
			String query = "SELECT gameid FROM wishlist WHERE userid = " + userID + " ;";
			PreparedStatement pst = connection.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			
			int columnCount = rs.getMetaData().getColumnCount();
			ArrayList<Integer> wishlist = new ArrayList<Integer>(columnCount);
			
			while(rs.next()) {
				wishlist.add(Integer.parseInt(rs.getString(1)));
			}
			
			rs.close();
			pst.close();
			
			return wishlist;
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		ArrayList<Integer> nullList = new ArrayList<Integer>();
		return nullList;
	}

}