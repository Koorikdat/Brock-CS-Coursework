package roborace.client;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class BoardCanvas extends Canvas implements Runnable {
	
    private final AnimatedBoard board;
    private boolean running;
    private Thread thread;

    public BoardCanvas(AnimatedBoard board) {
        this.board = board;
        Dimension dim = board.getDisplaySize();
        super.setSize(dim.width,dim.height);
    }

    public void start() {
        thread = new Thread(this);
        running = true;
        board.start();
        thread.start();
    }

    public void stop() {
        running = false;
        try{
            thread.join();
        } catch(InterruptedException e) {}
    }

    @Override
    public void run() {
        
        createBufferStrategy(2);
        BufferStrategy strategy = getBufferStrategy();
        
        long lastLoopTime = System.currentTimeMillis();
        long currentTime;
        long delta;
        Graphics graphics;
        
        
        
        while (running){
            currentTime = System.currentTimeMillis();
            delta = currentTime - lastLoopTime;
            lastLoopTime = currentTime;
            board.update(delta);
            graphics = strategy.getDrawGraphics();
            board.draw(graphics);
            graphics.dispose();
            strategy.show();
            
            if (delta < 10){
            try{
            Thread.sleep(10-delta);
            } catch (InterruptedException e){}
            
            }
        
        
        }
    }	
}