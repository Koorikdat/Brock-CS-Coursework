package roborace.client;

import roborace.common.Direction;

public class EventStep implements Event {
	
    private final int playerID;
    private final Direction direction;
	
    public EventStep(int playerID, Direction direction) {
        this.playerID = playerID;
    	this.direction = direction;
    }
	
    @Override
    public void execute(AnimatedBoard board, InfoPane infoPane) {
        board.getRobotByID(playerID).step(direction);
    }
}