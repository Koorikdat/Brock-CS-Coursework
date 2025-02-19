package roborace.server;

public class CardBack extends Card {

    public CardBack(int priority, int playerID) {
        super(priority, playerID);
    }

    @Override
    public void execute(EventList events, Board board) {
        int playerID = getID();
        Robot robot = board.getRobotByID(playerID);
        if (robot.isAlive()) {
            events.add(new EventCardStart(this));
            board.step(events,playerID,robot.getDirection().rotate180());
            events.add(new EventCardEnd());
        }
    }

    @Override
    public String toXMLString() {
        return "<CardBack priority=\"" + getPriority() + "\" playerID=\"" + getID() + "\"/>";
    }	
}