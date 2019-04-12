
public class Review {

	public int submittedUser;
	
	String text;
	String date;
	int rating;
	
	public Review(int submitter, String reviewText, String reviewDate, int reviewRating) {
		submittedUser = submitter;
		text = reviewText;
		date = reviewDate;
		rating = reviewRating;
	}
}
