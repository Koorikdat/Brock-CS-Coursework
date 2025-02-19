package roborace.server;

import roborace.common.Direction;

public class EventStep implements Event {
	
    private final int playerID;
    private final Direction direction;
	
    public EventStep(int playerID, Direction direction) {
        this.playerID = playerID;
    	this.direction = direction;
    }
	
    @Override
    public String toXMLString() {
        return "<EventStep playerID=\"" + playerID + "\" direction=\"" + direction + "\"/>";
    }	
}
