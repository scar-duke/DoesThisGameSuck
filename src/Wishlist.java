import java.util.ArrayList;

public class Wishlist {

	public ArrayList<Game> gameList = new ArrayList<Game>(5);
	
	public int listowner;
	
	private boolean visability;
	
	public boolean addGame(Game desiredGame) {
		if(gameList.contains(desiredGame))
			return false;
		
		else {
			gameList.add(desiredGame);
			return true;
		}
	}
	
	public boolean removeGame(Game undesiredGame) {
		if(gameList.contains(undesiredGame)) {
			gameList.remove(undesiredGame);
			return true;
		}
		
		else
			return false;
	}
}
