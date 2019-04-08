import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class UserAccount {
	
	public String username;
	private String password;
	
	public enum userRole{
		ADMINISTRATOR,
		MODERATOR,
		USER
	}
	
	public Wishlist userWishlist;
	
	public ArrayList<Review> reviewsHistory = new ArrayList<Review>(5);
	
	public boolean writeReview(Game reviewedGame, String writtenReview, int rating) {	//Rely on UI to prevent user from writing multiple reviews for one game.
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

		Review newReview = new Review(this, writtenReview, formatter.format(LocalDate.now()), rating);
		
		reviewedGame.reviews.add(newReview);
		reviewsHistory.add(newReview);
		
		return true;
	}
	
	private boolean confirmPassword(String attempt) {
		if(attempt.equals(password)) {					//Basic, unencrypted stand-in without SQL
			return true;
		}
		
		return false;
	}
	
	public boolean addGameWishlist(Game desiredGame) {
		if(userWishlist.gameList.contains(desiredGame))
			return false;
		else {
			userWishlist.gameList.add(desiredGame);
			return true;
		}
	}
	
	public boolean removeGameWishlist(Game undesiredGame) {
		if(userWishlist.gameList.contains(undesiredGame)) {
			userWishlist.gameList.remove(undesiredGame);
			return true;
		}
		else
			return false;
	}
	
}
