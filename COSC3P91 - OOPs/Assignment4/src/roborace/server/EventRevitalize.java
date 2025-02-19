package roborace.server;

public class EventRevitalize implements Event {
	
    private final int playerID;
    private final int startID;
	
    public EventRevitalize(int playerID, int startID) {
        this.playerID = playerID;
	this.startID = startID;
    }
		
    @Override
    public String toXMLString() {
	return "<EventRevitalize playerID=\"" + playerID + "\" startID=\"" + startID + "\"/>";
    }	
}
