package roborace.server;

public class DecorationCrusher extends Decoration {
	
    private final int phase1;
    private final int phase2;

    public DecorationCrusher(int x, int y, int phase1, int phase2) {
        super(x,y);
        this.phase1 = phase1;
        this.phase2 = phase2;
    }
    
    @Override
    public void effect(EventList events, int phase, int playerID, Board board) {
        Robot robot = board.getRobotByID(playerID);
        if (robot.isAlive() && (phase == phase1 || phase == phase2)) {
            robot.destroyed();
            events.add(new EventDestroyed(playerID));
        }
    }

    @Override
    public String toXMLString() {
        return "<DecorationCrusher " + super.toXMLString() + " phase1=\"" + phase1 + "\" phase2=\"" + phase2 +"\"/>";
    }
}