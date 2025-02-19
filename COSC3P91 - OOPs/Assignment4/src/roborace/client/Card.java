package roborace.client;

import COSC3P91.xml.XMLObject;
import java.awt.Image;

public abstract class Card implements XMLObject {
    
    private final int priority;
    private final int playerID;
    protected Image image;

    public Card(int priority, int playerID) {
        this.priority = priority;
        this.playerID = playerID;
    }
	
    public int getPriority() {
        return priority;
    }
    
    public int getID() {
        return playerID;
    }
	
    public Image getImage() {
	return image;
    }
}
