package roborace.server;

import COSC3P91.xml.XMLObject;
import java.awt.Point;
import roborace.common.Direction;

public class Board implements XMLObject {
	
    private final Factory factory;
    private final Robot[] robots;

    public Board(Factory factory, Robot[] robots) {
        this.factory = factory;
        this.robots = robots;
    }
    
    public Robot getRobotByID(int playerID) {
        return robots[playerID];
    }
    
    public void revitalize(EventList events) {
        for(int i=0; i<robots.length; i++) {
            if (!robots[i].isAlive()) {
                int startIndex;
                if (robotIDAt(factory.getStartConfigByID(i).getFirst()) == -1) {
                    startIndex = i;
                } else {
                    startIndex = -1;
                    for(int j=0; j<factory.getMaxPlayers(); j++) {
                        if (startIndex==-1 && robotIDAt(factory.getStartConfigByID(j).getFirst()) == -1) {
                            startIndex = j;
                        }
                    }                    
                }
                robots[i].revitalize(factory.getStartConfigByID(startIndex));
                events.add(new EventRevitalize(i,startIndex));
            }
        }
    }
    
    public void step(EventList events, int playerID, Direction direction) {
        EventList destroyedEvents = new EventList();
        stepRecursive(events,destroyedEvents,playerID,direction);
        events.add(new EventWait());
        if (!destroyedEvents.isEmpty()) {
            events.addAll(destroyedEvents);
            events.add(new EventWait());
        }
    }

    private void stepRecursive(EventList events, EventList destroyedEvents, int playerID, Direction direction) {
        Robot robot = robots[playerID];
        Point currentPos = robot.getPosition();
        if (factory.hasWall(currentPos,direction)) {
            events.add(new EventBump(playerID,direction));
        } else {
            Point newPos = null;
            switch (direction) {
                case North: 
                    newPos = new Point(currentPos.x,currentPos.y-1);
                    break;
                case East:  
                    newPos = new Point(currentPos.x+1,currentPos.y);
                    break;
                case South: 
                    newPos = new Point(currentPos.x,currentPos.y+1);
                    break;
                case West:  
                    newPos = new Point(currentPos.x-1,currentPos.y);
                    break;
            }
            if (newPos.x < 0 || newPos.x >= factory.getXSize() || newPos.y < 0 || newPos.y >= factory.getYSize() || factory.isPitAt(newPos)) {
                robot.setPosition(newPos);
                robot.destroyed();
                events.add(new EventStep(playerID,direction));
                destroyedEvents.add(new EventDestroyed(playerID));
            } else {
                int robot2ID = robotIDAt(newPos);
                if (robot2ID == -1) {
                    robot.setPosition(newPos);
                    events.add(new EventStep(playerID,direction)); 
                } else {
                    stepRecursive(events,destroyedEvents,robot2ID,direction);
                    if (robotIDAt(newPos) != -1) {
                        events.add(new EventBump(playerID,direction));
                    } else {
                        robot.setPosition(newPos);
                        events.add(new EventStep(playerID,direction));
                    }
                }
            }
        }
    }
    
    public void effect(EventList events, int playerID, int phase) {
        if (getRobotByID(playerID).isAlive() && !events.containsVictory()) {
            factory.effect(events,phase,playerID,this);
        }
    }

    @Override
    public String toXMLString() {
        String result = "<Board>\n";
        result += "<Robots number=\"" + robots.length + "\">\n";
        for(Robot robot : robots) 
                result += robot.toXMLString() + "\n"; 
        result += "</Robots>\n";
        result += factory.toXMLString() + "\n";
        return result + "</Board>";
    }
    
    private int robotIDAt(Point p) {
        int result = -1;
        for(int i=0; i<robots.length; i++) {
            if (p.equals(robots[i].getPosition())) {
                result = i;
            } 
        }
        return result;
    }
}