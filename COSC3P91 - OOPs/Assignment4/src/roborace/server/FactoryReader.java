package roborace.server;

import COSC3P91.util.Pair;
import COSC3P91.xml.XMLNodeConverter;
import java.awt.Point;
import java.util.List;
import org.w3c.dom.Node;
import roborace.common.Direction;

import static COSC3P91.xml.XMLTools.getBoolAttribute;
import static COSC3P91.xml.XMLTools.getChildNodes;
import static COSC3P91.xml.XMLTools.getEnumAttribute;
import static COSC3P91.xml.XMLTools.getIntAttribute;

public class FactoryReader implements XMLNodeConverter<Factory> {
	
    private final TileReader tileReader;
    private final DecorationReader decorationReader;

    public FactoryReader() {
        tileReader = new TileReader();
        decorationReader = new DecorationReader();
    }

    @Override
    public Factory convertXMLNode(Node node) {
        Factory factory = null;
        if (node.getNodeName().equals("Factory")) {
            int xSize = getIntAttribute(node,"xSize");
            int ySize = getIntAttribute(node,"ySize");
            int maxPlayers = getIntAttribute(node,"maxPlayers");
            int numDecorations = getIntAttribute(node,"numDecorations");
            int numWalls = getIntAttribute(node,"numWalls");
            Tile[][] tiles = new Tile[xSize][ySize];
            Decoration[] decorations = new Decoration[numDecorations];
            Wall[] walls = new Wall[numWalls];
            Pair<Point,Direction>[] robotStartConfigs = (Pair<Point,Direction>[]) new Pair<?,?>[maxPlayers];
            List<Node> list = getChildNodes(node);
            for(int j=0; j<ySize; j++) {
                for(int i=0; i<xSize; i++) {
                    tiles[i][j] = tileReader.convertXMLNode(list.get(j*xSize+i));
                }
            }       
            for(int i=0; i<numDecorations; i++) {
                decorations[i] = decorationReader.convertXMLNode(list.get(xSize*ySize+i));
            }
            for(int i=0; i<maxPlayers; i++) {
                Node node2 = list.get(xSize*ySize+numDecorations+i);
                if (node2.getNodeName().equals("StartConfig")) {
                    robotStartConfigs[i] = new Pair<Point,Direction>(new Point(getIntAttribute(node2,"x"),getIntAttribute(node2,"y")),(Direction) getEnumAttribute(Direction.class,node2,"direction"));
                }
            }
            for(int i=0; i<numWalls; i++) {
                Node node2 = list.get(xSize*ySize+numDecorations+maxPlayers+i);
                if(node2.getNodeName().equals("Wall")) {
                    walls[i] = new Wall(getIntAttribute(node2,"x"),getIntAttribute(node2,"y"),getBoolAttribute(node2,"isHorizontal"));
                }
            }
            factory = new Factory(xSize,ySize,tiles,decorations,walls,robotStartConfigs);
        }
        return factory;
    }	
}