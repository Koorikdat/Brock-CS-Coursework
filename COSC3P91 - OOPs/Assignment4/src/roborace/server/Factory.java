package roborace.server;

import COSC3P91.util.Pair;
import COSC3P91.xml.XMLObject;
import COSC3P91.xml.XMLReader;
import java.awt.Point;
import roborace.common.Direction;

public class Factory implements XMLObject {
	
    public static Factory load(String fileName) {
        XMLReader<Factory> reader = new XMLReader<Factory>();
        reader.setXMLSchema("Factory.xsd");
        reader.setXMLNodeConverter(new FactoryReader());
        return reader.readXML(fileName);
    }	

    private final int xSize;
    private final int ySize;
    private final Tile[][] tiles;
    private final Decoration[] decorations;
    private final Wall[] walls;
    private final Pair<Point,Direction>[] robotStartConfigs;

    public Factory(int xSize, int ySize, Tile[][] tiles, Decoration[] decorations, Wall[] walls, Pair<Point,Direction>[] robotStartConfigs) {
        this.xSize = xSize;
        this.ySize = ySize;
        this.tiles = tiles;
        this.decorations = decorations;
        this.walls = walls;
        this.robotStartConfigs = robotStartConfigs;
    }

    public int getXSize() {
        return xSize;
    }

    public int getYSize() {
        return ySize;
    }
    
    public int getMaxPlayers() {
        return robotStartConfigs.length;
    }
    
    public Pair<Point,Direction> getStartConfigByID(int id) {
        return robotStartConfigs[id];
    }

    public boolean hasWall(Point p, Direction direction) {
        boolean result = false;
        switch (direction) {
            case North:
                for(Wall w : walls) {
                    result |= w.isHorizontal() && w.getX() == p.x && w.getY() == p.y;
                }                
                break;
            case South:
                for(Wall w : walls) {
                    result |= w.isHorizontal() && w.getX() == p.x && w.getY() == p.y+1;
                }
                break;
            case West:
                for(Wall w : walls) {
                    result |= !w.isHorizontal() && w.getX() == p.x && w.getY() == p.y;
                }
                break;
            case East:
                for(Wall w : walls) {
                    result |= !w.isHorizontal() && w.getX() == p.x+1 && w.getY() == p.y;
                }
                break;
        }
        return result;
    }
    
    public boolean isPitAt(Point pos) {
        return tiles[pos.x][pos.y] instanceof TilePit;
    }
    
    public Decoration getDecorationAt(Point pos) {
        Decoration result = null;
        for(Decoration dec : decorations) {
            if (pos.equals(dec.getPosition())) {
                result = dec;
            }
        }
        return result;
    }
    
    public void effect(EventList events, int phase, int playerID, Board board) {
        Point pos = board.getRobotByID(playerID).getPosition();
        EventList temp = new EventList();
        boolean isDecorationEffect = false;
        Decoration decoration = getDecorationAt(pos);
        if (decoration != null) {
            decoration.effect(temp,phase,playerID,board);
            isDecorationEffect = !temp.isEmpty();
        }
        if (temp.isEmpty()) {
            tiles[pos.x][pos.y].effect(temp,playerID,board);
        }
        if (!temp.isEmpty()) {
            events.add(new EventEffectStart(pos,playerID,isDecorationEffect));
            events.addAll(temp);
            events.add(new EventEffectEnd());
        }     
    }

    @Override
    public String toXMLString() {
        String result = "<Factory xSize=\"" + xSize + "\" ySize=\"" + ySize +"\" maxPlayers=\"" + robotStartConfigs.length + "\" numDecorations=\"" + decorations.length + "\" numWalls=\"" + walls.length + "\">\n";
        for(int j=0; j<ySize; j++) {
            for(int i=0; i<xSize; i++) {
                result += tiles[i][j].toXMLString() + "\n";
            }
        }
        for (Decoration decoration : decorations) {
            result += decoration.toXMLString() + "\n";
        }
        for(Pair<Point,Direction> config : robotStartConfigs) {
            result += "<StartConfig x=\"" + config.getFirst().x + "\" y=\"" + config.getFirst().y + "\" direction=\"" + config.getSecond() + "\"/>\n";
        }
        for (Wall wall : walls) {
            result += wall.toXMLString() + "\n";
        }
        return result + "</Factory>";
    }
}