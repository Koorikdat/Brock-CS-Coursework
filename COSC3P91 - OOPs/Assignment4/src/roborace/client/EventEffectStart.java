package roborace.client;

import java.awt.Point;

public class EventEffectStart implements Event {
    
    private final int playerID;
    private final int x;
    private final int y;
    private final boolean isDecorationEffect;
    
    public EventEffectStart(int x, int y, int playerID, boolean isDecorationEffect) {
        this.playerID = playerID;
        this.x = x;
        this.y = y;
        this.isDecorationEffect = isDecorationEffect;
    }
    
    @Override
    public void execute(AnimatedBoard board, InfoPane infoPane) {
        infoPane.displayEffectInfo(board.getRobotByID(playerID).getName());
        if (isDecorationEffect) {
            board.runDecorationAt(new Point(x,y));
        }
    }
}
