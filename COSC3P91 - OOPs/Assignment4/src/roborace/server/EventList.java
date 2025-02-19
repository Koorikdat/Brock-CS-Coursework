package roborace.server;

import COSC3P91.xml.XMLObject;
import java.util.LinkedList;

public class EventList extends LinkedList<Event> implements XMLObject {

    public boolean containsVictory() {
        return size()>1 && get(size()-2) instanceof EventVictory;
    }
    
    @Override
    public String toXMLString() {
        String result = "<EventList>\n";
        for(Event event : this) {
            result += event.toXMLString() + "\n";
        }
        return result + "</EventList>";
    }
}