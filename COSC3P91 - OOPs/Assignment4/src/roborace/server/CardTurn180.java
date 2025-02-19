package roborace.server;

public class CardTurn180 extends Card {
	
    public CardTurn180(int priority, int playerID) {
        super(priority,playerID);
    }

    @Override
    public void execute(EventList events, Board board) {
        int playerID = getID();
        Robot robot = board.getRobotByID(playerID);
        if (robot.isAlive()) {
            events.add(new EventCardStart(this));
            robot.turn180();
            events.add(new EventTurn180(playerID));
            events.add(new EventCardEnd());
        }
    }

    @Override
    public String toXMLString() {
        return "<CardTurn180 priority=\"" + getPriority() + "\" playerID=\"" + getID() + "\"/>";
    }	
}