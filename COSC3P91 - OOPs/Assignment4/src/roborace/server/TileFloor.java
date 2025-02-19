package roborace.server;

public class TileFloor implements Tile {
	
    @Override
    public void effect(EventList events, int playerID, Board board) {
    }

    @Override
    public String toXMLString() {
        return "<TileFloor/>";
    }	
}