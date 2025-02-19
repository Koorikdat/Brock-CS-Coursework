package roborace.server;

import COSC3P91.xml.XMLObject;

public class Wall implements XMLObject{
    
    private final int x;
    private final int y;
    private final boolean isHorizontal;
    
    public Wall(int x, int y, boolean isHorizontal) {
        this.x = x;
        this.y = y;
        this.isHorizontal = isHorizontal;        
    }
    
    public boolean isHorizontal() {
        return isHorizontal;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }

    @Override
    public String toXMLString() {
        return "<Wall x=\"" + x +"\" y=\"" + y + "\" isHorizontal=\"" + isHorizontal + "\"/>";
    }
}
