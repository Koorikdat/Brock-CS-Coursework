package roborace.client;

import COSC3P91.xml.XMLNodeConverter;
import COSC3P91.graphics.Animation;
import org.w3c.dom.Node;
import roborace.common.Direction;

import static COSC3P91.xml.XMLTools.getBoolAttribute;
import static COSC3P91.xml.XMLTools.getEnumAttribute;

public class TileAnimationReader implements XMLNodeConverter<Animation> {

    @Override
    public Animation convertXMLNode(Node node) {
        Animation result = null;
        ImageAndAnimationFactory animationFactory = ImageAndAnimationFactory.getInstance();
        String name = node.getNodeName();
        if (name.equals("TileBelt")) {
            result = animationFactory.createBeltAnimation((Direction) getEnumAttribute(Direction.class,node,"direction"));
        }
        if (name.equals("TileFloor")) {
            result = animationFactory.createFloorAnimation();
        } 
        if (name.equals("TileGear")) {
            result = animationFactory.createGearAnimation(getBoolAttribute(node,"clockwise"));
        }
        if (name.equals("TileGoal")) {
            result = animationFactory.createGoalAnimation();
        }
        if (name.equals("TilePit")) {
            result = animationFactory.createPitAnimation();
        }
        return result;
    }
}
