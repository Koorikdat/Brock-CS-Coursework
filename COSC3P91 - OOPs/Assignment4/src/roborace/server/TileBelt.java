package roborace.server;

import roborace.common.Direction;

public class TileBelt implements Tile {
	
    private final Direction direction;

    public TileBelt(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void effect(EventList events, int playerID, Board board) {
        board.step(events,playerID,direction);
    }

    @Override
    public String toXMLString() {
        return "<TileBelt direction=\"" + direction + "\"/>";
    }
}