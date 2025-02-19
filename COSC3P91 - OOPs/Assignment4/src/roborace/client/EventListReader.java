package roborace.client;

import COSC3P91.xml.XMLNodeConverter;
import java.util.List;
import org.w3c.dom.Node;

import static COSC3P91.xml.XMLTools.getChildNodes;

public class EventListReader implements XMLNodeConverter<EventList> {
	
    private final EventReader eventReader;

    public EventListReader() {
        eventReader = new EventReader();
    }

    @Override
    public EventList convertXMLNode(Node node) {
        EventList result = new EventList();
        if (node.getNodeName().equals("EventList")) {
            List<Node> list = getChildNodes(node);
            for(Node n : list) {
                result.add(eventReader.convertXMLNode(n));
            }
        }
        return result;	
    }	
}