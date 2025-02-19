package roborace.client;

public class EventDestroyed implements Event {
    
    private final int playerID;
	
    public EventDestroyed(int playerID) {
	this.playerID = playerID;
    }
	
    @Override
    public void execute(AnimatedBoard board, InfoPane infoPane) {
	board.getRobotByID(playerID).destroyed();
    }
}