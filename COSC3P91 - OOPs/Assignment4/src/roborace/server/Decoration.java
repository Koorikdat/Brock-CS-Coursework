package roborace.server;

import COSC3P91.xml.XMLObject;
import java.awt.Point;

public abstract class Decoration implements XMLObject {

    private final int x;
    private final int y;

    public Decoration(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public Point getPosition() {
        return new Point(x,y);
    }
	
    public abstract void effect(EventList events, int phase, int playerID, Board board);	
    
    @Override
    public String toXMLString() {
        return "x=\"" + x + "\" y=\"" + y + "\"";
    }
}