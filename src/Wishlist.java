public class Wishlist {
	
	public int listOwner;
	
	private boolean visability;
	
	public boolean addGame(Game desiredGame) {
		return SQLRequest.addGameWishlist(listOwner, desiredGame.gameID);
	}
	
	public boolean removeGame(Game undesiredGame) {
		return SQLRequest.removeGameWishlist(listOwner, undesiredGame.gameID);
	}
}
