package roborace.client;

public class EventTurn180 implements Event {
    
    private final int playerID;
	
    public EventTurn180(int playerID) {
        this.playerID = playerID;
    }	

    @Override
    public void execute(AnimatedBoard board, InfoPane infoPane) {
        board.getRobotByID(playerID).turn180();
    }
}