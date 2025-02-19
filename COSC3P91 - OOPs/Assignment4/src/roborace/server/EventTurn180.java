package roborace.server;

public class EventTurn180 implements Event {
    
    private final int playerID;
	
    public EventTurn180(int playerID) {
        this.playerID = playerID;
    }	

    @Override
    public String toXMLString() {
        return "<EventTurn180 playerID=\"" + playerID + "\"/>";
    }
}
