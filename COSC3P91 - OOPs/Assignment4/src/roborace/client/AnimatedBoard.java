package roborace.client;

import COSC3P91.graphics.Animation;
import COSC3P91.util.Pair;
import COSC3P91.xml.XMLReader;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.io.Reader;
import roborace.common.Direction;

public class AnimatedBoard {
    
    public static final int LOC_SIZE = 97;
    public static final int ROBOT_OFFSET = 17;
    
    public static AnimatedBoard read(Reader input) {
        XMLReader<AnimatedBoard> reader = new XMLReader<AnimatedBoard>();
        reader.setXMLSchema("Board.xsd");
        reader.setXMLNodeConverter(new AnimatedBoardReader());
        return reader.readXML(input);
    }
    
    private final int xSize;
    private final int ySize;
    private final Animation[][] tileAnimations;
    private final DecorationAnimation[] decorationAnimations;
    private final WallImage[] wallImages;
    private final Pair<Point,Direction>[] robotStartConfigs;
    private final RobotSprite[] robotSprites;    
    
    public AnimatedBoard(int xSize, int ySize, Animation[][] tileAnimations, DecorationAnimation[] decorationAnimations, WallImage[] wallImages, Pair<Point,Direction>[] robotStartConfigs, RobotSprite[] robotSprites) {
        this.xSize = LOC_SIZE*xSize;
        this.ySize = LOC_SIZE*ySize;
        this.tileAnimations = tileAnimations;
        this.decorationAnimations = decorationAnimations;
        this.wallImages = wallImages;
        this.robotStartConfigs = robotStartConfigs;
        this.robotSprites = robotSprites;
    }
    
    public Dimension getDisplaySize() {
        return new Dimension(xSize,ySize);
    }
    
    public RobotSprite getRobotByID(int playerID) {
        return robotSprites[playerID];
    }
    
    public void runDecorationAt(Point pos) {
        int index = -1;
        for(int i=0; i<decorationAnimations.length; i++) {
            if (decorationAnimations[i].getPosition().equals(pos)) {
                index = i;
            }
        }
        decorationAnimations[index].start();
    }
    
    public void runDecorations(int phase) {
        for(DecorationAnimation decAmin : decorationAnimations) {
            decAmin.start(phase);
        }
    }
    
    public void revitalize(int playerID, int startID) {
        robotSprites[playerID].revitalize(robotStartConfigs[startID]);
    }
    
    public void start() {
        for(Animation[] row : tileAnimations) {
            for(Animation tile : row) {
                tile.start();
            }
        }
    }
    
    public void update(long elapsedTime) {
        for(Animation[] row : tileAnimations) {
            for(Animation tile : row) {
                tile.update(elapsedTime);
            }
        }
        for(Animation decoration : decorationAnimations) {
            decoration.update(elapsedTime);
        }
        for(RobotSprite robot : robotSprites) {
            robot.update(elapsedTime);
        }
    }
    
    public void draw(Graphics graphics) {
        for(int i=0; i<tileAnimations.length; i++) {
            for(int j=0; j<tileAnimations[0].length; j++) {
                graphics.drawImage(tileAnimations[i][j].getImage(),LOC_SIZE*i,LOC_SIZE*j,null);
            }
        }
        for (WallImage wallImage : wallImages) {
            wallImage.draw(graphics);
        }
        for (RobotSprite robotSprite : robotSprites) {
            robotSprite.draw(graphics);
        }
        for (DecorationAnimation decorationAnimation : decorationAnimations) {
            decorationAnimation.draw(graphics);
        }
    }
    
    public void waitOnAnimations() {
        for(RobotSprite robot : robotSprites) {
            robot.waitOnRobot();
        }
        for(DecorationAnimation decAmin : decorationAnimations) {
            decAmin.waitOnDecoration();
        }
    }
}
