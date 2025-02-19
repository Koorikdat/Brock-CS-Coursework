package roborace.server;

import COSC3P91.xml.XMLObject;

public interface Tile extends XMLObject {
	
    public void effect(EventList events, int playerID, Board board);	
}
