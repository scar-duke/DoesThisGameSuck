import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReviewTester {
	Connection connection=null;
	@BeforeEach
	void cleanTable() {
		Connection connection = connect.dbConnector();
		
		try {
			String query = "DELETE FROM reviews;";						//Query to clean database of preexisting reviews
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
		
		Review test0 = new Review(0, "GAMETEST0", 0, "USERTEST0", 3,"");		//Why are the names used in the constructor, rather than just the ids? //Because we can directly use name of game for title of the page and name of user for their review. we don't need to repeat the get name method.
		
		String result = null;												//Defined as the scope of variables declared in try is limited to that block
		
		try {
			String query = "SELECT * FROM reviews;";						//Query to acquire the new review from the database
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
	
	@Test
	void testGetRating() {
		Review test1 = new Review(1, "GAMETEST1", 1, "USERTEST1", 3,"");		//Various definitions and checks to ensure that the correct review average is returned
		
		assertEquals(3, Review.getRating(1));
		
		Review test2 = new Review(1, "GAMETEST1", 2, "USERTEST2", 5,"");

		assertEquals(4, Review.getRating(1));

		Review test3 = new Review(1, "GAMETEST1", 3, "USERTEST3", 2,"");
		
		assertEquals(3, Review.getRating(1));
		
		Review test4 = new Review(1, "GAMETEST1", 4, "USERTEST4", 1,"");
		
		assertEquals(2, Review.getRating(1));
		
		Review test4b = new Review(1, "GAMETEST1", 4, "USERTEST4", 5,"");
                
        assertEquals(2, Review.getRating(1));
		
		Review test5 = new Review(2, "GAMETEST2", 5, "USERTEST5", 5,"");		//Definition and check for one game's average not affected that of another game
		
		assertEquals(5, Review.getRating(2));
		
		Review test5b = new Review(2, "GAMETEST2", 6, "USERTEST5", 1,"");
		assertEquals(3, Review.getRating(2));
	}

}
