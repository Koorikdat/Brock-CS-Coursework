package roborace.client;

import COSC3P91.xml.XMLNodeConverter;
import org.w3c.dom.Node;
import roborace.common.Direction;

import static COSC3P91.xml.XMLTools.getBoolAttribute;
import static COSC3P91.xml.XMLTools.getEnumAttribute;
import static COSC3P91.xml.XMLTools.getIntAttribute;
import static COSC3P91.xml.XMLTools.getStringAttribute;

public class RobotSpriteReader implements XMLNodeConverter<RobotSprite> {

    @Override
    public RobotSprite convertXMLNode(Node node) {
        RobotSprite result = null;
        if (node.getNodeName().equals("Robot")) {
            String name = getStringAttribute(node,"name");
            int x = getIntAttribute(node,"x");
            int y = getIntAttribute(node,"y");
            Direction direction = (Direction) getEnumAttribute(Direction.class,node,"direction");
            boolean alive = getBoolAttribute(node,"alive");
            result = new RobotSprite(name,x,y,direction,alive);
        }		
        return result;
    }	
}