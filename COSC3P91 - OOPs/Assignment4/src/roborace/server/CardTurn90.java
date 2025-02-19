package roborace.server;

public class CardTurn90 extends Card {
	
    private final boolean clockwise;

    public CardTurn90(int priority, int playerID, boolean clockwise) {
        super(priority,playerID);
        this.clockwise = clockwise;
    }

    @Override
    public void execute(EventList events, Board board) {
        int playerID = getID();
        Robot robot = board.getRobotByID(playerID);
        if (robot.isAlive()) {
            events.add(new EventCardStart(this));
            robot.turn90(clockwise);
            events.add(new EventTurn90(playerID,clockwise));
            events.add(new EventCardEnd());
        }
    }

    @Override
    public String toXMLString() {
        return "<CardTurn90 priority=\"" + getPriority() + "\" playerID=\"" + getID() + "\" clockwise=\"" + clockwise + "\"/>";
    }
}