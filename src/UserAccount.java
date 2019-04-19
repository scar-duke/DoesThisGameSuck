import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserAccount {

	public int userID;
	
	public String username;
	private String password;
	
	public Role userRole;
	
	public ArrayList<Review> reviewsHistory = new ArrayList<Review>(5);
	
	public boolean writeReview(Game reviewedGame, String writtenReview, int rating) {	//Rely on UI to prevent user from writing multiple reviews for one game.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		Review newReview = new Review(this.userID, writtenReview, formatter.format(LocalDate.now()), rating);
		
		reviewedGame.reviews.add(newReview);
		reviewsHistory.add(newReview);
		
		reviewedGame.averageRating();
		
		return true;
	}
	
}
