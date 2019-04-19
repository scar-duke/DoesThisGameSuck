import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class Tester {
	
	@Test
	public void testWriteReview() {
		
		UserAccount userTest = new UserAccount();
		
		Game gameTest = new Game();
		Game gameTest2 = new Game();
		
		userTest.writeReview(gameTest, "REVIEW CONTENT TEST", 3);
		
		assertEquals(1, userTest.reviewsHistory.size());
		
		userTest.writeReview(gameTest2, "REVIEW CONTENT TEST", 3);
		
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
