package roborace.server;

public class CardMove extends Card {

    private int steps;

    public CardMove(int priority, int playerID, int steps) {
        super(priority,playerID);
        this.steps = steps;
    }

    public void execute(EventList events, Board board) {
        int playerID = getID();
        Robot robot = board.getRobotByID(playerID);
        if (robot.isAlive()) {
            events.add(new EventCardStart(this));
            for(int i=0; i<steps; i++) {
                if (robot.isAlive()) {
                    board.step(events,playerID,robot.getDirection());
                };
            };
            events.add(new EventCardEnd());
        };
    }

    public String toXMLString() {
        return "<CardMove priority=\"" + getPriority() + "\" playerID=\"" + getID() + "\" steps=\"" + steps + "\"/>";
    }	
}