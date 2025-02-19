package roborace.server;

public class EventTurn90 implements Event {
	
    private final int playerID;
    private final boolean clockwise;

    public EventTurn90(int playerID, boolean clockwise) {
        this.playerID = playerID;
        this.clockwise = clockwise;
    }

    @Override
    public String toXMLString() {
        return "<EventTurn90 playerID=\" " + playerID + "\" clockwise=\"" + clockwise + "\"/>";
    }
}