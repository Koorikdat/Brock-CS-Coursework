package COSC3P91.graphics;

import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.image.BufferedImage;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;
import java.io.File;
import java.io.IOException;
import javax.swing.SwingUtilities;
import javax.imageio.ImageIO;

public class ImageManager {
	
    private static ImageManager instance = null;

    public static ImageManager getInstance() {
        if (instance == null) {
            instance = new ImageManager();
        }
        return instance;
    }

    private final Toolkit toolkit; 			
    private String imagePath = "";	

    private ImageManager() {
        toolkit = Toolkit.getDefaultToolkit();
    }

    public void setImagePath(String path) {
        imagePath = path;
        if (!imagePath.endsWith("/")) {
            imagePath += "/";
        }
    }

    public String getImagePath() {
        return imagePath;
    }

    public BufferedImage loadImage(String file) {
        BufferedImage result = null;
        try {
            result = ImageIO.read(new File(imagePath + file));
        } catch (IOException e) {}
        return result;
    } 
    
    public void saveImage(Image image, String format, String file) {
        int coding = (format.equalsIgnoreCase("png")) ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_BGR;
        BufferedImage bufferedImage = new BufferedImage(image.getWidth(null),image.getHeight(null),coding);
        bufferedImage.createGraphics().drawImage(image,0,0,null);
        try {
            ImageIO.write(bufferedImage,format,new File(imagePath + file));
        } catch(IOException e) {}
    } 
    
    public BufferedImage filterImage(Image image, RGBImageFilter filter) {
        BufferedImage result = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = result.createGraphics();
        g.drawImage(toolkit.createImage(new FilteredImageSource(image.getSource(),filter)),0,0,null);
        g.dispose();
        return result;
    }

    public BufferedImage imageToBufferedImage(Image image) {
        BufferedImage bufImage;
        if (image instanceof BufferedImage) {
            bufImage = (BufferedImage) image;
        } else {
            bufImage = new BufferedImage(image.getWidth(null),image.getHeight(null),BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = bufImage.createGraphics();
            g.drawImage(image,0,0,image.getWidth(null),image.getHeight(null),null);
            g.dispose();
        }
        return bufImage;
    }

    public Image snapShot(Window window) {
        Point origin = new Point(0,0);
        SwingUtilities.convertPointToScreen(origin,window);
        Image result = null;
        try {
            result = new Robot().createScreenCapture(new Rectangle(origin,window.getSize()));
        } catch (AWTException e) {}
        return result;
    }
}