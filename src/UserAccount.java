
public class UserAccount {
	
	public String username;
	private String password;
	
	public enum userRole{
		ADMINISTRATOR,
		MODERATOR,
		USER
	}
	
	public Review[] reviewsHistory = new Review[100];			//The direct usage of arrays is a point of concern for me; should we switch to another implementation?
	public Wishlist[] userWishlist = new Wishlist[15];
	
	public boolean writeReview(Game reviewedGame, String writtenReview) { //Method should likely contain a rating parameter as well.
		reviewedGame.reviews;
	}
	
	public boolean addToWishlist(Game desiredGame) {
		return false;
	}
	
	private boolean checkPassword(String attempt) {
		if (attempt.equals(password)) {					//Basic, unencrypted stand-in without SQL.
			return true;
		}
		
		return false;
	}
	
}
