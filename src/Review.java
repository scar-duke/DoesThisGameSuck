
public class Review {

	UserAccount submittedUser;
	
	String text;
	String date;
	int rating;
	
	public Review(UserAccount submitter, String reviewText, String reviewDate, int reviewRating) {
		submittedUser = submitter;
		text = reviewText;
		date = reviewDate;
		rating = reviewRating;
	}
}
