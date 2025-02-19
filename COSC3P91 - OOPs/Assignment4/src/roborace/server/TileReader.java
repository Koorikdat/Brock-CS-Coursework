package roborace.server;

import COSC3P91.xml.XMLNodeConverter;
import roborace.common.Direction;
import org.w3c.dom.Node;

import static COSC3P91.xml.XMLTools.getBoolAttribute;
import static COSC3P91.xml.XMLTools.getEnumAttribute;

public class TileReader implements XMLNodeConverter<Tile> {
	
    @Override
    public Tile convertXMLNode(Node node) {
        Tile tile = null;
        String name = node.getNodeName();
        if (name.equals("TileBelt")) {
            tile = new TileBelt((Direction) getEnumAttribute(Direction.class,node,"direction"));
        }
        if (name.equals("TileFloor")) {
            tile = new TileFloor();
        }
        if (name.equals("TileGear")) {
            tile = new TileGear(getBoolAttribute(node,"clockwise"));
        }
        if (name.equals("TileGoal")) {
            tile = new TileGoal();
        }
        if (name.equals("TilePit")) {
            tile = new TilePit();
        }
        return tile;
    }
}