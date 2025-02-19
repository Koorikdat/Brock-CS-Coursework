package roborace.server;

public class TileGoal implements Tile {
	
    @Override
    public void effect(EventList events, int playerID, Board board) {
        events.add(new EventVictory(playerID));
    }

    @Override
    public String toXMLString() {
        return "<TileGoal/>";
    }	
}