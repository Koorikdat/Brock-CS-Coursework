package roborace.client;

import COSC3P91.xml.XMLNodeConverter;
import org.w3c.dom.Node;

import static COSC3P91.xml.XMLTools.getBoolAttribute;
import static COSC3P91.xml.XMLTools.getIntAttribute;

public class CardReader implements XMLNodeConverter<Card> {

    @Override
    public Card convertXMLNode(Node node) {
        Card result = null;
        int priority = getIntAttribute(node,"priority");
        int playerID = getIntAttribute(node,"playerID");
        String name = node.getNodeName();
        if (name.equals("CardBack")) {
            result = new CardBack(priority,playerID);
        }
        if (name.equals("CardMove")) {
            result = new CardMove(priority,playerID,getIntAttribute(node,"steps"));
        }
        if (name.equals("CardTurn180")) {
            result = new CardTurn180(priority,playerID);
        }
        if (name.equals("CardTurn90")) {
            result = new CardTurn90(priority,playerID,getBoolAttribute(node,"clockwise"));
        }
        return result;
    }	
}
