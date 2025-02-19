package roborace.server;

import COSC3P91.xml.XMLNodeConverter;
import org.w3c.dom.Node;
import roborace.common.Direction;

import static COSC3P91.xml.XMLTools.getEnumAttribute;
import static COSC3P91.xml.XMLTools.getIntAttribute;

public class DecorationReader implements XMLNodeConverter<Decoration> {
	
    @Override
    public Decoration convertXMLNode(Node node) {
        Decoration decoration = null;
        String name = node.getNodeName();
        int x = getIntAttribute(node,"x");
        int y = getIntAttribute(node,"y");
        int phase1 = getIntAttribute(node,"phase1");
        int phase2 = getIntAttribute(node,"phase2");
        if (name.equals("DecorationCrusher")) {
            decoration = new DecorationCrusher(x,y,phase1,phase2);
        } 
        if (name.equals("DecorationPusher")) {
            decoration = new DecorationPusher((Direction) getEnumAttribute(Direction.class,node,"direction"),x,y,phase1,phase2,getIntAttribute(node,"phase3"));
        }	
        return decoration;
    }
}