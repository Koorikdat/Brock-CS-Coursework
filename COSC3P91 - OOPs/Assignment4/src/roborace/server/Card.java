package roborace.server;

import COSC3P91.xml.XMLObject;

public abstract class Card implements Comparable<Card>, XMLObject {

    private final int priority;
    private final int playerID;

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

    @Override
    public int compareTo(Card c) {
        return c.priority - priority;
    }

    public abstract void execute(EventList events, Board board);	
}