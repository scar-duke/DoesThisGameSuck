import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class Tester {

	@Test
	public void testWriteReview() {
		public UserAccount userTest = new UserAccount();
		public Game gameTest = new Game();
		public Game gameTest2 = new Game();
		
		assertTrue(userTest.writeReview(gameTest, "REVIEW CONTENT TEST", 3));
		
		/*assertFalse(userTest.writeReview(gameTest, "REVIEW CONTENT TEST", 3));
		assertFalse(userTest.writeReview(gameTest, "REVIEW CONTENT TEST", 2));
		assertFalse(userTest.writeReview(gameTest, "REVIEW CONTENT TEST ALTERED", 3));
		assertFalse(userTest.writeReview(gameTest, "REVIEW CONTENT TEST ALTERED", 2)); *///UI should prevent multiple reviews from being written, not the method
		
		assertEquals(1, userTest.reviewsHistory.size());
		
		assertTrue(userTest.writeReview(gameTest2, "REVIEW CONTENT TEST", 3));
		
		assertEquals(2, userTest.reviewsHistory.size());
		
		//Run checks for review text and rating?
	}
	
	@Test
	public void testAverageRating() {
		UserAccount userTest = new UserAccount();
		UserAccount userTest2 = new UserAccount();
		UserAccount userTest3 = new UserAccount();
		UserAccount userTest4 = new UserAccount();
		UserAccount userTest5 = new UserAccount();
		Game gameTest = new Game();
		
		userTest.writeReview(gameTest, "REVIEW CONTENT TEST", 3);
		
		assertEquals(3, gameTest.rating);
		
		userTest2.writeReview(gameTest, "REVIEW CONTENT TEST", 5);
		
		assertEquals(4, gameTest.rating);
		
		userTest3.writeReview(gameTest, "REVIEW CONTENT TEST", 1);
		
		assertEquals(3, gameTest.rating);
		
		userTest4.writeReview(gameTest, "REVIEW CONTENT TEST", 2);
		
		assertEquals(3, gameTest.rating);
		
		userTest5.writeReview(gameTest, "REVIEW CONTENT TEST", 1);
		
		assertEquals(2, gameTest.rating);
	}

}
