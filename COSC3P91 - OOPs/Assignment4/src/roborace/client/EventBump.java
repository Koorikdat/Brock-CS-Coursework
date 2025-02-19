package roborace.client;

import roborace.common.Direction;

public class EventBump implements Event {
	
    private final int playerID;
    private final Direction direction;
	
    public EventBump(int playerID, Direction direction) {
        this.playerID = playerID;
	this.direction = direction;
    }
		
    @Override
    public void execute(AnimatedBoard board, InfoPane infoPane) {
        board.getRobotByID(playerID).bump(direction);
    }
}