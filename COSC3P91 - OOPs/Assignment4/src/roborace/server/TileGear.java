package roborace.server;

public class TileGear implements Tile {
	
    private final boolean clockwise;

    public TileGear(boolean clockwise) {
        this.clockwise = clockwise;
    }

    public boolean isClockwise() {
        return clockwise;
    }

    @Override
    public void effect(EventList events, int playerID, Board board) {
        board.getRobotByID(playerID).turn90(clockwise);
        events.add(new EventTurn90(playerID,clockwise));
    }

    @Override
    public String toXMLString() {
        return "<TileGear clockwise=\"" + clockwise + "\"/>";
    }
}