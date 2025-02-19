package roborace.server;

public class TilePit implements Tile {
	
    @Override
    public void effect(EventList events, int playerID, Board board) {
    }
	
    @Override
    public String toXMLString() {
	return "<TilePit/>";
    }	
}