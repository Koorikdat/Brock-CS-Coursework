package roborace.client;

public class EventRevitalize implements Event {
	
    private final int playerID;
    private final int startID;
	
    public EventRevitalize(int playerID, int startID) {
        this.playerID = playerID;
	this.startID = startID;
    }
		
    @Override
    public void execute(AnimatedBoard board, InfoPane infoPane) {
        board.revitalize(playerID,startID);
    }
}
