package roborace.client;

import COSC3P91.xml.XMLNodeConverter;
import java.util.List;
import org.w3c.dom.Node;
import roborace.common.Direction;

import static COSC3P91.xml.XMLTools.getBoolAttribute;
import static COSC3P91.xml.XMLTools.getChildNodes;
import static COSC3P91.xml.XMLTools.getEnumAttribute;
import static COSC3P91.xml.XMLTools.getIntAttribute;

public class EventReader implements XMLNodeConverter<Event> {
	
    private final CardReader cardReader;
	
    public EventReader() {
        cardReader = new CardReader();
    }

    @Override
    public Event convertXMLNode(Node node) {
        Event event = null;
        String name = node.getNodeName();
        if (name.equals("EventBump")) {
            event = new EventBump(getIntAttribute(node,"playerID"),(Direction) getEnumAttribute(Direction.class,node,"direction"));
        } 
        if (name.equals("EventCardEnd")) {
            event = new EventCardEnd();
        }
        if (name.equals("EventCardStart")) {
            List<Node> list = getChildNodes(node);
            event = new EventCardStart(cardReader.convertXMLNode(list.get(0)));
        }
        if (name.equals("EventDestroyed")) {
            event = new EventDestroyed(getIntAttribute(node,"playerID"));
        }
        if (name.equals("EventEffectEnd")) {
            event = new EventEffectEnd();
        }
        if (name.equals("EventEffectStart")) {
            event = new EventEffectStart(getIntAttribute(node,"x"),getIntAttribute(node,"y"),getIntAttribute(node,"playerID"),getBoolAttribute(node,"isDecorationEffect"));
        }
        if (name.equals("EventRevitalize")) {
            event = new EventRevitalize(getIntAttribute(node,"playerID"),getIntAttribute(node,"startID"));
        }
        if (name.equals("EventRunDecorations")) {
            event = new EventRunDecorations(getIntAttribute(node,"phase"));
        }
        if (name.equals("EventStep")) {
            event = new EventStep(getIntAttribute(node,"playerID"),(Direction) getEnumAttribute(Direction.class,node,"direction"));
        } 
        if (name.equals("EventTurn90")) {
            event = new EventTurn90(getIntAttribute(node,"playerID"),getBoolAttribute(node,"clockwise"));
        } 
        if (name.equals("EventTurn180")) {
            event = new EventTurn180(getIntAttribute(node,"playerID"));
        } 
        if (name.equals("EventVictory")) {
            event = new EventVictory(getIntAttribute(node,"playerID"));
        }       
        if (name.equals("EventWait")) {
            event = new EventWait();
        }
        return event;
    }
}