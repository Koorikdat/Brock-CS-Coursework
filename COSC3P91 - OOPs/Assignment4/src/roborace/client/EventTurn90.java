package roborace.client;

public class EventTurn90 implements Event {
	
    private final int playerID;
    private final boolean clockwise;

    public EventTurn90(int playerID, boolean clockwise) {
        this.playerID = playerID;
        this.clockwise = clockwise;
    }

    @Override
    public void execute(AnimatedBoard board, InfoPane infoPane) {
        board.getRobotByID(playerID).turn90(clockwise);
    }
}