package roborace.client;

import java.awt.Graphics;
import java.awt.Image;

import static roborace.client.AnimatedBoard.LOC_SIZE;

public class WallImage {
    
    private final int x;
    private final int y;
    private final Image image;
    
    public WallImage(int x, int y, boolean isHorizontal) {
        if (isHorizontal) {
            this.x = LOC_SIZE*x;
            this.y = LOC_SIZE*y-7;
            image = ImageAndAnimationFactory.getInstance().getHorizontalWallImage();
        } else {
            this.x = LOC_SIZE*x-7;
            this.y = LOC_SIZE*y;
            image = ImageAndAnimationFactory.getInstance().getVerticalWallImage();
        }
    }
    
    public void draw(Graphics graphics) {
        graphics.drawImage(image,x,y,null);
    } 
}
