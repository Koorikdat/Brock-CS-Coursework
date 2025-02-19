package roborace.server;

public class EventVictory implements Event {
	
    private final int playerID;

    public EventVictory(int playerID) {
        this.playerID = playerID;
    }	

    @Override
    public String toXMLString() {
        return "<EventVictory playerID=\"" + playerID + "\"/>";
    }
}