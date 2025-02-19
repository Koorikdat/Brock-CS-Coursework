package COSC3P91.graphics;

import COSC3P91.util.Pair;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

public class Animation {

    private final List<Pair<Image,Long>> frames;
    private int currentFrameIndex;
    private long animTime;
    private long totalDuration;
    private long currentFrameEndTime;
    private int startFrame;
    private final boolean repeating;
    private boolean running;

    public Animation(boolean repeating) {
        frames = new ArrayList<Pair<Image,Long>>();
        totalDuration = 0;
        currentFrameEndTime = 0;
        startFrame = 0;
        this.repeating = repeating;
        running = false;
    }

    public synchronized void addFrame(Image image, long duration) {
        totalDuration += duration;
        frames.add(new Pair<Image,Long>(image,duration));
    }
    
    public void setStartFrame(int startFrame) {
        if (startFrame < frames.size()) {
            this.startFrame = startFrame;
        }
    }

    public synchronized void start() {
        if (frames.size() > 1) {
            reset();
            running = true;
        }
    }
    
    public synchronized void stop() {
        reset();
        running = false;
    }
    
    public synchronized void reset() {
    	animTime = 0;
        currentFrameIndex = startFrame;
        currentFrameEndTime = frames.get(currentFrameIndex).getSecond();
    }
    
    public synchronized boolean isRunning() {
    	return running;
    }

    public synchronized void update(long elapsedTime) {
        if (running) {
            animTime += elapsedTime;
            if (animTime >= totalDuration) {
            	if (repeating) {
            		currentFrameIndex = startFrame;
                        currentFrameEndTime = frames.get(currentFrameIndex).getSecond();
                	animTime = animTime % totalDuration;
                } else {
                	animTime = totalDuration;
                	running = false;
                }
            }
            while (animTime > currentFrameEndTime) {
                currentFrameIndex++;
                if (currentFrameIndex >= frames.size()) {
                    currentFrameIndex = 0;
                }
                currentFrameEndTime += frames.get(currentFrameIndex).getSecond();
            }
        }
    }

    public synchronized Image getImage() {
        Image result = null;
        if (!frames.isEmpty()) {
            result = frames.get(currentFrameIndex).getFirst();
        }
        return result;
    }
}
