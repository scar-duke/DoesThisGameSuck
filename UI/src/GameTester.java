import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class GameTester {

	Connection connection=null;
	@BeforeEach
	void cleanTable() {
		Connection connection = connect.dbConnector();
		
		try {
			String query = "DELETE FROM review;";						//Query to clean database of preexisting reviews
			PreparedStatement pst = connection.prepareStatement(query);	
			pst.executeUpdate();
			pst.close();
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	void testReview() {														//Assumes that there are no pre-existing reviews
		
		connection = connect.dbConnector();									//Establishes a connection so that data can be pulled from the database
		
		Game test0 = new Game(1, 1,"test");		//Why are the names used in the constructor, rather than just the ids? //Because we can directly use name of game for title of the page and name of user for their review. we don't need to repeat the get name method.
		
		test0.addreview(5,"");
		
		String result = null;												//Defined as the scope of variables declared in try is limited to that block
		
		try {
			String query = "SELECT * FROM review;";						//Query to acquire the new review from the database
			PreparedStatement pst = connection.prepareStatement(query);	
			ResultSet rs = pst.executeQuery();
			rs.next();
			result = rs.getString(1);
			rs.close();
			pst.close();
		} 
		
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		assertTrue(result.length() > 0);									//Conditional to ensure that a review exists in the database
	}

}
