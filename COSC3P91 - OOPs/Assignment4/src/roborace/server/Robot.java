package roborace.server;

import COSC3P91.util.Pair;
import COSC3P91.xml.XMLObject;
import java.awt.Point;
import roborace.common.Direction;

public class Robot implements XMLObject {
	
    private final String name;
    private Point position;
    private Direction direction;
    private boolean alive;
	
    public Robot(String name, Pair<Point,Direction> startConfig, boolean alive) {
        this.name = name;
        this.position = new Point(startConfig.getFirst());
        this.direction = startConfig.getSecond();
        this.alive = alive;
    }
	
    public String getName() {
        return name;
    }
	
    public Direction getDirection() {
        return direction;
    }
	
    public Point getPosition() {
        return position;
    }
	
    public void setPosition(Point p) {
        position = new Point(p.x,p.y);
    }
    
    public void turn90(boolean clockwise) {
        direction = direction.rotate90(clockwise);
    }
    
    public void turn180() {
        direction = direction.rotate180();
    }
	
    public void destroyed() {
        alive = false;
    }
	
    public boolean isAlive() {
        return alive;
    }
	
    public void revitalize(Pair<Point,Direction> startConfig) {
        this.position = new Point(startConfig.getFirst());
        this.direction = startConfig.getSecond();
        alive = true;
    }
	
    @Override
    public String toXMLString() {
        return "<Robot name=\"" + name + "\" x=\"" + position.x + "\" y=\"" + position.y + "\" direction=\"" + direction + "\" alive=\"" + alive + "\"/>";
    }
}