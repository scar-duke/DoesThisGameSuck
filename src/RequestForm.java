
public class RequestForm {
	
	public Game requestedGame;
	public String gamePublisher;
	public String requestOwner;			//Change to UserAccount?
	
	public boolean deleteRequest() {	//Deleting an object from inside its class is neither possible nor is it necessary.
		return false;
	}
}
