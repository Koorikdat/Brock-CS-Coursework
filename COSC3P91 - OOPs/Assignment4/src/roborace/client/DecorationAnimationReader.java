package roborace.client;

import COSC3P91.xml.XMLNodeConverter;
import org.w3c.dom.Node;
import roborace.common.Direction;

import static COSC3P91.xml.XMLTools.getEnumAttribute;
import static COSC3P91.xml.XMLTools.getIntAttribute;

public class DecorationAnimationReader implements XMLNodeConverter<DecorationAnimation> {

    @Override
    public DecorationAnimation convertXMLNode(Node node) {
        DecorationAnimation result = null;
        ImageAndAnimationFactory animationFactory = ImageAndAnimationFactory.getInstance();
        String name = node.getNodeName();
        int x = getIntAttribute(node,"x");
        int y = getIntAttribute(node,"y");
        int phase1 = getIntAttribute(node,"phase1");
        int phase2 = getIntAttribute(node,"phase2");
        if (name.equals("DecorationCrusher")) {
            result = animationFactory.createCrusherAnimation(x,y,phase1,phase2);
        }
        if (name.equals("DecorationPusher")) {
            result = animationFactory.createPusherAnimation(x,y,(Direction) getEnumAttribute(Direction.class,node,"direction"),phase1,phase2,getIntAttribute(node,"phase3"));
        }
        return result;
    }
}
