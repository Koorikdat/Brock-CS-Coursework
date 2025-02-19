package roborace.server;

public class EventDestroyed implements Event {
    
    private final int playerID;
	
    public EventDestroyed(int playerID) {
	this.playerID = playerID;
    }
	
    @Override
    public String toXMLString() {
	return "<EventDestroyed playerID=\"" + playerID + "\"/>";
    }
}
