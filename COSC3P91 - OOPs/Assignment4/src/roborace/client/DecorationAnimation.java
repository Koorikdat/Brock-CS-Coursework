package roborace.client;

import COSC3P91.graphics.Animation;
import java.awt.Graphics;
import java.awt.Point;

import static roborace.client.AnimatedBoard.LOC_SIZE;

public abstract class DecorationAnimation extends Animation {
    
    private final int x;
    private final int y;
    
    public DecorationAnimation(int x, int y) {
        super(false);
        this.x = x;
        this.y = y;
    }
    
    public Point getPosition() {
        return new Point(x,y);
    }
    
    public synchronized void waitOnDecoration() {
        while (isRunning()) {
            try {
                wait();
            } catch (InterruptedException e) {}
        } 
    }
    
    public abstract void start(int phase);
    
    @Override
    public synchronized void update(long elapsedTime) {
        if (isRunning()) {
            super.update(elapsedTime);
        } else {
            reset();
            notify();
        }
    }
    
    public void draw(Graphics graphics) {
        graphics.drawImage(getImage(),LOC_SIZE*x,LOC_SIZE*y,null);
    }
}
