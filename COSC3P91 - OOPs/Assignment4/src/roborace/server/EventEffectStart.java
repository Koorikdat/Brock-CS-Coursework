package roborace.server;

import java.awt.Point;

public class EventEffectStart implements Event {
    
    private final int playerID;
    private final int x;
    private final int y;
    private final boolean isDecorationEffect;
    
    public EventEffectStart(Point pos, int playerID, boolean isDecorationEffect) {
        this.playerID = playerID;
        this.x = pos.x;
        this.y = pos.y;
        this.isDecorationEffect = isDecorationEffect;
    }
    
    @Override
    public String toXMLString() {
        return "<EventEffectStart x=\"" + x +"\" y=\"" + y +"\" playerID=\"" + playerID + "\" isDecorationEffect=\"" + isDecorationEffect + "\"/>";
    }
}

