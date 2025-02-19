package roborace.client;

import COSC3P91.xml.XMLReader;
import java.io.Reader;
import java.util.LinkedList;

public class EventList extends LinkedList<Event> {
	
    public static EventList read(Reader input) {
        XMLReader<EventList> reader = new XMLReader<EventList>();
        reader.setXMLSchema("EventList.xsd");
        reader.setXMLNodeConverter(new EventListReader());
        return reader.readXML(input);
    }

    public boolean containsVictory() {
        return size()>1 && get(size()-2) instanceof EventVictory;
    }

    public int getWinnerID() {
        int result = -1;
        for(Event e : this) {
            if (e instanceof EventVictory) {
                result = ((EventVictory) e).getPlayerID();
            }
        }
        return result;
    }

    public void execute(AnimatedBoard board, InfoPane infoPane) {
        for(Event event : this) {
            event.execute(board,infoPane);
        }
    }
}
