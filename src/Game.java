import java.util.ArrayList;

public class Game {

	public String title;
	public String description;
	public String publisher;
	public String genre;
	public String releaseDate;
	
	public int rating;
	public int gameID;
	
	public ArrayList<Review> reviews = new ArrayList<Review>(10);
	
	public String youtubeLink;
	
	public void averageRating(){
		int total = 0;
		
		for (Review r : reviews){
			total += r.rating;
		}
		
		int avg = total/reviews.size();
		
		rating = avg;
	}
}
