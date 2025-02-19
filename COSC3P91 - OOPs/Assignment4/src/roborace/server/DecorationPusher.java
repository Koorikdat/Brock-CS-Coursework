package roborace.server;

import roborace.common.Direction;

public class DecorationPusher extends Decoration {
	
    private final Direction direction;
    private final int phase1;
    private final int phase2;
    private final int phase3;

    public DecorationPusher(Direction direction, int x, int y, int phase1, int phase2, int phase3) {
        super(x,y);
        this.direction = direction;
        this.phase1 = phase1;
        this.phase2 = phase2;
        this.phase3 = phase3;
    }

    public Direction getDirection() {
        return direction;
    }

    @Override
    public void effect(EventList events, int phase, int playerID, Board board) {
        Robot robot = board.getRobotByID(playerID);
        if (robot.isAlive() && (phase == phase1 || phase == phase2 || phase == phase3)) {
            board.step(events,playerID,direction);
        }
    }

    @Override
    public String toXMLString() {
        return "<DecorationPusher " + super.toXMLString() + " direction=\"" + direction + "\" phase1=\"" + phase1 + "\" phase2=\"" + phase2 + "\" phase3=\"" + phase3 +"\"/>";
    }
}