package roborace.server;

import roborace.common.Direction;

public class EventBump implements Event {
	
    private final int playerID;
    private final Direction direction;
	
    public EventBump(int playerID, Direction direction) {
        this.playerID = playerID;
	this.direction = direction;
    }
		
    @Override
    public String toXMLString() {
	return "<EventBump playerID=\"" + playerID + "\" direction=\"" + direction + "\"/>";
    }	
}