package roborace.client;

import COSC3P91.graphics.Animation;
import COSC3P91.util.Pair;
import COSC3P91.xml.XMLNodeConverter;
import static COSC3P91.xml.XMLTools.getBoolAttribute;
import java.awt.Point;
import java.util.List;
import org.w3c.dom.Node;
import roborace.common.Direction;

import static COSC3P91.xml.XMLTools.getChildNodes;
import static COSC3P91.xml.XMLTools.getEnumAttribute;
import static COSC3P91.xml.XMLTools.getIntAttribute;

public class AnimatedBoardReader implements XMLNodeConverter<AnimatedBoard> {
	
    private final TileAnimationReader tileReader;
    private final DecorationAnimationReader decorationReader;
    private final RobotSpriteReader robotSpriteReader;

    public AnimatedBoardReader() {
        tileReader = new TileAnimationReader();
        decorationReader = new DecorationAnimationReader();
        robotSpriteReader = new RobotSpriteReader();
    }

    @Override
    public AnimatedBoard convertXMLNode(Node node) {
        AnimatedBoard result = null;
        List<Node> childNodes = getChildNodes(node);
        if (node.getNodeName().equals("Board") && childNodes.size() == 2) {
            Node factoryNode = childNodes.get(1);
            Node robotsNode = childNodes.get(0);
            if (factoryNode.getNodeName().equals("Factory") && robotsNode.getNodeName().equals("Robots")) {
                int xSize = getIntAttribute(factoryNode,"xSize");
                int ySize = getIntAttribute(factoryNode,"ySize");
                int maxPlayers = getIntAttribute(factoryNode,"maxPlayers");
                int numDecorations = getIntAttribute(factoryNode,"numDecorations");
                int numWalls = getIntAttribute(factoryNode,"numWalls");
                int numberRobots = getIntAttribute(robotsNode,"number");
                Animation[][] tileAnimations = new Animation[xSize][ySize];
                DecorationAnimation[] decorationAnimations = new DecorationAnimation[numDecorations];
                Pair<Point,Direction>[] robotStartConfigs = (Pair<Point,Direction>[]) new Pair<?,?>[maxPlayers];
                WallImage[] wallImages = new WallImage[numWalls];
                RobotSprite[] robotSprites = new RobotSprite[numberRobots];
                List<Node> factoryNodes = getChildNodes(factoryNode);
                for(int j=0; j<ySize; j++) {
                    for(int i=0; i<xSize; i++) {
                        tileAnimations[i][j] = tileReader.convertXMLNode(factoryNodes.get(j*xSize+i));
                    }
                }
                for(int i=0; i<numDecorations; i++) {
                    decorationAnimations[i] = decorationReader.convertXMLNode(factoryNodes.get(xSize*ySize+i));
                }
                for(int i=0; i<maxPlayers; i++) {
                    Node node2 = factoryNodes.get(xSize*ySize+numDecorations+i);
                    if (node2.getNodeName().equals("StartConfig")) {
                        robotStartConfigs[i] = new Pair<Point,Direction>(new Point(getIntAttribute(node2,"x"),getIntAttribute(node2,"y")),(Direction) getEnumAttribute(Direction.class,node2,"direction"));
                    }
                }
                for(int i=0; i<numWalls; i++) {
                    Node node2 = factoryNodes.get(xSize*ySize+numDecorations+maxPlayers+i);
                    if(node2.getNodeName().equals("Wall")) {
                        wallImages[i] = new WallImage(getIntAttribute(node2,"x"),getIntAttribute(node2,"y"),getBoolAttribute(node2,"isHorizontal"));
                    }
                }
                List<Node> robotNodes = getChildNodes(robotsNode);
                if (robotNodes.size() == numberRobots) {
                    for(int i=0; i<numberRobots; i++) {
                        robotSprites[i] = robotSpriteReader.convertXMLNode(robotNodes.get(i));
                    }
                }
                result = new AnimatedBoard(xSize,ySize,tileAnimations,decorationAnimations,wallImages,robotStartConfigs,robotSprites);
            }
        }
        return result;
    }	
}