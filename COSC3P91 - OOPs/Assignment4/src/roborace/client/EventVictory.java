package roborace.client;

public class EventVictory implements Event {
	
    private final int playerID;

    public EventVictory(int playerID) {
        this.playerID = playerID;
    }	

    @Override
    public void execute(AnimatedBoard board, InfoPane infoPane) {
    }

    public int getPlayerID() {
        return playerID;
    }
}